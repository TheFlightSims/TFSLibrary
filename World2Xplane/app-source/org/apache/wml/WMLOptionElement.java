package org.apache.wml;

public interface WMLOptionElement extends WMLElement {
  void setValue(String paramString);
  
  String getValue();
  
  void setTitle(String paramString);
  
  String getTitle();
  
  void setOnPick(String paramString);
  
  String getOnPick();
  
  void setXmlLang(String paramString);
  
  String getXmlLang();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\wml\WMLOptionElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */