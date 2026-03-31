package ezui;

import javax.swing.*;
import java.awt.*;


/**
 * The ComboBox class is a custom component that provides a basic wrapper for a
 * JComboBox component, offering additional styling and layout configuration.
 * It is designed for use as part of a graphical user interface, inheriting
 * from the Component class for consistent behavior across UI components.
 * <p>
 * This class uses a predefined font style for its items and offers functionality
 * to add items to the combo box, retrieve the selected item, and set the selected item.
 */
public class ComboBox extends Component {
    private final JComboBox<String> combo;

    /**
     * Constructs a new ComboBox instance with a predefined font style and layout configuration.
     * This constructor initializes an empty JComboBox with a default font style applied
     * from the FontPalette, associates it with a BorderLayout, and adds it as the primary
     * component of this instance. The ComboBox is designed to be used as a part of graphical
     * user interfaces, providing support for dropdown menu functionality with a consistent style.
     */
    public ComboBox() {
//        this.setBorder(BorderFactory.createEmptyBorder());
        combo = new JComboBox<>();
        combo.setFont(FontPalette.getFont("Body"));
//        combo.setOpaque(true);

        setLayout(new BorderLayout());
        super.add(combo, BorderLayout.CENTER);
    }

    /**
     * Constructs a ComboBox instance and populates it with items from the provided iterable.
     * Each item in the iterable is converted to a string using its `toString` method and
     * added to the ComboBox. If the iterable is null, no items are added.
     *
     * @param items an iterable collection of objects to populate the ComboBox; each object is
     *              converted to a string and added as an item
     */
    public ComboBox(Iterable<?> items) {
        this();
        if (items == null) return;
        for (var item : items) {
            addItem(String.valueOf(item));
        }
    }

    /**
     * Adds a new item to the ComboBox.
     *
     * @param item the string value of the item to be added to the ComboBox
     */
    public void addItem(String item) {
        combo.addItem(item);
    }

    /**
     * Retrieves the currently selected item from the ComboBox.
     *
     * @return the selected item as a string; if no item is selected, returns null
     */
    public String getSelectedItem() {
        return (String) combo.getSelectedItem();
    }
}