package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Connection {
  public static final int PORT_NUMBER = 4444;

  private ConnectionInformations connectionInformations;
  private Socket socket;

  public Connection(ConnectionInformations connectionInformations) {
    this.connectionInformations = connectionInformations;
    socket = new Socket();
  }

  public void connect() {
    try {
      socket = new Socket(InetAddress.getByName(connectionInformations.getIpAddress()), PORT_NUMBER);
    } catch (IOException e) {
      System.err.println("Impossible d'établir la connexion: " + e.getMessage());
    }
  }

  public void disconnect() {
    try {
      socket.close();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  public Socket getSocket() {
    return socket;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }
}
