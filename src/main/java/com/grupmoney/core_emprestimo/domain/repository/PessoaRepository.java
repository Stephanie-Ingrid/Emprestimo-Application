package com.grupmoney.core_emprestimo.domain.repository;

import com.grupmoney.core_emprestimo.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findPessoaById(Long id);

    void deletePessoaById(Long id);
}
