import java.util.ArrayList;

import java.util.ArrayList;

public class Buffer {
    int limite;
    static ArrayList<Integer> buffer;

    public Buffer(int limite) {
        buffer = new ArrayList<>();
        this.limite = limite;
    }

    public  void recibir(int idCorreo) {
        while (buffer.size() == limite) {// Si el buffer esta lleno, hay que esperar.
            try {
                System.out.println(
                        "Es buffer esta lleno. El hilo 'Writer' va a esperar que exista un espacio en el buffer");
                wait();
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        // Cuando hay un espacio vacio en el
        buffer.add(idCorreo);
        System.out.println("Se ha recibido el correo: " + idCorreo);
        notifyAll();
    }

    public  void abrir() {
        while (buffer.size() == 0) {// Puede usar funciones mejor?
            try {
                System.out.println(
                        "Es buffer esta vacio. El hilo 'Reader' va a esperar que exista un correo disponible.");
                wait();
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
        System.out.println("Se ha leido el correo: " + buffer.get(0));
        buffer.remove(0);
        notifyAll();
    }

    // Eliminar funciones
    public boolean isEmpty() {
        return buffer.isEmpty();
    }

    public boolean notFull() {
        return buffer.size() != limite;
    }
}