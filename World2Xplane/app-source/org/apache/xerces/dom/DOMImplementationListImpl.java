package org.apache.xerces.dom;

import java.util.Vector;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DOMImplementationList;

public class DOMImplementationListImpl implements DOMImplementationList {
  private Vector fImplementations = new Vector();
  
  public DOMImplementationListImpl() {}
  
  public DOMImplementationListImpl(Vector paramVector) {}
  
  public DOMImplementation item(int paramInt) {
    try {
      return this.fImplementations.elementAt(paramInt);
    } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
      return null;
    } 
  }
  
  public int getLength() {
    return this.fImplementations.size();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DOMImplementationListImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */