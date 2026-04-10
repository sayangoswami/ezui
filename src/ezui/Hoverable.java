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

    /**
     * Sets the action to perform when the mouse pointer exits the component.
     * Pair this with {@code onHover} to create toggle-style hover effects
     * (e.g., change color on enter, restore color on exit).
     * @param action The action to perform when the mouse pointer exits the component.
     */
    void onHoverExit(Runnable action);
}
