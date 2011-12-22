package heraclite.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
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
    JPanel amortissementPanel = createAmortissementPanel();

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 0;     
    constraints.gridheight = 1;
    constraints.gridwidth = 1;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.NORTHWEST;
    constraints.weightx = 0.0;
    constraints.weighty = 0.0;   
    this.add(folderInputPanel, constraints);
    
    constraints = new GridBagConstraints();
    constraints.gridx = 1;
    constraints.gridy = 0;    
    constraints.gridheight = GridBagConstraints.REMAINDER;
    constraints.gridwidth = GridBagConstraints.REMAINDER;
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1.0;
    constraints.weighty = 1.0;
    this.add(amortissementPanel, constraints);
    this.pack();
  }
  
  private JPanel createFolderInputPanel() {
    JPanel folderInputPanel = new JPanel();
    folderInputPanel.setBorder(BorderFactory.createTitledBorder("Program Arguments"));
    folderInputPanel.setPreferredSize(new Dimension(260, 80));
    GridLayout folderInputLayout = new GridLayout(3, 2);
    folderInputPanel.setLayout(folderInputLayout);

    folderInputPanel.add(new JLabel("Input Folder:"));
    JTextField inputFolder = new JTextField();
    folderInputPanel.add(inputFolder);
    folderInputPanel.add(new JLabel("Json output folder:"));
    JTextField outputFolder = new JTextField();
    folderInputPanel.add(outputFolder);
    folderInputPanel.add(new JLabel("HTML output folder:"));
    JTextField htmlOutputFolder = new JTextField();
    folderInputPanel.add(htmlOutputFolder);
    return folderInputPanel;
  }
  
  private JPanel createAmortissementPanel() {
    JPanel amortissementPanel = new JPanel();
    amortissementPanel.setPreferredSize(new Dimension(500, 500));
    amortissementPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    GridLayout amortissementLayout = new GridLayout(1, 1);
    amortissementPanel.setLayout(amortissementLayout);

    JTable amortissement = new JTable(1, 2);
    amortissement.setBorder(BorderFactory.createBevelBorder(0));
    amortissement.setValueAt("blah", 0, 0);
    amortissementPanel.add(amortissement);
    
    amortissement.setPreferredSize(new Dimension(100, 100));
    amortissement.setLocation(0, 0);
    return amortissementPanel;
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

}
