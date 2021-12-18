import com.mathsystem.graphapi.GraphFactory;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        var file = new File("C:\\IntelliJ\\test\\12.txt");
        var directedGraph = GraphFactory.loadDirectedGraphFromFile(file);
        var OrientedTree = new OrientedTree();

        System.out.print(OrientedTree.execute(directedGraph));
    }
}
