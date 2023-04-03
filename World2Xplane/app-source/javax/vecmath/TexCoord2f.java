package javax.vecmath;

import java.io.Serializable;

public class TexCoord2f extends Tuple2f implements Serializable {
  static final long serialVersionUID = 7998248474800032487L;
  
  public TexCoord2f(float paramFloat1, float paramFloat2) {
    super(paramFloat1, paramFloat2);
  }
  
  public TexCoord2f(float[] paramArrayOffloat) {
    super(paramArrayOffloat);
  }
  
  public TexCoord2f(TexCoord2f paramTexCoord2f) {
    super(paramTexCoord2f);
  }
  
  public TexCoord2f(Tuple2f paramTuple2f) {
    super(paramTuple2f);
  }
  
  public TexCoord2f() {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\vecmath\TexCoord2f.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */