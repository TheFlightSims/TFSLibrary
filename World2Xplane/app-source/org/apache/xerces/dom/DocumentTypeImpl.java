package org.apache.xerces.dom;

import java.util.Hashtable;
import org.w3c.dom.DOMException;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.UserDataHandler;

public class DocumentTypeImpl extends ParentNode implements DocumentType {
  static final long serialVersionUID = 7751299192316526485L;
  
  protected String name;
  
  protected NamedNodeMapImpl entities;
  
  protected NamedNodeMapImpl notations;
  
  protected NamedNodeMapImpl elements;
  
  protected String publicID;
  
  protected String systemID;
  
  protected String internalSubset;
  
  private int doctypeNumber = 0;
  
  private Hashtable userData = null;
  
  public DocumentTypeImpl(CoreDocumentImpl paramCoreDocumentImpl, String paramString) {
    super(paramCoreDocumentImpl);
    this.name = paramString;
    this.entities = new NamedNodeMapImpl(this);
    this.notations = new NamedNodeMapImpl(this);
    this.elements = new NamedNodeMapImpl(this);
  }
  
  public DocumentTypeImpl(CoreDocumentImpl paramCoreDocumentImpl, String paramString1, String paramString2, String paramString3) {
    this(paramCoreDocumentImpl, paramString1);
    this.publicID = paramString2;
    this.systemID = paramString3;
  }
  
  public String getPublicId() {
    if (needsSyncData())
      synchronizeData(); 
    return this.publicID;
  }
  
  public String getSystemId() {
    if (needsSyncData())
      synchronizeData(); 
    return this.systemID;
  }
  
  public void setInternalSubset(String paramString) {
    if (needsSyncData())
      synchronizeData(); 
    this.internalSubset = paramString;
  }
  
  public String getInternalSubset() {
    if (needsSyncData())
      synchronizeData(); 
    return this.internalSubset;
  }
  
  public short getNodeType() {
    return 10;
  }
  
  public String getNodeName() {
    if (needsSyncData())
      synchronizeData(); 
    return this.name;
  }
  
  public Node cloneNode(boolean paramBoolean) {
    DocumentTypeImpl documentTypeImpl = (DocumentTypeImpl)super.cloneNode(paramBoolean);
    documentTypeImpl.entities = this.entities.cloneMap(documentTypeImpl);
    documentTypeImpl.notations = this.notations.cloneMap(documentTypeImpl);
    documentTypeImpl.elements = this.elements.cloneMap(documentTypeImpl);
    return documentTypeImpl;
  }
  
  public String getTextContent() throws DOMException {
    return null;
  }
  
  public void setTextContent(String paramString) throws DOMException {}
  
  public boolean isEqualNode(Node paramNode) {
    if (!super.isEqualNode(paramNode))
      return false; 
    if (needsSyncData())
      synchronizeData(); 
    DocumentTypeImpl documentTypeImpl = (DocumentTypeImpl)paramNode;
    if ((getPublicId() == null && documentTypeImpl.getPublicId() != null) || (getPublicId() != null && documentTypeImpl.getPublicId() == null) || (getSystemId() == null && documentTypeImpl.getSystemId() != null) || (getSystemId() != null && documentTypeImpl.getSystemId() == null) || (getInternalSubset() == null && documentTypeImpl.getInternalSubset() != null) || (getInternalSubset() != null && documentTypeImpl.getInternalSubset() == null))
      return false; 
    if (getPublicId() != null && !getPublicId().equals(documentTypeImpl.getPublicId()))
      return false; 
    if (getSystemId() != null && !getSystemId().equals(documentTypeImpl.getSystemId()))
      return false; 
    if (getInternalSubset() != null && !getInternalSubset().equals(documentTypeImpl.getInternalSubset()))
      return false; 
    NamedNodeMapImpl namedNodeMapImpl1 = documentTypeImpl.entities;
    if ((this.entities == null && namedNodeMapImpl1 != null) || (this.entities != null && namedNodeMapImpl1 == null))
      return false; 
    if (this.entities != null && namedNodeMapImpl1 != null) {
      if (this.entities.getLength() != namedNodeMapImpl1.getLength())
        return false; 
      for (byte b = 0; this.entities.item(b) != null; b++) {
        Node node1 = this.entities.item(b);
        Node node2 = namedNodeMapImpl1.getNamedItem(node1.getNodeName());
        if (!((NodeImpl)node1).isEqualNode(node2))
          return false; 
      } 
    } 
    NamedNodeMapImpl namedNodeMapImpl2 = documentTypeImpl.notations;
    if ((this.notations == null && namedNodeMapImpl2 != null) || (this.notations != null && namedNodeMapImpl2 == null))
      return false; 
    if (this.notations != null && namedNodeMapImpl2 != null) {
      if (this.notations.getLength() != namedNodeMapImpl2.getLength())
        return false; 
      for (byte b = 0; this.notations.item(b) != null; b++) {
        Node node1 = this.notations.item(b);
        Node node2 = namedNodeMapImpl2.getNamedItem(node1.getNodeName());
        if (!((NodeImpl)node1).isEqualNode(node2))
          return false; 
      } 
    } 
    return true;
  }
  
  protected void setOwnerDocument(CoreDocumentImpl paramCoreDocumentImpl) {
    super.setOwnerDocument(paramCoreDocumentImpl);
    this.entities.setOwnerDocument(paramCoreDocumentImpl);
    this.notations.setOwnerDocument(paramCoreDocumentImpl);
    this.elements.setOwnerDocument(paramCoreDocumentImpl);
  }
  
  protected int getNodeNumber() {
    if (getOwnerDocument() != null)
      return super.getNodeNumber(); 
    if (this.doctypeNumber == 0) {
      CoreDOMImplementationImpl coreDOMImplementationImpl = (CoreDOMImplementationImpl)CoreDOMImplementationImpl.getDOMImplementation();
      this.doctypeNumber = coreDOMImplementationImpl.assignDocTypeNumber();
    } 
    return this.doctypeNumber;
  }
  
  public String getName() {
    if (needsSyncData())
      synchronizeData(); 
    return this.name;
  }
  
  public NamedNodeMap getEntities() {
    if (needsSyncChildren())
      synchronizeChildren(); 
    return this.entities;
  }
  
  public NamedNodeMap getNotations() {
    if (needsSyncChildren())
      synchronizeChildren(); 
    return this.notations;
  }
  
  public void setReadOnly(boolean paramBoolean1, boolean paramBoolean2) {
    if (needsSyncChildren())
      synchronizeChildren(); 
    super.setReadOnly(paramBoolean1, paramBoolean2);
    this.elements.setReadOnly(paramBoolean1, true);
    this.entities.setReadOnly(paramBoolean1, true);
    this.notations.setReadOnly(paramBoolean1, true);
  }
  
  public NamedNodeMap getElements() {
    if (needsSyncChildren())
      synchronizeChildren(); 
    return this.elements;
  }
  
  public Object setUserData(String paramString, Object paramObject, UserDataHandler paramUserDataHandler) {
    if (this.userData == null)
      this.userData = new Hashtable(); 
    if (paramObject == null) {
      if (this.userData != null) {
        Object object = this.userData.remove(paramString);
        if (object != null) {
          ParentNode.UserDataRecord userDataRecord1 = (ParentNode.UserDataRecord)object;
          return userDataRecord1.fData;
        } 
      } 
      return null;
    } 
    ParentNode.UserDataRecord userDataRecord = (ParentNode.UserDataRecord)this.userData.put(paramString, new ParentNode.UserDataRecord(this, paramObject, paramUserDataHandler));
    if (userDataRecord != null) {
      ParentNode.UserDataRecord userDataRecord1 = userDataRecord;
      return userDataRecord1.fData;
    } 
    return null;
  }
  
  public Object getUserData(String paramString) {
    if (this.userData == null)
      return null; 
    Object object = this.userData.get(paramString);
    if (object != null) {
      ParentNode.UserDataRecord userDataRecord = (ParentNode.UserDataRecord)object;
      return userDataRecord.fData;
    } 
    return null;
  }
  
  protected Hashtable getUserDataRecord() {
    return this.userData;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DocumentTypeImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */