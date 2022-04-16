import java.util.ArrayList;
import java.util.Collections;
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

            // Inicializamos los Arrays de hilos, tanto productores como consumidores y el
            // buffer.
            ArrayList<Thread> productores = new ArrayList<>();
            ArrayList<Thread> consumidores = new ArrayList<>();
            Buffer buffer = new Buffer(tamBuffer);

            // Introducimos los hilos en su correspondiente array.
            for (int i = 0; i < numProductores; i++) {
                productores.add(new Thread(new Writer(buffer), "" + i));
            }

            for (int i = 0; i < numConsumidores; i++) {
                consumidores.add(new Thread(new Reader(buffer), "" + i));
            }

            // Estos bucles rellenan los Array estaticos de ceros, de esta forma podemos usar
            // luego el metodo "set" en los hilos.
            for (int n = 0; n < numConsumidores; n++) {
                Reader.ArrayInicio.add((long) 0);
                Reader.ArrayFin.add((long) 0);
            }
            for (int n = 0; n < numProductores; n++) {
                Writer.ArrayInicio.add((long) 0);
                Writer.ArrayFin.add((long) 0);
            }


            // Inicializamos los hilos. Como el buffer va a estar vacio, iniciamos primero a
            // los Productores.
            for (int i = 0; i < numProductores; i++) {
                productores.get(i).start();
            }

            for (int i = 0; i < numConsumidores; i++) {
                consumidores.get(i).start();
            }

            // Mediante la variable 'bandera' decidimos cuanto va a durar la ejecución de
            // nuestro programa.
            try {
                Thread.sleep(10000);
                bandera = false;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Realizamos el joins.
            for (int i = 0; i < numProductores; i++) {
                try {
                    productores.get(i).join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < numConsumidores; i++) {
                try {
                    consumidores.get(i).join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            long tiempoInicialMin;
            long tiempoFinalMax;

            //Obtenemos el menor tiempo de ejecucion inicial de los Hilos Productores.
            long minProducerTime = Collections.min(Writer.ArrayInicio);

            //Obtenemos el mayor tiempo de ejecucion final de los Hilos Productores.
            long maxProducerTime = Collections.max(Writer.ArrayFin);

            //Obtenemos el menor tiempo de ejecucion inicial de los Hilos Consumidores.
            long minConsumerTime = Collections.min(Reader.ArrayInicio);

            //Obtenemos el mayor tiempo de ejecucion final de los Hilos Consumidores.
            long maxConsumerTime = Collections.max(Reader.ArrayFin);

            //Obtenemos el menor tiempo inicial de ejecucion entre Consumidores y Productores.
            if( minProducerTime < minConsumerTime) tiempoInicialMin = minProducerTime;
            else tiempoInicialMin = minConsumerTime;

            //Obtenemos el mayor tiempo final de ejecucion entre Consumidores y Productores.
            if( maxConsumerTime < maxProducerTime) tiempoFinalMax = maxProducerTime;
            else tiempoFinalMax = maxConsumerTime;


            //Calculamos el tiempo de ejecucion y el numero total de hilos.
            long elapsed = (tiempoFinalMax - tiempoInicialMin)/1000000;
            int numHilos = numConsumidores + numProductores;

            //Imprimimos el valor anterior por pantalla
            System.out.println(numHilos + " " + elapsed);

        }

    }

    // Funcion que comprueba los argumentos.
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