package org.apache.html.dom;

import org.w3c.dom.html.HTMLBodyElement;

public class HTMLBodyElementImpl extends HTMLElementImpl implements HTMLBodyElement {
  private static final long serialVersionUID = 9058852459426595202L;
  
  public String getALink() {
    return getAttribute("alink");
  }
  
  public void setALink(String paramString) {
    setAttribute("alink", paramString);
  }
  
  public String getBackground() {
    return getAttribute("background");
  }
  
  public void setBackground(String paramString) {
    setAttribute("background", paramString);
  }
  
  public String getBgColor() {
    return getAttribute("bgcolor");
  }
  
  public void setBgColor(String paramString) {
    setAttribute("bgcolor", paramString);
  }
  
  public String getLink() {
    return getAttribute("link");
  }
  
  public void setLink(String paramString) {
    setAttribute("link", paramString);
  }
  
  public String getText() {
    return getAttribute("text");
  }
  
  public void setText(String paramString) {
    setAttribute("text", paramString);
  }
  
  public String getVLink() {
    return getAttribute("vlink");
  }
  
  public void setVLink(String paramString) {
    setAttribute("vlink", paramString);
  }
  
  public HTMLBodyElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLBodyElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */