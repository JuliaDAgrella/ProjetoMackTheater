public class EntradaProfessor extends Entrada {
    public EntradaProfessor(Espetaculo espetaculo, int numeroAssento, double valorBase) {
        super(espetaculo, numeroAssento, valorBase);
    }

    @Override
    public double calcularValor() {
        return valorBase * 0.6;
    }
}
