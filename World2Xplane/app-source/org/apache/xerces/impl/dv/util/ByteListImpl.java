package org.apache.xerces.impl.dv.util;

import org.apache.xerces.xs.XSException;
import org.apache.xerces.xs.datatypes.ByteList;

public class ByteListImpl implements ByteList {
  protected final byte[] data;
  
  protected String canonical;
  
  public ByteListImpl(byte[] paramArrayOfbyte) {
    this.data = paramArrayOfbyte;
  }
  
  public int getLength() {
    return this.data.length;
  }
  
  public boolean contains(byte paramByte) {
    for (byte b = 0; b < this.data.length; b++) {
      if (this.data[b] == paramByte)
        return true; 
    } 
    return false;
  }
  
  public byte item(int paramInt) throws XSException {
    if (paramInt < 0 || paramInt > this.data.length - 1)
      throw new XSException((short)2, null); 
    return this.data[paramInt];
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\d\\util\ByteListImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */