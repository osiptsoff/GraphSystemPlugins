import com.mathsystem.graphapi.AbstractEdge;
import com.mathsystem.graphapi.AbstractGraph;
import com.mathsystem.graphapi.Vertex;
import com.mathsystem.plugin.GraphProperty;

import java.util.ArrayList;
import java.util.List;

public class LinearOrderingRelation implements GraphProperty {
    // Проверяем орграф на асимметричность:
    // Сравниваем i-тое ребро с j-тым: если вершина начала i-того есть конец j-того и вершина конца i-того есть начало j-того -
    // асимметричность графа не выполняется.
    // Иначе говоря,проверяем: если есть ребро из А в Б и из Б в А, то бинарное отношение не асимметрично
    public boolean isAsymmetric(ArrayList<AbstractEdge> edges, int edgesCount){
        var b = true;
        for(int i = 0; i < edgesCount; i++) {
            var start1 = edges.get(i).either();
            var end1 = edges.get(i).other(start1);
            for(int j = i; j < edgesCount; j++) {
                var start2 = edges.get(j).either();
                var end2 = edges.get(j).other(start2);
                if (start1 == end2 && end1 == start2){
                    i = j = edgesCount;
                    b = false;
                }
            }
        }
        return b;
    }

    // Проверяем граф на транзитивность
    // По определению: если есть ребро из А в Б, проверяем соседей Б: есть ребро из Б в В
    // Проверяем, есть ли ребро из А в В. Если нет - бинарное отношение не транзитивно
    public boolean isTransitive(ArrayList<AbstractEdge> edges, int edgesCount){
        var b = true;
        //var neigh = new ArrayList<Vertex>();
        int temp;

        for(int i = 0; i < edgesCount; i++) {
            var flag = false;
            var start1 = edges.get(i).either();
            var end1 = edges.get(i).other(start1);
            for(int j = i; j < edgesCount; j++) {
                var start2 = edges.get(j).either();
                if (start2 == end1){
                    var end2 = edges.get(j).other(start2);
                    temp = i;
                    while (temp < edgesCount && !flag){
                        if (edges.get(temp).either() == start1){
                            start1 = edges.get(temp).either();
                            if (edges.get(temp).other(start1) == end2){
                                flag = true;
                            }
                        }
                        temp++;
                    }
                    if (!flag) {
                        b = false;
                        i = j = edgesCount;
                    }
                }
            }
        }
        return b;
    }

    // Проверяем, является ли граф отношением линейного порядка ("все вершины соединены со всеми")
    // Сохраняем соседей вершины v (вершины, в которые можно попасть из v).
    // В цикле по всем вершинам w проверяем, сохранена ли она (есть ли ребро из v в w).
    // Чтобы не проверять одно ребро несколько раз, сохраняем просмотренные ребра
    // Если нет, проверить, есть ли ребро из w в v. Если его нет для некоторого w, граф не линейный

    public boolean isLinear(ArrayList<Vertex> vertices, int vertexCount, ArrayList<AbstractEdge> edges, int edgesCount){
        boolean b = true;
        var usedV = new ArrayList<Vertex>();
        var viewedE = new ArrayList<AbstractEdge>();
        for (int k = 0; k < vertexCount && b; k++) {
            var v = vertices.get(k);
            var edgesFromV = v.getEdgeList();
            for (AbstractEdge abstractEdge : edgesFromV) {
                var neigh = abstractEdge.other(v);
                usedV.add(neigh);
                if (!viewedE.contains(abstractEdge)) viewedE.add(abstractEdge);
            }

            for (int i = 0; i < vertexCount; i++) {
                var w = vertices.get(i);
                if (i != k) {
                    if (!usedV.contains(w)) {
                        var edgesFromW = w.getEdgeList();
                        var flag = false;
                        if (edgesFromW.size() != 0) {
                            for (AbstractEdge abstractEdge : edgesFromW) {
                                if (abstractEdge.other(w) == v) {
                                    flag = true;
                                    i = vertexCount;
                                }
                                if (!viewedE.contains(abstractEdge)){
                                    viewedE.add(abstractEdge);
                                }
                            }
                        } else {
                            for (int j = 0; j < edgesCount; j++){
                                var start = edges.get(j).either();
                                if (edges.get(j).other(start) == w) {
                                    flag = true;
                                    j = edgesCount;
                                }
                            }
                        }
                        b = flag;
                    }
                }

            }
            usedV.clear();
        }
        return b;
    }

    @Override
    // Граф является графом отношения линейного порядка R, если:
    // R - транзитивно, асимметрично и линейно (все вершины соединены между собой)
    public boolean execute(AbstractGraph abstractGraph) {
        List<Vertex> vertices = abstractGraph.getVertices();
        ArrayList<AbstractEdge> edges = new ArrayList<>();
        for (int i = 0; i < abstractGraph.getVertexCount(); i++) {
            Vertex vertex = vertices.get(i);
            edges.addAll(vertex.getEdgeList());
        }
        boolean result = false;
        var asym =  isAsymmetric(edges, edges.size());
        if (asym){
            var transit = isTransitive(edges, edges.size());
            if (transit){
                var linear = isLinear((ArrayList<Vertex>) vertices, abstractGraph.getVertexCount(), edges, edges.size());
                if (linear){
                    result = true;
                }
            }
        }
        return result;
    }
}

