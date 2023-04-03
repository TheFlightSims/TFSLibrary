package org.apache.html.dom;

import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.w3c.dom.html.HTMLScriptElement;

public class HTMLScriptElementImpl extends HTMLElementImpl implements HTMLScriptElement {
  private static final long serialVersionUID = 5090330049085326558L;
  
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
  
  public String getHtmlFor() {
    return getAttribute("for");
  }
  
  public void setHtmlFor(String paramString) {
    setAttribute("for", paramString);
  }
  
  public String getEvent() {
    return getAttribute("event");
  }
  
  public void setEvent(String paramString) {
    setAttribute("event", paramString);
  }
  
  public String getCharset() {
    return getAttribute("charset");
  }
  
  public void setCharset(String paramString) {
    setAttribute("charset", paramString);
  }
  
  public boolean getDefer() {
    return getBinary("defer");
  }
  
  public void setDefer(boolean paramBoolean) {
    setAttribute("defer", paramBoolean);
  }
  
  public String getSrc() {
    return getAttribute("src");
  }
  
  public void setSrc(String paramString) {
    setAttribute("src", paramString);
  }
  
  public String getType() {
    return getAttribute("type");
  }
  
  public void setType(String paramString) {
    setAttribute("type", paramString);
  }
  
  public HTMLScriptElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLScriptElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */