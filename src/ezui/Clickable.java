package ezui;

/**
 * All components must implement this interface.
 * It can be overridden to specify what to do when the component is clicked.
 * This is useful for creating interactive components.
 */
public interface Clickable {
    /**
     * Sets the action to perform when the component is clicked.
     * @param action The action to perform when the component is clicked.
     */
    void onClick(Runnable action);
}
