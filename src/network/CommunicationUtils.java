package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CommunicationUtils {
  public static void sendMessage(Socket socket, Object message) throws IOException {
    OutputStream stream = socket.getOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(stream);
    oos.writeObject(message);
    oos.flush();
  }

  public static Object receiveMessage(Socket socket) throws IOException, ClassNotFoundException {
    InputStream stream = socket.getInputStream();
    ObjectInputStream ois = new ObjectInputStream(stream);
    return ois.readObject();
  }
}
