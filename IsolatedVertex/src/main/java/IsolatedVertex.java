import com.mathsystem.graphapi.AbstractGraph;
import com.mathsystem.plugin.GraphProperty;

public class IsolatedVertex implements GraphProperty {
    @Override
    public boolean execute(AbstractGraph abstractGraph) {
        for(var vertex: abstractGraph.getVertices()){
            if(vertex.getEdgeList().isEmpty())
                return true;
        }
        return false;
    }
}
