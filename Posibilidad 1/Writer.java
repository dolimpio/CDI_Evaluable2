import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Writer implements Runnable {
    int random = ThreadLocalRandom.current().nextInt(10000);
    Buffer buffer;
    int idCorreo ; 
    public Writer(Buffer buffer){
        this.buffer = buffer;
        idCorreo = 0;
    }

    @Override
    public void run(){
        System.out.println("Hilo : " + Thread.currentThread().getName() + " iniciandose.");

        write();
 
    }
    public void write(){
        while(Posibilidad1.bandera){
            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buffer.recibir(idCorreo);
            idCorreo++;
        }

    }
}
