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
    public int counter;                 //This variable counts the weigth of the route
    
    public long memory = 0;             //This variable counts the bits that consume the algorithm 
    public int instructions = 0;        //This variable counts the instructions executed by the methods

    public int minDistance = 0;         //This variable save the total weigth of the route between an origin to destiny
    public String greedyRoute = "";     //This variable save the shortest route between an origin to destiny
    
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
            if (auxA.weigth < minD && !auxA.destiny.state) { // This verifies which is the destiny with the ligther weigth 
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
