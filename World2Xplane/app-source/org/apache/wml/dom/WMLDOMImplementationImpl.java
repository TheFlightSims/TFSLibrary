package org.apache.wml.dom;

import org.apache.wml.WMLDOMImplementation;
import org.apache.xerces.dom.DOMImplementationImpl;
import org.apache.xerces.dom.DOMMessageFormatter;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

public class WMLDOMImplementationImpl extends DOMImplementationImpl implements WMLDOMImplementation {
  static final DOMImplementationImpl singleton = new WMLDOMImplementationImpl();
  
  public static DOMImplementation getDOMImplementation() {
    return (DOMImplementation)singleton;
  }
  
  public Document createDocument(String paramString1, String paramString2, DocumentType paramDocumentType) throws DOMException {
    if (paramDocumentType != null && paramDocumentType.getOwnerDocument() != null)
      throw new DOMException((short)4, DOMMessageFormatter.formatMessage("http://www.w3.org/TR/1998/REC-xml-19980210", "WRONG_DOCUMENT_ERR", null)); 
    WMLDocumentImpl wMLDocumentImpl = new WMLDocumentImpl(paramDocumentType);
    if (paramString2 != null || paramString1 != null) {
      Element element = wMLDocumentImpl.createElementNS(paramString1, paramString2);
      wMLDocumentImpl.appendChild(element);
    } 
    return (Document)wMLDocumentImpl;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\wml\dom\WMLDOMImplementationImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */