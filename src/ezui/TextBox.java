package ezui;

import javax.swing.*;
import java.awt.*;


/**
 * The TextBox class is a user interface component that extends the Component class.
 * It serves as a text input field, allowing users to enter and edit text. This class
 * internally uses a JTextField and provides a clean API to interact with the text field.
 */
public class TextBox extends Component {
    private final JTextField textField;

    /**
     * Default constructor for the TextBox class. This initializes the TextBox with
     * a JTextField for user text input. The JTextField is styled with a font defined
     * in the FontPalette under the "Body" key and is set to a preferred size of
     * 150x30 pixels. The layout of the TextBox is configured with a BorderLayout,
     * and the JTextField is placed in the center region.
     */
    public TextBox() {
        textField = new JTextField();
        textField.setFont(FontPalette.getFont("Body"));
        textField.setPreferredSize(new Dimension(150, 30));

        setLayout(new BorderLayout());
        super.add(textField, BorderLayout.CENTER);
    }

    /**
     * Retrieves the current text content of the text field within the TextBox component.
     *
     * @return the text currently entered in the text field
     */
    public String getText() {
        return textField.getText();
    }

    /**
     * Sets the text content of the text field within the TextBox component.
     *
     * @param text the text to be displayed in the text field
     */
    public void setText(String text) {
        textField.setText(text);
    }
}