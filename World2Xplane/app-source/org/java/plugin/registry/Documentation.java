package org.java.plugin.registry;

import java.util.Collection;

public interface Documentation<T extends Identity> {
  String getCaption();
  
  String getText();
  
  Collection<Reference<T>> getReferences();
  
  T getDeclaringIdentity();
  
  public static interface Reference<E extends Identity> {
    String getRef();
    
    String getCaption();
    
    E getDeclaringIdentity();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\Documentation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */