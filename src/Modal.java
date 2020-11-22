import javax.swing.*;
import java.util.Map;
import java.util.HashMap;

public class Modal {
  private JDialog modal;
  private Map inputMap = new HashMap<String, JTextField>();

  public Modal(JFrame frame, String name) {
    modal = new JDialog(frame, name, true);
    modal.setLayout(new SpringLayout());
    // set cancel and submit buttons
    // like event listener to submit button
    // loop through inputMap and get textfield values when submit btn is clicked
  }

  public void addInput(String label) {
    
  }

  public JDialog getModal() { return modal; }
}
