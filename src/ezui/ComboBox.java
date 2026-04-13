package ezui;

import java.awt.*;
import javax.swing.*;


/**
 * A single-selection dropdown component backed by a {@link JComboBox}.
 * <p>
 * Implements {@link Selectable} so you can react to the user picking a new item
 * without needing a button:
 * </p>
 * <pre>{@code
 * ComboBox filter = new ComboBox(List.of("All", "A", "B", "C"));
 * filter.onSelectionChange(() -> {
 *     String grade = filter.getSelectedItem();
 *     // rebuild your filtered list, then call table.refresh()
 * });
 * }</pre>
 */
public class ComboBox extends Component implements Selectable {
    private final JComboBox<String> combo;
    private Runnable selectionChangeAction;

    /**
     * Creates an empty ComboBox.
     */
    public ComboBox() {
        combo = new JComboBox<>();
        combo.setFont(FontPalette.getFont("Body"));

        setLayout(new BorderLayout());
        super.add(combo, BorderLayout.CENTER);

        // ActionListener fires once per committed selection — no adjusting-guard needed
        combo.addActionListener(e -> {
            if (selectionChangeAction != null) selectionChangeAction.run();
        });
    }

    /**
     * Creates a ComboBox pre-populated with items from the given iterable.
     *
     * @param items the items to display; each is converted to a string via {@code toString()}
     */
    public ComboBox(Iterable<?> items) {
        this();
        if (items == null) return;
        for (var item : items) addItem(String.valueOf(item));
    }

    // ─── Items ───────────────────────────────────────────────────────────────

    /**
     * Appends an item to the dropdown list.
     *
     * @param item the string to add
     */
    public void addItem(String item) {
        combo.addItem(item);
    }

    // ─── Selection ───────────────────────────────────────────────────────────

    /**
     * Returns the currently selected item, or {@code null} if nothing is selected.
     *
     * @return the selected string
     */
    public String getSelectedItem() {
        return (String) combo.getSelectedItem();
    }

    /**
     * Sets the currently selected item.
     *
     * @param item the string to select
     */
    public void setSelectedItem(String item) {combo.setSelectedItem(item);}

    /**
     * Runs {@code action} every time the user picks a different item.
     * Call {@link #getSelectedItem()} inside the lambda to read the new value.
     *
     * @param action the action to execute on selection change
     */
    @Override
    public void onSelectionChange(Runnable action) {
        this.selectionChangeAction = action;
    }
}
