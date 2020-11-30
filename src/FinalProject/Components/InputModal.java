package FinalProject.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Used to created popup modals that takes inputs from users,
 * it includes input validation per input field and
 * returns a map containing the users inputs upon cancel, exit, or submission.
 */
public class InputModal extends JDialog {
  private Map<JLabel, JTextField> inputMap;
  private Map<JLabel, String> errMsgMap;
  private boolean clickedCancel;
  private JFrame frame;

  /**
   * Constructor that calls the constructor of JDialog and
   * instantiates various data structures used for the dynamic creation of a modal's content.
   *
   * @param frame that the JDialog is attached to.
   * @param name of the JDialog popup window.
   */
  public InputModal(JFrame frame, String name) {
    super(frame, name, true);
    this.frame = frame;
    inputMap = new LinkedHashMap<>();
    errMsgMap = new HashMap<>();
    addClosingListener();
  }

  /**
   * Adds a normal JTextField to the modal that
   * prevents submission if the textfield is left empty.
   *
   * @param label indicating the name of the textfield.
   */
  public void addInput(String label) {
    JLabel inputLabel = new JLabel(label);
    JTextField textField = new JTextField();
    String errMessage = "Please enter a value for \"" + inputLabel.getText() + "\"";
    inputMap.put(inputLabel, textField);
    errMsgMap.put(inputLabel, errMessage);
  }

  /**
   * Adds a passed in JTextField to the modal;
   * the passed in JTextField must have an overridden .getText() method that
   * returns "" if the input is invalid in which case
   * the modal will display the passed in error message.
   *
   * @param label indicating the name of the textfield.
   * @param textField that is a JTextField with an overridden .getText() method.
   * @param errMessage displayed if the input is invalid.
   */
  public void addInput(String label, JTextField textField, String errMessage) {
    JLabel inputLabel = new JLabel(label);
    inputMap.put(inputLabel, textField);
    errMsgMap.put(inputLabel, errMessage);
  }

  /**
   * Initializes modal with its content and makes it visible.
   *
   * @return Map where the key is the textfield label and the value is the textfield value,
   * if the modal is cancelled or exited, an empty map is returned.
   */
  public Map<String, String> showModal() {
    initModal();
    setVisible(true);
    return generateValueMap();
  }

  /**
   * Hides the visibility of the modal allowing execution of code after making the modal visible in showModal().
   */
  private void hideModal() { setVisible(false); }

  /**
   * Creates a JPanel to hold the textfields, labels, and the cancel/submit buttons.
   */
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

  /**
   * Creates a JPanel using GroupLayout to set the textfield labels and textfields adjacent to each other
   * where each row includes a passed in label and textfield.
   *
   * @return JPanel including the passed in textfields and labels.
   */
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

  /**
   * Creates a JPanel where the Cancel and Submit buttons are placed in the
   * bottom left and right corners of the modal.
   *
   * @return JPanel including the Cancel and Submit buttons.
   */
  private JPanel createButtonsPanel() {
    JPanel buttons = new JPanel(new BorderLayout());
    JButton cancelBtn = createCancelBtn();
    JButton submitBtn = createSubmitBtn();

    buttons.add(cancelBtn, BorderLayout.WEST);
    buttons.add(submitBtn, BorderLayout.EAST);
    return buttons;
  }

  /**
   * Creates a JButton and attaches an action listener that closes the modal upon click.
   *
   * @return JButton with Cancel button.
   */
  private JButton createCancelBtn() {
    clickedCancel = false;
    JButton cancelBtn = new JButton("Cancel");
    cancelBtn.addActionListener(e -> {
      clickedCancel = true;
      hideModal();
    });
    return cancelBtn;
  }

  /**
   * Creates a JButton and attaches an action listener that validates each textfield input,
   * if a textfield has an error, a message dialog will popup and prevent closing of the modal,
   * if all textfields are valid, the modal will be closed.
   *
   * @return JButton with Submit button.
   */
  private JButton createSubmitBtn() {
    JButton submitBtn = new JButton("Submit");
    submitBtn.addActionListener(e -> {
      String errorMsg = validateInput();
      if (!errorMsg.equals("")) JOptionPane.showMessageDialog(this, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
      else hideModal();
    });
    return submitBtn;
  }

  /**
   * Retrieves all inputted values from the textfields and generates a map,
   * if the modal was exited or the cancel button was pressed, an empty map is returned
   *
   * @return Map including the textfield labels (key) and the textfield input (value)
   */
  private Map<String, String> generateValueMap() {
    Map<String, String> valueMap = new HashMap<>();
    if (clickedCancel) return valueMap;
    inputMap.forEach((key, value) -> valueMap.put(key.getText(), value.getText().trim()));
    return valueMap;
  }

  /**
   * Calls the getText() method on each textfield,
   * if "" is returned, the input is considered invalid and the textfield's respective error message
   * is concatenated to error messages generated by other textfields.
   *
   * @return String with all errors generated by invalid textfield inputs.
   */
  private String validateInput() {
    StringBuilder errorMsg = new StringBuilder();
    for (Map.Entry<JLabel, JTextField> entry : inputMap.entrySet())
      if (entry.getValue().getText().trim().equals(""))
        errorMsg.append(errMsgMap.get(entry.getKey())).append("\n");
    return errorMsg.toString();
  }

  /**
   * Adds a window listener that is called when the exit button is clicked on the modal window;
   * this is needed to ensure that an empty map is returned by the generateValueMap() function.
   */
  private void addClosingListener() {
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) { clickedCancel = true; }
    });
  }
}
