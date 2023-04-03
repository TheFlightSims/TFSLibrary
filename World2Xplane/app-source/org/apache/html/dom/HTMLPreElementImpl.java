package org.apache.html.dom;

import org.w3c.dom.html.HTMLPreElement;

public class HTMLPreElementImpl extends HTMLElementImpl implements HTMLPreElement {
  private static final long serialVersionUID = -4195360849946217644L;
  
  public int getWidth() {
    return getInteger(getAttribute("width"));
  }
  
  public void setWidth(int paramInt) {
    setAttribute("width", String.valueOf(paramInt));
  }
  
  public HTMLPreElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLPreElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */