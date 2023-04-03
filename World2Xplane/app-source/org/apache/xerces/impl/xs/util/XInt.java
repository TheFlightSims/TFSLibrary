package org.apache.xerces.impl.xs.util;

public final class XInt {
  private int fValue;
  
  XInt(int paramInt) {
    this.fValue = paramInt;
  }
  
  public final int intValue() {
    return this.fValue;
  }
  
  public final short shortValue() {
    return (short)this.fValue;
  }
  
  public final boolean equals(XInt paramXInt) {
    return (this.fValue == paramXInt.fValue);
  }
  
  public String toString() {
    return Integer.toString(this.fValue);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\x\\util\XInt.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */