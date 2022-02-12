import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;


import java.awt.*;
import javax.swing.*;

public class SQFrame2 extends JFrame {
    private SparseGraph g;

    private void initGraph() {
        g = new SparseGraph();
        for (int i = 1; i < 10; i++) {
            //vertices
            g.addVertex(i);
            //aristas
            g.addEdge("Edge[1," + (i + 1) + "]", 1, i + 1);
            if (i > 1) {
                g.addEdge("Edge[" + i + "," + (i + 1) + "]", i, i + 1, EdgeType.DIRECTED);
            }
            DijkstraShortestPath ss=new DijkstraShortestPath(g);
            System.out.println( "caminos "+ss.getPath(i,2));
        }


        System.out.println("The graph g = " + g.toString());
    }
    public SQFrame2() {
        this.setTitle("Example");
        this.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        this.setBackground (Color.white); // Establecer el color de fondo de la ventana

        initGraph();

        // Crear estructura de diseño circular del visor (nodo V, E y tipo de enlace)
        VisualizationViewer<Integer, String> vv =
                new VisualizationViewer<Integer, String>(new CircleLayout(g));


        // establecer etiqueta de texto de vértice
        vv.getRenderContext()
                .setVertexLabelTransformer(new ToStringLabeller());

        // establecer color de vértice
        vv.getRenderContext()
                .setVertexFillPaintTransformer((p) -> {
                    if (p == 1)
                        return Color.green;
                    else
                        return Color.YELLOW;
                });

        // Establecer la etiqueta de texto del borde
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        // Establecer el estilo de línea del borde
        vv.getRenderContext().setEdgeStrokeTransformer(p->{
            return new BasicStroke(5f);
        });

        DefaultModalGraphMouse<Integer, String> gm = new DefaultModalGraphMouse<Integer, String>();
        gm.setMode(ModalGraphMouse.Mode.PICKING);
        vv.setGraphMouse(gm);
        // Coloque el objeto anterior en un contenedor Swing y muéstrelo
        getContentPane().add(vv);
        pack();
    }

    public static void main(String[] args) {
        SQFrame2 myframe = new SQFrame2();
        myframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        myframe.setVisible(true);
    }
}



