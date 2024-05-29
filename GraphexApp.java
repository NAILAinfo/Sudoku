import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GraphexApp {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public GraphexApp() {
        frame = new JFrame("GRAPHEX");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createFirstPanel();
        createGraphPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void createFirstPanel() {
        JPanel firstPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("GRAPHEX", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        JButton enterButton = new JButton("Enter");

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "graphPanel");
            }
        });

        firstPanel.add(titleLabel, BorderLayout.CENTER);
        firstPanel.add(enterButton, BorderLayout.SOUTH);
        mainPanel.add(firstPanel, "firstPanel");
    }

    private void createGraphPanel() {
        JPanel graphPanel = new JPanel(new BorderLayout());
        JPanel controlPanel = new JPanel();

        JButton addVertexButton = new JButton("Add Vertex");
        JButton addEdgeButton = new JButton("Add Edge");
        JButton addArcButton = new JButton("Add Arc");
        JButton addSourceButton = new JButton("Add Source Vertex");
        JButton addSinkButton = new JButton("Add Sink Vertex");
        JButton cancelButton = new JButton("Cancel");

        GraphComponent graphComponent = new GraphComponent();

        addVertexButton.addActionListener(e -> graphComponent.startAddingVertex());
        addEdgeButton.addActionListener(e -> graphComponent.startAddingEdge());
        addArcButton.addActionListener(e -> graphComponent.startAddingArc());
        addSourceButton.addActionListener(e -> graphComponent.startAddingSourceVertex());
        addSinkButton.addActionListener(e -> graphComponent.startAddingSinkVertex());
        cancelButton.addActionListener(e -> graphComponent.cancelGraph());

        controlPanel.add(addVertexButton);
        controlPanel.add(addEdgeButton);
        controlPanel.add(addArcButton);
        controlPanel.add(addSourceButton);
        controlPanel.add(addSinkButton);
        controlPanel.add(cancelButton);

        graphPanel.add(controlPanel, BorderLayout.NORTH);
        graphPanel.add(graphComponent, BorderLayout.CENTER);

        mainPanel.add(graphPanel, "graphPanel");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GraphexApp::new);
    }
}

class GraphComponent extends JComponent {
    private List<Point> vertices = new ArrayList<>();
    private List<Line> edges = new ArrayList<>();
    private List<Line> arcs = new ArrayList<>();
    private Point sourceVertex = null;
    private Point sinkVertex = null;

    private String currentAction = "";
    private Point tempPoint1 = null;
    private Point tempPoint2 = null;

    public GraphComponent() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePressed(e);
            }
        });
    }

    private void handleMousePressed(MouseEvent e) {
        if ("vertex".equals(currentAction)) {
            vertices.add(e.getPoint());
            repaint();
        } else if ("edge".equals(currentAction) || "arc".equals(currentAction)) {
            if (tempPoint1 == null) {
                tempPoint1 = e.getPoint();
            } else {
                tempPoint2 = e.getPoint();
                if ("edge".equals(currentAction)) {
                    edges.add(new Line(tempPoint1, tempPoint2));
                } else {
                    arcs.add(new Line(tempPoint1, tempPoint2));
                }
                tempPoint1 = null;
                tempPoint2 = null;
                repaint();
            }
        } else if ("sourceVertex".equals(currentAction)) {
            sourceVertex = e.getPoint();
            repaint();
        } else if ("sinkVertex".equals(currentAction)) {
            sinkVertex = e.getPoint();
            repaint();
        }
    }

    public void startAddingVertex() {
        currentAction = "vertex";
    }
    public void startAddingEdge() {
        currentAction = "edge";
    }
    public void startAddingArc() {
        currentAction = "arc";
    }
    public void startAddingSourceVertex() {
        currentAction = "sourceVertex";
    }
    public void startAddingSinkVertex() {
        currentAction = "sinkVertex";
    }

    public void cancelGraph() {
        vertices.clear();
        edges.clear();
        arcs.clear();
        sourceVertex = null;
        sinkVertex = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        for (Point vertex : vertices) {
            g.fillOval(vertex.x - 5, vertex.y - 5, 10, 10);
        }
        g.setColor(Color.BLUE);
        for (Line edge : edges) {
            g.drawLine(edge.start.x, edge.start.y, edge.end.x, edge.end.y);
        }
        g.setColor(Color.RED);
        for (Line arc : arcs) {
            g.drawLine(arc.start.x, arc.start.y, arc.end.x, arc.end.y);
            int midX = (arc.start.x + arc.end.x) / 2;
            int midY = (arc.start.y + arc.end.y) / 2;
            g.fillOval(midX - 2, midY - 2, 4, 4);
        }
        if (sourceVertex != null) {
            g.setColor(Color.GREEN);
            g.fillOval(sourceVertex.x - 7, sourceVertex.y - 7, 14, 14);
        }
        if (sinkVertex != null) {
            g.setColor(Color.ORANGE);
            g.fillOval(sinkVertex.x - 7, sinkVertex.y - 7, 14, 14);
        }
    }
    private static class Line {
        Point start, end;
        Line(Point start, Point end) {
            this.start = start;
            this.end = end;
        }
    }
}
