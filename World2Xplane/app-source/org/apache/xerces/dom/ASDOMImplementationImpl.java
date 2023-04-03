package org.apache.xerces.dom;

import org.apache.xerces.dom3.as.ASModel;
import org.apache.xerces.dom3.as.DOMASBuilder;
import org.apache.xerces.dom3.as.DOMASWriter;
import org.apache.xerces.dom3.as.DOMImplementationAS;
import org.apache.xerces.parsers.DOMASBuilderImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;

public class ASDOMImplementationImpl extends DOMImplementationImpl implements DOMImplementationAS {
  static ASDOMImplementationImpl singleton = new ASDOMImplementationImpl();
  
  public static DOMImplementation getDOMImplementation() {
    return singleton;
  }
  
  public ASModel createAS(boolean paramBoolean) {
    return new ASModelImpl(paramBoolean);
  }
  
  public DOMASBuilder createDOMASBuilder() {
    return (DOMASBuilder)new DOMASBuilderImpl();
  }
  
  public DOMASWriter createDOMASWriter() {
    String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
    throw new DOMException((short)9, str);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\ASDOMImplementationImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */