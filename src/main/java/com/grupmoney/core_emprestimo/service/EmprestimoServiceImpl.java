package com.grupmoney.core_emprestimo.service;

import com.grupmoney.core_emprestimo.domain.entity.Emprestimo;
import com.grupmoney.core_emprestimo.domain.repository.EmprestimoRepository;
import com.grupmoney.core_emprestimo.exception.BadRequestException;
import com.grupmoney.core_emprestimo.rest.dto.EmprestimoDTO;
import com.grupmoney.core_emprestimo.rest.dto.PessoaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
@Data
@AllArgsConstructor
public class EmprestimoServiceImpl implements EmprestimoService {

    private ModelMapper modelMapper;

    private EmprestimoRepository emprestimoRepository;

    private PessoaService pessoaService;


    @Override
    public EmprestimoDTO realizaEmprestimo(EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = modelMapper.map(emprestimoDTO, Emprestimo.class);

        validaEmprestimo(emprestimoDTO);

        emprestimo.setDataCriacao(LocalDateTime.now());

        emprestimoRepository.save(emprestimo);

        return modelMapper.map(emprestimo, EmprestimoDTO.class);
    }


    public void validaEmprestimo(EmprestimoDTO emprestimoDTO) {

        PessoaDTO pessoa = pessoaService.buscaPessoaPorId(emprestimoDTO.getIdPessoa());

        if (pessoa == null) {
            throw new BadRequestException("Pessoa informada não está cadastrada");
        }

        BigDecimal valorEmprestimo = emprestimoDTO.getValorEmprestimo();
        Long numeroParcelas = emprestimoDTO.getNumeroParcelas();
        BigDecimal valorParcela = valorEmprestimo.divide(BigDecimal.valueOf(numeroParcelas), RoundingMode.HALF_UP);

        if (valorEmprestimo.compareTo(pessoa.getTipoIdentificador().getValorMaxEmprestimo()) > 0) {
            throw new BadRequestException("O valor do empréstimo excede o limite máximo permitido para este tipo de identificador.");
        }

        if (valorParcela.compareTo(pessoa.getTipoIdentificador().getValorMinParcelaMensal()) < 0) {
            throw new BadRequestException("O valor da parcela é inferior ao valor mínimo permitido para este tipo de identificador.");
        }

        if (numeroParcelas > 24) {
            throw new BadRequestException("O número de parcelas não pode exceder 24.");
        }
    }

}
