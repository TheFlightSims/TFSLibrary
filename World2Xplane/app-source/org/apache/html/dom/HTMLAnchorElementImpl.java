package org.apache.html.dom;

import org.w3c.dom.html.HTMLAnchorElement;

public class HTMLAnchorElementImpl extends HTMLElementImpl implements HTMLAnchorElement {
  private static final long serialVersionUID = -140558580924061847L;
  
  public String getAccessKey() {
    String str = getAttribute("accesskey");
    if (str != null && str.length() > 1)
      str = str.substring(0, 1); 
    return str;
  }
  
  public void setAccessKey(String paramString) {
    if (paramString != null && paramString.length() > 1)
      paramString = paramString.substring(0, 1); 
    setAttribute("accesskey", paramString);
  }
  
  public String getCharset() {
    return getAttribute("charset");
  }
  
  public void setCharset(String paramString) {
    setAttribute("charset", paramString);
  }
  
  public String getCoords() {
    return getAttribute("coords");
  }
  
  public void setCoords(String paramString) {
    setAttribute("coords", paramString);
  }
  
  public String getHref() {
    return getAttribute("href");
  }
  
  public void setHref(String paramString) {
    setAttribute("href", paramString);
  }
  
  public String getHreflang() {
    return getAttribute("hreflang");
  }
  
  public void setHreflang(String paramString) {
    setAttribute("hreflang", paramString);
  }
  
  public String getName() {
    return getAttribute("name");
  }
  
  public void setName(String paramString) {
    setAttribute("name", paramString);
  }
  
  public String getRel() {
    return getAttribute("rel");
  }
  
  public void setRel(String paramString) {
    setAttribute("rel", paramString);
  }
  
  public String getRev() {
    return getAttribute("rev");
  }
  
  public void setRev(String paramString) {
    setAttribute("rev", paramString);
  }
  
  public String getShape() {
    return capitalize(getAttribute("shape"));
  }
  
  public void setShape(String paramString) {
    setAttribute("shape", paramString);
  }
  
  public int getTabIndex() {
    return getInteger(getAttribute("tabindex"));
  }
  
  public void setTabIndex(int paramInt) {
    setAttribute("tabindex", String.valueOf(paramInt));
  }
  
  public String getTarget() {
    return getAttribute("target");
  }
  
  public void setTarget(String paramString) {
    setAttribute("target", paramString);
  }
  
  public String getType() {
    return getAttribute("type");
  }
  
  public void setType(String paramString) {
    setAttribute("type", paramString);
  }
  
  public void blur() {}
  
  public void focus() {}
  
  public HTMLAnchorElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLAnchorElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */