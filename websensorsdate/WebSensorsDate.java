package websensorsdate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Classe da Main
 * @author Paulo Henrique Lima de Paula
 */
public class WebSensorsDate {

    /**
     * Método da main principal para execução do programa
     * Faz a leitura do arquivo com a entrada do dados e seta os valores em um objeto Dates
     * Após passa esse objeto a um ArrayList, finalizando assim a leitura
     * Em seguida é chamado o KMeans para a execução.
     * @author Paulo Henrique Lima de Paula
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\entrada.csv"));
        
        ArrayList posicaoDatas = new ArrayList();
        
        while(br.ready()){
            String[] coluna = br.readLine().split(",");
            Dates d = new Dates();
            ArrayList noticias = new ArrayList();
            d.setId(coluna[0]);
            int k=1;
            while(k < coluna.length){
                noticias.add(coluna[k]);
                k++;
            }
            d.setDatas(noticias);
            posicaoDatas.add(d);
        }
        br.close();
        KMeans kmeans = new KMeans(2,posicaoDatas);
        kmeans.segmentar(100);
    }
    
}
