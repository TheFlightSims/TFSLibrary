/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
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
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.MapLike;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.SortedMap;
/*     */ import scala.collection.SortedMapLike;
/*     */ import scala.collection.SortedSet;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.Sorted;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParMap;
/*     */ import scala.collection.parallel.immutable.ParSet;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t\035aaB\001\003!\003\r\t!\003\002\n'>\024H/\0323NCBT!a\001\003\002\023%lW.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001Qc\001\006\026?M1\001aC\b\"I!\002\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\021\001\022c\005\020\016\003\tI!A\005\002\003\0075\013\007\017\005\002\025+1\001A!\002\f\001\005\0049\"!A!\022\005aY\002C\001\007\032\023\tQbAA\004O_RD\027N\\4\021\0051a\022BA\017\007\005\r\te.\037\t\003)}!a\001\t\001\005\006\0049\"!\001\"\021\t\t\0323CH\007\002\t%\021\021\001\002\t\006!\025\032bdJ\005\003M\t\021q!T1q\031&\\W\r\005\003\021\001Mq\002#\002\022*'y9\023B\001\026\005\0055\031vN\035;fI6\013\007\017T5lK\")A\006\001C\001[\0051A%\0338ji\022\"\022A\f\t\003\031=J!\001\r\004\003\tUs\027\016\036\005\007e\001\001K\021K\032\002\0259,wOQ;jY\022,'/F\0015!\021)\004HO\024\016\003YR!a\016\003\002\0175,H/\0312mK&\021\021H\016\002\b\005VLG\016Z3s!\021a1h\005\020\n\005q2!A\002+va2,'\007C\003?\001\021\005s(A\003f[B$\0300F\001(\021\025\t\005\001\"\021C\003\035)\b\017Z1uK\022,\"a\021$\025\007\021K5\n\005\003\021\001M)\005C\001\013G\t\0259\005I1\001I\005\t\021\025'\005\002\0377!)!\n\021a\001'\005\0311.Z=\t\0131\003\005\031A#\002\013Y\fG.^3\t\0139\003A\021I(\002\r-,\027pU3u+\005\001\006c\001\tR'%\021!K\001\002\n'>\024H/\0323TKR4A\001\026\001\t+\n\031B)\0324bk2$8*Z=T_J$X\rZ*fiN\0311K\026)\021\005]CV\"\001\001\n\005QK\003\"\002.T\t\003Y\026A\002\037j]&$h\bF\001]!\t96\013C\003_'\022\005s,A\003%a2,8\017\006\002QA\")\021-\030a\001'\005!Q\r\\3n\021\025\0317\013\"\021e\003\031!S.\0338vgR\021\001+\032\005\006C\n\004\ra\005\005\006ON#\t\005[\001\ne\006tw-Z%na2$2\001U5o\021\025Qg\r1\001l\003\0211'o\\7\021\0071a7#\003\002n\r\t1q\n\035;j_:DQa\0344A\002-\fQ!\0368uS2DQA\030\001\005\002E,\"A];\025\005M4\b\003\002\t\001'Q\004\"\001F;\005\013\035\003(\031\001%\t\013]\004\b\031\001=\002\005-4\b\003\002\007<'QDQA\030\001\005Bi,\"a\037@\025\rq|\030QAA\005!\021\001\002aE?\021\005QqH!B$z\005\004A\005bBA\001s\002\007\0211A\001\006K2,W.\r\t\005\031m\032R\020C\004\002\be\004\r!a\001\002\013\025dW-\034\032\t\017\005-\021\0201\001\002\016\005)Q\r\\3ngB)A\"a\004\002\004%\031\021\021\003\004\003\025q\022X\r]3bi\026$g\bC\004\002\026\001!\t%a\006\002\025\021\002H.^:%a2,8/\006\003\002\032\005}A\003BA\016\003C\001R\001\005\001\024\003;\0012\001FA\020\t\0319\0251\003b\001\021\"A\0211EA\n\001\004\t)#\001\002ygB)!%a\n\002,%\031\021\021\006\003\003%\035+g\016\026:bm\026\0248/\0312mK>s7-\032\t\006\031m\032\022Q\004\005\b\003_\001A\021IA\031\003)1\027\016\034;fe.+\027p\035\013\004O\005M\002\002CA\033\003[\001\r!a\016\002\003A\004b\001DA\035'\005u\022bAA\036\r\tIa)\0368di&|g.\r\t\004\031\005}\022bAA!\r\t9!i\\8mK\006t\007bBA#\001\021\005\023qI\001\n[\006\004h+\0317vKN,B!!\023\002PQ!\0211JA*!\025\001\002aEA'!\r!\022q\n\003\b\003#\n\031E1\001\030\005\005\031\005\002CA+\003\007\002\r!a\026\002\003\031\004b\001DA\035=\0055saBA.\005!\005\021QL\001\n'>\024H/\0323NCB\0042\001EA0\r\031\t!\001#\001\002bM!\021qLA2!\031\t)'a\033\002p5\021\021q\r\006\004\003S\"\021aB4f]\026\024\030nY\005\005\003[\n9GA\rJ[6,H/\0312mKN{'\017^3e\033\006\004h)Y2u_JL\bC\001\t\001\021\035Q\026q\fC\001\003g\"\"!!\030\t\021\005]\024q\fC\002\003s\nAbY1o\005VLG\016\032$s_6,b!a\037\002\024\006]E\003BA?\0037\003\"\"!\032\002\000\005\r\025qRAM\023\021\t\t)a\032\003\031\r\013gNQ;jY\0224%o\\7\021\t\005\025\025qQ\007\003\003?JA!!#\002\f\n!1i\0347m\023\021\ti)a\032\003!M{'\017^3e\033\006\004h)Y2u_JL\bC\002\007<\003#\013)\nE\002\025\003'#aAFA;\005\0049\002c\001\013\002\030\0221\001%!\036C\002]\001b\001\005\001\002\022\006U\005\002CAO\003k\002\035!a(\002\007=\024H\r\005\004\002\"\006E\026\021\023\b\005\003G\013iK\004\003\002&\006-VBAAT\025\r\tI\013C\001\007yI|w\016\036 \n\003\035I1!a,\007\003\035\001\030mY6bO\026LA!a-\0026\nAqJ\0353fe&twMC\002\0020\032AqAPA0\t\003\tI,\006\004\002<\006\005\027Q\031\013\005\003{\0139\r\005\004\021\001\005}\0261\031\t\004)\005\005GA\002\f\0028\n\007q\003E\002\025\003\013$a\001IA\\\005\0049\002\002CAO\003o\003\035!!3\021\r\005\005\026\021WA`\r-\ti-a\030\021\002\007\005A!a4\003\017\021+g-Y;miV1\021\021[Al\0037\034r!a3\f\003'\fi\016\005\004\021\001\005U\027\021\034\t\004)\005]GA\002\f\002L\n\007q\003E\002\025\0037$q\001IAf\t\013\007q\003\005\005\002`\006\035\030Q[Am\035\021\t\t/!:\017\t\005\r\0261]\005\003\013\031I1!a\027\005\023\021\ti-!;\013\007\005mC\001\003\004-\003\027$\t!\f\005\b=\006-G\021IAx+\021\t\t0a>\025\t\005M\0301 \t\007!\001\t).!>\021\007Q\t9\020B\004H\003[\024\r!!?\022\007\005e7\004C\004x\003[\004\r!!@\021\r1Y\024Q[A{\021\035\031\0271\032C!\005\003!B!a5\003\004!9!*a@A\002\005U\007\003CAC\003\027\f).!7")
/*     */ public interface SortedMap<A, B> extends Map<A, B>, SortedMap<A, B>, MapLike<A, B, SortedMap<A, B>>, SortedMapLike<A, B, SortedMap<A, B>> {
/*     */   Builder<Tuple2<A, B>, SortedMap<A, B>> newBuilder();
/*     */   
/*     */   SortedMap<A, B> empty();
/*     */   
/*     */   <B1> SortedMap<A, B1> updated(A paramA, B1 paramB1);
/*     */   
/*     */   SortedSet<A> keySet();
/*     */   
/*     */   <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> paramTuple2);
/*     */   
/*     */   <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> paramTuple21, Tuple2<A, B1> paramTuple22, Seq<Tuple2<A, B1>> paramSeq);
/*     */   
/*     */   <B1> SortedMap<A, B1> $plus$plus(GenTraversableOnce<Tuple2<A, B1>> paramGenTraversableOnce);
/*     */   
/*     */   SortedMap<A, B> filterKeys(Function1<A, Object> paramFunction1);
/*     */   
/*     */   <C> SortedMap<A, C> mapValues(Function1<B, C> paramFunction1);
/*     */   
/*     */   public class DefaultKeySortedSet extends SortedMapLike<A, B, SortedMap<A, B>>.DefaultKeySortedSet implements SortedSet<A> {
/*     */     public SortedSet<A> empty() {
/*  44 */       return SortedSet$class.empty(this);
/*     */     }
/*     */     
/*     */     public GenericCompanion<Set> companion() {
/*  44 */       return Set$class.companion(this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*  44 */       return Set$class.toSet(this);
/*     */     }
/*     */     
/*     */     public Set<A> seq() {
/*  44 */       return Set$class.seq(this);
/*     */     }
/*     */     
/*     */     public Combiner<A, ParSet<A>> parCombiner() {
/*  44 */       return Set$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public DefaultKeySortedSet(SortedMap $outer) {
/*  44 */       super($outer);
/*  44 */       Traversable$class.$init$(this);
/*  44 */       Iterable$class.$init$(this);
/*  44 */       Set$class.$init$(this);
/*  44 */       SortedSet$class.$init$(this);
/*     */     }
/*     */     
/*     */     public SortedSet<A> $plus(Object elem) {
/*  46 */       return apply(elem) ? this : 
/*  47 */         (SortedSet<A>)SortedSet$.MODULE$.apply(Nil$.MODULE$, ordering()).$plus$plus((GenTraversableOnce)this).$plus(elem);
/*     */     }
/*     */     
/*     */     public SortedSet<A> $minus(Object elem) {
/*  49 */       return apply(elem) ? (SortedSet<A>)SortedSet$.MODULE$.apply(Nil$.MODULE$, ordering()).$plus$plus((GenTraversableOnce)this).$minus(elem) : 
/*  50 */         this;
/*     */     }
/*     */     
/*     */     public SortedSet<A> rangeImpl(Option from, Option until) {
/*  52 */       SortedMap<A, B> map = (SortedMap)scala$collection$immutable$SortedMap$DefaultKeySortedSet$$$outer().rangeImpl(from, until);
/*  53 */       return new DefaultKeySortedSet(map);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SortedMap$$anonfun$$plus$plus$1 extends AbstractFunction2<SortedMap<A, B1>, Tuple2<A, B1>, SortedMap<A, B1>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final SortedMap<A, B1> apply(SortedMap x$2, Tuple2 x$3) {
/*  80 */       return x$2.$plus(x$3);
/*     */     }
/*     */     
/*     */     public SortedMap$$anonfun$$plus$plus$1(SortedMap $outer) {}
/*     */   }
/*     */   
/*     */   public class SortedMap$$anon$1 extends MapLike<A, B, SortedMap<A, B>>.FilteredKeys implements Default<A, B> {
/*     */     private final Function1 p$1;
/*     */     
/*     */     public <B1> SortedMap<A, B1> $plus(Tuple2 kv) {
/*  82 */       return SortedMap.Default$class.$plus(this, kv);
/*     */     }
/*     */     
/*     */     public SortedMap<A, B> $minus(Object key) {
/*  82 */       return SortedMap.Default$class.$minus(this, key);
/*     */     }
/*     */     
/*     */     public Builder<Tuple2<A, B>, SortedMap<A, B>> newBuilder() {
/*  82 */       return SortedMap$class.newBuilder(this);
/*     */     }
/*     */     
/*     */     public SortedMap<A, B> empty() {
/*  82 */       return SortedMap$class.empty(this);
/*     */     }
/*     */     
/*     */     public <B1> SortedMap<A, B1> updated(Object key, Object value) {
/*  82 */       return SortedMap$class.updated(this, key, value);
/*     */     }
/*     */     
/*     */     public SortedSet<A> keySet() {
/*  82 */       return SortedMap$class.keySet(this);
/*     */     }
/*     */     
/*     */     public <B1> SortedMap<A, B1> $plus(Tuple2 elem1, Tuple2 elem2, Seq elems) {
/*  82 */       return SortedMap$class.$plus(this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public <B1> SortedMap<A, B1> $plus$plus(GenTraversableOnce xs) {
/*  82 */       return SortedMap$class.$plus$plus(this, xs);
/*     */     }
/*     */     
/*     */     public SortedMap<A, B> filterKeys(Function1 p) {
/*  82 */       return SortedMap$class.filterKeys(this, p);
/*     */     }
/*     */     
/*     */     public <C> SortedMap<A, C> mapValues(Function1 f) {
/*  82 */       return SortedMap$class.mapValues(this, f);
/*     */     }
/*     */     
/*     */     public A firstKey() {
/*  82 */       return (A)SortedMapLike.class.firstKey(this);
/*     */     }
/*     */     
/*     */     public A lastKey() {
/*  82 */       return (A)SortedMapLike.class.lastKey(this);
/*     */     }
/*     */     
/*     */     public int compare(Object k0, Object k1) {
/*  82 */       return Sorted.class.compare((Sorted)this, k0, k1);
/*     */     }
/*     */     
/*     */     public SortedMap<A, B> from(Object from) {
/*  82 */       return (SortedMap<A, B>)Sorted.class.from((Sorted)this, from);
/*     */     }
/*     */     
/*     */     public SortedMap<A, B> until(Object until) {
/*  82 */       return (SortedMap<A, B>)Sorted.class.until((Sorted)this, until);
/*     */     }
/*     */     
/*     */     public SortedMap<A, B> range(Object from, Object until) {
/*  82 */       return (SortedMap<A, B>)Sorted.class.range((Sorted)this, from, until);
/*     */     }
/*     */     
/*     */     public SortedMap<A, B> to(Object to) {
/*  82 */       return (SortedMap<A, B>)Sorted.class.to((Sorted)this, to);
/*     */     }
/*     */     
/*     */     public boolean hasAll(Iterator j) {
/*  82 */       return Sorted.class.hasAll((Sorted)this, j);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  82 */       return Map$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public Map<A, B> seq() {
/*  82 */       return Map$class.seq(this);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> withDefault(Function1 d) {
/*  82 */       return Map$class.withDefault(this, d);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> withDefaultValue(Object d) {
/*  82 */       return Map$class.withDefaultValue(this, d);
/*     */     }
/*     */     
/*     */     public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
/*  82 */       return MapLike$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public <C, That> That transform(Function2 f, CanBuildFrom bf) {
/*  82 */       return (That)MapLike$class.transform(this, f, bf);
/*     */     }
/*     */     
/*     */     public GenericCompanion<Iterable> companion() {
/*  82 */       return Iterable$class.companion(this);
/*     */     }
/*     */     
/*     */     public SortedMap$$anon$1(SortedMap $outer, Function1 p$1) {
/*  82 */       super($outer, p$1);
/*  82 */       Traversable$class.$init$(this);
/*  82 */       Iterable$class.$init$(this);
/*  82 */       MapLike$class.$init$(this);
/*  82 */       Map$class.$init$(this);
/*  82 */       Sorted.class.$init$((Sorted)this);
/*  82 */       SortedMapLike.class.$init$(this);
/*  82 */       SortedMap.class.$init$(this);
/*  82 */       SortedMap$class.$init$(this);
/*  82 */       SortedMap.Default.class.$init$(this);
/*  82 */       SortedMap.Default$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Ordering<A> ordering() {
/*  83 */       return this.$outer.ordering();
/*     */     }
/*     */     
/*     */     public SortedMap<A, B> rangeImpl(Option from, Option until) {
/*  84 */       return ((SortedMap<A, B>)this.$outer.rangeImpl(from, until)).filterKeys(this.p$1);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SortedMap$$anon$2 extends MapLike<A, B, SortedMap<A, B>>.MappedValues<C> implements Default<A, C> {
/*     */     private final Function1 f$1;
/*     */     
/*     */     public <B1> SortedMap<A, B1> $plus(Tuple2 kv) {
/*  87 */       return SortedMap.Default$class.$plus(this, kv);
/*     */     }
/*     */     
/*     */     public SortedMap<A, C> $minus(Object key) {
/*  87 */       return SortedMap.Default$class.$minus(this, key);
/*     */     }
/*     */     
/*     */     public Builder<Tuple2<A, C>, SortedMap<A, C>> newBuilder() {
/*  87 */       return SortedMap$class.newBuilder(this);
/*     */     }
/*     */     
/*     */     public SortedMap<A, C> empty() {
/*  87 */       return SortedMap$class.empty(this);
/*     */     }
/*     */     
/*     */     public <B1> SortedMap<A, B1> updated(Object key, Object value) {
/*  87 */       return SortedMap$class.updated(this, key, value);
/*     */     }
/*     */     
/*     */     public SortedSet<A> keySet() {
/*  87 */       return SortedMap$class.keySet(this);
/*     */     }
/*     */     
/*     */     public <B1> SortedMap<A, B1> $plus(Tuple2 elem1, Tuple2 elem2, Seq elems) {
/*  87 */       return SortedMap$class.$plus(this, elem1, elem2, elems);
/*     */     }
/*     */     
/*     */     public <B1> SortedMap<A, B1> $plus$plus(GenTraversableOnce xs) {
/*  87 */       return SortedMap$class.$plus$plus(this, xs);
/*     */     }
/*     */     
/*     */     public SortedMap<A, C> filterKeys(Function1 p) {
/*  87 */       return SortedMap$class.filterKeys(this, p);
/*     */     }
/*     */     
/*     */     public <C> SortedMap<A, C> mapValues(Function1 f) {
/*  87 */       return SortedMap$class.mapValues(this, f);
/*     */     }
/*     */     
/*     */     public A firstKey() {
/*  87 */       return (A)SortedMapLike.class.firstKey(this);
/*     */     }
/*     */     
/*     */     public A lastKey() {
/*  87 */       return (A)SortedMapLike.class.lastKey(this);
/*     */     }
/*     */     
/*     */     public int compare(Object k0, Object k1) {
/*  87 */       return Sorted.class.compare((Sorted)this, k0, k1);
/*     */     }
/*     */     
/*     */     public SortedMap<A, C> from(Object from) {
/*  87 */       return (SortedMap<A, C>)Sorted.class.from((Sorted)this, from);
/*     */     }
/*     */     
/*     */     public SortedMap<A, C> until(Object until) {
/*  87 */       return (SortedMap<A, C>)Sorted.class.until((Sorted)this, until);
/*     */     }
/*     */     
/*     */     public SortedMap<A, C> range(Object from, Object until) {
/*  87 */       return (SortedMap<A, C>)Sorted.class.range((Sorted)this, from, until);
/*     */     }
/*     */     
/*     */     public SortedMap<A, C> to(Object to) {
/*  87 */       return (SortedMap<A, C>)Sorted.class.to((Sorted)this, to);
/*     */     }
/*     */     
/*     */     public boolean hasAll(Iterator j) {
/*  87 */       return Sorted.class.hasAll((Sorted)this, j);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  87 */       return Map$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public Map<A, C> seq() {
/*  87 */       return Map$class.seq(this);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> withDefault(Function1 d) {
/*  87 */       return Map$class.withDefault(this, d);
/*     */     }
/*     */     
/*     */     public <B1> Map<A, B1> withDefaultValue(Object d) {
/*  87 */       return Map$class.withDefaultValue(this, d);
/*     */     }
/*     */     
/*     */     public Combiner<Tuple2<A, C>, ParMap<A, C>> parCombiner() {
/*  87 */       return MapLike$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public <C, That> That transform(Function2 f, CanBuildFrom bf) {
/*  87 */       return (That)MapLike$class.transform(this, f, bf);
/*     */     }
/*     */     
/*     */     public GenericCompanion<Iterable> companion() {
/*  87 */       return Iterable$class.companion(this);
/*     */     }
/*     */     
/*     */     public SortedMap$$anon$2(SortedMap $outer, Function1 f$1) {
/*  87 */       super($outer, f$1);
/*  87 */       Traversable$class.$init$(this);
/*  87 */       Iterable$class.$init$(this);
/*  87 */       MapLike$class.$init$(this);
/*  87 */       Map$class.$init$(this);
/*  87 */       Sorted.class.$init$((Sorted)this);
/*  87 */       SortedMapLike.class.$init$(this);
/*  87 */       SortedMap.class.$init$(this);
/*  87 */       SortedMap$class.$init$(this);
/*  87 */       SortedMap.Default.class.$init$(this);
/*  87 */       SortedMap.Default$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Ordering<A> ordering() {
/*  88 */       return this.$outer.ordering();
/*     */     }
/*     */     
/*     */     public SortedMap<A, C> rangeImpl(Option from, Option until) {
/*  89 */       return ((SortedMap)this.$outer.rangeImpl(from, until)).mapValues(this.f$1);
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class Default$class {
/*     */     public static void $init$(SortedMap.Default $this) {}
/*     */     
/*     */     public static SortedMap $plus(SortedMap.Default $this, Tuple2 kv) {
/* 106 */       Builder b = SortedMap$.MODULE$.newBuilder($this.ordering());
/* 107 */       b.$plus$plus$eq((TraversableOnce)$this);
/* 108 */       b.$plus$eq(new Tuple2(kv._1(), kv._2()));
/* 109 */       return (SortedMap)b.result();
/*     */     }
/*     */     
/*     */     public static SortedMap $minus(SortedMap.Default $this, Object key) {
/* 113 */       Builder b = $this.newBuilder();
/* 114 */       $this.withFilter((Function1)new SortedMap$Default$$anonfun$$minus$1($this, (SortedMap.Default<A, B>)key)).foreach((Function1)new SortedMap$Default$$anonfun$$minus$2($this, (SortedMap.Default<A, B>)b));
/* 115 */       return (SortedMap)b.result();
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface Default<A, B> extends SortedMap<A, B>, SortedMap.Default<A, B> {
/*     */     <B1> SortedMap<A, B1> $plus(Tuple2<A, B1> param1Tuple2);
/*     */     
/*     */     SortedMap<A, B> $minus(A param1A);
/*     */     
/*     */     public class SortedMap$Default$$anonfun$$minus$1 extends AbstractFunction1<Tuple2<A, B>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object key$1;
/*     */       
/*     */       public final boolean apply(Tuple2 kv) {
/*     */         Object object2 = this.key$1;
/*     */         Object object1;
/*     */         return !(((object1 = kv._1()) == object2) ? true : ((object1 == null) ? false : ((object1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object1, object2) : ((object1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object1, object2) : object1.equals(object2)))));
/*     */       }
/*     */       
/*     */       public SortedMap$Default$$anonfun$$minus$1(SortedMap.Default $outer, Object key$1) {}
/*     */     }
/*     */     
/*     */     public class SortedMap$Default$$anonfun$$minus$2 extends AbstractFunction1<Tuple2<A, B>, Builder<Tuple2<A, B>, SortedMap<A, B>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Builder b$1;
/*     */       
/*     */       public final Builder<Tuple2<A, B>, SortedMap<A, B>> apply(Tuple2 kv) {
/*     */         return this.b$1.$plus$eq(kv);
/*     */       }
/*     */       
/*     */       public SortedMap$Default$$anonfun$$minus$2(SortedMap.Default $outer, Builder b$1) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\SortedMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */