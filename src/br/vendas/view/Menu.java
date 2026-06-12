package br.vendas.view;

import br.vendas.model.Cliente;
import br.vendas.model.Funcionario;
import br.vendas.model.Produto;
import br.vendas.util.Persistencia;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

private static Scanner scanner = new Scanner(System.in);
private static List<Cliente> clientes = Persistencia.carregar("clientes.dat");
private static List<Produto> produtos = Persistencia.carregar("produtos.dat");
private static List<Funcionario> funcionarios = Persistencia.carregar("funcionarios.dat");
private static int proximoIdCliente = 1;
private static int proximoIdProduto = 1;
private static int proximoIdFuncionario = 1;

public static void main(String[] args) {
    garantirGerentePadrao();

    while (true) {
        limparTela();
        System.out.println("===== SISTEMA DE VENDAS =====");
        System.out.println("1 - Entrar como funcionario");
        System.out.println("2 - Entrar como cliente");
        System.out.println("3 - Criar conta de cliente");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opcao: ");

        int opcao = lerInteiro();

        switch (opcao) {
            case 0 -> {
                System.out.println("Encerrando o sistema.");
                return;
            }
            case 1 -> {
                Funcionario f = loginFuncionario();
                if (f != null) {
                    menuFuncionario(f);
                }
            }
            case 2 -> {
                Cliente c = loginCliente();
                if (c != null) {
                    menuCliente(c);
                }
            }
            case 3 -> {
                cadastrarCliente();
                pausar();
            }
            default -> {
                System.out.println("Opcao inexistente.");
                pausar();
            }
        }
    }
}

private static void garantirGerentePadrao() {
    if (funcionarios.isEmpty()) {
        Funcionario admin = Funcionario.criarFuncionario("Administrador", "00000000000", "admin", "admin@sistema.com", "admin", true);
        admin.setId(proximoIdFuncionario);
        proximoIdFuncionario++;
        funcionarios.add(admin);
        Persistencia.salvar(funcionarios, "funcionarios.dat");
    }
}

private static Funcionario loginFuncionario() {
    limparTela();
    System.out.println("===== LOGIN FUNCIONARIO =====");
    String login = lerTextoObrigatorio("Login: ");
    String senha = lerTextoObrigatorio("Senha: ");

    for (Funcionario f : funcionarios) {
        if (f.autenticar(login, senha)) {
            return f;
        }
    }
    System.out.println("Login ou senha invalidos.");
    pausar();
    return null;
}

private static Cliente loginCliente() {
    limparTela();
    System.out.println("===== LOGIN CLIENTE =====");
    String login = lerTextoObrigatorio("Login: ");
    String senha = lerTextoObrigatorio("Senha: ");

    for (Cliente c : clientes) {
        if (c.autenticar(login, senha)) {
            return c;
        }
    }
    System.out.println("Login ou senha invalidos.");
    pausar();
    return null;
}

private static void menuFuncionario(Funcionario funcionario) {
    int opcao = -1;
    while (opcao != 0) {
        limparTela();
        System.out.println("===== MENU FUNCIONARIO =====");
        System.out.println("Bem-vindo, " + funcionario.getNome());
        System.out.println("1 - Clientes");
        if (funcionario.isEhGerente()) {
            System.out.println("2 - Produtos");
            System.out.println("3 - Funcionarios");
        }
        System.out.println("0 - Sair (logout)");
        System.out.print("Escolha uma opcao: ");

        opcao = lerInteiro();

        switch (opcao) {
            case 1: submenuClientes(); break;
            case 2: if (funcionario.isEhGerente()) { submenuProdutos(); } else { acessoNegado(); pausar(); } break;
            case 3: if (funcionario.isEhGerente()) { submenuFuncionarios(); } else { acessoNegado(); pausar(); } break;
            case 0: System.out.println("Logout realizado."); break;
            default: System.out.println("Opcao inexistente."); pausar();
        }
    }
}

private static void submenuClientes() {
    int opcao = -1;
    while (opcao != 0) {
        limparTela();
        System.out.println("===== CLIENTES =====");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Listar clientes");
        System.out.println("3 - Editar cliente");
        System.out.println("4 - Desativar cliente");
        System.out.println("5 - Listar clientes suspensos");
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opcao: ");

        opcao = lerInteiro();

        switch (opcao) {
            case 1: cadastrarCliente(); pausar(); break;
            case 2: listarClientes(); pausar(); break;
            case 3: editarCliente(); pausar(); break;
            case 4: desativarCliente(); pausar(); break;
            case 5: listarClientesSuspensos(); pausar(); break;
            case 0: break;
            default: System.out.println("Opcao inexistente."); pausar();
        }
    }
}

private static void submenuProdutos() {
    int opcao = -1;
    while (opcao != 0) {
        limparTela();
        System.out.println("===== PRODUTOS =====");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Editar produto");
        System.out.println("4 - Gerenciar estoque");
        System.out.println("5 - Excluir produto");
        System.out.println("6 - Listar produtos sem estoque");
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opcao: ");

        opcao = lerInteiro();

        switch (opcao) {
            case 1: cadastrarProduto(); pausar(); break;
            case 2: listarProdutos(); pausar(); break;
            case 3: editarProduto(); pausar(); break;
            case 4: gerenciarEstoque(); pausar(); break;
            case 5: excluirProduto(); pausar(); break;
            case 6: listarProdutosSemEstoque(); pausar(); break;
            case 0: break;
            default: System.out.println("Opcao inexistente."); pausar();
        }
    }
}

private static void submenuFuncionarios() {
    int opcao = -1;
    while (opcao != 0) {
        limparTela();
        System.out.println("===== FUNCIONARIOS =====");
        System.out.println("1 - Cadastrar funcionario");
        System.out.println("2 - Listar funcionarios");
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opcao: ");

        opcao = lerInteiro();

        switch (opcao) {
            case 1: cadastrarFuncionario(); pausar(); break;
            case 2: listarFuncionarios(); pausar(); break;
            case 0: break;
            default: System.out.println("Opcao inexistente."); pausar();
        }
    }
}

private static void menuCliente(Cliente cliente) {
    int opcao = -1;
    while (opcao != 0) {
        limparTela();
        System.out.println("===== MENU CLIENTE =====");
        System.out.println("Bem-vindo, " + cliente.getNome());
        System.out.println("1 - Ver catalogo de produtos");
        System.out.println("2 - Ver meus dados");
        System.out.println("3 - Editar meus dados");
        System.out.println("0 - Sair (logout)");
        System.out.print("Escolha uma opcao: ");

        opcao = lerInteiro();

        switch (opcao) {
            case 1: listarProdutos(); pausar(); break;
            case 2: limparTela(); cliente.verCliente(); pausar(); break;
            case 3: limparTela(); editarCliente(); pausar(); break;
            case 0: System.out.println("Logout realizado."); break;
            default: System.out.println("Opcao inexistente."); pausar();
        }
    }
}

private static void acessoNegado() {
    System.out.println("Acesso negado. Apenas gerentes podem realizar esta acao.");
}

private static void cadastrarCliente() {
    limparTela();
    System.out.println("===== CADASTRAR CLIENTE =====");
    String nome = lerTextoObrigatorio("Nome: ");
    String cpf = lerTextoObrigatorio("CPF: ");
    String login = lerTextoObrigatorio("Login: ");
    String email = lerTextoObrigatorio("Email: ");
    String senha = lerTextoObrigatorio("Senha: ");
    String endereco = lerTextoObrigatorio("Endereco: ");
    String telefone = lerTextoObrigatorio("Telefone: ");

    Cliente cliente = Cliente.criarCliente(nome, cpf, login, email, senha);
    cliente.setId(proximoIdCliente);
    cliente.setEndereco(endereco);
    cliente.setTelefone(telefone);
    proximoIdCliente++;
    clientes.add(cliente);
    Persistencia.salvar(clientes, "clientes.dat");

    System.out.println("\nCliente cadastrado com sucesso.");
}

private static void listarClientes() {
    limparTela();
    System.out.println("===== CLIENTES =====");
    if (clientes.isEmpty()) {
        System.out.println("Nenhum cliente cadastrado.");
        return;
    }
    for (Cliente cliente : clientes) {
        cliente.verCliente();
        System.out.println("--------------------");
    }
}

private static void editarCliente() {
limparTela();
System.out.println("===== EDITAR CLIENTE =====");

Cliente cliente = buscarClientePorId();

if (cliente == null) {
    System.out.println("Cliente nao encontrado.");
    return;
}

String nome = lerTextoObrigatorio("Novo nome: ");
String cpf = lerTextoObrigatorio("Novo CPF: ");
String login = lerTextoObrigatorio("Novo login: ");
String senha = lerTextoObrigatorio("Nova senha: ");
String endereco = lerTextoObrigatorio("Novo endereco: ");
String telefone = lerTextoObrigatorio("Novo telefone: ");

cliente.editarCliente(nome,cpf,login,senha,endereco,telefone);
Persistencia.salvar(clientes, "clientes.dat");
System.out.println("\nCliente atualizado com sucesso.");
}

private static void desativarCliente() {
    limparTela();
    System.out.println("===== DESATIVAR CLIENTE =====");
    Cliente cliente = buscarClientePorId();
    if (cliente == null) {
        System.out.println("Cliente nao encontrado.");
        return;
    }
    cliente.desativarUsuario();
    Persistencia.salvar(clientes, "clientes.dat");
    System.out.println("\nCliente desativado com sucesso.");
}

private static void listarClientesSuspensos() {
    limparTela();
    System.out.println("===== CLIENTES SUSPENSOS =====");
    List<Cliente> suspensos = Cliente.listarClientesSuspensos(clientes);
    if (suspensos.isEmpty()) {
        System.out.println("Nenhum cliente suspenso.");
        return;
    }
    for (Cliente cliente : suspensos) {
        cliente.verCliente();
        System.out.println("--------------------");
    }
}

private static Cliente buscarClientePorId() {
    System.out.print("ID do cliente: ");
    int id = lerInteiro();
    for (Cliente cliente : clientes) {
        if (cliente.getId() == id) {
            return cliente;
        }
    }
    return null;
}

private static void cadastrarProduto() {
    limparTela();
    System.out.println("===== CADASTRAR PRODUTO =====");
    String nome = lerTextoObrigatorio("Nome: ");
    String descricao = lerTextoObrigatorio("Descricao: ");
    String categoria = lerTextoObrigatorio("Categoria: ");
    System.out.print("Quantidade em estoque: ");
    int quantidade = lerInteiro();
    System.out.print("Preco: ");
    double preco = lerDouble();

    Produto produto = Produto.criarProduto(proximoIdProduto, nome, descricao, categoria, quantidade, preco);
    proximoIdProduto++;
    produtos.add(produto);
    Persistencia.salvar(produtos, "produtos.dat");

    System.out.println("\nProduto cadastrado com sucesso.");
}

private static void listarProdutos() {
    limparTela();
    System.out.println("===== PRODUTOS =====");
    if (produtos.isEmpty()) {
        System.out.println("Nenhum produto cadastrado.");
        return;
    }
    for (Produto produto : produtos) {
        produto.verProduto();
        System.out.println("--------------------");
    }
}

private static void editarProduto() {
    limparTela();
    System.out.println("===== EDITAR PRODUTO =====");
    Produto produto = buscarProdutoPorId();
    if (produto == null) {
        System.out.println("Produto nao encontrado.");
        return;
    }
    String nome = lerTextoObrigatorio("Novo nome: ");
    String descricao = lerTextoObrigatorio("Nova descricao: ");
    String categoria = lerTextoObrigatorio("Nova categoria: ");
    System.out.print("Nova quantidade: ");
    int quantidade = lerInteiro();
    System.out.print("Novo preco: ");
    double preco = lerDouble();
    produto.editarProduto(nome, descricao, categoria, quantidade, preco);
    Persistencia.salvar(produtos, "produtos.dat");
    System.out.println("\nProduto atualizado com sucesso.");
}

private static void gerenciarEstoque() {
    limparTela();
    System.out.println("===== GERENCIAR ESTOQUE =====");
    Produto produto = buscarProdutoPorId();
    if (produto == null) {
        System.out.println("Produto nao encontrado.");
        return;
    }
    System.out.println("1 - Adicionar estoque");
    System.out.println("2 - Remover estoque");
    System.out.print("Escolha: ");
    int op = lerInteiro();
    System.out.print("Quantidade: ");
    int qtd = lerInteiro();
    if (op == 1) {
        produto.adicionarEstoque(qtd);
    } else if (op == 2) {
        produto.removerEstoque(qtd);
    } else {
        System.out.println("Opcao invalida.");
        return;
    }
    Persistencia.salvar(produtos, "produtos.dat");
    System.out.println("\nEstoque atualizado.");
}

private static void excluirProduto() {
    limparTela();
    System.out.println("===== EXCLUIR PRODUTO =====");
    Produto produto = buscarProdutoPorId();
    if (produto == null) {
        System.out.println("Produto nao encontrado.");
        return;
    }
    produto.excluirProduto();
    Persistencia.salvar(produtos, "produtos.dat");
    System.out.println("\nProduto excluido (estoque zerado).");
}

private static void listarProdutosSemEstoque() {
    limparTela();
    System.out.println("===== PRODUTOS SEM ESTOQUE =====");
    List<Produto> semEstoque = Produto.listarProdutosSemEstoque(produtos);
    if (semEstoque.isEmpty()) {
        System.out.println("Todos os produtos tem estoque.");
        return;
    }
    for (Produto produto : semEstoque) {
        produto.verProduto();
        System.out.println("--------------------");
    }
}

private static Produto buscarProdutoPorId() {
    System.out.print("ID do produto: ");
    int id = lerInteiro();
    for (Produto produto : produtos) {
        if (produto.getId() == id) {
            return produto;
        }
    }
    return null;
}

private static void cadastrarFuncionario() {
    limparTela();
    System.out.println("===== CADASTRAR FUNCIONARIO =====");
    String nome = lerTextoObrigatorio("Nome: ");
    String cpf = lerTextoObrigatorio("CPF: ");
    String login = lerTextoObrigatorio("Login: ");
    String email = lerTextoObrigatorio("Email: ");
    String senha = lerTextoObrigatorio("Senha: ");
    System.out.print("E gerente? (1 - Sim / 2 - Nao): ");
    int op = lerInteiro();
    boolean ehGerente = (op == 1);

    Funcionario funcionario = Funcionario.criarFuncionario(nome, cpf, login, email, senha, ehGerente);
    funcionario.setId(proximoIdFuncionario);
    proximoIdFuncionario++;
    funcionarios.add(funcionario);
    Persistencia.salvar(funcionarios, "funcionarios.dat");

    System.out.println("\nFuncionario cadastrado com sucesso.");
}

private static void listarFuncionarios() {
    limparTela();
    System.out.println("===== FUNCIONARIOS =====");
    if (funcionarios.isEmpty()) {
        System.out.println("Nenhum funcionario cadastrado.");
        return;
    }
    for (Funcionario funcionario : funcionarios) {
        funcionario.verFuncionario();
        System.out.println("--------------------");
    }
}

private static String lerTextoObrigatorio(String rotulo) {
    String valor;
    while (true) {
        System.out.print(rotulo);
        valor = scanner.nextLine().trim();
        if (!valor.isEmpty()) {
            return valor;
        }
        System.out.println("Campo obrigatorio. Digite um valor.");
    }
}

private static int lerInteiro() {
    while (true) {
        try {
            int valor = scanner.nextInt();
            scanner.nextLine();
            return valor;
        } catch (InputMismatchException e) {
            System.out.print("Valor invalido. Digite um numero inteiro: ");
            scanner.nextLine();
        }
    }
}

private static double lerDouble() {
    while (true) {
        try {
            double valor = scanner.nextDouble();
            scanner.nextLine();
            return valor;
        } catch (InputMismatchException e) {
            System.out.print("Valor invalido. Digite um numero: ");
            scanner.nextLine();
        }
    }
}

private static void pausar() {
    System.out.print("\nPressione Enter para continuar...");
    scanner.nextLine();
}

private static void limparTela() {
    try {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    } catch (Exception e) {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
}