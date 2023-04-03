package org.apache.xerces.dom;

import org.w3c.dom.Node;

public abstract class ChildNode extends NodeImpl {
  static final long serialVersionUID = -6112455738802414002L;
  
  protected ChildNode previousSibling;
  
  protected ChildNode nextSibling;
  
  protected ChildNode(CoreDocumentImpl paramCoreDocumentImpl) {
    super(paramCoreDocumentImpl);
  }
  
  public ChildNode() {}
  
  public Node cloneNode(boolean paramBoolean) {
    ChildNode childNode = (ChildNode)super.cloneNode(paramBoolean);
    childNode.previousSibling = null;
    childNode.nextSibling = null;
    childNode.isFirstChild(false);
    return childNode;
  }
  
  public Node getParentNode() {
    return isOwned() ? this.ownerNode : null;
  }
  
  final NodeImpl parentNode() {
    return isOwned() ? this.ownerNode : null;
  }
  
  public Node getNextSibling() {
    return this.nextSibling;
  }
  
  public Node getPreviousSibling() {
    return isFirstChild() ? null : this.previousSibling;
  }
  
  final ChildNode previousSibling() {
    return isFirstChild() ? null : this.previousSibling;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\ChildNode.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */