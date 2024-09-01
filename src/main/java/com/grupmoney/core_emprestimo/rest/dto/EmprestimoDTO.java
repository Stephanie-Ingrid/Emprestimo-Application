package com.grupmoney.core_emprestimo.rest.dto;

import com.grupmoney.core_emprestimo.domain.entity.Pessoa;
import com.grupmoney.core_emprestimo.domain.enums.StatusPagamento;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EmprestimoDTO {

    private Long idEmprestimo;

    private Pessoa pessoa;

    private BigDecimal valorEmprestimo;

    @Size(max = 24)
    private Long numeroParcelas;

    private StatusPagamento statusPagamento;

    private LocalDateTime dataCriacao;
}
