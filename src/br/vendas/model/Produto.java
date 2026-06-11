package br.vendas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String descricao;
    private String categoria;
    private int quantidadeEstoque;
    private double preco;

    public Produto() {
    }

    public Produto(int id, String nome, String descricao, String categoria, int quantidadeEstoque, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidadeEstoque = quantidadeEstoque;
        this.preco = preco;
    }

    public static Produto criarProduto(int id, String nome, String descricao, String categoria, int quantidadeEstoque, double preco) {
        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setCategoria(categoria);
        produto.setQuantidadeEstoque(quantidadeEstoque);
        produto.setPreco(preco);
        return produto;
    }

    public void verProduto() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Descricao: " + descricao);
        System.out.println("Categoria: " + categoria);
        System.out.println("Quantidade em Estoque: " + quantidadeEstoque);
        System.out.printf("Preco: R$%.2f%n", preco);
    }

    public void adicionarEstoque(int quantidade) {
        this.quantidadeEstoque += quantidade;
    }

    public void removerEstoque(int quantidade) {
        if (quantidade <= this.quantidadeEstoque) {
            this.quantidadeEstoque -= quantidade;
        } else {
            System.out.println("Quantidade insuficiente em estoque.");
        }
    }

    public static List<Produto> listarProdutos(List<Produto> produtos) {
        List<Produto> produtosDisponiveis = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.quantidadeEstoque > 0) {
                produtosDisponiveis.add(produto);
            }
        }
        return produtosDisponiveis;
    }

    public static List<Produto> listarProdutosSemEstoque(List<Produto> produtos) {
        List<Produto> produtosSemEstoque = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.quantidadeEstoque == 0) {
                produtosSemEstoque.add(produto);
            }
        }
        return produtosSemEstoque;
    }

    public void editarProduto(String nome, String descricao, String categoria, int quantidadeEstoque, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidadeEstoque = quantidadeEstoque;
        this.preco = preco;
    }

    public void excluirProduto() {
        this.quantidadeEstoque = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
