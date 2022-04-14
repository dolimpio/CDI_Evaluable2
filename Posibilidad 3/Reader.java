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
        while (Posibilidad3.bandera) {
            try {
                Thread.sleep(random);

                int correo = (int)buffer.peek();
                buffer.take();
                System.out.println("Se ha leido el correo: " + correo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}