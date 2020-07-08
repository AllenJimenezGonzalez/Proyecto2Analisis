package Graph;

//This class will be all the nodes that are going to conform the graph.
public class Vertex {
    public int id;
    public Vertex sigV;
    public Arc sigA;
    public boolean state = false;
    
    //This is the constructor method for creating vertices.
    public Vertex(int number){
        this.id = number;
    }

}
