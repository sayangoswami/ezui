package ezui;

import javax.swing.*;
import java.awt.*;


/**
 * The ListBox class is a graphical user interface (GUI) component that extends the Component class.
 * It provides functionality to display a scrollable list of items and allow the user to interact
 * with the list, such as selecting items or clearing the list content.
 * <p>
 * The ListBox is built using a JList and a DefaultListModel for managing the list's data. It is
 * wrapped in a JScrollPane to handle scrolling for long lists.
 */
public class ListBox extends Component {
    final private JList<String> list;
    final private DefaultListModel<String> model;

    /**
     * Constructs a new ListBox object.
     * The ListBox component is initialized with a scrollable list that is capable of displaying
     * items in a vertical format. It utilizes a DefaultListModel for managing the data and a JList
     * as the visual component for displaying the items.
     * <p>
     * The font for the items in the list is set using the FontPalette class, ensuring consistent
     * styling across the application. The list is wrapped in a JScrollPane, allowing the user to
     * scroll through the contents if the number of items exceeds the visible area.
     * <p>
     * This constructor also sets the layout of the ListBox component to BorderLayout and adds
     * the scrollable list in the center of the layout.
     */
    public ListBox() {
        model = new DefaultListModel<>();
        list = new JList<>(model);
        list.setFont(FontPalette.getFont("Body"));

        // Wrap in a scroll pane to handle overflow
        JScrollPane scrollPane = new JScrollPane(list);

        setLayout(new BorderLayout());
        super.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Constructs a new ListBox object and initializes it with the specified items.
     * The ListBox component is created as a scrollable list that allows a set of items
     * to be displayed vertically. Each item from the provided {@code items} iterable is
     * converted to a string and added to the list.
     *
     * @param items an {@code Iterable} containing the items to populate the ListBox;
     *              if {@code null}, the ListBox will be initialized as empty.
     */
    public ListBox(Iterable<?> items) {
        this();
        if (items == null) return;
        for (var item : items) {
            addItem(String.valueOf(item));
        }
    }

    /**
     * Adds a new item to the ListBox component.
     * This method appends the specified item to the internal list model,
     * causing the item to be displayed in the ListBox.
     *
     * @param item the item to add to the ListBox; cannot be {@code null}.
     */
    public void addItem(String item) {
        model.addElement(item);
    }

    /**
     * Retrieves the currently selected item in the ListBox.
     * If no item is selected, this method returns null.
     *
     * @return the selected item's value as a String, or null if no item is selected
     */
    public String getSelectedItem() {
        return list.getSelectedValue();
    }

    /**
     * Clears all items from the ListBox.
     * This method removes all elements from the internal data model
     * associated with the ListBox, leaving it empty.
     * The visual representation of the ListBox is updated accordingly.
     */
    public void clear() {
        model.clear();
    }
}