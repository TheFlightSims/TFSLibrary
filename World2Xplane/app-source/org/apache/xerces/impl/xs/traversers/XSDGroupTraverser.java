package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.XSAnnotationImpl;
import org.apache.xerces.impl.xs.XSGroupDecl;
import org.apache.xerces.impl.xs.XSModelGroupImpl;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSTerm;
import org.w3c.dom.Element;

class XSDGroupTraverser extends XSDAbstractParticleTraverser {
  XSDGroupTraverser(XSDHandler paramXSDHandler, XSAttributeChecker paramXSAttributeChecker) {
    super(paramXSDHandler, paramXSAttributeChecker);
  }
  
  XSParticleDecl traverseLocal(Element paramElement, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(paramElement, false, paramXSDocumentInfo);
    QName qName = (QName)arrayOfObject[XSAttributeChecker.ATTIDX_REF];
    XInt xInt1 = (XInt)arrayOfObject[XSAttributeChecker.ATTIDX_MINOCCURS];
    XInt xInt2 = (XInt)arrayOfObject[XSAttributeChecker.ATTIDX_MAXOCCURS];
    XSGroupDecl xSGroupDecl = null;
    if (qName == null) {
      reportSchemaError("s4s-att-must-appear", new Object[] { "group (local)", "ref" }, paramElement);
    } else {
      xSGroupDecl = (XSGroupDecl)this.fSchemaHandler.getGlobalDecl(paramXSDocumentInfo, 4, qName, paramElement);
    } 
    XSAnnotationImpl xSAnnotationImpl = null;
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
      reportSchemaError("s4s-elt-must-match.1", new Object[] { "group (local)", "(annotation?)", DOMUtil.getLocalName(paramElement) }, paramElement); 
    int i = xInt1.intValue();
    int j = xInt2.intValue();
    XSParticleDecl xSParticleDecl = null;
    if (xSGroupDecl != null && xSGroupDecl.fModelGroup != null && (i != 0 || j != 0)) {
      if (this.fSchemaHandler.fDeclPool != null) {
        xSParticleDecl = this.fSchemaHandler.fDeclPool.getParticleDecl();
      } else {
        xSParticleDecl = new XSParticleDecl();
      } 
      xSParticleDecl.fType = 3;
      xSParticleDecl.fValue = (XSTerm)xSGroupDecl.fModelGroup;
      xSParticleDecl.fMinOccurs = i;
      xSParticleDecl.fMaxOccurs = j;
      if (xSGroupDecl.fModelGroup.fCompositor == 103) {
        Long long_ = (Long)arrayOfObject[XSAttributeChecker.ATTIDX_FROMDEFAULT];
        xSParticleDecl = checkOccurrences(xSParticleDecl, SchemaSymbols.ELT_GROUP, (Element)paramElement.getParentNode(), 2, long_.longValue());
      } 
      if (qName != null) {
        XSObjectList xSObjectList;
        if (xSAnnotationImpl != null) {
          XSObjectListImpl xSObjectListImpl = new XSObjectListImpl();
          xSObjectListImpl.add((XSObject)xSAnnotationImpl);
        } else {
          xSObjectList = XSObjectListImpl.EMPTY_LIST;
        } 
        xSParticleDecl.fAnnotations = xSObjectList;
      } else {
        xSParticleDecl.fAnnotations = xSGroupDecl.fAnnotations;
      } 
    } 
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
    return xSParticleDecl;
  }
  
  XSGroupDecl traverseGlobal(Element paramElement, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(paramElement, true, paramXSDocumentInfo);
    String str = (String)arrayOfObject[XSAttributeChecker.ATTIDX_NAME];
    if (str == null)
      reportSchemaError("s4s-att-must-appear", new Object[] { "group (global)", "name" }, paramElement); 
    XSGroupDecl xSGroupDecl = null;
    XSParticleDecl xSParticleDecl = null;
    Element element = DOMUtil.getFirstChildElement(paramElement);
    XSAnnotationImpl xSAnnotationImpl = null;
    if (element == null) {
      reportSchemaError("s4s-elt-must-match.2", new Object[] { "group (global)", "(annotation?, (all | choice | sequence))" }, paramElement);
    } else {
      xSGroupDecl = new XSGroupDecl();
      String str1 = element.getLocalName();
      if (str1.equals(SchemaSymbols.ELT_ANNOTATION)) {
        xSAnnotationImpl = traverseAnnotationDecl(element, arrayOfObject, true, paramXSDocumentInfo);
        element = DOMUtil.getNextSiblingElement(element);
        if (element != null)
          str1 = element.getLocalName(); 
      } else {
        String str2 = DOMUtil.getSyntheticAnnotation(paramElement);
        if (str2 != null)
          xSAnnotationImpl = traverseSyntheticAnnotation(paramElement, str2, arrayOfObject, false, paramXSDocumentInfo); 
      } 
      if (element == null) {
        reportSchemaError("s4s-elt-must-match.2", new Object[] { "group (global)", "(annotation?, (all | choice | sequence))" }, paramElement);
      } else if (str1.equals(SchemaSymbols.ELT_ALL)) {
        xSParticleDecl = traverseAll(element, paramXSDocumentInfo, paramSchemaGrammar, 4, (XSObject)xSGroupDecl);
      } else if (str1.equals(SchemaSymbols.ELT_CHOICE)) {
        xSParticleDecl = traverseChoice(element, paramXSDocumentInfo, paramSchemaGrammar, 4, (XSObject)xSGroupDecl);
      } else if (str1.equals(SchemaSymbols.ELT_SEQUENCE)) {
        xSParticleDecl = traverseSequence(element, paramXSDocumentInfo, paramSchemaGrammar, 4, (XSObject)xSGroupDecl);
      } else {
        reportSchemaError("s4s-elt-must-match.1", new Object[] { "group (global)", "(annotation?, (all | choice | sequence))", DOMUtil.getLocalName(element) }, element);
      } 
      if (element != null && DOMUtil.getNextSiblingElement(element) != null)
        reportSchemaError("s4s-elt-must-match.1", new Object[] { "group (global)", "(annotation?, (all | choice | sequence))", DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(element)) }, DOMUtil.getNextSiblingElement(element)); 
      if (str != null) {
        XSObjectList xSObjectList;
        xSGroupDecl.fName = str;
        xSGroupDecl.fTargetNamespace = paramXSDocumentInfo.fTargetNamespace;
        if (xSParticleDecl != null)
          xSGroupDecl.fModelGroup = (XSModelGroupImpl)xSParticleDecl.fValue; 
        if (xSAnnotationImpl != null) {
          XSObjectListImpl xSObjectListImpl = new XSObjectListImpl();
          xSObjectListImpl.add((XSObject)xSAnnotationImpl);
        } else {
          xSObjectList = XSObjectListImpl.EMPTY_LIST;
        } 
        xSGroupDecl.fAnnotations = xSObjectList;
        paramSchemaGrammar.addGlobalGroupDecl(xSGroupDecl);
      } else {
        xSGroupDecl = null;
      } 
    } 
    if (xSGroupDecl != null) {
      Object object = this.fSchemaHandler.getGrpOrAttrGrpRedefinedByRestriction(4, new QName(XMLSymbols.EMPTY_STRING, str, str, paramXSDocumentInfo.fTargetNamespace), paramXSDocumentInfo, paramElement);
      if (object != null)
        paramSchemaGrammar.addRedefinedGroupDecl(xSGroupDecl, (XSGroupDecl)object, this.fSchemaHandler.element2Locator(paramElement)); 
    } 
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
    return xSGroupDecl;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\traversers\XSDGroupTraverser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */