package org.apache.xerces.dom;

import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Text;

public class DocumentFragmentImpl extends ParentNode implements DocumentFragment {
  static final long serialVersionUID = -7596449967279236746L;
  
  public DocumentFragmentImpl(CoreDocumentImpl paramCoreDocumentImpl) {
    super(paramCoreDocumentImpl);
  }
  
  public DocumentFragmentImpl() {}
  
  public short getNodeType() {
    return 11;
  }
  
  public String getNodeName() {
    return "#document-fragment";
  }
  
  public void normalize() {
    if (isNormalized())
      return; 
    if (needsSyncChildren())
      synchronizeChildren(); 
    for (ChildNode childNode = this.firstChild; childNode != null; childNode = childNode1) {
      ChildNode childNode1 = childNode.nextSibling;
      if (childNode.getNodeType() == 3)
        if (childNode1 != null && childNode1.getNodeType() == 3) {
          ((Text)childNode).appendData(childNode1.getNodeValue());
          removeChild(childNode1);
          childNode1 = childNode;
        } else if (childNode.getNodeValue() == null || childNode.getNodeValue().length() == 0) {
          removeChild(childNode);
        }  
      childNode.normalize();
    } 
    isNormalized(true);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\dom\DocumentFragmentImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */