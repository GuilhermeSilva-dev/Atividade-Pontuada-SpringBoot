package com.example.AtividadePontuada.model;

import jakarta.persistence.*;

// @Entity: Avisa ao Hibernate que esta classe é uma entidade JPA (vira uma tabela no banco).
@Entity
// @Table: Define o nome real da tabela no MySQL. É bom usar prefixos como 'tab_' para organização.
@Table(name = "tab_produto")
public class ProdutoModel {

    // @Id: Define que este campo é a chave primária (PK).
    // @GeneratedValue: Define o auto-incremento. O banco gera o ID sozinho.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Esses atributos viram as colunas da tabela automaticamente.
    private String nome;
    private String lote;
    private String validade; // Dica: para datas reais, costumamos usar LocalDate, mas String funciona para iniciantes.
    private String categoria;
    private int quantidade;

    // Construtor vazio: OBRIGATÓRIO para o JPA conseguir instanciar o objeto vindo do banco.
    public ProdutoModel() {
    }

    // Construtor cheio: Facilita na hora de criar objetos nos testes ou no Service.
    public ProdutoModel(Long id, String nome, String lote, String validade, String categoria, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.lote = lote;
        this.validade = validade;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }

    // Getters e Setters: Essenciais para o encapsulamento e para o Spring ler/escrever os dados.
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

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
