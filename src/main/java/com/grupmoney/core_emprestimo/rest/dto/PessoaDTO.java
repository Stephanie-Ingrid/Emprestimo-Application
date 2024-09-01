package com.grupmoney.core_emprestimo.rest.dto;

import com.grupmoney.core_emprestimo.domain.enums.TipoIdentificador;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    @NotNull(message = "campo nome é obrigatório")
    private String nome;

    @NotNull(message = "campo identificador é obrigatório")
    private IdentificadorDTO identificador;

    @NotNull(message = "campo data nascimento é obrigatório")
    private Date dataNascimento;

    @NotNull(message = "campo tipo identificador é obrigatório")
    private TipoIdentificador tipoIdentificador;

    @NotNull(message = "campo valor minimo parcela mensal é obrigatório")
    private BigDecimal valorMinParcelaMensal;

    @NotNull(message = "campo valor maximo emprestimo é obrigatório")
    private BigDecimal valorMaxEmprestimo;
}
