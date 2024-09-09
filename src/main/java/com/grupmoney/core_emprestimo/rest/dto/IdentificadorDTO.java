package com.grupmoney.core_emprestimo.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdentificadorDTO {

    @CPF(message = "CPF inválido")
    private String cpf;

    @CNPJ(message = "CNPJ inválido")
    private String CNPJ;

    private String matriculaEstudante;

    private String identificadorAposentado;

}
