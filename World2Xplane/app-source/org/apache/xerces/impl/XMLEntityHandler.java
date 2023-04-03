package org.apache.xerces.impl;

import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XNIException;

public interface XMLEntityHandler {
  void startEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException;
  
  void endEntity(String paramString, Augmentations paramAugmentations) throws XNIException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\XMLEntityHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */