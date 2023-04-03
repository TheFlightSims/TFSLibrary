package org.apache.wml.dom;

import org.apache.wml.WMLPElement;

public class WMLPElementImpl extends WMLElementImpl implements WMLPElement {
  private static final long serialVersionUID = 4263257796458499960L;
  
  public WMLPElementImpl(WMLDocumentImpl paramWMLDocumentImpl, String paramString) {
    super(paramWMLDocumentImpl, paramString);
  }
  
  public void setClassName(String paramString) {
    setAttribute("class", paramString);
  }
  
  public String getClassName() {
    return getAttribute("class");
  }
  
  public void setMode(String paramString) {
    setAttribute("mode", paramString);
  }
  
  public String getMode() {
    return getAttribute("mode");
  }
  
  public void setXmlLang(String paramString) {
    setAttribute("xml:lang", paramString);
  }
  
  public String getXmlLang() {
    return getAttribute("xml:lang");
  }
  
  public void setAlign(String paramString) {
    setAttribute("align", paramString);
  }
  
  public String getAlign() {
    return getAttribute("align");
  }
  
  public void setId(String paramString) {
    setAttribute("id", paramString);
  }
  
  public String getId() {
    return getAttribute("id");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\wml\dom\WMLPElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */