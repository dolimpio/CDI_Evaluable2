public class Writer implements Runnable {
    Buffer buffer;
    public Writer(Buffer buffer){
        this.buffer = buffer;
    }
    public void run(){
        System.out.println("Hilo : " + Thread.currentThread().getName() + " iniciandose.");

        write();
 
    }
    
    public synchronized void write(){
        while(buffer.size() == buffer.limite){//Mejor usar funciones?
            try {
                System.out.println("Es buffer esta lleno. El hilo 'Writer' va a esperar que exista un espacio en el buffer");
                wait();
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
        }
            buffer.recibir();
            notifyAll();
    }
}
