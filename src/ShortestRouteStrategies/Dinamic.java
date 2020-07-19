/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShortestRouteStrategies;

import Graph.Arc;
import Graph.Vertex;

/**
 *
 * @author JansMorphy
 */
public class Dinamic {

    static int numVertex = 0;                       //This variable counts 
    public long memory = 0;                 //This variable counts the bits that consume the algorithm 
    public int instructions = 0;            //This variable counts the instructions executed by the methods

    public static void setNumVertex(int numVertex) {
        Dinamic.numVertex = numVertex;
    }

    public int minDistance(int dist[], Boolean sptSet[]) { // Verifies if the actual is shortest than the old one
        int min = Integer.MAX_VALUE, min_index = -1;
        instructions += 32;

        for (int v = 0; v < numVertex; v++) {
            instructions += numVertex;
            memory += (numVertex + 1) * 32;

            if (sptSet[v] == false && dist[v] <= min) {
                instructions++;

                min = dist[v];
                min_index = v;

                instructions += 2;
                memory += 64;
            }
        }
        return min_index;
    }

    public int[][] converterDijkstra(Vertex index, int size) { // This convert the graph class to graph matrix
        int graph[][] = new int[size][size];

        Vertex aux = index;
        while (aux != null) {
            Arc auxA = aux.sigA;
            while (auxA != null) {
                graph[aux.id - 1][auxA.destiny.id - 1] = auxA.weigth;
                auxA = auxA.sigA;
            }
            aux = aux.sigV;
        }
        return graph;
    }

    public void Dijkstra(int graph[][], int src) { 
        instructions++;
        memory += 32 * numVertex;
        int resultMatrix[] = new int[numVertex];

        instructions++;
        memory += 1 * numVertex;
        Boolean markMatrix[] = new Boolean[numVertex];

        instructions += 3;
        memory += 32;
        for (int i = 0; i < numVertex; i++) { // This method fill the resultMatrix and markMatrix with default values
            instructions++;
            resultMatrix[i] = Integer.MAX_VALUE;
            instructions++;
            markMatrix[i] = false;
        }

        instructions++;
        resultMatrix[src - 1] = 0;

        instructions += 3;
        memory += 32;
        for (int count = 0; count < numVertex - 1; count++) {

            instructions++;
            memory += 32;
            int ligther = minDistance(resultMatrix, markMatrix);

            instructions++;
            markMatrix[ligther] = true; // This mark the visited vertex.

            instructions += 3;
            memory += 32;
            for (int v = 0; v < numVertex; v++) {
                instructions += 5;
                if (!markMatrix[v] && graph[ligther][v] != 0
                        && resultMatrix[ligther] != Integer.MAX_VALUE && resultMatrix[ligther] + graph[ligther][v] < resultMatrix[v]) { 
                    resultMatrix[v] = resultMatrix[ligther] + graph[ligther][v];
                }
            }
        }
        printSolution(resultMatrix, numVertex, src);
    }

    public void printSolution(int dist[], int n, int src) { //This method prints the solution founded by dijkstra
        for (int i = 0; i < numVertex; i++) {
            String result = "";
            if (dist[i] == 0) {
                result = "no existe";
            } else {
                result = String.valueOf(dist[i]);
            }
            String string = String.format("Ruta desde %d hacia %d peso: %s", src, (i + 1), result);
            System.out.println(string);
        }
    }

}
