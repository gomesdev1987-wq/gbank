package tech.gmesdev87.gbank.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ Gera o ID automaticamente
    private Long id;

    @Column(nullable = false, length = 100) // ✅ Campo obrigatório e limite de tamanho
    private String nome;

    @Column(length = 100)
    private String sobrenome;

    @Column(nullable = false, unique = true, length = 150) // ✅ E-mail único e obrigatório
    private String email;
}
