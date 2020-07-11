package Main;

import Graph.Arc;
import Graph.GraphFunctions;
import Graph.Vertex;
import java.io.IOException;

public class Main {
    static GraphFunctions graphFunctions = GraphFunctions.getInstance();

    public static void main(String[] args) throws IOException {
        insertGraph(10);
        //measureGraph(200);
        //measureGraph(400);
        //measureGraph(800);
        //measureGraph(1600);
        //measureGraph(3200);
    }

    /*
    This will be the the class that will test both methods of the graph class.
    It recives by parameter an specific lenght of data to execute the iterative
    and the recursive method. Also at the end it will send the parameters to a 
    functions that show us the results.
    */
    public static void insertGraph(int num) {
        System.out.println("-------------------------- Mediciones de grafos con tamaño = " + num + " --------------------------");

        for (int i = 1; i <= num; i++) {
            graphFunctions.insertVertex(i);  
        }
        
        Vertex origin = graphFunctions.index;
        while (origin != null) {
            if (origin.id != graphFunctions.last.id) {
                Vertex destiny = graphFunctions.index;
                while (destiny != null) {
                    if (origin.id != destiny.id && destiny.id != graphFunctions.index.id) {
                        graphFunctions.insertArc(99, origin.id, destiny.id);
                    }
                    destiny = destiny.sigV;
                }
            }
            origin = origin.sigV;
        }
        
        graphFunctions.vorazShortRoute(graphFunctions.index, graphFunctions.last, graphFunctions.shortRoute, graphFunctions.minRC);
        System.out.println("\nLa Ruta corta usando el algoritmo Voraz es de > ");
        System.out.println(graphFunctions.shortRoute);
        System.out.println("=====================\n");
        
        //AMPLITUD, TODOS LOS VERTICES CON SUS ARCOS
        
        
        Vertex aux = graphFunctions.index;
        while (aux != null) {
            System.out.println("Nombre del vertice ==================> " + aux.id);
            Arc auxArco = aux.sigA;
            while (auxArco != null) {
                System.out.println("Arco hacia > " + auxArco.destiny.id + " Y Peso > " + auxArco.weigth);
                auxArco = auxArco.sigA;
            }
            aux = aux.sigV;
        }
        
        int[][] matrizmieo = graphFunctions.converterDijkstra(graphFunctions.index, num);
        System.out.println("\n\n===========");
        for (int i = 0; i < matrizmieo.length; ++i) {
            for (int j = 0; j < matrizmieo.length; ++j) {
                System.out.print(matrizmieo[i][j]+"    ");
            }
            System.out.println("");
        }
        System.out.println("===========\n\n");
        
        graphFunctions.dijkstra(matrizmieo, 2);
    }
}
