import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class Posibilidad3{

    static boolean bandera = true;
    public static void main(String[] args) {

        //Se solicitan los parametros por consola

        //Comprobar que se introduzcan correctamente
        int numProductores = Integer.parseInt(args[0]);

        int numConsumidores = Integer.parseInt(args[1]);

        int tamBuffer = Integer.parseInt(args[2]);

        //Se confirma el resultado por consola
        System.out.println("Nº Productores: " + numProductores +
        "\nNº Consumidores: " + numConsumidores + "\nTamaño del Buffer: " + tamBuffer);

        BlockingQueue<Integer> buffer = new ArrayBlockingQueue<Integer>(tamBuffer);
        ArrayList<Thread> productores = new ArrayList<>();
        ArrayList<Thread> consumidores = new ArrayList<>();

        for(int i = 0; i < numProductores; i++){
            productores.add(new Thread(new Writer(buffer), "Productor: "+i));
        }

        for(int i = 0; i < numConsumidores; i++){
            consumidores.add(new Thread(new Reader(buffer), "Consumidor: "+i));
        }

        for(int i = 0; i < numProductores; i++){
            productores.add(new Thread(new Writer(buffer), "Productor: "+i));
        }

        for(int i = 0; i < numConsumidores; i++){
            consumidores.add(new Thread(new Reader(buffer), "Consumidor: "+i));
        }

        //Hay algun problema por empezarlos separadamente?
        System.out.println("Se van a lanzar los hilos Productores y Consumidores.");
        for(int i = 0; i < numProductores; i++){
            productores.get(i).start();
        }

        for(int i = 0; i < numConsumidores; i++){
            consumidores.get(i).start();
        }

        //Dejamos que la ejecuccion dure un tiempo
        try {
            
            Thread.sleep(10000);
            bandera = false;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Hay algun problema como el start() por no empezarlos en el mismo bucle?
        for(int i = 0; i < numProductores; i++){
            try{
                productores.get(i).join();
                System.out.println("El hilo " + productores.get(i).getName() + " ha terminado completamente.");
    
                }catch(Exception e){
                    e.printStackTrace();
                }        
        }

        for(int i = 0; i < numConsumidores; i++){
            try{
                consumidores.get(i).join();
                System.out.println("El hilo " + consumidores.get(i).getName() + " ha terminado completamente.");
    
                }catch(Exception e){
                    e.printStackTrace();
                }              
        }                      

    }
}