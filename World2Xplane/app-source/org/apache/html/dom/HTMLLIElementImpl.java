package org.apache.html.dom;

import org.w3c.dom.html.HTMLLIElement;

public class HTMLLIElementImpl extends HTMLElementImpl implements HTMLLIElement {
  private static final long serialVersionUID = -8987309345926701831L;
  
  public String getType() {
    return getAttribute("type");
  }
  
  public void setType(String paramString) {
    setAttribute("type", paramString);
  }
  
  public int getValue() {
    return getInteger(getAttribute("value"));
  }
  
  public void setValue(int paramInt) {
    setAttribute("value", String.valueOf(paramInt));
  }
  
  public HTMLLIElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLLIElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */