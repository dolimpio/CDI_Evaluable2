import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Writer implements Runnable {
    int random = ThreadLocalRandom.current().nextInt(10000);
    Buffer buffer;
    AtomicInteger idCorreo ; //Por que no se protege esta sección si es un aotmic integer??
    
    public Writer(Buffer buffer){
        this.buffer = buffer;
        idCorreo = new AtomicInteger(0); //Deberia hacer esta variable Atomic???
    }

    @Override
    public void run(){
        System.out.println("Hilo : " + Thread.currentThread().getName() + " iniciandose.");

        write();
 
    }
    public void write(){
        while(Posibilidad2.bandera){
            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //int correo = idCorreo.get();
            buffer.recibir(idCorreo.get());
            idCorreo.getAndIncrement();
        }

    }
}
