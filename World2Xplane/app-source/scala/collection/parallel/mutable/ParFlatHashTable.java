/*     */ package scala.collection.parallel.mutable;
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
/*     */ import scala.collection.DebugUtils$;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Iterator$;
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
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.FlatHashTable;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.AugmentedIterableIterator;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.IterableSplitter;
/*     */ import scala.collection.parallel.ParIterable;
/*     */ import scala.collection.parallel.RemainsIterator;
/*     */ import scala.collection.parallel.SeqSplitter;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\025caB\001\003!\003\r\ta\003\002\021!\006\024h\t\\1u\021\006\034\b\016V1cY\026T!a\001\003\002\0175,H/\0312mK*\021QAB\001\ta\006\024\030\r\0347fY*\021q\001C\001\013G>dG.Z2uS>t'\"A\005\002\013M\034\027\r\\1\004\001U\021A\002G\n\004\0015\t\002C\001\b\020\033\005A\021B\001\t\t\005\031\te.\037*fMB\031!\003\006\f\016\003MQ!a\001\004\n\005U\031\"!\004$mCRD\025m\0355UC\ndW\r\005\002\03011\001A!B\r\001\005\004Q\"!\001+\022\005mq\002C\001\b\035\023\ti\002BA\004O_RD\027N\\4\021\0059y\022B\001\021\t\005\r\te.\037\005\006E\001!\taI\001\007I%t\027\016\036\023\025\003\021\002\"AD\023\n\005\031B!\001B+oSRDQ\001\013\001\005B%\n\021#\0317xCf\034\030J\\5u'&TX-T1q+\005Q\003C\001\b,\023\ta\003BA\004C_>dW-\0318\007\0139\002\021\021A\030\0031A\013'O\0227bi\"\0137\017\033+bE2,\027\n^3sCR|'o\005\003.\033A\"\004cA\0313-5\tA!\003\0024\t\t\001\022\n^3sC\ndWm\0259mSR$XM\035\t\003kYj\021AA\005\003o\t\021AbU5{K6\013\007/\026;jYND\001\"O\027\003\002\004%\tAO\001\004S\022DX#A\036\021\0059a\024BA\037\t\005\rIe\016\036\005\t5\022\t\031!C\001\001\0069\021\016\032=`I\025\fHC\001\023B\021\035\021e(!AA\002m\n1\001\037\0232\021!!UF!A!B\023Y\024\001B5eq\002B\001BR\027\003\006\004%\tAO\001\006k:$\030\016\034\005\t\0216\022\t\021)A\005w\0051QO\034;jY\002B\001BS\027\003\006\004%\tAO\001\ni>$\030\r\\:ju\026D\001\002T\027\003\002\003\006IaO\001\013i>$\030\r\\:ju\026\004\003\"\002(.\t\003y\025A\002\037j]&$h\b\006\003Q%N#\006CA).\033\005\001\001\"B\035N\001\004Y\004\"\002$N\001\004Y\004\"\002&N\001\004Y\004b\002,.\001\004%IAO\001\niJ\fg/\032:tK\022Dq\001W\027A\002\023%\021,A\007ue\0064XM]:fI~#S-\035\013\003IiCqAQ,\002\002\003\0071\b\003\004][\001\006KaO\001\013iJ\fg/\032:tK\022\004\003b\0020.\005\004%IaX\001\nSR,'\017^1cY\026,\022\001\031\t\004\035\005l\021B\0012\t\005\025\t%O]1z\021\031!W\006)A\005A\006Q\021\016^3si\006\024G.\032\021\t\013\031lC\021B\022\002\tM\034\027M\034\005\006Q6\"IaI\001\fG\",7m\0332pk:$7\017C\003k[\031\0051.A\006oK^LE/\032:bi>\024H\003\002\031m]>DQ!\\5A\002m\nQ!\0338eKbDQAR5A\002mBQAS5A\002mBQ!]\027\005\002i\n\021B]3nC&t\027N\\4\t\013MlC\021A\025\002\017!\f7OT3yi\")Q/\fC\001m\006!a.\032=u)\0051\002\"\002=.\t\003I\030a\0013vaV\t\001\007C\003|[\021\005A0A\003ta2LG/F\001~!\021q\030Q\002\031\017\007}\fIA\004\003\002\002\005\035QBAA\002\025\r\t)AC\001\007yI|w\016\036 \n\003%I1!a\003\t\003\035\001\030mY6bO\026LA!a\004\002\022\t\0311+Z9\013\007\005-\001\002C\004\002\0265\"\t%a\006\002!\021,'-^4J]\032|'/\\1uS>tWCAA\r!\021\tY\"!\t\017\0079\ti\"C\002\002 !\ta\001\025:fI\0264\027\002BA\022\003K\021aa\025;sS:<'bAA\020\021!9\021\021F\027\005\022\005-\022AC2pk:$X\t\\3ngR)1(!\f\0022!9\021qFA\024\001\004Y\024\001\0024s_6DaARA\024\001\004Y\004bBA\033[\021E\021qG\001\021G>,h\016\036\"vG.,GoU5{KN$RaOA\035\003{Aq!a\017\0024\001\0071(\001\006ge>l'-^2lKRDq!a\020\0024\001\0071(A\006v]RLGNY;dW\026$\bBBA\"[\021%1%A\003dQ\026\0347\016")
/*     */ public interface ParFlatHashTable<T> extends FlatHashTable<T> {
/*     */   boolean alwaysInitSizeMap();
/*     */   
/*     */   public abstract class ParFlatHashTableIterator implements IterableSplitter<T>, SizeMapUtils {
/*     */     private int idx;
/*     */     
/*     */     private final int until;
/*     */     
/*     */     private final int totalsize;
/*     */     
/*     */     private int scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed;
/*     */     
/*     */     private final Object[] scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable;
/*     */     
/*     */     private Signalling signalDelegate;
/*     */     
/*     */     public int calcNumElems(int from, int until, int tableLength, int sizeMapBucketSize) {
/*  26 */       return SizeMapUtils$class.calcNumElems(this, from, until, tableLength, sizeMapBucketSize);
/*     */     }
/*     */     
/*     */     public Signalling signalDelegate() {
/*  26 */       return this.signalDelegate;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void signalDelegate_$eq(Signalling x$1) {
/*  26 */       this.signalDelegate = x$1;
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<T>> splitWithSignalling() {
/*  26 */       return IterableSplitter.class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/*  26 */       return IterableSplitter.class.shouldSplitFurther(this, coll, parallelismLevel);
/*     */     }
/*     */     
/*     */     public String buildString(Function1 closure) {
/*  26 */       return IterableSplitter.class.buildString(this, closure);
/*     */     }
/*     */     
/*     */     public IterableSplitter<T>.Taken newTaken(int until) {
/*  26 */       return IterableSplitter.class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public <U extends IterableSplitter<T>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/*  26 */       return (U)IterableSplitter.class.newSliceInternal(this, it, from1);
/*     */     }
/*     */     
/*     */     public IterableSplitter<T> take(int n) {
/*  26 */       return IterableSplitter.class.take(this, n);
/*     */     }
/*     */     
/*     */     public IterableSplitter<T> slice(int from1, int until1) {
/*  26 */       return IterableSplitter.class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<T>.Mapped<S> map(Function1 f) {
/*  26 */       return IterableSplitter.class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends IterableSplitter<U>> IterableSplitter<T>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/*  26 */       return IterableSplitter.class.appendParIterable(this, that);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<T>.Zipped<S> zipParSeq(SeqSplitter that) {
/*  26 */       return IterableSplitter.class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> IterableSplitter<T>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/*  26 */       return IterableSplitter.class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public boolean isAborted() {
/*  26 */       return DelegatedSignalling.class.isAborted((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public void abort() {
/*  26 */       DelegatedSignalling.class.abort((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public int indexFlag() {
/*  26 */       return DelegatedSignalling.class.indexFlag((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public void setIndexFlag(int f) {
/*  26 */       DelegatedSignalling.class.setIndexFlag((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfGreater(int f) {
/*  26 */       DelegatedSignalling.class.setIndexFlagIfGreater((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfLesser(int f) {
/*  26 */       DelegatedSignalling.class.setIndexFlagIfLesser((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public int tag() {
/*  26 */       return DelegatedSignalling.class.tag((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/*  26 */       return AugmentedIterableIterator.class.count((AugmentedIterableIterator)this, p);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/*  26 */       return (U)AugmentedIterableIterator.class.reduce((AugmentedIterableIterator)this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/*  26 */       return (U)AugmentedIterableIterator.class.fold((AugmentedIterableIterator)this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/*  26 */       return (U)AugmentedIterableIterator.class.sum((AugmentedIterableIterator)this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/*  26 */       return (U)AugmentedIterableIterator.class.product((AugmentedIterableIterator)this, num);
/*     */     }
/*     */     
/*     */     public <U> T min(Ordering ord) {
/*  26 */       return (T)AugmentedIterableIterator.class.min((AugmentedIterableIterator)this, ord);
/*     */     }
/*     */     
/*     */     public <U> T max(Ordering ord) {
/*  26 */       return (T)AugmentedIterableIterator.class.max((AugmentedIterableIterator)this, ord);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object array, int from, int len) {
/*  26 */       AugmentedIterableIterator.class.copyToArray((AugmentedIterableIterator)this, array, from, len);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(int howmany, Function2 op) {
/*  26 */       return (U)AugmentedIterableIterator.class.reduceLeft((AugmentedIterableIterator)this, howmany, op);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/*  26 */       return AugmentedIterableIterator.class.map2combiner((AugmentedIterableIterator)this, f, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/*  26 */       return AugmentedIterableIterator.class.collect2combiner((AugmentedIterableIterator)this, pf, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/*  26 */       return AugmentedIterableIterator.class.flatmap2combiner((AugmentedIterableIterator)this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/*  26 */       return (Bld)AugmentedIterableIterator.class.copy2builder((AugmentedIterableIterator)this, b);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/*  26 */       return AugmentedIterableIterator.class.filter2combiner((AugmentedIterableIterator)this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/*  26 */       return AugmentedIterableIterator.class.filterNot2combiner((AugmentedIterableIterator)this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/*  26 */       return AugmentedIterableIterator.class.partition2combiners((AugmentedIterableIterator)this, pred, btrue, bfalse);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/*  26 */       return AugmentedIterableIterator.class.take2combiner((AugmentedIterableIterator)this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/*  26 */       return AugmentedIterableIterator.class.drop2combiner((AugmentedIterableIterator)this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/*  26 */       return AugmentedIterableIterator.class.slice2combiner((AugmentedIterableIterator)this, from, until, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/*  26 */       return AugmentedIterableIterator.class.splitAt2combiners((AugmentedIterableIterator)this, at, before, after);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/*  26 */       return AugmentedIterableIterator.class.takeWhile2combiner((AugmentedIterableIterator)this, p, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/*  26 */       return AugmentedIterableIterator.class.span2combiners((AugmentedIterableIterator)this, p, before, after);
/*     */     }
/*     */     
/*     */     public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/*  26 */       AugmentedIterableIterator.class.scanToArray((AugmentedIterableIterator)this, z, op, array, from);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/*  26 */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/*  26 */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, howmany, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/*  26 */       return AugmentedIterableIterator.class.zip2combiner((AugmentedIterableIterator)this, otherpit, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/*  26 */       return AugmentedIterableIterator.class.zipAll2combiner((AugmentedIterableIterator)this, that, thiselem, thatelem, cb);
/*     */     }
/*     */     
/*     */     public boolean isRemainingCheap() {
/*  26 */       return RemainsIterator.class.isRemainingCheap((RemainsIterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<T> seq() {
/*  26 */       return Iterator.class.seq((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*  26 */       return Iterator.class.isEmpty((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/*  26 */       return Iterator.class.isTraversableAgain((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/*  26 */       return Iterator.class.hasDefiniteSize((Iterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<T> drop(int n) {
/*  26 */       return Iterator.class.drop((Iterator)this, n);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/*  26 */       return Iterator.class.$plus$plus((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/*  26 */       return Iterator.class.flatMap((Iterator)this, f);
/*     */     }
/*     */     
/*     */     public Iterator<T> filter(Function1 p) {
/*  26 */       return Iterator.class.filter((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/*  26 */       return Iterator.class.corresponds((Iterator)this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<T> withFilter(Function1 p) {
/*  26 */       return Iterator.class.withFilter((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Iterator<T> filterNot(Function1 p) {
/*  26 */       return Iterator.class.filterNot((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/*  26 */       return Iterator.class.collect((Iterator)this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/*  26 */       return Iterator.class.scanLeft((Iterator)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/*  26 */       return Iterator.class.scanRight((Iterator)this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<T> takeWhile(Function1 p) {
/*  26 */       return Iterator.class.takeWhile((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<T>, Iterator<T>> partition(Function1 p) {
/*  26 */       return Iterator.class.partition((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<T>, Iterator<T>> span(Function1 p) {
/*  26 */       return Iterator.class.span((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Iterator<T> dropWhile(Function1 p) {
/*  26 */       return Iterator.class.dropWhile((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<T, B>> zip(Iterator that) {
/*  26 */       return Iterator.class.zip((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/*  26 */       return Iterator.class.padTo((Iterator)this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<T, Object>> zipWithIndex() {
/*  26 */       return Iterator.class.zipWithIndex((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/*  26 */       return Iterator.class.zipAll((Iterator)this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  26 */       Iterator.class.foreach((Iterator)this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/*  26 */       return Iterator.class.forall((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/*  26 */       return Iterator.class.exists((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/*  26 */       return Iterator.class.contains((Iterator)this, elem);
/*     */     }
/*     */     
/*     */     public Option<T> find(Function1 p) {
/*  26 */       return Iterator.class.find((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/*  26 */       return Iterator.class.indexWhere((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/*  26 */       return Iterator.class.indexOf((Iterator)this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<T> buffered() {
/*  26 */       return Iterator.class.buffered((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<T>.GroupedIterator<B> grouped(int size) {
/*  26 */       return Iterator.class.grouped((Iterator)this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<T>.GroupedIterator<B> sliding(int size, int step) {
/*  26 */       return Iterator.class.sliding((Iterator)this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/*  26 */       return Iterator.class.length((Iterator)this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<T>, Iterator<T>> duplicate() {
/*  26 */       return Iterator.class.duplicate((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/*  26 */       return Iterator.class.patch((Iterator)this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/*  26 */       return Iterator.class.sameElements((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public Traversable<T> toTraversable() {
/*  26 */       return Iterator.class.toTraversable((Iterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<T> toIterator() {
/*  26 */       return Iterator.class.toIterator((Iterator)this);
/*     */     }
/*     */     
/*     */     public Stream<T> toStream() {
/*  26 */       return Iterator.class.toStream((Iterator)this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  26 */       return Iterator.class.toString((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/*  26 */       return Iterator.class.sliding$default$2((Iterator)this);
/*     */     }
/*     */     
/*     */     public List<T> reversed() {
/*  26 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/*  26 */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/*  26 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/*  26 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/*  26 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/*  26 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/*  26 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/*  26 */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/*  26 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/*  26 */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/*  26 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/*  26 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/*  26 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  26 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> T maxBy(Function1 f, Ordering cmp) {
/*  26 */       return (T)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> T minBy(Function1 f, Ordering cmp) {
/*  26 */       return (T)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/*  26 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/*  26 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/*  26 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/*  26 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<T> toList() {
/*  26 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<T> toIterable() {
/*  26 */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<T> toSeq() {
/*  26 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<T> toIndexedSeq() {
/*  26 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/*  26 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*  26 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<T> toVector() {
/*  26 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/*  26 */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  26 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/*  26 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/*  26 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/*  26 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  26 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/*  26 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/*  26 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  26 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public int idx() {
/*  26 */       return this.idx;
/*     */     }
/*     */     
/*     */     public void idx_$eq(int x$1) {
/*  26 */       this.idx = x$1;
/*     */     }
/*     */     
/*     */     public int until() {
/*  26 */       return this.until;
/*     */     }
/*     */     
/*     */     public int totalsize() {
/*  26 */       return this.totalsize;
/*     */     }
/*     */     
/*     */     public ParFlatHashTableIterator(ParFlatHashTable $outer, int idx, int until, int totalsize) {
/*  26 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  26 */       TraversableOnce.class.$init$((TraversableOnce)this);
/*  26 */       Iterator.class.$init$((Iterator)this);
/*  26 */       RemainsIterator.class.$init$((RemainsIterator)this);
/*  26 */       AugmentedIterableIterator.class.$init$((AugmentedIterableIterator)this);
/*  26 */       DelegatedSignalling.class.$init$((DelegatedSignalling)this);
/*  26 */       IterableSplitter.class.$init$(this);
/*  26 */       SizeMapUtils$class.$init$(this);
/*  30 */       this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed = 0;
/*  31 */       this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable = $outer.table();
/*  33 */       if (hasNext())
/*  33 */         scan(); 
/*     */     }
/*     */     
/*     */     public int scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed() {
/*     */       return this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed;
/*     */     }
/*     */     
/*     */     private void scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed_$eq(int x$1) {
/*     */       this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed = x$1;
/*     */     }
/*     */     
/*     */     public Object[] scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable() {
/*     */       return this.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable;
/*     */     }
/*     */     
/*     */     private void scan() {
/*  36 */       while (scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable()[idx()] == null)
/*  37 */         idx_$eq(idx() + 1); 
/*     */     }
/*     */     
/*     */     private void checkbounds() {
/*  41 */       if (idx() >= (scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable()).length)
/*  42 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(idx()).toString()); 
/*     */     }
/*     */     
/*     */     public int remaining() {
/*  47 */       return totalsize() - scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*  48 */       return (scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed() < totalsize());
/*     */     }
/*     */     
/*     */     public T next() {
/*  50 */       Object r = scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable()[idx()];
/*  51 */       scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed_$eq(scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed() + 1);
/*  52 */       idx_$eq(idx() + 1);
/*  53 */       if (hasNext())
/*  53 */         scan(); 
/*  54 */       return hasNext() ? (T)r : 
/*  55 */         (T)Iterator$.MODULE$.empty().next();
/*     */     }
/*     */     
/*     */     public IterableSplitter<T> dup() {
/*  56 */       return newIterator(idx(), until(), totalsize());
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<T>> split() {
/*  58 */       int divpt = (until() + idx()) / 2;
/*  60 */       int fstidx = idx();
/*  61 */       int fsttotal = calcNumElems(idx(), divpt, (scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable()).length, scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer().sizeMapBucketSize());
/*  63 */       IterableSplitter<T> fstit = newIterator(fstidx, divpt, fsttotal);
/*  65 */       int snduntil = until();
/*  67 */       int sndtotal = remaining() - fsttotal;
/*  68 */       IterableSplitter<T> sndit = newIterator(divpt, snduntil, sndtotal);
/*  70 */       (new IterableSplitter[2])[0] = fstit;
/*  70 */       (new IterableSplitter[2])[1] = sndit;
/*  71 */       (new ParFlatHashTableIterator[1])[0] = this;
/*  71 */       return (remaining() > 1) ? (Seq<IterableSplitter<T>>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new IterableSplitter[2])) : (Seq<IterableSplitter<T>>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new ParFlatHashTableIterator[1]));
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/*  73 */       return buildString(
/*  74 */           (Function1<Function1<String, BoxedUnit>, BoxedUnit>)new ParFlatHashTable$ParFlatHashTableIterator$$anonfun$debugInformation$1(this));
/*     */     }
/*     */     
/*     */     public class ParFlatHashTable$ParFlatHashTableIterator$$anonfun$debugInformation$1 extends AbstractFunction1<Function1<String, BoxedUnit>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public ParFlatHashTable$ParFlatHashTableIterator$$anonfun$debugInformation$1(ParFlatHashTable.ParFlatHashTableIterator $outer) {}
/*     */       
/*     */       public final void apply(Function1 append) {
/*  75 */         append.apply("Parallel flat hash table iterator");
/*  76 */         append.apply("---------------------------------");
/*  77 */         append.apply((new StringBuilder()).append("Traversed/total: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$traversed())).append(" / ").append(BoxesRunTime.boxToInteger(this.$outer.totalsize())).toString());
/*  78 */         append.apply((new StringBuilder()).append("Table idx/until: ").append(BoxesRunTime.boxToInteger(this.$outer.idx())).append(" / ").append(BoxesRunTime.boxToInteger(this.$outer.until())).toString());
/*  79 */         append.apply((new StringBuilder()).append("Table length: ").append(BoxesRunTime.boxToInteger((this.$outer.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable()).length)).toString());
/*  80 */         append.apply("Table: ");
/*  81 */         append.apply(DebugUtils$.MODULE$.arrayString(this.$outer.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable(), 0, (this.$outer.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable()).length));
/*  82 */         append.apply("Sizemap: ");
/*  83 */         append.apply(DebugUtils$.MODULE$.arrayString(this.$outer.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer().sizemap(), 0, (this.$outer.scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer().sizemap()).length));
/*     */       }
/*     */     }
/*     */     
/*     */     public int countElems(int from, int until) {
/*  87 */       int count = 0;
/*  88 */       int i = from;
/*  89 */       while (i < until) {
/*  90 */         if (scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$itertable()[i] != null)
/*  90 */           count++; 
/*  91 */         i++;
/*     */       } 
/*  93 */       return count;
/*     */     }
/*     */     
/*     */     public int countBucketSizes(int frombucket, int untilbucket) {
/*  97 */       int count = 0;
/*  98 */       int i = frombucket;
/*  99 */       while (i < untilbucket) {
/* 100 */         count += scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer().sizemap()[i];
/* 101 */         i++;
/*     */       } 
/* 103 */       return count;
/*     */     }
/*     */     
/*     */     private void check() {
/* 106 */       if (Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps(scala$collection$parallel$mutable$ParFlatHashTable$ParFlatHashTableIterator$$$outer().table()).slice(idx(), until())).count((Function1)new ParFlatHashTable$ParFlatHashTableIterator$$anonfun$check$1(this)) != remaining()) {
/* 107 */         Predef$.MODULE$.println((new StringBuilder()).append("Invariant broken: ").append(debugInformation()).toString());
/* 108 */         Predef$.MODULE$.assert(false);
/*     */       } 
/*     */     }
/*     */     
/*     */     public abstract IterableSplitter<T> newIterator(int param1Int1, int param1Int2, int param1Int3);
/*     */     
/*     */     public class ParFlatHashTable$ParFlatHashTableIterator$$anonfun$check$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Object x$1) {
/*     */         return !(x$1 == null);
/*     */       }
/*     */       
/*     */       public ParFlatHashTable$ParFlatHashTableIterator$$anonfun$check$1(ParFlatHashTable.ParFlatHashTableIterator $outer) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParFlatHashTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */