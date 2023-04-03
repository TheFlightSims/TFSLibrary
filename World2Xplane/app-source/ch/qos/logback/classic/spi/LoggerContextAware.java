package ch.qos.logback.classic.spi;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.spi.ContextAware;

public interface LoggerContextAware extends ContextAware {
  void setLoggerContext(LoggerContext paramLoggerContext);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\spi\LoggerContextAware.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */