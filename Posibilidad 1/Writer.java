public class Writer implements Runnable {
    Buffer buffer;
    int correo = 0;
    public Writer(Buffer buffer){
        this.buffer = buffer;
    }
    public void run(){
        
    }
    
    public void write(int n){
        while(!buffer.isEmpty()){
            buffer.recibir(n);
        }
        correo++;
    }
}
