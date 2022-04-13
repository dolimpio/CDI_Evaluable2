import java.util.ArrayList;
import java.util.Scanner;

public class Posibilidad2{

    static boolean bandera = true;
    public static void main(String[] args) {

        //Se solicitan los parametros por consola

        Scanner sc = new Scanner(System.in);   

        System.out.println("Introduce el numero de 'Writers' (Productores): ");
        int numProductores = Character. getNumericValue(sc.next().charAt(0));

        System.out.println("Introduce el numero de 'Readers' (Consumidores): ");
        int numConsumidores = Character. getNumericValue(sc.next().charAt(0));

        System.out.println("Introduce el tamaño del 'Buffer': ");
        int tamBuffer = Character. getNumericValue(sc.next().charAt(0));

        //Se confirma el resultado por consola
        System.out.println("Nº Productores: " + numProductores +
        "\nNº Consumidores: " + numConsumidores + "\nTamaño del Buffer: " + tamBuffer);


        ArrayList<Thread> productores = new ArrayList<>();
        ArrayList<Thread> consumidores = new ArrayList<>();
        Buffer buffer = new Buffer(tamBuffer);

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

        } catch (Exception e) {
            //TODO: handle exception
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