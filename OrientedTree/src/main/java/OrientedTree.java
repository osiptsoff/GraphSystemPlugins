import com.mathsystem.graphapi.AbstractEdge;
import com.mathsystem.graphapi.AbstractGraph;
import com.mathsystem.graphapi.Vertex;
import com.mathsystem.plugin.GraphProperty;

import java.util.ArrayList;
import java.util.List;

public class OrientedTree implements GraphProperty {
    @Override
    public boolean execute(AbstractGraph abstractGraph) {
        var vertexCount = abstractGraph.getVertexCount();
        var edgeCount = abstractGraph.getEdgeCount();

        List<Vertex> vertices = abstractGraph.getVertices();
        ArrayList<AbstractEdge> edges = new ArrayList<>();

        for (int i = 0; i < abstractGraph.getVertexCount(); i++) {
            Vertex vertex = vertices.get(i);
            edges.addAll(vertex.getEdgeList());
        }

        if (vertexCount - edgeCount != 1) return false; // Любое дерево с n вершинами содержит n-1 ребро
        else
        {
            var root = 0;
            int[] IncomingEdges = new int[vertexCount];
            for (int i = 0; i < edgeCount; i++) {
                IncomingEdges[edges.get(i).getW().getIndex()]  += 1;
            }
            for (int i = 0; i < edgeCount && root<=1; i++) {
                if (IncomingEdges[i] > 1) return false;
                else{
                    if (IncomingEdges[i] == 0) root += 1;
                }
            }
            return root <= 1;
        }
    }
}
