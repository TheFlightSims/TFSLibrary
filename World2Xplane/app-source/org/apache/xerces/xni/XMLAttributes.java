package org.apache.xerces.xni;

public interface XMLAttributes {
  int addAttribute(QName paramQName, String paramString1, String paramString2);
  
  void removeAllAttributes();
  
  void removeAttributeAt(int paramInt);
  
  int getLength();
  
  int getIndex(String paramString);
  
  int getIndex(String paramString1, String paramString2);
  
  void setName(int paramInt, QName paramQName);
  
  void getName(int paramInt, QName paramQName);
  
  String getPrefix(int paramInt);
  
  String getURI(int paramInt);
  
  String getLocalName(int paramInt);
  
  String getQName(int paramInt);
  
  void setType(int paramInt, String paramString);
  
  String getType(int paramInt);
  
  String getType(String paramString);
  
  String getType(String paramString1, String paramString2);
  
  void setValue(int paramInt, String paramString);
  
  String getValue(int paramInt);
  
  String getValue(String paramString);
  
  String getValue(String paramString1, String paramString2);
  
  void setNonNormalizedValue(int paramInt, String paramString);
  
  String getNonNormalizedValue(int paramInt);
  
  void setSpecified(int paramInt, boolean paramBoolean);
  
  boolean isSpecified(int paramInt);
  
  Augmentations getAugmentations(int paramInt);
  
  Augmentations getAugmentations(String paramString1, String paramString2);
  
  Augmentations getAugmentations(String paramString);
  
  void setAugmentations(int paramInt, Augmentations paramAugmentations);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\XMLAttributes.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */