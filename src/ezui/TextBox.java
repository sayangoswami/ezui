package ezui;

import javax.swing.*;
import java.awt.*;


/**
 * A single-line text input field with optional placeholder text and password masking.
 * <p>
 * Internally holds both a plain {@link JTextField} and a {@link JPasswordField};
 * {@link #setMasked(boolean)} swaps between them so {@link #getText()} is always safe
 * (no deprecated {@code JPasswordField.getText()} calls).
 * </p>
 */
public class TextBox extends Component {
    private final JTextField     textField;
    private final JPasswordField passwordField;
    private boolean masked = false;

    /**
     * Creates an empty, unmasked text input field.
     */
    public TextBox() {
        Font      font = FontPalette.getFont("Body");
        Dimension size = new Dimension(150, 30);

        textField = new JTextField();
        textField.setFont(font);
        textField.setPreferredSize(size);

        passwordField = new JPasswordField();
        passwordField.setFont(font);
        passwordField.setPreferredSize(size);

        setLayout(new BorderLayout());
        super.add(textField, BorderLayout.CENTER); // default: plain text
    }

    // ─── Text access ─────────────────────────────────────────────────────────

    /**
     * Returns the current text in the field.
     * Works correctly whether the field is masked or not.
     *
     * @return the current text, never {@code null}
     */
    public String getText() {
        return masked ? new String(passwordField.getPassword()) : textField.getText();
    }

    /**
     * Programmatically sets the text in the field.
     *
     * @param text the text to display
     */
    public void setText(String text) {
        if (masked) passwordField.setText(text);
        else        textField.setText(text);
    }

    // ─── Masking ─────────────────────────────────────────────────────────────

    /**
     * Switches between plain-text and password-masked input.
     * <pre>{@code
     * TextBox passField = new TextBox();
     * passField.setMasked(true);  // characters appear as dots
     * }</pre>
     *
     * @param isMasked {@code true} to hide characters, {@code false} to show them
     */
    public void setMasked(boolean isMasked) {
        if (this.masked == isMasked) return; // no-op if already in that state
        this.masked = isMasked;
        removeAll();
        super.add(isMasked ? passwordField : textField, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // ─── Placeholder ─────────────────────────────────────────────────────────

    /**
     * Sets placeholder (hint) text that appears inside the field when it is empty.
     * The hint disappears as soon as the user starts typing.
     * <pre>{@code
     * TextBox nameField = new TextBox();
     * nameField.setPlaceholder("Enter your full name…");
     * }</pre>
     *
     * @param hint the placeholder string to display
     */
    public void setPlaceholder(String hint) {
        // FlatLaf reads this client property to render placeholder text
        textField.putClientProperty("JTextField.placeholderText", hint);
        passwordField.putClientProperty("JTextField.placeholderText", hint);
    }
}
