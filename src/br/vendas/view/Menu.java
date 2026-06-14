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
                case 0:
                    System.out.println("Encerrando o sistema.");
                    return;

                case 1:
                    Funcionario funcionario = loginFuncionario();
                    if (funcionario != null) {
                        menuFuncionario(funcionario);
                    }
                    break;

                case 2:
                    Cliente cliente = loginCliente();
                    if (cliente != null) {
                        menuCliente(cliente);
                    }
                    break;

                case 3:
                    cadastrarCliente();
                    pausar();
                    break;

                default:
                    opcaoInvalida();
                    break;
            }
        }
    }

    private static void garantirGerentePadrao() {
        if (funcionarios.isEmpty()) {
            Funcionario admin = Funcionario.criarFuncionario(
                    "Administrador",
                    "0000000",
                    "admin",
                    "admin@sistema.com",
                    "admin",
                    true
            );

            admin.setId(gerarProximoIdFuncionario());
            funcionarios.add(admin);
            salvarFuncionarios();
        }
    }

    private static Funcionario loginFuncionario() {
        limparTela();
        System.out.println("===== LOGIN FUNCIONARIO =====");

        String login = lerTextoObrigatorio("Login: ");
        String senha = lerTextoObrigatorio("Senha: ");

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.autenticar(login, senha)) {
                return funcionario;
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

        for (Cliente cliente : clientes) {
            if (cliente.autenticar(login, senha)) {
                return cliente;
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
                case 1:
                    submenuClientes();
                    break;

                case 2:
                    if (funcionario.isEhGerente()) {
                        submenuProdutos();
                    } else {
                        acessoNegado();
                        pausar();
                    }
                    break;

                case 3:
                    if (funcionario.isEhGerente()) {
                        submenuFuncionarios();
                    } else {
                        acessoNegado();
                        pausar();
                    }
                    break;

                case 0:
                    System.out.println("Logout realizado.");
                    break;

                default:
                    opcaoInvalida();
                    break;
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
                case 1:
                    cadastrarCliente();
                    pausar();
                    break;

                case 2:
                    listarClientes();
                    pausar();
                    break;

                case 3:
                    editarClientePorFuncionario();
                    pausar();
                    break;

                case 4:
                    desativarCliente();
                    pausar();
                    break;

                case 5:
                    listarClientesSuspensos();
                    pausar();
                    break;

                case 0:
                    break;

                default:
                    opcaoInvalida();
                    break;
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
                case 1:
                    cadastrarProduto();
                    pausar();
                    break;

                case 2:
                    listarProdutos();
                    pausar();
                    break;

                case 3:
                    editarProduto();
                    pausar();
                    break;

                case 4:
                    gerenciarEstoque();
                    pausar();
                    break;

                case 5:
                    excluirProduto();
                    pausar();
                    break;

                case 6:
                    listarProdutosSemEstoque();
                    pausar();
                    break;

                case 0:
                    break;

                default:
                    opcaoInvalida();
                    break;
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
                case 1:
                    cadastrarFuncionario();
                    pausar();
                    break;

                case 2:
                    listarFuncionarios();
                    pausar();
                    break;

                case 0:
                    break;

                default:
                    opcaoInvalida();
                    break;
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
                case 1:
                    listarProdutos();
                    pausar();
                    break;

                case 2:
                    limparTela();
                    System.out.println("===== MEUS DADOS =====");
                    System.out.println("ID: " + cliente.getId());
                    cliente.verCliente();
                    pausar();
                    break;

                case 3:
                    limparTela();
                    editarClienteLogado(cliente);
                    pausar();
                    break;

                case 0:
                    System.out.println("Logout realizado.");
                    break;

                default:
                    opcaoInvalida();
                    break;
            }
        }
    }

    private static void acessoNegado() {
        System.out.println("Acesso negado. Apenas gerentes podem realizar esta acao.");
    }

    private static String lerCpf(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String valor = scanner.nextLine().trim();

            if (valor.matches("\\d{11}")) {
                return valor;
            }

            System.out.println("CPF invalido. Digite exatamente 11 numeros (apenas digitos).");
        }
    }

    private static String lerEmail(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String valor = scanner.nextLine().trim();

            if (!valor.isEmpty() && valor.contains("@")) {
                return valor;
            }

            System.out.println("Email invalido. O email deve conter '@'.");
        }
    }

    private static String lerTelefone(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String valor = scanner.nextLine().trim();

            if (valor.matches("\\d{11}")) {
                return valor;
            }

            System.out.println("Telefone invalido. Digite exatamente 11 numeros (apenas digitos, sem espacos ou tracos).");
        }
    }

    private static void cadastrarCliente() {
        limparTela();
        System.out.println("===== CADASTRAR CLIENTE =====");

        String nome     = lerTextoObrigatorio("Nome: ");
        String cpf      = lerCpf("CPF (11 digitos): ");
        String login    = lerLoginUnicoCliente(null);
        String email    = lerEmail("Email: ");
        String senha    = lerTextoObrigatorio("Senha: ");
        String endereco = lerTextoObrigatorio("Endereco: ");
        String telefone = lerTelefone("Telefone (11 digitos): ");

        Cliente cliente = Cliente.criarCliente(nome, cpf, login, email, senha);
        cliente.setId(gerarProximoIdCliente());
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefone);

        clientes.add(cliente);
        salvarClientes();

        System.out.println("\nCliente cadastrado com sucesso.");
    }

    private static void listarClientes() {
        limparTela();
        System.out.println("===== CLIENTES =====");

        List<Cliente> clientesAtivos = Cliente.listarClientes(clientes);

        if (clientesAtivos.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (Cliente c : clientesAtivos) {
            System.out.println("ID: " + c.getId());
            c.verCliente();
            System.out.println("--------------------");
        }
    }

    private static void editarClientePorFuncionario() {
        limparTela();
        System.out.println("===== EDITAR CLIENTE =====");

        Cliente cliente = buscarCliente();

        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }

        String nome     = lerTextoObrigatorio("Novo nome: ");
        String cpf      = lerCpf("Novo CPF (11  digitos): ");
        String login    = lerLoginUnicoCliente(cliente);
        String senha    = lerTextoObrigatorio("Nova senha: ");
        String endereco = lerTextoObrigatorio("Novo endereco: ");
        String telefone = lerTelefone("Novo telefone (11 digitos): ");

        cliente.editarCliente(nome, cpf, login, senha, endereco, telefone);
        salvarClientes();

        System.out.println("\nCliente atualizado com sucesso.");
    }

    private static void editarClienteLogado(Cliente cliente) {
        System.out.println("===== EDITAR MEUS DADOS =====");

        String nome     = lerTextoObrigatorio("Novo nome: ");
        String cpf      = lerCpf("Novo CPF (11 digitos): ");
        String login    = lerLoginUnicoCliente(cliente);
        String senha    = lerTextoObrigatorio("Nova senha: ");
        String endereco = lerTextoObrigatorio("Novo endereco: ");
        String telefone = lerTelefone("Novo telefone (11 digitos): ");

        cliente.editarCliente(nome, cpf, login, senha, endereco, telefone);
        salvarClientes();

        System.out.println("\nDados atualizados com sucesso.");
    }

    private static void desativarCliente() {
        limparTela();
        System.out.println("===== DESATIVAR CLIENTE =====");

        Cliente cliente = buscarCliente();

        if (cliente == null) {
            System.out.println("Cliente nao encontrado.");
            return;
        }

        cliente.desativarUsuario();
        salvarClientes();

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

        for (Cliente c : suspensos) {
            System.out.println("ID: " + c.getId());
            c.verCliente();
            System.out.println("--------------------");
        }
    }

    private static Cliente buscarCliente() {
        System.out.println("Buscar por:");
        System.out.println("1 - ID");
        System.out.println("2 - CPF");
        System.out.print("Escolha: ");
        int opcao = lerInteiro();

        if (opcao == 1) {
            System.out.print("ID do cliente: ");
            int id = lerInteiro();

            for (Cliente cliente : clientes) {
                if (cliente.getId() == id) {
                    return cliente;
                }
            }
        } else if (opcao == 2) {
            String cpf = lerCpf("CPF do cliente (11 digitos): ");

            for (Cliente cliente : clientes) {
                if (cliente.getCpf().equals(cpf)) {
                    return cliente;
                }
            }
        } else {
            System.out.println("Opcao invalida.");
        }

        return null;
    }

    private static void cadastrarProduto() {
        limparTela();
        System.out.println("===== CADASTRAR PRODUTO =====");

        String nome      = lerTextoObrigatorio("Nome: ");
        String descricao = lerTextoObrigatorio("Descricao: ");
        String categoria = lerTextoObrigatorio("Categoria: ");

        System.out.print("Quantidade em estoque: ");
        int quantidade = lerInteiro();

        System.out.print("Preco: ");
        double preco = lerDouble();

        Produto produto = Produto.criarProduto(
                gerarProximoIdProduto(),
                nome,
                descricao,
                categoria,
                quantidade,
                preco
        );

        produtos.add(produto);
        salvarProdutos();

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

        String nome      = lerTextoObrigatorio("Novo nome: ");
        String descricao = lerTextoObrigatorio("Nova descricao: ");
        String categoria = lerTextoObrigatorio("Nova categoria: ");

        System.out.print("Nova quantidade: ");
        int quantidade = lerInteiro();

        System.out.print("Novo preco: ");
        double preco = lerDouble();

        produto.editarProduto(nome, descricao, categoria, quantidade, preco);
        salvarProdutos();

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
        int opcao = lerInteiro();

        System.out.print("Quantidade: ");
        int quantidade = lerInteiro();

        if (quantidade <= 0) {
            System.out.println("Quantidade invalida.");
            return;
        }

        if (opcao == 1) {
            produto.adicionarEstoque(quantidade);
        } else if (opcao == 2) {
            produto.removerEstoque(quantidade);
        } else {
            System.out.println("Opcao invalida.");
            return;
        }

        salvarProdutos();
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
        salvarProdutos();

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

        String nome  = lerTextoObrigatorio("Nome: ");
        String cpf   = lerCpf("CPF (11 digitos): ");
        String login = lerLoginUnicoFuncionario();
        String email = lerEmail("Email: ");
        String senha = lerTextoObrigatorio("Senha: ");

        boolean ehGerente = lerOpcaoSimNao("E gerente? (1 - Sim / 2 - Nao): ");

        Funcionario funcionario = Funcionario.criarFuncionario(
                nome,
                cpf,
                login,
                email,
                senha,
                ehGerente
        );

        funcionario.setId(gerarProximoIdFuncionario());
        funcionarios.add(funcionario);
        salvarFuncionarios();

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

    private static String lerLoginUnicoCliente(Cliente clienteAtual) {
        while (true) {
            System.out.print("Login: ");
            String valor = scanner.nextLine().trim();

            if (valor.isEmpty()) {
                System.out.println("Campo obrigatorio. Digite um valor.");
                continue;
            }

            boolean duplicado = false;
            for (Cliente c : clientes) {
                if (c.getLogin().equals(valor) && (clienteAtual == null || c.getId() != clienteAtual.getId())) {
                    duplicado = true;
                    break;
                }
            }

            if (duplicado) {
                System.out.println("Login ja em uso. Escolha outro.");
                continue;
            }

            return valor;
        }
    }

    private static String lerLoginUnicoFuncionario() {
        while (true) {
            System.out.print("Login: ");
            String valor = scanner.nextLine().trim();

            if (valor.isEmpty()) {
                System.out.println("Campo obrigatorio. Digite um valor.");
                continue;
            }

            boolean duplicado = false;
            for (Funcionario f : funcionarios) {
                if (f.getLogin().equals(valor)) {
                    duplicado = true;
                    break;
                }
            }

            if (duplicado) {
                System.out.println("Login ja em uso. Escolha outro.");
                continue;
            }

            return valor;
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

    private static boolean lerOpcaoSimNao(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            int opcao = lerInteiro();

            if (opcao == 1) {
                return true;
            }

            if (opcao == 2) {
                return false;
            }

            System.out.println("Opcao invalida. Digite 1 para Sim ou 2 para Nao.");
        }
    }

    private static int gerarProximoIdCliente() {
        int maiorId = 0;

        for (Cliente cliente : clientes) {
            if (cliente.getId() > maiorId) {
                maiorId = cliente.getId();
            }
        }

        return maiorId + 1;
    }

    private static int gerarProximoIdProduto() {
        int maiorId = 0;

        for (Produto produto : produtos) {
            if (produto.getId() > maiorId) {
                maiorId = produto.getId();
            }
        }

        return maiorId + 1;
    }

    private static int gerarProximoIdFuncionario() {
        int maiorId = 0;

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() > maiorId) {
                maiorId = funcionario.getId();
            }
        }

        return maiorId + 1;
    }

    private static void salvarClientes() {
        Persistencia.salvar(clientes, "clientes.dat");
    }

    private static void salvarProdutos() {
        Persistencia.salvar(produtos, "produtos.dat");
    }

    private static void salvarFuncionarios() {
        Persistencia.salvar(funcionarios, "funcionarios.dat");
    }

    private static void opcaoInvalida() {
        System.out.println("Opcao inexistente.");
        pausar();
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
