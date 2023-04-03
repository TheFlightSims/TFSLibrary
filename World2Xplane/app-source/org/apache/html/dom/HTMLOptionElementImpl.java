package org.apache.html.dom;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLOptionElement;

public class HTMLOptionElementImpl extends HTMLElementImpl implements HTMLOptionElement {
  private static final long serialVersionUID = -4486774554137530907L;
  
  public boolean getDefaultSelected() {
    return getBinary("default-selected");
  }
  
  public void setDefaultSelected(boolean paramBoolean) {
    setAttribute("default-selected", paramBoolean);
  }
  
  public String getText() {
    StringBuffer stringBuffer = new StringBuffer();
    for (Node node = getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node instanceof Text)
        stringBuffer.append(((Text)node).getData()); 
    } 
    return stringBuffer.toString();
  }
  
  public void setText(String paramString) {
    for (Node node = getFirstChild(); node != null; node = node1) {
      Node node1 = node.getNextSibling();
      removeChild(node);
    } 
    insertBefore(getOwnerDocument().createTextNode(paramString), getFirstChild());
  }
  
  public int getIndex() {
    Node node;
    for (node = getParentNode(); node != null && !(node instanceof org.w3c.dom.html.HTMLSelectElement); node = node.getParentNode());
    if (node != null) {
      NodeList nodeList = ((HTMLElement)node).getElementsByTagName("OPTION");
      for (byte b = 0; b < nodeList.getLength(); b++) {
        if (nodeList.item(b) == this)
          return b; 
      } 
    } 
    return -1;
  }
  
  public void setIndex(int paramInt) {
    Node node;
    for (node = getParentNode(); node != null && !(node instanceof org.w3c.dom.html.HTMLSelectElement); node = node.getParentNode());
    if (node != null) {
      NodeList nodeList = ((HTMLElement)node).getElementsByTagName("OPTION");
      if (nodeList.item(paramInt) != this) {
        getParentNode().removeChild(this);
        Node node1 = nodeList.item(paramInt);
        node1.getParentNode().insertBefore(this, node1);
      } 
    } 
  }
  
  public boolean getDisabled() {
    return getBinary("disabled");
  }
  
  public void setDisabled(boolean paramBoolean) {
    setAttribute("disabled", paramBoolean);
  }
  
  public String getLabel() {
    return capitalize(getAttribute("label"));
  }
  
  public void setLabel(String paramString) {
    setAttribute("label", paramString);
  }
  
  public boolean getSelected() {
    return getBinary("selected");
  }
  
  public void setSelected(boolean paramBoolean) {
    setAttribute("selected", paramBoolean);
  }
  
  public String getValue() {
    return getAttribute("value");
  }
  
  public void setValue(String paramString) {
    setAttribute("value", paramString);
  }
  
  public HTMLOptionElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLOptionElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */