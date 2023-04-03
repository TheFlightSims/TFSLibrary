package org.apache.html.dom;

import org.w3c.dom.html.HTMLTableColElement;

public class HTMLTableColElementImpl extends HTMLElementImpl implements HTMLTableColElement {
  private static final long serialVersionUID = -6189626162811911792L;
  
  public String getAlign() {
    return capitalize(getAttribute("align"));
  }
  
  public void setAlign(String paramString) {
    setAttribute("align", paramString);
  }
  
  public String getCh() {
    String str = getAttribute("char");
    if (str != null && str.length() > 1)
      str = str.substring(0, 1); 
    return str;
  }
  
  public void setCh(String paramString) {
    if (paramString != null && paramString.length() > 1)
      paramString = paramString.substring(0, 1); 
    setAttribute("char", paramString);
  }
  
  public String getChOff() {
    return getAttribute("charoff");
  }
  
  public void setChOff(String paramString) {
    setAttribute("charoff", paramString);
  }
  
  public int getSpan() {
    return getInteger(getAttribute("span"));
  }
  
  public void setSpan(int paramInt) {
    setAttribute("span", String.valueOf(paramInt));
  }
  
  public String getVAlign() {
    return capitalize(getAttribute("valign"));
  }
  
  public void setVAlign(String paramString) {
    setAttribute("valign", paramString);
  }
  
  public String getWidth() {
    return getAttribute("width");
  }
  
  public void setWidth(String paramString) {
    setAttribute("width", paramString);
  }
  
  public HTMLTableColElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLTableColElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */