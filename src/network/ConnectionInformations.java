package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectionInformations implements Connection {
  private static final int PORT_NUMBER = 4444;

  private String ipAddress;
  private String name;
  private Socket connection;

  public ConnectionInformations(String name, String ipAddress) {
    this.ipAddress = ipAddress;
    this.name = name;
    connection = new Socket();
  }

  @Override
  public void connect() {
    try {
      connection = new Socket(InetAddress.getByName(ipAddress), PORT_NUMBER);
    } catch (IOException e) {
      System.err.println("Impossible d'établir la connexion: " + e.getMessage());
    }
  }

  @Override
  public void disconnect() {
    try {
      connection.close();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Socket getConnection() {
    return connection;
  }

  public void setConnection(Socket connection) {
    this.connection = connection;
  }
}
