package ch.qos.logback.core.encoder;

import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.LifeCycle;
import java.io.IOException;
import java.io.OutputStream;

public interface Encoder<E> extends ContextAware, LifeCycle {
  void init(OutputStream paramOutputStream) throws IOException;
  
  void doEncode(E paramE) throws IOException;
  
  void close() throws IOException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\encoder\Encoder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */