package ezui;

import javax.swing.*;

import org.kordamp.ikonli.swing.FontIcon;

import java.awt.*;


/**
 * A styled button component with text, optional icon, click and hover support.
 * <p>
 * By default the button uses a neutral secondary background. Call
 * {@link #setColor(String)} to switch to any palette color — for example
 * {@code btn.setColor("Danger")} for a red delete button.
 * </p>
 */
public class Button extends Component {
    protected JButton internalButton;
    FontIcon icon;

    /**
     * Creates a text-only button.
     *
     * @param label the text to display on the button
     */
    public Button(String label) {
        icon = null;
        this.setBorder(BorderFactory.createEmptyBorder());
        internalButton = new JButton(label);

        // Default palette styling
        internalButton.setFont(FontPalette.getFont("Body"));
        internalButton.setBackground(ColorPalette.getColor("Secondary"));
        internalButton.setForeground(ColorPalette.getColor("Primary"));

        setLayout(new BorderLayout());
        super.add(internalButton, BorderLayout.CENTER);

        // Wire ActionListener → clickAction (reliable for JButton clicks)
        internalButton.addActionListener(e -> {
            if (clickAction != null) clickAction.run();
        });

        // Forward hover events from the inner JButton so onHover/onHoverExit work.
        // Use forwardHoverEventsFrom (not forwardMouseEventsFrom) to avoid firing
        // clickAction twice — the ActionListener above already handles clicks.
        forwardHoverEventsFrom(internalButton);
    }

    /**
     * Creates a button with text and a FontAwesome icon.
     * Find valid icon names at <a href="https://fontawesome.com/v5/search?s=solid">FontAwesome v5 Solid</a>.
     *
     * @param label    the text to display on the button
     * @param iconCode a FontAwesomeSolid icon code, e.g. {@code "save"}, {@code "trash"}
     */
    public Button(String label, String iconCode) {
        this(label);
        Icon helper = new Icon(iconCode);
        icon = helper.getSwingIcon();
        this.internalButton.setIcon(icon);
        icon.setIconColor(ColorPalette.getColor("Primary"));
    }

    /**
     * Changes the button's background color using a palette key.
     * <p>Example: {@code btn.setColor("Danger")} gives a red button.</p>
     *
     * @param colorKey a key from the active {@link ColorPalette}
     */
    @Override
    public void setColor(String colorKey) {
        internalButton.setBackground(ColorPalette.getColor(colorKey));
        final String darkBG = "Primary Focus Link TextMuted Success Info Warning Danger";
        if (darkBG.contains(colorKey)) {
            internalButton.setForeground(ColorPalette.getColor("Alt"));
            if (icon != null) icon.setIconColor(ColorPalette.getColor("Alt"));
        } else {
            internalButton.setForeground(ColorPalette.getColor("Primary"));
            if (icon != null) icon.setIconColor(ColorPalette.getColor("Primary"));
        }
        repaint();
    }

    /**
     * Enables or disables this button. A disabled button ignores all interactions.
     *
     * @param enabled {@code true} to enable, {@code false} to disable
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        internalButton.setEnabled(enabled);
    }

    /**
     * Sets the label of a button.
     * @param text - new label
     */
    public void setText(String text) {
        internalButton.setText(text);
        repaint();
    }
}
