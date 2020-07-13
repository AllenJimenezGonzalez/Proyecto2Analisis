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

    public static void setV(int V) {
        Dinamic.V = V;
    }

    public int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
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
        int dist[] = new int[V];

        Boolean sptSet[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        dist[src - 1] = 0;

        for (int count = 0; count < V - 1; count++) {

            int u = minDistance(dist, sptSet);

            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
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
        for (int i = 0; i < V; i++) {
            System.out.println(i + 1 + " tt " + dist[i]);
        }
    }
    

        
}
