/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.FilterMonadic;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.ParIterable;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0012a!\001\002\002\002\0211!aE!cgR\024\030m\031;Ue\0064XM]:bE2,'BA\002\005\003)\031w\016\0347fGRLwN\034\006\002\013\005)1oY1mCV\021qAE\n\004\001!a\001CA\005\013\033\005!\021BA\006\005\005\031\te.\037*fMB\031QB\004\t\016\003\tI!a\004\002\003\027Q\023\030M^3sg\006\024G.\032\t\003#Ia\001\001\002\004\024\001\021\025\r!\006\002\002\003\016\001\021C\001\f\032!\tIq#\003\002\031\t\t9aj\034;iS:<\007CA\005\033\023\tYBAA\002B]fDQ!\b\001\005\002y\ta\001P5oSRtD#A\020\021\0075\001\001\003")
/*     */ public abstract class AbstractTraversable<A> implements Traversable<A> {
/*     */   public GenericCompanion<Traversable> companion() {
/* 105 */     return Traversable$class.companion(this);
/*     */   }
/*     */   
/*     */   public Traversable<A> seq() {
/* 105 */     return Traversable$class.seq(this);
/*     */   }
/*     */   
/*     */   public Builder<A, Traversable<A>> newBuilder() {
/* 105 */     return GenericTraversableTemplate.class.newBuilder(this);
/*     */   }
/*     */   
/*     */   public <B> Builder<B, Traversable<B>> genericBuilder() {
/* 105 */     return GenericTraversableTemplate.class.genericBuilder(this);
/*     */   }
/*     */   
/*     */   public <A1, A2> Tuple2<Traversable<A1>, Traversable<A2>> unzip(Function1 asPair) {
/* 105 */     return GenericTraversableTemplate.class.unzip(this, asPair);
/*     */   }
/*     */   
/*     */   public <A1, A2, A3> Tuple3<Traversable<A1>, Traversable<A2>, Traversable<A3>> unzip3(Function1 asTriple) {
/* 105 */     return GenericTraversableTemplate.class.unzip3(this, asTriple);
/*     */   }
/*     */   
/*     */   public <B> Traversable<B> flatten(Function1 asTraversable) {
/* 105 */     return (Traversable<B>)GenericTraversableTemplate.class.flatten(this, asTraversable);
/*     */   }
/*     */   
/*     */   public <B> Traversable<Traversable<B>> transpose(Function1 asTraversable) {
/* 105 */     return (Traversable<Traversable<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*     */   }
/*     */   
/*     */   public Traversable<A> repr() {
/* 105 */     return (Traversable<A>)TraversableLike$class.repr(this);
/*     */   }
/*     */   
/*     */   public final boolean isTraversableAgain() {
/* 105 */     return TraversableLike$class.isTraversableAgain(this);
/*     */   }
/*     */   
/*     */   public Traversable<A> thisCollection() {
/* 105 */     return TraversableLike$class.thisCollection(this);
/*     */   }
/*     */   
/*     */   public Traversable<A> toCollection(Object repr) {
/* 105 */     return TraversableLike$class.toCollection(this, repr);
/*     */   }
/*     */   
/*     */   public Combiner<A, ParIterable<A>> parCombiner() {
/* 105 */     return TraversableLike$class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 105 */     return TraversableLike$class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/* 105 */     return TraversableLike$class.hasDefiniteSize(this);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/* 105 */     return (That)TraversableLike$class.$plus$plus(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 105 */     return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 105 */     return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 105 */     return (That)TraversableLike$class.map(this, f, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 105 */     return (That)TraversableLike$class.flatMap(this, f, bf);
/*     */   }
/*     */   
/*     */   public Traversable<A> filter(Function1 p) {
/* 105 */     return (Traversable<A>)TraversableLike$class.filter(this, p);
/*     */   }
/*     */   
/*     */   public Traversable<A> filterNot(Function1 p) {
/* 105 */     return (Traversable<A>)TraversableLike$class.filterNot(this, p);
/*     */   }
/*     */   
/*     */   public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 105 */     return (That)TraversableLike$class.collect(this, pf, bf);
/*     */   }
/*     */   
/*     */   public Tuple2<Traversable<A>, Traversable<A>> partition(Function1 p) {
/* 105 */     return TraversableLike$class.partition(this, p);
/*     */   }
/*     */   
/*     */   public <K> Map<K, Traversable<A>> groupBy(Function1 f) {
/* 105 */     return TraversableLike$class.groupBy(this, f);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/* 105 */     return TraversableLike$class.forall(this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/* 105 */     return TraversableLike$class.exists(this, p);
/*     */   }
/*     */   
/*     */   public Option<A> find(Function1 p) {
/* 105 */     return TraversableLike$class.find(this, p);
/*     */   }
/*     */   
/*     */   public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 105 */     return (That)TraversableLike$class.scan(this, z, op, cbf);
/*     */   }
/*     */   
/*     */   public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 105 */     return (That)TraversableLike$class.scanLeft(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 105 */     return (That)TraversableLike$class.scanRight(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public A head() {
/* 105 */     return (A)TraversableLike$class.head(this);
/*     */   }
/*     */   
/*     */   public Option<A> headOption() {
/* 105 */     return TraversableLike$class.headOption(this);
/*     */   }
/*     */   
/*     */   public Traversable<A> tail() {
/* 105 */     return (Traversable<A>)TraversableLike$class.tail(this);
/*     */   }
/*     */   
/*     */   public A last() {
/* 105 */     return (A)TraversableLike$class.last(this);
/*     */   }
/*     */   
/*     */   public Option<A> lastOption() {
/* 105 */     return TraversableLike$class.lastOption(this);
/*     */   }
/*     */   
/*     */   public Traversable<A> init() {
/* 105 */     return (Traversable<A>)TraversableLike$class.init(this);
/*     */   }
/*     */   
/*     */   public Traversable<A> take(int n) {
/* 105 */     return (Traversable<A>)TraversableLike$class.take(this, n);
/*     */   }
/*     */   
/*     */   public Traversable<A> drop(int n) {
/* 105 */     return (Traversable<A>)TraversableLike$class.drop(this, n);
/*     */   }
/*     */   
/*     */   public Traversable<A> slice(int from, int until) {
/* 105 */     return (Traversable<A>)TraversableLike$class.slice(this, from, until);
/*     */   }
/*     */   
/*     */   public Traversable<A> sliceWithKnownDelta(int from, int until, int delta) {
/* 105 */     return (Traversable<A>)TraversableLike$class.sliceWithKnownDelta(this, from, until, delta);
/*     */   }
/*     */   
/*     */   public Traversable<A> sliceWithKnownBound(int from, int until) {
/* 105 */     return (Traversable<A>)TraversableLike$class.sliceWithKnownBound(this, from, until);
/*     */   }
/*     */   
/*     */   public Traversable<A> takeWhile(Function1 p) {
/* 105 */     return (Traversable<A>)TraversableLike$class.takeWhile(this, p);
/*     */   }
/*     */   
/*     */   public Traversable<A> dropWhile(Function1 p) {
/* 105 */     return (Traversable<A>)TraversableLike$class.dropWhile(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<Traversable<A>, Traversable<A>> span(Function1 p) {
/* 105 */     return TraversableLike$class.span(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<Traversable<A>, Traversable<A>> splitAt(int n) {
/* 105 */     return TraversableLike$class.splitAt(this, n);
/*     */   }
/*     */   
/*     */   public Iterator<Traversable<A>> tails() {
/* 105 */     return TraversableLike$class.tails(this);
/*     */   }
/*     */   
/*     */   public Iterator<Traversable<A>> inits() {
/* 105 */     return TraversableLike$class.inits(this);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start, int len) {
/* 105 */     TraversableLike$class.copyToArray(this, xs, start, len);
/*     */   }
/*     */   
/*     */   public Traversable<A> toTraversable() {
/* 105 */     return TraversableLike$class.toTraversable(this);
/*     */   }
/*     */   
/*     */   public Iterator<A> toIterator() {
/* 105 */     return TraversableLike$class.toIterator(this);
/*     */   }
/*     */   
/*     */   public Stream<A> toStream() {
/* 105 */     return TraversableLike$class.toStream(this);
/*     */   }
/*     */   
/*     */   public <Col> Col to(CanBuildFrom cbf) {
/* 105 */     return (Col)TraversableLike$class.to(this, cbf);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 105 */     return TraversableLike$class.toString(this);
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/* 105 */     return TraversableLike$class.stringPrefix(this);
/*     */   }
/*     */   
/*     */   public Object view() {
/* 105 */     return TraversableLike$class.view(this);
/*     */   }
/*     */   
/*     */   public TraversableView<A, Traversable<A>> view(int from, int until) {
/* 105 */     return TraversableLike$class.view(this, from, until);
/*     */   }
/*     */   
/*     */   public FilterMonadic<A, Traversable<A>> withFilter(Function1 p) {
/* 105 */     return TraversableLike$class.withFilter(this, p);
/*     */   }
/*     */   
/*     */   public ParIterable<A> par() {
/* 105 */     return (ParIterable<A>)Parallelizable$class.par(this);
/*     */   }
/*     */   
/*     */   public List<A> reversed() {
/* 105 */     return TraversableOnce$class.reversed(this);
/*     */   }
/*     */   
/*     */   public int size() {
/* 105 */     return TraversableOnce$class.size(this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/* 105 */     return TraversableOnce$class.nonEmpty(this);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/* 105 */     return TraversableOnce$class.count(this, p);
/*     */   }
/*     */   
/*     */   public <B> Option<B> collectFirst(PartialFunction pf) {
/* 105 */     return TraversableOnce$class.collectFirst(this, pf);
/*     */   }
/*     */   
/*     */   public <B> B $div$colon(Object z, Function2 op) {
/* 105 */     return (B)TraversableOnce$class.$div$colon(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B $colon$bslash(Object z, Function2 op) {
/* 105 */     return (B)TraversableOnce$class.$colon$bslash(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 op) {
/* 105 */     return (B)TraversableOnce$class.foldLeft(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 op) {
/* 105 */     return (B)TraversableOnce$class.foldRight(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 op) {
/* 105 */     return (B)TraversableOnce$class.reduceLeft(this, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/* 105 */     return (B)TraversableOnce$class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceLeftOption(Function2 op) {
/* 105 */     return TraversableOnce$class.reduceLeftOption(this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceRightOption(Function2 op) {
/* 105 */     return TraversableOnce$class.reduceRightOption(this, op);
/*     */   }
/*     */   
/*     */   public <A1> A1 reduce(Function2 op) {
/* 105 */     return (A1)TraversableOnce$class.reduce(this, op);
/*     */   }
/*     */   
/*     */   public <A1> Option<A1> reduceOption(Function2 op) {
/* 105 */     return TraversableOnce$class.reduceOption(this, op);
/*     */   }
/*     */   
/*     */   public <A1> A1 fold(Object z, Function2 op) {
/* 105 */     return (A1)TraversableOnce$class.fold(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 105 */     return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
/*     */   }
/*     */   
/*     */   public <B> B sum(Numeric num) {
/* 105 */     return (B)TraversableOnce$class.sum(this, num);
/*     */   }
/*     */   
/*     */   public <B> B product(Numeric num) {
/* 105 */     return (B)TraversableOnce$class.product(this, num);
/*     */   }
/*     */   
/*     */   public <B> A min(Ordering cmp) {
/* 105 */     return (A)TraversableOnce$class.min(this, cmp);
/*     */   }
/*     */   
/*     */   public <B> A max(Ordering cmp) {
/* 105 */     return (A)TraversableOnce$class.max(this, cmp);
/*     */   }
/*     */   
/*     */   public <B> A maxBy(Function1 f, Ordering cmp) {
/* 105 */     return (A)TraversableOnce$class.maxBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> A minBy(Function1 f, Ordering cmp) {
/* 105 */     return (A)TraversableOnce$class.minBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> void copyToBuffer(Buffer dest) {
/* 105 */     TraversableOnce$class.copyToBuffer(this, dest);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start) {
/* 105 */     TraversableOnce$class.copyToArray(this, xs, start);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs) {
/* 105 */     TraversableOnce$class.copyToArray(this, xs);
/*     */   }
/*     */   
/*     */   public <B> Object toArray(ClassTag evidence$1) {
/* 105 */     return TraversableOnce$class.toArray(this, evidence$1);
/*     */   }
/*     */   
/*     */   public List<A> toList() {
/* 105 */     return TraversableOnce$class.toList(this);
/*     */   }
/*     */   
/*     */   public Iterable<A> toIterable() {
/* 105 */     return TraversableOnce$class.toIterable(this);
/*     */   }
/*     */   
/*     */   public Seq<A> toSeq() {
/* 105 */     return TraversableOnce$class.toSeq(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<A> toIndexedSeq() {
/* 105 */     return TraversableOnce$class.toIndexedSeq(this);
/*     */   }
/*     */   
/*     */   public <B> Buffer<B> toBuffer() {
/* 105 */     return TraversableOnce$class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public <B> Set<B> toSet() {
/* 105 */     return TraversableOnce$class.toSet(this);
/*     */   }
/*     */   
/*     */   public Vector<A> toVector() {
/* 105 */     return TraversableOnce$class.toVector(this);
/*     */   }
/*     */   
/*     */   public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 105 */     return TraversableOnce$class.toMap(this, ev);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/* 105 */     return TraversableOnce$class.mkString(this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/* 105 */     return TraversableOnce$class.mkString(this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/* 105 */     return TraversableOnce$class.mkString(this);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 105 */     return TraversableOnce$class.addString(this, b, start, sep, end);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String sep) {
/* 105 */     return TraversableOnce$class.addString(this, b, sep);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b) {
/* 105 */     return TraversableOnce$class.addString(this, b);
/*     */   }
/*     */   
/*     */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 105 */     return (A1)GenTraversableOnce$class.$div$colon$bslash(this, z, op);
/*     */   }
/*     */   
/*     */   public AbstractTraversable() {
/* 105 */     GenTraversableOnce$class.$init$(this);
/* 105 */     TraversableOnce$class.$init$(this);
/* 105 */     Parallelizable$class.$init$(this);
/* 105 */     TraversableLike$class.$init$(this);
/* 105 */     GenericTraversableTemplate.class.$init$(this);
/* 105 */     GenTraversable$class.$init$(this);
/* 105 */     Traversable$class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\AbstractTraversable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */