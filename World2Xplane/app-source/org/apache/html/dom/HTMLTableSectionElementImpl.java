package org.apache.html.dom;

import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLTableSectionElement;

public class HTMLTableSectionElementImpl extends HTMLElementImpl implements HTMLTableSectionElement {
  private static final long serialVersionUID = 1016412997716618027L;
  
  private HTMLCollectionImpl _rows;
  
  public String getAlign() {
    return capitalize(getAttribute("align"));
  }
  
  public void setAlign(String paramString) {
    setAttribute("align", paramString);
  }
  
  public String getCh() {
    String str = getAttribute("char");
    if (str != null && str.length() > 1)
      str = str.substring(0, 1); 
    return str;
  }
  
  public void setCh(String paramString) {
    if (paramString != null && paramString.length() > 1)
      paramString = paramString.substring(0, 1); 
    setAttribute("char", paramString);
  }
  
  public String getChOff() {
    return getAttribute("charoff");
  }
  
  public void setChOff(String paramString) {
    setAttribute("charoff", paramString);
  }
  
  public String getVAlign() {
    return capitalize(getAttribute("valign"));
  }
  
  public void setVAlign(String paramString) {
    setAttribute("valign", paramString);
  }
  
  public HTMLCollection getRows() {
    if (this._rows == null)
      this._rows = new HTMLCollectionImpl(this, (short)7); 
    return this._rows;
  }
  
  public HTMLElement insertRow(int paramInt) {
    HTMLTableRowElementImpl hTMLTableRowElementImpl = new HTMLTableRowElementImpl((HTMLDocumentImpl)getOwnerDocument(), "TR");
    hTMLTableRowElementImpl.insertCell(0);
    if (insertRowX(paramInt, hTMLTableRowElementImpl) >= 0)
      appendChild(hTMLTableRowElementImpl); 
    return hTMLTableRowElementImpl;
  }
  
  int insertRowX(int paramInt, HTMLTableRowElementImpl paramHTMLTableRowElementImpl) {
    for (Node node = getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node instanceof org.w3c.dom.html.HTMLTableRowElement) {
        if (paramInt == 0) {
          insertBefore(paramHTMLTableRowElementImpl, node);
          return -1;
        } 
        paramInt--;
      } 
    } 
    return paramInt;
  }
  
  public void deleteRow(int paramInt) {
    deleteRowX(paramInt);
  }
  
  int deleteRowX(int paramInt) {
    for (Node node = getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node instanceof org.w3c.dom.html.HTMLTableRowElement) {
        if (paramInt == 0) {
          removeChild(node);
          return -1;
        } 
        paramInt--;
      } 
    } 
    return paramInt;
  }
  
  public Node cloneNode(boolean paramBoolean) {
    HTMLTableSectionElementImpl hTMLTableSectionElementImpl = (HTMLTableSectionElementImpl)super.cloneNode(paramBoolean);
    hTMLTableSectionElementImpl._rows = null;
    return hTMLTableSectionElementImpl;
  }
  
  public HTMLTableSectionElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLTableSectionElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */