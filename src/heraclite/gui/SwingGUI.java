package heraclite.gui;

import heraclite.calculator.Calculator;
import heraclite.dto.Amortissement;
import heraclite.dto.Extrant;
import heraclite.gui.amortissement.AmortissementHeader;
import heraclite.gui.amortissement.AmortissementTable;
import heraclite.gui.listeners.BrowseButtonListener;
import heraclite.gui.listeners.ConnectButtonListener;
import heraclite.gui.listeners.RunButtonListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SwingGUI extends JFrame implements GUI {

  private static final long serialVersionUID = 1L;
  private AmortissementTable table;

  @Override
  public void init() {
    Calculator.registerGUI(this);
    setUpFrameParameteres();
    addFoldersSelectionFields();
    addAmortissementTablePanel();
    addControlButtonsPanel();

    this.pack();
  }

  private void setUpFrameParameteres() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(640, 480));
    this.setLocationByPlatform(true);
    this.setLayout(new GridBagLayout());
  }

  private void addFoldersSelectionFields() {
    JPanel folderInputPanel = createFoldersSelectionFields();
    GridBagConstraints constraints = ConstraintsFactory.createFolderInput();
    this.add(folderInputPanel, constraints);
  }

  private JPanel createFoldersSelectionFields() {
    JPanel panel = createFoldersInputsPanel();
    addInputFolder(panel);
    addOutputFolder(panel);
    addHtmlOutputFolder(panel);
    return panel;
  }

  private JPanel createFoldersInputsPanel() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createTitledBorder("Program Arguments"));
    panel.setPreferredSize(new Dimension(400, 80));
    GridBagLayout folderInputLayout = new GridBagLayout();
    panel.setLayout(folderInputLayout);
    panel.setName("folderInputPanel");
    return panel;
  }

  private void addInputFolder(JPanel panel) {
    JLabel inputLabel = new JLabel("Input Folder:");
    inputLabel.setName("inputLabel");
    panel.add(inputLabel, ConstraintsFactory.createInputLabel());
    JTextField inputFolder = new JTextField("resources");
    inputFolder.setName("inputFolder");
    panel.add(inputFolder, ConstraintsFactory.createInputText());
    JButton inputButton = new JButton("Parcourir");
    inputButton.setName("inputButton");
    inputButton.addActionListener(new BrowseButtonListener(inputFolder));
    panel.add(inputButton, ConstraintsFactory.createInputButton());
  }

  private void addOutputFolder(JPanel panel) {
    JLabel outputLabel = new JLabel("Json output folder:");
    outputLabel.setName("outputLabel");
    panel.add(outputLabel, ConstraintsFactory.createOutputLabel());
    JTextField outputFolder = new JTextField("results");
    outputFolder.setName("outputFolder");
    panel.add(outputFolder, ConstraintsFactory.createOutputText());
    JButton outputButton = new JButton("Parcourir");
    outputButton.setName("outputButton");
    outputButton.addActionListener(new BrowseButtonListener(outputFolder));
    panel.add(outputButton, ConstraintsFactory.createOutputButton());
  }

  private void addHtmlOutputFolder(JPanel panel) {
    JLabel htmlLabel = new JLabel("HTML output folder:");
    htmlLabel.setName("htmlLabel");
    panel.add(htmlLabel, ConstraintsFactory.createHtmlLabel());
    JTextField htmlOutputFolder = new JTextField("htmlResults");
    htmlOutputFolder.setName("htmlOutputFolder");
    panel.add(htmlOutputFolder, ConstraintsFactory.createHtmlText());
    JButton htmlButton = new JButton("Parcourir");
    htmlButton.setName("htmlButton");
    htmlButton.addActionListener(new BrowseButtonListener(htmlOutputFolder));
    panel.add(htmlButton, ConstraintsFactory.createHtmlButton());
  }

  private void addAmortissementTablePanel() {
    JPanel amortissementPanel = createAmortissementPanel();
    amortissementPanel.add(createAmortissementTable());
    this.add(amortissementPanel, ConstraintsFactory.createAmortissement());
  }

  private JPanel createAmortissementPanel() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    panel.setLayout(new GridLayout(1, 1));
    return panel;
  }

  private JScrollPane createAmortissementTable() {
    table = new AmortissementTable(0, AmortissementHeader.values().length);
    setAmortissementTableHeaderValues();
    table.setBorder(BorderFactory.createBevelBorder(0));
    return new JScrollPane(table);
  }

  private void setAmortissementTableHeaderValues() {
    for (int i = 0; i < table.getColumnCount(); i++) {
      table.getColumnModel().getColumn(i).setHeaderValue(AmortissementHeader.values()[i]);
    }
  }

  private void addControlButtonsPanel() {
    JPanel buttonsPanel = createButtonsPanel();
    addControlButtons(buttonsPanel);
    this.add(buttonsPanel, ConstraintsFactory.createButtons());
  }

  private JPanel createButtonsPanel() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createTitledBorder("Buttons"));
    panel.setPreferredSize(new Dimension(260, 80));
    panel.setLayout(new GridLayout(2, 3));
    return panel;
  }

  private void addControlButtons(JPanel panel) {
    addClearTableButton(panel);
    addRunButton(panel);
    JButton connect = addConnectButton(panel);
    panel.add(connect);
  }

  private void addClearTableButton(JPanel panel) {
    JButton clearTable = new JButton("Clear Table");
    clearTable.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        table.clearData();
      }
    });
    panel.add(clearTable);
  }

  private void addRunButton(JPanel panel) {
    JButton run = new JButton("Run");
    run.addActionListener(new RunButtonListener(this.getContentPane()));
    panel.add(run);
  }

  private JButton addConnectButton(JPanel panel) {
    final JTextField ip = new JTextField("127.0.0.1");
    panel.add(ip);
    JButton connect = new JButton("Connect");
    connect.addActionListener(new ConnectButtonListener(ip));
    return connect;
  }

  @Override
  public void display() {
    this.setVisible(true);
  }
  
  synchronized public void addExtrant(Extrant extrant) {
    DefaultTableModel model = (DefaultTableModel)table.getModel();
    for (Amortissement ammortissement : extrant.getAmortissement()) {
      String[] rowData = ammortissement.toArray();
      model.addRow(rowData);
    }
  }

}
