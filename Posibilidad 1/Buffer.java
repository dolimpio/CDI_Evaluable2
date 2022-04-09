import java.util.ArrayList;

import java.util.ArrayList;
public class Buffer {
    int limite;
    int idCorreo;

    static ArrayList<Integer> buffer;
    public Buffer(int limite){
        buffer = new ArrayList<>();
        this.limite = limite;
        idCorreo = 0;
    }

    public synchronized void recibir(){
        if(buffer.size() < limite){
            buffer.add(idCorreo);
            idCorreo++;
            System.out.println("Se ha recibido el correo: " + idCorreo);
        }


    }

    public synchronized void abrir(){
        if(buffer.isEmpty()){
            System.out.println("Se ha leido el correo: " + buffer.get(0));
            buffer.remove(0);
        }else{
            System.out.println("No hay correos nuevos.");
        }

    }

    public boolean isEmpty(){
        return buffer.isEmpty();
    }

    public boolean notFull(){
        return  buffer.size() != limite ;
    }

    //Necesitamos esta funcion para poder acceder al tamanho del buffer desde otras clases.
    public int size(){
        return  buffer.size();
    }
}