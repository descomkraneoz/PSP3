package Ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Buffer b = new Buffer(5);
        List<Productor> productores = new ArrayList<>();
        List<Consumidor> consumidores = new ArrayList<>();
        for (int i=0;i<5;i++){
            productores.add(new Productor(i,b));
        }
        for (Productor p : productores){
            p.start();
        }
        for (int i=0;i<5;i++){
            consumidores.add(new Consumidor(i,b));
        }
        for (Consumidor c : consumidores){
            c.start();
        }
}
}
