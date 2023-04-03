package org.apache.wml.dom;

import org.apache.wml.WMLDoElement;

public class WMLDoElementImpl extends WMLElementImpl implements WMLDoElement {
  private static final long serialVersionUID = 7755861458464251322L;
  
  public WMLDoElementImpl(WMLDocumentImpl paramWMLDocumentImpl, String paramString) {
    super(paramWMLDocumentImpl, paramString);
  }
  
  public void setOptional(String paramString) {
    setAttribute("optional", paramString);
  }
  
  public String getOptional() {
    return getAttribute("optional");
  }
  
  public void setClassName(String paramString) {
    setAttribute("class", paramString);
  }
  
  public String getClassName() {
    return getAttribute("class");
  }
  
  public void setXmlLang(String paramString) {
    setAttribute("xml:lang", paramString);
  }
  
  public String getXmlLang() {
    return getAttribute("xml:lang");
  }
  
  public void setId(String paramString) {
    setAttribute("id", paramString);
  }
  
  public String getId() {
    return getAttribute("id");
  }
  
  public void setLabel(String paramString) {
    setAttribute("label", paramString);
  }
  
  public String getLabel() {
    return getAttribute("label");
  }
  
  public void setType(String paramString) {
    setAttribute("type", paramString);
  }
  
  public String getType() {
    return getAttribute("type");
  }
  
  public void setName(String paramString) {
    setAttribute("name", paramString);
  }
  
  public String getName() {
    return getAttribute("name");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\wml\dom\WMLDoElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */