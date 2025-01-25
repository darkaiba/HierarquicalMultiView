package websensorsdate;

import java.util.ArrayList;

/**
 * Classe de criação da Dates
 * @author Paulo Henrique Lima de Paula
 */
public class Dates {
    private String id;
    private ArrayList datas;

    /** Método construtor da Dates
    * @author Paulo Henrique Lima de Paula 
    */
    public Dates() {
        this.id = "";
        this.datas = new ArrayList();
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
    /** Método get das Datas
    * @author Paulo Henrique Lima de Paula
    *   @return ArrayList*/
    public ArrayList getDatas() {
        return datas;
    }
    /** Método set das Datas
    * @author Paulo Henrique Lima de Paula
    *   seta o ArrayList das datas*/
    public void setDatas(ArrayList datas) {
        this.datas = datas;
    }
    /** Método to String
    * @author Paulo Henrique Lima de Paula
    *   printa na tela os ids e o array das datas*/
    @Override
    public String toString() {
        return "Dates{" + "id=" + id + ", datas=" + datas + '}';
    }
    
}
