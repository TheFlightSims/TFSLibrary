package ch.qos.logback.core.spi;

import java.io.Serializable;

public interface PreSerializationTransformer<E> {
  Serializable transform(E paramE);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\core\spi\PreSerializationTransformer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */