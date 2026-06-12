package br.vendas.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    private static final long serialVersionUID = 1L;

    private int id;
    private String endereco;
    private String telefone;
    private LocalDate dataFimSuspensao;
    private boolean suspenso;

    public Cliente() {
        super();
    }

    public Cliente(String nome, String cpf, String login, String email, String senha, boolean ativo, String endereco, String telefone) {
        super(nome, cpf, login, email, senha, ativo);
        this.endereco = endereco;
        this.telefone = telefone;
        this.suspenso = false;
    }

    public static Cliente criarCliente(String nome, String cpf, String login, String email, String senha) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setLogin(login);
        cliente.setEmail(email);
        cliente.setSenha(senha);
        cliente.setAtivo(true);
        cliente.suspenso = false;
        return cliente;
    }

    public void verCliente() {
        super.verUsuario();
        System.out.println("Endereco: " + endereco);
        System.out.println("Telefone: " + telefone);
        System.out.println("Suspenso: " + (suspenso ? "Sim" : "Nao"));
        if (suspenso) {
            System.out.println("Data Fim Suspensao: " + dataFimSuspensao);
        }
    }

    public void editarCliente(String nome,String cpf, String login, String senha, String endereco, String telefone) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setLogin(login);
        super.setSenha(senha);

        this.endereco = endereco;
        this.telefone = telefone;
    }

    public static List<Cliente> listarClientes(List<Cliente> clientes) {
        List<Cliente> clientesAtivos = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (cliente.isAtivo()) {
                clientesAtivos.add(cliente);
            }
        }
        return clientesAtivos;
    }

    public static List<Cliente> listarClientesSuspensos(List<Cliente> clientes) {
        List<Cliente> clientesSuspensos = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (cliente.verificarSuspensao()) {
                clientesSuspensos.add(cliente);
            }
        }
        return clientesSuspensos;
    }

    public boolean verificarSuspensao() {
        if (suspenso && LocalDate.now().isAfter(dataFimSuspensao)) {
            removerSuspensao();
        }
        return suspenso;
    }

    public void removerSuspensao() {
        this.suspenso = false;
        this.dataFimSuspensao = null;
    }

    public void suspender() {
        this.suspenso = true;
        this.dataFimSuspensao = LocalDate.now().plusDays(15);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataFimSuspensao() {
        return dataFimSuspensao;
    }

    public void setDataFimSuspensao(LocalDate dataFimSuspensao) {
        this.dataFimSuspensao = dataFimSuspensao;
    }

    public boolean isSuspenso() {
        return suspenso;
    }

    public void setSuspenso(boolean suspenso) {
        this.suspenso = suspenso;
    }
}
