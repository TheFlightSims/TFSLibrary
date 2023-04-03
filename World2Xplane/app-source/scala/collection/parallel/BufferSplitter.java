/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.BufferedIterator;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.DelegatedSignalling;
/*     */ import scala.collection.generic.Signalling;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\00114Q!\001\002\001\005!\021aBQ;gM\026\0248\013\0357jiR,'O\003\002\004\t\005A\001/\031:bY2,GN\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006,\"!\003\013\024\007\001Qa\002\005\002\f\0315\ta!\003\002\016\r\t1\021I\\=SK\032\0042a\004\t\023\033\005\021\021BA\t\003\005AIE/\032:bE2,7\013\0357jiR,'\017\005\002\024)1\001A!B\013\001\005\0049\"!\001+\004\001E\021\001d\007\t\003\027eI!A\007\004\003\0179{G\017[5oOB\0211\002H\005\003;\031\0211!\0218z\021!y\002A!b\001\n\023\001\023A\0022vM\032,'/F\001\"!\r\021SEE\007\002G)\021A\005B\001\b[V$\030M\0317f\023\t13EA\006BeJ\f\027PQ;gM\026\024\b\002\003\025\001\005\003\005\013\021B\021\002\017\t,hMZ3sA!A!\006\001BA\002\023%1&A\003j]\022,\0070F\001-!\tYQ&\003\002/\r\t\031\021J\034;\t\021A\002!\0211A\005\nE\n\021\"\0338eKb|F%Z9\025\005I*\004CA\0064\023\t!dA\001\003V]&$\bb\002\0340\003\003\005\r\001L\001\004q\022\n\004\002\003\035\001\005\003\005\013\025\002\027\002\r%tG-\032=!\021!Q\004A!b\001\n\023Y\023!B;oi&d\007\002\003\037\001\005\003\005\013\021\002\027\002\rUtG/\0337!\021!q\004A!A!\002\023y\024aB0tS\036$W\r\034\t\003\001\016k\021!\021\006\003\005\022\tqaZ3oKJL7-\003\002E\003\nQ1+[4oC2d\027N\\4\t\013\031\003A\021A$\002\rqJg.\033;?)\025A\025JS&M!\ry\001A\005\005\006?\025\003\r!\t\005\006U\025\003\r\001\f\005\006u\025\003\r\001\f\005\006}\025\003\ra\020\005\006\035\002!\taT\001\bQ\006\034h*\032=u+\005\001\006CA\006R\023\t\021fAA\004C_>dW-\0318\t\013Q\003A\021A+\002\t9,\007\020\036\013\002%!)q\013\001C\001W\005I!/Z7bS:Lgn\032\005\0063\002!\tAW\001\004IV\004X#\001%\t\013q\003A\021A/\002\013M\004H.\033;\026\003y\0032a\0301\017\033\005!\021BA1\005\005\r\031V-\035\005\007G\002!\tE\0013\002!\021,'-^4J]\032|'/\\1uS>tW#A3\021\005\031LgBA\006h\023\tAg!\001\004Qe\026$WMZ\005\003U.\024aa\025;sS:<'B\0015\007\001")
/*     */ public class BufferSplitter<T> implements IterableSplitter<T> {
/*     */   private final ArrayBuffer<T> scala$collection$parallel$BufferSplitter$$buffer;
/*     */   
/*     */   private int scala$collection$parallel$BufferSplitter$$index;
/*     */   
/*     */   private final int scala$collection$parallel$BufferSplitter$$until;
/*     */   
/*     */   private Signalling signalDelegate;
/*     */   
/*     */   public Signalling signalDelegate() {
/* 149 */     return this.signalDelegate;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void signalDelegate_$eq(Signalling x$1) {
/* 149 */     this.signalDelegate = x$1;
/*     */   }
/*     */   
/*     */   public Seq<IterableSplitter<T>> splitWithSignalling() {
/* 149 */     return IterableSplitter$class.splitWithSignalling(this);
/*     */   }
/*     */   
/*     */   public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/* 149 */     return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
/*     */   }
/*     */   
/*     */   public String buildString(Function1 closure) {
/* 149 */     return IterableSplitter$class.buildString(this, closure);
/*     */   }
/*     */   
/*     */   public IterableSplitter<T>.Taken newTaken(int until) {
/* 149 */     return IterableSplitter$class.newTaken(this, until);
/*     */   }
/*     */   
/*     */   public <U extends IterableSplitter<T>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/* 149 */     return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
/*     */   }
/*     */   
/*     */   public IterableSplitter<T> take(int n) {
/* 149 */     return IterableSplitter$class.take(this, n);
/*     */   }
/*     */   
/*     */   public IterableSplitter<T> slice(int from1, int until1) {
/* 149 */     return IterableSplitter$class.slice(this, from1, until1);
/*     */   }
/*     */   
/*     */   public <S> IterableSplitter<T>.Mapped<S> map(Function1 f) {
/* 149 */     return IterableSplitter$class.map(this, f);
/*     */   }
/*     */   
/*     */   public <U, PI extends IterableSplitter<U>> IterableSplitter<T>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/* 149 */     return IterableSplitter$class.appendParIterable(this, that);
/*     */   }
/*     */   
/*     */   public <S> IterableSplitter<T>.Zipped<S> zipParSeq(SeqSplitter that) {
/* 149 */     return IterableSplitter$class.zipParSeq(this, that);
/*     */   }
/*     */   
/*     */   public <S, U, R> IterableSplitter<T>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/* 149 */     return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */   }
/*     */   
/*     */   public boolean isAborted() {
/* 149 */     return DelegatedSignalling.class.isAborted(this);
/*     */   }
/*     */   
/*     */   public void abort() {
/* 149 */     DelegatedSignalling.class.abort(this);
/*     */   }
/*     */   
/*     */   public int indexFlag() {
/* 149 */     return DelegatedSignalling.class.indexFlag(this);
/*     */   }
/*     */   
/*     */   public void setIndexFlag(int f) {
/* 149 */     DelegatedSignalling.class.setIndexFlag(this, f);
/*     */   }
/*     */   
/*     */   public void setIndexFlagIfGreater(int f) {
/* 149 */     DelegatedSignalling.class.setIndexFlagIfGreater(this, f);
/*     */   }
/*     */   
/*     */   public void setIndexFlagIfLesser(int f) {
/* 149 */     DelegatedSignalling.class.setIndexFlagIfLesser(this, f);
/*     */   }
/*     */   
/*     */   public int tag() {
/* 149 */     return DelegatedSignalling.class.tag(this);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/* 149 */     return AugmentedIterableIterator$class.count(this, p);
/*     */   }
/*     */   
/*     */   public <U> U reduce(Function2 op) {
/* 149 */     return (U)AugmentedIterableIterator$class.reduce(this, op);
/*     */   }
/*     */   
/*     */   public <U> U fold(Object z, Function2 op) {
/* 149 */     return (U)AugmentedIterableIterator$class.fold(this, z, op);
/*     */   }
/*     */   
/*     */   public <U> U sum(Numeric num) {
/* 149 */     return (U)AugmentedIterableIterator$class.sum(this, num);
/*     */   }
/*     */   
/*     */   public <U> U product(Numeric num) {
/* 149 */     return (U)AugmentedIterableIterator$class.product(this, num);
/*     */   }
/*     */   
/*     */   public <U> T min(Ordering ord) {
/* 149 */     return (T)AugmentedIterableIterator$class.min(this, ord);
/*     */   }
/*     */   
/*     */   public <U> T max(Ordering ord) {
/* 149 */     return (T)AugmentedIterableIterator$class.max(this, ord);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object array, int from, int len) {
/* 149 */     AugmentedIterableIterator$class.copyToArray(this, array, from, len);
/*     */   }
/*     */   
/*     */   public <U> U reduceLeft(int howmany, Function2 op) {
/* 149 */     return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
/*     */   }
/*     */   
/*     */   public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/* 149 */     return AugmentedIterableIterator$class.map2combiner(this, f, cb);
/*     */   }
/*     */   
/*     */   public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/* 149 */     return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
/*     */   }
/*     */   
/*     */   public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/* 149 */     return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
/*     */   }
/*     */   
/*     */   public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/* 149 */     return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
/*     */   }
/*     */   
/*     */   public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/* 149 */     return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
/*     */   }
/*     */   
/*     */   public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/* 149 */     return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
/*     */   }
/*     */   
/*     */   public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/* 149 */     return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
/*     */   }
/*     */   
/*     */   public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/* 149 */     return AugmentedIterableIterator$class.take2combiner(this, n, cb);
/*     */   }
/*     */   
/*     */   public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/* 149 */     return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
/*     */   }
/*     */   
/*     */   public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/* 149 */     return AugmentedIterableIterator$class.slice2combiner(this, from, until, cb);
/*     */   }
/*     */   
/*     */   public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/* 149 */     return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
/*     */   }
/*     */   
/*     */   public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/* 149 */     return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
/*     */   }
/*     */   
/*     */   public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/* 149 */     return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
/*     */   }
/*     */   
/*     */   public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/* 149 */     AugmentedIterableIterator$class.scanToArray(this, z, op, array, from);
/*     */   }
/*     */   
/*     */   public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/* 149 */     return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
/*     */   }
/*     */   
/*     */   public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/* 149 */     return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
/*     */   }
/*     */   
/*     */   public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/* 149 */     return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
/*     */   }
/*     */   
/*     */   public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/* 149 */     return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
/*     */   }
/*     */   
/*     */   public boolean isRemainingCheap() {
/* 149 */     return RemainsIterator$class.isRemainingCheap(this);
/*     */   }
/*     */   
/*     */   public Iterator<T> seq() {
/* 149 */     return Iterator.class.seq(this);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 149 */     return Iterator.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public boolean isTraversableAgain() {
/* 149 */     return Iterator.class.isTraversableAgain(this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/* 149 */     return Iterator.class.hasDefiniteSize(this);
/*     */   }
/*     */   
/*     */   public Iterator<T> drop(int n) {
/* 149 */     return Iterator.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> $plus$plus(Function0 that) {
/* 149 */     return Iterator.class.$plus$plus(this, that);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> flatMap(Function1 f) {
/* 149 */     return Iterator.class.flatMap(this, f);
/*     */   }
/*     */   
/*     */   public Iterator<T> filter(Function1 p) {
/* 149 */     return Iterator.class.filter(this, p);
/*     */   }
/*     */   
/*     */   public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/* 149 */     return Iterator.class.corresponds(this, that, p);
/*     */   }
/*     */   
/*     */   public Iterator<T> withFilter(Function1 p) {
/* 149 */     return Iterator.class.withFilter(this, p);
/*     */   }
/*     */   
/*     */   public Iterator<T> filterNot(Function1 p) {
/* 149 */     return Iterator.class.filterNot(this, p);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> collect(PartialFunction pf) {
/* 149 */     return Iterator.class.collect(this, pf);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/* 149 */     return Iterator.class.scanLeft(this, z, op);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> scanRight(Object z, Function2 op) {
/* 149 */     return Iterator.class.scanRight(this, z, op);
/*     */   }
/*     */   
/*     */   public Iterator<T> takeWhile(Function1 p) {
/* 149 */     return Iterator.class.takeWhile(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<Iterator<T>, Iterator<T>> partition(Function1 p) {
/* 149 */     return Iterator.class.partition(this, p);
/*     */   }
/*     */   
/*     */   public Tuple2<Iterator<T>, Iterator<T>> span(Function1 p) {
/* 149 */     return Iterator.class.span(this, p);
/*     */   }
/*     */   
/*     */   public Iterator<T> dropWhile(Function1 p) {
/* 149 */     return Iterator.class.dropWhile(this, p);
/*     */   }
/*     */   
/*     */   public <B> Iterator<Tuple2<T, B>> zip(Iterator that) {
/* 149 */     return Iterator.class.zip(this, that);
/*     */   }
/*     */   
/*     */   public <A1> Iterator<A1> padTo(int len, Object elem) {
/* 149 */     return Iterator.class.padTo(this, len, elem);
/*     */   }
/*     */   
/*     */   public Iterator<Tuple2<T, Object>> zipWithIndex() {
/* 149 */     return Iterator.class.zipWithIndex(this);
/*     */   }
/*     */   
/*     */   public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/* 149 */     return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/* 149 */     Iterator.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 p) {
/* 149 */     return Iterator.class.forall(this, p);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 p) {
/* 149 */     return Iterator.class.exists(this, p);
/*     */   }
/*     */   
/*     */   public boolean contains(Object elem) {
/* 149 */     return Iterator.class.contains(this, elem);
/*     */   }
/*     */   
/*     */   public Option<T> find(Function1 p) {
/* 149 */     return Iterator.class.find(this, p);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p) {
/* 149 */     return Iterator.class.indexWhere(this, p);
/*     */   }
/*     */   
/*     */   public <B> int indexOf(Object elem) {
/* 149 */     return Iterator.class.indexOf(this, elem);
/*     */   }
/*     */   
/*     */   public BufferedIterator<T> buffered() {
/* 149 */     return Iterator.class.buffered(this);
/*     */   }
/*     */   
/*     */   public <B> Iterator<T>.GroupedIterator<B> grouped(int size) {
/* 149 */     return Iterator.class.grouped(this, size);
/*     */   }
/*     */   
/*     */   public <B> Iterator<T>.GroupedIterator<B> sliding(int size, int step) {
/* 149 */     return Iterator.class.sliding(this, size, step);
/*     */   }
/*     */   
/*     */   public int length() {
/* 149 */     return Iterator.class.length(this);
/*     */   }
/*     */   
/*     */   public Tuple2<Iterator<T>, Iterator<T>> duplicate() {
/* 149 */     return Iterator.class.duplicate(this);
/*     */   }
/*     */   
/*     */   public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/* 149 */     return Iterator.class.patch(this, from, patchElems, replaced);
/*     */   }
/*     */   
/*     */   public boolean sameElements(Iterator that) {
/* 149 */     return Iterator.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public Traversable<T> toTraversable() {
/* 149 */     return Iterator.class.toTraversable(this);
/*     */   }
/*     */   
/*     */   public Iterator<T> toIterator() {
/* 149 */     return Iterator.class.toIterator(this);
/*     */   }
/*     */   
/*     */   public Stream<T> toStream() {
/* 149 */     return Iterator.class.toStream(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 149 */     return Iterator.class.toString(this);
/*     */   }
/*     */   
/*     */   public <B> int sliding$default$2() {
/* 149 */     return Iterator.class.sliding$default$2(this);
/*     */   }
/*     */   
/*     */   public List<T> reversed() {
/* 149 */     return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public int size() {
/* 149 */     return TraversableOnce.class.size((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/* 149 */     return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <B> Option<B> collectFirst(PartialFunction pf) {
/* 149 */     return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */   }
/*     */   
/*     */   public <B> B $div$colon(Object z, Function2 op) {
/* 149 */     return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B $colon$bslash(Object z, Function2 op) {
/* 149 */     return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldLeft(Object z, Function2 op) {
/* 149 */     return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B foldRight(Object z, Function2 op) {
/* 149 */     return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceLeft(Function2 op) {
/* 149 */     return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> B reduceRight(Function2 op) {
/* 149 */     return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceLeftOption(Function2 op) {
/* 149 */     return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> Option<B> reduceRightOption(Function2 op) {
/* 149 */     return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <A1> Option<A1> reduceOption(Function2 op) {
/* 149 */     return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */   }
/*     */   
/*     */   public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 149 */     return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */   }
/*     */   
/*     */   public <B> T maxBy(Function1 f, Ordering cmp) {
/* 149 */     return (T)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> T minBy(Function1 f, Ordering cmp) {
/* 149 */     return (T)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */   }
/*     */   
/*     */   public <B> void copyToBuffer(Buffer dest) {
/* 149 */     TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs, int start) {
/* 149 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */   }
/*     */   
/*     */   public <B> void copyToArray(Object xs) {
/* 149 */     TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */   }
/*     */   
/*     */   public <B> Object toArray(ClassTag evidence$1) {
/* 149 */     return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */   }
/*     */   
/*     */   public List<T> toList() {
/* 149 */     return TraversableOnce.class.toList((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public Iterable<T> toIterable() {
/* 149 */     return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public Seq<T> toSeq() {
/* 149 */     return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<T> toIndexedSeq() {
/* 149 */     return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <B> Buffer<B> toBuffer() {
/* 149 */     return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <B> Set<B> toSet() {
/* 149 */     return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public Vector<T> toVector() {
/* 149 */     return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public <Col> Col to(CanBuildFrom cbf) {
/* 149 */     return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */   }
/*     */   
/*     */   public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 149 */     return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/* 149 */     return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/* 149 */     return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/* 149 */     return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 149 */     return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b, String sep) {
/* 149 */     return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */   }
/*     */   
/*     */   public StringBuilder addString(StringBuilder b) {
/* 149 */     return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */   }
/*     */   
/*     */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 149 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<T> scala$collection$parallel$BufferSplitter$$buffer() {
/* 150 */     return this.scala$collection$parallel$BufferSplitter$$buffer;
/*     */   }
/*     */   
/*     */   public int scala$collection$parallel$BufferSplitter$$index() {
/* 150 */     return this.scala$collection$parallel$BufferSplitter$$index;
/*     */   }
/*     */   
/*     */   private void scala$collection$parallel$BufferSplitter$$index_$eq(int x$1) {
/* 150 */     this.scala$collection$parallel$BufferSplitter$$index = x$1;
/*     */   }
/*     */   
/*     */   public int scala$collection$parallel$BufferSplitter$$until() {
/* 150 */     return this.scala$collection$parallel$BufferSplitter$$until;
/*     */   }
/*     */   
/*     */   public BufferSplitter(ArrayBuffer<T> buffer, int index, int until, Signalling _sigdel) {
/* 150 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 150 */     TraversableOnce.class.$init$((TraversableOnce)this);
/* 150 */     Iterator.class.$init$(this);
/* 150 */     RemainsIterator$class.$init$(this);
/* 150 */     AugmentedIterableIterator$class.$init$(this);
/* 150 */     DelegatedSignalling.class.$init$(this);
/* 150 */     IterableSplitter$class.$init$(this);
/* 152 */     signalDelegate_$eq(_sigdel);
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 153 */     return (scala$collection$parallel$BufferSplitter$$index() < scala$collection$parallel$BufferSplitter$$until());
/*     */   }
/*     */   
/*     */   public T next() {
/* 155 */     Object r = scala$collection$parallel$BufferSplitter$$buffer().apply(scala$collection$parallel$BufferSplitter$$index());
/* 156 */     scala$collection$parallel$BufferSplitter$$index_$eq(scala$collection$parallel$BufferSplitter$$index() + 1);
/* 157 */     return (T)r;
/*     */   }
/*     */   
/*     */   public int remaining() {
/* 159 */     return scala$collection$parallel$BufferSplitter$$until() - scala$collection$parallel$BufferSplitter$$index();
/*     */   }
/*     */   
/*     */   public BufferSplitter<T> dup() {
/* 160 */     return new BufferSplitter(scala$collection$parallel$BufferSplitter$$buffer(), scala$collection$parallel$BufferSplitter$$index(), scala$collection$parallel$BufferSplitter$$until(), signalDelegate());
/*     */   }
/*     */   
/*     */   public Seq<IterableSplitter<T>> split() {
/* 162 */     int divsz = (scala$collection$parallel$BufferSplitter$$until() - scala$collection$parallel$BufferSplitter$$index()) / 2;
/* 163 */     (new BufferSplitter[2])[0] = 
/* 164 */       new BufferSplitter(scala$collection$parallel$BufferSplitter$$buffer(), scala$collection$parallel$BufferSplitter$$index(), scala$collection$parallel$BufferSplitter$$index() + divsz, signalDelegate());
/* 165 */     (new BufferSplitter[2])[1] = new BufferSplitter(scala$collection$parallel$BufferSplitter$$buffer(), scala$collection$parallel$BufferSplitter$$index() + divsz, scala$collection$parallel$BufferSplitter$$until(), signalDelegate());
/* 167 */     (new BufferSplitter[1])[0] = this;
/* 167 */     return (remaining() > 1) ? (Seq<IterableSplitter<T>>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new BufferSplitter[2])) : (Seq<IterableSplitter<T>>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new BufferSplitter[1]));
/*     */   }
/*     */   
/*     */   public String debugInformation() {
/* 169 */     return buildString(
/* 170 */         (Function1<Function1<String, BoxedUnit>, BoxedUnit>)new BufferSplitter$$anonfun$debugInformation$1(this));
/*     */   }
/*     */   
/*     */   public class BufferSplitter$$anonfun$debugInformation$1 extends AbstractFunction1<Function1<String, BoxedUnit>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public BufferSplitter$$anonfun$debugInformation$1(BufferSplitter $outer) {}
/*     */     
/*     */     public final void apply(Function1 append) {
/* 171 */       append.apply("---------------");
/* 172 */       append.apply("Buffer iterator");
/* 173 */       append.apply((new StringBuilder()).append("buffer: ").append(this.$outer.scala$collection$parallel$BufferSplitter$$buffer()).toString());
/* 174 */       append.apply((new StringBuilder()).append("index: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$BufferSplitter$$index())).toString());
/* 175 */       append.apply((new StringBuilder()).append("until: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$BufferSplitter$$until())).toString());
/* 176 */       append.apply("---------------");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\BufferSplitter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */