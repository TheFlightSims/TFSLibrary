/*    */ package scala.collection.parallel.immutable;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.MatchError;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.BufferedIterator;
/*    */ import scala.collection.CustomParallelizable;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSeqLike;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.IterableLike;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Parallel;
/*    */ import scala.collection.Parallelizable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.DelegatedSignalling;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.GenericParTemplate;
/*    */ import scala.collection.generic.GenericTraversableTemplate;
/*    */ import scala.collection.generic.Signalling;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.collection.immutable.Stream;
/*    */ import scala.collection.immutable.Vector;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.collection.parallel.AugmentedIterableIterator;
/*    */ import scala.collection.parallel.AugmentedSeqIterator;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.IterableSplitter;
/*    */ import scala.collection.parallel.ParIterable;
/*    */ import scala.collection.parallel.ParIterableLike;
/*    */ import scala.collection.parallel.ParIterableView;
/*    */ import scala.collection.parallel.ParSeq;
/*    */ import scala.collection.parallel.ParSeqLike;
/*    */ import scala.collection.parallel.PreciseSplitter;
/*    */ import scala.collection.parallel.RemainsIterator;
/*    */ import scala.collection.parallel.SeqSplitter;
/*    */ import scala.collection.parallel.Splitter;
/*    */ import scala.collection.parallel.TaskSupport;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\005c!B\001\003\001\021Q!A\003*fa\026$\030\016^5p]*\0211\001B\001\nS6lW\017^1cY\026T!!\002\004\002\021A\f'/\0317mK2T!a\002\005\002\025\r|G\016\\3di&|gNC\001\n\003\025\0318-\0317b+\tYacE\002\001\031A\001\"!\004\b\016\003!I!a\004\005\003\r\005s\027PU3g!\r\t\"\003F\007\002\005%\0211C\001\002\007!\006\0248+Z9\021\005U1B\002\001\003\006/\001\021\r!\007\002\002)\016\001\021C\001\016\036!\ti1$\003\002\035\021\t9aj\034;iS:<\007CA\007\037\023\ty\002BA\002B]fD\001\"\t\001\003\002\003\006I\001F\001\005K2,W\016\003\005$\001\t\025\r\021\"\001%\003\031aWM\\4uQV\tQ\005\005\002\016M%\021q\005\003\002\004\023:$\b\002C\025\001\005\003\005\013\021B\023\002\0171,gn\032;iA!)1\006\001C\001Y\0051A(\0338jiz\"2!\f\0300!\r\t\002\001\006\005\006C)\002\r\001\006\005\006G)\002\r!\n\005\006c\001!\tAM\001\006CB\004H.\037\013\003)MBQ\001\016\031A\002\025\n1!\0333y\021\0251\004\001\"\0218\003\r\031X-]\013\0025!)\021\b\001C\001u\0051Q\017\0353bi\026$2AG\036=\021\025!\004\b1\001&\021\025\t\003\b1\001\025\r\021q\004\001A \003\027A\013'/\023;fe\006$xN]\n\004{1\001\005cA!C)5\tA!\003\002D\t\tY1+Z9Ta2LG\017^3s\021!)UH!a\001\n\003!\023!A5\t\021\035k$\0211A\005\002!\013Q![0%KF$\"!\023'\021\0055Q\025BA&\t\005\021)f.\033;\t\01753\025\021!a\001K\005\031\001\020J\031\t\021=k$\021!Q!\n\025\n!!\033\021\t\021Ek$Q1A\005\002\021\nQ!\0368uS2D\001bU\037\003\002\003\006I!J\001\007k:$\030\016\034\021\t\021\005j$\021!Q\001\nQAQaK\037\005\002Y#BaV-[7B\021\001,P\007\002\001!9Q)\026I\001\002\004)\003bB)V!\003\005\r!\n\005\bCU\003\n\0211\001\025\021\025iV\b\"\001%\003%\021X-\\1j]&tw\rC\003`{\021\005\001-A\004iCNtU\r\037;\026\003\005\004\"!\0042\n\005\rD!a\002\"p_2,\027M\034\005\006Kv\"\tAZ\001\005]\026DH\017F\001\025\021\025AW\b\"\001j\003\r!W\017]\013\002/\")1.\020C\001Y\0061\001o\0359mSR$\"!\\=\021\00794\bI\004\002pi:\021\001o]\007\002c*\021!\017G\001\007yI|w\016\036 \n\003%I!!\036\005\002\017A\f7m[1hK&\021q\017\037\002\004'\026\f(BA;\t\021\025Q(\0161\001|\003\025\031\030N_3t!\riA0J\005\003{\"\021!\002\020:fa\026\fG/\0323?\021\031yX\b\"\001\002\002\005)1\017\0357jiV\021\0211\001\t\006\003\013\t9\001Q\007\002\r%\021qOB\004\n\003\027\001\021\021!E\001\003\033\t1\002U1s\023R,'/\031;peB\031\001,a\004\007\021y\002\021\021!E\001\003#\0312!a\004\r\021\035Y\023q\002C\001\003+!\"!!\004\t\025\005e\021qBI\001\n\003\tY\"A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$H%M\013\003\003;Q3!JA\020W\t\t\t\003\005\003\002$\0055RBAA\023\025\021\t9#!\013\002\023Ut7\r[3dW\026$'bAA\026\021\005Q\021M\0348pi\006$\030n\0348\n\t\005=\022Q\005\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007BCA\032\003\037\t\n\021\"\001\002\034\005YB\005\\3tg&t\027\016\036\023he\026\fG/\032:%I\0264\027-\0367uIIB!\"a\016\002\020E\005I\021AA\035\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%gU\021\0211\b\026\004)\005}\001BBA \001\021\005\021.\001\005ta2LG\017^3s\001")
/*    */ public class Repetition<T> implements ParSeq<T> {
/*    */   public final T scala$collection$parallel$immutable$Repetition$$elem;
/*    */   
/*    */   private final int length;
/*    */   
/*    */   private volatile ParIterator$ ParIterator$module;
/*    */   
/*    */   private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
/*    */   
/*    */   private volatile scala.collection.parallel.ParIterableLike$ScanNode$ ScanNode$module;
/*    */   
/*    */   private volatile scala.collection.parallel.ParIterableLike$ScanLeaf$ ScanLeaf$module;
/*    */   
/*    */   public GenericCompanion<ParSeq> companion() {
/* 18 */     return ParSeq$class.companion(this);
/*    */   }
/*    */   
/*    */   public ParSeq<T> toSeq() {
/* 18 */     return ParSeq$class.toSeq(this);
/*    */   }
/*    */   
/*    */   public ParIterable<T> toIterable() {
/* 18 */     return ParIterable$class.toIterable(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 18 */     return ParSeq.class.toString(this);
/*    */   }
/*    */   
/*    */   public String stringPrefix() {
/* 18 */     return ParSeq.class.stringPrefix(this);
/*    */   }
/*    */   
/*    */   public Object scala$collection$parallel$ParSeqLike$$super$zip(GenIterable that, CanBuildFrom bf) {
/* 18 */     return ParIterableLike.class.zip(this, that, bf);
/*    */   }
/*    */   
/*    */   public PreciseSplitter<T> iterator() {
/* 18 */     return ParSeqLike.class.iterator(this);
/*    */   }
/*    */   
/*    */   public int size() {
/* 18 */     return ParSeqLike.class.size(this);
/*    */   }
/*    */   
/*    */   public int segmentLength(Function1 p, int from) {
/* 18 */     return ParSeqLike.class.segmentLength(this, p, from);
/*    */   }
/*    */   
/*    */   public int indexWhere(Function1 p, int from) {
/* 18 */     return ParSeqLike.class.indexWhere(this, p, from);
/*    */   }
/*    */   
/*    */   public int lastIndexWhere(Function1 p, int end) {
/* 18 */     return ParSeqLike.class.lastIndexWhere(this, p, end);
/*    */   }
/*    */   
/*    */   public ParSeq<T> reverse() {
/* 18 */     return (ParSeq<T>)ParSeqLike.class.reverse(this);
/*    */   }
/*    */   
/*    */   public <S, That> That reverseMap(Function1 f, CanBuildFrom bf) {
/* 18 */     return (That)ParSeqLike.class.reverseMap(this, f, bf);
/*    */   }
/*    */   
/*    */   public <S> boolean startsWith(GenSeq that, int offset) {
/* 18 */     return ParSeqLike.class.startsWith(this, that, offset);
/*    */   }
/*    */   
/*    */   public <U> boolean sameElements(GenIterable that) {
/* 18 */     return ParSeqLike.class.sameElements(this, that);
/*    */   }
/*    */   
/*    */   public <S> boolean endsWith(GenSeq that) {
/* 18 */     return ParSeqLike.class.endsWith(this, that);
/*    */   }
/*    */   
/*    */   public <U, That> That patch(int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/* 18 */     return (That)ParSeqLike.class.patch(this, from, patch, replaced, bf);
/*    */   }
/*    */   
/*    */   public <U, That> That updated(int index, Object elem, CanBuildFrom bf) {
/* 18 */     return (That)ParSeqLike.class.updated(this, index, elem, bf);
/*    */   }
/*    */   
/*    */   public <U, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/* 18 */     return (That)ParSeqLike.class.$plus$colon(this, elem, bf);
/*    */   }
/*    */   
/*    */   public <U, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/* 18 */     return (That)ParSeqLike.class.$colon$plus(this, elem, bf);
/*    */   }
/*    */   
/*    */   public <U, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/* 18 */     return (That)ParSeqLike.class.padTo(this, len, elem, bf);
/*    */   }
/*    */   
/*    */   public <U, S, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 18 */     return (That)ParSeqLike.class.zip(this, that, bf);
/*    */   }
/*    */   
/*    */   public <S> boolean corresponds(GenSeq that, Function2 p) {
/* 18 */     return ParSeqLike.class.corresponds(this, that, p);
/*    */   }
/*    */   
/*    */   public <U> ParSeq<T> diff(GenSeq that) {
/* 18 */     return (ParSeq<T>)ParSeqLike.class.diff(this, that);
/*    */   }
/*    */   
/*    */   public <U> ParSeq<T> intersect(GenSeq that) {
/* 18 */     return (ParSeq<T>)ParSeqLike.class.intersect(this, that);
/*    */   }
/*    */   
/*    */   public ParSeq<T> distinct() {
/* 18 */     return (ParSeq<T>)ParSeqLike.class.distinct(this);
/*    */   }
/*    */   
/*    */   public Object view() {
/* 18 */     return ParSeqLike.class.view(this);
/*    */   }
/*    */   
/*    */   public SeqSplitter<T> down(IterableSplitter p) {
/* 18 */     return ParSeqLike.class.down(this, p);
/*    */   }
/*    */   
/*    */   public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
/* 18 */     return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
/* 18 */     this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
/*    */   }
/*    */   
/*    */   private scala.collection.parallel.ParIterableLike$ScanNode$ ScanNode$lzycompute() {
/* 18 */     synchronized (this) {
/* 18 */       if (this.ScanNode$module == null)
/* 18 */         this.ScanNode$module = new scala.collection.parallel.ParIterableLike$ScanNode$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/immutable/Repetition}} */
/* 18 */       return this.ScanNode$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public scala.collection.parallel.ParIterableLike$ScanNode$ ScanNode() {
/* 18 */     return (this.ScanNode$module == null) ? ScanNode$lzycompute() : this.ScanNode$module;
/*    */   }
/*    */   
/*    */   private scala.collection.parallel.ParIterableLike$ScanLeaf$ ScanLeaf$lzycompute() {
/* 18 */     synchronized (this) {
/* 18 */       if (this.ScanLeaf$module == null)
/* 18 */         this.ScanLeaf$module = new scala.collection.parallel.ParIterableLike$ScanLeaf$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/immutable/Repetition}} */
/* 18 */       return this.ScanLeaf$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public scala.collection.parallel.ParIterableLike$ScanLeaf$ ScanLeaf() {
/* 18 */     return (this.ScanLeaf$module == null) ? ScanLeaf$lzycompute() : this.ScanLeaf$module;
/*    */   }
/*    */   
/*    */   public void initTaskSupport() {
/* 18 */     ParIterableLike.class.initTaskSupport(this);
/*    */   }
/*    */   
/*    */   public TaskSupport tasksupport() {
/* 18 */     return ParIterableLike.class.tasksupport(this);
/*    */   }
/*    */   
/*    */   public void tasksupport_$eq(TaskSupport ts) {
/* 18 */     ParIterableLike.class.tasksupport_$eq(this, ts);
/*    */   }
/*    */   
/*    */   public ParSeq<T> repr() {
/* 18 */     return (ParSeq<T>)ParIterableLike.class.repr(this);
/*    */   }
/*    */   
/*    */   public final boolean isTraversableAgain() {
/* 18 */     return ParIterableLike.class.isTraversableAgain(this);
/*    */   }
/*    */   
/*    */   public boolean hasDefiniteSize() {
/* 18 */     return ParIterableLike.class.hasDefiniteSize(this);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 18 */     return ParIterableLike.class.isEmpty(this);
/*    */   }
/*    */   
/*    */   public boolean nonEmpty() {
/* 18 */     return ParIterableLike.class.nonEmpty(this);
/*    */   }
/*    */   
/*    */   public T head() {
/* 18 */     return (T)ParIterableLike.class.head(this);
/*    */   }
/*    */   
/*    */   public Option<T> headOption() {
/* 18 */     return ParIterableLike.class.headOption(this);
/*    */   }
/*    */   
/*    */   public ParSeq<T> tail() {
/* 18 */     return (ParSeq<T>)ParIterableLike.class.tail(this);
/*    */   }
/*    */   
/*    */   public T last() {
/* 18 */     return (T)ParIterableLike.class.last(this);
/*    */   }
/*    */   
/*    */   public Option<T> lastOption() {
/* 18 */     return ParIterableLike.class.lastOption(this);
/*    */   }
/*    */   
/*    */   public ParSeq<T> init() {
/* 18 */     return (ParSeq<T>)ParIterableLike.class.init(this);
/*    */   }
/*    */   
/*    */   public ParSeq<T> par() {
/* 18 */     return (ParSeq<T>)ParIterableLike.class.par(this);
/*    */   }
/*    */   
/*    */   public boolean isStrictSplitterCollection() {
/* 18 */     return ParIterableLike.class.isStrictSplitterCollection(this);
/*    */   }
/*    */   
/*    */   public <S, That> Combiner<S, That> reuse(Option oldc, Combiner newc) {
/* 18 */     return ParIterableLike.class.reuse(this, oldc, newc);
/*    */   }
/*    */   
/*    */   public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask tsk) {
/* 18 */     return ParIterableLike.class.task2ops(this, tsk);
/*    */   }
/*    */   
/*    */   public <R> Object wrap(Function0 body) {
/* 18 */     return ParIterableLike.class.wrap(this, body);
/*    */   }
/*    */   
/*    */   public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(DelegatedSignalling it) {
/* 18 */     return ParIterableLike.class.delegatedSignalling2ops(this, it);
/*    */   }
/*    */   
/*    */   public <Elem, To> Object builder2ops(Builder cb) {
/* 18 */     return ParIterableLike.class.builder2ops(this, cb);
/*    */   }
/*    */   
/*    */   public <S, That> Object bf2seq(CanBuildFrom bf) {
/* 18 */     return ParIterableLike.class.bf2seq(this, bf);
/*    */   }
/*    */   
/*    */   public <S, That extends Parallel> ParSeq<T> sequentially(Function1 b) {
/* 18 */     return (ParSeq<T>)ParIterableLike.class.sequentially(this, b);
/*    */   }
/*    */   
/*    */   public String mkString(String start, String sep, String end) {
/* 18 */     return ParIterableLike.class.mkString(this, start, sep, end);
/*    */   }
/*    */   
/*    */   public String mkString(String sep) {
/* 18 */     return ParIterableLike.class.mkString(this, sep);
/*    */   }
/*    */   
/*    */   public String mkString() {
/* 18 */     return ParIterableLike.class.mkString(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object other) {
/* 18 */     return ParIterableLike.class.canEqual(this, other);
/*    */   }
/*    */   
/*    */   public <U> U reduce(Function2 op) {
/* 18 */     return (U)ParIterableLike.class.reduce(this, op);
/*    */   }
/*    */   
/*    */   public <U> Option<U> reduceOption(Function2 op) {
/* 18 */     return ParIterableLike.class.reduceOption(this, op);
/*    */   }
/*    */   
/*    */   public <U> U fold(Object z, Function2 op) {
/* 18 */     return (U)ParIterableLike.class.fold(this, z, op);
/*    */   }
/*    */   
/*    */   public <S> S aggregate(Object z, Function2 seqop, Function2 combop) {
/* 18 */     return (S)ParIterableLike.class.aggregate(this, z, seqop, combop);
/*    */   }
/*    */   
/*    */   public <S> S foldLeft(Object z, Function2 op) {
/* 18 */     return (S)ParIterableLike.class.foldLeft(this, z, op);
/*    */   }
/*    */   
/*    */   public <S> S foldRight(Object z, Function2 op) {
/* 18 */     return (S)ParIterableLike.class.foldRight(this, z, op);
/*    */   }
/*    */   
/*    */   public <U> U reduceLeft(Function2 op) {
/* 18 */     return (U)ParIterableLike.class.reduceLeft(this, op);
/*    */   }
/*    */   
/*    */   public <U> U reduceRight(Function2 op) {
/* 18 */     return (U)ParIterableLike.class.reduceRight(this, op);
/*    */   }
/*    */   
/*    */   public <U> Option<U> reduceLeftOption(Function2 op) {
/* 18 */     return ParIterableLike.class.reduceLeftOption(this, op);
/*    */   }
/*    */   
/*    */   public <U> Option<U> reduceRightOption(Function2 op) {
/* 18 */     return ParIterableLike.class.reduceRightOption(this, op);
/*    */   }
/*    */   
/*    */   public <U> void foreach(Function1 f) {
/* 18 */     ParIterableLike.class.foreach(this, f);
/*    */   }
/*    */   
/*    */   public int count(Function1 p) {
/* 18 */     return ParIterableLike.class.count(this, p);
/*    */   }
/*    */   
/*    */   public <U> U sum(Numeric num) {
/* 18 */     return (U)ParIterableLike.class.sum(this, num);
/*    */   }
/*    */   
/*    */   public <U> U product(Numeric num) {
/* 18 */     return (U)ParIterableLike.class.product(this, num);
/*    */   }
/*    */   
/*    */   public <U> T min(Ordering ord) {
/* 18 */     return (T)ParIterableLike.class.min(this, ord);
/*    */   }
/*    */   
/*    */   public <U> T max(Ordering ord) {
/* 18 */     return (T)ParIterableLike.class.max(this, ord);
/*    */   }
/*    */   
/*    */   public <S> T maxBy(Function1 f, Ordering cmp) {
/* 18 */     return (T)ParIterableLike.class.maxBy(this, f, cmp);
/*    */   }
/*    */   
/*    */   public <S> T minBy(Function1 f, Ordering cmp) {
/* 18 */     return (T)ParIterableLike.class.minBy(this, f, cmp);
/*    */   }
/*    */   
/*    */   public <S, That> That map(Function1 f, CanBuildFrom bf) {
/* 18 */     return (That)ParIterableLike.class.map(this, f, bf);
/*    */   }
/*    */   
/*    */   public <S, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 18 */     return (That)ParIterableLike.class.collect(this, pf, bf);
/*    */   }
/*    */   
/*    */   public <S, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 18 */     return (That)ParIterableLike.class.flatMap(this, f, bf);
/*    */   }
/*    */   
/*    */   public boolean forall(Function1 pred) {
/* 18 */     return ParIterableLike.class.forall(this, pred);
/*    */   }
/*    */   
/*    */   public boolean exists(Function1 pred) {
/* 18 */     return ParIterableLike.class.exists(this, pred);
/*    */   }
/*    */   
/*    */   public Option<T> find(Function1 pred) {
/* 18 */     return ParIterableLike.class.find(this, pred);
/*    */   }
/*    */   
/*    */   public Object combinerFactory() {
/* 18 */     return ParIterableLike.class.combinerFactory(this);
/*    */   }
/*    */   
/*    */   public <S, That> Object combinerFactory(Function0 cbf) {
/* 18 */     return ParIterableLike.class.combinerFactory(this, cbf);
/*    */   }
/*    */   
/*    */   public ParSeq<T> filter(Function1 pred) {
/* 18 */     return (ParSeq<T>)ParIterableLike.class.filter(this, pred);
/*    */   }
/*    */   
/*    */   public ParSeq<T> filterNot(Function1 pred) {
/* 18 */     return (ParSeq<T>)ParIterableLike.class.filterNot(this, pred);
/*    */   }
/*    */   
/*    */   public <U, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/* 18 */     return (That)ParIterableLike.class.$plus$plus(this, that, bf);
/*    */   }
/*    */   
/*    */   public Tuple2<ParSeq<T>, ParSeq<T>> partition(Function1 pred) {
/* 18 */     return ParIterableLike.class.partition(this, pred);
/*    */   }
/*    */   
/*    */   public <K> ParMap<K, ParSeq<T>> groupBy(Function1 f) {
/* 18 */     return ParIterableLike.class.groupBy(this, f);
/*    */   }
/*    */   
/*    */   public ParSeq<T> take(int n) {
/* 18 */     return (ParSeq<T>)ParIterableLike.class.take(this, n);
/*    */   }
/*    */   
/*    */   public ParSeq<T> drop(int n) {
/* 18 */     return (ParSeq<T>)ParIterableLike.class.drop(this, n);
/*    */   }
/*    */   
/*    */   public ParSeq<T> slice(int unc_from, int unc_until) {
/* 18 */     return (ParSeq<T>)ParIterableLike.class.slice(this, unc_from, unc_until);
/*    */   }
/*    */   
/*    */   public Tuple2<ParSeq<T>, ParSeq<T>> splitAt(int n) {
/* 18 */     return ParIterableLike.class.splitAt(this, n);
/*    */   }
/*    */   
/*    */   public <U, That> That scan(Object z, Function2 op, CanBuildFrom bf) {
/* 18 */     return (That)ParIterableLike.class.scan(this, z, op, bf);
/*    */   }
/*    */   
/*    */   public <S, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 18 */     return (That)ParIterableLike.class.scanLeft(this, z, op, bf);
/*    */   }
/*    */   
/*    */   public <S, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 18 */     return (That)ParIterableLike.class.scanRight(this, z, op, bf);
/*    */   }
/*    */   
/*    */   public ParSeq<T> takeWhile(Function1 pred) {
/* 18 */     return (ParSeq<T>)ParIterableLike.class.takeWhile(this, pred);
/*    */   }
/*    */   
/*    */   public Tuple2<ParSeq<T>, ParSeq<T>> span(Function1 pred) {
/* 18 */     return ParIterableLike.class.span(this, pred);
/*    */   }
/*    */   
/*    */   public ParSeq<T> dropWhile(Function1 pred) {
/* 18 */     return (ParSeq<T>)ParIterableLike.class.dropWhile(this, pred);
/*    */   }
/*    */   
/*    */   public <U> void copyToArray(Object xs) {
/* 18 */     ParIterableLike.class.copyToArray(this, xs);
/*    */   }
/*    */   
/*    */   public <U> void copyToArray(Object xs, int start) {
/* 18 */     ParIterableLike.class.copyToArray(this, xs, start);
/*    */   }
/*    */   
/*    */   public <U> void copyToArray(Object xs, int start, int len) {
/* 18 */     ParIterableLike.class.copyToArray(this, xs, start, len);
/*    */   }
/*    */   
/*    */   public <U, That> That zipWithIndex(CanBuildFrom bf) {
/* 18 */     return (That)ParIterableLike.class.zipWithIndex(this, bf);
/*    */   }
/*    */   
/*    */   public <S, U, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 18 */     return (That)ParIterableLike.class.zipAll(this, that, thisElem, thatElem, bf);
/*    */   }
/*    */   
/*    */   public <U, That> That toParCollection(Function0 cbf) {
/* 18 */     return (That)ParIterableLike.class.toParCollection(this, cbf);
/*    */   }
/*    */   
/*    */   public <K, V, That> That toParMap(Function0 cbf, scala.Predef$.less.colon.less ev) {
/* 18 */     return (That)ParIterableLike.class.toParMap(this, cbf, ev);
/*    */   }
/*    */   
/*    */   public <U> Object toArray(ClassTag evidence$1) {
/* 18 */     return ParIterableLike.class.toArray(this, evidence$1);
/*    */   }
/*    */   
/*    */   public List<T> toList() {
/* 18 */     return ParIterableLike.class.toList(this);
/*    */   }
/*    */   
/*    */   public IndexedSeq<T> toIndexedSeq() {
/* 18 */     return ParIterableLike.class.toIndexedSeq(this);
/*    */   }
/*    */   
/*    */   public Stream<T> toStream() {
/* 18 */     return ParIterableLike.class.toStream(this);
/*    */   }
/*    */   
/*    */   public Iterator<T> toIterator() {
/* 18 */     return ParIterableLike.class.toIterator(this);
/*    */   }
/*    */   
/*    */   public <U> Buffer<U> toBuffer() {
/* 18 */     return ParIterableLike.class.toBuffer(this);
/*    */   }
/*    */   
/*    */   public GenTraversable<T> toTraversable() {
/* 18 */     return ParIterableLike.class.toTraversable(this);
/*    */   }
/*    */   
/*    */   public <U> ParSet<U> toSet() {
/* 18 */     return ParIterableLike.class.toSet(this);
/*    */   }
/*    */   
/*    */   public <K, V> ParMap<K, V> toMap(scala.Predef$.less.colon.less ev) {
/* 18 */     return ParIterableLike.class.toMap(this, ev);
/*    */   }
/*    */   
/*    */   public Vector<T> toVector() {
/* 18 */     return ParIterableLike.class.toVector(this);
/*    */   }
/*    */   
/*    */   public <Col> Col to(CanBuildFrom cbf) {
/* 18 */     return (Col)ParIterableLike.class.to(this, cbf);
/*    */   }
/*    */   
/*    */   public int scanBlockSize() {
/* 18 */     return ParIterableLike.class.scanBlockSize(this);
/*    */   }
/*    */   
/*    */   public <S> S $div$colon(Object z, Function2 op) {
/* 18 */     return (S)ParIterableLike.class.$div$colon(this, z, op);
/*    */   }
/*    */   
/*    */   public <S> S $colon$bslash(Object z, Function2 op) {
/* 18 */     return (S)ParIterableLike.class.$colon$bslash(this, z, op);
/*    */   }
/*    */   
/*    */   public String debugInformation() {
/* 18 */     return ParIterableLike.class.debugInformation(this);
/*    */   }
/*    */   
/*    */   public Seq<String> brokenInvariants() {
/* 18 */     return ParIterableLike.class.brokenInvariants(this);
/*    */   }
/*    */   
/*    */   public ArrayBuffer<String> debugBuffer() {
/* 18 */     return ParIterableLike.class.debugBuffer(this);
/*    */   }
/*    */   
/*    */   public void debugclear() {
/* 18 */     ParIterableLike.class.debugclear(this);
/*    */   }
/*    */   
/*    */   public ArrayBuffer<String> debuglog(String s) {
/* 18 */     return ParIterableLike.class.debuglog(this, s);
/*    */   }
/*    */   
/*    */   public void printDebugBuffer() {
/* 18 */     ParIterableLike.class.printDebugBuffer(this);
/*    */   }
/*    */   
/*    */   public Combiner<T, ParSeq<T>> parCombiner() {
/* 18 */     return CustomParallelizable.class.parCombiner((CustomParallelizable)this);
/*    */   }
/*    */   
/*    */   public Builder<T, ParSeq<T>> newBuilder() {
/* 18 */     return GenericParTemplate.class.newBuilder(this);
/*    */   }
/*    */   
/*    */   public Combiner<T, ParSeq<T>> newCombiner() {
/* 18 */     return GenericParTemplate.class.newCombiner(this);
/*    */   }
/*    */   
/*    */   public <B> Combiner<B, ParSeq<B>> genericBuilder() {
/* 18 */     return GenericParTemplate.class.genericBuilder(this);
/*    */   }
/*    */   
/*    */   public <B> Combiner<B, ParSeq<B>> genericCombiner() {
/* 18 */     return GenericParTemplate.class.genericCombiner(this);
/*    */   }
/*    */   
/*    */   public <A1, A2> Tuple2<ParSeq<A1>, ParSeq<A2>> unzip(Function1 asPair) {
/* 18 */     return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*    */   }
/*    */   
/*    */   public <A1, A2, A3> Tuple3<ParSeq<A1>, ParSeq<A2>, ParSeq<A3>> unzip3(Function1 asTriple) {
/* 18 */     return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*    */   }
/*    */   
/*    */   public <B> ParSeq<B> flatten(Function1 asTraversable) {
/* 18 */     return (ParSeq<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*    */   }
/*    */   
/*    */   public <B> ParSeq<ParSeq<B>> transpose(Function1 asTraversable) {
/* 18 */     return (ParSeq<ParSeq<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*    */   }
/*    */   
/*    */   public boolean isDefinedAt(int idx) {
/* 18 */     return GenSeqLike.class.isDefinedAt((GenSeqLike)this, idx);
/*    */   }
/*    */   
/*    */   public int prefixLength(Function1 p) {
/* 18 */     return GenSeqLike.class.prefixLength((GenSeqLike)this, p);
/*    */   }
/*    */   
/*    */   public int indexWhere(Function1 p) {
/* 18 */     return GenSeqLike.class.indexWhere((GenSeqLike)this, p);
/*    */   }
/*    */   
/*    */   public <B> int indexOf(Object elem) {
/* 18 */     return GenSeqLike.class.indexOf((GenSeqLike)this, elem);
/*    */   }
/*    */   
/*    */   public <B> int indexOf(Object elem, int from) {
/* 18 */     return GenSeqLike.class.indexOf((GenSeqLike)this, elem, from);
/*    */   }
/*    */   
/*    */   public <B> int lastIndexOf(Object elem) {
/* 18 */     return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem);
/*    */   }
/*    */   
/*    */   public <B> int lastIndexOf(Object elem, int end) {
/* 18 */     return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem, end);
/*    */   }
/*    */   
/*    */   public int lastIndexWhere(Function1 p) {
/* 18 */     return GenSeqLike.class.lastIndexWhere((GenSeqLike)this, p);
/*    */   }
/*    */   
/*    */   public <B> boolean startsWith(GenSeq that) {
/* 18 */     return GenSeqLike.class.startsWith((GenSeqLike)this, that);
/*    */   }
/*    */   
/*    */   public <B, That> That union(GenSeq that, CanBuildFrom bf) {
/* 18 */     return (That)GenSeqLike.class.union((GenSeqLike)this, that, bf);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 18 */     return GenSeqLike.class.hashCode((GenSeqLike)this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object that) {
/* 18 */     return GenSeqLike.class.equals((GenSeqLike)this, that);
/*    */   }
/*    */   
/*    */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 18 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*    */   }
/*    */   
/*    */   public int length() {
/* 18 */     return this.length;
/*    */   }
/*    */   
/*    */   public Repetition(Object elem, int length) {
/* 18 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 18 */     Parallelizable.class.$init$((Parallelizable)this);
/* 18 */     GenSeqLike.class.$init$((GenSeqLike)this);
/* 18 */     GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/* 18 */     GenTraversable.class.$init$((GenTraversable)this);
/* 18 */     GenIterable.class.$init$(this);
/* 18 */     GenSeq.class.$init$(this);
/* 18 */     GenericParTemplate.class.$init$(this);
/* 18 */     CustomParallelizable.class.$init$((CustomParallelizable)this);
/* 18 */     ParIterableLike.class.$init$(this);
/* 18 */     ParIterable.class.$init$(this);
/* 18 */     ParSeqLike.class.$init$(this);
/* 18 */     ParSeq.class.$init$(this);
/* 18 */     ParIterable$class.$init$(this);
/* 18 */     ParSeq$class.$init$(this);
/*    */   }
/*    */   
/*    */   public T apply(int idx) {
/* 21 */     if (0 <= idx && idx < length())
/* 21 */       return this.scala$collection$parallel$immutable$Repetition$$elem; 
/* 21 */     throw new IndexOutOfBoundsException(String.valueOf(BoxesRunTime.boxToInteger(idx)));
/*    */   }
/*    */   
/*    */   public scala.runtime.Nothing$ seq() {
/* 22 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public scala.runtime.Nothing$ update(int idx, Object elem) {
/* 23 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   private ParIterator$ ParIterator$lzycompute() {
/* 25 */     synchronized (this) {
/* 25 */       if (this.ParIterator$module == null)
/* 25 */         this.ParIterator$module = new ParIterator$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/immutable/Repetition}} */
/* 25 */       return this.ParIterator$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public ParIterator$ ParIterator() {
/* 25 */     return (this.ParIterator$module == null) ? ParIterator$lzycompute() : this.ParIterator$module;
/*    */   }
/*    */   
/*    */   public class ParIterator$ {
/*    */     public int $lessinit$greater$default$1() {
/* 25 */       return 0;
/*    */     }
/*    */     
/*    */     public int $lessinit$greater$default$2() {
/* 25 */       return this.$outer.length();
/*    */     }
/*    */     
/*    */     public T $lessinit$greater$default$3() {
/* 25 */       return this.$outer.scala$collection$parallel$immutable$Repetition$$elem;
/*    */     }
/*    */     
/*    */     public ParIterator$(Repetition $outer) {}
/*    */     
/*    */     public class Repetition$ParIterator$$anonfun$1 extends AbstractFunction2.mcIII.sp implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final int apply(int x$1, int x$2) {
/* 31 */         return x$1 + x$2;
/*    */       }
/*    */       
/*    */       public int apply$mcIII$sp(int x$1, int x$2) {
/* 31 */         return x$1 + x$2;
/*    */       }
/*    */       
/*    */       public Repetition$ParIterator$$anonfun$1(Repetition.ParIterator $outer) {}
/*    */     }
/*    */     
/*    */     public class Repetition$ParIterator$$anonfun$psplit$1 extends AbstractFunction1<Tuple2<Object, Object>, Object> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final boolean apply(Tuple2 check$ifrefutable$1) {
/*    */         boolean bool;
/* 32 */         if (check$ifrefutable$1 != null) {
/* 32 */           bool = true;
/*    */         } else {
/* 32 */           bool = false;
/*    */         } 
/* 32 */         return bool;
/*    */       }
/*    */       
/*    */       public Repetition$ParIterator$$anonfun$psplit$1(Repetition.ParIterator $outer) {}
/*    */     }
/*    */     
/*    */     public class Repetition$ParIterator$$anonfun$psplit$2 extends AbstractFunction1<Tuple2<Object, Object>, Repetition<T>.ParIterator> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Repetition<T>.ParIterator apply(Tuple2 x$3) {
/* 32 */         if (x$3 != null) {
/* 32 */           int i = this.$outer.i() + x$3._2$mcI$sp();
/* 32 */           scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 32 */           return new Repetition.ParIterator(this.$outer.scala$collection$parallel$immutable$Repetition$ParIterator$$$outer(), this.$outer.i() + x$3._1$mcI$sp(), scala.runtime.RichInt$.MODULE$.min$extension(i, this.$outer.until()), this.$outer.scala$collection$parallel$immutable$Repetition$ParIterator$$elem);
/*    */         } 
/* 32 */         throw new MatchError(x$3);
/*    */       }
/*    */       
/*    */       public Repetition$ParIterator$$anonfun$psplit$2(Repetition.ParIterator $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class ParIterator implements SeqSplitter<T> {
/*    */     private int i;
/*    */     
/*    */     private final int until;
/*    */     
/*    */     public final T scala$collection$parallel$immutable$Repetition$ParIterator$$elem;
/*    */     
/*    */     private Signalling signalDelegate;
/*    */     
/*    */     public Seq<SeqSplitter<T>> splitWithSignalling() {
/*    */       return SeqSplitter.class.splitWithSignalling(this);
/*    */     }
/*    */     
/*    */     public Seq<SeqSplitter<T>> psplitWithSignalling(Seq sizes) {
/*    */       return SeqSplitter.class.psplitWithSignalling(this, sizes);
/*    */     }
/*    */     
/*    */     public SeqSplitter<T>.Taken newTaken(int until) {
/*    */       return SeqSplitter.class.newTaken(this, until);
/*    */     }
/*    */     
/*    */     public SeqSplitter<T> take(int n) {
/*    */       return SeqSplitter.class.take(this, n);
/*    */     }
/*    */     
/*    */     public SeqSplitter<T> slice(int from1, int until1) {
/*    */       return SeqSplitter.class.slice(this, from1, until1);
/*    */     }
/*    */     
/*    */     public <S> SeqSplitter<T>.Mapped<S> map(Function1 f) {
/*    */       return SeqSplitter.class.map(this, f);
/*    */     }
/*    */     
/*    */     public <U, PI extends SeqSplitter<U>> SeqSplitter<T>.Appended<U, PI> appendParSeq(SeqSplitter that) {
/*    */       return SeqSplitter.class.appendParSeq(this, that);
/*    */     }
/*    */     
/*    */     public <S> SeqSplitter<T>.Zipped<S> zipParSeq(SeqSplitter that) {
/*    */       return SeqSplitter.class.zipParSeq(this, that);
/*    */     }
/*    */     
/*    */     public <S, U, R> SeqSplitter<T>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/*    */       return SeqSplitter.class.zipAllParSeq(this, that, thisElem, thatElem);
/*    */     }
/*    */     
/*    */     public SeqSplitter<T> reverse() {
/*    */       return SeqSplitter.class.reverse(this);
/*    */     }
/*    */     
/*    */     public <U> SeqSplitter<T>.Patched<U> patchParSeq(int from, SeqSplitter patchElems, int replaced) {
/*    */       return SeqSplitter.class.patchParSeq(this, from, patchElems, replaced);
/*    */     }
/*    */     
/*    */     public int prefixLength(Function1 pred) {
/*    */       return AugmentedSeqIterator.class.prefixLength((AugmentedSeqIterator)this, pred);
/*    */     }
/*    */     
/*    */     public int indexWhere(Function1 pred) {
/*    */       return AugmentedSeqIterator.class.indexWhere((AugmentedSeqIterator)this, pred);
/*    */     }
/*    */     
/*    */     public int lastIndexWhere(Function1 pred) {
/*    */       return AugmentedSeqIterator.class.lastIndexWhere((AugmentedSeqIterator)this, pred);
/*    */     }
/*    */     
/*    */     public <S> boolean corresponds(Function2 corr, Iterator that) {
/*    */       return AugmentedSeqIterator.class.corresponds((AugmentedSeqIterator)this, corr, that);
/*    */     }
/*    */     
/*    */     public <U, This> Combiner<U, This> reverse2combiner(Combiner cb) {
/*    */       return AugmentedSeqIterator.class.reverse2combiner((AugmentedSeqIterator)this, cb);
/*    */     }
/*    */     
/*    */     public <S, That> Combiner<S, That> reverseMap2combiner(Function1 f, Combiner cb) {
/*    */       return AugmentedSeqIterator.class.reverseMap2combiner((AugmentedSeqIterator)this, f, cb);
/*    */     }
/*    */     
/*    */     public <U, That> Combiner<U, That> updated2combiner(int index, Object elem, Combiner cb) {
/*    */       return AugmentedSeqIterator.class.updated2combiner((AugmentedSeqIterator)this, index, elem, cb);
/*    */     }
/*    */     
/*    */     public Signalling signalDelegate() {
/*    */       return this.signalDelegate;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void signalDelegate_$eq(Signalling x$1) {
/*    */       this.signalDelegate = x$1;
/*    */     }
/*    */     
/*    */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/*    */       return IterableSplitter.class.shouldSplitFurther((IterableSplitter)this, coll, parallelismLevel);
/*    */     }
/*    */     
/*    */     public String buildString(Function1 closure) {
/*    */       return IterableSplitter.class.buildString((IterableSplitter)this, closure);
/*    */     }
/*    */     
/*    */     public String debugInformation() {
/*    */       return IterableSplitter.class.debugInformation((IterableSplitter)this);
/*    */     }
/*    */     
/*    */     public <U extends IterableSplitter<T>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/*    */       return (U)IterableSplitter.class.newSliceInternal((IterableSplitter)this, it, from1);
/*    */     }
/*    */     
/*    */     public <U, PI extends IterableSplitter<U>> IterableSplitter<T>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/*    */       return IterableSplitter.class.appendParIterable((IterableSplitter)this, that);
/*    */     }
/*    */     
/*    */     public boolean isAborted() {
/*    */       return DelegatedSignalling.class.isAborted((DelegatedSignalling)this);
/*    */     }
/*    */     
/*    */     public void abort() {
/*    */       DelegatedSignalling.class.abort((DelegatedSignalling)this);
/*    */     }
/*    */     
/*    */     public int indexFlag() {
/*    */       return DelegatedSignalling.class.indexFlag((DelegatedSignalling)this);
/*    */     }
/*    */     
/*    */     public void setIndexFlag(int f) {
/*    */       DelegatedSignalling.class.setIndexFlag((DelegatedSignalling)this, f);
/*    */     }
/*    */     
/*    */     public void setIndexFlagIfGreater(int f) {
/*    */       DelegatedSignalling.class.setIndexFlagIfGreater((DelegatedSignalling)this, f);
/*    */     }
/*    */     
/*    */     public void setIndexFlagIfLesser(int f) {
/*    */       DelegatedSignalling.class.setIndexFlagIfLesser((DelegatedSignalling)this, f);
/*    */     }
/*    */     
/*    */     public int tag() {
/*    */       return DelegatedSignalling.class.tag((DelegatedSignalling)this);
/*    */     }
/*    */     
/*    */     public int count(Function1 p) {
/*    */       return AugmentedIterableIterator.class.count((AugmentedIterableIterator)this, p);
/*    */     }
/*    */     
/*    */     public <U> U reduce(Function2 op) {
/*    */       return (U)AugmentedIterableIterator.class.reduce((AugmentedIterableIterator)this, op);
/*    */     }
/*    */     
/*    */     public <U> U fold(Object z, Function2 op) {
/*    */       return (U)AugmentedIterableIterator.class.fold((AugmentedIterableIterator)this, z, op);
/*    */     }
/*    */     
/*    */     public <U> U sum(Numeric num) {
/*    */       return (U)AugmentedIterableIterator.class.sum((AugmentedIterableIterator)this, num);
/*    */     }
/*    */     
/*    */     public <U> U product(Numeric num) {
/*    */       return (U)AugmentedIterableIterator.class.product((AugmentedIterableIterator)this, num);
/*    */     }
/*    */     
/*    */     public <U> T min(Ordering ord) {
/*    */       return (T)AugmentedIterableIterator.class.min((AugmentedIterableIterator)this, ord);
/*    */     }
/*    */     
/*    */     public <U> T max(Ordering ord) {
/*    */       return (T)AugmentedIterableIterator.class.max((AugmentedIterableIterator)this, ord);
/*    */     }
/*    */     
/*    */     public <U> void copyToArray(Object array, int from, int len) {
/*    */       AugmentedIterableIterator.class.copyToArray((AugmentedIterableIterator)this, array, from, len);
/*    */     }
/*    */     
/*    */     public <U> U reduceLeft(int howmany, Function2 op) {
/*    */       return (U)AugmentedIterableIterator.class.reduceLeft((AugmentedIterableIterator)this, howmany, op);
/*    */     }
/*    */     
/*    */     public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/*    */       return AugmentedIterableIterator.class.map2combiner((AugmentedIterableIterator)this, f, cb);
/*    */     }
/*    */     
/*    */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/*    */       return AugmentedIterableIterator.class.collect2combiner((AugmentedIterableIterator)this, pf, cb);
/*    */     }
/*    */     
/*    */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/*    */       return AugmentedIterableIterator.class.flatmap2combiner((AugmentedIterableIterator)this, f, cb);
/*    */     }
/*    */     
/*    */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/*    */       return (Bld)AugmentedIterableIterator.class.copy2builder((AugmentedIterableIterator)this, b);
/*    */     }
/*    */     
/*    */     public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/*    */       return AugmentedIterableIterator.class.filter2combiner((AugmentedIterableIterator)this, pred, cb);
/*    */     }
/*    */     
/*    */     public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/*    */       return AugmentedIterableIterator.class.filterNot2combiner((AugmentedIterableIterator)this, pred, cb);
/*    */     }
/*    */     
/*    */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/*    */       return AugmentedIterableIterator.class.partition2combiners((AugmentedIterableIterator)this, pred, btrue, bfalse);
/*    */     }
/*    */     
/*    */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/*    */       return AugmentedIterableIterator.class.take2combiner((AugmentedIterableIterator)this, n, cb);
/*    */     }
/*    */     
/*    */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/*    */       return AugmentedIterableIterator.class.drop2combiner((AugmentedIterableIterator)this, n, cb);
/*    */     }
/*    */     
/*    */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/*    */       return AugmentedIterableIterator.class.slice2combiner((AugmentedIterableIterator)this, from, until, cb);
/*    */     }
/*    */     
/*    */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/*    */       return AugmentedIterableIterator.class.splitAt2combiners((AugmentedIterableIterator)this, at, before, after);
/*    */     }
/*    */     
/*    */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/*    */       return AugmentedIterableIterator.class.takeWhile2combiner((AugmentedIterableIterator)this, p, cb);
/*    */     }
/*    */     
/*    */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/*    */       return AugmentedIterableIterator.class.span2combiners((AugmentedIterableIterator)this, p, before, after);
/*    */     }
/*    */     
/*    */     public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/*    */       AugmentedIterableIterator.class.scanToArray((AugmentedIterableIterator)this, z, op, array, from);
/*    */     }
/*    */     
/*    */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/*    */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, startValue, op, cb);
/*    */     }
/*    */     
/*    */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/*    */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, howmany, startValue, op, cb);
/*    */     }
/*    */     
/*    */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/*    */       return AugmentedIterableIterator.class.zip2combiner((AugmentedIterableIterator)this, otherpit, cb);
/*    */     }
/*    */     
/*    */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/*    */       return AugmentedIterableIterator.class.zipAll2combiner((AugmentedIterableIterator)this, that, thiselem, thatelem, cb);
/*    */     }
/*    */     
/*    */     public boolean isRemainingCheap() {
/*    */       return RemainsIterator.class.isRemainingCheap((RemainsIterator)this);
/*    */     }
/*    */     
/*    */     public Iterator<T> seq() {
/*    */       return Iterator.class.seq((Iterator)this);
/*    */     }
/*    */     
/*    */     public boolean isEmpty() {
/*    */       return Iterator.class.isEmpty((Iterator)this);
/*    */     }
/*    */     
/*    */     public boolean isTraversableAgain() {
/*    */       return Iterator.class.isTraversableAgain((Iterator)this);
/*    */     }
/*    */     
/*    */     public boolean hasDefiniteSize() {
/*    */       return Iterator.class.hasDefiniteSize((Iterator)this);
/*    */     }
/*    */     
/*    */     public Iterator<T> drop(int n) {
/*    */       return Iterator.class.drop((Iterator)this, n);
/*    */     }
/*    */     
/*    */     public <B> Iterator<B> $plus$plus(Function0 that) {
/*    */       return Iterator.class.$plus$plus((Iterator)this, that);
/*    */     }
/*    */     
/*    */     public <B> Iterator<B> flatMap(Function1 f) {
/*    */       return Iterator.class.flatMap((Iterator)this, f);
/*    */     }
/*    */     
/*    */     public Iterator<T> filter(Function1 p) {
/*    */       return Iterator.class.filter((Iterator)this, p);
/*    */     }
/*    */     
/*    */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/*    */       return Iterator.class.corresponds((Iterator)this, that, p);
/*    */     }
/*    */     
/*    */     public Iterator<T> withFilter(Function1 p) {
/*    */       return Iterator.class.withFilter((Iterator)this, p);
/*    */     }
/*    */     
/*    */     public Iterator<T> filterNot(Function1 p) {
/*    */       return Iterator.class.filterNot((Iterator)this, p);
/*    */     }
/*    */     
/*    */     public <B> Iterator<B> collect(PartialFunction pf) {
/*    */       return Iterator.class.collect((Iterator)this, pf);
/*    */     }
/*    */     
/*    */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/*    */       return Iterator.class.scanLeft((Iterator)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/*    */       return Iterator.class.scanRight((Iterator)this, z, op);
/*    */     }
/*    */     
/*    */     public Iterator<T> takeWhile(Function1 p) {
/*    */       return Iterator.class.takeWhile((Iterator)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Iterator<T>, Iterator<T>> partition(Function1 p) {
/*    */       return Iterator.class.partition((Iterator)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<Iterator<T>, Iterator<T>> span(Function1 p) {
/*    */       return Iterator.class.span((Iterator)this, p);
/*    */     }
/*    */     
/*    */     public Iterator<T> dropWhile(Function1 p) {
/*    */       return Iterator.class.dropWhile((Iterator)this, p);
/*    */     }
/*    */     
/*    */     public <B> Iterator<Tuple2<T, B>> zip(Iterator that) {
/*    */       return Iterator.class.zip((Iterator)this, that);
/*    */     }
/*    */     
/*    */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/*    */       return Iterator.class.padTo((Iterator)this, len, elem);
/*    */     }
/*    */     
/*    */     public Iterator<Tuple2<T, Object>> zipWithIndex() {
/*    */       return Iterator.class.zipWithIndex((Iterator)this);
/*    */     }
/*    */     
/*    */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/*    */       return Iterator.class.zipAll((Iterator)this, that, thisElem, thatElem);
/*    */     }
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/*    */       Iterator.class.foreach((Iterator)this, f);
/*    */     }
/*    */     
/*    */     public boolean forall(Function1 p) {
/*    */       return Iterator.class.forall((Iterator)this, p);
/*    */     }
/*    */     
/*    */     public boolean exists(Function1 p) {
/*    */       return Iterator.class.exists((Iterator)this, p);
/*    */     }
/*    */     
/*    */     public boolean contains(Object elem) {
/*    */       return Iterator.class.contains((Iterator)this, elem);
/*    */     }
/*    */     
/*    */     public Option<T> find(Function1 p) {
/*    */       return Iterator.class.find((Iterator)this, p);
/*    */     }
/*    */     
/*    */     public <B> int indexOf(Object elem) {
/*    */       return Iterator.class.indexOf((Iterator)this, elem);
/*    */     }
/*    */     
/*    */     public BufferedIterator<T> buffered() {
/*    */       return Iterator.class.buffered((Iterator)this);
/*    */     }
/*    */     
/*    */     public <B> Iterator<T>.GroupedIterator<B> grouped(int size) {
/*    */       return Iterator.class.grouped((Iterator)this, size);
/*    */     }
/*    */     
/*    */     public <B> Iterator<T>.GroupedIterator<B> sliding(int size, int step) {
/*    */       return Iterator.class.sliding((Iterator)this, size, step);
/*    */     }
/*    */     
/*    */     public int length() {
/*    */       return Iterator.class.length((Iterator)this);
/*    */     }
/*    */     
/*    */     public Tuple2<Iterator<T>, Iterator<T>> duplicate() {
/*    */       return Iterator.class.duplicate((Iterator)this);
/*    */     }
/*    */     
/*    */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/*    */       return Iterator.class.patch((Iterator)this, from, patchElems, replaced);
/*    */     }
/*    */     
/*    */     public boolean sameElements(Iterator that) {
/*    */       return Iterator.class.sameElements((Iterator)this, that);
/*    */     }
/*    */     
/*    */     public Traversable<T> toTraversable() {
/*    */       return Iterator.class.toTraversable((Iterator)this);
/*    */     }
/*    */     
/*    */     public Iterator<T> toIterator() {
/*    */       return Iterator.class.toIterator((Iterator)this);
/*    */     }
/*    */     
/*    */     public Stream<T> toStream() {
/*    */       return Iterator.class.toStream((Iterator)this);
/*    */     }
/*    */     
/*    */     public String toString() {
/*    */       return Iterator.class.toString((Iterator)this);
/*    */     }
/*    */     
/*    */     public <B> int sliding$default$2() {
/*    */       return Iterator.class.sliding$default$2((Iterator)this);
/*    */     }
/*    */     
/*    */     public List<T> reversed() {
/*    */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public int size() {
/*    */       return TraversableOnce.class.size((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public boolean nonEmpty() {
/*    */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <B> Option<B> collectFirst(PartialFunction pf) {
/*    */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*    */     }
/*    */     
/*    */     public <B> B $div$colon(Object z, Function2 op) {
/*    */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B $colon$bslash(Object z, Function2 op) {
/*    */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B foldLeft(Object z, Function2 op) {
/*    */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B foldRight(Object z, Function2 op) {
/*    */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceLeft(Function2 op) {
/*    */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceRight(Function2 op) {
/*    */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceLeftOption(Function2 op) {
/*    */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceRightOption(Function2 op) {
/*    */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> Option<A1> reduceOption(Function2 op) {
/*    */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*    */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*    */     }
/*    */     
/*    */     public <B> T maxBy(Function1 f, Ordering cmp) {
/*    */       return (T)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public <B> T minBy(Function1 f, Ordering cmp) {
/*    */       return (T)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public <B> void copyToBuffer(Buffer dest) {
/*    */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start) {
/*    */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs) {
/*    */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*    */     }
/*    */     
/*    */     public <B> Object toArray(ClassTag evidence$1) {
/*    */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*    */     }
/*    */     
/*    */     public List<T> toList() {
/*    */       return TraversableOnce.class.toList((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public Iterable<T> toIterable() {
/*    */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public Seq<T> toSeq() {
/*    */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<T> toIndexedSeq() {
/*    */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <B> Buffer<B> toBuffer() {
/*    */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <B> Set<B> toSet() {
/*    */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public Vector<T> toVector() {
/*    */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <Col> Col to(CanBuildFrom cbf) {
/*    */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*    */     }
/*    */     
/*    */     public <T, U> Map<T, U> toMap(scala.Predef$.less.colon.less ev) {
/*    */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*    */     }
/*    */     
/*    */     public String mkString(String start, String sep, String end) {
/*    */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*    */     }
/*    */     
/*    */     public String mkString(String sep) {
/*    */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*    */     }
/*    */     
/*    */     public String mkString() {
/*    */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*    */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String sep) {
/*    */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b) {
/*    */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*    */     }
/*    */     
/*    */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*    */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public int i() {
/*    */       return this.i;
/*    */     }
/*    */     
/*    */     public void i_$eq(int x$1) {
/*    */       this.i = x$1;
/*    */     }
/*    */     
/*    */     public int until() {
/*    */       return this.until;
/*    */     }
/*    */     
/*    */     public ParIterator(Repetition $outer, int i, int until, Object elem) {
/*    */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*    */       TraversableOnce.class.$init$((TraversableOnce)this);
/*    */       Iterator.class.$init$((Iterator)this);
/*    */       RemainsIterator.class.$init$((RemainsIterator)this);
/*    */       AugmentedIterableIterator.class.$init$((AugmentedIterableIterator)this);
/*    */       DelegatedSignalling.class.$init$((DelegatedSignalling)this);
/*    */       IterableSplitter.class.$init$((IterableSplitter)this);
/*    */       AugmentedSeqIterator.class.$init$((AugmentedSeqIterator)this);
/*    */       SeqSplitter.class.$init$(this);
/*    */     }
/*    */     
/*    */     public int remaining() {
/*    */       return until() - i();
/*    */     }
/*    */     
/*    */     public boolean hasNext() {
/*    */       return (i() < until());
/*    */     }
/*    */     
/*    */     public T next() {
/*    */       i_$eq(i() + 1);
/*    */       return this.scala$collection$parallel$immutable$Repetition$ParIterator$$elem;
/*    */     }
/*    */     
/*    */     public ParIterator dup() {
/*    */       return new ParIterator(scala$collection$parallel$immutable$Repetition$ParIterator$$$outer(), i(), until(), this.scala$collection$parallel$immutable$Repetition$ParIterator$$elem);
/*    */     }
/*    */     
/*    */     public Seq<SeqSplitter<T>> psplit(Seq sizes) {
/*    */       Seq incr = (Seq)sizes.scanLeft(BoxesRunTime.boxToInteger(0), (Function2)new Repetition$ParIterator$$anonfun$1(this), scala.collection.Seq$.MODULE$.canBuildFrom());
/* 32 */       return (Seq<SeqSplitter<T>>)((TraversableLike)((IterableLike)incr.init()).zip((GenIterable)incr.tail(), scala.collection.Seq$.MODULE$.canBuildFrom())).withFilter((Function1)new Repetition$ParIterator$$anonfun$psplit$1(this)).map((Function1)new Repetition$ParIterator$$anonfun$psplit$2(this), scala.collection.Seq$.MODULE$.canBuildFrom());
/*    */     }
/*    */     
/*    */     public class Repetition$ParIterator$$anonfun$1 extends AbstractFunction2.mcIII.sp implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final int apply(int x$1, int x$2) {
/*    */         return x$1 + x$2;
/*    */       }
/*    */       
/*    */       public int apply$mcIII$sp(int x$1, int x$2) {
/*    */         return x$1 + x$2;
/*    */       }
/*    */       
/*    */       public Repetition$ParIterator$$anonfun$1(Repetition.ParIterator $outer) {}
/*    */     }
/*    */     
/*    */     public class Repetition$ParIterator$$anonfun$psplit$1 extends AbstractFunction1<Tuple2<Object, Object>, Object> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final boolean apply(Tuple2 check$ifrefutable$1) {
/*    */         boolean bool;
/* 32 */         if (check$ifrefutable$1 != null) {
/* 32 */           bool = true;
/*    */         } else {
/* 32 */           bool = false;
/*    */         } 
/* 32 */         return bool;
/*    */       }
/*    */       
/*    */       public Repetition$ParIterator$$anonfun$psplit$1(Repetition.ParIterator $outer) {}
/*    */     }
/*    */     
/*    */     public class Repetition$ParIterator$$anonfun$psplit$2 extends AbstractFunction1<Tuple2<Object, Object>, ParIterator> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final Repetition<T>.ParIterator apply(Tuple2 x$3) {
/* 32 */         if (x$3 != null) {
/* 32 */           int i = this.$outer.i() + x$3._2$mcI$sp();
/* 32 */           scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 32 */           return new Repetition.ParIterator(this.$outer.scala$collection$parallel$immutable$Repetition$ParIterator$$$outer(), this.$outer.i() + x$3._1$mcI$sp(), scala.runtime.RichInt$.MODULE$.min$extension(i, this.$outer.until()), this.$outer.scala$collection$parallel$immutable$Repetition$ParIterator$$elem);
/*    */         } 
/* 32 */         throw new MatchError(x$3);
/*    */       }
/*    */       
/*    */       public Repetition$ParIterator$$anonfun$psplit$2(Repetition.ParIterator $outer) {}
/*    */     }
/*    */     
/*    */     public Seq<SeqSplitter<T>> split() {
/* 34 */       return psplit((Seq<Object>)scala.Predef$.MODULE$.wrapIntArray(new int[] { remaining() / 2, remaining() - remaining() / 2 }));
/*    */     }
/*    */   }
/*    */   
/*    */   public ParIterator splitter() {
/* 37 */     return new ParIterator(this, ParIterator().$lessinit$greater$default$1(), ParIterator().$lessinit$greater$default$2(), ParIterator().$lessinit$greater$default$3());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\Repetition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */