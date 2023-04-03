package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.XSAnnotationImpl;
import org.apache.xerces.impl.xs.XSNotationDecl;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSObjectList;
import org.w3c.dom.Element;

class XSDNotationTraverser extends XSDAbstractTraverser {
  XSDNotationTraverser(XSDHandler paramXSDHandler, XSAttributeChecker paramXSAttributeChecker) {
    super(paramXSDHandler, paramXSAttributeChecker);
  }
  
  XSNotationDecl traverse(Element paramElement, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    XSObjectList xSObjectList;
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(paramElement, true, paramXSDocumentInfo);
    String str1 = (String)arrayOfObject[XSAttributeChecker.ATTIDX_NAME];
    String str2 = (String)arrayOfObject[XSAttributeChecker.ATTIDX_PUBLIC];
    String str3 = (String)arrayOfObject[XSAttributeChecker.ATTIDX_SYSTEM];
    if (str1 == null) {
      reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_NOTATION, SchemaSymbols.ATT_NAME }, paramElement);
      this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
      return null;
    } 
    if (str3 == null && str2 == null)
      reportSchemaError("PublicSystemOnNotation", null, paramElement); 
    XSNotationDecl xSNotationDecl = new XSNotationDecl();
    xSNotationDecl.fName = str1;
    xSNotationDecl.fTargetNamespace = paramXSDocumentInfo.fTargetNamespace;
    xSNotationDecl.fPublicId = str2;
    xSNotationDecl.fSystemId = str3;
    Element element = DOMUtil.getFirstChildElement(paramElement);
    XSAnnotationImpl xSAnnotationImpl = null;
    if (element != null && DOMUtil.getLocalName(element).equals(SchemaSymbols.ELT_ANNOTATION)) {
      xSAnnotationImpl = traverseAnnotationDecl(element, arrayOfObject, false, paramXSDocumentInfo);
      element = DOMUtil.getNextSiblingElement(element);
    } else {
      String str = DOMUtil.getSyntheticAnnotation(paramElement);
      if (str != null)
        xSAnnotationImpl = traverseSyntheticAnnotation(paramElement, str, arrayOfObject, false, paramXSDocumentInfo); 
    } 
    if (xSAnnotationImpl != null) {
      XSObjectListImpl xSObjectListImpl = new XSObjectListImpl();
      xSObjectListImpl.add((XSObject)xSAnnotationImpl);
    } else {
      xSObjectList = XSObjectListImpl.EMPTY_LIST;
    } 
    xSNotationDecl.fAnnotations = xSObjectList;
    if (element != null) {
      Object[] arrayOfObject1 = { SchemaSymbols.ELT_NOTATION, "(annotation?)", DOMUtil.getLocalName(element) };
      reportSchemaError("s4s-elt-must-match.1", arrayOfObject1, element);
    } 
    paramSchemaGrammar.addGlobalNotationDecl(xSNotationDecl);
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
    return xSNotationDecl;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\traversers\XSDNotationTraverser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */