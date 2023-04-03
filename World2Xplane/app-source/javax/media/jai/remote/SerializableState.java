package javax.media.jai.remote;

import java.io.Serializable;

public interface SerializableState extends Serializable {
  Class getObjectClass();
  
  Object getObject();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\remote\SerializableState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */