package br.vendas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemVenda implements Serializable {

    private static final long serialVersionUID = 1L;

    private Produto produto;
    private int quantidade;

    public ItemVenda() {
    }

    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public void verItemVenda() {
        if (produto != null) {
            System.out.println("Produto: " + produto.getNome());
            System.out.printf("Preco unitario: R$%.2f%n", produto.getPreco());
        } else {
            System.out.println("Produto: Nao informado");
        }

        System.out.println("Quantidade: " + quantidade);
        System.out.printf("Subtotal: R$%.2f%n", calcularSubtotal());
    }

    public void criarItemVenda(int quantidade) {
        if (quantidade < 0) {
            System.out.println("Quantidade invalida.");
            return;
        }

        if (produto != null && quantidade > produto.getQuantidadeEstoque()) {
            System.out.println("Quantidade maior que o estoque disponivel.");
            return;
        }

        this.quantidade = quantidade;
    }

    public static List<ItemVenda> listarItemVendaPorVenda(List<Venda> vendas, int idVenda) {
        for (Venda venda : vendas) {
            if (venda.getId() == idVenda) {
                return venda.getItens();
            }
        }
        return new ArrayList<>();
    }

    public void editarItemVenda(int quantidade) {
        if (quantidade < 0) {
            System.out.println("Quantidade invalida.");
            return;
        }

        if (produto != null && quantidade > produto.getQuantidadeEstoque()) {
            System.out.println("Quantidade maior que o estoque disponivel.");
            return;
        }

        this.quantidade = quantidade;
    }

    public void excluirItemVenda() {
        this.quantidade = 0;
        this.produto = null;
    }

    public double calcularSubtotal() {
        if (produto == null) {
            return 0.0;
        }

        return quantidade * produto.getPreco();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}