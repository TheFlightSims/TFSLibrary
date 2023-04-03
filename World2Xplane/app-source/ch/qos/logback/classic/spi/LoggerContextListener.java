package ch.qos.logback.classic.spi;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

public interface LoggerContextListener {
  boolean isResetResistant();
  
  void onStart(LoggerContext paramLoggerContext);
  
  void onReset(LoggerContext paramLoggerContext);
  
  void onStop(LoggerContext paramLoggerContext);
  
  void onLevelChange(Logger paramLogger, Level paramLevel);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\spi\LoggerContextListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */