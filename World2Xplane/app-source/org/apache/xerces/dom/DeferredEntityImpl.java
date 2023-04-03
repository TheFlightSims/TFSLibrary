package org.apache.xerces.dom;

public class DeferredEntityImpl extends EntityImpl implements DeferredNode {
  static final long serialVersionUID = 4760180431078941638L;
  
  protected transient int fNodeIndex;
  
  DeferredEntityImpl(DeferredDocumentImpl paramDeferredDocumentImpl, int paramInt) {
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
    this.publicId = deferredDocumentImpl.getNodeValue(this.fNodeIndex);
    this.systemId = deferredDocumentImpl.getNodeURI(this.fNodeIndex);
    int i = deferredDocumentImpl.getNodeExtra(this.fNodeIndex);
    deferredDocumentImpl.getNodeType(i);
    this.notationName = deferredDocumentImpl.getNodeName(i);
    this.version = deferredDocumentImpl.getNodeValue(i);
    this.encoding = deferredDocumentImpl.getNodeURI(i);
    int j = deferredDocumentImpl.getNodeExtra(i);
    this.baseURI = deferredDocumentImpl.getNodeName(j);
    this.inputEncoding = deferredDocumentImpl.getNodeValue(j);
  }
  
  protected void synchronizeChildren() {
    needsSyncChildren(false);
    isReadOnly(false);
    DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)ownerDocument();
    deferredDocumentImpl.synchronizeChildren(this, this.fNodeIndex);
    setReadOnly(true, true);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DeferredEntityImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */