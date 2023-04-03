package javax.vecmath;

import java.io.Serializable;

public class Point4i extends Tuple4i implements Serializable {
  static final long serialVersionUID = 620124780244617983L;
  
  public Point4i(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public Point4i(int[] paramArrayOfint) {
    super(paramArrayOfint);
  }
  
  public Point4i(Tuple4i paramTuple4i) {
    super(paramTuple4i);
  }
  
  public Point4i() {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\vecmath\Point4i.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */