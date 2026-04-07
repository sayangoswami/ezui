package ezui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


/**
 * The Window class is a custom extension of the JFrame class that provides a pre-configured
 * user interface window with a scrollable grid-based layout. It is designed to simplify
 * the creation of application windows by applying a consistent look and feel and integrating
 * a flexible grid layout for content arrangement.
 */
public class Window extends JFrame {

    private final JPanel sidebar;
    private final JPanel contentArea;
    private final CardLayout cardLayout;
    private final Grid sidebarGrid;
    private int pageCount = 0;
    private Map<String, Page> pages;

    /**
     * Constructs a new Window instance with the specified title, dimensions, and root grid layout.
     * The Window is preconfigured with the FlatLightLaf look-and-feel, includes a scrollable
     * view of the provided grid layout, and enforces minimum size constraints for usability.
     *
     * @param title the title of the window
     * @param width the width of the window in pixels
     * @param height the height of the window in pixels
     */
    public Window(String title, int width, int height) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.cardLayout = new CardLayout();
        this.contentArea = new JPanel(cardLayout);

        // Setup Sidebar: Fixed width, scrolls if there are many pages
        this.sidebarGrid = new Grid(20, 1, ColumnStyle.GROW());
        this.sidebar = new JPanel(new BorderLayout());
        this.sidebar.setPreferredSize(new Dimension(200, 0));
        this.sidebar.add(sidebarGrid, BorderLayout.NORTH);

        // Layout the main window
        super.setLayout(new BorderLayout());
        super.add(sidebar, BorderLayout.WEST);
        super.add(contentArea, BorderLayout.CENTER);

        // 4. Set sizing and constraints
        setSize(width, height);

        // Prevents the window from shrinking so small that things disappear
        setMinimumSize(new Dimension(600, 400));

        setLocationRelativeTo(null); // Centers the window on screen

        pages = new HashMap<>();
    }

    public void addPage(Page page) {
        page.setParentWindow(this);
        pages.put(page.getName(), page);

        // 1. Initialize the student's UI
        page.onCreate();

        // 2. Add to the CardLayout
        contentArea.add(page, page.getName());

        // 3. Create the navigation link
        Button navButton = new Button(page.getName());
        navButton.onClick(() -> cardLayout.show(contentArea, page.getName()));

        // 4. Place in sidebar
        sidebarGrid.add(navButton, pageCount++, 0, Alignment.FILL);
    }

    public void showPage(String name) {
        if (pages.containsKey(name)) {
            cardLayout.show(contentArea, name);
        }
    }
}