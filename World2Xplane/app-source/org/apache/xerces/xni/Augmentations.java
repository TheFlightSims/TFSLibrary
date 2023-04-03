package org.apache.xerces.xni;

import java.util.Enumeration;

public interface Augmentations {
  Object putItem(String paramString, Object paramObject);
  
  Object getItem(String paramString);
  
  Object removeItem(String paramString);
  
  Enumeration keys();
  
  void removeAllItems();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\Augmentations.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */