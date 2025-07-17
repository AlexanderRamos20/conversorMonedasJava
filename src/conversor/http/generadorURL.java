package conversor.http;

public class generadorURL {
    private static final String ApiBaseURL = "https://v6.exchangerate-api.com/v6/";
    private static final String ApiKey = "691ca8d4a3094a6e633fe23c";

    public static String generarURL(String unidadBase, String unidadObjetivo) {
        return ApiBaseURL + ApiKey + "/pair/" + unidadBase + "/" + unidadObjetivo;
    }
}
