package scala.collection.parallel;

import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001a2\001\"\001\002\021\002\007\005A\001\003\002\020%\026l\027-\0338t\023R,'/\031;pe*\0211\001B\001\ta\006\024\030\r\0347fY*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\026\005%i2c\001\001\013\035A\0211\002D\007\002\r%\021QB\002\002\007\003:L(+\0324\021\007=A2D\004\002\021-9\021\021#F\007\002%)\0211\003F\001\007yI|w\016\036 \004\001%\tq!\003\002\030\r\0059\001/Y2lC\036,\027BA\r\033\005!IE/\032:bi>\024(BA\f\007!\taR\004\004\001\005\ry\001AQ1\001 \005\005!\026C\001\021$!\tY\021%\003\002#\r\t9aj\034;iS:<\007CA\006%\023\t)cAA\002B]fDQa\n\001\005\002!\na\001J5oSR$C#A\025\021\005-Q\023BA\026\007\005\021)f.\033;\t\0135\002a\021\001\030\002\023I,W.Y5oS:<W#A\030\021\005-\001\024BA\031\007\005\rIe\016\036\005\006g\001!\t\001N\001\021SN\024V-\\1j]&twm\0215fCB,\022!\016\t\003\027YJ!a\016\004\003\017\t{w\016\\3b]\002")
public interface RemainsIterator<T> extends Iterator<T> {
  int remaining();
  
  boolean isRemainingCheap();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\RemainsIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */