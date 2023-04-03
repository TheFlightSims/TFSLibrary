package org.apache.xerces.impl.xs.util;

import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSException;

public class ShortListImpl implements ShortList {
  public static final ShortList EMPTY_LIST = new ShortList() {
      public int getLength() {
        return 0;
      }
      
      public boolean contains(short param1Short) {
        return false;
      }
      
      public short item(int param1Int) throws XSException {
        throw new XSException((short)2, null);
      }
    };
  
  private short[] fArray = null;
  
  private int fLength = 0;
  
  public ShortListImpl(short[] paramArrayOfshort, int paramInt) {
    this.fArray = paramArrayOfshort;
    this.fLength = paramInt;
  }
  
  public int getLength() {
    return this.fLength;
  }
  
  public boolean contains(short paramShort) {
    for (byte b = 0; b < this.fLength; b++) {
      if (this.fArray[b] == paramShort)
        return true; 
    } 
    return false;
  }
  
  public short item(int paramInt) throws XSException {
    if (paramInt < 0 || paramInt >= this.fLength)
      throw new XSException((short)2, null); 
    return this.fArray[paramInt];
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == null || !(paramObject instanceof ShortList))
      return false; 
    ShortList shortList = (ShortList)paramObject;
    if (this.fLength != shortList.getLength())
      return false; 
    for (byte b = 0; b < this.fLength; b++) {
      if (this.fArray[b] != shortList.item(b))
        return false; 
    } 
    return true;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\x\\util\ShortListImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */