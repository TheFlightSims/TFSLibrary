package ch.qos.logback.core.net.server;

import java.io.Closeable;

public interface Client extends Runnable, Closeable {
  void close();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\net\server\Client.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */