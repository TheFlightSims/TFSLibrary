package org.apache.html.dom;

import org.w3c.dom.html.HTMLFrameSetElement;

public class HTMLFrameSetElementImpl extends HTMLElementImpl implements HTMLFrameSetElement {
  private static final long serialVersionUID = 8403143821972586708L;
  
  public String getCols() {
    return getAttribute("cols");
  }
  
  public void setCols(String paramString) {
    setAttribute("cols", paramString);
  }
  
  public String getRows() {
    return getAttribute("rows");
  }
  
  public void setRows(String paramString) {
    setAttribute("rows", paramString);
  }
  
  public HTMLFrameSetElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLFrameSetElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */