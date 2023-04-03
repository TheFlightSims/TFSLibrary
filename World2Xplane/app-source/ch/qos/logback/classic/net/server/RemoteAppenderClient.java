package ch.qos.logback.classic.net.server;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.net.server.Client;

interface RemoteAppenderClient extends Client {
  void setLoggerContext(LoggerContext paramLoggerContext);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\net\server\RemoteAppenderClient.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */