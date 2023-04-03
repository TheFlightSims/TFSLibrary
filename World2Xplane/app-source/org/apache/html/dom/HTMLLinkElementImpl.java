package org.apache.html.dom;

import org.w3c.dom.html.HTMLLinkElement;

public class HTMLLinkElementImpl extends HTMLElementImpl implements HTMLLinkElement {
  private static final long serialVersionUID = 874345520063418879L;
  
  public boolean getDisabled() {
    return getBinary("disabled");
  }
  
  public void setDisabled(boolean paramBoolean) {
    setAttribute("disabled", paramBoolean);
  }
  
  public String getCharset() {
    return getAttribute("charset");
  }
  
  public void setCharset(String paramString) {
    setAttribute("charset", paramString);
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
  
  public String getMedia() {
    return getAttribute("media");
  }
  
  public void setMedia(String paramString) {
    setAttribute("media", paramString);
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
  
  public HTMLLinkElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLLinkElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */