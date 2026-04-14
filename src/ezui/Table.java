package ezui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * A data-driven Table component for ezui.
 * Automatically handles headers and rows from objects implementing ICsv.
 */
public class Table<T extends ITable> extends Component {
    private final JTable table;
    private final DefaultTableModel model;
    private List<T> dataSource;

    public Table(List<T> dataSource) {
        this.dataSource = dataSource;

        // 1. Initialize an empty model
        this.model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Keep it read-only for safety
            }
        };

        // 2. Setup the JTable with a custom "No Data" overlay
        this.table = new JTable(model) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Draw overlay if no rows exist
                if (getRowCount() == 0) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(Color.GRAY);
                    g2.setFont(new Font("SansSerif", Font.ITALIC, 16));

                    String msg = "No Data Available";
                    FontMetrics fm = g2.getFontMetrics();
                    int x = (getWidth() - fm.stringWidth(msg)) / 2;
                    int y = getHeight() / 2;
                    g2.drawString(msg, x, y);
                }
            }
        };

        // 3. Styling
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);

        // 4. Wrap in ScrollPane (Essential for headers to show up later)
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        setLayout(new BorderLayout());
        super.add(scrollPane, BorderLayout.CENTER);

        // Initial sync
        refresh();
    }

    /**
     * Synchronizes the UI with the current state of the ArrayList.
     */
    public void refresh() {
        model.setRowCount(0);

        if (dataSource != null && !dataSource.isEmpty()) {
            // Identify headers from the first object in the list
            String[] headers = dataSource.get(0).getHeaders();
            model.setColumnIdentifiers(headers);

            // Add all rows
            for (T item : dataSource) {
                model.addRow(item.getValues());
            }
        } else {
            // Reset columns if list is empty
            model.setColumnIdentifiers(new Object[0]);
        }

        table.revalidate();
        table.repaint();
    }

    /**
     * Helper to get the actual object associated with the selected row.
     */
    public T getSelectedObject() {
        int row = table.getSelectedRow();
        if (row != -1 && row < dataSource.size()) {
            // Note: If sorting is enabled, use table.convertRowIndexToModel(row)
            return dataSource.get(row);
        }
        return null;
    }

    /**
     * Utility to clear the selection.
     */
    public void clearSelection() {
        table.clearSelection();
    }
}