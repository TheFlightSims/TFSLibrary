package org.apache.html.dom;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLSelectElement;

public class HTMLSelectElementImpl extends HTMLElementImpl implements HTMLSelectElement, HTMLFormControl {
  private static final long serialVersionUID = -6998282711006968187L;
  
  private HTMLCollection _options;
  
  public String getType() {
    return getAttribute("type");
  }
  
  public String getValue() {
    return getAttribute("value");
  }
  
  public void setValue(String paramString) {
    setAttribute("value", paramString);
  }
  
  public int getSelectedIndex() {
    NodeList nodeList = getElementsByTagName("OPTION");
    for (byte b = 0; b < nodeList.getLength(); b++) {
      if (((HTMLOptionElement)nodeList.item(b)).getSelected())
        return b; 
    } 
    return -1;
  }
  
  public void setSelectedIndex(int paramInt) {
    NodeList nodeList = getElementsByTagName("OPTION");
    for (byte b = 0; b < nodeList.getLength(); b++)
      ((HTMLOptionElementImpl)nodeList.item(b)).setSelected((b == paramInt)); 
  }
  
  public HTMLCollection getOptions() {
    if (this._options == null)
      this._options = new HTMLCollectionImpl(this, (short)6); 
    return this._options;
  }
  
  public int getLength() {
    return getOptions().getLength();
  }
  
  public boolean getDisabled() {
    return getBinary("disabled");
  }
  
  public void setDisabled(boolean paramBoolean) {
    setAttribute("disabled", paramBoolean);
  }
  
  public boolean getMultiple() {
    return getBinary("multiple");
  }
  
  public void setMultiple(boolean paramBoolean) {
    setAttribute("multiple", paramBoolean);
  }
  
  public String getName() {
    return getAttribute("name");
  }
  
  public void setName(String paramString) {
    setAttribute("name", paramString);
  }
  
  public int getSize() {
    return getInteger(getAttribute("size"));
  }
  
  public void setSize(int paramInt) {
    setAttribute("size", String.valueOf(paramInt));
  }
  
  public int getTabIndex() {
    return getInteger(getAttribute("tabindex"));
  }
  
  public void setTabIndex(int paramInt) {
    setAttribute("tabindex", String.valueOf(paramInt));
  }
  
  public void add(HTMLElement paramHTMLElement1, HTMLElement paramHTMLElement2) {
    insertBefore(paramHTMLElement1, paramHTMLElement2);
  }
  
  public void remove(int paramInt) {
    NodeList nodeList = getElementsByTagName("OPTION");
    Node node = nodeList.item(paramInt);
    if (node != null)
      node.getParentNode().removeChild(node); 
  }
  
  public void blur() {}
  
  public void focus() {}
  
  public NodeList getChildNodes() {
    return getChildNodesUnoptimized();
  }
  
  public Node cloneNode(boolean paramBoolean) {
    HTMLSelectElementImpl hTMLSelectElementImpl = (HTMLSelectElementImpl)super.cloneNode(paramBoolean);
    hTMLSelectElementImpl._options = null;
    return hTMLSelectElementImpl;
  }
  
  public HTMLSelectElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLSelectElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */