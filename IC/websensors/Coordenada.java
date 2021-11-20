package websensors;

/**
 * Classe de criação da Coordenada
 * @author Paulo Henrique Lima de Paula
 */
public class Coordenada {
    private String id;
    double lat;
    double lon;

    /** Método construtor da Coordenada
    * @author Paulo Henrique Lima de Paula 
    */
    public Coordenada() {
        this.id = "";
        this.lat = 0.0;
        this.lon = 0.0;
    }
    /** Método get do ID
    * @author Paulo Henrique Lima de Paula
    *   @return String - id*/
    public String getId() {
        return id;
    }
    /** Método set do ID
    * @author Paulo Henrique Lima de Paula
    *   seta um valor ao id*/
    public void setId(String id) {
        this.id = id;
    }
    /** Método get da latitude(lat)
    * @author Paulo Henrique Lima de Paula
    *   @return double - lat*/
    public double getLat() {
        return lat;
    }
    /** Método set da latitude(lat)
    * @author Paulo Henrique Lima de Paula
    *   seta um valor da latitude (lat)*/
    public void setLat(double lat) {
        this.lat = lat;
    }
    /** Método get da longitude(lon)
    * @author Paulo Henrique Lima de Paula
    *   @return double - lon*/
    public double getLon() {
        return lon;
    }
    /** Método set da longitude(lon)
    * @author Paulo Henrique Lima de Paula
    *   seta um valor da longitude(lon)*/
    public void setLon(double lon) {
        this.lon = lon;
    }
    /** Método to String
    *@author Paulo Henrique Lima de Paula 
    */
    @Override
    public String toString() {
        return "Coordenada{" + "id=" + id + ", lat=" + lat + ", lon=" + lon + '}';
    }
}