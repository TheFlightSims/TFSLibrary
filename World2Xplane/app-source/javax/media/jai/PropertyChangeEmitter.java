package javax.media.jai;

import java.beans.PropertyChangeListener;

public interface PropertyChangeEmitter {
  void addPropertyChangeListener(PropertyChangeListener paramPropertyChangeListener);
  
  void addPropertyChangeListener(String paramString, PropertyChangeListener paramPropertyChangeListener);
  
  void removePropertyChangeListener(PropertyChangeListener paramPropertyChangeListener);
  
  void removePropertyChangeListener(String paramString, PropertyChangeListener paramPropertyChangeListener);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\PropertyChangeEmitter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */