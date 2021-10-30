import com.mathsystem.graphapi.GraphFactory;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        var file = new File("C:\\IntelliJ\\test\\graph (7).txt");
        var undirectedGraph = GraphFactory.loadUndirectedGraphFromFile(file);
        var CompleteGraph = new CompleteGraph();

        System.out.print(CompleteGraph.execute(undirectedGraph));
    }
}