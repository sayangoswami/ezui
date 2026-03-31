package ezui;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.CategoryStyler;
import org.knowm.xchart.style.PieStyler;
import java.util.*;
import java.awt.BorderLayout;

public class Chart extends Component {
    private final org.knowm.xchart.internal.chartpart.Chart<?, ?> xchart;

    public static Chart createBarChart(String title, String xTitle, String yTitle, Map<String, Double> data) {
        CategoryChart chart = new CategoryChartBuilder()
                .width(400).height(300)
                .title(title).xAxisTitle(xTitle).yAxisTitle(yTitle)
                .build();

        // Adding data returns a CategorySeries object
        chart.addSeries("Data", new ArrayList<>(data.keySet()), new ArrayList<>(data.values()));
        return new Chart(chart);
    }

    public static Chart createPieChart(String title, Map<String, Double> data) {
        PieChart chart = new PieChartBuilder()
                .width(400).height(300)
                .title(title)
                .build();

        data.forEach(chart::addSeries);
        return new Chart(chart);
    }

    private Chart(org.knowm.xchart.internal.chartpart.Chart<?, ?> xchart) {
        this.xchart = xchart;

        xchart.getStyler().setChartBackgroundColor(ColorPalette.getColor("Background"));
        xchart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        xchart.getStyler().setChartTitleBoxVisible(false);

        if (xchart instanceof CategoryChart) {
            CategoryStyler styler = ((CategoryChart) xchart).getStyler();
            styler.setLabelsVisible(true);
        } else if (xchart instanceof PieChart) {
            PieStyler styler = ((PieChart) xchart).getStyler();
            styler.setLegendVisible(true);
            styler.setCircular(true);
        }

        XChartPanel<?> panel = new XChartPanel<>(xchart);
        setLayout(new BorderLayout());
        super.add(panel, BorderLayout.CENTER);
    }
}
