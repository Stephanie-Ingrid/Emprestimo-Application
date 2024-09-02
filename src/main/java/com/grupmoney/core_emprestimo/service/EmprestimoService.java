package com.grupmoney.core_emprestimo.service;

import com.grupmoney.core_emprestimo.rest.dto.EmprestimoDTO;

import java.util.List;

public interface EmprestimoService {

    EmprestimoDTO realizaEmprestimo(EmprestimoDTO emprestimoDTO);

    List<EmprestimoDTO> buscaTodosEmprestimos();
}
