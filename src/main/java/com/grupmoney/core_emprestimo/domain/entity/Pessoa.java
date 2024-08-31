package com.grupmoney.core_emprestimo.domain.entity;

import com.grupmoney.core_emprestimo.domain.enums.TipoIdentificador;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idPessoa;

    @Column(name = "nome")
    private String nome;

    @Column(name = "identificador")
    private String identificador;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Enumerated(EnumType.STRING)
    private TipoIdentificador tipoIdentificador;

    @Column(name = "valor_min_parcela_mensal", precision = 18, scale = 4)
    private BigDecimal valorMinParcelaMensal;

    @Column(name = "valor_max_emprestimo", precision = 18, scale = 4)
    private BigDecimal valorMaxEmprestimo;

}
