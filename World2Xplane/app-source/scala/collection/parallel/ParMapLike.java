/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.BufferedIterator;
/*     */ import scala.collection.CustomParallelizable;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenMapLike;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenSetLike;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.MapLike;
/*     */ import scala.collection.Parallel;
/*     */ import scala.collection.Parallelizable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.DelegatedSignalling;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericParMapCompanion;
/*     */ import scala.collection.generic.GenericParMapTemplate;
/*     */ import scala.collection.generic.GenericParTemplate;
/*     */ import scala.collection.generic.GenericSetTemplate;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.generic.Signalling;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.immutable.ParMap;
/*     */ import scala.collection.parallel.immutable.ParSet;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0055gaB\001\003!\003\r\t!\003\002\013!\006\024X*\0319MS.,'BA\002\005\003!\001\030M]1mY\026d'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001Q#\002\006\026?\tZ3\003\002\001\f\037e\002\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\025\001\022c\005\020\"\033\005!\021B\001\n\005\005)9UM\\'ba2K7.\032\t\003)Ua\001\001B\003\027\001\t\007qCA\001L#\tA2\004\005\002\r3%\021!D\002\002\b\035>$\b.\0338h!\taA$\003\002\036\r\t\031\021I\\=\021\005QyBA\002\021\001\t\013\007qCA\001W!\t!\"\005\002\004$\001\021\025\r\001\n\002\005%\026\004(/\005\002\031KI\031a\005\013\034\007\t\035\002\001!\n\002\ryI,g-\0338f[\026tGO\020\t\007S\001\031b$\t\026\016\003\t\001\"\001F\026\005\r1\002AQ1\001.\005)\031V-];f]RL\027\r\\\t\00319\0222a\f\0314\r\0219\003\001\001\030\021\tA\t4CH\005\003e\021\0211!T1q!\025\001Bg\005\020+\023\t)DAA\004NCBd\025n[3\021\t%:4CH\005\003q\t\021a\001U1s\033\006\004\b#B\025;y\005R\023BA\036\003\005=\001\026M]%uKJ\f'\r\\3MS.,\007\003\002\007>'yI!A\020\004\003\rQ+\b\017\\33\021\025\001\005\001\"\001B\003\031!\023N\\5uIQ\t!\t\005\002\r\007&\021AI\002\002\005+:LG\017C\003G\001\021\005q)A\004eK\032\fW\017\034;\025\005yA\005\"B%F\001\004\031\022aA6fs\")1\n\001D\001\031\006)Q-\0349usV\t\021\005C\003O\001\021\005q*A\003baBd\027\020\006\002\037!\")\021*\024a\001'!)!\013\001C\001'\006Iq-\032;Pe\026c7/Z\013\003)Z#2!V-[!\t!b\013B\003X#\n\007\001LA\001V#\tq2\004C\003J#\002\0071\003\003\004G#\022\005\ra\027\t\004\031q+\026BA/\007\005!a$-\0378b[\026t\004\"B0\001\t\003\001\027\001C2p]R\f\027N\\:\025\005\005$\007C\001\007c\023\t\031gAA\004C_>dW-\0318\t\013%s\006\031A\n\t\013\031\004A\021A4\002\027%\034H)\0324j]\026$\027\t\036\013\003C\"DQ!S3A\002MAaA\033\001!\n\023Y\027\001D6fsNLE/\032:bi>\024HC\0017p!\rISnE\005\003]\n\021\001#\023;fe\006\024G.Z*qY&$H/\032:\t\013AL\007\031A9\002\003MT#A]:\021\007%jGhK\001u!\t)(0D\001w\025\t9\b0A\005v]\016DWmY6fI*\021\021PB\001\013C:tw\016^1uS>t\027BA>w\005E)hn\0315fG.,GMV1sS\006t7-\032\005\006U\002!\t!`\013\002Y\"9q\020\001Q\005\n\005\005\021A\004<bYV,7/\023;fe\006$xN\035\013\005\003\007\t)\001E\002*[zAQ\001\035@A\002EDaa \001\005\002\005%QCAA\002\r\031\ti\001\001\005\002\020\tiA)\0324bk2$8*Z=TKR\034R!a\003\f\003#\001B!KA\n'%\031\021Q\003\002\003\rA\013'oU3u\021!\tI\"a\003\005\002\005m\021A\002\037j]&$h\b\006\002\002\036A!\021qDA\006\033\005\001\001bB0\002\f\021\005\0211\005\013\004C\006\025\002BB%\002\"\001\0071\003C\004\002*\005-A\021A?\002\021M\004H.\033;uKJD\001\"!\f\002\f\021\005\021qF\001\006IAdWo\035\013\005\003#\t\t\004C\004\0024\005-\002\031A\n\002\t\025dW-\034\005\t\003o\tY\001\"\001\002:\0051A%\\5okN$B!!\005\002<!9\0211GA\033\001\004\031\002\002CA \003\027!\t%!\021\002\tML'0Z\013\003\003\007\0022\001DA#\023\r\t9E\002\002\004\023:$\b\002CA&\003\027!\t%!\024\002\017\031|'/Z1dQV!\021qJA/)\r\021\025\021\013\005\t\003'\nI\0051\001\002V\005\ta\r\005\004\r\003/\032\0221L\005\004\00332!!\003$v]\016$\030n\03482!\r!\022Q\f\003\b\003?\nIE1\001\030\005\005\031\006\002CA2\003\027!\t%!\032\002\007M,\027/\006\002\002hA!\001#!\033\024\023\r\tY\007\002\002\004'\026$hABA8\001!\t\tHA\013EK\032\fW\017\034;WC2,Xm]%uKJ\f'\r\\3\024\013\00554\"a\035\021\t%\n)HH\005\004\003o\022!a\003)be&#XM]1cY\026D\001\"!\007\002n\021\005\0211\020\013\003\003{\002B!a\b\002n!A\021\021FA7\t\003\tI\001\003\005\002@\0055D\021IA!\021!\tY%!\034\005B\005\025U\003BAD\003\037#2AQAE\021!\t\031&a!A\002\005-\005C\002\007\002Xy\ti\tE\002\025\003\037#q!a\030\002\004\n\007q\003\003\005\002d\0055D\021AAJ+\t\t)\n\005\003\021\003/s\022bAAM\t\tA\021\n^3sC\ndW\rC\004\002\036\002!\t!a(\002\r-,\027pU3u+\t\t\t\002C\004\002$\002!\t!!*\002\t-,\027p]\013\003\003O\003B!KA;'!9\0211\026\001\005\002\0055\026A\002<bYV,7/\006\002\002t!9\021\021\027\001\005\002\005M\026A\0034jYR,'oS3zgR\031a'!.\t\021\005]\026q\026a\001\003s\013\021\001\035\t\006\031\005]3#\031\005\b\003{\003A\021AA`\003%i\027\r\035,bYV,7/\006\003\002B\006\035G\003BAb\003\023\004R!K\034\024\003\013\0042\001FAd\t\035\ty&a/C\002]A\001\"a\025\002<\002\007\0211\032\t\007\031\005]c$!2")
/*     */ public interface ParMapLike<K, V, Repr extends ParMapLike<K, V, Repr, Sequential> & ParMap<K, V>, Sequential extends Map<K, V> & MapLike<K, V, Sequential>> extends GenMapLike<K, V, Repr>, ParIterableLike<Tuple2<K, V>, Repr, Sequential> {
/*     */   V default(K paramK);
/*     */   
/*     */   Repr empty();
/*     */   
/*     */   V apply(K paramK);
/*     */   
/*     */   <U> U getOrElse(K paramK, Function0<U> paramFunction0);
/*     */   
/*     */   boolean contains(K paramK);
/*     */   
/*     */   boolean isDefinedAt(K paramK);
/*     */   
/*     */   IterableSplitter<K> keysIterator();
/*     */   
/*     */   IterableSplitter<V> valuesIterator();
/*     */   
/*     */   ParSet<K> keySet();
/*     */   
/*     */   ParIterable<K> keys();
/*     */   
/*     */   ParIterable<V> values();
/*     */   
/*     */   ParMap<K, V> filterKeys(Function1<K, Object> paramFunction1);
/*     */   
/*     */   <S> ParMap<K, S> mapValues(Function1<V, S> paramFunction1);
/*     */   
/*     */   public class ParMapLike$$anon$3 implements IterableSplitter<K> {
/*     */     private final IterableSplitter<Tuple2<K, V>> iter;
/*     */     
/*     */     private Signalling signalDelegate;
/*     */     
/*     */     public Signalling signalDelegate() {
/*  66 */       return this.signalDelegate;
/*     */     }
/*     */     
/*     */     public void signalDelegate_$eq(Signalling x$1) {
/*  66 */       this.signalDelegate = x$1;
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<K>> splitWithSignalling() {
/*  66 */       return IterableSplitter$class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/*  66 */       return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
/*     */     }
/*     */     
/*     */     public String buildString(Function1 closure) {
/*  66 */       return IterableSplitter$class.buildString(this, closure);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/*  66 */       return IterableSplitter$class.debugInformation(this);
/*     */     }
/*     */     
/*     */     public IterableSplitter<K>.Taken newTaken(int until) {
/*  66 */       return IterableSplitter$class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public <U extends IterableSplitter<K>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/*  66 */       return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
/*     */     }
/*     */     
/*     */     public IterableSplitter<K> take(int n) {
/*  66 */       return IterableSplitter$class.take(this, n);
/*     */     }
/*     */     
/*     */     public IterableSplitter<K> slice(int from1, int until1) {
/*  66 */       return IterableSplitter$class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<K>.Mapped<S> map(Function1 f) {
/*  66 */       return IterableSplitter$class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends IterableSplitter<U>> IterableSplitter<K>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/*  66 */       return IterableSplitter$class.appendParIterable(this, that);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<K>.Zipped<S> zipParSeq(SeqSplitter that) {
/*  66 */       return IterableSplitter$class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> IterableSplitter<K>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/*  66 */       return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public boolean isAborted() {
/*  66 */       return DelegatedSignalling.class.isAborted(this);
/*     */     }
/*     */     
/*     */     public void abort() {
/*  66 */       DelegatedSignalling.class.abort(this);
/*     */     }
/*     */     
/*     */     public int indexFlag() {
/*  66 */       return DelegatedSignalling.class.indexFlag(this);
/*     */     }
/*     */     
/*     */     public void setIndexFlag(int f) {
/*  66 */       DelegatedSignalling.class.setIndexFlag(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfGreater(int f) {
/*  66 */       DelegatedSignalling.class.setIndexFlagIfGreater(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfLesser(int f) {
/*  66 */       DelegatedSignalling.class.setIndexFlagIfLesser(this, f);
/*     */     }
/*     */     
/*     */     public int tag() {
/*  66 */       return DelegatedSignalling.class.tag(this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/*  66 */       return AugmentedIterableIterator$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/*  66 */       return (U)AugmentedIterableIterator$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/*  66 */       return (U)AugmentedIterableIterator$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/*  66 */       return (U)AugmentedIterableIterator$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/*  66 */       return (U)AugmentedIterableIterator$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <U> K min(Ordering ord) {
/*  66 */       return (K)AugmentedIterableIterator$class.min(this, ord);
/*     */     }
/*     */     
/*     */     public <U> K max(Ordering ord) {
/*  66 */       return (K)AugmentedIterableIterator$class.max(this, ord);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object array, int from, int len) {
/*  66 */       AugmentedIterableIterator$class.copyToArray(this, array, from, len);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(int howmany, Function2 op) {
/*  66 */       return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/*  66 */       return AugmentedIterableIterator$class.map2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/*  66 */       return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/*  66 */       return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/*  66 */       return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/*  66 */       return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/*  66 */       return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/*  66 */       return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/*  66 */       return AugmentedIterableIterator$class.take2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/*  66 */       return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/*  66 */       return AugmentedIterableIterator$class.slice2combiner(this, from, until, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/*  66 */       return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/*  66 */       return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/*  66 */       return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
/*     */     }
/*     */     
/*     */     public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/*  66 */       AugmentedIterableIterator$class.scanToArray(this, z, op, array, from);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/*  66 */       return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/*  66 */       return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/*  66 */       return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/*  66 */       return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
/*     */     }
/*     */     
/*     */     public boolean isRemainingCheap() {
/*  66 */       return RemainsIterator$class.isRemainingCheap(this);
/*     */     }
/*     */     
/*     */     public Iterator<K> seq() {
/*  66 */       return Iterator.class.seq(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*  66 */       return Iterator.class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/*  66 */       return Iterator.class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/*  66 */       return Iterator.class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public Iterator<K> drop(int n) {
/*  66 */       return Iterator.class.drop(this, n);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/*  66 */       return Iterator.class.$plus$plus(this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/*  66 */       return Iterator.class.flatMap(this, f);
/*     */     }
/*     */     
/*     */     public Iterator<K> filter(Function1 p) {
/*  66 */       return Iterator.class.filter(this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/*  66 */       return Iterator.class.corresponds(this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<K> withFilter(Function1 p) {
/*  66 */       return Iterator.class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<K> filterNot(Function1 p) {
/*  66 */       return Iterator.class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/*  66 */       return Iterator.class.collect(this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/*  66 */       return Iterator.class.scanLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/*  66 */       return Iterator.class.scanRight(this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<K> takeWhile(Function1 p) {
/*  66 */       return Iterator.class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<K>, Iterator<K>> partition(Function1 p) {
/*  66 */       return Iterator.class.partition(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<K>, Iterator<K>> span(Function1 p) {
/*  66 */       return Iterator.class.span(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<K> dropWhile(Function1 p) {
/*  66 */       return Iterator.class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<K, B>> zip(Iterator that) {
/*  66 */       return Iterator.class.zip(this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/*  66 */       return Iterator.class.padTo(this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<K, Object>> zipWithIndex() {
/*  66 */       return Iterator.class.zipWithIndex(this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/*  66 */       return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  66 */       Iterator.class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/*  66 */       return Iterator.class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/*  66 */       return Iterator.class.exists(this, p);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/*  66 */       return Iterator.class.contains(this, elem);
/*     */     }
/*     */     
/*     */     public Option<K> find(Function1 p) {
/*  66 */       return Iterator.class.find(this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/*  66 */       return Iterator.class.indexWhere(this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/*  66 */       return Iterator.class.indexOf(this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<K> buffered() {
/*  66 */       return Iterator.class.buffered(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<K>.GroupedIterator<B> grouped(int size) {
/*  66 */       return Iterator.class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<K>.GroupedIterator<B> sliding(int size, int step) {
/*  66 */       return Iterator.class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/*  66 */       return Iterator.class.length(this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<K>, Iterator<K>> duplicate() {
/*  66 */       return Iterator.class.duplicate(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/*  66 */       return Iterator.class.patch(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/*  66 */       return Iterator.class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Traversable<K> toTraversable() {
/*  66 */       return Iterator.class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public Iterator<K> toIterator() {
/*  66 */       return Iterator.class.toIterator(this);
/*     */     }
/*     */     
/*     */     public Stream<K> toStream() {
/*  66 */       return Iterator.class.toStream(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  66 */       return Iterator.class.toString(this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/*  66 */       return Iterator.class.sliding$default$2(this);
/*     */     }
/*     */     
/*     */     public List<K> reversed() {
/*  66 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/*  66 */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/*  66 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/*  66 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/*  66 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/*  66 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/*  66 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/*  66 */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/*  66 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/*  66 */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/*  66 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/*  66 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/*  66 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  66 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> K maxBy(Function1 f, Ordering cmp) {
/*  66 */       return (K)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> K minBy(Function1 f, Ordering cmp) {
/*  66 */       return (K)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/*  66 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/*  66 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/*  66 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/*  66 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<K> toList() {
/*  66 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<K> toIterable() {
/*  66 */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<K> toSeq() {
/*  66 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<K> toIndexedSeq() {
/*  66 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/*  66 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*  66 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<K> toVector() {
/*  66 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/*  66 */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  66 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/*  66 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/*  66 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/*  66 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  66 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/*  66 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/*  66 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  66 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public ParMapLike$$anon$3(ParMapLike $outer, IterableSplitter<Tuple2<K, V>> s$1) {
/*  66 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  66 */       TraversableOnce.class.$init$((TraversableOnce)this);
/*  66 */       Iterator.class.$init$(this);
/*  66 */       RemainsIterator$class.$init$(this);
/*  66 */       AugmentedIterableIterator$class.$init$(this);
/*  66 */       DelegatedSignalling.class.$init$(this);
/*  66 */       IterableSplitter$class.$init$(this);
/*  68 */       this.iter = s$1;
/*     */     }
/*     */     
/*     */     private IterableSplitter<Tuple2<K, V>> iter() {
/*  68 */       return this.iter;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*  69 */       return iter().hasNext();
/*     */     }
/*     */     
/*     */     public K next() {
/*  70 */       return (K)((Tuple2)iter().next())._1();
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<K>> split() {
/*  72 */       Seq<IterableSplitter<K>> ss = (Seq)iter().split().map((Function1)new ParMapLike$$anon$3$$anonfun$1(this), Seq$.MODULE$.canBuildFrom());
/*  73 */       ss.foreach((Function1)new ParMapLike$$anon$3$$anonfun$split$1(this));
/*  74 */       return ss;
/*     */     }
/*     */     
/*     */     public class ParMapLike$$anon$3$$anonfun$1 extends AbstractFunction1<IterableSplitter<Tuple2<K, V>>, IterableSplitter<K>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final IterableSplitter<K> apply(IterableSplitter x$1) {
/*     */         return ParMapLike$class.scala$collection$parallel$ParMapLike$$keysIterator(this.$outer.scala$collection$parallel$ParMapLike$$anon$$$outer(), x$1);
/*     */       }
/*     */       
/*     */       public ParMapLike$$anon$3$$anonfun$1(ParMapLike$$anon$3 $outer) {}
/*     */     }
/*     */     
/*     */     public class ParMapLike$$anon$3$$anonfun$split$1 extends AbstractFunction1<IterableSplitter<K>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final void apply(IterableSplitter x$2) {
/*     */         x$2.signalDelegate_$eq(this.$outer.signalDelegate());
/*     */       }
/*     */       
/*     */       public ParMapLike$$anon$3$$anonfun$split$1(ParMapLike$$anon$3 $outer) {}
/*     */     }
/*     */     
/*     */     public int remaining() {
/*  76 */       return iter().remaining();
/*     */     }
/*     */     
/*     */     public IterableSplitter<K> dup() {
/*  77 */       return ParMapLike$class.scala$collection$parallel$ParMapLike$$keysIterator(this.$outer, iter().dup());
/*     */     }
/*     */   }
/*     */   
/*     */   public class ParMapLike$$anon$4 implements IterableSplitter<V> {
/*     */     private final IterableSplitter<Tuple2<K, V>> iter;
/*     */     
/*     */     private Signalling signalDelegate;
/*     */     
/*     */     public Signalling signalDelegate() {
/*  83 */       return this.signalDelegate;
/*     */     }
/*     */     
/*     */     public void signalDelegate_$eq(Signalling x$1) {
/*  83 */       this.signalDelegate = x$1;
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<V>> splitWithSignalling() {
/*  83 */       return IterableSplitter$class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/*  83 */       return IterableSplitter$class.shouldSplitFurther(this, coll, parallelismLevel);
/*     */     }
/*     */     
/*     */     public String buildString(Function1 closure) {
/*  83 */       return IterableSplitter$class.buildString(this, closure);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/*  83 */       return IterableSplitter$class.debugInformation(this);
/*     */     }
/*     */     
/*     */     public IterableSplitter<V>.Taken newTaken(int until) {
/*  83 */       return IterableSplitter$class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public <U extends IterableSplitter<V>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/*  83 */       return (U)IterableSplitter$class.newSliceInternal(this, it, from1);
/*     */     }
/*     */     
/*     */     public IterableSplitter<V> take(int n) {
/*  83 */       return IterableSplitter$class.take(this, n);
/*     */     }
/*     */     
/*     */     public IterableSplitter<V> slice(int from1, int until1) {
/*  83 */       return IterableSplitter$class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<V>.Mapped<S> map(Function1 f) {
/*  83 */       return IterableSplitter$class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends IterableSplitter<U>> IterableSplitter<V>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/*  83 */       return IterableSplitter$class.appendParIterable(this, that);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<V>.Zipped<S> zipParSeq(SeqSplitter that) {
/*  83 */       return IterableSplitter$class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> IterableSplitter<V>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/*  83 */       return IterableSplitter$class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public boolean isAborted() {
/*  83 */       return DelegatedSignalling.class.isAborted(this);
/*     */     }
/*     */     
/*     */     public void abort() {
/*  83 */       DelegatedSignalling.class.abort(this);
/*     */     }
/*     */     
/*     */     public int indexFlag() {
/*  83 */       return DelegatedSignalling.class.indexFlag(this);
/*     */     }
/*     */     
/*     */     public void setIndexFlag(int f) {
/*  83 */       DelegatedSignalling.class.setIndexFlag(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfGreater(int f) {
/*  83 */       DelegatedSignalling.class.setIndexFlagIfGreater(this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfLesser(int f) {
/*  83 */       DelegatedSignalling.class.setIndexFlagIfLesser(this, f);
/*     */     }
/*     */     
/*     */     public int tag() {
/*  83 */       return DelegatedSignalling.class.tag(this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/*  83 */       return AugmentedIterableIterator$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/*  83 */       return (U)AugmentedIterableIterator$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/*  83 */       return (U)AugmentedIterableIterator$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/*  83 */       return (U)AugmentedIterableIterator$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/*  83 */       return (U)AugmentedIterableIterator$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <U> V min(Ordering ord) {
/*  83 */       return (V)AugmentedIterableIterator$class.min(this, ord);
/*     */     }
/*     */     
/*     */     public <U> V max(Ordering ord) {
/*  83 */       return (V)AugmentedIterableIterator$class.max(this, ord);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object array, int from, int len) {
/*  83 */       AugmentedIterableIterator$class.copyToArray(this, array, from, len);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(int howmany, Function2 op) {
/*  83 */       return (U)AugmentedIterableIterator$class.reduceLeft(this, howmany, op);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/*  83 */       return AugmentedIterableIterator$class.map2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/*  83 */       return AugmentedIterableIterator$class.collect2combiner(this, pf, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/*  83 */       return AugmentedIterableIterator$class.flatmap2combiner(this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/*  83 */       return (Bld)AugmentedIterableIterator$class.copy2builder(this, b);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/*  83 */       return AugmentedIterableIterator$class.filter2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/*  83 */       return AugmentedIterableIterator$class.filterNot2combiner(this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/*  83 */       return AugmentedIterableIterator$class.partition2combiners(this, pred, btrue, bfalse);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/*  83 */       return AugmentedIterableIterator$class.take2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/*  83 */       return AugmentedIterableIterator$class.drop2combiner(this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/*  83 */       return AugmentedIterableIterator$class.slice2combiner(this, from, until, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/*  83 */       return AugmentedIterableIterator$class.splitAt2combiners(this, at, before, after);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/*  83 */       return AugmentedIterableIterator$class.takeWhile2combiner(this, p, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/*  83 */       return AugmentedIterableIterator$class.span2combiners(this, p, before, after);
/*     */     }
/*     */     
/*     */     public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/*  83 */       AugmentedIterableIterator$class.scanToArray(this, z, op, array, from);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/*  83 */       return AugmentedIterableIterator$class.scanToCombiner(this, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/*  83 */       return AugmentedIterableIterator$class.scanToCombiner(this, howmany, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/*  83 */       return AugmentedIterableIterator$class.zip2combiner(this, otherpit, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/*  83 */       return AugmentedIterableIterator$class.zipAll2combiner(this, that, thiselem, thatelem, cb);
/*     */     }
/*     */     
/*     */     public boolean isRemainingCheap() {
/*  83 */       return RemainsIterator$class.isRemainingCheap(this);
/*     */     }
/*     */     
/*     */     public Iterator<V> seq() {
/*  83 */       return Iterator.class.seq(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*  83 */       return Iterator.class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/*  83 */       return Iterator.class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/*  83 */       return Iterator.class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public Iterator<V> drop(int n) {
/*  83 */       return Iterator.class.drop(this, n);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/*  83 */       return Iterator.class.$plus$plus(this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/*  83 */       return Iterator.class.flatMap(this, f);
/*     */     }
/*     */     
/*     */     public Iterator<V> filter(Function1 p) {
/*  83 */       return Iterator.class.filter(this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/*  83 */       return Iterator.class.corresponds(this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<V> withFilter(Function1 p) {
/*  83 */       return Iterator.class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<V> filterNot(Function1 p) {
/*  83 */       return Iterator.class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/*  83 */       return Iterator.class.collect(this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/*  83 */       return Iterator.class.scanLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/*  83 */       return Iterator.class.scanRight(this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<V> takeWhile(Function1 p) {
/*  83 */       return Iterator.class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<V>, Iterator<V>> partition(Function1 p) {
/*  83 */       return Iterator.class.partition(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<V>, Iterator<V>> span(Function1 p) {
/*  83 */       return Iterator.class.span(this, p);
/*     */     }
/*     */     
/*     */     public Iterator<V> dropWhile(Function1 p) {
/*  83 */       return Iterator.class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<V, B>> zip(Iterator that) {
/*  83 */       return Iterator.class.zip(this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/*  83 */       return Iterator.class.padTo(this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<V, Object>> zipWithIndex() {
/*  83 */       return Iterator.class.zipWithIndex(this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/*  83 */       return Iterator.class.zipAll(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  83 */       Iterator.class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/*  83 */       return Iterator.class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/*  83 */       return Iterator.class.exists(this, p);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/*  83 */       return Iterator.class.contains(this, elem);
/*     */     }
/*     */     
/*     */     public Option<V> find(Function1 p) {
/*  83 */       return Iterator.class.find(this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/*  83 */       return Iterator.class.indexWhere(this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/*  83 */       return Iterator.class.indexOf(this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<V> buffered() {
/*  83 */       return Iterator.class.buffered(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<V>.GroupedIterator<B> grouped(int size) {
/*  83 */       return Iterator.class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<V>.GroupedIterator<B> sliding(int size, int step) {
/*  83 */       return Iterator.class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/*  83 */       return Iterator.class.length(this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<V>, Iterator<V>> duplicate() {
/*  83 */       return Iterator.class.duplicate(this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/*  83 */       return Iterator.class.patch(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/*  83 */       return Iterator.class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Traversable<V> toTraversable() {
/*  83 */       return Iterator.class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public Iterator<V> toIterator() {
/*  83 */       return Iterator.class.toIterator(this);
/*     */     }
/*     */     
/*     */     public Stream<V> toStream() {
/*  83 */       return Iterator.class.toStream(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  83 */       return Iterator.class.toString(this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/*  83 */       return Iterator.class.sliding$default$2(this);
/*     */     }
/*     */     
/*     */     public List<V> reversed() {
/*  83 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/*  83 */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/*  83 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/*  83 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/*  83 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/*  83 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/*  83 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/*  83 */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/*  83 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/*  83 */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/*  83 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/*  83 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/*  83 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  83 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> V maxBy(Function1 f, Ordering cmp) {
/*  83 */       return (V)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> V minBy(Function1 f, Ordering cmp) {
/*  83 */       return (V)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/*  83 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/*  83 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/*  83 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/*  83 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<V> toList() {
/*  83 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<V> toIterable() {
/*  83 */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<V> toSeq() {
/*  83 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<V> toIndexedSeq() {
/*  83 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/*  83 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*  83 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<V> toVector() {
/*  83 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/*  83 */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  83 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/*  83 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/*  83 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/*  83 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  83 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/*  83 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/*  83 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  83 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public ParMapLike$$anon$4(ParMapLike $outer, IterableSplitter<Tuple2<K, V>> s$2) {
/*  83 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  83 */       TraversableOnce.class.$init$((TraversableOnce)this);
/*  83 */       Iterator.class.$init$(this);
/*  83 */       RemainsIterator$class.$init$(this);
/*  83 */       AugmentedIterableIterator$class.$init$(this);
/*  83 */       DelegatedSignalling.class.$init$(this);
/*  83 */       IterableSplitter$class.$init$(this);
/*  85 */       this.iter = s$2;
/*     */     }
/*     */     
/*     */     private IterableSplitter<Tuple2<K, V>> iter() {
/*  85 */       return this.iter;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*  86 */       return iter().hasNext();
/*     */     }
/*     */     
/*     */     public V next() {
/*  87 */       return (V)((Tuple2)iter().next())._2();
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<V>> split() {
/*  89 */       Seq<IterableSplitter<V>> ss = (Seq)iter().split().map((Function1)new ParMapLike$$anon$4$$anonfun$2(this), Seq$.MODULE$.canBuildFrom());
/*  90 */       ss.foreach((Function1)new ParMapLike$$anon$4$$anonfun$split$2(this));
/*  91 */       return ss;
/*     */     }
/*     */     
/*     */     public class ParMapLike$$anon$4$$anonfun$2 extends AbstractFunction1<IterableSplitter<Tuple2<K, V>>, IterableSplitter<V>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final IterableSplitter<V> apply(IterableSplitter x$3) {
/*     */         return ParMapLike$class.scala$collection$parallel$ParMapLike$$valuesIterator(this.$outer.scala$collection$parallel$ParMapLike$$anon$$$outer(), x$3);
/*     */       }
/*     */       
/*     */       public ParMapLike$$anon$4$$anonfun$2(ParMapLike$$anon$4 $outer) {}
/*     */     }
/*     */     
/*     */     public class ParMapLike$$anon$4$$anonfun$split$2 extends AbstractFunction1<IterableSplitter<V>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final void apply(IterableSplitter x$4) {
/*     */         x$4.signalDelegate_$eq(this.$outer.signalDelegate());
/*     */       }
/*     */       
/*     */       public ParMapLike$$anon$4$$anonfun$split$2(ParMapLike$$anon$4 $outer) {}
/*     */     }
/*     */     
/*     */     public int remaining() {
/*  93 */       return iter().remaining();
/*     */     }
/*     */     
/*     */     public IterableSplitter<V> dup() {
/*  94 */       return ParMapLike$class.scala$collection$parallel$ParMapLike$$valuesIterator(this.$outer, iter().dup());
/*     */     }
/*     */   }
/*     */   
/*     */   public class DefaultKeySet implements ParSet<K> {
/*     */     private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */     
/*     */     private volatile ParIterableLike.ScanNode$ ScanNode$module;
/*     */     
/*     */     private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;
/*     */     
/*     */     public ParSet<K> empty() {
/*  99 */       return ParSet$class.empty(this);
/*     */     }
/*     */     
/*     */     public GenericCompanion<ParSet> companion() {
/*  99 */       return ParSet$class.companion(this);
/*     */     }
/*     */     
/*     */     public String stringPrefix() {
/*  99 */       return ParSet$class.stringPrefix(this);
/*     */     }
/*     */     
/*     */     public ParSet<K> union(GenSet that) {
/*  99 */       return ParSetLike$class.union(this, that);
/*     */     }
/*     */     
/*     */     public ParSet<K> diff(GenSet that) {
/*  99 */       return ParSetLike$class.diff(this, that);
/*     */     }
/*     */     
/*     */     public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
/*  99 */       return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
/*  99 */       this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
/*     */     }
/*     */     
/*     */     private ParIterableLike.ScanNode$ ScanNode$lzycompute() {
/*  99 */       synchronized (this) {
/*  99 */         if (this.ScanNode$module == null)
/*  99 */           this.ScanNode$module = new ParIterableLike.ScanNode$(this); 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/collection/parallel/ParMapLike}.Lscala/collection/parallel/ParMapLike$DefaultKeySet;}} */
/*  99 */         return this.ScanNode$module;
/*     */       } 
/*     */     }
/*     */     
/*     */     public ParIterableLike.ScanNode$ ScanNode() {
/*  99 */       return (this.ScanNode$module == null) ? ScanNode$lzycompute() : this.ScanNode$module;
/*     */     }
/*     */     
/*     */     private ParIterableLike.ScanLeaf$ ScanLeaf$lzycompute() {
/*  99 */       synchronized (this) {
/*  99 */         if (this.ScanLeaf$module == null)
/*  99 */           this.ScanLeaf$module = new ParIterableLike.ScanLeaf$(this); 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/collection/parallel/ParMapLike}.Lscala/collection/parallel/ParMapLike$DefaultKeySet;}} */
/*  99 */         return this.ScanLeaf$module;
/*     */       } 
/*     */     }
/*     */     
/*     */     public ParIterableLike.ScanLeaf$ ScanLeaf() {
/*  99 */       return (this.ScanLeaf$module == null) ? ScanLeaf$lzycompute() : this.ScanLeaf$module;
/*     */     }
/*     */     
/*     */     public void initTaskSupport() {
/*  99 */       ParIterableLike$class.initTaskSupport(this);
/*     */     }
/*     */     
/*     */     public TaskSupport tasksupport() {
/*  99 */       return ParIterableLike$class.tasksupport(this);
/*     */     }
/*     */     
/*     */     public void tasksupport_$eq(TaskSupport ts) {
/*  99 */       ParIterableLike$class.tasksupport_$eq(this, ts);
/*     */     }
/*     */     
/*     */     public ParSet<K> repr() {
/*  99 */       return (ParSet<K>)ParIterableLike$class.repr(this);
/*     */     }
/*     */     
/*     */     public final boolean isTraversableAgain() {
/*  99 */       return ParIterableLike$class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/*  99 */       return ParIterableLike$class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*  99 */       return ParIterableLike$class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/*  99 */       return ParIterableLike$class.nonEmpty(this);
/*     */     }
/*     */     
/*     */     public K head() {
/*  99 */       return (K)ParIterableLike$class.head(this);
/*     */     }
/*     */     
/*     */     public Option<K> headOption() {
/*  99 */       return ParIterableLike$class.headOption(this);
/*     */     }
/*     */     
/*     */     public ParSet<K> tail() {
/*  99 */       return (ParSet<K>)ParIterableLike$class.tail(this);
/*     */     }
/*     */     
/*     */     public K last() {
/*  99 */       return (K)ParIterableLike$class.last(this);
/*     */     }
/*     */     
/*     */     public Option<K> lastOption() {
/*  99 */       return ParIterableLike$class.lastOption(this);
/*     */     }
/*     */     
/*     */     public ParSet<K> init() {
/*  99 */       return (ParSet<K>)ParIterableLike$class.init(this);
/*     */     }
/*     */     
/*     */     public Splitter<K> iterator() {
/*  99 */       return ParIterableLike$class.iterator(this);
/*     */     }
/*     */     
/*     */     public ParSet<K> par() {
/*  99 */       return (ParSet<K>)ParIterableLike$class.par(this);
/*     */     }
/*     */     
/*     */     public boolean isStrictSplitterCollection() {
/*  99 */       return ParIterableLike$class.isStrictSplitterCollection(this);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> reuse(Option oldc, Combiner newc) {
/*  99 */       return ParIterableLike$class.reuse(this, oldc, newc);
/*     */     }
/*     */     
/*     */     public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask tsk) {
/*  99 */       return ParIterableLike$class.task2ops(this, tsk);
/*     */     }
/*     */     
/*     */     public <R> Object wrap(Function0 body) {
/*  99 */       return ParIterableLike$class.wrap(this, body);
/*     */     }
/*     */     
/*     */     public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(DelegatedSignalling it) {
/*  99 */       return ParIterableLike$class.delegatedSignalling2ops(this, it);
/*     */     }
/*     */     
/*     */     public <Elem, To> Object builder2ops(Builder cb) {
/*  99 */       return ParIterableLike$class.builder2ops(this, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Object bf2seq(CanBuildFrom bf) {
/*  99 */       return ParIterableLike$class.bf2seq(this, bf);
/*     */     }
/*     */     
/*     */     public <S, That extends Parallel> ParSet<K> sequentially(Function1 b) {
/*  99 */       return (ParSet<K>)ParIterableLike$class.sequentially(this, b);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/*  99 */       return ParIterableLike$class.mkString(this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/*  99 */       return ParIterableLike$class.mkString(this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/*  99 */       return ParIterableLike$class.mkString(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  99 */       return ParIterableLike$class.toString(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object other) {
/*  99 */       return ParIterableLike$class.canEqual(this, other);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/*  99 */       return (U)ParIterableLike$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <U> Option<U> reduceOption(Function2 op) {
/*  99 */       return ParIterableLike$class.reduceOption(this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/*  99 */       return (U)ParIterableLike$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <S> S aggregate(Object z, Function2 seqop, Function2 combop) {
/*  99 */       return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <S> S foldLeft(Object z, Function2 op) {
/*  99 */       return (S)ParIterableLike$class.foldLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <S> S foldRight(Object z, Function2 op) {
/*  99 */       return (S)ParIterableLike$class.foldRight(this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(Function2 op) {
/*  99 */       return (U)ParIterableLike$class.reduceLeft(this, op);
/*     */     }
/*     */     
/*     */     public <U> U reduceRight(Function2 op) {
/*  99 */       return (U)ParIterableLike$class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public <U> Option<U> reduceLeftOption(Function2 op) {
/*  99 */       return ParIterableLike$class.reduceLeftOption(this, op);
/*     */     }
/*     */     
/*     */     public <U> Option<U> reduceRightOption(Function2 op) {
/*  99 */       return ParIterableLike$class.reduceRightOption(this, op);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/*  99 */       return ParIterableLike$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/*  99 */       return (U)ParIterableLike$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/*  99 */       return (U)ParIterableLike$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <U> K min(Ordering ord) {
/*  99 */       return (K)ParIterableLike$class.min(this, ord);
/*     */     }
/*     */     
/*     */     public <U> K max(Ordering ord) {
/*  99 */       return (K)ParIterableLike$class.max(this, ord);
/*     */     }
/*     */     
/*     */     public <S> K maxBy(Function1 f, Ordering cmp) {
/*  99 */       return (K)ParIterableLike$class.maxBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <S> K minBy(Function1 f, Ordering cmp) {
/*  99 */       return (K)ParIterableLike$class.minBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <S, That> That map(Function1 f, CanBuildFrom bf) {
/*  99 */       return (That)ParIterableLike$class.map(this, f, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  99 */       return (That)ParIterableLike$class.collect(this, pf, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  99 */       return (That)ParIterableLike$class.flatMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 pred) {
/*  99 */       return ParIterableLike$class.forall(this, pred);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 pred) {
/*  99 */       return ParIterableLike$class.exists(this, pred);
/*     */     }
/*     */     
/*     */     public Option<K> find(Function1 pred) {
/*  99 */       return ParIterableLike$class.find(this, pred);
/*     */     }
/*     */     
/*     */     public Object combinerFactory() {
/*  99 */       return ParIterableLike$class.combinerFactory(this);
/*     */     }
/*     */     
/*     */     public <S, That> Object combinerFactory(Function0 cbf) {
/*  99 */       return ParIterableLike$class.combinerFactory(this, cbf);
/*     */     }
/*     */     
/*     */     public ParSet<K> filter(Function1 pred) {
/*  99 */       return (ParSet<K>)ParIterableLike$class.filter(this, pred);
/*     */     }
/*     */     
/*     */     public ParSet<K> filterNot(Function1 pred) {
/*  99 */       return (ParSet<K>)ParIterableLike$class.filterNot(this, pred);
/*     */     }
/*     */     
/*     */     public <U, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/*  99 */       return (That)ParIterableLike$class.$plus$plus(this, that, bf);
/*     */     }
/*     */     
/*     */     public Tuple2<ParSet<K>, ParSet<K>> partition(Function1 pred) {
/*  99 */       return ParIterableLike$class.partition(this, pred);
/*     */     }
/*     */     
/*     */     public <K> ParMap<K, ParSet<K>> groupBy(Function1 f) {
/*  99 */       return ParIterableLike$class.groupBy(this, f);
/*     */     }
/*     */     
/*     */     public ParSet<K> take(int n) {
/*  99 */       return (ParSet<K>)ParIterableLike$class.take(this, n);
/*     */     }
/*     */     
/*     */     public ParSet<K> drop(int n) {
/*  99 */       return (ParSet<K>)ParIterableLike$class.drop(this, n);
/*     */     }
/*     */     
/*     */     public ParSet<K> slice(int unc_from, int unc_until) {
/*  99 */       return (ParSet<K>)ParIterableLike$class.slice(this, unc_from, unc_until);
/*     */     }
/*     */     
/*     */     public Tuple2<ParSet<K>, ParSet<K>> splitAt(int n) {
/*  99 */       return ParIterableLike$class.splitAt(this, n);
/*     */     }
/*     */     
/*     */     public <U, That> That scan(Object z, Function2 op, CanBuildFrom bf) {
/*  99 */       return (That)ParIterableLike$class.scan(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  99 */       return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  99 */       return (That)ParIterableLike$class.scanRight(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public ParSet<K> takeWhile(Function1 pred) {
/*  99 */       return (ParSet<K>)ParIterableLike$class.takeWhile(this, pred);
/*     */     }
/*     */     
/*     */     public Tuple2<ParSet<K>, ParSet<K>> span(Function1 pred) {
/*  99 */       return ParIterableLike$class.span(this, pred);
/*     */     }
/*     */     
/*     */     public ParSet<K> dropWhile(Function1 pred) {
/*  99 */       return (ParSet<K>)ParIterableLike$class.dropWhile(this, pred);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object xs) {
/*  99 */       ParIterableLike$class.copyToArray(this, xs);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object xs, int start) {
/*  99 */       ParIterableLike$class.copyToArray(this, xs, start);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object xs, int start, int len) {
/*  99 */       ParIterableLike$class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public <U> boolean sameElements(GenIterable that) {
/*  99 */       return ParIterableLike$class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public <U, S, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  99 */       return (That)ParIterableLike$class.zip(this, that, bf);
/*     */     }
/*     */     
/*     */     public <U, That> That zipWithIndex(CanBuildFrom bf) {
/*  99 */       return (That)ParIterableLike$class.zipWithIndex(this, bf);
/*     */     }
/*     */     
/*     */     public <S, U, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  99 */       return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
/*     */     }
/*     */     
/*     */     public <U, That> That toParCollection(Function0 cbf) {
/*  99 */       return (That)ParIterableLike$class.toParCollection(this, cbf);
/*     */     }
/*     */     
/*     */     public <K, V, That> That toParMap(Function0 cbf, Predef$.less.colon.less ev) {
/*  99 */       return (That)ParIterableLike$class.toParMap(this, cbf, ev);
/*     */     }
/*     */     
/*     */     public Object view() {
/*  99 */       return ParIterableLike$class.view(this);
/*     */     }
/*     */     
/*     */     public <U> Object toArray(ClassTag evidence$1) {
/*  99 */       return ParIterableLike$class.toArray(this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<K> toList() {
/*  99 */       return ParIterableLike$class.toList(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<K> toIndexedSeq() {
/*  99 */       return ParIterableLike$class.toIndexedSeq(this);
/*     */     }
/*     */     
/*     */     public Stream<K> toStream() {
/*  99 */       return ParIterableLike$class.toStream(this);
/*     */     }
/*     */     
/*     */     public Iterator<K> toIterator() {
/*  99 */       return ParIterableLike$class.toIterator(this);
/*     */     }
/*     */     
/*     */     public <U> Buffer<U> toBuffer() {
/*  99 */       return ParIterableLike$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public GenTraversable<K> toTraversable() {
/*  99 */       return ParIterableLike$class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public ParIterable<K> toIterable() {
/*  99 */       return ParIterableLike$class.toIterable(this);
/*     */     }
/*     */     
/*     */     public ParSeq<K> toSeq() {
/*  99 */       return ParIterableLike$class.toSeq(this);
/*     */     }
/*     */     
/*     */     public <U> ParSet<U> toSet() {
/*  99 */       return ParIterableLike$class.toSet(this);
/*     */     }
/*     */     
/*     */     public <K, V> ParMap<K, V> toMap(Predef$.less.colon.less ev) {
/*  99 */       return ParIterableLike$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public Vector<K> toVector() {
/*  99 */       return ParIterableLike$class.toVector(this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/*  99 */       return (Col)ParIterableLike$class.to(this, cbf);
/*     */     }
/*     */     
/*     */     public int scanBlockSize() {
/*  99 */       return ParIterableLike$class.scanBlockSize(this);
/*     */     }
/*     */     
/*     */     public <S> S $div$colon(Object z, Function2 op) {
/*  99 */       return (S)ParIterableLike$class.$div$colon(this, z, op);
/*     */     }
/*     */     
/*     */     public <S> S $colon$bslash(Object z, Function2 op) {
/*  99 */       return (S)ParIterableLike$class.$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/*  99 */       return ParIterableLike$class.debugInformation(this);
/*     */     }
/*     */     
/*     */     public Seq<String> brokenInvariants() {
/*  99 */       return ParIterableLike$class.brokenInvariants(this);
/*     */     }
/*     */     
/*     */     public ArrayBuffer<String> debugBuffer() {
/*  99 */       return ParIterableLike$class.debugBuffer(this);
/*     */     }
/*     */     
/*     */     public void debugclear() {
/*  99 */       ParIterableLike$class.debugclear(this);
/*     */     }
/*     */     
/*     */     public ArrayBuffer<String> debuglog(String s) {
/*  99 */       return ParIterableLike$class.debuglog(this, s);
/*     */     }
/*     */     
/*     */     public void printDebugBuffer() {
/*  99 */       ParIterableLike$class.printDebugBuffer(this);
/*     */     }
/*     */     
/*     */     public Combiner<K, ParSet<K>> parCombiner() {
/*  99 */       return CustomParallelizable.class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public Builder<K, ParSet<K>> newBuilder() {
/*  99 */       return GenericParTemplate.class.newBuilder(this);
/*     */     }
/*     */     
/*     */     public Combiner<K, ParSet<K>> newCombiner() {
/*  99 */       return GenericParTemplate.class.newCombiner(this);
/*     */     }
/*     */     
/*     */     public <B> Combiner<B, ParSet<B>> genericBuilder() {
/*  99 */       return GenericParTemplate.class.genericBuilder(this);
/*     */     }
/*     */     
/*     */     public <B> Combiner<B, ParSet<B>> genericCombiner() {
/*  99 */       return GenericParTemplate.class.genericCombiner(this);
/*     */     }
/*     */     
/*     */     public <A1, A2> Tuple2<ParSet<A1>, ParSet<A2>> unzip(Function1 asPair) {
/*  99 */       return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*     */     }
/*     */     
/*     */     public <A1, A2, A3> Tuple3<ParSet<A1>, ParSet<A2>, ParSet<A3>> unzip3(Function1 asTriple) {
/*  99 */       return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*     */     }
/*     */     
/*     */     public <B> ParSet<B> flatten(Function1 asTraversable) {
/*  99 */       return (ParSet<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*     */     }
/*     */     
/*     */     public <B> ParSet<ParSet<B>> transpose(Function1 asTraversable) {
/*  99 */       return (ParSet<ParSet<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*     */     }
/*     */     
/*     */     public boolean apply(Object elem) {
/*  99 */       return GenSetLike.class.apply(this, elem);
/*     */     }
/*     */     
/*     */     public ParSet<K> intersect(GenSet that) {
/*  99 */       return (ParSet<K>)GenSetLike.class.intersect(this, that);
/*     */     }
/*     */     
/*     */     public ParSet<K> $amp(GenSet that) {
/*  99 */       return (ParSet<K>)GenSetLike.class.$amp(this, that);
/*     */     }
/*     */     
/*     */     public ParSet<K> $bar(GenSet that) {
/*  99 */       return (ParSet<K>)GenSetLike.class.$bar(this, that);
/*     */     }
/*     */     
/*     */     public ParSet<K> $amp$tilde(GenSet that) {
/*  99 */       return (ParSet<K>)GenSetLike.class.$amp$tilde(this, that);
/*     */     }
/*     */     
/*     */     public boolean subsetOf(GenSet that) {
/*  99 */       return GenSetLike.class.subsetOf(this, that);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/*  99 */       return GenSetLike.class.equals(this, that);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  99 */       return GenSetLike.class.hashCode(this);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZD$sp(double v1) {
/*  99 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/*  99 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/*  99 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/*  99 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/*  99 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/*  99 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/*  99 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/*  99 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/*  99 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/*  99 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/*  99 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/*  99 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/*  99 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/*  99 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/*  99 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/*  99 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/*  99 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/*  99 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/*  99 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/*  99 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/*  99 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/*  99 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/*  99 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/*  99 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose(Function1 g) {
/*  99 */       return Function1.class.compose((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*  99 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<K, A> andThen(Function1 g) {
/*  99 */       return Function1.class.andThen((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*  99 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  99 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public DefaultKeySet(ParMapLike $outer) {
/*  99 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  99 */       Parallelizable.class.$init$((Parallelizable)this);
/*  99 */       Function1.class.$init$((Function1)this);
/*  99 */       GenSetLike.class.$init$(this);
/*  99 */       GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/*  99 */       GenTraversable.class.$init$((GenTraversable)this);
/*  99 */       GenIterable.class.$init$(this);
/*  99 */       GenericSetTemplate.class.$init$((GenericSetTemplate)this);
/*  99 */       GenSet.class.$init$(this);
/*  99 */       GenericParTemplate.class.$init$(this);
/*  99 */       CustomParallelizable.class.$init$(this);
/*  99 */       ParIterableLike$class.$init$(this);
/*  99 */       ParIterable$class.$init$(this);
/*  99 */       ParSetLike$class.$init$(this);
/*  99 */       ParSet$class.$init$(this);
/*     */     }
/*     */     
/*     */     public boolean contains(Object key) {
/* 100 */       return scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().contains(key);
/*     */     }
/*     */     
/*     */     public IterableSplitter<K> splitter() {
/* 101 */       return ParMapLike$class.scala$collection$parallel$ParMapLike$$keysIterator(scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer(), scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().splitter());
/*     */     }
/*     */     
/*     */     public ParSet<K> $plus(Object elem) {
/* 103 */       return (ParSet<K>)((GenSetLike)((ParIterableLike)ParSet$.MODULE$.apply((Seq)Nil$.MODULE$)).$plus$plus((GenTraversableOnce)this, (CanBuildFrom)ParSet$.MODULE$.canBuildFrom())).$plus(elem);
/*     */     }
/*     */     
/*     */     public ParSet<K> $minus(Object elem) {
/* 105 */       return (ParSet<K>)((GenSetLike)((ParIterableLike)ParSet$.MODULE$.apply((Seq)Nil$.MODULE$)).$plus$plus((GenTraversableOnce)this, (CanBuildFrom)ParSet$.MODULE$.canBuildFrom())).$minus(elem);
/*     */     }
/*     */     
/*     */     public int size() {
/* 106 */       return scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().size();
/*     */     }
/*     */     
/*     */     public <S> void foreach(Function1 f) {
/* 107 */       scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().filter((Function1)new ParMapLike$DefaultKeySet$$anonfun$foreach$1(this)).foreach((Function1)new ParMapLike$DefaultKeySet$$anonfun$foreach$2(this, (DefaultKeySet)f));
/*     */     }
/*     */     
/*     */     public class ParMapLike$DefaultKeySet$$anonfun$foreach$1 extends AbstractFunction1<Tuple2<K, V>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Tuple2 check$ifrefutable$1) {
/*     */         boolean bool;
/* 107 */         if (check$ifrefutable$1 != null) {
/* 107 */           bool = true;
/*     */         } else {
/* 107 */           bool = false;
/*     */         } 
/* 107 */         return bool;
/*     */       }
/*     */       
/*     */       public ParMapLike$DefaultKeySet$$anonfun$foreach$1(ParMapLike.DefaultKeySet $outer) {}
/*     */     }
/*     */     
/*     */     public class ParMapLike$DefaultKeySet$$anonfun$foreach$2 extends AbstractFunction1<Tuple2<K, V>, S> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 f$1;
/*     */       
/*     */       public final S apply(Tuple2 x$5) {
/* 107 */         if (x$5 != null)
/* 107 */           return (S)this.f$1.apply(x$5._1()); 
/* 107 */         throw new MatchError(x$5);
/*     */       }
/*     */       
/*     */       public ParMapLike$DefaultKeySet$$anonfun$foreach$2(ParMapLike.DefaultKeySet $outer, Function1 f$1) {}
/*     */     }
/*     */     
/*     */     public Set<K> seq() {
/* 108 */       return ((MapLike)scala$collection$parallel$ParMapLike$DefaultKeySet$$$outer().seq()).keySet();
/*     */     }
/*     */   }
/*     */   
/*     */   public class DefaultValuesIterable implements ParIterable<V> {
/*     */     private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */     
/*     */     private volatile ParIterableLike.ScanNode$ ScanNode$module;
/*     */     
/*     */     private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;
/*     */     
/*     */     public GenericCompanion<ParIterable> companion() {
/* 111 */       return ParIterable$class.companion(this);
/*     */     }
/*     */     
/*     */     public String stringPrefix() {
/* 111 */       return ParIterable$class.stringPrefix(this);
/*     */     }
/*     */     
/*     */     public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
/* 111 */       return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
/* 111 */       this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
/*     */     }
/*     */     
/*     */     private ParIterableLike.ScanNode$ ScanNode$lzycompute() {
/* 111 */       synchronized (this) {
/* 111 */         if (this.ScanNode$module == null)
/* 111 */           this.ScanNode$module = new ParIterableLike.ScanNode$(this); 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/collection/parallel/ParMapLike}.Lscala/collection/parallel/ParMapLike$DefaultValuesIterable;}} */
/* 111 */         return this.ScanNode$module;
/*     */       } 
/*     */     }
/*     */     
/*     */     public ParIterableLike.ScanNode$ ScanNode() {
/* 111 */       return (this.ScanNode$module == null) ? ScanNode$lzycompute() : this.ScanNode$module;
/*     */     }
/*     */     
/*     */     private ParIterableLike.ScanLeaf$ ScanLeaf$lzycompute() {
/* 111 */       synchronized (this) {
/* 111 */         if (this.ScanLeaf$module == null)
/* 111 */           this.ScanLeaf$module = new ParIterableLike.ScanLeaf$(this); 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/collection/parallel/ParMapLike}.Lscala/collection/parallel/ParMapLike$DefaultValuesIterable;}} */
/* 111 */         return this.ScanLeaf$module;
/*     */       } 
/*     */     }
/*     */     
/*     */     public ParIterableLike.ScanLeaf$ ScanLeaf() {
/* 111 */       return (this.ScanLeaf$module == null) ? ScanLeaf$lzycompute() : this.ScanLeaf$module;
/*     */     }
/*     */     
/*     */     public void initTaskSupport() {
/* 111 */       ParIterableLike$class.initTaskSupport(this);
/*     */     }
/*     */     
/*     */     public TaskSupport tasksupport() {
/* 111 */       return ParIterableLike$class.tasksupport(this);
/*     */     }
/*     */     
/*     */     public void tasksupport_$eq(TaskSupport ts) {
/* 111 */       ParIterableLike$class.tasksupport_$eq(this, ts);
/*     */     }
/*     */     
/*     */     public ParIterable<V> repr() {
/* 111 */       return ParIterableLike$class.repr(this);
/*     */     }
/*     */     
/*     */     public final boolean isTraversableAgain() {
/* 111 */       return ParIterableLike$class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 111 */       return ParIterableLike$class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 111 */       return ParIterableLike$class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 111 */       return ParIterableLike$class.nonEmpty(this);
/*     */     }
/*     */     
/*     */     public V head() {
/* 111 */       return (V)ParIterableLike$class.head(this);
/*     */     }
/*     */     
/*     */     public Option<V> headOption() {
/* 111 */       return ParIterableLike$class.headOption(this);
/*     */     }
/*     */     
/*     */     public ParIterable<V> tail() {
/* 111 */       return ParIterableLike$class.tail(this);
/*     */     }
/*     */     
/*     */     public V last() {
/* 111 */       return (V)ParIterableLike$class.last(this);
/*     */     }
/*     */     
/*     */     public Option<V> lastOption() {
/* 111 */       return ParIterableLike$class.lastOption(this);
/*     */     }
/*     */     
/*     */     public ParIterable<V> init() {
/* 111 */       return ParIterableLike$class.init(this);
/*     */     }
/*     */     
/*     */     public Splitter<V> iterator() {
/* 111 */       return ParIterableLike$class.iterator(this);
/*     */     }
/*     */     
/*     */     public ParIterable<V> par() {
/* 111 */       return ParIterableLike$class.par(this);
/*     */     }
/*     */     
/*     */     public boolean isStrictSplitterCollection() {
/* 111 */       return ParIterableLike$class.isStrictSplitterCollection(this);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> reuse(Option oldc, Combiner newc) {
/* 111 */       return ParIterableLike$class.reuse(this, oldc, newc);
/*     */     }
/*     */     
/*     */     public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask tsk) {
/* 111 */       return ParIterableLike$class.task2ops(this, tsk);
/*     */     }
/*     */     
/*     */     public <R> Object wrap(Function0 body) {
/* 111 */       return ParIterableLike$class.wrap(this, body);
/*     */     }
/*     */     
/*     */     public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(DelegatedSignalling it) {
/* 111 */       return ParIterableLike$class.delegatedSignalling2ops(this, it);
/*     */     }
/*     */     
/*     */     public <Elem, To> Object builder2ops(Builder cb) {
/* 111 */       return ParIterableLike$class.builder2ops(this, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Object bf2seq(CanBuildFrom bf) {
/* 111 */       return ParIterableLike$class.bf2seq(this, bf);
/*     */     }
/*     */     
/*     */     public <S, That extends Parallel> ParIterable<V> sequentially(Function1 b) {
/* 111 */       return ParIterableLike$class.sequentially(this, b);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 111 */       return ParIterableLike$class.mkString(this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 111 */       return ParIterableLike$class.mkString(this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 111 */       return ParIterableLike$class.mkString(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 111 */       return ParIterableLike$class.toString(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object other) {
/* 111 */       return ParIterableLike$class.canEqual(this, other);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/* 111 */       return (U)ParIterableLike$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <U> Option<U> reduceOption(Function2 op) {
/* 111 */       return ParIterableLike$class.reduceOption(this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/* 111 */       return (U)ParIterableLike$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <S> S aggregate(Object z, Function2 seqop, Function2 combop) {
/* 111 */       return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <S> S foldLeft(Object z, Function2 op) {
/* 111 */       return (S)ParIterableLike$class.foldLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <S> S foldRight(Object z, Function2 op) {
/* 111 */       return (S)ParIterableLike$class.foldRight(this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(Function2 op) {
/* 111 */       return (U)ParIterableLike$class.reduceLeft(this, op);
/*     */     }
/*     */     
/*     */     public <U> U reduceRight(Function2 op) {
/* 111 */       return (U)ParIterableLike$class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public <U> Option<U> reduceLeftOption(Function2 op) {
/* 111 */       return ParIterableLike$class.reduceLeftOption(this, op);
/*     */     }
/*     */     
/*     */     public <U> Option<U> reduceRightOption(Function2 op) {
/* 111 */       return ParIterableLike$class.reduceRightOption(this, op);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 111 */       return ParIterableLike$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/* 111 */       return (U)ParIterableLike$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/* 111 */       return (U)ParIterableLike$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <U> V min(Ordering ord) {
/* 111 */       return (V)ParIterableLike$class.min(this, ord);
/*     */     }
/*     */     
/*     */     public <U> V max(Ordering ord) {
/* 111 */       return (V)ParIterableLike$class.max(this, ord);
/*     */     }
/*     */     
/*     */     public <S> V maxBy(Function1 f, Ordering cmp) {
/* 111 */       return (V)ParIterableLike$class.maxBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <S> V minBy(Function1 f, Ordering cmp) {
/* 111 */       return (V)ParIterableLike$class.minBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <S, That> That map(Function1 f, CanBuildFrom bf) {
/* 111 */       return (That)ParIterableLike$class.map(this, f, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 111 */       return (That)ParIterableLike$class.collect(this, pf, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 111 */       return (That)ParIterableLike$class.flatMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 pred) {
/* 111 */       return ParIterableLike$class.forall(this, pred);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 pred) {
/* 111 */       return ParIterableLike$class.exists(this, pred);
/*     */     }
/*     */     
/*     */     public Option<V> find(Function1 pred) {
/* 111 */       return ParIterableLike$class.find(this, pred);
/*     */     }
/*     */     
/*     */     public Object combinerFactory() {
/* 111 */       return ParIterableLike$class.combinerFactory(this);
/*     */     }
/*     */     
/*     */     public <S, That> Object combinerFactory(Function0 cbf) {
/* 111 */       return ParIterableLike$class.combinerFactory(this, cbf);
/*     */     }
/*     */     
/*     */     public ParIterable<V> filter(Function1 pred) {
/* 111 */       return ParIterableLike$class.filter(this, pred);
/*     */     }
/*     */     
/*     */     public ParIterable<V> filterNot(Function1 pred) {
/* 111 */       return ParIterableLike$class.filterNot(this, pred);
/*     */     }
/*     */     
/*     */     public <U, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/* 111 */       return (That)ParIterableLike$class.$plus$plus(this, that, bf);
/*     */     }
/*     */     
/*     */     public Tuple2<ParIterable<V>, ParIterable<V>> partition(Function1 pred) {
/* 111 */       return ParIterableLike$class.partition(this, pred);
/*     */     }
/*     */     
/*     */     public <K> ParMap<K, ParIterable<V>> groupBy(Function1 f) {
/* 111 */       return ParIterableLike$class.groupBy(this, f);
/*     */     }
/*     */     
/*     */     public ParIterable<V> take(int n) {
/* 111 */       return ParIterableLike$class.take(this, n);
/*     */     }
/*     */     
/*     */     public ParIterable<V> drop(int n) {
/* 111 */       return ParIterableLike$class.drop(this, n);
/*     */     }
/*     */     
/*     */     public ParIterable<V> slice(int unc_from, int unc_until) {
/* 111 */       return ParIterableLike$class.slice(this, unc_from, unc_until);
/*     */     }
/*     */     
/*     */     public Tuple2<ParIterable<V>, ParIterable<V>> splitAt(int n) {
/* 111 */       return ParIterableLike$class.splitAt(this, n);
/*     */     }
/*     */     
/*     */     public <U, That> That scan(Object z, Function2 op, CanBuildFrom bf) {
/* 111 */       return (That)ParIterableLike$class.scan(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 111 */       return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 111 */       return (That)ParIterableLike$class.scanRight(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public ParIterable<V> takeWhile(Function1 pred) {
/* 111 */       return ParIterableLike$class.takeWhile(this, pred);
/*     */     }
/*     */     
/*     */     public Tuple2<ParIterable<V>, ParIterable<V>> span(Function1 pred) {
/* 111 */       return ParIterableLike$class.span(this, pred);
/*     */     }
/*     */     
/*     */     public ParIterable<V> dropWhile(Function1 pred) {
/* 111 */       return ParIterableLike$class.dropWhile(this, pred);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object xs) {
/* 111 */       ParIterableLike$class.copyToArray(this, xs);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object xs, int start) {
/* 111 */       ParIterableLike$class.copyToArray(this, xs, start);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object xs, int start, int len) {
/* 111 */       ParIterableLike$class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public <U> boolean sameElements(GenIterable that) {
/* 111 */       return ParIterableLike$class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public <U, S, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 111 */       return (That)ParIterableLike$class.zip(this, that, bf);
/*     */     }
/*     */     
/*     */     public <U, That> That zipWithIndex(CanBuildFrom bf) {
/* 111 */       return (That)ParIterableLike$class.zipWithIndex(this, bf);
/*     */     }
/*     */     
/*     */     public <S, U, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 111 */       return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
/*     */     }
/*     */     
/*     */     public <U, That> That toParCollection(Function0 cbf) {
/* 111 */       return (That)ParIterableLike$class.toParCollection(this, cbf);
/*     */     }
/*     */     
/*     */     public <K, V, That> That toParMap(Function0 cbf, Predef$.less.colon.less ev) {
/* 111 */       return (That)ParIterableLike$class.toParMap(this, cbf, ev);
/*     */     }
/*     */     
/*     */     public Object view() {
/* 111 */       return ParIterableLike$class.view(this);
/*     */     }
/*     */     
/*     */     public <U> Object toArray(ClassTag evidence$1) {
/* 111 */       return ParIterableLike$class.toArray(this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<V> toList() {
/* 111 */       return ParIterableLike$class.toList(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<V> toIndexedSeq() {
/* 111 */       return ParIterableLike$class.toIndexedSeq(this);
/*     */     }
/*     */     
/*     */     public Stream<V> toStream() {
/* 111 */       return ParIterableLike$class.toStream(this);
/*     */     }
/*     */     
/*     */     public Iterator<V> toIterator() {
/* 111 */       return ParIterableLike$class.toIterator(this);
/*     */     }
/*     */     
/*     */     public <U> Buffer<U> toBuffer() {
/* 111 */       return ParIterableLike$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public GenTraversable<V> toTraversable() {
/* 111 */       return ParIterableLike$class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public ParIterable<V> toIterable() {
/* 111 */       return ParIterableLike$class.toIterable(this);
/*     */     }
/*     */     
/*     */     public ParSeq<V> toSeq() {
/* 111 */       return ParIterableLike$class.toSeq(this);
/*     */     }
/*     */     
/*     */     public <U> ParSet<U> toSet() {
/* 111 */       return ParIterableLike$class.toSet(this);
/*     */     }
/*     */     
/*     */     public <K, V> ParMap<K, V> toMap(Predef$.less.colon.less ev) {
/* 111 */       return ParIterableLike$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public Vector<V> toVector() {
/* 111 */       return ParIterableLike$class.toVector(this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 111 */       return (Col)ParIterableLike$class.to(this, cbf);
/*     */     }
/*     */     
/*     */     public int scanBlockSize() {
/* 111 */       return ParIterableLike$class.scanBlockSize(this);
/*     */     }
/*     */     
/*     */     public <S> S $div$colon(Object z, Function2 op) {
/* 111 */       return (S)ParIterableLike$class.$div$colon(this, z, op);
/*     */     }
/*     */     
/*     */     public <S> S $colon$bslash(Object z, Function2 op) {
/* 111 */       return (S)ParIterableLike$class.$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/* 111 */       return ParIterableLike$class.debugInformation(this);
/*     */     }
/*     */     
/*     */     public Seq<String> brokenInvariants() {
/* 111 */       return ParIterableLike$class.brokenInvariants(this);
/*     */     }
/*     */     
/*     */     public ArrayBuffer<String> debugBuffer() {
/* 111 */       return ParIterableLike$class.debugBuffer(this);
/*     */     }
/*     */     
/*     */     public void debugclear() {
/* 111 */       ParIterableLike$class.debugclear(this);
/*     */     }
/*     */     
/*     */     public ArrayBuffer<String> debuglog(String s) {
/* 111 */       return ParIterableLike$class.debuglog(this, s);
/*     */     }
/*     */     
/*     */     public void printDebugBuffer() {
/* 111 */       ParIterableLike$class.printDebugBuffer(this);
/*     */     }
/*     */     
/*     */     public Combiner<V, ParIterable<V>> parCombiner() {
/* 111 */       return CustomParallelizable.class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public Builder<V, ParIterable<V>> newBuilder() {
/* 111 */       return GenericParTemplate.class.newBuilder(this);
/*     */     }
/*     */     
/*     */     public Combiner<V, ParIterable<V>> newCombiner() {
/* 111 */       return GenericParTemplate.class.newCombiner(this);
/*     */     }
/*     */     
/*     */     public <B> Combiner<B, ParIterable<B>> genericBuilder() {
/* 111 */       return GenericParTemplate.class.genericBuilder(this);
/*     */     }
/*     */     
/*     */     public <B> Combiner<B, ParIterable<B>> genericCombiner() {
/* 111 */       return GenericParTemplate.class.genericCombiner(this);
/*     */     }
/*     */     
/*     */     public <A1, A2> Tuple2<ParIterable<A1>, ParIterable<A2>> unzip(Function1 asPair) {
/* 111 */       return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*     */     }
/*     */     
/*     */     public <A1, A2, A3> Tuple3<ParIterable<A1>, ParIterable<A2>, ParIterable<A3>> unzip3(Function1 asTriple) {
/* 111 */       return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*     */     }
/*     */     
/*     */     public <B> ParIterable<B> flatten(Function1 asTraversable) {
/* 111 */       return (ParIterable<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*     */     }
/*     */     
/*     */     public <B> ParIterable<ParIterable<B>> transpose(Function1 asTraversable) {
/* 111 */       return (ParIterable<ParIterable<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 111 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public DefaultValuesIterable(ParMapLike $outer) {
/* 111 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 111 */       Parallelizable.class.$init$((Parallelizable)this);
/* 111 */       GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/* 111 */       GenTraversable.class.$init$((GenTraversable)this);
/* 111 */       GenIterable.class.$init$(this);
/* 111 */       GenericParTemplate.class.$init$(this);
/* 111 */       CustomParallelizable.class.$init$(this);
/* 111 */       ParIterableLike$class.$init$(this);
/* 111 */       ParIterable$class.$init$(this);
/*     */     }
/*     */     
/*     */     public IterableSplitter<V> splitter() {
/* 112 */       return ParMapLike$class.scala$collection$parallel$ParMapLike$$valuesIterator(scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer(), scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer().splitter());
/*     */     }
/*     */     
/*     */     public int size() {
/* 113 */       return scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer().size();
/*     */     }
/*     */     
/*     */     public <S> void foreach(Function1 f) {
/* 114 */       scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer().filter((Function1)new ParMapLike$DefaultValuesIterable$$anonfun$foreach$3(this)).foreach((Function1)new ParMapLike$DefaultValuesIterable$$anonfun$foreach$4(this, (DefaultValuesIterable)f));
/*     */     }
/*     */     
/*     */     public class ParMapLike$DefaultValuesIterable$$anonfun$foreach$3 extends AbstractFunction1<Tuple2<K, V>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Tuple2 check$ifrefutable$2) {
/*     */         boolean bool;
/* 114 */         if (check$ifrefutable$2 != null) {
/* 114 */           bool = true;
/*     */         } else {
/* 114 */           bool = false;
/*     */         } 
/* 114 */         return bool;
/*     */       }
/*     */       
/*     */       public ParMapLike$DefaultValuesIterable$$anonfun$foreach$3(ParMapLike.DefaultValuesIterable $outer) {}
/*     */     }
/*     */     
/*     */     public class ParMapLike$DefaultValuesIterable$$anonfun$foreach$4 extends AbstractFunction1<Tuple2<K, V>, S> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 f$2;
/*     */       
/*     */       public final S apply(Tuple2 x$6) {
/* 114 */         if (x$6 != null)
/* 114 */           return (S)this.f$2.apply(x$6._2()); 
/* 114 */         throw new MatchError(x$6);
/*     */       }
/*     */       
/*     */       public ParMapLike$DefaultValuesIterable$$anonfun$foreach$4(ParMapLike.DefaultValuesIterable $outer, Function1 f$2) {}
/*     */     }
/*     */     
/*     */     public Iterable<V> seq() {
/* 115 */       return ((MapLike)scala$collection$parallel$ParMapLike$DefaultValuesIterable$$$outer().seq()).values();
/*     */     }
/*     */   }
/*     */   
/*     */   public class ParMapLike$$anon$1 implements ParMap<K, V> {
/*     */     private Repr filtered;
/*     */     
/*     */     public final Function1 p$1;
/*     */     
/*     */     private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     private volatile ParIterableLike.ScanNode$ ScanNode$module;
/*     */     
/*     */     private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;
/*     */     
/*     */     public GenericParMapCompanion<ParMap> mapCompanion() {
/* 124 */       return ParMap$class.mapCompanion(this);
/*     */     }
/*     */     
/*     */     public ParMap<K, V> empty() {
/* 124 */       return ParMap$class.empty(this);
/*     */     }
/*     */     
/*     */     public String stringPrefix() {
/* 124 */       return ParMap$class.stringPrefix(this);
/*     */     }
/*     */     
/*     */     public <U> ParMap<K, U> updated(Object key, Object value) {
/* 124 */       return ParMap$class.updated(this, key, value);
/*     */     }
/*     */     
/*     */     public V default(Object key) {
/* 124 */       return (V)ParMapLike$class.default(this, key);
/*     */     }
/*     */     
/*     */     public V apply(Object key) {
/* 124 */       return (V)ParMapLike$class.apply(this, key);
/*     */     }
/*     */     
/*     */     public <U> U getOrElse(Object key, Function0 default) {
/* 124 */       return (U)ParMapLike$class.getOrElse(this, key, default);
/*     */     }
/*     */     
/*     */     public boolean isDefinedAt(Object key) {
/* 124 */       return ParMapLike$class.isDefinedAt(this, key);
/*     */     }
/*     */     
/*     */     public IterableSplitter<K> keysIterator() {
/* 124 */       return ParMapLike$class.keysIterator(this);
/*     */     }
/*     */     
/*     */     public IterableSplitter<V> valuesIterator() {
/* 124 */       return ParMapLike$class.valuesIterator(this);
/*     */     }
/*     */     
/*     */     public ParSet<K> keySet() {
/* 124 */       return ParMapLike$class.keySet(this);
/*     */     }
/*     */     
/*     */     public ParIterable<K> keys() {
/* 124 */       return ParMapLike$class.keys(this);
/*     */     }
/*     */     
/*     */     public ParIterable<V> values() {
/* 124 */       return ParMapLike$class.values(this);
/*     */     }
/*     */     
/*     */     public ParMap<K, V> filterKeys(Function1 p) {
/* 124 */       return ParMapLike$class.filterKeys(this, p);
/*     */     }
/*     */     
/*     */     public <S> ParMap<K, S> mapValues(Function1 f) {
/* 124 */       return ParMapLike$class.mapValues(this, f);
/*     */     }
/*     */     
/*     */     public GenericCompanion<ParIterable> companion() {
/* 124 */       return ParIterable$class.companion(this);
/*     */     }
/*     */     
/*     */     public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
/* 124 */       return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
/* 124 */       this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
/*     */     }
/*     */     
/*     */     private ParIterableLike.ScanNode$ ScanNode$lzycompute() {
/* 124 */       synchronized (this) {
/* 124 */         if (this.ScanNode$module == null)
/* 124 */           this.ScanNode$module = new ParIterableLike.ScanNode$(this); 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/ParMapLike$$anon$1}} */
/* 124 */         return this.ScanNode$module;
/*     */       } 
/*     */     }
/*     */     
/*     */     public ParIterableLike.ScanNode$ ScanNode() {
/* 124 */       return (this.ScanNode$module == null) ? ScanNode$lzycompute() : this.ScanNode$module;
/*     */     }
/*     */     
/*     */     private ParIterableLike.ScanLeaf$ ScanLeaf$lzycompute() {
/* 124 */       synchronized (this) {
/* 124 */         if (this.ScanLeaf$module == null)
/* 124 */           this.ScanLeaf$module = new ParIterableLike.ScanLeaf$(this); 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/ParMapLike$$anon$1}} */
/* 124 */         return this.ScanLeaf$module;
/*     */       } 
/*     */     }
/*     */     
/*     */     public ParIterableLike.ScanLeaf$ ScanLeaf() {
/* 124 */       return (this.ScanLeaf$module == null) ? ScanLeaf$lzycompute() : this.ScanLeaf$module;
/*     */     }
/*     */     
/*     */     public void initTaskSupport() {
/* 124 */       ParIterableLike$class.initTaskSupport(this);
/*     */     }
/*     */     
/*     */     public TaskSupport tasksupport() {
/* 124 */       return ParIterableLike$class.tasksupport(this);
/*     */     }
/*     */     
/*     */     public void tasksupport_$eq(TaskSupport ts) {
/* 124 */       ParIterableLike$class.tasksupport_$eq(this, ts);
/*     */     }
/*     */     
/*     */     public ParMap<K, V> repr() {
/* 124 */       return (ParMap<K, V>)ParIterableLike$class.repr(this);
/*     */     }
/*     */     
/*     */     public final boolean isTraversableAgain() {
/* 124 */       return ParIterableLike$class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 124 */       return ParIterableLike$class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 124 */       return ParIterableLike$class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 124 */       return ParIterableLike$class.nonEmpty(this);
/*     */     }
/*     */     
/*     */     public Tuple2<K, V> head() {
/* 124 */       return (Tuple2<K, V>)ParIterableLike$class.head(this);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<K, V>> headOption() {
/* 124 */       return ParIterableLike$class.headOption(this);
/*     */     }
/*     */     
/*     */     public ParMap<K, V> tail() {
/* 124 */       return (ParMap<K, V>)ParIterableLike$class.tail(this);
/*     */     }
/*     */     
/*     */     public Tuple2<K, V> last() {
/* 124 */       return (Tuple2<K, V>)ParIterableLike$class.last(this);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<K, V>> lastOption() {
/* 124 */       return ParIterableLike$class.lastOption(this);
/*     */     }
/*     */     
/*     */     public ParMap<K, V> init() {
/* 124 */       return (ParMap<K, V>)ParIterableLike$class.init(this);
/*     */     }
/*     */     
/*     */     public Splitter<Tuple2<K, V>> iterator() {
/* 124 */       return ParIterableLike$class.iterator(this);
/*     */     }
/*     */     
/*     */     public ParMap<K, V> par() {
/* 124 */       return (ParMap<K, V>)ParIterableLike$class.par(this);
/*     */     }
/*     */     
/*     */     public boolean isStrictSplitterCollection() {
/* 124 */       return ParIterableLike$class.isStrictSplitterCollection(this);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> reuse(Option oldc, Combiner newc) {
/* 124 */       return ParIterableLike$class.reuse(this, oldc, newc);
/*     */     }
/*     */     
/*     */     public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask tsk) {
/* 124 */       return ParIterableLike$class.task2ops(this, tsk);
/*     */     }
/*     */     
/*     */     public <R> Object wrap(Function0 body) {
/* 124 */       return ParIterableLike$class.wrap(this, body);
/*     */     }
/*     */     
/*     */     public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(DelegatedSignalling it) {
/* 124 */       return ParIterableLike$class.delegatedSignalling2ops(this, it);
/*     */     }
/*     */     
/*     */     public <Elem, To> Object builder2ops(Builder cb) {
/* 124 */       return ParIterableLike$class.builder2ops(this, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Object bf2seq(CanBuildFrom bf) {
/* 124 */       return ParIterableLike$class.bf2seq(this, bf);
/*     */     }
/*     */     
/*     */     public <S, That extends Parallel> ParMap<K, V> sequentially(Function1 b) {
/* 124 */       return (ParMap<K, V>)ParIterableLike$class.sequentially(this, b);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 124 */       return ParIterableLike$class.mkString(this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 124 */       return ParIterableLike$class.mkString(this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 124 */       return ParIterableLike$class.mkString(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 124 */       return ParIterableLike$class.toString(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object other) {
/* 124 */       return ParIterableLike$class.canEqual(this, other);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/* 124 */       return (U)ParIterableLike$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <U> Option<U> reduceOption(Function2 op) {
/* 124 */       return ParIterableLike$class.reduceOption(this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/* 124 */       return (U)ParIterableLike$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <S> S aggregate(Object z, Function2 seqop, Function2 combop) {
/* 124 */       return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <S> S foldLeft(Object z, Function2 op) {
/* 124 */       return (S)ParIterableLike$class.foldLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <S> S foldRight(Object z, Function2 op) {
/* 124 */       return (S)ParIterableLike$class.foldRight(this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(Function2 op) {
/* 124 */       return (U)ParIterableLike$class.reduceLeft(this, op);
/*     */     }
/*     */     
/*     */     public <U> U reduceRight(Function2 op) {
/* 124 */       return (U)ParIterableLike$class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public <U> Option<U> reduceLeftOption(Function2 op) {
/* 124 */       return ParIterableLike$class.reduceLeftOption(this, op);
/*     */     }
/*     */     
/*     */     public <U> Option<U> reduceRightOption(Function2 op) {
/* 124 */       return ParIterableLike$class.reduceRightOption(this, op);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 124 */       return ParIterableLike$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/* 124 */       return (U)ParIterableLike$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/* 124 */       return (U)ParIterableLike$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <U> Tuple2<K, V> min(Ordering ord) {
/* 124 */       return (Tuple2<K, V>)ParIterableLike$class.min(this, ord);
/*     */     }
/*     */     
/*     */     public <U> Tuple2<K, V> max(Ordering ord) {
/* 124 */       return (Tuple2<K, V>)ParIterableLike$class.max(this, ord);
/*     */     }
/*     */     
/*     */     public <S> Tuple2<K, V> maxBy(Function1 f, Ordering cmp) {
/* 124 */       return (Tuple2<K, V>)ParIterableLike$class.maxBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <S> Tuple2<K, V> minBy(Function1 f, Ordering cmp) {
/* 124 */       return (Tuple2<K, V>)ParIterableLike$class.minBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <S, That> That map(Function1 f, CanBuildFrom bf) {
/* 124 */       return (That)ParIterableLike$class.map(this, f, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 124 */       return (That)ParIterableLike$class.collect(this, pf, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 124 */       return (That)ParIterableLike$class.flatMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 pred) {
/* 124 */       return ParIterableLike$class.forall(this, pred);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 pred) {
/* 124 */       return ParIterableLike$class.exists(this, pred);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<K, V>> find(Function1 pred) {
/* 124 */       return ParIterableLike$class.find(this, pred);
/*     */     }
/*     */     
/*     */     public Object combinerFactory() {
/* 124 */       return ParIterableLike$class.combinerFactory(this);
/*     */     }
/*     */     
/*     */     public <S, That> Object combinerFactory(Function0 cbf) {
/* 124 */       return ParIterableLike$class.combinerFactory(this, cbf);
/*     */     }
/*     */     
/*     */     public ParMap<K, V> filter(Function1 pred) {
/* 124 */       return (ParMap<K, V>)ParIterableLike$class.filter(this, pred);
/*     */     }
/*     */     
/*     */     public ParMap<K, V> filterNot(Function1 pred) {
/* 124 */       return (ParMap<K, V>)ParIterableLike$class.filterNot(this, pred);
/*     */     }
/*     */     
/*     */     public <U, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/* 124 */       return (That)ParIterableLike$class.$plus$plus(this, that, bf);
/*     */     }
/*     */     
/*     */     public Tuple2<ParMap<K, V>, ParMap<K, V>> partition(Function1 pred) {
/* 124 */       return ParIterableLike$class.partition(this, pred);
/*     */     }
/*     */     
/*     */     public <K> ParMap<K, ParMap<K, V>> groupBy(Function1 f) {
/* 124 */       return ParIterableLike$class.groupBy(this, f);
/*     */     }
/*     */     
/*     */     public ParMap<K, V> take(int n) {
/* 124 */       return (ParMap<K, V>)ParIterableLike$class.take(this, n);
/*     */     }
/*     */     
/*     */     public ParMap<K, V> drop(int n) {
/* 124 */       return (ParMap<K, V>)ParIterableLike$class.drop(this, n);
/*     */     }
/*     */     
/*     */     public ParMap<K, V> slice(int unc_from, int unc_until) {
/* 124 */       return (ParMap<K, V>)ParIterableLike$class.slice(this, unc_from, unc_until);
/*     */     }
/*     */     
/*     */     public Tuple2<ParMap<K, V>, ParMap<K, V>> splitAt(int n) {
/* 124 */       return ParIterableLike$class.splitAt(this, n);
/*     */     }
/*     */     
/*     */     public <U, That> That scan(Object z, Function2 op, CanBuildFrom bf) {
/* 124 */       return (That)ParIterableLike$class.scan(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 124 */       return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 124 */       return (That)ParIterableLike$class.scanRight(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public ParMap<K, V> takeWhile(Function1 pred) {
/* 124 */       return (ParMap<K, V>)ParIterableLike$class.takeWhile(this, pred);
/*     */     }
/*     */     
/*     */     public Tuple2<ParMap<K, V>, ParMap<K, V>> span(Function1 pred) {
/* 124 */       return ParIterableLike$class.span(this, pred);
/*     */     }
/*     */     
/*     */     public ParMap<K, V> dropWhile(Function1 pred) {
/* 124 */       return (ParMap<K, V>)ParIterableLike$class.dropWhile(this, pred);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object xs) {
/* 124 */       ParIterableLike$class.copyToArray(this, xs);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object xs, int start) {
/* 124 */       ParIterableLike$class.copyToArray(this, xs, start);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object xs, int start, int len) {
/* 124 */       ParIterableLike$class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public <U> boolean sameElements(GenIterable that) {
/* 124 */       return ParIterableLike$class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public <U, S, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 124 */       return (That)ParIterableLike$class.zip(this, that, bf);
/*     */     }
/*     */     
/*     */     public <U, That> That zipWithIndex(CanBuildFrom bf) {
/* 124 */       return (That)ParIterableLike$class.zipWithIndex(this, bf);
/*     */     }
/*     */     
/*     */     public <S, U, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 124 */       return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
/*     */     }
/*     */     
/*     */     public <U, That> That toParCollection(Function0 cbf) {
/* 124 */       return (That)ParIterableLike$class.toParCollection(this, cbf);
/*     */     }
/*     */     
/*     */     public <K, V, That> That toParMap(Function0 cbf, Predef$.less.colon.less ev) {
/* 124 */       return (That)ParIterableLike$class.toParMap(this, cbf, ev);
/*     */     }
/*     */     
/*     */     public Object view() {
/* 124 */       return ParIterableLike$class.view(this);
/*     */     }
/*     */     
/*     */     public <U> Object toArray(ClassTag evidence$1) {
/* 124 */       return ParIterableLike$class.toArray(this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<Tuple2<K, V>> toList() {
/* 124 */       return ParIterableLike$class.toList(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<Tuple2<K, V>> toIndexedSeq() {
/* 124 */       return ParIterableLike$class.toIndexedSeq(this);
/*     */     }
/*     */     
/*     */     public Stream<Tuple2<K, V>> toStream() {
/* 124 */       return ParIterableLike$class.toStream(this);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<K, V>> toIterator() {
/* 124 */       return ParIterableLike$class.toIterator(this);
/*     */     }
/*     */     
/*     */     public <U> Buffer<U> toBuffer() {
/* 124 */       return ParIterableLike$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public GenTraversable<Tuple2<K, V>> toTraversable() {
/* 124 */       return ParIterableLike$class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public ParIterable<Tuple2<K, V>> toIterable() {
/* 124 */       return ParIterableLike$class.toIterable(this);
/*     */     }
/*     */     
/*     */     public ParSeq<Tuple2<K, V>> toSeq() {
/* 124 */       return ParIterableLike$class.toSeq(this);
/*     */     }
/*     */     
/*     */     public <U> ParSet<U> toSet() {
/* 124 */       return ParIterableLike$class.toSet(this);
/*     */     }
/*     */     
/*     */     public <K, V> ParMap<K, V> toMap(Predef$.less.colon.less ev) {
/* 124 */       return ParIterableLike$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public Vector<Tuple2<K, V>> toVector() {
/* 124 */       return ParIterableLike$class.toVector(this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 124 */       return (Col)ParIterableLike$class.to(this, cbf);
/*     */     }
/*     */     
/*     */     public int scanBlockSize() {
/* 124 */       return ParIterableLike$class.scanBlockSize(this);
/*     */     }
/*     */     
/*     */     public <S> S $div$colon(Object z, Function2 op) {
/* 124 */       return (S)ParIterableLike$class.$div$colon(this, z, op);
/*     */     }
/*     */     
/*     */     public <S> S $colon$bslash(Object z, Function2 op) {
/* 124 */       return (S)ParIterableLike$class.$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/* 124 */       return ParIterableLike$class.debugInformation(this);
/*     */     }
/*     */     
/*     */     public Seq<String> brokenInvariants() {
/* 124 */       return ParIterableLike$class.brokenInvariants(this);
/*     */     }
/*     */     
/*     */     public ArrayBuffer<String> debugBuffer() {
/* 124 */       return ParIterableLike$class.debugBuffer(this);
/*     */     }
/*     */     
/*     */     public void debugclear() {
/* 124 */       ParIterableLike$class.debugclear(this);
/*     */     }
/*     */     
/*     */     public ArrayBuffer<String> debuglog(String s) {
/* 124 */       return ParIterableLike$class.debuglog(this, s);
/*     */     }
/*     */     
/*     */     public void printDebugBuffer() {
/* 124 */       ParIterableLike$class.printDebugBuffer(this);
/*     */     }
/*     */     
/*     */     public Combiner<Tuple2<K, V>, ParMap<K, V>> parCombiner() {
/* 124 */       return CustomParallelizable.class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public Combiner<Tuple2<K, V>, ParMap<K, V>> newCombiner() {
/* 124 */       return GenericParMapTemplate.class.newCombiner(this);
/*     */     }
/*     */     
/*     */     public <P, Q> Combiner<Tuple2<P, Q>, ParMap<P, Q>> genericMapCombiner() {
/* 124 */       return GenericParMapTemplate.class.genericMapCombiner(this);
/*     */     }
/*     */     
/*     */     public Builder<Tuple2<K, V>, ParIterable<Tuple2<K, V>>> newBuilder() {
/* 124 */       return GenericParTemplate.class.newBuilder(this);
/*     */     }
/*     */     
/*     */     public <B> Combiner<B, ParIterable<B>> genericBuilder() {
/* 124 */       return GenericParTemplate.class.genericBuilder(this);
/*     */     }
/*     */     
/*     */     public <B> Combiner<B, ParIterable<B>> genericCombiner() {
/* 124 */       return GenericParTemplate.class.genericCombiner(this);
/*     */     }
/*     */     
/*     */     public <A1, A2> Tuple2<ParIterable<A1>, ParIterable<A2>> unzip(Function1 asPair) {
/* 124 */       return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*     */     }
/*     */     
/*     */     public <A1, A2, A3> Tuple3<ParIterable<A1>, ParIterable<A2>, ParIterable<A3>> unzip3(Function1 asTriple) {
/* 124 */       return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*     */     }
/*     */     
/*     */     public <B> ParIterable<B> flatten(Function1 asTraversable) {
/* 124 */       return (ParIterable<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*     */     }
/*     */     
/*     */     public <B> ParIterable<ParIterable<B>> transpose(Function1 asTraversable) {
/* 124 */       return (ParIterable<ParIterable<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 124 */       return GenMapLike.class.hashCode(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 124 */       return GenMapLike.class.equals(this, that);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 124 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public ParMapLike$$anon$1(ParMapLike $outer, Function1 p$1) {
/* 124 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 124 */       Parallelizable.class.$init$((Parallelizable)this);
/* 124 */       GenMapLike.class.$init$(this);
/* 124 */       GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/* 124 */       GenTraversable.class.$init$((GenTraversable)this);
/* 124 */       GenIterable.class.$init$(this);
/* 124 */       GenericParTemplate.class.$init$(this);
/* 124 */       GenericParMapTemplate.class.$init$(this);
/* 124 */       CustomParallelizable.class.$init$(this);
/* 124 */       ParIterableLike$class.$init$(this);
/* 124 */       ParIterable$class.$init$(this);
/* 124 */       ParMapLike$class.$init$(this);
/* 124 */       ParMap$class.$init$(this);
/*     */     }
/*     */     
/*     */     private ParMap filtered$lzycompute() {
/* 125 */       synchronized (this) {
/* 125 */         if (!this.bitmap$0) {
/* 125 */           this.filtered = (Repr)this.$outer.filter((Function1)new ParMapLike$$anon$1$$anonfun$filtered$1(this));
/* 125 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/ParMapLike$$anon$1}} */
/* 125 */         return (ParMap)this.filtered;
/*     */       } 
/*     */     }
/*     */     
/*     */     private Repr filtered() {
/* 125 */       return this.bitmap$0 ? this.filtered : (Repr)filtered$lzycompute();
/*     */     }
/*     */     
/*     */     public class ParMapLike$$anon$1$$anonfun$filtered$1 extends AbstractFunction1<Tuple2<K, V>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Tuple2 kv) {
/* 125 */         return BoxesRunTime.unboxToBoolean(this.$outer.p$1.apply(kv._1()));
/*     */       }
/*     */       
/*     */       public ParMapLike$$anon$1$$anonfun$filtered$1(ParMapLike$$anon$1 $outer) {}
/*     */     }
/*     */     
/*     */     public <S> void foreach(Function1 f) {
/* 126 */       this.$outer.foreach((Function1)new ParMapLike$$anon$1$$anonfun$foreach$5(this, ($anon$1)f));
/*     */     }
/*     */     
/*     */     public class ParMapLike$$anon$1$$anonfun$foreach$5 extends AbstractFunction1<Tuple2<K, V>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 f$3;
/*     */       
/*     */       public final Object apply(Tuple2 kv) {
/* 126 */         return BoxesRunTime.unboxToBoolean(this.$outer.p$1.apply(kv._1())) ? this.f$3.apply(kv) : BoxedUnit.UNIT;
/*     */       }
/*     */       
/*     */       public ParMapLike$$anon$1$$anonfun$foreach$5(ParMapLike$$anon$1 $outer, Function1 f$3) {}
/*     */     }
/*     */     
/*     */     public IterableSplitter<Tuple2<K, V>> splitter() {
/* 127 */       return filtered().splitter();
/*     */     }
/*     */     
/*     */     public boolean contains(Object key) {
/* 128 */       return (this.$outer.contains(key) && BoxesRunTime.unboxToBoolean(this.p$1.apply(key)));
/*     */     }
/*     */     
/*     */     public Option<V> get(Object key) {
/* 129 */       return BoxesRunTime.unboxToBoolean(this.p$1.apply(key)) ? this.$outer.get(key) : (Option<V>)None$.MODULE$;
/*     */     }
/*     */     
/*     */     public Map<K, V> seq() {
/* 130 */       return ((MapLike)this.$outer.seq()).filterKeys(this.p$1);
/*     */     }
/*     */     
/*     */     public int size() {
/* 131 */       return filtered().size();
/*     */     }
/*     */     
/*     */     public <U> ParMap<K, U> $plus(Tuple2 kv) {
/* 132 */       return ((ParMap)((ParIterableLike)ParMap$.MODULE$.apply((Seq)Nil$.MODULE$)).$plus$plus((GenTraversableOnce)this, (CanBuildFrom)ParMap$.MODULE$.canBuildFrom())).$plus(kv);
/*     */     }
/*     */     
/*     */     public ParMap<K, V> $minus(Object key) {
/* 133 */       return (ParMap<K, V>)((GenMapLike)((ParIterableLike)ParMap$.MODULE$.apply((Seq)Nil$.MODULE$)).$plus$plus((GenTraversableOnce)this, (CanBuildFrom)ParMap$.MODULE$.canBuildFrom())).$minus(key);
/*     */     }
/*     */   }
/*     */   
/*     */   public class ParMapLike$$anon$2 implements ParMap<K, S> {
/*     */     public final Function1 f$4;
/*     */     
/*     */     private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */     
/*     */     private volatile ParIterableLike.ScanNode$ ScanNode$module;
/*     */     
/*     */     private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;
/*     */     
/*     */     public GenericParMapCompanion<ParMap> mapCompanion() {
/* 136 */       return ParMap$class.mapCompanion(this);
/*     */     }
/*     */     
/*     */     public ParMap<K, S> empty() {
/* 136 */       return ParMap$class.empty(this);
/*     */     }
/*     */     
/*     */     public String stringPrefix() {
/* 136 */       return ParMap$class.stringPrefix(this);
/*     */     }
/*     */     
/*     */     public <U> ParMap<K, U> updated(Object key, Object value) {
/* 136 */       return ParMap$class.updated(this, key, value);
/*     */     }
/*     */     
/*     */     public S default(Object key) {
/* 136 */       return (S)ParMapLike$class.default(this, key);
/*     */     }
/*     */     
/*     */     public S apply(Object key) {
/* 136 */       return (S)ParMapLike$class.apply(this, key);
/*     */     }
/*     */     
/*     */     public <U> U getOrElse(Object key, Function0 default) {
/* 136 */       return (U)ParMapLike$class.getOrElse(this, key, default);
/*     */     }
/*     */     
/*     */     public boolean isDefinedAt(Object key) {
/* 136 */       return ParMapLike$class.isDefinedAt(this, key);
/*     */     }
/*     */     
/*     */     public IterableSplitter<K> keysIterator() {
/* 136 */       return ParMapLike$class.keysIterator(this);
/*     */     }
/*     */     
/*     */     public IterableSplitter<S> valuesIterator() {
/* 136 */       return ParMapLike$class.valuesIterator(this);
/*     */     }
/*     */     
/*     */     public ParSet<K> keySet() {
/* 136 */       return ParMapLike$class.keySet(this);
/*     */     }
/*     */     
/*     */     public ParIterable<K> keys() {
/* 136 */       return ParMapLike$class.keys(this);
/*     */     }
/*     */     
/*     */     public ParIterable<S> values() {
/* 136 */       return ParMapLike$class.values(this);
/*     */     }
/*     */     
/*     */     public ParMap<K, S> filterKeys(Function1 p) {
/* 136 */       return ParMapLike$class.filterKeys(this, p);
/*     */     }
/*     */     
/*     */     public <S> ParMap<K, S> mapValues(Function1 f) {
/* 136 */       return ParMapLike$class.mapValues(this, f);
/*     */     }
/*     */     
/*     */     public GenericCompanion<ParIterable> companion() {
/* 136 */       return ParIterable$class.companion(this);
/*     */     }
/*     */     
/*     */     public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
/* 136 */       return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
/* 136 */       this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
/*     */     }
/*     */     
/*     */     private ParIterableLike.ScanNode$ ScanNode$lzycompute() {
/* 136 */       synchronized (this) {
/* 136 */         if (this.ScanNode$module == null)
/* 136 */           this.ScanNode$module = new ParIterableLike.ScanNode$(this); 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/ParMapLike$$anon$2}} */
/* 136 */         return this.ScanNode$module;
/*     */       } 
/*     */     }
/*     */     
/*     */     public ParIterableLike.ScanNode$ ScanNode() {
/* 136 */       return (this.ScanNode$module == null) ? ScanNode$lzycompute() : this.ScanNode$module;
/*     */     }
/*     */     
/*     */     private ParIterableLike.ScanLeaf$ ScanLeaf$lzycompute() {
/* 136 */       synchronized (this) {
/* 136 */         if (this.ScanLeaf$module == null)
/* 136 */           this.ScanLeaf$module = new ParIterableLike.ScanLeaf$(this); 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/ParMapLike$$anon$2}} */
/* 136 */         return this.ScanLeaf$module;
/*     */       } 
/*     */     }
/*     */     
/*     */     public ParIterableLike.ScanLeaf$ ScanLeaf() {
/* 136 */       return (this.ScanLeaf$module == null) ? ScanLeaf$lzycompute() : this.ScanLeaf$module;
/*     */     }
/*     */     
/*     */     public void initTaskSupport() {
/* 136 */       ParIterableLike$class.initTaskSupport(this);
/*     */     }
/*     */     
/*     */     public TaskSupport tasksupport() {
/* 136 */       return ParIterableLike$class.tasksupport(this);
/*     */     }
/*     */     
/*     */     public void tasksupport_$eq(TaskSupport ts) {
/* 136 */       ParIterableLike$class.tasksupport_$eq(this, ts);
/*     */     }
/*     */     
/*     */     public ParMap<K, S> repr() {
/* 136 */       return (ParMap<K, S>)ParIterableLike$class.repr(this);
/*     */     }
/*     */     
/*     */     public final boolean isTraversableAgain() {
/* 136 */       return ParIterableLike$class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 136 */       return ParIterableLike$class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 136 */       return ParIterableLike$class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 136 */       return ParIterableLike$class.nonEmpty(this);
/*     */     }
/*     */     
/*     */     public Tuple2<K, S> head() {
/* 136 */       return (Tuple2<K, S>)ParIterableLike$class.head(this);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<K, S>> headOption() {
/* 136 */       return ParIterableLike$class.headOption(this);
/*     */     }
/*     */     
/*     */     public ParMap<K, S> tail() {
/* 136 */       return (ParMap<K, S>)ParIterableLike$class.tail(this);
/*     */     }
/*     */     
/*     */     public Tuple2<K, S> last() {
/* 136 */       return (Tuple2<K, S>)ParIterableLike$class.last(this);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<K, S>> lastOption() {
/* 136 */       return ParIterableLike$class.lastOption(this);
/*     */     }
/*     */     
/*     */     public ParMap<K, S> init() {
/* 136 */       return (ParMap<K, S>)ParIterableLike$class.init(this);
/*     */     }
/*     */     
/*     */     public Splitter<Tuple2<K, S>> iterator() {
/* 136 */       return ParIterableLike$class.iterator(this);
/*     */     }
/*     */     
/*     */     public ParMap<K, S> par() {
/* 136 */       return (ParMap<K, S>)ParIterableLike$class.par(this);
/*     */     }
/*     */     
/*     */     public boolean isStrictSplitterCollection() {
/* 136 */       return ParIterableLike$class.isStrictSplitterCollection(this);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> reuse(Option oldc, Combiner newc) {
/* 136 */       return ParIterableLike$class.reuse(this, oldc, newc);
/*     */     }
/*     */     
/*     */     public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask tsk) {
/* 136 */       return ParIterableLike$class.task2ops(this, tsk);
/*     */     }
/*     */     
/*     */     public <R> Object wrap(Function0 body) {
/* 136 */       return ParIterableLike$class.wrap(this, body);
/*     */     }
/*     */     
/*     */     public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(DelegatedSignalling it) {
/* 136 */       return ParIterableLike$class.delegatedSignalling2ops(this, it);
/*     */     }
/*     */     
/*     */     public <Elem, To> Object builder2ops(Builder cb) {
/* 136 */       return ParIterableLike$class.builder2ops(this, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Object bf2seq(CanBuildFrom bf) {
/* 136 */       return ParIterableLike$class.bf2seq(this, bf);
/*     */     }
/*     */     
/*     */     public <S, That extends Parallel> ParMap<K, S> sequentially(Function1 b) {
/* 136 */       return (ParMap<K, S>)ParIterableLike$class.sequentially(this, b);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 136 */       return ParIterableLike$class.mkString(this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 136 */       return ParIterableLike$class.mkString(this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 136 */       return ParIterableLike$class.mkString(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 136 */       return ParIterableLike$class.toString(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object other) {
/* 136 */       return ParIterableLike$class.canEqual(this, other);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/* 136 */       return (U)ParIterableLike$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <U> Option<U> reduceOption(Function2 op) {
/* 136 */       return ParIterableLike$class.reduceOption(this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/* 136 */       return (U)ParIterableLike$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <S> S aggregate(Object z, Function2 seqop, Function2 combop) {
/* 136 */       return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <S> S foldLeft(Object z, Function2 op) {
/* 136 */       return (S)ParIterableLike$class.foldLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <S> S foldRight(Object z, Function2 op) {
/* 136 */       return (S)ParIterableLike$class.foldRight(this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(Function2 op) {
/* 136 */       return (U)ParIterableLike$class.reduceLeft(this, op);
/*     */     }
/*     */     
/*     */     public <U> U reduceRight(Function2 op) {
/* 136 */       return (U)ParIterableLike$class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public <U> Option<U> reduceLeftOption(Function2 op) {
/* 136 */       return ParIterableLike$class.reduceLeftOption(this, op);
/*     */     }
/*     */     
/*     */     public <U> Option<U> reduceRightOption(Function2 op) {
/* 136 */       return ParIterableLike$class.reduceRightOption(this, op);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 136 */       return ParIterableLike$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/* 136 */       return (U)ParIterableLike$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/* 136 */       return (U)ParIterableLike$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <U> Tuple2<K, S> min(Ordering ord) {
/* 136 */       return (Tuple2<K, S>)ParIterableLike$class.min(this, ord);
/*     */     }
/*     */     
/*     */     public <U> Tuple2<K, S> max(Ordering ord) {
/* 136 */       return (Tuple2<K, S>)ParIterableLike$class.max(this, ord);
/*     */     }
/*     */     
/*     */     public <S> Tuple2<K, S> maxBy(Function1 f, Ordering cmp) {
/* 136 */       return (Tuple2<K, S>)ParIterableLike$class.maxBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <S> Tuple2<K, S> minBy(Function1 f, Ordering cmp) {
/* 136 */       return (Tuple2<K, S>)ParIterableLike$class.minBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <S, That> That map(Function1 f, CanBuildFrom bf) {
/* 136 */       return (That)ParIterableLike$class.map(this, f, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 136 */       return (That)ParIterableLike$class.collect(this, pf, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 136 */       return (That)ParIterableLike$class.flatMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 pred) {
/* 136 */       return ParIterableLike$class.forall(this, pred);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 pred) {
/* 136 */       return ParIterableLike$class.exists(this, pred);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<K, S>> find(Function1 pred) {
/* 136 */       return ParIterableLike$class.find(this, pred);
/*     */     }
/*     */     
/*     */     public Object combinerFactory() {
/* 136 */       return ParIterableLike$class.combinerFactory(this);
/*     */     }
/*     */     
/*     */     public <S, That> Object combinerFactory(Function0 cbf) {
/* 136 */       return ParIterableLike$class.combinerFactory(this, cbf);
/*     */     }
/*     */     
/*     */     public ParMap<K, S> filter(Function1 pred) {
/* 136 */       return (ParMap<K, S>)ParIterableLike$class.filter(this, pred);
/*     */     }
/*     */     
/*     */     public ParMap<K, S> filterNot(Function1 pred) {
/* 136 */       return (ParMap<K, S>)ParIterableLike$class.filterNot(this, pred);
/*     */     }
/*     */     
/*     */     public <U, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/* 136 */       return (That)ParIterableLike$class.$plus$plus(this, that, bf);
/*     */     }
/*     */     
/*     */     public Tuple2<ParMap<K, S>, ParMap<K, S>> partition(Function1 pred) {
/* 136 */       return ParIterableLike$class.partition(this, pred);
/*     */     }
/*     */     
/*     */     public <K> ParMap<K, ParMap<K, S>> groupBy(Function1 f) {
/* 136 */       return ParIterableLike$class.groupBy(this, f);
/*     */     }
/*     */     
/*     */     public ParMap<K, S> take(int n) {
/* 136 */       return (ParMap<K, S>)ParIterableLike$class.take(this, n);
/*     */     }
/*     */     
/*     */     public ParMap<K, S> drop(int n) {
/* 136 */       return (ParMap<K, S>)ParIterableLike$class.drop(this, n);
/*     */     }
/*     */     
/*     */     public ParMap<K, S> slice(int unc_from, int unc_until) {
/* 136 */       return (ParMap<K, S>)ParIterableLike$class.slice(this, unc_from, unc_until);
/*     */     }
/*     */     
/*     */     public Tuple2<ParMap<K, S>, ParMap<K, S>> splitAt(int n) {
/* 136 */       return ParIterableLike$class.splitAt(this, n);
/*     */     }
/*     */     
/*     */     public <U, That> That scan(Object z, Function2 op, CanBuildFrom bf) {
/* 136 */       return (That)ParIterableLike$class.scan(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 136 */       return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <S, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 136 */       return (That)ParIterableLike$class.scanRight(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public ParMap<K, S> takeWhile(Function1 pred) {
/* 136 */       return (ParMap<K, S>)ParIterableLike$class.takeWhile(this, pred);
/*     */     }
/*     */     
/*     */     public Tuple2<ParMap<K, S>, ParMap<K, S>> span(Function1 pred) {
/* 136 */       return ParIterableLike$class.span(this, pred);
/*     */     }
/*     */     
/*     */     public ParMap<K, S> dropWhile(Function1 pred) {
/* 136 */       return (ParMap<K, S>)ParIterableLike$class.dropWhile(this, pred);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object xs) {
/* 136 */       ParIterableLike$class.copyToArray(this, xs);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object xs, int start) {
/* 136 */       ParIterableLike$class.copyToArray(this, xs, start);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object xs, int start, int len) {
/* 136 */       ParIterableLike$class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public <U> boolean sameElements(GenIterable that) {
/* 136 */       return ParIterableLike$class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public <U, S, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 136 */       return (That)ParIterableLike$class.zip(this, that, bf);
/*     */     }
/*     */     
/*     */     public <U, That> That zipWithIndex(CanBuildFrom bf) {
/* 136 */       return (That)ParIterableLike$class.zipWithIndex(this, bf);
/*     */     }
/*     */     
/*     */     public <S, U, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 136 */       return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
/*     */     }
/*     */     
/*     */     public <U, That> That toParCollection(Function0 cbf) {
/* 136 */       return (That)ParIterableLike$class.toParCollection(this, cbf);
/*     */     }
/*     */     
/*     */     public <K, V, That> That toParMap(Function0 cbf, Predef$.less.colon.less ev) {
/* 136 */       return (That)ParIterableLike$class.toParMap(this, cbf, ev);
/*     */     }
/*     */     
/*     */     public Object view() {
/* 136 */       return ParIterableLike$class.view(this);
/*     */     }
/*     */     
/*     */     public <U> Object toArray(ClassTag evidence$1) {
/* 136 */       return ParIterableLike$class.toArray(this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<Tuple2<K, S>> toList() {
/* 136 */       return ParIterableLike$class.toList(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<Tuple2<K, S>> toIndexedSeq() {
/* 136 */       return ParIterableLike$class.toIndexedSeq(this);
/*     */     }
/*     */     
/*     */     public Stream<Tuple2<K, S>> toStream() {
/* 136 */       return ParIterableLike$class.toStream(this);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<K, S>> toIterator() {
/* 136 */       return ParIterableLike$class.toIterator(this);
/*     */     }
/*     */     
/*     */     public <U> Buffer<U> toBuffer() {
/* 136 */       return ParIterableLike$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public GenTraversable<Tuple2<K, S>> toTraversable() {
/* 136 */       return ParIterableLike$class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public ParIterable<Tuple2<K, S>> toIterable() {
/* 136 */       return ParIterableLike$class.toIterable(this);
/*     */     }
/*     */     
/*     */     public ParSeq<Tuple2<K, S>> toSeq() {
/* 136 */       return ParIterableLike$class.toSeq(this);
/*     */     }
/*     */     
/*     */     public <U> ParSet<U> toSet() {
/* 136 */       return ParIterableLike$class.toSet(this);
/*     */     }
/*     */     
/*     */     public <K, V> ParMap<K, V> toMap(Predef$.less.colon.less ev) {
/* 136 */       return ParIterableLike$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public Vector<Tuple2<K, S>> toVector() {
/* 136 */       return ParIterableLike$class.toVector(this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 136 */       return (Col)ParIterableLike$class.to(this, cbf);
/*     */     }
/*     */     
/*     */     public int scanBlockSize() {
/* 136 */       return ParIterableLike$class.scanBlockSize(this);
/*     */     }
/*     */     
/*     */     public <S> S $div$colon(Object z, Function2 op) {
/* 136 */       return (S)ParIterableLike$class.$div$colon(this, z, op);
/*     */     }
/*     */     
/*     */     public <S> S $colon$bslash(Object z, Function2 op) {
/* 136 */       return (S)ParIterableLike$class.$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/* 136 */       return ParIterableLike$class.debugInformation(this);
/*     */     }
/*     */     
/*     */     public Seq<String> brokenInvariants() {
/* 136 */       return ParIterableLike$class.brokenInvariants(this);
/*     */     }
/*     */     
/*     */     public ArrayBuffer<String> debugBuffer() {
/* 136 */       return ParIterableLike$class.debugBuffer(this);
/*     */     }
/*     */     
/*     */     public void debugclear() {
/* 136 */       ParIterableLike$class.debugclear(this);
/*     */     }
/*     */     
/*     */     public ArrayBuffer<String> debuglog(String s) {
/* 136 */       return ParIterableLike$class.debuglog(this, s);
/*     */     }
/*     */     
/*     */     public void printDebugBuffer() {
/* 136 */       ParIterableLike$class.printDebugBuffer(this);
/*     */     }
/*     */     
/*     */     public Combiner<Tuple2<K, S>, ParMap<K, S>> parCombiner() {
/* 136 */       return CustomParallelizable.class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public Combiner<Tuple2<K, S>, ParMap<K, S>> newCombiner() {
/* 136 */       return GenericParMapTemplate.class.newCombiner(this);
/*     */     }
/*     */     
/*     */     public <P, Q> Combiner<Tuple2<P, Q>, ParMap<P, Q>> genericMapCombiner() {
/* 136 */       return GenericParMapTemplate.class.genericMapCombiner(this);
/*     */     }
/*     */     
/*     */     public Builder<Tuple2<K, S>, ParIterable<Tuple2<K, S>>> newBuilder() {
/* 136 */       return GenericParTemplate.class.newBuilder(this);
/*     */     }
/*     */     
/*     */     public <B> Combiner<B, ParIterable<B>> genericBuilder() {
/* 136 */       return GenericParTemplate.class.genericBuilder(this);
/*     */     }
/*     */     
/*     */     public <B> Combiner<B, ParIterable<B>> genericCombiner() {
/* 136 */       return GenericParTemplate.class.genericCombiner(this);
/*     */     }
/*     */     
/*     */     public <A1, A2> Tuple2<ParIterable<A1>, ParIterable<A2>> unzip(Function1 asPair) {
/* 136 */       return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*     */     }
/*     */     
/*     */     public <A1, A2, A3> Tuple3<ParIterable<A1>, ParIterable<A2>, ParIterable<A3>> unzip3(Function1 asTriple) {
/* 136 */       return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*     */     }
/*     */     
/*     */     public <B> ParIterable<B> flatten(Function1 asTraversable) {
/* 136 */       return (ParIterable<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*     */     }
/*     */     
/*     */     public <B> ParIterable<ParIterable<B>> transpose(Function1 asTraversable) {
/* 136 */       return (ParIterable<ParIterable<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 136 */       return GenMapLike.class.hashCode(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 136 */       return GenMapLike.class.equals(this, that);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 136 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public ParMapLike$$anon$2(ParMapLike $outer, Function1 f$4) {
/* 136 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 136 */       Parallelizable.class.$init$((Parallelizable)this);
/* 136 */       GenMapLike.class.$init$(this);
/* 136 */       GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/* 136 */       GenTraversable.class.$init$((GenTraversable)this);
/* 136 */       GenIterable.class.$init$(this);
/* 136 */       GenericParTemplate.class.$init$(this);
/* 136 */       GenericParMapTemplate.class.$init$(this);
/* 136 */       CustomParallelizable.class.$init$(this);
/* 136 */       ParIterableLike$class.$init$(this);
/* 136 */       ParIterable$class.$init$(this);
/* 136 */       ParMapLike$class.$init$(this);
/* 136 */       ParMap$class.$init$(this);
/*     */     }
/*     */     
/*     */     public <Q> void foreach(Function1 g) {
/* 137 */       this.$outer.filter((Function1)new ParMapLike$$anon$2$$anonfun$foreach$6(this)).foreach((Function1)new ParMapLike$$anon$2$$anonfun$foreach$7(this, ($anon$2)g));
/*     */     }
/*     */     
/*     */     public class ParMapLike$$anon$2$$anonfun$foreach$6 extends AbstractFunction1<Tuple2<K, V>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Tuple2 check$ifrefutable$3) {
/*     */         boolean bool;
/* 137 */         if (check$ifrefutable$3 != null) {
/* 137 */           bool = true;
/*     */         } else {
/* 137 */           bool = false;
/*     */         } 
/* 137 */         return bool;
/*     */       }
/*     */       
/*     */       public ParMapLike$$anon$2$$anonfun$foreach$6(ParMapLike$$anon$2 $outer) {}
/*     */     }
/*     */     
/*     */     public class ParMapLike$$anon$2$$anonfun$foreach$7 extends AbstractFunction1<Tuple2<K, V>, Q> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 g$1;
/*     */       
/*     */       public final Q apply(Tuple2 x$7) {
/* 137 */         if (x$7 != null)
/* 137 */           return (Q)this.g$1.apply(new Tuple2(x$7._1(), this.$outer.f$4.apply(x$7._2()))); 
/* 137 */         throw new MatchError(x$7);
/*     */       }
/*     */       
/*     */       public ParMapLike$$anon$2$$anonfun$foreach$7(ParMapLike$$anon$2 $outer, Function1 g$1) {}
/*     */     }
/*     */     
/*     */     public IterableSplitter<Tuple2<K, V>>.Mapped<Tuple2<K, S>> splitter() {
/* 138 */       return this.$outer.splitter().map((Function1)new ParMapLike$$anon$2$$anonfun$splitter$1(this));
/*     */     }
/*     */     
/*     */     public class ParMapLike$$anon$2$$anonfun$splitter$1 extends AbstractFunction1<Tuple2<K, V>, Tuple2<K, S>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Tuple2<K, S> apply(Tuple2 kv) {
/* 138 */         return new Tuple2(kv._1(), this.$outer.f$4.apply(kv._2()));
/*     */       }
/*     */       
/*     */       public ParMapLike$$anon$2$$anonfun$splitter$1(ParMapLike$$anon$2 $outer) {}
/*     */     }
/*     */     
/*     */     public int size() {
/* 139 */       return this.$outer.size();
/*     */     }
/*     */     
/*     */     public boolean contains(Object key) {
/* 140 */       return this.$outer.contains(key);
/*     */     }
/*     */     
/*     */     public Option<S> get(Object key) {
/* 141 */       Function1 function1 = this.f$4;
/*     */       Option option;
/* 141 */       return (option = this.$outer.get(key)).isEmpty() ? (Option<S>)None$.MODULE$ : (Option<S>)new Some(function1.apply(option.get()));
/*     */     }
/*     */     
/*     */     public Map<K, S> seq() {
/* 142 */       return ((MapLike)this.$outer.seq()).mapValues(this.f$4);
/*     */     }
/*     */     
/*     */     public <U> ParMap<K, U> $plus(Tuple2 kv) {
/* 143 */       return ((ParMap)((ParIterableLike)ParMap$.MODULE$.apply((Seq)Nil$.MODULE$)).$plus$plus((GenTraversableOnce)this, (CanBuildFrom)ParMap$.MODULE$.canBuildFrom())).$plus(kv);
/*     */     }
/*     */     
/*     */     public ParMap<K, S> $minus(Object key) {
/* 144 */       return (ParMap<K, S>)((GenMapLike)((ParIterableLike)ParMap$.MODULE$.apply((Seq)Nil$.MODULE$)).$plus$plus((GenTraversableOnce)this, (CanBuildFrom)ParMap$.MODULE$.canBuildFrom())).$minus(key);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParMapLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */