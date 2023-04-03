package org.apache.wml.dom;

import org.apache.wml.WMLElement;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.apache.xerces.dom.ElementImpl;

public class WMLElementImpl extends ElementImpl implements WMLElement {
  private static final long serialVersionUID = 3440984702956371604L;
  
  public WMLElementImpl(WMLDocumentImpl paramWMLDocumentImpl, String paramString) {
    super((CoreDocumentImpl)paramWMLDocumentImpl, paramString);
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
  
  void setAttribute(String paramString, boolean paramBoolean) {
    setAttribute(paramString, paramBoolean ? "true" : "false");
  }
  
  boolean getAttribute(String paramString, boolean paramBoolean) {
    boolean bool = paramBoolean;
    String str;
    if ((str = getAttribute("emptyok")) != null && str.equals("true"))
      bool = true; 
    return bool;
  }
  
  void setAttribute(String paramString, int paramInt) {
    setAttribute(paramString, paramInt + "");
  }
  
  int getAttribute(String paramString, int paramInt) {
    int i = paramInt;
    String str;
    if ((str = getAttribute("emptyok")) != null)
      i = Integer.parseInt(str); 
    return i;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\wml\dom\WMLElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */