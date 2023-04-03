/*    */ package scala.collection.parallel.immutable;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Function3;
/*    */ import scala.Function4;
/*    */ import scala.Function5;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.CustomParallelizable;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSeqLike;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Parallel;
/*    */ import scala.collection.Parallelizable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Seq$;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.CanCombineFrom;
/*    */ import scala.collection.generic.DelegatedSignalling;
/*    */ import scala.collection.generic.GenTraversableFactory;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.GenericParTemplate;
/*    */ import scala.collection.generic.GenericTraversableTemplate;
/*    */ import scala.collection.generic.Signalling;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.collection.immutable.Stream;
/*    */ import scala.collection.immutable.Vector;
/*    */ import scala.collection.immutable.Vector$;
/*    */ import scala.collection.immutable.VectorIterator;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.collection.mutable.ArrayBuffer$;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.AugmentedIterableIterator;
/*    */ import scala.collection.parallel.AugmentedSeqIterator;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.IterableSplitter;
/*    */ import scala.collection.parallel.ParIterable;
/*    */ import scala.collection.parallel.ParIterableLike;
/*    */ import scala.collection.parallel.ParIterableLike$ScanLeaf$;
/*    */ import scala.collection.parallel.ParIterableLike$ScanNode$;
/*    */ import scala.collection.parallel.ParIterableView;
/*    */ import scala.collection.parallel.ParSeq;
/*    */ import scala.collection.parallel.ParSeqLike;
/*    */ import scala.collection.parallel.PreciseSplitter;
/*    */ import scala.collection.parallel.RemainsIterator;
/*    */ import scala.collection.parallel.SeqSplitter;
/*    */ import scala.collection.parallel.Splitter;
/*    */ import scala.collection.parallel.TaskSupport;
/*    */ import scala.math.Integral;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.Nothing$;
/*    */ import scala.runtime.ObjectRef;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\005d\001B\001\003\001-\021\021\002U1s-\026\034Go\034:\013\005\r!\021!C5n[V$\030M\0317f\025\t)a!\001\005qCJ\fG\016\\3m\025\t9\001\"\001\006d_2dWm\031;j_:T\021!C\001\006g\016\fG.Y\002\001+\taqc\005\004\001\033E\001s%\r\t\003\035=i\021\001C\005\003!!\021a!\0218z%\0264\007c\001\n\024+5\t!!\003\002\025\005\t1\001+\031:TKF\004\"AF\f\r\001\0211\001\004\001CC\002e\021\021\001V\t\0035u\001\"AD\016\n\005qA!a\002(pi\"Lgn\032\t\003\035yI!a\b\005\003\007\005s\027\020\005\003\"IU1S\"\001\022\013\005\r2\021aB4f]\026\024\030nY\005\003K\t\022!cR3oKJL7\rU1s)\026l\007\017\\1uKB\021!\003\001\t\006Q%*2\006L\007\002\t%\021!\006\002\002\013!\006\0248+Z9MS.,\007c\001\n\001+A\031QfL\013\016\0039R!a\001\004\n\005Ar#A\002,fGR|'\017\005\002\017e%\0211\007\003\002\r'\026\024\030.\0317ju\006\024G.\032\005\tk\001\021\t\021)A\005Y\0051a/Z2u_JDQa\016\001\005\002a\na\001P5oSRtDCA\026:\021\025)d\0071\001-\021\025Y\004\001\"\021=\003%\031w.\0349b]&|g.F\001>\035\t\021bhB\003@\005!\005\001)A\005QCJ4Vm\031;peB\021!#\021\004\006\003\tA\tAQ\n\004\003\016\013\004cA\021EM%\021QI\t\002\013!\006\024h)Y2u_JL\b\"B\034B\t\0039E#\001!\t\013%\013E1\001&\002\031\r\fgNQ;jY\0224%o\\7\026\005-3V#\001'\021\013\005ju*V,\n\0059\023#AD\"b]\016{WNY5oK\032\023x.\034\t\003!Fk\021!Q\005\003%N\023AaQ8mY&\021AK\t\002\021\017\026tWM]5d\007>l\007/\0318j_:\004\"A\006,\005\013aA%\031A\r\021\007I\001Q\013C\003Z\003\022\005!,\001\006oK^\024U/\0337eKJ,\"a\0271\026\003q\003B\001K/`C&\021a\f\002\002\t\007>l'-\0338feB\021a\003\031\003\0061a\023\r!\007\t\004%\001y\006\"B2B\t\003!\027a\0038fo\016{WNY5oKJ,\"!\0325\026\003\031\004B\001K/hSB\021a\003\033\003\0061\t\024\r!\007\t\004%\0019\007bB6B\003\003%I\001\\\001\fe\026\fGMU3t_24X\rF\001n!\tq7/D\001p\025\t\001\030/\001\003mC:<'\"\001:\002\t)\fg/Y\005\003i>\024aa\0242kK\016$\b\"B\034\001\t\0031H#A\026\t\013a\004A\021A=\002\013\005\004\b\017\\=\025\005UQ\b\"B>x\001\004a\030aA5eqB\021a\"`\005\003}\"\0211!\0238u\021\035\t\t\001\001C\001\003\007\ta\001\\3oORDW#\001?\t\017\005\035\001\001\"\001\002\n\005A1\017\0357jiR,'/\006\002\002\fA!\001&!\004\026\023\r\ty\001\002\002\f'\026\f8\013\0357jiR,'\017C\004\002\024\001!\t%!\006\002\007M,\027/F\001-\021\035\tI\002\001C!\003+\t\001\002^8WK\016$xN\035\004\007\003;\001\001!a\b\003#A\013'OV3di>\024\030\n^3sCR|'o\005\004\002\034\005\005\0221\002\t\005[\005\rR#C\002\002&9\022aBV3di>\024\030\n^3sCR|'\017\003\006\002*\005m!\021!Q\001\nq\faaX:uCJ$\bBCA\027\0037\021\t\021)A\005y\006!q,\0328e\021\0359\0241\004C\001\003c!b!a\r\0028\005e\002\003BA\033\0037i\021\001\001\005\b\003S\ty\0031\001}\021\035\ti#a\fA\002qD\001\"!\020\002\034\021\005\0211A\001\ne\026l\027-\0338j]\036D\001\"!\021\002\034\021\005\021\021B\001\004IV\004\b\002CA#\0037!\t!a\022\002\013M\004H.\033;\026\005\005%\003CBA&\003\033\n\031$D\001\007\023\r\tyE\002\002\004'\026\f\b\002CA*\0037!\t!!\026\002\rA\034\b\017\\5u)\021\tI%a\026\t\021\005e\023\021\013a\001\0037\nQa]5{KN\004BADA/y&\031\021q\f\005\003\025q\022X\r]3bi\026$g\b")
/*    */ public class ParVector<T> implements ParSeq<T>, GenericParTemplate<T, ParVector>, ParSeqLike<T, ParVector<T>, Vector<T>>, Serializable {
/*    */   private final Vector<T> vector;
/*    */   
/*    */   private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
/*    */   
/*    */   private volatile ParIterableLike$ScanNode$ ScanNode$module;
/*    */   
/*    */   private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;
/*    */   
/*    */   public ParSeq<T> toSeq() {
/* 43 */     return ParSeq$class.toSeq(this);
/*    */   }
/*    */   
/*    */   public ParIterable<T> toIterable() {
/* 43 */     return ParIterable$class.toIterable(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 43 */     return ParSeq.class.toString(this);
/*    */   }
/*    */   
/*    */   public String stringPrefix() {
/* 43 */     return ParSeq.class.stringPrefix(this);
/*    */   }
/*    */   
/*    */   public Object scala$collection$parallel$ParSeqLike$$super$zip(GenIterable that, CanBuildFrom bf) {
/* 43 */     return ParIterableLike.class.zip(this, that, bf);
/*    */   }
/*    */   
/*    */   public PreciseSplitter<T> iterator() {
/* 43 */     return ParSeqLike.class.iterator(this);
/*    */   }
/*    */   
/*    */   public int size() {
/* 43 */     return ParSeqLike.class.size(this);
/*    */   }
/*    */   
/*    */   public int segmentLength(Function1 p, int from) {
/* 43 */     return ParSeqLike.class.segmentLength(this, p, from);
/*    */   }
/*    */   
/*    */   public int indexWhere(Function1 p, int from) {
/* 43 */     return ParSeqLike.class.indexWhere(this, p, from);
/*    */   }
/*    */   
/*    */   public int lastIndexWhere(Function1 p, int end) {
/* 43 */     return ParSeqLike.class.lastIndexWhere(this, p, end);
/*    */   }
/*    */   
/*    */   public ParVector<T> reverse() {
/* 43 */     return (ParVector<T>)ParSeqLike.class.reverse(this);
/*    */   }
/*    */   
/*    */   public <S, That> That reverseMap(Function1 f, CanBuildFrom bf) {
/* 43 */     return (That)ParSeqLike.class.reverseMap(this, f, bf);
/*    */   }
/*    */   
/*    */   public <S> boolean startsWith(GenSeq that, int offset) {
/* 43 */     return ParSeqLike.class.startsWith(this, that, offset);
/*    */   }
/*    */   
/*    */   public <U> boolean sameElements(GenIterable that) {
/* 43 */     return ParSeqLike.class.sameElements(this, that);
/*    */   }
/*    */   
/*    */   public <S> boolean endsWith(GenSeq that) {
/* 43 */     return ParSeqLike.class.endsWith(this, that);
/*    */   }
/*    */   
/*    */   public <U, That> That patch(int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/* 43 */     return (That)ParSeqLike.class.patch(this, from, patch, replaced, bf);
/*    */   }
/*    */   
/*    */   public <U, That> That updated(int index, Object elem, CanBuildFrom bf) {
/* 43 */     return (That)ParSeqLike.class.updated(this, index, elem, bf);
/*    */   }
/*    */   
/*    */   public <U, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/* 43 */     return (That)ParSeqLike.class.$plus$colon(this, elem, bf);
/*    */   }
/*    */   
/*    */   public <U, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/* 43 */     return (That)ParSeqLike.class.$colon$plus(this, elem, bf);
/*    */   }
/*    */   
/*    */   public <U, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/* 43 */     return (That)ParSeqLike.class.padTo(this, len, elem, bf);
/*    */   }
/*    */   
/*    */   public <U, S, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 43 */     return (That)ParSeqLike.class.zip(this, that, bf);
/*    */   }
/*    */   
/*    */   public <S> boolean corresponds(GenSeq that, Function2 p) {
/* 43 */     return ParSeqLike.class.corresponds(this, that, p);
/*    */   }
/*    */   
/*    */   public <U> ParVector<T> diff(GenSeq that) {
/* 43 */     return (ParVector<T>)ParSeqLike.class.diff(this, that);
/*    */   }
/*    */   
/*    */   public <U> ParVector<T> intersect(GenSeq that) {
/* 43 */     return (ParVector<T>)ParSeqLike.class.intersect(this, that);
/*    */   }
/*    */   
/*    */   public ParVector<T> distinct() {
/* 43 */     return (ParVector<T>)ParSeqLike.class.distinct(this);
/*    */   }
/*    */   
/*    */   public Object view() {
/* 43 */     return ParSeqLike.class.view(this);
/*    */   }
/*    */   
/*    */   public SeqSplitter<T> down(IterableSplitter p) {
/* 43 */     return ParSeqLike.class.down(this, p);
/*    */   }
/*    */   
/*    */   public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
/* 43 */     return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
/* 43 */     this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
/*    */   }
/*    */   
/*    */   private ParIterableLike$ScanNode$ ScanNode$lzycompute() {
/* 43 */     synchronized (this) {
/* 43 */       if (this.ScanNode$module == null)
/* 43 */         this.ScanNode$module = new ParIterableLike$ScanNode$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/immutable/ParVector}} */
/* 43 */       return this.ScanNode$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public ParIterableLike$ScanNode$ ScanNode() {
/* 43 */     return (this.ScanNode$module == null) ? ScanNode$lzycompute() : this.ScanNode$module;
/*    */   }
/*    */   
/*    */   private ParIterableLike$ScanLeaf$ ScanLeaf$lzycompute() {
/* 43 */     synchronized (this) {
/* 43 */       if (this.ScanLeaf$module == null)
/* 43 */         this.ScanLeaf$module = new ParIterableLike$ScanLeaf$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/immutable/ParVector}} */
/* 43 */       return this.ScanLeaf$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public ParIterableLike$ScanLeaf$ ScanLeaf() {
/* 43 */     return (this.ScanLeaf$module == null) ? ScanLeaf$lzycompute() : this.ScanLeaf$module;
/*    */   }
/*    */   
/*    */   public void initTaskSupport() {
/* 43 */     ParIterableLike.class.initTaskSupport(this);
/*    */   }
/*    */   
/*    */   public TaskSupport tasksupport() {
/* 43 */     return ParIterableLike.class.tasksupport(this);
/*    */   }
/*    */   
/*    */   public void tasksupport_$eq(TaskSupport ts) {
/* 43 */     ParIterableLike.class.tasksupport_$eq(this, ts);
/*    */   }
/*    */   
/*    */   public ParVector<T> repr() {
/* 43 */     return (ParVector<T>)ParIterableLike.class.repr(this);
/*    */   }
/*    */   
/*    */   public final boolean isTraversableAgain() {
/* 43 */     return ParIterableLike.class.isTraversableAgain(this);
/*    */   }
/*    */   
/*    */   public boolean hasDefiniteSize() {
/* 43 */     return ParIterableLike.class.hasDefiniteSize(this);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 43 */     return ParIterableLike.class.isEmpty(this);
/*    */   }
/*    */   
/*    */   public boolean nonEmpty() {
/* 43 */     return ParIterableLike.class.nonEmpty(this);
/*    */   }
/*    */   
/*    */   public T head() {
/* 43 */     return (T)ParIterableLike.class.head(this);
/*    */   }
/*    */   
/*    */   public Option<T> headOption() {
/* 43 */     return ParIterableLike.class.headOption(this);
/*    */   }
/*    */   
/*    */   public ParVector<T> tail() {
/* 43 */     return (ParVector<T>)ParIterableLike.class.tail(this);
/*    */   }
/*    */   
/*    */   public T last() {
/* 43 */     return (T)ParIterableLike.class.last(this);
/*    */   }
/*    */   
/*    */   public Option<T> lastOption() {
/* 43 */     return ParIterableLike.class.lastOption(this);
/*    */   }
/*    */   
/*    */   public ParVector<T> init() {
/* 43 */     return (ParVector<T>)ParIterableLike.class.init(this);
/*    */   }
/*    */   
/*    */   public ParVector<T> par() {
/* 43 */     return (ParVector<T>)ParIterableLike.class.par(this);
/*    */   }
/*    */   
/*    */   public boolean isStrictSplitterCollection() {
/* 43 */     return ParIterableLike.class.isStrictSplitterCollection(this);
/*    */   }
/*    */   
/*    */   public <S, That> Combiner<S, That> reuse(Option oldc, Combiner newc) {
/* 43 */     return ParIterableLike.class.reuse(this, oldc, newc);
/*    */   }
/*    */   
/*    */   public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask tsk) {
/* 43 */     return ParIterableLike.class.task2ops(this, tsk);
/*    */   }
/*    */   
/*    */   public <R> Object wrap(Function0 body) {
/* 43 */     return ParIterableLike.class.wrap(this, body);
/*    */   }
/*    */   
/*    */   public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(DelegatedSignalling it) {
/* 43 */     return ParIterableLike.class.delegatedSignalling2ops(this, it);
/*    */   }
/*    */   
/*    */   public <Elem, To> Object builder2ops(Builder cb) {
/* 43 */     return ParIterableLike.class.builder2ops(this, cb);
/*    */   }
/*    */   
/*    */   public <S, That> Object bf2seq(CanBuildFrom bf) {
/* 43 */     return ParIterableLike.class.bf2seq(this, bf);
/*    */   }
/*    */   
/*    */   public <S, That extends Parallel> ParVector<T> sequentially(Function1 b) {
/* 43 */     return (ParVector<T>)ParIterableLike.class.sequentially(this, b);
/*    */   }
/*    */   
/*    */   public String mkString(String start, String sep, String end) {
/* 43 */     return ParIterableLike.class.mkString(this, start, sep, end);
/*    */   }
/*    */   
/*    */   public String mkString(String sep) {
/* 43 */     return ParIterableLike.class.mkString(this, sep);
/*    */   }
/*    */   
/*    */   public String mkString() {
/* 43 */     return ParIterableLike.class.mkString(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object other) {
/* 43 */     return ParIterableLike.class.canEqual(this, other);
/*    */   }
/*    */   
/*    */   public <U> U reduce(Function2 op) {
/* 43 */     return (U)ParIterableLike.class.reduce(this, op);
/*    */   }
/*    */   
/*    */   public <U> Option<U> reduceOption(Function2 op) {
/* 43 */     return ParIterableLike.class.reduceOption(this, op);
/*    */   }
/*    */   
/*    */   public <U> U fold(Object z, Function2 op) {
/* 43 */     return (U)ParIterableLike.class.fold(this, z, op);
/*    */   }
/*    */   
/*    */   public <S> S aggregate(Object z, Function2 seqop, Function2 combop) {
/* 43 */     return (S)ParIterableLike.class.aggregate(this, z, seqop, combop);
/*    */   }
/*    */   
/*    */   public <S> S foldLeft(Object z, Function2 op) {
/* 43 */     return (S)ParIterableLike.class.foldLeft(this, z, op);
/*    */   }
/*    */   
/*    */   public <S> S foldRight(Object z, Function2 op) {
/* 43 */     return (S)ParIterableLike.class.foldRight(this, z, op);
/*    */   }
/*    */   
/*    */   public <U> U reduceLeft(Function2 op) {
/* 43 */     return (U)ParIterableLike.class.reduceLeft(this, op);
/*    */   }
/*    */   
/*    */   public <U> U reduceRight(Function2 op) {
/* 43 */     return (U)ParIterableLike.class.reduceRight(this, op);
/*    */   }
/*    */   
/*    */   public <U> Option<U> reduceLeftOption(Function2 op) {
/* 43 */     return ParIterableLike.class.reduceLeftOption(this, op);
/*    */   }
/*    */   
/*    */   public <U> Option<U> reduceRightOption(Function2 op) {
/* 43 */     return ParIterableLike.class.reduceRightOption(this, op);
/*    */   }
/*    */   
/*    */   public <U> void foreach(Function1 f) {
/* 43 */     ParIterableLike.class.foreach(this, f);
/*    */   }
/*    */   
/*    */   public int count(Function1 p) {
/* 43 */     return ParIterableLike.class.count(this, p);
/*    */   }
/*    */   
/*    */   public <U> U sum(Numeric num) {
/* 43 */     return (U)ParIterableLike.class.sum(this, num);
/*    */   }
/*    */   
/*    */   public <U> U product(Numeric num) {
/* 43 */     return (U)ParIterableLike.class.product(this, num);
/*    */   }
/*    */   
/*    */   public <U> T min(Ordering ord) {
/* 43 */     return (T)ParIterableLike.class.min(this, ord);
/*    */   }
/*    */   
/*    */   public <U> T max(Ordering ord) {
/* 43 */     return (T)ParIterableLike.class.max(this, ord);
/*    */   }
/*    */   
/*    */   public <S> T maxBy(Function1 f, Ordering cmp) {
/* 43 */     return (T)ParIterableLike.class.maxBy(this, f, cmp);
/*    */   }
/*    */   
/*    */   public <S> T minBy(Function1 f, Ordering cmp) {
/* 43 */     return (T)ParIterableLike.class.minBy(this, f, cmp);
/*    */   }
/*    */   
/*    */   public <S, That> That map(Function1 f, CanBuildFrom bf) {
/* 43 */     return (That)ParIterableLike.class.map(this, f, bf);
/*    */   }
/*    */   
/*    */   public <S, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 43 */     return (That)ParIterableLike.class.collect(this, pf, bf);
/*    */   }
/*    */   
/*    */   public <S, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 43 */     return (That)ParIterableLike.class.flatMap(this, f, bf);
/*    */   }
/*    */   
/*    */   public boolean forall(Function1 pred) {
/* 43 */     return ParIterableLike.class.forall(this, pred);
/*    */   }
/*    */   
/*    */   public boolean exists(Function1 pred) {
/* 43 */     return ParIterableLike.class.exists(this, pred);
/*    */   }
/*    */   
/*    */   public Option<T> find(Function1 pred) {
/* 43 */     return ParIterableLike.class.find(this, pred);
/*    */   }
/*    */   
/*    */   public Object combinerFactory() {
/* 43 */     return ParIterableLike.class.combinerFactory(this);
/*    */   }
/*    */   
/*    */   public <S, That> Object combinerFactory(Function0 cbf) {
/* 43 */     return ParIterableLike.class.combinerFactory(this, cbf);
/*    */   }
/*    */   
/*    */   public ParVector<T> filter(Function1 pred) {
/* 43 */     return (ParVector<T>)ParIterableLike.class.filter(this, pred);
/*    */   }
/*    */   
/*    */   public ParVector<T> filterNot(Function1 pred) {
/* 43 */     return (ParVector<T>)ParIterableLike.class.filterNot(this, pred);
/*    */   }
/*    */   
/*    */   public <U, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/* 43 */     return (That)ParIterableLike.class.$plus$plus(this, that, bf);
/*    */   }
/*    */   
/*    */   public Tuple2<ParVector<T>, ParVector<T>> partition(Function1 pred) {
/* 43 */     return ParIterableLike.class.partition(this, pred);
/*    */   }
/*    */   
/*    */   public <K> ParMap<K, ParVector<T>> groupBy(Function1 f) {
/* 43 */     return ParIterableLike.class.groupBy(this, f);
/*    */   }
/*    */   
/*    */   public ParVector<T> take(int n) {
/* 43 */     return (ParVector<T>)ParIterableLike.class.take(this, n);
/*    */   }
/*    */   
/*    */   public ParVector<T> drop(int n) {
/* 43 */     return (ParVector<T>)ParIterableLike.class.drop(this, n);
/*    */   }
/*    */   
/*    */   public ParVector<T> slice(int unc_from, int unc_until) {
/* 43 */     return (ParVector<T>)ParIterableLike.class.slice(this, unc_from, unc_until);
/*    */   }
/*    */   
/*    */   public Tuple2<ParVector<T>, ParVector<T>> splitAt(int n) {
/* 43 */     return ParIterableLike.class.splitAt(this, n);
/*    */   }
/*    */   
/*    */   public <U, That> That scan(Object z, Function2 op, CanBuildFrom bf) {
/* 43 */     return (That)ParIterableLike.class.scan(this, z, op, bf);
/*    */   }
/*    */   
/*    */   public <S, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 43 */     return (That)ParIterableLike.class.scanLeft(this, z, op, bf);
/*    */   }
/*    */   
/*    */   public <S, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 43 */     return (That)ParIterableLike.class.scanRight(this, z, op, bf);
/*    */   }
/*    */   
/*    */   public ParVector<T> takeWhile(Function1 pred) {
/* 43 */     return (ParVector<T>)ParIterableLike.class.takeWhile(this, pred);
/*    */   }
/*    */   
/*    */   public Tuple2<ParVector<T>, ParVector<T>> span(Function1 pred) {
/* 43 */     return ParIterableLike.class.span(this, pred);
/*    */   }
/*    */   
/*    */   public ParVector<T> dropWhile(Function1 pred) {
/* 43 */     return (ParVector<T>)ParIterableLike.class.dropWhile(this, pred);
/*    */   }
/*    */   
/*    */   public <U> void copyToArray(Object xs) {
/* 43 */     ParIterableLike.class.copyToArray(this, xs);
/*    */   }
/*    */   
/*    */   public <U> void copyToArray(Object xs, int start) {
/* 43 */     ParIterableLike.class.copyToArray(this, xs, start);
/*    */   }
/*    */   
/*    */   public <U> void copyToArray(Object xs, int start, int len) {
/* 43 */     ParIterableLike.class.copyToArray(this, xs, start, len);
/*    */   }
/*    */   
/*    */   public <U, That> That zipWithIndex(CanBuildFrom bf) {
/* 43 */     return (That)ParIterableLike.class.zipWithIndex(this, bf);
/*    */   }
/*    */   
/*    */   public <S, U, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 43 */     return (That)ParIterableLike.class.zipAll(this, that, thisElem, thatElem, bf);
/*    */   }
/*    */   
/*    */   public <U, That> That toParCollection(Function0 cbf) {
/* 43 */     return (That)ParIterableLike.class.toParCollection(this, cbf);
/*    */   }
/*    */   
/*    */   public <K, V, That> That toParMap(Function0 cbf, Predef$.less.colon.less ev) {
/* 43 */     return (That)ParIterableLike.class.toParMap(this, cbf, ev);
/*    */   }
/*    */   
/*    */   public <U> Object toArray(ClassTag evidence$1) {
/* 43 */     return ParIterableLike.class.toArray(this, evidence$1);
/*    */   }
/*    */   
/*    */   public List<T> toList() {
/* 43 */     return ParIterableLike.class.toList(this);
/*    */   }
/*    */   
/*    */   public IndexedSeq<T> toIndexedSeq() {
/* 43 */     return ParIterableLike.class.toIndexedSeq(this);
/*    */   }
/*    */   
/*    */   public Stream<T> toStream() {
/* 43 */     return ParIterableLike.class.toStream(this);
/*    */   }
/*    */   
/*    */   public Iterator<T> toIterator() {
/* 43 */     return ParIterableLike.class.toIterator(this);
/*    */   }
/*    */   
/*    */   public <U> Buffer<U> toBuffer() {
/* 43 */     return ParIterableLike.class.toBuffer(this);
/*    */   }
/*    */   
/*    */   public GenTraversable<T> toTraversable() {
/* 43 */     return ParIterableLike.class.toTraversable(this);
/*    */   }
/*    */   
/*    */   public <U> ParSet<U> toSet() {
/* 43 */     return ParIterableLike.class.toSet(this);
/*    */   }
/*    */   
/*    */   public <K, V> ParMap<K, V> toMap(Predef$.less.colon.less ev) {
/* 43 */     return ParIterableLike.class.toMap(this, ev);
/*    */   }
/*    */   
/*    */   public <Col> Col to(CanBuildFrom cbf) {
/* 43 */     return (Col)ParIterableLike.class.to(this, cbf);
/*    */   }
/*    */   
/*    */   public int scanBlockSize() {
/* 43 */     return ParIterableLike.class.scanBlockSize(this);
/*    */   }
/*    */   
/*    */   public <S> S $div$colon(Object z, Function2 op) {
/* 43 */     return (S)ParIterableLike.class.$div$colon(this, z, op);
/*    */   }
/*    */   
/*    */   public <S> S $colon$bslash(Object z, Function2 op) {
/* 43 */     return (S)ParIterableLike.class.$colon$bslash(this, z, op);
/*    */   }
/*    */   
/*    */   public String debugInformation() {
/* 43 */     return ParIterableLike.class.debugInformation(this);
/*    */   }
/*    */   
/*    */   public Seq<String> brokenInvariants() {
/* 43 */     return ParIterableLike.class.brokenInvariants(this);
/*    */   }
/*    */   
/*    */   public ArrayBuffer<String> debugBuffer() {
/* 43 */     return ParIterableLike.class.debugBuffer(this);
/*    */   }
/*    */   
/*    */   public void debugclear() {
/* 43 */     ParIterableLike.class.debugclear(this);
/*    */   }
/*    */   
/*    */   public ArrayBuffer<String> debuglog(String s) {
/* 43 */     return ParIterableLike.class.debuglog(this, s);
/*    */   }
/*    */   
/*    */   public void printDebugBuffer() {
/* 43 */     ParIterableLike.class.printDebugBuffer(this);
/*    */   }
/*    */   
/*    */   public Combiner<T, ParVector<T>> parCombiner() {
/* 43 */     return CustomParallelizable.class.parCombiner((CustomParallelizable)this);
/*    */   }
/*    */   
/*    */   public Builder<T, ParVector<T>> newBuilder() {
/* 43 */     return GenericParTemplate.class.newBuilder(this);
/*    */   }
/*    */   
/*    */   public Combiner<T, ParVector<T>> newCombiner() {
/* 43 */     return GenericParTemplate.class.newCombiner(this);
/*    */   }
/*    */   
/*    */   public <B> Combiner<B, ParVector<B>> genericBuilder() {
/* 43 */     return GenericParTemplate.class.genericBuilder(this);
/*    */   }
/*    */   
/*    */   public <B> Combiner<B, ParVector<B>> genericCombiner() {
/* 43 */     return GenericParTemplate.class.genericCombiner(this);
/*    */   }
/*    */   
/*    */   public <A1, A2> Tuple2<ParVector<A1>, ParVector<A2>> unzip(Function1 asPair) {
/* 43 */     return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*    */   }
/*    */   
/*    */   public <A1, A2, A3> Tuple3<ParVector<A1>, ParVector<A2>, ParVector<A3>> unzip3(Function1 asTriple) {
/* 43 */     return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*    */   }
/*    */   
/*    */   public <B> ParVector<B> flatten(Function1 asTraversable) {
/* 43 */     return (ParVector<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*    */   }
/*    */   
/*    */   public <B> ParVector<ParVector<B>> transpose(Function1 asTraversable) {
/* 43 */     return (ParVector<ParVector<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*    */   }
/*    */   
/*    */   public boolean isDefinedAt(int idx) {
/* 43 */     return GenSeqLike.class.isDefinedAt((GenSeqLike)this, idx);
/*    */   }
/*    */   
/*    */   public int prefixLength(Function1 p) {
/* 43 */     return GenSeqLike.class.prefixLength((GenSeqLike)this, p);
/*    */   }
/*    */   
/*    */   public int indexWhere(Function1 p) {
/* 43 */     return GenSeqLike.class.indexWhere((GenSeqLike)this, p);
/*    */   }
/*    */   
/*    */   public <B> int indexOf(Object elem) {
/* 43 */     return GenSeqLike.class.indexOf((GenSeqLike)this, elem);
/*    */   }
/*    */   
/*    */   public <B> int indexOf(Object elem, int from) {
/* 43 */     return GenSeqLike.class.indexOf((GenSeqLike)this, elem, from);
/*    */   }
/*    */   
/*    */   public <B> int lastIndexOf(Object elem) {
/* 43 */     return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem);
/*    */   }
/*    */   
/*    */   public <B> int lastIndexOf(Object elem, int end) {
/* 43 */     return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem, end);
/*    */   }
/*    */   
/*    */   public int lastIndexWhere(Function1 p) {
/* 43 */     return GenSeqLike.class.lastIndexWhere((GenSeqLike)this, p);
/*    */   }
/*    */   
/*    */   public <B> boolean startsWith(GenSeq that) {
/* 43 */     return GenSeqLike.class.startsWith((GenSeqLike)this, that);
/*    */   }
/*    */   
/*    */   public <B, That> That union(GenSeq that, CanBuildFrom bf) {
/* 43 */     return (That)GenSeqLike.class.union((GenSeqLike)this, that, bf);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 43 */     return GenSeqLike.class.hashCode((GenSeqLike)this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object that) {
/* 43 */     return GenSeqLike.class.equals((GenSeqLike)this, that);
/*    */   }
/*    */   
/*    */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 43 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*    */   }
/*    */   
/*    */   public ParVector(Vector<T> vector) {
/* 43 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 43 */     Parallelizable.class.$init$((Parallelizable)this);
/* 43 */     GenSeqLike.class.$init$((GenSeqLike)this);
/* 43 */     GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/* 43 */     GenTraversable.class.$init$((GenTraversable)this);
/* 43 */     GenIterable.class.$init$(this);
/* 43 */     GenSeq.class.$init$(this);
/* 43 */     GenericParTemplate.class.$init$(this);
/* 43 */     CustomParallelizable.class.$init$((CustomParallelizable)this);
/* 43 */     ParIterableLike.class.$init$(this);
/* 43 */     ParIterable.class.$init$(this);
/* 43 */     ParSeqLike.class.$init$(this);
/* 43 */     ParSeq.class.$init$(this);
/* 43 */     ParIterable$class.$init$(this);
/* 43 */     ParSeq$class.$init$(this);
/*    */   }
/*    */   
/*    */   public ParVector$ companion() {
/* 49 */     return ParVector$.MODULE$;
/*    */   }
/*    */   
/*    */   public ParVector() {
/* 51 */     this((Vector<T>)Vector$.MODULE$.apply((Seq)Nil$.MODULE$));
/*    */   }
/*    */   
/*    */   public T apply(int idx) {
/* 53 */     return (T)this.vector.apply(idx);
/*    */   }
/*    */   
/*    */   public int length() {
/* 55 */     return this.vector.length();
/*    */   }
/*    */   
/*    */   public SeqSplitter<T> splitter() {
/* 58 */     ParVectorIterator pit = new ParVectorIterator(this, this.vector.startIndex(), this.vector.endIndex());
/* 59 */     this.vector.initIterator(pit);
/* 60 */     return pit;
/*    */   }
/*    */   
/*    */   public Vector<T> seq() {
/* 63 */     return this.vector;
/*    */   }
/*    */   
/*    */   public Vector<T> toVector() {
/* 65 */     return this.vector;
/*    */   }
/*    */   
/*    */   public static <T> CanCombineFrom<ParVector<?>, T, ParVector<T>> canBuildFrom() {
/*    */     return ParVector$.MODULE$.canBuildFrom();
/*    */   }
/*    */   
/*    */   public static <A> ParVector<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*    */     return (ParVector<A>)ParVector$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*    */   }
/*    */   
/*    */   public static <T> ParVector<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*    */     return (ParVector<T>)ParVector$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*    */   }
/*    */   
/*    */   public static <T> ParVector<T> range(T paramT1, T paramT2, Integral<T> paramIntegral) {
/*    */     return (ParVector<T>)ParVector$.MODULE$.range(paramT1, paramT2, paramIntegral);
/*    */   }
/*    */   
/*    */   public static <A> ParVector<ParVector<ParVector<ParVector<ParVector<A>>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function5<Object, Object, Object, Object, Object, A> paramFunction5) {
/*    */     return (ParVector<ParVector<ParVector<ParVector<ParVector<A>>>>>)ParVector$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction5);
/*    */   }
/*    */   
/*    */   public static <A> ParVector<ParVector<ParVector<ParVector<A>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function4<Object, Object, Object, Object, A> paramFunction4) {
/*    */     return (ParVector<ParVector<ParVector<ParVector<A>>>>)ParVector$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction4);
/*    */   }
/*    */   
/*    */   public static <A> ParVector<ParVector<ParVector<A>>> tabulate(int paramInt1, int paramInt2, int paramInt3, Function3<Object, Object, Object, A> paramFunction3) {
/*    */     return (ParVector<ParVector<ParVector<A>>>)ParVector$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramFunction3);
/*    */   }
/*    */   
/*    */   public static <A> ParVector<ParVector<A>> tabulate(int paramInt1, int paramInt2, Function2<Object, Object, A> paramFunction2) {
/*    */     return (ParVector<ParVector<A>>)ParVector$.MODULE$.tabulate(paramInt1, paramInt2, paramFunction2);
/*    */   }
/*    */   
/*    */   public static <A> ParVector<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*    */     return (ParVector<A>)ParVector$.MODULE$.tabulate(paramInt, paramFunction1);
/*    */   }
/*    */   
/*    */   public static <A> ParVector<ParVector<ParVector<ParVector<ParVector<A>>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function0<A> paramFunction0) {
/*    */     return (ParVector<ParVector<ParVector<ParVector<ParVector<A>>>>>)ParVector$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction0);
/*    */   }
/*    */   
/*    */   public static <A> ParVector<ParVector<ParVector<ParVector<A>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function0<A> paramFunction0) {
/*    */     return (ParVector<ParVector<ParVector<ParVector<A>>>>)ParVector$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction0);
/*    */   }
/*    */   
/*    */   public static <A> ParVector<ParVector<ParVector<A>>> fill(int paramInt1, int paramInt2, int paramInt3, Function0<A> paramFunction0) {
/*    */     return (ParVector<ParVector<ParVector<A>>>)ParVector$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramFunction0);
/*    */   }
/*    */   
/*    */   public static <A> ParVector<ParVector<A>> fill(int paramInt1, int paramInt2, Function0<A> paramFunction0) {
/*    */     return (ParVector<ParVector<A>>)ParVector$.MODULE$.fill(paramInt1, paramInt2, paramFunction0);
/*    */   }
/*    */   
/*    */   public static <A> ParVector<A> fill(int paramInt, Function0<A> paramFunction0) {
/*    */     return (ParVector<A>)ParVector$.MODULE$.fill(paramInt, paramFunction0);
/*    */   }
/*    */   
/*    */   public static <A> ParVector<A> concat(Seq<Traversable<A>> paramSeq) {
/*    */     return (ParVector<A>)ParVector$.MODULE$.concat(paramSeq);
/*    */   }
/*    */   
/*    */   public static GenTraversableFactory<ParVector>.GenericCanBuildFrom<Nothing$> ReusableCBF() {
/*    */     return ParVector$.MODULE$.ReusableCBF();
/*    */   }
/*    */   
/*    */   public static <A> ParVector<A> empty() {
/*    */     return (ParVector<A>)ParVector$.MODULE$.empty();
/*    */   }
/*    */   
/*    */   public class ParVectorIterator extends VectorIterator<T> implements SeqSplitter<T> {
/*    */     private Signalling signalDelegate;
/*    */     
/*    */     public Seq<SeqSplitter<T>> splitWithSignalling() {
/* 67 */       return SeqSplitter.class.splitWithSignalling(this);
/*    */     }
/*    */     
/*    */     public Seq<SeqSplitter<T>> psplitWithSignalling(Seq sizes) {
/* 67 */       return SeqSplitter.class.psplitWithSignalling(this, sizes);
/*    */     }
/*    */     
/*    */     public SeqSplitter<T>.Taken newTaken(int until) {
/* 67 */       return SeqSplitter.class.newTaken(this, until);
/*    */     }
/*    */     
/*    */     public SeqSplitter<T> take(int n) {
/* 67 */       return SeqSplitter.class.take(this, n);
/*    */     }
/*    */     
/*    */     public SeqSplitter<T> slice(int from1, int until1) {
/* 67 */       return SeqSplitter.class.slice(this, from1, until1);
/*    */     }
/*    */     
/*    */     public <S> SeqSplitter<T>.Mapped<S> map(Function1 f) {
/* 67 */       return SeqSplitter.class.map(this, f);
/*    */     }
/*    */     
/*    */     public <U, PI extends SeqSplitter<U>> SeqSplitter<T>.Appended<U, PI> appendParSeq(SeqSplitter that) {
/* 67 */       return SeqSplitter.class.appendParSeq(this, that);
/*    */     }
/*    */     
/*    */     public <S> SeqSplitter<T>.Zipped<S> zipParSeq(SeqSplitter that) {
/* 67 */       return SeqSplitter.class.zipParSeq(this, that);
/*    */     }
/*    */     
/*    */     public <S, U, R> SeqSplitter<T>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/* 67 */       return SeqSplitter.class.zipAllParSeq(this, that, thisElem, thatElem);
/*    */     }
/*    */     
/*    */     public SeqSplitter<T> reverse() {
/* 67 */       return SeqSplitter.class.reverse(this);
/*    */     }
/*    */     
/*    */     public <U> SeqSplitter<T>.Patched<U> patchParSeq(int from, SeqSplitter patchElems, int replaced) {
/* 67 */       return SeqSplitter.class.patchParSeq(this, from, patchElems, replaced);
/*    */     }
/*    */     
/*    */     public int prefixLength(Function1 pred) {
/* 67 */       return AugmentedSeqIterator.class.prefixLength((AugmentedSeqIterator)this, pred);
/*    */     }
/*    */     
/*    */     public int indexWhere(Function1 pred) {
/* 67 */       return AugmentedSeqIterator.class.indexWhere((AugmentedSeqIterator)this, pred);
/*    */     }
/*    */     
/*    */     public int lastIndexWhere(Function1 pred) {
/* 67 */       return AugmentedSeqIterator.class.lastIndexWhere((AugmentedSeqIterator)this, pred);
/*    */     }
/*    */     
/*    */     public <S> boolean corresponds(Function2 corr, Iterator that) {
/* 67 */       return AugmentedSeqIterator.class.corresponds((AugmentedSeqIterator)this, corr, that);
/*    */     }
/*    */     
/*    */     public <U, This> Combiner<U, This> reverse2combiner(Combiner cb) {
/* 67 */       return AugmentedSeqIterator.class.reverse2combiner((AugmentedSeqIterator)this, cb);
/*    */     }
/*    */     
/*    */     public <S, That> Combiner<S, That> reverseMap2combiner(Function1 f, Combiner cb) {
/* 67 */       return AugmentedSeqIterator.class.reverseMap2combiner((AugmentedSeqIterator)this, f, cb);
/*    */     }
/*    */     
/*    */     public <U, That> Combiner<U, That> updated2combiner(int index, Object elem, Combiner cb) {
/* 67 */       return AugmentedSeqIterator.class.updated2combiner((AugmentedSeqIterator)this, index, elem, cb);
/*    */     }
/*    */     
/*    */     public Signalling signalDelegate() {
/* 67 */       return this.signalDelegate;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void signalDelegate_$eq(Signalling x$1) {
/* 67 */       this.signalDelegate = x$1;
/*    */     }
/*    */     
/*    */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/* 67 */       return IterableSplitter.class.shouldSplitFurther((IterableSplitter)this, coll, parallelismLevel);
/*    */     }
/*    */     
/*    */     public String buildString(Function1 closure) {
/* 67 */       return IterableSplitter.class.buildString((IterableSplitter)this, closure);
/*    */     }
/*    */     
/*    */     public String debugInformation() {
/* 67 */       return IterableSplitter.class.debugInformation((IterableSplitter)this);
/*    */     }
/*    */     
/*    */     public <U extends IterableSplitter<T>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/* 67 */       return (U)IterableSplitter.class.newSliceInternal((IterableSplitter)this, it, from1);
/*    */     }
/*    */     
/*    */     public <U, PI extends IterableSplitter<U>> IterableSplitter<T>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/* 67 */       return IterableSplitter.class.appendParIterable((IterableSplitter)this, that);
/*    */     }
/*    */     
/*    */     public boolean isAborted() {
/* 67 */       return DelegatedSignalling.class.isAborted((DelegatedSignalling)this);
/*    */     }
/*    */     
/*    */     public void abort() {
/* 67 */       DelegatedSignalling.class.abort((DelegatedSignalling)this);
/*    */     }
/*    */     
/*    */     public int indexFlag() {
/* 67 */       return DelegatedSignalling.class.indexFlag((DelegatedSignalling)this);
/*    */     }
/*    */     
/*    */     public void setIndexFlag(int f) {
/* 67 */       DelegatedSignalling.class.setIndexFlag((DelegatedSignalling)this, f);
/*    */     }
/*    */     
/*    */     public void setIndexFlagIfGreater(int f) {
/* 67 */       DelegatedSignalling.class.setIndexFlagIfGreater((DelegatedSignalling)this, f);
/*    */     }
/*    */     
/*    */     public void setIndexFlagIfLesser(int f) {
/* 67 */       DelegatedSignalling.class.setIndexFlagIfLesser((DelegatedSignalling)this, f);
/*    */     }
/*    */     
/*    */     public int tag() {
/* 67 */       return DelegatedSignalling.class.tag((DelegatedSignalling)this);
/*    */     }
/*    */     
/*    */     public int count(Function1 p) {
/* 67 */       return AugmentedIterableIterator.class.count((AugmentedIterableIterator)this, p);
/*    */     }
/*    */     
/*    */     public <U> U reduce(Function2 op) {
/* 67 */       return (U)AugmentedIterableIterator.class.reduce((AugmentedIterableIterator)this, op);
/*    */     }
/*    */     
/*    */     public <U> U fold(Object z, Function2 op) {
/* 67 */       return (U)AugmentedIterableIterator.class.fold((AugmentedIterableIterator)this, z, op);
/*    */     }
/*    */     
/*    */     public <U> U sum(Numeric num) {
/* 67 */       return (U)AugmentedIterableIterator.class.sum((AugmentedIterableIterator)this, num);
/*    */     }
/*    */     
/*    */     public <U> U product(Numeric num) {
/* 67 */       return (U)AugmentedIterableIterator.class.product((AugmentedIterableIterator)this, num);
/*    */     }
/*    */     
/*    */     public <U> T min(Ordering ord) {
/* 67 */       return (T)AugmentedIterableIterator.class.min((AugmentedIterableIterator)this, ord);
/*    */     }
/*    */     
/*    */     public <U> T max(Ordering ord) {
/* 67 */       return (T)AugmentedIterableIterator.class.max((AugmentedIterableIterator)this, ord);
/*    */     }
/*    */     
/*    */     public <U> void copyToArray(Object array, int from, int len) {
/* 67 */       AugmentedIterableIterator.class.copyToArray((AugmentedIterableIterator)this, array, from, len);
/*    */     }
/*    */     
/*    */     public <U> U reduceLeft(int howmany, Function2 op) {
/* 67 */       return (U)AugmentedIterableIterator.class.reduceLeft((AugmentedIterableIterator)this, howmany, op);
/*    */     }
/*    */     
/*    */     public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/* 67 */       return AugmentedIterableIterator.class.map2combiner((AugmentedIterableIterator)this, f, cb);
/*    */     }
/*    */     
/*    */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/* 67 */       return AugmentedIterableIterator.class.collect2combiner((AugmentedIterableIterator)this, pf, cb);
/*    */     }
/*    */     
/*    */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/* 67 */       return AugmentedIterableIterator.class.flatmap2combiner((AugmentedIterableIterator)this, f, cb);
/*    */     }
/*    */     
/*    */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/* 67 */       return (Bld)AugmentedIterableIterator.class.copy2builder((AugmentedIterableIterator)this, b);
/*    */     }
/*    */     
/*    */     public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/* 67 */       return AugmentedIterableIterator.class.filter2combiner((AugmentedIterableIterator)this, pred, cb);
/*    */     }
/*    */     
/*    */     public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/* 67 */       return AugmentedIterableIterator.class.filterNot2combiner((AugmentedIterableIterator)this, pred, cb);
/*    */     }
/*    */     
/*    */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/* 67 */       return AugmentedIterableIterator.class.partition2combiners((AugmentedIterableIterator)this, pred, btrue, bfalse);
/*    */     }
/*    */     
/*    */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/* 67 */       return AugmentedIterableIterator.class.take2combiner((AugmentedIterableIterator)this, n, cb);
/*    */     }
/*    */     
/*    */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/* 67 */       return AugmentedIterableIterator.class.drop2combiner((AugmentedIterableIterator)this, n, cb);
/*    */     }
/*    */     
/*    */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/* 67 */       return AugmentedIterableIterator.class.slice2combiner((AugmentedIterableIterator)this, from, until, cb);
/*    */     }
/*    */     
/*    */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/* 67 */       return AugmentedIterableIterator.class.splitAt2combiners((AugmentedIterableIterator)this, at, before, after);
/*    */     }
/*    */     
/*    */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/* 67 */       return AugmentedIterableIterator.class.takeWhile2combiner((AugmentedIterableIterator)this, p, cb);
/*    */     }
/*    */     
/*    */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/* 67 */       return AugmentedIterableIterator.class.span2combiners((AugmentedIterableIterator)this, p, before, after);
/*    */     }
/*    */     
/*    */     public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/* 67 */       AugmentedIterableIterator.class.scanToArray((AugmentedIterableIterator)this, z, op, array, from);
/*    */     }
/*    */     
/*    */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/* 67 */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, startValue, op, cb);
/*    */     }
/*    */     
/*    */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/* 67 */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, howmany, startValue, op, cb);
/*    */     }
/*    */     
/*    */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/* 67 */       return AugmentedIterableIterator.class.zip2combiner((AugmentedIterableIterator)this, otherpit, cb);
/*    */     }
/*    */     
/*    */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/* 67 */       return AugmentedIterableIterator.class.zipAll2combiner((AugmentedIterableIterator)this, that, thiselem, thatelem, cb);
/*    */     }
/*    */     
/*    */     public boolean isRemainingCheap() {
/* 67 */       return RemainsIterator.class.isRemainingCheap((RemainsIterator)this);
/*    */     }
/*    */     
/*    */     public ParVectorIterator(ParVector $outer, int _start, int _end) {
/* 67 */       super(_start, _end);
/* 67 */       RemainsIterator.class.$init$((RemainsIterator)this);
/* 67 */       AugmentedIterableIterator.class.$init$((AugmentedIterableIterator)this);
/* 67 */       DelegatedSignalling.class.$init$((DelegatedSignalling)this);
/* 67 */       IterableSplitter.class.$init$((IterableSplitter)this);
/* 67 */       AugmentedSeqIterator.class.$init$((AugmentedSeqIterator)this);
/* 67 */       SeqSplitter.class.$init$(this);
/*    */     }
/*    */     
/*    */     public int remaining() {
/* 68 */       return remainingElementCount();
/*    */     }
/*    */     
/*    */     public SeqSplitter<T> dup() {
/* 69 */       return (new ParVector<T>(remainingVector())).splitter();
/*    */     }
/*    */     
/*    */     public Seq<ParVectorIterator> split() {
/* 71 */       int rem = remaining();
/* 73 */       (new ParVectorIterator[1])[0] = this;
/* 73 */       return (rem >= 2) ? psplit((Seq<Object>)Predef$.MODULE$.wrapIntArray(new int[] { rem / 2, rem - rem / 2 })) : (Seq<ParVectorIterator>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new ParVectorIterator[1]));
/*    */     }
/*    */     
/*    */     public Seq<ParVectorIterator> psplit(Seq sizes) {
/* 76 */       ObjectRef remvector = new ObjectRef(remainingVector());
/* 77 */       ArrayBuffer splitted = new ArrayBuffer();
/* 78 */       sizes.foreach((Function1)new ParVector$ParVectorIterator$$anonfun$psplit$1(this, remvector, (ParVectorIterator)splitted));
/* 82 */       return (Seq<ParVectorIterator>)splitted.map((Function1)new ParVector$ParVectorIterator$$anonfun$psplit$2(this), ArrayBuffer$.MODULE$.canBuildFrom());
/*    */     }
/*    */     
/*    */     public class ParVector$ParVectorIterator$$anonfun$psplit$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final ObjectRef remvector$1;
/*    */       
/*    */       private final ArrayBuffer splitted$1;
/*    */       
/*    */       public final void apply(int sz) {
/*    */         apply$mcVI$sp(sz);
/*    */       }
/*    */       
/*    */       public ParVector$ParVectorIterator$$anonfun$psplit$1(ParVector.ParVectorIterator $outer, ObjectRef remvector$1, ArrayBuffer splitted$1) {}
/*    */       
/*    */       public void apply$mcVI$sp(int sz) {
/*    */         this.splitted$1.$plus$eq(((Vector)this.remvector$1.elem).take(sz));
/*    */         this.remvector$1.elem = ((Vector)this.remvector$1.elem).drop(sz);
/*    */       }
/*    */     }
/*    */     
/*    */     public class ParVector$ParVectorIterator$$anonfun$psplit$2 extends AbstractFunction1<Vector<T>, ParVectorIterator> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final ParVector<T>.ParVectorIterator apply(Vector<?> v) {
/* 82 */         return (ParVector.ParVectorIterator)(new ParVector(v)).splitter();
/*    */       }
/*    */       
/*    */       public ParVector$ParVectorIterator$$anonfun$psplit$2(ParVector.ParVectorIterator $outer) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */