package org.apache.wml;

public interface WMLInputElement extends WMLElement {
  void setName(String paramString);
  
  String getName();
  
  void setValue(String paramString);
  
  String getValue();
  
  void setType(String paramString);
  
  String getType();
  
  void setFormat(String paramString);
  
  String getFormat();
  
  void setEmptyOk(boolean paramBoolean);
  
  boolean getEmptyOk();
  
  void setSize(int paramInt);
  
  int getSize();
  
  void setMaxLength(int paramInt);
  
  int getMaxLength();
  
  void setTitle(String paramString);
  
  String getTitle();
  
  void setTabIndex(int paramInt);
  
  int getTabIndex();
  
  void setXmlLang(String paramString);
  
  String getXmlLang();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\wml\WMLInputElement.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */