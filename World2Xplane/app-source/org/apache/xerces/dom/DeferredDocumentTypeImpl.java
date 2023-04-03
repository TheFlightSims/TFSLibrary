package org.apache.xerces.dom;

import org.w3c.dom.Node;

public class DeferredDocumentTypeImpl extends DocumentTypeImpl implements DeferredNode {
  static final long serialVersionUID = -2172579663227313509L;
  
  protected transient int fNodeIndex;
  
  DeferredDocumentTypeImpl(DeferredDocumentImpl paramDeferredDocumentImpl, int paramInt) {
    super(paramDeferredDocumentImpl, null);
    this.fNodeIndex = paramInt;
    needsSyncData(true);
    needsSyncChildren(true);
  }
  
  public int getNodeIndex() {
    return this.fNodeIndex;
  }
  
  protected void synchronizeData() {
    needsSyncData(false);
    DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)this.ownerDocument;
    this.name = deferredDocumentImpl.getNodeName(this.fNodeIndex);
    this.publicID = deferredDocumentImpl.getNodeValue(this.fNodeIndex);
    this.systemID = deferredDocumentImpl.getNodeURI(this.fNodeIndex);
    int i = deferredDocumentImpl.getNodeExtra(this.fNodeIndex);
    this.internalSubset = deferredDocumentImpl.getNodeValue(i);
  }
  
  protected void synchronizeChildren() {
    boolean bool = ownerDocument().getMutationEvents();
    ownerDocument().setMutationEvents(false);
    needsSyncChildren(false);
    DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)this.ownerDocument;
    this.entities = new NamedNodeMapImpl(this);
    this.notations = new NamedNodeMapImpl(this);
    this.elements = new NamedNodeMapImpl(this);
    Node node = null;
    for (int i = deferredDocumentImpl.getLastChild(this.fNodeIndex); i != -1; i = deferredDocumentImpl.getPrevSibling(i)) {
      DeferredNode deferredNode = deferredDocumentImpl.getNodeObject(i);
      short s = deferredNode.getNodeType();
      switch (s) {
        case 6:
          this.entities.setNamedItem(deferredNode);
          break;
        case 12:
          this.notations.setNamedItem(deferredNode);
          break;
        case 21:
          this.elements.setNamedItem(deferredNode);
          break;
        case 1:
          if (((DocumentImpl)getOwnerDocument()).allowGrammarAccess) {
            insertBefore(deferredNode, node);
            node = deferredNode;
            break;
          } 
        default:
          System.out.println("DeferredDocumentTypeImpl#synchronizeInfo: node.getNodeType() = " + deferredNode.getNodeType() + ", class = " + deferredNode.getClass().getName());
          break;
      } 
    } 
    ownerDocument().setMutationEvents(bool);
    setReadOnly(true, false);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DeferredDocumentTypeImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */