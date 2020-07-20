/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShortestRouteStrategies;

import Graph.Vertex;
import Graph.Arc;
/**
 *
 * @author allen
 */
public class Child {
    public Child next;
    public Vertex actualVertex;
    public Arc roadToNext;
    public Vertex nextVertex;
    public boolean state;
    
    public Child(Vertex actualVertex,Arc roadToNext, Vertex nextVertex){
        this.actualVertex = actualVertex;
        this.nextVertex = nextVertex;
        this.roadToNext = roadToNext;
    }
}
