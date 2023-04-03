package org.apache.html.dom;

import org.w3c.dom.html.HTMLInputElement;

public class HTMLInputElementImpl extends HTMLElementImpl implements HTMLInputElement, HTMLFormControl {
  private static final long serialVersionUID = 640139325394332007L;
  
  public String getDefaultValue() {
    return getAttribute("defaultValue");
  }
  
  public void setDefaultValue(String paramString) {
    setAttribute("defaultValue", paramString);
  }
  
  public boolean getDefaultChecked() {
    return getBinary("defaultChecked");
  }
  
  public void setDefaultChecked(boolean paramBoolean) {
    setAttribute("defaultChecked", paramBoolean);
  }
  
  public String getAccept() {
    return getAttribute("accept");
  }
  
  public void setAccept(String paramString) {
    setAttribute("accept", paramString);
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
  
  public String getAlign() {
    return capitalize(getAttribute("align"));
  }
  
  public void setAlign(String paramString) {
    setAttribute("align", paramString);
  }
  
  public String getAlt() {
    return getAttribute("alt");
  }
  
  public void setAlt(String paramString) {
    setAttribute("alt", paramString);
  }
  
  public boolean getChecked() {
    return getBinary("checked");
  }
  
  public void setChecked(boolean paramBoolean) {
    setAttribute("checked", paramBoolean);
  }
  
  public boolean getDisabled() {
    return getBinary("disabled");
  }
  
  public void setDisabled(boolean paramBoolean) {
    setAttribute("disabled", paramBoolean);
  }
  
  public int getMaxLength() {
    return getInteger(getAttribute("maxlength"));
  }
  
  public void setMaxLength(int paramInt) {
    setAttribute("maxlength", String.valueOf(paramInt));
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
  
  public String getSize() {
    return getAttribute("size");
  }
  
  public void setSize(String paramString) {
    setAttribute("size", paramString);
  }
  
  public String getSrc() {
    return getAttribute("src");
  }
  
  public void setSrc(String paramString) {
    setAttribute("src", paramString);
  }
  
  public int getTabIndex() {
    try {
      return Integer.parseInt(getAttribute("tabindex"));
    } catch (NumberFormatException numberFormatException) {
      return 0;
    } 
  }
  
  public void setTabIndex(int paramInt) {
    setAttribute("tabindex", String.valueOf(paramInt));
  }
  
  public String getType() {
    return getAttribute("type");
  }
  
  public String getUseMap() {
    return getAttribute("useMap");
  }
  
  public void setUseMap(String paramString) {
    setAttribute("useMap", paramString);
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
  
  public void click() {}
  
  public HTMLInputElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLInputElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */