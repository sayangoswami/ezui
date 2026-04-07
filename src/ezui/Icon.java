package ezui;

import org.kordamp.ikonli.swing.FontIcon;
import org.kordamp.ikonli.IkonResolver;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import javax.swing.*;
import java.awt.*;


/**
 * The Icon class is a graphical user interface component that displays an icon
 * using the FontAwesomeSolid icon set. It provides functionality to customize
 * the size and color of the displayed icon and is designed to integrate seamlessly
 * into Java Swing applications as a subclass of the Component class.
 */
public class Icon extends Component {
    private FontIcon internalIcon;
    private JLabel label;

    /**
     * Constructs an Icon component initialized with the specified icon code.
     * The Icon utilizes the FontAwesomeSolid icon set and applies default
     * styling, including size and color. It gracefully handles invalid icon
     * codes by defaulting to a question mark icon and logging a warning message.
     *
     * @param iconCode A string representing the FontAwesomeSolid icon code. The
     *                 icon code is case-insensitive and may include hyphens (e.g., "home", "arrow-left").
     *                 You can find icon names <a href="https://fontawesome.com/search?ip=classic&s=solid&ic=free-collection">here</a>
     */
    public Icon(String iconCode) {
        String enumName = iconCode.replace("-", "_").toUpperCase();
        try {
            internalIcon = FontIcon.of(FontAwesomeSolid.valueOf(enumName));
        } catch (IllegalArgumentException e) {
            // Fallback to a question mark if the student types a code wrong
            internalIcon = FontIcon.of(FontAwesomeSolid.valueOf("QUESTION"));
            System.err.println("Warning: Icon code '" + enumName + "' not found.");
        }

        // Style from our palettes
        internalIcon.setIconSize(24);
        internalIcon.setIconColor(ColorPalette.getColor("Primary"));

        label = new JLabel(internalIcon);
        setLayout(new BorderLayout());
        super.add(label, BorderLayout.CENTER);
    }

    /**
     * Sets the size of the icon displayed by this component.
     *
     * @param size the desired size of the icon in pixels
     */
    public void setSize(int size) {
        internalIcon.setIconSize(size);
        this.repaint();
    }

    /**
     * Sets the color of the icon displayed in this component.
     * The color is determined based on the specified color key
     * from the active color palette.
     *
     * @param colorKey The key representing the desired color in the active
     *                 color palette. Valid keys include "Primary", "Secondary",
     *                 "Success", "Info", "Warning", "Danger", "Focus", "Alt",
     *                 "Light", "Dark", and "Link".
     */
    public void setColor(String colorKey) {
        internalIcon.setIconColor(ColorPalette.getColor(colorKey));
        this.repaint();
    }

    /**
     * Retrieves the internal Swing-based icon representation used by this component.
     * The returned icon is an instance of FontIcon configured with properties such as size
     * and color based on the component's current state.
     *
     * @return the internal FontIcon instance representing the Swing-compatible icon
     */
    public FontIcon getSwingIcon() {
        return this.internalIcon;
    }
}