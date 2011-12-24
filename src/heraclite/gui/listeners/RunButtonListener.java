package heraclite.gui.listeners;

import heraclite.calculator.Calculator;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class RunButtonListener implements ActionListener {

  Container container;

  public RunButtonListener(Container container) {
    this.container = container;
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
    Container inputPanel = getFolderInputPanel();
    String[] args = new String[3];
    for (Component c : inputPanel.getComponents()) {
      switch (c.getName()) {
      case "inputFolder" :
        args[0] = ((JTextField)c).getText();
        break;
      case "outputFolder" :
        args[1] = ((JTextField)c).getText();
        break;
      case "htmlOutputFolder" :
        args[2] = ((JTextField)c).getText();
        break;
      }
    }
    Calculator.calculate(args);
  }

  private Container getFolderInputPanel() {
    for (Component c : container.getComponents()) {
      if (c.getName() != null  && c.getName().equals("folderInputPanel")) {
        return (Container) c;
      }
    }
    return null;
  }

}
