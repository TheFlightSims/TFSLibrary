package org.apache.html.dom;

import org.w3c.dom.html.HTMLButtonElement;

public class HTMLButtonElementImpl extends HTMLElementImpl implements HTMLButtonElement, HTMLFormControl {
  private static final long serialVersionUID = -753685852948076730L;
  
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
    return capitalize(getAttribute("type"));
  }
  
  public String getValue() {
    return getAttribute("value");
  }
  
  public void setValue(String paramString) {
    setAttribute("value", paramString);
  }
  
  public HTMLButtonElementImpl(HTMLDocumentImpl paramHTMLDocumentImpl, String paramString) {
    super(paramHTMLDocumentImpl, paramString);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLButtonElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */