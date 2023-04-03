package org.apache.html.dom;

import org.w3c.dom.html.HTMLAreaElement;

public class HTMLAreaElementImpl extends HTMLElementImpl implements HTMLAreaElement {
  private static final long serialVersionUID = 7164004431531608995L;
  
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
  
  public String getAlt() {
    return getAttribute("alt");
  }
  
  public void setAlt(String paramString) {
    setAttribute("alt", paramString);
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
  
  public boolean getNoHref() {
    return getBinary("href");
  }
  
  public void setNoHref(boolean paramBoolean) {
    setAttribute("nohref", paramBoolean);
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
  
  public HTMLAreaElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLAreaElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */