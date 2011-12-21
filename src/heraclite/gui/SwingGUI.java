package heraclite.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SwingGUI extends JFrame implements GUI {

  private static final long serialVersionUID = 1L;

  @Override
  public void init() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(640, 480));
    this.setLocationByPlatform(true);

    FlowLayout mainFrameLayout = new FlowLayout();
    mainFrameLayout.setAlignment(FlowLayout.LEFT);
    this.setLayout(mainFrameLayout);

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

    JPanel amortissementPanel = new JPanel();
    amortissementPanel.setPreferredSize(new Dimension(500, 500));
    amortissementPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    JTable amortissement = new JTable(1, 2); // FUCK YOU JTABLE
    amortissement.setBorder(BorderFactory.createBevelBorder(0));
    amortissement.setValueAt("blah", 0, 0);
    amortissementPanel.add(amortissement);
    
    amortissement.setPreferredSize(new Dimension(100, 100));
    amortissement.setLocation(0, 0);

    this.add(folderInputPanel);
    this.add(amortissementPanel);
    this.add(amortissement);
    this.pack();
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

}
