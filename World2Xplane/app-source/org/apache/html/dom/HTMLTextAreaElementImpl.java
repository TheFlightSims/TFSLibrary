package org.apache.html.dom;

import org.w3c.dom.html.HTMLTextAreaElement;

public class HTMLTextAreaElementImpl extends HTMLElementImpl implements HTMLTextAreaElement, HTMLFormControl {
  private static final long serialVersionUID = -6737778308542678104L;
  
  public String getDefaultValue() {
    return getAttribute("default-value");
  }
  
  public void setDefaultValue(String paramString) {
    setAttribute("default-value", paramString);
  }
  
  public String getAccessKey() {
    String str = getAttribute("accesskey");
    if (str != null && str.length() > 1)
      str = str.substring(0, 1); 
    return str;
  }
  
  public void setAccessKey(String paramString) {
    if (paramString != null && paramString.length() > 1)
      paramString = paramString.substring(0, 1); 
    setAttribute("accesskey", paramString);
  }
  
  public int getCols() {
    return getInteger(getAttribute("cols"));
  }
  
  public void setCols(int paramInt) {
    setAttribute("cols", String.valueOf(paramInt));
  }
  
  public boolean getDisabled() {
    return getBinary("disabled");
  }
  
  public void setDisabled(boolean paramBoolean) {
    setAttribute("disabled", paramBoolean);
  }
  
  public String getName() {
    return getAttribute("name");
  }
  
  public void setName(String paramString) {
    setAttribute("name", paramString);
  }
  
  public boolean getReadOnly() {
    return getBinary("readonly");
  }
  
  public void setReadOnly(boolean paramBoolean) {
    setAttribute("readonly", paramBoolean);
  }
  
  public int getRows() {
    return getInteger(getAttribute("rows"));
  }
  
  public void setRows(int paramInt) {
    setAttribute("rows", String.valueOf(paramInt));
  }
  
  public int getTabIndex() {
    return getInteger(getAttribute("tabindex"));
  }
  
  public void setTabIndex(int paramInt) {
    setAttribute("tabindex", String.valueOf(paramInt));
  }
  
  public String getType() {
    return getAttribute("type");
  }
  
  public String getValue() {
    return getAttribute("value");
  }
  
  public void setValue(String paramString) {
    setAttribute("value", paramString);
  }
  
  public void blur() {}
  
  public void focus() {}
  
  public void select() {}
  
  public HTMLTextAreaElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLTextAreaElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */