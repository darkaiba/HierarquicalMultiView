
package agrupamento;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe Agrupamentos
 * @author Paulo Henrique Lima de Paula
 */
public class Agrupamentos {

    /** Método construtor do Agrupamentos
    * @author Paulo Henrique Lima de Paula 
    */
    public Agrupamentos() {
    }
    
    /** Método set de verificação de similaridade
    * @author Paulo Henrique Lima de Paula
    * @param map recebe um hashmap para ser verificado
    *   metodo que verifica, se os cluster associados as noticias são iguais
    *   caso for verdadeiro, eles se associam em um mesmo grupo
    */
    public void verificarSimilaridade(HashMap map){
        /* Inicio do laço 
        * @param similaridade é setada como verdadeira
        * ou seja, as noticias são iniciadas como sendo similaridade verdadeiras
        * um ArrayList para criar os grupos
        */
        boolean similaridade = true;
        ArrayList<String> grupos = new ArrayList();
        
        for(int j = 0; j < map.size(); j++){
            /* Recebe um valor para ser comparado com o restante*/
            ArrayList id = (ArrayList) map.get(map.keySet().toArray()[j]);
            
            for(int i = j+1; i < map.size(); i++){
                ArrayList comparador = (ArrayList) map.get(map.keySet().toArray()[i]);
                /* Verificando similaridade de cada cluster*/
                if(!(id.get(0).equals(comparador.get(0)))) similaridade = false;
                if(!(id.get(1).equals(comparador.get(1)))) similaridade = false;
                if(!(id.get(2).equals(comparador.get(2)))) similaridade = false;
                
                /* Identificando as similaridades das noticias */
                if(similaridade){
                    
                    if(grupos.contains(map.keySet().toArray()[j])){
                        grupos.get(j-1).concat(";"+map.keySet().toArray()[i]);
                    }else{
                        String g = map.keySet().toArray()[j]+";"+map.keySet().toArray()[i];
                        grupos.add(g);
                    }
                }
                /* @param similaridade volta a ter o valor true */
                similaridade = true;
            }
        }
        
        /* Salvando os dados no arquivo */
        BufferedWriter bwriter;
        try {
                  
            bwriter = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/saida.csv", true));
            for(int i=0 ; i < grupos.size(); i++){
                bwriter.write(grupos.get(i)+"\n");
            }
            bwriter.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Agrupamentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}