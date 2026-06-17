# 🛒 Sistema Simples de Vendas e Controle de Estoque

Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos**, com foco na aplicação de conceitos como **herança, encapsulamento, composição, serialização e manipulação de coleções**, por meio da implementação de um sistema de vendas em ambiente de console.

O sistema permite o gerenciamento de **clientes, funcionários, produtos e vendas**, além de realizar **controle de estoque**, **registro de pagamentos**, **devoluções** e **suspensão temporária de clientes inadimplentes**.

---

## 📋 Visão geral

O projeto simula o funcionamento básico de uma loja, oferecendo operações essenciais para cadastro, consulta e controle das entidades do sistema.

Entre os principais recursos implementados, estão:

- autenticação de clientes e funcionários
- diferenciação entre funcionários comuns e gerentes
- cadastro e manutenção de clientes
- cadastro e gerenciamento de produtos
- cadastro e gerenciamento de funcionários
- registro de vendas com múltiplos itens
- cálculo automático do valor total da venda
- controle de pagamento
- devolução de vendas com atualização do estoque
- persistência de dados em arquivos `.dat`

Toda a interação é realizada por meio de **menus no terminal**, organizando o fluxo de uso do sistema de forma simples e objetiva.

---

## ✅ Funcionalidades

### Clientes
- criar conta de cliente
- realizar login
- visualizar dados cadastrais
- editar informações
- listar clientes cadastrados
- desativar cliente
- listar clientes suspensos
- visualizar compras realizadas

### Funcionários
- realizar login
- cadastrar funcionários
- listar funcionários
- distinguir funcionários comuns de gerentes

### Produtos
- cadastrar produtos
- listar produtos
- editar produtos
- excluir logicamente produtos
- adicionar e remover estoque
- listar produtos sem estoque

### Vendas
- registrar vendas
- adicionar itens à venda
- calcular subtotal por item
- calcular total da venda
- registrar pagamento
- registrar devolução
- listar vendas
- listar compras de um cliente

---

## 📐 Regras de negócio

O sistema segue as seguintes regras:

- qualquer pessoa que deseje comprar deve estar cadastrada como cliente
- usuários não são removidos definitivamente, apenas desativados
- apenas funcionários com perfil de gerente podem gerenciar produtos e funcionários
- clientes suspensos não podem realizar novas compras
- vendas não pagas geram suspensão de 15 dias para o cliente responsável
- após o prazo da suspensão, o cliente volta a ficar apto para comprar
- devoluções retornam os produtos ao estoque
- caso não exista nenhum funcionário cadastrado, o sistema cria automaticamente um gerente padrão

---

## 🗂️ Estrutura do projeto

```text
src/
└── br/
    └── vendas/
        ├── model/
        │   ├── Usuario.java
        │   ├── Cliente.java
        │   ├── Funcionario.java
        │   ├── Produto.java
        │   ├── ItemVenda.java
        │   └── Venda.java
        ├── util/
        │   └── Persistencia.java
        └── view/
            └── Menu.java
```

### Organização das classes

**`Usuario`** — Classe abstrata base do sistema. Reúne os atributos e comportamentos comuns aos usuários: nome, CPF, login, e-mail, senha e status de ativação.

**`Cliente`** — Especialização de `Usuario` que representa os clientes da loja. Possui: ID, endereço, telefone, status de suspensão e data de fim da suspensão.

**`Funcionario`** — Especialização de `Usuario` responsável por representar os funcionários do sistema. Possui: ID e indicador de gerente.

**`Produto`** — Representa os produtos comercializados pela loja, contendo: ID, nome, descrição, categoria, quantidade em estoque e preço.

**`ItemVenda`** — Representa cada item associado a uma venda, contendo: produto, quantidade e cálculo de subtotal.

**`Venda`** — Representa uma venda registrada no sistema, contendo: ID, data da venda, status de pagamento, status de devolução, cliente, funcionário e lista de itens vendidos.

**`Persistencia`** — Classe utilitária responsável por salvar e carregar os dados serializados em arquivos `.dat`.

**`Menu`** — Classe responsável pela execução principal do sistema e pela interação com o usuário por meio do terminal.

---

## 💾 Persistência de dados

As informações do sistema são armazenadas em arquivos locais, permitindo que os dados permaneçam disponíveis entre diferentes execuções.

Arquivos utilizados:

| Arquivo | Conteúdo |
|---|---|
| `clientes.dat` | Dados dos clientes cadastrados |
| `funcionarios.dat` | Dados dos funcionários cadastrados |
| `produtos.dat` | Dados dos produtos cadastrados |
| `vendas.dat` | Histórico de vendas registradas |

---

## 🛠️ Tecnologias e conceitos aplicados

- **Java**
- Programação Orientada a Objetos
- Herança e Encapsulamento
- Composição
- Serialização de objetos
- Persistência em arquivos
- Interface textual via terminal

---

## ▶️ Como executar

### Pré-requisitos

- Java instalado
- JDK configurado no ambiente

### Compilação

No terminal, dentro da raiz do projeto, execute:

```bash
javac -d bin src/br/vendas/model/*.java src/br/vendas/util/*.java src/br/vendas/view/*.java
```

### Execução

```bash
java -cp bin br.vendas.view.Menu
```

### Acesso inicial

Se não existir nenhum funcionário cadastrado, o sistema cria automaticamente um gerente padrão:

```
Login: admin
Senha: admin
```

### Exemplo de fluxo de uso

1. Iniciar o sistema
2. Entrar como funcionário com as credenciais do gerente padrão
3. Cadastrar produtos
4. Cadastrar clientes
5. Registrar vendas
6. Registrar pagamento ou inadimplência
7. Consultar clientes, produtos e vendas

---

## 👥 Equipe

| Nome | Matrícula |
|---|---|
| Gerislan da Silva Araujo | — |
| José Veríssimo de Oliveira Queiroz | — |
| Paulo Henrique Souza Lima | — |

---

## 🎓 Informações acadêmicas

**Disciplina:** PEX0130 — Programação Orientada a Objetos  
**Instituição:** Universidade Federal Rural do Semi-Árido — Campus Pau dos Ferros

---

## 💡 Considerações finais

Este projeto foi desenvolvido com finalidade acadêmica, como exercício prático dos principais fundamentos da Programação Orientada a Objetos aplicados a um cenário real de vendas e controle de estoque.

O sistema cobre com solidez os requisitos propostos e, ao mesmo tempo, serve como ponto de partida para evoluções futuras — seja no refinamento das validações, na melhoria da navegação pelos menus, ou na migração para uma interface gráfica ou web. A estrutura orientada a objetos adotada facilita essas expansões sem comprometer a base já construída.

> _"Todo sistema começa pequeno. O que importa é que a fundação seja sólida."_