package org.apache.xerces.xpointer;

import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xs.AttributePSVI;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;

class ShortHandPointer implements XPointerPart {
  private String fShortHandPointer;
  
  private boolean fIsFragmentResolved = false;
  
  private SymbolTable fSymbolTable;
  
  int fMatchingChildCount = 0;
  
  public ShortHandPointer() {}
  
  public ShortHandPointer(SymbolTable paramSymbolTable) {
    this.fSymbolTable = paramSymbolTable;
  }
  
  public void parseXPointer(String paramString) throws XNIException {
    this.fShortHandPointer = paramString;
    this.fIsFragmentResolved = false;
  }
  
  public boolean resolveXPointer(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations, int paramInt) throws XNIException {
    if (this.fMatchingChildCount == 0)
      this.fIsFragmentResolved = false; 
    if (paramInt == 0) {
      if (this.fMatchingChildCount == 0)
        this.fIsFragmentResolved = hasMatchingIdentifier(paramQName, paramXMLAttributes, paramAugmentations, paramInt); 
      if (this.fIsFragmentResolved)
        this.fMatchingChildCount++; 
    } else if (paramInt == 2) {
      if (this.fMatchingChildCount == 0)
        this.fIsFragmentResolved = hasMatchingIdentifier(paramQName, paramXMLAttributes, paramAugmentations, paramInt); 
    } else if (this.fIsFragmentResolved) {
      this.fMatchingChildCount--;
    } 
    return this.fIsFragmentResolved;
  }
  
  private boolean hasMatchingIdentifier(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations, int paramInt) throws XNIException {
    String str = null;
    if (paramXMLAttributes != null)
      for (byte b = 0; b < paramXMLAttributes.getLength(); b++) {
        str = getSchemaDeterminedID(paramXMLAttributes, b);
        if (str != null)
          break; 
        str = getChildrenSchemaDeterminedID(paramXMLAttributes, b);
        if (str != null)
          break; 
        str = getDTDDeterminedID(paramXMLAttributes, b);
        if (str != null)
          break; 
      }  
    return (str != null && str.equals(this.fShortHandPointer));
  }
  
  public String getDTDDeterminedID(XMLAttributes paramXMLAttributes, int paramInt) throws XNIException {
    return paramXMLAttributes.getType(paramInt).equals("ID") ? paramXMLAttributes.getValue(paramInt) : null;
  }
  
  public String getSchemaDeterminedID(XMLAttributes paramXMLAttributes, int paramInt) throws XNIException {
    Augmentations augmentations = paramXMLAttributes.getAugmentations(paramInt);
    AttributePSVI attributePSVI = (AttributePSVI)augmentations.getItem("ATTRIBUTE_PSVI");
    if (attributePSVI != null) {
      XSTypeDefinition xSTypeDefinition;
      XSSimpleTypeDefinition xSSimpleTypeDefinition = attributePSVI.getMemberTypeDefinition();
      if (xSSimpleTypeDefinition != null)
        xSTypeDefinition = attributePSVI.getTypeDefinition(); 
      if (xSTypeDefinition != null && ((XSSimpleType)xSTypeDefinition).isIDType())
        return attributePSVI.getSchemaNormalizedValue(); 
    } 
    return null;
  }
  
  public String getChildrenSchemaDeterminedID(XMLAttributes paramXMLAttributes, int paramInt) throws XNIException {
    return null;
  }
  
  public boolean isFragmentResolved() {
    return this.fIsFragmentResolved;
  }
  
  public boolean isChildFragmentResolved() {
    return (this.fIsFragmentResolved && this.fMatchingChildCount > 0);
  }
  
  public String getSchemeName() {
    return this.fShortHandPointer;
  }
  
  public String getSchemeData() {
    return null;
  }
  
  public void setSchemeName(String paramString) {
    this.fShortHandPointer = paramString;
  }
  
  public void setSchemeData(String paramString) {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xpointer\ShortHandPointer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */