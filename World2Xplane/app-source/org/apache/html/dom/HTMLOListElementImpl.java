package org.apache.html.dom;

import org.w3c.dom.html.HTMLOListElement;

public class HTMLOListElementImpl extends HTMLElementImpl implements HTMLOListElement {
  private static final long serialVersionUID = 1293750546025862146L;
  
  public boolean getCompact() {
    return getBinary("compact");
  }
  
  public void setCompact(boolean paramBoolean) {
    setAttribute("compact", paramBoolean);
  }
  
  public int getStart() {
    return getInteger(getAttribute("start"));
  }
  
  public void setStart(int paramInt) {
    setAttribute("start", String.valueOf(paramInt));
  }
  
  public String getType() {
    return getAttribute("type");
  }
  
  public void setType(String paramString) {
    setAttribute("type", paramString);
  }
  
  public HTMLOListElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLOListElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */