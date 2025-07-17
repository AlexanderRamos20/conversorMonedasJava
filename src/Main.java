import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import conversor.http.ConexionAPI;
import conversor.http.generadorURL;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al conversor!");

        while (true){
            Map<Integer, String[]> opcionesConversion = new HashMap<>();
            opcionesConversion.put(1, new String[]{"USD", "CLP"});
            opcionesConversion.put(2, new String[]{"CLP", "USD"});
            opcionesConversion.put(3, new String[]{"USD", "BRL"});
            opcionesConversion.put(4, new String[]{"BRL", "USD"});
            opcionesConversion.put(5, new String[]{"CLP", "BRL"});
            opcionesConversion.put(6, new String[]{"BRL", "CLP"});

            System.out.println("===== CONVERSOR DE MONEDAS =====");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Dólar (USD) → Peso Chileno (CLP)");
            System.out.println("2. Peso Chileno (CLP) → Dólar (USD)");
            System.out.println("3. Dólar (USD) → Real Brasileño (BRL)");
            System.out.println("4. Real Brasileño (BRL) → Dólar (USD)");
            System.out.println("5. Peso Chileno (CLP) → Real Brasileño (BRL)");
            System.out.println("6. Real Brasileño (BRL) → Peso Chileno (CLP)");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción (0-6): ");
            ArrayList<Integer> respuestasValidas  = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

            try {
                int respuesta = scanner.nextInt();
                if (respuesta == 0) {
                    System.out.println("Hasta luego!");
                    break;
                }

                if (respuestasValidas.contains(respuesta)){
                    String [] opcionSeleccionada = opcionesConversion.get(respuesta);
                    String url =  generadorURL.generarURL(opcionSeleccionada[0], opcionSeleccionada[1]);
                    String json = ConexionAPI.consumirApi(url);
                    JsonObject jsonObj = JsonParser.parseString(json).getAsJsonObject();
                    double tasa = jsonObj.get("conversion_rate").getAsDouble();
                    try {
                        System.out.println("Ingrese el monto a convertir");
                        double montoAConvertir = scanner.nextDouble();
                        double resultado = montoAConvertir * tasa;
                        System.out.println(montoAConvertir + " " + opcionSeleccionada[0] +
                                " son "+ resultado + " "+ opcionSeleccionada[1]);
                    } catch (InputMismatchException e){
                        System.out.println("No se ingresó un monto válido.");
                    }
                }

            } catch (InputMismatchException e){
                System.out.println("No se ingresó una opción numérica");
                scanner.nextLine();
                }

        }

    }
}