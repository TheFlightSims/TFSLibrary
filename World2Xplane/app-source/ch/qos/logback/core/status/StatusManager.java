package ch.qos.logback.core.status;

import java.util.List;

public interface StatusManager {
  void add(Status paramStatus);
  
  List<Status> getCopyOfStatusList();
  
  int getCount();
  
  void add(StatusListener paramStatusListener);
  
  void remove(StatusListener paramStatusListener);
  
  void clear();
  
  List<StatusListener> getCopyOfStatusListenerList();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\status\StatusManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */