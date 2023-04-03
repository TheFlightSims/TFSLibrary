package org.apache.html.dom;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLTableRowElement;

public class HTMLTableRowElementImpl extends HTMLElementImpl implements HTMLTableRowElement {
  private static final long serialVersionUID = 5409562635656244263L;
  
  HTMLCollection _cells;
  
  public int getRowIndex() {
    Node node = getParentNode();
    if (node instanceof org.w3c.dom.html.HTMLTableSectionElement)
      node = node.getParentNode(); 
    return (node instanceof org.w3c.dom.html.HTMLTableElement) ? getRowIndex(node) : -1;
  }
  
  public void setRowIndex(int paramInt) {
    Node node = getParentNode();
    if (node instanceof org.w3c.dom.html.HTMLTableSectionElement)
      node = node.getParentNode(); 
    if (node instanceof org.w3c.dom.html.HTMLTableElement)
      ((HTMLTableElementImpl)node).insertRowX(paramInt, this); 
  }
  
  public int getSectionRowIndex() {
    Node node = getParentNode();
    return (node instanceof org.w3c.dom.html.HTMLTableSectionElement) ? getRowIndex(node) : -1;
  }
  
  public void setSectionRowIndex(int paramInt) {
    Node node = getParentNode();
    if (node instanceof org.w3c.dom.html.HTMLTableSectionElement)
      ((HTMLTableSectionElementImpl)node).insertRowX(paramInt, this); 
  }
  
  int getRowIndex(Node paramNode) {
    NodeList nodeList = ((HTMLElement)paramNode).getElementsByTagName("TR");
    for (byte b = 0; b < nodeList.getLength(); b++) {
      if (nodeList.item(b) == this)
        return b; 
    } 
    return -1;
  }
  
  public HTMLCollection getCells() {
    if (this._cells == null)
      this._cells = new HTMLCollectionImpl(this, (short)-3); 
    return this._cells;
  }
  
  public void setCells(HTMLCollection paramHTMLCollection) {
    Node node;
    for (node = getFirstChild(); node != null; node = node.getNextSibling())
      removeChild(node); 
    byte b = 0;
    for (node = paramHTMLCollection.item(b); node != null; node = paramHTMLCollection.item(++b))
      appendChild(node); 
  }
  
  public HTMLElement insertCell(int paramInt) {
    HTMLTableCellElementImpl hTMLTableCellElementImpl = new HTMLTableCellElementImpl((HTMLDocumentImpl)getOwnerDocument(), "TD");
    for (Node node = getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node instanceof org.w3c.dom.html.HTMLTableCellElement) {
        if (paramInt == 0) {
          insertBefore(hTMLTableCellElementImpl, node);
          return hTMLTableCellElementImpl;
        } 
        paramInt--;
      } 
    } 
    appendChild(hTMLTableCellElementImpl);
    return hTMLTableCellElementImpl;
  }
  
  public void deleteCell(int paramInt) {
    for (Node node = getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node instanceof org.w3c.dom.html.HTMLTableCellElement) {
        if (paramInt == 0) {
          removeChild(node);
          return;
        } 
        paramInt--;
      } 
    } 
  }
  
  public String getAlign() {
    return capitalize(getAttribute("align"));
  }
  
  public void setAlign(String paramString) {
    setAttribute("align", paramString);
  }
  
  public String getBgColor() {
    return getAttribute("bgcolor");
  }
  
  public void setBgColor(String paramString) {
    setAttribute("bgcolor", paramString);
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
  
  public Node cloneNode(boolean paramBoolean) {
    HTMLTableRowElementImpl hTMLTableRowElementImpl = (HTMLTableRowElementImpl)super.cloneNode(paramBoolean);
    hTMLTableRowElementImpl._cells = null;
    return hTMLTableRowElementImpl;
  }
  
  public HTMLTableRowElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLTableRowElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */