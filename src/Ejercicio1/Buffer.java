package Ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Buffer {
    int tamanyo;
    Semaphore semaforo = new Semaphore(1);
    List<Integer> enteros = new ArrayList<>();

    public Buffer(int tamanyo) {
        this.tamanyo = tamanyo;
    }

    public synchronized boolean produce(int identificador, int valor){
        try{
            if (enteros.size()==tamanyo){
                System.out.println("LA LISTA ESTA LLENA, EL PRODUCTOR "+identificador+" NO PUEDE PRODUCIR");
                return false;
            }else{
                semaforo.acquire();
                System.out.println("El semáforo ha sido cogido por el productor ->" + identificador);
                System.out.println("El productor " + identificador + " esta produciendo, espere un momento.");
                Thread.sleep(1000);
                enteros.add(valor);
                System.out.println("El productor "+identificador+" ha producido el valor: [" + valor+"]");
                semaforo.release();
                System.out.println("El semáforo ha sido soltado por el productor ->" + identificador+"\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public synchronized boolean consume(int identificador){
        int consumido=0;
        try{
            if (enteros.isEmpty()){
                System.out.println("LA LISTA ESTA VACÍA, EL CONSUMIDOR "+identificador+" NO PUEDE CONSUMIR");
                return false;
            }else{
                semaforo.acquire();
                System.out.println("El semáforo ha sido cogido por el consumidor ->" + identificador);
                System.out.println("El consumidor "+identificador+" está consumiendo, dale un momento");
                Thread.sleep(500);
                consumido=(int)(Math.random()*enteros.size());
                enteros.remove(enteros.get(consumido));
                System.out.println("El consumidor "+identificador+" ha consumido.");
                semaforo.release();
                System.out.println("El semáforo ha sido soltado por el consumidor ->" + identificador + "\n");
            }
        }catch (Exception e){

        }
        return true;
    }
}
