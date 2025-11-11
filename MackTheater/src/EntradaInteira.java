public class EntradaInteira extends Entrada {
    public EntradaInteira(Espetaculo espetaculo, int numeroAssento, double valorBase) {
        super(espetaculo, numeroAssento, valorBase);
    }

    @Override
    public double calcularValor() {
        return valorBase;
    }
}
