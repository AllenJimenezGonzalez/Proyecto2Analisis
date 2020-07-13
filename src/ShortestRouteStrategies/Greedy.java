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
    
    public int minDistance = 0;
    public String greedyRoute = "";
    
    public void greedyShortRoute(Vertex origin, Vertex destiny){
        if(origin.state || origin == destiny){
            return; 
        }
        origin.state = true;
        Arc auxA = origin.sigA;
        int minD = Integer.MAX_VALUE;
        Arc minA = null;
        while (auxA != null) {
            if (auxA.weigth < minD && !auxA.destiny.state) {
                minA = auxA;
                minD = auxA.weigth;
            }
            auxA = auxA.sigA;
        }
        if (minA != null) {
            greedyRoute = greedyRoute + minA.destiny.id + "-->";
            minDistance = minDistance + minA.weigth;
            greedyShortRoute(minA.destiny, destiny);
        }
    }
}
