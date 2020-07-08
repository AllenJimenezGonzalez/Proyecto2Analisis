package Graph;

//This class will let us to create the path from one vertex to another.
public class Arc {
    public int id;
    public int weigth;
    public Vertex destiny;
    public Arc sigA;
    
    //This is the constructor class, that will let us to create an arc.
    public Arc(int weigth){
        this.weigth = weigth;
    }
}
