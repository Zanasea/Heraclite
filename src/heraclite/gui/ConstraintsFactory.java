package heraclite.gui;

import java.awt.GridBagConstraints;

public class ConstraintsFactory {


  public static GridBagConstraints createButtons() {
    GridBagConstraints constraints;
    constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridheight = 1;
    constraints.gridwidth = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.NORTH;
    constraints.weightx = 0.0;
    constraints.weighty = 0.0;
    return constraints;
  }

  public static GridBagConstraints createFolderInput() {
    GridBagConstraints constraints;
    constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.gridheight = 1;
    constraints.gridwidth = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.NORTHWEST;
    constraints.weightx = 0.0;
    constraints.weighty = 0.0;
    return constraints;
  }

  public static GridBagConstraints createAmortissement() {
    GridBagConstraints constraints;
    constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridheight = GridBagConstraints.REMAINDER;
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1.0;
    constraints.weighty = 1.0;
    return constraints;
  }

  public static GridBagConstraints createInputLabel() {
    GridBagConstraints constraints;
    constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.anchor = GridBagConstraints.WEST;
    return constraints;
  }
  
  public static GridBagConstraints createOutputLabel() {
    GridBagConstraints constraints;
    constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.anchor = GridBagConstraints.WEST;
    return constraints;
  }
  
  public static GridBagConstraints createHtmlLabel() {
    GridBagConstraints constraints;
    constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.anchor = GridBagConstraints.WEST;
    return constraints;
  }

  public static GridBagConstraints createInputText() {
    GridBagConstraints constraints;
    constraints = new GridBagConstraints();
    constraints.gridx = 1;
    constraints.gridy = 0;
    constraints.weightx = 1.0;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.WEST;
    return constraints;
  }
  
  public static GridBagConstraints createOutputText() {
    GridBagConstraints constraints;
    constraints = new GridBagConstraints();
    constraints.gridx = 1;
    constraints.gridy = 1;
    constraints.weightx = 1.0;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.WEST;
    return constraints;
  }
  
  public static GridBagConstraints createHtmlText() {
    GridBagConstraints constraints;
    constraints = new GridBagConstraints();
    constraints.gridx = 1;
    constraints.gridy = 2;
    constraints.weightx = 1.0;
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.anchor = GridBagConstraints.WEST;
    return constraints;
  }

  public static GridBagConstraints createInputButton() {
    GridBagConstraints constraints;
    constraints = new GridBagConstraints();
    constraints.gridx = 2;
    constraints.gridy = 0;
    constraints.anchor = GridBagConstraints.EAST;
    return constraints;
  }
  
  public static GridBagConstraints createOutputButton() {
    GridBagConstraints constraints;
    constraints = new GridBagConstraints();
    constraints.gridx = 2;
    constraints.gridy = 1;
    constraints.anchor = GridBagConstraints.EAST;
    return constraints;
  }
  
  public static GridBagConstraints createHtmlButton() {
    GridBagConstraints constraints;
    constraints = new GridBagConstraints();
    constraints.gridx = 2;
    constraints.gridy = 2;
    constraints.anchor = GridBagConstraints.EAST;
    return constraints;
  }
}
