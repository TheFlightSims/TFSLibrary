package org.apache.html.dom;

import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.html.HTMLTitleElement;

public class HTMLTitleElementImpl extends HTMLElementImpl implements HTMLTitleElement {
  private static final long serialVersionUID = 879646303512367875L;
  
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
  
  public HTMLTitleElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLTitleElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */