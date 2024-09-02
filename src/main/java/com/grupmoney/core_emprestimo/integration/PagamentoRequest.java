package com.grupmoney.core_emprestimo.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoRequest {

    private Long idEmprestimo;

    private BigDecimal valorPagamento;

    private String chavePix;

}