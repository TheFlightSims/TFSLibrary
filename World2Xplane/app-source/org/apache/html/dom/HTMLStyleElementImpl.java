package org.apache.html.dom;

import org.w3c.dom.html.HTMLStyleElement;

public class HTMLStyleElementImpl extends HTMLElementImpl implements HTMLStyleElement {
  private static final long serialVersionUID = -9001815754196124532L;
  
  public boolean getDisabled() {
    return getBinary("disabled");
  }
  
  public void setDisabled(boolean paramBoolean) {
    setAttribute("disabled", paramBoolean);
  }
  
  public String getMedia() {
    return getAttribute("media");
  }
  
  public void setMedia(String paramString) {
    setAttribute("media", paramString);
  }
  
  public String getType() {
    return getAttribute("type");
  }
  
  public void setType(String paramString) {
    setAttribute("type", paramString);
  }
  
  public HTMLStyleElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLStyleElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */