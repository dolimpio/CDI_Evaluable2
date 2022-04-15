import java.util.ArrayList;
import java.util.Scanner;

public class Posibilidad1 {

    static boolean bandera = true;

    public static void main(String[] args) {

        // Parametrizamos los argumentos introducidos

        int numProductores = Integer.parseInt(args[0]);

        int numConsumidores = Integer.parseInt(args[1]);

        int tamBuffer = Integer.parseInt(args[2]);

        // Si los parametros son correctos, se sigue con la ejecucción, si no, salimos
        // del programa.
        if (!comprobarParametros(numProductores, numConsumidores, tamBuffer) && args.length > 3) {
            System.out.println("Saliendo del programa...");
            System.exit(1);
        } else {

            // Imprimimos en pantalla los parametros introducidos.
            System.out.println("Inicializando el programa...");
            System.out.println("Nº Productores: " + numProductores +
                    "\nNº Consumidores: " + numConsumidores + "\nTamaño del Buffer: " + tamBuffer);

            //Inicializamos los Arrays de hilos, tanto productores como consumidores y el buffer.
            ArrayList<Thread> productores = new ArrayList<>();
            ArrayList<Thread> consumidores = new ArrayList<>();
            Buffer buffer = new Buffer(tamBuffer);

            //Introducimos los hilos en su correspondiente array.
            for (int i = 0; i < numProductores; i++) {
                productores.add(new Thread(new Writer(buffer), "Productor: " + i));
            }

            for (int i = 0; i < numConsumidores; i++) {
                consumidores.add(new Thread(new Reader(buffer), "Consumidor: " + i));
            }

            //Inicializamos los hilos. Como el buffer va a estar vacio, iniciamos primero a los Productores.
            System.out.println("Se van a lanzar los hilos Productores y Consumidores.");
            for (int i = 0; i < numProductores; i++) {
                productores.get(i).start();
            }

            for (int i = 0; i < numConsumidores; i++) {
                consumidores.get(i).start();
            }

            //Mediante la variable 'bandera' decidimos cuanto va a durar la ejecución de nuestro programa.
            try {
                Thread.sleep(10000);
                bandera = false;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Realizamos el joins.
            for (int i = 0; i < numProductores; i++) {
                try {
                    productores.get(i).join();
                    System.out.println("El hilo " + productores.get(i).getName() + " ha parado completamente.");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < numConsumidores; i++) {
                try {
                    consumidores.get(i).join();
                    System.out.println("El hilo " + consumidores.get(i).getName() + " ha parado completamente.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //Funcion que comprueba los argumentos.
    static boolean comprobarParametros(int a, int b, int c) {
        if (a < 0 || b < 0 || c < 0) {
            System.out.println("Los parametros tienen que ser numeros positivos.");
            return false;
        } else if (a == 0 || b == 0 || c == 0) {
            System.out.println("Los parametros tienen que ser mayor que 0.");
            return false;
        }

        return true;
    }
}