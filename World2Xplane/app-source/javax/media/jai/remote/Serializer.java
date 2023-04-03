package javax.media.jai.remote;

import java.awt.RenderingHints;

public interface Serializer {
  Class getSupportedClass();
  
  boolean permitsSubclasses();
  
  SerializableState getState(Object paramObject, RenderingHints paramRenderingHints);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\Serializer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */