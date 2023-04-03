package org.apache.html.dom;

import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLTableCaptionElement;
import org.w3c.dom.html.HTMLTableElement;
import org.w3c.dom.html.HTMLTableSectionElement;

public class HTMLTableElementImpl extends HTMLElementImpl implements HTMLTableElement {
  private static final long serialVersionUID = -1824053099870917532L;
  
  private HTMLCollectionImpl _rows;
  
  private HTMLCollectionImpl _bodies;
  
  public synchronized HTMLTableCaptionElement getCaption() {
    for (Node node = getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node instanceof HTMLTableCaptionElement && node.getNodeName().equals("CAPTION"))
        return (HTMLTableCaptionElement)node; 
    } 
    return null;
  }
  
  public synchronized void setCaption(HTMLTableCaptionElement paramHTMLTableCaptionElement) {
    if (paramHTMLTableCaptionElement != null && !paramHTMLTableCaptionElement.getTagName().equals("CAPTION"))
      throw new IllegalArgumentException("HTM016 Argument 'caption' is not an element of type <CAPTION>."); 
    deleteCaption();
    if (paramHTMLTableCaptionElement != null)
      appendChild(paramHTMLTableCaptionElement); 
  }
  
  public synchronized HTMLElement createCaption() {
    HTMLTableCaptionElement hTMLTableCaptionElement = getCaption();
    if (hTMLTableCaptionElement != null)
      return hTMLTableCaptionElement; 
    hTMLTableCaptionElement = new HTMLTableCaptionElementImpl((HTMLDocumentImpl)getOwnerDocument(), "CAPTION");
    appendChild(hTMLTableCaptionElement);
    return hTMLTableCaptionElement;
  }
  
  public synchronized void deleteCaption() {
    HTMLTableCaptionElement hTMLTableCaptionElement = getCaption();
    if (hTMLTableCaptionElement != null)
      removeChild(hTMLTableCaptionElement); 
  }
  
  public synchronized HTMLTableSectionElement getTHead() {
    for (Node node = getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node instanceof HTMLTableSectionElement && node.getNodeName().equals("THEAD"))
        return (HTMLTableSectionElement)node; 
    } 
    return null;
  }
  
  public synchronized void setTHead(HTMLTableSectionElement paramHTMLTableSectionElement) {
    if (paramHTMLTableSectionElement != null && !paramHTMLTableSectionElement.getTagName().equals("THEAD"))
      throw new IllegalArgumentException("HTM017 Argument 'tHead' is not an element of type <THEAD>."); 
    deleteTHead();
    if (paramHTMLTableSectionElement != null)
      appendChild(paramHTMLTableSectionElement); 
  }
  
  public synchronized HTMLElement createTHead() {
    HTMLTableSectionElement hTMLTableSectionElement = getTHead();
    if (hTMLTableSectionElement != null)
      return hTMLTableSectionElement; 
    hTMLTableSectionElement = new HTMLTableSectionElementImpl((HTMLDocumentImpl)getOwnerDocument(), "THEAD");
    appendChild(hTMLTableSectionElement);
    return hTMLTableSectionElement;
  }
  
  public synchronized void deleteTHead() {
    HTMLTableSectionElement hTMLTableSectionElement = getTHead();
    if (hTMLTableSectionElement != null)
      removeChild(hTMLTableSectionElement); 
  }
  
  public synchronized HTMLTableSectionElement getTFoot() {
    for (Node node = getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node instanceof HTMLTableSectionElement && node.getNodeName().equals("TFOOT"))
        return (HTMLTableSectionElement)node; 
    } 
    return null;
  }
  
  public synchronized void setTFoot(HTMLTableSectionElement paramHTMLTableSectionElement) {
    if (paramHTMLTableSectionElement != null && !paramHTMLTableSectionElement.getTagName().equals("TFOOT"))
      throw new IllegalArgumentException("HTM018 Argument 'tFoot' is not an element of type <TFOOT>."); 
    deleteTFoot();
    if (paramHTMLTableSectionElement != null)
      appendChild(paramHTMLTableSectionElement); 
  }
  
  public synchronized HTMLElement createTFoot() {
    HTMLTableSectionElement hTMLTableSectionElement = getTFoot();
    if (hTMLTableSectionElement != null)
      return hTMLTableSectionElement; 
    hTMLTableSectionElement = new HTMLTableSectionElementImpl((HTMLDocumentImpl)getOwnerDocument(), "TFOOT");
    appendChild(hTMLTableSectionElement);
    return hTMLTableSectionElement;
  }
  
  public synchronized void deleteTFoot() {
    HTMLTableSectionElement hTMLTableSectionElement = getTFoot();
    if (hTMLTableSectionElement != null)
      removeChild(hTMLTableSectionElement); 
  }
  
  public HTMLCollection getRows() {
    if (this._rows == null)
      this._rows = new HTMLCollectionImpl(this, (short)7); 
    return this._rows;
  }
  
  public HTMLCollection getTBodies() {
    if (this._bodies == null)
      this._bodies = new HTMLCollectionImpl(this, (short)-2); 
    return this._bodies;
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
  
  public String getBorder() {
    return getAttribute("border");
  }
  
  public void setBorder(String paramString) {
    setAttribute("border", paramString);
  }
  
  public String getCellPadding() {
    return getAttribute("cellpadding");
  }
  
  public void setCellPadding(String paramString) {
    setAttribute("cellpadding", paramString);
  }
  
  public String getCellSpacing() {
    return getAttribute("cellspacing");
  }
  
  public void setCellSpacing(String paramString) {
    setAttribute("cellspacing", paramString);
  }
  
  public String getFrame() {
    return capitalize(getAttribute("frame"));
  }
  
  public void setFrame(String paramString) {
    setAttribute("frame", paramString);
  }
  
  public String getRules() {
    return capitalize(getAttribute("rules"));
  }
  
  public void setRules(String paramString) {
    setAttribute("rules", paramString);
  }
  
  public String getSummary() {
    return getAttribute("summary");
  }
  
  public void setSummary(String paramString) {
    setAttribute("summary", paramString);
  }
  
  public String getWidth() {
    return getAttribute("width");
  }
  
  public void setWidth(String paramString) {
    setAttribute("width", paramString);
  }
  
  public HTMLElement insertRow(int paramInt) {
    HTMLTableRowElementImpl hTMLTableRowElementImpl = new HTMLTableRowElementImpl((HTMLDocumentImpl)getOwnerDocument(), "TR");
    insertRowX(paramInt, hTMLTableRowElementImpl);
    return hTMLTableRowElementImpl;
  }
  
  void insertRowX(int paramInt, HTMLTableRowElementImpl paramHTMLTableRowElementImpl) {
    Node node2 = null;
    for (Node node1 = getFirstChild(); node1 != null; node1 = node1.getNextSibling()) {
      if (node1 instanceof org.w3c.dom.html.HTMLTableRowElement) {
        if (paramInt == 0) {
          insertBefore(paramHTMLTableRowElementImpl, node1);
          return;
        } 
      } else if (node1 instanceof HTMLTableSectionElementImpl) {
        node2 = node1;
        paramInt = ((HTMLTableSectionElementImpl)node1).insertRowX(paramInt, paramHTMLTableRowElementImpl);
        if (paramInt < 0)
          return; 
      } 
    } 
    if (node2 != null) {
      node2.appendChild(paramHTMLTableRowElementImpl);
    } else {
      appendChild(paramHTMLTableRowElementImpl);
    } 
  }
  
  public synchronized void deleteRow(int paramInt) {
    for (Node node = getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node instanceof org.w3c.dom.html.HTMLTableRowElement) {
        if (paramInt == 0) {
          removeChild(node);
          return;
        } 
        paramInt--;
      } else if (node instanceof HTMLTableSectionElementImpl) {
        paramInt = ((HTMLTableSectionElementImpl)node).deleteRowX(paramInt);
        if (paramInt < 0)
          return; 
      } 
    } 
  }
  
  public Node cloneNode(boolean paramBoolean) {
    HTMLTableElementImpl hTMLTableElementImpl = (HTMLTableElementImpl)super.cloneNode(paramBoolean);
    hTMLTableElementImpl._rows = null;
    hTMLTableElementImpl._bodies = null;
    return hTMLTableElementImpl;
  }
  
  public HTMLTableElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLTableElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */