package heraclite.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class SwingGUI extends JFrame implements GUI {

  private static final long serialVersionUID = 1L;

  @Override
  public void init() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(640, 480));
    this.setLocationByPlatform(true);
    this.pack();
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

}
