public class Reader implements Runnable{
    Buffer buffer;
    public Reader(Buffer buffer){
        this.buffer = buffer;
    }
    public void run(){
        System.out.println("Hilo: " + Thread.currentThread().getName() + " iniciandose.");
        try {
            Thread.sleep(100000);
        } catch (Exception e) {
            //TODO: handle exception
        }
        read();
    }

    public synchronized void read(){//Se tienen que sincornizar con el buffer, checkear donde ajustar el synchronized
        while(buffer.size() == 0){//Puede usar funciones mejor?
            try {
                System.out.println("Es buffer esta vacio. El hilo 'Reader' va a esperar que exista un correo disponible.");
                wait();
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
        }
        buffer.abrir();
        notifyAll();
    }

}