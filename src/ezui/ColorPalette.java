package ezui;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that assigns colors for each of the following categories - Background, Primary, Secondary, Success, Info, Warning,
 * Danger, Focus, Alt, Link.
 * We have some predefined palettes that will be used by our components.
 * The active palette can be changed by calling the set method with the name of the desired palette.
 */
public class ColorPalette {
    private static String activePalette = "Light";
    private static final Map<String, Map<String, Color>> registry = new HashMap<>();

    private ColorPalette(String name) {
        if (registry.containsKey(name)) {
            activePalette = name;
        }
    }

    static {
        // Preset: Soft Paper
        Map<String, Color> light = Map.of(
                "Background", new Color(0xF8F9FB),
                "Primary", new Color(0x3E5CB8),
                "Secondary", new Color(0xE2E8F0),
                "Success", new Color(0x22863A),
                "Info", new Color(0x005CC5),
                "Warning", new Color(0xB08800),
                "Danger", new Color(0xD73A49),
                "Focus", new Color(0x0366D6),
                "Alt", new Color(0xFFFFFF),
                "Link", new Color(0x005FB8)
        );
        registry.put("Light", light);

        // Preset: Slate & Indigo
        Map<String, Color> dark = Map.of(
                "Background", new Color(0x1E1E2E),
                "Primary", new Color(0x89B4FA),
                "Secondary", new Color(0x45475A),
                "Success", new Color(0xA6E3A1),
                "Info", new Color(0x74C7EC),
                "Warning", new Color(0xF9E2AF),
                "Danger", new Color(0xF38BA8),
                "Focus", new Color(0xB4BEFE),
                "Alt", new Color(0x313244),
                "Link", new Color(0xCBA6F7)
        );
        registry.put("Dark", dark);
    }

    /**
     * Sets the active color palette.
     * @param name The name of the color palette to activate. Can be "Light" or "Dark" (for now).
     */
    public static void set(String name) {
        new ColorPalette(name);
    }

    /**
     * Gets the color associated with the specified key in the active color palette.
     * @param key The key for the desired color. Keys can be Primary, Secondary, Success, Info, Warning,
     *             Danger, Focus, Alt, Light, Dark, Link.
     * @return The color associated with the key, or black if not found.
     */
    public static Color getColor(String key) {
        return registry.get(activePalette).getOrDefault(key, Color.BLACK);
    }
}