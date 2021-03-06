import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Writer implements Runnable {
    private BlockingQueue buffer;
    AtomicInteger idCorreo;

    public Writer(BlockingQueue buffer) {
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

        // Una variable en la clase Posibilidad3, controla hasta cuando se ejecuta el
        // programa.
        while (Posibilidad3.bandera) {
            try {
                Thread.sleep(random);
                buffer.put(idCorreo.get()); // La accion buffer.put() (introducir un elemento en el buffer) solo se
                                      // realizara si hay espacio en el buffer, en
                                      // caso contrario el hilo esperara hasta que exista un elemento que pueda ser
                                      // recibido.
                System.out.println(
                        "El 'Writer' " + Thread.currentThread().getName() + " ha enviado el correo: " + idCorreo + ".");
                        idCorreo.incrementAndGet();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
