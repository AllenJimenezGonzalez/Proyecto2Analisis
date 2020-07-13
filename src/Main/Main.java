package Main;

import Graph.Arc;
import Graph.GraphFunctions;
import Graph.Vertex;
import ShortestRouteStrategies.Backtracking;
import ShortestRouteStrategies.BranchAndBound;
import ShortestRouteStrategies.Dinamic;
import ShortestRouteStrategies.Greedy;
import java.io.IOException;

public class Main {
    static GraphFunctions graphFunctions = GraphFunctions.getInstance();
    
    static Greedy greedy = new Greedy();
    static Backtracking backtracking = new Backtracking();
    static BranchAndBound branchAndBound = new BranchAndBound();
    static Dinamic dinamic = new Dinamic();

   
    public static void main(String[] args) throws IOException {
        insertGraph(10);
        //measureGraph(20);
        //measureGraph(40);
        //measureGraph(80);
        //measureGraph(150);
        //measureGraph(300);
        //measureGraph(500);
        //measureGraph(800);
        //measureGraph(1000);
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
        
        int[][] matriz = dinamic.converterDijkstra(graphFunctions.index, num);

        for (int i = 0; i < matriz.length; ++i) {
            for (int j = 0; j < matriz.length; ++j) {
                System.out.print(matriz[i][j]+"    ");
            }
            System.out.println("");
        }

        System.out.println("\n\n----------------------------------------\n");
        //dinamic.Dijkstra(matriz, 2);
        graphFunctions.cleanMarks();
        dinamic.setV(num);
        System.out.println("Ruta con dinamico: ");
        dinamic.Dijkstra(matriz, 1);
        System.out.println("\n----------------------------------------\n");
        //prueba Voraz
        graphFunctions.cleanMarks();
        greedy.greedyShortRoute(graphFunctions.index, graphFunctions.last);
        System.out.println("Ruta con voraz: " + greedy.greedyRoute);
        System.out.println("\n----------------------------------------\n");
        //Prueba backtracking
        graphFunctions.cleanMarks();
        backtracking.BacktrackingShortRoute(graphFunctions.index, graphFunctions.last, "", 0);
        System.out.println("Ruta con backtracking: " + backtracking.shortRoute);
        System.out.println("\n----------------------------------------\n");
        
        //Prueba ramificacion
        graphFunctions.cleanMarks();
        branchAndBound.shortRouteRamification(graphFunctions.index, graphFunctions.last, "", 0);
        System.out.println("Ruta con ramificacion " + branchAndBound.shortRoute);
        
        System.out.println("\n----------------------------------------\n"
                + "Cantidad de cosas Backtracking: \nInstrucciones: "+backtracking.instructions+ "\nBits: "+backtracking.memory + " bits");
        System.out.println("\n----------------------------------------\n"
                + "Cantidad de cosas Greedy: \nInstrucciones: "+greedy.instructions+ "\nBits: "+greedy.memory + " bits");
        System.out.println("\n----------------------------------------\n"
                + "Cantidad de cosas Branch and bound: \nInstrucciones: "+branchAndBound.instructions+ "\nBits: "+branchAndBound.memory + " bits");
        System.out.println("\n----------------------------------------\n"
                + "Cantidad de cosas Dijkstra: \nInstrucciones: "+dinamic.instructions+ "\nBits: "+dinamic.memory + " bits");
        
    }

    //AMPLITUD, TODOS LOS VERTICES CON SUS ARCOS
    public void imprimirGrafo() {
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
        
    }
}
