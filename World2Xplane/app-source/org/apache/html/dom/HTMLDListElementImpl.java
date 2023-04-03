package org.apache.html.dom;

import org.w3c.dom.html.HTMLDListElement;

public class HTMLDListElementImpl extends HTMLElementImpl implements HTMLDListElement {
  private static final long serialVersionUID = -2130005642453038604L;
  
  public boolean getCompact() {
    return getBinary("compact");
  }
  
  public void setCompact(boolean paramBoolean) {
    setAttribute("compact", paramBoolean);
  }
  
  public HTMLDListElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLDListElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */