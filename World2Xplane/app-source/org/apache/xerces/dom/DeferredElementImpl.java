package org.apache.xerces.dom;

import org.w3c.dom.NamedNodeMap;

public class DeferredElementImpl extends ElementImpl implements DeferredNode {
  static final long serialVersionUID = -7670981133940934842L;
  
  protected transient int fNodeIndex;
  
  DeferredElementImpl(DeferredDocumentImpl paramDeferredDocumentImpl, int paramInt) {
    super(paramDeferredDocumentImpl, null);
    this.fNodeIndex = paramInt;
    needsSyncChildren(true);
  }
  
  public final int getNodeIndex() {
    return this.fNodeIndex;
  }
  
  protected final void synchronizeData() {
    needsSyncData(false);
    DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)this.ownerDocument;
    boolean bool = deferredDocumentImpl.mutationEvents;
    deferredDocumentImpl.mutationEvents = false;
    this.name = deferredDocumentImpl.getNodeName(this.fNodeIndex);
    setupDefaultAttributes();
    int i = deferredDocumentImpl.getNodeExtra(this.fNodeIndex);
    if (i != -1) {
      NamedNodeMap namedNodeMap = getAttributes();
      do {
        NodeImpl nodeImpl = (NodeImpl)deferredDocumentImpl.getNodeObject(i);
        namedNodeMap.setNamedItem(nodeImpl);
        i = deferredDocumentImpl.getPrevSibling(i);
      } while (i != -1);
    } 
    deferredDocumentImpl.mutationEvents = bool;
  }
  
  protected final void synchronizeChildren() {
    DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)ownerDocument();
    deferredDocumentImpl.synchronizeChildren(this, this.fNodeIndex);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DeferredElementImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */