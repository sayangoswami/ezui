package ezui;

import javax.swing.*;
import java.awt.*;


/**
 * The TextArea class is a user interface component that wraps a {@link JTextArea} inside
 * a scrollable pane, providing a multi-line text editing area with automatic line wrapping
 * and word wrapping.
 * <p>
 * This class extends the {@link Component} class, inheriting functionality for handling
 * click and hover interactions while offering additional methods for managing text content
 * within the text area.
 */
public class TextArea extends Component {
    private final JTextArea textArea;
    private final JScrollPane scrollPane;

    /**
     * Constructs a TextArea object with the specified number of rows and columns.
     * The TextArea serves as a scrollable multi-line text input component, initialized
     * with a specific font and line-wrapping behavior.
     *
     * @param rows the initial number of rows for the text area
     * @param cols the initial number of columns for the text area
     */
    public TextArea(int rows, int cols) {
        textArea = new JTextArea(rows, cols);
        textArea.setFont(FontPalette.getFont("Body"));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textArea);

        setLayout(new BorderLayout());
        super.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Constructs a TextArea object with default dimensions of 5 rows and 20 columns.
     * This constructor initializes a scrollable multi-line text area with a pre-configured
     * font and line-wrapping behavior. It is a convenience constructor that delegates
     * to {@link #TextArea(int, int)} with default values.
     */
    public TextArea() {
        this(5, 20);
    }

    /**
     * Retrieves the current text content of the text area.
     *
     * @return the text currently contained within the text area
     */
    public String getText() {
        return textArea.getText();
    }

    /**
     * Sets the text content of the text area.
     *
     * @param text the new text content to set in the text area
     */
    public void setText(String text) {
        textArea.setText(text);
    }
}