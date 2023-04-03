package scala.collection.generic;

import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001u2q!\001\002\021\002\007\005\021BA\nEK2,w-\031;fINKwM\\1mY&twM\003\002\004\t\0059q-\0328fe&\034'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\0011c\001\001\013\035A\0211\002D\007\002\r%\021QB\002\002\007\003:L(+\0324\021\005=\001R\"\001\002\n\005E\021!AC*jO:\fG\016\\5oO\")1\003\001C\001)\0051A%\0338ji\022\"\022!\006\t\003\027YI!a\006\004\003\tUs\027\016\036\005\b3\001\001\rQ\"\001\033\0039\031\030n\0328bY\022+G.Z4bi\026,\022A\004\005\b9\001\001\rQ\"\001\036\003I\031\030n\0328bY\022+G.Z4bi\026|F%Z9\025\005Uq\002bB\020\034\003\003\005\rAD\001\004q\022\n\004\"B\021\001\t\003\021\023!C5t\003\n|'\017^3e+\005\031\003CA\006%\023\t)cAA\004C_>dW-\0318\t\013\035\002A\021\001\013\002\013\005\024wN\035;\t\013%\002A\021\001\026\002\023%tG-\032=GY\006<W#A\026\021\005-a\023BA\027\007\005\rIe\016\036\005\006_\001!\t\001M\001\rg\026$\030J\0343fq\032c\027m\032\013\003+EBQA\r\030A\002-\n\021A\032\005\006i\001!\t!N\001\026g\026$\030J\0343fq\032c\027mZ%g\017J,\027\r^3s)\t)b\007C\0033g\001\0071\006C\0039\001\021\005\021(\001\013tKRLe\016Z3y\r2\fw-\0234MKN\034XM\035\013\003+iBQAM\034A\002-BQ\001\020\001\005\002)\n1\001^1h\001")
public interface DelegatedSignalling extends Signalling {
  Signalling signalDelegate();
  
  void signalDelegate_$eq(Signalling paramSignalling);
  
  boolean isAborted();
  
  void abort();
  
  int indexFlag();
  
  void setIndexFlag(int paramInt);
  
  void setIndexFlagIfGreater(int paramInt);
  
  void setIndexFlagIfLesser(int paramInt);
  
  int tag();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\DelegatedSignalling.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */