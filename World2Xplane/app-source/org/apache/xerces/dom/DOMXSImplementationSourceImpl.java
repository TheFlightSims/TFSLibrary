package org.apache.xerces.dom;

import java.util.Vector;
import org.apache.xerces.impl.xs.XSImplementationImpl;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DOMImplementationList;

public class DOMXSImplementationSourceImpl extends DOMImplementationSourceImpl {
  public DOMImplementation getDOMImplementation(String paramString) {
    DOMImplementation dOMImplementation = super.getDOMImplementation(paramString);
    if (dOMImplementation != null)
      return dOMImplementation; 
    dOMImplementation = PSVIDOMImplementationImpl.getDOMImplementation();
    if (testImpl(dOMImplementation, paramString))
      return dOMImplementation; 
    dOMImplementation = XSImplementationImpl.getDOMImplementation();
    return testImpl(dOMImplementation, paramString) ? dOMImplementation : null;
  }
  
  public DOMImplementationList getDOMImplementationList(String paramString) {
    Vector vector = new Vector();
    DOMImplementationList dOMImplementationList = super.getDOMImplementationList(paramString);
    for (byte b = 0; b < dOMImplementationList.getLength(); b++)
      vector.addElement(dOMImplementationList.item(b)); 
    DOMImplementation dOMImplementation = PSVIDOMImplementationImpl.getDOMImplementation();
    if (testImpl(dOMImplementation, paramString))
      vector.addElement(dOMImplementation); 
    dOMImplementation = XSImplementationImpl.getDOMImplementation();
    if (testImpl(dOMImplementation, paramString))
      vector.addElement(dOMImplementation); 
    return new DOMImplementationListImpl(vector);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DOMXSImplementationSourceImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */