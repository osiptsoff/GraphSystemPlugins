import com.mathsystem.graphapi.AbstractGraph;
import com.mathsystem.plugin.GraphProperty;

public class CompleteGraph implements GraphProperty {
    @Override
    public boolean execute(AbstractGraph abstractGraph) {
        return (abstractGraph.getVertexCount() * (abstractGraph.getVertexCount() - 1) / 2) == abstractGraph.getEdgeCount();
    }
}