package scala.collection;

import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Tuple2;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005MgaB\001\003!\003\r\ta\002\002\r\033\006\004\bK]8ys2K7.\032\006\003\007\021\t!bY8mY\026\034G/[8o\025\005)\021!B:dC2\f7\001A\013\005\021Mi\002e\005\003\001\0235I\003C\001\006\f\033\005!\021B\001\007\005\005\031\te.\037*fMB)abD\t\035?5\t!!\003\002\021\005\t9Q*\0319MS.,\007C\001\n\024\031\001!Q\001\006\001C\002U\021\021!Q\t\003-e\001\"AC\f\n\005a!!a\002(pi\"Lgn\032\t\003\025iI!a\007\003\003\007\005s\027\020\005\002\023;\0211a\004\001CC\002U\021\021A\021\t\003%\001\"a!\t\001\005\006\004\021#\001\002+iSN\f\"AF\022\023\007\021jaE\002\003&\001\001\031#\001\004\037sK\032Lg.Z7f]Rt\004\003\002\b(#qI!\001\013\002\003\0075\013\007\017\005\003\017U1z\022BA\026\003\005EIE/\032:bE2,\007K]8ys2K7.\032\t\005\0255\nB$\003\002/\t\t1A+\0369mKJBQ\001\r\001\005\002E\na\001J5oSR$C#\001\032\021\005)\031\024B\001\033\005\005\021)f.\033;\t\013Y\002A\021I\034\002\007\035,G\017\006\0029wA\031!\"\017\017\n\005i\"!AB(qi&|g\016C\003=k\001\007\021#A\002lKfDQA\020\001\005B}\n\001\"\033;fe\006$xN]\013\002\001B\031a\"\021\027\n\005\t\023!\001C%uKJ\fGo\034:\t\013\021\003A\021I#\002\013\021\002H.^:\026\005\031KECA$M!\021qq%\005%\021\005IIE!\002&D\005\004Y%A\001\"2#\ta\022\004C\003N\007\002\007a*\001\002lmB!!\"L\tI\021\025\001\006\001\"\021R\003\031!S.\0338vgR\021qD\025\005\006y=\003\r!\005\005\006)\002!\t%V\001\bSN,U\016\035;z+\0051\006C\001\006X\023\tAFAA\004C_>dW-\0318\t\013i\003A\021I.\002\023\035,Go\024:FYN,WC\001/_)\riv\f\031\t\003%y#QAS-C\002-CQ\001P-A\002EAa!Y-\005\002\004\021\027a\0023fM\006,H\016\036\t\004\025\rl\026B\0013\005\005!a$-\0378b[\026t\004\"\0024\001\t\003:\027!B1qa2LHC\001\017i\021\025aT\r1\001\022\021\025Q\007\001\"\021l\003!\031wN\034;bS:\034HC\001,m\021\025a\024\0161\001\022\021\025q\007\001\"\021p\003-I7\017R3gS:,G-\021;\025\005Y\003\b\"\002\037n\001\004\t\002\"\002:\001\t\003\032\030AB6fsN+G/F\001u!\rqQ/E\005\003m\n\0211aU3u\021\025A\b\001\"\021z\0031YW-_:Ji\026\024\030\r^8s+\005Q\bc\001\bB#!)A\020\001C!{\006!1.Z=t+\005q\bc\001\b\000#%\031\021\021\001\002\003\021%#XM]1cY\026Dq!!\002\001\t\003\n9!\001\004wC2,Xm]\013\003\003\023\0012AD@\035\021\035\ti\001\001C!\003\037\taB^1mk\026\034\030\n^3sCR|'/\006\002\002\022A\031a\"\021\017\t\r\005\004A\021IA\013)\ra\022q\003\005\007y\005M\001\031A\t\t\017\005m\001\001\"\021\002\036\005Qa-\0337uKJ\\U-_:\025\007\031\ny\002\003\005\002\"\005e\001\031AA\022\003\005\001\b#\002\006\002&E1\026bAA\024\t\tIa)\0368di&|g.\r\005\b\003W\001A\021IA\027\003%i\027\r\035,bYV,7/\006\003\0020\005UB\003BA\031\003s\001RAD\024\022\003g\0012AEA\033\t\035\t9$!\013C\002U\021\021a\021\005\t\003w\tI\0031\001\002>\005\ta\r\005\004\013\003Ka\0221\007\005\b\003\003\002A\021IA\"\003\035)\b\017Z1uK\022,B!!\022\002LQ1\021qIA'\003\037\002RAD\024\022\003\023\0022AEA&\t\031Q\025q\bb\001\027\"1A(a\020A\002EA\001\"!\025\002@\001\007\021\021J\001\006m\006dW/\032\005\007\t\002!\t%!\026\026\t\005]\023Q\f\013\t\0033\ny&!\032\002jA)abJ\t\002\\A\031!#!\030\005\r)\013\031F1\001L\021!\t\t'a\025A\002\005\r\024aA6wcA)!\"L\t\002\\!A\021qMA*\001\004\t\031'A\002lmJB\001\"a\033\002T\001\007\021QN\001\004WZ\034\b#\002\006\002p\005\r\024bAA9\t\tQAH]3qK\006$X\r\032 \t\017\005U\004\001\"\021\002x\005QA\005\0357vg\022\002H.^:\026\t\005e\024q\020\013\005\003w\n\t\tE\003\017OE\ti\bE\002\023\003\"aASA:\005\004Y\005\002CAB\003g\002\r!!\"\002\005a\034\b#\002\b\002\b\006-\025bAAE\005\t\021r)\0328Ue\0064XM]:bE2,wJ\\2f!\025QQ&EA?\021\035\ty\t\001C!\003#\013\021BZ5mi\026\024hj\034;\025\007}\t\031\n\003\005\002\"\0055\005\031AAK!\025Q\021Q\005\027W\021\035\tI\n\001C!\0037\013\021\"\0313e'R\024\030N\\4\025\025\005u\025QWA]\003\027\fy\r\005\003\002 \006=f\002BAQ\003WsA!a)\002*6\021\021Q\025\006\004\003O3\021A\002\037s_>$h(C\001\006\023\r\ti\013B\001\ba\006\0347.Y4f\023\021\t\t,a-\003\033M#(/\0338h\005VLG\016Z3s\025\r\ti\013\002\005\t\003o\0139\n1\001\002\036\006\t!\r\003\005\002<\006]\005\031AA_\003\025\031H/\031:u!\021\ty,!2\017\007)\t\t-C\002\002D\022\ta\001\025:fI\0264\027\002BAd\003\023\024aa\025;sS:<'bAAb\t!A\021QZAL\001\004\ti,A\002tKBD\001\"!5\002\030\002\007\021QX\001\004K:$\007")
public interface MapProxyLike<A, B, This extends MapLike<A, B, This> & Map<A, B>> extends MapLike<A, B, This>, IterableProxyLike<Tuple2<A, B>, This> {
  Option<B> get(A paramA);
  
  Iterator<Tuple2<A, B>> iterator();
  
  <B1> Map<A, B1> $plus(Tuple2<A, B1> paramTuple2);
  
  This $minus(A paramA);
  
  boolean isEmpty();
  
  <B1> B1 getOrElse(A paramA, Function0<B1> paramFunction0);
  
  B apply(A paramA);
  
  boolean contains(A paramA);
  
  boolean isDefinedAt(A paramA);
  
  Set<A> keySet();
  
  Iterator<A> keysIterator();
  
  Iterable<A> keys();
  
  Iterable<B> values();
  
  Iterator<B> valuesIterator();
  
  B default(A paramA);
  
  Map<A, B> filterKeys(Function1<A, Object> paramFunction1);
  
  <C> Map<A, C> mapValues(Function1<B, C> paramFunction1);
  
  <B1> Map<A, B1> updated(A paramA, B1 paramB1);
  
  <B1> Map<A, B1> $plus(Tuple2<A, B1> paramTuple21, Tuple2<A, B1> paramTuple22, Seq<Tuple2<A, B1>> paramSeq);
  
  <B1> Map<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> paramGenTraversableOnce);
  
  This filterNot(Function1<Tuple2<A, B>, Object> paramFunction1);
  
  StringBuilder addString(StringBuilder paramStringBuilder, String paramString1, String paramString2, String paramString3);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\MapProxyLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */