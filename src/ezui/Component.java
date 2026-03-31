package ezui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * This is an abstract Component class that defines the common features of a GUI component
 * It provides default actions for clickable and hoverable interfaces,
 * which is to ignore them (can be changed in derived classes).
 * <p>
 * This class serves as a base for all UI components, providing common functionality
 * and a consistent API.
 * </p>
 */
public abstract class Component extends JPanel implements Clickable, Hoverable {
    protected Runnable clickAction;
    protected Runnable hoverAction;

    /**
     * Constructor for the Component class.
     * Sets the component to be transparent by default and initializes event listeners.
     */
    public Component() {
        setOpaque(false); // Default to transparent for nesting

        // Setup Event Listeners
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isEnabled() && clickAction != null) clickAction.run();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (isEnabled() && hoverAction != null) hoverAction.run();
            }
        });
    }

    /**
     * Sets the action to be performed on click events.
     * @param action The action to be performed when the component is clicked.
     */
    @Override
    public void onClick(Runnable action) {
        this.clickAction = action;
    }

    /**
     * Sets the action to be performed on hover events.
     * @param action The action to be performed when the component is hovered over.
     */
    @Override
    public void onHover(Runnable action) {
        this.hoverAction = action;
    }
}