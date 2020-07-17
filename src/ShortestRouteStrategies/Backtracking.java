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
public class Backtracking {
  
    public int counter;
    
    public long memory = 0;
    public int instructions = 0;
    
    
    public String shortRoute = "";
    public int minRC = 0;
    
    public void BacktrackingShortRoute(Vertex aux, Vertex destiny, String route, int weight) {
        instructions += 2;
        if ((aux != null) && (!aux.state)) {
            instructions ++;
            if (aux == destiny) {
                instructions += 2; 
                counter++;
                if(counter <=5){
                    System.out.println("Ruta " + route);
                }
                
                if ((shortRoute.equals("")) || (minRC > weight)) {
                    shortRoute = route + "-" + destiny.id;
                    memory += shortRoute.length() * 8;
                    minRC = weight;
                    memory += 32;                
                    instructions += 2;
                   
                }
                return;
            }
            aux.state = true;
            instructions ++;
            memory ++;
            
            Arc auxA = aux.sigA;
            instructions ++;
            memory += 64;
            instructions++;
            while (auxA != null) {
                instructions++;
                BacktrackingShortRoute(auxA.destiny, destiny, route + "-" + aux.id, weight + auxA.weigth);

                auxA = auxA.sigA;
                instructions ++;
                memory += 64;
                
            }
            aux.state = false;
            memory ++;
            instructions ++;
        }
    }
}
