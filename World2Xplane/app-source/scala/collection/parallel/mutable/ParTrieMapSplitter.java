/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.PartialFunction;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.concurrent.TrieMap;
/*     */ import scala.collection.concurrent.TrieMapIterator;
/*     */ import scala.collection.generic.DelegatedSignalling;
/*     */ import scala.collection.generic.Signalling;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.parallel.AugmentedIterableIterator;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.IterableSplitter;
/*     */ import scala.collection.parallel.ParIterable;
/*     */ import scala.collection.parallel.RemainsIterator;
/*     */ import scala.collection.parallel.SeqSplitter;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\025a!B\001\003\001\031Q!A\005)beR\023\030.Z'baN\003H.\033;uKJT!a\001\003\002\0175,H/\0312mK*\021QAB\001\ta\006\024\030\r\0347fY*\021q\001C\001\013G>dG.Z2uS>t'\"A\005\002\013M\034\027\r\\1\026\007-!\002eE\002\001\031\t\002B!\004\t\023?5\taB\003\002\020\r\005Q1m\0348dkJ\024XM\034;\n\005Eq!a\004+sS\026l\025\r]%uKJ\fGo\034:\021\005M!B\002\001\003\006+\001\021\ra\006\002\002\027\016\001\021C\001\r\035!\tI\"$D\001\t\023\tY\002BA\004O_RD\027N\\4\021\005ei\022B\001\020\t\005\r\te.\037\t\003'\001\"Q!\t\001C\002]\021\021A\026\t\004G\0212S\"\001\003\n\005\025\"!\001E%uKJ\f'\r\\3Ta2LG\017^3s!\021IrEE\020\n\005!B!A\002+va2,'\007\003\005+\001\t\005\t\025!\003,\003\raWM\036\t\00331J!!\f\005\003\007%sG\017\003\0050\001\t\005\t\025!\0031\003\t\031G\017\005\003\016cIy\022B\001\032\017\005\035!&/[3NCBD\001\002\016\001\003\002\003\006I!N\001\t[V\034H/\0238jiB\021\021DN\005\003o!\021qAQ8pY\026\fg\016C\003:\001\021\005!(\001\004=S:LGO\020\013\005wurt\b\005\003=\001IyR\"\001\002\t\013)B\004\031A\026\t\013=B\004\031\001\031\t\013QB\004\031A\033\t\021\005\003\001R1A\005\002\t\013\021\002^8uC2\034\030N_3\026\003-B\001\002\022\001\t\002\003\006KaK\001\013i>$\030\r\\:ju\026\004\003b\002$\001\001\004%\tAQ\001\tSR,'/\031;fI\"9\001\n\001a\001\n\003I\025\001D5uKJ\fG/\0323`I\025\fHC\001&N!\tI2*\003\002M\021\t!QK\\5u\021\035qu)!AA\002-\n1\001\037\0232\021\031\001\006\001)Q\005W\005I\021\016^3sCR,G\r\t\005\006%\002!\tfU\001\f]\026<\030\n^3sCR|'\017\006\003<)ZC\006\"B+R\001\004Y\023\001B0mKZDQaV)A\002A\n1aX2u\021\025I\026\0131\0016\003%yV.^:u\023:LG\017C\003\\\001\021\005C,\001\ntQ>,H\016Z*qY&$h)\036:uQ\026\024XCA/e)\r)dL\032\005\006?j\003\r\001Y\001\005G>dG\016E\002$C\016L!A\031\003\003\027A\013'/\023;fe\006\024G.\032\t\003'\021$Q!\032.C\002]\021\021a\025\005\006Oj\003\raK\001\021a\006\024\030\r\0347fY&\034X\016T3wK2DQ!\033\001\005\002)\f1\001Z;q+\005Y\004\"\0027\001\t\003j\027\001\0028fqR$\022A\n\005\006_\002!\t\001]\001\006gBd\027\016^\013\002cB\031!O\037\022\017\005MDhB\001;x\033\005)(B\001<\027\003\031a$o\\8u}%\t\021\"\003\002z\021\0059\001/Y2lC\036,\027BA>}\005\r\031V-\035\006\003s\"AQA \001\005B}\f\001#[:SK6\f\027N\\5oO\016CW-\0319\026\003UBa!a\001\001\t\003\021\025!\003:f[\006Lg.\0338h\001")
/*     */ public class ParTrieMapSplitter<K, V> extends TrieMapIterator<K, V> implements IterableSplitter<Tuple2<K, V>> {
/*     */   private final TrieMap<K, V> ct;
/*     */   
/*     */   private int totalsize;
/*     */   
/*     */   private int iterated;
/*     */   
/*     */   private Signalling signalDelegate;
/*     */   
/*     */   private volatile boolean bitmap$0;
/*     */   
/*     */   public Signalling signalDelegate() {
/* 123 */     return this.signalDelegate;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void signalDelegate_$eq(Signalling x$1) {
/* 123 */     this.signalDelegate = x$1;
/*     */   }
/*     */   
/*     */   public Seq<IterableSplitter<Tuple2<K, V>>> splitWithSignalling() {
/* 123 */     return IterableSplitter.class.splitWithSignalling(this);
/*     */   }
/*     */   
/*     */   public String buildString(Function1 closure) {
/* 123 */     return IterableSplitter.class.buildString(this, closure);
/*     */   }
/*     */   
/*     */   public String debugInformation() {
/* 123 */     return IterableSplitter.class.debugInformation(this);
/*     */   }
/*     */   
/*     */   public IterableSplitter<Tuple2<K, V>>.Taken newTaken(int until) {
/* 123 */     return IterableSplitter.class.newTaken(this, until);
/*     */   }
/*     */   
/*     */   public <U extends IterableSplitter<Tuple2<K, V>>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/* 123 */     return (U)IterableSplitter.class.newSliceInternal(this, it, from1);
/*     */   }
/*     */   
/*     */   public IterableSplitter<Tuple2<K, V>> take(int n) {
/* 123 */     return IterableSplitter.class.take(this, n);
/*     */   }
/*     */   
/*     */   public IterableSplitter<Tuple2<K, V>> slice(int from1, int until1) {
/* 123 */     return IterableSplitter.class.slice(this, from1, until1);
/*     */   }
/*     */   
/*     */   public <S> IterableSplitter<Tuple2<K, V>>.Mapped<S> map(Function1 f) {
/* 123 */     return IterableSplitter.class.map(this, f);
/*     */   }
/*     */   
/*     */   public <U, PI extends IterableSplitter<U>> IterableSplitter<Tuple2<K, V>>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/* 123 */     return IterableSplitter.class.appendParIterable(this, that);
/*     */   }
/*     */   
/*     */   public <S> IterableSplitter<Tuple2<K, V>>.Zipped<S> zipParSeq(SeqSplitter that) {
/* 123 */     return IterableSplitter.class.zipParSeq(this, that);
/*     */   }
/*     */   
/*     */   public <S, U, R> IterableSplitter<Tuple2<K, V>>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/* 123 */     return IterableSplitter.class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */   }
/*     */   
/*     */   public boolean isAborted() {
/* 123 */     return DelegatedSignalling.class.isAborted((DelegatedSignalling)this);
/*     */   }
/*     */   
/*     */   public void abort() {
/* 123 */     DelegatedSignalling.class.abort((DelegatedSignalling)this);
/*     */   }
/*     */   
/*     */   public int indexFlag() {
/* 123 */     return DelegatedSignalling.class.indexFlag((DelegatedSignalling)this);
/*     */   }
/*     */   
/*     */   public void setIndexFlag(int f) {
/* 123 */     DelegatedSignalling.class.setIndexFlag((DelegatedSignalling)this, f);
/*     */   }
/*     */   
/*     */   public void setIndexFlagIfGreater(int f) {
/* 123 */     DelegatedSignalling.class.setIndexFlagIfGreater((DelegatedSignalling)this, f);
/*     */   }
/*     */   
/*     */   public void setIndexFlagIfLesser(int f) {
/* 123 */     DelegatedSignalling.class.setIndexFlagIfLesser((DelegatedSignalling)this, f);
/*     */   }
/*     */   
/*     */   public int tag() {
/* 123 */     return DelegatedSignalling.class.tag((DelegatedSignalling)this);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/* 123 */     return AugmentedIterableIterator.class.count((AugmentedIterableIterator)this, p);
/*     */   }
/*     */   
/*     */   public <U> U reduce(Function2 op) {
/* 123 */     return (U)AugmentedIterableIterator.class.reduce((AugmentedIterableIterator)this, op);
/*     */   }
/*     */   
/*     */   public <U> U fold(Object z, Function2 op) {
/* 123 */     return (U)AugmentedIterableIterator.class.fold((AugmentedIterableIterator)this, z, op);
/*     */   }
/*     */   
/*     */   public <U> U sum(Numeric num) {
/* 123 */     return (U)AugmentedIterableIterator.class.sum((AugmentedIterableIterator)this, num);
/*     */   }
/*     */   
/*     */   public <U> U product(Numeric num) {
/* 123 */     return (U)AugmentedIterableIterator.class.product((AugmentedIterableIterator)this, num);
/*     */   }
/*     */   
/*     */   public <U> Tuple2<K, V> min(Ordering ord) {
/* 123 */     return (Tuple2<K, V>)AugmentedIterableIterator.class.min((AugmentedIterableIterator)this, ord);
/*     */   }
/*     */   
/*     */   public <U> Tuple2<K, V> max(Ordering ord) {
/* 123 */     return (Tuple2<K, V>)AugmentedIterableIterator.class.max((AugmentedIterableIterator)this, ord);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object array, int from, int len) {
/* 123 */     AugmentedIterableIterator.class.copyToArray((AugmentedIterableIterator)this, array, from, len);
/*     */   }
/*     */   
/*     */   public <U> U reduceLeft(int howmany, Function2 op) {
/* 123 */     return (U)AugmentedIterableIterator.class.reduceLeft((AugmentedIterableIterator)this, howmany, op);
/*     */   }
/*     */   
/*     */   public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/* 123 */     return AugmentedIterableIterator.class.map2combiner((AugmentedIterableIterator)this, f, cb);
/*     */   }
/*     */   
/*     */   public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/* 123 */     return AugmentedIterableIterator.class.collect2combiner((AugmentedIterableIterator)this, pf, cb);
/*     */   }
/*     */   
/*     */   public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/* 123 */     return AugmentedIterableIterator.class.flatmap2combiner((AugmentedIterableIterator)this, f, cb);
/*     */   }
/*     */   
/*     */   public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/* 123 */     return (Bld)AugmentedIterableIterator.class.copy2builder((AugmentedIterableIterator)this, b);
/*     */   }
/*     */   
/*     */   public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/* 123 */     return AugmentedIterableIterator.class.filter2combiner((AugmentedIterableIterator)this, pred, cb);
/*     */   }
/*     */   
/*     */   public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/* 123 */     return AugmentedIterableIterator.class.filterNot2combiner((AugmentedIterableIterator)this, pred, cb);
/*     */   }
/*     */   
/*     */   public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/* 123 */     return AugmentedIterableIterator.class.partition2combiners((AugmentedIterableIterator)this, pred, btrue, bfalse);
/*     */   }
/*     */   
/*     */   public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/* 123 */     return AugmentedIterableIterator.class.take2combiner((AugmentedIterableIterator)this, n, cb);
/*     */   }
/*     */   
/*     */   public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/* 123 */     return AugmentedIterableIterator.class.drop2combiner((AugmentedIterableIterator)this, n, cb);
/*     */   }
/*     */   
/*     */   public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/* 123 */     return AugmentedIterableIterator.class.slice2combiner((AugmentedIterableIterator)this, from, until, cb);
/*     */   }
/*     */   
/*     */   public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/* 123 */     return AugmentedIterableIterator.class.splitAt2combiners((AugmentedIterableIterator)this, at, before, after);
/*     */   }
/*     */   
/*     */   public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/* 123 */     return AugmentedIterableIterator.class.takeWhile2combiner((AugmentedIterableIterator)this, p, cb);
/*     */   }
/*     */   
/*     */   public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/* 123 */     return AugmentedIterableIterator.class.span2combiners((AugmentedIterableIterator)this, p, before, after);
/*     */   }
/*     */   
/*     */   public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/* 123 */     AugmentedIterableIterator.class.scanToArray((AugmentedIterableIterator)this, z, op, array, from);
/*     */   }
/*     */   
/*     */   public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/* 123 */     return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, startValue, op, cb);
/*     */   }
/*     */   
/*     */   public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/* 123 */     return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, howmany, startValue, op, cb);
/*     */   }
/*     */   
/*     */   public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/* 123 */     return AugmentedIterableIterator.class.zip2combiner((AugmentedIterableIterator)this, otherpit, cb);
/*     */   }
/*     */   
/*     */   public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/* 123 */     return AugmentedIterableIterator.class.zipAll2combiner((AugmentedIterableIterator)this, that, thiselem, thatelem, cb);
/*     */   }
/*     */   
/*     */   public ParTrieMapSplitter(int lev, TrieMap<K, V> ct, boolean mustInit) {
/* 123 */     super(
/* 124 */         lev, ct, mustInit);
/*     */     RemainsIterator.class.$init$((RemainsIterator)this);
/*     */     AugmentedIterableIterator.class.$init$((AugmentedIterableIterator)this);
/*     */     DelegatedSignalling.class.$init$((DelegatedSignalling)this);
/*     */     IterableSplitter.class.$init$(this);
/* 129 */     this.iterated = 0;
/*     */   }
/*     */   
/*     */   private int totalsize$lzycompute() {
/*     */     synchronized (this) {
/*     */       if (!this.bitmap$0) {
/*     */         this.totalsize = this.ct.par().size();
/*     */         this.bitmap$0 = true;
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/mutable/ParTrieMapSplitter}} */
/*     */       return this.totalsize;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int totalsize() {
/*     */     return this.bitmap$0 ? this.totalsize : totalsize$lzycompute();
/*     */   }
/*     */   
/*     */   public int iterated() {
/* 129 */     return this.iterated;
/*     */   }
/*     */   
/*     */   public void iterated_$eq(int x$1) {
/* 129 */     this.iterated = x$1;
/*     */   }
/*     */   
/*     */   public ParTrieMapSplitter<K, V> newIterator(int _lev, TrieMap<K, V> _ct, boolean _mustInit) {
/* 131 */     return new ParTrieMapSplitter(_lev, _ct, _mustInit);
/*     */   }
/*     */   
/*     */   public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/* 134 */     int maxsplits = 3 + Integer.highestOneBit(parallelismLevel);
/* 135 */     return (level() < maxsplits);
/*     */   }
/*     */   
/*     */   public ParTrieMapSplitter<K, V> dup() {
/* 139 */     ParTrieMapSplitter<K, V> it = newIterator(0, this.ct, false);
/* 140 */     dupTo(it);
/* 141 */     it.iterated_$eq(iterated());
/* 142 */     return it;
/*     */   }
/*     */   
/*     */   public Tuple2<K, V> next() {
/* 146 */     iterated_$eq(iterated() + 1);
/* 147 */     return super.next();
/*     */   }
/*     */   
/*     */   public Seq<IterableSplitter<Tuple2<K, V>>> split() {
/* 150 */     return subdivide();
/*     */   }
/*     */   
/*     */   public boolean isRemainingCheap() {
/* 152 */     return false;
/*     */   }
/*     */   
/*     */   public int remaining() {
/* 154 */     return totalsize() - iterated();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParTrieMapSplitter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */