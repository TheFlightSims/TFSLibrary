package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.impl.xs.identity.IdentityConstraint;
import org.apache.xerces.impl.xs.identity.UniqueOrKey;
import org.apache.xerces.util.DOMUtil;
import org.w3c.dom.Element;

class XSDUniqueOrKeyTraverser extends XSDAbstractIDConstraintTraverser {
  public XSDUniqueOrKeyTraverser(XSDHandler paramXSDHandler, XSAttributeChecker paramXSAttributeChecker) {
    super(paramXSDHandler, paramXSAttributeChecker);
  }
  
  void traverse(Element paramElement, XSElementDecl paramXSElementDecl, XSDocumentInfo paramXSDocumentInfo, SchemaGrammar paramSchemaGrammar) {
    Object[] arrayOfObject = this.fAttrChecker.checkAttributes(paramElement, false, paramXSDocumentInfo);
    String str = (String)arrayOfObject[XSAttributeChecker.ATTIDX_NAME];
    if (str == null) {
      reportSchemaError("s4s-att-must-appear", new Object[] { DOMUtil.getLocalName(paramElement), SchemaSymbols.ATT_NAME }, paramElement);
      this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
      return;
    } 
    UniqueOrKey uniqueOrKey = null;
    if (DOMUtil.getLocalName(paramElement).equals(SchemaSymbols.ELT_UNIQUE)) {
      uniqueOrKey = new UniqueOrKey(paramXSDocumentInfo.fTargetNamespace, str, paramXSElementDecl.fName, (short)3);
    } else {
      uniqueOrKey = new UniqueOrKey(paramXSDocumentInfo.fTargetNamespace, str, paramXSElementDecl.fName, (short)1);
    } 
    traverseIdentityConstraint((IdentityConstraint)uniqueOrKey, paramElement, paramXSDocumentInfo, arrayOfObject);
    paramSchemaGrammar.addIDConstraintDecl(paramXSElementDecl, (IdentityConstraint)uniqueOrKey);
    this.fAttrChecker.returnAttrArray(arrayOfObject, paramXSDocumentInfo);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\traversers\XSDUniqueOrKeyTraverser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */