package org.apache.html.dom;

import org.w3c.dom.html.HTMLParamElement;

public class HTMLParamElementImpl extends HTMLElementImpl implements HTMLParamElement {
  private static final long serialVersionUID = -8513050483880341412L;
  
  public String getName() {
    return getAttribute("name");
  }
  
  public void setName(String paramString) {
    setAttribute("name", paramString);
  }
  
  public String getType() {
    return getAttribute("type");
  }
  
  public void setType(String paramString) {
    setAttribute("type", paramString);
  }
  
  public String getValue() {
    return getAttribute("value");
  }
  
  public void setValue(String paramString) {
    setAttribute("value", paramString);
  }
  
  public String getValueType() {
    return capitalize(getAttribute("valuetype"));
  }
  
  public void setValueType(String paramString) {
    setAttribute("valuetype", paramString);
  }
  
  public HTMLParamElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLParamElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */