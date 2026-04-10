package ezui;

import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * A scrollable multi-selection list component backed by a {@link JList}.
 * <p>
 * Implements {@link Selectable} so you can react immediately whenever the
 * user changes their selection — no button required:
 * </p>
 * <pre>{@code
 * MultiListBox tagPicker = new MultiListBox(List.of("Easy", "Medium", "Hard"));
 * tagPicker.onSelectionChange(() -> {
 *     List<String> picked = tagPicker.getSelectedItems();
 *     summaryText.setText("Selected: " + picked.size() + " tags");
 * });
 * }</pre>
 */
public class MultiListBox extends Component implements Selectable {
    private final JList<String> list;
    private final DefaultListModel<String> model;
    private Runnable selectionChangeAction;

    /**
     * Creates an empty MultiListBox.
     */
    public MultiListBox() {
        model = new DefaultListModel<>();
        list  = new JList<>(model);
        list.setFont(FontPalette.getFont("Body"));
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(list);
        setLayout(new BorderLayout());
        super.add(scrollPane, BorderLayout.CENTER);

        // Guard with getValueIsAdjusting() so rapid drags don't spam the action —
        // the action fires only once the user has finished selecting.
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && selectionChangeAction != null)
                selectionChangeAction.run();
        });
    }

    /**
     * Creates a MultiListBox pre-populated with items from the given iterable.
     *
     * @param items the items to display; each is converted to a string via {@code toString()}
     */
    public MultiListBox(Iterable<?> items) {
        this();
        if (items == null) return;
        for (var item : items) addItem(String.valueOf(item));
    }

    // ─── Items ───────────────────────────────────────────────────────────────

    /**
     * Appends an item to the list.
     *
     * @param item the string to add
     */
    public void addItem(String item) {
        model.addElement(item);
    }

    /**
     * Removes all items from the list and clears any selection.
     */
    public void clear() {
        model.clear();
    }

    // ─── Selection ───────────────────────────────────────────────────────────

    /**
     * Returns all currently selected items, or an empty list if nothing is selected.
     *
     * @return an unmodifiable list of selected strings
     */
    public List<String> getSelectedItems() {
        return list.getSelectedValuesList();
    }

    /**
     * Runs {@code action} every time the user changes their selection.
     * Call {@link #getSelectedItems()} inside the lambda to read the new values.
     *
     * @param action the action to execute on selection change
     */
    @Override
    public void onSelectionChange(Runnable action) {
        this.selectionChangeAction = action;
    }
}
