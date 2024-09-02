package com.grupmoney.core_emprestimo.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoDTO {

    private Long idEmprestimo;

    @NotNull(message = "idPessoa não pode ser nulo")
    private Long idPessoa;

    @NotNull(message = "valorEmprestimo não pode ser nulo")
    private BigDecimal valorEmprestimo;

    @NotNull(message = "numeroParcelas não pode ser nulo")
    private Long numeroParcelas;

    private LocalDateTime dataCriacao;
}
