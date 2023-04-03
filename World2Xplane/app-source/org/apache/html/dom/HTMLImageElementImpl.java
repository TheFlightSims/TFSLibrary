package org.apache.html.dom;

import org.w3c.dom.html.HTMLImageElement;

public class HTMLImageElementImpl extends HTMLElementImpl implements HTMLImageElement {
  private static final long serialVersionUID = 1424360710977241315L;
  
  public String getLowSrc() {
    return getAttribute("lowsrc");
  }
  
  public void setLowSrc(String paramString) {
    setAttribute("lowsrc", paramString);
  }
  
  public String getSrc() {
    return getAttribute("src");
  }
  
  public void setSrc(String paramString) {
    setAttribute("src", paramString);
  }
  
  public String getName() {
    return getAttribute("name");
  }
  
  public void setName(String paramString) {
    setAttribute("name", paramString);
  }
  
  public String getAlign() {
    return capitalize(getAttribute("align"));
  }
  
  public void setAlign(String paramString) {
    setAttribute("align", paramString);
  }
  
  public String getAlt() {
    return getAttribute("alt");
  }
  
  public void setAlt(String paramString) {
    setAttribute("alt", paramString);
  }
  
  public String getBorder() {
    return getAttribute("border");
  }
  
  public void setBorder(String paramString) {
    setAttribute("border", paramString);
  }
  
  public String getHeight() {
    return getAttribute("height");
  }
  
  public void setHeight(String paramString) {
    setAttribute("height", paramString);
  }
  
  public String getHspace() {
    return getAttribute("hspace");
  }
  
  public void setHspace(String paramString) {
    setAttribute("hspace", paramString);
  }
  
  public boolean getIsMap() {
    return getBinary("ismap");
  }
  
  public void setIsMap(boolean paramBoolean) {
    setAttribute("ismap", paramBoolean);
  }
  
  public String getLongDesc() {
    return getAttribute("longdesc");
  }
  
  public void setLongDesc(String paramString) {
    setAttribute("longdesc", paramString);
  }
  
  public String getUseMap() {
    return getAttribute("useMap");
  }
  
  public void setUseMap(String paramString) {
    setAttribute("useMap", paramString);
  }
  
  public String getVspace() {
    return getAttribute("vspace");
  }
  
  public void setVspace(String paramString) {
    setAttribute("vspace", paramString);
  }
  
  public String getWidth() {
    return getAttribute("width");
  }
  
  public void setWidth(String paramString) {
    setAttribute("width", paramString);
  }
  
  public HTMLImageElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLImageElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */