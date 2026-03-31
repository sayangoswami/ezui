package ezui;

/**
 * This is a thin wrapper over column style alignment parameters such as 'grow', 'fill', etc.
 * This is used by the Grid class.
 * The idea is to provide a clean API for specifying column styles without having to remember the exact syntax of MigLayout constraints.
 * This allows developers to focus on layout logic rather than layout syntax.
 * <p>
 * Example usage:
 * <pre>
 * ColumnStyle style = ColumnStyle.GROW();
 * </pre>
 * <p>
 * This allows for more readable and maintainable layout code.
 * </p>
 */
public class ColumnStyle {
    private final String constraint;

    private ColumnStyle(String constraint) {
        this.constraint = constraint;
    }

    /**
     * Creates a new ColumnStyle instance representing a fixed-width column.
     * A fixed-width column does not grow or shrink and is defined
     * with an explicit fixed-size constraint.
     *
     * @return a new ColumnStyle instance configured for a fixed-width column
     */
    public static ColumnStyle FIXED() { return new ColumnStyle("[]"); }

    /**
     * Creates and returns a new {@code ColumnStyle} instance configured to allow
     * the column to grow and fill the available space in the container.
     * This style is particularly useful when designing layouts where
     * flexibility in column width is required to accommodate dynamic content.
     *
     * @return a new {@code ColumnStyle} instance with "grow, fill" constraints
     */
    public static ColumnStyle GROW()  { return new ColumnStyle("[grow]"); }

    /**
     * Creates and returns a new {@code ColumnStyle} instance configured
     * with a fixed width in pixels. The column will not grow or shrink,
     * and its width will be exactly the specified number of pixels.
     *
     * @param pixels the fixed width of the column in pixels
     * @return a new {@code ColumnStyle} instance configured for a fixed-width column with the specified pixel value
     */
    // The "Pro" option: Specify exact pixels
    public static ColumnStyle WIDTH(int pixels) {
        return new ColumnStyle("[" + pixels + "!]");
    }

    public String getConstraint() { return constraint; }
}
