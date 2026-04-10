package ezui;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages named color palettes for the ezui library.
 * <p>
 * Components use palette keys rather than hard-coded colors, so switching between
 * light and dark themes is automatic. Available keys:
 * </p>
 * <ul>
 *   <li>{@code "Background"} — page/window background</li>
 *   <li>{@code "Primary"}    — brand/accent color (buttons, links, icons)</li>
 *   <li>{@code "Secondary"}  — neutral surface (default button background)</li>
 *   <li>{@code "Text"}       — default body-text color</li>
 *   <li>{@code "TextMuted"}  — secondary/hint text color</li>
 *   <li>{@code "Success"}    — green for confirmations</li>
 *   <li>{@code "Info"}       — blue for informational hints</li>
 *   <li>{@code "Warning"}    — amber for cautions</li>
 *   <li>{@code "Danger"}     — red for destructive actions / errors</li>
 *   <li>{@code "Focus"}      — highlight ring color</li>
 *   <li>{@code "Alt"}        — white / near-white surface (text on dark buttons)</li>
 *   <li>{@code "Link"}       — hyperlink color</li>
 * </ul>
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
        // ── Preset: Soft Paper (Light) ───────────────────────────────────────
        Map<String, Color> light = new HashMap<>();
        light.put("Background", new Color(0xF8F9FB));
        light.put("Primary",    new Color(0x3E5CB8)); // blue accent
        light.put("Secondary",  new Color(0xE2E8F0)); // neutral button bg
        light.put("Text",       new Color(0x1A202C)); // near-black body text
        light.put("TextMuted",  new Color(0x718096)); // grey secondary text
        light.put("Success",    new Color(0x22863A));
        light.put("Info",       new Color(0x005CC5));
        light.put("Warning",    new Color(0xB08800));
        light.put("Danger",     new Color(0xD73A49));
        light.put("Focus",      new Color(0x0366D6));
        light.put("Alt",        new Color(0xFFFFFF)); // white (text on colored buttons)
        light.put("Link",       new Color(0x005FB8));
        registry.put("Light", light);

        // ── Preset: Slate & Indigo (Dark) ────────────────────────────────────
        Map<String, Color> dark = new HashMap<>();
        dark.put("Background", new Color(0x1E1E2E));
        dark.put("Primary",    new Color(0x89B4FA)); // soft blue accent
        dark.put("Secondary",  new Color(0x45475A)); // neutral button bg
        dark.put("Text",       new Color(0xCDD6F4)); // near-white body text
        dark.put("TextMuted",  new Color(0x6C7086)); // grey secondary text
        dark.put("Success",    new Color(0xA6E3A1));
        dark.put("Info",       new Color(0x74C7EC));
        dark.put("Warning",    new Color(0xF9E2AF));
        dark.put("Danger",     new Color(0xF38BA8));
        dark.put("Focus",      new Color(0xB4BEFE));
        dark.put("Alt",        new Color(0x313244)); // dark surface
        dark.put("Link",       new Color(0xCBA6F7));
        registry.put("Dark", dark);
    }

    /**
     * Activates a named color palette. Call this via {@link Theme#initialize} before
     * creating any components.
     *
     * @param name palette name — currently {@code "Light"} or {@code "Dark"}
     */
    public static void set(String name) {
        new ColorPalette(name);
    }

    /**
     * Returns the {@link Color} for the given key in the active palette.
     * Falls back to {@link Color#BLACK} if the key is not found.
     *
     * @param key one of the palette keys documented on this class
     * @return the corresponding color
     */
    public static Color getColor(String key) {
        return registry.get(activePalette).getOrDefault(key, Color.BLACK);
    }
}
