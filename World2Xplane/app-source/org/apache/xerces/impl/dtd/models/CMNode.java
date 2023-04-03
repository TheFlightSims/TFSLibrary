package org.apache.xerces.impl.dtd.models;

public abstract class CMNode {
  private final int fType;
  
  private CMStateSet fFirstPos = null;
  
  private CMStateSet fFollowPos = null;
  
  private CMStateSet fLastPos = null;
  
  private int fMaxStates = -1;
  
  private boolean fCompactedForUPA = false;
  
  public CMNode(int paramInt) {
    this.fType = paramInt;
  }
  
  public abstract boolean isNullable();
  
  public final int type() {
    return this.fType;
  }
  
  public final CMStateSet firstPos() {
    if (this.fFirstPos == null) {
      this.fFirstPos = new CMStateSet(this.fMaxStates);
      calcFirstPos(this.fFirstPos);
    } 
    return this.fFirstPos;
  }
  
  public final CMStateSet lastPos() {
    if (this.fLastPos == null) {
      this.fLastPos = new CMStateSet(this.fMaxStates);
      calcLastPos(this.fLastPos);
    } 
    return this.fLastPos;
  }
  
  final void setFollowPos(CMStateSet paramCMStateSet) {
    this.fFollowPos = paramCMStateSet;
  }
  
  public final void setMaxStates(int paramInt) {
    this.fMaxStates = paramInt;
  }
  
  public boolean isCompactedForUPA() {
    return this.fCompactedForUPA;
  }
  
  public void setIsCompactUPAModel(boolean paramBoolean) {
    this.fCompactedForUPA = paramBoolean;
  }
  
  protected abstract void calcFirstPos(CMStateSet paramCMStateSet);
  
  protected abstract void calcLastPos(CMStateSet paramCMStateSet);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dtd\models\CMNode.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */