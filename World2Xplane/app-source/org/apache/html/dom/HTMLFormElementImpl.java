package org.apache.html.dom;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLFormElement;

public class HTMLFormElementImpl extends HTMLElementImpl implements HTMLFormElement {
  private static final long serialVersionUID = -7324749629151493210L;
  
  private HTMLCollectionImpl _elements;
  
  public HTMLCollection getElements() {
    if (this._elements == null)
      this._elements = new HTMLCollectionImpl(this, (short)8); 
    return this._elements;
  }
  
  public int getLength() {
    return getElements().getLength();
  }
  
  public String getName() {
    return getAttribute("name");
  }
  
  public void setName(String paramString) {
    setAttribute("name", paramString);
  }
  
  public String getAcceptCharset() {
    return getAttribute("accept-charset");
  }
  
  public void setAcceptCharset(String paramString) {
    setAttribute("accept-charset", paramString);
  }
  
  public String getAction() {
    return getAttribute("action");
  }
  
  public void setAction(String paramString) {
    setAttribute("action", paramString);
  }
  
  public String getEnctype() {
    return getAttribute("enctype");
  }
  
  public void setEnctype(String paramString) {
    setAttribute("enctype", paramString);
  }
  
  public String getMethod() {
    return capitalize(getAttribute("method"));
  }
  
  public void setMethod(String paramString) {
    setAttribute("method", paramString);
  }
  
  public String getTarget() {
    return getAttribute("target");
  }
  
  public void setTarget(String paramString) {
    setAttribute("target", paramString);
  }
  
  public void submit() {}
  
  public void reset() {}
  
  public NodeList getChildNodes() {
    return getChildNodesUnoptimized();
  }
  
  public Node cloneNode(boolean paramBoolean) {
    HTMLFormElementImpl hTMLFormElementImpl = (HTMLFormElementImpl)super.cloneNode(paramBoolean);
    hTMLFormElementImpl._elements = null;
    return hTMLFormElementImpl;
  }
  
  public HTMLFormElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLFormElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */