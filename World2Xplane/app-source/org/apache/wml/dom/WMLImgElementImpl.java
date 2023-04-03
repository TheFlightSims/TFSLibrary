package org.apache.wml.dom;

import org.apache.wml.WMLImgElement;

public class WMLImgElementImpl extends WMLElementImpl implements WMLImgElement {
  private static final long serialVersionUID = -500092034867051550L;
  
  public WMLImgElementImpl(WMLDocumentImpl paramWMLDocumentImpl, String paramString) {
    super(paramWMLDocumentImpl, paramString);
  }
  
  public void setWidth(String paramString) {
    setAttribute("width", paramString);
  }
  
  public String getWidth() {
    return getAttribute("width");
  }
  
  public void setClassName(String paramString) {
    setAttribute("class", paramString);
  }
  
  public String getClassName() {
    return getAttribute("class");
  }
  
  public void setXmlLang(String paramString) {
    setAttribute("xml:lang", paramString);
  }
  
  public String getXmlLang() {
    return getAttribute("xml:lang");
  }
  
  public void setLocalSrc(String paramString) {
    setAttribute("localsrc", paramString);
  }
  
  public String getLocalSrc() {
    return getAttribute("localsrc");
  }
  
  public void setHeight(String paramString) {
    setAttribute("height", paramString);
  }
  
  public String getHeight() {
    return getAttribute("height");
  }
  
  public void setAlign(String paramString) {
    setAttribute("align", paramString);
  }
  
  public String getAlign() {
    return getAttribute("align");
  }
  
  public void setVspace(String paramString) {
    setAttribute("vspace", paramString);
  }
  
  public String getVspace() {
    return getAttribute("vspace");
  }
  
  public void setAlt(String paramString) {
    setAttribute("alt", paramString);
  }
  
  public String getAlt() {
    return getAttribute("alt");
  }
  
  public void setId(String paramString) {
    setAttribute("id", paramString);
  }
  
  public String getId() {
    return getAttribute("id");
  }
  
  public void setHspace(String paramString) {
    setAttribute("hspace", paramString);
  }
  
  public String getHspace() {
    return getAttribute("hspace");
  }
  
  public void setSrc(String paramString) {
    setAttribute("src", paramString);
  }
  
  public String getSrc() {
    return getAttribute("src");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\wml\dom\WMLImgElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */