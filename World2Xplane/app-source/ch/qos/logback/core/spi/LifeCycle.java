package ch.qos.logback.core.spi;

public interface LifeCycle {
  void start();
  
  void stop();
  
  boolean isStarted();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\spi\LifeCycle.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */