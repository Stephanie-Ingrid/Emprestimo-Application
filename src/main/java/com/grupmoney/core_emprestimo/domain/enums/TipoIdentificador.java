package com.grupmoney.core_emprestimo.domain.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum TipoIdentificador {

    PF(new BigDecimal(300), new BigDecimal(10000)),
    PJ(new BigDecimal(1000), new BigDecimal(100000)),
    EU(new BigDecimal(100), new BigDecimal(10000)),
    AP(new BigDecimal(400), new BigDecimal(25000));

    private BigDecimal valorMinParcelaMensal ;
    private BigDecimal valorMaxEmprestimo;


    TipoIdentificador(BigDecimal valorMinParcelaMensal, BigDecimal valorMaxEmprestimo) {
        this.valorMinParcelaMensal = valorMinParcelaMensal;
        this.valorMaxEmprestimo = valorMaxEmprestimo;
    }
}
