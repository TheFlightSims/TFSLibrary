package org.apache.html.dom;

import org.w3c.dom.html.HTMLAppletElement;

public class HTMLAppletElementImpl extends HTMLElementImpl implements HTMLAppletElement {
  private static final long serialVersionUID = 8375794094117740967L;
  
  public String getAlign() {
    return getAttribute("align");
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
  
  public String getArchive() {
    return getAttribute("archive");
  }
  
  public void setArchive(String paramString) {
    setAttribute("archive", paramString);
  }
  
  public String getCode() {
    return getAttribute("code");
  }
  
  public void setCode(String paramString) {
    setAttribute("code", paramString);
  }
  
  public String getCodeBase() {
    return getAttribute("codebase");
  }
  
  public void setCodeBase(String paramString) {
    setAttribute("codebase", paramString);
  }
  
  public String getHeight() {
    return getAttribute("height");
  }
  
  public void setHeight(String paramString) {
    setAttribute("height", paramString);
  }
  
  public String getHspace() {
    return getAttribute("height");
  }
  
  public void setHspace(String paramString) {
    setAttribute("height", paramString);
  }
  
  public String getName() {
    return getAttribute("name");
  }
  
  public void setName(String paramString) {
    setAttribute("name", paramString);
  }
  
  public String getObject() {
    return getAttribute("object");
  }
  
  public void setObject(String paramString) {
    setAttribute("object", paramString);
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
  
  public HTMLAppletElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLAppletElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */