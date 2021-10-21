import com.mathsystem.graphapi.AbstractGraph;
import com.mathsystem.plugin.GraphCharacteristic;

public class VertexesDegreesSum implements GraphCharacteristic {
    @Override
    public Integer execute(AbstractGraph abstractGraph) {
        return abstractGraph.getEdgeCount() * 2;
    }
}
