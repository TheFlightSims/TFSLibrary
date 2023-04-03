package org.apache.xerces.dom;

import java.util.Vector;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class AttributeMap extends NamedNodeMapImpl {
  static final long serialVersionUID = 8872606282138665383L;
  
  protected AttributeMap(ElementImpl paramElementImpl, NamedNodeMapImpl paramNamedNodeMapImpl) {
    super(paramElementImpl);
    if (paramNamedNodeMapImpl != null) {
      cloneContent(paramNamedNodeMapImpl);
      if (this.nodes != null)
        hasDefaults(true); 
    } 
  }
  
  public Node setNamedItem(Node paramNode) throws DOMException {
    boolean bool = (this.ownerNode.ownerDocument()).errorChecking;
    if (bool) {
      if (isReadOnly()) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
        throw new DOMException((short)7, str);
      } 
      if (paramNode.getOwnerDocument() != this.ownerNode.ownerDocument()) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null);
        throw new DOMException((short)4, str);
      } 
      if (paramNode.getNodeType() != 2) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "HIERARCHY_REQUEST_ERR", null);
        throw new DOMException((short)3, str);
      } 
    } 
    AttrImpl attrImpl1 = (AttrImpl)paramNode;
    if (attrImpl1.isOwned()) {
      if (bool && attrImpl1.getOwnerElement() != this.ownerNode) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INUSE_ATTRIBUTE_ERR", null);
        throw new DOMException((short)10, str);
      } 
      return paramNode;
    } 
    attrImpl1.ownerNode = this.ownerNode;
    attrImpl1.isOwned(true);
    int i = findNamePoint(attrImpl1.getNodeName(), 0);
    AttrImpl attrImpl2 = null;
    if (i >= 0) {
      attrImpl2 = this.nodes.elementAt(i);
      this.nodes.setElementAt(paramNode, i);
      attrImpl2.ownerNode = this.ownerNode.ownerDocument();
      attrImpl2.isOwned(false);
      attrImpl2.isSpecified(true);
    } else {
      i = -1 - i;
      if (null == this.nodes)
        this.nodes = new Vector(5, 10); 
      this.nodes.insertElementAt(paramNode, i);
    } 
    this.ownerNode.ownerDocument().setAttrNode(attrImpl1, attrImpl2);
    if (!attrImpl1.isNormalized())
      this.ownerNode.isNormalized(false); 
    return attrImpl2;
  }
  
  public Node setNamedItemNS(Node paramNode) throws DOMException {
    boolean bool = (this.ownerNode.ownerDocument()).errorChecking;
    if (bool) {
      if (isReadOnly()) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
        throw new DOMException((short)7, str);
      } 
      if (paramNode.getOwnerDocument() != this.ownerNode.ownerDocument()) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null);
        throw new DOMException((short)4, str);
      } 
      if (paramNode.getNodeType() != 2) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "HIERARCHY_REQUEST_ERR", null);
        throw new DOMException((short)3, str);
      } 
    } 
    AttrImpl attrImpl1 = (AttrImpl)paramNode;
    if (attrImpl1.isOwned()) {
      if (bool && attrImpl1.getOwnerElement() != this.ownerNode) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "INUSE_ATTRIBUTE_ERR", null);
        throw new DOMException((short)10, str);
      } 
      return paramNode;
    } 
    attrImpl1.ownerNode = this.ownerNode;
    attrImpl1.isOwned(true);
    int i = findNamePoint(attrImpl1.getNamespaceURI(), attrImpl1.getLocalName());
    AttrImpl attrImpl2 = null;
    if (i >= 0) {
      attrImpl2 = this.nodes.elementAt(i);
      this.nodes.setElementAt(paramNode, i);
      attrImpl2.ownerNode = this.ownerNode.ownerDocument();
      attrImpl2.isOwned(false);
      attrImpl2.isSpecified(true);
    } else {
      i = findNamePoint(paramNode.getNodeName(), 0);
      if (i >= 0) {
        attrImpl2 = this.nodes.elementAt(i);
        this.nodes.insertElementAt(paramNode, i);
      } else {
        i = -1 - i;
        if (null == this.nodes)
          this.nodes = new Vector(5, 10); 
        this.nodes.insertElementAt(paramNode, i);
      } 
    } 
    this.ownerNode.ownerDocument().setAttrNode(attrImpl1, attrImpl2);
    if (!attrImpl1.isNormalized())
      this.ownerNode.isNormalized(false); 
    return attrImpl2;
  }
  
  public Node removeNamedItem(String paramString) throws DOMException {
    return internalRemoveNamedItem(paramString, true);
  }
  
  Node safeRemoveNamedItem(String paramString) {
    return internalRemoveNamedItem(paramString, false);
  }
  
  protected Node removeItem(Node paramNode, boolean paramBoolean) throws DOMException {
    byte b = -1;
    if (this.nodes != null)
      for (byte b1 = 0; b1 < this.nodes.size(); b1++) {
        if (this.nodes.elementAt(b1) == paramNode) {
          b = b1;
          break;
        } 
      }  
    if (b < 0) {
      String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null);
      throw new DOMException((short)8, str);
    } 
    return remove((AttrImpl)paramNode, b, paramBoolean);
  }
  
  protected final Node internalRemoveNamedItem(String paramString, boolean paramBoolean) {
    if (isReadOnly()) {
      String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
      throw new DOMException((short)7, str);
    } 
    int i = findNamePoint(paramString, 0);
    if (i < 0) {
      if (paramBoolean) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null);
        throw new DOMException((short)8, str);
      } 
      return null;
    } 
    return remove(this.nodes.elementAt(i), i, true);
  }
  
  private final Node remove(AttrImpl paramAttrImpl, int paramInt, boolean paramBoolean) {
    CoreDocumentImpl coreDocumentImpl = this.ownerNode.ownerDocument();
    String str = paramAttrImpl.getNodeName();
    if (paramAttrImpl.isIdAttribute())
      coreDocumentImpl.removeIdentifier(paramAttrImpl.getValue()); 
    if (hasDefaults() && paramBoolean) {
      NamedNodeMapImpl namedNodeMapImpl = ((ElementImpl)this.ownerNode).getDefaultAttributes();
      Node node;
      if (namedNodeMapImpl != null && (node = namedNodeMapImpl.getNamedItem(str)) != null && findNamePoint(str, paramInt + 1) < 0) {
        NodeImpl nodeImpl = (NodeImpl)node.cloneNode(true);
        if (node.getLocalName() != null)
          ((AttrNSImpl)nodeImpl).namespaceURI = paramAttrImpl.getNamespaceURI(); 
        nodeImpl.ownerNode = this.ownerNode;
        nodeImpl.isOwned(true);
        nodeImpl.isSpecified(false);
        this.nodes.setElementAt(nodeImpl, paramInt);
        if (paramAttrImpl.isIdAttribute())
          coreDocumentImpl.putIdentifier(nodeImpl.getNodeValue(), (ElementImpl)this.ownerNode); 
      } else {
        this.nodes.removeElementAt(paramInt);
      } 
    } else {
      this.nodes.removeElementAt(paramInt);
    } 
    paramAttrImpl.ownerNode = coreDocumentImpl;
    paramAttrImpl.isOwned(false);
    paramAttrImpl.isSpecified(true);
    paramAttrImpl.isIdAttribute(false);
    coreDocumentImpl.removedAttrNode(paramAttrImpl, this.ownerNode, str);
    return paramAttrImpl;
  }
  
  public Node removeNamedItemNS(String paramString1, String paramString2) throws DOMException {
    return internalRemoveNamedItemNS(paramString1, paramString2, true);
  }
  
  Node safeRemoveNamedItemNS(String paramString1, String paramString2) {
    return internalRemoveNamedItemNS(paramString1, paramString2, false);
  }
  
  protected final Node internalRemoveNamedItemNS(String paramString1, String paramString2, boolean paramBoolean) {
    CoreDocumentImpl coreDocumentImpl = this.ownerNode.ownerDocument();
    if (coreDocumentImpl.errorChecking && isReadOnly()) {
      String str1 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
      throw new DOMException((short)7, str1);
    } 
    int i = findNamePoint(paramString1, paramString2);
    if (i < 0) {
      if (paramBoolean) {
        String str1 = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null);
        throw new DOMException((short)8, str1);
      } 
      return null;
    } 
    AttrImpl attrImpl = this.nodes.elementAt(i);
    if (attrImpl.isIdAttribute())
      coreDocumentImpl.removeIdentifier(attrImpl.getValue()); 
    String str = attrImpl.getNodeName();
    if (hasDefaults()) {
      NamedNodeMapImpl namedNodeMapImpl = ((ElementImpl)this.ownerNode).getDefaultAttributes();
      Node node;
      if (namedNodeMapImpl != null && (node = namedNodeMapImpl.getNamedItem(str)) != null) {
        int j = findNamePoint(str, 0);
        if (j >= 0 && findNamePoint(str, j + 1) < 0) {
          NodeImpl nodeImpl = (NodeImpl)node.cloneNode(true);
          nodeImpl.ownerNode = this.ownerNode;
          if (node.getLocalName() != null)
            ((AttrNSImpl)nodeImpl).namespaceURI = paramString1; 
          nodeImpl.isOwned(true);
          nodeImpl.isSpecified(false);
          this.nodes.setElementAt(nodeImpl, i);
          if (nodeImpl.isIdAttribute())
            coreDocumentImpl.putIdentifier(nodeImpl.getNodeValue(), (ElementImpl)this.ownerNode); 
        } else {
          this.nodes.removeElementAt(i);
        } 
      } else {
        this.nodes.removeElementAt(i);
      } 
    } else {
      this.nodes.removeElementAt(i);
    } 
    attrImpl.ownerNode = coreDocumentImpl;
    attrImpl.isOwned(false);
    attrImpl.isSpecified(true);
    attrImpl.isIdAttribute(false);
    coreDocumentImpl.removedAttrNode(attrImpl, this.ownerNode, paramString2);
    return attrImpl;
  }
  
  public NamedNodeMapImpl cloneMap(NodeImpl paramNodeImpl) {
    AttributeMap attributeMap = new AttributeMap((ElementImpl)paramNodeImpl, null);
    attributeMap.hasDefaults(hasDefaults());
    attributeMap.cloneContent(this);
    return attributeMap;
  }
  
  protected void cloneContent(NamedNodeMapImpl paramNamedNodeMapImpl) {
    Vector vector = paramNamedNodeMapImpl.nodes;
    if (vector != null) {
      int i = vector.size();
      if (i != 0) {
        if (this.nodes == null)
          this.nodes = new Vector(i); 
        this.nodes.setSize(i);
        for (byte b = 0; b < i; b++) {
          NodeImpl nodeImpl1 = vector.elementAt(b);
          NodeImpl nodeImpl2 = (NodeImpl)nodeImpl1.cloneNode(true);
          nodeImpl2.isSpecified(nodeImpl1.isSpecified());
          this.nodes.setElementAt(nodeImpl2, b);
          nodeImpl2.ownerNode = this.ownerNode;
          nodeImpl2.isOwned(true);
        } 
      } 
    } 
  }
  
  void moveSpecifiedAttributes(AttributeMap paramAttributeMap) {
    byte b = (paramAttributeMap.nodes != null) ? paramAttributeMap.nodes.size() : 0;
    for (int i = b - 1; i >= 0; i--) {
      AttrImpl attrImpl = paramAttributeMap.nodes.elementAt(i);
      if (attrImpl.isSpecified()) {
        paramAttributeMap.remove(attrImpl, i, false);
        if (attrImpl.getLocalName() != null) {
          setNamedItem(attrImpl);
        } else {
          setNamedItemNS(attrImpl);
        } 
      } 
    } 
  }
  
  protected void reconcileDefaults(NamedNodeMapImpl paramNamedNodeMapImpl) {
    byte b = (this.nodes != null) ? this.nodes.size() : 0;
    for (int i = b - 1; i >= 0; i--) {
      AttrImpl attrImpl = this.nodes.elementAt(i);
      if (!attrImpl.isSpecified())
        remove(attrImpl, i, false); 
    } 
    if (paramNamedNodeMapImpl == null)
      return; 
    if (this.nodes == null || this.nodes.size() == 0) {
      cloneContent(paramNamedNodeMapImpl);
    } else {
      int j = paramNamedNodeMapImpl.nodes.size();
      for (byte b1 = 0; b1 < j; b1++) {
        AttrImpl attrImpl = paramNamedNodeMapImpl.nodes.elementAt(b1);
        int k = findNamePoint(attrImpl.getNodeName(), 0);
        if (k < 0) {
          k = -1 - k;
          NodeImpl nodeImpl = (NodeImpl)attrImpl.cloneNode(true);
          nodeImpl.ownerNode = this.ownerNode;
          nodeImpl.isOwned(true);
          nodeImpl.isSpecified(false);
          this.nodes.insertElementAt(nodeImpl, k);
        } 
      } 
    } 
  }
  
  protected final int addItem(Node paramNode) {
    AttrImpl attrImpl = (AttrImpl)paramNode;
    attrImpl.ownerNode = this.ownerNode;
    attrImpl.isOwned(true);
    int i = findNamePoint(attrImpl.getNamespaceURI(), attrImpl.getLocalName());
    if (i >= 0) {
      this.nodes.setElementAt(paramNode, i);
    } else {
      i = findNamePoint(attrImpl.getNodeName(), 0);
      if (i >= 0) {
        this.nodes.insertElementAt(paramNode, i);
      } else {
        i = -1 - i;
        if (null == this.nodes)
          this.nodes = new Vector(5, 10); 
        this.nodes.insertElementAt(paramNode, i);
      } 
    } 
    this.ownerNode.ownerDocument().setAttrNode(attrImpl, null);
    return i;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\AttributeMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */