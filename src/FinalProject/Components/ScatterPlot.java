package FinalProject.Components;

import java.awt.Color;
import java.util.LinkedHashMap;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import FinalProject.Models.Utilities.DatabaseConverter;
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
        DatabaseConverter DBConverter = new DatabaseConverter();
        String[] yes = DBConverter.getDBKeys();
        mergedDBs.values().forEach((map) -> {

            //map.forEach((key,value) -> System.out.println(key + ": " + value));

            System.out.println("Dates: " + map.get(yes[0]));
            //System.out.println(dates.getDBKeys());
        });
        int hundred = 0;
        int ninety = 0;
        int eighty = 0;
        int seventy = 0;
        int sixty = 0;
        int fifty = 0;
        int forty = 0;
        int thirty = 0;
        int twenty = 0;
        int ten = 0;
        int zero = 0;

        for(int i = 0; i < dates.length - 1; i++)
        {
            for(int j = 0; j < array.length - 1; j++)
            {
                XYSeries series = new XYSeries(DateArray[i]);
                if(array[j] >= 75)
                {
                    hundred++;
                }
                else if(array[j] < 75 && array[j] >= 67.5)
                {
                    ninety++;
                }
                else if(array[j] < 67.5 && array[j] >= 60)
                {
                    eighty++;
                }
                else if(array[j] < 60 && array[j] >= 52.5)
                {
                    seventy++;
                }
                else if(array[j] < 52.5 && array[j] >= 45)
                {
                    sixty++;
                }
                else if(array[j] < 45 && array[j] >= 37.5)
                {
                    fifty++;
                }
                else if(array[j] < 37.5 && array[j] >= 30)
                {
                    forty++;
                }
                else if(array[j] < 30 && array[j] >= 22.5)
                {
                    thirty++;
                }
                else if(array[j] < 22.5 && array[j] >= 15)
                {
                    twenty++;
                }
                else if(array[j] < 15 && array[j] >= 7.5)
                {
                    ten++;
                }
                else
                {
                    System.out.println("invaid attendance");
                }
                series.add(100, hundred);
                series.add(90, ninety);
                series.add(80, eighty);
                series.add(70, seventy);
                series.add(60, sixty);
                series.add(50, fifty);
                series.add(40, forty);
                series.add(30, thirty);
                series.add(20, twenty);
                series.add(10, ten);

            }
        }
        //return dataset;

        return null;

    }

}
