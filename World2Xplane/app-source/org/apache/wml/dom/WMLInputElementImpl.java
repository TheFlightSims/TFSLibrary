package org.apache.wml.dom;

import org.apache.wml.WMLInputElement;

public class WMLInputElementImpl extends WMLElementImpl implements WMLInputElement {
  private static final long serialVersionUID = 2897319793637966619L;
  
  public WMLInputElementImpl(WMLDocumentImpl paramWMLDocumentImpl, String paramString) {
    super(paramWMLDocumentImpl, paramString);
  }
  
  public void setSize(int paramInt) {
    setAttribute("size", paramInt);
  }
  
  public int getSize() {
    return getAttribute("size", 0);
  }
  
  public void setFormat(String paramString) {
    setAttribute("format", paramString);
  }
  
  public String getFormat() {
    return getAttribute("format");
  }
  
  public void setValue(String paramString) {
    setAttribute("value", paramString);
  }
  
  public String getValue() {
    return getAttribute("value");
  }
  
  public void setMaxLength(int paramInt) {
    setAttribute("maxlength", paramInt);
  }
  
  public int getMaxLength() {
    return getAttribute("maxlength", 0);
  }
  
  public void setTabIndex(int paramInt) {
    setAttribute("tabindex", paramInt);
  }
  
  public int getTabIndex() {
    return getAttribute("tabindex", 0);
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
  
  public void setEmptyOk(boolean paramBoolean) {
    setAttribute("emptyok", paramBoolean);
  }
  
  public boolean getEmptyOk() {
    return getAttribute("emptyok", false);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\wml\dom\WMLInputElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */