package heraclite;

import heraclite.calculator.Calculator;
import heraclite.calculator.JSONFileManager;
import heraclite.calculator.PersistanceManager;
import heraclite.calculator.SourceReader;
import heraclite.gui.GUI;
import heraclite.gui.SwingGUI;

import java.io.File;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

  public static void main(String[] args) throws Exception {
    if (ArgsValidator.isNoGUIValid(args)) {
      Calculator.calculate(args);
    } else {
      GUI gui = new SwingGUI();
      gui.init();
      gui.display();

      ServerSocket server = new ServerSocket(4444);
      while (true) {
        Socket socket = server.accept();
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println("CONNECT TOI A VENTRILO");
        writer.close();
        socket.close();
      }
    }
  }
}
