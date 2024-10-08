package com.grupmoney.core_emprestimo.service;

import com.grupmoney.core_emprestimo.rest.dto.PessoaDTO;

public interface PessoaService {

    PessoaDTO cadastroPessoa(PessoaDTO pessoaDTO);

    PessoaDTO buscaPessoaPorId(Long id);

    void deletarPessoaPorId(Long id);

    void atualizaPessoa(Long id, PessoaDTO pessoaDTO);
}
