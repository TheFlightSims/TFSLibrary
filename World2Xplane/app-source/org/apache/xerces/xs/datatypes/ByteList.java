package org.apache.xerces.xs.datatypes;

import org.apache.xerces.xs.XSException;

public interface ByteList {
  int getLength();
  
  boolean contains(byte paramByte);
  
  byte item(int paramInt) throws XSException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\datatypes\ByteList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */