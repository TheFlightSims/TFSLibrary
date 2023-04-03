/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.MapLike;
/*     */ import scala.collection.Parallelizable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.Shrinkable;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.mutable.ParMap;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005egaB\001\003!\003\r\t!\003\002\b\033\006\004H*[6f\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\t)!b$I\n\t\001-yAF\r\035<}A\021A\"D\007\002\r%\021aB\002\002\007\003:L(+\0324\021\013A\t\"#\b\021\016\003\021I!!\001\003\021\005M!B\002\001\003\006+\001\021\rA\006\002\002\003F\021qC\007\t\003\031aI!!\007\004\003\0179{G\017[5oOB\021AbG\005\0039\031\0211!\0218z!\t\031b\004B\003 \001\t\007aCA\001C!\t\031\022\005\002\004#\001\021\025\ra\t\002\005)\"L7/\005\002\030II\031QeJ\025\007\t\031\002\001\001\n\002\ryI,g-\0338f[\026tGO\020\t\006Q\001\021R\004I\007\002\005A!\001F\013\n\036\023\tY#AA\002NCB\004B\001K\0270A%\021aF\001\002\b\005VLG\016Z3s!\021a\001GE\017\n\005E2!A\002+va2,'\007E\0024m=j\021\001\016\006\003k\021\tqaZ3oKJL7-\003\0028i\tAqI]8xC\ndW\rE\0024sII!A\017\033\003\025MC'/\0338lC\ndW\rE\002)y\001J!!\020\002\003\023\rcwN\\3bE2,\007\003\002\t@_\005K!\001\021\003\003\035A\013'/\0317mK2L'0\0312mKB!!I\022\n\036\033\005\031%BA\002E\025\t)E!\001\005qCJ\fG\016\\3m\023\t95I\001\004QCJl\025\r\035\005\006\023\002!\tAS\001\007I%t\027\016\036\023\025\003-\003\"\001\004'\n\00553!\001B+oSRDaa\024\001!\n#\002\026A\0038fo\n+\030\016\0343feV\tA\006\003\004S\001\001&\tfU\001\fa\006\0248i\\7cS:,'/F\001U!\021)fkL!\016\003\021K!a\026#\003\021\r{WNY5oKJDQ!\027\001\005\002i\0131\001];u)\rYf\f\031\t\004\031qk\022BA/\007\005\031y\005\017^5p]\")q\f\027a\001%\005\0311.Z=\t\013\005D\006\031A\017\002\013Y\fG.^3\t\013\r\004A\021\0013\002\rU\004H-\031;f)\rYUM\032\005\006?\n\004\rA\005\005\006C\n\004\r!\b\005\006Q\0021\t![\001\tIAdWo\035\023fcR\021!n[\007\002\001!)An\032a\001_\005\0211N\036\005\006]\002!\te\\\001\bkB$\027\r^3e+\t\0018\017F\002rm^\004B\001\013\026\023eB\0211c\035\003\006i6\024\r!\036\002\003\005F\n\"!\b\016\t\013}k\007\031\001\n\t\013\005l\007\031\001:\t\013e\004A\021\001>\002\013\021\002H.^:\026\005mtHC\001?\000!\021A#FE?\021\005MqH!\002;y\005\004)\bB\0027y\001\004\t\t\001\005\003\raIi\bf\002=\002\006\005E\021Q\003\t\005\003\017\ti!\004\002\002\n)\031\0211\002\004\002\025\005tgn\034;bi&|g.\003\003\002\020\005%!!C7jOJ\fG/[8oC\t\t\031\"A-aW\001\0043M]3bi\026\034\b%\031\021oK^\004S.\0319/AU\033X\r\t1,{\001\004Co\034\021bI\022\004\023M\034\021fY\026lWM\034;!i>\004C\017[5tA5\f\007\017I1oI\002\022X\r^;s]\002\"\b.\031;![\006\004\b%\033;tK24g&\t\002\002\030\005)!G\f\035/a!1\021\020\001C!\0037)B!!\b\002$QA\021qDA\023\003W\ty\003E\003)UI\t\t\003E\002\024\003G!a\001^A\r\005\004)\b\002CA\024\0033\001\r!!\013\002\013\025dW-\\\031\021\0131\001$#!\t\t\021\0055\022\021\004a\001\003S\tQ!\0327f[JB\001\"!\r\002\032\001\007\0211G\001\006K2,Wn\035\t\006\031\005U\022\021F\005\004\003o1!A\003\037sKB,\027\r^3e}!B\021\021DA\003\003#\t)\002C\004\002>\001!\t%a\020\002\025\021\002H.^:%a2,8/\006\003\002B\005\035C\003BA\"\003\023\002R\001\013\026\023\003\013\0022aEA$\t\031!\0301\bb\001k\"A\0211JA\036\001\004\ti%\001\002ygB)\001#a\024\002T%\031\021\021\013\003\003%\035+g\016\026:bm\026\0248/\0312mK>s7-\032\t\006\031A\022\022Q\t\025\t\003w\t)!a\026\002\026\005\022\021\021L\001\\A.Z\003\rI2sK\006$Xm\035\021bA9,w\017I7ba:\002Sk]3!A.ZS\b\031\021u_\002\nG\r\032\021b]\002*G.Z7f]R\004Co\034\021uQ&\034\b%\\1qA\005tG\r\t:fiV\024h\016\t;iCR\004S.\0319!SR\034X\r\0344/\021\035\ti\006\001C\001\003?\naA]3n_Z,GcA.\002b!1q,a\027A\002IAq!!\032\001\r\003\t9'A\005%[&tWo\035\023fcR\031!.!\033\t\r}\013\031\0071\001\023\021\035\ti\007\001C!\003_\na\001J7j]V\034Hc\001\021\002r!1q,a\033A\002IA\003\"a\033\002\006\005U\024QC\021\003\003o\na\fY\027aA\r\024X-\031;fg\002\n\007E\\3xA5\f\007O\f\021Vg\026\004\003-L\037aAQ|\007E]3n_Z,\007%\0318!K2,W.\0328uA\031\024x.\034\021uQ&\034\b%\\1qA\005tG\r\t:fiV\024h\016\t;iCR\004S.\0319!SR\034X\r\0344/\021\031\tY\b\001C\001\025\006)1\r\\3be\"9\021q\020\001\005\002\005\005\025aD4fi>\023X\t\\:f+B$\027\r^3\025\013u\t\031)!\"\t\r}\013i\b1\001\023\021%\t9)! \005\002\004\tI)\001\002paB!A\"a#\036\023\r\tiI\002\002\ty\tLh.Y7f}!9\021\021\023\001\005\002\005M\025!\003;sC:\034hm\034:n)\rQ\027Q\023\005\t\003/\013y\t1\001\002\032\006\ta\r\005\004\r\0037\023R$H\005\004\003;3!!\003$v]\016$\030n\03483\021\035\t\t\013\001C\001\003G\013aA]3uC&tGc\0016\002&\"A\021qUAP\001\004\tI+A\001q!\035a\0211\024\n\036\003W\0032\001DAW\023\r\tyK\002\002\b\005>|G.Z1o\021\035\t\031\f\001C!\003k\013Qa\0317p]\026$\022\001\t\005\b\003s\003A\021AA[\003\031\021Xm];mi\"9\021Q\016\001\005B\005uFc\002\021\002@\006\005\0271\031\005\b\003O\tY\f1\001\023\021\035\ti#a/A\002IA\001\"!\r\002<\002\007\021Q\031\t\005\031\005U\"\003\013\005\002<\006\025\021QOA\013\021\035\tY\r\001C!\003\033\fA\002J7j]V\034H%\\5okN$2\001IAh\021!\tY%!3A\002\005E\007\003\002\t\002PIA\003\"!3\002\006\005U\027QC\021\003\003/\f\001\rY\027.A\002\032'/Z1uKN\004\023\r\t8fo\002j\027\r\035\030!+N,\007\005Y\027.{\001\004Co\034\021sK6|g/\032\021b]\002*G.Z7f]R\004cM]8nAQD\027n\035\021nCB\004\023M\0343!e\026$XO\0358!i\"\fG\017I7ba\002JGo]3mM:\002")
/*     */ public interface MapLike<A, B, This extends MapLike<A, B, This> & Map<A, B>> extends MapLike<A, B, This>, Builder<Tuple2<A, B>, This>, Growable<Tuple2<A, B>>, Shrinkable<A>, Cloneable<This>, Parallelizable<Tuple2<A, B>, ParMap<A, B>> {
/*     */   Builder<Tuple2<A, B>, This> newBuilder();
/*     */   
/*     */   Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner();
/*     */   
/*     */   Option<B> put(A paramA, B paramB);
/*     */   
/*     */   void update(A paramA, B paramB);
/*     */   
/*     */   MapLike<A, B, This> $plus$eq(Tuple2<A, B> paramTuple2);
/*     */   
/*     */   <B1> Map<A, B1> updated(A paramA, B1 paramB1);
/*     */   
/*     */   <B1> Map<A, B1> $plus(Tuple2<A, B1> paramTuple2);
/*     */   
/*     */   <B1> Map<A, B1> $plus(Tuple2<A, B1> paramTuple21, Tuple2<A, B1> paramTuple22, Seq<Tuple2<A, B1>> paramSeq);
/*     */   
/*     */   <B1> Map<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> paramGenTraversableOnce);
/*     */   
/*     */   Option<B> remove(A paramA);
/*     */   
/*     */   MapLike<A, B, This> $minus$eq(A paramA);
/*     */   
/*     */   This $minus(A paramA);
/*     */   
/*     */   void clear();
/*     */   
/*     */   B getOrElseUpdate(A paramA, Function0<B> paramFunction0);
/*     */   
/*     */   MapLike<A, B, This> transform(Function2<A, B, B> paramFunction2);
/*     */   
/*     */   MapLike<A, B, This> retain(Function2<A, B, Object> paramFunction2);
/*     */   
/*     */   This clone();
/*     */   
/*     */   This result();
/*     */   
/*     */   This $minus(A paramA1, A paramA2, Seq<A> paramSeq);
/*     */   
/*     */   This $minus$minus(GenTraversableOnce<A> paramGenTraversableOnce);
/*     */   
/*     */   public class MapLike$$anonfun$clear$1 extends AbstractFunction1<A, MapLike<A, B, This>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final MapLike<A, B, This> apply(Object key) {
/* 174 */       return this.$outer.$minus$eq((A)key);
/*     */     }
/*     */     
/*     */     public MapLike$$anonfun$clear$1(MapLike $outer) {}
/*     */   }
/*     */   
/*     */   public class MapLike$$anonfun$transform$1 extends AbstractFunction1<Tuple2<A, B>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 f$1;
/*     */     
/*     */     public final void apply(Tuple2 x0$1) {
/* 200 */       if (x0$1 != null) {
/* 201 */         this.$outer.update(x0$1._1(), this.f$1.apply(x0$1._1(), x0$1._2()));
/*     */         return;
/*     */       } 
/*     */       throw new MatchError(x0$1);
/*     */     }
/*     */     
/*     */     public MapLike$$anonfun$transform$1(MapLike $outer, Function2 f$1) {}
/*     */   }
/*     */   
/*     */   public class MapLike$$anonfun$retain$1 extends AbstractFunction1<Tuple2<A, B>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Tuple2 check$ifrefutable$1) {
/*     */       boolean bool;
/* 212 */       if (check$ifrefutable$1 != null) {
/* 212 */         bool = true;
/*     */       } else {
/* 212 */         bool = false;
/*     */       } 
/* 212 */       return bool;
/*     */     }
/*     */     
/*     */     public MapLike$$anonfun$retain$1(MapLike $outer) {}
/*     */   }
/*     */   
/*     */   public class MapLike$$anonfun$retain$2 extends AbstractFunction1<Tuple2<A, B>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 p$1;
/*     */     
/*     */     public final Object apply(Tuple2 x$1) {
/* 212 */       if (x$1 != null)
/* 212 */         return 
/* 213 */           BoxesRunTime.unboxToBoolean(this.p$1.apply(x$1._1(), x$1._2())) ? BoxedUnit.UNIT : this.$outer.$minus$eq(x$1._1()); 
/*     */       throw new MatchError(x$1);
/*     */     }
/*     */     
/*     */     public MapLike$$anonfun$retain$2(MapLike $outer, Function2 p$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\MapLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */