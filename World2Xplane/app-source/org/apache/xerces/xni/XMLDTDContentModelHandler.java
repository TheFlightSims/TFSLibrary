package org.apache.xerces.xni;

import org.apache.xerces.xni.parser.XMLDTDContentModelSource;

public interface XMLDTDContentModelHandler {
  public static final short SEPARATOR_CHOICE = 0;
  
  public static final short SEPARATOR_SEQUENCE = 1;
  
  public static final short OCCURS_ZERO_OR_ONE = 2;
  
  public static final short OCCURS_ZERO_OR_MORE = 3;
  
  public static final short OCCURS_ONE_OR_MORE = 4;
  
  void startContentModel(String paramString, Augmentations paramAugmentations) throws XNIException;
  
  void any(Augmentations paramAugmentations) throws XNIException;
  
  void empty(Augmentations paramAugmentations) throws XNIException;
  
  void startGroup(Augmentations paramAugmentations) throws XNIException;
  
  void pcdata(Augmentations paramAugmentations) throws XNIException;
  
  void element(String paramString, Augmentations paramAugmentations) throws XNIException;
  
  void separator(short paramShort, Augmentations paramAugmentations) throws XNIException;
  
  void occurrence(short paramShort, Augmentations paramAugmentations) throws XNIException;
  
  void endGroup(Augmentations paramAugmentations) throws XNIException;
  
  void endContentModel(Augmentations paramAugmentations) throws XNIException;
  
  void setDTDContentModelSource(XMLDTDContentModelSource paramXMLDTDContentModelSource);
  
  XMLDTDContentModelSource getDTDContentModelSource();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xni\XMLDTDContentModelHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */