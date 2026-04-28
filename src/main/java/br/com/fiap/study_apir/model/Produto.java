package br.com.fiap.study_apir.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    private Long id;
    private String nome;
    private BigDecimal valor;


    public Produto(Long id, String nome, BigDecimal valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public Produto() {
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }



    
}
