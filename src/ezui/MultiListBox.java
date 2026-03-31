package ezui;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * A component that provides a list box with multi-selection capability.
 * The MultiListBox class allows for adding items, retrieving selected items,
 * and clearing all items. This component integrates a scroll pane to handle overflow
 * and ensures that the list is styled with the default "Body" font from the FontPalette.
 */
public class MultiListBox extends Component {
    final private JList<String> list;
    final private DefaultListModel<String> model;

    /**
     * Constructs a new MultiListBox component, which provides functionality
     * for displaying a list with multi-selection capability. This component
     * utilizes a {@code DefaultListModel} to manage the list data and is
     * integrated with a scroll pane to handle overflow.
     * <p>
     * The list is styled with the "Body" font from the {@code FontPalette},
     * and it allows selection of multiple intervals using the
     * {@code ListSelectionModel.MULTIPLE_INTERVAL_SELECTION}.
     * <p>
     * The component is constructed with a {@code BorderLayout}, where the list
     * wrapped in a scroll pane is added to the center.
     */
    public MultiListBox() {
        model = new DefaultListModel<>();
        list = new JList<>(model);
        list.setFont(FontPalette.getFont("Body"));

        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Wrap in a scroll pane to handle overflow
        JScrollPane scrollPane = new JScrollPane(list);

        setLayout(new BorderLayout());
        super.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Constructs a new MultiListBox component and initializes it with the provided
     * collection of items. Each item in the collection is added to the list displayed
     * in this component. This constructor provides convenience for initializing
     * the MultiListBox directly with a set of items.
     * <p>
     * If the provided collection is null, the MultiListBox is initialized as empty.
     *
     * @param items an Iterable collection of objects to be added to the MultiListBox;
     *              if null, the list will remain empty
     */
    public MultiListBox(Iterable<?> items) {
        this();
        if (items == null) return;
        for (var item : items) {
            addItem(String.valueOf(item));
        }
    }

    /**
     * Adds a new item to the list model associated with this component.
     * The specified item will be displayed in the list managed by this component.
     *
     * @param item the item to be added to the list; must not be null
     */
    public void addItem(String item) {
        model.addElement(item);
    }

    /**
     * Retrieves a list of currently selected items from the list displayed in this component.
     *
     * @return a list of strings representing the selected items, or an empty list if no items are selected
     */
    public List<String> getSelectedItems() {
        return list.getSelectedValuesList();
    }

    /**
     * Clears all items currently stored in the list model associated with this MultiListBox component.
     * This method removes all elements, leaving the list in an empty state.
     */
    public void clear() {
        model.clear();
    }
}
