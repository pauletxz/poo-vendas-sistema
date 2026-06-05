package br.vendas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String cpf;
    private String login;
    private String email;
    private String senha;
    private boolean ativo;

    public Usuario() {
    }

    public Usuario(String nome, String cpf, String login, String email, String senha, boolean ativo) {
        this.nome = nome;
        this.cpf = cpf;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
    }

    public static Usuario criarUsuario(String nome, String cpf, String login, String email, String senha) {
        Usuario usuario = new Usuario() {};
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setLogin(login);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setAtivo(true);
        return usuario;
    }

    public void verUsuario() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Login: " + login);
        System.out.println("Email: " + email);
        System.out.println("Ativo: " + (ativo ? "Sim" : "Nao"));
    }

    public boolean autenticar(String loginInformado, String senhaInformada) {
        return this.ativo
                && this.login.equals(loginInformado)
                && this.senha.equals(senhaInformada);
    }

    public static List<Usuario> listarUsuarios(List<Usuario> usuarios) {
        List<Usuario> usuariosAtivos = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario.ativo) {
                usuariosAtivos.add(usuario);
            }
        }
        return usuariosAtivos;
    }

    public void desativarUsuario() {
        this.ativo = false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
