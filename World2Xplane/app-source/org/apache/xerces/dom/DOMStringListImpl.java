package org.apache.xerces.dom;

import java.util.Vector;
import org.w3c.dom.DOMStringList;

public class DOMStringListImpl implements DOMStringList {
  private Vector fStrings = new Vector();
  
  public DOMStringListImpl() {}
  
  public DOMStringListImpl(Vector paramVector) {}
  
  public String item(int paramInt) {
    try {
      return this.fStrings.elementAt(paramInt);
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return null;
    } 
  }
  
  public int getLength() {
    return this.fStrings.size();
  }
  
  public boolean contains(String paramString) {
    return this.fStrings.contains(paramString);
  }
  
  public void add(String paramString) {
    this.fStrings.add(paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DOMStringListImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */