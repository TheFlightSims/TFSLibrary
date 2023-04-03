package javax.vecmath;

import java.awt.Color;
import java.io.Serializable;

public class Color4f extends Tuple4f implements Serializable {
  static final long serialVersionUID = 8577680141580006740L;
  
  public Color4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    super(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public Color4f(float[] paramArrayOffloat) {
    super(paramArrayOffloat);
  }
  
  public Color4f(Color4f paramColor4f) {
    super(paramColor4f);
  }
  
  public Color4f(Tuple4f paramTuple4f) {
    super(paramTuple4f);
  }
  
  public Color4f(Tuple4d paramTuple4d) {
    super(paramTuple4d);
  }
  
  public Color4f(Color paramColor) {
    super(paramColor.getRed() / 255.0F, paramColor.getGreen() / 255.0F, paramColor.getBlue() / 255.0F, paramColor.getAlpha() / 255.0F);
  }
  
  public Color4f() {}
  
  public final void set(Color paramColor) {
    this.x = paramColor.getRed() / 255.0F;
    this.y = paramColor.getGreen() / 255.0F;
    this.z = paramColor.getBlue() / 255.0F;
    this.w = paramColor.getAlpha() / 255.0F;
  }
  
  public final Color get() {
    int i = Math.round(this.x * 255.0F);
    int j = Math.round(this.y * 255.0F);
    int k = Math.round(this.z * 255.0F);
    int m = Math.round(this.w * 255.0F);
    return new Color(i, j, k, m);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\vecmath\Color4f.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */