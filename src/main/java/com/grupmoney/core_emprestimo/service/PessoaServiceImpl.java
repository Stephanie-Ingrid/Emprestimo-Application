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

@Data
@Slf4j
@Service
@AllArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private ModelMapper modelMapper;

    private PessoaRepository pessoaRepository;

    @Override
    public PessoaDTO cadastroPessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = modelMapper.map(pessoaDTO, Pessoa.class);

        pessoa = validaTipoIdentificador(pessoaDTO, pessoa);

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

    @Override
    public void atualizaPessoa(Long id, PessoaDTO pessoaDTO) {

        Pessoa pessoa = pessoaRepository.findPessoaById(id).orElseThrow(() -> new BadRequestException("Pessoa informada não existe"));

        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setChavePix(pessoaDTO.getChavePix());
        pessoa.setTipoIdentificador(pessoaDTO.getTipoIdentificador());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());

        pessoa = validaTipoIdentificador(pessoaDTO, pessoa);

        try {
            pessoaRepository.save(pessoa);
        } catch (DataAccessException e) {
            log.warn("erro atualizar pessoa com id: " + id);
            throw new DadosDuplicadosException("dados informados já existem");
        }
    }

    private Pessoa validaTipoIdentificador(PessoaDTO pessoaDTO, Pessoa pessoa) {

        IdentificadorDTO identificadorDTO = pessoaDTO.getIdentificador();

        switch (pessoaDTO.getTipoIdentificador()) {
            case PF:
                if (identificadorDTO.getCpf() == null) {
                    throw new BadRequestException("campo CPF não pode ser nulo");
                }

                pessoa.setIdentificador(identificadorDTO.getCpf());
                pessoa.setValorMinParcelaMensal(TipoIdentificador.PF.getValorMinParcelaMensal());
                pessoa.setValorMaxEmprestimo(TipoIdentificador.PF.getValorMaxEmprestimo());
                break;

            case PJ:

                if (identificadorDTO.getCNPJ() == null) {
                    throw new BadRequestException("campo CNPJ não pode ser nulo");
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
                if (!ValidacaoPessoa.isValidIdentificadorAposentado(identificadorDTO.getIdentificadorAposentado())) {
                    throw new BadRequestException("Identificador de Aposentado inválido");
                }
                pessoa.setIdentificador(identificadorDTO.getIdentificadorAposentado());
                pessoa.setValorMinParcelaMensal(TipoIdentificador.AP.getValorMinParcelaMensal());
                pessoa.setValorMaxEmprestimo(TipoIdentificador.AP.getValorMaxEmprestimo());
                break;
        }
        return pessoa;
    }

}



