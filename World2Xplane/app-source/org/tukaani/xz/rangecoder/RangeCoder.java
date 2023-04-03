package org.tukaani.xz.rangecoder;

import java.util.Arrays;

public abstract class RangeCoder {
  static final int SHIFT_BITS = 8;
  
  static final int TOP_MASK = -16777216;
  
  static final int BIT_MODEL_TOTAL_BITS = 11;
  
  static final int BIT_MODEL_TOTAL = 2048;
  
  static final short PROB_INIT = 1024;
  
  static final int MOVE_BITS = 5;
  
  public static final void initProbs(short[] paramArrayOfshort) {
    Arrays.fill(paramArrayOfshort, (short)1024);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\tukaani\xz\rangecoder\RangeCoder.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */