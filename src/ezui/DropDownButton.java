package ezui;

import javax.swing.*;


/**
 * A button that reveals a popup menu of labelled actions when clicked.
 * Use when a single grid cell needs to offer several related operations
 * (e.g. "Sort by…", "Export as…", "Edit / Delete").
 */
public class DropDownButton extends Button {
    private final JPopupMenu menu;

    /**
     * Creates a text-only dropdown button.
     *
     * @param label the text to display on the button
     */
    public DropDownButton(String label) {
        super(label);
        menu = new JPopupMenu();
        initMenu();
    }

    /**
     * Creates a dropdown button with text and a FontAwesome icon.
     * Find valid icon names at <a href="https://fontawesome.com/v5/search?s=solid">FontAwesome v5 Solid</a>.
     *
     * @param label    the text to display on the button
     * @param iconCode a FontAwesomeSolid icon code, e.g. {@code "folder-open"}, {@code "cog"}
     */
    public DropDownButton(String label, String iconCode) {
        super(label, iconCode);
        menu = new JPopupMenu();
        initMenu();
    }

    /** Wires the button click to show the popup menu. */
    private void initMenu() {
        this.onClick(() -> menu.show(internalButton, 0, internalButton.getHeight()));
    }

    /**
     * Adds an option to the dropdown menu.
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