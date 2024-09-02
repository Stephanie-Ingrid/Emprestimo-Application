package com.grupmoney.core_emprestimo.rest.controller;

import com.grupmoney.core_emprestimo.rest.dto.PessoaDTO;
import com.grupmoney.core_emprestimo.service.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RequestMapping("/pessoa")
@AllArgsConstructor
@RestController
public class PessoaController {

    private PessoaService pessoaService;

    @PostMapping
    @ResponseStatus(CREATED)
    public PessoaDTO cadastroPessoa(@Valid @RequestBody PessoaDTO pessoaDTO) {
        return pessoaService.cadastroPessoa(pessoaDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public PessoaDTO buscaPessoaPorId(@PathVariable Long id) {
        return pessoaService.buscaPessoaPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletaPessoaPorId(@PathVariable Long id) {
        pessoaService.deletarPessoaPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void atualizaPessoa (@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO){
        pessoaService.atualizaPessoa(id, pessoaDTO);
    }


}
