/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Tuple2;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenIterableViewLike;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSeqLike;
/*    */ import scala.collection.GenSeqViewLike;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.GenTraversableViewLike;
/*    */ import scala.collection.IndexedSeq;
/*    */ import scala.collection.IndexedSeqLike;
/*    */ import scala.collection.IndexedSeqOptimized;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.IterableLike;
/*    */ import scala.collection.IterableView;
/*    */ import scala.collection.IterableViewLike;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Parallelizable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.SeqLike;
/*    */ import scala.collection.SeqView;
/*    */ import scala.collection.SeqViewLike;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.TraversableViewLike;
/*    */ import scala.collection.ViewMkString;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.FilterMonadic;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.GenericTraversableTemplate;
/*    */ import scala.collection.generic.SliceInterval;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.collection.immutable.Range;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.collection.immutable.Stream;
/*    */ import scala.collection.immutable.Vector;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.mutable.ParSeq;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\00113q!\001\002\021\002\007\005\021B\001\bJ]\022,\0070\0323TKFd\025n[3\013\005\r!\021aB7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\004\025QY2c\001\001\f\037A\021A\"D\007\002\r%\021aB\002\002\004\003:L\b\003\002\t\022%ii\021\001B\005\003\003\021\001\"a\005\013\r\001\021)Q\003\001b\001-\t\t\021)\005\002\030\027A\021A\002G\005\0033\031\021qAT8uQ&tw\r\005\002\0247\0211A\004\001CC\002Y\021AAU3qe\")a\004\001C\001?\0051A%\0338ji\022\"\022\001\t\t\003\031\005J!A\t\004\003\tUs\027\016\036\005\007I\001\001K\021K\023\002\035QD\027n]\"pY2,7\r^5p]V\ta\005E\002(QIi\021AA\005\003S\t\021!\"\0238eKb,GmU3r\021\031Y\003\001)C)Y\005aAo\\\"pY2,7\r^5p]R\021a%\f\005\006])\002\rAG\001\005e\026\004(\017C\0031\001\031\005\021'\001\004va\022\fG/\032\013\004AI:\004\"B\0320\001\004!\024aA5eqB\021A\"N\005\003m\031\0211!\0238u\021\025At\0061\001\023\003\021)G.Z7\t\013i\002A\021I\036\002\tYLWm^\013\002yI\031Qh\020\"\007\tyJ\004\001\020\002\ryI,g-\0338f[\026tGO\020\t\003\031\001K!!\021\004\003\r\005s\027PU3g!\02193I\005\016\n\005\021\023!AD%oI\026DX\rZ*fcZKWm\036\005\006u\001!\tE\022\013\004\005\036K\005\"\002%F\001\004!\024\001\0024s_6DQAS#A\002Q\nQ!\0368uS2\004Ba\n\001\0235\001")
/*    */ public interface IndexedSeqLike<A, Repr> extends IndexedSeqLike<A, Repr> {
/*    */   IndexedSeq<A> thisCollection();
/*    */   
/*    */   IndexedSeq<A> toCollection(Repr paramRepr);
/*    */   
/*    */   void update(int paramInt, A paramA);
/*    */   
/*    */   Object view();
/*    */   
/*    */   IndexedSeqView<A, Repr> view(int paramInt1, int paramInt2);
/*    */   
/*    */   public class IndexedSeqLike$$anon$1 implements IndexedSeqView<A, Repr> {
/*    */     private Repr underlying;
/*    */     
/*    */     private volatile boolean bitmap$0;
/*    */     
/*    */     public IndexedSeqView<A, Repr>.Transformed<A> newFiltered(Function1 p) {
/* 54 */       return IndexedSeqView$class.newFiltered(this, p);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr>.Transformed<A> newSliced(SliceInterval _endpoints) {
/* 54 */       return IndexedSeqView$class.newSliced(this, _endpoints);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr>.Transformed<A> newDroppedWhile(Function1 p) {
/* 54 */       return IndexedSeqView$class.newDroppedWhile(this, p);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr>.Transformed<A> newTakenWhile(Function1 p) {
/* 54 */       return IndexedSeqView$class.newTakenWhile(this, p);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr>.Transformed<A> newReversed() {
/* 54 */       return IndexedSeqView$class.newReversed(this);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> filter(Function1 p) {
/* 54 */       return IndexedSeqView$class.filter(this, p);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> init() {
/* 54 */       return IndexedSeqView$class.init(this);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> drop(int n) {
/* 54 */       return IndexedSeqView$class.drop(this, n);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> take(int n) {
/* 54 */       return IndexedSeqView$class.take(this, n);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> slice(int from, int until) {
/* 54 */       return IndexedSeqView$class.slice(this, from, until);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> dropWhile(Function1 p) {
/* 54 */       return IndexedSeqView$class.dropWhile(this, p);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> takeWhile(Function1 p) {
/* 54 */       return IndexedSeqView$class.takeWhile(this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<IndexedSeqView<A, Repr>, IndexedSeqView<A, Repr>> span(Function1 p) {
/* 54 */       return IndexedSeqView$class.span(this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<IndexedSeqView<A, Repr>, IndexedSeqView<A, Repr>> splitAt(int n) {
/* 54 */       return IndexedSeqView$class.splitAt(this, n);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> reverse() {
/* 54 */       return IndexedSeqView$class.reverse(this);
/*    */     }
/*    */     
/*    */     public <B> SeqViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<B> newForced(Function0 xs) {
/* 54 */       return SeqViewLike.class.newForced(this, xs);
/*    */     }
/*    */     
/*    */     public <B> SeqViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<B> newAppended(GenTraversable that) {
/* 54 */       return SeqViewLike.class.newAppended(this, that);
/*    */     }
/*    */     
/*    */     public <B> SeqViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<B> newMapped(Function1 f) {
/* 54 */       return SeqViewLike.class.newMapped(this, f);
/*    */     }
/*    */     
/*    */     public <B> SeqViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<B> newFlatMapped(Function1 f) {
/* 54 */       return SeqViewLike.class.newFlatMapped(this, f);
/*    */     }
/*    */     
/*    */     public <B> SeqViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<Tuple2<A, B>> newZipped(GenIterable that) {
/* 54 */       return SeqViewLike.class.newZipped(this, that);
/*    */     }
/*    */     
/*    */     public <A1, B> SeqViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<Tuple2<A1, B>> newZippedAll(GenIterable that, Object _thisElem, Object _thatElem) {
/* 54 */       return SeqViewLike.class.newZippedAll(this, that, _thisElem, _thatElem);
/*    */     }
/*    */     
/*    */     public <B> SeqViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<B> newPatched(int _from, GenSeq _patch, int _replaced) {
/* 54 */       return SeqViewLike.class.newPatched(this, _from, _patch, _replaced);
/*    */     }
/*    */     
/*    */     public <B> SeqViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<B> newPrepended(Object elem) {
/* 54 */       return SeqViewLike.class.newPrepended(this, elem);
/*    */     }
/*    */     
/*    */     public SeqViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<A> newTaken(int n) {
/* 54 */       return SeqViewLike.class.newTaken(this, n);
/*    */     }
/*    */     
/*    */     public SeqViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<A> newDropped(int n) {
/* 54 */       return SeqViewLike.class.newDropped(this, n);
/*    */     }
/*    */     
/*    */     public <B, That> That patch(int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/* 54 */       return (That)SeqViewLike.class.patch(this, from, patch, replaced, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/* 54 */       return (That)SeqViewLike.class.padTo(this, len, elem, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That reverseMap(Function1 f, CanBuildFrom bf) {
/* 54 */       return (That)SeqViewLike.class.reverseMap(this, f, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That updated(int index, Object elem, CanBuildFrom bf) {
/* 54 */       return (That)SeqViewLike.class.updated(this, index, elem, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/* 54 */       return (That)SeqViewLike.class.$plus$colon(this, elem, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/* 54 */       return (That)SeqViewLike.class.$colon$plus(this, elem, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That union(GenSeq that, CanBuildFrom bf) {
/* 54 */       return (That)SeqViewLike.class.union(this, that, bf);
/*    */     }
/*    */     
/*    */     public <B> IndexedSeqView<A, Repr> diff(GenSeq that) {
/* 54 */       return (IndexedSeqView<A, Repr>)SeqViewLike.class.diff(this, that);
/*    */     }
/*    */     
/*    */     public <B> IndexedSeqView<A, Repr> intersect(GenSeq that) {
/* 54 */       return (IndexedSeqView<A, Repr>)SeqViewLike.class.intersect(this, that);
/*    */     }
/*    */     
/*    */     public <B> IndexedSeqView<A, Repr> sorted(Ordering ord) {
/* 54 */       return (IndexedSeqView<A, Repr>)SeqViewLike.class.sorted(this, ord);
/*    */     }
/*    */     
/*    */     public String stringPrefix() {
/* 54 */       return SeqViewLike.class.stringPrefix(this);
/*    */     }
/*    */     
/*    */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 54 */       return (That)IterableViewLike.class.zip((IterableViewLike)this, that, bf);
/*    */     }
/*    */     
/*    */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/* 54 */       return (That)IterableViewLike.class.zipWithIndex((IterableViewLike)this, bf);
/*    */     }
/*    */     
/*    */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 54 */       return (That)IterableViewLike.class.zipAll((IterableViewLike)this, that, thisElem, thatElem, bf);
/*    */     }
/*    */     
/*    */     public Iterator<IndexedSeqView<A, Repr>> grouped(int size) {
/* 54 */       return IterableViewLike.class.grouped((IterableViewLike)this, size);
/*    */     }
/*    */     
/*    */     public Iterator<IndexedSeqView<A, Repr>> sliding(int size, int step) {
/* 54 */       return IterableViewLike.class.sliding((IterableViewLike)this, size, step);
/*    */     }
/*    */     
/*    */     public Builder<A, IndexedSeqView<A, Repr>> newBuilder() {
/* 54 */       return TraversableViewLike.class.newBuilder((TraversableViewLike)this);
/*    */     }
/*    */     
/*    */     public String viewIdentifier() {
/* 54 */       return TraversableViewLike.class.viewIdentifier((TraversableViewLike)this);
/*    */     }
/*    */     
/*    */     public String viewIdString() {
/* 54 */       return TraversableViewLike.class.viewIdString((TraversableViewLike)this);
/*    */     }
/*    */     
/*    */     public <B, That> That force(CanBuildFrom bf) {
/* 54 */       return (That)TraversableViewLike.class.force((TraversableViewLike)this, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/* 54 */       return (That)TraversableViewLike.class.$plus$plus((TraversableViewLike)this, xs, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 54 */       return (That)TraversableViewLike.class.map((TraversableViewLike)this, f, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 54 */       return (That)TraversableViewLike.class.collect((TraversableViewLike)this, pf, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 54 */       return (That)TraversableViewLike.class.flatMap((TraversableViewLike)this, f, bf);
/*    */     }
/*    */     
/*    */     public <B> TraversableViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<B> flatten(Function1 asTraversable) {
/* 54 */       return TraversableViewLike.class.flatten((TraversableViewLike)this, asTraversable);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> withFilter(Function1 p) {
/* 54 */       return (IndexedSeqView<A, Repr>)TraversableViewLike.class.withFilter((TraversableViewLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<IndexedSeqView<A, Repr>, IndexedSeqView<A, Repr>> partition(Function1 p) {
/* 54 */       return TraversableViewLike.class.partition((TraversableViewLike)this, p);
/*    */     }
/*    */     
/*    */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 54 */       return (That)TraversableViewLike.class.scanLeft((TraversableViewLike)this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 54 */       return (That)TraversableViewLike.class.scanRight((TraversableViewLike)this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <K> Map<K, IndexedSeqView<A, Repr>> groupBy(Function1 f) {
/* 54 */       return TraversableViewLike.class.groupBy((TraversableViewLike)this, f);
/*    */     }
/*    */     
/*    */     public <A1, A2> Tuple2<TraversableViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<A1>, TraversableViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<A2>> unzip(Function1 asPair) {
/* 54 */       return TraversableViewLike.class.unzip((TraversableViewLike)this, asPair);
/*    */     }
/*    */     
/*    */     public <A1, A2, A3> Tuple3<TraversableViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<A1>, TraversableViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<A2>, TraversableViewLike<A, Repr, IndexedSeqView<A, Repr>>.Transformed<A3>> unzip3(Function1 asTriple) {
/* 54 */       return TraversableViewLike.class.unzip3((TraversableViewLike)this, asTriple);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 54 */       return TraversableViewLike.class.toString((TraversableViewLike)this);
/*    */     }
/*    */     
/*    */     public String viewToString() {
/* 54 */       return GenTraversableViewLike.class.viewToString((GenTraversableViewLike)this);
/*    */     }
/*    */     
/*    */     public Seq<A> thisSeq() {
/* 54 */       return ViewMkString.class.thisSeq((ViewMkString)this);
/*    */     }
/*    */     
/*    */     public String mkString() {
/* 54 */       return ViewMkString.class.mkString((ViewMkString)this);
/*    */     }
/*    */     
/*    */     public String mkString(String sep) {
/* 54 */       return ViewMkString.class.mkString((ViewMkString)this, sep);
/*    */     }
/*    */     
/*    */     public String mkString(String start, String sep, String end) {
/* 54 */       return ViewMkString.class.mkString((ViewMkString)this, start, sep, end);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 54 */       return ViewMkString.class.addString((ViewMkString)this, b, start, sep, end);
/*    */     }
/*    */     
/*    */     public Object scala$collection$IndexedSeqOptimized$$super$reduceLeft(Function2 op) {
/* 54 */       return TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public Object scala$collection$IndexedSeqOptimized$$super$reduceRight(Function2 op) {
/* 54 */       return IterableLike.class.reduceRight(this, op);
/*    */     }
/*    */     
/*    */     public Object scala$collection$IndexedSeqOptimized$$super$zip(GenIterable that, CanBuildFrom bf) {
/* 54 */       return IterableLike.class.zip(this, that, bf);
/*    */     }
/*    */     
/*    */     public Object scala$collection$IndexedSeqOptimized$$super$head() {
/* 54 */       return IterableLike.class.head(this);
/*    */     }
/*    */     
/*    */     public Object scala$collection$IndexedSeqOptimized$$super$tail() {
/* 54 */       return TraversableLike.class.tail(this);
/*    */     }
/*    */     
/*    */     public Object scala$collection$IndexedSeqOptimized$$super$last() {
/* 54 */       return TraversableLike.class.last(this);
/*    */     }
/*    */     
/*    */     public Object scala$collection$IndexedSeqOptimized$$super$init() {
/* 54 */       return TraversableLike.class.init(this);
/*    */     }
/*    */     
/*    */     public boolean scala$collection$IndexedSeqOptimized$$super$sameElements(GenIterable that) {
/* 54 */       return IterableLike.class.sameElements(this, that);
/*    */     }
/*    */     
/*    */     public boolean scala$collection$IndexedSeqOptimized$$super$endsWith(GenSeq that) {
/* 54 */       return SeqLike.class.endsWith(this, that);
/*    */     }
/*    */     
/*    */     public boolean isEmpty() {
/* 54 */       return IndexedSeqOptimized.class.isEmpty(this);
/*    */     }
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 54 */       IndexedSeqOptimized.class.foreach(this, f);
/*    */     }
/*    */     
/*    */     public boolean forall(Function1 p) {
/* 54 */       return IndexedSeqOptimized.class.forall(this, p);
/*    */     }
/*    */     
/*    */     public boolean exists(Function1 p) {
/* 54 */       return IndexedSeqOptimized.class.exists(this, p);
/*    */     }
/*    */     
/*    */     public Option<A> find(Function1 p) {
/* 54 */       return IndexedSeqOptimized.class.find(this, p);
/*    */     }
/*    */     
/*    */     public <B> B foldLeft(Object z, Function2 op) {
/* 54 */       return (B)IndexedSeqOptimized.class.foldLeft(this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B foldRight(Object z, Function2 op) {
/* 54 */       return (B)IndexedSeqOptimized.class.foldRight(this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceLeft(Function2 op) {
/* 54 */       return (B)IndexedSeqOptimized.class.reduceLeft(this, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceRight(Function2 op) {
/* 54 */       return (B)IndexedSeqOptimized.class.reduceRight(this, op);
/*    */     }
/*    */     
/*    */     public A head() {
/* 54 */       return (A)IndexedSeqOptimized.class.head(this);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> tail() {
/* 54 */       return (IndexedSeqView<A, Repr>)IndexedSeqOptimized.class.tail(this);
/*    */     }
/*    */     
/*    */     public A last() {
/* 54 */       return (A)IndexedSeqOptimized.class.last(this);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> takeRight(int n) {
/* 54 */       return (IndexedSeqView<A, Repr>)IndexedSeqOptimized.class.takeRight(this, n);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> dropRight(int n) {
/* 54 */       return (IndexedSeqView<A, Repr>)IndexedSeqOptimized.class.dropRight(this, n);
/*    */     }
/*    */     
/*    */     public <B> boolean sameElements(GenIterable that) {
/* 54 */       return IndexedSeqOptimized.class.sameElements(this, that);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start, int len) {
/* 54 */       IndexedSeqOptimized.class.copyToArray(this, xs, start, len);
/*    */     }
/*    */     
/*    */     public int lengthCompare(int len) {
/* 54 */       return IndexedSeqOptimized.class.lengthCompare(this, len);
/*    */     }
/*    */     
/*    */     public int segmentLength(Function1 p, int from) {
/* 54 */       return IndexedSeqOptimized.class.segmentLength(this, p, from);
/*    */     }
/*    */     
/*    */     public int indexWhere(Function1 p, int from) {
/* 54 */       return IndexedSeqOptimized.class.indexWhere(this, p, from);
/*    */     }
/*    */     
/*    */     public int lastIndexWhere(Function1 p, int end) {
/* 54 */       return IndexedSeqOptimized.class.lastIndexWhere(this, p, end);
/*    */     }
/*    */     
/*    */     public Iterator<A> reverseIterator() {
/* 54 */       return IndexedSeqOptimized.class.reverseIterator(this);
/*    */     }
/*    */     
/*    */     public <B> boolean startsWith(GenSeq that, int offset) {
/* 54 */       return IndexedSeqOptimized.class.startsWith(this, that, offset);
/*    */     }
/*    */     
/*    */     public <B> boolean endsWith(GenSeq that) {
/* 54 */       return IndexedSeqOptimized.class.endsWith(this, that);
/*    */     }
/*    */     
/*    */     public GenericCompanion<IndexedSeq> companion() {
/* 54 */       return IndexedSeq$class.companion(this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<A> seq() {
/* 54 */       return IndexedSeq$class.seq(this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<A> thisCollection() {
/* 54 */       return IndexedSeqLike$class.thisCollection(this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<A> toCollection(Object repr) {
/* 54 */       return IndexedSeqLike$class.toCollection(this, repr);
/*    */     }
/*    */     
/*    */     public Object view() {
/* 54 */       return IndexedSeqLike$class.view(this);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, IndexedSeqView<A, Repr>> view(int from, int until) {
/* 54 */       return IndexedSeqLike$class.view(this, from, until);
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 54 */       return IndexedSeqLike.class.hashCode(this);
/*    */     }
/*    */     
/*    */     public <A1> Buffer<A1> toBuffer() {
/* 54 */       return IndexedSeqLike.class.toBuffer(this);
/*    */     }
/*    */     
/*    */     public Combiner<A, ParSeq<A>> parCombiner() {
/* 54 */       return SeqLike$class.parCombiner(this);
/*    */     }
/*    */     
/*    */     public SeqLike<A, Seq<A>> transform(Function1 f) {
/* 54 */       return SeqLike$class.transform(this, f);
/*    */     }
/*    */     
/*    */     public Object scala$collection$mutable$Cloneable$$super$clone() {
/* 54 */       return super.clone();
/*    */     }
/*    */     
/*    */     public Seq<A> clone() {
/* 54 */       return (Seq<A>)Cloneable$class.clone(this);
/*    */     }
/*    */     
/*    */     public int size() {
/* 54 */       return SeqLike.class.size(this);
/*    */     }
/*    */     
/*    */     public Iterator<IndexedSeqView<A, Repr>> permutations() {
/* 54 */       return SeqLike.class.permutations(this);
/*    */     }
/*    */     
/*    */     public Iterator<IndexedSeqView<A, Repr>> combinations(int n) {
/* 54 */       return SeqLike.class.combinations(this, n);
/*    */     }
/*    */     
/*    */     public <B> int indexOfSlice(GenSeq that) {
/* 54 */       return SeqLike.class.indexOfSlice(this, that);
/*    */     }
/*    */     
/*    */     public <B> int indexOfSlice(GenSeq that, int from) {
/* 54 */       return SeqLike.class.indexOfSlice(this, that, from);
/*    */     }
/*    */     
/*    */     public <B> int lastIndexOfSlice(GenSeq that) {
/* 54 */       return SeqLike.class.lastIndexOfSlice(this, that);
/*    */     }
/*    */     
/*    */     public <B> int lastIndexOfSlice(GenSeq that, int end) {
/* 54 */       return SeqLike.class.lastIndexOfSlice(this, that, end);
/*    */     }
/*    */     
/*    */     public <B> boolean containsSlice(GenSeq that) {
/* 54 */       return SeqLike.class.containsSlice(this, that);
/*    */     }
/*    */     
/*    */     public boolean contains(Object elem) {
/* 54 */       return SeqLike.class.contains(this, elem);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> distinct() {
/* 54 */       return (IndexedSeqView<A, Repr>)SeqLike.class.distinct(this);
/*    */     }
/*    */     
/*    */     public <B> boolean corresponds(GenSeq that, Function2 p) {
/* 54 */       return SeqLike.class.corresponds(this, that, p);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> sortWith(Function2 lt) {
/* 54 */       return (IndexedSeqView<A, Repr>)SeqLike.class.sortWith(this, lt);
/*    */     }
/*    */     
/*    */     public <B> IndexedSeqView<A, Repr> sortBy(Function1 f, Ordering ord) {
/* 54 */       return (IndexedSeqView<A, Repr>)SeqLike.class.sortBy(this, f, ord);
/*    */     }
/*    */     
/*    */     public Seq<A> toSeq() {
/* 54 */       return SeqLike.class.toSeq(this);
/*    */     }
/*    */     
/*    */     public Range indices() {
/* 54 */       return SeqLike.class.indices(this);
/*    */     }
/*    */     
/*    */     public boolean isDefinedAt(int idx) {
/* 54 */       return GenSeqLike.class.isDefinedAt((GenSeqLike)this, idx);
/*    */     }
/*    */     
/*    */     public int prefixLength(Function1 p) {
/* 54 */       return GenSeqLike.class.prefixLength((GenSeqLike)this, p);
/*    */     }
/*    */     
/*    */     public int indexWhere(Function1 p) {
/* 54 */       return GenSeqLike.class.indexWhere((GenSeqLike)this, p);
/*    */     }
/*    */     
/*    */     public <B> int indexOf(Object elem) {
/* 54 */       return GenSeqLike.class.indexOf((GenSeqLike)this, elem);
/*    */     }
/*    */     
/*    */     public <B> int indexOf(Object elem, int from) {
/* 54 */       return GenSeqLike.class.indexOf((GenSeqLike)this, elem, from);
/*    */     }
/*    */     
/*    */     public <B> int lastIndexOf(Object elem) {
/* 54 */       return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem);
/*    */     }
/*    */     
/*    */     public <B> int lastIndexOf(Object elem, int end) {
/* 54 */       return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem, end);
/*    */     }
/*    */     
/*    */     public int lastIndexWhere(Function1 p) {
/* 54 */       return GenSeqLike.class.lastIndexWhere((GenSeqLike)this, p);
/*    */     }
/*    */     
/*    */     public <B> boolean startsWith(GenSeq that) {
/* 54 */       return GenSeqLike.class.startsWith((GenSeqLike)this, that);
/*    */     }
/*    */     
/*    */     public boolean equals(Object that) {
/* 54 */       return GenSeqLike.class.equals((GenSeqLike)this, that);
/*    */     }
/*    */     
/*    */     public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 54 */       return PartialFunction.class.orElse((PartialFunction)this, that);
/*    */     }
/*    */     
/*    */     public <C> PartialFunction<Object, C> andThen(Function1 k) {
/* 54 */       return PartialFunction.class.andThen((PartialFunction)this, k);
/*    */     }
/*    */     
/*    */     public Function1<Object, Option<A>> lift() {
/* 54 */       return PartialFunction.class.lift((PartialFunction)this);
/*    */     }
/*    */     
/*    */     public <A1, B1> B1 applyOrElse(Object x, Function1 default) {
/* 54 */       return (B1)PartialFunction.class.applyOrElse((PartialFunction)this, x, default);
/*    */     }
/*    */     
/*    */     public <U> Function1<Object, Object> runWith(Function1 action) {
/* 54 */       return PartialFunction.class.runWith((PartialFunction)this, action);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZD$sp(double v1) {
/* 54 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDD$sp(double v1) {
/* 54 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFD$sp(double v1) {
/* 54 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcID$sp(double v1) {
/* 54 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJD$sp(double v1) {
/* 54 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVD$sp(double v1) {
/* 54 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZF$sp(float v1) {
/* 54 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDF$sp(float v1) {
/* 54 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFF$sp(float v1) {
/* 54 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcIF$sp(float v1) {
/* 54 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJF$sp(float v1) {
/* 54 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVF$sp(float v1) {
/* 54 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZI$sp(int v1) {
/* 54 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDI$sp(int v1) {
/* 54 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFI$sp(int v1) {
/* 54 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcII$sp(int v1) {
/* 54 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJI$sp(int v1) {
/* 54 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVI$sp(int v1) {
/* 54 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZJ$sp(long v1) {
/* 54 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDJ$sp(long v1) {
/* 54 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFJ$sp(long v1) {
/* 54 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcIJ$sp(long v1) {
/* 54 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJJ$sp(long v1) {
/* 54 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVJ$sp(long v1) {
/* 54 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, A> compose(Function1 g) {
/* 54 */       return Function1.class.compose((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 54 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 54 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public Iterable<A> toIterable() {
/* 54 */       return IterableLike.class.toIterable(this);
/*    */     }
/*    */     
/*    */     public Iterator<A> toIterator() {
/* 54 */       return IterableLike.class.toIterator(this);
/*    */     }
/*    */     
/*    */     public Iterator<IndexedSeqView<A, Repr>> sliding(int size) {
/* 54 */       return IterableLike.class.sliding(this, size);
/*    */     }
/*    */     
/*    */     public Stream<A> toStream() {
/* 54 */       return IterableLike.class.toStream(this);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object that) {
/* 54 */       return IterableLike.class.canEqual(this, that);
/*    */     }
/*    */     
/*    */     public <B> Builder<B, IndexedSeq<B>> genericBuilder() {
/* 54 */       return GenericTraversableTemplate.class.genericBuilder(this);
/*    */     }
/*    */     
/*    */     public <B> IndexedSeq<IndexedSeq<B>> transpose(Function1 asTraversable) {
/* 54 */       return (IndexedSeq<IndexedSeq<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> repr() {
/* 54 */       return (IndexedSeqView<A, Repr>)TraversableLike.class.repr(this);
/*    */     }
/*    */     
/*    */     public final boolean isTraversableAgain() {
/* 54 */       return TraversableLike.class.isTraversableAgain(this);
/*    */     }
/*    */     
/*    */     public boolean hasDefiniteSize() {
/* 54 */       return TraversableLike.class.hasDefiniteSize(this);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 54 */       return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 54 */       return (That)TraversableLike.class.$plus$plus$colon(this, that, bf);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> filterNot(Function1 p) {
/* 54 */       return (IndexedSeqView<A, Repr>)TraversableLike.class.filterNot(this, p);
/*    */     }
/*    */     
/*    */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 54 */       return (That)TraversableLike.class.scan(this, z, op, cbf);
/*    */     }
/*    */     
/*    */     public Option<A> headOption() {
/* 54 */       return TraversableLike.class.headOption(this);
/*    */     }
/*    */     
/*    */     public Option<A> lastOption() {
/* 54 */       return TraversableLike.class.lastOption(this);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> sliceWithKnownDelta(int from, int until, int delta) {
/* 54 */       return (IndexedSeqView<A, Repr>)TraversableLike.class.sliceWithKnownDelta(this, from, until, delta);
/*    */     }
/*    */     
/*    */     public IndexedSeqView<A, Repr> sliceWithKnownBound(int from, int until) {
/* 54 */       return (IndexedSeqView<A, Repr>)TraversableLike.class.sliceWithKnownBound(this, from, until);
/*    */     }
/*    */     
/*    */     public Iterator<IndexedSeqView<A, Repr>> tails() {
/* 54 */       return TraversableLike.class.tails(this);
/*    */     }
/*    */     
/*    */     public Iterator<IndexedSeqView<A, Repr>> inits() {
/* 54 */       return TraversableLike.class.inits(this);
/*    */     }
/*    */     
/*    */     public Traversable<A> toTraversable() {
/* 54 */       return TraversableLike.class.toTraversable(this);
/*    */     }
/*    */     
/*    */     public <Col> Col to(CanBuildFrom cbf) {
/* 54 */       return (Col)TraversableLike.class.to(this, cbf);
/*    */     }
/*    */     
/*    */     public ParSeq<A> par() {
/* 54 */       return (ParSeq<A>)Parallelizable.class.par(this);
/*    */     }
/*    */     
/*    */     public List<A> reversed() {
/* 54 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public boolean nonEmpty() {
/* 54 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public int count(Function1 p) {
/* 54 */       return TraversableOnce.class.count((TraversableOnce)this, p);
/*    */     }
/*    */     
/*    */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 54 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*    */     }
/*    */     
/*    */     public <B> B $div$colon(Object z, Function2 op) {
/* 54 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 54 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 54 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 54 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> A1 reduce(Function2 op) {
/* 54 */       return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 54 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> A1 fold(Object z, Function2 op) {
/* 54 */       return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 54 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*    */     }
/*    */     
/*    */     public <B> B sum(Numeric num) {
/* 54 */       return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*    */     }
/*    */     
/*    */     public <B> B product(Numeric num) {
/* 54 */       return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*    */     }
/*    */     
/*    */     public <B> A min(Ordering cmp) {
/* 54 */       return (A)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*    */     }
/*    */     
/*    */     public <B> A max(Ordering cmp) {
/* 54 */       return (A)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*    */     }
/*    */     
/*    */     public <B> A maxBy(Function1 f, Ordering cmp) {
/* 54 */       return (A)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public <B> A minBy(Function1 f, Ordering cmp) {
/* 54 */       return (A)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public <B> void copyToBuffer(Buffer dest) {
/* 54 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start) {
/* 54 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs) {
/* 54 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*    */     }
/*    */     
/*    */     public <B> Object toArray(ClassTag evidence$1) {
/* 54 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*    */     }
/*    */     
/*    */     public List<A> toList() {
/* 54 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<A> toIndexedSeq() {
/* 54 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <B> Set<B> toSet() {
/* 54 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public Vector<A> toVector() {
/* 54 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 54 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String sep) {
/* 54 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b) {
/* 54 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*    */     }
/*    */     
/*    */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 54 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public IndexedSeqLike$$anon$1(IndexedSeqLike $outer) {
/* 54 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 54 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 54 */       Parallelizable.class.$init$(this);
/* 54 */       TraversableLike.class.$init$(this);
/* 54 */       GenericTraversableTemplate.class.$init$(this);
/* 54 */       GenTraversable.class.$init$((GenTraversable)this);
/* 54 */       Traversable.class.$init$(this);
/* 54 */       Traversable$class.$init$(this);
/* 54 */       GenIterable.class.$init$((GenIterable)this);
/* 54 */       IterableLike.class.$init$(this);
/* 54 */       Iterable.class.$init$(this);
/* 54 */       Iterable$class.$init$(this);
/* 54 */       Function1.class.$init$((Function1)this);
/* 54 */       PartialFunction.class.$init$((PartialFunction)this);
/* 54 */       GenSeqLike.class.$init$((GenSeqLike)this);
/* 54 */       GenSeq.class.$init$((GenSeq)this);
/* 54 */       SeqLike.class.$init$(this);
/* 54 */       Seq.class.$init$(this);
/* 54 */       Cloneable$class.$init$(this);
/* 54 */       SeqLike$class.$init$(this);
/* 54 */       Seq$class.$init$(this);
/* 54 */       IndexedSeqLike.class.$init$(this);
/* 54 */       IndexedSeq.class.$init$(this);
/* 54 */       IndexedSeqLike$class.$init$(this);
/* 54 */       IndexedSeq$class.$init$(this);
/* 54 */       IndexedSeqOptimized.class.$init$(this);
/* 54 */       ViewMkString.class.$init$((ViewMkString)this);
/* 54 */       GenTraversableViewLike.class.$init$((GenTraversableViewLike)this);
/* 54 */       TraversableViewLike.class.$init$((TraversableViewLike)this);
/* 54 */       GenIterableViewLike.class.$init$((GenIterableViewLike)this);
/* 54 */       IterableViewLike.class.$init$((IterableViewLike)this);
/* 54 */       GenSeqViewLike.class.$init$((GenSeqViewLike)this);
/* 54 */       SeqViewLike.class.$init$(this);
/* 54 */       IndexedSeqView$class.$init$(this);
/*    */     }
/*    */     
/*    */     private Object underlying$lzycompute() {
/* 55 */       synchronized (this) {
/* 55 */         if (!this.bitmap$0) {
/* 55 */           this.underlying = (Repr)this.$outer.repr();
/* 55 */           this.bitmap$0 = true;
/*    */         } 
/*    */         /* monitor exit ThisExpression{ObjectType{scala/collection/mutable/IndexedSeqLike$$anon$1}} */
/* 55 */         return this.underlying;
/*    */       } 
/*    */     }
/*    */     
/*    */     public Repr underlying() {
/* 55 */       return this.bitmap$0 ? this.underlying : (Repr)underlying$lzycompute();
/*    */     }
/*    */     
/*    */     public Iterator<A> iterator() {
/* 56 */       return this.$outer.iterator();
/*    */     }
/*    */     
/*    */     public int length() {
/* 57 */       return this.$outer.length();
/*    */     }
/*    */     
/*    */     public A apply(int idx) {
/* 58 */       return (A)this.$outer.apply(idx);
/*    */     }
/*    */     
/*    */     public void update(int idx, Object elem) {
/* 59 */       this.$outer.update(idx, elem);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\IndexedSeqLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */