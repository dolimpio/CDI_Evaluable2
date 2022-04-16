import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Reader implements Runnable {
    //Se declaran de manera estatica (Para poder acceder a ellos desde 'Posibilidad1') los arrays que guardaran el tiempo de ejecucion de los hilos
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
        long tiempoFin = System.nanoTime();
        int id = Integer.parseInt(Thread.currentThread().getName());
        // Guardamos tiempos (Cada posición corresponde a un hilo diferente).
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