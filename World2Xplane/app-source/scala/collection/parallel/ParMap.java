/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Tuple2;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.CustomParallelizable;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenMapLike;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Map;
/*    */ import scala.collection.Parallel;
/*    */ import scala.collection.Parallelizable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.DelegatedSignalling;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.GenericParMapCompanion;
/*    */ import scala.collection.generic.GenericParMapTemplate;
/*    */ import scala.collection.generic.GenericParTemplate;
/*    */ import scala.collection.generic.GenericTraversableTemplate;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Stream;
/*    */ import scala.collection.immutable.Vector;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.immutable.ParMap;
/*    */ import scala.collection.parallel.immutable.ParSet;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005MeaB\001\003!\003\r\t!\003\002\007!\006\024X*\0319\013\005\r!\021\001\0039be\006dG.\0327\013\005\0251\021AC2pY2,7\r^5p]*\tq!A\003tG\006d\027m\001\001\026\007))rd\005\004\001\027=\t\023f\f\t\003\0315i\021AB\005\003\035\031\021a!\0218z%\0264\007\003\002\t\022'yi\021\001B\005\003%\021\021aaR3o\033\006\004\bC\001\013\026\031\001!QA\006\001C\002]\021\021aS\t\0031m\001\"\001D\r\n\005i1!a\002(pi\"Lgn\032\t\003\031qI!!\b\004\003\007\005s\027\020\005\002\025?\0211\001\005\001CC\002]\021\021A\026\t\006E\025\032bdJ\007\002G)\021A\005B\001\bO\026tWM]5d\023\t13EA\013HK:,'/[2QCJl\025\r\035+f[Bd\027\r^3\021\005!\002Q\"\001\002\021\007!RC&\003\002,\005\tY\001+\031:Ji\026\024\030M\0317f!\021aQf\005\020\n\00592!A\002+va2,'\007\005\004)aMq\"gM\005\003c\t\021!\002U1s\033\006\004H*[6f!\021A\003a\005\020\021\tA!4CH\005\003k\021\0211!T1q\021\0259\004\001\"\0019\003\031!\023N\\5uIQ\t\021\b\005\002\ru%\0211H\002\002\005+:LG\017C\003>\001\021\005a(\001\007nCB\034u.\0349b]&|g.F\001@!\r\021\003iJ\005\003\003\016\022acR3oKJL7\rU1s\033\006\0048i\\7qC:LwN\034\005\006\007\002!\t\001R\001\006K6\004H/_\013\002e!)a\t\001C!\017\006a1\017\036:j]\036\004&/\0324jqV\t\001\n\005\002J\0356\t!J\003\002L\031\006!A.\0318h\025\005i\025\001\0026bm\006L!a\024&\003\rM#(/\0338h\021\025\t\006\001\"\021S\003\035)\b\017Z1uK\022,\"a\025,\025\007QK6\f\005\003)\001M)\006C\001\013W\t\0259\006K1\001Y\005\005)\026C\001\020\034\021\025Q\006\0131\001\024\003\rYW-\037\005\0069B\003\r!V\001\006m\006dW/\032\005\006=\0021\taX\001\006IAdWo]\013\003A\016$\"!\0313\021\t!\0021C\031\t\003)\r$QaV/C\002aCQ!Z/A\002\031\f!a\033<\021\t1i3CY\004\006Q\nA\t![\001\007!\006\024X*\0319\021\005!Rg!B\001\003\021\003Y7C\0016m!\r\021SnJ\005\003]\016\022Q\002U1s\033\006\004h)Y2u_JL\b\"\0029k\t\003\t\030A\002\037j]&$h\bF\001j\021\025\031%\016\"\001t+\r!x/_\013\002kB!\001\006\001<y!\t!r\017B\003\027e\n\007q\003\005\002\025s\022)\001E\035b\001/!)1P\033C\001y\006Ya.Z<D_6\024\027N\\3s+\025i\030qAA\006+\005q\bC\002\025\000\003\007\ti!C\002\002\002\t\021\001bQ8nE&tWM\035\t\007\0315\n)!!\003\021\007Q\t9\001B\003\027u\n\007q\003E\002\025\003\027!Q\001\t>C\002]\001b\001\013\001\002\006\005%\001bBA\tU\022\r\0211C\001\rG\006t')^5mI\032\023x.\\\013\007\003+\ti#!\r\026\005\005]\001#\003\022\002\032\005u\021\021FA\032\023\r\tYb\t\002\017\007\006t7i\\7cS:,gI]8n!\021\ty\"!\t\016\003)LA!a\t\002&\t!1i\0347m\023\r\t9c\t\002\016\017\026tW*\0319GC\016$xN]=\021\r1i\0231FA\030!\r!\022Q\006\003\007-\005=!\031A\f\021\007Q\t\t\004\002\004!\003\037\021\ra\006\t\007Q\001\tY#a\f\007\017\005]\".!\001\002:\tYq+\033;i\t\0264\027-\0367u+\031\tY$!\021\002HM)\021QG\006\002>A1\001\006AA \003\013\0022\001FA!\t\035\t\031%!\016C\002]\021\021!\021\t\004)\005\035C\001CA%\003k!)\031A\f\003\003\tC1\"!\024\0026\t\005\t\025!\003\002>\005QQO\0343fe2L\030N\\4\t\027\005E\023Q\007B\001B\003%\0211K\001\002IB9A\"!\026\002@\005\025\023bAA,\r\tIa)\0368di&|g.\r\005\ba\006UB\021AA.)\031\ti&a\030\002bAA\021qDA\033\003\t)\005\003\005\002N\005e\003\031AA\037\021!\t\t&!\027A\002\005M\003\002CA3\003k!\t%a\032\002\tML'0Z\013\003\003S\0022\001DA6\023\r\tiG\002\002\004\023:$\b\002CA9\003k!\t!a\035\002\007\035,G\017\006\003\002v\005m\004#\002\007\002x\005\025\023bAA=\r\t1q\n\035;j_:DqAWA8\001\004\ty\004\003\005\002\000\005UB\021AAA\003!\031\b\017\\5ui\026\024XCAAB!\025A\023QQAE\023\r\t9I\001\002\021\023R,'/\0312mKN\003H.\033;uKJ\004b\001D\027\002@\005\025\003\002CAG\003k!\t%a$\002\017\021,g-Y;miR!\021QIAI\021\035Q\0261\022a\001\003\001")
/*    */ public interface ParMap<K, V> extends GenMap<K, V>, GenericParMapTemplate<K, V, ParMap>, ParIterable<Tuple2<K, V>>, ParMapLike<K, V, ParMap<K, V>, Map<K, V>> {
/*    */   GenericParMapCompanion<ParMap> mapCompanion();
/*    */   
/*    */   ParMap<K, V> empty();
/*    */   
/*    */   String stringPrefix();
/*    */   
/*    */   <U> ParMap<K, U> updated(K paramK, U paramU);
/*    */   
/*    */   <U> ParMap<K, U> $plus(Tuple2<K, U> paramTuple2);
/*    */   
/*    */   public static abstract class WithDefault<A, B> implements ParMap<A, B> {
/*    */     private final ParMap<A, B> underlying;
/*    */     
/*    */     private final Function1<A, B> d;
/*    */     
/*    */     private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
/*    */     
/*    */     private volatile ParIterableLike.ScanNode$ ScanNode$module;
/*    */     
/*    */     private volatile ParIterableLike.ScanLeaf$ ScanLeaf$module;
/*    */     
/*    */     public GenericParMapCompanion<ParMap> mapCompanion() {
/* 62 */       return ParMap$class.mapCompanion(this);
/*    */     }
/*    */     
/*    */     public ParMap<A, B> empty() {
/* 62 */       return ParMap$class.empty(this);
/*    */     }
/*    */     
/*    */     public String stringPrefix() {
/* 62 */       return ParMap$class.stringPrefix(this);
/*    */     }
/*    */     
/*    */     public <U> ParMap<A, U> updated(Object key, Object value) {
/* 62 */       return ParMap$class.updated(this, key, value);
/*    */     }
/*    */     
/*    */     public B apply(Object key) {
/* 62 */       return (B)ParMapLike$class.apply(this, key);
/*    */     }
/*    */     
/*    */     public <U> U getOrElse(Object key, Function0 default) {
/* 62 */       return (U)ParMapLike$class.getOrElse(this, key, default);
/*    */     }
/*    */     
/*    */     public boolean contains(Object key) {
/* 62 */       return ParMapLike$class.contains(this, key);
/*    */     }
/*    */     
/*    */     public boolean isDefinedAt(Object key) {
/* 62 */       return ParMapLike$class.isDefinedAt(this, key);
/*    */     }
/*    */     
/*    */     public IterableSplitter<A> keysIterator() {
/* 62 */       return ParMapLike$class.keysIterator(this);
/*    */     }
/*    */     
/*    */     public IterableSplitter<B> valuesIterator() {
/* 62 */       return ParMapLike$class.valuesIterator(this);
/*    */     }
/*    */     
/*    */     public ParSet<A> keySet() {
/* 62 */       return ParMapLike$class.keySet(this);
/*    */     }
/*    */     
/*    */     public ParIterable<A> keys() {
/* 62 */       return ParMapLike$class.keys(this);
/*    */     }
/*    */     
/*    */     public ParIterable<B> values() {
/* 62 */       return ParMapLike$class.values(this);
/*    */     }
/*    */     
/*    */     public ParMap<A, B> filterKeys(Function1 p) {
/* 62 */       return ParMapLike$class.filterKeys(this, p);
/*    */     }
/*    */     
/*    */     public <S> ParMap<A, S> mapValues(Function1 f) {
/* 62 */       return ParMapLike$class.mapValues(this, f);
/*    */     }
/*    */     
/*    */     public GenericCompanion<ParIterable> companion() {
/* 62 */       return ParIterable$class.companion(this);
/*    */     }
/*    */     
/*    */     public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
/* 62 */       return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
/*    */     }
/*    */     
/*    */     @TraitSetter
/*    */     public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
/* 62 */       this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
/*    */     }
/*    */     
/*    */     private ParIterableLike.ScanNode$ ScanNode$lzycompute() {
/* 62 */       synchronized (this) {
/* 62 */         if (this.ScanNode$module == null)
/* 62 */           this.ScanNode$module = new ParIterableLike.ScanNode$(this); 
/*    */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/collection/parallel/ParMap}.Lscala/collection/parallel/ParMap$WithDefault;}} */
/* 62 */         return this.ScanNode$module;
/*    */       } 
/*    */     }
/*    */     
/*    */     public ParIterableLike.ScanNode$ ScanNode() {
/* 62 */       return (this.ScanNode$module == null) ? ScanNode$lzycompute() : this.ScanNode$module;
/*    */     }
/*    */     
/*    */     private ParIterableLike.ScanLeaf$ ScanLeaf$lzycompute() {
/* 62 */       synchronized (this) {
/* 62 */         if (this.ScanLeaf$module == null)
/* 62 */           this.ScanLeaf$module = new ParIterableLike.ScanLeaf$(this); 
/*    */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/collection/parallel/ParMap}.Lscala/collection/parallel/ParMap$WithDefault;}} */
/* 62 */         return this.ScanLeaf$module;
/*    */       } 
/*    */     }
/*    */     
/*    */     public ParIterableLike.ScanLeaf$ ScanLeaf() {
/* 62 */       return (this.ScanLeaf$module == null) ? ScanLeaf$lzycompute() : this.ScanLeaf$module;
/*    */     }
/*    */     
/*    */     public void initTaskSupport() {
/* 62 */       ParIterableLike$class.initTaskSupport(this);
/*    */     }
/*    */     
/*    */     public TaskSupport tasksupport() {
/* 62 */       return ParIterableLike$class.tasksupport(this);
/*    */     }
/*    */     
/*    */     public void tasksupport_$eq(TaskSupport ts) {
/* 62 */       ParIterableLike$class.tasksupport_$eq(this, ts);
/*    */     }
/*    */     
/*    */     public ParMap<A, B> repr() {
/* 62 */       return (ParMap<A, B>)ParIterableLike$class.repr(this);
/*    */     }
/*    */     
/*    */     public final boolean isTraversableAgain() {
/* 62 */       return ParIterableLike$class.isTraversableAgain(this);
/*    */     }
/*    */     
/*    */     public boolean hasDefiniteSize() {
/* 62 */       return ParIterableLike$class.hasDefiniteSize(this);
/*    */     }
/*    */     
/*    */     public boolean isEmpty() {
/* 62 */       return ParIterableLike$class.isEmpty(this);
/*    */     }
/*    */     
/*    */     public boolean nonEmpty() {
/* 62 */       return ParIterableLike$class.nonEmpty(this);
/*    */     }
/*    */     
/*    */     public Tuple2<A, B> head() {
/* 62 */       return (Tuple2<A, B>)ParIterableLike$class.head(this);
/*    */     }
/*    */     
/*    */     public Option<Tuple2<A, B>> headOption() {
/* 62 */       return ParIterableLike$class.headOption(this);
/*    */     }
/*    */     
/*    */     public ParMap<A, B> tail() {
/* 62 */       return (ParMap<A, B>)ParIterableLike$class.tail(this);
/*    */     }
/*    */     
/*    */     public Tuple2<A, B> last() {
/* 62 */       return (Tuple2<A, B>)ParIterableLike$class.last(this);
/*    */     }
/*    */     
/*    */     public Option<Tuple2<A, B>> lastOption() {
/* 62 */       return ParIterableLike$class.lastOption(this);
/*    */     }
/*    */     
/*    */     public ParMap<A, B> init() {
/* 62 */       return (ParMap<A, B>)ParIterableLike$class.init(this);
/*    */     }
/*    */     
/*    */     public Splitter<Tuple2<A, B>> iterator() {
/* 62 */       return ParIterableLike$class.iterator(this);
/*    */     }
/*    */     
/*    */     public ParMap<A, B> par() {
/* 62 */       return (ParMap<A, B>)ParIterableLike$class.par(this);
/*    */     }
/*    */     
/*    */     public boolean isStrictSplitterCollection() {
/* 62 */       return ParIterableLike$class.isStrictSplitterCollection(this);
/*    */     }
/*    */     
/*    */     public <S, That> Combiner<S, That> reuse(Option oldc, Combiner newc) {
/* 62 */       return ParIterableLike$class.reuse(this, oldc, newc);
/*    */     }
/*    */     
/*    */     public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask tsk) {
/* 62 */       return ParIterableLike$class.task2ops(this, tsk);
/*    */     }
/*    */     
/*    */     public <R> Object wrap(Function0 body) {
/* 62 */       return ParIterableLike$class.wrap(this, body);
/*    */     }
/*    */     
/*    */     public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(DelegatedSignalling it) {
/* 62 */       return ParIterableLike$class.delegatedSignalling2ops(this, it);
/*    */     }
/*    */     
/*    */     public <Elem, To> Object builder2ops(Builder cb) {
/* 62 */       return ParIterableLike$class.builder2ops(this, cb);
/*    */     }
/*    */     
/*    */     public <S, That> Object bf2seq(CanBuildFrom bf) {
/* 62 */       return ParIterableLike$class.bf2seq(this, bf);
/*    */     }
/*    */     
/*    */     public <S, That extends Parallel> ParMap<A, B> sequentially(Function1 b) {
/* 62 */       return (ParMap<A, B>)ParIterableLike$class.sequentially(this, b);
/*    */     }
/*    */     
/*    */     public String mkString(String start, String sep, String end) {
/* 62 */       return ParIterableLike$class.mkString(this, start, sep, end);
/*    */     }
/*    */     
/*    */     public String mkString(String sep) {
/* 62 */       return ParIterableLike$class.mkString(this, sep);
/*    */     }
/*    */     
/*    */     public String mkString() {
/* 62 */       return ParIterableLike$class.mkString(this);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 62 */       return ParIterableLike$class.toString(this);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object other) {
/* 62 */       return ParIterableLike$class.canEqual(this, other);
/*    */     }
/*    */     
/*    */     public <U> U reduce(Function2 op) {
/* 62 */       return (U)ParIterableLike$class.reduce(this, op);
/*    */     }
/*    */     
/*    */     public <U> Option<U> reduceOption(Function2 op) {
/* 62 */       return ParIterableLike$class.reduceOption(this, op);
/*    */     }
/*    */     
/*    */     public <U> U fold(Object z, Function2 op) {
/* 62 */       return (U)ParIterableLike$class.fold(this, z, op);
/*    */     }
/*    */     
/*    */     public <S> S aggregate(Object z, Function2 seqop, Function2 combop) {
/* 62 */       return (S)ParIterableLike$class.aggregate(this, z, seqop, combop);
/*    */     }
/*    */     
/*    */     public <S> S foldLeft(Object z, Function2 op) {
/* 62 */       return (S)ParIterableLike$class.foldLeft(this, z, op);
/*    */     }
/*    */     
/*    */     public <S> S foldRight(Object z, Function2 op) {
/* 62 */       return (S)ParIterableLike$class.foldRight(this, z, op);
/*    */     }
/*    */     
/*    */     public <U> U reduceLeft(Function2 op) {
/* 62 */       return (U)ParIterableLike$class.reduceLeft(this, op);
/*    */     }
/*    */     
/*    */     public <U> U reduceRight(Function2 op) {
/* 62 */       return (U)ParIterableLike$class.reduceRight(this, op);
/*    */     }
/*    */     
/*    */     public <U> Option<U> reduceLeftOption(Function2 op) {
/* 62 */       return ParIterableLike$class.reduceLeftOption(this, op);
/*    */     }
/*    */     
/*    */     public <U> Option<U> reduceRightOption(Function2 op) {
/* 62 */       return ParIterableLike$class.reduceRightOption(this, op);
/*    */     }
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 62 */       ParIterableLike$class.foreach(this, f);
/*    */     }
/*    */     
/*    */     public int count(Function1 p) {
/* 62 */       return ParIterableLike$class.count(this, p);
/*    */     }
/*    */     
/*    */     public <U> U sum(Numeric num) {
/* 62 */       return (U)ParIterableLike$class.sum(this, num);
/*    */     }
/*    */     
/*    */     public <U> U product(Numeric num) {
/* 62 */       return (U)ParIterableLike$class.product(this, num);
/*    */     }
/*    */     
/*    */     public <U> Tuple2<A, B> min(Ordering ord) {
/* 62 */       return (Tuple2<A, B>)ParIterableLike$class.min(this, ord);
/*    */     }
/*    */     
/*    */     public <U> Tuple2<A, B> max(Ordering ord) {
/* 62 */       return (Tuple2<A, B>)ParIterableLike$class.max(this, ord);
/*    */     }
/*    */     
/*    */     public <S> Tuple2<A, B> maxBy(Function1 f, Ordering cmp) {
/* 62 */       return (Tuple2<A, B>)ParIterableLike$class.maxBy(this, f, cmp);
/*    */     }
/*    */     
/*    */     public <S> Tuple2<A, B> minBy(Function1 f, Ordering cmp) {
/* 62 */       return (Tuple2<A, B>)ParIterableLike$class.minBy(this, f, cmp);
/*    */     }
/*    */     
/*    */     public <S, That> That map(Function1 f, CanBuildFrom bf) {
/* 62 */       return (That)ParIterableLike$class.map(this, f, bf);
/*    */     }
/*    */     
/*    */     public <S, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 62 */       return (That)ParIterableLike$class.collect(this, pf, bf);
/*    */     }
/*    */     
/*    */     public <S, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 62 */       return (That)ParIterableLike$class.flatMap(this, f, bf);
/*    */     }
/*    */     
/*    */     public boolean forall(Function1 pred) {
/* 62 */       return ParIterableLike$class.forall(this, pred);
/*    */     }
/*    */     
/*    */     public boolean exists(Function1 pred) {
/* 62 */       return ParIterableLike$class.exists(this, pred);
/*    */     }
/*    */     
/*    */     public Option<Tuple2<A, B>> find(Function1 pred) {
/* 62 */       return ParIterableLike$class.find(this, pred);
/*    */     }
/*    */     
/*    */     public Object combinerFactory() {
/* 62 */       return ParIterableLike$class.combinerFactory(this);
/*    */     }
/*    */     
/*    */     public <S, That> Object combinerFactory(Function0 cbf) {
/* 62 */       return ParIterableLike$class.combinerFactory(this, cbf);
/*    */     }
/*    */     
/*    */     public ParMap<A, B> filter(Function1 pred) {
/* 62 */       return (ParMap<A, B>)ParIterableLike$class.filter(this, pred);
/*    */     }
/*    */     
/*    */     public ParMap<A, B> filterNot(Function1 pred) {
/* 62 */       return (ParMap<A, B>)ParIterableLike$class.filterNot(this, pred);
/*    */     }
/*    */     
/*    */     public <U, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/* 62 */       return (That)ParIterableLike$class.$plus$plus(this, that, bf);
/*    */     }
/*    */     
/*    */     public Tuple2<ParMap<A, B>, ParMap<A, B>> partition(Function1 pred) {
/* 62 */       return ParIterableLike$class.partition(this, pred);
/*    */     }
/*    */     
/*    */     public <K> ParMap<K, ParMap<A, B>> groupBy(Function1 f) {
/* 62 */       return ParIterableLike$class.groupBy(this, f);
/*    */     }
/*    */     
/*    */     public ParMap<A, B> take(int n) {
/* 62 */       return (ParMap<A, B>)ParIterableLike$class.take(this, n);
/*    */     }
/*    */     
/*    */     public ParMap<A, B> drop(int n) {
/* 62 */       return (ParMap<A, B>)ParIterableLike$class.drop(this, n);
/*    */     }
/*    */     
/*    */     public ParMap<A, B> slice(int unc_from, int unc_until) {
/* 62 */       return (ParMap<A, B>)ParIterableLike$class.slice(this, unc_from, unc_until);
/*    */     }
/*    */     
/*    */     public Tuple2<ParMap<A, B>, ParMap<A, B>> splitAt(int n) {
/* 62 */       return ParIterableLike$class.splitAt(this, n);
/*    */     }
/*    */     
/*    */     public <U, That> That scan(Object z, Function2 op, CanBuildFrom bf) {
/* 62 */       return (That)ParIterableLike$class.scan(this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <S, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 62 */       return (That)ParIterableLike$class.scanLeft(this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <S, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 62 */       return (That)ParIterableLike$class.scanRight(this, z, op, bf);
/*    */     }
/*    */     
/*    */     public ParMap<A, B> takeWhile(Function1 pred) {
/* 62 */       return (ParMap<A, B>)ParIterableLike$class.takeWhile(this, pred);
/*    */     }
/*    */     
/*    */     public Tuple2<ParMap<A, B>, ParMap<A, B>> span(Function1 pred) {
/* 62 */       return ParIterableLike$class.span(this, pred);
/*    */     }
/*    */     
/*    */     public ParMap<A, B> dropWhile(Function1 pred) {
/* 62 */       return (ParMap<A, B>)ParIterableLike$class.dropWhile(this, pred);
/*    */     }
/*    */     
/*    */     public <U> void copyToArray(Object xs) {
/* 62 */       ParIterableLike$class.copyToArray(this, xs);
/*    */     }
/*    */     
/*    */     public <U> void copyToArray(Object xs, int start) {
/* 62 */       ParIterableLike$class.copyToArray(this, xs, start);
/*    */     }
/*    */     
/*    */     public <U> void copyToArray(Object xs, int start, int len) {
/* 62 */       ParIterableLike$class.copyToArray(this, xs, start, len);
/*    */     }
/*    */     
/*    */     public <U> boolean sameElements(GenIterable that) {
/* 62 */       return ParIterableLike$class.sameElements(this, that);
/*    */     }
/*    */     
/*    */     public <U, S, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 62 */       return (That)ParIterableLike$class.zip(this, that, bf);
/*    */     }
/*    */     
/*    */     public <U, That> That zipWithIndex(CanBuildFrom bf) {
/* 62 */       return (That)ParIterableLike$class.zipWithIndex(this, bf);
/*    */     }
/*    */     
/*    */     public <S, U, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 62 */       return (That)ParIterableLike$class.zipAll(this, that, thisElem, thatElem, bf);
/*    */     }
/*    */     
/*    */     public <U, That> That toParCollection(Function0 cbf) {
/* 62 */       return (That)ParIterableLike$class.toParCollection(this, cbf);
/*    */     }
/*    */     
/*    */     public <K, V, That> That toParMap(Function0 cbf, Predef$.less.colon.less ev) {
/* 62 */       return (That)ParIterableLike$class.toParMap(this, cbf, ev);
/*    */     }
/*    */     
/*    */     public Object view() {
/* 62 */       return ParIterableLike$class.view(this);
/*    */     }
/*    */     
/*    */     public <U> Object toArray(ClassTag evidence$1) {
/* 62 */       return ParIterableLike$class.toArray(this, evidence$1);
/*    */     }
/*    */     
/*    */     public List<Tuple2<A, B>> toList() {
/* 62 */       return ParIterableLike$class.toList(this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<Tuple2<A, B>> toIndexedSeq() {
/* 62 */       return ParIterableLike$class.toIndexedSeq(this);
/*    */     }
/*    */     
/*    */     public Stream<Tuple2<A, B>> toStream() {
/* 62 */       return ParIterableLike$class.toStream(this);
/*    */     }
/*    */     
/*    */     public Iterator<Tuple2<A, B>> toIterator() {
/* 62 */       return ParIterableLike$class.toIterator(this);
/*    */     }
/*    */     
/*    */     public <U> Buffer<U> toBuffer() {
/* 62 */       return ParIterableLike$class.toBuffer(this);
/*    */     }
/*    */     
/*    */     public GenTraversable<Tuple2<A, B>> toTraversable() {
/* 62 */       return ParIterableLike$class.toTraversable(this);
/*    */     }
/*    */     
/*    */     public ParIterable<Tuple2<A, B>> toIterable() {
/* 62 */       return ParIterableLike$class.toIterable(this);
/*    */     }
/*    */     
/*    */     public ParSeq<Tuple2<A, B>> toSeq() {
/* 62 */       return ParIterableLike$class.toSeq(this);
/*    */     }
/*    */     
/*    */     public <U> ParSet<U> toSet() {
/* 62 */       return ParIterableLike$class.toSet(this);
/*    */     }
/*    */     
/*    */     public <K, V> ParMap<K, V> toMap(Predef$.less.colon.less ev) {
/* 62 */       return ParIterableLike$class.toMap(this, ev);
/*    */     }
/*    */     
/*    */     public Vector<Tuple2<A, B>> toVector() {
/* 62 */       return ParIterableLike$class.toVector(this);
/*    */     }
/*    */     
/*    */     public <Col> Col to(CanBuildFrom cbf) {
/* 62 */       return (Col)ParIterableLike$class.to(this, cbf);
/*    */     }
/*    */     
/*    */     public int scanBlockSize() {
/* 62 */       return ParIterableLike$class.scanBlockSize(this);
/*    */     }
/*    */     
/*    */     public <S> S $div$colon(Object z, Function2 op) {
/* 62 */       return (S)ParIterableLike$class.$div$colon(this, z, op);
/*    */     }
/*    */     
/*    */     public <S> S $colon$bslash(Object z, Function2 op) {
/* 62 */       return (S)ParIterableLike$class.$colon$bslash(this, z, op);
/*    */     }
/*    */     
/*    */     public String debugInformation() {
/* 62 */       return ParIterableLike$class.debugInformation(this);
/*    */     }
/*    */     
/*    */     public Seq<String> brokenInvariants() {
/* 62 */       return ParIterableLike$class.brokenInvariants(this);
/*    */     }
/*    */     
/*    */     public ArrayBuffer<String> debugBuffer() {
/* 62 */       return ParIterableLike$class.debugBuffer(this);
/*    */     }
/*    */     
/*    */     public void debugclear() {
/* 62 */       ParIterableLike$class.debugclear(this);
/*    */     }
/*    */     
/*    */     public ArrayBuffer<String> debuglog(String s) {
/* 62 */       return ParIterableLike$class.debuglog(this, s);
/*    */     }
/*    */     
/*    */     public void printDebugBuffer() {
/* 62 */       ParIterableLike$class.printDebugBuffer(this);
/*    */     }
/*    */     
/*    */     public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
/* 62 */       return CustomParallelizable.class.parCombiner(this);
/*    */     }
/*    */     
/*    */     public Combiner<Tuple2<A, B>, ParMap<A, B>> newCombiner() {
/* 62 */       return GenericParMapTemplate.class.newCombiner(this);
/*    */     }
/*    */     
/*    */     public <P, Q> Combiner<Tuple2<P, Q>, ParMap<P, Q>> genericMapCombiner() {
/* 62 */       return GenericParMapTemplate.class.genericMapCombiner(this);
/*    */     }
/*    */     
/*    */     public Builder<Tuple2<A, B>, ParIterable<Tuple2<A, B>>> newBuilder() {
/* 62 */       return GenericParTemplate.class.newBuilder(this);
/*    */     }
/*    */     
/*    */     public <B> Combiner<B, ParIterable<B>> genericBuilder() {
/* 62 */       return GenericParTemplate.class.genericBuilder(this);
/*    */     }
/*    */     
/*    */     public <B> Combiner<B, ParIterable<B>> genericCombiner() {
/* 62 */       return GenericParTemplate.class.genericCombiner(this);
/*    */     }
/*    */     
/*    */     public <A1, A2> Tuple2<ParIterable<A1>, ParIterable<A2>> unzip(Function1 asPair) {
/* 62 */       return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*    */     }
/*    */     
/*    */     public <A1, A2, A3> Tuple3<ParIterable<A1>, ParIterable<A2>, ParIterable<A3>> unzip3(Function1 asTriple) {
/* 62 */       return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*    */     }
/*    */     
/*    */     public <B> ParIterable<B> flatten(Function1 asTraversable) {
/* 62 */       return (ParIterable<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*    */     }
/*    */     
/*    */     public <B> ParIterable<ParIterable<B>> transpose(Function1 asTraversable) {
/* 62 */       return (ParIterable<ParIterable<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 62 */       return GenMapLike.class.hashCode(this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object that) {
/* 62 */       return GenMapLike.class.equals(this, that);
/*    */     }
/*    */     
/*    */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 62 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public WithDefault(ParMap<A, B> underlying, Function1<A, B> d) {
/* 62 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 62 */       Parallelizable.class.$init$((Parallelizable)this);
/* 62 */       GenMapLike.class.$init$(this);
/* 62 */       GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/* 62 */       GenTraversable.class.$init$((GenTraversable)this);
/* 62 */       GenIterable.class.$init$(this);
/* 62 */       GenericParTemplate.class.$init$(this);
/* 62 */       GenericParMapTemplate.class.$init$(this);
/* 62 */       CustomParallelizable.class.$init$(this);
/* 62 */       ParIterableLike$class.$init$(this);
/* 62 */       ParIterable$class.$init$(this);
/* 62 */       ParMapLike$class.$init$(this);
/* 62 */       ParMap$class.$init$(this);
/*    */     }
/*    */     
/*    */     public int size() {
/* 63 */       return this.underlying.size();
/*    */     }
/*    */     
/*    */     public Option<B> get(Object key) {
/* 64 */       return this.underlying.get(key);
/*    */     }
/*    */     
/*    */     public IterableSplitter<Tuple2<A, B>> splitter() {
/* 65 */       return this.underlying.splitter();
/*    */     }
/*    */     
/*    */     public B default(Object key) {
/* 66 */       return (B)this.d.apply(key);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */