package scala.collection.generic;

import scala.collection.GenIterable;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0053q!\001\002\021\002\007\005\021BA\tJi\026\024\030M\0317f\r>\024x/\031:eKJT!a\001\003\002\017\035,g.\032:jG*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"F\n\005\001-ya\004\005\002\r\0335\ta!\003\002\017\r\t1\021I\\=SK\032\0042\001E\t\024\033\005!\021B\001\n\005\005!IE/\032:bE2,\007C\001\013\026\031\001!aA\006\001\005\006\0049\"!A!\022\005aY\002C\001\007\032\023\tQbAA\004O_RD\027N\\4\021\0051a\022BA\017\007\005\r\te.\037\t\004?\001\032R\"\001\002\n\005\005\022!\001\006+sCZ,'o]1cY\0264uN]<be\022,'\017C\003$\001\021\005A%\001\004%S:LG\017\n\013\002KA\021ABJ\005\003O\031\021A!\0268ji\")\021\006\001D\tU\005QQO\0343fe2L\030N\\4\026\003=AQ\001\f\001\005B5\n\001\"\033;fe\006$xN]\013\002]A\031\001cL\n\n\005A\"!\001C%uKJ\fGo\034:\t\013I\002A\021I\032\002\031M\fW.Z#mK6,g\016^:\026\005QrDCA\0339!\taa'\003\0028\r\t9!i\\8mK\006t\007\"B\0352\001\004Q\024\001\002;iCR\0042\001E\036>\023\taDAA\006HK:LE/\032:bE2,\007C\001\013?\t\025y\024G1\001A\005\005\021\025CA\n\034\001")
public interface IterableForwarder<A> extends Iterable<A>, TraversableForwarder<A> {
  Iterable<A> underlying();
  
  Iterator<A> iterator();
  
  <B> boolean sameElements(GenIterable<B> paramGenIterable);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\IterableForwarder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */