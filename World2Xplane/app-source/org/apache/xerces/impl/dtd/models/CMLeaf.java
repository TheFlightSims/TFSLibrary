package org.apache.xerces.impl.dtd.models;

import org.apache.xerces.xni.QName;

public class CMLeaf extends CMNode {
  private final QName fElement = new QName();
  
  private int fPosition = -1;
  
  public CMLeaf(QName paramQName, int paramInt) {
    super(0);
    this.fElement.setValues(paramQName);
    this.fPosition = paramInt;
  }
  
  public CMLeaf(QName paramQName) {
    super(0);
    this.fElement.setValues(paramQName);
  }
  
  final QName getElement() {
    return this.fElement;
  }
  
  final int getPosition() {
    return this.fPosition;
  }
  
  final void setPosition(int paramInt) {
    this.fPosition = paramInt;
  }
  
  public boolean isNullable() {
    return (this.fPosition == -1);
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer(this.fElement.toString());
    stringBuffer.append(" (");
    stringBuffer.append(this.fElement.uri);
    stringBuffer.append(',');
    stringBuffer.append(this.fElement.localpart);
    stringBuffer.append(')');
    if (this.fPosition >= 0)
      stringBuffer.append(" (Pos:").append(Integer.toString(this.fPosition)).append(')'); 
    return stringBuffer.toString();
  }
  
  protected void calcFirstPos(CMStateSet paramCMStateSet) {
    if (this.fPosition == -1) {
      paramCMStateSet.zeroBits();
    } else {
      paramCMStateSet.setBit(this.fPosition);
    } 
  }
  
  protected void calcLastPos(CMStateSet paramCMStateSet) {
    if (this.fPosition == -1) {
      paramCMStateSet.zeroBits();
    } else {
      paramCMStateSet.setBit(this.fPosition);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dtd\models\CMLeaf.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */