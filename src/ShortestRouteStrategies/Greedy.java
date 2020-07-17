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
public class Greedy {
    public int counter;
    
    public long memory = 0;
    public int instructions = 0;

    public int minDistance = 0;
    public String greedyRoute = "";
    
    public void greedyShortRoute(Vertex origin, Vertex destiny){
        instructions +=2;
        if(origin.state || origin == destiny){
            return; 
        }

        origin.state = true;
        Arc auxA = origin.sigA;
        int minD = Integer.MAX_VALUE;
        Arc minA = null;
        
        memory+=1;
        memory+=64;
        memory+=32;
        
        instructions +=3;
        
        instructions ++;
        while (auxA != null) {
            instructions ++;
            instructions ++;
            if (auxA.weigth < minD && !auxA.destiny.state) {
                minA = auxA;
                minD = auxA.weigth;
                instructions +=2;
            }
            instructions ++;
            auxA = auxA.sigA;
        }
        instructions ++;
        if (minA != null) {
            counter += minA.weigth;
            greedyRoute = greedyRoute + minA.destiny.id + "- Peso: "+ minA.weigth+"->";
            minDistance = minDistance + minA.weigth;
            instructions+=2;
            greedyShortRoute(minA.destiny, destiny);
        }
    }
    
}
