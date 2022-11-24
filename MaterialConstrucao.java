import java.sql.Connection;
import java.util.Scanner;

public class MaterialConstrucao {
    public static void main(String[] args) throws Exception {
        
        int opcao = 0;
        Scanner sc = new Scanner(System.in);
        Connection conexao = DAO.createConnection();

        do {
            System.out.println("===== Material Construção =====");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Produto");
            System.out.println("3 - Cadastrar Ordem de Compra");
            System.out.println("4 - Alterar Cliente");
            System.out.println("5 - Alterar Produto");
            System.out.println("6 - Excluir Cliente");
            System.out.println("7 - Excluir Produto");
            System.out.println("8 - Excluir Ordem de Compra");
            System.out.println("9 - Listar Clientes");
            System.out.println("10 - Listar Produtos");
            System.out.println("11 - Listar Ordens de Compra");
            System.out.println("12 - Sair");
            System.out.println("Digite a opção desejada: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrar Cliente");
                    CadastrarCliente(sc);
                    break;
                case 2:
                    System.out.println("Cadastrar Produto");
                    CadastrarProduto(sc);
                    break;
                case 3:
                    System.out.println("Cadastrar Ordem de Compra");
                    CadastrarOrdemCompra(sc, conexao);
                    break;
                case 4:
                    System.out.println("Alterar Cliente");
                    AlterarCliente(sc, conexao);
                    break;
                case 5:
                    System.out.println("Alterar Produto");
                    AlterarProduto(sc, conexao);
                    break;
                case 6:
                    System.out.println("Excluir Cliente");
                    ExcluirCliente(sc, conexao);
                    break;
                case 7:
                    System.out.println("Excluir Produto");
                    ExcluirProduto(sc, conexao);
                    break;
                case 8:
                    System.out.println("Excluir Ordem de Compra");
                    ExcluirOC(sc, conexao);
                    break;
                case 9:
                    System.out.println("Listar Clientes");
                    ListarClientes(conexao);
                    break;
                case 10:
                    System.out.println("Listar Produtos");
                    ListarProdutos(conexao);
                    break;
                case 11:
                    System.out.println("Listar Ordens de Compra");
                    ListarOC(conexao);
                    break;
                case 12:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }

        } while (opcao != 9);

        DAO.closeConnection();
        sc.close();
    }

    public static void CadastrarCliente(Scanner sc) throws Exception {
        System.out.println("Insira o nome do cliente: ");
        String nome = sc.next();
        System.out.println("Insira o CPF do cliente: ");
        String cpf = sc.next();

        Cliente cliente = new Cliente(nome, cpf);

        System.out.println("Cliente "+cliente.getNome()+" cadastrado com sucesso!");
    }

    public static void CadastrarProduto(Scanner sc) throws Exception {
        System.out.println("Insira o nome do produto: ");
        String nome = sc.next();
        System.out.println("Insira o valor do produto: ");
        double valor = sc.nextDouble();
        System.out.println("Insira o tipo do produto: [1] - Tubo | [2] - Cabo | [3] - Tijolo");
        int tipo = sc.nextInt();
        switch (tipo) {
            case 1:
                System.out.println("Insira a cor do tubo: ");
                String cor = sc.next();
                System.out.println("Insira o diâmetro do tubo: ");
                double diametro = sc.nextDouble();

                Tubo tubo = new Tubo(nome, valor, cor, diametro);

                System.out.println("Tubo "+tubo.getNome()+" cadastrado com sucesso!");
                break;
            
            case 2:
                System.out.println("Insira o peso do cabo: ");
                double peso = sc.nextDouble();
                System.out.println("Insira o comprimento do cabo: ");
                double comprimento = sc.nextDouble();

                Cabo cabo = new Cabo(nome, valor, peso, comprimento);

                System.out.println("Cabo "+cabo.getNome()+" cadastrado com sucesso!");
                break;

            case 3: 
                System.out.println("Insira a cor do tijolo: ");
                cor = sc.next();
                System.out.println("Insira o peso do tijolo: ");
                peso = sc.nextInt();

                Tijolo tijolo = new Tijolo(nome, valor, cor, peso);

                System.out.println("Tijolo "+tijolo.getNome()+" cadastrado com sucesso!");
                break;
        
            default:
                System.out.println("Opção inválida");
                break;
        }

        System.out.println("Produto cadastrado com sucesso!");
    }

    public static void CadastrarOrdemCompra(Scanner sc, Connection conexao) throws Exception {
        System.out.println("Insira o id do cliente da OC: ");
        int idCliente = sc.nextInt();
        Cliente cliente = Cliente.getCliente(conexao, idCliente);
        System.out.println("Insira o id do produto da OC: ");
        int idProduto = sc.nextInt();
        Produto produto = Produto.getProduto(conexao, idProduto);
        System.out.println("Insira a quantidade do produto da OC: "); 
        int quantidade = sc.nextInt();

        OrdemCompra ordemCompra = new OrdemCompra(cliente, produto, quantidade);
        System.out.println("OC "+ordemCompra.getId()+" cadastrado com sucesso!");
    }  

    public static void AlterarCliente(Scanner sc, Connection conexao) throws Exception {
        System.out.println("Insira o id do cliente a ser alterado: ");
        int id = sc.nextInt();
        Cliente cliente = Cliente.getCliente(conexao, id);
        System.out.println("Qual o dado a ser alterado? [1] - Nome | [2] - CPF");
        int dado = sc.nextInt();
        switch (dado) {
            case 1:
                System.out.println("Insira o novo nome do cliente: ");
                String nome = sc.next();
                Cliente.alterarNome(conexao, nome, id);
                break;
            case 2:
                System.out.println("Insira o novo CPF do cliente: ");
                String cpf = sc.next();
                Cliente.alterarCpf(conexao, cpf, id);
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }

        System.out.println("Cliente "+cliente.getNome()+" alterado com sucesso!");
    }

    public static void AlterarProduto(Scanner sc, Connection conexao) throws Exception {
        System.out.println("Insira o id do produto a ser alterado: ");
        int id = sc.nextInt();
        Produto produto = Produto.getProduto(conexao, id);
        if (produto instanceof Tubo) {
            System.out.println("Qual o dado a ser alterado? [1] - Nome | [2] - Valor | [3] - Cor | [4] - Diâmetro");
            int dado = sc.nextInt();
            switch (dado) {
                case 1:
                    System.out.println("Insira o novo nome do produto: ");
                    String nome = sc.next();
                    Produto.alterarNome(conexao, nome, id);
                    break;
                case 2:
                    System.out.println("Insira o novo valor do produto: ");
                    double valor = sc.nextDouble();
                    Produto.alterarPreco(conexao, valor, id);
                    break;
                case 3:
                    System.out.println("Insira a nova cor do produto: ");
                    String cor = sc.next();
                    Tubo.alteraCor(conexao, cor, id);
                    break;
                case 4:
                    System.out.println("Insira o novo diâmetro do produto: ");
                    double diametro = sc.nextDouble();
                    Tubo.alteraTamanho(conexao, diametro, id);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } else if (produto instanceof Cabo) {
            System.out.println("Qual o dado a ser alterado? [1] - Nome | [2] - Valor | [3] - Peso | [4] - Comprimento");
            int dado = sc.nextInt();
            switch (dado) {
                case 1:
                    System.out.println("Insira o novo nome do produto: ");
                    String nome = sc.next();
                    Produto.alterarNome(conexao, nome, id);
                    break;
                case 2:
                    System.out.println("Insira o novo valor do produto: ");
                    double valor = sc.nextDouble();
                    Produto.alterarPreco(conexao, valor, id);
                    break;
                case 3:
                    System.out.println("Insira o novo peso do produto: ");
                    double peso = sc.nextDouble();
                    Cabo.alteraPeso(conexao, peso, id);
                    break;
                case 4:
                    System.out.println("Insira o novo comprimento do produto: ");
                    double comprimento = sc.nextDouble();
                    Cabo.alteraTamanho(conexao, comprimento, id);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } else if (produto instanceof Tijolo) {
            System.out.println("Qual o dado a ser alterado? [1] - Nome | [2] - Valor | [3] - Cor | [4] - Peso");  
            int dado = sc.nextInt();
            switch (dado) {
                case 1:
                    System.out.println("Insira o novo nome do produto: ");
                    String nome = sc.next();
                    Produto.alterarNome(conexao, nome, id);
                    break;
                case 2:
                    System.out.println("Insira o novo valor do produto: ");
                    double valor = sc.nextDouble();
                    Produto.alterarPreco(conexao, valor, id);
                    break;
                case 3:
                    System.out.println("Insira a nova cor do produto: ");
                    String cor = sc.next();
                    Tijolo.alteraCor(conexao, cor, id);
                    break;
                case 4:
                    System.out.println("Insira o novo peso do produto: ");
                    double peso = sc.nextDouble();
                    Tijolo.alteraPeso(conexao, peso, id);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }

        System.out.println("Produto alterado com sucesso!");
    }

    public static void ExcluirCliente(Scanner sc, Connection conexao) throws Exception {
        System.out.println("Insira o id do cliente a ser excluído: ");
        int id = sc.nextInt();
        Cliente cliente = Cliente.getCliente(conexao, id);
        Cliente.excluirCliente(conexao, id);
        System.out.println("Cliente "+ cliente.getNome()+" excluído com sucesso!");
    }

    public static void ExcluirProduto(Scanner sc, Connection conexao) throws Exception {
        System.out.println("Insira o id do produto a ser excluído: ");
        int id = sc.nextInt();
        Produto produto = Produto.getProduto(conexao, id);
        Produto.excluirProduto(conexao, id);
        System.out.println("Produto "+ produto.getNome() +" excluído com sucesso!");
    }

    public static void ExcluirOC(Scanner sc, Connection conexao) throws Exception {
        System.out.println("Insira o id da OC a ser excluída: ");
        int id = sc.nextInt();
        OrdemCompra oc = OrdemCompra.getOrdemCompra(conexao, id);
        OrdemCompra.excluirOrdemCompra(conexao, id);
        System.out.println("OC "+ oc.getId() +" excluída com sucesso!");
    }   

    public static void ListarClientes(Connection conexao) throws Exception{
        Cliente.imprimirClientes(conexao);
    }

    public static void ListarProdutos(Connection conexao) throws Exception{
        Produto.ImprimirProdutos(conexao);
    }

    public static void ListarOC(Connection conexao) throws Exception{
        OrdemCompra.ImprimirOrdensCompra(conexao);
    }
}
