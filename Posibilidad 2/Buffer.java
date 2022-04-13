import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Buffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    int limite;
    static ArrayList<Integer> buffer;

    public Buffer(int limite) {
        buffer = new ArrayList<>();
        this.limite = limite;
    }

    public void recibir(int idCorreo) {
        lock.lock();
        try {
            while (buffer.size() == limite) { // Si el buffer esta lleno, hay que esperar.
                System.out.println(
                        "Es buffer esta lleno. El hilo 'Writer' va a esperar que exista un espacio en el buffer");
                notEmpty.await();
            }
            // Cuando hay un espacio vacio en el buffer, el correo entra
            buffer.add(idCorreo);
            System.out.println("Se ha recibido el correo: " + idCorreo);
            notFull.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // Pase lo que pase, siempre hay que liberar el lock
        }

    }

    public void abrir() {

        lock.lock();
        try {
            while (buffer.size() == 0) { // Si el buffer esta vacio, hay que esperar.
                System.out.println(
                        "Es buffer esta vacio. El hilo 'Reader' va a esperar que exista un correo disponible.");
                notFull.await();
            }
            // Cuando llega un correo al buffer, se lee
            System.out.println("Se ha leido el correo: " + buffer.get(0));
            buffer.remove(0);
            notEmpty.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // Pase lo que pase, siempre hay que liberar el lock
        }
    }

}