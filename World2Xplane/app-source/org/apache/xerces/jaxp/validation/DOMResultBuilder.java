package org.apache.xerces.jaxp.validation;

import java.util.ArrayList;
import javax.xml.transform.dom.DOMResult;
import org.apache.xerces.dom.AttrImpl;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.apache.xerces.dom.DOMMessageFormatter;
import org.apache.xerces.dom.DocumentTypeImpl;
import org.apache.xerces.dom.ElementImpl;
import org.apache.xerces.dom.ElementNSImpl;
import org.apache.xerces.dom.EntityImpl;
import org.apache.xerces.dom.NotationImpl;
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
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Notation;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

final class DOMResultBuilder implements DOMDocumentHandler {
  private static final int[] kidOK = new int[13];
  
  private Document fDocument;
  
  private CoreDocumentImpl fDocumentImpl;
  
  private boolean fStorePSVI;
  
  private Node fTarget;
  
  private Node fNextSibling;
  
  private Node fCurrentNode;
  
  private Node fFragmentRoot;
  
  private final ArrayList fTargetChildren = new ArrayList();
  
  private boolean fIgnoreChars;
  
  private final QName fAttributeQName = new QName();
  
  public void setDOMResult(DOMResult paramDOMResult) {
    this.fCurrentNode = null;
    this.fFragmentRoot = null;
    this.fIgnoreChars = false;
    this.fTargetChildren.clear();
    if (paramDOMResult != null) {
      this.fTarget = paramDOMResult.getNode();
      this.fNextSibling = paramDOMResult.getNextSibling();
      this.fDocument = (this.fTarget.getNodeType() == 9) ? (Document)this.fTarget : this.fTarget.getOwnerDocument();
      this.fDocumentImpl = (this.fDocument instanceof CoreDocumentImpl) ? (CoreDocumentImpl)this.fDocument : null;
      this.fStorePSVI = this.fDocument instanceof org.apache.xerces.dom.PSVIDocumentImpl;
      return;
    } 
    this.fTarget = null;
    this.fNextSibling = null;
    this.fDocument = null;
    this.fDocumentImpl = null;
    this.fStorePSVI = false;
  }
  
  public void doctypeDecl(DocumentType paramDocumentType) throws XNIException {
    if (this.fDocumentImpl != null) {
      DocumentType documentType = this.fDocumentImpl.createDocumentType(paramDocumentType.getName(), paramDocumentType.getPublicId(), paramDocumentType.getSystemId());
      String str = paramDocumentType.getInternalSubset();
      if (str != null)
        ((DocumentTypeImpl)documentType).setInternalSubset(str); 
      NamedNodeMap namedNodeMap1 = paramDocumentType.getEntities();
      NamedNodeMap namedNodeMap2 = documentType.getEntities();
      int i = namedNodeMap1.getLength();
      for (byte b1 = 0; b1 < i; b1++) {
        Entity entity = (Entity)namedNodeMap1.item(b1);
        EntityImpl entityImpl = (EntityImpl)this.fDocumentImpl.createEntity(entity.getNodeName());
        entityImpl.setPublicId(entity.getPublicId());
        entityImpl.setSystemId(entity.getSystemId());
        entityImpl.setNotationName(entity.getNotationName());
        namedNodeMap2.setNamedItem((Node)entityImpl);
      } 
      namedNodeMap1 = paramDocumentType.getNotations();
      namedNodeMap2 = documentType.getNotations();
      i = namedNodeMap1.getLength();
      for (byte b2 = 0; b2 < i; b2++) {
        Notation notation = (Notation)namedNodeMap1.item(b2);
        NotationImpl notationImpl = (NotationImpl)this.fDocumentImpl.createNotation(notation.getNodeName());
        notationImpl.setPublicId(notation.getPublicId());
        notationImpl.setSystemId(notation.getSystemId());
        namedNodeMap2.setNamedItem((Node)notationImpl);
      } 
      append(documentType);
    } 
  }
  
  public void characters(Text paramText) throws XNIException {
    append(this.fDocument.createTextNode(paramText.getNodeValue()));
  }
  
  public void cdata(CDATASection paramCDATASection) throws XNIException {
    append(this.fDocument.createCDATASection(paramCDATASection.getNodeValue()));
  }
  
  public void comment(Comment paramComment) throws XNIException {
    append(this.fDocument.createComment(paramComment.getNodeValue()));
  }
  
  public void processingInstruction(ProcessingInstruction paramProcessingInstruction) throws XNIException {
    append(this.fDocument.createProcessingInstruction(paramProcessingInstruction.getTarget(), paramProcessingInstruction.getData()));
  }
  
  public void setIgnoringCharacters(boolean paramBoolean) {
    this.fIgnoreChars = paramBoolean;
  }
  
  public void startDocument(XMLLocator paramXMLLocator, String paramString, NamespaceContext paramNamespaceContext, Augmentations paramAugmentations) throws XNIException {}
  
  public void xmlDecl(String paramString1, String paramString2, String paramString3, Augmentations paramAugmentations) throws XNIException {}
  
  public void doctypeDecl(String paramString1, String paramString2, String paramString3, Augmentations paramAugmentations) throws XNIException {}
  
  public void comment(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {}
  
  public void processingInstruction(String paramString, XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {}
  
  public void startElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    Element element;
    int i = paramXMLAttributes.getLength();
    if (this.fDocumentImpl == null) {
      element = this.fDocument.createElementNS(paramQName.uri, paramQName.rawname);
      for (byte b = 0; b < i; b++) {
        paramXMLAttributes.getName(b, this.fAttributeQName);
        element.setAttributeNS(this.fAttributeQName.uri, this.fAttributeQName.rawname, paramXMLAttributes.getValue(b));
      } 
    } else {
      element = this.fDocumentImpl.createElementNS(paramQName.uri, paramQName.rawname, paramQName.localpart);
      for (byte b = 0; b < i; b++) {
        paramXMLAttributes.getName(b, this.fAttributeQName);
        AttrImpl attrImpl = (AttrImpl)this.fDocumentImpl.createAttributeNS(this.fAttributeQName.uri, this.fAttributeQName.rawname, this.fAttributeQName.localpart);
        attrImpl.setValue(paramXMLAttributes.getValue(b));
        element.setAttributeNodeNS((Attr)attrImpl);
        AttributePSVI attributePSVI = (AttributePSVI)paramXMLAttributes.getAugmentations(b).getItem("ATTRIBUTE_PSVI");
        if (attributePSVI != null) {
          XSTypeDefinition xSTypeDefinition;
          if (this.fStorePSVI)
            ((PSVIAttrNSImpl)attrImpl).setPSVI(attributePSVI); 
          XSSimpleTypeDefinition xSSimpleTypeDefinition = attributePSVI.getMemberTypeDefinition();
          if (xSSimpleTypeDefinition == null) {
            xSTypeDefinition = attributePSVI.getTypeDefinition();
            if (xSTypeDefinition != null) {
              attrImpl.setType(xSTypeDefinition);
              if (((XSSimpleType)xSTypeDefinition).isIDType())
                ((ElementImpl)element).setIdAttributeNode((Attr)attrImpl, true); 
            } 
          } else {
            attrImpl.setType(xSTypeDefinition);
            if (((XSSimpleType)xSTypeDefinition).isIDType())
              ((ElementImpl)element).setIdAttributeNode((Attr)attrImpl, true); 
          } 
        } 
        attrImpl.setSpecified(paramXMLAttributes.isSpecified(b));
      } 
    } 
    append(element);
    this.fCurrentNode = element;
    if (this.fFragmentRoot == null)
      this.fFragmentRoot = element; 
  }
  
  public void emptyElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    startElement(paramQName, paramXMLAttributes, paramAugmentations);
    endElement(paramQName, paramAugmentations);
  }
  
  public void startGeneralEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void textDecl(String paramString1, String paramString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void endGeneralEntity(String paramString, Augmentations paramAugmentations) throws XNIException {}
  
  public void characters(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (!this.fIgnoreChars)
      append(this.fDocument.createTextNode(paramXMLString.toString())); 
  }
  
  public void ignorableWhitespace(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    characters(paramXMLString, paramAugmentations);
  }
  
  public void endElement(QName paramQName, Augmentations paramAugmentations) throws XNIException {
    if (paramAugmentations != null && this.fDocumentImpl != null) {
      ElementPSVI elementPSVI = (ElementPSVI)paramAugmentations.getItem("ELEMENT_PSVI");
      if (elementPSVI != null) {
        XSTypeDefinition xSTypeDefinition;
        if (this.fStorePSVI)
          ((PSVIElementNSImpl)this.fCurrentNode).setPSVI(elementPSVI); 
        XSSimpleTypeDefinition xSSimpleTypeDefinition = elementPSVI.getMemberTypeDefinition();
        if (xSSimpleTypeDefinition == null)
          xSTypeDefinition = elementPSVI.getTypeDefinition(); 
        ((ElementNSImpl)this.fCurrentNode).setType(xSTypeDefinition);
      } 
    } 
    if (this.fCurrentNode == this.fFragmentRoot) {
      this.fCurrentNode = null;
      this.fFragmentRoot = null;
      return;
    } 
    this.fCurrentNode = this.fCurrentNode.getParentNode();
  }
  
  public void startCDATA(Augmentations paramAugmentations) throws XNIException {}
  
  public void endCDATA(Augmentations paramAugmentations) throws XNIException {}
  
  public void endDocument(Augmentations paramAugmentations) throws XNIException {
    int i = this.fTargetChildren.size();
    if (this.fNextSibling == null) {
      for (byte b = 0; b < i; b++)
        this.fTarget.appendChild(this.fTargetChildren.get(b)); 
    } else {
      for (byte b = 0; b < i; b++)
        this.fTarget.insertBefore(this.fTargetChildren.get(b), this.fNextSibling); 
    } 
  }
  
  public void setDocumentSource(XMLDocumentSource paramXMLDocumentSource) {}
  
  public XMLDocumentSource getDocumentSource() {
    return null;
  }
  
  private void append(Node paramNode) throws XNIException {
    if (this.fCurrentNode != null) {
      this.fCurrentNode.appendChild(paramNode);
    } else {
      if ((kidOK[this.fTarget.getNodeType()] & 1 << paramNode.getNodeType()) == 0) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "HIERARCHY_REQUEST_ERR", null);
        throw new XNIException(str);
      } 
      this.fTargetChildren.add(paramNode);
    } 
  }
  
  static {
    kidOK[9] = 1410;
    kidOK[1] = 442;
    kidOK[5] = 442;
    kidOK[6] = 442;
    kidOK[11] = 442;
    kidOK[2] = 40;
    kidOK[10] = 0;
    kidOK[7] = 0;
    kidOK[8] = 0;
    kidOK[3] = 0;
    kidOK[4] = 0;
    kidOK[12] = 0;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\jaxp\validation\DOMResultBuilder.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */