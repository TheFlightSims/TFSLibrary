package org.apache.html.dom;

import org.w3c.dom.html.HTMLFrameElement;

public class HTMLFrameElementImpl extends HTMLElementImpl implements HTMLFrameElement {
  private static final long serialVersionUID = 635237057173695984L;
  
  public String getFrameBorder() {
    return getAttribute("frameborder");
  }
  
  public void setFrameBorder(String paramString) {
    setAttribute("frameborder", paramString);
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
  
  public boolean getNoResize() {
    return getBinary("noresize");
  }
  
  public void setNoResize(boolean paramBoolean) {
    setAttribute("noresize", paramBoolean);
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
  
  public HTMLFrameElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLFrameElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */