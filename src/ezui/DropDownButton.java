package ezui;

import javax.swing.*;


/**
 * A specialized button component that provides dropdown functionality.
 * Extends the {@link Button} class and overrides the default click behavior to display a popup menu.
 * The dropdown menu can be populated with options where each option has a label and an associated action.
 */
public class DropDownButton extends Button {
    private final JPopupMenu menu;

    /**
     * Constructs a DropDownButton with the specified label. The button displays
     * a dropdown menu when clicked. The dropdown can be populated with options
     * using the {@code addOption} method.
     *
     * @param label the text to display on the button
     */
    public DropDownButton(String label) {
        super(label);
        menu = new JPopupMenu();

        // Override the default click behavior to show the menu
        this.onClick(() -> {
            menu.show(internalButton, 0, internalButton.getHeight());
        });
    }

    /**
     * Adds a selectable option to the dropdown menu.
     *
     * @param label    the text shown for the menu item
     * @param onSelect the action executed when the item is chosen
     */
    public void addOption(String label, Runnable onSelect) {
        JMenuItem item = new JMenuItem(label);
        item.setFont(FontPalette.getFont("Small"));
        item.addActionListener(e -> onSelect.run());
        menu.add(item);
    }
}