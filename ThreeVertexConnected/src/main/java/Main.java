import com.mathsystem.graphapi.GraphFactory;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        var file = new File("C:\\Users\\Nikita\\Downloads\\graph.txt");
        var undirectedGraph = GraphFactory.loadUndirectedGraphFromFile(file);
        var threeVertexConnected = new ThreeVertexConnected();

        System.out.print(threeVertexConnected.execute(undirectedGraph));
    }
}
