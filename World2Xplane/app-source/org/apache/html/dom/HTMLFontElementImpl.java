package org.apache.html.dom;

import org.w3c.dom.html.HTMLFontElement;

public class HTMLFontElementImpl extends HTMLElementImpl implements HTMLFontElement {
  private static final long serialVersionUID = -415914342045846318L;
  
  public String getColor() {
    return capitalize(getAttribute("color"));
  }
  
  public void setColor(String paramString) {
    setAttribute("color", paramString);
  }
  
  public String getFace() {
    return capitalize(getAttribute("face"));
  }
  
  public void setFace(String paramString) {
    setAttribute("face", paramString);
  }
  
  public String getSize() {
    return getAttribute("size");
  }
  
  public void setSize(String paramString) {
    setAttribute("size", paramString);
  }
  
  public HTMLFontElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLFontElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */