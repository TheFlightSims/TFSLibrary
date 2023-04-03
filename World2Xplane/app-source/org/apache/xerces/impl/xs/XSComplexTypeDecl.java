package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import org.apache.xerces.impl.xs.models.CMBuilder;
import org.apache.xerces.impl.xs.models.XSCMValidator;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.xs.XSAttributeUse;
import org.apache.xerces.xs.XSComplexTypeDefinition;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSParticle;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xs.XSWildcard;
import org.w3c.dom.TypeInfo;

public class XSComplexTypeDecl implements XSComplexTypeDefinition, TypeInfo {
  String fName = null;
  
  String fTargetNamespace = null;
  
  XSTypeDefinition fBaseType = null;
  
  short fDerivedBy = 2;
  
  short fFinal = 0;
  
  short fBlock = 0;
  
  short fMiscFlags = 0;
  
  XSAttributeGroupDecl fAttrGrp = null;
  
  short fContentType = 0;
  
  XSSimpleType fXSSimpleType = null;
  
  XSParticleDecl fParticle = null;
  
  XSCMValidator fCMValidator = null;
  
  XSCMValidator fUPACMValidator = null;
  
  XSObjectListImpl fAnnotations = null;
  
  static final int DERIVATION_ANY = 0;
  
  static final int DERIVATION_RESTRICTION = 1;
  
  static final int DERIVATION_EXTENSION = 2;
  
  static final int DERIVATION_UNION = 4;
  
  static final int DERIVATION_LIST = 8;
  
  private static final short CT_IS_ABSTRACT = 1;
  
  private static final short CT_HAS_TYPE_ID = 2;
  
  private static final short CT_IS_ANONYMOUS = 4;
  
  public void setValues(String paramString1, String paramString2, XSTypeDefinition paramXSTypeDefinition, short paramShort1, short paramShort2, short paramShort3, short paramShort4, boolean paramBoolean, XSAttributeGroupDecl paramXSAttributeGroupDecl, XSSimpleType paramXSSimpleType, XSParticleDecl paramXSParticleDecl, XSObjectListImpl paramXSObjectListImpl) {
    this.fTargetNamespace = paramString2;
    this.fBaseType = paramXSTypeDefinition;
    this.fDerivedBy = paramShort1;
    this.fFinal = paramShort2;
    this.fBlock = paramShort3;
    this.fContentType = paramShort4;
    if (paramBoolean)
      this.fMiscFlags = (short)(this.fMiscFlags | 0x1); 
    this.fAttrGrp = paramXSAttributeGroupDecl;
    this.fXSSimpleType = paramXSSimpleType;
    this.fParticle = paramXSParticleDecl;
    this.fAnnotations = paramXSObjectListImpl;
  }
  
  public void setName(String paramString) {
    this.fName = paramString;
  }
  
  public short getTypeCategory() {
    return 15;
  }
  
  public String getTypeName() {
    return this.fName;
  }
  
  public short getFinalSet() {
    return this.fFinal;
  }
  
  public String getTargetNamespace() {
    return this.fTargetNamespace;
  }
  
  public boolean containsTypeID() {
    return ((this.fMiscFlags & 0x2) != 0);
  }
  
  public void setIsAbstractType() {
    this.fMiscFlags = (short)(this.fMiscFlags | 0x1);
  }
  
  public void setContainsTypeID() {
    this.fMiscFlags = (short)(this.fMiscFlags | 0x2);
  }
  
  public void setIsAnonymous() {
    this.fMiscFlags = (short)(this.fMiscFlags | 0x4);
  }
  
  public XSCMValidator getContentModel(CMBuilder paramCMBuilder) {
    return getContentModel(paramCMBuilder, false);
  }
  
  public synchronized XSCMValidator getContentModel(CMBuilder paramCMBuilder, boolean paramBoolean) {
    if (this.fCMValidator == null) {
      if (paramBoolean) {
        if (this.fUPACMValidator == null) {
          this.fUPACMValidator = paramCMBuilder.getContentModel(this, true);
          if (this.fUPACMValidator != null && !this.fUPACMValidator.isCompactedForUPA())
            this.fCMValidator = this.fUPACMValidator; 
        } 
        return this.fUPACMValidator;
      } 
      this.fCMValidator = paramCMBuilder.getContentModel(this, false);
    } 
    return this.fCMValidator;
  }
  
  public XSAttributeGroupDecl getAttrGrp() {
    return this.fAttrGrp;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    appendTypeInfo(stringBuffer);
    return stringBuffer.toString();
  }
  
  void appendTypeInfo(StringBuffer paramStringBuffer) {
    String[] arrayOfString1 = { "EMPTY", "SIMPLE", "ELEMENT", "MIXED" };
    String[] arrayOfString2 = { "EMPTY", "EXTENSION", "RESTRICTION" };
    paramStringBuffer.append("Complex type name='").append(this.fTargetNamespace).append(",").append(getTypeName()).append("', ");
    if (this.fBaseType != null)
      paramStringBuffer.append(" base type name='").append(this.fBaseType.getName()).append("', "); 
    paramStringBuffer.append(" content type='").append(arrayOfString1[this.fContentType]).append("', ");
    paramStringBuffer.append(" isAbstract='").append(getAbstract()).append("', ");
    paramStringBuffer.append(" hasTypeId='").append(containsTypeID()).append("', ");
    paramStringBuffer.append(" final='").append(this.fFinal).append("', ");
    paramStringBuffer.append(" block='").append(this.fBlock).append("', ");
    if (this.fParticle != null)
      paramStringBuffer.append(" particle='").append(this.fParticle.toString()).append("', "); 
    paramStringBuffer.append(" derivedBy='").append(arrayOfString2[this.fDerivedBy]).append("'. ");
  }
  
  public boolean derivedFromType(XSTypeDefinition paramXSTypeDefinition, short paramShort) {
    XSTypeDefinition xSTypeDefinition;
    if (paramXSTypeDefinition == null)
      return false; 
    if (paramXSTypeDefinition == SchemaGrammar.fAnyType)
      return true; 
    XSComplexTypeDecl xSComplexTypeDecl = this;
    while (xSComplexTypeDecl != paramXSTypeDefinition && xSComplexTypeDecl != SchemaGrammar.fAnySimpleType && xSComplexTypeDecl != SchemaGrammar.fAnyType)
      xSTypeDefinition = xSComplexTypeDecl.getBaseType(); 
    return (xSTypeDefinition == paramXSTypeDefinition);
  }
  
  public boolean derivedFrom(String paramString1, String paramString2, short paramShort) {
    XSTypeDefinition xSTypeDefinition;
    if (paramString2 == null)
      return false; 
    if (paramString1 != null && paramString1.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && paramString2.equals("anyType"))
      return true; 
    XSComplexTypeDecl xSComplexTypeDecl = this;
    while ((!paramString2.equals(xSComplexTypeDecl.getName()) || ((paramString1 != null || xSComplexTypeDecl.getNamespace() != null) && (paramString1 == null || !paramString1.equals(xSComplexTypeDecl.getNamespace())))) && xSComplexTypeDecl != SchemaGrammar.fAnySimpleType && xSComplexTypeDecl != SchemaGrammar.fAnyType)
      xSTypeDefinition = xSComplexTypeDecl.getBaseType(); 
    return (xSTypeDefinition != SchemaGrammar.fAnySimpleType && xSTypeDefinition != SchemaGrammar.fAnyType);
  }
  
  public boolean isDOMDerivedFrom(String paramString1, String paramString2, int paramInt) {
    if (paramString2 == null)
      return false; 
    if (paramString1 != null && paramString1.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && paramString2.equals("anyType") && paramInt == 1 && paramInt == 2)
      return true; 
    if ((paramInt & 0x1) != 0 && isDerivedByRestriction(paramString1, paramString2, paramInt, (XSTypeDefinition)this))
      return true; 
    if ((paramInt & 0x2) != 0 && isDerivedByExtension(paramString1, paramString2, paramInt, (XSTypeDefinition)this))
      return true; 
    if (((paramInt & 0x8) != 0 || (paramInt & 0x4) != 0) && (paramInt & 0x1) == 0 && (paramInt & 0x2) == 0) {
      if (paramString1.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && paramString2.equals("anyType"))
        paramString2 = "anySimpleType"; 
      if (!this.fName.equals("anyType") || !this.fTargetNamespace.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA)) {
        if (this.fBaseType != null && this.fBaseType instanceof XSSimpleTypeDecl)
          return ((XSSimpleTypeDecl)this.fBaseType).isDOMDerivedFrom(paramString1, paramString2, paramInt); 
        if (this.fBaseType != null && this.fBaseType instanceof XSComplexTypeDecl)
          return ((XSComplexTypeDecl)this.fBaseType).isDOMDerivedFrom(paramString1, paramString2, paramInt); 
      } 
    } 
    return ((paramInt & 0x2) == 0 && (paramInt & 0x1) == 0 && (paramInt & 0x8) == 0 && (paramInt & 0x4) == 0) ? isDerivedByAny(paramString1, paramString2, paramInt, (XSTypeDefinition)this) : false;
  }
  
  private boolean isDerivedByAny(String paramString1, String paramString2, int paramInt, XSTypeDefinition paramXSTypeDefinition) {
    XSTypeDefinition xSTypeDefinition = null;
    boolean bool = false;
    while (paramXSTypeDefinition != null && paramXSTypeDefinition != xSTypeDefinition) {
      if (paramString2.equals(paramXSTypeDefinition.getName()) && ((paramString1 == null && paramXSTypeDefinition.getNamespace() == null) || (paramString1 != null && paramString1.equals(paramXSTypeDefinition.getNamespace())))) {
        bool = true;
        break;
      } 
      if (isDerivedByRestriction(paramString1, paramString2, paramInt, paramXSTypeDefinition))
        return true; 
      if (!isDerivedByExtension(paramString1, paramString2, paramInt, paramXSTypeDefinition))
        return true; 
      xSTypeDefinition = paramXSTypeDefinition;
      paramXSTypeDefinition = paramXSTypeDefinition.getBaseType();
    } 
    return bool;
  }
  
  private boolean isDerivedByRestriction(String paramString1, String paramString2, int paramInt, XSTypeDefinition paramXSTypeDefinition) {
    XSTypeDefinition xSTypeDefinition = null;
    while (paramXSTypeDefinition != null && paramXSTypeDefinition != xSTypeDefinition) {
      if (paramString1 != null && paramString1.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && paramString2.equals("anySimpleType"))
        return false; 
      if ((paramString2.equals(paramXSTypeDefinition.getName()) && paramString1 != null && paramString1.equals(paramXSTypeDefinition.getNamespace())) || (paramXSTypeDefinition.getNamespace() == null && paramString1 == null))
        return true; 
      if (paramXSTypeDefinition instanceof XSSimpleTypeDecl) {
        if (paramString1.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && paramString2.equals("anyType"))
          paramString2 = "anySimpleType"; 
        return ((XSSimpleTypeDecl)paramXSTypeDefinition).isDOMDerivedFrom(paramString1, paramString2, paramInt);
      } 
      if (((XSComplexTypeDecl)paramXSTypeDefinition).getDerivationMethod() != 2)
        return false; 
      xSTypeDefinition = paramXSTypeDefinition;
      paramXSTypeDefinition = paramXSTypeDefinition.getBaseType();
    } 
    return false;
  }
  
  private boolean isDerivedByExtension(String paramString1, String paramString2, int paramInt, XSTypeDefinition paramXSTypeDefinition) {
    boolean bool = false;
    XSTypeDefinition xSTypeDefinition = null;
    while (paramXSTypeDefinition != null && paramXSTypeDefinition != xSTypeDefinition && (paramString1 == null || !paramString1.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) || !paramString2.equals("anySimpleType") || !SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(paramXSTypeDefinition.getNamespace()) || !"anyType".equals(paramXSTypeDefinition.getName()))) {
      if (paramString2.equals(paramXSTypeDefinition.getName()) && ((paramString1 == null && paramXSTypeDefinition.getNamespace() == null) || (paramString1 != null && paramString1.equals(paramXSTypeDefinition.getNamespace()))))
        return bool; 
      if (paramXSTypeDefinition instanceof XSSimpleTypeDecl) {
        if (paramString1.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && paramString2.equals("anyType"))
          paramString2 = "anySimpleType"; 
        return ((paramInt & 0x2) != 0) ? (bool & ((XSSimpleTypeDecl)paramXSTypeDefinition).isDOMDerivedFrom(paramString1, paramString2, paramInt & 0x1)) : (bool & ((XSSimpleTypeDecl)paramXSTypeDefinition).isDOMDerivedFrom(paramString1, paramString2, paramInt));
      } 
      if (((XSComplexTypeDecl)paramXSTypeDefinition).getDerivationMethod() == 1)
        int i = bool | true; 
      xSTypeDefinition = paramXSTypeDefinition;
      paramXSTypeDefinition = paramXSTypeDefinition.getBaseType();
    } 
    return false;
  }
  
  public void reset() {
    this.fName = null;
    this.fTargetNamespace = null;
    this.fBaseType = null;
    this.fDerivedBy = 2;
    this.fFinal = 0;
    this.fBlock = 0;
    this.fMiscFlags = 0;
    this.fAttrGrp.reset();
    this.fContentType = 0;
    this.fXSSimpleType = null;
    this.fParticle = null;
    this.fCMValidator = null;
    this.fUPACMValidator = null;
    if (this.fAnnotations != null)
      this.fAnnotations.clear(); 
    this.fAnnotations = null;
  }
  
  public short getType() {
    return 3;
  }
  
  public String getName() {
    return getAnonymous() ? null : this.fName;
  }
  
  public boolean getAnonymous() {
    return ((this.fMiscFlags & 0x4) != 0);
  }
  
  public String getNamespace() {
    return this.fTargetNamespace;
  }
  
  public XSTypeDefinition getBaseType() {
    return this.fBaseType;
  }
  
  public short getDerivationMethod() {
    return this.fDerivedBy;
  }
  
  public boolean isFinal(short paramShort) {
    return ((this.fFinal & paramShort) != 0);
  }
  
  public short getFinal() {
    return this.fFinal;
  }
  
  public boolean getAbstract() {
    return ((this.fMiscFlags & 0x1) != 0);
  }
  
  public XSObjectList getAttributeUses() {
    return this.fAttrGrp.getAttributeUses();
  }
  
  public XSWildcard getAttributeWildcard() {
    return this.fAttrGrp.getAttributeWildcard();
  }
  
  public short getContentType() {
    return this.fContentType;
  }
  
  public XSSimpleTypeDefinition getSimpleType() {
    return (XSSimpleTypeDefinition)this.fXSSimpleType;
  }
  
  public XSParticle getParticle() {
    return this.fParticle;
  }
  
  public boolean isProhibitedSubstitution(short paramShort) {
    return ((this.fBlock & paramShort) != 0);
  }
  
  public short getProhibitedSubstitutions() {
    return this.fBlock;
  }
  
  public XSObjectList getAnnotations() {
    return (this.fAnnotations != null) ? (XSObjectList)this.fAnnotations : XSObjectListImpl.EMPTY_LIST;
  }
  
  public XSNamespaceItem getNamespaceItem() {
    return null;
  }
  
  public XSAttributeUse getAttributeUse(String paramString1, String paramString2) {
    return this.fAttrGrp.getAttributeUse(paramString1, paramString2);
  }
  
  public String getTypeNamespace() {
    return getNamespace();
  }
  
  public boolean isDerivedFrom(String paramString1, String paramString2, int paramInt) {
    return isDOMDerivedFrom(paramString1, paramString2, paramInt);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\XSComplexTypeDecl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */