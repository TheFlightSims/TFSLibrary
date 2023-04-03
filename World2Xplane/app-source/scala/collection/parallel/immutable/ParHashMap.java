/*     */ package scala.collection.parallel.immutable;
/*     */ 
/*     */ import java.util.concurrent.atomic.AtomicInteger;
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
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.Parallel;
/*     */ import scala.collection.Parallelizable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.CanCombineFrom;
/*     */ import scala.collection.generic.DelegatedSignalling;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericParMapCompanion;
/*     */ import scala.collection.generic.GenericParMapTemplate;
/*     */ import scala.collection.generic.GenericParTemplate;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.generic.Signalling;
/*     */ import scala.collection.immutable.HashMap;
/*     */ import scala.collection.immutable.HashMap$;
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
/*     */ import scala.collection.parallel.ParMap;
/*     */ import scala.collection.parallel.ParMapLike;
/*     */ import scala.collection.parallel.ParSeq;
/*     */ import scala.collection.parallel.ParSet;
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
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t\025e\001B\001\003\001-\021!\002U1s\021\006\034\b.T1q\025\t\031A!A\005j[6,H/\0312mK*\021QAB\001\ta\006\024\030\r\0347fY*\021q\001C\001\013G>dG.Z2uS>t'\"A\005\002\013M\034\027\r\\1\004\001U\031AbF\021\024\r\001i\021c\t\0265!\tqq\"D\001\t\023\t\001\002B\001\004B]f\024VM\032\t\005%M)\002%D\001\003\023\t!\"A\001\004QCJl\025\r\035\t\003-]a\001\001B\003\031\001\t\007\021DA\001L#\tQR\004\005\002\0177%\021A\004\003\002\b\035>$\b.\0338h!\tqa$\003\002 \021\t\031\021I\\=\021\005Y\tCA\002\022\001\t\013\007\021DA\001W!\025!s%\006\021*\033\005)#B\001\024\007\003\0359WM\\3sS\016L!\001K\023\003+\035+g.\032:jGB\013'/T1q)\026l\007\017\\1uKB\021!\003\001\t\007W1*\002EL\030\016\003\021I!!\f\003\003\025A\013'/T1q\031&\\W\r\005\003\023\001U\001\003\003\002\0313+\001j\021!\r\006\003\007\031I!aM\031\003\017!\0137\017['baB\021a\"N\005\003m!\021AbU3sS\006d\027N_1cY\026D\001\002\017\001\003\002\003\006IaL\001\005iJLW\r\003\004;\001\021\005!aO\001\007y%t\027\016\036 \025\0059b\004\"\002\035:\001\004y\003\"\002\036\001\t\003qD#\001\030\t\013\001\003A\021I!\002\0315\f\007oQ8na\006t\027n\0348\026\003\t\0032\001J\"*\023\t!UE\001\fHK:,'/[2QCJl\025\r]\"p[B\fg.[8o\021\0251\005\001\"\021H\003\025)W\016\035;z+\005q\003BB%\001A\023E#*A\006oK^\034u.\0342j]\026\024X#A&\021\tIaU\003I\005\003\033\n\021q\002S1tQ6\013\007oQ8nE&tWM\035\005\006\037\002!\t\001U\001\tgBd\027\016\036;feV\t\021\013E\002,%RK!a\025\003\003!%#XM]1cY\026\034\006\017\\5ui\026\024\b\003\002\bV+\001J!A\026\005\003\rQ+\b\017\\33\021\025A\006\001\"\021Z\003\r\031X-]\013\002_!)1\f\001C\0019\0061A%\\5okN$\"AL/\t\013yS\006\031A\013\002\003-DQ\001\031\001\005\002\005\fQ\001\n9mkN,\"AY3\025\005\rD\007\003\002\n\001+\021\004\"AF3\005\013\031|&\031A4\003\003U\013\"\001I\017\t\013%|\006\031\0016\002\005-4\b\003\002\bV+\021DQ\001\034\001\005\0025\f1aZ3u)\tq\027\017E\002\017_\002J!\001\035\005\003\r=\003H/[8o\021\025q6\0161\001\026\021\025\031\b\001\"\021u\003\021\031\030N_3\026\003U\004\"A\004<\n\005]D!aA%oi\")\021\020\001C)u\006)!/Z;tKV)10!\001\002\bQ)A0a\003\002\022A)1&`@\002\006%\021a\020\002\002\t\007>l'-\0338feB\031a#!\001\005\r\005\r\001P1\001\032\005\005\031\006c\001\f\002\b\0211\021\021\002=C\002e\021A\001\0265bi\"9\021Q\002=A\002\005=\021\001B8mI\016\0042AD8}\021\031\t\031\002\037a\001y\006!a.Z<d\r\031\t9\002\001\001\002\032\t\021\002+\031:ICNDW*\0319Ji\026\024\030\r^8s'\021\t)\"D)\t\027\005u\021Q\003BA\002\023\005\021qD\001\007iJLG/\032:\026\005\005\005\002CBA\022\003g\tID\004\003\002&\005=b\002BA\024\003[i!!!\013\013\007\005-\"\"\001\004=e>|GOP\005\002\023%\031\021\021\007\005\002\017A\f7m[1hK&!\021QGA\034\005!IE/\032:bi>\024(bAA\031\021A)a\"V\013\002<)\032\001%!\020,\005\005}\002\003BA!\003\027j!!a\021\013\t\005\025\023qI\001\nk:\034\007.Z2lK\022T1!!\023\t\003)\tgN\\8uCRLwN\\\005\005\003\033\n\031EA\tv]\016DWmY6fIZ\013'/[1oG\026D1\"!\025\002\026\t\005\r\021\"\001\002T\005QAO]5uKJ|F%Z9\025\t\005U\0231\f\t\004\035\005]\023bAA-\021\t!QK\\5u\021)\ti&a\024\002\002\003\007\021\021E\001\004q\022\n\004bCA1\003+\021\t\021)Q\005\003C\tq\001\036:ji\026\024\b\005\003\006\002f\005U!Q1A\005\002Q\f!a\035>\t\025\005%\024Q\003B\001B\003%Q/A\002tu\002BqAOA\013\t\003\ti\007\006\004\002p\005M\024Q\017\t\005\003c\n)\"D\001\001\021!\ti\"a\033A\002\005\005\002bBA3\003W\002\r!\036\005\n\003s\n)\0021A\005\002Q\f\021!\033\005\013\003{\n)\0021A\005\002\005}\024!B5`I\025\fH\003BA+\003\003C\021\"!\030\002|\005\005\t\031A;\t\021\005\025\025Q\003Q!\nU\f!!\033\021\t\017\005%\025Q\003C\001!\006\031A-\0369\t\021\0055\025Q\003C\005\003\037\013q\002Z;q\rJ|W.\023;fe\006$xN\035\013\005\003_\n\t\n\003\005\002\024\006-\005\031AA\021\003\tIG\017\003\005\002\030\006UA\021AAM\003\025\031\b\017\\5u+\t\tY\nE\003\002$\005u\025+\003\003\002 \006]\"aA*fc\"A\0211UA\013\t\003\t)+\001\003oKb$H#\001+\t\021\005%\026Q\003C\001\003W\013q\001[1t\035\026DH/\006\002\002.B\031a\"a,\n\007\005E\006BA\004C_>dW-\0318\t\017\005U\026Q\003C\001i\006I!/Z7bS:Lgn\032\005\t\003s\013)\002\"\021\002<\006AAo\\*ue&tw\r\006\002\002>B!\021qXAe\033\t\t\tM\003\003\002D\006\025\027\001\0027b]\036T!!a2\002\t)\fg/Y\005\005\003\027\f\tM\001\004TiJLgn\032\005\t\003\037\004A\021\001\003\002R\006q\001O]5oi\022+'-^4J]\032|GCAA+Q\025\001\021Q[An!\rq\021q[\005\004\0033D!\001E*fe&\fGNV3sg&|g.V%E=\005\tqaBAp\005!\005\021\021]\001\013!\006\024\b*Y:i\033\006\004\bc\001\n\002d\0321\021A\001E\001\003K\034R!a9\002hR\002B\001JAuS%\031\0211^\023\003\033A\013'/T1q\r\006\034Go\034:z\021\035Q\0241\035C\001\003_$\"!!9\t\017\031\013\031\017\"\001\002tV1\021Q_A~\003,\"!a>\021\rI\001\021\021`A!\r1\0221 \003\0071\005E(\031A\r\021\007Y\ty\020\002\004#\003c\024\r!\007\005\b\023\006\rH\021\001B\002+\031\021)A!\004\003\022U\021!q\001\t\007Wu\024IAa\005\021\r9)&1\002B\b!\r1\"Q\002\003\0071\t\005!\031A\r\021\007Y\021\t\002\002\004#\005\003\021\r!\007\t\007%\001\021YAa\004\t\021\t]\0211\035C\002\0053\tAbY1o\005VLG\016\032$s_6,bAa\007\0034\t]RC\001B\017!%!#q\004B\022\005_\021I$C\002\003\"\025\022abQ1o\007>l'-\0338f\rJ|W\016\005\003\003&\t\035RBAAr\023\021\021ICa\013\003\t\r{G\016\\\005\004\005[)#!D$f]6\013\007OR1di>\024\030\020\005\004\017+\nE\"Q\007\t\004-\tMBA\002\r\003\026\t\007\021\004E\002\027\005o!aA\tB\013\005\004I\002C\002\n\001\005c\021)\004\003\005\003>\005\rH\021\001B \003!1'o\\7Ue&,WC\002B!\005\017\022Y\005\006\003\003D\t5\003C\002\n\001\005\013\022I\005E\002\027\005\017\"a\001\007B\036\005\004I\002c\001\f\003L\0211!Ea\017C\002eA\001Ba\024\003<\001\007!\021K\001\002iB1\001G\rB#\005\023B!B!\026\002d\002\007I\021\001B,\0035!x\016^1mG>l'-\0338fgV\021!\021\f\t\005\0057\022I'\004\002\003^)!!q\fB1\003\031\tGo\\7jG*!!1\rB3\003)\031wN\\2veJ,g\016\036\006\005\005O\n)-\001\003vi&d\027\002\002B6\005;\022Q\"\021;p[&\034\027J\034;fO\026\024\bB\003B8\003G\004\r\021\"\001\003r\005\tBo\034;bY\016|WNY5oKN|F%Z9\025\t\005U#1\017\005\013\003;\022i'!AA\002\te\003\"\003B<\003G\004\013\025\002B-\0039!x\016^1mG>l'-\0338fg\002B!Ba\037\002d\006\005I\021\002B?\003-\021X-\0313SKN|GN^3\025\005\t}\004\003BA`\005\003KAAa!\002B\n1qJ\0316fGR\004")
/*     */ public class ParHashMap<K, V> implements ParMap<K, V>, GenericParMapTemplate<K, V, ParHashMap>, ParMapLike<K, V, ParHashMap<K, V>, HashMap<K, V>>, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final HashMap<K, V> trie;
/*     */   
/*     */   private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */   
/*     */   private volatile ParIterableLike$ScanNode$ ScanNode$module;
/*     */   
/*     */   private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;
/*     */   
/*     */   public String stringPrefix() {
/*  46 */     return ParMap$class.stringPrefix(this);
/*     */   }
/*     */   
/*     */   public <P, Q> ParMap<P, Q> toMap(Predef$.less.colon.less ev) {
/*  46 */     return ParMap$class.toMap(this, ev);
/*     */   }
/*     */   
/*     */   public <U> ParMap<K, U> updated(Object key, Object value) {
/*  46 */     return ParMap$class.updated(this, key, value);
/*     */   }
/*     */   
/*     */   public <U> ParMap<K, U> withDefault(Function1 d) {
/*  46 */     return ParMap$class.withDefault(this, d);
/*     */   }
/*     */   
/*     */   public <U> ParMap<K, U> withDefaultValue(Object d) {
/*  46 */     return ParMap$class.withDefaultValue(this, d);
/*     */   }
/*     */   
/*     */   public GenericCompanion<ParIterable> companion() {
/*  46 */     return ParIterable$class.companion(this);
/*     */   }
/*     */   
/*     */   public ParIterable<Tuple2<K, V>> toIterable() {
/*  46 */     return ParIterable$class.toIterable(this);
/*     */   }
/*     */   
/*     */   public ParSeq<Tuple2<K, V>> toSeq() {
/*  46 */     return ParIterable$class.toSeq(this);
/*     */   }
/*     */   
/*     */   public V default(Object key) {
/*  46 */     return (V)ParMapLike.class.default(this, key);
/*     */   }
/*     */   
/*     */   public V apply(Object key) {
/*  46 */     return (V)ParMapLike.class.apply(this, key);
/*     */   }
/*     */   
/*     */   public <U> U getOrElse(Object key, Function0 default) {
/*  46 */     return (U)ParMapLike.class.getOrElse(this, key, default);
/*     */   }
/*     */   
/*     */   public boolean contains(Object key) {
/*  46 */     return ParMapLike.class.contains(this, key);
/*     */   }
/*     */   
/*     */   public boolean isDefinedAt(Object key) {
/*  46 */     return ParMapLike.class.isDefinedAt(this, key);
/*     */   }
/*     */   
/*     */   public IterableSplitter<K> keysIterator() {
/*  46 */     return ParMapLike.class.keysIterator(this);
/*     */   }
/*     */   
/*     */   public IterableSplitter<V> valuesIterator() {
/*  46 */     return ParMapLike.class.valuesIterator(this);
/*     */   }
/*     */   
/*     */   public ParSet<K> keySet() {
/*  46 */     return ParMapLike.class.keySet(this);
/*     */   }
/*     */   
/*     */   public ParIterable<K> keys() {
/*  46 */     return ParMapLike.class.keys(this);
/*     */   }
/*     */   
/*     */   public ParIterable<V> values() {
/*  46 */     return ParMapLike.class.values(this);
/*     */   }
/*     */   
/*     */   public ParMap<K, V> filterKeys(Function1 p) {
/*  46 */     return ParMapLike.class.filterKeys(this, p);
/*     */   }
/*     */   
/*     */   public <S> ParMap<K, S> mapValues(Function1 f) {
/*  46 */     return ParMapLike.class.mapValues(this, f);
/*     */   }
/*     */   
/*     */   public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
/*  46 */     return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
/*  46 */     this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
/*     */   }
/*     */   
/*     */   private ParIterableLike$ScanNode$ ScanNode$lzycompute() {
/*  46 */     synchronized (this) {
/*  46 */       if (this.ScanNode$module == null)
/*  46 */         this.ScanNode$module = new ParIterableLike$ScanNode$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/immutable/ParHashMap}} */
/*  46 */       return this.ScanNode$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParIterableLike$ScanNode$ ScanNode() {
/*  46 */     return (this.ScanNode$module == null) ? ScanNode$lzycompute() : this.ScanNode$module;
/*     */   }
/*     */   
/*     */   private ParIterableLike$ScanLeaf$ ScanLeaf$lzycompute() {
/*  46 */     synchronized (this) {
/*  46 */       if (this.ScanLeaf$module == null)
/*  46 */         this.ScanLeaf$module = new ParIterableLike$ScanLeaf$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/immutable/ParHashMap}} */
/*  46 */       return this.ScanLeaf$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParIterableLike$ScanLeaf$ ScanLeaf() {
/*  46 */     return (this.ScanLeaf$module == null) ? ScanLeaf$lzycompute() : this.ScanLeaf$module;
/*     */   }
/*     */   
/*     */   public void initTaskSupport() {
/*  46 */     ParIterableLike.class.initTaskSupport(this);
/*     */   }
/*     */   
/*     */   public TaskSupport tasksupport() {
/*  46 */     return ParIterableLike.class.tasksupport(this);
/*     */   }
/*     */   
/*     */   public void tasksupport_$eq(TaskSupport ts) {
/*  46 */     ParIterableLike.class.tasksupport_$eq(this, ts);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> repr() {
/*  46 */     return (ParHashMap<K, V>)ParIterableLike.class.repr(this);
/*     */   }
/*     */   
/*     */   public final boolean isTraversableAgain() {
/*  46 */     return ParIterableLike.class.isTraversableAgain(this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/*  46 */     return ParIterableLike.class.hasDefiniteSize(this);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  46 */     return ParIterableLike.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/*  46 */     return ParIterableLike.class.nonEmpty(this);
/*     */   }
/*     */   
/*     */   public Tuple2<K, V> head() {
/*  46 */     return (Tuple2<K, V>)ParIterableLike.class.head(this);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<K, V>> headOption() {
/*  46 */     return ParIterableLike.class.headOption(this);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> tail() {
/*  46 */     return (ParHashMap<K, V>)ParIterableLike.class.tail(this);
/*     */   }
/*     */   
/*     */   public Tuple2<K, V> last() {
/*  46 */     return (Tuple2<K, V>)ParIterableLike.class.last(this);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<K, V>> lastOption() {
/*  46 */     return ParIterableLike.class.lastOption(this);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> init() {
/*  46 */     return (ParHashMap<K, V>)ParIterableLike.class.init(this);
/*     */   }
/*     */   
/*     */   public Splitter<Tuple2<K, V>> iterator() {
/*  46 */     return ParIterableLike.class.iterator(this);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> par() {
/*  46 */     return (ParHashMap<K, V>)ParIterableLike.class.par(this);
/*     */   }
/*     */   
/*     */   public boolean isStrictSplitterCollection() {
/*  46 */     return ParIterableLike.class.isStrictSplitterCollection(this);
/*     */   }
/*     */   
/*     */   public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask tsk) {
/*  46 */     return ParIterableLike.class.task2ops(this, tsk);
/*     */   }
/*     */   
/*     */   public <R> Object wrap(Function0 body) {
/*  46 */     return ParIterableLike.class.wrap(this, body);
/*     */   }
/*     */   
/*     */   public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(DelegatedSignalling it) {
/*  46 */     return ParIterableLike.class.delegatedSignalling2ops(this, it);
/*     */   }
/*     */   
/*     */   public <Elem, To> Object builder2ops(Builder cb) {
/*  46 */     return ParIterableLike.class.builder2ops(this, cb);
/*     */   }
/*     */   
/*     */   public <S, That> Object bf2seq(CanBuildFrom bf) {
/*  46 */     return ParIterableLike.class.bf2seq(this, bf);
/*     */   }
/*     */   
/*     */   public <S, That extends Parallel> ParHashMap<K, V> sequentially(Function1 b) {
/*  46 */     return (ParHashMap<K, V>)ParIterableLike.class.sequentially(this, b);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/*  46 */     return ParIterableLike.class.mkString(this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/*  46 */     return ParIterableLike.class.mkString(this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/*  46 */     return ParIterableLike.class.mkString(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  46 */     return ParIterableLike.class.toString(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object other) {
/*  46 */     return ParIterableLike.class.canEqual(this, other);
/*     */   }
/*     */   
/*     */   public <U> U reduce(Function2 op) {
/*  46 */     return (U)ParIterableLike.class.reduce(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceOption(Function2 op) {
/*  46 */     return ParIterableLike.class.reduceOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> U fold(Object z, Function2 op) {
/*  46 */     return (U)ParIterableLike.class.fold(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S aggregate(Object z, Function2 seqop, Function2 combop) {
/*  46 */     return (S)ParIterableLike.class.aggregate(this, z, seqop, combop);
/*     */   }
/*     */   
/*     */   public <S> S foldLeft(Object z, Function2 op) {
/*  46 */     return (S)ParIterableLike.class.foldLeft(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S foldRight(Object z, Function2 op) {
/*  46 */     return (S)ParIterableLike.class.foldRight(this, z, op);
/*     */   }
/*     */   
/*     */   public <U> U reduceLeft(Function2 op) {
/*  46 */     return (U)ParIterableLike.class.reduceLeft(this, op);
/*     */   }
/*     */   
/*     */   public <U> U reduceRight(Function2 op) {
/*  46 */     return (U)ParIterableLike.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceLeftOption(Function2 op) {
/*  46 */     return ParIterableLike.class.reduceLeftOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceRightOption(Function2 op) {
/*  46 */     return ParIterableLike.class.reduceRightOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/*  46 */     ParIterableLike.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/*  46 */     return ParIterableLike.class.count(this, p);
/*     */   }
/*     */   
/*     */   public <U> U sum(Numeric num) {
/*  46 */     return (U)ParIterableLike.class.sum(this, num);
/*     */   }
/*     */   
/*     */   public <U> U product(Numeric num) {
/*  46 */     return (U)ParIterableLike.class.product(this, num);
/*     */   }
/*     */   
/*     */   public <U> Tuple2<K, V> min(Ordering ord) {
/*  46 */     return (Tuple2<K, V>)ParIterableLike.class.min(this, ord);
/*     */   }
/*     */   
/*     */   public <U> Tuple2<K, V> max(Ordering ord) {
/*  46 */     return (Tuple2<K, V>)ParIterableLike.class.max(this, ord);
/*     */   }
/*     */   
/*     */   public <S> Tuple2<K, V> maxBy(Function1 f, Ordering cmp) {
/*  46 */     return (Tuple2<K, V>)ParIterableLike.class.maxBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <S> Tuple2<K, V> minBy(Function1 f, Ordering cmp) {
/*  46 */     return (Tuple2<K, V>)ParIterableLike.class.minBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <S, That> That map(Function1 f, CanBuildFrom bf) {
/*  46 */     return (That)ParIterableLike.class.map(this, f, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  46 */     return (That)ParIterableLike.class.collect(this, pf, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  46 */     return (That)ParIterableLike.class.flatMap(this, f, bf);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 pred) {
/*  46 */     return ParIterableLike.class.forall(this, pred);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 pred) {
/*  46 */     return ParIterableLike.class.exists(this, pred);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<K, V>> find(Function1 pred) {
/*  46 */     return ParIterableLike.class.find(this, pred);
/*     */   }
/*     */   
/*     */   public Object combinerFactory() {
/*  46 */     return ParIterableLike.class.combinerFactory(this);
/*     */   }
/*     */   
/*     */   public <S, That> Object combinerFactory(Function0 cbf) {
/*  46 */     return ParIterableLike.class.combinerFactory(this, cbf);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> filter(Function1 pred) {
/*  46 */     return (ParHashMap<K, V>)ParIterableLike.class.filter(this, pred);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> filterNot(Function1 pred) {
/*  46 */     return (ParHashMap<K, V>)ParIterableLike.class.filterNot(this, pred);
/*     */   }
/*     */   
/*     */   public <U, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/*  46 */     return (That)ParIterableLike.class.$plus$plus(this, that, bf);
/*     */   }
/*     */   
/*     */   public Tuple2<ParHashMap<K, V>, ParHashMap<K, V>> partition(Function1 pred) {
/*  46 */     return ParIterableLike.class.partition(this, pred);
/*     */   }
/*     */   
/*     */   public <K> ParMap<K, ParHashMap<K, V>> groupBy(Function1 f) {
/*  46 */     return ParIterableLike.class.groupBy(this, f);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> take(int n) {
/*  46 */     return (ParHashMap<K, V>)ParIterableLike.class.take(this, n);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> drop(int n) {
/*  46 */     return (ParHashMap<K, V>)ParIterableLike.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> slice(int unc_from, int unc_until) {
/*  46 */     return (ParHashMap<K, V>)ParIterableLike.class.slice(this, unc_from, unc_until);
/*     */   }
/*     */   
/*     */   public Tuple2<ParHashMap<K, V>, ParHashMap<K, V>> splitAt(int n) {
/*  46 */     return ParIterableLike.class.splitAt(this, n);
/*     */   }
/*     */   
/*     */   public <U, That> That scan(Object z, Function2 op, CanBuildFrom bf) {
/*  46 */     return (That)ParIterableLike.class.scan(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  46 */     return (That)ParIterableLike.class.scanLeft(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  46 */     return (That)ParIterableLike.class.scanRight(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> takeWhile(Function1 pred) {
/*  46 */     return (ParHashMap<K, V>)ParIterableLike.class.takeWhile(this, pred);
/*     */   }
/*     */   
/*     */   public Tuple2<ParHashMap<K, V>, ParHashMap<K, V>> span(Function1 pred) {
/*  46 */     return ParIterableLike.class.span(this, pred);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> dropWhile(Function1 pred) {
/*  46 */     return (ParHashMap<K, V>)ParIterableLike.class.dropWhile(this, pred);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs) {
/*  46 */     ParIterableLike.class.copyToArray(this, xs);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs, int start) {
/*  46 */     ParIterableLike.class.copyToArray(this, xs, start);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs, int start, int len) {
/*  46 */     ParIterableLike.class.copyToArray(this, xs, start, len);
/*     */   }
/*     */   
/*     */   public <U> boolean sameElements(GenIterable that) {
/*  46 */     return ParIterableLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public <U, S, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  46 */     return (That)ParIterableLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That zipWithIndex(CanBuildFrom bf) {
/*  46 */     return (That)ParIterableLike.class.zipWithIndex(this, bf);
/*     */   }
/*     */   
/*     */   public <S, U, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  46 */     return (That)ParIterableLike.class.zipAll(this, that, thisElem, thatElem, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That toParCollection(Function0 cbf) {
/*  46 */     return (That)ParIterableLike.class.toParCollection(this, cbf);
/*     */   }
/*     */   
/*     */   public <K, V, That> That toParMap(Function0 cbf, Predef$.less.colon.less ev) {
/*  46 */     return (That)ParIterableLike.class.toParMap(this, cbf, ev);
/*     */   }
/*     */   
/*     */   public Object view() {
/*  46 */     return ParIterableLike.class.view(this);
/*     */   }
/*     */   
/*     */   public <U> Object toArray(ClassTag evidence$1) {
/*  46 */     return ParIterableLike.class.toArray(this, evidence$1);
/*     */   }
/*     */   
/*     */   public List<Tuple2<K, V>> toList() {
/*  46 */     return ParIterableLike.class.toList(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<Tuple2<K, V>> toIndexedSeq() {
/*  46 */     return ParIterableLike.class.toIndexedSeq(this);
/*     */   }
/*     */   
/*     */   public Stream<Tuple2<K, V>> toStream() {
/*  46 */     return ParIterableLike.class.toStream(this);
/*     */   }
/*     */   
/*     */   public Iterator<Tuple2<K, V>> toIterator() {
/*  46 */     return ParIterableLike.class.toIterator(this);
/*     */   }
/*     */   
/*     */   public <U> Buffer<U> toBuffer() {
/*  46 */     return ParIterableLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public GenTraversable<Tuple2<K, V>> toTraversable() {
/*  46 */     return ParIterableLike.class.toTraversable(this);
/*     */   }
/*     */   
/*     */   public <U> ParSet<U> toSet() {
/*  46 */     return ParIterableLike.class.toSet(this);
/*     */   }
/*     */   
/*     */   public Vector<Tuple2<K, V>> toVector() {
/*  46 */     return ParIterableLike.class.toVector(this);
/*     */   }
/*     */   
/*     */   public <Col> Col to(CanBuildFrom cbf) {
/*  46 */     return (Col)ParIterableLike.class.to(this, cbf);
/*     */   }
/*     */   
/*     */   public int scanBlockSize() {
/*  46 */     return ParIterableLike.class.scanBlockSize(this);
/*     */   }
/*     */   
/*     */   public <S> S $div$colon(Object z, Function2 op) {
/*  46 */     return (S)ParIterableLike.class.$div$colon(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S $colon$bslash(Object z, Function2 op) {
/*  46 */     return (S)ParIterableLike.class.$colon$bslash(this, z, op);
/*     */   }
/*     */   
/*     */   public String debugInformation() {
/*  46 */     return ParIterableLike.class.debugInformation(this);
/*     */   }
/*     */   
/*     */   public Seq<String> brokenInvariants() {
/*  46 */     return ParIterableLike.class.brokenInvariants(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<String> debugBuffer() {
/*  46 */     return ParIterableLike.class.debugBuffer(this);
/*     */   }
/*     */   
/*     */   public void debugclear() {
/*  46 */     ParIterableLike.class.debugclear(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<String> debuglog(String s) {
/*  46 */     return ParIterableLike.class.debuglog(this, s);
/*     */   }
/*     */   
/*     */   public void printDebugBuffer() {
/*  46 */     ParIterableLike.class.printDebugBuffer(this);
/*     */   }
/*     */   
/*     */   public Combiner<Tuple2<K, V>, ParHashMap<K, V>> parCombiner() {
/*  46 */     return CustomParallelizable.class.parCombiner((CustomParallelizable)this);
/*     */   }
/*     */   
/*     */   public <P, Q> Combiner<Tuple2<P, Q>, ParHashMap<P, Q>> genericMapCombiner() {
/*  46 */     return GenericParMapTemplate.class.genericMapCombiner(this);
/*     */   }
/*     */   
/*     */   public Builder<Tuple2<K, V>, ParIterable<Tuple2<K, V>>> newBuilder() {
/*  46 */     return GenericParTemplate.class.newBuilder(this);
/*     */   }
/*     */   
/*     */   public <B> Combiner<B, ParIterable<B>> genericBuilder() {
/*  46 */     return GenericParTemplate.class.genericBuilder(this);
/*     */   }
/*     */   
/*     */   public <B> Combiner<B, ParIterable<B>> genericCombiner() {
/*  46 */     return GenericParTemplate.class.genericCombiner(this);
/*     */   }
/*     */   
/*     */   public <A1, A2> Tuple2<ParIterable<A1>, ParIterable<A2>> unzip(Function1 asPair) {
/*  46 */     return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*     */   }
/*     */   
/*     */   public <A1, A2, A3> Tuple3<ParIterable<A1>, ParIterable<A2>, ParIterable<A3>> unzip3(Function1 asTriple) {
/*  46 */     return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*     */   }
/*     */   
/*     */   public <B> ParIterable<B> flatten(Function1 asTraversable) {
/*  46 */     return (ParIterable<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public <B> ParIterable<ParIterable<B>> transpose(Function1 asTraversable) {
/*  46 */     return (ParIterable<ParIterable<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  46 */     return GenMapLike.class.hashCode((GenMapLike)this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*  46 */     return GenMapLike.class.equals((GenMapLike)this, that);
/*     */   }
/*     */   
/*     */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  46 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public ParHashMap(HashMap<K, V> trie) {
/*  46 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  46 */     Parallelizable.class.$init$((Parallelizable)this);
/*  46 */     GenMapLike.class.$init$((GenMapLike)this);
/*  46 */     GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/*  46 */     GenTraversable.class.$init$((GenTraversable)this);
/*  46 */     GenIterable.class.$init$(this);
/*  46 */     GenericParTemplate.class.$init$(this);
/*  46 */     GenericParMapTemplate.class.$init$(this);
/*  46 */     CustomParallelizable.class.$init$((CustomParallelizable)this);
/*  46 */     ParIterableLike.class.$init$(this);
/*  46 */     ParIterable.class.$init$(this);
/*  46 */     ParMapLike.class.$init$(this);
/*  46 */     ParMap.class.$init$(this);
/*  46 */     ParIterable$class.$init$(this);
/*  46 */     ParMap$class.$init$(this);
/*     */   }
/*     */   
/*     */   public ParHashMap() {
/*  54 */     this(HashMap$.MODULE$.empty());
/*     */   }
/*     */   
/*     */   public GenericParMapCompanion<ParHashMap> mapCompanion() {
/*  56 */     return (GenericParMapCompanion<ParHashMap>)ParHashMap$.MODULE$;
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> empty() {
/*  58 */     return new ParHashMap();
/*     */   }
/*     */   
/*     */   public HashMapCombiner<K, V> newCombiner() {
/*  60 */     return HashMapCombiner$.MODULE$.apply();
/*     */   }
/*     */   
/*     */   public IterableSplitter<Tuple2<K, V>> splitter() {
/*  62 */     return new ParHashMapIterator(this, this.trie.iterator(), this.trie.size());
/*     */   }
/*     */   
/*     */   public HashMap<K, V> seq() {
/*  64 */     return this.trie;
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> $minus(Object k) {
/*  66 */     return new ParHashMap(this.trie.$minus(k));
/*     */   }
/*     */   
/*     */   public <U> ParHashMap<K, U> $plus(Tuple2 kv) {
/*  68 */     return new ParHashMap(this.trie.$plus(kv));
/*     */   }
/*     */   
/*     */   public Option<V> get(Object k) {
/*  70 */     return this.trie.get(k);
/*     */   }
/*     */   
/*     */   public int size() {
/*  72 */     return this.trie.size();
/*     */   }
/*     */   
/*     */   public <S, That> Combiner<S, That> reuse(Option oldc, Combiner<S, That> newc) {
/*     */     Combiner<S, That> combiner;
/*  74 */     if (oldc instanceof Some) {
/*  74 */       Some some = (Some)oldc;
/*  75 */       combiner = (Combiner)some.x();
/*     */     } else {
/*  76 */       if (None$.MODULE$ == null) {
/*  76 */         if (oldc != null)
/*     */           throw new MatchError(oldc); 
/*  76 */       } else if (!None$.MODULE$.equals(oldc)) {
/*     */         throw new MatchError(oldc);
/*     */       } 
/*  76 */       combiner = newc;
/*     */     } 
/*     */     return combiner;
/*     */   }
/*     */   
/*     */   public class ParHashMapIterator implements IterableSplitter<Tuple2<K, V>> {
/*     */     private Iterator<Tuple2<K, V>> triter;
/*     */     
/*     */     private final int sz;
/*     */     
/*     */     private int i;
/*     */     
/*     */     private Signalling signalDelegate;
/*     */     
/*     */     public Signalling signalDelegate() {
/*  79 */       return this.signalDelegate;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void signalDelegate_$eq(Signalling x$1) {
/*  79 */       this.signalDelegate = x$1;
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<Tuple2<K, V>>> splitWithSignalling() {
/*  79 */       return IterableSplitter.class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/*  79 */       return IterableSplitter.class.shouldSplitFurther(this, coll, parallelismLevel);
/*     */     }
/*     */     
/*     */     public String buildString(Function1 closure) {
/*  79 */       return IterableSplitter.class.buildString(this, closure);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/*  79 */       return IterableSplitter.class.debugInformation(this);
/*     */     }
/*     */     
/*     */     public IterableSplitter<Tuple2<K, V>>.Taken newTaken(int until) {
/*  79 */       return IterableSplitter.class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public <U extends IterableSplitter<Tuple2<K, V>>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/*  79 */       return (U)IterableSplitter.class.newSliceInternal(this, it, from1);
/*     */     }
/*     */     
/*     */     public IterableSplitter<Tuple2<K, V>> take(int n) {
/*  79 */       return IterableSplitter.class.take(this, n);
/*     */     }
/*     */     
/*     */     public IterableSplitter<Tuple2<K, V>> slice(int from1, int until1) {
/*  79 */       return IterableSplitter.class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<Tuple2<K, V>>.Mapped<S> map(Function1 f) {
/*  79 */       return IterableSplitter.class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends IterableSplitter<U>> IterableSplitter<Tuple2<K, V>>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/*  79 */       return IterableSplitter.class.appendParIterable(this, that);
/*     */     }
/*     */     
/*     */     public <S> IterableSplitter<Tuple2<K, V>>.Zipped<S> zipParSeq(SeqSplitter that) {
/*  79 */       return IterableSplitter.class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> IterableSplitter<Tuple2<K, V>>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/*  79 */       return IterableSplitter.class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public boolean isAborted() {
/*  79 */       return DelegatedSignalling.class.isAborted((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public void abort() {
/*  79 */       DelegatedSignalling.class.abort((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public int indexFlag() {
/*  79 */       return DelegatedSignalling.class.indexFlag((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public void setIndexFlag(int f) {
/*  79 */       DelegatedSignalling.class.setIndexFlag((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfGreater(int f) {
/*  79 */       DelegatedSignalling.class.setIndexFlagIfGreater((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfLesser(int f) {
/*  79 */       DelegatedSignalling.class.setIndexFlagIfLesser((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public int tag() {
/*  79 */       return DelegatedSignalling.class.tag((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/*  79 */       return AugmentedIterableIterator.class.count((AugmentedIterableIterator)this, p);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/*  79 */       return (U)AugmentedIterableIterator.class.reduce((AugmentedIterableIterator)this, op);
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2 op) {
/*  79 */       return (U)AugmentedIterableIterator.class.fold((AugmentedIterableIterator)this, z, op);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/*  79 */       return (U)AugmentedIterableIterator.class.sum((AugmentedIterableIterator)this, num);
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/*  79 */       return (U)AugmentedIterableIterator.class.product((AugmentedIterableIterator)this, num);
/*     */     }
/*     */     
/*     */     public <U> Tuple2<K, V> min(Ordering ord) {
/*  79 */       return (Tuple2<K, V>)AugmentedIterableIterator.class.min((AugmentedIterableIterator)this, ord);
/*     */     }
/*     */     
/*     */     public <U> Tuple2<K, V> max(Ordering ord) {
/*  79 */       return (Tuple2<K, V>)AugmentedIterableIterator.class.max((AugmentedIterableIterator)this, ord);
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object array, int from, int len) {
/*  79 */       AugmentedIterableIterator.class.copyToArray((AugmentedIterableIterator)this, array, from, len);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(int howmany, Function2 op) {
/*  79 */       return (U)AugmentedIterableIterator.class.reduceLeft((AugmentedIterableIterator)this, howmany, op);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> map2combiner(Function1 f, Combiner cb) {
/*  79 */       return AugmentedIterableIterator.class.map2combiner((AugmentedIterableIterator)this, f, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction pf, Combiner cb) {
/*  79 */       return AugmentedIterableIterator.class.collect2combiner((AugmentedIterableIterator)this, pf, cb);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner cb) {
/*  79 */       return AugmentedIterableIterator.class.flatmap2combiner((AugmentedIterableIterator)this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder b) {
/*  79 */       return (Bld)AugmentedIterableIterator.class.copy2builder((AugmentedIterableIterator)this, b);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filter2combiner(Function1 pred, Combiner cb) {
/*  79 */       return AugmentedIterableIterator.class.filter2combiner((AugmentedIterableIterator)this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filterNot2combiner(Function1 pred, Combiner cb) {
/*  79 */       return AugmentedIterableIterator.class.filterNot2combiner((AugmentedIterableIterator)this, pred, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1 pred, Combiner btrue, Combiner bfalse) {
/*  79 */       return AugmentedIterableIterator.class.partition2combiners((AugmentedIterableIterator)this, pred, btrue, bfalse);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner cb) {
/*  79 */       return AugmentedIterableIterator.class.take2combiner((AugmentedIterableIterator)this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner cb) {
/*  79 */       return AugmentedIterableIterator.class.drop2combiner((AugmentedIterableIterator)this, n, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/*  79 */       return AugmentedIterableIterator.class.slice2combiner((AugmentedIterableIterator)this, from, until, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/*  79 */       return AugmentedIterableIterator.class.splitAt2combiners((AugmentedIterableIterator)this, at, before, after);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/*  79 */       return AugmentedIterableIterator.class.takeWhile2combiner((AugmentedIterableIterator)this, p, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/*  79 */       return AugmentedIterableIterator.class.span2combiners((AugmentedIterableIterator)this, p, before, after);
/*     */     }
/*     */     
/*     */     public <U, A> void scanToArray(Object z, Function2 op, Object array, int from) {
/*  79 */       AugmentedIterableIterator.class.scanToArray((AugmentedIterableIterator)this, z, op, array, from);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/*  79 */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/*  79 */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, howmany, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/*  79 */       return AugmentedIterableIterator.class.zip2combiner((AugmentedIterableIterator)this, otherpit, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/*  79 */       return AugmentedIterableIterator.class.zipAll2combiner((AugmentedIterableIterator)this, that, thiselem, thatelem, cb);
/*     */     }
/*     */     
/*     */     public boolean isRemainingCheap() {
/*  79 */       return RemainsIterator.class.isRemainingCheap((RemainsIterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<K, V>> seq() {
/*  79 */       return Iterator.class.seq((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*  79 */       return Iterator.class.isEmpty((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/*  79 */       return Iterator.class.isTraversableAgain((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/*  79 */       return Iterator.class.hasDefiniteSize((Iterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<K, V>> drop(int n) {
/*  79 */       return Iterator.class.drop((Iterator)this, n);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/*  79 */       return Iterator.class.$plus$plus((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/*  79 */       return Iterator.class.flatMap((Iterator)this, f);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<K, V>> filter(Function1 p) {
/*  79 */       return Iterator.class.filter((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/*  79 */       return Iterator.class.corresponds((Iterator)this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<K, V>> withFilter(Function1 p) {
/*  79 */       return Iterator.class.withFilter((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<K, V>> filterNot(Function1 p) {
/*  79 */       return Iterator.class.filterNot((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/*  79 */       return Iterator.class.collect((Iterator)this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/*  79 */       return Iterator.class.scanLeft((Iterator)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/*  79 */       return Iterator.class.scanRight((Iterator)this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<K, V>> takeWhile(Function1 p) {
/*  79 */       return Iterator.class.takeWhile((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Tuple2<K, V>>, Iterator<Tuple2<K, V>>> partition(Function1 p) {
/*  79 */       return Iterator.class.partition((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Tuple2<K, V>>, Iterator<Tuple2<K, V>>> span(Function1 p) {
/*  79 */       return Iterator.class.span((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<K, V>> dropWhile(Function1 p) {
/*  79 */       return Iterator.class.dropWhile((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<Tuple2<K, V>, B>> zip(Iterator that) {
/*  79 */       return Iterator.class.zip((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/*  79 */       return Iterator.class.padTo((Iterator)this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<Tuple2<K, V>, Object>> zipWithIndex() {
/*  79 */       return Iterator.class.zipWithIndex((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/*  79 */       return Iterator.class.zipAll((Iterator)this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  79 */       Iterator.class.foreach((Iterator)this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/*  79 */       return Iterator.class.forall((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/*  79 */       return Iterator.class.exists((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/*  79 */       return Iterator.class.contains((Iterator)this, elem);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<K, V>> find(Function1 p) {
/*  79 */       return Iterator.class.find((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/*  79 */       return Iterator.class.indexWhere((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/*  79 */       return Iterator.class.indexOf((Iterator)this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<Tuple2<K, V>> buffered() {
/*  79 */       return Iterator.class.buffered((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<K, V>>.GroupedIterator<B> grouped(int size) {
/*  79 */       return Iterator.class.grouped((Iterator)this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<K, V>>.GroupedIterator<B> sliding(int size, int step) {
/*  79 */       return Iterator.class.sliding((Iterator)this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/*  79 */       return Iterator.class.length((Iterator)this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<Tuple2<K, V>>, Iterator<Tuple2<K, V>>> duplicate() {
/*  79 */       return Iterator.class.duplicate((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/*  79 */       return Iterator.class.patch((Iterator)this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/*  79 */       return Iterator.class.sameElements((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public Traversable<Tuple2<K, V>> toTraversable() {
/*  79 */       return Iterator.class.toTraversable((Iterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<K, V>> toIterator() {
/*  79 */       return Iterator.class.toIterator((Iterator)this);
/*     */     }
/*     */     
/*     */     public Stream<Tuple2<K, V>> toStream() {
/*  79 */       return Iterator.class.toStream((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/*  79 */       return Iterator.class.sliding$default$2((Iterator)this);
/*     */     }
/*     */     
/*     */     public List<Tuple2<K, V>> reversed() {
/*  79 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/*  79 */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/*  79 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/*  79 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/*  79 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/*  79 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/*  79 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/*  79 */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/*  79 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/*  79 */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/*  79 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/*  79 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/*  79 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  79 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> Tuple2<K, V> maxBy(Function1 f, Ordering cmp) {
/*  79 */       return (Tuple2<K, V>)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> Tuple2<K, V> minBy(Function1 f, Ordering cmp) {
/*  79 */       return (Tuple2<K, V>)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/*  79 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/*  79 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/*  79 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/*  79 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<Tuple2<K, V>> toList() {
/*  79 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<Tuple2<K, V>> toIterable() {
/*  79 */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<Tuple2<K, V>> toSeq() {
/*  79 */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<Tuple2<K, V>> toIndexedSeq() {
/*  79 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/*  79 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*  79 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<Tuple2<K, V>> toVector() {
/*  79 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/*  79 */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  79 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/*  79 */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/*  79 */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/*  79 */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  79 */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/*  79 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/*  79 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  79 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<K, V>> triter() {
/*  79 */       return this.triter;
/*     */     }
/*     */     
/*     */     public void triter_$eq(Iterator<Tuple2<K, V>> x$1) {
/*  79 */       this.triter = x$1;
/*     */     }
/*     */     
/*     */     public int sz() {
/*  79 */       return this.sz;
/*     */     }
/*     */     
/*     */     public ParHashMapIterator(ParHashMap $outer, Iterator<Tuple2<K, V>> triter, int sz) {
/*  79 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  79 */       TraversableOnce.class.$init$((TraversableOnce)this);
/*  79 */       Iterator.class.$init$((Iterator)this);
/*  79 */       RemainsIterator.class.$init$((RemainsIterator)this);
/*  79 */       AugmentedIterableIterator.class.$init$((AugmentedIterableIterator)this);
/*  79 */       DelegatedSignalling.class.$init$((DelegatedSignalling)this);
/*  79 */       IterableSplitter.class.$init$(this);
/*  81 */       this.i = 0;
/*     */     }
/*     */     
/*     */     public int i() {
/*  81 */       return this.i;
/*     */     }
/*     */     
/*     */     public void i_$eq(int x$1) {
/*  81 */       this.i = x$1;
/*     */     }
/*     */     
/*     */     public IterableSplitter<Tuple2<K, V>> dup() {
/*     */       ParHashMapIterator parHashMapIterator;
/*  82 */       Iterator<Tuple2<K, V>> iterator = triter();
/*  83 */       if (iterator instanceof TrieIterator) {
/*  83 */         TrieIterator trieIterator = (TrieIterator)iterator;
/*  84 */         parHashMapIterator = dupFromIterator((Iterator<Tuple2<K, V>>)trieIterator.dupIterator());
/*     */       } else {
/*  86 */         Buffer buff = triter().toBuffer();
/*  87 */         triter_$eq(buff.iterator());
/*  88 */         parHashMapIterator = dupFromIterator(buff.iterator());
/*     */       } 
/*     */       return parHashMapIterator;
/*     */     }
/*     */     
/*     */     private ParHashMapIterator dupFromIterator(Iterator<Tuple2<K, V>> it) {
/*  91 */       ParHashMapIterator phit = new ParHashMapIterator(scala$collection$parallel$immutable$ParHashMap$ParHashMapIterator$$$outer(), it, sz());
/*  92 */       phit.i_$eq(i());
/*  93 */       return phit;
/*     */     }
/*     */     
/*     */     public Seq<IterableSplitter<Tuple2<K, V>>> split() {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: invokevirtual remaining : ()I
/*     */       //   4: iconst_2
/*     */       //   5: if_icmpge -> 37
/*     */       //   8: getstatic scala/collection/Seq$.MODULE$ : Lscala/collection/Seq$;
/*     */       //   11: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */       //   14: iconst_1
/*     */       //   15: anewarray scala/collection/parallel/immutable/ParHashMap$ParHashMapIterator
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
/*     */       //   158: anewarray scala/collection/parallel/immutable/ParHashMap$ParHashMapIterator
/*     */       //   161: dup
/*     */       //   162: iconst_0
/*     */       //   163: new scala/collection/parallel/immutable/ParHashMap$ParHashMapIterator
/*     */       //   166: dup
/*     */       //   167: aload_0
/*     */       //   168: invokevirtual scala$collection$parallel$immutable$ParHashMap$ParHashMapIterator$$$outer : ()Lscala/collection/parallel/immutable/ParHashMap;
/*     */       //   171: aload #5
/*     */       //   173: iload #6
/*     */       //   175: invokespecial <init> : (Lscala/collection/parallel/immutable/ParHashMap;Lscala/collection/Iterator;I)V
/*     */       //   178: aastore
/*     */       //   179: dup
/*     */       //   180: iconst_1
/*     */       //   181: new scala/collection/parallel/immutable/ParHashMap$ParHashMapIterator
/*     */       //   184: dup
/*     */       //   185: aload_0
/*     */       //   186: invokevirtual scala$collection$parallel$immutable$ParHashMap$ParHashMapIterator$$$outer : ()Lscala/collection/parallel/immutable/ParHashMap;
/*     */       //   189: aload #7
/*     */       //   191: iload #8
/*     */       //   193: invokespecial <init> : (Lscala/collection/parallel/immutable/ParHashMap;Lscala/collection/Iterator;I)V
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
/*     */       //   329: new scala/collection/parallel/immutable/ParHashMap$ParHashMapIterator$$anonfun$split$1
/*     */       //   332: dup
/*     */       //   333: aload_0
/*     */       //   334: invokespecial <init> : (Lscala/collection/parallel/immutable/ParHashMap$ParHashMapIterator;)V
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
/*     */       //   #95	-> 0
/*     */       //   #96	-> 42
/*     */       //   #97	-> 54
/*     */       //   #98	-> 60
/*     */       //   #99	-> 144
/*     */       //   #100	-> 151
/*     */       //   #101	-> 163
/*     */       //   #100	-> 179
/*     */       //   #102	-> 181
/*     */       //   #100	-> 200
/*     */       //   #96	-> 209
/*     */       //   #98	-> 214
/*     */       //   #106	-> 224
/*     */       //   #107	-> 235
/*     */       //   #108	-> 297
/*     */       //   #104	-> 351
/*     */       //   #95	-> 353
/*     */       //   #107	-> 356
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	366	0	this	Lscala/collection/parallel/immutable/ParHashMap$ParHashMapIterator;
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
/*     */     public class ParHashMap$ParHashMapIterator$$anonfun$split$1 extends AbstractFunction1<Buffer<Tuple2<K, V>>, ParHashMapIterator> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final ParHashMap<K, V>.ParHashMapIterator apply(Buffer b) {
/* 108 */         return new ParHashMap.ParHashMapIterator(this.$outer.scala$collection$parallel$immutable$ParHashMap$ParHashMapIterator$$$outer(), b.iterator(), b.length());
/*     */       }
/*     */       
/*     */       public ParHashMap$ParHashMapIterator$$anonfun$split$1(ParHashMap.ParHashMapIterator $outer) {}
/*     */     }
/*     */     
/*     */     public Tuple2<K, V> next() {
/* 111 */       i_$eq(i() + 1);
/* 112 */       Tuple2<K, V> r = (Tuple2)triter().next();
/* 113 */       return r;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 116 */       return (i() < sz());
/*     */     }
/*     */     
/*     */     public int remaining() {
/* 118 */       return sz() - i();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 119 */       return (new StringBuilder()).append("HashTrieIterator(").append(BoxesRunTime.boxToInteger(sz())).append(")").toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public void printDebugInfo() {
/* 125 */     Predef$.MODULE$.println("Parallel hash trie");
/* 126 */     Predef$.MODULE$.println((new StringBuilder()).append("Top level inner trie type: ").append(this.trie.getClass()).toString());
/* 127 */     HashMap<K, V> hashMap = this.trie;
/* 128 */     if (hashMap instanceof HashMap.HashMap1) {
/* 128 */       HashMap.HashMap1 hashMap1 = (HashMap.HashMap1)hashMap;
/* 129 */       Predef$.MODULE$.println("single node type");
/* 130 */       Predef$.MODULE$.println((new StringBuilder()).append("key stored: ").append(hashMap1.getKey()).toString());
/* 131 */       Predef$.MODULE$.println((new StringBuilder()).append("hash of key: ").append(BoxesRunTime.boxToInteger(hashMap1.getHash())).toString());
/* 132 */       Predef$.MODULE$.println((new StringBuilder()).append("computed hash of ").append(hashMap1.getKey()).append(": ").append(BoxesRunTime.boxToInteger(hashMap1.computeHashFor(hashMap1.getKey()))).toString());
/* 133 */       Predef$.MODULE$.println((new StringBuilder()).append("trie.get(key): ").append(hashMap1.get(hashMap1.getKey())).toString());
/*     */     } else {
/* 135 */       Predef$.MODULE$.println("other kind of node");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void totalcombines_$eq(AtomicInteger paramAtomicInteger) {
/*     */     ParHashMap$.MODULE$.totalcombines_$eq(paramAtomicInteger);
/*     */   }
/*     */   
/*     */   public static AtomicInteger totalcombines() {
/*     */     return ParHashMap$.MODULE$.totalcombines();
/*     */   }
/*     */   
/*     */   public static <K, V> ParHashMap<K, V> fromTrie(HashMap<K, V> paramHashMap) {
/*     */     return ParHashMap$.MODULE$.fromTrie(paramHashMap);
/*     */   }
/*     */   
/*     */   public static <K, V> CanCombineFrom<ParHashMap<?, ?>, Tuple2<K, V>, ParHashMap<K, V>> canBuildFrom() {
/*     */     return ParHashMap$.MODULE$.canBuildFrom();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\ParHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */