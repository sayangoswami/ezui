package ezui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Abstract base class for all ezui UI components.
 * Provides a unified API for click events, hover events, and color changes,
 * so every widget in the library behaves consistently.
 * <p>
 * Hover note: because most components wrap an inner Swing widget (JButton, JLabel, etc.),
 * mouse events arrive on that inner widget rather than on this JPanel wrapper.
 * Subclasses should call {@link #forwardMouseEventsFrom(java.awt.Component)} in their
 * constructor to ensure hover works correctly.
 * </p>
 */
public abstract class Component extends JPanel implements Clickable, Hoverable {
    protected Runnable clickAction;
    protected Runnable hoverAction;
    protected Runnable hoverExitAction;

    /**
     * Constructor for the Component class.
     * Sets the component to be transparent by default.
     * Note: mouse events on the JPanel wrapper are registered here as a fallback,
     * but most subclasses should also call forwardMouseEventsFrom(innerWidget).
     */
    public Component() {
        setOpaque(false);

        // Fallback listener on the JPanel wrapper itself.
        // For components that add forwardMouseEventsFrom(), this may not fire
        // because the inner widget sits on top. It is kept for components that
        // have no inner widget (e.g. a custom-painted Component subclass).
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isEnabled() && clickAction != null) clickAction.run();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isEnabled() && hoverAction != null) hoverAction.run();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (isEnabled() && hoverExitAction != null) hoverExitAction.run();
            }
        });
    }

    // ─── Clickable ────────────────────────────────────────────────────────────

    /** Runs {@code action} whenever the user clicks this component. */
    @Override
    public void onClick(Runnable action) {
        this.clickAction = action;
    }

    // ─── Hoverable ────────────────────────────────────────────────────────────

    /** Runs {@code action} when the mouse pointer enters this component. */
    @Override
    public void onHover(Runnable action) {
        this.hoverAction = action;
    }

    /**
     * Runs {@code action} when the mouse pointer exits this component.
     * Use together with {@link #onHover} to create reversible hover effects.
     * <pre>{@code
     * icon.onHover(()    -> icon.setColor("Primary"));
     * icon.onHoverExit(() -> icon.setColor("TextMuted"));
     * }</pre>
     */
    @Override
    public void onHoverExit(Runnable action) {
        this.hoverExitAction = action;
    }

    // ─── Color ────────────────────────────────────────────────────────────────

    /**
     * Changes the primary color of this component using a palette key.
     * What "primary color" means is component-specific:
     * <ul>
     *   <li>{@link Text}   — foreground (text) color</li>
     *   <li>{@link Button} — background color</li>
     *   <li>{@link Icon}   — icon tint color</li>
     * </ul>
     * Valid keys: {@code "Primary"}, {@code "Secondary"}, {@code "Success"},
     * {@code "Info"}, {@code "Warning"}, {@code "Danger"}, {@code "Text"},
     * {@code "TextMuted"}, {@code "Alt"}, {@code "Link"}.
     *
     * @param colorKey a key from the active {@link ColorPalette}
     */
    public void setColor(String colorKey) {
        // Base no-op. Subclasses override to apply the color to their inner widget.
    }

    // ─── Internal helper ──────────────────────────────────────────────────────

    /**
     * Attaches click and hover listeners to an inner Swing widget so that
     * {@link #onClick}, {@link #onHover}, and {@link #onHoverExit} work even
     * when mouse events land on the inner widget instead of this JPanel wrapper.
     * <p>
     * Subclasses should call this in their constructor immediately after
     * creating their inner widget, e.g.:
     * </p>
     * <pre>{@code
     * label = new JLabel("hello");
     * forwardMouseEventsFrom(label);
     * }</pre>
     *
     * @param source the inner Swing component whose mouse events should be forwarded
     */
    protected void forwardMouseEventsFrom(java.awt.Component source) {
        source.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isEnabled() && clickAction != null) clickAction.run();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isEnabled() && hoverAction != null) hoverAction.run();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (isEnabled() && hoverExitAction != null) hoverExitAction.run();
            }
        });
    }

    /**
     * Like {@link #forwardMouseEventsFrom} but only forwards hover/exit events,
     * not clicks. Use this for components (e.g. Button) that already handle
     * clicks via an ActionListener to avoid firing the click action twice.
     *
     * @param source the inner Swing component whose hover events should be forwarded
     */
    protected void forwardHoverEventsFrom(java.awt.Component source) {
        source.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isEnabled() && hoverAction != null) hoverAction.run();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (isEnabled() && hoverExitAction != null) hoverExitAction.run();
            }
        });
    }
}
