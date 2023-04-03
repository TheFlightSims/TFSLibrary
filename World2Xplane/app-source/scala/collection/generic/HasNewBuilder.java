package scala.collection.generic;

import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\r2q!\001\002\021\002G\005\021BA\007ICNtUm\036\"vS2$WM\035\006\003\007\021\tqaZ3oKJL7M\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!F\002\0135\005\032\"\001A\006\021\0051iQ\"\001\004\n\00591!aA!os\"1\001\003\001Q\007\022E\t!B\\3x\005VLG\016Z3s+\005\021\002\003B\n\0271\001j\021\001\006\006\003+\021\tq!\\;uC\ndW-\003\002\030)\t9!)^5mI\026\024\bCA\r\033\031\001!aa\007\001\005\006\004a\"!A!\022\005uY\001C\001\007\037\023\tybAA\004O_RD\027N\\4\021\005e\tCA\002\022\001\t\013\007AD\001\003SKB\024\b")
public interface HasNewBuilder<A, Repr> {
  Builder<A, Repr> newBuilder();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\HasNewBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */