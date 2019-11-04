package Ejercicio2;

public class Filosofo extends Thread {
    protected Mesa mesa;
    protected int identificador;
    protected int palilloIzquierda;
    protected int palilloDerecha;
    protected int espera;

    public Filosofo(int identificador) {
        this.identificador = identificador;
        this.palilloDerecha = identificador;
        if (identificador == 0) {
            this.palilloIzquierda = 4;
        } else {
            this.palilloIzquierda = identificador - 1;
        }
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public boolean estaCogidoPalillo(int identificadorPalillo) {
        return (mesa.palillos[identificadorPalillo].estaCogido());
    }

    @Override
    public void run() {
        try {
            //Bucle de ejecución infinita
            while (true) {
                System.out.println("Filosofo " + identificador + " -> Empezando a pensar");
                Thread.sleep((int) (Math.random() * 5000));
                System.out.println("Filosofo " + identificador + " -> Tiene hambre, intentar comer");
                //Bucle de comprobación del palillo de la izquierda está disponible
                while (!mesa.palillos[palilloIzquierda].semaphore.tryAcquire()) {
                    Thread.sleep(1000);
                    espera++;
                    System.out.println("Filosofo " + identificador + " -> Palillo izquierda ocupado, esperando...");
                }
                //Si llega aquí, es que ha cogido el palillo de la izquierda
                mesa.palillos[palilloIzquierda].setCogido(true);
                System.out.println("Filosofo " + identificador + " -> Palillo izquierda cogido.");
                //Bucle de comprobación del palillo de la derecha está disponible
                while (!mesa.palillos[palilloDerecha].semaphore.tryAcquire()) {
                    Thread.sleep(1000);
                    espera++;
                    System.out.println("Filosofo " + identificador + " -> Palillo derecha ocupado, esperando...");
                }
                //Si llega aquí, es que ha cogido el palillo de la derecha
                mesa.palillos[palilloDerecha].setCogido(true);
                System.out.println("Filosofo " + identificador + " -> Palillo derecha cogido.");
                //Si llega aquí, es que tiene los dos palillos, y empieza a comer.
                System.out.println("Filosofo " + identificador + " -> Tiene los dos palillos, a comer.");
                Thread.sleep(3000);
                //Cuando llegue aquí, habrá dejado de comer
                System.out.println("Filosofo " + identificador + " -> Ha comido, liberando palillos");
                mesa.palillos[palilloIzquierda].setCogido(true);
                mesa.palillos[palilloDerecha].setCogido(true);
                mesa.palillos[palilloIzquierda].semaphore.release();
                mesa.palillos[palilloDerecha].semaphore.release();
                Thread.sleep(3000);
                //Reiniciando el tiempo de espera
                this.espera = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
