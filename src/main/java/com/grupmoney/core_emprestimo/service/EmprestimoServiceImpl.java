package com.grupmoney.core_emprestimo.service;

import com.grupmoney.core_emprestimo.domain.entity.Emprestimo;
import com.grupmoney.core_emprestimo.domain.enums.StatusPagamento;
import com.grupmoney.core_emprestimo.domain.enums.TipoIdentificador;
import com.grupmoney.core_emprestimo.domain.repository.EmprestimoRepository;
import com.grupmoney.core_emprestimo.exception.BadRequestException;
import com.grupmoney.core_emprestimo.exception.IntegrationErrorException;
import com.grupmoney.core_emprestimo.integration.PagamentoIntegration;
import com.grupmoney.core_emprestimo.integration.PagamentoRequest;
import com.grupmoney.core_emprestimo.integration.PagamentoResponse;
import com.grupmoney.core_emprestimo.rest.dto.EmprestimoDTO;
import com.grupmoney.core_emprestimo.rest.dto.PessoaDTO;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
@Slf4j
@AllArgsConstructor
public class EmprestimoServiceImpl implements EmprestimoService {

    private ModelMapper modelMapper;

    private EmprestimoRepository emprestimoRepository;

    private PessoaService pessoaService;

    private PagamentoIntegration pagamentoIntegration;


    @Override
    @Transactional
    public EmprestimoDTO realizaEmprestimo(EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = modelMapper.map(emprestimoDTO, Emprestimo.class);

        PessoaDTO pessoa = pessoaService.buscaPessoaPorId(emprestimoDTO.getIdPessoa());

        validaEmprestimo(emprestimoDTO, pessoa.getTipoIdentificador());

        emprestimo.setDataCriacao(LocalDateTime.now());

        emprestimoRepository.save(emprestimo);

        PagamentoRequest novoPagamento = PagamentoRequest.builder()
                .idEmprestimo(emprestimo.getIdEmprestimo())
                .valorPagamento(emprestimo.getValorEmprestimo())
                .chavePix(pessoa.getChavePix())
                .build();

        try {
            PagamentoResponse solicitacaoPagamento = pagamentoIntegration.pagamentoresponse(novoPagamento);
        } catch (FeignException e) {
            log.error("erro na integração de pagamento, emprestimo id: " + novoPagamento.getIdEmprestimo());
            throw new IntegrationErrorException();
        }

        emprestimo.setStatusPagamento(StatusPagamento.CONFIRMADO);

        emprestimoRepository.save(emprestimo);

        return modelMapper.map(emprestimo, EmprestimoDTO.class);
    }

    @Override
    public List<EmprestimoDTO> buscaTodosEmprestimos() {
        List<Emprestimo> emprestimoList = emprestimoRepository.findAll();
        Type listType = new TypeToken<ArrayList<EmprestimoDTO>>() {
        }.getType();

        return modelMapper.map(emprestimoList, listType);
    }

    public void validaEmprestimo(EmprestimoDTO emprestimoDTO, TipoIdentificador tipoIdentificador) {

        if (tipoIdentificador == null) {
            throw new BadRequestException("Pessoa informada não está cadastrada");
        }

        BigDecimal valorEmprestimo = emprestimoDTO.getValorEmprestimo();
        Long numeroParcelas = emprestimoDTO.getNumeroParcelas();
        BigDecimal valorParcela = valorEmprestimo.divide(BigDecimal.valueOf(numeroParcelas), RoundingMode.HALF_UP);

        if (valorEmprestimo.compareTo(tipoIdentificador.getValorMaxEmprestimo()) > 0) {
            throw new BadRequestException("O valor do empréstimo excede o limite máximo permitido para este tipo de identificador.");
        }

        if (valorParcela.compareTo(tipoIdentificador.getValorMinParcelaMensal()) < 0) {
            throw new BadRequestException("O valor da parcela é inferior ao valor mínimo permitido para este tipo de identificador.");
        }

        if (numeroParcelas > 24) {
            throw new BadRequestException("O número de parcelas não pode exceder 24.");
        }
    }

}
