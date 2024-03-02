package group02.weatherforecast;

import org.springframework.web.client.RestTemplate;

@SuppressWarnings("unchecked")
public class Application {

    public static String URL_GEO_PREFIX = "https://geocode.maps.co/search?q=";//Polska,Warszawa,Chmielna,00-020";
    public static String URL_GEO_SUFIX = "&api_key=65e2f07a7c763292633381fac5b8a44";
    public static String URL_METEO_PREFIX = "https://api.openweathermap.org/data/2.5/weather?lat=";
    public static String URL_METEO_BETWEEN = "&lon=";
    public static String URL_METEO_SUFIX = "&appid=1139bee7b46f1baf02438a443c237bad";

    public static void main(String[] args) {

        UserDataInput test;
         UserDataInput userDataInputReader = new UserDataInput();
         String localization = userDataInputReader.getData();
         System.out.println(localization);
        String[] text = localization.split(",");
        String country = text[0].trim();
        String city = text[1].trim();
        String postCode = text[2].trim();
        String streetName = text[3].trim();

        RestTemplate restTemplate = new RestTemplate();

        String urlGeo = new StringBuilder()
                .append(URL_GEO_PREFIX)
                .append(country)
                .append(",")
                .append(city)
                .append(",")
                .append(postCode)
                .append(",")
                .append(streetName)
                .append(URL_GEO_SUFIX).toString();

        Geo[] geos = restTemplate.getForObject(urlGeo, Geo[].class);
        String lat = geos[0].getLat().substring(0, geos[0].getLat().indexOf(".") + 3);
        String lon = geos[0].getLon().substring(0, geos[0].getLon().indexOf(".") + 3);
        System.out.println("Geolokalizacja: " + "szerokość: " + lat + " długość: " + lon);

        String urlMeteo = new StringBuilder()
                .append(URL_METEO_PREFIX)
                .append(geos[0].getLat())
                .append(URL_METEO_BETWEEN)
                .append(geos[0].getLon())
                .append(URL_METEO_SUFIX).toString();

        Meteo meteo = restTemplate.getForObject(urlMeteo, Meteo.class);
        String temp = String.format("%.2f", (Float.parseFloat(meteo.getMain().getTemp()) - 273.15));
        System.out.println("Meteo: " + "temp: " + temp + " pressure: " + meteo.getMain().getPressure() + " humidity: " + meteo.getMain().getHumidity());
        System.out.println("************************************************");

    }
}
