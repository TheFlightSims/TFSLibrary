package org.apache.xerces.dom;

public final class DeferredAttrImpl extends AttrImpl implements DeferredNode {
  static final long serialVersionUID = 6903232312469148636L;
  
  protected transient int fNodeIndex;
  
  DeferredAttrImpl(DeferredDocumentImpl paramDeferredDocumentImpl, int paramInt) {
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
    DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)ownerDocument();
    this.name = deferredDocumentImpl.getNodeName(this.fNodeIndex);
    int i = deferredDocumentImpl.getNodeExtra(this.fNodeIndex);
    isSpecified(((i & 0x20) != 0));
    isIdAttribute(((i & 0x200) != 0));
    int j = deferredDocumentImpl.getLastChild(this.fNodeIndex);
    this.type = deferredDocumentImpl.getTypeInfo(j);
  }
  
  protected void synchronizeChildren() {
    DeferredDocumentImpl deferredDocumentImpl = (DeferredDocumentImpl)ownerDocument();
    deferredDocumentImpl.synchronizeChildren(this, this.fNodeIndex);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DeferredAttrImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */