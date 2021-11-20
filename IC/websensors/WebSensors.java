package websensors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Classe da Main
 * @author Paulo Henrique Lima de Paula
 */
public class WebSensors {
    /**
     * Método da main principal para execução do programa
     * Faz a leitura do arquivo com a entrada do dados e seta os valores em um objeto Coordenada
     * Após passa esse objeto a um ArrayList, finalizando assim a leitura
     * Em seguida é chamado o KMeans para a execução.
     * @author Paulo Henrique Lima de Paula
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\entrada.csv"));
        
        ArrayList posicaoCoordenada = new ArrayList();
        
        while(br.ready()){
            String[] linha = br.readLine().split(";");
            Coordenada c = new Coordenada();
            c.setId(linha[0]);
            c.setLat(Double.parseDouble(linha[1]));
            c.setLon(Double.parseDouble(linha[2]));
            posicaoCoordenada.add(c);
        }
        br.close();
        
        KMeans kmeans = new KMeans(5,posicaoCoordenada);
        kmeans.segmentar(100);
    }
    
}
