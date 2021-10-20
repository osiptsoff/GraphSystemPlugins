import java.util.ArrayList;
import java.util.LinkedList;

import com.mathsystem.graphapi.AbstractEdge;
import com.mathsystem.graphapi.AbstractGraph;
import com.mathsystem.graphapi.Vertex;
import com.mathsystem.plugin.GraphProperty;

public class ThreeVertexConnected implements GraphProperty {
    private void BuildPath(Vertex start, ArrayList<Vertex> parents, ArrayList<Vertex> children, ArrayList<AbstractEdge> excluded){
        var other = parents.get(children.indexOf(start));
        if(other != null) {
            for(var edge: other.getEdgeList())
                if(edge.other(other) == start){
                    excluded.add(edge);
                    break;
                }
            BuildPath(other, parents, children, excluded);
        }
    }

    private boolean PathExist(Vertex start, Vertex end, ArrayList<AbstractEdge> excluded)
    {
        var queue = new LinkedList<Vertex>();
        var viewed = new ArrayList<Vertex>();
        var viewedParents = new ArrayList<Vertex>();
        queue.addLast(start);
        viewed.add(start);
        viewedParents.add(null);

        while(!queue.isEmpty()) {
            var firstVertex = queue.pop();

            for(var edge: firstVertex.getEdgeList())
                if(!excluded.contains(edge))
                    if(edge.other(firstVertex) == end) {
                        excluded.add(edge);
                        BuildPath(firstVertex, viewedParents, viewed, excluded);
                        return true;
                    }
                    else{
                        if(!viewed.contains(edge.other(firstVertex))){
                            viewedParents.add(firstVertex);
                            viewed.add(edge.other(firstVertex));
                            queue.add(edge.other(firstVertex));
                        }
                    }
        }

        return false;
    }

    @Override
    public boolean execute(AbstractGraph abstractGraph) {
        var vertexList = abstractGraph.getVertices();
        var vertexCount = abstractGraph.getVertexCount();
        var excluded = new ArrayList<AbstractEdge>();

        for(int i = 0; i < vertexCount; i++)
            for(int j = i+1; j < vertexCount; j++) {
                excluded.clear();
                for(int k = 0; k < 3; k++)
                    if(!PathExist(vertexList.get(i), vertexList.get(j), excluded))
                        return false;
            }
        return true;
    }
}
