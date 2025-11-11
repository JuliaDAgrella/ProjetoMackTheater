import java.util.Arrays;

public class Espetaculo {
    private String nome;
    private String data;
    private String hora;
    private double preco;
    private boolean[] assentos;
    private String[] compradorCpf;
    private String[] compradorNome;

    public Espetaculo(String nome, String data, String hora, double preco) {
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.preco = preco;
        this.assentos = new boolean[50]; // true = livre, false = ocupado
        this.compradorCpf = new String[50];
        this.compradorNome = new String[50];
        Arrays.fill(this.assentos, true);
    }

    public String getNome() {
        return nome;
    }

    public String getHora() {
        return hora;
    }

    public double getPreco() {
        return preco;
    }

    public void apresentarAssentos() {
        System.out.println("Assentos Disponíveis:");
        for (int i = 0; i < assentos.length; i++) {
            System.out.printf("%s\t", assentos[i] ? (i + 1) : "XX");
            if ((i + 1) % 10 == 0) System.out.println();
        }
    }

    public double comprarAssento(int numero, int tipoEntrada, String cpfComprador, String nomeComprador) {
        // Verifica se o assento é válido
        if (numero < 1 || numero > assentos.length) {
            System.out.println("Número de assento inválido!");
            return -1;
        }

        // Verifica se o assento já está ocupado
        if (!assentos[numero - 1]) {
            System.out.println("Assento " + numero + " já está ocupado!");
            return -1;
        }

        // Marca o assento como ocupado
        assentos[numero - 1] = false;
        compradorCpf[numero - 1] = cpfComprador;
        compradorNome[numero - 1] = nomeComprador;

        // Calcula o valor da entrada
        switch (tipoEntrada) {
            case 1: return preco;          // Inteira
            case 2: return preco * 0.5;    // Meia
            case 3: return preco * 0.6;    // Professor
            default: return -1;
        }
    }

    public boolean isAssentoDisponivel(int numero) {
        if (numero < 1 || numero > assentos.length) return false;
        return assentos[numero - 1];
    }



    public void atualizarComprador(int numeroAssento, String cpf, String nome) {
        if (numeroAssento >= 1 && numeroAssento <= assentos.length) {
            compradorCpf[numeroAssento - 1] = cpf;
            compradorNome[numeroAssento - 1] = nome;
        }
    }


    public void mostrarAssentosDetalhados() {
        System.out.println("Assentos e Compradores:");
        for (int i = 0; i < assentos.length; i++) {
            String status = assentos[i]
                ? "Disponível"
                : "Comprado por: CPF " + compradorCpf[i] + " - Nome: " + compradorNome[i];
            System.out.printf("Assento %02d: %s\n", i + 1, status);
        }
    }
}
