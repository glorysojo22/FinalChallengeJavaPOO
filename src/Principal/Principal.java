package Principal;

import Modelo.ClienteHttp;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        ClienteHttp clienteHttp = new ClienteHttp();

        try {
            //Obtener el cambio
            JsonObject cambio = clienteHttp.obtenerCambio();

            //Interfaz
            Scanner scanner = new Scanner(System.in);

            while (true){
                System.out.println("\n--- Conversor de Monedas ---");
                System.out.println("1. Convertir de USD a otra moneda");
                System.out.println("2. Convertir de otra moneda a USD");
                System.out.println("3. Salir");
                System.out.println("Seleccione una opcion: ");
                int opcion = scanner.nextInt();

                if(opcion == 3){
                    System.out.println("Gracias por usar el conversor!");
                    break;
                }

                System.out.println("Ingrese la cantidad: ");
                double cantidad = scanner.nextDouble();

                System.out.println("Seleccione la moneda: ");
                System.out.println("1. ARS - Peso argentino");
                System.out.println("2. BOB - Boliviano boliviano");
                System.out.println("3. BRL - Real brasileño");
                System.out.println("4. CLP - Peso chileno");
                System.out.println("5. COP - Peso colombiano");
                int monedaSeleccionada = scanner.nextInt();

                String moneda = obtenerCodigoModena(monedaSeleccionada);
                if(moneda == null){
                    System.out.println("Opcion invalida. Intente nuevamente.");
                    continue;
                }

                double tasaCambio = cambio.get("conversion_rates").getAsJsonObject().get(moneda).getAsDouble();

                if (opcion == 1) {
                    // De USD a otra moneda
                    double resultado = cantidad * tasaCambio;
                    System.out.printf("%.2f USD equivalen a %.2f %s%n", cantidad, resultado, moneda);
                } else if (opcion == 2) {
                    // De otra moneda a USD
                    double resultado = cantidad / tasaCambio;
                    System.out.printf("%.2f %s equivalen a %.2f USD%n", cantidad, moneda, resultado);
                } else {
                    System.out.println("Opción inválida. Intente de nuevo.");
                }
            }
        }catch (Exception e){
            System.err.println("Ocurrio un error: " + e.getMessage());
        }
    }

    //Metodo para obtener el codigo de modenda segun la opcion
    private static String obtenerCodigoModena(int opcion){
        return switch (opcion){
            case 1 -> "ARS";
            case 2 -> "BOB";
            case 3 -> "BRL";
            case 4 -> "CLP";
            case 5 -> "COP";
            default -> null;
        };
    }
}
