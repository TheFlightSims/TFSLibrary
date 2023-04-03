package scala.collection.mutable;

import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0313q!\001\002\021\002\007\005\021B\001\005Nk2$\030.T1q\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\007))\"eE\002\001\027=\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\021\001\022c\005\020\016\003\tI!A\005\002\003\0075\013\007\017\005\002\025+1\001A!\002\f\001\005\0049\"!A!\022\005aY\002C\001\007\032\023\tQbAA\004O_RD\027N\\4\021\0051a\022BA\017\007\005\r\te.\037\t\004!}\t\023B\001\021\003\005\r\031V\r\036\t\003)\t\"Qa\t\001C\002]\021\021A\021\005\006K\001!\tAJ\001\007I%t\027\016\036\023\025\003\035\002\"\001\004\025\n\005%2!\001B+oSRDQa\013\001\005\0221\nq!\\1lKN+G/F\001\037\021\025q\003\001\"\0010\003)\tG\r\032\"j]\022Lgn\032\013\004aE\032T\"\001\001\t\013Ij\003\031A\n\002\007-,\027\020C\0035[\001\007\021%A\003wC2,X\rC\0037\001\021\005q'A\007sK6|g/\032\"j]\022Lgn\032\013\004aaJ\004\"\002\0326\001\004\031\002\"\002\0336\001\004\t\003\"B\036\001\t\003a\024aC3oiJLX\t_5tiN$2!\020!B!\taa(\003\002@\r\t9!i\\8mK\006t\007\"\002\032;\001\004\031\002\"\002\";\001\004\031\025!\0019\021\t1!\025%P\005\003\013\032\021\021BR;oGRLwN\\\031")
public interface MultiMap<A, B> extends Map<A, Set<B>> {
  Set<B> makeSet();
  
  MultiMap<A, B> addBinding(A paramA, B paramB);
  
  MultiMap<A, B> removeBinding(A paramA, B paramB);
  
  boolean entryExists(A paramA, Function1<B, Object> paramFunction1);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\MultiMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */