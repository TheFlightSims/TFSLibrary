package org.apache.wml.dom;

import org.apache.wml.WMLCardElement;

public class WMLCardElementImpl extends WMLElementImpl implements WMLCardElement {
  private static final long serialVersionUID = -3571126568344328924L;
  
  public WMLCardElementImpl(WMLDocumentImpl paramWMLDocumentImpl, String paramString) {
    super(paramWMLDocumentImpl, paramString);
  }
  
  public void setOnTimer(String paramString) {
    setAttribute("ontimer", paramString);
  }
  
  public String getOnTimer() {
    return getAttribute("ontimer");
  }
  
  public void setOrdered(boolean paramBoolean) {
    setAttribute("ordered", paramBoolean);
  }
  
  public boolean getOrdered() {
    return getAttribute("ordered", true);
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
  
  public void setXmlLang(String paramString) {
    setAttribute("xml:lang", paramString);
  }
  
  public String getXmlLang() {
    return getAttribute("xml:lang");
  }
  
  public void setTitle(String paramString) {
    setAttribute("title", paramString);
  }
  
  public String getTitle() {
    return getAttribute("title");
  }
  
  public void setNewContext(boolean paramBoolean) {
    setAttribute("newcontext", paramBoolean);
  }
  
  public boolean getNewContext() {
    return getAttribute("newcontext", false);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\wml\dom\WMLCardElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */