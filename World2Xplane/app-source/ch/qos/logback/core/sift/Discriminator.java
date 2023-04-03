package ch.qos.logback.core.sift;

import ch.qos.logback.core.spi.LifeCycle;

public interface Discriminator<E> extends LifeCycle {
  String getDiscriminatingValue(E paramE);
  
  String getKey();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\sift\Discriminator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */