package Ejercicio1;


public class Consumidor extends Thread {
    int identificador;
    Buffer buffer;

    public Consumidor(int identificador, Buffer buffer){
        this.identificador=identificador;
        this.buffer=buffer;
    }

    @Override
    public void run(){
        try{
            while (true){
                if (buffer.consume(identificador)){
                    Thread.sleep((int)(Math.random()*2000)+2000);
                } else {
                    Thread.sleep((int)(Math.random()*2000)+3000);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
