package com.example.sqlitemarg;

public class Cliente {

    int preco;
    String nome;

    public Cliente(){

    }

    public  Cliente(int preco, String nome){
        this.preco = preco;
        this.nome = nome;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
