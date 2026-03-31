package ezui;

import java.awt.BorderLayout;
import javax.swing.*;

public abstract class Page extends JPanel {
    private final String name;
    protected final Grid root;

    public Page(String name, int rows, int cols, ColumnStyle... styles) {
        this.name = name;
        this.root = new Grid(rows, cols, styles);

        // 2. Wrap the Root Grid in a ScrollPane
        // This handles your "Shrinking Window" wrinkle by adding scrollbars
        JScrollPane scrollPane = new JScrollPane(root);
        scrollPane.setBorder(null); // Removes the ugly border around the scroll area

        // Speed up the scrolling for a better feel
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        // 3. Add the ScrollPane to the Frame
        super.add(scrollPane, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        super.add(root, BorderLayout.CENTER);
    }

    public String getName() {
        return name;
    }

    // Students override this to populate their page
    public abstract void onCreate();
}