package ezui;

import net.miginfocom.swing.MigLayout;


/**
 * The Grid class is a user interface component that provides a flexible grid-based layout
 * for arranging child components in a two-dimensional structure. It is designed to offer
 * a clean and simple API for developers to define grid layouts with customizable
 * column styles based on the underlying MigLayout library.
 */
public class Grid extends Component {
    private final int maxRows;
    private final int maxCols;
    private final MigLayout layout;

    /**
     * Constructs a Grid where every column uses {@link ColumnStyle#GROW()}.
     * Equivalent to calling {@code new Grid(rows, cols, ColumnStyle.GROW())}.
     *
     * @param rows the number of rows in the grid
     * @param cols the number of columns in the grid
     */
    public Grid(int rows, int cols) {
        this(rows, cols, ColumnStyle.GROW());
    }

    /**
     * Constructs a Grid object with the specified number of rows, columns, and column styles.
     * This constructor allows customization of the grid layout by accepting an array of
     * {@code ColumnStyle} objects to define the behavior of each column.
     *
     * @param rows   the number of rows in the grid
     * @param cols   the number of columns in the grid
     * @param styles an array of {@code ColumnStyle} objects specifying the constraints for each column;
     *               if no styles are provided, the column constraints will default to empty
     *               If the number of styles provided is less than the number of columns,
     *               the last style will be used for the remaining columns.
     */
    public Grid(int rows, int cols, ColumnStyle... styles) {
        this.maxRows = rows;
        this.maxCols = cols;
        StringBuilder colConstraints = new StringBuilder();
        for (int i = 0; i < cols; i++) {
            // Use the provided style, or the last one given, or default to FIXED
            String constraint;
            if (styles.length > 0) {
                int styleIndex = Math.min(i, styles.length - 1);
                constraint = styles[styleIndex].getConstraint();
            } else {
                constraint = ColumnStyle.GROW().getConstraint(); // Default: all columns grow
            }
            colConstraints.append(constraint);
        }
        this.layout = new MigLayout("fillx, aligny top, insets 10, gap 10 10", colConstraints.toString());
        setLayout(this.layout);
    }

    /**
     * Adds a component to the grid at the specified row and column with layout constraints
     * ensuring the component fills the width with its column and aligns to the top of its row.
     *
     * @param component the component to be added to the grid
     * @param row the row index where the container should be placed
     * @param col the column index where the container should be placed
     */
    public void add(Component component, int row, int col) {
        // by default, we want to fill the width with the column and align to the top of the row
        add(component, row, col, Alignment.FILL);
    }

    /**
     * Adds a component to the grid at a specified row and column, aligning it according to the given alignment parameter.
     *
     * @param component the component to be added to the grid
     * @param row the row index where the component should be placed
     * @param col the column index where the component should be placed
     * @param align the alignment specifying how the component should be positioned horizontally within its cell
     *              (e.g., left, center, right, or fill)
     *              Alignment.FILL will fill the width with the cell, while Alignment.LEFT will align the component to the left.
     *              Alignment.CENTER will center the component within its cell.
     *              Alignment.RIGHT will align the component to the right.
     * @throws IllegalArgumentException if the specified row or column index is out of bounds for the grid size
     */
    public void add(Component component, int row, int col, Alignment align) {
        // Check if the row and column indices are within the bounds of the grid
        if (row >= maxRows || col >= maxCols) {
            throw new IllegalArgumentException(
                    String.format("Grid Error: Attempted to add component at [%d, %d], but Grid size is only [%d, %d]",
                            row, col, maxRows, maxCols)
            );
        }

        // We build the constraint string based on the Alignment enum
        String alignConstraint = switch (align) {
            case LEFT   -> "alignx left";
            case CENTER -> "alignx center";
            case RIGHT  -> "alignx right";
            case FILL   -> "growx";
        };

        // 'cell col row' places it, 'aligny top' keeps our vertical fix,
        // and 'insets 10' inserts padding around the component
        String finalConstraints = String.format("cell %d %d, %s, aligny top",
                col, row, alignConstraint);

        super.add(component, finalConstraints);
        this.refresh();
    }

    /**
     * Forces the MigLayout to recalculate the grid and redraw.
     */
    public void refresh() {
        // 1. Tell MigLayout to re-calculate component constraints
        this.revalidate();

        // 2. Tell the underlying panel to physically repaint
        this.repaint();

        // 3. Optional: If the grid is inside a ScrollPane or another container,
        // sometimes we need to trigger the parent to realize the child has grown.
        if (getParent() != null) {
            getParent().revalidate();
        }
    }

    public void removeAt(int row, int col) {
        for (java.awt.Component comp : getComponents()) {
            Object constraints = layout.getComponentConstraints(comp);
            if (constraints != null && constraints.toString().contains("cell " + col + " " + row)) {
                super.remove(comp);
                break;
            }
        }
        refresh();
    }
}