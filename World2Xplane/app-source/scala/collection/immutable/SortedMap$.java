/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
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
/*     */ import scala.collection.generic.ImmutableSortedMapFactory;
/*     */ import scala.collection.generic.Sorted;
/*     */ import scala.collection.generic.SortedMapFactory;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParMap;
/*     */ import scala.math.Ordering;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ 
/*     */ public final class SortedMap$ extends ImmutableSortedMapFactory<SortedMap> {
/*     */   public static final SortedMap$ MODULE$;
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
/*     */   public class SortedMap$$anon$1 extends MapLike<A, B, SortedMap<A, B>>.FilteredKeys implements SortedMap.Default<A, B> {
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
/*     */     public <T, U> Map<T, U> toMap(scala.Predef$.less.colon.less ev) {
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
/*     */   public class SortedMap$$anon$2 extends MapLike<A, B, SortedMap<A, B>>.MappedValues<C> implements SortedMap.Default<A, C> {
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
/*     */     public <T, U> Map<T, U> toMap(scala.Predef$.less.colon.less ev) {
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
/*     */   private SortedMap$() {
/*  98 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A, B> CanBuildFrom<SortedMap<?, ?>, Tuple2<A, B>, SortedMap<A, B>> canBuildFrom(Ordering ord) {
/* 100 */     return (CanBuildFrom<SortedMap<?, ?>, Tuple2<A, B>, SortedMap<A, B>>)new SortedMapFactory.SortedMapCanBuildFrom((SortedMapFactory)this, ord);
/*     */   }
/*     */   
/*     */   public <A, B> SortedMap<A, B> empty(Ordering<A> ord) {
/* 101 */     return TreeMap$.MODULE$.empty(ord);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\SortedMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */