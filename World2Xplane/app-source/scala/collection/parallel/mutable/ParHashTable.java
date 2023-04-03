/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import scala.Array$;
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
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.ArrayBuffer$;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.HashEntry;
/*     */ import scala.collection.mutable.HashTable;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.AugmentedIterableIterator;
/*     */ import scala.collection.parallel.BufferSplitter;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.IterableSplitter;
/*     */ import scala.collection.parallel.ParIterable;
/*     */ import scala.collection.parallel.RemainsIterator;
/*     */ import scala.collection.parallel.SeqSplitter;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\025eaB\001\003!\003\r\ta\003\002\r!\006\024\b*Y:i)\006\024G.\032\006\003\007\021\tq!\\;uC\ndWM\003\002\006\r\005A\001/\031:bY2,GN\003\002\b\021\005Q1m\0347mK\016$\030n\0348\013\003%\tQa]2bY\006\034\001!F\002\r1\t\0322\001A\007\022!\tqq\"D\001\t\023\t\001\002B\001\004B]f\024VM\032\t\005%Q1\022%D\001\024\025\t\031a!\003\002\026'\tI\001*Y:i)\006\024G.\032\t\003/aa\001\001B\003\032\001\t\007!DA\001L#\tYb\004\005\002\0179%\021Q\004\003\002\b\035>$\b.\0338h!\tqq$\003\002!\021\t\031\021I\\=\021\005]\021C!B\022\001\005\004!#!B#oiJL\030CA\023)!\tqa%\003\002(\021\t!a*\0367m!\021\021\022FF\021\n\005)\032\"!\003%bg\",e\016\036:z\021\025a\003\001\"\001.\003\031!\023N\\5uIQ\ta\006\005\002\017_%\021\001\007\003\002\005+:LG\017C\0033\001\021\0053'A\tbY^\f\027p]%oSR\034\026N_3NCB,\022\001\016\t\003\035UJ!A\016\005\003\017\t{w\016\\3b]\032)\001\bAA\001s\tiQI\034;ss&#XM]1u_J,2A\017!k'\0219Tb\017\"\021\007qjt(D\001\005\023\tqDA\001\tJi\026\024\030M\0317f'Bd\027\016\036;feB\021q\003\021\003\006\003^\022\rA\007\002\002)B\0211\tR\007\002\005%\021QI\001\002\r'&TX-T1q+RLGn\035\005\t\017^\022\t\031!C\005\021\006\031\021\016\032=\026\003%\003\"A\004&\n\005-C!aA%oi\"AQj\016BA\002\023%a*A\004jIb|F%Z9\025\0059z\005b\002)M\003\003\005\r!S\001\004q\022\n\004\002\003*8\005\003\005\013\025B%\002\t%$\007\020\t\005\t)^\022)\031!C\005\021\006)QO\034;jY\"Aak\016B\001B\003%\021*\001\004v]RLG\016\t\005\t1^\022)\031!C\005\021\006IAo\034;bYNL'0\032\005\t5^\022\t\021)A\005\023\006QAo\034;bYNL'0\032\021\t\021q;$\0211A\005\nu\013!!Z:\026\003\005B\001bX\034\003\002\004%I\001Y\001\007KN|F%Z9\025\0059\n\007b\002)_\003\003\005\r!\t\005\tG^\022\t\021)Q\005C\005\031Qm\035\021\t\013\025<D\021\0014\002\rqJg.\033;?)\0259WN\\8q!\021AwgP5\016\003\001\001\"a\0066\005\r-<DQ1\001m\005!IE/\032:SKB\024\030CA\016<\021\0259E\r1\001J\021\025!F\r1\001J\021\025AF\r1\001J\021\025aF\r1\001\"\021\035\021xG1A\005\nM\f\021\"\033;feR\f'\r\\3\026\003Q\0042AD;)\023\t1\bBA\003BeJ\f\027\020\003\004yo\001\006I\001^\001\013SR,'\017^1cY\026\004\003b\002>8\001\004%I\001S\001\niJ\fg/\032:tK\022Dq\001`\034A\002\023%Q0A\007ue\0064XM]:fI~#S-\035\013\003]yDq\001U>\002\002\003\007\021\nC\004\002\002]\002\013\025B%\002\025Q\024\030M^3sg\026$\007\005C\004\002\006]2\t!a\002\002\025\025tGO]=3SR,W\016F\002@\003\023Aq!a\003\002\004\001\007\021%A\001f\021\035\tya\016D\001\003#\t1B\\3x\023R,'/\031;peRI\021.a\005\002\030\005m\021q\004\005\b\003+\ti\0011\001J\003\035IG\r\037$s_6Dq!!\007\002\016\001\007\021*\001\005jIb,f\016^5m\021\035\ti\"!\004A\002%\013\021\002^8uC2\034\026N_3\t\rq\013i\0011\001\"\021\031\t\031c\016C\001g\0059\001.Y:OKb$\bbBA\024o\021\005\021\021F\001\005]\026DH\017F\001@\021\031\tic\016C\001[\005!1oY1o\021\031\t\td\016C\001\021\006I!/Z7bS:Lgn\032\005\t\003k9D\021\t\003\0028\005\001B-\0322vO&sgm\034:nCRLwN\\\013\003\003s\001B!a\017\002B9\031a\"!\020\n\007\005}\002\"\001\004Qe\026$WMZ\005\005\003\007\n)E\001\004TiJLgn\032\006\004\003A\001bBA%o\021\005\0211J\001\004IV\004X#A5\t\017\005=s\007\"\001\002R\005)1\017\0357jiV\021\0211\013\t\006\003+\n9fO\007\002\r%\031\021\021\f\004\003\007M+\027\017C\004\002^]\"I!a\030\002)\r|gN^3siR{\027I\035:bs\n+hMZ3s)\021\t\t'a\032\021\tI\t\031gP\005\004\003K\032\"aC!se\006L()\0364gKJDq!!\033\002\\\001\007\021%A\005dQ\006Lg\016[3bI\"9\021QN\034\005\022\005=\024AC2pk:$X\t\\3ngR)\021*!\035\002v!9\0211OA6\001\004I\025\001\0024s_6Da\001VA6\001\004I\005bBA=o\021E\0211P\001\021G>,h\016\036\"vG.,GoU5{KN$R!SA?\003\003Cq!a \002x\001\007\021*\001\006ge>l')^2lKRDq!a!\002x\001\007\021*A\006v]RLGNQ;dW\026$\b")
/*     */ public interface ParHashTable<K, Entry extends HashEntry<K, Entry>> extends HashTable<K, Entry> {
/*     */   boolean alwaysInitSizeMap();
/*     */   
/*     */   public abstract class EntryIterator<T, IterRepr extends IterableSplitter<T>> implements IterableSplitter<T>, SizeMapUtils {
/*     */     private int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx;
/*     */     
/*     */     private final int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until;
/*     */     
/*     */     private final int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$totalsize;
/*     */     
/*     */     private Entry scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es;
/*     */     
/*     */     private final HashEntry<K, Entry>[] scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable;
/*     */     
/*     */     private int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed;
/*     */     
/*     */     private Signalling signalDelegate;
/*     */     
/*     */     public int calcNumElems(int from, int until, int tableLength, int sizeMapBucketSize) {
/*  31 */       return SizeMapUtils$class.calcNumElems(this, from, until, tableLength, sizeMapBucketSize);
/*     */     }
/*     */     
/*     */     public Signalling signalDelegate() {
/*  31 */       return this.signalDelegate;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void signalDelegate_$eq(Signalling x$1) {
/*  31 */       this.signalDelegate = x$1;
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<T>> splitWithSignalling() {
/*  31 */       return IterableSplitter.class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/*  31 */       return IterableSplitter.class.shouldSplitFurther(this, coll, parallelismLevel);
/*     */     }
/*     */     
/*     */     public String buildString(Function1 closure) {
/*  31 */       return IterableSplitter.class.buildString(this, closure);
/*     */     }
/*     */     
/*     */     public IterableSplitter<T>.Taken newTaken(int until) {
/*  31 */       return IterableSplitter.class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public <U extends IterableSplitter<T>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/*  31 */       return (U)IterableSplitter.class.newSliceInternal(this, it, from1);
/*     */     }
/*     */     
/*     */     public IterableSplitter<T> take(int n) {
/*  31 */       return IterableSplitter.class.take(this, n);
/*     */     }
/*     */     
/*     */     public IterableSplitter<T> slice(int from1, int until1) {
/*  31 */       return IterableSplitter.class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<T>.Mapped<S> map(Function1 f) {
/*  31 */       return IterableSplitter.class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends IterableSplitter<U>> IterableSplitter<T>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/*  31 */       return IterableSplitter.class.appendParIterable(this, that);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<T>.Zipped<S> zipParSeq(SeqSplitter that) {
/*  31 */       return IterableSplitter.class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> IterableSplitter<T>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/*  31 */       return IterableSplitter.class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public boolean isAborted() {
/*  31 */       return DelegatedSignalling.class.isAborted((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public void abort() {
/*  31 */       DelegatedSignalling.class.abort((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public int indexFlag() {
/*  31 */       return DelegatedSignalling.class.indexFlag((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public void setIndexFlag(int f) {
/*  31 */       DelegatedSignalling.class.setIndexFlag((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfGreater(int f) {
/*  31 */       DelegatedSignalling.class.setIndexFlagIfGreater((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfLesser(int f) {
/*  31 */       DelegatedSignalling.class.setIndexFlagIfLesser((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public int tag() {
/*  31 */       return DelegatedSignalling.class.tag((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/*  31 */       return AugmentedIterableIterator.class.count((AugmentedIterableIterator)this, p);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/*  31 */       return (U)AugmentedIterableIterator.class.reduce((AugmentedIterableIterator)this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/*  31 */       return (U)AugmentedIterableIterator.class.fold((AugmentedIterableIterator)this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/*  31 */       return (U)AugmentedIterableIterator.class.sum((AugmentedIterableIterator)this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/*  31 */       return (U)AugmentedIterableIterator.class.product((AugmentedIterableIterator)this, num);
/*     */     }
/*     */     
/*     */     public <U> T min(Ordering ord) {
/*  31 */       return (T)AugmentedIterableIterator.class.min((AugmentedIterableIterator)this, ord);
/*     */     }
/*     */     
/*     */     public <U> T max(Ordering ord) {
/*  31 */       return (T)AugmentedIterableIterator.class.max((AugmentedIterableIterator)this, ord);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object array, int from, int len) {
/*  31 */       AugmentedIterableIterator.class.copyToArray((AugmentedIterableIterator)this, array, from, len);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(int howmany, Function2 op) {
/*  31 */       return (U)AugmentedIterableIterator.class.reduceLeft((AugmentedIterableIterator)this, howmany, op);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/*  31 */       return AugmentedIterableIterator.class.map2combiner((AugmentedIterableIterator)this, f, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/*  31 */       return AugmentedIterableIterator.class.collect2combiner((AugmentedIterableIterator)this, pf, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/*  31 */       return AugmentedIterableIterator.class.flatmap2combiner((AugmentedIterableIterator)this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/*  31 */       return (Bld)AugmentedIterableIterator.class.copy2builder((AugmentedIterableIterator)this, b);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/*  31 */       return AugmentedIterableIterator.class.filter2combiner((AugmentedIterableIterator)this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/*  31 */       return AugmentedIterableIterator.class.filterNot2combiner((AugmentedIterableIterator)this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/*  31 */       return AugmentedIterableIterator.class.partition2combiners((AugmentedIterableIterator)this, pred, btrue, bfalse);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/*  31 */       return AugmentedIterableIterator.class.take2combiner((AugmentedIterableIterator)this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/*  31 */       return AugmentedIterableIterator.class.drop2combiner((AugmentedIterableIterator)this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/*  31 */       return AugmentedIterableIterator.class.slice2combiner((AugmentedIterableIterator)this, from, until, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/*  31 */       return AugmentedIterableIterator.class.splitAt2combiners((AugmentedIterableIterator)this, at, before, after);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/*  31 */       return AugmentedIterableIterator.class.takeWhile2combiner((AugmentedIterableIterator)this, p, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/*  31 */       return AugmentedIterableIterator.class.span2combiners((AugmentedIterableIterator)this, p, before, after);
/*     */     }
/*     */     
/*     */     public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/*  31 */       AugmentedIterableIterator.class.scanToArray((AugmentedIterableIterator)this, z, op, array, from);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/*  31 */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/*  31 */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, howmany, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/*  31 */       return AugmentedIterableIterator.class.zip2combiner((AugmentedIterableIterator)this, otherpit, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/*  31 */       return AugmentedIterableIterator.class.zipAll2combiner((AugmentedIterableIterator)this, that, thiselem, thatelem, cb);
/*     */     }
/*     */     
/*     */     public boolean isRemainingCheap() {
/*  31 */       return RemainsIterator.class.isRemainingCheap((RemainsIterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<T> seq() {
/*  31 */       return Iterator.class.seq((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*  31 */       return Iterator.class.isEmpty((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/*  31 */       return Iterator.class.isTraversableAgain((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/*  31 */       return Iterator.class.hasDefiniteSize((Iterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<T> drop(int n) {
/*  31 */       return Iterator.class.drop((Iterator)this, n);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/*  31 */       return Iterator.class.$plus$plus((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/*  31 */       return Iterator.class.flatMap((Iterator)this, f);
/*     */     }
/*     */     
/*     */     public Iterator<T> filter(Function1 p) {
/*  31 */       return Iterator.class.filter((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/*  31 */       return Iterator.class.corresponds((Iterator)this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<T> withFilter(Function1 p) {
/*  31 */       return Iterator.class.withFilter((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Iterator<T> filterNot(Function1 p) {
/*  31 */       return Iterator.class.filterNot((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/*  31 */       return Iterator.class.collect((Iterator)this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/*  31 */       return Iterator.class.scanLeft((Iterator)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/*  31 */       return Iterator.class.scanRight((Iterator)this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<T> takeWhile(Function1 p) {
/*  31 */       return Iterator.class.takeWhile((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<T>, Iterator<T>> partition(Function1 p) {
/*  31 */       return Iterator.class.partition((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<T>, Iterator<T>> span(Function1 p) {
/*  31 */       return Iterator.class.span((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Iterator<T> dropWhile(Function1 p) {
/*  31 */       return Iterator.class.dropWhile((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<T, B>> zip(Iterator that) {
/*  31 */       return Iterator.class.zip((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/*  31 */       return Iterator.class.padTo((Iterator)this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<T, Object>> zipWithIndex() {
/*  31 */       return Iterator.class.zipWithIndex((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/*  31 */       return Iterator.class.zipAll((Iterator)this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  31 */       Iterator.class.foreach((Iterator)this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/*  31 */       return Iterator.class.forall((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/*  31 */       return Iterator.class.exists((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/*  31 */       return Iterator.class.contains((Iterator)this, elem);
/*     */     }
/*     */     
/*     */     public Option<T> find(Function1 p) {
/*  31 */       return Iterator.class.find((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/*  31 */       return Iterator.class.indexWhere((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/*  31 */       return Iterator.class.indexOf((Iterator)this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<T> buffered() {
/*  31 */       return Iterator.class.buffered((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<T>.GroupedIterator<B> grouped(int size) {
/*  31 */       return Iterator.class.grouped((Iterator)this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<T>.GroupedIterator<B> sliding(int size, int step) {
/*  31 */       return Iterator.class.sliding((Iterator)this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/*  31 */       return Iterator.class.length((Iterator)this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<T>, Iterator<T>> duplicate() {
/*  31 */       return Iterator.class.duplicate((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/*  31 */       return Iterator.class.patch((Iterator)this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/*  31 */       return Iterator.class.sameElements((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public Traversable<T> toTraversable() {
/*  31 */       return Iterator.class.toTraversable((Iterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<T> toIterator() {
/*  31 */       return Iterator.class.toIterator((Iterator)this);
/*     */     }
/*     */     
/*     */     public Stream<T> toStream() {
/*  31 */       return Iterator.class.toStream((Iterator)this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  31 */       return Iterator.class.toString((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/*  31 */       return Iterator.class.sliding$default$2((Iterator)this);
/*     */     }
/*     */     
/*     */     public List<T> reversed() {
/*  31 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/*  31 */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/*  31 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/*  31 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/*  31 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/*  31 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/*  31 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/*  31 */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/*  31 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/*  31 */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/*  31 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/*  31 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/*  31 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  31 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> T maxBy(Function1 f, Ordering cmp) {
/*  31 */       return (T)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> T minBy(Function1 f, Ordering cmp) {
/*  31 */       return (T)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/*  31 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/*  31 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/*  31 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/*  31 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<T> toList() {
/*  31 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<T> toIterable() {
/*  31 */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<T> toSeq() {
/*  31 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<T> toIndexedSeq() {
/*  31 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/*  31 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*  31 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<T> toVector() {
/*  31 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/*  31 */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  31 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/*  31 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/*  31 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/*  31 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  31 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/*  31 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/*  31 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  31 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx() {
/*  32 */       return this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx;
/*     */     }
/*     */     
/*     */     private void scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx_$eq(int x$1) {
/*  32 */       this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx = x$1;
/*     */     }
/*     */     
/*     */     public int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until() {
/*  32 */       return this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until;
/*     */     }
/*     */     
/*     */     public int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$totalsize() {
/*  32 */       return this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$totalsize;
/*     */     }
/*     */     
/*     */     public Entry scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es() {
/*  32 */       return this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es;
/*     */     }
/*     */     
/*     */     private void scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es_$eq(HashEntry x$1) {
/*  32 */       this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es = (Entry)x$1;
/*     */     }
/*     */     
/*     */     public EntryIterator(ParHashTable $outer, int idx, int until, int totalsize, HashEntry es) {
/*  32 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  32 */       TraversableOnce.class.$init$((TraversableOnce)this);
/*  32 */       Iterator.class.$init$((Iterator)this);
/*  32 */       RemainsIterator.class.$init$((RemainsIterator)this);
/*  32 */       AugmentedIterableIterator.class.$init$((AugmentedIterableIterator)this);
/*  32 */       DelegatedSignalling.class.$init$((DelegatedSignalling)this);
/*  32 */       IterableSplitter.class.$init$(this);
/*  32 */       SizeMapUtils$class.$init$(this);
/*  34 */       this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable = (HashEntry<K, Entry>[])$outer.table();
/*  35 */       this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed = 0;
/*  36 */       scan();
/*     */     }
/*     */     
/*     */     public HashEntry<K, Entry>[] scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable() {
/*     */       return this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable;
/*     */     }
/*     */     
/*     */     public int scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed() {
/*     */       return this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed;
/*     */     }
/*     */     
/*     */     private void scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed_$eq(int x$1) {
/*     */       this.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*  42 */       return (scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es() != null);
/*     */     }
/*     */     
/*     */     public T next() {
/*  46 */       HashEntry res = (HashEntry)scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es();
/*  47 */       scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es_$eq((Entry)scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es().next());
/*  48 */       scan();
/*  49 */       scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed_$eq(scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed() + 1);
/*  50 */       return entry2item((Entry)res);
/*     */     }
/*     */     
/*     */     public void scan() {
/*  54 */       while (scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es() == null && scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx() < scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until()) {
/*  55 */         scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es_$eq((Entry)scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable()[scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx()]);
/*  56 */         scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx_$eq(scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx() + 1);
/*     */       } 
/*     */     }
/*     */     
/*     */     public int remaining() {
/*  60 */       return scala$collection$parallel$mutable$ParHashTable$EntryIterator$$totalsize() - scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed();
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/*  63 */       return buildString(
/*  64 */           (Function1<Function1<String, BoxedUnit>, BoxedUnit>)new ParHashTable$EntryIterator$$anonfun$debugInformation$1(this));
/*     */     }
/*     */     
/*     */     public class ParHashTable$EntryIterator$$anonfun$debugInformation$1 extends AbstractFunction1<Function1<String, BoxedUnit>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public ParHashTable$EntryIterator$$anonfun$debugInformation$1(ParHashTable.EntryIterator $outer) {}
/*     */       
/*     */       public final void apply(Function1 append) {
/*  65 */         append.apply("/--------------------\\");
/*  66 */         append.apply("Parallel hash table entry iterator");
/*  67 */         append.apply((new StringBuilder()).append("total hash table elements: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$$outer().tableSize())).toString());
/*  68 */         append.apply((new StringBuilder()).append("pos: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx())).toString());
/*  69 */         append.apply((new StringBuilder()).append("until: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until())).toString());
/*  70 */         append.apply((new StringBuilder()).append("traversed: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$traversed())).toString());
/*  71 */         append.apply((new StringBuilder()).append("totalsize: ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$totalsize())).toString());
/*  72 */         append.apply((new StringBuilder()).append("current entry: ").append(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es()).toString());
/*  73 */         append.apply((new StringBuilder()).append("underlying from ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx())).append(" until ").append(BoxesRunTime.boxToInteger(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until())).toString());
/*  74 */         append.apply(Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])Predef$.MODULE$.refArrayOps((Object[])this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable()).slice(this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx(), this.$outer.scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until())).map((Function1)new ParHashTable$EntryIterator$$anonfun$debugInformation$1$$anonfun$apply$1(this), Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.apply(String.class)))).mkString(" | "));
/*  75 */         append.apply("\\--------------------/");
/*     */       }
/*     */       
/*     */       public class ParHashTable$EntryIterator$$anonfun$debugInformation$1$$anonfun$apply$1 extends AbstractFunction1<HashEntry<K, Entry>, String> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final String apply(HashEntry x) {
/*     */           return (x == null) ? "n/a" : x.toString();
/*     */         }
/*     */         
/*     */         public ParHashTable$EntryIterator$$anonfun$debugInformation$1$$anonfun$apply$1(ParHashTable$EntryIterator$$anonfun$debugInformation$1 $outer) {}
/*     */       }
/*     */     }
/*     */     
/*     */     public IterRepr dup() {
/*  79 */       return newIterator(scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx(), scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until(), scala$collection$parallel$mutable$ParHashTable$EntryIterator$$totalsize(), scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es());
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<T>> split() {
/*  85 */       int divsz = (scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until() - scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx()) / 2;
/*  88 */       int sidx = scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx() + divsz + 1;
/*  89 */       int suntil = scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until();
/*  90 */       HashEntry<K, Entry> ses = scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable()[sidx - 1];
/*  91 */       int stotal = calcNumElems(sidx - 1, suntil, (scala$collection$parallel$mutable$ParHashTable$EntryIterator$$$outer().table()).length, scala$collection$parallel$mutable$ParHashTable$EntryIterator$$$outer().sizeMapBucketSize());
/*  94 */       int fidx = scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx();
/*  95 */       int funtil = scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx() + divsz;
/*  96 */       HashEntry fes = (HashEntry)scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es();
/*  97 */       int ftotal = scala$collection$parallel$mutable$ParHashTable$EntryIterator$$totalsize() - stotal;
/*  99 */       (new IterableSplitter[2])[0] = 
/* 100 */         (IterableSplitter)newIterator(fidx, funtil, ftotal, (Entry)fes);
/* 101 */       (new IterableSplitter[2])[1] = (IterableSplitter)newIterator(sidx, suntil, stotal, (Entry)ses);
/* 106 */       ArrayBuffer<T> arr = convertToArrayBuffer(scala$collection$parallel$mutable$ParHashTable$EntryIterator$$es());
/* 107 */       BufferSplitter arrpit = new BufferSplitter(arr, 0, arr.length(), signalDelegate());
/* 110 */       (new IterableSplitter[1])[0] = this;
/* 110 */       return (remaining() > 1) ? ((scala$collection$parallel$mutable$ParHashTable$EntryIterator$$until() > scala$collection$parallel$mutable$ParHashTable$EntryIterator$$idx()) ? (Seq<IterableSplitter<T>>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new IterableSplitter[2])) : arrpit.split()) : (Seq<IterableSplitter<T>>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new IterableSplitter[1]));
/*     */     }
/*     */     
/*     */     private ArrayBuffer<T> convertToArrayBuffer(HashEntry chainhead) {
/* 113 */       ArrayBuffer buff = (ArrayBuffer)ArrayBuffer$.MODULE$.apply((Seq)Nil$.MODULE$);
/* 114 */       HashEntry curr = chainhead;
/* 115 */       while (curr != null) {
/* 116 */         buff.$plus$eq(curr);
/* 117 */         curr = (HashEntry)curr.next();
/*     */       } 
/* 120 */       return (ArrayBuffer<T>)buff.map((Function1)new ParHashTable$EntryIterator$$anonfun$convertToArrayBuffer$1(this), ArrayBuffer$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public class ParHashTable$EntryIterator$$anonfun$convertToArrayBuffer$1 extends AbstractFunction1<Entry, T> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final T apply(HashEntry e) {
/* 120 */         return (T)this.$outer.entry2item(e);
/*     */       }
/*     */       
/*     */       public ParHashTable$EntryIterator$$anonfun$convertToArrayBuffer$1(ParHashTable.EntryIterator $outer) {}
/*     */     }
/*     */     
/*     */     public int countElems(int from, int until) {
/* 124 */       int c = 0;
/* 125 */       int idx = from;
/* 126 */       while (idx < until) {
/* 128 */         HashEntry<K, Entry> es = scala$collection$parallel$mutable$ParHashTable$EntryIterator$$itertable()[idx];
/* 129 */         while (es != null) {
/* 130 */           c++;
/* 131 */           es = (HashEntry<K, Entry>)es.next();
/*     */         } 
/* 133 */         idx++;
/*     */       } 
/* 135 */       return c;
/*     */     }
/*     */     
/*     */     public int countBucketSizes(int fromBucket, int untilBucket) {
/* 139 */       int c = 0;
/* 140 */       int idx = fromBucket;
/* 141 */       while (idx < untilBucket) {
/* 142 */         c += scala$collection$parallel$mutable$ParHashTable$EntryIterator$$$outer().sizemap()[idx];
/* 143 */         idx++;
/*     */       } 
/* 145 */       return c;
/*     */     }
/*     */     
/*     */     public abstract T entry2item(Entry param1Entry);
/*     */     
/*     */     public abstract IterRepr newIterator(int param1Int1, int param1Int2, int param1Int3, Entry param1Entry);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParHashTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */