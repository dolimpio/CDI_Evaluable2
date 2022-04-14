import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Reader implements Runnable {
    int random = ThreadLocalRandom.current().nextInt(10000);
    private BlockingQueue buffer;

    public Reader(BlockingQueue buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        System.out.println("Hilo: " + Thread.currentThread().getName() + " iniciandose.");

        read();
    }

    public void read() {
        int correo;
        while (Posibilidad3.bandera) {
            try {
                Thread.sleep(random);
                
                //tengo que hacer esta comprobación para leer
                if( !buffer.isEmpty()){
                     correo = (int)buffer.peek();
                }else{
                    correo = -1; //Nunca va a tomar el valor '-1'. 
                                 //La accion buffer.take() solo se realizara si hay elementos en el buffer
                }  
            
                buffer.take();
                System.out.println("El 'Reader' " + Thread.currentThread().getName() + " ha leido el correo: " + correo + ".");             } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}