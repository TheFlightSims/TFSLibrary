package scala.collection;

import scala.Equals;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Serializable;
import scala.Tuple2;
import scala.collection.parallel.ParMap;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;

@ScalaSignature(bytes = "\006\001\005ucaB\001\003!\003\r\ta\002\002\013\017\026tW*\0319MS.,'BA\002\005\003)\031w\016\0347fGRLwN\034\006\002\013\005)1oY1mC\016\001Q\003\002\005\027A\r\032R\001A\005\016K!\002\"AC\006\016\003\021I!\001\004\003\003\r\005s\027PU3g!\021qq\"\005\022\016\003\tI!\001\005\002\003\037\035+g.\023;fe\006\024G.\032'jW\026\004BA\003\n\025?%\0211\003\002\002\007)V\004H.\032\032\021\005U1B\002\001\003\006/\001\021\r\001\007\002\002\003F\021\021\004\b\t\003\025iI!a\007\003\003\0179{G\017[5oOB\021!\"H\005\003=\021\0211!\0218z!\t)\002\005\002\004\"\001\021\025\r\001\007\002\002\005B\021Qc\t\003\007I\001!)\031\001\r\003\tI+\007O\035\t\003\025\031J!a\n\003\003\r\025\013X/\0317t!\021q\021&E\026\n\005)\022!A\004)be\006dG.\0327ju\006\024G.\032\t\005Y=\"r$D\001.\025\tq#!\001\005qCJ\fG\016\\3m\023\t\001TF\001\004QCJl\025\r\035\005\006e\001!\taM\001\007I%t\027\016\036\023\025\003Q\002\"AC\033\n\005Y\"!\001B+oSRDQ\001\017\001\007\002e\nq\001Z3gCVdG\017\006\002 u!)1h\016a\001)\005\0311.Z=\t\013u\002a\021\001 \002\007\035,G\017\006\002@\005B\031!\002Q\020\n\005\005#!AB(qi&|g\016C\003<y\001\007A\003C\003E\001\031\005Q)A\003baBd\027\020\006\002 \r\")1h\021a\001)!)\001\n\001D\001\023\006\0311/Z9\026\003)\003BAD&\025?%\021AJ\001\002\004\033\006\004\b\"\002(\001\r\003y\025!\002\023qYV\034XC\001)V)\t\t\006\f\005\003\017%R!\026BA*\003\005\0319UM\\'baB\021Q#\026\003\006-6\023\ra\026\002\003\005F\n\"a\b\017\t\013ek\005\031\001.\002\005-4\b\003\002\006\023)QCQ\001\030\001\007\002u\013a\001J7j]V\034HC\001\022_\021\025Y4\f1\001\025\021\025\001\007\001\"\021b\003!A\027m\0355D_\022,G#\0012\021\005)\031\027B\0013\005\005\rIe\016\036\005\006M\0021\taZ\001\nO\026$xJ]#mg\026,\"\001\0336\025\007%\\G\016\005\002\026U\022)a+\032b\001/\")1(\032a\001)!1\001(\032CA\0025\0042A\0038j\023\tyGA\001\005=Eft\027-\\3?\021\025\t\bA\"\001s\003!\031wN\034;bS:\034HCA:w!\tQA/\003\002v\t\t9!i\\8mK\006t\007\"B\036q\001\004!\002\"\002=\001\r\003I\030aC5t\t\0264\027N\\3e\003R$\"a\035>\t\013m:\b\031\001\013\t\013q\004a\021A?\002\r-,\027pU3u+\005q\bc\001\b\000)%\031\021\021\001\002\003\r\035+gnU3u\021\035\t)\001\001D\001\003\017\tAa[3zgV\021\021\021\002\t\005\035\005-A#C\002\002\016\t\0211bR3o\023R,'/\0312mK\"9\021\021\003\001\007\002\005M\021A\002<bYV,7/\006\002\002\026A!a\"a\003 \021\035\tI\002\001D\001\0037\tAb[3zg&#XM]1u_J,\"!!\b\021\t9\ty\002F\005\004\003C\021!\001C%uKJ\fGo\034:\t\017\005\025\002A\"\001\002(\005qa/\0317vKNLE/\032:bi>\024XCAA\025!\021q\021qD\020\t\017\0055\002A\"\001\0020\005Qa-\0337uKJ\\U-_:\025\t\005E\0221\007\t\005\035I#r\004\003\005\0026\005-\002\031AA\034\003\005\001\b#\002\006\002:Q\031\030bAA\036\t\tIa)\0368di&|g.\r\005\b\003\001a\021AA!\003%i\027\r\035,bYV,7/\006\003\002D\005%C\003BA#\003\033\002RA\004*\025\003\017\0022!FA%\t\035\tY%!\020C\002a\021\021a\021\005\t\003\037\ni\0041\001\002R\005\ta\r\005\004\013\003sy\022q\t\005\b\003+\002A\021IA,\003\031)\027/^1mgR\0311/!\027\t\017\005m\0231\013a\0019\005!A\017[1u\001")
public interface GenMapLike<A, B, Repr> extends GenIterableLike<Tuple2<A, B>, Repr>, Equals, Parallelizable<Tuple2<A, B>, ParMap<A, B>> {
  B default(A paramA);
  
  Option<B> get(A paramA);
  
  B apply(A paramA);
  
  Map<A, B> seq();
  
  <B1> GenMap<A, B1> $plus(Tuple2<A, B1> paramTuple2);
  
  Repr $minus(A paramA);
  
  int hashCode();
  
  <B1> B1 getOrElse(A paramA, Function0<B1> paramFunction0);
  
  boolean contains(A paramA);
  
  boolean isDefinedAt(A paramA);
  
  GenSet<A> keySet();
  
  GenIterable<A> keys();
  
  GenIterable<B> values();
  
  Iterator<A> keysIterator();
  
  Iterator<B> valuesIterator();
  
  GenMap<A, B> filterKeys(Function1<A, Object> paramFunction1);
  
  <C> GenMap<A, C> mapValues(Function1<B, C> paramFunction1);
  
  boolean equals(Object paramObject);
  
  public class GenMapLike$$anonfun$liftedTree1$1$1 extends AbstractFunction1<Tuple2<A, B>, Object> implements Serializable {
    public static final long serialVersionUID = 0L;
    
    private final GenMap x2$1;
    
    public final boolean apply(Tuple2 x0$1) {
      // Byte code:
      //   0: aload_1
      //   1: ifnull -> 125
      //   4: aload_0
      //   5: getfield x2$1 : Lscala/collection/GenMap;
      //   8: aload_1
      //   9: invokevirtual _1 : ()Ljava/lang/Object;
      //   12: invokeinterface get : (Ljava/lang/Object;)Lscala/Option;
      //   17: astore_2
      //   18: aload_2
      //   19: instanceof scala/Some
      //   22: ifeq -> 119
      //   25: aload_2
      //   26: checkcast scala/Some
      //   29: astore_3
      //   30: aload_1
      //   31: invokevirtual _2 : ()Ljava/lang/Object;
      //   34: aload_3
      //   35: invokevirtual x : ()Ljava/lang/Object;
      //   38: astore #5
      //   40: dup
      //   41: astore #4
      //   43: aload #5
      //   45: if_acmpne -> 52
      //   48: iconst_1
      //   49: goto -> 110
      //   52: aload #4
      //   54: ifnonnull -> 61
      //   57: iconst_0
      //   58: goto -> 110
      //   61: aload #4
      //   63: instanceof java/lang/Number
      //   66: ifeq -> 82
      //   69: aload #4
      //   71: checkcast java/lang/Number
      //   74: aload #5
      //   76: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
      //   79: goto -> 110
      //   82: aload #4
      //   84: instanceof java/lang/Character
      //   87: ifeq -> 103
      //   90: aload #4
      //   92: checkcast java/lang/Character
      //   95: aload #5
      //   97: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
      //   100: goto -> 110
      //   103: aload #4
      //   105: aload #5
      //   107: invokevirtual equals : (Ljava/lang/Object;)Z
      //   110: ifeq -> 119
      //   113: iconst_1
      //   114: istore #6
      //   116: goto -> 122
      //   119: iconst_0
      //   120: istore #6
      //   122: iload #6
      //   124: ireturn
      //   125: new scala/MatchError
      //   128: dup
      //   129: aload_1
      //   130: invokespecial <init> : (Ljava/lang/Object;)V
      //   133: athrow
      // Line number table:
      //   Java source line number -> byte code offset
      //   #118	-> 0
      //   #119	-> 4
      //   #118	-> 8
      //   #119	-> 9
      //   #120	-> 18
      //   #118	-> 30
      //   #120	-> 31
      //   #119	-> 34
      //   #120	-> 35
      //   #121	-> 113
      //   #122	-> 119
      //   #118	-> 122
      //   #119	-> 122
      // Local variable table:
      //   start	length	slot	name	descriptor
      //   0	134	0	this	Lscala/collection/GenMapLike$$anonfun$liftedTree1$1$1;
      //   0	134	1	x0$1	Lscala/Tuple2;
    }
    
    public GenMapLike$$anonfun$liftedTree1$1$1(GenMapLike $outer, GenMap x2$1) {}
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\GenMapLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */