import java.util.concurrent.ThreadLocalRandom;

public class Reader implements Runnable {
    int random = ThreadLocalRandom.current().nextInt(10000);
    Buffer buffer;

    public Reader(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        System.out.println("Hilo: " + Thread.currentThread().getName() + " iniciandose.");

        read();
    }

    public void read() {// Se tienen que sincornizar con el buffer, checkear donde ajustar el
                        // synchronized
        while (Posibilidad2.bandera) {
            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buffer.abrir();
        }
    }

}