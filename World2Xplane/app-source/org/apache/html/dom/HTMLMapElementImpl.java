package org.apache.html.dom;

import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLMapElement;

public class HTMLMapElementImpl extends HTMLElementImpl implements HTMLMapElement {
  private static final long serialVersionUID = 7520887584251976392L;
  
  private HTMLCollection _areas;
  
  public HTMLCollection getAreas() {
    if (this._areas == null)
      this._areas = new HTMLCollectionImpl(this, (short)-1); 
    return this._areas;
  }
  
  public String getName() {
    return getAttribute("name");
  }
  
  public void setName(String paramString) {
    setAttribute("name", paramString);
  }
  
  public Node cloneNode(boolean paramBoolean) {
    HTMLMapElementImpl hTMLMapElementImpl = (HTMLMapElementImpl)super.cloneNode(paramBoolean);
    hTMLMapElementImpl._areas = null;
    return hTMLMapElementImpl;
  }
  
  public HTMLMapElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLMapElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */