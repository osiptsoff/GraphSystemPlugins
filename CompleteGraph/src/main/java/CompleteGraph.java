import com.mathsystem.graphapi.AbstractGraph;
import com.mathsystem.plugin.GraphProperty;

import java.util.HashSet;

public class CompleteGraph implements GraphProperty {
    @Override
    public boolean execute(AbstractGraph abstractGraph) {
        var vertexList = abstractGraph.getVertices();
        var vertexCount = abstractGraph.getVertexCount();
        for(int i = 0; i < vertexCount; i++){
            var tmpVertex = vertexList.get(i);
            var edgeList = tmpVertex.getEdgeList();
            var adjVertex = new HashSet<Integer>();
            for(int j = 0; j < edgeList.size(); j++){
                var finalVertex = edgeList.get(j).getV();
                if (edgeList.get(j).getV().getIndex() == tmpVertex.getIndex()) finalVertex = edgeList.get(j).getW();
                adjVertex.add(finalVertex.getIndex());
            }
            if (adjVertex.size() < vertexCount - 1) return false;
        }
        return true;
    }
}