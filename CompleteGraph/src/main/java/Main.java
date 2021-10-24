import com.mathsystem.graphapi.GraphFactory;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        var file = new File("C:\\IntelliJ\\IdeaProjects\\GraphSystemPlugins\\CompleteGraph\\src\\main\\java\\0.txt");
        var undirectedGraph = GraphFactory.loadUndirectedGraphFromFile(file);
        var CompleteGraph = new CompleteGraph();

        System.out.print(CompleteGraph.execute(undirectedGraph));
    }
}