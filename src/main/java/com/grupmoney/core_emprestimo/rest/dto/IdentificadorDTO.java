package com.grupmoney.core_emprestimo.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^\\d{11}$", message = "CPF deve conter 11 digitos")
    private String cpf;

    @CNPJ(message = "CNPJ inválido")
    @Pattern(regexp = "^\\d{14}$", message = "CNPJ deve conter 14 digitos")
    private String CNPJ;

    @Pattern(regexp = "^\\d{8}$", message = "Matricula deve conter 8 digitos")
    private String matriculaEstudante;

    @Pattern(regexp = "^\\d{10}$", message = "Identificador Aposentado deve conter 10 digitos")
    private String identificadorAposentado;

}
