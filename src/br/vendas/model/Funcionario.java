package br.vendas.model;

import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Usuario {

    private static final long serialVersionUID = 1L;

    private int id;
    private boolean ehGerente;

    public Funcionario() {
        super();
    }

    public Funcionario(String nome, String cpf, String login, String email, String senha, boolean ehGerente) {
        super(nome, cpf, login, email, senha, true);
        this.ehGerente = ehGerente;
    }

    public static Funcionario criarFuncionario(String nome, String cpf, String login, String email, String senha, boolean ehGerente) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setLogin(login);
        funcionario.setEmail(email);
        funcionario.setSenha(senha);
        funcionario.setAtivo(true);
        funcionario.setEhGerente(ehGerente);
        return funcionario;
    }

    public void verFuncionario() {
        super.verUsuario();
        System.out.println("ID: " + id);
        System.out.println("Gerente: " + (ehGerente ? "Sim" : "Nao"));
        System.out.println("Ativo: " + (isAtivo() ? "Sim" : "Nao"));
    }

    public void editarFuncionario(String nome, String cpf, String login, String email, String senha, boolean ehGerente) {
        setNome(nome);
        setCpf(cpf);
        setLogin(login);
        setEmail(email);
        setSenha(senha);
        this.ehGerente = ehGerente;
    }

    public static List<Funcionario> listarFuncionarios(List<Funcionario> funcionarios) {
        List<Funcionario> funcionariosAtivos = new ArrayList<>();
        for (Funcionario f : funcionarios) {
            if (f.isAtivo()) {
                funcionariosAtivos.add(f);
            }
        }
        return funcionariosAtivos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEhGerente() {
        return ehGerente;
    }

    public void setEhGerente(boolean ehGerente) {
        this.ehGerente = ehGerente;
    }
}