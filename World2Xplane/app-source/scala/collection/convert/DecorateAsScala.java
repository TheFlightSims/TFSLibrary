/*     */ package scala.collection.convert;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Dictionary;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.concurrent.Map;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.ConcurrentMap;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.Set;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005UfaB\001\003!\003\r\t!\003\002\020\t\026\034wN]1uK\006\0338kY1mC*\0211\001B\001\bG>tg/\032:u\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001'\t\001!\002\005\002\f\0315\ta!\003\002\016\r\t1\021I\\=SK\032DQa\004\001\005\002A\ta\001J5oSR$C#A\t\021\005-\021\022BA\n\007\005\021)f.\033;\t\013U\001A1\001\f\0021\005\0348kY1mC&#XM]1u_J\034uN\034<feR,'/\006\002\030OQ\021\001\004\r\t\0043u\tcB\001\016\034\033\005\021\021B\001\017\003\003)!UmY8sCR|'o]\005\003=}\021q!Q:TG\006d\027-\003\002!\005\tQA)Z2pe\006$xN]:\021\007\t\032S%D\001\005\023\t!CA\001\005Ji\026\024\030\r^8s!\t1s\005\004\001\005\013!\"\"\031A\025\003\003\005\013\"AK\027\021\005-Y\023B\001\027\007\005\035qu\016\0365j]\036\004\"a\003\030\n\005=2!aA!os\")\021\007\006a\001e\005\t\021\016E\0024q\025j\021\001\016\006\003kY\nA!\036;jY*\tq'\001\003kCZ\f\027B\001\0235\021\025Q\004\001b\001<\003\r*g.^7fe\006$\030n\0348BgN\033\027\r\\1Ji\026\024\030\r^8s\007>tg/\032:uKJ,\"\001\020!\025\005u\n\005cA\r\036}A\031!eI \021\005\031\002E!\002\025:\005\004I\003\"B\031:\001\004\021\005cA\032D%\021A\t\016\002\f\013:,X.\032:bi&|g\016C\003G\001\021\rq)\001\021ji\026\024\030M\0317f\003N\0346-\0317b\023R,'/\0312mK\016{gN^3si\026\024XC\001%O)\tIu\nE\002\032;)\0032AI&N\023\taEA\001\005Ji\026\024\030M\0317f!\t1c\nB\003)\013\n\007\021\006C\0032\013\002\007\001\013E\002R)6k\021A\025\006\003'Z\nA\001\\1oO&\021AJ\025\005\006-\002!\031aV\001#G>dG.Z2uS>t\027i]*dC2\f\027\n^3sC\ndWmQ8om\026\024H/\032:\026\005acFCA-^!\rIRD\027\t\004E-[\006C\001\024]\t\025ASK1\001*\021\025\tT\0131\001_!\r\031tlW\005\003AR\022!bQ8mY\026\034G/[8o\021\025\021\007\001b\001d\003Y\t7oU2bY\006\024UO\0324fe\016{gN^3si\026\024XC\0013n)\t)g\016E\002\032;\031\0042a\0326m\033\005A'BA5\005\003\035iW\017^1cY\026L!a\0335\003\r\t+hMZ3s!\t1S\016B\003)C\n\007\021\006C\003pC\002\007\001/A\001m!\r\031\024\017\\\005\003eR\022A\001T5ti\")A\017\001C\002k\006\031\022m]*dC2\f7+\032;D_:4XM\035;feV\021a\017 \013\003ov\0042!G\017y!\r9\027p_\005\003u\"\0241aU3u!\t1C\020B\003)g\n\007\021\006C\003g\002\007q0A\001t!\021\031\024\021A>\n\005i$\004bBA\003\001\021\r\021qA\001\027[\006\004\030i]*dC2\fW*\0319D_:4XM\035;feV1\021\021BA\013\0033!B!a\003\002\036A!\021$HA\007!\0359\027qBA\n\003/I1!!\005i\005\ri\025\r\035\t\004M\005UAA\002\025\002\004\t\007\021\006E\002'\0033!q!a\007\002\004\t\007\021FA\001C\021!\ty\"a\001A\002\005\005\022!A7\021\017M\n\031#a\005\002\030%\031\021\021\003\033\t\017\005\035\002\001\"\001\002*\005i\022m]*dC2\f7i\0348dkJ\024XM\034;NCB\034uN\034<feR,'/\006\004\002,\005]\0221\b\013\005\003[\ti\004\005\003\032;\005=\002cB4\0022\005U\022\021H\005\004\003gA'!D\"p]\016,(O]3oi6\013\007\017E\002'\003o!a\001KA\023\005\004I\003c\001\024\002<\0219\0211DA\023\005\004I\003\002CA\020\003K\001\r!a\020\021\021\005\005\023qIA\033\003si!!a\021\013\007\005\025C'\001\006d_:\034WO\035:f]RLA!a\r\002D!B\021QEA&\003#\n)\006E\002\f\003\033J1!a\024\007\005)!W\r\035:fG\006$X\rZ\021\003\003'\nA-V:fA\001l\027\r]!t'\016\fG.Y\"p]\016,(O]3oi6\013\007oQ8om\026\024H/\032:aA%t7\017^3bI2\002\023M\0343!kN,\007\005Y2p]\016,(O]3oi:j\025\r\0351!S:\034H/Z1eA=4\007\005Y\"p]\016,(O]3oi6\013\007\017\031\030\"\005\005]\023A\002\032/cAr\003\007C\004\002\\\001!\031!!\030\002A5\f\007/Q:TG\006d\027mQ8oGV\024(/\0328u\033\006\0048i\0348wKJ$XM]\013\007\003?\ni'!\035\025\t\005\005\0241\017\t\0053u\t\031\007\005\005\002f\005%\0241NA8\033\t\t9GC\002\002F\021IA!!\005\002hA\031a%!\034\005\r!\nIF1\001*!\r1\023\021\017\003\b\0037\tIF1\001*\021!\ty\"!\027A\002\005U\004\003CA!\003\017\nY'a\034\t\017\005e\004\001b\001\002|\005iB-[2uS>t\027M]=BgN\033\027\r\\1NCB\034uN\034<feR,'/\006\004\002~\005\025\025\021\022\013\005\003\nY\t\005\003\032;\005\005\005cB4\002\020\005\r\025q\021\t\004M\005\025EA\002\025\002x\t\007\021\006E\002'\003\023#q!a\007\002x\t\007\021\006\003\005\002\016\006]\004\031AAH\003\005\001\bcB\032\002\022\006\r\025qQ\005\004\003'#$A\003#jGRLwN\\1ss\"9\021q\023\001\005\004\005e\025!\b9s_B,'\017^5fg\006\0338kY1mC6\013\007oQ8om\026\024H/\032:\025\t\005m\025Q\026\t\0053u\ti\nE\004h\003\037\ty*a(\021\t\005\005\026q\025\b\004\027\005\r\026bAAS\r\0051\001K]3eK\032LA!!+\002,\n11\013\036:j]\036T1!!*\007\021!\ti)!&A\002\005=\006cA\032\0022&\031\0211\027\033\003\025A\023x\016]3si&,7\017")
/*     */ public interface DecorateAsScala {
/*     */   <A> Decorators.AsScala<Iterator<A>> asScalaIteratorConverter(Iterator<A> paramIterator);
/*     */   
/*     */   <A> Decorators.AsScala<Iterator<A>> enumerationAsScalaIteratorConverter(Enumeration<A> paramEnumeration);
/*     */   
/*     */   <A> Decorators.AsScala<Iterable<A>> iterableAsScalaIterableConverter(Iterable<A> paramIterable);
/*     */   
/*     */   <A> Decorators.AsScala<Iterable<A>> collectionAsScalaIterableConverter(Collection<A> paramCollection);
/*     */   
/*     */   <A> Decorators.AsScala<Buffer<A>> asScalaBufferConverter(List<A> paramList);
/*     */   
/*     */   <A> Decorators.AsScala<Set<A>> asScalaSetConverter(Set<A> paramSet);
/*     */   
/*     */   <A, B> Decorators.AsScala<Map<A, B>> mapAsScalaMapConverter(Map<A, B> paramMap);
/*     */   
/*     */   <A, B> Decorators.AsScala<ConcurrentMap<A, B>> asScalaConcurrentMapConverter(ConcurrentMap<A, B> paramConcurrentMap);
/*     */   
/*     */   <A, B> Decorators.AsScala<Map<A, B>> mapAsScalaConcurrentMapConverter(ConcurrentMap<A, B> paramConcurrentMap);
/*     */   
/*     */   <A, B> Decorators.AsScala<Map<A, B>> dictionaryAsScalaMapConverter(Dictionary<A, B> paramDictionary);
/*     */   
/*     */   Decorators.AsScala<Map<String, String>> propertiesAsScalaMapConverter(Properties paramProperties);
/*     */   
/*     */   public class DecorateAsScala$$anonfun$asScalaIteratorConverter$1 extends AbstractFunction0<Iterator<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Iterator i$1;
/*     */     
/*     */     public final Iterator<A> apply() {
/*  35 */       return WrapAsScala$.MODULE$.asScalaIterator(this.i$1);
/*     */     }
/*     */     
/*     */     public DecorateAsScala$$anonfun$asScalaIteratorConverter$1(DecorateAsScala $outer, Iterator i$1) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsScala$$anonfun$enumerationAsScalaIteratorConverter$1 extends AbstractFunction0<Iterator<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Enumeration i$2;
/*     */     
/*     */     public final Iterator<A> apply() {
/*  54 */       return WrapAsScala$.MODULE$.enumerationAsScalaIterator(this.i$2);
/*     */     }
/*     */     
/*     */     public DecorateAsScala$$anonfun$enumerationAsScalaIteratorConverter$1(DecorateAsScala $outer, Enumeration i$2) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsScala$$anonfun$iterableAsScalaIterableConverter$1 extends AbstractFunction0<Iterable<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Iterable i$3;
/*     */     
/*     */     public final Iterable<A> apply() {
/*  73 */       return WrapAsScala$.MODULE$.iterableAsScalaIterable(this.i$3);
/*     */     }
/*     */     
/*     */     public DecorateAsScala$$anonfun$iterableAsScalaIterableConverter$1(DecorateAsScala $outer, Iterable i$3) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsScala$$anonfun$collectionAsScalaIterableConverter$1 extends AbstractFunction0<Iterable<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Collection i$4;
/*     */     
/*     */     public final Iterable<A> apply() {
/*  88 */       return WrapAsScala$.MODULE$.collectionAsScalaIterable(this.i$4);
/*     */     }
/*     */     
/*     */     public DecorateAsScala$$anonfun$collectionAsScalaIterableConverter$1(DecorateAsScala $outer, Collection i$4) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsScala$$anonfun$asScalaBufferConverter$1 extends AbstractFunction0<Buffer<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final List l$1;
/*     */     
/*     */     public final Buffer<A> apply() {
/* 107 */       return WrapAsScala$.MODULE$.asScalaBuffer(this.l$1);
/*     */     }
/*     */     
/*     */     public DecorateAsScala$$anonfun$asScalaBufferConverter$1(DecorateAsScala $outer, List l$1) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsScala$$anonfun$asScalaSetConverter$1 extends AbstractFunction0<Set<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Set s$1;
/*     */     
/*     */     public final Set<A> apply() {
/* 126 */       return WrapAsScala$.MODULE$.asScalaSet(this.s$1);
/*     */     }
/*     */     
/*     */     public DecorateAsScala$$anonfun$asScalaSetConverter$1(DecorateAsScala $outer, Set s$1) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsScala$$anonfun$mapAsScalaMapConverter$1 extends AbstractFunction0<Map<A, B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Map m$1;
/*     */     
/*     */     public final Map<A, B> apply() {
/* 143 */       return WrapAsScala$.MODULE$.mapAsScalaMap(this.m$1);
/*     */     }
/*     */     
/*     */     public DecorateAsScala$$anonfun$mapAsScalaMapConverter$1(DecorateAsScala $outer, Map m$1) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsScala$$anonfun$asScalaConcurrentMapConverter$1 extends AbstractFunction0<ConcurrentMap<A, B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ConcurrentMap m$2;
/*     */     
/*     */     public final ConcurrentMap<A, B> apply() {
/* 162 */       return WrapAsScala$.MODULE$.asScalaConcurrentMap(this.m$2);
/*     */     }
/*     */     
/*     */     public DecorateAsScala$$anonfun$asScalaConcurrentMapConverter$1(DecorateAsScala $outer, ConcurrentMap m$2) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsScala$$anonfun$mapAsScalaConcurrentMapConverter$1 extends AbstractFunction0<Map<A, B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ConcurrentMap m$3;
/*     */     
/*     */     public final Map<A, B> apply() {
/* 180 */       return WrapAsScala$.MODULE$.mapAsScalaConcurrentMap(this.m$3);
/*     */     }
/*     */     
/*     */     public DecorateAsScala$$anonfun$mapAsScalaConcurrentMapConverter$1(DecorateAsScala $outer, ConcurrentMap m$3) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsScala$$anonfun$dictionaryAsScalaMapConverter$1 extends AbstractFunction0<Map<A, B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Dictionary p$1;
/*     */     
/*     */     public final Map<A, B> apply() {
/* 194 */       return WrapAsScala$.MODULE$.dictionaryAsScalaMap(this.p$1);
/*     */     }
/*     */     
/*     */     public DecorateAsScala$$anonfun$dictionaryAsScalaMapConverter$1(DecorateAsScala $outer, Dictionary p$1) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsScala$$anonfun$propertiesAsScalaMapConverter$1 extends AbstractFunction0<Map<String, String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Properties p$2;
/*     */     
/*     */     public final Map<String, String> apply() {
/* 208 */       return WrapAsScala$.MODULE$.propertiesAsScalaMap(this.p$2);
/*     */     }
/*     */     
/*     */     public DecorateAsScala$$anonfun$propertiesAsScalaMapConverter$1(DecorateAsScala $outer, Properties p$2) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\DecorateAsScala.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */