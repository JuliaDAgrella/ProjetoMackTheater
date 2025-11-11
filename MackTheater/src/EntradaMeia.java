public class EntradaMeia extends Entrada {
    public EntradaMeia(Espetaculo espetaculo, int numeroAssento, double valorBase) {
        super(espetaculo, numeroAssento, valorBase);
    }

    @Override
    public double calcularValor() {
        return valorBase * 0.5;
    }
}
