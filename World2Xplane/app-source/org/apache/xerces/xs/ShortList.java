package org.apache.xerces.xs;

public interface ShortList {
  int getLength();
  
  boolean contains(short paramShort);
  
  short item(int paramInt) throws XSException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\xs\ShortList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */