package ezui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;


/**
 * A class representing a text component that extends the base Component class.
 * The Text class is used for displaying textual content with a specified font style and color.
 * The text is encapsulated in a JLabel, and a layout with padding is applied to enhance its presentation.
 */
public class Text extends Component {
    private final JLabel label;

    /**
     * Constructs a Text component with the specified content and font type.
     * The Text component is displayed using a JLabel, styled with the selected font and color.
     * A right padding of 2 pixels is added to ensure proper spacing.
     *
     * @param content The textual content to be displayed within the Text component.
     * @param fontType The type of font to be used for displaying the text, as defined in the FontPalette.
     */
    public Text(String content, String fontType) {
        label = new JLabel(content);
        label.setFont(FontPalette.getFont(fontType));
        label.setForeground(ColorPalette.getColor("Primary"));

        int top = 0; //(fontType.equals("H1") || fontType.equals("Title")) ? 20 : 5;
        int bottom = 0; //(fontType.equals("H1")) ? 15 : 5;

        // 2 pixels of padding on the right ensure the last character
        // doesn't hit the "wall" of the container.
        label.setBorder(new EmptyBorder(top, 0, bottom, 2));

        setLayout(new BorderLayout());
        super.add(label, BorderLayout.CENTER);
    }

    /**
     * Updates the text displayed in the associated label of the Text component.
     *
     * @param content The new textual content to be displayed.
     */
    public void setText(String content) {
        label.setText(content);
    }
}