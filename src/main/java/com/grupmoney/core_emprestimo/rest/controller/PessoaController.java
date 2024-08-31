package com.grupmoney.core_emprestimo.rest.controller;

import com.grupmoney.core_emprestimo.rest.dto.PessoaDTO;
import com.grupmoney.core_emprestimo.service.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/pessoa")
@AllArgsConstructor
@RestController
public class PessoaController {

    private PessoaService pessoaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaDTO cadastroPessoa(@Valid @RequestBody PessoaDTO pessoaDTO) {
        return pessoaService.cadastroPessoa(pessoaDTO);
    }
}
