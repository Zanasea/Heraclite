package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import message.Message;

public class Server implements Runnable {
  ServerSocket serverSocket;
  boolean keepProcessing = true;

  public Server(int port) throws IOException {
    serverSocket = new ServerSocket(port);
  }

  @Override
  public void run() {
    while (keepProcessing) {
      try {
        Socket socket = serverSocket.accept();
        process(socket);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void process(Socket socket) {
    if (socket == null)
      return;
    try {
      Message message = CommunicationUtils.receiveMessage(socket);
      System.out.println("Server: got message: " + message);
      socket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void stop() {
    keepProcessing = false;
    try {
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
