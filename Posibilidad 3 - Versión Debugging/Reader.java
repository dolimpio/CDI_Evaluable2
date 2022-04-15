import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Reader implements Runnable {
    private BlockingQueue buffer;

    public Reader(BlockingQueue buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        System.out.println("Hilo: " + Thread.currentThread().getName() + " iniciandose.");
        read();
    }

    // Metodo encargado de leer correos.
    public void read() {
        int random = ThreadLocalRandom.current().nextInt(1000);
        // Una variable en la clase Posibilidad3, controla hasta cuando se ejecuta el
        // programa.
        while (Posibilidad3.bandera) {
            try {
                Thread.sleep(random);

                buffer.take(); // La accion buffer.take() (retirar un elemento del buffer) solo se realizara si
                               // hay elementos en el buffer, en
                               // caso contrario el hilo esperara hasta que exista un elemento que pueda ser
                               // leido.

                System.out.println(
                        "El 'Reader' " + Thread.currentThread().getName()
                                + " ha leido el primer correo de la bandeja de entrada.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}