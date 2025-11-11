import java.util.ArrayList;
import java.util.Scanner;

public class MackTheater {
    private static ArrayList<Espetaculo> espetaculos = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n---- MACK THEATER ----");
            System.out.println("1) Cadastrar Espetáculo");
            System.out.println("2) Cadastrar Cliente");
            System.out.println("3) Compra de Entradas");
            System.out.println("4) Verificar Assento");
            System.out.println("5) Sair");
            System.out.print("--Selecione uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarEspetaculo();
                case 2 -> cadastrarCliente();
                case 3 -> compraDeEntradas();
                case 4 -> verificarAssento();
                case 5 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }


    private static void cadastrarEspetaculo() {
        System.out.println("\n---- CADASTRO DE ESPETÁCULO ----");
        System.out.print("-Nome do Espetáculo: ");
        String nome = sc.nextLine();
        System.out.print("-Data (dd/mm/yyyy): ");
        String data = sc.nextLine();
        System.out.print("-Hora (HH:mm): ");
        String hora = sc.nextLine();
        System.out.print("-Preço da Entrada Inteira: ");
        double preco = sc.nextDouble();
        sc.nextLine();

        Espetaculo espetaculo = new Espetaculo(nome, data, hora, preco);
        espetaculos.add(espetaculo);
        System.out.println("Espetáculo cadastrado com sucesso!\n");
    }


    private static void cadastrarCliente() {
        System.out.println("\n---- CADASTRO DE CLIENTE ----");
        System.out.print("-Nome do Cliente: ");
        String nome = sc.nextLine();
        System.out.print("-CPF: ");
        String cpf = sc.nextLine();

        Cliente cliente = new Cliente(nome, cpf);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!\n");
    }


    private static Cliente buscarClientePorCPF(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    private static void compraDeEntradas() {
        if (espetaculos.isEmpty()) {
            System.out.println("Nenhum espetáculo cadastrado.");
            return;
        }

        System.out.println("\n---- VENDA DE ENTRADAS ----");
        for (int i = 0; i < espetaculos.size(); i++) {
            Espetaculo e = espetaculos.get(i);
            System.out.printf("%d) %s - %s - R$ %.2f\n", i + 1, e.getNome(), e.getHora(), e.getPreco());
        }

        System.out.print("Selecione um espetáculo: ");
        int opcaoEspetaculo = sc.nextInt() - 1;
        sc.nextLine();

        if (opcaoEspetaculo < 0 || opcaoEspetaculo >= espetaculos.size()) {
            System.out.println("Espetáculo inválido.");
            return;
        }

        Espetaculo espetaculo = espetaculos.get(opcaoEspetaculo);
        double total = 0;
        ArrayList<Pedido> pedidosDoCliente = new ArrayList<>();

        //Loop principal de compra
        while (true) {
            System.out.println("\n|| Assentos Disponíveis ||");
            espetaculo.apresentarAssentos();

            System.out.print("\nSelecione um assento: ");
            int assento = sc.nextInt();
            sc.nextLine();

            // Validação de número do assento
            if (assento < 1 || assento > 50) {
                System.out.println("Número de assento inválido!");
                continue;
            }

            // Verifica se o assento já está ocupado antes de escolher o tipo
            if (!espetaculo.isAssentoDisponivel(assento)) {
                System.out.println("!!Assento " + assento + " já está ocupado! Escolha outro.");
                continue; // volta para o início do loop pedindo novo assento
            }

            // Só chega aqui se o assento estiver livre
            System.out.println("\nTipos de Entrada");
            System.out.println("1) Inteira");
            System.out.println("2) Meia - 50% do valor");
            System.out.println("3) Professor - 40% do valor");
            System.out.print("Selecione o tipo de entrada: ");
            int tipo = sc.nextInt();
            sc.nextLine();

            // Cria a Entrada conforme o tipo
            Entrada entrada = switch (tipo) {
                case 1 -> new EntradaInteira(espetaculo, assento, espetaculo.getPreco());
                case 2 -> new EntradaMeia(espetaculo, assento, espetaculo.getPreco());
                case 3 -> new EntradaProfessor(espetaculo, assento, espetaculo.getPreco());
                default -> null;
            };

            if (entrada == null) {
                System.out.println("Tipo de entrada inválido.");
                continue;
            }

            double precoEntrada = entrada.calcularValor();
            double retornoCompra = espetaculo.comprarAssento(assento, tipo, "", "");

            if (retornoCompra == -1) {
                System.out.println("Erro ao processar o assento.");
            } else {
                total += precoEntrada;
                pedidosDoCliente.add(new Pedido(espetaculo, assento, tipo, precoEntrada));
                System.out.println(" Entrada adicionada ao carrinho!");
            }

            System.out.print("\nDeseja comprar outra entrada (S/N)? ");
            if (sc.nextLine().equalsIgnoreCase("N")) break;
        }

        // Finalização da compra
        if (pedidosDoCliente.isEmpty()) {
            System.out.println("Nenhuma entrada foi comprada.");
            return;
        }

        System.out.print("\nInforme o CPF do comprador: ");
        String cpfComprador = sc.nextLine();

        System.out.print("Informe o nome do comprador: ");
        String nomeComprador = sc.nextLine();

        // Verifica se o cliente já existe
        Cliente cliente = buscarClientePorCPF(cpfComprador);
        if (cliente == null) {
            cliente = new Cliente(nomeComprador, cpfComprador);
            clientes.add(cliente);
        }

        // Registra os pedidos e atualiza os assentos com o nome e CPF do comprador
        for (Pedido p : pedidosDoCliente) {
            cliente.adicionarPedido(p);
            espetaculo.atualizarComprador(p.getNumeroAssento(), cpfComprador, nomeComprador);
        }

        System.out.printf("\nValor Total: R$ %.2f\n", total);
        System.out.println("Compra finalizada com sucesso!");
    }


    private static void verificarAssento() {
        if (espetaculos.isEmpty()) {
            System.out.println("Nenhum espetáculo cadastrado.");
            return;
        }

        System.out.println("\n---- VERIFICAÇÃO DE ASSENTOS ----");
        for (int i = 0; i < espetaculos.size(); i++) {
            Espetaculo e = espetaculos.get(i);
            System.out.printf("%d) %s - %s\n", i + 1, e.getNome(), e.getHora());
        }

        System.out.print("Selecione um espetáculo: ");
        int opcaoEspetaculo = sc.nextInt() - 1;
        sc.nextLine();

        if (opcaoEspetaculo < 0 || opcaoEspetaculo >= espetaculos.size()) {
            System.out.println("Espetáculo inválido.");
            return;
        }

        Espetaculo espetaculo = espetaculos.get(opcaoEspetaculo);
        espetaculo.mostrarAssentosDetalhados();
    }
}
