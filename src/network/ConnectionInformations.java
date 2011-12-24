package network;

import java.io.Serializable;

public class ConnectionInformations implements Serializable {
  private static final long serialVersionUID = 497982241148511076L;
  
  private String ipAddress;
  private String name;
  

  public ConnectionInformations(String name, String ipAddress) {
    this.ipAddress = ipAddress;
    this.name = name;
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
}
