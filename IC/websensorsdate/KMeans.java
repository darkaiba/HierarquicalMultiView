
package websensorsdate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.time.temporal.ChronoUnit;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe da KMeans, algoritmo de Inteligencia Artificial que executa o programa
 * @author Paulo Henrique Lima de Paula
 */

public class KMeans {
    int numeroCentroide;
    ArrayList datas = new ArrayList();
    ArrayList cluster = new ArrayList();
    ArrayList centroides;
    
    /** Método construtor do Kmeans
    * @author Paulo Henrique Lima de Paula
    * @param numeroCentroides int - numero de Centroides que você quer que possua
    * @param datas ArrayList - arrayList que contém os cluster com as datas
    */
    public KMeans(int numeroCentroides, ArrayList datas){
        this.numeroCentroide=numeroCentroides;
        this.datas=datas;
    }
    
    /** Método de inicialização do KMeans
    * Inicializa os cluster e os centroides e chama o metodo de atualização
    * @author Paulo Henrique Lima de Paula
    */
    public void inicializar() throws Exception{
        Random r = new Random();
        for(int i=0; i < datas.size(); i++){
            Dates d = (Dates) datas.get(i);
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
    public void atualizarCentroide() throws Exception{
        centroides = new ArrayList();
        for(int centro=0; centro < numeroCentroide; centro++){
            Dates d_centro = new Dates();
            double contador = 0.0;
            Timestamp timestamp = null;
            long soma = 0;
            for(int j=0; j < cluster.size(); j++){
                /* recupera o centroide da data j */
                int centroide = (int) cluster.get(j); 
                if(centroide == centro){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    Dates dates = (Dates) datas.get(j);
                    for(int k=0;k<dates.getDatas().size();k++){
                        String d = dates.getDatas().get(k).toString();
                        dateFormat.setLenient(false);
                        Date data = dateFormat.parse(d);
                        timestamp = new Timestamp(data.getTime());
                        soma += timestamp.getTime();
                        contador++;
                    }
                }
            }
            /* Data central do centróide "centro" */
            if(contador == 0.0) contador = 1.0;
            long time = (long) (soma/contador);
            
            ArrayList data_centro = new ArrayList();
            Timestamp stamp = new Timestamp((long) time);
            Date date = new Date(stamp.getTime());
            
            DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            
            data_centro.add(formatter.format(date.getTime()));            
            d_centro.setDatas(data_centro);            
            centroides.add(d_centro);
            
        }
    }
    
    /** Método de atribuir o grupo ao Centroide
    * atribuir cada data ao seu centroide mais proximo com base na data do centroide
    * @author Paulo Henrique Lima de Paula
    */
    public void atribuir(){
        for(int i=0; i < datas.size(); i++){
            Dates p = (Dates) datas.get(i);
            
            double minDist = Double.MAX_VALUE;
            int centroideSelecionado = -1;
            
            for(int j=0; j < centroides.size(); j++){
                Dates p_centro = (Dates) centroides.get(j);
                double dist = distanciaDatas(p,p_centro);
                if(dist < minDist){
                    centroideSelecionado = j;
                    minDist=dist;
                }
            }
          
            cluster.set(i, centroideSelecionado);
        }
    }
    
    /** Método para o calculo da distância entre as datas
    obtem o firstDate, e o secondDate, com base nos dois objetos apresentados e é calculado a sua distancia (em tempo)
    ao final é feita uma divisão da soma das datas com o numero de datas
    * @author Paulo Henrique Lima de Paula
    * @param d1 Dates - objeto d1 para comparar a distância com d2 em tempo de datas
    * @param d2 Dates - objeto d2 para comparar a distância com d1 em tempo de datas
    */
    public long distanciaDatas(Dates d1, Dates d2) {
        
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        long days = 0;
        long contador = 0; 
        for(int i=0;i<d1.getDatas().size();i++){
            final String firstInput = d1.getDatas().get(i).toString();
            for(int j=0;j< d2.getDatas().size();j++){
                final String secondInput = d2.getDatas().get(j).toString();
                final LocalDate firstDate = LocalDate.parse(firstInput, formatter);
                final LocalDate secondDate = LocalDate.parse(secondInput, formatter);
                days += ChronoUnit.DAYS.between(firstDate, secondDate);
                contador++;
            }
        }
        if(contador == 0) contador = 1;
        
        return Math.abs(days/contador);
    }
    
    /** Método de segmentação
    Segmenta os metodos anteriores para a execução do KMeans
    Ao final é salvo em um arquivo de saida as datas com seus respectivos centroides
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
        
        for(int i=0;i<datas.size();i++){
            bwriter.write("Para a noticia"+(i+1)+"\t foi escolhido o cluster "+cluster.get(i)+" \n");
        }
        bwriter.close();
    }
}

