/*     */ package scala.collection.parallel.immutable;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.BufferedIterator;
/*     */ import scala.collection.CustomParallelizable;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSeqLike;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Parallel;
/*     */ import scala.collection.Parallelizable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.DelegatedSignalling;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericParTemplate;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.generic.Signalling;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.AugmentedIterableIterator;
/*     */ import scala.collection.parallel.AugmentedSeqIterator;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.IterableSplitter;
/*     */ import scala.collection.parallel.ParIterable;
/*     */ import scala.collection.parallel.ParIterableLike;
/*     */ import scala.collection.parallel.ParIterableView;
/*     */ import scala.collection.parallel.ParSeq;
/*     */ import scala.collection.parallel.ParSeqLike;
/*     */ import scala.collection.parallel.PreciseSplitter;
/*     */ import scala.collection.parallel.RemainsIterator;
/*     */ import scala.collection.parallel.SeqSplitter;
/*     */ import scala.collection.parallel.Splitter;
/*     */ import scala.collection.parallel.TaskSupport;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005mg\001B\001\003\001-\021\001\002U1s%\006tw-\032\006\003\007\021\t\021\"[7nkR\f'\r\\3\013\005\0251\021\001\0039be\006dG.\0327\013\005\035A\021AC2pY2,7\r^5p]*\t\021\"A\003tG\006d\027m\001\001\024\t\001a\001c\006\t\003\0339i\021\001C\005\003\037!\021a!\0218z%\0264\007cA\t\023)5\t!!\003\002\024\005\t1\001+\031:TKF\004\"!D\013\n\005YA!aA%oiB\021Q\002G\005\0033!\021AbU3sS\006d\027N_1cY\026D\001b\007\001\003\006\004%\t\001H\001\006e\006tw-Z\013\002;A\021a\004I\007\002?)\0211AB\005\003C}\021QAU1oO\026D\001b\t\001\003\002\003\006I!H\001\007e\006tw-\032\021\t\013\025\002A\021\001\024\002\rqJg.\033;?)\t9\003\006\005\002\022\001!)1\004\na\001;!)!\006\001C!9\005\0311/Z9\t\0131\002AQA\027\002\r1,gn\032;i+\005!\002FA\0260!\ti\001'\003\0022\021\t1\021N\0347j]\026DQa\r\001\005\006Q\nQ!\0319qYf$\"\001F\033\t\013Y\022\004\031\001\013\002\007%$\007\020\013\0023_!)\021\b\001C\001u\005A1\017\0357jiR,'/F\001<!\taT(D\001\001\r\021q\004\001A \003!A\013'OU1oO\026LE/\032:bi>\0248cA\037\r\001B\031\021I\021\013\016\003\021I!a\021\003\003\027M+\027o\0259mSR$XM\035\005\t7u\022\t\021)A\005;!)Q%\020C\001\rR\0211h\022\005\b7\025\003\n\0211\001\036\021\025IU\b\"\021K\003!!xn\025;sS:<G#A&\021\0051\013V\"A'\013\0059{\025\001\0027b]\036T\021\001U\001\005U\0064\030-\003\002S\033\n11\013\036:j]\036Dq\001V\037A\002\023%Q&A\002j]\022DqAV\037A\002\023%q+A\004j]\022|F%Z9\025\005a[\006CA\007Z\023\tQ\006B\001\003V]&$\bb\002/V\003\003\005\r\001F\001\004q\022\n\004B\0020>A\003&A#\001\003j]\022\004\003b\0021>\005\004%I!L\001\004Y\026t\007B\0022>A\003%A#\001\003mK:\004\003\"\0023>\t\013i\023!\003:f[\006Lg.\0338h\021\0251W\b\"\002h\003\035A\027m\035(fqR,\022\001\033\t\003\033%L!A\033\005\003\017\t{w\016\\3b]\")A.\020C\003[\006!a.\032=u)\005!\002\"B8>\t\023a\022!\003:b]\036,G.\0324u\021\025\tX\b\"\001;\003\r!W\017\035\005\006gv\"\t\001^\001\006gBd\027\016^\013\002kB\031ao^\036\016\003\031I!\001\037\004\003\007M+\027\017C\003{{\021\00510\001\004qgBd\027\016\036\013\004y\006=\001\003B?\002\f\001s1A`A\004\035\ry\030QA\007\003\003\003Q1!a\001\013\003\031a$o\\8u}%\t\021\"C\002\002\n!\tq\001]1dW\006<W-C\002y\003\033Q1!!\003\t\021\035\t\t\"\037a\001\003'\tQa]5{KN\004B!DA\013)%\031\021q\003\005\003\025q\022X\r]3bi\026$g\bC\004\002\034u\"\t%!\b\002\017\031|'/Z1dQV!\021qDA\030)\rA\026\021\005\005\t\003G\tI\0021\001\002&\005\ta\r\005\004\016\003O!\0221F\005\004\003SA!!\003$v]\016$\030n\03482!\021\ti#a\f\r\001\021A\021\021GA\r\005\004\t\031DA\001V#\021\t)$a\017\021\0075\t9$C\002\002:!\021qAT8uQ&tw\rE\002\016\003{I1!a\020\t\005\r\te.\037\005\b\003\007jD\021IA#\003\031\021X\rZ;dKV!\021qIA&)\021\tI%a\024\021\t\0055\0221\n\003\t\003c\t\tE1\001\002NE\031A#a\017\t\021\005E\023\021\ta\001\003'\n!a\0349\021\0235\t)&!\023\002J\005%\023bAA,\021\tIa)\0368di&|gN\r\005\b\0037jD\021IA/\0031i\027\r\035\032d_6\024\027N\\3s+\031\ty&!\033\002pQ1\021\021MA:\003o\002r!QA2\003O\ni'C\002\002f\021\021\001bQ8nE&tWM\035\t\005\003[\tI\007\002\005\002l\005e#\031AA\032\005\005\031\006\003BA\027\003_\"\001\"!\035\002Z\t\007\0211\007\002\005)\"\fG\017\003\005\002$\005e\003\031AA;!\031i\021q\005\013\002h!A\021\021PA-\001\004\t\t'\001\002dE\036I\021Q\020\001\002\002#\005\021qP\001\021!\006\024(+\0318hK&#XM]1u_J\0042\001PAA\r!q\004!!A\t\002\005\r5cAAA\031!9Q%!!\005\002\005\035ECAA@\021)\tY)!!\022\002\023\005\021QR\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017J\031\026\005\005=%fA\017\002\022.\022\0211\023\t\005\003+\013y*\004\002\002\030*!\021\021TAN\003%)hn\0315fG.,GMC\002\002\036\"\t!\"\0318o_R\fG/[8o\023\021\t\t+a&\003#Ut7\r[3dW\026$g+\031:jC:\034W\rK\003\001\003K\013Y\013E\002\016\003OK1!!+\t\005A\031VM]5bYZ+'o]5p]VKEIH\001\002\017\035\tyK\001E\001\003c\013\001\002U1s%\006tw-\032\t\004#\005MfAB\001\003\021\003\t)l\005\003\002429\002bB\023\0024\022\005\021\021\030\013\003\003cCqaMAZ\t\003\ti\fF\005(\003\013\031-a2\002L\"9\021\021YA^\001\004!\022!B:uCJ$\bbBAc\003w\003\r\001F\001\004K:$\007bBAe\003w\003\r\001F\001\005gR,\007\017C\004\002N\006m\006\031\0015\002\023%t7\r\\;tSZ,\007BCAi\003g\013\t\021\"\003\002T\006Y!/Z1e%\026\034x\016\034<f)\t\t)\016E\002M\003/L1!!7N\005\031y%M[3di\002")
/*     */ public class ParRange implements ParSeq<Object>, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Range range;
/*     */   
/*     */   private volatile ParRangeIterator$ ParRangeIterator$module;
/*     */   
/*     */   private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */   
/*     */   private volatile scala.collection.parallel.ParIterableLike$ScanNode$ ScanNode$module;
/*     */   
/*     */   private volatile scala.collection.parallel.ParIterableLike$ScanLeaf$ ScanLeaf$module;
/*     */   
/*     */   public GenericCompanion<ParSeq> companion() {
/*  35 */     return ParSeq$class.companion(this);
/*     */   }
/*     */   
/*     */   public ParSeq<Object> toSeq() {
/*  35 */     return ParSeq$class.toSeq(this);
/*     */   }
/*     */   
/*     */   public ParIterable<Object> toIterable() {
/*  35 */     return ParIterable$class.toIterable(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  35 */     return ParSeq.class.toString(this);
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/*  35 */     return ParSeq.class.stringPrefix(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$parallel$ParSeqLike$$super$zip(GenIterable that, CanBuildFrom bf) {
/*  35 */     return ParIterableLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public PreciseSplitter<Object> iterator() {
/*  35 */     return ParSeqLike.class.iterator(this);
/*     */   }
/*     */   
/*     */   public int size() {
/*  35 */     return ParSeqLike.class.size(this);
/*     */   }
/*     */   
/*     */   public int segmentLength(Function1 p, int from) {
/*  35 */     return ParSeqLike.class.segmentLength(this, p, from);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p, int from) {
/*  35 */     return ParSeqLike.class.indexWhere(this, p, from);
/*     */   }
/*     */   
/*     */   public int lastIndexWhere(Function1 p, int end) {
/*  35 */     return ParSeqLike.class.lastIndexWhere(this, p, end);
/*     */   }
/*     */   
/*     */   public ParSeq<Object> reverse() {
/*  35 */     return (ParSeq<Object>)ParSeqLike.class.reverse(this);
/*     */   }
/*     */   
/*     */   public <S, That> That reverseMap(Function1 f, CanBuildFrom bf) {
/*  35 */     return (That)ParSeqLike.class.reverseMap(this, f, bf);
/*     */   }
/*     */   
/*     */   public <S> boolean startsWith(GenSeq that, int offset) {
/*  35 */     return ParSeqLike.class.startsWith(this, that, offset);
/*     */   }
/*     */   
/*     */   public <U> boolean sameElements(GenIterable that) {
/*  35 */     return ParSeqLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public <S> boolean endsWith(GenSeq that) {
/*  35 */     return ParSeqLike.class.endsWith(this, that);
/*     */   }
/*     */   
/*     */   public <U, That> That patch(int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/*  35 */     return (That)ParSeqLike.class.patch(this, from, patch, replaced, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That updated(int index, Object elem, CanBuildFrom bf) {
/*  35 */     return (That)ParSeqLike.class.updated(this, index, elem, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/*  35 */     return (That)ParSeqLike.class.$plus$colon(this, elem, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/*  35 */     return (That)ParSeqLike.class.$colon$plus(this, elem, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/*  35 */     return (That)ParSeqLike.class.padTo(this, len, elem, bf);
/*     */   }
/*     */   
/*     */   public <U, S, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  35 */     return (That)ParSeqLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public <S> boolean corresponds(GenSeq that, Function2 p) {
/*  35 */     return ParSeqLike.class.corresponds(this, that, p);
/*     */   }
/*     */   
/*     */   public <U> ParSeq<Object> diff(GenSeq that) {
/*  35 */     return (ParSeq<Object>)ParSeqLike.class.diff(this, that);
/*     */   }
/*     */   
/*     */   public <U> ParSeq<Object> intersect(GenSeq that) {
/*  35 */     return (ParSeq<Object>)ParSeqLike.class.intersect(this, that);
/*     */   }
/*     */   
/*     */   public ParSeq<Object> distinct() {
/*  35 */     return (ParSeq<Object>)ParSeqLike.class.distinct(this);
/*     */   }
/*     */   
/*     */   public Object view() {
/*  35 */     return ParSeqLike.class.view(this);
/*     */   }
/*     */   
/*     */   public SeqSplitter<Object> down(IterableSplitter p) {
/*  35 */     return ParSeqLike.class.down(this, p);
/*     */   }
/*     */   
/*     */   public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
/*  35 */     return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
/*  35 */     this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
/*     */   }
/*     */   
/*     */   private scala.collection.parallel.ParIterableLike$ScanNode$ ScanNode$lzycompute() {
/*  35 */     synchronized (this) {
/*  35 */       if (this.ScanNode$module == null)
/*  35 */         this.ScanNode$module = new scala.collection.parallel.ParIterableLike$ScanNode$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/immutable/ParRange}} */
/*  35 */       return this.ScanNode$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public scala.collection.parallel.ParIterableLike$ScanNode$ ScanNode() {
/*  35 */     return (this.ScanNode$module == null) ? ScanNode$lzycompute() : this.ScanNode$module;
/*     */   }
/*     */   
/*     */   private scala.collection.parallel.ParIterableLike$ScanLeaf$ ScanLeaf$lzycompute() {
/*  35 */     synchronized (this) {
/*  35 */       if (this.ScanLeaf$module == null)
/*  35 */         this.ScanLeaf$module = new scala.collection.parallel.ParIterableLike$ScanLeaf$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/immutable/ParRange}} */
/*  35 */       return this.ScanLeaf$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public scala.collection.parallel.ParIterableLike$ScanLeaf$ ScanLeaf() {
/*  35 */     return (this.ScanLeaf$module == null) ? ScanLeaf$lzycompute() : this.ScanLeaf$module;
/*     */   }
/*     */   
/*     */   public void initTaskSupport() {
/*  35 */     ParIterableLike.class.initTaskSupport(this);
/*     */   }
/*     */   
/*     */   public TaskSupport tasksupport() {
/*  35 */     return ParIterableLike.class.tasksupport(this);
/*     */   }
/*     */   
/*     */   public void tasksupport_$eq(TaskSupport ts) {
/*  35 */     ParIterableLike.class.tasksupport_$eq(this, ts);
/*     */   }
/*     */   
/*     */   public ParSeq<Object> repr() {
/*  35 */     return (ParSeq<Object>)ParIterableLike.class.repr(this);
/*     */   }
/*     */   
/*     */   public final boolean isTraversableAgain() {
/*  35 */     return ParIterableLike.class.isTraversableAgain(this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/*  35 */     return ParIterableLike.class.hasDefiniteSize(this);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  35 */     return ParIterableLike.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/*  35 */     return ParIterableLike.class.nonEmpty(this);
/*     */   }
/*     */   
/*     */   public Object head() {
/*  35 */     return ParIterableLike.class.head(this);
/*     */   }
/*     */   
/*     */   public Option<Object> headOption() {
/*  35 */     return ParIterableLike.class.headOption(this);
/*     */   }
/*     */   
/*     */   public ParSeq<Object> tail() {
/*  35 */     return (ParSeq<Object>)ParIterableLike.class.tail(this);
/*     */   }
/*     */   
/*     */   public Object last() {
/*  35 */     return ParIterableLike.class.last(this);
/*     */   }
/*     */   
/*     */   public Option<Object> lastOption() {
/*  35 */     return ParIterableLike.class.lastOption(this);
/*     */   }
/*     */   
/*     */   public ParSeq<Object> init() {
/*  35 */     return (ParSeq<Object>)ParIterableLike.class.init(this);
/*     */   }
/*     */   
/*     */   public ParSeq<Object> par() {
/*  35 */     return (ParSeq<Object>)ParIterableLike.class.par(this);
/*     */   }
/*     */   
/*     */   public boolean isStrictSplitterCollection() {
/*  35 */     return ParIterableLike.class.isStrictSplitterCollection(this);
/*     */   }
/*     */   
/*     */   public <S, That> Combiner<S, That> reuse(Option oldc, Combiner newc) {
/*  35 */     return ParIterableLike.class.reuse(this, oldc, newc);
/*     */   }
/*     */   
/*     */   public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask tsk) {
/*  35 */     return ParIterableLike.class.task2ops(this, tsk);
/*     */   }
/*     */   
/*     */   public <R> Object wrap(Function0 body) {
/*  35 */     return ParIterableLike.class.wrap(this, body);
/*     */   }
/*     */   
/*     */   public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(DelegatedSignalling it) {
/*  35 */     return ParIterableLike.class.delegatedSignalling2ops(this, it);
/*     */   }
/*     */   
/*     */   public <Elem, To> Object builder2ops(Builder cb) {
/*  35 */     return ParIterableLike.class.builder2ops(this, cb);
/*     */   }
/*     */   
/*     */   public <S, That> Object bf2seq(CanBuildFrom bf) {
/*  35 */     return ParIterableLike.class.bf2seq(this, bf);
/*     */   }
/*     */   
/*     */   public <S, That extends Parallel> ParSeq<Object> sequentially(Function1 b) {
/*  35 */     return (ParSeq<Object>)ParIterableLike.class.sequentially(this, b);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/*  35 */     return ParIterableLike.class.mkString(this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/*  35 */     return ParIterableLike.class.mkString(this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/*  35 */     return ParIterableLike.class.mkString(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object other) {
/*  35 */     return ParIterableLike.class.canEqual(this, other);
/*     */   }
/*     */   
/*     */   public <U> U reduce(Function2 op) {
/*  35 */     return (U)ParIterableLike.class.reduce(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceOption(Function2 op) {
/*  35 */     return ParIterableLike.class.reduceOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> U fold(Object z, Function2 op) {
/*  35 */     return (U)ParIterableLike.class.fold(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S aggregate(Object z, Function2 seqop, Function2 combop) {
/*  35 */     return (S)ParIterableLike.class.aggregate(this, z, seqop, combop);
/*     */   }
/*     */   
/*     */   public <S> S foldLeft(Object z, Function2 op) {
/*  35 */     return (S)ParIterableLike.class.foldLeft(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S foldRight(Object z, Function2 op) {
/*  35 */     return (S)ParIterableLike.class.foldRight(this, z, op);
/*     */   }
/*     */   
/*     */   public <U> U reduceLeft(Function2 op) {
/*  35 */     return (U)ParIterableLike.class.reduceLeft(this, op);
/*     */   }
/*     */   
/*     */   public <U> U reduceRight(Function2 op) {
/*  35 */     return (U)ParIterableLike.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceLeftOption(Function2 op) {
/*  35 */     return ParIterableLike.class.reduceLeftOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceRightOption(Function2 op) {
/*  35 */     return ParIterableLike.class.reduceRightOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/*  35 */     ParIterableLike.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/*  35 */     return ParIterableLike.class.count(this, p);
/*     */   }
/*     */   
/*     */   public <U> U sum(Numeric num) {
/*  35 */     return (U)ParIterableLike.class.sum(this, num);
/*     */   }
/*     */   
/*     */   public <U> U product(Numeric num) {
/*  35 */     return (U)ParIterableLike.class.product(this, num);
/*     */   }
/*     */   
/*     */   public <U> int min(Ordering ord) {
/*  35 */     return ParIterableLike.class.min(this, ord);
/*     */   }
/*     */   
/*     */   public <U> int max(Ordering ord) {
/*  35 */     return ParIterableLike.class.max(this, ord);
/*     */   }
/*     */   
/*     */   public <S> int maxBy(Function1 f, Ordering cmp) {
/*  35 */     return ParIterableLike.class.maxBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <S> int minBy(Function1 f, Ordering cmp) {
/*  35 */     return ParIterableLike.class.minBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <S, That> That map(Function1 f, CanBuildFrom bf) {
/*  35 */     return (That)ParIterableLike.class.map(this, f, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  35 */     return (That)ParIterableLike.class.collect(this, pf, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  35 */     return (That)ParIterableLike.class.flatMap(this, f, bf);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 pred) {
/*  35 */     return ParIterableLike.class.forall(this, pred);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 pred) {
/*  35 */     return ParIterableLike.class.exists(this, pred);
/*     */   }
/*     */   
/*     */   public Option<Object> find(Function1 pred) {
/*  35 */     return ParIterableLike.class.find(this, pred);
/*     */   }
/*     */   
/*     */   public Object combinerFactory() {
/*  35 */     return ParIterableLike.class.combinerFactory(this);
/*     */   }
/*     */   
/*     */   public <S, That> Object combinerFactory(Function0 cbf) {
/*  35 */     return ParIterableLike.class.combinerFactory(this, cbf);
/*     */   }
/*     */   
/*     */   public ParSeq<Object> filter(Function1 pred) {
/*  35 */     return (ParSeq<Object>)ParIterableLike.class.filter(this, pred);
/*     */   }
/*     */   
/*     */   public ParSeq<Object> filterNot(Function1 pred) {
/*  35 */     return (ParSeq<Object>)ParIterableLike.class.filterNot(this, pred);
/*     */   }
/*     */   
/*     */   public <U, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/*  35 */     return (That)ParIterableLike.class.$plus$plus(this, that, bf);
/*     */   }
/*     */   
/*     */   public Tuple2<ParSeq<Object>, ParSeq<Object>> partition(Function1 pred) {
/*  35 */     return ParIterableLike.class.partition(this, pred);
/*     */   }
/*     */   
/*     */   public <K> ParMap<K, ParSeq<Object>> groupBy(Function1 f) {
/*  35 */     return ParIterableLike.class.groupBy(this, f);
/*     */   }
/*     */   
/*     */   public ParSeq<Object> take(int n) {
/*  35 */     return (ParSeq<Object>)ParIterableLike.class.take(this, n);
/*     */   }
/*     */   
/*     */   public ParSeq<Object> drop(int n) {
/*  35 */     return (ParSeq<Object>)ParIterableLike.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public ParSeq<Object> slice(int unc_from, int unc_until) {
/*  35 */     return (ParSeq<Object>)ParIterableLike.class.slice(this, unc_from, unc_until);
/*     */   }
/*     */   
/*     */   public Tuple2<ParSeq<Object>, ParSeq<Object>> splitAt(int n) {
/*  35 */     return ParIterableLike.class.splitAt(this, n);
/*     */   }
/*     */   
/*     */   public <U, That> That scan(Object z, Function2 op, CanBuildFrom bf) {
/*  35 */     return (That)ParIterableLike.class.scan(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  35 */     return (That)ParIterableLike.class.scanLeft(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  35 */     return (That)ParIterableLike.class.scanRight(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public ParSeq<Object> takeWhile(Function1 pred) {
/*  35 */     return (ParSeq<Object>)ParIterableLike.class.takeWhile(this, pred);
/*     */   }
/*     */   
/*     */   public Tuple2<ParSeq<Object>, ParSeq<Object>> span(Function1 pred) {
/*  35 */     return ParIterableLike.class.span(this, pred);
/*     */   }
/*     */   
/*     */   public ParSeq<Object> dropWhile(Function1 pred) {
/*  35 */     return (ParSeq<Object>)ParIterableLike.class.dropWhile(this, pred);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs) {
/*  35 */     ParIterableLike.class.copyToArray(this, xs);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs, int start) {
/*  35 */     ParIterableLike.class.copyToArray(this, xs, start);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs, int start, int len) {
/*  35 */     ParIterableLike.class.copyToArray(this, xs, start, len);
/*     */   }
/*     */   
/*     */   public <U, That> That zipWithIndex(CanBuildFrom bf) {
/*  35 */     return (That)ParIterableLike.class.zipWithIndex(this, bf);
/*     */   }
/*     */   
/*     */   public <S, U, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  35 */     return (That)ParIterableLike.class.zipAll(this, that, thisElem, thatElem, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That toParCollection(Function0 cbf) {
/*  35 */     return (That)ParIterableLike.class.toParCollection(this, cbf);
/*     */   }
/*     */   
/*     */   public <K, V, That> That toParMap(Function0 cbf, scala.Predef$.less.colon.less ev) {
/*  35 */     return (That)ParIterableLike.class.toParMap(this, cbf, ev);
/*     */   }
/*     */   
/*     */   public <U> Object toArray(ClassTag evidence$1) {
/*  35 */     return ParIterableLike.class.toArray(this, evidence$1);
/*     */   }
/*     */   
/*     */   public List<Object> toList() {
/*  35 */     return ParIterableLike.class.toList(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<Object> toIndexedSeq() {
/*  35 */     return ParIterableLike.class.toIndexedSeq(this);
/*     */   }
/*     */   
/*     */   public Stream<Object> toStream() {
/*  35 */     return ParIterableLike.class.toStream(this);
/*     */   }
/*     */   
/*     */   public Iterator<Object> toIterator() {
/*  35 */     return ParIterableLike.class.toIterator(this);
/*     */   }
/*     */   
/*     */   public <U> Buffer<U> toBuffer() {
/*  35 */     return ParIterableLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public GenTraversable<Object> toTraversable() {
/*  35 */     return ParIterableLike.class.toTraversable(this);
/*     */   }
/*     */   
/*     */   public <U> ParSet<U> toSet() {
/*  35 */     return ParIterableLike.class.toSet(this);
/*     */   }
/*     */   
/*     */   public <K, V> ParMap<K, V> toMap(scala.Predef$.less.colon.less ev) {
/*  35 */     return ParIterableLike.class.toMap(this, ev);
/*     */   }
/*     */   
/*     */   public Vector<Object> toVector() {
/*  35 */     return ParIterableLike.class.toVector(this);
/*     */   }
/*     */   
/*     */   public <Col> Col to(CanBuildFrom cbf) {
/*  35 */     return (Col)ParIterableLike.class.to(this, cbf);
/*     */   }
/*     */   
/*     */   public int scanBlockSize() {
/*  35 */     return ParIterableLike.class.scanBlockSize(this);
/*     */   }
/*     */   
/*     */   public <S> S $div$colon(Object z, Function2 op) {
/*  35 */     return (S)ParIterableLike.class.$div$colon(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S $colon$bslash(Object z, Function2 op) {
/*  35 */     return (S)ParIterableLike.class.$colon$bslash(this, z, op);
/*     */   }
/*     */   
/*     */   public String debugInformation() {
/*  35 */     return ParIterableLike.class.debugInformation(this);
/*     */   }
/*     */   
/*     */   public Seq<String> brokenInvariants() {
/*  35 */     return ParIterableLike.class.brokenInvariants(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<String> debugBuffer() {
/*  35 */     return ParIterableLike.class.debugBuffer(this);
/*     */   }
/*     */   
/*     */   public void debugclear() {
/*  35 */     ParIterableLike.class.debugclear(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<String> debuglog(String s) {
/*  35 */     return ParIterableLike.class.debuglog(this, s);
/*     */   }
/*     */   
/*     */   public void printDebugBuffer() {
/*  35 */     ParIterableLike.class.printDebugBuffer(this);
/*     */   }
/*     */   
/*     */   public Combiner<Object, ParSeq<Object>> parCombiner() {
/*  35 */     return CustomParallelizable.class.parCombiner((CustomParallelizable)this);
/*     */   }
/*     */   
/*     */   public Builder<Object, ParSeq<Object>> newBuilder() {
/*  35 */     return GenericParTemplate.class.newBuilder(this);
/*     */   }
/*     */   
/*     */   public Combiner<Object, ParSeq<Object>> newCombiner() {
/*  35 */     return GenericParTemplate.class.newCombiner(this);
/*     */   }
/*     */   
/*     */   public <B> Combiner<B, ParSeq<B>> genericBuilder() {
/*  35 */     return GenericParTemplate.class.genericBuilder(this);
/*     */   }
/*     */   
/*     */   public <B> Combiner<B, ParSeq<B>> genericCombiner() {
/*  35 */     return GenericParTemplate.class.genericCombiner(this);
/*     */   }
/*     */   
/*     */   public <A1, A2> Tuple2<ParSeq<A1>, ParSeq<A2>> unzip(Function1 asPair) {
/*  35 */     return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*     */   }
/*     */   
/*     */   public <A1, A2, A3> Tuple3<ParSeq<A1>, ParSeq<A2>, ParSeq<A3>> unzip3(Function1 asTriple) {
/*  35 */     return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*     */   }
/*     */   
/*     */   public <B> ParSeq<B> flatten(Function1 asTraversable) {
/*  35 */     return (ParSeq<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public <B> ParSeq<ParSeq<B>> transpose(Function1 asTraversable) {
/*  35 */     return (ParSeq<ParSeq<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public boolean isDefinedAt(int idx) {
/*  35 */     return GenSeqLike.class.isDefinedAt((GenSeqLike)this, idx);
/*     */   }
/*     */   
/*     */   public int prefixLength(Function1 p) {
/*  35 */     return GenSeqLike.class.prefixLength((GenSeqLike)this, p);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p) {
/*  35 */     return GenSeqLike.class.indexWhere((GenSeqLike)this, p);
/*     */   }
/*     */   
/*     */   public <B> int indexOf(Object elem) {
/*  35 */     return GenSeqLike.class.indexOf((GenSeqLike)this, elem);
/*     */   }
/*     */   
/*     */   public <B> int indexOf(Object elem, int from) {
/*  35 */     return GenSeqLike.class.indexOf((GenSeqLike)this, elem, from);
/*     */   }
/*     */   
/*     */   public <B> int lastIndexOf(Object elem) {
/*  35 */     return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem);
/*     */   }
/*     */   
/*     */   public <B> int lastIndexOf(Object elem, int end) {
/*  35 */     return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem, end);
/*     */   }
/*     */   
/*     */   public int lastIndexWhere(Function1 p) {
/*  35 */     return GenSeqLike.class.lastIndexWhere((GenSeqLike)this, p);
/*     */   }
/*     */   
/*     */   public <B> boolean startsWith(GenSeq that) {
/*  35 */     return GenSeqLike.class.startsWith((GenSeqLike)this, that);
/*     */   }
/*     */   
/*     */   public <B, That> That union(GenSeq that, CanBuildFrom bf) {
/*  35 */     return (That)GenSeqLike.class.union((GenSeqLike)this, that, bf);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  35 */     return GenSeqLike.class.hashCode((GenSeqLike)this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*  35 */     return GenSeqLike.class.equals((GenSeqLike)this, that);
/*     */   }
/*     */   
/*     */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  35 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public Range range() {
/*  35 */     return this.range;
/*     */   }
/*     */   
/*     */   public ParRange(Range range) {
/*  35 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  35 */     Parallelizable.class.$init$((Parallelizable)this);
/*  35 */     GenSeqLike.class.$init$((GenSeqLike)this);
/*  35 */     GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/*  35 */     GenTraversable.class.$init$((GenTraversable)this);
/*  35 */     GenIterable.class.$init$(this);
/*  35 */     GenSeq.class.$init$(this);
/*  35 */     GenericParTemplate.class.$init$(this);
/*  35 */     CustomParallelizable.class.$init$((CustomParallelizable)this);
/*  35 */     ParIterableLike.class.$init$(this);
/*  35 */     ParIterable.class.$init$(this);
/*  35 */     ParSeqLike.class.$init$(this);
/*  35 */     ParSeq.class.$init$(this);
/*  35 */     ParIterable$class.$init$(this);
/*  35 */     ParSeq$class.$init$(this);
/*     */   }
/*     */   
/*     */   public Range seq() {
/*  41 */     return range();
/*     */   }
/*     */   
/*     */   public final int length() {
/*  43 */     return range().length();
/*     */   }
/*     */   
/*     */   public final int apply(int idx) {
/*  45 */     return range().apply$mcII$sp(idx);
/*     */   }
/*     */   
/*     */   public ParRangeIterator splitter() {
/*  47 */     return new ParRangeIterator(this, ParRangeIterator().$lessinit$greater$default$1());
/*     */   }
/*     */   
/*     */   private ParRangeIterator$ ParRangeIterator$lzycompute() {
/*  49 */     synchronized (this) {
/*  49 */       if (this.ParRangeIterator$module == null)
/*  49 */         this.ParRangeIterator$module = new ParRangeIterator$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/immutable/ParRange}} */
/*  49 */       return this.ParRangeIterator$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParRangeIterator$ ParRangeIterator() {
/*  49 */     return (this.ParRangeIterator$module == null) ? ParRangeIterator$lzycompute() : this.ParRangeIterator$module;
/*     */   }
/*     */   
/*     */   public class ParRangeIterator$ {
/*     */     public Range $lessinit$greater$default$1() {
/*  49 */       return this.$outer.range();
/*     */     }
/*     */     
/*     */     public ParRangeIterator$(ParRange $outer) {}
/*     */     
/*     */     public class ParRange$ParRangeIterator$$anonfun$psplit$1 extends AbstractFunction1<Object, ParRange.ParRangeIterator> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final ObjectRef rleft$1;
/*     */       
/*     */       public ParRange$ParRangeIterator$$anonfun$psplit$1(ParRange.ParRangeIterator $outer, ObjectRef rleft$1) {}
/*     */       
/*     */       public final ParRange.ParRangeIterator apply(int sz) {
/*  82 */         Range fronttaken = ((Range)this.rleft$1.elem).take(sz);
/*  83 */         this.rleft$1.elem = ((Range)this.rleft$1.elem).drop(sz);
/*  84 */         return new ParRange.ParRangeIterator(this.$outer.scala$collection$parallel$immutable$ParRange$ParRangeIterator$$$outer(), fronttaken);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class ParRangeIterator implements SeqSplitter<Object> {
/*     */     private final Range range;
/*     */     
/*     */     private int ind;
/*     */     
/*     */     private final int len;
/*     */     
/*     */     private Signalling signalDelegate;
/*     */     
/*     */     public Seq<SeqSplitter<Object>> splitWithSignalling() {
/*     */       return SeqSplitter.class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<Object>> psplitWithSignalling(Seq sizes) {
/*     */       return SeqSplitter.class.psplitWithSignalling(this, sizes);
/*     */     }
/*     */     
/*     */     public SeqSplitter<Object>.Taken newTaken(int until) {
/*     */       return SeqSplitter.class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public SeqSplitter<Object> take(int n) {
/*     */       return SeqSplitter.class.take(this, n);
/*     */     }
/*     */     
/*     */     public SeqSplitter<Object> slice(int from1, int until1) {
/*     */       return SeqSplitter.class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> SeqSplitter<Object>.Mapped<S> map(Function1 f) {
/*     */       return SeqSplitter.class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends SeqSplitter<U>> SeqSplitter<Object>.Appended<U, PI> appendParSeq(SeqSplitter that) {
/*     */       return SeqSplitter.class.appendParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S> SeqSplitter<Object>.Zipped<S> zipParSeq(SeqSplitter that) {
/*     */       return SeqSplitter.class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> SeqSplitter<Object>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/*     */       return SeqSplitter.class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public SeqSplitter<Object> reverse() {
/*     */       return SeqSplitter.class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> SeqSplitter<Object>.Patched<U> patchParSeq(int from, SeqSplitter patchElems, int replaced) {
/*     */       return SeqSplitter.class.patchParSeq(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public int prefixLength(Function1 pred) {
/*     */       return AugmentedSeqIterator.class.prefixLength((AugmentedSeqIterator)this, pred);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 pred) {
/*     */       return AugmentedSeqIterator.class.indexWhere((AugmentedSeqIterator)this, pred);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 pred) {
/*     */       return AugmentedSeqIterator.class.lastIndexWhere((AugmentedSeqIterator)this, pred);
/*     */     }
/*     */     
/*     */     public <S> boolean corresponds(Function2 corr, Iterator that) {
/*     */       return AugmentedSeqIterator.class.corresponds((AugmentedSeqIterator)this, corr, that);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> reverse2combiner(Combiner cb) {
/*     */       return AugmentedSeqIterator.class.reverse2combiner((AugmentedSeqIterator)this, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> reverseMap2combiner(Function1 f, Combiner cb) {
/*     */       return AugmentedSeqIterator.class.reverseMap2combiner((AugmentedSeqIterator)this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> updated2combiner(int index, Object elem, Combiner cb) {
/*     */       return AugmentedSeqIterator.class.updated2combiner((AugmentedSeqIterator)this, index, elem, cb);
/*     */     }
/*     */     
/*     */     public Signalling signalDelegate() {
/*     */       return this.signalDelegate;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void signalDelegate_$eq(Signalling x$1) {
/*     */       this.signalDelegate = x$1;
/*     */     }
/*     */     
/*     */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/*     */       return IterableSplitter.class.shouldSplitFurther((IterableSplitter)this, coll, parallelismLevel);
/*     */     }
/*     */     
/*     */     public String buildString(Function1 closure) {
/*     */       return IterableSplitter.class.buildString((IterableSplitter)this, closure);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/*     */       return IterableSplitter.class.debugInformation((IterableSplitter)this);
/*     */     }
/*     */     
/*     */     public <U extends IterableSplitter<Object>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/*     */       return (U)IterableSplitter.class.newSliceInternal((IterableSplitter)this, it, from1);
/*     */     }
/*     */     
/*     */     public <U, PI extends IterableSplitter<U>> IterableSplitter<Object>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/*     */       return IterableSplitter.class.appendParIterable((IterableSplitter)this, that);
/*     */     }
/*     */     
/*     */     public boolean isAborted() {
/*     */       return DelegatedSignalling.class.isAborted((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public void abort() {
/*     */       DelegatedSignalling.class.abort((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public int indexFlag() {
/*     */       return DelegatedSignalling.class.indexFlag((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public void setIndexFlag(int f) {
/*     */       DelegatedSignalling.class.setIndexFlag((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfGreater(int f) {
/*     */       DelegatedSignalling.class.setIndexFlagIfGreater((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfLesser(int f) {
/*     */       DelegatedSignalling.class.setIndexFlagIfLesser((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public int tag() {
/*     */       return DelegatedSignalling.class.tag((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/*     */       return AugmentedIterableIterator.class.count((AugmentedIterableIterator)this, p);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/*     */       return (U)AugmentedIterableIterator.class.fold((AugmentedIterableIterator)this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/*     */       return (U)AugmentedIterableIterator.class.sum((AugmentedIterableIterator)this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/*     */       return (U)AugmentedIterableIterator.class.product((AugmentedIterableIterator)this, num);
/*     */     }
/*     */     
/*     */     public <U> int min(Ordering ord) {
/*     */       return AugmentedIterableIterator.class.min((AugmentedIterableIterator)this, ord);
/*     */     }
/*     */     
/*     */     public <U> int max(Ordering ord) {
/*     */       return AugmentedIterableIterator.class.max((AugmentedIterableIterator)this, ord);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object array, int from, int len) {
/*     */       AugmentedIterableIterator.class.copyToArray((AugmentedIterableIterator)this, array, from, len);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(int howmany, Function2 op) {
/*     */       return (U)AugmentedIterableIterator.class.reduceLeft((AugmentedIterableIterator)this, howmany, op);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.collect2combiner((AugmentedIterableIterator)this, pf, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.flatmap2combiner((AugmentedIterableIterator)this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/*     */       return (Bld)AugmentedIterableIterator.class.copy2builder((AugmentedIterableIterator)this, b);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.filter2combiner((AugmentedIterableIterator)this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.filterNot2combiner((AugmentedIterableIterator)this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/*     */       return AugmentedIterableIterator.class.partition2combiners((AugmentedIterableIterator)this, pred, btrue, bfalse);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.take2combiner((AugmentedIterableIterator)this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.drop2combiner((AugmentedIterableIterator)this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.slice2combiner((AugmentedIterableIterator)this, from, until, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/*     */       return AugmentedIterableIterator.class.splitAt2combiners((AugmentedIterableIterator)this, at, before, after);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.takeWhile2combiner((AugmentedIterableIterator)this, p, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/*     */       return AugmentedIterableIterator.class.span2combiners((AugmentedIterableIterator)this, p, before, after);
/*     */     }
/*     */     
/*     */     public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/*     */       AugmentedIterableIterator.class.scanToArray((AugmentedIterableIterator)this, z, op, array, from);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, howmany, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.zip2combiner((AugmentedIterableIterator)this, otherpit, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.zipAll2combiner((AugmentedIterableIterator)this, that, thiselem, thatelem, cb);
/*     */     }
/*     */     
/*     */     public boolean isRemainingCheap() {
/*     */       return RemainsIterator.class.isRemainingCheap((RemainsIterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<Object> seq() {
/*     */       return Iterator.class.seq((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*     */       return Iterator.class.isEmpty((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/*     */       return Iterator.class.isTraversableAgain((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/*     */       return Iterator.class.hasDefiniteSize((Iterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<Object> drop(int n) {
/*     */       return Iterator.class.drop((Iterator)this, n);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/*     */       return Iterator.class.$plus$plus((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/*     */       return Iterator.class.flatMap((Iterator)this, f);
/*     */     }
/*     */     
/*     */     public Iterator<Object> filter(Function1 p) {
/*     */       return Iterator.class.filter((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/*     */       return Iterator.class.corresponds((Iterator)this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<Object> withFilter(Function1 p) {
/*     */       return Iterator.class.withFilter((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Iterator<Object> filterNot(Function1 p) {
/*     */       return Iterator.class.filterNot((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/*     */       return Iterator.class.collect((Iterator)this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/*     */       return Iterator.class.scanLeft((Iterator)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/*     */       return Iterator.class.scanRight((Iterator)this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<Object> takeWhile(Function1 p) {
/*     */       return Iterator.class.takeWhile((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Object>, Iterator<Object>> partition(Function1 p) {
/*     */       return Iterator.class.partition((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Object>, Iterator<Object>> span(Function1 p) {
/*     */       return Iterator.class.span((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Iterator<Object> dropWhile(Function1 p) {
/*     */       return Iterator.class.dropWhile((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<Object, B>> zip(Iterator that) {
/*     */       return Iterator.class.zip((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/*     */       return Iterator.class.padTo((Iterator)this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<Object, Object>> zipWithIndex() {
/*     */       return Iterator.class.zipWithIndex((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/*     */       return Iterator.class.zipAll((Iterator)this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/*     */       return Iterator.class.forall((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/*     */       return Iterator.class.exists((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/*     */       return Iterator.class.contains((Iterator)this, elem);
/*     */     }
/*     */     
/*     */     public Option<Object> find(Function1 p) {
/*     */       return Iterator.class.find((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/*     */       return Iterator.class.indexOf((Iterator)this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<Object> buffered() {
/*     */       return Iterator.class.buffered((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Object>.GroupedIterator<B> grouped(int size) {
/*     */       return Iterator.class.grouped((Iterator)this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Object>.GroupedIterator<B> sliding(int size, int step) {
/*     */       return Iterator.class.sliding((Iterator)this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/*     */       return Iterator.class.length((Iterator)this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Object>, Iterator<Object>> duplicate() {
/*     */       return Iterator.class.duplicate((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/*     */       return Iterator.class.patch((Iterator)this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/*     */       return Iterator.class.sameElements((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public Traversable<Object> toTraversable() {
/*     */       return Iterator.class.toTraversable((Iterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<Object> toIterator() {
/*     */       return Iterator.class.toIterator((Iterator)this);
/*     */     }
/*     */     
/*     */     public Stream<Object> toStream() {
/*     */       return Iterator.class.toStream((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/*     */       return Iterator.class.sliding$default$2((Iterator)this);
/*     */     }
/*     */     
/*     */     public List<Object> reversed() {
/*     */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/*     */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/*     */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/*     */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/*     */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/*     */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/*     */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/*     */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/*     */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/*     */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/*     */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/*     */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/*     */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*     */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> int maxBy(Function1 f, Ordering cmp) {
/*     */       return TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> int minBy(Function1 f, Ordering cmp) {
/*     */       return TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/*     */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/*     */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/*     */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/*     */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<Object> toList() {
/*     */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<Object> toIterable() {
/*     */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<Object> toSeq() {
/*     */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<Object> toIndexedSeq() {
/*     */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/*     */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*     */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<Object> toVector() {
/*     */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/*     */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(scala.Predef$.less.colon.less ev) {
/*     */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/*     */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/*     */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/*     */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*     */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/*     */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/*     */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*     */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public ParRangeIterator(ParRange $outer, Range range) {
/*     */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*     */       TraversableOnce.class.$init$((TraversableOnce)this);
/*     */       Iterator.class.$init$((Iterator)this);
/*     */       RemainsIterator.class.$init$((RemainsIterator)this);
/*     */       AugmentedIterableIterator.class.$init$((AugmentedIterableIterator)this);
/*     */       DelegatedSignalling.class.$init$((DelegatedSignalling)this);
/*     */       IterableSplitter.class.$init$((IterableSplitter)this);
/*     */       AugmentedSeqIterator.class.$init$((AugmentedSeqIterator)this);
/*     */       SeqSplitter.class.$init$(this);
/*     */       this.ind = 0;
/*     */       this.len = range.length();
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return (new StringBuilder()).append("ParRangeIterator(over: ").append(this.range).append(")").toString();
/*     */     }
/*     */     
/*     */     private int ind() {
/*     */       return this.ind;
/*     */     }
/*     */     
/*     */     private void ind_$eq(int x$1) {
/*     */       this.ind = x$1;
/*     */     }
/*     */     
/*     */     private int len() {
/*     */       return this.len;
/*     */     }
/*     */     
/*     */     public final int remaining() {
/*     */       return len() - ind();
/*     */     }
/*     */     
/*     */     public final boolean hasNext() {
/*     */       return (ind() < len());
/*     */     }
/*     */     
/*     */     public final int next() {
/*     */       int r = this.range.apply$mcII$sp(ind());
/*     */       ind_$eq(ind() + 1);
/*     */       return hasNext() ? r : BoxesRunTime.unboxToInt(scala.collection.Iterator$.MODULE$.empty().next());
/*     */     }
/*     */     
/*     */     private Range rangeleft() {
/*     */       return this.range.drop(ind());
/*     */     }
/*     */     
/*     */     public ParRangeIterator dup() {
/*     */       return new ParRangeIterator(scala$collection$parallel$immutable$ParRange$ParRangeIterator$$$outer(), rangeleft());
/*     */     }
/*     */     
/*     */     public Seq<ParRangeIterator> split() {
/*     */       Range rleft = rangeleft();
/*     */       int elemleft = rleft.length();
/*     */       (new ParRangeIterator[1])[0] = new ParRangeIterator(scala$collection$parallel$immutable$ParRange$ParRangeIterator$$$outer(), rleft);
/*     */       (new ParRangeIterator[2])[0] = new ParRangeIterator(scala$collection$parallel$immutable$ParRange$ParRangeIterator$$$outer(), rleft.take(elemleft / 2));
/*     */       (new ParRangeIterator[2])[1] = new ParRangeIterator(scala$collection$parallel$immutable$ParRange$ParRangeIterator$$$outer(), rleft.drop(elemleft / 2));
/*     */       return (elemleft < 2) ? (Seq<ParRangeIterator>)scala.collection.Seq$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new ParRangeIterator[1])) : (Seq<ParRangeIterator>)scala.collection.Seq$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new ParRangeIterator[2]));
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<Object>> psplit(Seq sizes) {
/*     */       ObjectRef rleft = new ObjectRef(rangeleft());
/*     */       return (Seq<SeqSplitter<Object>>)sizes.map((Function1)new ParRange$ParRangeIterator$$anonfun$psplit$1(this, rleft), scala.collection.Seq$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public class ParRange$ParRangeIterator$$anonfun$psplit$1 extends AbstractFunction1<Object, ParRangeIterator> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final ObjectRef rleft$1;
/*     */       
/*     */       public ParRange$ParRangeIterator$$anonfun$psplit$1(ParRange.ParRangeIterator $outer, ObjectRef rleft$1) {}
/*     */       
/*     */       public final ParRange.ParRangeIterator apply(int sz) {
/*     */         Range fronttaken = ((Range)this.rleft$1.elem).take(sz);
/*     */         this.rleft$1.elem = ((Range)this.rleft$1.elem).drop(sz);
/*  84 */         return new ParRange.ParRangeIterator(this.$outer.scala$collection$parallel$immutable$ParRange$ParRangeIterator$$$outer(), fronttaken);
/*     */       }
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*     */       Range range;
/*  91 */       if ((range = this.range.drop(ind())).validateRangeBoundaries(f)) {
/*     */         int terminal1;
/*     */         int step1;
/*     */         int i1;
/*  91 */         for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/*  91 */           f.apply$mcVI$sp(i1);
/*  91 */           i1 += step1;
/*     */         } 
/*     */       } 
/*  92 */       ind_$eq(len());
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/*  96 */       Object r = rangeleft().reduceLeft(op);
/*  97 */       ind_$eq(len());
/*  98 */       return (U)r;
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner<S, That> cb) {
/* 104 */       while (hasNext())
/* 105 */         cb.$plus$eq(f.apply(BoxesRunTime.boxToInteger(next()))); 
/* 107 */       return cb;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParRange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */