package org.apache.xerces.dom;

public class DeferredNotationImpl extends NotationImpl implements DeferredNode {
  static final long serialVersionUID = 5705337172887990848L;
  
  protected transient int fNodeIndex;
  
  DeferredNotationImpl(DeferredDocumentImpl paramDeferredDocumentImpl, int paramInt) {
    super(paramDeferredDocumentImpl, null);
    this.fNodeIndex = paramInt;
    needsSyncData(true);
  }
  
  public int getNodeIndex() {
    return this.fNodeIndex;
  }
  
  protected void synchronizeData() {
    needsSyncData(false);
    DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)ownerDocument();
    this.name = deferredDocumentImpl.getNodeName(this.fNodeIndex);
    deferredDocumentImpl.getNodeType(this.fNodeIndex);
    this.publicId = deferredDocumentImpl.getNodeValue(this.fNodeIndex);
    this.systemId = deferredDocumentImpl.getNodeURI(this.fNodeIndex);
    int i = deferredDocumentImpl.getNodeExtra(this.fNodeIndex);
    deferredDocumentImpl.getNodeType(i);
    this.baseURI = deferredDocumentImpl.getNodeName(i);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DeferredNotationImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */