package org.hsqldb.server;

import java.net.Socket;

public interface HsqlSocketRequestHandler {
  void handleConnection(Socket paramSocket);
  
  void signalCloseAllServerConnections();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\server\HsqlSocketRequestHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */