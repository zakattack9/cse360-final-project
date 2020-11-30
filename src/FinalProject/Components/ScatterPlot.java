package FinalProject.Components;

import java.awt.Color;
import java.util.LinkedHashMap;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import FinalProject.Models.AttendanceDatabase;
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

        String[] attendance = AttendanceDatabase.getInstance().getTimeKeys();
        String[] dateKeys = AttendanceDatabase.getInstance().getDateKeys();
        LinkedHashMap<String, String> dates = AttendanceDatabase.getInstance().getDate();
        LinkedHashMap<String, Integer> times = AttendanceDatabase.getInstance().getAdditionalAsurites();

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

        for(int i = 0; i < dateKeys.length; i++)
        {
            for(int j = 0; j < times.size(); j++)
            {
                System.out.println(dateKeys[i]);
                System.out.println(times.get(attendance[j]));
                XYSeries series = new XYSeries(dates.get(dateKeys[i]));
                if(times.get(attendance[j]) >= 75)
                {
                    hundred++;
                }
                else if(times.get(attendance[j]) < 75 && times.get(attendance[j]) >= 67.5)
                {
                    ninety++;
                }
                else if(times.get(attendance[j]) < 67.5 && times.get(attendance[j]) >= 60)
                {
                    eighty++;
                }
                else if(times.get(attendance[j]) < 60 && times.get(attendance[j]) >= 52.5)
                {
                    seventy++;
                }
                else if(times.get(attendance[j]) < 52.5 && times.get(attendance[j]) >= 45)
                {
                    sixty++;
                }
                else if(times.get(attendance[j]) < 45 && times.get(attendance[j]) >= 37.5)
                {
                    fifty++;
                }
                else if(times.get(attendance[j]) < 37.5 && times.get(attendance[j]) >= 30)
                {
                    forty++;
                }
                else if(times.get(attendance[j]) < 30 && times.get(attendance[j]) >= 22.5)
                {
                    thirty++;
                }
                else if(times.get(attendance[j]) < 22.5 && times.get(attendance[j]) >= 15)
                {
                    twenty++;
                }
                else if(times.get(attendance[j]) < 15 && times.get(attendance[j]) >= 7.5)
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
        return dataset;


    }

}
