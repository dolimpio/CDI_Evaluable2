import java.util.ArrayList;

public class Posibilidad1{
    public static void main(String[] args) {
        
        int numHilos = Integer.parseInt(args[0]);
        int tamBuffer = Integer.parseInt(args[1]);

        ArrayList<Thread> productores = new ArrayList<>();
        ArrayList<Thread> consumidores = new ArrayList<>();
        Buffer buffer = new Buffer(tamBuffer);

        for(int i = 0; i < numHilos; i++){
            productores.add(new Thread(new Writer(buffer), "Productor: "+i));
            consumidores.add(new Thread(new Reader(buffer), "Consumidor: "+i));
        }

        System.out.println("Se van a lanzar los hilos Productores y Consumidores.");
        for(int i = 0; i < numHilos; i++){
            productores.get(i).start();
            consumidores.get(i).start();
        }

        for(int i = 0; i < numHilos; i++){
            try{
            productores.get(i).join();
            System.out.println("El hilo " + productores.get(i).getName() + " ha terminado completamente.");

            consumidores.get(i).join();
            System.out.println("El hilo " + productores.get(i).getName() + " ha terminado completamente.");

            }catch(Exception e){}
        }
    }
}