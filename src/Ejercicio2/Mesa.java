package Ejercicio2;

public class Mesa extends Thread {
    Filosofo[] filosofos;
    Palillo[] palillos;

    public Mesa(Filosofo[] filosofos, Palillo[] palillos) {
        this.filosofos = filosofos;
        this.palillos = palillos;
    }

    public void empiezaLaVelada() {
        for (Filosofo f : filosofos) {
            f.start();
        }
    }

    public void liberaTodosLosPalillos() {
        System.out.println("---Posible abrazo mortal, liberando todos los palillos---");
        for (Palillo p : palillos) {
            p.getSemaphore().release();
            p.setCogido(false);
        }
        for (Filosofo f : filosofos) {
            f.stop();
            f.start();
        }
    }

    @Override
    public void run() {
        //Hago esta comprobación para detectar un posible abrazo mortal, que cada filosofo tenga un palillo
        //y resolverlo de forma que todos suelten los palillos y vuelva a empezar el ciclo.
        //Hasta ahora no ha saltado ninguna vez, pero... por si acaso.
        while (true) {
            for (int i = 0; i < 5; i++) {
                if (filosofos[i].espera >= 15) {
                    liberaTodosLosPalillos();
                }
            }
        }
    }
}
