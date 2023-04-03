package javax.vecmath;

class VecMathUtil {
  static int floatToIntBits(float paramFloat) {
    return (paramFloat == 0.0F) ? 0 : Float.floatToIntBits(paramFloat);
  }
  
  static long doubleToLongBits(double paramDouble) {
    return (paramDouble == 0.0D) ? 0L : Double.doubleToLongBits(paramDouble);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\vecmath\VecMathUtil.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */