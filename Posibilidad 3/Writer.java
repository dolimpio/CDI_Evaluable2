import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Writer implements Runnable {
    int random = ThreadLocalRandom.current().nextInt(10000);
    private BlockingQueue buffer;
    AtomicInteger idCorreo ; //Por que no se protege esta sección si es un aotmic integer??
    
    public Writer(BlockingQueue buffer){
        this.buffer = buffer;
        idCorreo = new AtomicInteger(0); //Deberia hacer esta variable Atomic???
    }

    @Override
    public void run(){
        System.out.println("Hilo : " + Thread.currentThread().getName() + " iniciandose.");

        write();
 
    }
    public synchronized void write(){
        while(Posibilidad3.bandera){
            try {
                Thread.sleep(random);
                buffer.put(idCorreo.get());
                System.out.println("El 'Writer' " + Thread.currentThread().getName() + " ha enviado el correo: " + idCorreo + ".");
                idCorreo.getAndIncrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
