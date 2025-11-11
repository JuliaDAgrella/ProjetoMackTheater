import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String cpf;
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado para este cliente.");
        } else {
            System.out.println("Pedidos do cliente " + nome + ":");
            for (Pedido p : pedidos) {
                System.out.printf("- Espetáculo: %s | Assento %d | Tipo: %s | Valor: R$ %.2f\n",
                    p.getEspetaculo().getNome(), p.getNumeroAssento(), p.getTipoEntrada(), p.getValor());
            }
        }
    }
}
