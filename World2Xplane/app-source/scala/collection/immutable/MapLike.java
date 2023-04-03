/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.MapLike;
/*     */ import scala.collection.Parallelizable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.SetLike;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParMap;
/*     */ import scala.collection.parallel.immutable.ParSet;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\005eaB\001\003!\003\r\t!\003\002\b\033\006\004H*[6f\025\t\031A!A\005j[6,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U!!\002\006\020\"'\021\0011b\004\027\021\0051iQ\"\001\004\n\00591!AB!osJ+g\rE\003\021#Ii\002%D\001\005\023\t\tA\001\005\002\024)1\001A!B\013\001\005\0041\"!A!\022\005]Q\002C\001\007\031\023\tIbAA\004O_RD\027N\\4\021\0051Y\022B\001\017\007\005\r\te.\037\t\003'y!aa\b\001\005\006\0041\"!\001\"\021\005M\tCA\002\022\001\t\013\0071E\001\003UQ&\034\030CA\f%%\r)s%\013\004\005M\001\001AE\001\007=e\0264\027N\\3nK:$h\bE\003)\001Ii\002%D\001\003!\021A#FE\017\n\005-\022!aA'baB!\001#L\0303\023\tqCA\001\bQCJ\fG\016\\3mSj\f'\r\\3\021\t1\001$#H\005\003c\031\021a\001V;qY\026\024\004\003B\0328%ui\021\001\016\006\003\007UR!A\016\003\002\021A\f'/\0317mK2L!\001\017\033\003\rA\013'/T1q\021\025Q\004\001\"\001<\003\031!\023N\\5uIQ\tA\b\005\002\r{%\021aH\002\002\005+:LG\017\003\004A\001\001&\t&Q\001\fa\006\0248i\\7cS:,'/F\001C!\021\031Ei\f\032\016\003UJ!!R\033\003\021\r{WNY5oKJDQa\022\001\005B!\013q!\0369eCR,G-\006\002J\031R\031!jT)\021\t!R#c\023\t\003'1#Q!\024$C\0029\023!AQ\031\022\005uQ\002\"\002)G\001\004\021\022aA6fs\")!K\022a\001\027\006)a/\0317vK\")A\013\001D\001+\006)A\005\0357vgV\021a+\027\013\003/j\003B\001\013\026\0231B\0211#\027\003\006\033N\023\rA\024\005\0067N\003\r\001X\001\003WZ\004B\001\004\031\0231\")A\013\001C!=V\021qL\031\013\005A\0164\007\016\005\003)UI\t\007CA\nc\t\025iUL1\001O\021\025!W\f1\001f\003\025)G.Z72!\021a\001GE1\t\013\035l\006\031A3\002\013\025dW-\034\032\t\013%l\006\031\0016\002\013\025dW-\\:\021\0071YW-\003\002m\r\tQAH]3qK\006$X\r\032 \t\0139\004A\021I8\002\025\021\002H.^:%a2,8/\006\002qgR\021\021\017\036\t\005Q)\022\"\017\005\002\024g\022)Q*\034b\001\035\")Q/\034a\001m\006\021\001p\035\t\004!]L\030B\001=\005\005I9UM\034+sCZ,'o]1cY\026|enY3\021\t1\001$C\035\005\006w\002!\t\005`\001\013M&dG/\032:LKf\034HCA\025~\021\025q(\0201\001\000\003\005\001\bC\002\007\002\002I\t)!C\002\002\004\031\021\021BR;oGRLwN\\\031\021\0071\t9!C\002\002\n\031\021qAQ8pY\026\fg\016C\004\002\016\001!\t%a\004\002\0235\f\007OV1mk\026\034X\003BA\t\003/!B!a\005\002\034A)\001F\013\n\002\026A\0311#a\006\005\017\005e\0211\002b\001-\t\t1\t\003\005\002\036\005-\001\031AA\020\003\0051\007C\002\007\002\002u\t)\002C\004\002$\001!\t%!\n\002\r-,\027pU3u+\t\t9\003\005\003)\003S\021\022bAA\026\005\t\0311+\032;\007\r\005=\002\001CA\031\005YIU.\\;uC\ndW\rR3gCVdGoS3z'\026$8CBA\027\003g\t9\003\005\003\0026\005]R\"\001\001\n\007\005e\022CA\007EK\032\fW\017\034;LKf\034V\r\036\005\t\003{\ti\003\"\001\002@\0051A(\0338jiz\"\"!!\021\021\t\005U\022Q\006\005\b)\0065B\021IA#)\021\t9#a\022\t\017\005%\0231\ta\001%\005!Q\r\\3n\021!\ti%!\f\005B\005=\023A\002\023nS:,8\017\006\003\002(\005E\003bBA%\003\027\002\rA\005\005\b\003+\002A\021AA,\003%!(/\0318tM>\024X.\006\004\002Z\005]\024q\f\013\005\0037\nI\b\006\003\002^\005\r\004cA\n\002`\0219\021\021MA*\005\0041\"\001\002+iCRD\001\"!\032\002T\001\017\021qM\001\003E\032\004\022\"!\033\002p\001\n\031(!\030\016\005\005-$bAA7\t\0059q-\0328fe&\034\027\002BA9\003W\022AbQ1o\005VLG\016\032$s_6\004R\001\004\031\023\003k\0022aEA<\t\035\tI\"a\025C\002YA\001\"!\b\002T\001\007\0211\020\t\b\031\005u$#HA;\023\r\tyH\002\002\n\rVt7\r^5p]J\002")
/*     */ public interface MapLike<A, B, This extends MapLike<A, B, This> & Map<A, B>> extends MapLike<A, B, This>, Parallelizable<Tuple2<A, B>, ParMap<A, B>> {
/*     */   Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner();
/*     */   
/*     */   <B1> Map<A, B1> updated(A paramA, B1 paramB1);
/*     */   
/*     */   <B1> Map<A, B1> $plus(Tuple2<A, B1> paramTuple2);
/*     */   
/*     */   <B1> Map<A, B1> $plus(Tuple2<A, B1> paramTuple21, Tuple2<A, B1> paramTuple22, Seq<Tuple2<A, B1>> paramSeq);
/*     */   
/*     */   <B1> Map<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> paramGenTraversableOnce);
/*     */   
/*     */   Map<A, B> filterKeys(Function1<A, Object> paramFunction1);
/*     */   
/*     */   <C> Map<A, C> mapValues(Function1<B, C> paramFunction1);
/*     */   
/*     */   Set<A> keySet();
/*     */   
/*     */   <C, That> That transform(Function2<A, B, C> paramFunction2, CanBuildFrom<This, Tuple2<A, C>, That> paramCanBuildFrom);
/*     */   
/*     */   public class MapLike$$anonfun$$plus$plus$1 extends AbstractFunction2<Map<A, B1>, Tuple2<A, B1>, Map<A, B1>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Map<A, B1> apply(Map x$2, Tuple2 x$3) {
/*  87 */       return x$2.$plus(x$3);
/*     */     }
/*     */     
/*     */     public MapLike$$anonfun$$plus$plus$1(MapLike $outer) {}
/*     */   }
/*     */   
/*     */   public class MapLike$$anon$1 extends MapLike<A, B, This>.FilteredKeys implements DefaultMap<A, B> {
/*     */     public <B1> Map<A, B1> $plus(Tuple2 kv) {
/*  94 */       return DefaultMap$class.$plus(this, kv);
/*     */     }
/*     */     
/*     */     public Map<A, B> $minus(Object key) {
/*  94 */       return DefaultMap$class.$minus(this, key);
/*     */     }
/*     */     
/*     */     public Map<A, B> empty() {
/*  94 */       return Map$class.empty(this);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  94 */       return Map$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public Map<A, B> seq() {
/*  94 */       return Map$class.seq(this);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> withDefault(Function1 d) {
/*  94 */       return Map$class.withDefault(this, d);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> withDefaultValue(Object d) {
/*  94 */       return Map$class.withDefaultValue(this, d);
/*     */     }
/*     */     
/*     */     public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
/*  94 */       return MapLike$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> updated(Object key, Object value) {
/*  94 */       return MapLike$class.updated(this, key, value);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> $plus(Tuple2 elem1, Tuple2 elem2, Seq elems) {
/*  94 */       return MapLike$class.$plus(this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> $plus$plus(GenTraversableOnce xs) {
/*  94 */       return MapLike$class.$plus$plus(this, xs);
/*     */     }
/*     */     
/*     */     public Map<A, B> filterKeys(Function1 p) {
/*  94 */       return MapLike$class.filterKeys(this, p);
/*     */     }
/*     */     
/*     */     public <C> Map<A, C> mapValues(Function1 f) {
/*  94 */       return MapLike$class.mapValues(this, f);
/*     */     }
/*     */     
/*     */     public Set<A> keySet() {
/*  94 */       return MapLike$class.keySet(this);
/*     */     }
/*     */     
/*     */     public <C, That> That transform(Function2 f, CanBuildFrom bf) {
/*  94 */       return (That)MapLike$class.transform(this, f, bf);
/*     */     }
/*     */     
/*     */     public GenericCompanion<Iterable> companion() {
/*  94 */       return Iterable$class.companion(this);
/*     */     }
/*     */     
/*     */     public MapLike$$anon$1(MapLike $outer, Function1 p$1) {
/*  94 */       super($outer, p$1);
/*  94 */       Traversable$class.$init$(this);
/*  94 */       Iterable$class.$init$(this);
/*  94 */       MapLike$class.$init$(this);
/*  94 */       Map$class.$init$(this);
/*  94 */       DefaultMap$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class MapLike$$anon$2 extends MapLike<A, B, This>.MappedValues<C> implements DefaultMap<A, C> {
/*     */     public <B1> Map<A, B1> $plus(Tuple2 kv) {
/* 101 */       return DefaultMap$class.$plus(this, kv);
/*     */     }
/*     */     
/*     */     public Map<A, C> $minus(Object key) {
/* 101 */       return DefaultMap$class.$minus(this, key);
/*     */     }
/*     */     
/*     */     public Map<A, C> empty() {
/* 101 */       return Map$class.empty(this);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 101 */       return Map$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public Map<A, C> seq() {
/* 101 */       return Map$class.seq(this);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> withDefault(Function1 d) {
/* 101 */       return Map$class.withDefault(this, d);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> withDefaultValue(Object d) {
/* 101 */       return Map$class.withDefaultValue(this, d);
/*     */     }
/*     */     
/*     */     public Combiner<Tuple2<A, C>, ParMap<A, C>> parCombiner() {
/* 101 */       return MapLike$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> updated(Object key, Object value) {
/* 101 */       return MapLike$class.updated(this, key, value);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> $plus(Tuple2 elem1, Tuple2 elem2, Seq elems) {
/* 101 */       return MapLike$class.$plus(this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> $plus$plus(GenTraversableOnce xs) {
/* 101 */       return MapLike$class.$plus$plus(this, xs);
/*     */     }
/*     */     
/*     */     public Map<A, C> filterKeys(Function1 p) {
/* 101 */       return MapLike$class.filterKeys(this, p);
/*     */     }
/*     */     
/*     */     public <C> Map<A, C> mapValues(Function1 f) {
/* 101 */       return MapLike$class.mapValues(this, f);
/*     */     }
/*     */     
/*     */     public Set<A> keySet() {
/* 101 */       return MapLike$class.keySet(this);
/*     */     }
/*     */     
/*     */     public <C, That> That transform(Function2 f, CanBuildFrom bf) {
/* 101 */       return (That)MapLike$class.transform(this, f, bf);
/*     */     }
/*     */     
/*     */     public GenericCompanion<Iterable> companion() {
/* 101 */       return Iterable$class.companion(this);
/*     */     }
/*     */     
/*     */     public MapLike$$anon$2(MapLike $outer, Function1 f$1) {
/* 101 */       super($outer, f$1);
/* 101 */       Traversable$class.$init$(this);
/* 101 */       Iterable$class.$init$(this);
/* 101 */       MapLike$class.$init$(this);
/* 101 */       Map$class.$init$(this);
/* 101 */       DefaultMap$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class ImmutableDefaultKeySet extends MapLike<A, B, This>.DefaultKeySet implements Set<A> {
/*     */     public GenericCompanion<Set> companion() {
/* 108 */       return Set$class.companion(this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 108 */       return Set$class.toSet(this);
/*     */     }
/*     */     
/*     */     public Set<A> seq() {
/* 108 */       return Set$class.seq(this);
/*     */     }
/*     */     
/*     */     public Combiner<A, ParSet<A>> parCombiner() {
/* 108 */       return Set$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public ImmutableDefaultKeySet(MapLike $outer) {
/* 108 */       super($outer);
/* 108 */       Traversable$class.$init$(this);
/* 108 */       Iterable$class.$init$(this);
/* 108 */       Set$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Set<A> $plus(Object elem) {
/* 110 */       return apply(elem) ? this : 
/* 111 */         (Set<A>)((SetLike)Set$.MODULE$.apply(Nil$.MODULE$)).$plus$plus((GenTraversableOnce)this).$plus(elem);
/*     */     }
/*     */     
/*     */     public Set<A> $minus(Object elem) {
/* 113 */       return apply(elem) ? (Set<A>)((SetLike)Set$.MODULE$.apply(Nil$.MODULE$)).$plus$plus((GenTraversableOnce)this).$minus(elem) : 
/* 114 */         this;
/*     */     }
/*     */   }
/*     */   
/*     */   public class MapLike$$anonfun$transform$1 extends AbstractFunction1<Tuple2<A, B>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Tuple2 check$ifrefutable$1) {
/*     */       boolean bool;
/* 125 */       if (check$ifrefutable$1 != null) {
/* 125 */         bool = true;
/*     */       } else {
/* 125 */         bool = false;
/*     */       } 
/* 125 */       return bool;
/*     */     }
/*     */     
/*     */     public MapLike$$anonfun$transform$1(MapLike $outer) {}
/*     */   }
/*     */   
/*     */   public class MapLike$$anonfun$transform$2 extends AbstractFunction1<Tuple2<A, B>, Builder<Tuple2<A, C>, That>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$1;
/*     */     
/*     */     private final Function2 f$2;
/*     */     
/*     */     public final Builder<Tuple2<A, C>, That> apply(Tuple2 x$4) {
/* 125 */       if (x$4 != null)
/* 125 */         return this.b$1.$plus$eq(new Tuple2(x$4._1(), this.f$2.apply(x$4._1(), x$4._2()))); 
/* 125 */       throw new MatchError(x$4);
/*     */     }
/*     */     
/*     */     public MapLike$$anonfun$transform$2(MapLike $outer, Builder b$1, Function2 f$2) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\MapLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */