/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Map;
/*    */ import scala.collection.Parallel;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.GenericParMapCompanion;
/*    */ import scala.collection.generic.GenericParMapTemplate;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.generic.Shrinkable;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.Cloneable;
/*    */ import scala.collection.mutable.Iterable;
/*    */ import scala.collection.mutable.Map;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.ParIterable;
/*    */ import scala.collection.parallel.ParMap;
/*    */ import scala.collection.parallel.ParSeq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005%haB\001\003!\003\r\ta\003\002\007!\006\024X*\0319\013\005\r!\021aB7vi\006\024G.\032\006\003\013\031\t\001\002]1sC2dW\r\034\006\003\017!\t!bY8mY\026\034G/[8o\025\005I\021!B:dC2\f7\001A\013\004\031]\t3c\002\001\016#\r2S\006\016\t\003\035=i\021\001C\005\003!!\021a!\0218z%\0264\007\003\002\n\024+\001j\021AB\005\003)\031\021aaR3o\033\006\004\bC\001\f\030\031\001!Q\001\007\001C\002e\021\021aS\t\0035u\001\"AD\016\n\005qA!a\002(pi\"Lgn\032\t\003\035yI!a\b\005\003\007\005s\027\020\005\002\027C\021)!\005\001b\0013\t\ta\013\005\003%KU\001S\"\001\003\n\005\005!\001cA\024)U5\t!!\003\002*\005\tY\001+\031:Ji\026\024\030M\0317f!\021q1&\006\021\n\0051B!A\002+va2,'\007E\003/cU\0013'D\0010\025\t\001d!A\004hK:,'/[2\n\005Iz#!F$f]\026\024\030n\031)be6\013\007\017V3na2\fG/\032\t\003O\001\001baJ\033\026A]B\024B\001\034\003\005)\001\026M]'ba2K7.\032\t\005O\001)\002\005\005\003:wU\001S\"\001\036\013\005\r1\021B\001\037;\005\ri\025\r\035\005\006}\001!\taP\001\007I%t\027\016\036\023\025\003\001\003\"AD!\n\005\tC!\001B+oSRDa\001\022\001!\n#*\025a\0038fo\016{WNY5oKJ,\022A\022\t\005I\035Ss'\003\002I\t\tA1i\\7cS:,'\017C\003K\001\021\0053*\001\007nCB\034u.\0349b]&|g.F\001M!\rqSjM\005\003\035>\022acR3oKJL7\rU1s\033\006\0048i\\7qC:LwN\034\005\006!\002!\t%U\001\006K6\004H/_\013\002o!)1\013\001D\001)\006\0311/Z9\026\003aBQA\026\001\005B]\013q!\0369eCR,G-\006\002Y7R\031\021L\0301\021\t\035\002QC\027\t\003-m#Q\001X+C\002u\023\021!V\t\003AuAQaX+A\002U\t1a[3z\021\025\tW\0131\001[\003\0251\030\r\\;f\021\025\031\007\001\"\001e\003-9\030\016\0365EK\032\fW\017\034;\025\005]*\007\"\0024c\001\0049\027!\0013\021\t9AW\003I\005\003S\"\021\021BR;oGRLwN\\\031\t\013-\004A\021\0017\002!]LG\017\033#fM\006,H\016\036,bYV,GCA\034n\021\0251'\0161\001!\017\025y'\001#\001q\003\031\001\026M]'baB\021q%\035\004\006\003\tA\tA]\n\003cN\0042A\f;4\023\t)xFA\007QCJl\025\r\035$bGR|'/\037\005\006oF$\t\001_\001\007y%t\027\016\036 \025\003ADQ\001U9\005\002i,Ba\037@\002\002U\tA\020\005\003(\001u|\bC\001\f\t\025A\022P1\001\032!\r1\022\021\001\003\006Ee\024\r!\007\005\007\tF$\t!!\002\026\r\005\035\021qBA\n+\t\tI\001\005\004%\017\006-\021Q\003\t\007\035-\ni!!\005\021\007Y\ty\001\002\004\031\003\007\021\r!\007\t\004-\005MAA\002\022\002\004\t\007\021\004\005\004(\001\0055\021\021\003\005\b\0033\tH1AA\016\0031\031\027M\034\"vS2$gI]8n+\031\ti\"!\016\002:U\021\021q\004\t\n]\005\005\022QEA\031\003wI1!a\t0\0059\031\025M\\\"p[\nLg.\032$s_6\004B!a\n\002*5\t\021/\003\003\002,\0055\"\001B\"pY2L1!a\f0\00559UM\\'ba\032\0137\r^8ssB1abKA\032\003o\0012AFA\033\t\031A\022q\003b\0013A\031a#!\017\005\r\t\n9B1\001\032!\0319\003!a\r\0028\0311\021qH9\001\003\003\0221bV5uQ\022+g-Y;miV1\0211IA2\003O\032b!!\020\002F\005%\004\003CA$\003;\n\t'!\032\017\t\005%\0231\f\b\005\003\027\nIF\004\003\002N\005]c\002BA(\003+j!!!\025\013\007\005M#\"\001\004=e>|GOP\005\002\023%\021q\001C\005\003\013\031I!a\034\003\n\t\005}\022q\f\006\003_\022\0012AFA2\t\031A\022Q\bb\0013A\031a#a\032\005\r\t\niD1\001\032!\0319\003!!\031\002f!Y\021QNA\037\005\003\005\013\021BA5\003))h\016Z3sYfLgn\032\005\013M\006u\"\021!Q\001\n\005E\004C\002\bi\003C\n)\007C\004x\003{!\t!!\036\025\r\005]\024\021PA>!!\t9#!\020\002b\005\025\004\002CA7\003g\002\r!!\033\t\017\031\f\031\b1\001\002r!A\021qPA\037\t\003\n\t)\001\005%a2,8\017J3r)\021\t\031)!\"\016\005\005u\002\002CAD\003{\002\r!!#\002\005-4\bC\002\b,\003C\n)\007\003\005\002\016\006uB\021AAH\003%!S.\0338vg\022*\027\017\006\003\002\004\006E\005bB0\002\f\002\007\021\021\r\005\b!\006uB\021IAK+\t\t9\bC\004W\003{!\t%!'\026\t\005m\025\021\025\013\007\003;\013)+a*\021\021\005\035\022QHA1\003?\0032AFAQ\t\035a\026q\023b\001\003G\0132!!\032\036\021\035y\026q\023a\001\003CBq!YAL\001\004\ty\n\003\005\002,\006uB\021IAW\003\025!\003\017\\;t+\021\ty+!.\025\t\005E\026q\027\t\t\003O\ti$!\031\0024B\031a#!.\005\017q\013IK1\001\002$\"A\021qQAU\001\004\tI\f\005\004\017W\005\005\0241\027\005\t\003{\013i\004\"\021\002@\0061A%\\5okN$B!a\036\002B\"9q,a/A\002\005\005\004bB*\002>\021\005\023QY\013\003\003\017\004b!O\036\002b\005\025\004bBAf\003{!\taP\001\006G2,\027M\035\005\t\003\037\fi\004\"\001\002R\006\031\001/\036;\025\r\005M\027\021\\An!\025q\021Q[A3\023\r\t9\016\003\002\007\037B$\030n\0348\t\017}\013i\r1\001\002b!9\021-!4A\002\005\025\004bB2\002>\021\005\023q\034\013\005\003S\n\t\017C\004g\003;\004\r!!\035\t\017-\fi\004\"\021\002fR!\021\021NAt\021\0351\0271\035a\001\003K\002")
/*    */ public interface ParMap<K, V> extends GenMap<K, V>, ParMap<K, V>, ParIterable<Tuple2<K, V>>, GenericParMapTemplate<K, V, ParMap>, ParMapLike<K, V, ParMap<K, V>, Map<K, V>> {
/*    */   Combiner<Tuple2<K, V>, ParMap<K, V>> newCombiner();
/*    */   
/*    */   GenericParMapCompanion<ParMap> mapCompanion();
/*    */   
/*    */   ParMap<K, V> empty();
/*    */   
/*    */   Map<K, V> seq();
/*    */   
/*    */   <U> ParMap<K, U> updated(K paramK, U paramU);
/*    */   
/*    */   ParMap<K, V> withDefault(Function1<K, V> paramFunction1);
/*    */   
/*    */   ParMap<K, V> withDefaultValue(V paramV);
/*    */   
/*    */   public class ParMap$$anonfun$withDefaultValue$1 extends AbstractFunction1<K, V> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object d$2;
/*    */     
/*    */     public final V apply(Object x) {
/* 65 */       return (V)this.d$2;
/*    */     }
/*    */     
/*    */     public ParMap$$anonfun$withDefaultValue$1(ParMap $outer, Object d$2) {}
/*    */   }
/*    */   
/*    */   public static class WithDefault<K, V> extends ParMap.WithDefault<K, V> implements ParMap<K, V> {
/*    */     private final ParMap<K, V> underlying;
/*    */     
/*    */     private final Function1<K, V> d;
/*    */     
/*    */     public Combiner<Tuple2<K, V>, ParMap<K, V>> newCombiner() {
/* 78 */       return ParMap$class.newCombiner(this);
/*    */     }
/*    */     
/*    */     public GenericParMapCompanion<ParMap> mapCompanion() {
/* 78 */       return ParMap$class.mapCompanion(this);
/*    */     }
/*    */     
/*    */     public Object scala$collection$mutable$Cloneable$$super$clone() {
/* 78 */       return super.clone();
/*    */     }
/*    */     
/*    */     public ParMap<K, V> clone() {
/* 78 */       return (ParMap<K, V>)Cloneable.class.clone(this);
/*    */     }
/*    */     
/*    */     public Shrinkable<K> $minus$eq(Object elem1, Object elem2, Seq elems) {
/* 78 */       return Shrinkable.class.$minus$eq(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Shrinkable<K> $minus$minus$eq(TraversableOnce xs) {
/* 78 */       return Shrinkable.class.$minus$minus$eq(this, xs);
/*    */     }
/*    */     
/*    */     public Growable<Tuple2<K, V>> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 78 */       return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Growable<Tuple2<K, V>> $plus$plus$eq(TraversableOnce xs) {
/* 78 */       return Growable.class.$plus$plus$eq(this, xs);
/*    */     }
/*    */     
/*    */     public GenericCompanion<ParIterable> companion() {
/* 78 */       return ParIterable$class.companion(this);
/*    */     }
/*    */     
/*    */     public ParIterable<Tuple2<K, V>> toIterable() {
/* 78 */       return ParIterable$class.toIterable(this);
/*    */     }
/*    */     
/*    */     public ParSeq<Tuple2<K, V>> toSeq() {
/* 78 */       return ParIterable$class.toSeq(this);
/*    */     }
/*    */     
/*    */     public WithDefault(ParMap<K, V> underlying, Function1<K, V> d) {
/* 78 */       super(
/* 79 */           underlying, d);
/*    */       ParIterable$class.$init$(this);
/*    */       Growable.class.$init$(this);
/*    */       Shrinkable.class.$init$(this);
/*    */       Cloneable.class.$init$(this);
/*    */       ParMapLike$class.$init$(this);
/*    */       ParMap$class.$init$(this);
/*    */     }
/*    */     
/*    */     public WithDefault<K, V> $plus$eq(Tuple2<K, V> kv) {
/* 80 */       this.underlying.$plus$eq(kv);
/* 80 */       return this;
/*    */     }
/*    */     
/*    */     public WithDefault<K, V> $minus$eq(Object key) {
/* 81 */       this.underlying.$minus$eq((K)key);
/* 81 */       return this;
/*    */     }
/*    */     
/*    */     public WithDefault<K, V> empty() {
/* 82 */       return new WithDefault(this.underlying.empty(), this.d);
/*    */     }
/*    */     
/*    */     public <U> WithDefault<K, U> updated(Object key, Object value) {
/* 83 */       return new WithDefault(this.underlying.updated((K)key, (V)value), this.d);
/*    */     }
/*    */     
/*    */     public <U> WithDefault<K, U> $plus(Tuple2 kv) {
/* 84 */       return updated((K)kv._1(), (U)kv._2());
/*    */     }
/*    */     
/*    */     public WithDefault<K, V> $minus(Object key) {
/* 85 */       return new WithDefault(this.underlying.$minus((K)key), this.d);
/*    */     }
/*    */     
/*    */     public Map<K, V> seq() {
/* 86 */       return this.underlying.seq().withDefault(this.d);
/*    */     }
/*    */     
/*    */     public void clear() {
/* 87 */       this.underlying.clear();
/*    */     }
/*    */     
/*    */     public Option<V> put(Object key, Object value) {
/* 88 */       return this.underlying.put((K)key, (V)value);
/*    */     }
/*    */     
/*    */     public ParMap<K, V> withDefault(Function1<K, V> d) {
/* 93 */       return new WithDefault(this.underlying, d);
/*    */     }
/*    */     
/*    */     public ParMap<K, V> withDefaultValue(Object d) {
/* 94 */       return new WithDefault(this.underlying, (Function1<K, V>)new ParMap$WithDefault$$anonfun$withDefaultValue$2(this, (WithDefault<K, V>)d));
/*    */     }
/*    */     
/*    */     public class ParMap$WithDefault$$anonfun$withDefaultValue$2 extends AbstractFunction1<K, V> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Object d$1;
/*    */       
/*    */       public final V apply(Object x) {
/* 94 */         return (V)this.d$1;
/*    */       }
/*    */       
/*    */       public ParMap$WithDefault$$anonfun$withDefaultValue$2(ParMap.WithDefault $outer, Object d$1) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */