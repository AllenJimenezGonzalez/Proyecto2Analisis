/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShortestRouteStrategies;

import Graph.Arc;
import Graph.GraphFunctions;
import Graph.Vertex;
import java.util.ArrayList;

/**
 *
 * @author allen
 */
public class Genetic {

    private static Genetic instance = null;

    public static Genetic getInstnace() {
        if (instance == null) {
            instance = new Genetic();
        }
        return instance;
    }

    Child originChild;
    Child testChild;
    ArrayList<ArrayList<Child>> routes = new ArrayList<>();

    String route = "";
    int counter = 0;
    int selector = 0; //Contador de la medida de seleccion que se hara
    int quantity = 0;

    public void selection(Vertex origin, Vertex destiny) {
        if (origin != null && !origin.state) {
            quantity++;
            if (origin == destiny && selector <= 9) {
                ArrayList<Child> selectionList = new ArrayList<>();
                Child auxC = originChild;
                while (auxC != null) {
                    selectionList.add(auxC);
                    auxC = auxC.next;
                }
                routes.add(selectionList);
                originChild = null;
                selector++;
                quantity = 0;
                return;
            }
            origin.state = true;
            Arc auxA = origin.sigA;
            while (auxA != null) {
                
                if(auxA.destiny == GraphFunctions.getInstance().last && quantity <=4){
                    auxA = auxA.sigA;
                }

               
                if (!auxA.destiny.state) {
                    traceRoute(origin, auxA);
                    selection(auxA.destiny, destiny);

                    if (origin != GraphFunctions.getInstance().index) {
                        origin.state = false;
                        return;
                    }
                }

                auxA = auxA.sigA;
            }
        }
    }

    public void traceRoute(Vertex aux, Arc arc) {
        Child child = new Child(aux, arc, arc.destiny);
        if (originChild == null) {
            originChild = child;
        } else {
            Child auxC = originChild;
            while (auxC != null) {
                if (auxC.next == null) {
                    auxC.next = child;
                    break;
                }
                auxC = auxC.next;
            }
        }
    }

    public void printRoutes() {
        int counterPrint = 1;
        Child lastChild = null;
        for (ArrayList<Child> list : routes) {
            System.out.println(String.format("\nRuta genetica numero %d ", counterPrint));
            for (Child child : list) {
                System.out.print(child.actualVertex.id + " -> ");
                lastChild = child;
            }
            System.out.print(lastChild.roadToNext.destiny.id);
            counterPrint++;
        }

        System.out.println("\n");
    }

}
