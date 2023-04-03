package org.apache.xerces.dom;

import java.io.Serializable;
import java.util.Vector;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class NamedNodeMapImpl implements NamedNodeMap, Serializable {
  static final long serialVersionUID = -7039242451046758020L;
  
  protected short flags;
  
  protected static final short READONLY = 1;
  
  protected static final short CHANGED = 2;
  
  protected static final short HASDEFAULTS = 4;
  
  protected Vector nodes;
  
  protected NodeImpl ownerNode;
  
  protected NamedNodeMapImpl(NodeImpl paramNodeImpl) {
    this.ownerNode = paramNodeImpl;
  }
  
  public int getLength() {
    return (this.nodes != null) ? this.nodes.size() : 0;
  }
  
  public Node item(int paramInt) {
    return (this.nodes != null && paramInt < this.nodes.size()) ? this.nodes.elementAt(paramInt) : null;
  }
  
  public Node getNamedItem(String paramString) {
    int i = findNamePoint(paramString, 0);
    return (i < 0) ? null : this.nodes.elementAt(i);
  }
  
  public Node getNamedItemNS(String paramString1, String paramString2) {
    int i = findNamePoint(paramString1, paramString2);
    return (i < 0) ? null : this.nodes.elementAt(i);
  }
  
  public Node setNamedItem(Node paramNode) throws DOMException {
    CoreDocumentImpl coreDocumentImpl = this.ownerNode.ownerDocument();
    if (coreDocumentImpl.errorChecking) {
      if (isReadOnly()) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
        throw new DOMException((short)7, str);
      } 
      if (paramNode.getOwnerDocument() != coreDocumentImpl) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null);
        throw new DOMException((short)4, str);
      } 
    } 
    int i = findNamePoint(paramNode.getNodeName(), 0);
    NodeImpl nodeImpl = null;
    if (i >= 0) {
      nodeImpl = this.nodes.elementAt(i);
      this.nodes.setElementAt(paramNode, i);
    } else {
      i = -1 - i;
      if (null == this.nodes)
        this.nodes = new Vector(5, 10); 
      this.nodes.insertElementAt(paramNode, i);
    } 
    return nodeImpl;
  }
  
  public Node setNamedItemNS(Node paramNode) throws DOMException {
    CoreDocumentImpl coreDocumentImpl = this.ownerNode.ownerDocument();
    if (coreDocumentImpl.errorChecking) {
      if (isReadOnly()) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
        throw new DOMException((short)7, str);
      } 
      if (paramNode.getOwnerDocument() != coreDocumentImpl) {
        String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "WRONG_DOCUMENT_ERR", null);
        throw new DOMException((short)4, str);
      } 
    } 
    int i = findNamePoint(paramNode.getNamespaceURI(), paramNode.getLocalName());
    NodeImpl nodeImpl = null;
    if (i >= 0) {
      nodeImpl = this.nodes.elementAt(i);
      this.nodes.setElementAt(paramNode, i);
    } else {
      i = findNamePoint(paramNode.getNodeName(), 0);
      if (i >= 0) {
        nodeImpl = this.nodes.elementAt(i);
        this.nodes.insertElementAt(paramNode, i);
      } else {
        i = -1 - i;
        if (null == this.nodes)
          this.nodes = new Vector(5, 10); 
        this.nodes.insertElementAt(paramNode, i);
      } 
    } 
    return nodeImpl;
  }
  
  public Node removeNamedItem(String paramString) throws DOMException {
    if (isReadOnly()) {
      String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
      throw new DOMException((short)7, str);
    } 
    int i = findNamePoint(paramString, 0);
    if (i < 0) {
      String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null);
      throw new DOMException((short)8, str);
    } 
    NodeImpl nodeImpl = this.nodes.elementAt(i);
    this.nodes.removeElementAt(i);
    return nodeImpl;
  }
  
  public Node removeNamedItemNS(String paramString1, String paramString2) throws DOMException {
    if (isReadOnly()) {
      String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NO_MODIFICATION_ALLOWED_ERR", null);
      throw new DOMException((short)7, str);
    } 
    int i = findNamePoint(paramString1, paramString2);
    if (i < 0) {
      String str = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_FOUND_ERR", null);
      throw new DOMException((short)8, str);
    } 
    NodeImpl nodeImpl = this.nodes.elementAt(i);
    this.nodes.removeElementAt(i);
    return nodeImpl;
  }
  
  public NamedNodeMapImpl cloneMap(NodeImpl paramNodeImpl) {
    NamedNodeMapImpl namedNodeMapImpl = new NamedNodeMapImpl(paramNodeImpl);
    namedNodeMapImpl.cloneContent(this);
    return namedNodeMapImpl;
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
          NodeImpl nodeImpl1 = paramNamedNodeMapImpl.nodes.elementAt(b);
          NodeImpl nodeImpl2 = (NodeImpl)nodeImpl1.cloneNode(true);
          nodeImpl2.isSpecified(nodeImpl1.isSpecified());
          this.nodes.setElementAt(nodeImpl2, b);
        } 
      } 
    } 
  }
  
  void setReadOnly(boolean paramBoolean1, boolean paramBoolean2) {
    isReadOnly(paramBoolean1);
    if (paramBoolean2 && this.nodes != null)
      for (int i = this.nodes.size() - 1; i >= 0; i--)
        ((NodeImpl)this.nodes.elementAt(i)).setReadOnly(paramBoolean1, paramBoolean2);  
  }
  
  boolean getReadOnly() {
    return isReadOnly();
  }
  
  protected void setOwnerDocument(CoreDocumentImpl paramCoreDocumentImpl) {
    if (this.nodes != null)
      for (byte b = 0; b < this.nodes.size(); b++)
        ((NodeImpl)item(b)).setOwnerDocument(paramCoreDocumentImpl);  
  }
  
  final boolean isReadOnly() {
    return ((this.flags & 0x1) != 0);
  }
  
  final void isReadOnly(boolean paramBoolean) {
    this.flags = paramBoolean ? (short)(this.flags | 0x1) : (short)(this.flags & 0xFFFFFFFE);
  }
  
  final boolean changed() {
    return ((this.flags & 0x2) != 0);
  }
  
  final void changed(boolean paramBoolean) {
    this.flags = paramBoolean ? (short)(this.flags | 0x2) : (short)(this.flags & 0xFFFFFFFD);
  }
  
  final boolean hasDefaults() {
    return ((this.flags & 0x4) != 0);
  }
  
  final void hasDefaults(boolean paramBoolean) {
    this.flags = paramBoolean ? (short)(this.flags | 0x4) : (short)(this.flags & 0xFFFFFFFB);
  }
  
  protected int findNamePoint(String paramString, int paramInt) {
    int i = 0;
    if (this.nodes != null) {
      int j = paramInt;
      int k = this.nodes.size() - 1;
      while (j <= k) {
        i = (j + k) / 2;
        int m = paramString.compareTo(((Node)this.nodes.elementAt(i)).getNodeName());
        if (m == 0)
          return i; 
        if (m < 0) {
          k = i - 1;
          continue;
        } 
        j = i + 1;
      } 
      if (j > i)
        i = j; 
    } 
    return -1 - i;
  }
  
  protected int findNamePoint(String paramString1, String paramString2) {
    if (this.nodes == null)
      return -1; 
    if (paramString2 == null)
      return -1; 
    for (byte b = 0; b < this.nodes.size(); b++) {
      NodeImpl nodeImpl = this.nodes.elementAt(b);
      String str1 = nodeImpl.getNamespaceURI();
      String str2 = nodeImpl.getLocalName();
      if (paramString1 == null) {
        if (str1 == null && (paramString2.equals(str2) || (str2 == null && paramString2.equals(nodeImpl.getNodeName()))))
          return b; 
      } else if (paramString1.equals(str1) && paramString2.equals(str2)) {
        return b;
      } 
    } 
    return -1;
  }
  
  protected boolean precedes(Node paramNode1, Node paramNode2) {
    if (this.nodes != null)
      for (byte b = 0; b < this.nodes.size(); b++) {
        Node node = this.nodes.elementAt(b);
        if (node == paramNode1)
          return true; 
        if (node == paramNode2)
          return false; 
      }  
    return false;
  }
  
  protected void removeItem(int paramInt) {
    if (this.nodes != null && paramInt < this.nodes.size())
      this.nodes.removeElementAt(paramInt); 
  }
  
  protected Object getItem(int paramInt) {
    return (this.nodes != null) ? this.nodes.elementAt(paramInt) : null;
  }
  
  protected int addItem(Node paramNode) {
    int i = findNamePoint(paramNode.getNamespaceURI(), paramNode.getLocalName());
    if (i >= 0) {
      this.nodes.setElementAt(paramNode, i);
    } else {
      i = findNamePoint(paramNode.getNodeName(), 0);
      if (i >= 0) {
        this.nodes.insertElementAt(paramNode, i);
      } else {
        i = -1 - i;
        if (null == this.nodes)
          this.nodes = new Vector(5, 10); 
        this.nodes.insertElementAt(paramNode, i);
      } 
    } 
    return i;
  }
  
  protected Vector cloneMap(Vector paramVector) {
    if (paramVector == null)
      paramVector = new Vector(5, 10); 
    paramVector.setSize(0);
    if (this.nodes != null)
      for (byte b = 0; b < this.nodes.size(); b++)
        paramVector.insertElementAt(this.nodes.elementAt(b), b);  
    return paramVector;
  }
  
  protected int getNamedItemIndex(String paramString1, String paramString2) {
    return findNamePoint(paramString1, paramString2);
  }
  
  public void removeAll() {
    if (this.nodes != null)
      this.nodes.removeAllElements(); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\NamedNodeMapImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */