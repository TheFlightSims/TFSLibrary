package scala.collection.mutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\t]caB\001\003!\003\r\t!\003\002\020'ft7\r\033:p]&TX\rZ'ba*\0211\001B\001\b[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\rQQcH\n\004\001-y\001C\001\007\016\033\0051\021B\001\b\007\005\031\te.\037*fMB!\001#E\n\037\033\005\021\021B\001\n\003\005\ri\025\r\035\t\003)Ua\001\001B\003\027\001\t\007qCA\001B#\tA2\004\005\002\r3%\021!D\002\002\b\035>$\b.\0338h!\taA$\003\002\036\r\t\031\021I\\=\021\005QyB!\002\021\001\005\0049\"!\001\"\t\013\t\002A\021A\022\002\r\021Jg.\033;%)\005!\003C\001\007&\023\t1cA\001\003V]&$\bB\002\025\001!\023\005\021&A\002hKR$\"AK\027\021\0071Yc$\003\002-\r\t1q\n\035;j_:DQAL\024A\002M\t1a[3z\021\031\001\004\001%C\001c\005A\021\016^3sCR|'/F\0013!\r\031DGN\007\002\t%\021Q\007\002\002\t\023R,'/\031;peB!AbN\n\037\023\tAdA\001\004UkBdWM\r\005\007u\001\001J\021A\036\002\021\021\002H.^:%KF$\"\001P\037\016\003\001AQAP\035A\002Y\n!a\033<\t\r\001\003\001\023\"\001B\003%!S.\0338vg\022*\027\017\006\002=\005\")af\020a\001'!)A\t\001C!\013\006!1/\033>f+\0051\005C\001\007H\023\tAeAA\002J]RDQA\023\001\005B-\0131\001];u)\rQC*\024\005\006]%\003\ra\005\005\006\035&\003\rAH\001\006m\006dW/\032\005\006!\002!\t%U\001\007kB$\027\r^3\025\007\021\0226\013C\003/\037\002\0071\003C\003O\037\002\007a\004C\003V\001\021\005c+\001\004sK6|g/\032\013\003U]CQA\f+A\002MAQ!\027\001\005B\r\nQa\0317fCJDQa\027\001\005Bq\013qbZ3u\037J,En]3Va\022\fG/\032\013\004=us\006\"\002\030[\001\004\031\002BB0[\t\003\007\001-A\004eK\032\fW\017\034;\021\0071\tg$\003\002c\r\tAAHY=oC6,g\bC\003e\001\021\005S-A\005ue\006t7OZ8s[R\021AH\032\005\006O\016\004\r\001[\001\002MB)A\"[\n\037=%\021!N\002\002\n\rVt7\r^5p]JBQ\001\034\001\005B5\faA]3uC&tGC\001\037o\021\025y7\0161\001q\003\005\001\b#\002\007j'y\t\bC\001\007s\023\t\031hAA\004C_>dW-\0318\t\013U\004A\021\t<\002\rY\fG.^3t+\0059\bcA\032y=%\021\021\020\002\002\t\023R,'/\0312mK\"2Ao_A\002\003\017\001\"\001`@\016\003uT!A \004\002\025\005tgn\034;bi&|g.C\002\002\002u\024\021\"\\5he\006$\030n\0348\"\005\005\025\021!\0171wC2,Xm\0351!e\026$XO\0358tA\001LE/\032:bE2,7LQ/aAI\fG\017[3sAQD\027M\034\021a\023R,'/\031;pen\023U\f\031\030\"\005\005%\021!\002\032/q9\002\004bBA\007\001\021\005\023qB\001\017m\006dW/Z:Ji\026\024\030\r^8s+\t\t\t\002E\0024iyAq!!\006\001\t\003\n9\"A\003dY>tW\r\006\002\002\032A\031A(a\007\n\t\005u\021q\004\002\005'\026dg-C\002\002\"\021\021q\002\026:bm\026\0248/\0312mK2K7.\032\005\b\003K\001A\021IA\024\003\0351wN]3bG\",B!!\013\0026Q\031A%a\013\t\017\035\f\031\0031\001\002.A1A\"a\f7\003gI1!!\r\007\005%1UO\\2uS>t\027\007E\002\025\003k!q!a\016\002$\t\007qCA\001V\021\035\tY\004\001C!\003{\tQ!\0319qYf$2AHA \021\031q\023\021\ba\001'!9\0211\t\001\005B\005\025\023AB6fsN+G/\006\002\002HA!1'!\023\024\023\r\tY\005\002\002\004'\026$\bbBA(\001\021\005\023\021K\001\005W\026L8/\006\002\002TA\0311\007_\n)\017\005530a\026\002\b\005\022\021\021L\0018A.,\027p\0351!e\026$XO\0358tA\001LE/\032:bE2,7,Q/aAI\fG\017[3sAQD\027M\034\021a\023R,'/\031;pen\013U\f\031\030\t\017\005u\003\001\"\021\002`\005a1.Z=t\023R,'/\031;peV\021\021\021\r\t\004gQ\032\002bBA3\001\021\005\023qM\001\bSN,U\016\035;z+\005\t\bbBA6\001\021\005\023QN\001\tG>tG/Y5ogR\031\021/a\034\t\r9\nI\0071\001\024\021\035\t\031\b\001C!\003k\n1\"[:EK\032Lg.\0323BiR\031\021/a\036\t\r9\n\t\b1\001\024\0211\tY\bAA\001\002\023%\021QPAA\003%\031X\017]3sI\035,G\017F\002+\003BaALA=\001\004\031\022b\001\025\002\004&\031\021Q\021\003\003\0175\013\007\017T5lK\"Y\021\021\022\001\002\002\003%I!MAF\0039\031X\017]3sI%$XM]1u_JL1\001MAB\0211\ty\tAA\001\002\023%\021\021SAK\0039\031X\017]3sI\021\002H.^:%KF$2\001PAJ\021\031q\024Q\022a\001m%\031!(a&\n\007\005\025%\001\003\007\002\034\002\t\t\021!C\005\003;\013\t+A\btkB,'\017\n\023nS:,8\017J3r)\ra\024q\024\005\007]\005e\005\031A\n\n\007\001\0139\nC\006\002&\002\t\t\021!C\005\013\006\035\026AC:va\026\024He]5{K&\031A)!+\n\007\005-FAA\bUe\0064XM]:bE2,wJ\\2f\0211\ty\013AA\001\002\023%\021\021WA\\\003%\031X\017]3sIA,H\017F\003+\003g\013)\f\003\004/\003[\003\ra\005\005\007\035\0065\006\031\001\020\n\007)\0139\n\003\007\002<\002\t\t\021!C\005\003{\013\031-\001\007tkB,'\017J;qI\006$X\rF\003%\003\013\t\r\003\004/\003s\003\ra\005\005\007\035\006e\006\031\001\020\n\007A\0139\n\003\007\002H\002\t\t\021!C\005\003\023\fi-\001\007tkB,'\017\n:f[>4X\rF\002+\003\027DaALAc\001\004\031\022bA+\002\030\"Y\021\021\033\001\002\002\003%IaIAj\003-\031X\017]3sI\rdW-\031:\n\007e\0139\n\003\007\002X\002\t\t\021!C\005\0033\f\t/A\013tkB,'\017J4fi>\023X\t\\:f+B$\027\r^3\025\013y\tY.!8\t\r9\n)\0161\001\024\021!\ty.!6\005\002\004\001\027AA8q\023\rY\026q\023\005\r\003K\004\021\021!A\005\n\005\035\0301^\001\020gV\004XM\035\023ue\006t7OZ8s[R\031A(!;\t\r\035\f\031\0171\001i\023\r!\027q\023\005\r\003_\004\021\021!A\005\n\005E\030Q_\001\rgV\004XM\035\023sKR\f\027N\034\013\004y\005M\bBB8\002n\002\007\001/C\002m\003/C1\"!?\001\003\003\005I\021\002<\002|\006a1/\0369fe\0222\030\r\\;fg&\031Q/a!\t\031\005}\b!!A\001\n\023\tyA!\001\002)M,\b/\032:%m\006dW/Z:Ji\026\024\030\r^8s\023\021\ti!a!\t\031\t\025\001!!A\001\n\023\0219A!\003\002\027M,\b/\032:%G2|g.\032\013\002\037%!\021QCAL\0211\021i\001AA\001\002\023%!q\002B\016\0035\031X\017]3sI\031|'/Z1dQV!!\021\003B\r)\r!#1\003\005\bO\n-\001\031\001B\013!\031a\021q\006\034\003\030A\031AC!\007\005\017\005]\"1\002b\001/%!\021Q\005B\017\023\r\021y\002\002\002\r\023R,'/\0312mK2K7.\032\005\r\005G\001\021\021!A\005\n\t\025\"\021F\001\fgV\004XM\035\023baBd\027\020F\002\037\005OAaA\fB\021\001\004\031\022\002BA\036\003\007CAB!\f\001\003\003\005I\021BA#\005_\tAb];qKJ$3.Z=TKRLA!a\021\002\004\"a!1\007\001\002\002\003%I!!\025\0036\005Q1/\0369fe\022ZW-_:\n\t\005=\0231\021\005\r\005s\001\021\021!A\005\n\005}#1H\001\023gV\004XM\035\023lKf\034\030\n^3sCR|'/\003\003\002^\005\r\005\002\004B \001\005\005\t\021\"\003\002h\t\005\023!D:va\026\024H%[:F[B$\0300\003\003\002f\005\r\005\002\004B#\001\005\005\t\021\"\003\003H\t-\023AD:va\026\024HeY8oi\006Lgn\035\013\004c\n%\003B\002\030\003D\001\0071#\003\003\002l\005\r\005\002\004B(\001\005\005\t\021\"\003\003R\tU\023!E:va\026\024H%[:EK\032Lg.\0323BiR\031\021Oa\025\t\r9\022i\0051\001\024\023\021\t\031(a!")
public interface SynchronizedMap<A, B> extends Map<A, B> {
  Option<B> scala$collection$mutable$SynchronizedMap$$super$get(A paramA);
  
  Iterator<Tuple2<A, B>> scala$collection$mutable$SynchronizedMap$$super$iterator();
  
  SynchronizedMap<A, B> scala$collection$mutable$SynchronizedMap$$super$$plus$eq(Tuple2<A, B> paramTuple2);
  
  SynchronizedMap<A, B> scala$collection$mutable$SynchronizedMap$$super$$minus$eq(A paramA);
  
  int scala$collection$mutable$SynchronizedMap$$super$size();
  
  Option<B> scala$collection$mutable$SynchronizedMap$$super$put(A paramA, B paramB);
  
  void scala$collection$mutable$SynchronizedMap$$super$update(A paramA, B paramB);
  
  Option<B> scala$collection$mutable$SynchronizedMap$$super$remove(A paramA);
  
  void scala$collection$mutable$SynchronizedMap$$super$clear();
  
  B scala$collection$mutable$SynchronizedMap$$super$getOrElseUpdate(A paramA, Function0<B> paramFunction0);
  
  SynchronizedMap<A, B> scala$collection$mutable$SynchronizedMap$$super$transform(Function2<A, B, B> paramFunction2);
  
  SynchronizedMap<A, B> scala$collection$mutable$SynchronizedMap$$super$retain(Function2<A, B, Object> paramFunction2);
  
  Iterable<B> scala$collection$mutable$SynchronizedMap$$super$values();
  
  Iterator<B> scala$collection$mutable$SynchronizedMap$$super$valuesIterator();
  
  Map<A, B> scala$collection$mutable$SynchronizedMap$$super$clone();
  
  <U> void scala$collection$mutable$SynchronizedMap$$super$foreach(Function1<Tuple2<A, B>, U> paramFunction1);
  
  B scala$collection$mutable$SynchronizedMap$$super$apply(A paramA);
  
  Set<A> scala$collection$mutable$SynchronizedMap$$super$keySet();
  
  Iterable<A> scala$collection$mutable$SynchronizedMap$$super$keys();
  
  Iterator<A> scala$collection$mutable$SynchronizedMap$$super$keysIterator();
  
  boolean scala$collection$mutable$SynchronizedMap$$super$isEmpty();
  
  boolean scala$collection$mutable$SynchronizedMap$$super$contains(A paramA);
  
  boolean scala$collection$mutable$SynchronizedMap$$super$isDefinedAt(A paramA);
  
  Option<B> get(A paramA);
  
  Iterator<Tuple2<A, B>> iterator();
  
  SynchronizedMap<A, B> $plus$eq(Tuple2<A, B> paramTuple2);
  
  SynchronizedMap<A, B> $minus$eq(A paramA);
  
  int size();
  
  Option<B> put(A paramA, B paramB);
  
  void update(A paramA, B paramB);
  
  Option<B> remove(A paramA);
  
  void clear();
  
  B getOrElseUpdate(A paramA, Function0<B> paramFunction0);
  
  SynchronizedMap<A, B> transform(Function2<A, B, B> paramFunction2);
  
  SynchronizedMap<A, B> retain(Function2<A, B, Object> paramFunction2);
  
  Iterable<B> values();
  
  Iterator<B> valuesIterator();
  
  Map<A, B> clone();
  
  <U> void foreach(Function1<Tuple2<A, B>, U> paramFunction1);
  
  B apply(A paramA);
  
  Set<A> keySet();
  
  Iterable<A> keys();
  
  Iterator<A> keysIterator();
  
  boolean isEmpty();
  
  boolean contains(A paramA);
  
  boolean isDefinedAt(A paramA);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\SynchronizedMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */