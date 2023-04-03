package ch.qos.logback.core.net.server;

import ch.qos.logback.core.spi.ContextAware;
import java.io.IOException;

public interface ServerRunner<T extends Client> extends ContextAware, Runnable {
  boolean isRunning();
  
  void stop() throws IOException;
  
  void accept(ClientVisitor<T> paramClientVisitor);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\net\server\ServerRunner.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */