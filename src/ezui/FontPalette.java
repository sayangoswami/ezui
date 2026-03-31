package ezui;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that manages font styles for different UI components.
 * It allows switching between different font palettes.
 */
public class FontPalette {
    private static String activePalette = "Default";
    private static final Map<String, Map<String, Font>> registry = new HashMap<>();

    static {
        // Preset: Modern/Default
        Map<String, Font> modern = new HashMap<>();
        modern.put("Title", new Font("SansSerif", Font.BOLD, 32));
        modern.put("H1",    new Font("SansSerif", Font.BOLD, 24));
        modern.put("H2",    new Font("SansSerif", Font.BOLD, 20));
        modern.put("H3",    new Font("SansSerif", Font.BOLD, 18));
        modern.put("Body",  new Font("SansSerif", Font.PLAIN, 14));
        modern.put("Small", new Font("SansSerif", Font.PLAIN, 12));
        modern.put("Label", new Font("SansSerif", Font.ITALIC, 13));
        registry.put("Default", modern);

        // Preset: Compact (for data-heavy dashboards)
        Map<String, Font> compact = new HashMap<>();
        compact.put("Title", new Font("Segoe UI", Font.BOLD, 22));
        compact.put("H1",    new Font("Segoe UI", Font.BOLD, 18));
        compact.put("H2",    new Font("Segoe UI", Font.BOLD, 16));
        compact.put("H3",    new Font("Segoe UI", Font.BOLD, 14));
        compact.put("Body",  new Font("Segoe UI", Font.PLAIN, 12));
        compact.put("Small", new Font("Segoe UI", Font.PLAIN, 10));
        compact.put("Label", new Font("Segoe UI", Font.PLAIN, 11));
        registry.put("Compact", compact);
    }

    private FontPalette(String name) {
        // If the name exists, set it as the active global palette
        if (registry.containsKey(name)) {
            activePalette = name;
        }
    }

    /**
     * Sets the active font palette.
     * @param name The name of the font palette to activate. Can be "Default" or "Compact" (for now).
     */
    public static void set(String name) {
        new FontPalette(name);
    }

    /**
     * Components call this to fetch the specific font style dictated by the active palette.
     * @param key The key for the desired font style. Keys can be Title, H1, H2, H3, Body, Small, Label.
     * @return The font associated with the key, or a default font if not found.
     */
    public static Font getFont(String key) {
        return registry.get(activePalette).getOrDefault(key, new Font("Monospaced", Font.PLAIN, 12));
    }
}