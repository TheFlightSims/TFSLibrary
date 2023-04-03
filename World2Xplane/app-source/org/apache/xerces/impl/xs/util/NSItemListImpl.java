package org.apache.xerces.impl.xs.util;

import java.util.Vector;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSNamespaceItemList;

public class NSItemListImpl implements XSNamespaceItemList {
  private XSNamespaceItem[] fArray = null;
  
  private int fLength = 0;
  
  private Vector fVector;
  
  public NSItemListImpl(Vector paramVector) {
    this.fVector = paramVector;
    this.fLength = paramVector.size();
  }
  
  public NSItemListImpl(XSNamespaceItem[] paramArrayOfXSNamespaceItem, int paramInt) {
    this.fArray = paramArrayOfXSNamespaceItem;
    this.fLength = paramInt;
  }
  
  public int getLength() {
    return this.fLength;
  }
  
  public XSNamespaceItem item(int paramInt) {
    return (paramInt < 0 || paramInt >= this.fLength) ? null : ((this.fVector != null) ? this.fVector.elementAt(paramInt) : this.fArray[paramInt]);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\x\\util\NSItemListImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */