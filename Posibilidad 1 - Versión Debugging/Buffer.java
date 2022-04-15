import java.util.ArrayList;

import java.util.ArrayList;

public class Buffer {
    int limite;
    static ArrayList<Integer> buffer;

    public Buffer(int limite) {
        this.limite = limite;
        buffer = new ArrayList<>();
    }

    //Bloque de codigo sincronizado para recibir correos.
    public synchronized void recibir(int idCorreo) {
        while (buffer.size() == limite) {// Si el buffer esta lleno, se espera.
            try {
                System.out.println(
                        "El buffer esta lleno. El hilo 'Writer' va a esperar que exista un espacio en el buffer");
                //El hilo se pone en modo espera hasta que 'alguien' le avise de que puede recibir un correo mas.
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Cuando hay un espacio vacio en el buffer, añadimos un correo al buffer.
        buffer.add(idCorreo);
        System.out.println("Se ha recibido el correo: " + idCorreo);
        //Se avisa a todos los hilos que esten esperando.
        notifyAll();
    }

    //Bloque de codigo sincronizado para leer correos.
    public synchronized void abrir() {
        while (buffer.size() == 0) { //Si el buffer esta vacio, se espera.
            try {
                System.out.println(
                        "Es buffer esta vacio. El hilo 'Reader' va a esperar que exista un correo disponible.");
                //El hilo se pone a la espera de que 'alguien' le avise que puede leer un correo mas.
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Cuando hay algún correo en el buffer, se lee.
        System.out.println("Se ha leido el correo: " + buffer.get(0));
        buffer.remove(0);
        //Se avisa a todos los hilos que esten esperando.
        notifyAll();
    }
}