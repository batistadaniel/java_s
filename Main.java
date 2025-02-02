import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        Menu menu = new Menu();
        App app = new App("Salao Beleza Rapida");

        menu.tituloInicial();
        app.menu();

    }
}

class Menu {

    // metodo void, ou seja, sem retorno
    void tituloInicial() {
        System.out.println();
        System.out.println("*************** BEM VINDO ***************");
        System.out.println();
    }
}
class App {

    private final String nome;
    private final Scanner entrada;
    private Usuario usuario;
    private Administrador administrador;
    private Funcionario funcionario;
    private Cliente cliente;
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Administrador> administradores = new ArrayList<>();
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Servico> servicos = new ArrayList<>();



    public App(String nome) {
        this.nome = nome;
        this.entrada = new Scanner(System.in);
    }

    public Scanner getEntrada() {
        return entrada;
    }

    public void menu() {
        int opcao;

        do {
            System.out.println();
            System.out.println("*************** " + nome + " ***************");
            System.out.println();
            System.out.println(">>> 1. Cadastrar Usuario");
            System.out.println(">>> 2. Listar Usuarios");
            System.out.println(">>> 3. Login");
            System.out.println(">>> 4. Sair");

            System.out.print(">>>>> ");
            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    login();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao!= 4);
    }

    private void cadastrarUsuario() {
        int user;

        System.out.println();

        System.out.print("Nome: ");
        String nome = getEntrada().nextLine();

        System.out.print("Telefone: ");
        String telefone = getEntrada().nextLine();

        System.out.print("CPF: ");
        String cpf = getEntrada().nextLine();

        System.out.print("Email: ");
        String email = getEntrada().nextLine();

        System.out.print("Senha: ");
        String senha = getEntrada().nextLine();

        System.out.println("Tipo de Usuario: ");
        System.out.println(">>> 1. Administrador");
        System.out.println(">>> 2. Funcionario");
        System.out.println(">>> 3. Cliente");
        System.out.print(">>>>> ");
        user = getEntrada().nextInt();

        switch (user) {
            case 1: // Administrador
                Administrador administrador = new Administrador(nome, telefone, cpf, email, senha, "Administrador");
                administradores.add(administrador);
                System.out.println("Administrador cadastrado com sucesso!");
                break;
            case 2: // Funcionario
                Funcionario funcionario = new Funcionario(nome, telefone, cpf, email, senha, "Funcionario");
                funcionarios.add(funcionario);
                System.out.println("Funcionario cadastrado com sucesso!");
                break;
            case 3: // Cliente
                Cliente cliente = new Cliente(nome, telefone, cpf, email, senha, "Cliente");
                clientes.add(cliente);
                System.out.println("Cliente cadastrado com sucesso!");
                break;
            default:
                System.out.println("Opção invalida!");
                break;
        }
    }

    private void listarUsuarios() {
        System.out.println();
        System.out.println("*************** Lista de Usuarios ***************");
        System.out.println();

        // Lista Administradores
        System.out.println(">> Administradores:");
        if (administradores.isEmpty()) {
            System.out.println("Nenhum administrador cadastrado.");
        } else {
            for (int i = 0; i < administradores.size(); i++) {
                System.out.println((i + 1) + " - " + administradores.get(i).getNome() + " (Administrador)");
            }
        }

        // Lista Clientes
        System.out.println("\n>> Clientes:");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (int i = 0; i < clientes.size(); i++) {
                System.out.println((i + 1) + " - " + clientes.get(i).getNome() + " (Cliente)");
            }
        }

        // Lista Funcionários
        System.out.println("\n>> Funcionários:");
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (int i = 0; i < funcionarios.size(); i++) {
                System.out.println((i + 1) + " - " + funcionarios.get(i).getNome() + " (Funcionário)");
            }
        }
    }

    void cadastrarServico() {
        int opcao;

        System.out.println();

        System.out.print("Nome do Servico: ");
        String nome = entrada.nextLine();

        System.out.println("Descricao do Servico: ");
        String descricao = entrada.nextLine();

        System.out.println("Codigo do Servico: ");
        String codigo = entrada.nextLine();

        System.out.print("Valor do Servico: ");
        double valorServico = entrada.nextDouble();


        System.out.print(">>>>> ");
        opcao = getEntrada().nextInt();

        Servico novoServico = new Servico(nome, descricao, codigo, valorServico, servicos);
        servicos.add(novoServico);
        System.out.println("Servico de Estética cadastrado com sucesso!");


    }

    private void listarServicos() {
        System.out.println();
        System.out.println("*************** Lista de Serviços ***************");
        System.out.println();

        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado.");
        } else {
            for (int i = 0; i < servicos.size(); i++) {
                System.out.println((i + 1) + " - " + servicos.get(i).getNome() + " (Código: " + servicos.get(i).getCodigo() + ")");
            }
        }
    }

    private void login() {

        System.out.println();
        System.out.println("*************** Login *************** ");
        System.out.println();


        System.out.print("Email: ");
        String email = entrada.nextLine();

        System.out.print("Senha: ");
        String senha = entrada.nextLine();

        boolean usuarioEncontrado = false;

        // Verifica na lista de administradores
        for (Administrador admin : administradores) {
            if (admin.getEmail().equals(email) && admin.getSenha().equals(senha)) {
                usuarioEncontrado = true;
                this.usuario = admin;
                System.out.println("\nLogin efetuado com sucesso! Bem-vindo, Administrador " + usuario.getNome());
                administrador.menuAdministrador(this);
                break;
            }
        }

        // Verifica na lista de funcionários
        if (!usuarioEncontrado) {
            for (Funcionario func : funcionarios) {
                if (func.getEmail().equals(email) && func.getSenha().equals(senha)) {
                    usuarioEncontrado = true;

                    System.out.println("\nLogin efetuado com sucesso! Bem-vindo, Funcionário " + usuario.getNome());
//                    funcionario.menuFuncionario(this);
                    break;
                }
            }
        }

        // Verifica na lista de clientes
        if (!usuarioEncontrado) {
            for (Cliente cli : clientes) {
                if (cli.getEmail().equals(email) && cli.getSenha().equals(senha)) {
                    usuarioEncontrado = true;
                    System.out.println("\nLogin efetuado com sucesso! Bem-vindo, Cliente " + usuario.getNome());
//                    cliente.menuCliente(this);
                    break;
                }
            }
        }

        // Caso não encontre o usuário
        if (!usuarioEncontrado) {
            System.out.println("\nErro: Email ou senha incorretos. Tente novamente.");
        }
    }
}

class Usuario {
    private String nome;
    private String telefone;
    private String cpf;
    private String email;
    private String senha;
    private String tipoUsuario;
    private final ArrayList<Usuario> usuarios = null;


    public Usuario(String nome, String telefone, String cpf, String email, String senha, String tipoUsuario) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

class Administrador extends Usuario {

    private App app;
    public Administrador(String nome, String telefone, String cpf, String email, String senha, String tipoUsuario) {
        super(nome, telefone, cpf, email, senha, "Administrador");
    }

    public static void menuAdministrador(App app) {

        Scanner entrada = app.getEntrada();
        int opcao;
        do {
            System.out.println();
            System.out.println("*************** Menu Administrador ***************");
            System.out.println();
            System.out.println(">>>>> Escolha uma opcao:");
            System.out.println();
            System.out.println("1. Cadastrar servico");
            System.out.println("2. Listar servico");
            System.out.println("3. Sair");

            System.out.print(">>>>> ");
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    app.cadastrarServico();
                    break;
                case 2:
//                    listarServicos();
                    break;
                case 3:

                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 3);
    }

//    private void cadastrarServico() {
//        System.out.println();
//        System.out.println("*************** Cadastro de Servico *************** ");
//        System.out.println();
//
//        System.out.print("Nome do servico: ");
//        String nome = app.getEntrada().nextLine();
//
//        System.out.print("Descricao do servico: ");
//        String descricao = app.getEntrada().nextLine();
//
//        System.out.print("Codigo do servico:: ");
//        String codigo = app.getEntrada().nextLine();
//
//        System.out.print("Valor do servico: ");
//        Double valorServico = app.getEntrada().nextDouble();
//
//    }
//
//    private void listarServicos() {}

}

class Funcionario extends Usuario {
    public Funcionario(String nome, String telefone, String cpf, String email, String senha, String tipoUsuario) {
        super(nome, telefone, cpf, email, senha, "Funcionario");
    }
}

class Cliente extends Usuario {
    public Cliente(String nome, String telefone, String cpf, String email, String senha, String tipoUsuario) {
        super(nome, telefone, cpf, email, senha, "Cliente");
    }
}

class Servico {

    private String nome;
    private String descricao;
    private String codigo;
    private double valorServico;

    private final ArrayList<Servico> servicos;

    public Servico(String nome, String descricao, String codigo, double valorServico, ArrayList<Servico> servicos) {
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
        this.valorServico = valorServico;
        this.servicos = servicos;
    }

    // Getters e Setters
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getValorServico() {
        return valorServico;
    }

    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
    }
}

class Agendamento extends Servico {

    Agendamento(String nome, String descricao, String codigo, double valorServico, ArrayList<Servico> servicos) {
        super(nome, descricao, codigo, valorServico, servicos);
    }
}

