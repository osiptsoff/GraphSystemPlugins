import com.mathsystem.graphapi.GraphFactory;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        var files = new File[3];
        var VDS = new VertexesDegreesSum();

        for(Integer i = 0; i < files.length; i++) {
            files[i] = new File("C:\\Users\\User\\Downloads\\graph(" + i.toString() + ").txt");
            var undirectedGraph = GraphFactory.loadUndirectedGraphFromFile(files[i]);
            System.out.print(VDS.execute(undirectedGraph).toString() + "\n");
        }
    }
}
