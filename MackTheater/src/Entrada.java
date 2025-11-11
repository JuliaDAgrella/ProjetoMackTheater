public abstract class Entrada {
    protected Espetaculo espetaculo;
    protected int numeroAssento;
    protected double valorBase;

    public Entrada(Espetaculo espetaculo, int numeroAssento, double valorBase) {
        this.espetaculo = espetaculo;
        this.numeroAssento = numeroAssento;
        this.valorBase = valorBase;
    }

    public abstract double calcularValor();

    public Espetaculo getEspetaculo() {
        return espetaculo;
    }

    public int getNumeroAssento() {
        return numeroAssento;
    }

    public double getValorBase() {
        return valorBase;
    }
}
