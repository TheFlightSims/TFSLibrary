package ch.qos.logback.core.rolling.helper;

import ch.qos.logback.core.spi.ContextAware;
import java.util.Date;

public interface ArchiveRemover extends ContextAware {
  void clean(Date paramDate);
  
  void setMaxHistory(int paramInt);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\rolling\helper\ArchiveRemover.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */