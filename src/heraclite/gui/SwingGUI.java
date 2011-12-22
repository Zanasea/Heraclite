package heraclite.gui;

import heraclite.gui.amortissement.AmortissementHeader;
import heraclite.gui.amortissement.AmortissementTable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class SwingGUI extends JFrame implements GUI {

  private static final long serialVersionUID = 1L;

  @Override
  public void init() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(640, 480));
    this.setLocationByPlatform(true);

    GridBagLayout mainFrameLayout = new GridBagLayout();
    this.setLayout(mainFrameLayout);

    JPanel folderInputPanel = createFolderInputPanel();
    GridBagConstraints constraints = ConstraintsFactory.createFolderInput();
    this.add(folderInputPanel, constraints);
    
    JPanel amortissementPanel = createAmortissementPanel();
    constraints = ConstraintsFactory.createAmortissement();
    this.add(amortissementPanel, constraints);

    JPanel buttonsPanel = createButtonsPanel();
    constraints = ConstraintsFactory.createButtons();
    this.add(buttonsPanel, constraints);
    
    this.pack();
  }

  private JPanel createFolderInputPanel() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createTitledBorder("Program Arguments"));
    panel.setPreferredSize(new Dimension(400, 80));
    GridBagLayout folderInputLayout = new GridBagLayout();
    panel.setLayout(folderInputLayout);

    Dimension textFieldDimension = new Dimension(300, 20);
    
    panel.add(new JLabel("Input Folder:"), ConstraintsFactory.createInputLabel());
    JTextField inputFolder = new JTextField();
    inputFolder.setPreferredSize(textFieldDimension);
    panel.add(inputFolder, ConstraintsFactory.createInputText());
    JButton inputButton = new JButton("Parcourir");
    panel.add(inputButton,  ConstraintsFactory.createInputButton());
    
    panel.add(new JLabel("Json output folder:"),  ConstraintsFactory.createOutputLabel());
    JTextField outputFolder = new JTextField();
    outputFolder.setPreferredSize(textFieldDimension);
    panel.add(outputFolder, ConstraintsFactory.createOutputText());
    JButton outputButton = new JButton("Parcourir");
    panel.add(outputButton,  ConstraintsFactory.createOutputButton());
    
    panel.add(new JLabel("HTML output folder:"), ConstraintsFactory.createHtmlLabel());
    JTextField htmlOutputFolder = new JTextField();
    htmlOutputFolder.setPreferredSize(textFieldDimension);
    panel.add(htmlOutputFolder, ConstraintsFactory.createHtmlText());
    JButton htmlButton = new JButton("Parcourir");
    panel.add(htmlButton,  ConstraintsFactory.createHtmlButton());
    return panel;
  }

  private JPanel createButtonsPanel() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createTitledBorder("Buttons"));
    panel.setPreferredSize(new Dimension(260, 80));
    GridLayout folderInputLayout = new GridLayout(2, 3);
    panel.setLayout(folderInputLayout);

    JButton clearTable = new JButton("Clear Table");
    panel.add(clearTable);
    JButton run = new JButton("Run");
    panel.add(run);
    return panel;
  }
  

  private JPanel createAmortissementPanel() {
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(500, 500));
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    GridLayout amortissementLayout = new GridLayout(1, 1);
    panel.setLayout(amortissementLayout);

    AmortissementTable table = new AmortissementTable(1, AmortissementHeader.values().length);
    for (int i = 0; i < table.getColumnCount(); i++) {
      table.getColumnModel().getColumn(i).setHeaderValue(AmortissementHeader.values()[i]);
    }
    table.setBorder(BorderFactory.createBevelBorder(0));
    table.setValueAt("blah", 0, 0);

    JScrollPane scrollPane = new JScrollPane(table);

    panel.add(scrollPane);

    return panel;
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

}
