package com.example.sqlitemarg;

public class Materiais {

    int id;
    String nome;
    float unidade_medida;
    float preco;

    public Materiais(int _id, String _nome, float _unidade_medida, float _preco) {
        this.id = _id;
        this.nome = _nome;
        this.unidade_medida = _unidade_medida;
        this.preco = _preco;
    }

    public Materiais(String _nome, float _unidade_medida, float _preco) {
        this.nome = _nome;
        this.unidade_medida = _unidade_medida;
        this.preco = _preco;
    }

    @Override
    public String toString() {
        return "Materiais{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", unidade_medida=" + unidade_medida +
                ", preco=" + preco +
                '}';
    }

    public Materiais() {

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
