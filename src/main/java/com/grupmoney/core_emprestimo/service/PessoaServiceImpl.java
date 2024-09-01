package com.grupmoney.core_emprestimo.service;

import com.grupmoney.core_emprestimo.domain.entity.Pessoa;
import com.grupmoney.core_emprestimo.domain.enums.TipoIdentificador;
import com.grupmoney.core_emprestimo.domain.repository.PessoaRepository;
import com.grupmoney.core_emprestimo.exception.BadRequestException;
import com.grupmoney.core_emprestimo.exception.DadosDuplicadosException;
import com.grupmoney.core_emprestimo.rest.dto.IdentificadorDTO;
import com.grupmoney.core_emprestimo.rest.dto.PessoaDTO;
import com.grupmoney.validacoes.ValidacaoPessoa;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@Slf4j
@AllArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private ModelMapper modelMapper;

    private PessoaRepository pessoaRepository;

    @Override
    public PessoaDTO cadastroPessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = modelMapper.map(pessoaDTO, Pessoa.class);

        IdentificadorDTO identificadorDTO = pessoaDTO.getIdentificador();

        switch (pessoaDTO.getTipoIdentificador()) {
            case PF:
                if (!ValidacaoPessoa.validaCPF(identificadorDTO.getCpf())) {
                    throw new BadRequestException("CPF inválido");
                }
                pessoa.setIdentificador(identificadorDTO.getCpf());
                pessoa.setValorMinParcelaMensal(TipoIdentificador.PF.getValorMinParcelaMensal());
                pessoa.setValorMaxEmprestimo(TipoIdentificador.PF.getValorMaxEmprestimo());
                break;

            case PJ:
                if (!ValidacaoPessoa.validaCNPJ(identificadorDTO.getCNPJ())) {
                    throw new BadRequestException("CNPJ inválido");
                }
                pessoa.setIdentificador(identificadorDTO.getCNPJ());
                pessoa.setValorMinParcelaMensal(TipoIdentificador.PJ.getValorMinParcelaMensal());
                pessoa.setValorMaxEmprestimo(TipoIdentificador.PJ.getValorMaxEmprestimo());
                break;

            case EU:
                if (!ValidacaoPessoa.validaEstudante(identificadorDTO.getMatriculaEstudante())) {
                    throw new BadRequestException("Identificador de Estudante Universitário inválido");
                }
                pessoa.setIdentificador(identificadorDTO.getMatriculaEstudante());
                pessoa.setValorMinParcelaMensal(TipoIdentificador.EU.getValorMinParcelaMensal());
                pessoa.setValorMaxEmprestimo(TipoIdentificador.EU.getValorMaxEmprestimo());
                break;

            case AP:
                if (!ValidacaoPessoa.validaCaractersAposentado(identificadorDTO.getIdentificadorAposentado())
                        || !ValidacaoPessoa.isValidIdentificadorAposentado(identificadorDTO.getIdentificadorAposentado())) {
                    throw new BadRequestException("Identificador de Aposentado inválido");
                }
                pessoa.setIdentificador(identificadorDTO.getIdentificadorAposentado());
                pessoa.setValorMinParcelaMensal(TipoIdentificador.AP.getValorMinParcelaMensal());
                pessoa.setValorMaxEmprestimo(TipoIdentificador.AP.getValorMaxEmprestimo());
                break;
        }

        try {
            pessoaRepository.save(pessoa);

        } catch (DataAccessException e) {
            log.warn("erro ao salvar usuario na base");
            throw new DadosDuplicadosException("Pessoa informada já está cadastrada");
        }

        PessoaDTO pessoaCadastradaDTO = modelMapper.map(pessoa, PessoaDTO.class);
        pessoaCadastradaDTO.setIdentificador(pessoaDTO.getIdentificador());

        return pessoaCadastradaDTO;
    }


    @Override
    public PessoaDTO buscaPessoaPorId(Long id) {

        Optional<Pessoa> pessoa = pessoaRepository.findPessoaById(id);

        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    @Override
    @Transactional
    public void deletarPessoaPorId(Long id) {
        pessoaRepository.deletePessoaById(id);
    }


}



