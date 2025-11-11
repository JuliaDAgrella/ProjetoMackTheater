public class Pedido {
    private Espetaculo espetaculo;
    private int numeroAssento;
    private String tipoEntrada;
    private double valor;

    public Pedido(Espetaculo espetaculo, int numeroAssento, int tipoEntrada, double valor) {
        this.espetaculo = espetaculo;
        this.numeroAssento = numeroAssento;
        this.valor = valor;

        switch (tipoEntrada) {
            case 1 -> this.tipoEntrada = "Inteira";
            case 2 -> this.tipoEntrada = "Meia";
            case 3 -> this.tipoEntrada = "Professor";
            default -> this.tipoEntrada = "Desconhecida";
        }
    }

    public Espetaculo getEspetaculo() {
        return espetaculo;
    }

    public int getNumeroAssento() {
        return numeroAssento;
    }

    public String getTipoEntrada() {
        return tipoEntrada;
    }

    public double getValor() {
        return valor;
    }
}
