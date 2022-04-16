import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Reader implements Runnable {
    static ArrayList<Long> ArrayInicio = new ArrayList();
    static ArrayList<Long> ArrayFin = new ArrayList();
    
    Buffer buffer;

    public Reader(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        long tiempoInicio = System.nanoTime();
        read();
        // Guardamos tiempos (Cada posición corresponde a un hilo diferente).
        long tiempoFin = System.nanoTime();
        int id = Integer.parseInt(Thread.currentThread().getName());
        ArrayInicio.set(id, tiempoInicio);
        ArrayFin.set(id, tiempoFin);
    }

    // Metodo encargado de leer correos.
    public void read() {
        int random = ThreadLocalRandom.current().nextInt(100);

        // Una variable en la clase Posibilidad1, controla hasta cuando se ejecuta el
        // programa.
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