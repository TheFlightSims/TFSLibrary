package com.ctc.wstx.dtd;

import java.util.BitSet;
import java.util.List;

public abstract class ModelNode {
  public abstract ModelNode cloneModel();
  
  public abstract boolean isNullable();
  
  public abstract void indexTokens(List paramList);
  
  public abstract void addFirstPos(BitSet paramBitSet);
  
  public abstract void addLastPos(BitSet paramBitSet);
  
  public abstract void calcFollowPos(BitSet[] paramArrayOfBitSet);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\ModelNode.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */