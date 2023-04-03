package org.apache.xerces.impl.dtd;

import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XNIException;

public class XML11NSDTDValidator extends XML11DTDValidator {
  private final QName fAttributeQName = new QName();
  
  protected final void startNamespaceScope(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    this.fNamespaceContext.pushContext();
    if (paramQName.prefix == XMLSymbols.PREFIX_XMLNS)
      this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementXMLNSPrefix", new Object[] { paramQName.rawname }, (short)2); 
    int i = paramXMLAttributes.getLength();
    for (byte b1 = 0; b1 < i; b1++) {
      String str1 = paramXMLAttributes.getLocalName(b1);
      String str2 = paramXMLAttributes.getPrefix(b1);
      if (str2 == XMLSymbols.PREFIX_XMLNS || (str2 == XMLSymbols.EMPTY_STRING && str1 == XMLSymbols.PREFIX_XMLNS)) {
        String str3 = this.fSymbolTable.addSymbol(paramXMLAttributes.getValue(b1));
        if (str2 == XMLSymbols.PREFIX_XMLNS && str1 == XMLSymbols.PREFIX_XMLNS)
          this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXMLNS", new Object[] { paramXMLAttributes.getQName(b1) }, (short)2); 
        if (str3 == NamespaceContext.XMLNS_URI)
          this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXMLNS", new Object[] { paramXMLAttributes.getQName(b1) }, (short)2); 
        if (str1 == XMLSymbols.PREFIX_XML) {
          if (str3 != NamespaceContext.XML_URI)
            this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXML", new Object[] { paramXMLAttributes.getQName(b1) }, (short)2); 
        } else if (str3 == NamespaceContext.XML_URI) {
          this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "CantBindXML", new Object[] { paramXMLAttributes.getQName(b1) }, (short)2);
        } 
        str2 = (str1 != XMLSymbols.PREFIX_XMLNS) ? str1 : XMLSymbols.EMPTY_STRING;
        this.fNamespaceContext.declarePrefix(str2, (str3.length() != 0) ? str3 : null);
      } 
    } 
    String str = (paramQName.prefix != null) ? paramQName.prefix : XMLSymbols.EMPTY_STRING;
    paramQName.uri = this.fNamespaceContext.getURI(str);
    if (paramQName.prefix == null && paramQName.uri != null)
      paramQName.prefix = XMLSymbols.EMPTY_STRING; 
    if (paramQName.prefix != null && paramQName.uri == null)
      this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "ElementPrefixUnbound", new Object[] { paramQName.prefix, paramQName.rawname }, (short)2); 
    for (byte b2 = 0; b2 < i; b2++) {
      paramXMLAttributes.getName(b2, this.fAttributeQName);
      String str1 = (this.fAttributeQName.prefix != null) ? this.fAttributeQName.prefix : XMLSymbols.EMPTY_STRING;
      String str2 = this.fAttributeQName.rawname;
      if (str2 == XMLSymbols.PREFIX_XMLNS) {
        this.fAttributeQName.uri = this.fNamespaceContext.getURI(XMLSymbols.PREFIX_XMLNS);
        paramXMLAttributes.setName(b2, this.fAttributeQName);
      } else if (str1 != XMLSymbols.EMPTY_STRING) {
        this.fAttributeQName.uri = this.fNamespaceContext.getURI(str1);
        if (this.fAttributeQName.uri == null)
          this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributePrefixUnbound", new Object[] { paramQName.rawname, str2, str1 }, (short)2); 
        paramXMLAttributes.setName(b2, this.fAttributeQName);
      } 
    } 
    int j = paramXMLAttributes.getLength();
    for (byte b3 = 0; b3 < j - 1; b3++) {
      String str1 = paramXMLAttributes.getURI(b3);
      if (str1 != null && str1 != NamespaceContext.XMLNS_URI) {
        String str2 = paramXMLAttributes.getLocalName(b3);
        for (int k = b3 + 1; k < j; k++) {
          String str3 = paramXMLAttributes.getLocalName(k);
          String str4 = paramXMLAttributes.getURI(k);
          if (str2 == str3 && str1 == str4)
            this.fErrorReporter.reportError("http://www.w3.org/TR/1999/REC-xml-names-19990114", "AttributeNSNotUnique", new Object[] { paramQName.rawname, str2, str1 }, (short)2); 
        } 
      } 
    } 
  }
  
  protected void endNamespaceScope(QName paramQName, Augmentations paramAugmentations, boolean paramBoolean) throws XNIException {
    String str = (paramQName.prefix != null) ? paramQName.prefix : XMLSymbols.EMPTY_STRING;
    paramQName.uri = this.fNamespaceContext.getURI(str);
    if (paramQName.uri != null)
      paramQName.prefix = str; 
    if (this.fDocumentHandler != null && !paramBoolean)
      this.fDocumentHandler.endElement(paramQName, paramAugmentations); 
    this.fNamespaceContext.popContext();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dtd\XML11NSDTDValidator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */