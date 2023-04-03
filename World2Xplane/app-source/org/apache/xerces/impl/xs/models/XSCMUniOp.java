package org.apache.xerces.impl.xs.models;

import org.apache.xerces.impl.dtd.models.CMNode;
import org.apache.xerces.impl.dtd.models.CMStateSet;

public class XSCMUniOp extends CMNode {
  private CMNode fChild;
  
  public XSCMUniOp(int paramInt, CMNode paramCMNode) {
    super(paramInt);
    if (type() != 5 && type() != 4 && type() != 6)
      throw new RuntimeException("ImplementationMessages.VAL_UST"); 
    this.fChild = paramCMNode;
  }
  
  final CMNode getChild() {
    return this.fChild;
  }
  
  public boolean isNullable() {
    return (type() == 6) ? this.fChild.isNullable() : true;
  }
  
  protected void calcFirstPos(CMStateSet paramCMStateSet) {
    paramCMStateSet.setTo(this.fChild.firstPos());
  }
  
  protected void calcLastPos(CMStateSet paramCMStateSet) {
    paramCMStateSet.setTo(this.fChild.lastPos());
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\impl\xs\models\XSCMUniOp.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */