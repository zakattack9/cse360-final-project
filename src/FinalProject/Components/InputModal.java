package FinalProject.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class InputModal extends JDialog {
  private Map<JLabel, JTextField> inputMap;
  private Map<JLabel, String> errMsgMap;
  private boolean clickedCancel;
  private JFrame frame;

  public InputModal(JFrame frame, String name) {
    super(frame, name, true);
    this.frame = frame;
    inputMap = new LinkedHashMap<>();
    errMsgMap = new HashMap<>();
    addClosingListener();
  }

  // adds input with normal JTextField
  public void addInput(String label) {
    JLabel inputLabel = new JLabel(label);
    JTextField textField = new JTextField();
    String errMessage = "Please enter a value for \"" + inputLabel.getText() + "\"";
    inputMap.put(inputLabel, textField);
    errMsgMap.put(inputLabel, errMessage);
  }

  // adds input with passed in JTextField (e.g. FinalProject.Controllers.DateInput)
  // .getText() should return "" if input is invalid
  // returning "" will display its respective error message
  public void addInput(String label, JTextField textField, String errMessage) {
    JLabel inputLabel = new JLabel(label);
    inputMap.put(inputLabel, textField);
    errMsgMap.put(inputLabel, errMessage);
  }

  public Map<String, String> showModal() {
    initModal();
    setVisible(true);
    return generateValueMap();
  }

  private void hideModal() { setVisible(false); }

  // initializes all content in modal
  private void initModal() {
    JPanel layout = new JPanel(new BorderLayout());
    JPanel content = createInputsPanel();
    JPanel buttons = createButtonsPanel();
    layout.add(content, BorderLayout.NORTH);
    layout.add(buttons, BorderLayout.SOUTH);
    add(layout);
    pack();
    setLocationRelativeTo(this.frame);
  }

  // creates JPanel with all added inputs
  private JPanel createInputsPanel() {
    JPanel content = new JPanel();
    GroupLayout contentLayout = new GroupLayout(content);
    
    GroupLayout.SequentialGroup horizontal = contentLayout.createSequentialGroup();
    GroupLayout.ParallelGroup labelGroup = contentLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
    GroupLayout.ParallelGroup textFieldGroup = contentLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
    inputMap.keySet().forEach(labelGroup::addComponent);
    inputMap.values().forEach(textFieldGroup::addComponent);
    horizontal.addGroup(labelGroup).addGroup(textFieldGroup);

    GroupLayout.SequentialGroup vertical = contentLayout.createSequentialGroup();
    inputMap.forEach((key, value) ->
      vertical.addGroup(contentLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        .addComponent(key)
        .addComponent(value)));
    
    contentLayout.setHorizontalGroup(horizontal);
    contentLayout.setVerticalGroup(vertical);
    contentLayout.setAutoCreateGaps(true);
    contentLayout.setAutoCreateContainerGaps(true);
    content.setLayout(contentLayout);
    return content;
  }

  // creates JPanel with cancel and submit buttons
  private JPanel createButtonsPanel() {
    JPanel buttons = new JPanel(new BorderLayout());
    JButton cancelBtn = createCancelBtn();
    JButton submitBtn = createSubmitBtn();

    buttons.add(cancelBtn, BorderLayout.WEST);
    buttons.add(submitBtn, BorderLayout.EAST);
    return buttons;
  }

  private JButton createCancelBtn() {
    clickedCancel = false;
    JButton cancelBtn = new JButton("Cancel");
    cancelBtn.addActionListener(e -> {
      clickedCancel = true;
      hideModal();
    });
    return cancelBtn;
  }

  private JButton createSubmitBtn() {
    JButton submitBtn = new JButton("Submit");
    submitBtn.addActionListener(e -> {
      String errorMsg = validateInput();
      if (!errorMsg.equals("")) JOptionPane.showMessageDialog(this, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
      else hideModal();
    });
    return submitBtn;
  }

  private Map<String, String> generateValueMap() {
    Map<String, String> valueMap = new HashMap<>();
    if (clickedCancel) return valueMap;
    inputMap.forEach((key, value) -> valueMap.put(key.getText(), value.getText().trim()));
    return valueMap;
  }

  private String validateInput() {
    StringBuilder errorMsg = new StringBuilder();
    for (Map.Entry<JLabel, JTextField> entry : inputMap.entrySet())
      if (entry.getValue().getText().trim().equals(""))
        errorMsg.append(errMsgMap.get(entry.getKey())).append("\n");
    return errorMsg.toString();
  }

  private void addClosingListener() {
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) { clickedCancel = true; }
    });
  }
}
