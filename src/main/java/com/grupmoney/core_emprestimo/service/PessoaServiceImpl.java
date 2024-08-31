package com.grupmoney.core_emprestimo.service;

import com.grupmoney.core_emprestimo.domain.entity.Pessoa;
import com.grupmoney.core_emprestimo.domain.repository.PessoaRepository;
import com.grupmoney.core_emprestimo.rest.dto.PessoaDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PessoaServiceImpl implements PessoaService {

    private ModelMapper modelMapper;

    private PessoaRepository pessoaRepository;

    @Override
    public PessoaDTO cadastroPessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = modelMapper.map(pessoaDTO, Pessoa.class);

        pessoaRepository.save(pessoa);

        return modelMapper.map(pessoa, PessoaDTO.class);
    }

}
