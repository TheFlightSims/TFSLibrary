/*     */ package scala.collection.convert;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.concurrent.Map;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.ConcurrentMap;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.Seq;
/*     */ import scala.collection.mutable.Set;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005-haB\001\003!\003\r\t!\003\002\017\t\026\034wN]1uK\006\033(*\031<b\025\t\031A!A\004d_:4XM\035;\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\024\005\001Q\001CA\006\r\033\0051\021BA\007\007\005\031\te.\037*fM\")q\002\001C\001!\0051A%\0338ji\022\"\022!\005\t\003\027II!a\005\004\003\tUs\027\016\036\005\006+\001!\031AF\001\030CNT\025M^1Ji\026\024\030\r^8s\007>tg/\032:uKJ,\"aF\026\025\005a!\004cA\r\036C9\021!dG\007\002\005%\021ADA\001\013\t\026\034wN]1u_J\034\030B\001\020 \005\031\t5OS1wC&\021\001E\001\002\013\t\026\034wN]1u_J\034\bc\001\022(S5\t1E\003\002%K\005!Q\017^5m\025\0051\023\001\0026bm\006L!\001K\022\003\021%#XM]1u_J\004\"AK\026\r\001\021)A\006\006b\001[\t\t\021)\005\002/cA\0211bL\005\003a\031\021qAT8uQ&tw\r\005\002\fe%\0211G\002\002\004\003:L\b\"B\033\025\001\0041\024!A5\021\007]B\024&D\001\005\023\tAC\001C\003;\001\021\r1(\001\016bg*\013g/Y#ok6,'/\031;j_:\034uN\034<feR,'/\006\002=\003R\021QH\021\t\0043y\002\025BA  \005E\t5OS1wC\026sW/\\3sCRLwN\034\t\003U\005#Q\001L\035C\0025BQ!N\035A\002\r\0032a\016\035A\021\025)\005\001b\001G\003]\t7OS1wC&#XM]1cY\026\034uN\034<feR,'/\006\002H!R\021\001*\025\t\0043uI\005c\001&N\0376\t1J\003\002MK\005!A.\0318h\023\tq5J\001\005Ji\026\024\030M\0317f!\tQ\003\013B\003-\t\n\007Q\006C\0036\t\002\007!\013E\0028'>K!A\024\003\t\013U\003A1\001,\0023\005\034(*\031<b\007>dG.Z2uS>t7i\0348wKJ$XM]\013\003/r#\"\001W/\021\007eI6,\003\002[?\t\001\022i\035&bm\006\034u\016\0347fGRLwN\034\t\003Uq#Q\001\f+C\0025BQ!\016+A\002y\0032aN*\\\021\025\001\007\001b\001b\003e\021WO\0324fe\006\033(*\031<b\031&\034HoQ8om\026\024H/\032:\026\005\tDGCA2j!\rIR\004\032\t\004E\025<\027B\0014$\005\021a\025n\035;\021\005)BG!\002\027`\005\004i\003\"\0026`\001\004Y\027!\0012\021\0071|w-D\001n\025\tqG!A\004nkR\f'\r\\3\n\005Al'A\002\"vM\032,'\017C\003s\001\021\r1/A\017nkR\f'\r\\3TKF\f5OS1wC2K7\017^\"p]Z,'\017^3s+\t!\b\020\006\002vsB\031\021$\b<\021\007\t*w\017\005\002+q\022)A&\035b\001[!)!.\035a\001uB\031An_<\n\005ql'aA*fc\")a\020\001C\002\00612/Z9Bg*\013g/\031'jgR\034uN\034<feR,'/\006\003\002\002\005%A\003BA\002\003\027\001B!G\017\002\006A!!%ZA\004!\rQ\023\021\002\003\006Yu\024\r!\f\005\007Uv\004\r!!\004\021\013]\ny!a\002\n\005q$\001bBA\n\001\021\r\021QC\001\035[V$\030M\0317f'\026$\030i\035&bm\006\034V\r^\"p]Z,'\017^3s+\021\t9\"a\t\025\t\005e\021Q\005\t\0053u\tY\002E\003#\003;\t\t#C\002\002 \r\0221aU3u!\rQ\0231\005\003\007Y\005E!\031A\027\t\021\005\035\022\021\003a\001\003S\t\021a\035\t\006Y\006-\022\021E\005\004\003?i\007bBA\030\001\021\r\021\021G\001\026g\026$\030i\035&bm\006\034V\r^\"p]Z,'\017^3s+\021\t\031$a\017\025\t\005U\022Q\b\t\0053u\t9\004E\003#\003;\tI\004E\002+\003w!a\001LA\027\005\004i\003\002CA\024\003[\001\r!a\020\021\013]\n\t%!\017\n\007\005}A\001C\004\002F\001!\031!a\022\00295,H/\0312mK6\013\007/Q:KCZ\fW*\0319D_:4XM\035;feV1\021\021JA+\0033\"B!a\023\002^A!\021$HA'!\035\021\023qJA*\003/J1!!\025$\005\ri\025\r\035\t\004U\005UCA\002\027\002D\t\007Q\006E\002+\0033\"q!a\027\002D\t\007QFA\001C\021!\ty&a\021A\002\005\005\024!A7\021\0171\f\031'a\025\002X%\031\021\021K7\t\017\005\035\004\001b\001\002j\005I\022m\035&bm\006$\025n\031;j_:\f'/_\"p]Z,'\017^3s+\031\tY'!\036\002zQ!\021QNA>!\035I\022qNA:\003oJ1!!\035 \005A\t5OS1wC\022K7\r^5p]\006\024\030\020E\002+\003k\"a\001LA3\005\004i\003c\001\026\002z\0219\0211LA3\005\004i\003\002CA0\003K\002\r!! \021\0171\f\031'a\035\002x!9\021\021\021\001\005\004\005\r\025!F7ba\006\033(*\031<b\033\006\0048i\0348wKJ$XM]\013\007\003\013\013i)!%\025\t\005\035\0251\023\t\0053u\tI\tE\004#\003\037\nY)a$\021\007)\ni\t\002\004-\003\022\r!\f\t\004U\005EEaBA.\003\022\r!\f\005\t\003?\ny\b1\001\002\026B9q'a&\002\f\006=\025bAA)\t!9\0211\024\001\005\004\005u\025\001H1t\025\0064\030mQ8oGV\024(/\0328u\033\006\0048i\0348wKJ$XM]\013\007\003?\013\t,!.\025\t\005\005\026q\027\t\0053u\t\031\013\005\005\002&\006-\026qVAZ\033\t\t9KC\002\002*\016\n!bY8oGV\024(/\0328u\023\021\ti+a*\003\033\r{gnY;se\026tG/T1q!\rQ\023\021\027\003\007Y\005e%\031A\027\021\007)\n)\fB\004\002\\\005e%\031A\027\t\021\005}\023\021\024a\001\003s\003r\001\\A^\003_\013\031,C\002\002.6D\003\"!'\002@\006\025\027\021\032\t\004\027\005\005\027bAAb\r\tQA-\0329sK\016\fG/\0323\"\005\005\035\027\001M+tK\002\0027m\0348dkJ\024XM\034;/\033\006\004\b\rI5ogR,\027\r\032\021pM\002\0027i\0348dkJ\024XM\034;NCB\004g&\t\002\002L\0061!GL\0311]ABq!a4\001\t\007\t\t.A\020nCB\f5OS1wC\016{gnY;se\026tG/T1q\007>tg/\032:uKJ,b!a5\002\\\006}G\003BAk\003C\004B!G\017\002XBA\021QUAV\0033\fi\016E\002+\0037$a\001LAg\005\004i\003c\001\026\002`\0229\0211LAg\005\004i\003\002CA0\003\033\004\r!a9\021\021\005\025\030\021^Am\003;l!!a:\013\007\005%F!\003\003\002R\005\035\b")
/*     */ public interface DecorateAsJava {
/*     */   <A> Decorators.AsJava<Iterator<A>> asJavaIteratorConverter(Iterator<A> paramIterator);
/*     */   
/*     */   <A> Decorators.AsJavaEnumeration<A> asJavaEnumerationConverter(Iterator<A> paramIterator);
/*     */   
/*     */   <A> Decorators.AsJava<Iterable<A>> asJavaIterableConverter(Iterable<A> paramIterable);
/*     */   
/*     */   <A> Decorators.AsJavaCollection<A> asJavaCollectionConverter(Iterable<A> paramIterable);
/*     */   
/*     */   <A> Decorators.AsJava<List<A>> bufferAsJavaListConverter(Buffer<A> paramBuffer);
/*     */   
/*     */   <A> Decorators.AsJava<List<A>> mutableSeqAsJavaListConverter(Seq<A> paramSeq);
/*     */   
/*     */   <A> Decorators.AsJava<List<A>> seqAsJavaListConverter(Seq<A> paramSeq);
/*     */   
/*     */   <A> Decorators.AsJava<Set<A>> mutableSetAsJavaSetConverter(Set<A> paramSet);
/*     */   
/*     */   <A> Decorators.AsJava<Set<A>> setAsJavaSetConverter(Set<A> paramSet);
/*     */   
/*     */   <A, B> Decorators.AsJava<Map<A, B>> mutableMapAsJavaMapConverter(Map<A, B> paramMap);
/*     */   
/*     */   <A, B> Decorators.AsJavaDictionary<A, B> asJavaDictionaryConverter(Map<A, B> paramMap);
/*     */   
/*     */   <A, B> Decorators.AsJava<Map<A, B>> mapAsJavaMapConverter(Map<A, B> paramMap);
/*     */   
/*     */   <A, B> Decorators.AsJava<ConcurrentMap<A, B>> asJavaConcurrentMapConverter(ConcurrentMap<A, B> paramConcurrentMap);
/*     */   
/*     */   <A, B> Decorators.AsJava<ConcurrentMap<A, B>> mapAsJavaConcurrentMapConverter(Map<A, B> paramMap);
/*     */   
/*     */   public class DecorateAsJava$$anonfun$asJavaIteratorConverter$1 extends AbstractFunction0<Iterator<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Iterator i$1;
/*     */     
/*     */     public final Iterator<A> apply() {
/*  74 */       return WrapAsJava$.MODULE$.asJavaIterator(this.i$1);
/*     */     }
/*     */     
/*     */     public DecorateAsJava$$anonfun$asJavaIteratorConverter$1(DecorateAsJava $outer, Iterator i$1) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsJava$$anonfun$asJavaIterableConverter$1 extends AbstractFunction0<Iterable<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Iterable i$2;
/*     */     
/*     */     public final Iterable<A> apply() {
/* 111 */       return WrapAsJava$.MODULE$.asJavaIterable(this.i$2);
/*     */     }
/*     */     
/*     */     public DecorateAsJava$$anonfun$asJavaIterableConverter$1(DecorateAsJava $outer, Iterable i$2) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsJava$$anonfun$bufferAsJavaListConverter$1 extends AbstractFunction0<List<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Buffer b$1;
/*     */     
/*     */     public final List<A> apply() {
/* 145 */       return WrapAsJava$.MODULE$.bufferAsJavaList(this.b$1);
/*     */     }
/*     */     
/*     */     public DecorateAsJava$$anonfun$bufferAsJavaListConverter$1(DecorateAsJava $outer, Buffer b$1) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsJava$$anonfun$mutableSeqAsJavaListConverter$1 extends AbstractFunction0<List<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq b$2;
/*     */     
/*     */     public final List<A> apply() {
/* 164 */       return WrapAsJava$.MODULE$.mutableSeqAsJavaList(this.b$2);
/*     */     }
/*     */     
/*     */     public DecorateAsJava$$anonfun$mutableSeqAsJavaListConverter$1(DecorateAsJava $outer, Seq b$2) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsJava$$anonfun$seqAsJavaListConverter$1 extends AbstractFunction0<List<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq b$3;
/*     */     
/*     */     public final List<A> apply() {
/* 183 */       return WrapAsJava$.MODULE$.seqAsJavaList(this.b$3);
/*     */     }
/*     */     
/*     */     public DecorateAsJava$$anonfun$seqAsJavaListConverter$1(DecorateAsJava $outer, Seq b$3) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsJava$$anonfun$mutableSetAsJavaSetConverter$1 extends AbstractFunction0<Set<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Set s$1;
/*     */     
/*     */     public final Set<A> apply() {
/* 202 */       return WrapAsJava$.MODULE$.mutableSetAsJavaSet(this.s$1);
/*     */     }
/*     */     
/*     */     public DecorateAsJava$$anonfun$mutableSetAsJavaSetConverter$1(DecorateAsJava $outer, Set s$1) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsJava$$anonfun$setAsJavaSetConverter$1 extends AbstractFunction0<Set<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Set s$2;
/*     */     
/*     */     public final Set<A> apply() {
/* 221 */       return WrapAsJava$.MODULE$.setAsJavaSet(this.s$2);
/*     */     }
/*     */     
/*     */     public DecorateAsJava$$anonfun$setAsJavaSetConverter$1(DecorateAsJava $outer, Set s$2) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsJava$$anonfun$mutableMapAsJavaMapConverter$1 extends AbstractFunction0<Map<A, B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Map m$1;
/*     */     
/*     */     public final Map<A, B> apply() {
/* 240 */       return WrapAsJava$.MODULE$.mutableMapAsJavaMap(this.m$1);
/*     */     }
/*     */     
/*     */     public DecorateAsJava$$anonfun$mutableMapAsJavaMapConverter$1(DecorateAsJava $outer, Map m$1) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsJava$$anonfun$mapAsJavaMapConverter$1 extends AbstractFunction0<Map<A, B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Map m$2;
/*     */     
/*     */     public final Map<A, B> apply() {
/* 278 */       return WrapAsJava$.MODULE$.mapAsJavaMap(this.m$2);
/*     */     }
/*     */     
/*     */     public DecorateAsJava$$anonfun$mapAsJavaMapConverter$1(DecorateAsJava $outer, Map m$2) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsJava$$anonfun$asJavaConcurrentMapConverter$1 extends AbstractFunction0<ConcurrentMap<A, B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ConcurrentMap m$3;
/*     */     
/*     */     public final ConcurrentMap<A, B> apply() {
/* 298 */       return WrapAsJava$.MODULE$.asJavaConcurrentMap(this.m$3);
/*     */     }
/*     */     
/*     */     public DecorateAsJava$$anonfun$asJavaConcurrentMapConverter$1(DecorateAsJava $outer, ConcurrentMap m$3) {}
/*     */   }
/*     */   
/*     */   public class DecorateAsJava$$anonfun$mapAsJavaConcurrentMapConverter$1 extends AbstractFunction0<ConcurrentMap<A, B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Map m$4;
/*     */     
/*     */     public final ConcurrentMap<A, B> apply() {
/* 317 */       return WrapAsJava$.MODULE$.mapAsJavaConcurrentMap(this.m$4);
/*     */     }
/*     */     
/*     */     public DecorateAsJava$$anonfun$mapAsJavaConcurrentMapConverter$1(DecorateAsJava $outer, Map m$4) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\DecorateAsJava.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */