/*      */ package scala.collection.immutable;
/*      */ 
/*      */ import scala.Function0;
/*      */ import scala.Function1;
/*      */ import scala.Function2;
/*      */ import scala.Option;
/*      */ import scala.PartialFunction;
/*      */ import scala.Serializable;
/*      */ import scala.Some;
/*      */ import scala.Tuple2;
/*      */ import scala.Tuple3;
/*      */ import scala.collection.GenIterable;
/*      */ import scala.collection.GenIterableViewLike;
/*      */ import scala.collection.GenMap;
/*      */ import scala.collection.GenSeq;
/*      */ import scala.collection.GenSeqLike;
/*      */ import scala.collection.GenSeqViewLike;
/*      */ import scala.collection.GenSet;
/*      */ import scala.collection.GenTraversable;
/*      */ import scala.collection.GenTraversableOnce;
/*      */ import scala.collection.GenTraversableViewLike;
/*      */ import scala.collection.Iterable;
/*      */ import scala.collection.IterableLike;
/*      */ import scala.collection.IterableView;
/*      */ import scala.collection.IterableViewLike;
/*      */ import scala.collection.Iterator;
/*      */ import scala.collection.Parallelizable;
/*      */ import scala.collection.Seq;
/*      */ import scala.collection.SeqLike;
/*      */ import scala.collection.SeqView;
/*      */ import scala.collection.SeqViewLike;
/*      */ import scala.collection.Traversable;
/*      */ import scala.collection.TraversableLike;
/*      */ import scala.collection.TraversableOnce;
/*      */ import scala.collection.TraversableView;
/*      */ import scala.collection.TraversableViewLike;
/*      */ import scala.collection.ViewMkString;
/*      */ import scala.collection.generic.CanBuildFrom;
/*      */ import scala.collection.generic.FilterMonadic;
/*      */ import scala.collection.generic.GenericCompanion;
/*      */ import scala.collection.generic.GenericTraversableTemplate;
/*      */ import scala.collection.generic.SeqFactory;
/*      */ import scala.collection.generic.SliceInterval;
/*      */ import scala.collection.mutable.Buffer;
/*      */ import scala.collection.mutable.Builder;
/*      */ import scala.collection.mutable.StringBuilder;
/*      */ import scala.collection.parallel.Combiner;
/*      */ import scala.collection.parallel.ParSeq;
/*      */ import scala.math.Integral;
/*      */ import scala.math.Numeric;
/*      */ import scala.math.Ordering;
/*      */ import scala.reflect.ClassTag;
/*      */ import scala.runtime.AbstractFunction0;
/*      */ import scala.runtime.AbstractFunction1;
/*      */ import scala.runtime.BoxedUnit;
/*      */ import scala.runtime.BoxesRunTime;
/*      */ import scala.runtime.ObjectRef;
/*      */ 
/*      */ public final class Stream$ extends SeqFactory<Stream> {
/*      */   public static final Stream$ MODULE$;
/*      */   
/*      */   public class Stream$$anonfun$append$1 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function0 rest$1;
/*      */     
/*      */     public final Stream<B> apply() {
/*  237 */       return ((Stream)this.$outer.tail()).append(this.rest$1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$append$1(Stream $outer, Function0 rest$1) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$$plus$plus$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final GenTraversableOnce that$1;
/*      */     
/*      */     public final Stream<A> apply() {
/*  330 */       return (Stream<A>)((Stream)this.$outer.tail()).$plus$plus(this.that$1, Stream$.MODULE$.canBuildFrom());
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$$plus$plus$1(Stream $outer, GenTraversableOnce that$1) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$$plus$colon$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     public final Stream<A> apply() {
/*  335 */       return this.$outer;
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$$plus$colon$1(Stream $outer) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$scanLeft$1 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Object z$1;
/*      */     
/*      */     private final Function2 op$1;
/*      */     
/*      */     public final Stream<B> apply() {
/*  355 */       return (Stream<B>)((Stream)this.$outer.tail()).scanLeft(this.op$1.apply(this.z$1, this.$outer.head()), this.op$1, Stream$.MODULE$.canBuildFrom());
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$scanLeft$1(Stream $outer, Object z$1, Function2 op$1) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$map$1 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 f$1;
/*      */     
/*      */     public final Stream<B> apply() {
/*  376 */       CanBuildFrom<Stream<?>, ?, Stream<?>> canBuildFrom = Stream$.MODULE$.canBuildFrom();
/*  376 */       Function1 function1 = this.f$1;
/*  376 */       Stream stream = (Stream)this.$outer.tail();
/*  376 */       Stream$$anonfun$map$1 stream$$anonfun$map$1 = new Stream$$anonfun$map$1(stream, (Stream<A>)function1);
/*  376 */       Object object = function1.apply(stream.head());
/*  376 */       Stream.cons$ cons$ = Stream.cons$.MODULE$;
/*  376 */       return (canBuildFrom.apply(stream.repr()) instanceof Stream.StreamBuilder) ? (stream.isEmpty() ? Stream.Empty$.MODULE$ : new Stream.Cons<B>((B)object, (Function0<Stream<B>>)stream$$anonfun$map$1)) : (Stream<B>)TraversableLike.class.map(stream, function1, canBuildFrom);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$map$1(Stream $outer, Function1 f$1) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$flatMap$1 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 f$2;
/*      */     
/*      */     private final ObjectRef nonEmptyPrefix$1;
/*      */     
/*      */     public final Stream<B> apply() {
/*      */       Stream prefix1;
/*      */       ObjectRef nonEmptyPrefix1;
/*      */       Stream stream1;
/*      */       Function1 function1;
/*      */       CanBuildFrom<Stream<?>, ?, Stream<?>> canBuildFrom;
/*  450 */       for (canBuildFrom = Stream$.MODULE$.canBuildFrom(), function1 = this.f$2, stream1 = (Stream)((Stream)this.nonEmptyPrefix$1.elem).tail(), nonEmptyPrefix1 = new ObjectRef(stream1), prefix1 = ((GenTraversableOnce)function1.apply(((Stream)nonEmptyPrefix1.elem).head())).toStream(); !((Stream)nonEmptyPrefix1.elem).isEmpty() && prefix1.isEmpty(); ) {
/*  450 */         nonEmptyPrefix1.elem = ((Stream)nonEmptyPrefix1.elem).tail();
/*  450 */         if (!((Stream)nonEmptyPrefix1.elem).isEmpty())
/*  450 */           prefix1 = ((GenTraversableOnce)function1.apply(((Stream)nonEmptyPrefix1.elem).head())).toStream(); 
/*      */       } 
/*  450 */       return (canBuildFrom.apply(stream1.repr()) instanceof Stream.StreamBuilder) ? (stream1.isEmpty() ? Stream.Empty$.MODULE$ : (((Stream)nonEmptyPrefix1.elem).isEmpty() ? Stream$.MODULE$.<B>empty() : prefix1.append((Function0)new Stream$$anonfun$flatMap$1(stream1, function1, (Stream<A>)nonEmptyPrefix1)))) : (Stream<B>)TraversableLike.class.flatMap(stream1, function1, canBuildFrom);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$flatMap$1(Stream $outer, Function1 f$2, ObjectRef nonEmptyPrefix$1) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$partition$1 extends AbstractFunction1<A, Object> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 p$2;
/*      */     
/*      */     public final boolean apply(Object x$1) {
/*  603 */       return BoxesRunTime.unboxToBoolean(this.p$2.apply(x$1));
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$partition$1(Stream $outer, Function1 p$2) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$partition$2 extends AbstractFunction1<A, Object> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 p$2;
/*      */     
/*      */     public final boolean apply(Object x$2) {
/*  603 */       return BoxesRunTime.unboxToBoolean(this.p$2.apply(x$2));
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$partition$2(Stream $outer, Function1 p$2) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$zip$1 extends AbstractFunction0<Stream<Tuple2<A1, B>>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final GenIterable that$2;
/*      */     
/*      */     public final Stream<Tuple2<A1, B>> apply() {
/*  641 */       return (Stream<Tuple2<A1, B>>)((Stream)this.$outer.tail()).zip((GenIterable)this.that$2.tail(), Stream$.MODULE$.canBuildFrom());
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$zip$1(Stream $outer, GenIterable that$2) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$take$1 extends AbstractFunction0<Stream<scala.runtime.Nothing$>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     public final Stream<scala.runtime.Nothing$> apply() {
/*  730 */       return Stream$.MODULE$.empty();
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$take$1(Stream $outer) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$take$2 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final int n$1;
/*      */     
/*      */     public final Stream<A> apply() {
/*  731 */       return ((Stream<A>)this.$outer.tail()).take(this.n$1 - 1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$take$2(Stream $outer, int n$1) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$init$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     public final Stream<A> apply() {
/*  766 */       return ((Stream<A>)this.$outer.tail()).init();
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$init$1(Stream $outer) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$takeWhile$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 p$3;
/*      */     
/*      */     public final Stream<A> apply() {
/*  803 */       return ((Stream<A>)this.$outer.tail()).takeWhile(this.p$3);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$takeWhile$1(Stream $outer, Function1 p$3) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$scala$collection$immutable$Stream$$loop$4$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Set seen$1;
/*      */     
/*      */     private final Stream rest$2;
/*      */     
/*      */     public final Stream<A> apply() {
/*  850 */       return this.$outer.scala$collection$immutable$Stream$$loop$4((Set)this.seen$1.$plus(this.rest$2.head()), (Stream)this.rest$2.tail());
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$scala$collection$immutable$Stream$$loop$4$1(Stream $outer, Set seen$1, Stream rest$2) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$scala$collection$immutable$Stream$$loop$5$1 extends AbstractFunction0<B> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     public final Object elem$1;
/*      */     
/*      */     public final B apply() {
/*  883 */       return (B)this.elem$1;
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$scala$collection$immutable$Stream$$loop$5$1(Stream $outer, Object elem$1) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$scala$collection$immutable$Stream$$loop$5$2 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Object elem$1;
/*      */     
/*      */     private final int len$1;
/*      */     
/*      */     private final Stream these$1;
/*      */     
/*      */     public final Stream<B> apply() {
/*  884 */       return this.$outer.scala$collection$immutable$Stream$$loop$5(this.len$1 - 1, (Stream)this.these$1.tail(), this.elem$1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$scala$collection$immutable$Stream$$loop$5$2(Stream $outer, Object elem$1, int len$1, Stream these$1) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final ObjectRef result$1;
/*      */     
/*      */     public final Stream<A> apply() {
/*  914 */       return (Stream<A>)this.result$1.elem;
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$1(Stream $outer, ObjectRef result$1) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$scala$collection$immutable$Stream$$flatten1$1$1 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 asTraversable$1;
/*      */     
/*      */     private final Traversable t$1;
/*      */     
/*      */     public final Stream<B> apply() {
/*  937 */       return this.$outer.scala$collection$immutable$Stream$$flatten1$1((Traversable)this.t$1.tail(), this.asTraversable$1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$scala$collection$immutable$Stream$$flatten1$1$1(Stream $outer, Function1 asTraversable$1, Traversable t$1) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anon$1 implements StreamView<A, Stream<A>> {
/*      */     private Stream<A> underlying;
/*      */     
/*      */     private volatile boolean bitmap$0;
/*      */     
/*      */     public <B, That> That force(CanBuildFrom bf) {
/*  945 */       return (That)StreamViewLike$class.force(this, bf);
/*      */     }
/*      */     
/*      */     public <B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<B> newForced(Function0 xs) {
/*  945 */       return StreamViewLike$class.newForced(this, xs);
/*      */     }
/*      */     
/*      */     public <B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<B> newAppended(GenTraversable that) {
/*  945 */       return StreamViewLike$class.newAppended(this, that);
/*      */     }
/*      */     
/*      */     public <B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<B> newMapped(Function1 f) {
/*  945 */       return StreamViewLike$class.newMapped(this, f);
/*      */     }
/*      */     
/*      */     public <B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<B> newFlatMapped(Function1 f) {
/*  945 */       return StreamViewLike$class.newFlatMapped(this, f);
/*      */     }
/*      */     
/*      */     public StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A> newFiltered(Function1 p) {
/*  945 */       return StreamViewLike$class.newFiltered(this, p);
/*      */     }
/*      */     
/*      */     public StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A> newSliced(SliceInterval _endpoints) {
/*  945 */       return StreamViewLike$class.newSliced(this, _endpoints);
/*      */     }
/*      */     
/*      */     public StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A> newDroppedWhile(Function1 p) {
/*  945 */       return StreamViewLike$class.newDroppedWhile(this, p);
/*      */     }
/*      */     
/*      */     public StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A> newTakenWhile(Function1 p) {
/*  945 */       return StreamViewLike$class.newTakenWhile(this, p);
/*      */     }
/*      */     
/*      */     public <B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<Tuple2<A, B>> newZipped(GenIterable that) {
/*  945 */       return StreamViewLike$class.newZipped(this, that);
/*      */     }
/*      */     
/*      */     public <A1, B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<Tuple2<A1, B>> newZippedAll(GenIterable that, Object _thisElem, Object _thatElem) {
/*  945 */       return StreamViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
/*      */     }
/*      */     
/*      */     public StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A> newReversed() {
/*  945 */       return StreamViewLike$class.newReversed(this);
/*      */     }
/*      */     
/*      */     public <B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<B> newPatched(int _from, GenSeq _patch, int _replaced) {
/*  945 */       return StreamViewLike$class.newPatched(this, _from, _patch, _replaced);
/*      */     }
/*      */     
/*      */     public <B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<B> newPrepended(Object elem) {
/*  945 */       return StreamViewLike$class.newPrepended(this, elem);
/*      */     }
/*      */     
/*      */     public String stringPrefix() {
/*  945 */       return StreamViewLike$class.stringPrefix(this);
/*      */     }
/*      */     
/*      */     public SeqViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A> newTaken(int n) {
/*  945 */       return SeqViewLike.class.newTaken(this, n);
/*      */     }
/*      */     
/*      */     public SeqViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A> newDropped(int n) {
/*  945 */       return SeqViewLike.class.newDropped(this, n);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> reverse() {
/*  945 */       return (StreamView<A, Stream<A>>)SeqViewLike.class.reverse(this);
/*      */     }
/*      */     
/*      */     public <B, That> That patch(int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/*  945 */       return (That)SeqViewLike.class.patch(this, from, patch, replaced, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/*  945 */       return (That)SeqViewLike.class.padTo(this, len, elem, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That reverseMap(Function1 f, CanBuildFrom bf) {
/*  945 */       return (That)SeqViewLike.class.reverseMap(this, f, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That updated(int index, Object elem, CanBuildFrom bf) {
/*  945 */       return (That)SeqViewLike.class.updated(this, index, elem, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/*  945 */       return (That)SeqViewLike.class.$plus$colon(this, elem, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/*  945 */       return (That)SeqViewLike.class.$colon$plus(this, elem, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That union(GenSeq that, CanBuildFrom bf) {
/*  945 */       return (That)SeqViewLike.class.union(this, that, bf);
/*      */     }
/*      */     
/*      */     public <B> StreamView<A, Stream<A>> diff(GenSeq that) {
/*  945 */       return (StreamView<A, Stream<A>>)SeqViewLike.class.diff(this, that);
/*      */     }
/*      */     
/*      */     public <B> StreamView<A, Stream<A>> intersect(GenSeq that) {
/*  945 */       return (StreamView<A, Stream<A>>)SeqViewLike.class.intersect(this, that);
/*      */     }
/*      */     
/*      */     public <B> StreamView<A, Stream<A>> sorted(Ordering ord) {
/*  945 */       return (StreamView<A, Stream<A>>)SeqViewLike.class.sorted(this, ord);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> drop(int n) {
/*  945 */       return (StreamView<A, Stream<A>>)IterableViewLike.class.drop((IterableViewLike)this, n);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> take(int n) {
/*  945 */       return (StreamView<A, Stream<A>>)IterableViewLike.class.take((IterableViewLike)this, n);
/*      */     }
/*      */     
/*      */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  945 */       return (That)IterableViewLike.class.zip((IterableViewLike)this, that, bf);
/*      */     }
/*      */     
/*      */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/*  945 */       return (That)IterableViewLike.class.zipWithIndex((IterableViewLike)this, bf);
/*      */     }
/*      */     
/*      */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  945 */       return (That)IterableViewLike.class.zipAll((IterableViewLike)this, that, thisElem, thatElem, bf);
/*      */     }
/*      */     
/*      */     public Iterator<StreamView<A, Stream<A>>> grouped(int size) {
/*  945 */       return IterableViewLike.class.grouped((IterableViewLike)this, size);
/*      */     }
/*      */     
/*      */     public Iterator<StreamView<A, Stream<A>>> sliding(int size, int step) {
/*  945 */       return IterableViewLike.class.sliding((IterableViewLike)this, size, step);
/*      */     }
/*      */     
/*      */     public Builder<A, StreamView<A, Stream<A>>> newBuilder() {
/*  945 */       return TraversableViewLike.class.newBuilder((TraversableViewLike)this);
/*      */     }
/*      */     
/*      */     public String viewIdentifier() {
/*  945 */       return TraversableViewLike.class.viewIdentifier((TraversableViewLike)this);
/*      */     }
/*      */     
/*      */     public String viewIdString() {
/*  945 */       return TraversableViewLike.class.viewIdString((TraversableViewLike)this);
/*      */     }
/*      */     
/*      */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/*  945 */       return (That)TraversableViewLike.class.$plus$plus((TraversableViewLike)this, xs, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/*  945 */       return (That)TraversableViewLike.class.map((TraversableViewLike)this, f, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  945 */       return (That)TraversableViewLike.class.collect((TraversableViewLike)this, pf, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  945 */       return (That)TraversableViewLike.class.flatMap((TraversableViewLike)this, f, bf);
/*      */     }
/*      */     
/*      */     public <B> TraversableViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<B> flatten(Function1 asTraversable) {
/*  945 */       return TraversableViewLike.class.flatten((TraversableViewLike)this, asTraversable);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> filter(Function1 p) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableViewLike.class.filter((TraversableViewLike)this, p);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> withFilter(Function1 p) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableViewLike.class.withFilter((TraversableViewLike)this, p);
/*      */     }
/*      */     
/*      */     public Tuple2<StreamView<A, Stream<A>>, StreamView<A, Stream<A>>> partition(Function1 p) {
/*  945 */       return TraversableViewLike.class.partition((TraversableViewLike)this, p);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> init() {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableViewLike.class.init((TraversableViewLike)this);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> slice(int from, int until) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableViewLike.class.slice((TraversableViewLike)this, from, until);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> dropWhile(Function1 p) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableViewLike.class.dropWhile((TraversableViewLike)this, p);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> takeWhile(Function1 p) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableViewLike.class.takeWhile((TraversableViewLike)this, p);
/*      */     }
/*      */     
/*      */     public Tuple2<StreamView<A, Stream<A>>, StreamView<A, Stream<A>>> span(Function1 p) {
/*  945 */       return TraversableViewLike.class.span((TraversableViewLike)this, p);
/*      */     }
/*      */     
/*      */     public Tuple2<StreamView<A, Stream<A>>, StreamView<A, Stream<A>>> splitAt(int n) {
/*  945 */       return TraversableViewLike.class.splitAt((TraversableViewLike)this, n);
/*      */     }
/*      */     
/*      */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  945 */       return (That)TraversableViewLike.class.scanLeft((TraversableViewLike)this, z, op, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  945 */       return (That)TraversableViewLike.class.scanRight((TraversableViewLike)this, z, op, bf);
/*      */     }
/*      */     
/*      */     public <K> Map<K, StreamView<A, Stream<A>>> groupBy(Function1 f) {
/*  945 */       return TraversableViewLike.class.groupBy((TraversableViewLike)this, f);
/*      */     }
/*      */     
/*      */     public <A1, A2> Tuple2<TraversableViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A1>, TraversableViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A2>> unzip(Function1 asPair) {
/*  945 */       return TraversableViewLike.class.unzip((TraversableViewLike)this, asPair);
/*      */     }
/*      */     
/*      */     public <A1, A2, A3> Tuple3<TraversableViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A1>, TraversableViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A2>, TraversableViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A3>> unzip3(Function1 asTriple) {
/*  945 */       return TraversableViewLike.class.unzip3((TraversableViewLike)this, asTriple);
/*      */     }
/*      */     
/*      */     public String toString() {
/*  945 */       return TraversableViewLike.class.toString((TraversableViewLike)this);
/*      */     }
/*      */     
/*      */     public String viewToString() {
/*  945 */       return GenTraversableViewLike.class.viewToString((GenTraversableViewLike)this);
/*      */     }
/*      */     
/*      */     public Seq<A> thisSeq() {
/*  945 */       return ViewMkString.class.thisSeq((ViewMkString)this);
/*      */     }
/*      */     
/*      */     public String mkString() {
/*  945 */       return ViewMkString.class.mkString((ViewMkString)this);
/*      */     }
/*      */     
/*      */     public String mkString(String sep) {
/*  945 */       return ViewMkString.class.mkString((ViewMkString)this, sep);
/*      */     }
/*      */     
/*      */     public String mkString(String start, String sep, String end) {
/*  945 */       return ViewMkString.class.mkString((ViewMkString)this, start, sep, end);
/*      */     }
/*      */     
/*      */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  945 */       return ViewMkString.class.addString((ViewMkString)this, b, start, sep, end);
/*      */     }
/*      */     
/*      */     public GenericCompanion<Seq> companion() {
/*  945 */       return Seq.class.companion((Seq)this);
/*      */     }
/*      */     
/*      */     public Seq<A> seq() {
/*  945 */       return Seq.class.seq((Seq)this);
/*      */     }
/*      */     
/*      */     public Seq<A> thisCollection() {
/*  945 */       return SeqLike.class.thisCollection((SeqLike)this);
/*      */     }
/*      */     
/*      */     public Seq<A> toCollection(Object repr) {
/*  945 */       return SeqLike.class.toCollection((SeqLike)this, repr);
/*      */     }
/*      */     
/*      */     public Combiner<A, ParSeq<A>> parCombiner() {
/*  945 */       return SeqLike.class.parCombiner((SeqLike)this);
/*      */     }
/*      */     
/*      */     public int lengthCompare(int len) {
/*  945 */       return SeqLike.class.lengthCompare((SeqLike)this, len);
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/*  945 */       return SeqLike.class.isEmpty((SeqLike)this);
/*      */     }
/*      */     
/*      */     public int size() {
/*  945 */       return SeqLike.class.size((SeqLike)this);
/*      */     }
/*      */     
/*      */     public int segmentLength(Function1 p, int from) {
/*  945 */       return SeqLike.class.segmentLength((SeqLike)this, p, from);
/*      */     }
/*      */     
/*      */     public int indexWhere(Function1 p, int from) {
/*  945 */       return SeqLike.class.indexWhere((SeqLike)this, p, from);
/*      */     }
/*      */     
/*      */     public int lastIndexWhere(Function1 p, int end) {
/*  945 */       return SeqLike.class.lastIndexWhere((SeqLike)this, p, end);
/*      */     }
/*      */     
/*      */     public Iterator<StreamView<A, Stream<A>>> permutations() {
/*  945 */       return SeqLike.class.permutations((SeqLike)this);
/*      */     }
/*      */     
/*      */     public Iterator<StreamView<A, Stream<A>>> combinations(int n) {
/*  945 */       return SeqLike.class.combinations((SeqLike)this, n);
/*      */     }
/*      */     
/*      */     public Iterator<A> reverseIterator() {
/*  945 */       return SeqLike.class.reverseIterator((SeqLike)this);
/*      */     }
/*      */     
/*      */     public <B> boolean startsWith(GenSeq that, int offset) {
/*  945 */       return SeqLike.class.startsWith((SeqLike)this, that, offset);
/*      */     }
/*      */     
/*      */     public <B> boolean endsWith(GenSeq that) {
/*  945 */       return SeqLike.class.endsWith((SeqLike)this, that);
/*      */     }
/*      */     
/*      */     public <B> int indexOfSlice(GenSeq that) {
/*  945 */       return SeqLike.class.indexOfSlice((SeqLike)this, that);
/*      */     }
/*      */     
/*      */     public <B> int indexOfSlice(GenSeq that, int from) {
/*  945 */       return SeqLike.class.indexOfSlice((SeqLike)this, that, from);
/*      */     }
/*      */     
/*      */     public <B> int lastIndexOfSlice(GenSeq that) {
/*  945 */       return SeqLike.class.lastIndexOfSlice((SeqLike)this, that);
/*      */     }
/*      */     
/*      */     public <B> int lastIndexOfSlice(GenSeq that, int end) {
/*  945 */       return SeqLike.class.lastIndexOfSlice((SeqLike)this, that, end);
/*      */     }
/*      */     
/*      */     public <B> boolean containsSlice(GenSeq that) {
/*  945 */       return SeqLike.class.containsSlice((SeqLike)this, that);
/*      */     }
/*      */     
/*      */     public boolean contains(Object elem) {
/*  945 */       return SeqLike.class.contains((SeqLike)this, elem);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> distinct() {
/*  945 */       return (StreamView<A, Stream<A>>)SeqLike.class.distinct((SeqLike)this);
/*      */     }
/*      */     
/*      */     public <B> boolean corresponds(GenSeq that, Function2 p) {
/*  945 */       return SeqLike.class.corresponds((SeqLike)this, that, p);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> sortWith(Function2 lt) {
/*  945 */       return (StreamView<A, Stream<A>>)SeqLike.class.sortWith((SeqLike)this, lt);
/*      */     }
/*      */     
/*      */     public <B> StreamView<A, Stream<A>> sortBy(Function1 f, Ordering ord) {
/*  945 */       return (StreamView<A, Stream<A>>)SeqLike.class.sortBy((SeqLike)this, f, ord);
/*      */     }
/*      */     
/*      */     public Seq<A> toSeq() {
/*  945 */       return SeqLike.class.toSeq((SeqLike)this);
/*      */     }
/*      */     
/*      */     public Range indices() {
/*  945 */       return SeqLike.class.indices((SeqLike)this);
/*      */     }
/*      */     
/*      */     public Object view() {
/*  945 */       return SeqLike.class.view((SeqLike)this);
/*      */     }
/*      */     
/*      */     public SeqView<A, StreamView<A, Stream<A>>> view(int from, int until) {
/*  945 */       return SeqLike.class.view((SeqLike)this, from, until);
/*      */     }
/*      */     
/*      */     public boolean isDefinedAt(int idx) {
/*  945 */       return GenSeqLike.class.isDefinedAt((GenSeqLike)this, idx);
/*      */     }
/*      */     
/*      */     public int prefixLength(Function1 p) {
/*  945 */       return GenSeqLike.class.prefixLength((GenSeqLike)this, p);
/*      */     }
/*      */     
/*      */     public int indexWhere(Function1 p) {
/*  945 */       return GenSeqLike.class.indexWhere((GenSeqLike)this, p);
/*      */     }
/*      */     
/*      */     public <B> int indexOf(Object elem) {
/*  945 */       return GenSeqLike.class.indexOf((GenSeqLike)this, elem);
/*      */     }
/*      */     
/*      */     public <B> int indexOf(Object elem, int from) {
/*  945 */       return GenSeqLike.class.indexOf((GenSeqLike)this, elem, from);
/*      */     }
/*      */     
/*      */     public <B> int lastIndexOf(Object elem) {
/*  945 */       return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem);
/*      */     }
/*      */     
/*      */     public <B> int lastIndexOf(Object elem, int end) {
/*  945 */       return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem, end);
/*      */     }
/*      */     
/*      */     public int lastIndexWhere(Function1 p) {
/*  945 */       return GenSeqLike.class.lastIndexWhere((GenSeqLike)this, p);
/*      */     }
/*      */     
/*      */     public <B> boolean startsWith(GenSeq that) {
/*  945 */       return GenSeqLike.class.startsWith((GenSeqLike)this, that);
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  945 */       return GenSeqLike.class.hashCode((GenSeqLike)this);
/*      */     }
/*      */     
/*      */     public boolean equals(Object that) {
/*  945 */       return GenSeqLike.class.equals((GenSeqLike)this, that);
/*      */     }
/*      */     
/*      */     public <U> void foreach(Function1 f) {
/*  945 */       IterableLike.class.foreach((IterableLike)this, f);
/*      */     }
/*      */     
/*      */     public boolean forall(Function1 p) {
/*  945 */       return IterableLike.class.forall((IterableLike)this, p);
/*      */     }
/*      */     
/*      */     public boolean exists(Function1 p) {
/*  945 */       return IterableLike.class.exists((IterableLike)this, p);
/*      */     }
/*      */     
/*      */     public Option<A> find(Function1 p) {
/*  945 */       return IterableLike.class.find((IterableLike)this, p);
/*      */     }
/*      */     
/*      */     public <B> B foldRight(Object z, Function2 op) {
/*  945 */       return (B)IterableLike.class.foldRight((IterableLike)this, z, op);
/*      */     }
/*      */     
/*      */     public <B> B reduceRight(Function2 op) {
/*  945 */       return (B)IterableLike.class.reduceRight((IterableLike)this, op);
/*      */     }
/*      */     
/*      */     public Iterable<A> toIterable() {
/*  945 */       return IterableLike.class.toIterable((IterableLike)this);
/*      */     }
/*      */     
/*      */     public Iterator<A> toIterator() {
/*  945 */       return IterableLike.class.toIterator((IterableLike)this);
/*      */     }
/*      */     
/*      */     public A head() {
/*  945 */       return (A)IterableLike.class.head((IterableLike)this);
/*      */     }
/*      */     
/*      */     public Iterator<StreamView<A, Stream<A>>> sliding(int size) {
/*  945 */       return IterableLike.class.sliding((IterableLike)this, size);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> takeRight(int n) {
/*  945 */       return (StreamView<A, Stream<A>>)IterableLike.class.takeRight((IterableLike)this, n);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> dropRight(int n) {
/*  945 */       return (StreamView<A, Stream<A>>)IterableLike.class.dropRight((IterableLike)this, n);
/*      */     }
/*      */     
/*      */     public <B> void copyToArray(Object xs, int start, int len) {
/*  945 */       IterableLike.class.copyToArray((IterableLike)this, xs, start, len);
/*      */     }
/*      */     
/*      */     public <B> boolean sameElements(GenIterable that) {
/*  945 */       return IterableLike.class.sameElements((IterableLike)this, that);
/*      */     }
/*      */     
/*      */     public Stream<A> toStream() {
/*  945 */       return IterableLike.class.toStream((IterableLike)this);
/*      */     }
/*      */     
/*      */     public boolean canEqual(Object that) {
/*  945 */       return IterableLike.class.canEqual((IterableLike)this, that);
/*      */     }
/*      */     
/*      */     public <B> Builder<B, Seq<B>> genericBuilder() {
/*  945 */       return GenericTraversableTemplate.class.genericBuilder((GenericTraversableTemplate)this);
/*      */     }
/*      */     
/*      */     public <B> Seq<Seq<B>> transpose(Function1 asTraversable) {
/*  945 */       return (Seq<Seq<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> repr() {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableLike.class.repr((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public final boolean isTraversableAgain() {
/*  945 */       return TraversableLike.class.isTraversableAgain((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public boolean hasDefiniteSize() {
/*  945 */       return TraversableLike.class.hasDefiniteSize((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/*  945 */       return (That)TraversableLike.class.$plus$plus$colon((TraversableLike)this, that, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/*  945 */       return (That)TraversableLike.class.$plus$plus$colon((TraversableLike)this, that, bf);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> filterNot(Function1 p) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableLike.class.filterNot((TraversableLike)this, p);
/*      */     }
/*      */     
/*      */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/*  945 */       return (That)TraversableLike.class.scan((TraversableLike)this, z, op, cbf);
/*      */     }
/*      */     
/*      */     public Option<A> headOption() {
/*  945 */       return TraversableLike.class.headOption((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> tail() {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableLike.class.tail((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public A last() {
/*  945 */       return (A)TraversableLike.class.last((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public Option<A> lastOption() {
/*  945 */       return TraversableLike.class.lastOption((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> sliceWithKnownDelta(int from, int until, int delta) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableLike.class.sliceWithKnownDelta((TraversableLike)this, from, until, delta);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> sliceWithKnownBound(int from, int until) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableLike.class.sliceWithKnownBound((TraversableLike)this, from, until);
/*      */     }
/*      */     
/*      */     public Iterator<StreamView<A, Stream<A>>> tails() {
/*  945 */       return TraversableLike.class.tails((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public Iterator<StreamView<A, Stream<A>>> inits() {
/*  945 */       return TraversableLike.class.inits((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public Traversable<A> toTraversable() {
/*  945 */       return TraversableLike.class.toTraversable((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public <Col> Col to(CanBuildFrom cbf) {
/*  945 */       return (Col)TraversableLike.class.to((TraversableLike)this, cbf);
/*      */     }
/*      */     
/*      */     public ParSeq<A> par() {
/*  945 */       return (ParSeq<A>)Parallelizable.class.par((Parallelizable)this);
/*      */     }
/*      */     
/*      */     public List<A> reversed() {
/*  945 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*      */     }
/*      */     
/*      */     public boolean nonEmpty() {
/*  945 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*      */     }
/*      */     
/*      */     public int count(Function1 p) {
/*  945 */       return TraversableOnce.class.count((TraversableOnce)this, p);
/*      */     }
/*      */     
/*      */     public <B> Option<B> collectFirst(PartialFunction pf) {
/*  945 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*      */     }
/*      */     
/*      */     public <B> B $div$colon(Object z, Function2 op) {
/*  945 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*      */     }
/*      */     
/*      */     public <B> B $colon$bslash(Object z, Function2 op) {
/*  945 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*      */     }
/*      */     
/*      */     public <B> B foldLeft(Object z, Function2 op) {
/*  945 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*      */     }
/*      */     
/*      */     public <B> B reduceLeft(Function2 op) {
/*  945 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*      */     }
/*      */     
/*      */     public <B> Option<B> reduceLeftOption(Function2 op) {
/*  945 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*      */     }
/*      */     
/*      */     public <B> Option<B> reduceRightOption(Function2 op) {
/*  945 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*      */     }
/*      */     
/*      */     public <A1> A1 reduce(Function2 op) {
/*  945 */       return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*      */     }
/*      */     
/*      */     public <A1> Option<A1> reduceOption(Function2 op) {
/*  945 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*      */     }
/*      */     
/*      */     public <A1> A1 fold(Object z, Function2 op) {
/*  945 */       return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*      */     }
/*      */     
/*      */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  945 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*      */     }
/*      */     
/*      */     public <B> B sum(Numeric num) {
/*  945 */       return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*      */     }
/*      */     
/*      */     public <B> B product(Numeric num) {
/*  945 */       return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*      */     }
/*      */     
/*      */     public <B> A min(Ordering cmp) {
/*  945 */       return (A)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*      */     }
/*      */     
/*      */     public <B> A max(Ordering cmp) {
/*  945 */       return (A)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*      */     }
/*      */     
/*      */     public <B> A maxBy(Function1 f, Ordering cmp) {
/*  945 */       return (A)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*      */     }
/*      */     
/*      */     public <B> A minBy(Function1 f, Ordering cmp) {
/*  945 */       return (A)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*      */     }
/*      */     
/*      */     public <B> void copyToBuffer(Buffer dest) {
/*  945 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*      */     }
/*      */     
/*      */     public <B> void copyToArray(Object xs, int start) {
/*  945 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*      */     }
/*      */     
/*      */     public <B> void copyToArray(Object xs) {
/*  945 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*      */     }
/*      */     
/*      */     public <B> Object toArray(ClassTag evidence$1) {
/*  945 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*      */     }
/*      */     
/*      */     public List<A> toList() {
/*  945 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*      */     }
/*      */     
/*      */     public IndexedSeq<A> toIndexedSeq() {
/*  945 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*      */     }
/*      */     
/*      */     public <B> Buffer<B> toBuffer() {
/*  945 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*      */     }
/*      */     
/*      */     public <B> Set<B> toSet() {
/*  945 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*      */     }
/*      */     
/*      */     public Vector<A> toVector() {
/*  945 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*      */     }
/*      */     
/*      */     public <T, U> Map<T, U> toMap(scala.Predef$.less.colon.less ev) {
/*  945 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*      */     }
/*      */     
/*      */     public StringBuilder addString(StringBuilder b, String sep) {
/*  945 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*      */     }
/*      */     
/*      */     public StringBuilder addString(StringBuilder b) {
/*  945 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*      */     }
/*      */     
/*      */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  945 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*      */     }
/*      */     
/*      */     public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/*  945 */       return PartialFunction.class.orElse((PartialFunction)this, that);
/*      */     }
/*      */     
/*      */     public <C> PartialFunction<Object, C> andThen(Function1 k) {
/*  945 */       return PartialFunction.class.andThen((PartialFunction)this, k);
/*      */     }
/*      */     
/*      */     public Function1<Object, Option<A>> lift() {
/*  945 */       return PartialFunction.class.lift((PartialFunction)this);
/*      */     }
/*      */     
/*      */     public <A1, B1> B1 applyOrElse(Object x, Function1 default) {
/*  945 */       return (B1)PartialFunction.class.applyOrElse((PartialFunction)this, x, default);
/*      */     }
/*      */     
/*      */     public <U> Function1<Object, Object> runWith(Function1 action) {
/*  945 */       return PartialFunction.class.runWith((PartialFunction)this, action);
/*      */     }
/*      */     
/*      */     public boolean apply$mcZD$sp(double v1) {
/*  945 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public double apply$mcDD$sp(double v1) {
/*  945 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public float apply$mcFD$sp(double v1) {
/*  945 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public int apply$mcID$sp(double v1) {
/*  945 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public long apply$mcJD$sp(double v1) {
/*  945 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public void apply$mcVD$sp(double v1) {
/*  945 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public boolean apply$mcZF$sp(float v1) {
/*  945 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public double apply$mcDF$sp(float v1) {
/*  945 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public float apply$mcFF$sp(float v1) {
/*  945 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public int apply$mcIF$sp(float v1) {
/*  945 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public long apply$mcJF$sp(float v1) {
/*  945 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public void apply$mcVF$sp(float v1) {
/*  945 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public boolean apply$mcZI$sp(int v1) {
/*  945 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public double apply$mcDI$sp(int v1) {
/*  945 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public float apply$mcFI$sp(int v1) {
/*  945 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public int apply$mcII$sp(int v1) {
/*  945 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public long apply$mcJI$sp(int v1) {
/*  945 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public void apply$mcVI$sp(int v1) {
/*  945 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public boolean apply$mcZJ$sp(long v1) {
/*  945 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public double apply$mcDJ$sp(long v1) {
/*  945 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public float apply$mcFJ$sp(long v1) {
/*  945 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public int apply$mcIJ$sp(long v1) {
/*  945 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public long apply$mcJJ$sp(long v1) {
/*  945 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public void apply$mcVJ$sp(long v1) {
/*  945 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, A> compose(Function1 g) {
/*  945 */       return Function1.class.compose((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public Stream$$anon$1(Stream $outer) {
/*  945 */       Function1.class.$init$((Function1)this);
/*  945 */       PartialFunction.class.$init$((PartialFunction)this);
/*  945 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  945 */       TraversableOnce.class.$init$((TraversableOnce)this);
/*  945 */       Parallelizable.class.$init$((Parallelizable)this);
/*  945 */       TraversableLike.class.$init$((TraversableLike)this);
/*  945 */       GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/*  945 */       GenTraversable.class.$init$((GenTraversable)this);
/*  945 */       Traversable.class.$init$((Traversable)this);
/*  945 */       GenIterable.class.$init$((GenIterable)this);
/*  945 */       IterableLike.class.$init$((IterableLike)this);
/*  945 */       Iterable.class.$init$((Iterable)this);
/*  945 */       GenSeqLike.class.$init$((GenSeqLike)this);
/*  945 */       GenSeq.class.$init$((GenSeq)this);
/*  945 */       SeqLike.class.$init$((SeqLike)this);
/*  945 */       Seq.class.$init$((Seq)this);
/*  945 */       ViewMkString.class.$init$((ViewMkString)this);
/*  945 */       GenTraversableViewLike.class.$init$((GenTraversableViewLike)this);
/*  945 */       TraversableViewLike.class.$init$((TraversableViewLike)this);
/*  945 */       GenIterableViewLike.class.$init$((GenIterableViewLike)this);
/*  945 */       IterableViewLike.class.$init$((IterableViewLike)this);
/*  945 */       GenSeqViewLike.class.$init$((GenSeqViewLike)this);
/*  945 */       SeqViewLike.class.$init$(this);
/*  945 */       StreamViewLike$class.$init$(this);
/*      */     }
/*      */     
/*      */     private Stream underlying$lzycompute() {
/*  946 */       synchronized (this) {
/*  946 */         if (!this.bitmap$0) {
/*  946 */           this.underlying = (Stream<A>)this.$outer.repr();
/*  946 */           this.bitmap$0 = true;
/*      */         } 
/*      */         /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/Stream$$anon$1}} */
/*  946 */         return this.underlying;
/*      */       } 
/*      */     }
/*      */     
/*      */     public Stream<A> underlying() {
/*  946 */       return this.bitmap$0 ? this.underlying : underlying$lzycompute();
/*      */     }
/*      */     
/*      */     public Iterator<A> iterator() {
/*  947 */       return this.$outer.iterator();
/*      */     }
/*      */     
/*      */     public int length() {
/*  948 */       return this.$outer.length();
/*      */     }
/*      */     
/*      */     public A apply(int idx) {
/*  949 */       return this.$outer.apply(idx);
/*      */     }
/*      */   }
/*      */   
/*      */   private Stream$() {
/*  998 */     MODULE$ = this;
/*      */   }
/*      */   
/*      */   public <A> CanBuildFrom<Stream<?>, A, Stream<A>> canBuildFrom() {
/* 1010 */     return (CanBuildFrom<Stream<?>, A, Stream<A>>)new Stream.StreamCanBuildFrom();
/*      */   }
/*      */   
/*      */   public <A> Builder<A, Stream<A>> newBuilder() {
/* 1013 */     return (Builder<A, Stream<A>>)new Stream.StreamBuilder();
/*      */   }
/*      */   
/*      */   public <A> Stream<A> empty() {
/* 1034 */     return Stream.Empty$.MODULE$;
/*      */   }
/*      */   
/*      */   public <A> Stream<A> apply(Seq xs) {
/* 1037 */     return xs.toStream();
/*      */   }
/*      */   
/*      */   public <A> Stream.ConsWrapper<A> consWrapper(Function0<Stream<A>> stream) {
/* 1051 */     return new Stream.ConsWrapper<A>(stream);
/*      */   }
/*      */   
/*      */   public static class $hash$colon$colon$ {
/*      */     public static final $hash$colon$colon$ MODULE$;
/*      */     
/*      */     public $hash$colon$colon$() {
/* 1055 */       MODULE$ = this;
/*      */     }
/*      */     
/*      */     public <A> Option<Tuple2<A, Stream<A>>> unapply(Stream xs) {
/* 1057 */       return xs.isEmpty() ? (Option<Tuple2<A, Stream<A>>>)scala.None$.MODULE$ : 
/* 1058 */         (Option<Tuple2<A, Stream<A>>>)new Some(new Tuple2(xs.head(), xs.tail()));
/*      */     }
/*      */   }
/*      */   
/*      */   public <A> Stream<A> iterate(Object start, Function1 f) {
/* 1098 */     Stream$$anonfun$iterate$1 stream$$anonfun$iterate$1 = new Stream$$anonfun$iterate$1(start, f);
/* 1098 */     Stream.cons$ cons$ = Stream.cons$.MODULE$;
/* 1098 */     return new Stream.Cons<A>((A)start, (Function0<Stream<A>>)stream$$anonfun$iterate$1);
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$iterate$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Object start$2;
/*      */     
/*      */     private final Function1 f$6;
/*      */     
/*      */     public final Stream<A> apply() {
/* 1098 */       return Stream$.MODULE$.iterate((A)this.f$6.apply(this.start$2), this.f$6);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$iterate$1(Object start$2, Function1 f$6) {}
/*      */   }
/*      */   
/*      */   public <A> Stream<A> iterate(Object start, int len, Function1<A, A> f) {
/* 1101 */     return iterate((A)start, f).take(len);
/*      */   }
/*      */   
/*      */   public Stream<Object> from(int start, int step) {
/* 1112 */     Stream$$anonfun$from$1 stream$$anonfun$from$1 = new Stream$$anonfun$from$1(start, step);
/* 1112 */     Integer integer = BoxesRunTime.boxToInteger(start);
/* 1112 */     Stream.cons$ cons$ = Stream.cons$.MODULE$;
/* 1112 */     return new Stream.Cons(integer, (Function0<Stream<Object>>)stream$$anonfun$from$1);
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$from$1 extends AbstractFunction0<Stream<Object>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final int start$1;
/*      */     
/*      */     private final int step$1;
/*      */     
/*      */     public final Stream<Object> apply() {
/* 1112 */       return Stream$.MODULE$.from(this.start$1 + this.step$1, this.step$1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$from$1(int start$1, int step$1) {}
/*      */   }
/*      */   
/*      */   public Stream<Object> from(int start) {
/* 1120 */     return from(start, 1);
/*      */   }
/*      */   
/*      */   public <A> Stream<A> continually(Function0 elem) {
/* 1129 */     Stream$$anonfun$continually$1 stream$$anonfun$continually$1 = new Stream$$anonfun$continually$1(elem);
/* 1129 */     Object object = elem.apply();
/* 1129 */     Stream.cons$ cons$ = Stream.cons$.MODULE$;
/* 1129 */     return new Stream.Cons<A>((A)object, (Function0<Stream<A>>)stream$$anonfun$continually$1);
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$continually$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function0 elem$3;
/*      */     
/*      */     public final Stream<A> apply() {
/* 1129 */       return Stream$.MODULE$.continually(this.elem$3);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$continually$1(Function0 elem$3) {}
/*      */   }
/*      */   
/*      */   public <A> Stream<A> fill(int n, Function0 elem) {
/* 1132 */     Stream$$anonfun$fill$1 stream$$anonfun$fill$1 = new Stream$$anonfun$fill$1(n, elem);
/* 1132 */     Object object = elem.apply();
/* 1132 */     Stream.cons$ cons$ = Stream.cons$.MODULE$;
/* 1132 */     return (n <= 0) ? Stream.Empty$.MODULE$ : new Stream.Cons<A>((A)object, (Function0<Stream<A>>)stream$$anonfun$fill$1);
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$fill$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final int n$2;
/*      */     
/*      */     private final Function0 elem$2;
/*      */     
/*      */     public final Stream<A> apply() {
/* 1132 */       return Stream$.MODULE$.fill(this.n$2 - 1, this.elem$2);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$fill$1(int n$2, Function0 elem$2) {}
/*      */   }
/*      */   
/*      */   public final Stream scala$collection$immutable$Stream$$loop$6(int i, int n$3, Function1 f$7) {
/* 1136 */     Stream$$anonfun$scala$collection$immutable$Stream$$loop$6$1 stream$$anonfun$scala$collection$immutable$Stream$$loop$6$1 = new Stream$$anonfun$scala$collection$immutable$Stream$$loop$6$1(n$3, f$7, i);
/* 1136 */     Object object = f$7.apply(BoxesRunTime.boxToInteger(i));
/* 1136 */     Stream.cons$ cons$ = Stream.cons$.MODULE$;
/* 1136 */     return (i >= n$3) ? Stream.Empty$.MODULE$ : new Stream.Cons(object, (Function0<Stream<Object>>)stream$$anonfun$scala$collection$immutable$Stream$$loop$6$1);
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$scala$collection$immutable$Stream$$loop$6$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final int n$3;
/*      */     
/*      */     private final Function1 f$7;
/*      */     
/*      */     private final int i$1;
/*      */     
/*      */     public final Stream<A> apply() {
/* 1136 */       return Stream$.MODULE$.scala$collection$immutable$Stream$$loop$6(this.i$1 + 1, this.n$3, this.f$7);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$scala$collection$immutable$Stream$$loop$6$1(int n$3, Function1 f$7, int i$1) {}
/*      */   }
/*      */   
/*      */   public <A> Stream<A> tabulate(int n, Function1 f) {
/* 1137 */     return scala$collection$immutable$Stream$$loop$6(0, n, f);
/*      */   }
/*      */   
/*      */   public <T> Stream<T> range(Object start, Object end, Object step, Integral evidence$1) {
/* 1141 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 1141 */     Integral num = evidence$1;
/* 1145 */     Stream$$anonfun$range$1 stream$$anonfun$range$1 = new Stream$$anonfun$range$1(start, end, step, evidence$1, num);
/* 1145 */     Stream.cons$ cons$ = Stream.cons$.MODULE$;
/* 1145 */     return (num.mkOrderingOps(step).$less(num.zero()) ? num.mkOrderingOps(start).$less$eq(end) : num.mkOrderingOps(end).$less$eq(start)) ? Stream.Empty$.MODULE$ : new Stream.Cons<T>((T)start, (Function0<Stream<T>>)stream$$anonfun$range$1);
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$range$1 extends AbstractFunction0<Stream<T>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Object start$3;
/*      */     
/*      */     private final Object end$2;
/*      */     
/*      */     private final Object step$2;
/*      */     
/*      */     private final Integral evidence$1$1;
/*      */     
/*      */     private final Integral num$1;
/*      */     
/*      */     public final Stream<T> apply() {
/* 1145 */       return Stream$.MODULE$.range((T)this.num$1.mkNumericOps(this.start$3).$plus(this.step$2), (T)this.end$2, (T)this.step$2, this.evidence$1$1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$range$1(Object start$3, Object end$2, Object step$2, Integral evidence$1$1, Integral num$1) {}
/*      */   }
/*      */   
/*      */   public <A> Stream.Cons<A> filteredTail(Stream<Object> stream, Function1 p) {
/* 1149 */     Stream$$anonfun$filteredTail$1 stream$$anonfun$filteredTail$1 = new Stream$$anonfun$filteredTail$1(stream, p);
/* 1149 */     A a = (A)stream.head();
/* 1149 */     Stream.cons$ cons$ = Stream.cons$.MODULE$;
/* 1149 */     return new Stream.Cons<A>(a, (Function0<Stream<A>>)stream$$anonfun$filteredTail$1);
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$filteredTail$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Stream stream$2;
/*      */     
/*      */     private final Function1 p$1;
/*      */     
/*      */     public final Stream<A> apply() {
/* 1149 */       return ((Stream<A>)this.stream$2.tail()).filter(this.p$1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$filteredTail$1(Stream stream$2, Function1 p$1) {}
/*      */   }
/*      */   
/*      */   public <A, B, That> Stream.Cons<B> collectedTail(Stream stream, PartialFunction pf, CanBuildFrom bf) {
/* 1153 */     Stream$$anonfun$collectedTail$1 stream$$anonfun$collectedTail$1 = new Stream$$anonfun$collectedTail$1(stream, pf, bf);
/* 1153 */     Object object = pf.apply(stream.head());
/* 1153 */     Stream.cons$ cons$ = Stream.cons$.MODULE$;
/* 1153 */     return new Stream.Cons<B>((B)object, (Function0<Stream<B>>)stream$$anonfun$collectedTail$1);
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$collectedTail$1 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Stream stream$1;
/*      */     
/*      */     private final PartialFunction pf$1;
/*      */     
/*      */     private final CanBuildFrom bf$1;
/*      */     
/*      */     public final Stream<B> apply() {
/* 1153 */       return (Stream<B>)((Stream)this.stream$1.tail()).collect(this.pf$1, this.bf$1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$collectedTail$1(Stream stream$1, PartialFunction pf$1, CanBuildFrom bf$1) {}
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Stream$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */