package com.grupmoney.validacoes;

import com.grupmoney.core_emprestimo.exception.BadRequestException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValidacaoPessoa {

    public static boolean isValidIdentificadorAposentado(String aposentado) {

        if (aposentado == null) {
            throw new BadRequestException("Informe seu identificador");
        }

        char ultimoDig = aposentado.charAt(9);

        String ultimoDigConvert = String.valueOf(ultimoDig);

        String restante = aposentado.substring(0, aposentado.length() - 1);

        return !restante.contains(ultimoDigConvert);
    }

    public static boolean validaEstudante(String estudante) {

        if (estudante == null) {
            throw new BadRequestException("Informe sua matricula");
        }

        char primeiroDigito = estudante.charAt(0);
        char ultimoDigito = estudante.charAt(7);

        String primeiroDigitoConvert = String.valueOf(primeiroDigito);
        String ultimoDigitoConvert = String.valueOf(ultimoDigito);

        Integer primeiroNumero = Integer.parseInt(primeiroDigitoConvert);
        Integer ultimoNumero = Integer.parseInt(ultimoDigitoConvert);

        if (primeiroNumero + ultimoNumero != 9) {
            throw new BadRequestException("Matricula Inválida");
        }

        return true;
    }
}

