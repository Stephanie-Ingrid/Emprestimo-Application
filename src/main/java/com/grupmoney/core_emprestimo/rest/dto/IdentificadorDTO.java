package com.grupmoney.core_emprestimo.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @CPF(message = "digite o cpf corretamente")
    private String cpf;

    @CNPJ(message = "digite o CNPJ corretamente")
    private String CNPJ;

    @Pattern(regexp = "^\\d{8}$", message = "Deve conter 8 digitos")
    private String matriculaEstudante;

    @Pattern(regexp = "^\\d{10}$", message = "Deve conter 10 digitos")
    private String identificadorAposentado;

}
