package org.apache.xerces.impl.xs.models;

public final class XSCMRepeatingLeaf extends XSCMLeaf {
  private final int fMinOccurs;
  
  private final int fMaxOccurs;
  
  public XSCMRepeatingLeaf(int paramInt1, Object paramObject, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    super(paramInt1, paramObject, paramInt4, paramInt5);
    this.fMinOccurs = paramInt2;
    this.fMaxOccurs = paramInt3;
  }
  
  final int getMinOccurs() {
    return this.fMinOccurs;
  }
  
  final int getMaxOccurs() {
    return this.fMaxOccurs;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\models\XSCMRepeatingLeaf.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */