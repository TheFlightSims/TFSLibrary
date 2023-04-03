package scala.collection.generic;

import scala.collection.mutable.Builder;
import scala.collection.parallel.Combiner;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\00114q!\001\002\021\002\007\005\021B\001\nHK:,'/[2QCJ$V-\0349mCR,'BA\002\005\003\0359WM\\3sS\016T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)2AC\013 '\021\0011b\004\027\021\0051iQ\"\001\004\n\00591!AB!osJ+g\r\005\003\021#MqR\"\001\002\n\005I\021!AG$f]\026\024\030n\031+sCZ,'o]1cY\026$V-\0349mCR,\007C\001\013\026\031\001!aA\006\001\005\006\0049\"!A!\022\005aY\002C\001\007\032\023\tQbAA\004O_RD\027N\\4\021\0051a\022BA\017\007\005\r\te.\037\t\003)}!a\001\t\001\005\006\004\t#AA\"D+\t\021#&\005\002\031GA\031AeJ\025\016\003\025R!A\n\003\002\021A\f'/\0317mK2L!\001K\023\003\027A\013'/\023;fe\006\024G.\032\t\003))\"QaK\020C\002]\021\021\001\027\t\005!5\032r&\003\002/\005\tq\001*Y:OK^\034u.\0342j]\026\024(F\001\0312!\r!rdE\026\002eA\0211\007O\007\002i)\021QGN\001\nk:\034\007.Z2lK\022T!a\016\004\002\025\005tgn\034;bi&|g.\003\002:i\t\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\013m\002A\021\001\037\002\r\021Jg.\033;%)\005i\004C\001\007?\023\tydA\001\003V]&$\b\"B!\001\r\003\021\025!C2p[B\fg.[8o+\005\031%c\001#G\023\032!Q\t\001\001D\0051a$/\0324j]\026lWM\034;?!\r\001rIH\005\003\021\n\021\001cR3oKJL7mQ8na\006t\027n\0348\021\007AQe$\003\002L\005\t\031r)\0328fe&\034\007+\031:D_6\004\030M\\5p]\"1Q\n\001Q\005R9\013!B\\3x\005VLG\016Z3s+\005y\005\003\002)T'Aj\021!\025\006\003%\022\tq!\\;uC\ndW-\003\002U#\n9!)^5mI\026\024\bB\002,\001A\023Es+A\006oK^\034u.\0342j]\026\024X#\001-\021\t\021J6\003M\005\0035\026\022\001bQ8nE&tWM\035\005\0069\002!\t%X\001\017O\026tWM]5d\005VLG\016Z3s+\tq\026-F\001`!\021!\023\fY2\021\005Q\tG!\0022\\\005\0049\"!\001\"\021\007Qy\002\rC\003f\001\021\005a-A\bhK:,'/[2D_6\024\027N\\3s+\t9'.F\001i!\021!\023,[6\021\005QQG!\0022e\005\0049\002c\001\013 S\002")
public interface GenericParTemplate<A, CC extends scala.collection.parallel.ParIterable<Object>> extends GenericTraversableTemplate<A, CC>, HasNewCombiner<A, CC> {
  GenericCompanion<CC> companion();
  
  Builder<A, CC> newBuilder();
  
  Combiner<A, CC> newCombiner();
  
  <B> Combiner<B, CC> genericBuilder();
  
  <B> Combiner<B, CC> genericCombiner();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenericParTemplate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */