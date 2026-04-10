package ezui;

import javax.swing.*;
import java.awt.*;


/**
 * A scrollable single-selection list component backed by a {@link JList}.
 * <p>
 * Implements {@link Selectable} so you can react immediately when the user
 * clicks a different item — no button required:
 * </p>
 * <pre>{@code
 * ListBox courseList = new ListBox(List.of("CS1", "CS2", "CS3"));
 * courseList.onSelectionChange(() -> {
 *     String course = courseList.getSelectedItem();
 *     detailLabel.setText("Viewing: " + course);
 * });
 * }</pre>
 */
public class ListBox extends Component implements Selectable {
    private final JList<String> list;
    private final DefaultListModel<String> model;
    private Runnable selectionChangeAction;

    /**
     * Creates an empty ListBox.
     */
    public ListBox() {
        model = new DefaultListModel<>();
        list  = new JList<>(model);
        list.setFont(FontPalette.getFont("Body"));

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
     * Creates a ListBox pre-populated with items from the given iterable.
     *
     * @param items the items to display; each is converted to a string via {@code toString()}
     */
    public ListBox(Iterable<?> items) {
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
     * Returns the currently selected item, or {@code null} if nothing is selected.
     *
     * @return the selected string
     */
    public String getSelectedItem() {
        return list.getSelectedValue();
    }

    /**
     * Runs {@code action} every time the user selects a different item.
     * Call {@link #getSelectedItem()} inside the lambda to read the new value.
     *
     * @param action the action to execute on selection change
     */
    @Override
    public void onSelectionChange(Runnable action) {
        this.selectionChangeAction = action;
    }
}
