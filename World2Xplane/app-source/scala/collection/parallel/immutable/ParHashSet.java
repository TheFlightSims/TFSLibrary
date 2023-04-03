/*     */ package scala.collection.parallel.immutable;
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
/*     */ import scala.collection.generic.Signalling;
/*     */ import scala.collection.immutable.HashSet;
/*     */ import scala.collection.immutable.HashSet$;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.TrieIterator;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.AugmentedIterableIterator;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.IterableSplitter;
/*     */ import scala.collection.parallel.ParIterable;
/*     */ import scala.collection.parallel.ParIterableLike;
/*     */ import scala.collection.parallel.ParIterableLike$ScanLeaf$;
/*     */ import scala.collection.parallel.ParIterableLike$ScanNode$;
/*     */ import scala.collection.parallel.ParSeq;
/*     */ import scala.collection.parallel.ParSet;
/*     */ import scala.collection.parallel.ParSetLike;
/*     */ import scala.collection.parallel.RemainsIterator;
/*     */ import scala.collection.parallel.SeqSplitter;
/*     */ import scala.collection.parallel.Splitter;
/*     */ import scala.collection.parallel.TaskSupport;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005]h\001B\001\003\001-\021!\002U1s\021\006\034\bnU3u\025\t\031A!A\005j[6,H/\0312mK*\021QAB\001\ta\006\024\030\r\0347fY*\021q\001C\001\013G>dG.Z2uS>t'\"A\005\002\013M\034\027\r\\1\004\001U\021AbF\n\007\0015\t\002eJ\031\021\0059yQ\"\001\005\n\005AA!AB!osJ+g\rE\002\023'Ui\021AA\005\003)\t\021a\001U1s'\026$\bC\001\f\030\031\001!Q\001\007\001C\002e\021\021\001V\t\0035u\001\"AD\016\n\005qA!a\002(pi\"Lgn\032\t\003\035yI!a\b\005\003\007\005s\027\020\005\003\"IU1S\"\001\022\013\005\r2\021aB4f]\026\024\030nY\005\003K\t\022!cR3oKJL7\rU1s)\026l\007\017\\1uKB\021!\003\001\t\006Q%*2\006L\007\002\t%\021!\006\002\002\013!\006\0248+\032;MS.,\007c\001\n\001+A\031QfL\013\016\0039R!a\001\004\n\005Ar#a\002%bg\"\034V\r\036\t\003\035IJ!a\r\005\003\031M+'/[1mSj\f'\r\\3\t\021U\002!\021!Q\001\n1\nA\001\036:jK\"1q\007\001C\001\005a\na\001P5oSRtDCA\026:\021\025)d\0071\001-\021\0259\004\001\"\001<)\005Y\003\"B\037\001\t\003r\024!C2p[B\fg.[8o+\005y$c\001!C\013\032!\021\t\001\001@\0051a$/\0324j]\026lWM\034;?!\r\t3IJ\005\003\t\n\022\001cR3oKJL7mQ8na\006t\027n\0348\021\007\0052e%\003\002HE\t\031r)\0328fe&\034\007+\031:D_6\004\030M\\5p]\")\021\n\001C!\025\006)Q-\0349usV\t1\006C\003M\001\021\005Q*\001\005ta2LG\017^3s+\005q\005c\001\025P+%\021\001\013\002\002\021\023R,'/\0312mKN\003H.\033;uKJDQA\025\001\005BM\0131a]3r+\005a\003\"B+\001\t\0031\026A\002\023nS:,8\017\006\002,/\")\001\f\026a\001+\005\tQ\rC\003[\001\021\0051,A\003%a2,8\017\006\002,9\")\001,\027a\001+!)a\f\001C\001?\006A1m\0348uC&t7\017\006\002aGB\021a\"Y\005\003E\"\021qAQ8pY\026\fg\016C\003Y;\002\007Q\003C\003f\001\021\005c-\001\003tSj,W#A4\021\0059A\027BA5\t\005\rIe\016\036\005\006W\002!\t\006\\\001\006e\026,8/Z\013\004[J,Hc\0018xyB!\001f\\9u\023\t\001HA\001\005D_6\024\027N\\3s!\t1\"\017B\003tU\n\007\021DA\001T!\t1R\017B\003wU\n\007\021D\001\003UQ\006$\b\"\002=k\001\004I\030\001B8mI\016\0042A\004>o\023\tY\bB\001\004PaRLwN\034\005\006{*\004\rA\\\001\005]\026<8MB\003\000\001\001\t\tA\001\nQCJD\025m\0355TKRLE/\032:bi>\0248c\001@\016\035\"Q\021Q\001@\003\002\004%\t!a\002\002\rQ\024\030\016^3s+\t\tI\001E\003\002\f\005mQC\004\003\002\016\005]a\002BA\b\003+i!!!\005\013\007\005M!\"\001\004=e>|GOP\005\002\023%\031\021\021\004\005\002\017A\f7m[1hK&!\021QDA\020\005!IE/\032:bi>\024(bAA\r\021!Q\0211\005@\003\002\004%\t!!\n\002\025Q\024\030\016^3s?\022*\027\017\006\003\002(\0055\002c\001\b\002*%\031\0211\006\005\003\tUs\027\016\036\005\013\003_\t\t#!AA\002\005%\021a\001=%c!Q\0211\007@\003\002\003\006K!!\003\002\017Q\024\030\016^3sA!I\021q\007@\003\006\004%\tAZ\001\003gjD\021\"a\017\005\003\005\013\021B4\002\007MT\b\005\003\0048}\022\005\021q\b\013\007\003\003\n)%a\022\021\007\005\rc0D\001\001\021!\t)!!\020A\002\005%\001bBA\034\003{\001\ra\032\005\t\003\027r\b\031!C\001M\006\t\021\016C\005\002Py\004\r\021\"\001\002R\005)\021n\030\023fcR!\021qEA*\021%\ty#!\024\002\002\003\007q\rC\004\002Xy\004\013\025B4\002\005%\004\003BBA.}\022\005Q*A\002ekBDq!a\030\t\023\t\t'A\bekB4%o\\7Ji\026\024\030\r^8s)\021\t\t%a\031\t\021\005\025\024Q\fa\001\003\023\t!!\033;\t\017\005%d\020\"\001\002l\005)1\017\0357jiV\021\021Q\016\t\006\003\027\tyGT\005\005\003c\nyBA\002TKFDq!!\036\t\003\t9(\001\003oKb$H#A\013\t\017\005md\020\"\001\002~\0059\001.Y:OKb$X#\0011\t\r\005\005e\020\"\001g\003%\021X-\\1j]&tw\rK\003\001\003\013\013Y\tE\002\017\003\017K1!!#\t\005A\031VM]5bYZ+'o]5p]VKEIH\001\002\017\035\tyI\001E\001\003#\013!\002U1s\021\006\034\bnU3u!\r\021\0221\023\004\007\003\tA\t!!&\024\013\005M\025qS\031\021\t\005\nIJJ\005\004\0037\023#!\004)beN+GOR1di>\024\030\020C\0048\003'#\t!a(\025\005\005E\005\002CAR\003'#\t!!*\002\0279,woQ8nE&tWM]\013\005\003O\013i+\006\002\002*B1\001f\\AV\003_\0032AFAW\t\031A\022\021\025b\0013A!!\003AAV\021!\t\031,a%\005\004\005U\026\001D2b]\n+\030\016\0343Ge>lW\003BA\\\003\023,\"!!/\021\023\005\nY,a0\002H\006-\027bAA_E\tq1)\0318D_6\024\027N\\3Ge>l\007\003BAa\003\007l!!a%\n\007\005\0257I\001\003D_2d\007c\001\f\002J\0221\001$!-C\002e\001BA\005\001\002H\"A\021qZAJ\t\003\t\t.\001\005ge>lGK]5f+\021\t\031.!7\025\t\005U\0271\034\t\005%\001\t9\016E\002\027\0033$a\001GAg\005\004I\002\002CAo\003\033\004\r!a8\002\003Q\004B!L\030\002X\"Q\0211]AJ\003\003%I!!:\002\027I,\027\r\032*fg>dg/\032\013\003\003O\004B!!;\002t6\021\0211\036\006\005\003[\fy/\001\003mC:<'BAAy\003\021Q\027M^1\n\t\005U\0301\036\002\007\037\nTWm\031;")
/*     */ public class ParHashSet<T> implements ParSet<T>, GenericParTemplate<T, ParHashSet>, ParSetLike<T, ParHashSet<T>, HashSet<T>>, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final HashSet<T> trie;
/*     */   
/*     */   private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */   
/*     */   private volatile ParIterableLike$ScanNode$ ScanNode$module;
/*     */   
/*     */   private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;
/*     */   
/*     */   public String stringPrefix() {
/*  45 */     return ParSet$class.stringPrefix(this);
/*     */   }
/*     */   
/*     */   public <U> ParSet<U> toSet() {
/*  45 */     return ParSet$class.toSet(this);
/*     */   }
/*     */   
/*     */   public ParIterable<T> toIterable() {
/*  45 */     return ParIterable$class.toIterable(this);
/*     */   }
/*     */   
/*     */   public ParSeq<T> toSeq() {
/*  45 */     return ParIterable$class.toSeq(this);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> union(GenSet that) {
/*  45 */     return (ParHashSet<T>)ParSetLike.class.union(this, that);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> diff(GenSet that) {
/*  45 */     return (ParHashSet<T>)ParSetLike.class.diff(this, that);
/*     */   }
/*     */   
/*     */   public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
/*  45 */     return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
/*  45 */     this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
/*     */   }
/*     */   
/*     */   private ParIterableLike$ScanNode$ ScanNode$lzycompute() {
/*  45 */     synchronized (this) {
/*  45 */       if (this.ScanNode$module == null)
/*  45 */         this.ScanNode$module = new ParIterableLike$ScanNode$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/immutable/ParHashSet}} */
/*  45 */       return this.ScanNode$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParIterableLike$ScanNode$ ScanNode() {
/*  45 */     return (this.ScanNode$module == null) ? ScanNode$lzycompute() : this.ScanNode$module;
/*     */   }
/*     */   
/*     */   private ParIterableLike$ScanLeaf$ ScanLeaf$lzycompute() {
/*  45 */     synchronized (this) {
/*  45 */       if (this.ScanLeaf$module == null)
/*  45 */         this.ScanLeaf$module = new ParIterableLike$ScanLeaf$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/immutable/ParHashSet}} */
/*  45 */       return this.ScanLeaf$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParIterableLike$ScanLeaf$ ScanLeaf() {
/*  45 */     return (this.ScanLeaf$module == null) ? ScanLeaf$lzycompute() : this.ScanLeaf$module;
/*     */   }
/*     */   
/*     */   public void initTaskSupport() {
/*  45 */     ParIterableLike.class.initTaskSupport(this);
/*     */   }
/*     */   
/*     */   public TaskSupport tasksupport() {
/*  45 */     return ParIterableLike.class.tasksupport(this);
/*     */   }
/*     */   
/*     */   public void tasksupport_$eq(TaskSupport ts) {
/*  45 */     ParIterableLike.class.tasksupport_$eq(this, ts);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> repr() {
/*  45 */     return (ParHashSet<T>)ParIterableLike.class.repr(this);
/*     */   }
/*     */   
/*     */   public final boolean isTraversableAgain() {
/*  45 */     return ParIterableLike.class.isTraversableAgain(this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/*  45 */     return ParIterableLike.class.hasDefiniteSize(this);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  45 */     return ParIterableLike.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/*  45 */     return ParIterableLike.class.nonEmpty(this);
/*     */   }
/*     */   
/*     */   public T head() {
/*  45 */     return (T)ParIterableLike.class.head(this);
/*     */   }
/*     */   
/*     */   public Option<T> headOption() {
/*  45 */     return ParIterableLike.class.headOption(this);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> tail() {
/*  45 */     return (ParHashSet<T>)ParIterableLike.class.tail(this);
/*     */   }
/*     */   
/*     */   public T last() {
/*  45 */     return (T)ParIterableLike.class.last(this);
/*     */   }
/*     */   
/*     */   public Option<T> lastOption() {
/*  45 */     return ParIterableLike.class.lastOption(this);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> init() {
/*  45 */     return (ParHashSet<T>)ParIterableLike.class.init(this);
/*     */   }
/*     */   
/*     */   public Splitter<T> iterator() {
/*  45 */     return ParIterableLike.class.iterator(this);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> par() {
/*  45 */     return (ParHashSet<T>)ParIterableLike.class.par(this);
/*     */   }
/*     */   
/*     */   public boolean isStrictSplitterCollection() {
/*  45 */     return ParIterableLike.class.isStrictSplitterCollection(this);
/*     */   }
/*     */   
/*     */   public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask tsk) {
/*  45 */     return ParIterableLike.class.task2ops(this, tsk);
/*     */   }
/*     */   
/*     */   public <R> Object wrap(Function0 body) {
/*  45 */     return ParIterableLike.class.wrap(this, body);
/*     */   }
/*     */   
/*     */   public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(DelegatedSignalling it) {
/*  45 */     return ParIterableLike.class.delegatedSignalling2ops(this, it);
/*     */   }
/*     */   
/*     */   public <Elem, To> Object builder2ops(Builder cb) {
/*  45 */     return ParIterableLike.class.builder2ops(this, cb);
/*     */   }
/*     */   
/*     */   public <S, That> Object bf2seq(CanBuildFrom bf) {
/*  45 */     return ParIterableLike.class.bf2seq(this, bf);
/*     */   }
/*     */   
/*     */   public <S, That extends Parallel> ParHashSet<T> sequentially(Function1 b) {
/*  45 */     return (ParHashSet<T>)ParIterableLike.class.sequentially(this, b);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/*  45 */     return ParIterableLike.class.mkString(this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/*  45 */     return ParIterableLike.class.mkString(this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/*  45 */     return ParIterableLike.class.mkString(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  45 */     return ParIterableLike.class.toString(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object other) {
/*  45 */     return ParIterableLike.class.canEqual(this, other);
/*     */   }
/*     */   
/*     */   public <U> U reduce(Function2 op) {
/*  45 */     return (U)ParIterableLike.class.reduce(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceOption(Function2 op) {
/*  45 */     return ParIterableLike.class.reduceOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> U fold(Object z, Function2 op) {
/*  45 */     return (U)ParIterableLike.class.fold(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S aggregate(Object z, Function2 seqop, Function2 combop) {
/*  45 */     return (S)ParIterableLike.class.aggregate(this, z, seqop, combop);
/*     */   }
/*     */   
/*     */   public <S> S foldLeft(Object z, Function2 op) {
/*  45 */     return (S)ParIterableLike.class.foldLeft(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S foldRight(Object z, Function2 op) {
/*  45 */     return (S)ParIterableLike.class.foldRight(this, z, op);
/*     */   }
/*     */   
/*     */   public <U> U reduceLeft(Function2 op) {
/*  45 */     return (U)ParIterableLike.class.reduceLeft(this, op);
/*     */   }
/*     */   
/*     */   public <U> U reduceRight(Function2 op) {
/*  45 */     return (U)ParIterableLike.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceLeftOption(Function2 op) {
/*  45 */     return ParIterableLike.class.reduceLeftOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceRightOption(Function2 op) {
/*  45 */     return ParIterableLike.class.reduceRightOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/*  45 */     ParIterableLike.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/*  45 */     return ParIterableLike.class.count(this, p);
/*     */   }
/*     */   
/*     */   public <U> U sum(Numeric num) {
/*  45 */     return (U)ParIterableLike.class.sum(this, num);
/*     */   }
/*     */   
/*     */   public <U> U product(Numeric num) {
/*  45 */     return (U)ParIterableLike.class.product(this, num);
/*     */   }
/*     */   
/*     */   public <U> T min(Ordering ord) {
/*  45 */     return (T)ParIterableLike.class.min(this, ord);
/*     */   }
/*     */   
/*     */   public <U> T max(Ordering ord) {
/*  45 */     return (T)ParIterableLike.class.max(this, ord);
/*     */   }
/*     */   
/*     */   public <S> T maxBy(Function1 f, Ordering cmp) {
/*  45 */     return (T)ParIterableLike.class.maxBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <S> T minBy(Function1 f, Ordering cmp) {
/*  45 */     return (T)ParIterableLike.class.minBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <S, That> That map(Function1 f, CanBuildFrom bf) {
/*  45 */     return (That)ParIterableLike.class.map(this, f, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  45 */     return (That)ParIterableLike.class.collect(this, pf, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  45 */     return (That)ParIterableLike.class.flatMap(this, f, bf);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 pred) {
/*  45 */     return ParIterableLike.class.forall(this, pred);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 pred) {
/*  45 */     return ParIterableLike.class.exists(this, pred);
/*     */   }
/*     */   
/*     */   public Option<T> find(Function1 pred) {
/*  45 */     return ParIterableLike.class.find(this, pred);
/*     */   }
/*     */   
/*     */   public Object combinerFactory() {
/*  45 */     return ParIterableLike.class.combinerFactory(this);
/*     */   }
/*     */   
/*     */   public <S, That> Object combinerFactory(Function0 cbf) {
/*  45 */     return ParIterableLike.class.combinerFactory(this, cbf);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> filter(Function1 pred) {
/*  45 */     return (ParHashSet<T>)ParIterableLike.class.filter(this, pred);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> filterNot(Function1 pred) {
/*  45 */     return (ParHashSet<T>)ParIterableLike.class.filterNot(this, pred);
/*     */   }
/*     */   
/*     */   public <U, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/*  45 */     return (That)ParIterableLike.class.$plus$plus(this, that, bf);
/*     */   }
/*     */   
/*     */   public Tuple2<ParHashSet<T>, ParHashSet<T>> partition(Function1 pred) {
/*  45 */     return ParIterableLike.class.partition(this, pred);
/*     */   }
/*     */   
/*     */   public <K> ParMap<K, ParHashSet<T>> groupBy(Function1 f) {
/*  45 */     return ParIterableLike.class.groupBy(this, f);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> take(int n) {
/*  45 */     return (ParHashSet<T>)ParIterableLike.class.take(this, n);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> drop(int n) {
/*  45 */     return (ParHashSet<T>)ParIterableLike.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> slice(int unc_from, int unc_until) {
/*  45 */     return (ParHashSet<T>)ParIterableLike.class.slice(this, unc_from, unc_until);
/*     */   }
/*     */   
/*     */   public Tuple2<ParHashSet<T>, ParHashSet<T>> splitAt(int n) {
/*  45 */     return ParIterableLike.class.splitAt(this, n);
/*     */   }
/*     */   
/*     */   public <U, That> That scan(Object z, Function2 op, CanBuildFrom bf) {
/*  45 */     return (That)ParIterableLike.class.scan(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  45 */     return (That)ParIterableLike.class.scanLeft(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  45 */     return (That)ParIterableLike.class.scanRight(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> takeWhile(Function1 pred) {
/*  45 */     return (ParHashSet<T>)ParIterableLike.class.takeWhile(this, pred);
/*     */   }
/*     */   
/*     */   public Tuple2<ParHashSet<T>, ParHashSet<T>> span(Function1 pred) {
/*  45 */     return ParIterableLike.class.span(this, pred);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> dropWhile(Function1 pred) {
/*  45 */     return (ParHashSet<T>)ParIterableLike.class.dropWhile(this, pred);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs) {
/*  45 */     ParIterableLike.class.copyToArray(this, xs);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs, int start) {
/*  45 */     ParIterableLike.class.copyToArray(this, xs, start);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs, int start, int len) {
/*  45 */     ParIterableLike.class.copyToArray(this, xs, start, len);
/*     */   }
/*     */   
/*     */   public <U> boolean sameElements(GenIterable that) {
/*  45 */     return ParIterableLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public <U, S, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  45 */     return (That)ParIterableLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That zipWithIndex(CanBuildFrom bf) {
/*  45 */     return (That)ParIterableLike.class.zipWithIndex(this, bf);
/*     */   }
/*     */   
/*     */   public <S, U, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  45 */     return (That)ParIterableLike.class.zipAll(this, that, thisElem, thatElem, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That toParCollection(Function0 cbf) {
/*  45 */     return (That)ParIterableLike.class.toParCollection(this, cbf);
/*     */   }
/*     */   
/*     */   public <K, V, That> That toParMap(Function0 cbf, Predef$.less.colon.less ev) {
/*  45 */     return (That)ParIterableLike.class.toParMap(this, cbf, ev);
/*     */   }
/*     */   
/*     */   public Object view() {
/*  45 */     return ParIterableLike.class.view(this);
/*     */   }
/*     */   
/*     */   public <U> Object toArray(ClassTag evidence$1) {
/*  45 */     return ParIterableLike.class.toArray(this, evidence$1);
/*     */   }
/*     */   
/*     */   public List<T> toList() {
/*  45 */     return ParIterableLike.class.toList(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<T> toIndexedSeq() {
/*  45 */     return ParIterableLike.class.toIndexedSeq(this);
/*     */   }
/*     */   
/*     */   public Stream<T> toStream() {
/*  45 */     return ParIterableLike.class.toStream(this);
/*     */   }
/*     */   
/*     */   public Iterator<T> toIterator() {
/*  45 */     return ParIterableLike.class.toIterator(this);
/*     */   }
/*     */   
/*     */   public <U> Buffer<U> toBuffer() {
/*  45 */     return ParIterableLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public GenTraversable<T> toTraversable() {
/*  45 */     return ParIterableLike.class.toTraversable(this);
/*     */   }
/*     */   
/*     */   public <K, V> ParMap<K, V> toMap(Predef$.less.colon.less ev) {
/*  45 */     return ParIterableLike.class.toMap(this, ev);
/*     */   }
/*     */   
/*     */   public Vector<T> toVector() {
/*  45 */     return ParIterableLike.class.toVector(this);
/*     */   }
/*     */   
/*     */   public <Col> Col to(CanBuildFrom cbf) {
/*  45 */     return (Col)ParIterableLike.class.to(this, cbf);
/*     */   }
/*     */   
/*     */   public int scanBlockSize() {
/*  45 */     return ParIterableLike.class.scanBlockSize(this);
/*     */   }
/*     */   
/*     */   public <S> S $div$colon(Object z, Function2 op) {
/*  45 */     return (S)ParIterableLike.class.$div$colon(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S $colon$bslash(Object z, Function2 op) {
/*  45 */     return (S)ParIterableLike.class.$colon$bslash(this, z, op);
/*     */   }
/*     */   
/*     */   public String debugInformation() {
/*  45 */     return ParIterableLike.class.debugInformation(this);
/*     */   }
/*     */   
/*     */   public Seq<String> brokenInvariants() {
/*  45 */     return ParIterableLike.class.brokenInvariants(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<String> debugBuffer() {
/*  45 */     return ParIterableLike.class.debugBuffer(this);
/*     */   }
/*     */   
/*     */   public void debugclear() {
/*  45 */     ParIterableLike.class.debugclear(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<String> debuglog(String s) {
/*  45 */     return ParIterableLike.class.debuglog(this, s);
/*     */   }
/*     */   
/*     */   public void printDebugBuffer() {
/*  45 */     ParIterableLike.class.printDebugBuffer(this);
/*     */   }
/*     */   
/*     */   public Combiner<T, ParHashSet<T>> parCombiner() {
/*  45 */     return CustomParallelizable.class.parCombiner((CustomParallelizable)this);
/*     */   }
/*     */   
/*     */   public Builder<T, ParHashSet<T>> newBuilder() {
/*  45 */     return GenericParTemplate.class.newBuilder(this);
/*     */   }
/*     */   
/*     */   public Combiner<T, ParHashSet<T>> newCombiner() {
/*  45 */     return GenericParTemplate.class.newCombiner(this);
/*     */   }
/*     */   
/*     */   public <B> Combiner<B, ParHashSet<B>> genericBuilder() {
/*  45 */     return GenericParTemplate.class.genericBuilder(this);
/*     */   }
/*     */   
/*     */   public <B> Combiner<B, ParHashSet<B>> genericCombiner() {
/*  45 */     return GenericParTemplate.class.genericCombiner(this);
/*     */   }
/*     */   
/*     */   public <A1, A2> Tuple2<ParHashSet<A1>, ParHashSet<A2>> unzip(Function1 asPair) {
/*  45 */     return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*     */   }
/*     */   
/*     */   public <A1, A2, A3> Tuple3<ParHashSet<A1>, ParHashSet<A2>, ParHashSet<A3>> unzip3(Function1 asTriple) {
/*  45 */     return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*     */   }
/*     */   
/*     */   public <B> ParHashSet<B> flatten(Function1 asTraversable) {
/*  45 */     return (ParHashSet<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public <B> ParHashSet<ParHashSet<B>> transpose(Function1 asTraversable) {
/*  45 */     return (ParHashSet<ParHashSet<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public boolean apply(Object elem) {
/*  45 */     return GenSetLike.class.apply((GenSetLike)this, elem);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> intersect(GenSet that) {
/*  45 */     return (ParHashSet<T>)GenSetLike.class.intersect((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> $amp(GenSet that) {
/*  45 */     return (ParHashSet<T>)GenSetLike.class.$amp((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> $bar(GenSet that) {
/*  45 */     return (ParHashSet<T>)GenSetLike.class.$bar((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public ParHashSet<T> $amp$tilde(GenSet that) {
/*  45 */     return (ParHashSet<T>)GenSetLike.class.$amp$tilde((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public boolean subsetOf(GenSet that) {
/*  45 */     return GenSetLike.class.subsetOf((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*  45 */     return GenSetLike.class.equals((GenSetLike)this, that);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  45 */     return GenSetLike.class.hashCode((GenSetLike)this);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZD$sp(double v1) {
/*  45 */     return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDD$sp(double v1) {
/*  45 */     return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFD$sp(double v1) {
/*  45 */     return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcID$sp(double v1) {
/*  45 */     return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJD$sp(double v1) {
/*  45 */     return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVD$sp(double v1) {
/*  45 */     Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZF$sp(float v1) {
/*  45 */     return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDF$sp(float v1) {
/*  45 */     return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFF$sp(float v1) {
/*  45 */     return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIF$sp(float v1) {
/*  45 */     return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJF$sp(float v1) {
/*  45 */     return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVF$sp(float v1) {
/*  45 */     Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZI$sp(int v1) {
/*  45 */     return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDI$sp(int v1) {
/*  45 */     return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFI$sp(int v1) {
/*  45 */     return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcII$sp(int v1) {
/*  45 */     return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJI$sp(int v1) {
/*  45 */     return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVI$sp(int v1) {
/*  45 */     Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public boolean apply$mcZJ$sp(long v1) {
/*  45 */     return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public double apply$mcDJ$sp(long v1) {
/*  45 */     return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public float apply$mcFJ$sp(long v1) {
/*  45 */     return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public int apply$mcIJ$sp(long v1) {
/*  45 */     return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public long apply$mcJJ$sp(long v1) {
/*  45 */     return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public void apply$mcVJ$sp(long v1) {
/*  45 */     Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose(Function1 g) {
/*  45 */     return Function1.class.compose((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*  45 */     return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<T, A> andThen(Function1 g) {
/*  45 */     return Function1.class.andThen((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*  45 */     return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */   }
/*     */   
/*     */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  45 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public ParHashSet(HashSet<T> trie) {
/*  45 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  45 */     Parallelizable.class.$init$((Parallelizable)this);
/*  45 */     Function1.class.$init$((Function1)this);
/*  45 */     GenSetLike.class.$init$((GenSetLike)this);
/*  45 */     GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/*  45 */     GenTraversable.class.$init$((GenTraversable)this);
/*  45 */     GenIterable.class.$init$(this);
/*  45 */     GenericSetTemplate.class.$init$((GenericSetTemplate)this);
/*  45 */     GenSet.class.$init$(this);
/*  45 */     GenericParTemplate.class.$init$(this);
/*  45 */     CustomParallelizable.class.$init$((CustomParallelizable)this);
/*  45 */     ParIterableLike.class.$init$(this);
/*  45 */     ParIterable.class.$init$(this);
/*  45 */     ParSetLike.class.$init$(this);
/*  45 */     ParSet.class.$init$(this);
/*  45 */     ParIterable$class.$init$(this);
/*  45 */     ParSet$class.$init$(this);
/*     */   }
/*     */   
/*     */   public ParHashSet() {
/*  53 */     this(HashSet$.MODULE$.empty());
/*     */   }
/*     */   
/*     */   public GenericCompanion<ParHashSet> companion() {
/*  55 */     return (GenericCompanion<ParHashSet>)ParHashSet$.MODULE$;
/*     */   }
/*     */   
/*     */   public ParHashSet<T> empty() {
/*  57 */     return new ParHashSet();
/*     */   }
/*     */   
/*     */   public IterableSplitter<T> splitter() {
/*  59 */     return new ParHashSetIterator(this, this.trie.iterator(), this.trie.size());
/*     */   }
/*     */   
/*     */   public HashSet<T> seq() {
/*  61 */     return this.trie;
/*     */   }
/*     */   
/*     */   public ParHashSet<T> $minus(Object e) {
/*  63 */     return new ParHashSet(this.trie.$minus(e));
/*     */   }
/*     */   
/*     */   public ParHashSet<T> $plus(Object e) {
/*  65 */     return new ParHashSet(this.trie.$plus(e));
/*     */   }
/*     */   
/*     */   public boolean contains(Object e) {
/*  67 */     return this.trie.contains(e);
/*     */   }
/*     */   
/*     */   public int size() {
/*  69 */     return this.trie.size();
/*     */   }
/*     */   
/*     */   public <S, That> Combiner<S, That> reuse(Option oldc, Combiner<S, That> newc) {
/*     */     Combiner<S, That> combiner;
/*  71 */     if (oldc instanceof Some) {
/*  71 */       Some some = (Some)oldc;
/*  72 */       combiner = (Combiner)some.x();
/*     */     } else {
/*  73 */       if (None$.MODULE$ == null) {
/*  73 */         if (oldc != null)
/*     */           throw new MatchError(oldc); 
/*  73 */       } else if (!None$.MODULE$.equals(oldc)) {
/*     */         throw new MatchError(oldc);
/*     */       } 
/*  73 */       combiner = newc;
/*     */     } 
/*     */     return combiner;
/*     */   }
/*     */   
/*     */   public static <T> ParHashSet<T> fromTrie(HashSet<T> paramHashSet) {
/*     */     return ParHashSet$.MODULE$.fromTrie(paramHashSet);
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
/*     */   public class ParHashSetIterator implements IterableSplitter<T> {
/*     */     private Iterator<T> triter;
/*     */     
/*     */     private final int sz;
/*     */     
/*     */     private int i;
/*     */     
/*     */     private Signalling signalDelegate;
/*     */     
/*     */     public Signalling signalDelegate() {
/*  76 */       return this.signalDelegate;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void signalDelegate_$eq(Signalling x$1) {
/*  76 */       this.signalDelegate = x$1;
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<T>> splitWithSignalling() {
/*  76 */       return IterableSplitter.class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/*  76 */       return IterableSplitter.class.shouldSplitFurther(this, coll, parallelismLevel);
/*     */     }
/*     */     
/*     */     public String buildString(Function1 closure) {
/*  76 */       return IterableSplitter.class.buildString(this, closure);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/*  76 */       return IterableSplitter.class.debugInformation(this);
/*     */     }
/*     */     
/*     */     public IterableSplitter<T>.Taken newTaken(int until) {
/*  76 */       return IterableSplitter.class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public <U extends IterableSplitter<T>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/*  76 */       return (U)IterableSplitter.class.newSliceInternal(this, it, from1);
/*     */     }
/*     */     
/*     */     public IterableSplitter<T> take(int n) {
/*  76 */       return IterableSplitter.class.take(this, n);
/*     */     }
/*     */     
/*     */     public IterableSplitter<T> slice(int from1, int until1) {
/*  76 */       return IterableSplitter.class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<T>.Mapped<S> map(Function1 f) {
/*  76 */       return IterableSplitter.class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends IterableSplitter<U>> IterableSplitter<T>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/*  76 */       return IterableSplitter.class.appendParIterable(this, that);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<T>.Zipped<S> zipParSeq(SeqSplitter that) {
/*  76 */       return IterableSplitter.class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> IterableSplitter<T>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/*  76 */       return IterableSplitter.class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public boolean isAborted() {
/*  76 */       return DelegatedSignalling.class.isAborted((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public void abort() {
/*  76 */       DelegatedSignalling.class.abort((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public int indexFlag() {
/*  76 */       return DelegatedSignalling.class.indexFlag((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public void setIndexFlag(int f) {
/*  76 */       DelegatedSignalling.class.setIndexFlag((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfGreater(int f) {
/*  76 */       DelegatedSignalling.class.setIndexFlagIfGreater((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfLesser(int f) {
/*  76 */       DelegatedSignalling.class.setIndexFlagIfLesser((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public int tag() {
/*  76 */       return DelegatedSignalling.class.tag((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/*  76 */       return AugmentedIterableIterator.class.count((AugmentedIterableIterator)this, p);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/*  76 */       return (U)AugmentedIterableIterator.class.reduce((AugmentedIterableIterator)this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/*  76 */       return (U)AugmentedIterableIterator.class.fold((AugmentedIterableIterator)this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/*  76 */       return (U)AugmentedIterableIterator.class.sum((AugmentedIterableIterator)this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/*  76 */       return (U)AugmentedIterableIterator.class.product((AugmentedIterableIterator)this, num);
/*     */     }
/*     */     
/*     */     public <U> T min(Ordering ord) {
/*  76 */       return (T)AugmentedIterableIterator.class.min((AugmentedIterableIterator)this, ord);
/*     */     }
/*     */     
/*     */     public <U> T max(Ordering ord) {
/*  76 */       return (T)AugmentedIterableIterator.class.max((AugmentedIterableIterator)this, ord);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object array, int from, int len) {
/*  76 */       AugmentedIterableIterator.class.copyToArray((AugmentedIterableIterator)this, array, from, len);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(int howmany, Function2 op) {
/*  76 */       return (U)AugmentedIterableIterator.class.reduceLeft((AugmentedIterableIterator)this, howmany, op);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/*  76 */       return AugmentedIterableIterator.class.map2combiner((AugmentedIterableIterator)this, f, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/*  76 */       return AugmentedIterableIterator.class.collect2combiner((AugmentedIterableIterator)this, pf, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/*  76 */       return AugmentedIterableIterator.class.flatmap2combiner((AugmentedIterableIterator)this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/*  76 */       return (Bld)AugmentedIterableIterator.class.copy2builder((AugmentedIterableIterator)this, b);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/*  76 */       return AugmentedIterableIterator.class.filter2combiner((AugmentedIterableIterator)this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/*  76 */       return AugmentedIterableIterator.class.filterNot2combiner((AugmentedIterableIterator)this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/*  76 */       return AugmentedIterableIterator.class.partition2combiners((AugmentedIterableIterator)this, pred, btrue, bfalse);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/*  76 */       return AugmentedIterableIterator.class.take2combiner((AugmentedIterableIterator)this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/*  76 */       return AugmentedIterableIterator.class.drop2combiner((AugmentedIterableIterator)this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/*  76 */       return AugmentedIterableIterator.class.slice2combiner((AugmentedIterableIterator)this, from, until, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/*  76 */       return AugmentedIterableIterator.class.splitAt2combiners((AugmentedIterableIterator)this, at, before, after);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/*  76 */       return AugmentedIterableIterator.class.takeWhile2combiner((AugmentedIterableIterator)this, p, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/*  76 */       return AugmentedIterableIterator.class.span2combiners((AugmentedIterableIterator)this, p, before, after);
/*     */     }
/*     */     
/*     */     public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/*  76 */       AugmentedIterableIterator.class.scanToArray((AugmentedIterableIterator)this, z, op, array, from);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/*  76 */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/*  76 */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, howmany, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/*  76 */       return AugmentedIterableIterator.class.zip2combiner((AugmentedIterableIterator)this, otherpit, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/*  76 */       return AugmentedIterableIterator.class.zipAll2combiner((AugmentedIterableIterator)this, that, thiselem, thatelem, cb);
/*     */     }
/*     */     
/*     */     public boolean isRemainingCheap() {
/*  76 */       return RemainsIterator.class.isRemainingCheap((RemainsIterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<T> seq() {
/*  76 */       return Iterator.class.seq((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*  76 */       return Iterator.class.isEmpty((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/*  76 */       return Iterator.class.isTraversableAgain((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/*  76 */       return Iterator.class.hasDefiniteSize((Iterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<T> drop(int n) {
/*  76 */       return Iterator.class.drop((Iterator)this, n);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/*  76 */       return Iterator.class.$plus$plus((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/*  76 */       return Iterator.class.flatMap((Iterator)this, f);
/*     */     }
/*     */     
/*     */     public Iterator<T> filter(Function1 p) {
/*  76 */       return Iterator.class.filter((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/*  76 */       return Iterator.class.corresponds((Iterator)this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<T> withFilter(Function1 p) {
/*  76 */       return Iterator.class.withFilter((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Iterator<T> filterNot(Function1 p) {
/*  76 */       return Iterator.class.filterNot((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/*  76 */       return Iterator.class.collect((Iterator)this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/*  76 */       return Iterator.class.scanLeft((Iterator)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/*  76 */       return Iterator.class.scanRight((Iterator)this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<T> takeWhile(Function1 p) {
/*  76 */       return Iterator.class.takeWhile((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<T>, Iterator<T>> partition(Function1 p) {
/*  76 */       return Iterator.class.partition((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<T>, Iterator<T>> span(Function1 p) {
/*  76 */       return Iterator.class.span((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Iterator<T> dropWhile(Function1 p) {
/*  76 */       return Iterator.class.dropWhile((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<T, B>> zip(Iterator that) {
/*  76 */       return Iterator.class.zip((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/*  76 */       return Iterator.class.padTo((Iterator)this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<T, Object>> zipWithIndex() {
/*  76 */       return Iterator.class.zipWithIndex((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/*  76 */       return Iterator.class.zipAll((Iterator)this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  76 */       Iterator.class.foreach((Iterator)this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/*  76 */       return Iterator.class.forall((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/*  76 */       return Iterator.class.exists((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/*  76 */       return Iterator.class.contains((Iterator)this, elem);
/*     */     }
/*     */     
/*     */     public Option<T> find(Function1 p) {
/*  76 */       return Iterator.class.find((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/*  76 */       return Iterator.class.indexWhere((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/*  76 */       return Iterator.class.indexOf((Iterator)this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<T> buffered() {
/*  76 */       return Iterator.class.buffered((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<T>.GroupedIterator<B> grouped(int size) {
/*  76 */       return Iterator.class.grouped((Iterator)this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<T>.GroupedIterator<B> sliding(int size, int step) {
/*  76 */       return Iterator.class.sliding((Iterator)this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/*  76 */       return Iterator.class.length((Iterator)this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<T>, Iterator<T>> duplicate() {
/*  76 */       return Iterator.class.duplicate((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/*  76 */       return Iterator.class.patch((Iterator)this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/*  76 */       return Iterator.class.sameElements((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public Traversable<T> toTraversable() {
/*  76 */       return Iterator.class.toTraversable((Iterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<T> toIterator() {
/*  76 */       return Iterator.class.toIterator((Iterator)this);
/*     */     }
/*     */     
/*     */     public Stream<T> toStream() {
/*  76 */       return Iterator.class.toStream((Iterator)this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  76 */       return Iterator.class.toString((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/*  76 */       return Iterator.class.sliding$default$2((Iterator)this);
/*     */     }
/*     */     
/*     */     public List<T> reversed() {
/*  76 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/*  76 */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/*  76 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/*  76 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/*  76 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/*  76 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/*  76 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/*  76 */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/*  76 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/*  76 */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/*  76 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/*  76 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/*  76 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  76 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> T maxBy(Function1 f, Ordering cmp) {
/*  76 */       return (T)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> T minBy(Function1 f, Ordering cmp) {
/*  76 */       return (T)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/*  76 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/*  76 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/*  76 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/*  76 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<T> toList() {
/*  76 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<T> toIterable() {
/*  76 */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<T> toSeq() {
/*  76 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<T> toIndexedSeq() {
/*  76 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/*  76 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*  76 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<T> toVector() {
/*  76 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/*  76 */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  76 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/*  76 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/*  76 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/*  76 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  76 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/*  76 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/*  76 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  76 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<T> triter() {
/*  76 */       return this.triter;
/*     */     }
/*     */     
/*     */     public void triter_$eq(Iterator<T> x$1) {
/*  76 */       this.triter = x$1;
/*     */     }
/*     */     
/*     */     public int sz() {
/*  76 */       return this.sz;
/*     */     }
/*     */     
/*     */     public ParHashSetIterator(ParHashSet $outer, Iterator<T> triter, int sz) {
/*  76 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  76 */       TraversableOnce.class.$init$((TraversableOnce)this);
/*  76 */       Iterator.class.$init$((Iterator)this);
/*  76 */       RemainsIterator.class.$init$((RemainsIterator)this);
/*  76 */       AugmentedIterableIterator.class.$init$((AugmentedIterableIterator)this);
/*  76 */       DelegatedSignalling.class.$init$((DelegatedSignalling)this);
/*  76 */       IterableSplitter.class.$init$(this);
/*  78 */       this.i = 0;
/*     */     }
/*     */     
/*     */     public int i() {
/*  78 */       return this.i;
/*     */     }
/*     */     
/*     */     public void i_$eq(int x$1) {
/*  78 */       this.i = x$1;
/*     */     }
/*     */     
/*     */     public IterableSplitter<T> dup() {
/*     */       ParHashSetIterator parHashSetIterator;
/*  79 */       Iterator<T> iterator = triter();
/*  80 */       if (iterator instanceof TrieIterator) {
/*  80 */         TrieIterator trieIterator = (TrieIterator)iterator;
/*  81 */         parHashSetIterator = dupFromIterator((Iterator<T>)trieIterator.dupIterator());
/*     */       } else {
/*  83 */         Buffer buff = triter().toBuffer();
/*  84 */         triter_$eq(buff.iterator());
/*  85 */         parHashSetIterator = dupFromIterator(buff.iterator());
/*     */       } 
/*     */       return parHashSetIterator;
/*     */     }
/*     */     
/*     */     private ParHashSetIterator dupFromIterator(Iterator<T> it) {
/*  88 */       ParHashSetIterator phit = new ParHashSetIterator(scala$collection$parallel$immutable$ParHashSet$ParHashSetIterator$$$outer(), it, sz());
/*  89 */       phit.i_$eq(i());
/*  90 */       return phit;
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<T>> split() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: invokevirtual remaining : ()I
/*     */       //   4: iconst_2
/*     */       //   5: if_icmpge -> 37
/*     */       //   8: getstatic scala/collection/Seq$.MODULE$ : Lscala/collection/Seq$;
/*     */       //   11: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */       //   14: iconst_1
/*     */       //   15: anewarray scala/collection/parallel/immutable/ParHashSet$ParHashSetIterator
/*     */       //   18: dup
/*     */       //   19: iconst_0
/*     */       //   20: aload_0
/*     */       //   21: aastore
/*     */       //   22: checkcast [Ljava/lang/Object;
/*     */       //   25: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */       //   28: invokevirtual apply : (Lscala/collection/Seq;)Lscala/collection/GenTraversable;
/*     */       //   31: checkcast scala/collection/Seq
/*     */       //   34: goto -> 355
/*     */       //   37: aload_0
/*     */       //   38: invokevirtual triter : ()Lscala/collection/Iterator;
/*     */       //   41: astore_1
/*     */       //   42: aload_1
/*     */       //   43: instanceof scala/collection/immutable/TrieIterator
/*     */       //   46: ifeq -> 224
/*     */       //   49: aload_1
/*     */       //   50: checkcast scala/collection/immutable/TrieIterator
/*     */       //   53: astore_2
/*     */       //   54: aload_0
/*     */       //   55: invokevirtual remaining : ()I
/*     */       //   58: istore #4
/*     */       //   60: aload_2
/*     */       //   61: invokevirtual split : ()Lscala/Tuple2;
/*     */       //   64: astore #9
/*     */       //   66: aload #9
/*     */       //   68: ifnull -> 214
/*     */       //   71: aload #9
/*     */       //   73: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   76: ifnull -> 214
/*     */       //   79: new scala/Tuple3
/*     */       //   82: dup
/*     */       //   83: aload #9
/*     */       //   85: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   88: checkcast scala/Tuple2
/*     */       //   91: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   94: aload #9
/*     */       //   96: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   99: checkcast scala/Tuple2
/*     */       //   102: invokevirtual _2$mcI$sp : ()I
/*     */       //   105: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */       //   108: aload #9
/*     */       //   110: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   113: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
/*     */       //   116: astore_3
/*     */       //   117: aload_3
/*     */       //   118: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   121: checkcast scala/collection/Iterator
/*     */       //   124: astore #5
/*     */       //   126: aload_3
/*     */       //   127: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   130: invokestatic unboxToInt : (Ljava/lang/Object;)I
/*     */       //   133: istore #6
/*     */       //   135: aload_3
/*     */       //   136: invokevirtual _3 : ()Ljava/lang/Object;
/*     */       //   139: checkcast scala/collection/Iterator
/*     */       //   142: astore #7
/*     */       //   144: iload #4
/*     */       //   146: iload #6
/*     */       //   148: isub
/*     */       //   149: istore #8
/*     */       //   151: getstatic scala/collection/Seq$.MODULE$ : Lscala/collection/Seq$;
/*     */       //   154: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */       //   157: iconst_2
/*     */       //   158: anewarray scala/collection/parallel/immutable/ParHashSet$ParHashSetIterator
/*     */       //   161: dup
/*     */       //   162: iconst_0
/*     */       //   163: new scala/collection/parallel/immutable/ParHashSet$ParHashSetIterator
/*     */       //   166: dup
/*     */       //   167: aload_0
/*     */       //   168: invokevirtual scala$collection$parallel$immutable$ParHashSet$ParHashSetIterator$$$outer : ()Lscala/collection/parallel/immutable/ParHashSet;
/*     */       //   171: aload #5
/*     */       //   173: iload #6
/*     */       //   175: invokespecial <init> : (Lscala/collection/parallel/immutable/ParHashSet;Lscala/collection/Iterator;I)V
/*     */       //   178: aastore
/*     */       //   179: dup
/*     */       //   180: iconst_1
/*     */       //   181: new scala/collection/parallel/immutable/ParHashSet$ParHashSetIterator
/*     */       //   184: dup
/*     */       //   185: aload_0
/*     */       //   186: invokevirtual scala$collection$parallel$immutable$ParHashSet$ParHashSetIterator$$$outer : ()Lscala/collection/parallel/immutable/ParHashSet;
/*     */       //   189: aload #7
/*     */       //   191: iload #8
/*     */       //   193: invokespecial <init> : (Lscala/collection/parallel/immutable/ParHashSet;Lscala/collection/Iterator;I)V
/*     */       //   196: aastore
/*     */       //   197: checkcast [Ljava/lang/Object;
/*     */       //   200: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */       //   203: invokevirtual apply : (Lscala/collection/Seq;)Lscala/collection/GenTraversable;
/*     */       //   206: checkcast scala/collection/Seq
/*     */       //   209: astore #14
/*     */       //   211: goto -> 353
/*     */       //   214: new scala/MatchError
/*     */       //   217: dup
/*     */       //   218: aload #9
/*     */       //   220: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   223: athrow
/*     */       //   224: aload_0
/*     */       //   225: invokevirtual triter : ()Lscala/collection/Iterator;
/*     */       //   228: invokeinterface toBuffer : ()Lscala/collection/mutable/Buffer;
/*     */       //   233: astore #10
/*     */       //   235: aload #10
/*     */       //   237: aload #10
/*     */       //   239: invokeinterface length : ()I
/*     */       //   244: iconst_2
/*     */       //   245: idiv
/*     */       //   246: invokeinterface splitAt : (I)Lscala/Tuple2;
/*     */       //   251: astore #15
/*     */       //   253: aload #15
/*     */       //   255: ifnull -> 356
/*     */       //   258: new scala/Tuple2
/*     */       //   261: dup
/*     */       //   262: aload #15
/*     */       //   264: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   267: aload #15
/*     */       //   269: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   272: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */       //   275: astore #11
/*     */       //   277: aload #11
/*     */       //   279: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   282: checkcast scala/collection/mutable/Buffer
/*     */       //   285: astore #12
/*     */       //   287: aload #11
/*     */       //   289: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   292: checkcast scala/collection/mutable/Buffer
/*     */       //   295: astore #13
/*     */       //   297: getstatic scala/collection/Seq$.MODULE$ : Lscala/collection/Seq$;
/*     */       //   300: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */       //   303: iconst_2
/*     */       //   304: anewarray scala/collection/mutable/Buffer
/*     */       //   307: dup
/*     */       //   308: iconst_0
/*     */       //   309: aload #12
/*     */       //   311: aastore
/*     */       //   312: dup
/*     */       //   313: iconst_1
/*     */       //   314: aload #13
/*     */       //   316: aastore
/*     */       //   317: checkcast [Ljava/lang/Object;
/*     */       //   320: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */       //   323: invokevirtual apply : (Lscala/collection/Seq;)Lscala/collection/GenTraversable;
/*     */       //   326: checkcast scala/collection/TraversableLike
/*     */       //   329: new scala/collection/parallel/immutable/ParHashSet$ParHashSetIterator$$anonfun$split$1
/*     */       //   332: dup
/*     */       //   333: aload_0
/*     */       //   334: invokespecial <init> : (Lscala/collection/parallel/immutable/ParHashSet$ParHashSetIterator;)V
/*     */       //   337: getstatic scala/collection/Seq$.MODULE$ : Lscala/collection/Seq$;
/*     */       //   340: invokevirtual canBuildFrom : ()Lscala/collection/generic/CanBuildFrom;
/*     */       //   343: invokeinterface map : (Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */       //   348: checkcast scala/collection/Seq
/*     */       //   351: astore #14
/*     */       //   353: aload #14
/*     */       //   355: areturn
/*     */       //   356: new scala/MatchError
/*     */       //   359: dup
/*     */       //   360: aload #15
/*     */       //   362: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   365: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #92	-> 0
/*     */       //   #93	-> 42
/*     */       //   #94	-> 54
/*     */       //   #95	-> 60
/*     */       //   #96	-> 144
/*     */       //   #97	-> 151
/*     */       //   #98	-> 163
/*     */       //   #97	-> 179
/*     */       //   #99	-> 181
/*     */       //   #97	-> 200
/*     */       //   #93	-> 209
/*     */       //   #95	-> 214
/*     */       //   #103	-> 224
/*     */       //   #104	-> 235
/*     */       //   #105	-> 297
/*     */       //   #101	-> 351
/*     */       //   #92	-> 353
/*     */       //   #104	-> 356
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	366	0	this	Lscala/collection/parallel/immutable/ParHashSet$ParHashSetIterator;
/*     */       //   60	149	4	previousRemaining	I
/*     */       //   126	83	5	fst	Lscala/collection/Iterator;
/*     */       //   135	74	6	fstlength	I
/*     */       //   144	65	7	snd	Lscala/collection/Iterator;
/*     */       //   151	58	8	sndlength	I
/*     */       //   235	116	10	buff	Lscala/collection/mutable/Buffer;
/*     */       //   287	64	12	fp	Lscala/collection/mutable/Buffer;
/*     */       //   297	54	13	sp	Lscala/collection/mutable/Buffer;
/*     */     }
/*     */     
/*     */     public class ParHashSet$ParHashSetIterator$$anonfun$split$1 extends AbstractFunction1<Buffer<T>, ParHashSetIterator> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final ParHashSet<T>.ParHashSetIterator apply(Buffer b) {
/* 105 */         return new ParHashSet.ParHashSetIterator(this.$outer.scala$collection$parallel$immutable$ParHashSet$ParHashSetIterator$$$outer(), b.iterator(), b.length());
/*     */       }
/*     */       
/*     */       public ParHashSet$ParHashSetIterator$$anonfun$split$1(ParHashSet.ParHashSetIterator $outer) {}
/*     */     }
/*     */     
/*     */     public T next() {
/* 108 */       i_$eq(i() + 1);
/* 109 */       return (T)triter().next();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 112 */       return (i() < sz());
/*     */     }
/*     */     
/*     */     public int remaining() {
/* 114 */       return sz() - i();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParHashSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */