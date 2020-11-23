import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.HashMap;

public class ModalBuilder {
  private final int MODAL_WIDTH = (int) (Main.WINDOW_WIDTH * 0.5);
  private final int MODAL_HEIGHT = (int) (Main.WINDOW_HEIGHT * 0.5);

  private JDialog modal;
  private Map<JLabel, JTextField> inputMap;

  public ModalBuilder(JFrame frame, String name) {
    modal = new JDialog(frame, name, true);
    inputMap = new HashMap();

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

  private JPanel createInputsPanel() {
    JPanel content = new JPanel();
    GroupLayout contentLayout = new GroupLayout(content);
    
    GroupLayout.SequentialGroup horizontal = contentLayout.createSequentialGroup();
    GroupLayout.ParallelGroup labelGroup = contentLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
    GroupLayout.ParallelGroup textFieldGroup = contentLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
    inputMap.keySet().stream().forEach(label -> labelGroup.addComponent(label));
    inputMap.values().stream().forEach(textField -> textFieldGroup.addComponent(textField));
    horizontal.addGroup(labelGroup).addGroup(textFieldGroup);

    GroupLayout.SequentialGroup vertical = contentLayout.createSequentialGroup();
    inputMap.entrySet().stream().forEach(entry -> {
      vertical.addGroup(contentLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
          .addComponent(entry.getKey())
          .addComponent(entry.getValue()));
    });
    
    contentLayout.setHorizontalGroup(horizontal);
    contentLayout.setVerticalGroup(vertical);
    contentLayout.setAutoCreateGaps(true);
    contentLayout.setAutoCreateContainerGaps(true);
    content.setLayout(contentLayout);
    return content;
  }

  private JPanel createButtonsPanel() {
    JPanel buttons = new JPanel(new BorderLayout());
    JButton cancelBtn = createCancelBtn();
    JButton submitBtn = createSubmitBtn();

    buttons.add(cancelBtn, BorderLayout.WEST);
    buttons.add(submitBtn, BorderLayout.EAST);
    return buttons;
  }

  private JButton createCancelBtn() {
    JButton cancelBtn = new JButton("Cancel");
    // add event listener
    return cancelBtn;
  }

  private JButton createSubmitBtn() {
    JButton submitBtn = new JButton("Submit");
    submitBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // loop through inputMap and get textfield values when submit btn is clicked
        inputMap.values().stream().forEach(textField -> System.out.println(textField.getText()));
      }
    });
    return submitBtn;
  }

  private void addToModal(JPanel content, JPanel buttons) {
    JPanel layout = new JPanel(new BorderLayout());
    layout.add(content, BorderLayout.NORTH);
    layout.add(buttons, BorderLayout.SOUTH);
    modal.add(layout);
  }

  public JDialog getModal() {
    JPanel content = createInputsPanel();
    JPanel buttons = createButtonsPanel();
    addToModal(content, buttons);
    return modal;
  }
}
