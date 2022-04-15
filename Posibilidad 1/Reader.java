import java.util.concurrent.ThreadLocalRandom;

public class Reader implements Runnable {
    Buffer buffer;

    public Reader(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        System.out.println("Hilo: " + Thread.currentThread().getName() + " iniciandose.");
        read();
    }

    //Metodo encargado de leer correos.
    public void read() {
        int random = ThreadLocalRandom.current().nextInt(100);

        //Una variable en la clase Posibilidad1, controla hasta cuando se ejecuta el programa.
        while (Posibilidad1.bandera) {
            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buffer.abrir();
        }
    }

}