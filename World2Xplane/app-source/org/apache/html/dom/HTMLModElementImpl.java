package org.apache.html.dom;

import org.w3c.dom.html.HTMLModElement;

public class HTMLModElementImpl extends HTMLElementImpl implements HTMLModElement {
  private static final long serialVersionUID = 6424581972706750120L;
  
  public String getCite() {
    return getAttribute("cite");
  }
  
  public void setCite(String paramString) {
    setAttribute("cite", paramString);
  }
  
  public String getDateTime() {
    return getAttribute("datetime");
  }
  
  public void setDateTime(String paramString) {
    setAttribute("datetime", paramString);
  }
  
  public HTMLModElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLModElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */