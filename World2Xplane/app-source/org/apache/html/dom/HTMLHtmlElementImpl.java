package org.apache.html.dom;

import org.w3c.dom.html.HTMLHtmlElement;

public class HTMLHtmlElementImpl extends HTMLElementImpl implements HTMLHtmlElement {
  private static final long serialVersionUID = -4489734201536616166L;
  
  public String getVersion() {
    return capitalize(getAttribute("version"));
  }
  
  public void setVersion(String paramString) {
    setAttribute("version", paramString);
  }
  
  public HTMLHtmlElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLHtmlElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */