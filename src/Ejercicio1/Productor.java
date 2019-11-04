package Ejercicio1;

public class Productor extends Thread{
    int identificador;
    Buffer buffer;

    public Productor(int identificador, Buffer buffer) {
        this.identificador = identificador;
        this.buffer=buffer;
    }

    @Override
    public void run(){
        try{
            while (true){
                int valor = (int) (Math.random() * 10) + 1; //gracias a esta linea el productor va a llenar la lista y dejara de producir
                if (buffer.produce(identificador,valor)){
                    Thread.sleep((int)(Math.random()*2000)+2000);
                }else{
                    Thread.sleep((int)(Math.random()*2000)+3000);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
