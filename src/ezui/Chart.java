package ezui;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.CategoryStyler;
import org.knowm.xchart.style.PieStyler;
import java.util.*;
import java.awt.BorderLayout;

/**
 * A chart component that displays bar or pie charts backed by a {@code Map<String, Double>}.
 * <p>
 * Create charts via the static factory methods, then call {@link #refresh(Map)} whenever
 * the underlying data changes — just like {@link Table#refresh()}.
 * </p>
 * <pre>{@code
 * Map<String, Double> grades = new LinkedHashMap<>();
 * grades.put("A", 12.0);
 * grades.put("B", 18.0);
 *
 * Chart pie = Chart.createPieChart("Grade Distribution", grades);
 * root.add(pie, 0, 1, Alignment.FILL);
 *
 * // Later, after data changes:
 * grades.put("C", 5.0);
 * pie.refresh(grades);   // chart redraws instantly
 * }</pre>
 */
public class Chart extends Component {

    /** Distinguishes bar from pie so {@link #refresh} can rebuild the right type. */
    private enum ChartType { BAR, PIE }

    private final ChartType chartType;
    private final String title;
    private final String xTitle; // bar only
    private final String yTitle; // bar only
    private Map<String, Double> data;
    private XChartPanel<?> chartPanel;

    // ─── Factory methods ──────────────────────────────────────────────────────

    /**
     * Creates a bar chart.
     *
     * @param title  the chart title
     * @param xTitle the x-axis label
     * @param yTitle the y-axis label
     * @param data   the initial data: category name → value
     */
    public static Chart createBarChart(String title, String xTitle, String yTitle, Map<String, Double> data) {
        return new Chart(ChartType.BAR, title, xTitle, yTitle, data);
    }

    /**
     * Creates a pie chart.
     *
     * @param title the chart title
     * @param data  the initial data: slice name → value
     */
    public static Chart createPieChart(String title, Map<String, Double> data) {
        return new Chart(ChartType.PIE, title, null, null, data);
    }

    // ─── Constructor ──────────────────────────────────────────────────────────

    private Chart(ChartType chartType, String title, String xTitle, String yTitle, Map<String, Double> data) {
        this.chartType = chartType;
        this.title     = title;
        this.xTitle    = xTitle;
        this.yTitle    = yTitle;
        this.data      = new LinkedHashMap<>(data);
        setLayout(new BorderLayout());
        buildPanel();
    }

    // ─── Public API ───────────────────────────────────────────────────────────

    /**
     * Replaces the chart data and redraws the chart.
     * Call this whenever your data map changes, just like {@link Table#refresh()}.
     *
     * @param newData the updated data map
     */
    public void refresh(Map<String, Double> newData) {
        this.data = new LinkedHashMap<>(newData);
        buildPanel();
    }

    // ─── Internal ─────────────────────────────────────────────────────────────

    /**
     * Builds (or rebuilds) the underlying XChart and wraps it in a new panel.
     * Called once on construction and again on every {@link #refresh}.
     */
    private void buildPanel() {
        // Remove the old panel if one already exists
        if (chartPanel != null) remove(chartPanel);

        org.knowm.xchart.internal.chartpart.Chart<?, ?> xchart;

        if (chartType == ChartType.PIE) {
            PieChart chart = new PieChartBuilder()
                    .width(400).height(300)
                    .title(title)
                    .build();
            data.forEach(chart::addSeries);
            applyCommonStyle(chart);
            PieStyler ps = chart.getStyler();
            ps.setLegendVisible(true);
            ps.setCircular(true);
            xchart = chart;
        } else {
            CategoryChart chart = new CategoryChartBuilder()
                    .width(400).height(300)
                    .title(title).xAxisTitle(xTitle).yAxisTitle(yTitle)
                    .build();
            chart.addSeries("Data", new ArrayList<>(data.keySet()), new ArrayList<>(data.values()));
            applyCommonStyle(chart);
            CategoryStyler cs = chart.getStyler();
            cs.setLabelsVisible(true);
            xchart = chart;
        }

        chartPanel = new XChartPanel<>(xchart);
        super.add(chartPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void applyCommonStyle(org.knowm.xchart.internal.chartpart.Chart<?, ?> chart) {
        chart.getStyler().setChartBackgroundColor(ColorPalette.getColor("Background"));
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setChartTitleBoxVisible(false);
    }
}
