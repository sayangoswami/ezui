package ezui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;


/**
 * A component for displaying styled text labels, headings, and body copy.
 * Style strings correspond to entries in {@link FontPalette}: {@code "Title"},
 * {@code "H1"}, {@code "H2"}, {@code "H3"}, {@code "Body"}, {@code "Small"}, {@code "Label"}.
 */
public class Text extends Component {
    private final JLabel label;

    /**
     * Creates a Text component with a specific font style.
     *
     * @param content  the text to display
     * @param fontType a style key from {@link FontPalette}, e.g. {@code "H1"}, {@code "Body"}, {@code "Label"}
     */
    public Text(String content, String fontType) {
        label = new JLabel(content);
        label.setFont(FontPalette.getFont(fontType));
        label.setForeground(ColorPalette.getColor("Text"));

        // 2 pixels of right padding so the last character doesn't hit the cell wall
        label.setBorder(new EmptyBorder(0, 0, 0, 2));

        setLayout(new BorderLayout());
        super.add(label, BorderLayout.CENTER);

        // Forward hover/click events from the inner JLabel
        forwardMouseEventsFrom(label);
    }

    /**
     * Creates a Text component with the default {@code "Body"} style.
     *
     * @param content the text to display
     */
    public Text(String content) {
        this(content, "Body");
    }

    /**
     * Updates the displayed text.
     *
     * @param content the new text to display
     */
    public void setText(String content) {
        label.setText(content);
    }

    /**
     * Changes the text foreground color using a palette key.
     * Example: {@code text.setColor("Warning")} turns the text amber.
     *
     * @param colorKey a key from the active {@link ColorPalette}
     */
    @Override
    public void setColor(String colorKey) {
        label.setForeground(ColorPalette.getColor(colorKey));
        repaint();
    }
}
