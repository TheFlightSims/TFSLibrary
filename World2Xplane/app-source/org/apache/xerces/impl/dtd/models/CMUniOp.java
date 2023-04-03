package org.apache.xerces.impl.dtd.models;

public class CMUniOp extends CMNode {
  private final CMNode fChild;
  
  public CMUniOp(int paramInt, CMNode paramCMNode) {
    super(paramInt);
    if (type() != 1 && type() != 2 && type() != 3)
      throw new RuntimeException("ImplementationMessages.VAL_UST"); 
    this.fChild = paramCMNode;
  }
  
  final CMNode getChild() {
    return this.fChild;
  }
  
  public boolean isNullable() {
    return (type() == 3) ? this.fChild.isNullable() : true;
  }
  
  protected void calcFirstPos(CMStateSet paramCMStateSet) {
    paramCMStateSet.setTo(this.fChild.firstPos());
  }
  
  protected void calcLastPos(CMStateSet paramCMStateSet) {
    paramCMStateSet.setTo(this.fChild.lastPos());
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\dtd\models\CMUniOp.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */