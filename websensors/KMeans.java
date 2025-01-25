package websensors;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe da KMeans, algoritmo de Inteligencia Artificial que executa o programa
 * @author Paulo Henrique Lima de Paula
 */

public class KMeans {
    int numeroCentroide;
    ArrayList coordenadas;
    ArrayList cluster = new ArrayList();
    ArrayList centroides;
    
    /** Método construtor do Kmeans
    * @author Paulo Henrique Lima de Paula
    * @param numeroCentroides int - numero de Centroides que você quer que possua
    * @param coordenadas ArrayList - arrayList que contém os cluster com as coordenadas
    */
    public KMeans(int numeroCentroides, ArrayList coordenadas){
        this.numeroCentroide=numeroCentroides;
        this.coordenadas=coordenadas;
    }
    
    /** Método de inicialização do KMeans
    * Inicializa os cluster e os centroides e chama o metodo de atualização
    * @author Paulo Henrique Lima de Paula
    */
    public void inicializar(){
        Random r = new Random();
        for(int i=0; i < coordenadas.size(); i++){
            Coordenada c = (Coordenada) coordenadas.get(i);
            /* escolhe um numeroCentroide de forma aleatoria */
            int segmento = r.nextInt(numeroCentroide); 
            cluster.add(segmento);
        }
        atualizarCentroide();
    }
    
    /** Método de atualizar o centroide
    * Atualizar os centroides de acordo com os grupos os quais a ele pertence
    * @author Paulo Henrique Lima de Paula
    */
    public void atualizarCentroide(){
        centroides = new ArrayList();
        for(int centro=0; centro < numeroCentroide; centro++){
            Coordenada c_centro = new Coordenada();
            double contador = 0.0;
            for(int j=0; j < cluster.size(); j++){
                /* recupera o centroide da coordenada j */
                int centroide = (int) cluster.get(j); 
                if(centroide == centro){
                    Coordenada c = (Coordenada) coordenadas.get(j);
                    c_centro.lat += c.lat;
                    c_centro.lon += c.lon;
                    contador++;
                }
            }
            /* Coordenada central do centróide "centro" */
            if(contador == 0.0) contador = 1.0;
            c_centro.lat /= contador;
            c_centro.lon /= contador;
            
            centroides.add(c_centro);
        }
    }
    
    /** Método de atribuir o grupo ao Centroide
    * atribuir cada coordenada ao seu centroide mais proximo com base na coordenada do centroide
    * @author Paulo Henrique Lima de Paula
    */
    public void atribuir(){
        for(int i=0; i < coordenadas.size(); i++){
            Coordenada p = (Coordenada) coordenadas.get(i);
            
            double minDist = Double.MAX_VALUE;
            int centroideSelecionado = -1;
            
            for(int j=0; j < centroides.size(); j++){
                Coordenada p_centro = (Coordenada) centroides.get(j);
                double dist = haversine(p,p_centro);
                if(dist < minDist){
                    centroideSelecionado = j;
                    minDist=dist;
                }
            }
            
            cluster.set(i, centroideSelecionado);
        }
    }
    
    /** Método para o calculo da distância entre as coordenadas
    * obtem a distanciaLat, distanciaLon, com base nos dois objetos apresentados e é calculado a sua distancia
    * ao final é feita uma conversão para quilômetros(Km)
    * @author Paulo Henrique Lima de Paula
    * @param c1 Coordenada - objeto c1 para comparar a distância com c2
    * @param c2 Coordenada - objeto c2 para comparar a distância com c1
    */
    public double haversine(Coordenada c1, Coordenada c2) {
        double distanciaLat = Math.toRadians(c2.lat - c1.lat);
        double distanciaLon = Math.toRadians(c2.lon - c1.lon);
        c1.lat = Math.toRadians(c1.lat);
        c2.lat = Math.toRadians(c2.lat);
        double a = Math.pow(Math.sin(distanciaLat / 2),2) + Math.pow(Math.sin(distanciaLon / 2),2) * Math.cos(c1.lat) * Math.cos(c2.lat);
        double c = 2 * Math.asin(Math.sqrt(a));
        /* Conversão para quilômetros(Km)*/
        double R = 6372.8; 
        return R * c;
    }
    
    /** Método de segmentação
    * Segmenta os metodos anteriores para a execução do KMeans
    * Ao final é salvo em um arquivode saida as coordenadas com seus respectivos centroides
    * @author Paulo Henrique Lima de Paula
    * @throws java.lang.Exception
    * @param numIteracoes int - numero de interações que o algoritmo irá executar para obter sua conversão final
    */
    public void segmentar(int numIteracoes) throws Exception{
        inicializar();
        for(int i=0; i < numIteracoes; i++){
            try{
                Thread.sleep(0000);
            }catch(Exception e){
                e.printStackTrace();
            }
            atribuir();
            atualizarCentroide();
            System.out.println("Iteracao "+i);
        }
        
        BufferedWriter bwriter = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"\\saida.csv"));
        
        for(int i=0;i<coordenadas.size();i++){
            bwriter.write("Para o id_"+(i+1)+"\t foi escolhido o cluster "+cluster.get(i)+" \n");
        }
        bwriter.close();
    }
}
