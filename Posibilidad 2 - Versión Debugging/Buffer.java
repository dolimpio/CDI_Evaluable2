import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Buffer {
    final Lock lock;
    final Condition notFull;
    final Condition notEmpty;
    int limite;
    static ArrayList<Integer> buffer;

    // Inicializamo Buffer con el lock y las condiciones adecuadas.
    public Buffer(int limite) {
        this.limite = limite;
        buffer = new ArrayList<>();
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public void recibir(int idCorreo) {
        lock.lock(); // Protegemos la parte de codigo crítica con el lock.
        try {
            while (buffer.size() == limite) { // Si el buffer esta lleno, se espera.
                System.out.println(
                        "Es buffer esta lleno. El hilo 'Writer' va a esperar que exista un espacio en el buffer");
                notEmpty.await(); // Con la condición notEmpty, el buffer esta lleno, por lo que el hilo espera.
                                  // El buffer no puede recibir correos.
            }
            // Cuando hay un espacio vacio en el buffer, añadimos un correo al buffer.
            buffer.add(idCorreo);
            System.out.println("Se ha recibido el correo: " + idCorreo);
            // Se avisa a todos los hilos que esten esperando con la condicion notFull.
            notFull.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // Pase lo que pase, siempre hay que liberar el lock
        }

    }

    public void abrir() {
        lock.lock(); // Protegemos la parte de codigo crítica con el lock.
        try {
            while (buffer.size() == 0) { // Si el buffer esta vacio, se espera.
                System.out.println(
                        "Es buffer esta vacio. El hilo 'Reader' va a esperar que exista un correo disponible.");
                notFull.await(); // Con la condición notFull, el buffer esta vacio, por lo que el hilo espera. No
                                 // se pueden abrir correos que aun no llegaron al buffer.
            }
            // Cuando hay algún correo en el buffer, se lee.
            System.out.println("Se ha leido el correo: " + buffer.get(0));
            buffer.remove(0);
            // Se avisa a todos los hilos que esten esperando con la condicion notEmpty.
            notEmpty.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // Pase lo que pase, siempre hay que liberar el lock
        }
    }

}