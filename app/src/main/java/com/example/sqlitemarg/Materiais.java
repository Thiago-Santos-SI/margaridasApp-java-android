package com.example.sqlitemarg;

public class Materiais {

    int id;
    String nome;
    float unidade_medida;
    float preco;

    public Materiais(String nome, float unidade_medida, float preco, int id) {
        this.nome = nome;
        this.unidade_medida = unidade_medida;
        this.preco = preco;
        this.id = id;
    }

    public Materiais(String nome, float unidade_medida, float preco) {
        this.nome = nome;
        this.unidade_medida = unidade_medida;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getUnidade_medida() {
        return unidade_medida;
    }

    public void setUnidade_medida(float unidade_medida) {
        this.unidade_medida = unidade_medida;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
