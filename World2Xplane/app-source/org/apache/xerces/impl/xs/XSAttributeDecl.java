package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSAnnotation;
import org.apache.xerces.xs.XSAttributeDeclaration;
import org.apache.xerces.xs.XSComplexTypeDefinition;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSSimpleTypeDefinition;

public class XSAttributeDecl implements XSAttributeDeclaration {
  public static final short SCOPE_ABSENT = 0;
  
  public static final short SCOPE_GLOBAL = 1;
  
  public static final short SCOPE_LOCAL = 2;
  
  String fName = null;
  
  String fTargetNamespace = null;
  
  XSSimpleType fType = null;
  
  short fConstraintType = 0;
  
  short fScope = 0;
  
  XSComplexTypeDecl fEnclosingCT = null;
  
  XSObjectList fAnnotations = null;
  
  ValidatedInfo fDefault = null;
  
  public void setValues(String paramString1, String paramString2, XSSimpleType paramXSSimpleType, short paramShort1, short paramShort2, ValidatedInfo paramValidatedInfo, XSComplexTypeDecl paramXSComplexTypeDecl, XSObjectList paramXSObjectList) {
    this.fName = paramString1;
    this.fTargetNamespace = paramString2;
    this.fType = paramXSSimpleType;
    this.fConstraintType = paramShort1;
    this.fScope = paramShort2;
    this.fDefault = paramValidatedInfo;
    this.fEnclosingCT = paramXSComplexTypeDecl;
    this.fAnnotations = paramXSObjectList;
  }
  
  public void reset() {
    this.fName = null;
    this.fTargetNamespace = null;
    this.fType = null;
    this.fConstraintType = 0;
    this.fScope = 0;
    this.fDefault = null;
    this.fAnnotations = null;
  }
  
  public short getType() {
    return 1;
  }
  
  public String getName() {
    return this.fName;
  }
  
  public String getNamespace() {
    return this.fTargetNamespace;
  }
  
  public XSSimpleTypeDefinition getTypeDefinition() {
    return (XSSimpleTypeDefinition)this.fType;
  }
  
  public short getScope() {
    return this.fScope;
  }
  
  public XSComplexTypeDefinition getEnclosingCTDefinition() {
    return this.fEnclosingCT;
  }
  
  public short getConstraintType() {
    return this.fConstraintType;
  }
  
  public String getConstraintValue() {
    return (getConstraintType() == 0) ? null : this.fDefault.stringValue();
  }
  
  public XSAnnotation getAnnotation() {
    return (this.fAnnotations != null) ? (XSAnnotation)this.fAnnotations.item(0) : null;
  }
  
  public XSObjectList getAnnotations() {
    return (this.fAnnotations != null) ? this.fAnnotations : XSObjectListImpl.EMPTY_LIST;
  }
  
  public ValidatedInfo getValInfo() {
    return this.fDefault;
  }
  
  public XSNamespaceItem getNamespaceItem() {
    return null;
  }
  
  public Object getActualVC() {
    return (getConstraintType() == 0) ? null : this.fDefault.actualValue;
  }
  
  public short getActualVCType() {
    return (getConstraintType() == 0) ? 45 : this.fDefault.actualValueType;
  }
  
  public ShortList getItemValueTypes() {
    return (getConstraintType() == 0) ? null : this.fDefault.itemValueTypes;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\XSAttributeDecl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */