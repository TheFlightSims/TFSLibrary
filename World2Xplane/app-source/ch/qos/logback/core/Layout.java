package ch.qos.logback.core;

import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.LifeCycle;

public interface Layout<E> extends ContextAware, LifeCycle {
  String doLayout(E paramE);
  
  String getFileHeader();
  
  String getPresentationHeader();
  
  String getPresentationFooter();
  
  String getFileFooter();
  
  String getContentType();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\Layout.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */