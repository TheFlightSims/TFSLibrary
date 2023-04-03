package org.apache.html.dom;

import org.w3c.dom.html.HTMLObjectElement;

public class HTMLObjectElementImpl extends HTMLElementImpl implements HTMLObjectElement, HTMLFormControl {
  private static final long serialVersionUID = 2276953229932965067L;
  
  public String getCode() {
    return getAttribute("code");
  }
  
  public void setCode(String paramString) {
    setAttribute("code", paramString);
  }
  
  public String getAlign() {
    return capitalize(getAttribute("align"));
  }
  
  public void setAlign(String paramString) {
    setAttribute("align", paramString);
  }
  
  public String getArchive() {
    return getAttribute("archive");
  }
  
  public void setArchive(String paramString) {
    setAttribute("archive", paramString);
  }
  
  public String getBorder() {
    return getAttribute("border");
  }
  
  public void setBorder(String paramString) {
    setAttribute("border", paramString);
  }
  
  public String getCodeBase() {
    return getAttribute("codebase");
  }
  
  public void setCodeBase(String paramString) {
    setAttribute("codebase", paramString);
  }
  
  public String getCodeType() {
    return getAttribute("codetype");
  }
  
  public void setCodeType(String paramString) {
    setAttribute("codetype", paramString);
  }
  
  public String getData() {
    return getAttribute("data");
  }
  
  public void setData(String paramString) {
    setAttribute("data", paramString);
  }
  
  public boolean getDeclare() {
    return getBinary("declare");
  }
  
  public void setDeclare(boolean paramBoolean) {
    setAttribute("declare", paramBoolean);
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
  
  public String getName() {
    return getAttribute("name");
  }
  
  public void setName(String paramString) {
    setAttribute("name", paramString);
  }
  
  public String getStandby() {
    return getAttribute("standby");
  }
  
  public void setStandby(String paramString) {
    setAttribute("standby", paramString);
  }
  
  public int getTabIndex() {
    try {
      return Integer.parseInt(getAttribute("tabindex"));
    } catch (NumberFormatException numberFormatException) {
      return 0;
    } 
  }
  
  public void setTabIndex(int paramInt) {
    setAttribute("tabindex", String.valueOf(paramInt));
  }
  
  public String getType() {
    return getAttribute("type");
  }
  
  public void setType(String paramString) {
    setAttribute("type", paramString);
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
  
  public HTMLObjectElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLObjectElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */