package ezui;

import javax.swing.*;
import java.awt.*;


/**
 * A customizable button component extending the {@code Component} class.
 * Provides text and icon options along with support for click actions.
 * Internally leverages a {@code JButton} for rendering and behavior.
 */
public class Button extends Component {
    protected JButton internalButton;

    /**
     * Creates a new {@code Button} instance with the specified label.
     * The button leverages internal styling from predefined palettes for fonts and colors.
     * Clicking the button triggers an associated click action if one is defined.
     *
     * @param label The text to display on the button.
     */
    public Button(String label) {
        this.setBorder(BorderFactory.createEmptyBorder());
        internalButton = new JButton(label);

        // Styling from Palettes
        internalButton.setFont(FontPalette.getFont("Body"));
        internalButton.setBackground(ColorPalette.getColor("Secondary"));
        internalButton.setForeground(ColorPalette.getColor("Primary"));

        // Layout
        setLayout(new BorderLayout());
        super.add(internalButton, BorderLayout.CENTER);

        // Link the internal Swing action to our Component's clickAction
        internalButton.addActionListener(e -> {
            if (clickAction != null) clickAction.run();
        });
    }

    /**
     * Creates a new {@code Button} instance with the specified label and an icon.
     * The button combines text and graphical representation to enhance visual
     * appeal and functionality. Clicking the button triggers an associated click
     * action if one is defined.
     *
     * @param label    The text to display on the button.
     * @param iconCode The code representing the desired icon. This corresponds to
     *                 a FontAwesomeSolid icon code (e.g., "user-circle", "home").
     *                 You can find icon names <a href="https://fontawesome.com/v5/search?ic=free-collection">here</a>
     */
    public Button(String label, String iconCode) {
        this(label); // Call an existing constructor

        // Create the icon and set it on the internal Swing button
        // Use YOUR Icon class to handle the "user-circle" -> USER_CIRCLE logic
        Icon helper = new Icon(iconCode);
        var icon = helper.getSwingIcon();

        // Grab the processed swing icon and give it to the JButton
        this.internalButton.setIcon(icon);
        icon.setIconColor(ColorPalette.getColor("Info"));
    }

    /**
     * Sets whether this component and its associated internal button are enabled.
     * When disabled, both the component and its internal button will not respond
     * to user interactions.
     *
     * @param enabled a boolean indicating whether the component should be enabled (true)
     *                or disabled (false)
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        internalButton.setEnabled(enabled);
    }
}