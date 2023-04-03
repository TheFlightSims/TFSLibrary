package org.apache.html.dom;

import org.w3c.dom.html.HTMLHRElement;

public class HTMLHRElementImpl extends HTMLElementImpl implements HTMLHRElement {
  private static final long serialVersionUID = -4210053417678939270L;
  
  public String getAlign() {
    return capitalize(getAttribute("align"));
  }
  
  public void setAlign(String paramString) {
    setAttribute("align", paramString);
  }
  
  public boolean getNoShade() {
    return getBinary("noshade");
  }
  
  public void setNoShade(boolean paramBoolean) {
    setAttribute("noshade", paramBoolean);
  }
  
  public String getSize() {
    return getAttribute("size");
  }
  
  public void setSize(String paramString) {
    setAttribute("size", paramString);
  }
  
  public String getWidth() {
    return getAttribute("width");
  }
  
  public void setWidth(String paramString) {
    setAttribute("width", paramString);
  }
  
  public HTMLHRElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLHRElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */