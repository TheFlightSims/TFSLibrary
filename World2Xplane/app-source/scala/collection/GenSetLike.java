package scala.collection;

import scala.Equals;
import scala.Function1;
import scala.collection.parallel.ParSet;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001y4q!\001\002\021\002\007\005qA\001\006HK:\034V\r\036'jW\026T!a\001\003\002\025\r|G\016\\3di&|gNC\001\006\003\025\0318-\0317b\007\001)2\001C\n\036'\031\001\021\"D\020&QA\021!bC\007\002\t%\021A\002\002\002\007\003:L(+\0324\021\t9y\021\003H\007\002\005%\021\001C\001\002\020\017\026t\027\n^3sC\ndW\rT5lKB\021!c\005\007\001\t\025!\002A1\001\026\005\005\t\025C\001\f\032!\tQq#\003\002\031\t\t9aj\034;iS:<\007C\001\006\033\023\tYBAA\002B]f\004\"AE\017\005\ry\001AQ1\001\026\005\021\021V\r\035:\021\t)\001\023CI\005\003C\021\021\021BR;oGRLwN\\\031\021\005)\031\023B\001\023\005\005\035\021un\0347fC:\004\"A\003\024\n\005\035\"!AB#rk\006d7\017\005\003\017SEY\023B\001\026\003\0059\001\026M]1mY\026d\027N_1cY\026\0042\001L\030\022\033\005i#B\001\030\003\003!\001\030M]1mY\026d\027B\001\031.\005\031\001\026M]*fi\")!\007\001C\001g\0051A%\0338ji\022\"\022\001\016\t\003\025UJ!A\016\003\003\tUs\027\016\036\005\006q\0011\t!O\001\tSR,'/\031;peV\t!\bE\002\017wEI!\001\020\002\003\021%#XM]1u_JDQA\020\001\007\002}\n\001bY8oi\006Lgn\035\013\003E\001CQ!Q\037A\002E\tA!\0327f[\")1\t\001D\001\t\006)A\005\0357vgR\021A$\022\005\006\003\n\003\r!\005\005\006\017\0021\t\001S\001\007I5Lg.^:\025\005qI\005\"B!G\001\004\t\002\"B&\001\r\003a\025aA:fcV\tQ\nE\002\017\035FI!a\024\002\003\007M+G\017C\003R\001\021\005!+A\003baBd\027\020\006\002#'\")\021\t\025a\001#!)Q\013\001C\001-\006I\021N\034;feN,7\r\036\013\0039]CQ\001\027+A\002e\013A\001\0365biB\031aBW\t\n\005m\023!AB$f]N+G\017C\003^\001\021\005a,\001\003%C6\004HC\001\017`\021\025AF\f1\001Z\021\025\t\007A\"\001c\003\025)h.[8o)\ta2\rC\003YA\002\007\021\fC\003f\001\021\005a-\001\003%E\006\024HC\001\017h\021\025AF\r1\001Z\021\025I\007A\"\001k\003\021!\027N\0324\025\005qY\007\"\002-i\001\004I\006\"B7\001\t\003q\027A\003\023b[B$C/\0337eKR\021Ad\034\005\00612\004\r!\027\005\006c\002!\tA]\001\tgV\0247/\032;PMR\021!e\035\005\0061B\004\r!\027\005\006k\002!\tE^\001\007KF,\030\r\\:\025\005\t:\b\"\002-u\001\004I\002\"B=\001\t\003R\030\001\0035bg\"\034u\016Z3\025\003m\004\"A\003?\n\005u$!aA%oi\002")
public interface GenSetLike<A, Repr> extends GenIterableLike<A, Repr>, Function1<A, Object>, Equals, Parallelizable<A, ParSet<A>> {
  Iterator<A> iterator();
  
  boolean contains(A paramA);
  
  Repr $plus(A paramA);
  
  Repr $minus(A paramA);
  
  Set<A> seq();
  
  boolean apply(A paramA);
  
  Repr intersect(GenSet<A> paramGenSet);
  
  Repr $amp(GenSet<A> paramGenSet);
  
  Repr union(GenSet<A> paramGenSet);
  
  Repr $bar(GenSet<A> paramGenSet);
  
  Repr diff(GenSet<A> paramGenSet);
  
  Repr $amp$tilde(GenSet<A> paramGenSet);
  
  boolean subsetOf(GenSet<A> paramGenSet);
  
  boolean equals(Object paramObject);
  
  int hashCode();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenSetLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */