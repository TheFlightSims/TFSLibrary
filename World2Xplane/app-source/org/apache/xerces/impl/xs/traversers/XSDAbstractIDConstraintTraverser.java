package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xpath.XPathException;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.identity.Field;
import org.apache.xerces.impl.xs.identity.IdentityConstraint;
import org.apache.xerces.impl.xs.identity.Selector;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.xni.NamespaceContext;
import org.w3c.dom.Element;

class XSDAbstractIDConstraintTraverser extends XSDAbstractTraverser {
  public XSDAbstractIDConstraintTraverser(XSDHandler paramXSDHandler, XSAttributeChecker paramXSAttributeChecker) {
    super(paramXSDHandler, paramXSAttributeChecker);
  }
  
  void traverseIdentityConstraint(IdentityConstraint paramIdentityConstraint, Element paramElement, XSDocumentInfo paramXSDocumentInfo, Object[] paramArrayOfObject) {
    Element element1 = DOMUtil.getFirstChildElement(paramElement);
    if (element1 == null) {
      reportSchemaError("s4s-elt-must-match.2", new Object[] { "identity constraint", "(annotation?, selector, field+)" }, paramElement);
      return;
    } 
    if (DOMUtil.getLocalName(element1).equals(SchemaSymbols.ELT_ANNOTATION)) {
      paramIdentityConstraint.addAnnotation(traverseAnnotationDecl(element1, paramArrayOfObject, false, paramXSDocumentInfo));
      element1 = DOMUtil.getNextSiblingElement(element1);
      if (element1 == null) {
        reportSchemaError("s4s-elt-must-match.2", new Object[] { "identity constraint", "(annotation?, selector, field+)" }, paramElement);
        return;
      } 
    } else {
      String str1 = DOMUtil.getSyntheticAnnotation(paramElement);
      if (str1 != null)
        paramIdentityConstraint.addAnnotation(traverseSyntheticAnnotation(paramElement, str1, paramArrayOfObject, false, paramXSDocumentInfo)); 
    } 
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(element1, false, paramXSDocumentInfo);
    if (!DOMUtil.getLocalName(element1).equals(SchemaSymbols.ELT_SELECTOR))
      reportSchemaError("s4s-elt-must-match.1", new Object[] { "identity constraint", "(annotation?, selector, field+)", SchemaSymbols.ELT_SELECTOR }, element1); 
    Element element2 = DOMUtil.getFirstChildElement(element1);
    if (element2 != null) {
      if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
        paramIdentityConstraint.addAnnotation(traverseAnnotationDecl(element2, arrayOfObject, false, paramXSDocumentInfo));
        element2 = DOMUtil.getNextSiblingElement(element2);
      } else {
        reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_SELECTOR, "(annotation?)", DOMUtil.getLocalName(element2) }, element2);
      } 
      if (element2 != null)
        reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_SELECTOR, "(annotation?)", DOMUtil.getLocalName(element2) }, element2); 
    } else {
      String str1 = DOMUtil.getSyntheticAnnotation(element1);
      if (str1 != null)
        paramIdentityConstraint.addAnnotation(traverseSyntheticAnnotation(paramElement, str1, arrayOfObject, false, paramXSDocumentInfo)); 
    } 
    String str = (String)arrayOfObject[XSAttributeChecker.ATTIDX_XPATH];
    if (str == null) {
      reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_SELECTOR, SchemaSymbols.ATT_XPATH }, element1);
      return;
    } 
    str = XMLChar.trim(str);
    Selector.XPath xPath = null;
    try {
      xPath = new Selector.XPath(str, this.fSymbolTable, (NamespaceContext)paramXSDocumentInfo.fNamespaceSupport);
      Selector selector = new Selector(xPath, paramIdentityConstraint);
      paramIdentityConstraint.setSelector(selector);
    } catch (XPathException xPathException) {
      reportSchemaError(xPathException.getKey(), new Object[] { str }, element1);
      this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
      return;
    } 
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
    Element element3 = DOMUtil.getNextSiblingElement(element1);
    if (element3 == null)
      reportSchemaError("s4s-elt-must-match.2", new Object[] { "identity constraint", "(annotation?, selector, field+)" }, element1); 
    while (element3 != null) {
      arrayOfObject = this.fAttrChecker.checkAttributes(element3, false, paramXSDocumentInfo);
      if (!DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_FIELD))
        reportSchemaError("s4s-elt-must-match.1", new Object[] { "identity constraint", "(annotation?, selector, field+)", SchemaSymbols.ELT_FIELD }, element3); 
      Element element = DOMUtil.getFirstChildElement(element3);
      if (element != null && DOMUtil.getLocalName(element).equals(SchemaSymbols.ELT_ANNOTATION)) {
        paramIdentityConstraint.addAnnotation(traverseAnnotationDecl(element, arrayOfObject, false, paramXSDocumentInfo));
        element = DOMUtil.getNextSiblingElement(element);
      } 
      if (element != null) {
        reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_FIELD, "(annotation?)", DOMUtil.getLocalName(element) }, element);
      } else {
        String str2 = DOMUtil.getSyntheticAnnotation(element3);
        if (str2 != null)
          paramIdentityConstraint.addAnnotation(traverseSyntheticAnnotation(paramElement, str2, arrayOfObject, false, paramXSDocumentInfo)); 
      } 
      String str1 = (String)arrayOfObject[XSAttributeChecker.ATTIDX_XPATH];
      if (str1 == null) {
        reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_FIELD, SchemaSymbols.ATT_XPATH }, element3);
        return;
      } 
      str1 = str1.trim();
      try {
        Field.XPath xPath1 = new Field.XPath(str1, this.fSymbolTable, (NamespaceContext)paramXSDocumentInfo.fNamespaceSupport);
        Field field = new Field(xPath1, paramIdentityConstraint);
        paramIdentityConstraint.addField(field);
      } catch (XPathException xPathException) {
        reportSchemaError(xPathException.getKey(), new Object[] { str1 }, element3);
        this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
        return;
      } 
      element3 = DOMUtil.getNextSiblingElement(element3);
      this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\traversers\XSDAbstractIDConstraintTraverser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */