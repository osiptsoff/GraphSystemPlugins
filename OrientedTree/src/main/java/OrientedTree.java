import com.mathsystem.graphapi.AbstractEdge;
import com.mathsystem.graphapi.AbstractGraph;
import com.mathsystem.graphapi.Vertex;
import com.mathsystem.plugin.GraphProperty;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrientedTree implements GraphProperty {
    @Override
    public boolean execute(AbstractGraph abstractGraph) {
        var vertexCount = abstractGraph.getVertexCount();
        var edgeCount = abstractGraph.getEdgeCount();

        ArrayList<AbstractEdge> edges = new ArrayList<>();
        LinkedList<Vertex> queue = new LinkedList<>();
        List<Vertex> vertices = abstractGraph.getVertices();

        for (int i = 0; i < abstractGraph.getVertexCount(); i++) {
            Vertex vertex = vertices.get(i);
            edges.addAll(vertex.getEdgeList());
        }
        if (vertexCount == 1) return true;
        if (vertexCount - edgeCount != 1) return false; // Любое дерево с n вершинами содержит n-1 ребро
        else
        {
            var root = 0; //Корень дерева только один
            int VerRoot = 0;
            int[] IncomingEdges = new int[vertexCount];
            for (int i = 0; i < edgeCount; i++) {
                IncomingEdges[edges.get(i).getW().getIndex()]  += 1;
            }
            for (int i = 0; i < vertexCount && root<=1; i++) {
                if (IncomingEdges[i] > 1) return false; //если
                else{
                    if (IncomingEdges[i] == 0) {
                        root += 1;
                        VerRoot = i;
                    }
                }
            }
            if (root == 1)
            {
                Vertex start = vertices.get(VerRoot);
                Vertex v;
                boolean[] WasVisited = new boolean[vertexCount];
                WasVisited[start.getIndex()] = true;
                queue.add(start); //Обход в ширину для проверки связности графа

                while (!queue.isEmpty()) {
                    var firstVertex = queue.pop();

                    for (var edge : firstVertex.getEdgeList()) {//Цикл по смежным вершинам
                        v = edge.other(firstVertex);
                        if (!WasVisited[v.getIndex()]) {
                            WasVisited[v.getIndex()] = true;
                            queue.add(v);
                        }
                    }
                }

                for (int i = 0; i < vertexCount; i++) {
                    if (!WasVisited[i])
                        return false; //Если граф несвязный значит граф не является ориентированным деревом
                }
                return true;
            }
            else return false;
        }
    }
}
