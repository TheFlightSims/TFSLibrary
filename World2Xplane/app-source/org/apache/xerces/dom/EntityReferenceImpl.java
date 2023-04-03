package org.apache.xerces.dom;

import org.apache.xerces.util.URI;
import org.w3c.dom.DocumentType;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class EntityReferenceImpl extends ParentNode implements EntityReference {
  static final long serialVersionUID = -7381452955687102062L;
  
  protected String name;
  
  protected String baseURI;
  
  public EntityReferenceImpl(CoreDocumentImpl paramCoreDocumentImpl, String paramString) {
    super(paramCoreDocumentImpl);
    this.name = paramString;
    isReadOnly(true);
    needsSyncChildren(true);
  }
  
  public short getNodeType() {
    return 5;
  }
  
  public String getNodeName() {
    if (needsSyncData())
      synchronizeData(); 
    return this.name;
  }
  
  public Node cloneNode(boolean paramBoolean) {
    EntityReferenceImpl entityReferenceImpl = (EntityReferenceImpl)super.cloneNode(paramBoolean);
    entityReferenceImpl.setReadOnly(true, paramBoolean);
    return entityReferenceImpl;
  }
  
  public String getBaseURI() {
    if (needsSyncData())
      synchronizeData(); 
    if (this.baseURI == null) {
      DocumentType documentType;
      NamedNodeMap namedNodeMap;
      if (null != (documentType = getOwnerDocument().getDoctype()) && null != (namedNodeMap = documentType.getEntities())) {
        EntityImpl entityImpl = (EntityImpl)namedNodeMap.getNamedItem(getNodeName());
        if (entityImpl != null)
          return entityImpl.getBaseURI(); 
      } 
    } else if (this.baseURI != null && this.baseURI.length() != 0) {
      try {
        return (new URI(this.baseURI)).toString();
      } catch (org.apache.xerces.util.URI.MalformedURIException malformedURIException) {
        return null;
      } 
    } 
    return this.baseURI;
  }
  
  public void setBaseURI(String paramString) {
    if (needsSyncData())
      synchronizeData(); 
    this.baseURI = paramString;
  }
  
  protected String getEntityRefValue() {
    if (needsSyncChildren())
      synchronizeChildren(); 
    String str = "";
    if (this.firstChild != null) {
      if (this.firstChild.getNodeType() == 5) {
        str = ((EntityReferenceImpl)this.firstChild).getEntityRefValue();
      } else if (this.firstChild.getNodeType() == 3) {
        str = this.firstChild.getNodeValue();
      } else {
        return null;
      } 
      if (this.firstChild.nextSibling == null)
        return str; 
      StringBuffer stringBuffer = new StringBuffer(str);
      for (ChildNode childNode = this.firstChild.nextSibling; childNode != null; childNode = childNode.nextSibling) {
        if (childNode.getNodeType() == 5) {
          str = ((EntityReferenceImpl)childNode).getEntityRefValue();
        } else if (childNode.getNodeType() == 3) {
          str = childNode.getNodeValue();
        } else {
          return null;
        } 
        stringBuffer.append(str);
      } 
      return stringBuffer.toString();
    } 
    return "";
  }
  
  protected void synchronizeChildren() {
    needsSyncChildren(false);
    DocumentType documentType;
    NamedNodeMap namedNodeMap;
    if (null != (documentType = getOwnerDocument().getDoctype()) && null != (namedNodeMap = documentType.getEntities())) {
      EntityImpl entityImpl = (EntityImpl)namedNodeMap.getNamedItem(getNodeName());
      if (entityImpl == null)
        return; 
      isReadOnly(false);
      for (Node node = entityImpl.getFirstChild(); node != null; node = node.getNextSibling()) {
        Node node1 = node.cloneNode(true);
        insertBefore(node1, null);
      } 
      setReadOnly(true, true);
    } 
  }
  
  public void setReadOnly(boolean paramBoolean1, boolean paramBoolean2) {
    if (needsSyncData())
      synchronizeData(); 
    if (paramBoolean2) {
      if (needsSyncChildren())
        synchronizeChildren(); 
      for (ChildNode childNode = this.firstChild; childNode != null; childNode = childNode.nextSibling)
        childNode.setReadOnly(paramBoolean1, true); 
    } 
    isReadOnly(paramBoolean1);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\EntityReferenceImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */