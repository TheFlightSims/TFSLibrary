package org.apache.wml.dom;

import org.apache.wml.WMLAElement;

public class WMLAElementImpl extends WMLElementImpl implements WMLAElement {
  private static final long serialVersionUID = 2628169803370301255L;
  
  public WMLAElementImpl(WMLDocumentImpl paramWMLDocumentImpl, String paramString) {
    super(paramWMLDocumentImpl, paramString);
  }
  
  public void setHref(String paramString) {
    setAttribute("href", paramString);
  }
  
  public String getHref() {
    return getAttribute("href");
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
  
  public void setId(String paramString) {
    setAttribute("id", paramString);
  }
  
  public String getId() {
    return getAttribute("id");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\wml\dom\WMLAElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */