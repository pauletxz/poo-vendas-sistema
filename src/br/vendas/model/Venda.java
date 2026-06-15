package br.vendas.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private LocalDate dataVenda;
    private boolean foiPago;
    private boolean devolucao;
    private Cliente cliente;
    private Funcionario funcionario;
    private List<ItemVenda> itens;

    public Venda() {
        this.itens = new ArrayList<>();
    }

    public Venda(LocalDate dataVenda, boolean foiPago) {
        this.dataVenda = dataVenda;
        this.foiPago = foiPago;
        this.devolucao = false;
        this.itens = new ArrayList<>();
    }

    public static Venda criarVenda(LocalDate dataVenda, boolean foiPago) {
        Venda venda = new Venda();
        venda.setDataVenda(dataVenda);
        venda.setFoiPago(foiPago);
        venda.setDevolucao(false);
        return venda;
    }

    public void verVenda() {
        System.out.println("ID: " + id);
        System.out.println("Data: " + dataVenda);
        System.out.println("Pago: " + (foiPago ? "Sim" : "Nao"));
        System.out.println("Devolucao: " + (devolucao ? "Sim" : "Nao"));
        if (cliente != null) {
            System.out.println("Cliente: " + cliente.getNome());
        }
        if (funcionario != null) {
            System.out.println("Funcionario: " + funcionario.getNome());
        }
        System.out.println("Itens:");
        for (ItemVenda item : itens) {
            item.verItemVenda();
        }
        System.out.printf("Total: R$%.2f%n", calcularTotal());
    }

    public Venda editarVenda(LocalDate dataVenda, boolean foiPago) {
        this.dataVenda = dataVenda;
        this.foiPago = foiPago;
        return this;
    }

    public void excluirVenda() {
        this.itens.clear();
        this.foiPago = false;
        this.devolucao = false;
    }

    public void registrarPagamento() {
        this.foiPago = true;
    }

    public void registrarDevolucao(int idVenda) {
        if (this.id == idVenda) {
            this.devolucao = true;
            for (ItemVenda item : itens) {
                if (item.getProduto() != null) {
                    item.getProduto().adicionarEstoque(item.getQuantidade());
                }
            }
        }
    }

    public void adicionarItem(ItemVenda item) {
        this.itens.add(item);
    }

    public static List<Venda> listarVendas(List<Venda> vendas) {
        return new ArrayList<>(vendas);
    }

    public static List<Venda> listarVendasPorData(List<Venda> vendas, LocalDate data) {
        List<Venda> resultado = new ArrayList<>();
        for (Venda venda : vendas) {
            if (venda.getDataVenda() != null && venda.getDataVenda().isEqual(data)) {
                resultado.add(venda);
            }
        }
        return resultado;
    }

    public static List<Venda> listarVendasPorStatusPagamento(List<Venda> vendas, boolean foiPago) {
        List<Venda> resultado = new ArrayList<>();
        for (Venda venda : vendas) {
            if (venda.isFoiPago() == foiPago) {
                resultado.add(venda);
            }
        }
        return resultado;
    }

    public static List<Venda> listarVendasPorCliente(List<Venda> vendas, int idCliente) {
        List<Venda> resultado = new ArrayList<>();
        for (Venda venda : vendas) {
            if (venda.getCliente() != null && venda.getCliente().getId() == idCliente) {
                resultado.add(venda);
            }
        }
        return resultado;
    }

    public static List<Venda> listarVendasPorProduto(List<Venda> vendas, int idProduto) {
        List<Venda> resultado = new ArrayList<>();
        for (Venda venda : vendas) {
            for (ItemVenda item : venda.getItens()) {
                if (item.getProduto() != null && item.getProduto().getId() == idProduto) {
                    resultado.add(venda);
                    break;
                }
            }
        }
        return resultado;
    }

    public static List<Venda> listarVendasPorFuncionario(List<Venda> vendas, int idFuncionario) {
        List<Venda> resultado = new ArrayList<>();
        for (Venda venda : vendas) {
            if (venda.getFuncionario() != null && venda.getFuncionario().getId() == idFuncionario) {
                resultado.add(venda);
            }
        }
        return resultado;
    }

    public static List<Venda> listarVendasDevolvidas(List<Venda> vendas) {
        List<Venda> resultado = new ArrayList<>();
        for (Venda venda : vendas) {
            if (venda.isDevolucao()) {
                resultado.add(venda);
            }
        }
        return resultado;
    }

    public double calcularTotal() {
        double total = 0.0;
        for (ItemVenda item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public boolean isFoiPago() {
        return foiPago;
    }

    public void setFoiPago(boolean foiPago) {
        this.foiPago = foiPago;
    }

    public boolean isDevolucao() {
        return devolucao;
    }

    public void setDevolucao(boolean devolucao) {
        this.devolucao = devolucao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }
}
