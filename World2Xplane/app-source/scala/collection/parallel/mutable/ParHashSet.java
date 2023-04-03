/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.CustomParallelizable;
/*     */ import scala.collection.DebugUtils$;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenSetLike;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Parallel;
/*     */ import scala.collection.Parallelizable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.CanCombineFrom;
/*     */ import scala.collection.generic.DelegatedSignalling;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericParTemplate;
/*     */ import scala.collection.generic.GenericSetTemplate;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.Shrinkable;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.Cloneable;
/*     */ import scala.collection.mutable.FlatHashTable;
/*     */ import scala.collection.mutable.HashSet;
/*     */ import scala.collection.mutable.Iterable;
/*     */ import scala.collection.mutable.Set;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.IterableSplitter;
/*     */ import scala.collection.parallel.ParIterable;
/*     */ import scala.collection.parallel.ParIterableLike;
/*     */ import scala.collection.parallel.ParIterableLike$ScanLeaf$;
/*     */ import scala.collection.parallel.ParIterableLike$ScanNode$;
/*     */ import scala.collection.parallel.ParSeq;
/*     */ import scala.collection.parallel.ParSet;
/*     */ import scala.collection.parallel.ParSetLike;
/*     */ import scala.collection.parallel.Splitter;
/*     */ import scala.collection.parallel.TaskSupport;
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
/*     */ @ScalaSignature(bytes = "\006\001\005mg\001B\001\003\001-\021!\002U1s\021\006\034\bnU3u\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021\001\0039be\006dG.\0327\013\005\035A\021AC2pY2,7\r^5p]*\t\021\"A\003tG\006d\027m\001\001\026\005192c\002\001\016#\001:\003g\r\t\003\035=i\021\001C\005\003!!\021a!\0218z%\0264\007c\001\n\024+5\t!!\003\002\025\005\t1\001+\031:TKR\004\"AF\f\r\001\021)\001\004\001b\0013\t\tA+\005\002\033;A\021abG\005\0039!\021qAT8uQ&tw\r\005\002\017=%\021q\004\003\002\004\003:L\b\003B\021%+\031j\021A\t\006\003G\031\tqaZ3oKJL7-\003\002&E\t\021r)\0328fe&\034\007+\031:UK6\004H.\031;f!\t\021\002\001E\003\023QUQ3&\003\002*\005\tQ\001+\031:TKRd\025n[3\021\007I\001Q\003E\002-]Ui\021!\f\006\003\007\031I!aL\027\003\017!\0137\017[*fiB\031!#M\013\n\005I\022!\001\005)be\032c\027\r\036%bg\"$\026M\0317f!\tqA'\003\0026\021\ta1+\032:jC2L'0\0312mK\"Aq\007\001B\001B\003%\001(\001\005d_:$XM\034;t!\rID(\006\b\003YiJ!aO\027\002\033\031c\027\r\036%bg\"$\026M\0317f\023\tidH\001\005D_:$XM\034;t\025\tYT\006\003\004A\001\021\005a!Q\001\007y%t\027\016\036 \025\005)\022\005\"B\034@\001\004A\004\"\002!\001\t\003!E#\001\026\t\013\031\003A\021I$\002\023\r|W\016]1oS>tW#\001%\017\005IIu!\002&\003\021\003Y\025A\003)be\"\0137\017[*fiB\021!\003\024\004\006\003\tA\t!T\n\004\031:\033\004cA\021PM%\021\001K\t\002\016!\006\0248+\032;GC\016$xN]=\t\013\001cE\021\001*\025\003-CQ\001\026'\005\004U\013AbY1o\005VLG\016\032$s_6,\"AV1\026\003]\003R!\t-[A\nL!!\027\022\003\035\r\013gnQ8nE&tWM\022:p[B\0211\fX\007\002\031&\021QL\030\002\005\007>dG.\003\002`E\t\001r)\0328fe&\0347i\\7qC:LwN\034\t\003-\005$Q\001G*C\002e\0012A\005\001a\021\025!G\n\"\021f\003)qWm\036\"vS2$WM]\013\003M2,\022a\032\t\005Q&\\W.D\001\005\023\tQGA\001\005D_6\024\027N\\3s!\t1B\016B\003\031G\n\007\021\004E\002\023\001-DQa\034'\005BA\f1B\\3x\007>l'-\0338feV\021\021\017^\013\002eB!\001.[:v!\t1B\017B\003\031]\n\007\021\004E\002\023\001MDqa\036'\002\002\023%\0010A\006sK\006$'+Z:pYZ,G#A=\021\005i|X\"A>\013\005ql\030\001\0027b]\036T\021A`\001\005U\0064\030-C\002\002\002m\024aa\0242kK\016$\bbBA\003\001\021\005\023qA\001\006K6\004H/_\013\002U!9\0211\002\001\005B\0055\021\001C5uKJ\fGo\034:\026\005\005=\001\003BA\t\003'i\021\001\001\004\007\003+\001\001!a\006\003%A\013'\017S1tQN+G/\023;fe\006$xN]\n\005\003'\tI\002\005\003\002\022\005m\021bAA\017c\tA\002+\031:GY\006$\b*Y:i)\006\024G.Z%uKJ\fGo\034:\t\027\005\005\0221\003B\001B\003%\0211E\001\006gR\f'\017\036\t\004\035\005\025\022bAA\024\021\t\031\021J\034;\t\033\005-\0221\003B\001B\003%\0211EA\027\0035IG/\032:bi\026\034XK\034;jY&!\021qFA\016\003\025)h\016^5m\0215\t\031$a\005\003\002\003\006I!a\t\0026\005iAo\034;bY\026cW-\\3oiNLA!a\016\002\034\005IAo\034;bYNL'0\032\005\b\001\006MA\021AA\036)!\ty!!\020\002@\005\005\003\002CA\021\003s\001\r!a\t\t\021\005-\022\021\ba\001\003GA\001\"a\r\002:\001\007\0211\005\005\t\003\013\n\031\002\"\001\002H\005Ya.Z<Ji\026\024\030\r^8s)!\ty!!\023\002L\0055\003\002CA\021\003\007\002\r!a\t\t\021\005=\0221\ta\001\003GA\001\"a\024\002D\001\007\0211E\001\006i>$\030\r\034\005\b\003'\002A\021IA+\003\021\031\030N_3\026\005\005\r\002bBA-\001\021\005\0211L\001\006G2,\027M\035\013\003\003;\0022ADA0\023\r\t\t\007\003\002\005+:LG\017C\004\002f\001!\t%a\032\002\007M,\027/F\001,\021\035\tY\007\001C\001\003[\n\001\002\n9mkN$S-\035\013\005\003#\ty\007C\004\002r\005%\004\031A\013\002\t\025dW-\034\005\b\003k\002A\021AA<\003%!S.\0338vg\022*\027\017\006\003\002\022\005e\004bBA9\003g\002\r!\006\005\b\003{\002A\021IA@\0031\031HO]5oOB\023XMZ5y+\t\t\t\tE\002{\003\007K1!!\"|\005\031\031FO]5oO\"9\021\021\022\001\005\002\005-\025\001C2p]R\f\027N\\:\025\t\0055\0251\023\t\004\035\005=\025bAAI\021\t9!i\\8mK\006t\007bBA9\003\017\003\r!\006\005\b\003/\003A\021AA\007\003!\031\b\017\\5ui\026\024\bbBAN\001\021%\021QT\001\foJLG/Z(cU\026\034G\017\006\003\002^\005}\005\002CAQ\0033\003\r!a)\002\003M\004B!!*\002,6\021\021q\025\006\004\003Sk\030AA5p\023\021\ti+a*\003%=\023'.Z2u\037V$\b/\036;TiJ,\027-\034\005\b\003c\003A\021BAZ\003)\021X-\0313PE*,7\r\036\013\005\003;\n)\f\003\005\0028\006=\006\031AA]\003\tIg\016\005\003\002&\006m\026\002BA_\003O\023\021c\0242kK\016$\030J\0349viN#(/Z1n\021\035\t\t\r\001C!\003\007\f\001\003Z3ck\036LeNZ8s[\006$\030n\0348\026\005\005\025\007\003BAd\003\033t1ADAe\023\r\tY\rC\001\007!J,G-\0324\n\t\005\025\025q\032\006\004\003\027D\001&\002\001\002T\006e\007c\001\b\002V&\031\021q\033\005\003!M+'/[1m-\026\0248/[8o+&#e$A\001")
/*     */ public class ParHashSet<T> implements ParSet<T>, GenericParTemplate<T, ParHashSet>, ParSetLike<T, ParHashSet<T>, HashSet<T>>, ParFlatHashTable<T>, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private transient int _loadFactor;
/*     */   
/*     */   private transient Object[] table;
/*     */   
/*     */   private transient int tableSize;
/*     */   
/*     */   private transient int threshold;
/*     */   
/*     */   private transient int[] sizemap;
/*     */   
/*     */   private transient int seedvalue;
/*     */   
/*     */   private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */   
/*     */   private volatile ParIterableLike$ScanNode$ ScanNode$module;
/*     */   
/*     */   private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;
/*     */   
/*     */   public boolean alwaysInitSizeMap() {
/*  36 */     return ParFlatHashTable$class.alwaysInitSizeMap(this);
/*     */   }
/*     */   
/*     */   public int _loadFactor() {
/*  36 */     return this._loadFactor;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void _loadFactor_$eq(int x$1) {
/*  36 */     this._loadFactor = x$1;
/*     */   }
/*     */   
/*     */   public Object[] table() {
/*  36 */     return this.table;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void table_$eq(Object[] x$1) {
/*  36 */     this.table = x$1;
/*     */   }
/*     */   
/*     */   public int tableSize() {
/*  36 */     return this.tableSize;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void tableSize_$eq(int x$1) {
/*  36 */     this.tableSize = x$1;
/*     */   }
/*     */   
/*     */   public int threshold() {
/*  36 */     return this.threshold;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void threshold_$eq(int x$1) {
/*  36 */     this.threshold = x$1;
/*     */   }
/*     */   
/*     */   public int[] sizemap() {
/*  36 */     return this.sizemap;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void sizemap_$eq(int[] x$1) {
/*  36 */     this.sizemap = x$1;
/*     */   }
/*     */   
/*     */   public int seedvalue() {
/*  36 */     return this.seedvalue;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void seedvalue_$eq(int x$1) {
/*  36 */     this.seedvalue = x$1;
/*     */   }
/*     */   
/*     */   public int capacity(int expectedSize) {
/*  36 */     return FlatHashTable.class.capacity(this, expectedSize);
/*     */   }
/*     */   
/*     */   public int initialSize() {
/*  36 */     return FlatHashTable.class.initialSize(this);
/*     */   }
/*     */   
/*     */   public int randomSeed() {
/*  36 */     return FlatHashTable.class.randomSeed(this);
/*     */   }
/*     */   
/*     */   public int tableSizeSeed() {
/*  36 */     return FlatHashTable.class.tableSizeSeed(this);
/*     */   }
/*     */   
/*     */   public void init(ObjectInputStream in, Function1 f) {
/*  36 */     FlatHashTable.class.init(this, in, f);
/*     */   }
/*     */   
/*     */   public void serializeTo(ObjectOutputStream out) {
/*  36 */     FlatHashTable.class.serializeTo(this, out);
/*     */   }
/*     */   
/*     */   public Option<T> findEntry(Object elem) {
/*  36 */     return FlatHashTable.class.findEntry(this, elem);
/*     */   }
/*     */   
/*     */   public boolean containsEntry(Object elem) {
/*  36 */     return FlatHashTable.class.containsEntry(this, elem);
/*     */   }
/*     */   
/*     */   public boolean addEntry(Object elem) {
/*  36 */     return FlatHashTable.class.addEntry(this, elem);
/*     */   }
/*     */   
/*     */   public Option<T> removeEntry(Object elem) {
/*  36 */     return FlatHashTable.class.removeEntry(this, elem);
/*     */   }
/*     */   
/*     */   public void nnSizeMapAdd(int h) {
/*  36 */     FlatHashTable.class.nnSizeMapAdd(this, h);
/*     */   }
/*     */   
/*     */   public void nnSizeMapRemove(int h) {
/*  36 */     FlatHashTable.class.nnSizeMapRemove(this, h);
/*     */   }
/*     */   
/*     */   public void nnSizeMapReset(int tableLength) {
/*  36 */     FlatHashTable.class.nnSizeMapReset(this, tableLength);
/*     */   }
/*     */   
/*     */   public final int totalSizeMapBuckets() {
/*  36 */     return FlatHashTable.class.totalSizeMapBuckets(this);
/*     */   }
/*     */   
/*     */   public int calcSizeMapSize(int tableLength) {
/*  36 */     return FlatHashTable.class.calcSizeMapSize(this, tableLength);
/*     */   }
/*     */   
/*     */   public void sizeMapInit(int tableLength) {
/*  36 */     FlatHashTable.class.sizeMapInit(this, tableLength);
/*     */   }
/*     */   
/*     */   public void sizeMapInitAndRebuild() {
/*  36 */     FlatHashTable.class.sizeMapInitAndRebuild(this);
/*     */   }
/*     */   
/*     */   public void printSizeMap() {
/*  36 */     FlatHashTable.class.printSizeMap(this);
/*     */   }
/*     */   
/*     */   public void printContents() {
/*  36 */     FlatHashTable.class.printContents(this);
/*     */   }
/*     */   
/*     */   public void sizeMapDisable() {
/*  36 */     FlatHashTable.class.sizeMapDisable(this);
/*     */   }
/*     */   
/*     */   public boolean isSizeMapDefined() {
/*  36 */     return FlatHashTable.class.isSizeMapDefined(this);
/*     */   }
/*     */   
/*     */   public final int index(int hcode) {
/*  36 */     return FlatHashTable.class.index(this, hcode);
/*     */   }
/*     */   
/*     */   public void clearTable() {
/*  36 */     FlatHashTable.class.clearTable(this);
/*     */   }
/*     */   
/*     */   public FlatHashTable.Contents<T> hashTableContents() {
/*  36 */     return FlatHashTable.class.hashTableContents(this);
/*     */   }
/*     */   
/*     */   public void initWithContents(FlatHashTable.Contents c) {
/*  36 */     FlatHashTable.class.initWithContents(this, c);
/*     */   }
/*     */   
/*     */   public final int sizeMapBucketBitSize() {
/*  36 */     return FlatHashTable.HashUtils.class.sizeMapBucketBitSize((FlatHashTable.HashUtils)this);
/*     */   }
/*     */   
/*     */   public final int sizeMapBucketSize() {
/*  36 */     return FlatHashTable.HashUtils.class.sizeMapBucketSize((FlatHashTable.HashUtils)this);
/*     */   }
/*     */   
/*     */   public int elemHashCode(Object elem) {
/*  36 */     return FlatHashTable.HashUtils.class.elemHashCode((FlatHashTable.HashUtils)this, elem);
/*     */   }
/*     */   
/*     */   public final int improve(int hcode, int seed) {
/*  36 */     return FlatHashTable.HashUtils.class.improve((FlatHashTable.HashUtils)this, hcode, seed);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> $plus(Object elem) {
/*  36 */     return (ParHashSet<T>)ParSetLike$class.$plus(this, elem);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> $minus(Object elem) {
/*  36 */     return (ParHashSet<T>)ParSetLike$class.$minus(this, elem);
/*     */   }
/*     */   
/*     */   public Object scala$collection$mutable$Cloneable$$super$clone() {
/*  36 */     return super.clone();
/*     */   }
/*     */   
/*     */   public ParHashSet<T> clone() {
/*  36 */     return (ParHashSet<T>)Cloneable.class.clone(this);
/*     */   }
/*     */   
/*     */   public Shrinkable<T> $minus$eq(Object elem1, Object elem2, Seq elems) {
/*  36 */     return Shrinkable.class.$minus$eq(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Shrinkable<T> $minus$minus$eq(TraversableOnce xs) {
/*  36 */     return Shrinkable.class.$minus$minus$eq(this, xs);
/*     */   }
/*     */   
/*     */   public Growable<T> $plus$eq(Object elem1, Object elem2, Seq elems) {
/*  36 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Growable<T> $plus$plus$eq(TraversableOnce xs) {
/*  36 */     return Growable.class.$plus$plus$eq(this, xs);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> union(GenSet that) {
/*  36 */     return (ParHashSet<T>)ParSetLike.class.union(this, that);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> diff(GenSet that) {
/*  36 */     return (ParHashSet<T>)ParSetLike.class.diff(this, that);
/*     */   }
/*     */   
/*     */   public ParIterable<T> toIterable() {
/*  36 */     return ParIterable$class.toIterable(this);
/*     */   }
/*     */   
/*     */   public ParSeq<T> toSeq() {
/*  36 */     return ParIterable$class.toSeq(this);
/*     */   }
/*     */   
/*     */   public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
/*  36 */     return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
/*  36 */     this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
/*     */   }
/*     */   
/*     */   private ParIterableLike$ScanNode$ ScanNode$lzycompute() {
/*  36 */     synchronized (this) {
/*  36 */       if (this.ScanNode$module == null)
/*  36 */         this.ScanNode$module = new ParIterableLike$ScanNode$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/mutable/ParHashSet}} */
/*  36 */       return this.ScanNode$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParIterableLike$ScanNode$ ScanNode() {
/*  36 */     return (this.ScanNode$module == null) ? ScanNode$lzycompute() : this.ScanNode$module;
/*     */   }
/*     */   
/*     */   private ParIterableLike$ScanLeaf$ ScanLeaf$lzycompute() {
/*  36 */     synchronized (this) {
/*  36 */       if (this.ScanLeaf$module == null)
/*  36 */         this.ScanLeaf$module = new ParIterableLike$ScanLeaf$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/mutable/ParHashSet}} */
/*  36 */       return this.ScanLeaf$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParIterableLike$ScanLeaf$ ScanLeaf() {
/*  36 */     return (this.ScanLeaf$module == null) ? ScanLeaf$lzycompute() : this.ScanLeaf$module;
/*     */   }
/*     */   
/*     */   public void initTaskSupport() {
/*  36 */     ParIterableLike.class.initTaskSupport(this);
/*     */   }
/*     */   
/*     */   public TaskSupport tasksupport() {
/*  36 */     return ParIterableLike.class.tasksupport(this);
/*     */   }
/*     */   
/*     */   public void tasksupport_$eq(TaskSupport ts) {
/*  36 */     ParIterableLike.class.tasksupport_$eq(this, ts);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> repr() {
/*  36 */     return (ParHashSet<T>)ParIterableLike.class.repr(this);
/*     */   }
/*     */   
/*     */   public final boolean isTraversableAgain() {
/*  36 */     return ParIterableLike.class.isTraversableAgain(this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/*  36 */     return ParIterableLike.class.hasDefiniteSize(this);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  36 */     return ParIterableLike.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/*  36 */     return ParIterableLike.class.nonEmpty(this);
/*     */   }
/*     */   
/*     */   public T head() {
/*  36 */     return (T)ParIterableLike.class.head(this);
/*     */   }
/*     */   
/*     */   public Option<T> headOption() {
/*  36 */     return ParIterableLike.class.headOption(this);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> tail() {
/*  36 */     return (ParHashSet<T>)ParIterableLike.class.tail(this);
/*     */   }
/*     */   
/*     */   public T last() {
/*  36 */     return (T)ParIterableLike.class.last(this);
/*     */   }
/*     */   
/*     */   public Option<T> lastOption() {
/*  36 */     return ParIterableLike.class.lastOption(this);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> init() {
/*  36 */     return (ParHashSet<T>)ParIterableLike.class.init(this);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> par() {
/*  36 */     return (ParHashSet<T>)ParIterableLike.class.par(this);
/*     */   }
/*     */   
/*     */   public boolean isStrictSplitterCollection() {
/*  36 */     return ParIterableLike.class.isStrictSplitterCollection(this);
/*     */   }
/*     */   
/*     */   public <S, That> Combiner<S, That> reuse(Option oldc, Combiner newc) {
/*  36 */     return ParIterableLike.class.reuse(this, oldc, newc);
/*     */   }
/*     */   
/*     */   public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask tsk) {
/*  36 */     return ParIterableLike.class.task2ops(this, tsk);
/*     */   }
/*     */   
/*     */   public <R> Object wrap(Function0 body) {
/*  36 */     return ParIterableLike.class.wrap(this, body);
/*     */   }
/*     */   
/*     */   public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(DelegatedSignalling it) {
/*  36 */     return ParIterableLike.class.delegatedSignalling2ops(this, it);
/*     */   }
/*     */   
/*     */   public <Elem, To> Object builder2ops(Builder cb) {
/*  36 */     return ParIterableLike.class.builder2ops(this, cb);
/*     */   }
/*     */   
/*     */   public <S, That> Object bf2seq(CanBuildFrom bf) {
/*  36 */     return ParIterableLike.class.bf2seq(this, bf);
/*     */   }
/*     */   
/*     */   public <S, That extends Parallel> ParHashSet<T> sequentially(Function1 b) {
/*  36 */     return (ParHashSet<T>)ParIterableLike.class.sequentially(this, b);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/*  36 */     return ParIterableLike.class.mkString(this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/*  36 */     return ParIterableLike.class.mkString(this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/*  36 */     return ParIterableLike.class.mkString(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  36 */     return ParIterableLike.class.toString(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object other) {
/*  36 */     return ParIterableLike.class.canEqual(this, other);
/*     */   }
/*     */   
/*     */   public <U> U reduce(Function2 op) {
/*  36 */     return (U)ParIterableLike.class.reduce(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceOption(Function2 op) {
/*  36 */     return ParIterableLike.class.reduceOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> U fold(Object z, Function2 op) {
/*  36 */     return (U)ParIterableLike.class.fold(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S aggregate(Object z, Function2 seqop, Function2 combop) {
/*  36 */     return (S)ParIterableLike.class.aggregate(this, z, seqop, combop);
/*     */   }
/*     */   
/*     */   public <S> S foldLeft(Object z, Function2 op) {
/*  36 */     return (S)ParIterableLike.class.foldLeft(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S foldRight(Object z, Function2 op) {
/*  36 */     return (S)ParIterableLike.class.foldRight(this, z, op);
/*     */   }
/*     */   
/*     */   public <U> U reduceLeft(Function2 op) {
/*  36 */     return (U)ParIterableLike.class.reduceLeft(this, op);
/*     */   }
/*     */   
/*     */   public <U> U reduceRight(Function2 op) {
/*  36 */     return (U)ParIterableLike.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceLeftOption(Function2 op) {
/*  36 */     return ParIterableLike.class.reduceLeftOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceRightOption(Function2 op) {
/*  36 */     return ParIterableLike.class.reduceRightOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/*  36 */     ParIterableLike.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/*  36 */     return ParIterableLike.class.count(this, p);
/*     */   }
/*     */   
/*     */   public <U> U sum(Numeric num) {
/*  36 */     return (U)ParIterableLike.class.sum(this, num);
/*     */   }
/*     */   
/*     */   public <U> U product(Numeric num) {
/*  36 */     return (U)ParIterableLike.class.product(this, num);
/*     */   }
/*     */   
/*     */   public <U> T min(Ordering ord) {
/*  36 */     return (T)ParIterableLike.class.min(this, ord);
/*     */   }
/*     */   
/*     */   public <U> T max(Ordering ord) {
/*  36 */     return (T)ParIterableLike.class.max(this, ord);
/*     */   }
/*     */   
/*     */   public <S> T maxBy(Function1 f, Ordering cmp) {
/*  36 */     return (T)ParIterableLike.class.maxBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <S> T minBy(Function1 f, Ordering cmp) {
/*  36 */     return (T)ParIterableLike.class.minBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <S, That> That map(Function1 f, CanBuildFrom bf) {
/*  36 */     return (That)ParIterableLike.class.map(this, f, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  36 */     return (That)ParIterableLike.class.collect(this, pf, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  36 */     return (That)ParIterableLike.class.flatMap(this, f, bf);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 pred) {
/*  36 */     return ParIterableLike.class.forall(this, pred);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 pred) {
/*  36 */     return ParIterableLike.class.exists(this, pred);
/*     */   }
/*     */   
/*     */   public Option<T> find(Function1 pred) {
/*  36 */     return ParIterableLike.class.find(this, pred);
/*     */   }
/*     */   
/*     */   public Object combinerFactory() {
/*  36 */     return ParIterableLike.class.combinerFactory(this);
/*     */   }
/*     */   
/*     */   public <S, That> Object combinerFactory(Function0 cbf) {
/*  36 */     return ParIterableLike.class.combinerFactory(this, cbf);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> filter(Function1 pred) {
/*  36 */     return (ParHashSet<T>)ParIterableLike.class.filter(this, pred);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> filterNot(Function1 pred) {
/*  36 */     return (ParHashSet<T>)ParIterableLike.class.filterNot(this, pred);
/*     */   }
/*     */   
/*     */   public <U, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/*  36 */     return (That)ParIterableLike.class.$plus$plus(this, that, bf);
/*     */   }
/*     */   
/*     */   public Tuple2<ParHashSet<T>, ParHashSet<T>> partition(Function1 pred) {
/*  36 */     return ParIterableLike.class.partition(this, pred);
/*     */   }
/*     */   
/*     */   public <K> ParMap<K, ParHashSet<T>> groupBy(Function1 f) {
/*  36 */     return ParIterableLike.class.groupBy(this, f);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> take(int n) {
/*  36 */     return (ParHashSet<T>)ParIterableLike.class.take(this, n);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> drop(int n) {
/*  36 */     return (ParHashSet<T>)ParIterableLike.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> slice(int unc_from, int unc_until) {
/*  36 */     return (ParHashSet<T>)ParIterableLike.class.slice(this, unc_from, unc_until);
/*     */   }
/*     */   
/*     */   public Tuple2<ParHashSet<T>, ParHashSet<T>> splitAt(int n) {
/*  36 */     return ParIterableLike.class.splitAt(this, n);
/*     */   }
/*     */   
/*     */   public <U, That> That scan(Object z, Function2 op, CanBuildFrom bf) {
/*  36 */     return (That)ParIterableLike.class.scan(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  36 */     return (That)ParIterableLike.class.scanLeft(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  36 */     return (That)ParIterableLike.class.scanRight(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> takeWhile(Function1 pred) {
/*  36 */     return (ParHashSet<T>)ParIterableLike.class.takeWhile(this, pred);
/*     */   }
/*     */   
/*     */   public Tuple2<ParHashSet<T>, ParHashSet<T>> span(Function1 pred) {
/*  36 */     return ParIterableLike.class.span(this, pred);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> dropWhile(Function1 pred) {
/*  36 */     return (ParHashSet<T>)ParIterableLike.class.dropWhile(this, pred);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs) {
/*  36 */     ParIterableLike.class.copyToArray(this, xs);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs, int start) {
/*  36 */     ParIterableLike.class.copyToArray(this, xs, start);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs, int start, int len) {
/*  36 */     ParIterableLike.class.copyToArray(this, xs, start, len);
/*     */   }
/*     */   
/*     */   public <U> boolean sameElements(GenIterable that) {
/*  36 */     return ParIterableLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public <U, S, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  36 */     return (That)ParIterableLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That zipWithIndex(CanBuildFrom bf) {
/*  36 */     return (That)ParIterableLike.class.zipWithIndex(this, bf);
/*     */   }
/*     */   
/*     */   public <S, U, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  36 */     return (That)ParIterableLike.class.zipAll(this, that, thisElem, thatElem, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That toParCollection(Function0 cbf) {
/*  36 */     return (That)ParIterableLike.class.toParCollection(this, cbf);
/*     */   }
/*     */   
/*     */   public <K, V, That> That toParMap(Function0 cbf, Predef$.less.colon.less ev) {
/*  36 */     return (That)ParIterableLike.class.toParMap(this, cbf, ev);
/*     */   }
/*     */   
/*     */   public Object view() {
/*  36 */     return ParIterableLike.class.view(this);
/*     */   }
/*     */   
/*     */   public <U> Object toArray(ClassTag evidence$1) {
/*  36 */     return ParIterableLike.class.toArray(this, evidence$1);
/*     */   }
/*     */   
/*     */   public List<T> toList() {
/*  36 */     return ParIterableLike.class.toList(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<T> toIndexedSeq() {
/*  36 */     return ParIterableLike.class.toIndexedSeq(this);
/*     */   }
/*     */   
/*     */   public Stream<T> toStream() {
/*  36 */     return ParIterableLike.class.toStream(this);
/*     */   }
/*     */   
/*     */   public Iterator<T> toIterator() {
/*  36 */     return ParIterableLike.class.toIterator(this);
/*     */   }
/*     */   
/*     */   public <U> Buffer<U> toBuffer() {
/*  36 */     return ParIterableLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public GenTraversable<T> toTraversable() {
/*  36 */     return ParIterableLike.class.toTraversable(this);
/*     */   }
/*     */   
/*     */   public <U> ParSet<U> toSet() {
/*  36 */     return ParIterableLike.class.toSet(this);
/*     */   }
/*     */   
/*     */   public <K, V> ParMap<K, V> toMap(Predef$.less.colon.less ev) {
/*  36 */     return ParIterableLike.class.toMap(this, ev);
/*     */   }
/*     */   
/*     */   public Vector<T> toVector() {
/*  36 */     return ParIterableLike.class.toVector(this);
/*     */   }
/*     */   
/*     */   public <Col> Col to(CanBuildFrom cbf) {
/*  36 */     return (Col)ParIterableLike.class.to(this, cbf);
/*     */   }
/*     */   
/*     */   public int scanBlockSize() {
/*  36 */     return ParIterableLike.class.scanBlockSize(this);
/*     */   }
/*     */   
/*     */   public <S> S $div$colon(Object z, Function2 op) {
/*  36 */     return (S)ParIterableLike.class.$div$colon(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S $colon$bslash(Object z, Function2 op) {
/*  36 */     return (S)ParIterableLike.class.$colon$bslash(this, z, op);
/*     */   }
/*     */   
/*     */   public Seq<String> brokenInvariants() {
/*  36 */     return ParIterableLike.class.brokenInvariants(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<String> debugBuffer() {
/*  36 */     return ParIterableLike.class.debugBuffer(this);
/*     */   }
/*     */   
/*     */   public void debugclear() {
/*  36 */     ParIterableLike.class.debugclear(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<String> debuglog(String s) {
/*  36 */     return ParIterableLike.class.debuglog(this, s);
/*     */   }
/*     */   
/*     */   public void printDebugBuffer() {
/*  36 */     ParIterableLike.class.printDebugBuffer(this);
/*     */   }
/*     */   
/*     */   public Combiner<T, ParHashSet<T>> parCombiner() {
/*  36 */     return CustomParallelizable.class.parCombiner((CustomParallelizable)this);
/*     */   }
/*     */   
/*     */   public Builder<T, ParHashSet<T>> newBuilder() {
/*  36 */     return GenericParTemplate.class.newBuilder(this);
/*     */   }
/*     */   
/*     */   public Combiner<T, ParHashSet<T>> newCombiner() {
/*  36 */     return GenericParTemplate.class.newCombiner(this);
/*     */   }
/*     */   
/*     */   public <B> Combiner<B, ParHashSet<B>> genericBuilder() {
/*  36 */     return GenericParTemplate.class.genericBuilder(this);
/*     */   }
/*     */   
/*     */   public <B> Combiner<B, ParHashSet<B>> genericCombiner() {
/*  36 */     return GenericParTemplate.class.genericCombiner(this);
/*     */   }
/*     */   
/*     */   public <A1, A2> Tuple2<ParHashSet<A1>, ParHashSet<A2>> unzip(Function1 asPair) {
/*  36 */     return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*     */   }
/*     */   
/*     */   public <A1, A2, A3> Tuple3<ParHashSet<A1>, ParHashSet<A2>, ParHashSet<A3>> unzip3(Function1 asTriple) {
/*  36 */     return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*     */   }
/*     */   
/*     */   public <B> ParHashSet<B> flatten(Function1 asTraversable) {
/*  36 */     return (ParHashSet<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public <B> ParHashSet<ParHashSet<B>> transpose(Function1 asTraversable) {
/*  36 */     return (ParHashSet<ParHashSet<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public boolean apply(Object elem) {
/*  36 */     return GenSetLike.class.apply(this, elem);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> intersect(GenSet that) {
/*  36 */     return (ParHashSet<T>)GenSetLike.class.intersect(this, that);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> $amp(GenSet that) {
/*  36 */     return (ParHashSet<T>)GenSetLike.class.$amp(this, that);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> $bar(GenSet that) {
/*  36 */     return (ParHashSet<T>)GenSetLike.class.$bar(this, that);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> $amp$tilde(GenSet that) {
/*  36 */     return (ParHashSet<T>)GenSetLike.class.$amp$tilde(this, that);
/*     */   }
/*     */   
/*     */   public boolean subsetOf(GenSet that) {
/*  36 */     return GenSetLike.class.subsetOf(this, that);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*  36 */     return GenSetLike.class.equals(this, that);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  36 */     return GenSetLike.class.hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZD$sp(double v1) {
/*  36 */     return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDD$sp(double v1) {
/*  36 */     return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFD$sp(double v1) {
/*  36 */     return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcID$sp(double v1) {
/*  36 */     return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJD$sp(double v1) {
/*  36 */     return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVD$sp(double v1) {
/*  36 */     Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZF$sp(float v1) {
/*  36 */     return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDF$sp(float v1) {
/*  36 */     return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFF$sp(float v1) {
/*  36 */     return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIF$sp(float v1) {
/*  36 */     return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJF$sp(float v1) {
/*  36 */     return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVF$sp(float v1) {
/*  36 */     Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZI$sp(int v1) {
/*  36 */     return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDI$sp(int v1) {
/*  36 */     return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFI$sp(int v1) {
/*  36 */     return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcII$sp(int v1) {
/*  36 */     return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJI$sp(int v1) {
/*  36 */     return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVI$sp(int v1) {
/*  36 */     Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZJ$sp(long v1) {
/*  36 */     return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDJ$sp(long v1) {
/*  36 */     return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFJ$sp(long v1) {
/*  36 */     return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIJ$sp(long v1) {
/*  36 */     return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJJ$sp(long v1) {
/*  36 */     return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVJ$sp(long v1) {
/*  36 */     Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose(Function1 g) {
/*  36 */     return Function1.class.compose((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*  36 */     return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<T, A> andThen(Function1 g) {
/*  36 */     return Function1.class.andThen((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*  36 */     return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  36 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public ParHashSet(FlatHashTable.Contents<T> contents) {
/*  36 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  36 */     Parallelizable.class.$init$((Parallelizable)this);
/*  36 */     Function1.class.$init$((Function1)this);
/*  36 */     GenSetLike.class.$init$(this);
/*  36 */     GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/*  36 */     GenTraversable.class.$init$((GenTraversable)this);
/*  36 */     GenIterable.class.$init$(this);
/*  36 */     GenericSetTemplate.class.$init$((GenericSetTemplate)this);
/*  36 */     GenSet.class.$init$(this);
/*  36 */     GenericParTemplate.class.$init$(this);
/*  36 */     CustomParallelizable.class.$init$((CustomParallelizable)this);
/*  36 */     ParIterableLike.class.$init$(this);
/*  36 */     ParIterable.class.$init$(this);
/*  36 */     ParIterable$class.$init$(this);
/*  36 */     ParSetLike.class.$init$(this);
/*  36 */     ParSet.class.$init$(this);
/*  36 */     Growable.class.$init$(this);
/*  36 */     Shrinkable.class.$init$(this);
/*  36 */     Cloneable.class.$init$(this);
/*  36 */     ParSetLike$class.$init$(this);
/*  36 */     ParSet$class.$init$(this);
/*  36 */     FlatHashTable.HashUtils.class.$init$((FlatHashTable.HashUtils)this);
/*  36 */     FlatHashTable.class.$init$(this);
/*  36 */     ParFlatHashTable$class.$init$(this);
/*  43 */     initWithContents(contents);
/*     */   }
/*     */   
/*     */   public ParHashSet() {
/*  48 */     this(null);
/*     */   }
/*     */   
/*     */   public ParHashSet$ companion() {
/*  50 */     return ParHashSet$.MODULE$;
/*     */   }
/*     */   
/*     */   public ParHashSet<T> empty() {
/*  52 */     return new ParHashSet();
/*     */   }
/*     */   
/*     */   public ParHashSetIterator iterator() {
/*  54 */     return splitter();
/*     */   }
/*     */   
/*     */   public int size() {
/*  56 */     return tableSize();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  58 */     clearTable();
/*     */   }
/*     */   
/*     */   public HashSet<T> seq() {
/*  60 */     return new HashSet(hashTableContents());
/*     */   }
/*     */   
/*     */   public ParHashSet<T> $plus$eq(Object elem) {
/*  63 */     addEntry((T)elem);
/*  64 */     return this;
/*     */   }
/*     */   
/*     */   public ParHashSet<T> $minus$eq(Object elem) {
/*  68 */     removeEntry((T)elem);
/*  69 */     return this;
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/*  72 */     return "ParHashSet";
/*     */   }
/*     */   
/*     */   public boolean contains(Object elem) {
/*  74 */     return containsEntry((T)elem);
/*     */   }
/*     */   
/*     */   public ParHashSetIterator splitter() {
/*  76 */     return new ParHashSetIterator(this, 0, (table()).length, size());
/*     */   }
/*     */   
/*     */   public class ParHashSetIterator extends ParFlatHashTable<T>.ParFlatHashTableIterator {
/*     */     public ParHashSetIterator(ParHashSet<T> $outer, int start, int iteratesUntil, int totalElements) {
/*  78 */       super($outer, 
/*  79 */           start, iteratesUntil, totalElements);
/*     */     }
/*     */     
/*     */     public ParHashSetIterator newIterator(int start, int until, int total) {
/*  80 */       return new ParHashSetIterator(scala$collection$parallel$mutable$ParHashSet$ParHashSetIterator$$$outer(), start, until, total);
/*     */     }
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream s) {
/*  84 */     serializeTo(s);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) {
/*  88 */     init(in, (Function1<T, BoxedUnit>)new ParHashSet$$anonfun$readObject$1(this));
/*     */   }
/*     */   
/*     */   public class ParHashSet$$anonfun$readObject$1 extends AbstractFunction1<T, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Object x) {}
/*     */     
/*     */     public ParHashSet$$anonfun$readObject$1(ParHashSet $outer) {}
/*     */   }
/*     */   
/*     */   public String debugInformation() {
/*  92 */     return DebugUtils$.MODULE$.buildString(
/*  93 */         (Function1)new ParHashSet$$anonfun$debugInformation$1(this));
/*     */   }
/*     */   
/*     */   public static <T> CanCombineFrom<ParHashSet<?>, T, ParHashSet<T>> canBuildFrom() {
/*     */     return ParHashSet$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static <A> Object setCanBuildFrom() {
/*     */     return ParHashSet$.MODULE$.setCanBuildFrom();
/*     */   }
/*     */   
/*     */   public class ParHashSet$$anonfun$debugInformation$1 extends AbstractFunction1<Function1<Object, BoxedUnit>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public ParHashSet$$anonfun$debugInformation$1(ParHashSet $outer) {}
/*     */     
/*     */     public final void apply(Function1 append) {
/*  94 */       append.apply("Parallel flat hash table set");
/*  95 */       append.apply((new StringBuilder()).append("No. elems: ").append(BoxesRunTime.boxToInteger(this.$outer.tableSize())).toString());
/*  96 */       append.apply((new StringBuilder()).append("Table length: ").append(BoxesRunTime.boxToInteger((this.$outer.table()).length)).toString());
/*  97 */       append.apply("Table: ");
/*  98 */       append.apply(DebugUtils$.MODULE$.arrayString(this.$outer.table(), 0, (this.$outer.table()).length));
/*  99 */       append.apply("Sizemap: ");
/* 100 */       append.apply(DebugUtils$.MODULE$.arrayString(this.$outer.sizemap(), 0, (this.$outer.sizemap()).length));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParHashSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */