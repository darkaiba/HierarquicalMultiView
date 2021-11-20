
package avaliador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
/**
 * Classe Avaliador
 * @author Paulo Henrique Lima de Paula
 */
public class Avaliador {
    /**
     * Método da main que faz a leitura de cada arquivo do diretorio
     * Após salva a o resultado dos acertos com o nome do arquivo e sua taxa
     * @author Paulo Henrique Lima de Paula
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
        File diretorio = new File(args[0]);
        File[] arquivos = diretorio.listFiles();
        
        for(int i=0;i<arquivos.length;i++){
            BufferedReader br = new BufferedReader(new FileReader(arquivos[i]));
            int totalErros = 0;
            int totalAresta = 0;
            double erroPercentual = 0.0;
            
            while(br.ready()){
                totalAresta++;
                String[] coluna = br.readLine().split(";");
                
                String[] id_1 = coluna[0].split("//.");
                String[] id_2 = coluna[1].split("//.");
                
                if(!(id_1.equals(id_2))){
                    totalErros++;
                }
            }
            
            erroPercentual = totalErros/totalAresta;
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]+"/saidaAvaliador.csv", true));
            bw.write(arquivos[i].getName()+";"+totalErros+";"+erroPercentual+";"+totalAresta);
            bw.close();
            br.close();
        }
    }
    
}
