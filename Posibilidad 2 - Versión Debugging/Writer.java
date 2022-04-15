import java.util.concurrent.ThreadLocalRandom;

public class Writer implements Runnable {
    Buffer buffer;
    int idCorreo;

    public Writer(Buffer buffer) {
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
            buffer.recibir(idCorreo);
            idCorreo++;
        }

    }
}
