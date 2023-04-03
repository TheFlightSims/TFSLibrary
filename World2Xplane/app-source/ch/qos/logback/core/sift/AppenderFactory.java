package ch.qos.logback.core.sift;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.joran.spi.JoranException;

public interface AppenderFactory<E> {
  Appender<E> buildAppender(Context paramContext, String paramString) throws JoranException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\sift\AppenderFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */