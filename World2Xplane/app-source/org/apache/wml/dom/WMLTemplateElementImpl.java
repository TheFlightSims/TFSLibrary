package org.apache.wml.dom;

import org.apache.wml.WMLTemplateElement;

public class WMLTemplateElementImpl extends WMLElementImpl implements WMLTemplateElement {
  private static final long serialVersionUID = 4231732841621131049L;
  
  public WMLTemplateElementImpl(WMLDocumentImpl paramWMLDocumentImpl, String paramString) {
    super(paramWMLDocumentImpl, paramString);
  }
  
  public void setOnTimer(String paramString) {
    setAttribute("ontimer", paramString);
  }
  
  public String getOnTimer() {
    return getAttribute("ontimer");
  }
  
  public void setOnEnterBackward(String paramString) {
    setAttribute("onenterbackward", paramString);
  }
  
  public String getOnEnterBackward() {
    return getAttribute("onenterbackward");
  }
  
  public void setClassName(String paramString) {
    setAttribute("class", paramString);
  }
  
  public String getClassName() {
    return getAttribute("class");
  }
  
  public void setId(String paramString) {
    setAttribute("id", paramString);
  }
  
  public String getId() {
    return getAttribute("id");
  }
  
  public void setOnEnterForward(String paramString) {
    setAttribute("onenterforward", paramString);
  }
  
  public String getOnEnterForward() {
    return getAttribute("onenterforward");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\wml\dom\WMLTemplateElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */