package ezui;

/**
 * Implemented by components that allow the user to choose from a list of items:
 * {@link ComboBox}, {@link ListBox}, and {@link MultiListBox}.
 * <p>
 * Following the same pattern as {@link Clickable} and {@link Hoverable}, the
 * registered action is a plain {@link Runnable} — no parameters. Retrieve the
 * current selection from inside the lambda by calling {@code getSelectedItem()}
 * or {@code getSelectedItems()} on the component.
 * </p>
 * <pre>{@code
 * ComboBox filter = new ComboBox(List.of("All", "A", "B", "C"));
 * filter.onSelectionChange(() -> {
 *     String grade = filter.getSelectedItem();
 *     // update your data and call table.refresh()
 * });
 * }</pre>
 */
public interface Selectable {

    /**
     * Registers an action to run whenever the user changes the selection.
     * <p>
     * For {@link ComboBox} this fires once per committed change.
     * For {@link ListBox} and {@link MultiListBox} this fires after the mouse
     * is released (i.e. only when {@code getValueIsAdjusting()} is {@code false}),
     * so rapid drags do not spam the action.
     * </p>
     *
     * @param action the {@link Runnable} to execute on selection change
     */
    void onSelectionChange(Runnable action);
}
