package FinalProject.Controllers;

import FinalProject.Components.ScatterPlot;
import org.jfree.data.xy.XYDataset;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveController implements ActionListener {
  public SaveController() {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    ScatterPlot set = new ScatterPlot("test");
    set.createDataset();


  }
}
