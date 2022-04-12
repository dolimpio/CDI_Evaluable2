import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Writer implements Runnable {
    int random = ThreadLocalRandom.current().nextInt(10000);
    Buffer buffer;
    AtomicInteger idCorreo ; //Hacemos que el idCorreo sea Atomico, simplemente para distinguir los correos
    public Writer(Buffer buffer){
        this.buffer = buffer;
        idCorreo = new AtomicInteger(0); //Deberia hacer esta variable Atomic o deberia ser un int normal????
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
            int correo = idCorreo.get();
            buffer.recibir(correo);
            idCorreo.getAndIncrement();
        }

    }
}
