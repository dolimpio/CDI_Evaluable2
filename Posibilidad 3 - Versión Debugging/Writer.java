import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Writer implements Runnable {
    private BlockingQueue buffer;
    int idCorreo;

    public Writer(BlockingQueue buffer) {
        this.buffer = buffer;
        idCorreo = 0;
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
    public synchronized void write() {
        int random = ThreadLocalRandom.current().nextInt(1000);

        // Una variable en la clase Posibilidad3, controla hasta cuando se ejecuta el
        // programa.
        while (Posibilidad3.bandera) {
            try {
                Thread.sleep(random);
                buffer.put(idCorreo); // La accion buffer.put() (introducir un elemento en el buffer) solo se
                                      // realizara si hay espacio en el buffer, en
                                      // caso contrario el hilo esperara hasta que exista un elemento que pueda ser
                                      // recibido.
                System.out.println(
                        "El 'Writer' " + Thread.currentThread().getName() + " ha enviado el correo: " + idCorreo + ".");
                idCorreo++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
