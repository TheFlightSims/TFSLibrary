package org.apache.xerces.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

public class PSVIDOMImplementationImpl extends CoreDOMImplementationImpl {
  static PSVIDOMImplementationImpl singleton = new PSVIDOMImplementationImpl();
  
  public static DOMImplementation getDOMImplementation() {
    return singleton;
  }
  
  public boolean hasFeature(String paramString1, String paramString2) {
    return (super.hasFeature(paramString1, paramString2) || paramString1.equalsIgnoreCase("psvi"));
  }
  
  public Document createDocument(String paramString1, String paramString2, DocumentType paramDocumentType) throws DOMException {
    if (paramDocumentType != null && paramDocumentType.getOwnerDocument() != null)
      throw new DOMException((short)4, DOMMessageFormatter.formatMessage("http://www.w3.org/TR/1998/REC-xml-19980210", "WRONG_DOCUMENT_ERR", null)); 
    PSVIDocumentImpl pSVIDocumentImpl = new PSVIDocumentImpl(paramDocumentType);
    if (paramString2 != null || paramString1 != null) {
      Element element = pSVIDocumentImpl.createElementNS(paramString1, paramString2);
      pSVIDocumentImpl.appendChild(element);
    } 
    return pSVIDocumentImpl;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\PSVIDOMImplementationImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */