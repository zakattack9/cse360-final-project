import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.HashMap;

public class InputModal extends JDialog {
  private final int MODAL_WIDTH = (int) (Main.WINDOW_WIDTH * 0.5);
  private final int MODAL_HEIGHT = (int) (Main.WINDOW_HEIGHT * 0.5);

  private Map<JLabel, JTextField> inputMap;
  private Map<JLabel, String> errMsgMap;
  private Map<String, String> valueMap;

  public InputModal(JFrame frame, String name) {
    super(frame, name, true);
    this.setSize(MODAL_WIDTH, MODAL_HEIGHT);
    inputMap = new HashMap<>();
    errMsgMap = new HashMap<>();
    valueMap = new HashMap<>();
  }

  // adds input with normal JTextField
  public void addInput(String label) {
    JLabel inputLabel = new JLabel(label);
    JTextField textField = new JTextField();
    String errMessage = "Please enter a value for \"" + inputLabel.getText() + "\"";
    inputMap.put(inputLabel, textField);
    errMsgMap.put(inputLabel, errMessage);
  }

  // adds input with passed in JTextField (e.g. DateInput)
  public void addInput(String label, JTextField textField, String errMessage) {
    JLabel inputLabel = new JLabel(label);
    inputMap.put(inputLabel, textField);
    errMsgMap.put(inputLabel, errMessage);
  }

  public Map<String, String> showModal() {
    initModal();
    this.setVisible(true);
    return valueMap;
  }

  public void hideModal() {
    this.setVisible(false);
  }

  // initializes all content in modal
  private void initModal() {
    JPanel layout = new JPanel(new BorderLayout());
    JPanel content = createInputsPanel();
    JPanel buttons = createButtonsPanel();
    layout.add(content, BorderLayout.NORTH);
    layout.add(buttons, BorderLayout.SOUTH);
    this.add(layout);
  }

  // creates JPanel with all added inputs
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
    JButton cancelBtn = new JButton("Cancel");
    cancelBtn.addActionListener(e -> hideModal());
    return cancelBtn;
  }

  private JButton createSubmitBtn() {
    JButton submitBtn = new JButton("Submit");
    submitBtn.addActionListener(e -> {
      String errorMsg = validateInput();
      if (errorMsg != "") {
        JOptionPane.showMessageDialog(this, errorMsg);
      } else {
        inputMap.entrySet().stream().forEach(entry -> {
          valueMap.put(entry.getKey().getText(), entry.getValue().getText());
        });
        hideModal();
      }
    });
    return submitBtn;
  }

  private String validateInput() {
    String errorMsg = "";
    for (Map.Entry<JLabel, JTextField> entry : inputMap.entrySet()) {
      if (entry.getValue().getText().equals("")) errorMsg += errMsgMap.get(entry.getKey()) + "\n";
    }
    return errorMsg;
  }
}
