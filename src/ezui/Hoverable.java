package ezui;

/**
 * All components must implement this interface.
 * It can be overridden to specify what to do when the mouse pointer hovers over the component.
 * This is useful for creating interactive components.
 */
public interface Hoverable {
    /**
     * Sets the action to perform when the mouse pointer hovers over the component.
     * @param action The action to perform when the mouse pointer hovers over the component.
     */
    void onHover(Runnable action);
}
