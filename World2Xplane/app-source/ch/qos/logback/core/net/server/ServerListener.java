package ch.qos.logback.core.net.server;

import java.io.Closeable;
import java.io.IOException;

public interface ServerListener<T extends Client> extends Closeable {
  T acceptClient() throws IOException, InterruptedException;
  
  void close();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\net\server\ServerListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */