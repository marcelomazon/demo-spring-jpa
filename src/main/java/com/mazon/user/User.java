package com.mazon.user;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "email", length = 150, nullable = false)
    @NotNull(message = "O e-mail é obrigatório")
    private String email;

    @Column(name = "dh_criacao", nullable = false)
    private LocalDateTime dhCriacao;

    @Column(name = "nm_usuario_criacao", nullable = false)
    private String nomeUsuarioCriacao;

    @Column(name = "dh_atualizacao")
    private LocalDateTime dhAtualizacao;

    @Column(name = "nm_usuario_atualizacao")
    private String nomeUsuarioAtualizacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDhCriacao() {
        return dhCriacao;
    }

    public void setDhCriacao(LocalDateTime dhCriacao) {
        this.dhCriacao = dhCriacao;
    }

    public String getNomeUsuarioCriacao() {
        return nomeUsuarioCriacao;
    }

    public void setNomeUsuarioCriacao(String nomeUsuarioCriacao) {
        this.nomeUsuarioCriacao = nomeUsuarioCriacao;
    }

    public LocalDateTime getDhAtualizacao() {
        return dhAtualizacao;
    }

    public void setDhAtualizacao(LocalDateTime dhAtualizacao) {
        this.dhAtualizacao = dhAtualizacao;
    }

    public String getNomeUsuarioAtualizacao() {
        return nomeUsuarioAtualizacao;
    }

    public void setNomeUsuarioAtualizacao(String nomeUsuarioAtualizacao) {
        this.nomeUsuarioAtualizacao = nomeUsuarioAtualizacao;
    }
}
