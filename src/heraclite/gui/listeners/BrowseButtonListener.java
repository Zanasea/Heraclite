package heraclite.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class BrowseButtonListener implements ActionListener {

  JTextField textField;
  
  public BrowseButtonListener(JTextField textField) {
    this.textField = textField;
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
    }
  }

}
