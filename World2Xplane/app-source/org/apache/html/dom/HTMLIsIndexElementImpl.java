package org.apache.html.dom;

import org.w3c.dom.html.HTMLIsIndexElement;

public class HTMLIsIndexElementImpl extends HTMLElementImpl implements HTMLIsIndexElement {
  private static final long serialVersionUID = 3073521742049689699L;
  
  public String getPrompt() {
    return getAttribute("prompt");
  }
  
  public void setPrompt(String paramString) {
    setAttribute("prompt", paramString);
  }
  
  public HTMLIsIndexElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLIsIndexElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */