package com.grupmoney.core_emprestimo.domain.repository;

import com.grupmoney.core_emprestimo.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
