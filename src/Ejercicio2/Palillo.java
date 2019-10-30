package Ejercicio2;

import java.util.concurrent.Semaphore;

public class Palillo {
    protected int identificador;
    protected Semaphore semaphore = new Semaphore(1);
    protected boolean cogido = false;

    public Palillo(int identificador) {
        this.identificador = identificador;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public boolean estaCogido() {
        return cogido;
    }

    public void setCogido(boolean cogido) {
        this.cogido = cogido;
    }

    @Override
    public String toString() {
        return "Palillo{" +
                "identificador=" + identificador +
                '}';
    }
}
