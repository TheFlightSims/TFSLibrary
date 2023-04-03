package org.apache.wml.dom;

import org.apache.wml.WMLOneventElement;

public class WMLOneventElementImpl extends WMLElementImpl implements WMLOneventElement {
  private static final long serialVersionUID = -4279215241146570871L;
  
  public WMLOneventElementImpl(WMLDocumentImpl paramWMLDocumentImpl, String paramString) {
    super(paramWMLDocumentImpl, paramString);
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
  
  public void setType(String paramString) {
    setAttribute("type", paramString);
  }
  
  public String getType() {
    return getAttribute("type");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\wml\dom\WMLOneventElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */