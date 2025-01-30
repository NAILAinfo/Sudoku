import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {

    private JTable table;

    public UI() {
        setTitle("Sudoku");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel(9, 9);

        // Remplissage avec des nombres aléatoires entre 1 et 9
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tableModel.setValueAt((int) (Math.random() * 9) + 1, i, j);
            }
        }

        table = new JTable(tableModel);
        table.setTableHeader(null);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        table.setRowHeight(40);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(true);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton valider = new JButton("Valider");
        valider.setFont(new Font("Arial", Font.BOLD, 25));
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkSolution()) {
                    JOptionPane.showMessageDialog(UI.this, "Bravo ! La solution est correcte !");
                } else {
                    JOptionPane.showMessageDialog(UI.this, "La solution est incorrecte. Essayez encore !");
                }
            }
        });

        add(panel, BorderLayout.CENTER);
        add(valider, BorderLayout.SOUTH);
        setVisible(true);
    }

    // Vérification de la solution complète
    private boolean checkSolution() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!checkCell(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Vérification d'une cellule spécifique
    private boolean checkCell(int row, int col) {
        Object v = table.getValueAt(row, col);

        if (!(v instanceof Integer)) {
            return false;
        }

        int valeur = (int) v;

        // Appel correct de la méthode `valide` d'une autre classe (ex: Sudoku)
        return Sudoku.valide(row, col, valeur);
    }

    public static void main(String[] args) {
        new UI();
    }
}
