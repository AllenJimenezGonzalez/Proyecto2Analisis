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

    ArrayList<ArrayList<Child>> newGen = new ArrayList<>();

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

                if (auxA.destiny == GraphFunctions.getInstance().last && quantity <= 3) {
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

    public void mutate() {
        int crossIndex = 0;
        if (routes.get(1).size() % 2 == 0) {
            crossIndex = routes.get(1).get((routes.size() / 2) - 1).actualVertex.id;
        } else {
            crossIndex = routes.get(1).get((routes.size() / 2) - 2).actualVertex.id;
        }

        System.out.println("Punto de interseccion detectado en el vertice " + crossIndex);

        for (int x = 0; x < routes.size() - 1; x++) {
            newGen.add(generateChild(routes.get(x), routes.get(x + 1)));
        }
    }

    public ArrayList<Child> generateChild(ArrayList<Child> genAdn1, ArrayList<Child> genAdn2) {
        ArrayList<Child> newGenChild = new ArrayList<>();

        if (genAdn1.size() == genAdn2.size()) {
            if (genAdn1.get(genAdn1.size() / 2).actualVertex.id == genAdn2.get(genAdn2.size() / 2).actualVertex.id) {

                System.out.println("Se encontro un punto de cruce en el vetice: " + genAdn1.get(genAdn1.size() / 2).actualVertex.id);
                if (!verifyDiferentParents(genAdn1, genAdn2)) {
                    for (int x = 0; x < genAdn1.size(); x++) {
                        if (x <= (genAdn1.size() / 2)) {
                            newGenChild.add(genAdn1.get(x));
                        }

                    }
                    for (int y = 0; y < genAdn2.size(); y++) {
                        if (y > (genAdn2.size() / 2)) {
                            newGenChild.add(genAdn2.get(y));
                        }
                    }
                }
            } else {
                System.out.println("El punto de cruce no es exacto, abortando mutacion.");
            }
        } else {
            System.out.println("Un hijo defectuoso");
        }

        return newGenChild;
    }

    public boolean verifyDiferentParents(ArrayList<Child> genAdn1, ArrayList<Child> genAdn2) {

        for (int x = 0; x < genAdn1.size(); x++) {
            for (int y = 0; y < genAdn2.size(); y++) {
                if (x <= (genAdn1.size() / 2) && y > (genAdn2.size() / 2)) {
                    if (genAdn1.get(x).actualVertex.id == genAdn2.get(y).actualVertex.id) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

    public void printRoutes() {
        int counterPrint = 1;
        int totalWeigth = 0;
        Child lastChild = null;
        for (ArrayList<Child> list : routes) {
            System.out.println(String.format("\nRuta genetica numero %d ", counterPrint));
            for (Child child : list) {
                System.out.print(child.actualVertex.id + " -> ");
                totalWeigth += child.roadToNext.weigth;
                lastChild = child;
            }
            System.out.print(lastChild.roadToNext.destiny.id + " Peso total: " + totalWeigth);
            totalWeigth = 0;

            counterPrint++;
        }

        System.out.println("\n");
    }

    public void printNewGen() {
        int counterPrint = 1;
        int totalWeigth = 0;
        System.out.println("\n--------------------------Resultados de la combinacion genetica--------------------------\n");
        for (ArrayList<Child> list : newGen) {
            System.out.println(String.format("\n\n Combinacion genetica numero %d \n", counterPrint));
            for (Child child : list) {
                if (child.actualVertex == null) {
                    System.out.println("Problemas de visita al mismo vertice en el pariente 1 y pariente 2");
                } else {
                    System.out.print("Desde: " + child.actualVertex.id + " -> ");
                    totalWeigth += child.roadToNext.weigth;
                    System.out.println("hacia: " + child.roadToNext.destiny.id);
                }

            }
            System.out.print("\nPeso total: " + totalWeigth);
            totalWeigth = 0;
            counterPrint++;
        }

        System.out.println("\n");
    }

}
