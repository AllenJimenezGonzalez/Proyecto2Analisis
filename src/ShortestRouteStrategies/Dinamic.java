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
    static int V = 0;

    public int memory = 0;
    public int instructions = 0;

    
    public static void setV(int V) {
        Dinamic.V = V;
    }

    public int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;
        instructions += 32;

        for (int v = 0; v < V; v++) {
            instructions += V;
            memory += (V+1) * 32;
            
            if (sptSet[v] == false && dist[v] <= min) {
                instructions ++;
                
                min = dist[v];
                min_index = v;
                
                instructions += 2;
                memory += 64;
            }
        }
        return min_index;
    }

    public int[][] converterDijkstra(Vertex index, int size) {
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
        memory+=32*V;
        int dist[] = new int[V];

        instructions++;
        memory+=1*V;
        Boolean sptSet[] = new Boolean[V];

        instructions+=3;
        memory+=32;
        for (int i = 0; i < V; i++) {
            instructions++;
            dist[i] = Integer.MAX_VALUE;
            instructions++;
            sptSet[i] = false;
        }
        
        instructions++;
        dist[src - 1] = 0;

        instructions+=3;
        memory+=32;
        for (int count = 0; count < V - 1; count++) {

            instructions++;
            memory+=32;
            int u = minDistance(dist, sptSet);

            instructions++;
            sptSet[u] = true;

            instructions+=3;
            memory+=32;
            for (int v = 0; v < V; v++) {
                instructions+=5;
                if (!sptSet[v] && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }
        printSolution(dist, V);
    }

    public void printSolution(int dist[], int n) {
        System.out.println("Vertex   Distance from Source");
        instructions+=3;
        memory+=32;
        for (int i = 0; i < V; i++) {
            System.out.println(i + 1 + " tt " + dist[i]);
        }
    }
    

        
}
