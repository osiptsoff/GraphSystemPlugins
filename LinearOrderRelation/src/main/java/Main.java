import com.mathsystem.graphapi.GraphFactory;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        var file = new File("C:\\Users\\admin\\Downloads\\graph34.txt");
        var directedGraph = GraphFactory.loadDirectedGraphFromFile(file);
        var linearOrderingRelation = new LinearOrderRelation();
        System.out.print(linearOrderingRelation.execute(directedGraph));
    }
}