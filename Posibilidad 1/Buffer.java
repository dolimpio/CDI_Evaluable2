import java.util.ArrayList;

import java.util.ArrayList;
public class Buffer {

    ArrayList<Integer> buffer;
    public Buffer(int n){
        buffer = new ArrayList<>(n);
    }

    public synchronized void recibir(int n){
        buffer.add(n);

    }

    public synchronized void abrir(){

    }

    public boolean isEmpty(){
        return buffer.isEmpty();
    }
}