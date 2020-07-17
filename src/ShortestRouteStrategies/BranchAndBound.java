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
public class BranchAndBound {
    
    public int counter =0;
    
    public String shortRoute = "";
    public int minRC = Integer.MAX_VALUE;
    
    public long memory = 0;
    public int instructions = 0;
    public void shortRouteRamification(Vertex aux, Vertex destiny, String route, int weight) {
        instructions += 2;
        if ((aux != null) && (!aux.state)) {
            instructions ++;
            if (aux == destiny) {
                instructions += 2;
                if ((shortRoute.equals("")) || (minRC > weight)) {
                    shortRoute = route + "-" + destiny.id;
                    minRC = weight; 
                    memory += 32;
                    instructions += 2;
                }
                return;
            }
            aux.state = true;
            instructions++;
            memory++;

            Arc auxA = aux.sigA;
            instructions++;
            memory += 64;
            
            instructions++;
            while (auxA != null) {
                instructions++;
                if (weight + auxA.weigth < minRC) {
                    instructions ++;
                    shortRouteRamification(auxA.destiny, destiny, route + "-" + aux.id, weight + auxA.weigth);
                }else{
                    counter++;
                    if(counter<=5){
                        System.out.println("Ruta podada desde: "+aux.id+" hacia "+auxA.destiny.id);
                    }
                }
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
