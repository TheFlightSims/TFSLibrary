package ch.qos.logback.core.joran.conditional;

import ch.qos.logback.core.joran.event.SaxEvent;
import java.util.List;

class IfState {
  Boolean boolResult;
  
  List<SaxEvent> thenSaxEventList;
  
  List<SaxEvent> elseSaxEventList;
  
  boolean active;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\joran\conditional\IfState.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */