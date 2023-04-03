package org.apache.xerces.jaxp.validation;

import javax.xml.transform.dom.DOMResult;
import org.apache.xerces.dom.AttrImpl;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.apache.xerces.dom.ElementImpl;
import org.apache.xerces.dom.ElementNSImpl;
import org.apache.xerces.dom.PSVIAttrNSImpl;
import org.apache.xerces.dom.PSVIElementNSImpl;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLDocumentSource;
import org.apache.xerces.xs.AttributePSVI;
import org.apache.xerces.xs.ElementPSVI;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

final class DOMResultAugmentor implements DOMDocumentHandler {
  private final DOMValidatorHelper fDOMValidatorHelper;
  
  private Document fDocument;
  
  private CoreDocumentImpl fDocumentImpl;
  
  private boolean fStorePSVI;
  
  private boolean fIgnoreChars;
  
  private final QName fAttributeQName = new QName();
  
  public DOMResultAugmentor(DOMValidatorHelper paramDOMValidatorHelper) {
    this.fDOMValidatorHelper = paramDOMValidatorHelper;
  }
  
  public void setDOMResult(DOMResult paramDOMResult) {
    this.fIgnoreChars = false;
    if (paramDOMResult != null) {
      Node node = paramDOMResult.getNode();
      this.fDocument = (node.getNodeType() == 9) ? (Document)node : node.getOwnerDocument();
      this.fDocumentImpl = (this.fDocument instanceof CoreDocumentImpl) ? (CoreDocumentImpl)this.fDocument : null;
      this.fStorePSVI = this.fDocument instanceof org.apache.xerces.dom.PSVIDocumentImpl;
      return;
    } 
    this.fDocument = null;
    this.fDocumentImpl = null;
    this.fStorePSVI = false;
  }
  
  public void doctypeDecl(DocumentType paramDocumentType) throws XNIException {}
  
  public void characters(Text paramText) throws XNIException {}
  
  public void cdata(CDATASection paramCDATASection) throws XNIException {}
  
  public void comment(Comment paramComment) throws XNIException {}
  
  public void processingInstruction(ProcessingInstruction paramProcessingInstruction) throws XNIException {}
  
  public void setIgnoringCharacters(boolean paramBoolean) {
    this.fIgnoreChars = paramBoolean;
  }
  
  public void startDocument(XMLLocator paramXMLLocator, String paramString, NamespaceContext paramNamespaceContext, Augmentations paramAugmentations) throws XNIException {}
  
  public void xmlDecl(String paramString1, String paramString2, String paramString3, Augmentations paramAugmentations) throws XNIException {}
  
  public void doctypeDecl(String paramString1, String paramString2, String paramString3, Augmentations paramAugmentations) throws XNIException {}
  
  public void comment(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {}
  
  public void processingInstruction(String paramString, XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {}
  
  public void startElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    Element element = (Element)this.fDOMValidatorHelper.getCurrentElement();
    NamedNodeMap namedNodeMap = element.getAttributes();
    int i = namedNodeMap.getLength();
    if (this.fDocumentImpl != null)
      for (byte b = 0; b < i; b++) {
        AttrImpl attrImpl = (AttrImpl)namedNodeMap.item(b);
        AttributePSVI attributePSVI = (AttributePSVI)paramXMLAttributes.getAugmentations(b).getItem("ATTRIBUTE_PSVI");
        if (attributePSVI != null && processAttributePSVI(attrImpl, attributePSVI))
          ((ElementImpl)element).setIdAttributeNode((Attr)attrImpl, true); 
      }  
    int j = paramXMLAttributes.getLength();
    if (j > i)
      if (this.fDocumentImpl == null) {
        for (int k = i; k < j; k++) {
          paramXMLAttributes.getName(k, this.fAttributeQName);
          element.setAttributeNS(this.fAttributeQName.uri, this.fAttributeQName.rawname, paramXMLAttributes.getValue(k));
        } 
      } else {
        for (int k = i; k < j; k++) {
          paramXMLAttributes.getName(k, this.fAttributeQName);
          AttrImpl attrImpl = (AttrImpl)this.fDocumentImpl.createAttributeNS(this.fAttributeQName.uri, this.fAttributeQName.rawname, this.fAttributeQName.localpart);
          attrImpl.setValue(paramXMLAttributes.getValue(k));
          element.setAttributeNodeNS((Attr)attrImpl);
          AttributePSVI attributePSVI = (AttributePSVI)paramXMLAttributes.getAugmentations(k).getItem("ATTRIBUTE_PSVI");
          if (attributePSVI != null && processAttributePSVI(attrImpl, attributePSVI))
            ((ElementImpl)element).setIdAttributeNode((Attr)attrImpl, true); 
          attrImpl.setSpecified(false);
        } 
      }  
  }
  
  public void emptyElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    startElement(paramQName, paramXMLAttributes, paramAugmentations);
    endElement(paramQName, paramAugmentations);
  }
  
  public void startGeneralEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void textDecl(String paramString1, String paramString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void endGeneralEntity(String paramString, Augmentations paramAugmentations) throws XNIException {}
  
  public void characters(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (!this.fIgnoreChars) {
      Element element = (Element)this.fDOMValidatorHelper.getCurrentElement();
      element.appendChild(this.fDocument.createTextNode(paramXMLString.toString()));
    } 
  }
  
  public void ignorableWhitespace(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    characters(paramXMLString, paramAugmentations);
  }
  
  public void endElement(QName paramQName, Augmentations paramAugmentations) throws XNIException {
    Node node = this.fDOMValidatorHelper.getCurrentElement();
    if (paramAugmentations != null && this.fDocumentImpl != null) {
      ElementPSVI elementPSVI = (ElementPSVI)paramAugmentations.getItem("ELEMENT_PSVI");
      if (elementPSVI != null) {
        XSTypeDefinition xSTypeDefinition;
        if (this.fStorePSVI)
          ((PSVIElementNSImpl)node).setPSVI(elementPSVI); 
        XSSimpleTypeDefinition xSSimpleTypeDefinition = elementPSVI.getMemberTypeDefinition();
        if (xSSimpleTypeDefinition == null)
          xSTypeDefinition = elementPSVI.getTypeDefinition(); 
        ((ElementNSImpl)node).setType(xSTypeDefinition);
      } 
    } 
  }
  
  public void startCDATA(Augmentations paramAugmentations) throws XNIException {}
  
  public void endCDATA(Augmentations paramAugmentations) throws XNIException {}
  
  public void endDocument(Augmentations paramAugmentations) throws XNIException {}
  
  public void setDocumentSource(XMLDocumentSource paramXMLDocumentSource) {}
  
  public XMLDocumentSource getDocumentSource() {
    return null;
  }
  
  private boolean processAttributePSVI(AttrImpl paramAttrImpl, AttributePSVI paramAttributePSVI) {
    XSTypeDefinition xSTypeDefinition;
    if (this.fStorePSVI)
      ((PSVIAttrNSImpl)paramAttrImpl).setPSVI(paramAttributePSVI); 
    XSSimpleTypeDefinition xSSimpleTypeDefinition = paramAttributePSVI.getMemberTypeDefinition();
    if (xSSimpleTypeDefinition == null) {
      xSTypeDefinition = paramAttributePSVI.getTypeDefinition();
      if (xSTypeDefinition != null) {
        paramAttrImpl.setType(xSTypeDefinition);
        return ((XSSimpleType)xSTypeDefinition).isIDType();
      } 
    } else {
      paramAttrImpl.setType(xSTypeDefinition);
      return ((XSSimpleType)xSTypeDefinition).isIDType();
    } 
    return false;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\validation\DOMResultAugmentor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */