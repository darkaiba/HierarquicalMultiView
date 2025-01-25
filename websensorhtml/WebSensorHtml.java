/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websensorhtml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author Paulo
 */
public class WebSensorHtml {

    public static void main(String[] args) throws Exception {
        
        File diretorio = new File(System.getProperty("user.dir")+"/arquivo");
        File arquivos[] = diretorio.listFiles();
        
        for(int j = 0; j < arquivos.length; j++){
            BufferedReader br = new BufferedReader(new FileReader(arquivos[j]));

            ArrayList node = new ArrayList();
            ArrayList classe = new ArrayList();

            ArrayList eventos = new ArrayList();

            ArrayList no = new ArrayList();

            while(br.ready()){
                String[] noticia = br.readLine().split(";");

                String[] not1 = noticia[0].split("\\.");
                String[] not2 = noticia[1].split("\\.");

                if(!(classe.contains(not1[0]))){
                    classe.add(not1[0]);
                }

                if(!(classe.contains(not2[0]))){
                    classe.add(not2[0]);
                }



                if(!(node.contains(not1[1]))){
                    Node nodes = new Node();
                    nodes.setId(node.size());
                    nodes.setName(not1[1]);
                    nodes.setGroup(classe.indexOf(not1[0]));
                    no.add(nodes);
                    node.add(not1[1]);

                    if(!(node.contains(not1[0]))){
                        Node newNode = new Node();
                        newNode.setId(node.size());
                        newNode.setName(not1[0]);
                        newNode.setGroup(classe.indexOf(not1[0]));
                        no.add(newNode);
                        node.add(not1[0]);
                    }

                    Evento edge = new Evento();
                    edge.setEvento1(node.indexOf(not1[1]));
                    edge.setEvento2(node.indexOf(not1[0]));
                    eventos.add(edge);
                }

                if(!(node.contains(not2[1]))){
                    Node nodes = new Node();
                    nodes.setId(node.size());
                    nodes.setName(not2[1]);
                    nodes.setGroup(classe.indexOf(not2[0]));
                    no.add(nodes);
                    node.add(not2[1]);

                    if(!(node.contains(not2[0]))){
                        Node newNode = new Node();
                        newNode.setId(node.size());
                        newNode.setName(not2[0]);
                        newNode.setGroup(classe.indexOf(not2[0]));
                        no.add(newNode);
                        node.add(not2[0]);
                    }

                    Evento edge = new Evento();
                    edge.setEvento1(node.indexOf(not2[1]));
                    edge.setEvento2(node.indexOf(not2[0]));
                    eventos.add(edge);
                }

                Evento edge = new Evento();
                edge.setEvento1(node.indexOf(not1[1]));
                edge.setEvento2(node.indexOf(not2[1]));            
                eventos.add(edge); 
            }

            BufferedWriter bwriter = new BufferedWriter(new FileWriter(arquivos[j].getName()+"_saida.html"));
                bwriter.write(
                        "<!doctype html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title></title>\n" +
                        "\n" +
                        "\n" +
                        "    <style type=\"text/css\">\n" +
                        "body{\n" +
                        "margin: 0;\n" +
                        "padding: 0\n" +
                        "}\n" +
                        "        #mynetwork {\n" +
                        "            width: 100vw;\n" +
                        "            height: 100vh;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "\n" +
                        "    <script type=\"text/javascript\" src=\"vis.js\"></script>\n" +
                        "    <link href=\"vis-network.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
                        "\n" +
                        "    <script type=\"text/javascript\">\n" +
                        "        function draw() {\n" +
                        "            // create some nodes\n" +
                        "            var nodes = ["
                );
                for(int i=0;i<no.size();i++){
                    bwriter.write(no.get(i)+",");
                }
                bwriter.write(
                        "            ];\n" +
                        "\n" +
                        "            // create some edges\n" +
                        "            var edges = ["
                );
                for(int i=0;i<eventos.size();i++){
                    bwriter.write(eventos.get(i)+",");
                }
                bwriter.write(
                        "];\n" +
                        "\n" +
                        "var network;\n" +
                        "\n" +
                        "\n" +
                        "   // create a network\n" +
                        "   var container = document.getElementById('mynetwork');\n" +
                        "   var data = {\n" +
                        "      nodes: nodes,\n" +
                        "      edges: edges\n" +
                        "   };\n" +
                        "   var options = {\n" +
                        "      nodes: {\n" +
                        "        shape: 'dot',\n" +
                        "        scaling: {\n" +
                        "          min: 10,\n" +
                        "          max: 30\n" +
                        "        },\n" +
                        "        font: {\n" +
                        "          size: 12,\n" +
                        "          face: 'Tahoma'\n" +
                        "        }\n" +
                        "      },\n" +
                        "      edges: {\n" +
                        "        width: 0,\n" +
                        "	    color: {\n" +
                        "	      color:'#ffffff',\n" +
                        "	      highlight:'#ffffff',\n" +
                        "	      hover: '#ffffff',	\n" +
                        "	      opacity:0\n" +
                        "	    },\n" +
                        "        smooth: {\n" +
                        "          type: 'continuous'\n" +
                        "        }\n" +
                        "      },\n" +
                        "      physics: {\n" +
                        "        stabilization: false,\n" +
                        "        barnesHut: {\n" +
                        "          gravitationalConstant: -80000,\n" +
                        "          springConstant: 0.001,\n" +
                        "          springLength: 200\n" +
                        "        }\n" +
                        "      },\n" +
                        "      interaction: {\n" +
                        "        tooltipDelay: 200,\n" +
                        "        hideEdgesOnDrag: true\n" +
                        "      }\n" +
                        "    };\n" +
                        "\n" +
                        "    // Note: data is coming from ./datasources/WorldCup2014.js\n" +
                        "    network = new vis.Network(container, data, options);\n" +
                        " \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "        }\n" +
                        "    </script>\n" +
                        "    \n" +
                        "</head>\n" +
                        "\n" +
                        "<body onload=\"draw()\">\n" +
                        "\n" +
                        "\n" +
                        "<div id=\"wrapper\">\n" +
                        "    <div id=\"mynetwork\"></div>\n" +
                        " </div>\n" +
                        "</body>\n" +
                        "</html>"
                );
            bwriter.close();
        }
    }
}
