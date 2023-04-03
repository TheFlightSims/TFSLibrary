package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.XSAnnotationImpl;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.impl.xs.XSWildcardDecl;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSTerm;
import org.w3c.dom.Element;

class XSDWildcardTraverser extends XSDAbstractTraverser {
  XSDWildcardTraverser(XSDHandler paramXSDHandler, XSAttributeChecker paramXSAttributeChecker) {
    super(paramXSDHandler, paramXSAttributeChecker);
  }
  
  XSParticleDecl traverseAny(Element paramElement, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(paramElement, false, paramXSDocumentInfo);
    XSWildcardDecl xSWildcardDecl = traverseWildcardDecl(paramElement, arrayOfObject, paramXSDocumentInfo, paramSchemaGrammar);
    XSParticleDecl xSParticleDecl = null;
    if (xSWildcardDecl != null) {
      int i = ((XInt)arrayOfObject[XSAttributeChecker.ATTIDX_MINOCCURS]).intValue();
      int j = ((XInt)arrayOfObject[XSAttributeChecker.ATTIDX_MAXOCCURS]).intValue();
      if (j != 0) {
        if (this.fSchemaHandler.fDeclPool != null) {
          xSParticleDecl = this.fSchemaHandler.fDeclPool.getParticleDecl();
        } else {
          xSParticleDecl = new XSParticleDecl();
        } 
        xSParticleDecl.fType = 2;
        xSParticleDecl.fValue = (XSTerm)xSWildcardDecl;
        xSParticleDecl.fMinOccurs = i;
        xSParticleDecl.fMaxOccurs = j;
        xSParticleDecl.fAnnotations = xSWildcardDecl.fAnnotations;
      } 
    } 
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
    return xSParticleDecl;
  }
  
  XSWildcardDecl traverseAnyAttribute(Element paramElement, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(paramElement, false, paramXSDocumentInfo);
    XSWildcardDecl xSWildcardDecl = traverseWildcardDecl(paramElement, arrayOfObject, paramXSDocumentInfo, paramSchemaGrammar);
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
    return xSWildcardDecl;
  }
  
  XSWildcardDecl traverseWildcardDecl(Element paramElement, Object[] paramArrayOfObject, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    XSObjectList xSObjectList;
    XSWildcardDecl xSWildcardDecl = new XSWildcardDecl();
    XInt xInt1 = (XInt)paramArrayOfObject[XSAttributeChecker.ATTIDX_NAMESPACE];
    xSWildcardDecl.fType = xInt1.shortValue();
    xSWildcardDecl.fNamespaceList = (String[])paramArrayOfObject[XSAttributeChecker.ATTIDX_NAMESPACE_LIST];
    XInt xInt2 = (XInt)paramArrayOfObject[XSAttributeChecker.ATTIDX_PROCESSCONTENTS];
    xSWildcardDecl.fProcessContents = xInt2.shortValue();
    Element element = DOMUtil.getFirstChildElement(paramElement);
    XSAnnotationImpl xSAnnotationImpl = null;
    if (element != null) {
      if (DOMUtil.getLocalName(element).equals(SchemaSymbols.ELT_ANNOTATION)) {
        xSAnnotationImpl = traverseAnnotationDecl(element, paramArrayOfObject, false, paramXSDocumentInfo);
        element = DOMUtil.getNextSiblingElement(element);
      } else {
        String str = DOMUtil.getSyntheticAnnotation(paramElement);
        if (str != null)
          xSAnnotationImpl = traverseSyntheticAnnotation(paramElement, str, paramArrayOfObject, false, paramXSDocumentInfo); 
      } 
      if (element != null)
        reportSchemaError("s4s-elt-must-match.1", new Object[] { "wildcard", "(annotation?)", DOMUtil.getLocalName(element) }, paramElement); 
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
    xSWildcardDecl.fAnnotations = xSObjectList;
    return xSWildcardDecl;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\traversers\XSDWildcardTraverser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */