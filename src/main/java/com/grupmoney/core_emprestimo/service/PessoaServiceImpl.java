package com.grupmoney.core_emprestimo.service;

import com.grupmoney.core_emprestimo.domain.entity.Pessoa;
import com.grupmoney.core_emprestimo.domain.enums.TipoIdentificador;
import com.grupmoney.core_emprestimo.domain.repository.PessoaRepository;
import com.grupmoney.core_emprestimo.rest.dto.PessoaDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    public PessoaDTO buscaPessoaPorId(Long id) {

        Optional<Pessoa> pessoa = pessoaRepository.findPessoaById(id);

        return modelMapper.map(pessoa, PessoaDTO.class);
    }


        public static boolean validar(String identificador) {
            Pattern pattern = Pattern.compile("^\\d{8}$");
            Matcher matcher = pattern.matcher(identificador);

            if (!matcher.find()) {
                return false;
            }

            int primeiroDigito = Integer.parseInt(identificador.substring(0, 1));
            int ultimoDigito = Integer.parseInt(identificador.substring(7));
            return primeiroDigito + ultimoDigito == 9;
        }
        


}
