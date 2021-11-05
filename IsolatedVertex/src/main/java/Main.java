import com.mathsystem.graphapi.GraphFactory;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        var file = new File("D:\\Загрузки\\graph.txt");
        var undirectedGraph = GraphFactory.loadUndirectedGraphFromFile(file);
        var isolated = new IsolatedVertex();

        System.out.print(isolated.execute(undirectedGraph));
    }
}