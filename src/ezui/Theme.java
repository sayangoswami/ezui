package ezui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;


/**
 * The Theme class provides the functionality to initialize and apply a global theme
 * for the application's user interface. It allows selecting a color palette and font
 * style to achieve a consistent and personalized look and feel across the application.
 */
public class Theme {

    /**
     * Initializes the application's theme by setting the color palette and font configuration
     * based on the provided mode and font name. The Look and Feel is configured for either
     * dark or light mode, and the specified font palette is applied globally.
     * <p>
     * If an error occurs during initialization, the theme falls back to the default settings.
     *
     * @param mode The theme mode to initialize. Accepts "Dark" for dark mode or any other value for light mode.
     * @param font The name of the font palette to apply. Examples include "Default", "Compact".
     */
    public static void initialize(String mode, String font) {
        try {
            // Set the Look and Feel BEFORE any components exist
            if (mode.equalsIgnoreCase("Dark")) {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                ColorPalette.set("Dark");
            } else {
                UIManager.setLookAndFeel(new FlatLightLaf());
                ColorPalette.set("Light");
            }
            FontPalette.set(font);
        } catch (Exception e) {
            System.err.println("Theme initialization failed. Falling back to default.");
        }
    }
}