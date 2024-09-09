package com.grupmoney.core_emprestimo.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.grupmoney.core_emprestimo.domain.enums.TipoIdentificador;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PessoaDTO {

    private Long id;

    @NotNull(message = "campo nome é obrigatório")
    private String nome;

    @Valid
    @NotNull(message = "campo identificador é obrigatório")
    private IdentificadorDTO identificador;

    @NotNull(message = "campo data nascimento é obrigatório")
    private Date dataNascimento;

    @NotNull(message = "campo tipo identificador é obrigatório")
    private TipoIdentificador tipoIdentificador;

    @NotNull(message = "campo chavePix é obrigatório")
    private String chavePix;

    private BigDecimal valorMinParcelaMensal;

    private BigDecimal valorMaxEmprestimo;
}
