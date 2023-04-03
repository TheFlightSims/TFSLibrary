package org.apache.html.dom;

import org.w3c.dom.html.HTMLMetaElement;

public class HTMLMetaElementImpl extends HTMLElementImpl implements HTMLMetaElement {
  private static final long serialVersionUID = -2401961905874264272L;
  
  public String getContent() {
    return getAttribute("content");
  }
  
  public void setContent(String paramString) {
    setAttribute("content", paramString);
  }
  
  public String getHttpEquiv() {
    return getAttribute("http-equiv");
  }
  
  public void setHttpEquiv(String paramString) {
    setAttribute("http-equiv", paramString);
  }
  
  public String getName() {
    return getAttribute("name");
  }
  
  public void setName(String paramString) {
    setAttribute("name", paramString);
  }
  
  public String getScheme() {
    return getAttribute("scheme");
  }
  
  public void setScheme(String paramString) {
    setAttribute("scheme", paramString);
  }
  
  public HTMLMetaElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLMetaElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */