package com.example.listacompras.model;

import jakarta.persistence.*;

@Entity
@Table(name = "itens")
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column
    private String categoria;
    
    @Column
    private Integer quantidade;
    
    @Column
    private Boolean comprado = false;
    
    @Column
    private String observacoes;
    
    public Item() {
    }
    
    public Item(String nome, String categoria, Integer quantidade) {
        this();
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public Integer getQuantidade() {
        return quantidade;
    }
    
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    public Boolean getComprado() {
        return comprado;
    }
    
    public void setComprado(Boolean comprado) {
        this.comprado = comprado;
    }
    
    public String getObservacoes() {
        return observacoes;
    }
    
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}