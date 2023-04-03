/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.FilterMonadic;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.generic.SliceInterval;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.ArraySeq;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.HashMap;
/*     */ import scala.collection.mutable.HashSet;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.ParSeq;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.ObjectRef;
/*     */ 
/*     */ public final class SeqLike$ {
/*     */   public static final SeqLike$ MODULE$;
/*     */   
/*     */   public class SeqLike$$anonfun$reverse$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectRef xs$1;
/*     */     
/*     */     public SeqLike$$anonfun$reverse$1(SeqLike $outer, ObjectRef xs$1) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 266 */       this.xs$1.elem = ((List)this.xs$1.elem).$colon$colon(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$reverseMap$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectRef xs$2;
/*     */     
/*     */     public SeqLike$$anonfun$reverseMap$1(SeqLike $outer, ObjectRef xs$2) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 277 */       this.xs$2.elem = ((List)this.xs$2.elem).$colon$colon(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$contains$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object elem$1;
/*     */     
/*     */     public final boolean apply(Object x$12) {
/* 393 */       Object object = this.elem$1;
/* 393 */       return ((x$12 == object) ? true : ((x$12 == null) ? false : ((x$12 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)x$12, object) : ((x$12 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)x$12, object) : x$12.equals(object)))));
/*     */     }
/*     */     
/*     */     public SeqLike$$anonfun$contains$1(SeqLike $outer, Object elem$1) {}
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$diff$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Map occ$1;
/*     */     
/*     */     private final Builder b$3;
/*     */     
/*     */     public SeqLike$$anonfun$diff$1(SeqLike $outer, Map occ$1, Builder b$3) {}
/*     */     
/*     */     public final Object apply(Object x) {
/* 444 */       this.occ$1.update(x, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.occ$1.apply(x)) - 1));
/* 444 */       return (BoxesRunTime.unboxToInt(this.occ$1.apply(x)) == 0) ? this.b$3.$plus$eq(x) : BoxedUnit.UNIT;
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$intersect$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Map occ$2;
/*     */     
/*     */     private final Builder b$4;
/*     */     
/*     */     public SeqLike$$anonfun$intersect$1(SeqLike $outer, Map occ$2, Builder b$4) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 472 */       if (BoxesRunTime.unboxToInt(this.occ$2.apply(x)) > 0) {
/* 473 */         this.b$4.$plus$eq(x);
/* 474 */         this.occ$2.update(x, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.occ$2.apply(x)) - 1));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqLike$$anon$1 extends HashMap<Object, Object> {
/*     */     public int default(Object k) {
/* 480 */       return 0;
/*     */     }
/*     */     
/*     */     public SeqLike$$anon$1(SeqLike $outer) {}
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$occCounts$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final HashMap occ$3;
/*     */     
/*     */     public final void apply(Object y) {
/* 481 */       this.occ$3.update(y, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.occ$3.apply(y)) + 1));
/*     */     }
/*     */     
/*     */     public SeqLike$$anonfun$occCounts$1(SeqLike $outer, HashMap occ$3) {}
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$distinct$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$5;
/*     */     
/*     */     private final HashSet seen$1;
/*     */     
/*     */     public SeqLike$$anonfun$distinct$1(SeqLike $outer, Builder b$5, HashSet seen$1) {}
/*     */     
/*     */     public final Object apply(Object x) {
/* 495 */       this.b$5.$plus$eq(x);
/* 496 */       return this.seen$1.apply(x) ? BoxedUnit.UNIT : this.seen$1.$plus$eq(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$sorted$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ArraySeq arr$1;
/*     */     
/*     */     private final IntRef i$1;
/*     */     
/*     */     public SeqLike$$anonfun$sorted$1(SeqLike $outer, ArraySeq arr$1, IntRef i$1) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 612 */       this.arr$1.update(this.i$1.elem, x);
/* 613 */       this.i$1.elem++;
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$sorted$2 extends AbstractFunction1<A, Builder<A, Repr>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$6;
/*     */     
/*     */     public final Builder<A, Repr> apply(Object x) {
/* 618 */       return this.b$6.$plus$eq(x);
/*     */     }
/*     */     
/*     */     public SeqLike$$anonfun$sorted$2(SeqLike $outer, Builder b$6) {}
/*     */   }
/*     */   
/*     */   public class SeqLike$$anon$2 implements SeqView<A, Repr> {
/*     */     private Repr underlying;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public <B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<B> newForced(Function0 xs) {
/* 635 */       return SeqViewLike$class.newForced(this, xs);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<B> newAppended(GenTraversable that) {
/* 635 */       return SeqViewLike$class.newAppended(this, that);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<B> newMapped(Function1 f) {
/* 635 */       return SeqViewLike$class.newMapped(this, f);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<B> newFlatMapped(Function1 f) {
/* 635 */       return SeqViewLike$class.newFlatMapped(this, f);
/*     */     }
/*     */     
/*     */     public SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A> newFiltered(Function1 p) {
/* 635 */       return SeqViewLike$class.newFiltered(this, p);
/*     */     }
/*     */     
/*     */     public SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A> newSliced(SliceInterval _endpoints) {
/* 635 */       return SeqViewLike$class.newSliced(this, _endpoints);
/*     */     }
/*     */     
/*     */     public SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A> newDroppedWhile(Function1 p) {
/* 635 */       return SeqViewLike$class.newDroppedWhile(this, p);
/*     */     }
/*     */     
/*     */     public SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A> newTakenWhile(Function1 p) {
/* 635 */       return SeqViewLike$class.newTakenWhile(this, p);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<Tuple2<A, B>> newZipped(GenIterable that) {
/* 635 */       return SeqViewLike$class.newZipped(this, that);
/*     */     }
/*     */     
/*     */     public <A1, B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<Tuple2<A1, B>> newZippedAll(GenIterable that, Object _thisElem, Object _thatElem) {
/* 635 */       return SeqViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
/*     */     }
/*     */     
/*     */     public SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A> newReversed() {
/* 635 */       return SeqViewLike$class.newReversed(this);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<B> newPatched(int _from, GenSeq _patch, int _replaced) {
/* 635 */       return SeqViewLike$class.newPatched(this, _from, _patch, _replaced);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<B> newPrepended(Object elem) {
/* 635 */       return SeqViewLike$class.newPrepended(this, elem);
/*     */     }
/*     */     
/*     */     public SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A> newTaken(int n) {
/* 635 */       return SeqViewLike$class.newTaken(this, n);
/*     */     }
/*     */     
/*     */     public SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A> newDropped(int n) {
/* 635 */       return SeqViewLike$class.newDropped(this, n);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> reverse() {
/* 635 */       return SeqViewLike$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <B, That> That patch(int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/* 635 */       return (That)SeqViewLike$class.patch(this, from, patch, replaced, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/* 635 */       return (That)SeqViewLike$class.padTo(this, len, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That reverseMap(Function1 f, CanBuildFrom bf) {
/* 635 */       return (That)SeqViewLike$class.reverseMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That updated(int index, Object elem, CanBuildFrom bf) {
/* 635 */       return (That)SeqViewLike$class.updated(this, index, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/* 635 */       return (That)SeqViewLike$class.$plus$colon(this, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/* 635 */       return (That)SeqViewLike$class.$colon$plus(this, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That union(GenSeq that, CanBuildFrom bf) {
/* 635 */       return (That)SeqViewLike$class.union(this, that, bf);
/*     */     }
/*     */     
/*     */     public <B> SeqView<A, Repr> diff(GenSeq that) {
/* 635 */       return SeqViewLike$class.diff(this, that);
/*     */     }
/*     */     
/*     */     public <B> SeqView<A, Repr> intersect(GenSeq that) {
/* 635 */       return SeqViewLike$class.intersect(this, that);
/*     */     }
/*     */     
/*     */     public <B> SeqView<A, Repr> sorted(Ordering ord) {
/* 635 */       return SeqViewLike$class.sorted(this, ord);
/*     */     }
/*     */     
/*     */     public String stringPrefix() {
/* 635 */       return SeqViewLike$class.stringPrefix(this);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> drop(int n) {
/* 635 */       return (SeqView<A, Repr>)IterableViewLike$class.drop(this, n);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> take(int n) {
/* 635 */       return (SeqView<A, Repr>)IterableViewLike$class.take(this, n);
/*     */     }
/*     */     
/*     */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 635 */       return (That)IterableViewLike$class.zip(this, that, bf);
/*     */     }
/*     */     
/*     */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/* 635 */       return (That)IterableViewLike$class.zipWithIndex(this, bf);
/*     */     }
/*     */     
/*     */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 635 */       return (That)IterableViewLike$class.zipAll(this, that, thisElem, thatElem, bf);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<A, Repr>> grouped(int size) {
/* 635 */       return IterableViewLike$class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<A, Repr>> sliding(int size, int step) {
/* 635 */       return IterableViewLike$class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public Builder<A, SeqView<A, Repr>> newBuilder() {
/* 635 */       return TraversableViewLike$class.newBuilder(this);
/*     */     }
/*     */     
/*     */     public String viewIdentifier() {
/* 635 */       return TraversableViewLike$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public String viewIdString() {
/* 635 */       return TraversableViewLike$class.viewIdString(this);
/*     */     }
/*     */     
/*     */     public <B, That> That force(CanBuildFrom bf) {
/* 635 */       return (That)TraversableViewLike$class.force(this, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/* 635 */       return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 635 */       return (That)TraversableViewLike$class.map(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 635 */       return (That)TraversableViewLike$class.collect(this, pf, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 635 */       return (That)TraversableViewLike$class.flatMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B> TraversableViewLike<A, Repr, SeqView<A, Repr>>.Transformed<B> flatten(Function1 asTraversable) {
/* 635 */       return TraversableViewLike$class.flatten(this, asTraversable);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> filter(Function1 p) {
/* 635 */       return (SeqView<A, Repr>)TraversableViewLike$class.filter(this, p);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> withFilter(Function1 p) {
/* 635 */       return (SeqView<A, Repr>)TraversableViewLike$class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<SeqView<A, Repr>, SeqView<A, Repr>> partition(Function1 p) {
/* 635 */       return TraversableViewLike$class.partition(this, p);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> init() {
/* 635 */       return (SeqView<A, Repr>)TraversableViewLike$class.init(this);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> slice(int from, int until) {
/* 635 */       return (SeqView<A, Repr>)TraversableViewLike$class.slice(this, from, until);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> dropWhile(Function1 p) {
/* 635 */       return (SeqView<A, Repr>)TraversableViewLike$class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> takeWhile(Function1 p) {
/* 635 */       return (SeqView<A, Repr>)TraversableViewLike$class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<SeqView<A, Repr>, SeqView<A, Repr>> span(Function1 p) {
/* 635 */       return TraversableViewLike$class.span(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<SeqView<A, Repr>, SeqView<A, Repr>> splitAt(int n) {
/* 635 */       return TraversableViewLike$class.splitAt(this, n);
/*     */     }
/*     */     
/*     */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 635 */       return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 635 */       return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <K> Map<K, SeqView<A, Repr>> groupBy(Function1 f) {
/* 635 */       return TraversableViewLike$class.groupBy(this, f);
/*     */     }
/*     */     
/*     */     public <A1, A2> Tuple2<TraversableViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A1>, TraversableViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A2>> unzip(Function1 asPair) {
/* 635 */       return TraversableViewLike$class.unzip(this, asPair);
/*     */     }
/*     */     
/*     */     public <A1, A2, A3> Tuple3<TraversableViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A1>, TraversableViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A2>, TraversableViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A3>> unzip3(Function1 asTriple) {
/* 635 */       return TraversableViewLike$class.unzip3(this, asTriple);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 635 */       return TraversableViewLike$class.toString(this);
/*     */     }
/*     */     
/*     */     public String viewToString() {
/* 635 */       return GenTraversableViewLike$class.viewToString(this);
/*     */     }
/*     */     
/*     */     public Seq<A> thisSeq() {
/* 635 */       return ViewMkString$class.thisSeq(this);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 635 */       return ViewMkString$class.mkString(this);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 635 */       return ViewMkString$class.mkString(this, sep);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 635 */       return ViewMkString$class.mkString(this, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 635 */       return ViewMkString$class.addString(this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public GenericCompanion<Seq> companion() {
/* 635 */       return Seq$class.companion(this);
/*     */     }
/*     */     
/*     */     public Seq<A> seq() {
/* 635 */       return Seq$class.seq(this);
/*     */     }
/*     */     
/*     */     public Seq<A> thisCollection() {
/* 635 */       return SeqLike$class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public Seq<A> toCollection(Object repr) {
/* 635 */       return SeqLike$class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public Combiner<A, ParSeq<A>> parCombiner() {
/* 635 */       return SeqLike$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public int lengthCompare(int len) {
/* 635 */       return SeqLike$class.lengthCompare(this, len);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 635 */       return SeqLike$class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 635 */       return SeqLike$class.size(this);
/*     */     }
/*     */     
/*     */     public int segmentLength(Function1 p, int from) {
/* 635 */       return SeqLike$class.segmentLength(this, p, from);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p, int from) {
/* 635 */       return SeqLike$class.indexWhere(this, p, from);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 p, int end) {
/* 635 */       return SeqLike$class.lastIndexWhere(this, p, end);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<A, Repr>> permutations() {
/* 635 */       return SeqLike$class.permutations(this);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<A, Repr>> combinations(int n) {
/* 635 */       return SeqLike$class.combinations(this, n);
/*     */     }
/*     */     
/*     */     public Iterator<A> reverseIterator() {
/* 635 */       return SeqLike$class.reverseIterator(this);
/*     */     }
/*     */     
/*     */     public <B> boolean startsWith(GenSeq that, int offset) {
/* 635 */       return SeqLike$class.startsWith(this, that, offset);
/*     */     }
/*     */     
/*     */     public <B> boolean endsWith(GenSeq that) {
/* 635 */       return SeqLike$class.endsWith(this, that);
/*     */     }
/*     */     
/*     */     public <B> int indexOfSlice(GenSeq that) {
/* 635 */       return SeqLike$class.indexOfSlice(this, that);
/*     */     }
/*     */     
/*     */     public <B> int indexOfSlice(GenSeq that, int from) {
/* 635 */       return SeqLike$class.indexOfSlice(this, that, from);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOfSlice(GenSeq that) {
/* 635 */       return SeqLike$class.lastIndexOfSlice(this, that);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOfSlice(GenSeq that, int end) {
/* 635 */       return SeqLike$class.lastIndexOfSlice(this, that, end);
/*     */     }
/*     */     
/*     */     public <B> boolean containsSlice(GenSeq that) {
/* 635 */       return SeqLike$class.containsSlice(this, that);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/* 635 */       return SeqLike$class.contains(this, elem);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> distinct() {
/* 635 */       return (SeqView<A, Repr>)SeqLike$class.distinct(this);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenSeq that, Function2 p) {
/* 635 */       return SeqLike$class.corresponds(this, that, p);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> sortWith(Function2 lt) {
/* 635 */       return (SeqView<A, Repr>)SeqLike$class.sortWith(this, lt);
/*     */     }
/*     */     
/*     */     public <B> SeqView<A, Repr> sortBy(Function1 f, Ordering ord) {
/* 635 */       return (SeqView<A, Repr>)SeqLike$class.sortBy(this, f, ord);
/*     */     }
/*     */     
/*     */     public Seq<A> toSeq() {
/* 635 */       return SeqLike$class.toSeq(this);
/*     */     }
/*     */     
/*     */     public Range indices() {
/* 635 */       return SeqLike$class.indices(this);
/*     */     }
/*     */     
/*     */     public Object view() {
/* 635 */       return SeqLike$class.view(this);
/*     */     }
/*     */     
/*     */     public SeqView<A, SeqView<A, Repr>> view(int from, int until) {
/* 635 */       return SeqLike$class.view(this, from, until);
/*     */     }
/*     */     
/*     */     public boolean isDefinedAt(int idx) {
/* 635 */       return GenSeqLike$class.isDefinedAt(this, idx);
/*     */     }
/*     */     
/*     */     public int prefixLength(Function1 p) {
/* 635 */       return GenSeqLike$class.prefixLength(this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/* 635 */       return GenSeqLike$class.indexWhere(this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/* 635 */       return GenSeqLike$class.indexOf(this, elem);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem, int from) {
/* 635 */       return GenSeqLike$class.indexOf(this, elem, from);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOf(Object elem) {
/* 635 */       return GenSeqLike$class.lastIndexOf(this, elem);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOf(Object elem, int end) {
/* 635 */       return GenSeqLike$class.lastIndexOf(this, elem, end);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 p) {
/* 635 */       return GenSeqLike$class.lastIndexWhere(this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean startsWith(GenSeq that) {
/* 635 */       return GenSeqLike$class.startsWith(this, that);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 635 */       return GenSeqLike$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 635 */       return GenSeqLike$class.equals(this, that);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 635 */       IterableLike$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/* 635 */       return IterableLike$class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/* 635 */       return IterableLike$class.exists(this, p);
/*     */     }
/*     */     
/*     */     public Option<A> find(Function1 p) {
/* 635 */       return IterableLike$class.find(this, p);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/* 635 */       return (B)IterableLike$class.foldRight(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/* 635 */       return (B)IterableLike$class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public Iterable<A> toIterable() {
/* 635 */       return IterableLike$class.toIterable(this);
/*     */     }
/*     */     
/*     */     public Iterator<A> toIterator() {
/* 635 */       return IterableLike$class.toIterator(this);
/*     */     }
/*     */     
/*     */     public A head() {
/* 635 */       return (A)IterableLike$class.head(this);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<A, Repr>> sliding(int size) {
/* 635 */       return IterableLike$class.sliding(this, size);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> takeRight(int n) {
/* 635 */       return (SeqView<A, Repr>)IterableLike$class.takeRight(this, n);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> dropRight(int n) {
/* 635 */       return (SeqView<A, Repr>)IterableLike$class.dropRight(this, n);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start, int len) {
/* 635 */       IterableLike$class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public <B> boolean sameElements(GenIterable that) {
/* 635 */       return IterableLike$class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Stream<A> toStream() {
/* 635 */       return IterableLike$class.toStream(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/* 635 */       return IterableLike$class.canEqual(this, that);
/*     */     }
/*     */     
/*     */     public <B> Builder<B, Seq<B>> genericBuilder() {
/* 635 */       return GenericTraversableTemplate.class.genericBuilder(this);
/*     */     }
/*     */     
/*     */     public <B> Seq<Seq<B>> transpose(Function1 asTraversable) {
/* 635 */       return (Seq<Seq<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> repr() {
/* 635 */       return (SeqView<A, Repr>)TraversableLike$class.repr(this);
/*     */     }
/*     */     
/*     */     public final boolean isTraversableAgain() {
/* 635 */       return TraversableLike$class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 635 */       return TraversableLike$class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 635 */       return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 635 */       return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> filterNot(Function1 p) {
/* 635 */       return (SeqView<A, Repr>)TraversableLike$class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 635 */       return (That)TraversableLike$class.scan(this, z, op, cbf);
/*     */     }
/*     */     
/*     */     public Option<A> headOption() {
/* 635 */       return TraversableLike$class.headOption(this);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> tail() {
/* 635 */       return (SeqView<A, Repr>)TraversableLike$class.tail(this);
/*     */     }
/*     */     
/*     */     public A last() {
/* 635 */       return (A)TraversableLike$class.last(this);
/*     */     }
/*     */     
/*     */     public Option<A> lastOption() {
/* 635 */       return TraversableLike$class.lastOption(this);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> sliceWithKnownDelta(int from, int until, int delta) {
/* 635 */       return (SeqView<A, Repr>)TraversableLike$class.sliceWithKnownDelta(this, from, until, delta);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> sliceWithKnownBound(int from, int until) {
/* 635 */       return (SeqView<A, Repr>)TraversableLike$class.sliceWithKnownBound(this, from, until);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<A, Repr>> tails() {
/* 635 */       return TraversableLike$class.tails(this);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<A, Repr>> inits() {
/* 635 */       return TraversableLike$class.inits(this);
/*     */     }
/*     */     
/*     */     public Traversable<A> toTraversable() {
/* 635 */       return TraversableLike$class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 635 */       return (Col)TraversableLike$class.to(this, cbf);
/*     */     }
/*     */     
/*     */     public ParSeq<A> par() {
/* 635 */       return (ParSeq<A>)Parallelizable$class.par(this);
/*     */     }
/*     */     
/*     */     public List<A> reversed() {
/* 635 */       return TraversableOnce$class.reversed(this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 635 */       return TraversableOnce$class.nonEmpty(this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 635 */       return TraversableOnce$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 635 */       return TraversableOnce$class.collectFirst(this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/* 635 */       return (B)TraversableOnce$class.$div$colon(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 635 */       return (B)TraversableOnce$class.$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/* 635 */       return (B)TraversableOnce$class.foldLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/* 635 */       return (B)TraversableOnce$class.reduceLeft(this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 635 */       return TraversableOnce$class.reduceLeftOption(this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 635 */       return TraversableOnce$class.reduceRightOption(this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 reduce(Function2 op) {
/* 635 */       return (A1)TraversableOnce$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 635 */       return TraversableOnce$class.reduceOption(this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 fold(Object z, Function2 op) {
/* 635 */       return (A1)TraversableOnce$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 635 */       return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> B sum(Numeric num) {
/* 635 */       return (B)TraversableOnce$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <B> B product(Numeric num) {
/* 635 */       return (B)TraversableOnce$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <B> A min(Ordering cmp) {
/* 635 */       return (A)TraversableOnce$class.min(this, cmp);
/*     */     }
/*     */     
/*     */     public <B> A max(Ordering cmp) {
/* 635 */       return (A)TraversableOnce$class.max(this, cmp);
/*     */     }
/*     */     
/*     */     public <B> A maxBy(Function1 f, Ordering cmp) {
/* 635 */       return (A)TraversableOnce$class.maxBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> A minBy(Function1 f, Ordering cmp) {
/* 635 */       return (A)TraversableOnce$class.minBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/* 635 */       TraversableOnce$class.copyToBuffer(this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/* 635 */       TraversableOnce$class.copyToArray(this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/* 635 */       TraversableOnce$class.copyToArray(this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/* 635 */       return TraversableOnce$class.toArray(this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<A> toList() {
/* 635 */       return TraversableOnce$class.toList(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<A> toIndexedSeq() {
/* 635 */       return TraversableOnce$class.toIndexedSeq(this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/* 635 */       return TraversableOnce$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 635 */       return TraversableOnce$class.toSet(this);
/*     */     }
/*     */     
/*     */     public Vector<A> toVector() {
/* 635 */       return TraversableOnce$class.toVector(this);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(scala.Predef$.less.colon.less ev) {
/* 635 */       return TraversableOnce$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/* 635 */       return TraversableOnce$class.addString(this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/* 635 */       return TraversableOnce$class.addString(this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 635 */       return (A1)GenTraversableOnce$class.$div$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 635 */       return PartialFunction.class.orElse(this, that);
/*     */     }
/*     */     
/*     */     public <C> PartialFunction<Object, C> andThen(Function1 k) {
/* 635 */       return PartialFunction.class.andThen(this, k);
/*     */     }
/*     */     
/*     */     public Function1<Object, Option<A>> lift() {
/* 635 */       return PartialFunction.class.lift(this);
/*     */     }
/*     */     
/*     */     public <A1, B1> B1 applyOrElse(Object x, Function1 default) {
/* 635 */       return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*     */     }
/*     */     
/*     */     public <U> Function1<Object, Object> runWith(Function1 action) {
/* 635 */       return PartialFunction.class.runWith(this, action);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZD$sp(double v1) {
/* 635 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/* 635 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/* 635 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/* 635 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/* 635 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/* 635 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/* 635 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/* 635 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/* 635 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/* 635 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/* 635 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/* 635 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/* 635 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/* 635 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/* 635 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/* 635 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/* 635 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/* 635 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/* 635 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/* 635 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/* 635 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/* 635 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/* 635 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/* 635 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, A> compose(Function1 g) {
/* 635 */       return Function1.class.compose((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public SeqLike$$anon$2(SeqLike $outer) {
/* 635 */       Function1.class.$init$((Function1)this);
/* 635 */       PartialFunction.class.$init$(this);
/* 635 */       GenTraversableOnce$class.$init$(this);
/* 635 */       TraversableOnce$class.$init$(this);
/* 635 */       Parallelizable$class.$init$(this);
/* 635 */       TraversableLike$class.$init$(this);
/* 635 */       GenericTraversableTemplate.class.$init$(this);
/* 635 */       GenTraversable$class.$init$(this);
/* 635 */       Traversable$class.$init$(this);
/* 635 */       GenIterable$class.$init$(this);
/* 635 */       IterableLike$class.$init$(this);
/* 635 */       Iterable$class.$init$(this);
/* 635 */       GenSeqLike$class.$init$(this);
/* 635 */       GenSeq$class.$init$(this);
/* 635 */       SeqLike$class.$init$(this);
/* 635 */       Seq$class.$init$(this);
/* 635 */       ViewMkString$class.$init$(this);
/* 635 */       GenTraversableViewLike$class.$init$(this);
/* 635 */       TraversableViewLike$class.$init$(this);
/* 635 */       GenIterableViewLike$class.$init$(this);
/* 635 */       IterableViewLike$class.$init$(this);
/* 635 */       GenSeqViewLike$class.$init$(this);
/* 635 */       SeqViewLike$class.$init$(this);
/*     */     }
/*     */     
/*     */     private Object underlying$lzycompute() {
/* 636 */       synchronized (this) {
/* 636 */         if (!this.bitmap$0) {
/* 636 */           this.underlying = (Repr)this.$outer.repr();
/* 636 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/SeqLike$$anon$2}} */
/* 636 */         return this.underlying;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Repr underlying() {
/* 636 */       return this.bitmap$0 ? this.underlying : (Repr)underlying$lzycompute();
/*     */     }
/*     */     
/*     */     public Iterator<A> iterator() {
/* 637 */       return this.$outer.iterator();
/*     */     }
/*     */     
/*     */     public int length() {
/* 638 */       return this.$outer.length();
/*     */     }
/*     */     
/*     */     public A apply(int idx) {
/* 639 */       return (A)this.$outer.apply(idx);
/*     */     }
/*     */   }
/*     */   
/*     */   private SeqLike$() {
/* 651 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   private <B> IndexedSeq<B> kmpOptimizeWord(Seq W, int n0, int n1, boolean forward) {
/*     */     SeqLike$$anon$5 seqLike$$anon$5;
/* 663 */     if (W instanceof IndexedSeq) {
/* 663 */       IndexedSeq indexedSeq = (IndexedSeq)W;
/* 666 */       seqLike$$anon$5 = (SeqLike$$anon$5)((forward && n0 == 0 && n1 == W.length()) ? indexedSeq : (
/* 667 */         forward ? new SeqLike$$anon$3(n0, n1, indexedSeq) : 
/*     */         
/* 671 */         new SeqLike$$anon$4(n0, n1, indexedSeq)));
/*     */     } else {
/* 678 */       seqLike$$anon$5 = new SeqLike$$anon$5(W, n0, n1, forward);
/*     */     } 
/*     */     return seqLike$$anon$5;
/*     */   }
/*     */   
/*     */   public static class SeqLike$$anon$3 extends AbstractSeq<B> implements IndexedSeq<B> {
/*     */     private final int length;
/*     */     
/*     */     private final int n0$1;
/*     */     
/*     */     private final IndexedSeq x2$1;
/*     */     
/*     */     public GenericCompanion<IndexedSeq> companion() {
/*     */       return IndexedSeq$class.companion(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> seq() {
/*     */       return IndexedSeq$class.seq(this);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*     */       return IndexedSeqLike$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> thisCollection() {
/*     */       return IndexedSeqLike$class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> toCollection(Object repr) {
/*     */       return IndexedSeqLike$class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public Iterator<B> iterator() {
/*     */       return IndexedSeqLike$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <A1> Buffer<A1> toBuffer() {
/*     */       return IndexedSeqLike$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public SeqLike$$anon$3(int n0$1, int n1$1, IndexedSeq x2$1) {
/*     */       IndexedSeqLike$class.$init$(this);
/*     */       IndexedSeq$class.$init$(this);
/*     */       this.length = n1$1 - n0$1;
/*     */     }
/*     */     
/*     */     public int length() {
/*     */       return this.length;
/*     */     }
/*     */     
/*     */     public B apply(int x) {
/*     */       return this.x2$1.apply(this.n0$1 + x);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SeqLike$$anon$4 extends AbstractSeq<B> implements IndexedSeq<B> {
/*     */     private final int n0$1;
/*     */     
/*     */     private final int n1$1;
/*     */     
/*     */     private final IndexedSeq x2$1;
/*     */     
/*     */     public GenericCompanion<IndexedSeq> companion() {
/*     */       return IndexedSeq$class.companion(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> seq() {
/*     */       return IndexedSeq$class.seq(this);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*     */       return IndexedSeqLike$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> thisCollection() {
/*     */       return IndexedSeqLike$class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> toCollection(Object repr) {
/*     */       return IndexedSeqLike$class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public Iterator<B> iterator() {
/*     */       return IndexedSeqLike$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <A1> Buffer<A1> toBuffer() {
/*     */       return IndexedSeqLike$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public SeqLike$$anon$4(int n0$1, int n1$1, IndexedSeq x2$1) {
/*     */       IndexedSeqLike$class.$init$(this);
/*     */       IndexedSeq$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int length() {
/*     */       return this.n1$1 - this.n0$1;
/*     */     }
/*     */     
/*     */     public B apply(int x) {
/*     */       return this.x2$1.apply(this.n1$1 - 1 - x);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SeqLike$$anon$5 extends AbstractSeq<B> implements IndexedSeq<B> {
/*     */     private final Object[] Warr;
/*     */     
/*     */     private final int delta;
/*     */     
/*     */     private final int done;
/*     */     
/*     */     private final Iterator<B> wit;
/*     */     
/*     */     private int i;
/*     */     
/*     */     private final int length;
/*     */     
/*     */     public GenericCompanion<IndexedSeq> companion() {
/* 678 */       return IndexedSeq$class.companion(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> seq() {
/* 678 */       return IndexedSeq$class.seq(this);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 678 */       return IndexedSeqLike$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> thisCollection() {
/* 678 */       return IndexedSeqLike$class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> toCollection(Object repr) {
/* 678 */       return IndexedSeqLike$class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public Iterator<B> iterator() {
/* 678 */       return IndexedSeqLike$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <A1> Buffer<A1> toBuffer() {
/* 678 */       return IndexedSeqLike$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public SeqLike$$anon$5(Seq<B> W$1, int n0$1, int n1$1, boolean forward$1) {
/* 678 */       IndexedSeqLike$class.$init$(this);
/* 678 */       IndexedSeq$class.$init$(this);
/* 679 */       this.Warr = new Object[n1$1 - n0$1];
/* 680 */       this.delta = forward$1 ? 1 : -1;
/* 681 */       this.done = forward$1 ? (n1$1 - n0$1) : -1;
/* 682 */       this.wit = W$1.iterator().drop(n0$1);
/* 683 */       this.i = forward$1 ? 0 : (n1$1 - n0$1 - 1);
/* 684 */       while (i() != this.done) {
/* 685 */         this.Warr[i()] = wit().next();
/* 686 */         i_$eq(i() + this.delta);
/*     */       } 
/* 689 */       this.length = n1$1 - n0$1;
/*     */     }
/*     */     
/*     */     public Iterator<B> wit() {
/*     */       return this.wit;
/*     */     }
/*     */     
/*     */     public int i() {
/*     */       return this.i;
/*     */     }
/*     */     
/*     */     public void i_$eq(int x$1) {
/*     */       this.i = x$1;
/*     */     }
/*     */     
/*     */     public int length() {
/* 689 */       return this.length;
/*     */     }
/*     */     
/*     */     public B apply(int x) {
/* 690 */       return (B)this.Warr[x];
/*     */     }
/*     */   }
/*     */   
/*     */   private <B> int[] kmpJumpTable(IndexedSeq<Object> Wopt, int wlen) {
/* 703 */     int[] arr = new int[wlen];
/* 704 */     int pos = 2;
/* 705 */     int cnd = 0;
/* 706 */     arr[0] = -1;
/* 707 */     arr[1] = 0;
/* 708 */     while (pos < wlen) {
/* 709 */       Object object = Wopt.apply(cnd);
/*     */       Number number;
/* 709 */       if (((number = (Number)Wopt.apply(pos - 1)) == object) ? true : ((number == null) ? false : ((number instanceof Number) ? BoxesRunTime.equalsNumObject(number, object) : ((number instanceof Character) ? BoxesRunTime.equalsCharObject((Character)number, object) : number.equals(object))))) {
/* 710 */         arr[pos] = cnd + 1;
/* 711 */         pos++;
/* 712 */         cnd++;
/*     */         continue;
/*     */       } 
/* 714 */       if (cnd > 0) {
/* 715 */         cnd = arr[cnd];
/*     */         continue;
/*     */       } 
/* 718 */       arr[pos] = 0;
/* 719 */       pos++;
/*     */     } 
/* 722 */     return arr;
/*     */   }
/*     */   
/*     */   private final int clipR$1(int x, int y) {
/* 741 */     return (x < y) ? x : -1;
/*     */   }
/*     */   
/*     */   private final int clipL$1(int x, int y) {
/* 742 */     return (x > y) ? x : -1;
/*     */   }
/*     */   
/*     */   public <B> int scala$collection$SeqLike$$kmpSearch(Seq<Object> S, int m0, int m1, Seq<?> W, int n0, int n1, boolean forward) {
/* 744 */     if (n1 == n0 + 1) {
/*     */     
/* 752 */     } else if (m1 - m0 == n1 - n0) {
/* 754 */       TraversableView traversableView = W.view().slice(n0, n1);
/* 754 */       if (S.view().slice(m0, m1) == null) {
/* 754 */         S.view().slice(m0, m1);
/* 754 */         if (traversableView != null);
/* 754 */       } else if (S.view().slice(m0, m1).equals(traversableView)) {
/*     */       
/*     */       } 
/*     */     } else {
/*     */       int i;
/* 758 */       if (S instanceof IndexedSeq) {
/* 761 */         IndexedSeq<?> Wopt = kmpOptimizeWord(W, n0, n1, forward);
/* 762 */         int[] T = kmpJumpTable(Wopt, n1 - n0);
/* 763 */         int j = 0, m = 0;
/* 764 */         int zero = forward ? m0 : (m1 - 1);
/* 765 */         int delta = forward ? 1 : -1;
/* 766 */         while (j + m < m1 - m0) {
/* 767 */           Object object1 = S.apply(zero + delta * (j + m));
/*     */           Object object;
/* 767 */           if (((object = Wopt.apply(j)) == object1) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, object1) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, object1) : object.equals(object1))))) {
/* 768 */             if (++j == 
/* 769 */               n1 - n0)
/* 769 */               return forward ? (m + m0) : (m1 - m - j); 
/*     */             continue;
/*     */           } 
/* 772 */           int ti = T[j];
/* 773 */           m += j - ti;
/* 774 */           if (j > 0)
/* 774 */             j = ti; 
/*     */         } 
/* 777 */         i = -1;
/*     */       } else {
/* 780 */         Iterator iter = S.iterator().drop(m0);
/* 781 */         IndexedSeq<?> Wopt = kmpOptimizeWord(W, n0, n1, true);
/* 782 */         int[] T = kmpJumpTable(Wopt, n1 - n0);
/* 783 */         Object[] cache = new Object[n1 - n0];
/* 784 */         int largest = 0;
/* 785 */         int j = 0, m = 0;
/* 786 */         int answer = -1;
/* 787 */         while (m + m0 + n1 - n0 <= m1) {
/* 788 */           while (j + m >= largest) {
/* 789 */             cache[largest % (n1 - n0)] = iter.next();
/* 790 */             largest++;
/*     */           } 
/* 792 */           Object object2 = cache[(j + m) % (n1 - n0)];
/*     */           Object object1;
/* 792 */           if (((object1 = Wopt.apply(j)) == object2) ? true : ((object1 == null) ? false : ((object1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object1, object2) : ((object1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object1, object2) : object1.equals(object2))))) {
/* 793 */             if (++j == 
/* 794 */               n1 - n0) {
/* 795 */               if (forward)
/* 795 */                 return m + m0; 
/* 797 */               j--;
/* 798 */               answer = m + m0;
/* 799 */               int k = T[j];
/* 800 */               m += j - k;
/* 801 */               if (j > 0)
/* 801 */                 j = k; 
/*     */             } 
/*     */             continue;
/*     */           } 
/* 806 */           int ti = T[j];
/* 807 */           m += j - ti;
/* 808 */           if (j > 0)
/* 808 */             j = ti; 
/*     */         } 
/* 811 */         i = answer;
/*     */       } 
/*     */       return i;
/*     */     } 
/*     */     return m0;
/*     */   }
/*     */   
/*     */   public <B> int indexOf(Seq<?> source, int sourceOffset, int sourceCount, Seq<?> target, int targetOffset, int targetCount, int fromIndex) {
/* 840 */     int slen = source.length();
/* 841 */     int clippedFrom = scala.math.package$.MODULE$.max(0, fromIndex);
/* 842 */     int s0 = scala.math.package$.MODULE$.min(slen, sourceOffset + clippedFrom);
/* 843 */     int s1 = scala.math.package$.MODULE$.min(slen, s0 + sourceCount);
/* 844 */     int tlen = target.length();
/* 845 */     int t0 = scala.math.package$.MODULE$.min(tlen, targetOffset);
/* 846 */     int t1 = scala.math.package$.MODULE$.min(tlen, t0 + targetCount);
/* 854 */     int ans = scala$collection$SeqLike$$kmpSearch(source, s0, s1, target, t0, t1, true);
/* 855 */     return (clippedFrom > slen - sourceOffset) ? -1 : ((t1 - t0 < 1) ? s0 : ((s1 - s0 < t1 - t0) ? -1 : ((ans < 0) ? ans : (ans - scala.math.package$.MODULE$.min(slen, sourceOffset)))));
/*     */   }
/*     */   
/*     */   public <B> int lastIndexOf(Seq<?> source, int sourceOffset, int sourceCount, Seq<?> target, int targetOffset, int targetCount, int fromIndex) {
/* 871 */     int slen = source.length();
/* 872 */     int tlen = target.length();
/* 873 */     int s0 = scala.math.package$.MODULE$.min(slen, sourceOffset);
/* 874 */     int s1 = scala.math.package$.MODULE$.min(slen, s0 + sourceCount);
/* 875 */     int clippedFrom = scala.math.package$.MODULE$.min(s1 - s0, fromIndex);
/* 876 */     int t0 = scala.math.package$.MODULE$.min(tlen, targetOffset);
/* 877 */     int t1 = scala.math.package$.MODULE$.min(tlen, t0 + targetCount);
/* 878 */     int fixed_s1 = scala.math.package$.MODULE$.min(s1, s0 + clippedFrom + t1 - t0 - 1);
/* 886 */     int ans = scala$collection$SeqLike$$kmpSearch(source, s0, fixed_s1, target, t0, t1, false);
/* 887 */     return (clippedFrom < 0) ? -1 : ((t1 - t0 < 1) ? (s0 + clippedFrom) : ((fixed_s1 - s0 < t1 - t0) ? -1 : ((ans < 0) ? ans : (ans - s0))));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SeqLike$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */