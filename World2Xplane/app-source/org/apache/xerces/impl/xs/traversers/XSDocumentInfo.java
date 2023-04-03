package org.apache.xerces.impl.xs.traversers;

import java.util.Stack;
import java.util.Vector;
import org.apache.xerces.impl.validation.ValidationState;
import org.apache.xerces.impl.xs.SchemaNamespaceSupport;
import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.NamespaceContext;
import org.w3c.dom.Element;

class XSDocumentInfo {
  protected SchemaNamespaceSupport fNamespaceSupport;
  
  protected SchemaNamespaceSupport fNamespaceSupportRoot;
  
  protected Stack SchemaNamespaceSupportStack = new Stack();
  
  protected boolean fAreLocalAttributesQualified;
  
  protected boolean fAreLocalElementsQualified;
  
  protected short fBlockDefault;
  
  protected short fFinalDefault;
  
  String fTargetNamespace;
  
  protected boolean fIsChameleonSchema;
  
  protected Element fSchemaElement;
  
  Vector fImportedNS = new Vector();
  
  protected ValidationState fValidationContext = new ValidationState();
  
  SymbolTable fSymbolTable = null;
  
  protected XSAttributeChecker fAttrChecker;
  
  protected Object[] fSchemaAttrs;
  
  protected XSAnnotationInfo fAnnotations = null;
  
  private Vector fReportedTNS = null;
  
  XSDocumentInfo(Element paramElement, XSAttributeChecker paramXSAttributeChecker, SymbolTable paramSymbolTable) throws XMLSchemaException {
    this.fSchemaElement = paramElement;
    this.fNamespaceSupport = new SchemaNamespaceSupport(paramElement, paramSymbolTable);
    this.fNamespaceSupport.reset();
    this.fIsChameleonSchema = false;
    this.fSymbolTable = paramSymbolTable;
    this.fAttrChecker = paramXSAttributeChecker;
    if (paramElement != null) {
      Element element = paramElement;
      this.fSchemaAttrs = paramXSAttributeChecker.checkAttributes(element, true, this);
      if (this.fSchemaAttrs == null)
        throw new XMLSchemaException(null, null); 
      this.fAreLocalAttributesQualified = (((XInt)this.fSchemaAttrs[XSAttributeChecker.ATTIDX_AFORMDEFAULT]).intValue() == 1);
      this.fAreLocalElementsQualified = (((XInt)this.fSchemaAttrs[XSAttributeChecker.ATTIDX_EFORMDEFAULT]).intValue() == 1);
      this.fBlockDefault = ((XInt)this.fSchemaAttrs[XSAttributeChecker.ATTIDX_BLOCKDEFAULT]).shortValue();
      this.fFinalDefault = ((XInt)this.fSchemaAttrs[XSAttributeChecker.ATTIDX_FINALDEFAULT]).shortValue();
      this.fTargetNamespace = (String)this.fSchemaAttrs[XSAttributeChecker.ATTIDX_TARGETNAMESPACE];
      if (this.fTargetNamespace != null)
        this.fTargetNamespace = paramSymbolTable.addSymbol(this.fTargetNamespace); 
      this.fNamespaceSupportRoot = new SchemaNamespaceSupport(this.fNamespaceSupport);
      this.fValidationContext.setNamespaceSupport((NamespaceContext)this.fNamespaceSupport);
      this.fValidationContext.setSymbolTable(paramSymbolTable);
    } 
  }
  
  void backupNSSupport(SchemaNamespaceSupport paramSchemaNamespaceSupport) {
    this.SchemaNamespaceSupportStack.push(this.fNamespaceSupport);
    if (paramSchemaNamespaceSupport == null)
      paramSchemaNamespaceSupport = this.fNamespaceSupportRoot; 
    this.fNamespaceSupport = new SchemaNamespaceSupport(paramSchemaNamespaceSupport);
    this.fValidationContext.setNamespaceSupport((NamespaceContext)this.fNamespaceSupport);
  }
  
  void restoreNSSupport() {
    this.fNamespaceSupport = this.SchemaNamespaceSupportStack.pop();
    this.fValidationContext.setNamespaceSupport((NamespaceContext)this.fNamespaceSupport);
  }
  
  public String toString() {
    return (this.fTargetNamespace == null) ? "no targetNamspace" : ("targetNamespace is " + this.fTargetNamespace);
  }
  
  public void addAllowedNS(String paramString) {
    this.fImportedNS.addElement((paramString == null) ? "" : paramString);
  }
  
  public boolean isAllowedNS(String paramString) {
    return this.fImportedNS.contains((paramString == null) ? "" : paramString);
  }
  
  final boolean needReportTNSError(String paramString) {
    if (this.fReportedTNS == null) {
      this.fReportedTNS = new Vector();
    } else if (this.fReportedTNS.contains(paramString)) {
      return false;
    } 
    this.fReportedTNS.addElement(paramString);
    return true;
  }
  
  Object[] getSchemaAttrs() {
    return this.fSchemaAttrs;
  }
  
  void returnSchemaAttrs() {
    this.fAttrChecker.returnAttrArray(this.fSchemaAttrs, null);
    this.fSchemaAttrs = null;
  }
  
  void addAnnotation(XSAnnotationInfo paramXSAnnotationInfo) {
    paramXSAnnotationInfo.next = this.fAnnotations;
    this.fAnnotations = paramXSAnnotationInfo;
  }
  
  XSAnnotationInfo getAnnotations() {
    return this.fAnnotations;
  }
  
  void removeAnnotations() {
    this.fAnnotations = null;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\traversers\XSDocumentInfo.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */