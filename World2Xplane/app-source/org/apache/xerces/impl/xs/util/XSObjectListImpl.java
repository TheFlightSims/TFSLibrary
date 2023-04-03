package org.apache.xerces.impl.xs.util;

import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSObjectList;

public class XSObjectListImpl implements XSObjectList {
  public static final XSObjectList EMPTY_LIST = new XSObjectList() {
      public int getLength() {
        return 0;
      }
      
      public XSObject item(int param1Int) {
        return null;
      }
    };
  
  private static final int DEFAULT_SIZE = 4;
  
  private XSObject[] fArray = null;
  
  private int fLength = 0;
  
  public XSObjectListImpl() {
    this.fArray = new XSObject[4];
    this.fLength = 0;
  }
  
  public XSObjectListImpl(XSObject[] paramArrayOfXSObject, int paramInt) {
    this.fArray = paramArrayOfXSObject;
    this.fLength = paramInt;
  }
  
  public int getLength() {
    return this.fLength;
  }
  
  public XSObject item(int paramInt) {
    return (paramInt < 0 || paramInt >= this.fLength) ? null : this.fArray[paramInt];
  }
  
  public void clear() {
    for (byte b = 0; b < this.fLength; b++)
      this.fArray[b] = null; 
    this.fArray = null;
    this.fLength = 0;
  }
  
  public void add(XSObject paramXSObject) {
    if (this.fLength == this.fArray.length) {
      XSObject[] arrayOfXSObject = new XSObject[this.fLength + 4];
      System.arraycopy(this.fArray, 0, arrayOfXSObject, 0, this.fLength);
      this.fArray = arrayOfXSObject;
    } 
    this.fArray[this.fLength++] = paramXSObject;
  }
  
  public void add(int paramInt, XSObject paramXSObject) {
    this.fArray[paramInt] = paramXSObject;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\x\\util\XSObjectListImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */