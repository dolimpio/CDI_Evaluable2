import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Reader implements Runnable {
    //Se declaran de manera estatica (Para poder acceder a ellos desde 'Posibilidad1') los arrays que guardaran el tiempo de ejecucion de los hilos
    static ArrayList<Long> ArrayInicio = new ArrayList();
    static ArrayList<Long> ArrayFin = new ArrayList();
    
    private BlockingQueue buffer;

    public Reader(BlockingQueue buffer) {
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

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}