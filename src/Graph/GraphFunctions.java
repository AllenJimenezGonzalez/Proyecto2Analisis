package Graph;

/*
This class will contain all the necesary methods for creating, and printing 
the graphs in the iterative and recursive way.
 */
public class GraphFunctions {

    public static GraphFunctions instance = null;

    /*
    This is a singleton pattern, that will allow us to have a single instance 
    of these functions everywhere in the proyect. So the variables wont be lost.
     */
    public static GraphFunctions getInstance() {
        if (instance == null) {
            instance = new GraphFunctions();
        }
        return instance;
    }

    /*
    These are going to be the variables that will be used globaly in our 
    proyect.
     */
    public Vertex index;
    public Vertex last;

    /*
    This method will let us to insert the vertices into the list that starts 
    with index variable.
     */
    public boolean insertVertex(int id) {
        Vertex newVertex = new Vertex(id);
        if (index == null) {
            index = newVertex;
            last = newVertex;
            return true;
        } else {
            Vertex aux = index;
            while (aux.sigV != null) {
                aux = aux.sigV;
            }
            aux.sigV = newVertex;
            last = newVertex;
            return true;
        }

    }

    /*
    This method will let us to search an specific vertex in the graph. Also
    it is also helpful when trying to insert a new arc.
     */
    public Vertex searchVertex(int id) {
        if (index != null) {
            Vertex aux = index;
            while (aux != null) {
                if (aux.id == id) {
                    return aux;
                }
                aux = aux.sigV;
            }
        }
        return null;
    }

    /*
    This method will let us to insert the arcs into the sublist of the 
    specific origin vertex. 
     */
    public boolean insertArc(int id, int origin, int destiny) {

        if (searchVertex(origin) == null || searchVertex(destiny) == null || searchArc(searchVertex(origin), searchVertex(destiny)) != null) {
            return false;
        }
        Arc newArc = new Arc(randomNumber(id));
        newArc.destiny = searchVertex(destiny);
        newArc.sigA = searchVertex(origin).sigA;
        searchVertex(origin).sigA = newArc;
        return true;
    }

    /*
    This method will let us to search an specific arc in the graph.
     */
    public Arc searchArc(Vertex origin, Vertex destiny) {
        if (origin.sigA != null) {
            Arc aux = origin.sigA;
            while (aux != null) {
                if (aux.destiny == destiny) {
                    return aux;
                }
                aux = aux.sigA;
            }
        }
        return null;
    }

    //This method let us to get a random number in an specific range.
    public int randomNumber(int range) {
        double number = Math.random() * range + 1;
        int intNumber = (int) number;
        return intNumber;
    }

    public String shortRoute = "";
    public int minRC = 0;

    public void vorazShortRoute(Vertex aux, Vertex destiny, String route, int weight) {
        if ((aux != null) && (!aux.state)) {
            if (aux == destiny) {
                if ((shortRoute.equals("")) || (minRC > weight)) {
                    shortRoute = route + "-" + destiny.id;
                    minRC = weight;
                }
                return;
            }
            aux.state = true;
            Arc auxA = aux.sigA;
            while (auxA != null) {
                vorazShortRoute(auxA.destiny, destiny, route + "-" + aux.id, weight + auxA.weigth);
                auxA = auxA.sigA;
            }
            aux.state = false;
        }
    }

    public int[][] converter(Vertex index, int size) {
        int graph[][] = new int[size][size];

        Vertex aux = index;
        while (aux != null) {
            Arc auxA = aux.sigA;
            while (auxA != null) {
                    graph[aux.id-1][auxA.destiny.id-1] = auxA.weigth;  
                auxA = auxA.sigA;
            }
            aux = aux.sigV;
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] == 0 && i != j) {
                    graph[i][j] = 9999;
                }
            }
        }       
        return graph;
    }
    
    public void floydWarshall(int graph[][], int num) {
        int dist[][] = new int[num][num];
        int i, j, k;
        
        for (i = 0; i < num; i++) {
            for (j = 0; j < num; j++) {
                dist[i][j] = graph[i][j];
            }
        }
        for (k = 0; k < num; k++) {

            for (i = 0; i < num; i++) {

                for (j = 0; j < num; j++) {

                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        printSolution(dist, num);
    }
    
    public void printSolution(int dist[][], int vertexNumber){ 
        for (int i=0; i<vertexNumber; ++i) 
        { 
            for (int j=0; j<vertexNumber; ++j) 
            { 
                if (dist[i][j]==9999) 
                    System.out.print("NO   "); 
                else
                    System.out.print(dist[i][j]+"   "); 
            } 
            System.out.println(); 
        } 
    } 
}
