package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.XSAnnotationImpl;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.XSConstraints;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSTerm;
import org.apache.xerces.xs.XSTypeDefinition;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

class XSDElementTraverser extends XSDAbstractTraverser {
  protected final XSElementDecl fTempElementDecl = new XSElementDecl();
  
  boolean fDeferTraversingLocalElements;
  
  XSDElementTraverser(XSDHandler paramXSDHandler, XSAttributeChecker paramXSAttributeChecker) {
    super(paramXSDHandler, paramXSAttributeChecker);
  }
  
  XSParticleDecl traverseLocal(Element paramElement, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar, int paramInt, XSObject paramXSObject) {
    XSParticleDecl xSParticleDecl = null;
    if (this.fSchemaHandler.fDeclPool != null) {
      xSParticleDecl = this.fSchemaHandler.fDeclPool.getParticleDecl();
    } else {
      xSParticleDecl = new XSParticleDecl();
    } 
    if (this.fDeferTraversingLocalElements) {
      xSParticleDecl.fType = 1;
      Attr attr = paramElement.getAttributeNode(SchemaSymbols.ATT_MINOCCURS);
      if (attr != null) {
        String str = attr.getValue();
        try {
          int i = Integer.parseInt(XMLChar.trim(str));
          if (i >= 0)
            xSParticleDecl.fMinOccurs = i; 
        } catch (NumberFormatException numberFormatException) {}
      } 
      this.fSchemaHandler.fillInLocalElemInfo(paramElement, paramXSDocumentInfo, paramInt, paramXSObject, xSParticleDecl);
    } else {
      traverseLocal(xSParticleDecl, paramElement, paramXSDocumentInfo, paramSchemaGrammar, paramInt, paramXSObject, null);
      if (xSParticleDecl.fType == 0)
        xSParticleDecl = null; 
    } 
    return xSParticleDecl;
  }
  
  protected void traverseLocal(XSParticleDecl paramXSParticleDecl, Element paramElement, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar, int paramInt, XSObject paramXSObject, String[] paramArrayOfString) {
    if (paramArrayOfString != null)
      paramXSDocumentInfo.fNamespaceSupport.setEffectiveContext(paramArrayOfString); 
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(paramElement, false, paramXSDocumentInfo);
    QName qName = (QName)arrayOfObject[XSAttributeChecker.ATTIDX_REF];
    XInt xInt1 = (XInt)arrayOfObject[XSAttributeChecker.ATTIDX_MINOCCURS];
    XInt xInt2 = (XInt)arrayOfObject[XSAttributeChecker.ATTIDX_MAXOCCURS];
    XSElementDecl xSElementDecl = null;
    XSAnnotationImpl xSAnnotationImpl = null;
    if (paramElement.getAttributeNode(SchemaSymbols.ATT_REF) != null) {
      if (qName != null) {
        xSElementDecl = (XSElementDecl)this.fSchemaHandler.getGlobalDecl(paramXSDocumentInfo, 3, qName, paramElement);
        Element element = DOMUtil.getFirstChildElement(paramElement);
        if (element != null && DOMUtil.getLocalName(element).equals(SchemaSymbols.ELT_ANNOTATION)) {
          xSAnnotationImpl = traverseAnnotationDecl(element, arrayOfObject, false, paramXSDocumentInfo);
          element = DOMUtil.getNextSiblingElement(element);
        } else {
          String str = DOMUtil.getSyntheticAnnotation(paramElement);
          if (str != null)
            xSAnnotationImpl = traverseSyntheticAnnotation(paramElement, str, arrayOfObject, false, paramXSDocumentInfo); 
        } 
        if (element != null)
          reportSchemaError("src-element.2.2", new Object[] { qName.rawname, DOMUtil.getLocalName(element) }, element); 
      } else {
        xSElementDecl = null;
      } 
    } else {
      xSElementDecl = traverseNamedElement(paramElement, arrayOfObject, paramXSDocumentInfo, paramSchemaGrammar, false, paramXSObject);
    } 
    paramXSParticleDecl.fMinOccurs = xInt1.intValue();
    paramXSParticleDecl.fMaxOccurs = xInt2.intValue();
    if (xSElementDecl != null) {
      paramXSParticleDecl.fType = 1;
      paramXSParticleDecl.fValue = (XSTerm)xSElementDecl;
    } else {
      paramXSParticleDecl.fType = 0;
    } 
    if (qName != null) {
      XSObjectList xSObjectList;
      if (xSAnnotationImpl != null) {
        XSObjectListImpl xSObjectListImpl = new XSObjectListImpl();
        xSObjectListImpl.add((XSObject)xSAnnotationImpl);
      } else {
        xSObjectList = XSObjectListImpl.EMPTY_LIST;
      } 
      paramXSParticleDecl.fAnnotations = xSObjectList;
    } else {
      paramXSParticleDecl.fAnnotations = (xSElementDecl != null) ? xSElementDecl.fAnnotations : XSObjectListImpl.EMPTY_LIST;
    } 
    Long long_ = (Long)arrayOfObject[XSAttributeChecker.ATTIDX_FROMDEFAULT];
    checkOccurrences(paramXSParticleDecl, SchemaSymbols.ELT_ELEMENT, (Element)paramElement.getParentNode(), paramInt, long_.longValue());
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
  }
  
  XSElementDecl traverseGlobal(Element paramElement, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(paramElement, true, paramXSDocumentInfo);
    XSElementDecl xSElementDecl = traverseNamedElement(paramElement, arrayOfObject, paramXSDocumentInfo, paramSchemaGrammar, true, null);
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
    return xSElementDecl;
  }
  
  XSElementDecl traverseNamedElement(Element paramElement, Object[] paramArrayOfObject, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar, boolean paramBoolean, XSObject paramXSObject) {
    XSObjectList xSObjectList;
    XSSimpleType xSSimpleType;
    XSTypeDefinition xSTypeDefinition;
    XSComplexTypeDecl xSComplexTypeDecl1;
    Boolean bool1 = (Boolean)paramArrayOfObject[XSAttributeChecker.ATTIDX_ABSTRACT];
    XInt xInt1 = (XInt)paramArrayOfObject[XSAttributeChecker.ATTIDX_BLOCK];
    String str1 = (String)paramArrayOfObject[XSAttributeChecker.ATTIDX_DEFAULT];
    XInt xInt2 = (XInt)paramArrayOfObject[XSAttributeChecker.ATTIDX_FINAL];
    String str2 = (String)paramArrayOfObject[XSAttributeChecker.ATTIDX_FIXED];
    XInt xInt3 = (XInt)paramArrayOfObject[XSAttributeChecker.ATTIDX_FORM];
    String str3 = (String)paramArrayOfObject[XSAttributeChecker.ATTIDX_NAME];
    Boolean bool2 = (Boolean)paramArrayOfObject[XSAttributeChecker.ATTIDX_NILLABLE];
    QName qName1 = (QName)paramArrayOfObject[XSAttributeChecker.ATTIDX_SUBSGROUP];
    QName qName2 = (QName)paramArrayOfObject[XSAttributeChecker.ATTIDX_TYPE];
    XSElementDecl xSElementDecl = null;
    if (this.fSchemaHandler.fDeclPool != null) {
      xSElementDecl = this.fSchemaHandler.fDeclPool.getElementDecl();
    } else {
      xSElementDecl = new XSElementDecl();
    } 
    if (str3 != null)
      xSElementDecl.fName = this.fSymbolTable.addSymbol(str3); 
    if (paramBoolean) {
      xSElementDecl.fTargetNamespace = paramXSDocumentInfo.fTargetNamespace;
      xSElementDecl.setIsGlobal();
    } else {
      if (paramXSObject instanceof XSComplexTypeDecl)
        xSElementDecl.setIsLocal((XSComplexTypeDecl)paramXSObject); 
      if (xInt3 != null) {
        if (xInt3.intValue() == 1) {
          xSElementDecl.fTargetNamespace = paramXSDocumentInfo.fTargetNamespace;
        } else {
          xSElementDecl.fTargetNamespace = null;
        } 
      } else if (paramXSDocumentInfo.fAreLocalElementsQualified) {
        xSElementDecl.fTargetNamespace = paramXSDocumentInfo.fTargetNamespace;
      } else {
        xSElementDecl.fTargetNamespace = null;
      } 
    } 
    xSElementDecl.fBlock = (xInt1 == null) ? paramXSDocumentInfo.fBlockDefault : xInt1.shortValue();
    xSElementDecl.fFinal = (xInt2 == null) ? paramXSDocumentInfo.fFinalDefault : xInt2.shortValue();
    xSElementDecl.fBlock = (short)(xSElementDecl.fBlock & 0x7);
    xSElementDecl.fFinal = (short)(xSElementDecl.fFinal & 0x3);
    if (bool2.booleanValue())
      xSElementDecl.setIsNillable(); 
    if (bool1 != null && bool1.booleanValue())
      xSElementDecl.setIsAbstract(); 
    if (str2 != null) {
      xSElementDecl.fDefault = new ValidatedInfo();
      xSElementDecl.fDefault.normalizedValue = str2;
      xSElementDecl.setConstraintType((short)2);
    } else if (str1 != null) {
      xSElementDecl.fDefault = new ValidatedInfo();
      xSElementDecl.fDefault.normalizedValue = str1;
      xSElementDecl.setConstraintType((short)1);
    } else {
      xSElementDecl.setConstraintType((short)0);
    } 
    if (qName1 != null)
      xSElementDecl.fSubGroup = (XSElementDecl)this.fSchemaHandler.getGlobalDecl(paramXSDocumentInfo, 3, qName1, paramElement); 
    Element element = DOMUtil.getFirstChildElement(paramElement);
    XSAnnotationImpl xSAnnotationImpl = null;
    if (element != null && DOMUtil.getLocalName(element).equals(SchemaSymbols.ELT_ANNOTATION)) {
      xSAnnotationImpl = traverseAnnotationDecl(element, paramArrayOfObject, false, paramXSDocumentInfo);
      element = DOMUtil.getNextSiblingElement(element);
    } else {
      String str = DOMUtil.getSyntheticAnnotation(paramElement);
      if (str != null)
        xSAnnotationImpl = traverseSyntheticAnnotation(paramElement, str, paramArrayOfObject, false, paramXSDocumentInfo); 
    } 
    if (xSAnnotationImpl != null) {
      XSObjectListImpl xSObjectListImpl = new XSObjectListImpl();
      xSObjectListImpl.add((XSObject)xSAnnotationImpl);
    } else {
      xSObjectList = XSObjectListImpl.EMPTY_LIST;
    } 
    xSElementDecl.fAnnotations = xSObjectList;
    XSComplexTypeDecl xSComplexTypeDecl2 = null;
    boolean bool = false;
    if (element != null) {
      String str = DOMUtil.getLocalName(element);
      if (str.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
        xSComplexTypeDecl2 = this.fSchemaHandler.fComplexTypeTraverser.traverseLocal(element, paramXSDocumentInfo, paramSchemaGrammar);
        bool = true;
        element = DOMUtil.getNextSiblingElement(element);
      } else if (str.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
        xSSimpleType = this.fSchemaHandler.fSimpleTypeTraverser.traverseLocal(element, paramXSDocumentInfo, paramSchemaGrammar);
        bool = true;
        element = DOMUtil.getNextSiblingElement(element);
      } 
    } 
    if (xSSimpleType == null && qName2 != null)
      xSTypeDefinition = (XSTypeDefinition)this.fSchemaHandler.getGlobalDecl(paramXSDocumentInfo, 7, qName2, paramElement); 
    if (xSTypeDefinition == null && xSElementDecl.fSubGroup != null)
      xSTypeDefinition = xSElementDecl.fSubGroup.fType; 
    if (xSTypeDefinition == null)
      xSComplexTypeDecl1 = SchemaGrammar.fAnyType; 
    xSElementDecl.fType = (XSTypeDefinition)xSComplexTypeDecl1;
    if (element != null) {
      String str = DOMUtil.getLocalName(element);
      while (element != null && (str.equals(SchemaSymbols.ELT_KEY) || str.equals(SchemaSymbols.ELT_KEYREF) || str.equals(SchemaSymbols.ELT_UNIQUE))) {
        if (str.equals(SchemaSymbols.ELT_KEY) || str.equals(SchemaSymbols.ELT_UNIQUE)) {
          DOMUtil.setHidden(element, this.fSchemaHandler.fHiddenNodes);
          this.fSchemaHandler.fUniqueOrKeyTraverser.traverse(element, xSElementDecl, paramXSDocumentInfo, paramSchemaGrammar);
          if (DOMUtil.getAttrValue(element, SchemaSymbols.ATT_NAME).length() != 0)
            this.fSchemaHandler.checkForDuplicateNames((paramXSDocumentInfo.fTargetNamespace == null) ? ("," + DOMUtil.getAttrValue(element, SchemaSymbols.ATT_NAME)) : (paramXSDocumentInfo.fTargetNamespace + "," + DOMUtil.getAttrValue(element, SchemaSymbols.ATT_NAME)), this.fSchemaHandler.getIDRegistry(), this.fSchemaHandler.getIDRegistry_sub(), element, paramXSDocumentInfo); 
        } else if (str.equals(SchemaSymbols.ELT_KEYREF)) {
          this.fSchemaHandler.storeKeyRef(element, paramXSDocumentInfo, xSElementDecl);
        } 
        element = DOMUtil.getNextSiblingElement(element);
        if (element != null)
          str = DOMUtil.getLocalName(element); 
      } 
    } 
    if (paramBoolean && str3 != null)
      paramSchemaGrammar.addGlobalElementDecl(xSElementDecl); 
    if (str3 == null) {
      if (paramBoolean) {
        reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_ELEMENT, SchemaSymbols.ATT_NAME }, paramElement);
      } else {
        reportSchemaError("src-element.2.1", null, paramElement);
      } 
      str3 = "(no name)";
    } 
    if (element != null)
      reportSchemaError("s4s-elt-must-match.1", new Object[] { str3, "(annotation?, (simpleType | complexType)?, (unique | key | keyref)*))", DOMUtil.getLocalName(element) }, element); 
    if (str1 != null && str2 != null)
      reportSchemaError("src-element.1", new Object[] { str3 }, paramElement); 
    if (bool && qName2 != null)
      reportSchemaError("src-element.3", new Object[] { str3 }, paramElement); 
    checkNotationType(str3, (XSTypeDefinition)xSComplexTypeDecl1, paramElement);
    if (xSElementDecl.fDefault != null) {
      this.fValidationState.setNamespaceSupport((NamespaceContext)paramXSDocumentInfo.fNamespaceSupport);
      if (XSConstraints.ElementDefaultValidImmediate(xSElementDecl.fType, xSElementDecl.fDefault.normalizedValue, (ValidationContext)this.fValidationState, xSElementDecl.fDefault) == null) {
        reportSchemaError("e-props-correct.2", new Object[] { str3, xSElementDecl.fDefault.normalizedValue }, paramElement);
        xSElementDecl.setConstraintType((short)0);
      } 
    } 
    if (xSElementDecl.fSubGroup != null && !XSConstraints.checkTypeDerivationOk(xSElementDecl.fType, xSElementDecl.fSubGroup.fType, xSElementDecl.fSubGroup.fFinal))
      reportSchemaError("e-props-correct.4", new Object[] { str3, qName1.prefix + ":" + qName1.localpart }, paramElement); 
    if (xSElementDecl.fDefault != null && ((xSComplexTypeDecl1.getTypeCategory() == 16 && ((XSSimpleType)xSComplexTypeDecl1).isIDType()) || (xSComplexTypeDecl1.getTypeCategory() == 15 && xSComplexTypeDecl1.containsTypeID())))
      reportSchemaError("e-props-correct.5", new Object[] { xSElementDecl.fName }, paramElement); 
    return (xSElementDecl.fName == null) ? null : xSElementDecl;
  }
  
  void reset(SymbolTable paramSymbolTable, boolean paramBoolean) {
    super.reset(paramSymbolTable, paramBoolean);
    this.fDeferTraversingLocalElements = true;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\traversers\XSDElementTraverser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */