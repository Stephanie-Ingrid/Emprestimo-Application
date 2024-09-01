package com.grupmoney.core_emprestimo.rest.controller;

import com.grupmoney.core_emprestimo.rest.dto.EmprestimoDTO;
import com.grupmoney.core_emprestimo.service.EmprestimoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/emprestimo")
@AllArgsConstructor
@RestController
public class EmprestimoController {

    private EmprestimoService emprestimoService;


    @PostMapping
    @ResponseStatus(CREATED)
    public EmprestimoDTO realizarEmprestimo(@RequestBody @Valid EmprestimoDTO emprestimoDTO){
        return emprestimoService.realizaEmprestimo(emprestimoDTO);
    }

}
