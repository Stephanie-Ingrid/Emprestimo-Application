package com.grupmoney.core_emprestimo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErros {

    private List<String> erros;

    public ApiErros(String mensagemErro) {
        this.erros = Arrays.asList(mensagemErro);
    }
}
