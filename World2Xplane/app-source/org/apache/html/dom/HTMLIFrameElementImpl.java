package org.apache.html.dom;

import org.w3c.dom.html.HTMLIFrameElement;

public class HTMLIFrameElementImpl extends HTMLElementImpl implements HTMLIFrameElement {
  private static final long serialVersionUID = 2393622754706230429L;
  
  public String getAlign() {
    return capitalize(getAttribute("align"));
  }
  
  public void setAlign(String paramString) {
    setAttribute("align", paramString);
  }
  
  public String getFrameBorder() {
    return getAttribute("frameborder");
  }
  
  public void setFrameBorder(String paramString) {
    setAttribute("frameborder", paramString);
  }
  
  public String getHeight() {
    return getAttribute("height");
  }
  
  public void setHeight(String paramString) {
    setAttribute("height", paramString);
  }
  
  public String getLongDesc() {
    return getAttribute("longdesc");
  }
  
  public void setLongDesc(String paramString) {
    setAttribute("longdesc", paramString);
  }
  
  public String getMarginHeight() {
    return getAttribute("marginheight");
  }
  
  public void setMarginHeight(String paramString) {
    setAttribute("marginheight", paramString);
  }
  
  public String getMarginWidth() {
    return getAttribute("marginwidth");
  }
  
  public void setMarginWidth(String paramString) {
    setAttribute("marginwidth", paramString);
  }
  
  public String getName() {
    return getAttribute("name");
  }
  
  public void setName(String paramString) {
    setAttribute("name", paramString);
  }
  
  public String getScrolling() {
    return capitalize(getAttribute("scrolling"));
  }
  
  public void setScrolling(String paramString) {
    setAttribute("scrolling", paramString);
  }
  
  public String getSrc() {
    return getAttribute("src");
  }
  
  public void setSrc(String paramString) {
    setAttribute("src", paramString);
  }
  
  public String getWidth() {
    return getAttribute("width");
  }
  
  public void setWidth(String paramString) {
    setAttribute("width", paramString);
  }
  
  public HTMLIFrameElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLIFrameElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */