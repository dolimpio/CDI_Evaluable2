import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Writer implements Runnable {
    Buffer buffer;
    AtomicInteger idCorreo;

    public Writer(Buffer buffer) {
        this.buffer = buffer;
        idCorreo = new AtomicInteger(0); //Hacemos uso de AtomicInteger para que cada hilo, envie su correo de una manera mas segura.
    }

    @Override
    public void run() {
        System.out.println("Hilo : " + Thread.currentThread().getName() + " iniciandose.");
        write();

    }

    // Metodo encargado de enviar correos.
    // Generamos un número aleatorio para que el hilo duerma.
    // Enviamos un correo (Un numero entero). Cada hilo puede mandar el mismo
    // numero.
    public void write() {
        int random = ThreadLocalRandom.current().nextInt(1000);

        // Una variable en la clase Posibilidad2, controla hasta cuando se ejecuta el
        // programa.
        while (Posibilidad2.bandera) {
            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buffer.recibir(idCorreo.getAndIncrement());
        }

    }
}
