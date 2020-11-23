import javax.swing.*;
import java.util.Map;
import java.util.HashMap;

public class ModalBuilder {
  private final int MODAL_WIDTH = (int) (Main.WINDOW_WIDTH * 0.5);
  private final int MODAL_HEIGHT = (int) (Main.WINDOW_HEIGHT * 0.5);

  private JDialog modal;
  private JPanel content;
  private GroupLayout layout;
  private Map<JLabel, JTextField> inputMap;

  public ModalBuilder(JFrame frame, String name) {
    modal = new JDialog(frame, name, true);
    content = new JPanel();
    inputMap = new HashMap();

    layout = new GroupLayout(content);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);
    content.setLayout(layout);

    modal.setSize(MODAL_WIDTH, MODAL_HEIGHT);
  }

  // adds input with normal JTextField
  public void addInput(String label) {
    JLabel inputLabel = new JLabel(label);
    JTextField textField = new JTextField();
    inputMap.put(inputLabel, textField);
  }

  // adds input with passed in textField (e.g. DateInput)
  public void addInput(String label, JTextField textField) {
    JLabel inputLabel = new JLabel(label);
    inputMap.put(inputLabel, textField);
  }

  private void addInputsToLayout() {
    GroupLayout.SequentialGroup horizontal = layout.createSequentialGroup();
    GroupLayout.ParallelGroup labelGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    GroupLayout.ParallelGroup textfieldGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
    inputMap.keySet().stream().forEach(label -> labelGroup.addComponent(label));
    inputMap.values().stream().forEach(textfield -> textfieldGroup.addComponent(textfield));
    layout.setHorizontalGroup(horizontal.addGroup(labelGroup).addGroup(textfieldGroup));

    GroupLayout.SequentialGroup vertical = layout.createSequentialGroup();
    inputMap.entrySet().stream().forEach(entry -> {
      vertical.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
          .addComponent(entry.getKey())
          .addComponent(entry.getValue()));
    });
    layout.setVerticalGroup(vertical);
  }

  // adds cancel and submit buttons to bottom of modal
  private void addCancelSubmit() {
    JButton cancelBtn = createCancelBtn();
    JButton submitBtn = createSubmitBtn();
    // link event listener to submit button
  }

  private JButton createCancelBtn() {
    JButton cancelBtn = new JButton();
    return cancelBtn;
  }

  private JButton createSubmitBtn() {
    JButton submitBtn = new JButton();
    // loop through inputMap and get textfield values when submit btn is clicked
    return submitBtn;
  }

  public JDialog getModal() {
    addInputsToLayout();
    addCancelSubmit();
    modal.add(content);
    return modal;
  }
}
