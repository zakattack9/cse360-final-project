package FinalProject.Components;

import java.awt.Color;
import java.util.LinkedHashMap;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import FinalProject.Models.Utilities.DatabaseMerger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class ScatterPlot extends JFrame
{
    private static final long serialVersionUID = 6294689542092367723L;

    public ScatterPlot(String title)
    {
        super(title);


        XYDataset dataset = createDataset();

        JFreeChart chart = ChartFactory.createScatterPlot("Percentage of Time Students are in Class",
                "Percentage", "Frequency", dataset);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(255, 228, 196));

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);

    }

    public XYDataset createDataset()
    {
        XYSeriesCollection dataset = new XYSeriesCollection();

        //add x then y datasets
        DatabaseMerger databaseMerger = new DatabaseMerger();
        LinkedHashMap<String, LinkedHashMap<String, String>> mergedDBs = databaseMerger.getMergedDBs();
        mergedDBs.values().forEach((map) -> {
            //map.forEach((key,value) -> System.out.println(key + ": " + value));
            System.out.println(map.get("Nov 10"));
        });



        XYSeries time = new XYSeries("Percentage");

        return null;

    }

}
