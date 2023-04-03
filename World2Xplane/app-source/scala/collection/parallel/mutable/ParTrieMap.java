/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
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
/*     */ import scala.collection.Seq$;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.concurrent.BasicNode;
/*     */ import scala.collection.concurrent.CNode;
/*     */ import scala.collection.concurrent.INode;
/*     */ import scala.collection.concurrent.LNode;
/*     */ import scala.collection.concurrent.MainNode;
/*     */ import scala.collection.concurrent.TNode;
/*     */ import scala.collection.concurrent.TrieMap;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.CanCombineFrom;
/*     */ import scala.collection.generic.DelegatedSignalling;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericParMapCompanion;
/*     */ import scala.collection.generic.GenericParMapTemplate;
/*     */ import scala.collection.generic.GenericParTemplate;
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
/*     */ import scala.collection.mutable.Iterable;
/*     */ import scala.collection.mutable.Map;
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
/*     */ import scala.collection.parallel.Splitter;
/*     */ import scala.collection.parallel.Task;
/*     */ import scala.collection.parallel.TaskSupport;
/*     */ import scala.collection.parallel.immutable.ParMap;
/*     */ import scala.collection.parallel.immutable.ParSet;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t=a\001B\001\003\005-\021!\002U1s)JLW-T1q\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021\001\0039be\006dG.\0327\013\005\035A\021AC2pY2,7\r^5p]*\t\021\"A\003tG\006d\027m\001\001\026\00719\022eE\004\001\033E\031#\006N\034\021\0059yQ\"\001\005\n\005AA!AB!osJ+g\r\005\003\023'U\001S\"\001\002\n\005Q\021!A\002)be6\013\007\017\005\002\027/1\001A!\002\r\001\005\004I\"!A&\022\005ii\002C\001\b\034\023\ta\002BA\004O_RD\027N\\4\021\0059q\022BA\020\t\005\r\te.\037\t\003-\005\"QA\t\001C\002e\021\021A\026\t\006I\035*\002%K\007\002K)\021aEB\001\bO\026tWM]5d\023\tASEA\013HK:,'/[2QCJl\025\r\035+f[Bd\027\r^3\021\005I\001\001C\002\n,+\001jc&\003\002-\005\tQ\001+\031:NCBd\025n[3\021\tI\001Q\003\t\t\005_I*\002%D\0011\025\t\td!\001\006d_:\034WO\035:f]RL!a\r\031\003\017Q\023\030.Z'baB!!#N\013!\023\t1$A\001\nQCJ$&/[3NCB\034u.\0342j]\026\024\bC\001\b9\023\tI\004B\001\007TKJL\027\r\\5{C\ndW\r\003\005<\001\t\025\r\021\"\003=\003\025\031GO]5f+\005q\003\002\003 \001\005\003\005\013\021\002\030\002\r\r$(/[3!\021\031\001\005\001\"\001\007\003\0061A(\0338jiz\"\"!\f\"\t\013mz\004\031\001\030\t\013\001\003A\021\001#\025\0035BQA\022\001\005B\035\013A\"\\1q\007>l\007/\0318j_:,\022\001\023\t\004I%K\023B\001&&\005Y9UM\\3sS\016\004\026M]'ba\016{W\016]1oS>t\007\"\002'\001\t\003j\025!B3naRLX#A\027\t\r=\003\001\025\"\025Q\003-qWm^\"p[\nLg.\032:\026\003E\003BAU*V[5\tA!\003\002U\t\tA1i\\7cS:,'\017\005\003\017-V\001\023BA,\t\005\031!V\017\0357fe!)\021\f\001C!y\005\0311/Z9\t\013m\003A\021\001/\002\021M\004H.\033;uKJ,\022!\030\t\005%y+\002%\003\002`\005\t\021\002+\031:Ue&,W*\0319Ta2LG\017^3s\021\025\t\007\001\"\021c\003\025\031G.Z1s)\005\031\007C\001\be\023\t)\007B\001\003V]&$\b\"B4\001\t\003!\025A\002:fgVdG\017C\003j\001\021\005!.A\002hKR$\"a\0338\021\0079a\007%\003\002n\021\t1q\n\035;j_:DQa\0345A\002U\t1a[3z\021\025\t\b\001\"\001s\003\r\001X\017\036\013\004WN$\b\"B8q\001\004)\002\"B;q\001\004\001\023!\002<bYV,\007\"B<\001\t\003A\030AB;qI\006$X\rF\002dsjDQa\034<A\002UAQ!\036<A\002\001BQ\001 \001\005\002u\faA]3n_Z,GCA6\021\025y7\0201\001\026\021\035\t\t\001\001C\001\003\007\t\001\002\n9mkN$S-\035\013\005\003\013\t9!D\001\001\021\031\tIa a\001+\006\0211N\036\005\b\003\033\001A\021AA\b\003%!S.\0338vg\022*\027\017\006\003\002\006\005E\001BB8\002\f\001\007Q\003C\004\002\026\001!\t%a\006\002\tML'0Z\013\003\0033\0012ADA\016\023\r\ti\002\003\002\004\023:$\bbBA\021\001\021\005\0231E\001\rgR\024\030N\\4Qe\0264\027\016_\013\003\003K\001B!a\n\00225\021\021\021\006\006\005\003W\ti#\001\003mC:<'BAA\030\003\021Q\027M^1\n\t\005M\022\021\006\002\007'R\024\030N\\4\007\r\005]\002\001AA\035\005\021\031\026N_3\024\013\005UR\"a\017\021\017I\013i$!\007\002B%\031\021q\b\003\003\tQ\0137o\033\t\005\003\013\t)\004C\006\002F\005U\"\021!Q\001\n\005e\021AB8gMN,G\017C\006\002J\005U\"\021!Q\001\n\005e\021a\0025po6\fg.\037\005\f\003\033\n)D!A!\002\023\ty%A\003beJ\f\027\020E\003\017\003#\n)&C\002\002T!\021Q!\021:sCf\0042aLA,\023\r\tI\006\r\002\n\005\006\034\030n\031(pI\026Dq\001QA\033\t\003\ti\006\006\005\002B\005}\023\021MA2\021!\t)%a\027A\002\005e\001\002CA%\0037\002\r!!\007\t\021\0055\0231\fa\001\003\037B\021bZA\033\001\004%\t!a\006\t\025\005%\024Q\007a\001\n\003\tY'\001\006sKN,H\016^0%KF$2aYA7\021)\ty'a\032\002\002\003\007\021\021D\001\004q\022\n\004\"CA:\003k\001\013\025BA\r\003\035\021Xm];mi\002B\001\"a\036\0026\021\005\021\021P\001\005Y\026\fg\rF\002d\003wB\001\"! \002v\001\007\021qP\001\005aJ,g\017\005\003\017Y\006e\001\002CAB\003k!\t!!\"\002\013M\004H.\033;\026\005\005\035\005CBAE\003\027\013\t%D\001\007\023\r\tiI\002\002\004'\026\f\b\002CAI\003k!\t!a%\002%MDw.\0367e'Bd\027\016\036$veRDWM]\013\003\003+\0032ADAL\023\r\tI\n\003\002\b\005>|G.Z1o\021!\ti*!\016\005B\005}\025!B7fe\036,GcA2\002\"\"A\0211UAN\001\004\t\t%\001\003uQ\006$xaBAT\005!\005\021\021V\001\013!\006\024HK]5f\033\006\004\bc\001\n\002,\0321\021A\001E\001\003[\033R!a+\0020^\002B\001JAYS%\031\0211W\023\003\033A\013'/T1q\r\006\034Go\034:z\021\035\001\0251\026C\001\003o#\"!!+\t\0171\013Y\013\"\001\002<V1\021QXAb\003\017,\"!a0\021\rI\001\021\021YAc!\r1\0221\031\003\0071\005e&\031A\r\021\007Y\t9\r\002\004#\003s\023\r!\007\005\b\037\006-F\021AAf+\031\ti-!6\002ZV\021\021q\032\t\007%N\013\t.a7\021\r91\0261[Al!\r1\022Q\033\003\0071\005%'\031A\r\021\007Y\tI\016\002\004#\003\023\024\r!\007\t\007%\001\t\031.a6\t\021\005}\0271\026C\002\003C\fAbY1o\005VLG\016\032$s_6,b!a9\002|\006}XCAAs!%!\023q]Av\003o\024\t!C\002\002j\026\022abQ1o\007>l'-\0338f\rJ|W\016\005\003\002n\006=XBAAV\023\021\t\t0a=\003\t\r{G\016\\\005\004\003k,#!D$f]6\013\007OR1di>\024\030\020\005\004\017-\006e\030Q \t\004-\005mHA\002\r\002^\n\007\021\004E\002\027\003$aAIAo\005\004I\002C\002\n\001\003s\fi\020\003\006\003\006\005-\026\021!C\005\005\017\t1B]3bIJ+7o\0347wKR\021!\021\002\t\005\003O\021Y!\003\003\003\016\005%\"AB(cU\026\034G\017")
/*     */ public final class ParTrieMap<K, V> implements ParMap<K, V>, GenericParMapTemplate<K, V, ParTrieMap>, ParMapLike<K, V, ParTrieMap<K, V>, TrieMap<K, V>>, ParTrieMapCombiner<K, V>, Serializable {
/*     */   private final TrieMap<K, V> scala$collection$parallel$mutable$ParTrieMap$$ctrie;
/*     */   
/*     */   private volatile transient TaskSupport _combinerTaskSupport;
/*     */   
/*     */   private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */   
/*     */   private volatile ParIterableLike$ScanNode$ ScanNode$module;
/*     */   
/*     */   private volatile ParIterableLike$ScanLeaf$ ScanLeaf$module;
/*     */   
/*     */   public <N extends Tuple2<K, V>, NewTo> Combiner<N, NewTo> combine(Combiner other) {
/*  39 */     return ParTrieMapCombiner$class.combine(this, other);
/*     */   }
/*     */   
/*     */   public boolean canBeShared() {
/*  39 */     return ParTrieMapCombiner$class.canBeShared(this);
/*     */   }
/*     */   
/*     */   public TaskSupport _combinerTaskSupport() {
/*  39 */     return this._combinerTaskSupport;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void _combinerTaskSupport_$eq(TaskSupport x$1) {
/*  39 */     this._combinerTaskSupport = x$1;
/*     */   }
/*     */   
/*     */   public TaskSupport combinerTaskSupport() {
/*  39 */     return Combiner.class.combinerTaskSupport(this);
/*     */   }
/*     */   
/*     */   public void combinerTaskSupport_$eq(TaskSupport cts) {
/*  39 */     Combiner.class.combinerTaskSupport_$eq(this, cts);
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> resultWithTaskSupport() {
/*  39 */     return (ParTrieMap<K, V>)Combiner.class.resultWithTaskSupport(this);
/*     */   }
/*     */   
/*     */   public void sizeHint(int size) {
/*  39 */     Builder.class.sizeHint((Builder)this, size);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll) {
/*  39 */     Builder.class.sizeHint((Builder)this, coll);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll, int delta) {
/*  39 */     Builder.class.sizeHint((Builder)this, coll, delta);
/*     */   }
/*     */   
/*     */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/*  39 */     Builder.class.sizeHintBounded((Builder)this, size, boundingColl);
/*     */   }
/*     */   
/*     */   public <NewTo> Builder<Tuple2<K, V>, NewTo> mapResult(Function1 f) {
/*  39 */     return Builder.class.mapResult((Builder)this, f);
/*     */   }
/*     */   
/*     */   public <U> ParMap<K, U> updated(Object key, Object value) {
/*  39 */     return ParMap$class.updated(this, key, value);
/*     */   }
/*     */   
/*     */   public ParMap<K, V> withDefault(Function1 d) {
/*  39 */     return ParMap$class.withDefault(this, d);
/*     */   }
/*     */   
/*     */   public ParMap<K, V> withDefaultValue(Object d) {
/*  39 */     return ParMap$class.withDefaultValue(this, d);
/*     */   }
/*     */   
/*     */   public <U> ParMap<K, U> $plus(Tuple2 kv) {
/*  39 */     return ParMapLike$class.$plus(this, kv);
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> $minus(Object key) {
/*  39 */     return (ParTrieMap<K, V>)ParMapLike$class.$minus(this, key);
/*     */   }
/*     */   
/*     */   public Object scala$collection$mutable$Cloneable$$super$clone() {
/*  39 */     return super.clone();
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> clone() {
/*  39 */     return (ParTrieMap<K, V>)Cloneable.class.clone(this);
/*     */   }
/*     */   
/*     */   public Shrinkable<K> $minus$eq(Object elem1, Object elem2, Seq elems) {
/*  39 */     return Shrinkable.class.$minus$eq(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Shrinkable<K> $minus$minus$eq(TraversableOnce xs) {
/*  39 */     return Shrinkable.class.$minus$minus$eq(this, xs);
/*     */   }
/*     */   
/*     */   public Growable<Tuple2<K, V>> $plus$eq(Object elem1, Object elem2, Seq elems) {
/*  39 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Growable<Tuple2<K, V>> $plus$plus$eq(TraversableOnce xs) {
/*  39 */     return Growable.class.$plus$plus$eq(this, xs);
/*     */   }
/*     */   
/*     */   public GenericCompanion<ParIterable> companion() {
/*  39 */     return ParIterable$class.companion(this);
/*     */   }
/*     */   
/*     */   public ParIterable<Tuple2<K, V>> toIterable() {
/*  39 */     return ParIterable$class.toIterable(this);
/*     */   }
/*     */   
/*     */   public ParSeq<Tuple2<K, V>> toSeq() {
/*  39 */     return ParIterable$class.toSeq(this);
/*     */   }
/*     */   
/*     */   public V default(Object key) {
/*  39 */     return (V)ParMapLike.class.default(this, key);
/*     */   }
/*     */   
/*     */   public V apply(Object key) {
/*  39 */     return (V)ParMapLike.class.apply(this, key);
/*     */   }
/*     */   
/*     */   public <U> U getOrElse(Object key, Function0 default) {
/*  39 */     return (U)ParMapLike.class.getOrElse(this, key, default);
/*     */   }
/*     */   
/*     */   public boolean contains(Object key) {
/*  39 */     return ParMapLike.class.contains(this, key);
/*     */   }
/*     */   
/*     */   public boolean isDefinedAt(Object key) {
/*  39 */     return ParMapLike.class.isDefinedAt(this, key);
/*     */   }
/*     */   
/*     */   public IterableSplitter<K> keysIterator() {
/*  39 */     return ParMapLike.class.keysIterator(this);
/*     */   }
/*     */   
/*     */   public IterableSplitter<V> valuesIterator() {
/*  39 */     return ParMapLike.class.valuesIterator(this);
/*     */   }
/*     */   
/*     */   public ParSet<K> keySet() {
/*  39 */     return ParMapLike.class.keySet(this);
/*     */   }
/*     */   
/*     */   public ParIterable<K> keys() {
/*  39 */     return ParMapLike.class.keys(this);
/*     */   }
/*     */   
/*     */   public ParIterable<V> values() {
/*  39 */     return ParMapLike.class.values(this);
/*     */   }
/*     */   
/*     */   public ParMap<K, V> filterKeys(Function1 p) {
/*  39 */     return ParMapLike.class.filterKeys(this, p);
/*     */   }
/*     */   
/*     */   public <S> ParMap<K, S> mapValues(Function1 f) {
/*  39 */     return ParMapLike.class.mapValues(this, f);
/*     */   }
/*     */   
/*     */   public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
/*  39 */     return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
/*  39 */     this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
/*     */   }
/*     */   
/*     */   private ParIterableLike$ScanNode$ ScanNode$lzycompute() {
/*  39 */     synchronized (this) {
/*  39 */       if (this.ScanNode$module == null)
/*  39 */         this.ScanNode$module = new ParIterableLike$ScanNode$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/mutable/ParTrieMap}} */
/*  39 */       return this.ScanNode$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParIterableLike$ScanNode$ ScanNode() {
/*  39 */     return (this.ScanNode$module == null) ? ScanNode$lzycompute() : this.ScanNode$module;
/*     */   }
/*     */   
/*     */   private ParIterableLike$ScanLeaf$ ScanLeaf$lzycompute() {
/*  39 */     synchronized (this) {
/*  39 */       if (this.ScanLeaf$module == null)
/*  39 */         this.ScanLeaf$module = new ParIterableLike$ScanLeaf$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/mutable/ParTrieMap}} */
/*  39 */       return this.ScanLeaf$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParIterableLike$ScanLeaf$ ScanLeaf() {
/*  39 */     return (this.ScanLeaf$module == null) ? ScanLeaf$lzycompute() : this.ScanLeaf$module;
/*     */   }
/*     */   
/*     */   public void initTaskSupport() {
/*  39 */     ParIterableLike.class.initTaskSupport(this);
/*     */   }
/*     */   
/*     */   public TaskSupport tasksupport() {
/*  39 */     return ParIterableLike.class.tasksupport(this);
/*     */   }
/*     */   
/*     */   public void tasksupport_$eq(TaskSupport ts) {
/*  39 */     ParIterableLike.class.tasksupport_$eq(this, ts);
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> repr() {
/*  39 */     return (ParTrieMap<K, V>)ParIterableLike.class.repr(this);
/*     */   }
/*     */   
/*     */   public final boolean isTraversableAgain() {
/*  39 */     return ParIterableLike.class.isTraversableAgain(this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/*  39 */     return ParIterableLike.class.hasDefiniteSize(this);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  39 */     return ParIterableLike.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/*  39 */     return ParIterableLike.class.nonEmpty(this);
/*     */   }
/*     */   
/*     */   public Tuple2<K, V> head() {
/*  39 */     return (Tuple2<K, V>)ParIterableLike.class.head(this);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<K, V>> headOption() {
/*  39 */     return ParIterableLike.class.headOption(this);
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> tail() {
/*  39 */     return (ParTrieMap<K, V>)ParIterableLike.class.tail(this);
/*     */   }
/*     */   
/*     */   public Tuple2<K, V> last() {
/*  39 */     return (Tuple2<K, V>)ParIterableLike.class.last(this);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<K, V>> lastOption() {
/*  39 */     return ParIterableLike.class.lastOption(this);
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> init() {
/*  39 */     return (ParTrieMap<K, V>)ParIterableLike.class.init(this);
/*     */   }
/*     */   
/*     */   public Splitter<Tuple2<K, V>> iterator() {
/*  39 */     return ParIterableLike.class.iterator(this);
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> par() {
/*  39 */     return (ParTrieMap<K, V>)ParIterableLike.class.par(this);
/*     */   }
/*     */   
/*     */   public boolean isStrictSplitterCollection() {
/*  39 */     return ParIterableLike.class.isStrictSplitterCollection(this);
/*     */   }
/*     */   
/*     */   public <S, That> Combiner<S, That> reuse(Option oldc, Combiner newc) {
/*  39 */     return ParIterableLike.class.reuse(this, oldc, newc);
/*     */   }
/*     */   
/*     */   public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask tsk) {
/*  39 */     return ParIterableLike.class.task2ops(this, tsk);
/*     */   }
/*     */   
/*     */   public <R> Object wrap(Function0 body) {
/*  39 */     return ParIterableLike.class.wrap(this, body);
/*     */   }
/*     */   
/*     */   public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(DelegatedSignalling it) {
/*  39 */     return ParIterableLike.class.delegatedSignalling2ops(this, it);
/*     */   }
/*     */   
/*     */   public <Elem, To> Object builder2ops(Builder cb) {
/*  39 */     return ParIterableLike.class.builder2ops(this, cb);
/*     */   }
/*     */   
/*     */   public <S, That> Object bf2seq(CanBuildFrom bf) {
/*  39 */     return ParIterableLike.class.bf2seq(this, bf);
/*     */   }
/*     */   
/*     */   public <S, That extends Parallel> ParTrieMap<K, V> sequentially(Function1 b) {
/*  39 */     return (ParTrieMap<K, V>)ParIterableLike.class.sequentially(this, b);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/*  39 */     return ParIterableLike.class.mkString(this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/*  39 */     return ParIterableLike.class.mkString(this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/*  39 */     return ParIterableLike.class.mkString(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  39 */     return ParIterableLike.class.toString(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object other) {
/*  39 */     return ParIterableLike.class.canEqual(this, other);
/*     */   }
/*     */   
/*     */   public <U> U reduce(Function2 op) {
/*  39 */     return (U)ParIterableLike.class.reduce(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceOption(Function2 op) {
/*  39 */     return ParIterableLike.class.reduceOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> U fold(Object z, Function2 op) {
/*  39 */     return (U)ParIterableLike.class.fold(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S aggregate(Object z, Function2 seqop, Function2 combop) {
/*  39 */     return (S)ParIterableLike.class.aggregate(this, z, seqop, combop);
/*     */   }
/*     */   
/*     */   public <S> S foldLeft(Object z, Function2 op) {
/*  39 */     return (S)ParIterableLike.class.foldLeft(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S foldRight(Object z, Function2 op) {
/*  39 */     return (S)ParIterableLike.class.foldRight(this, z, op);
/*     */   }
/*     */   
/*     */   public <U> U reduceLeft(Function2 op) {
/*  39 */     return (U)ParIterableLike.class.reduceLeft(this, op);
/*     */   }
/*     */   
/*     */   public <U> U reduceRight(Function2 op) {
/*  39 */     return (U)ParIterableLike.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceLeftOption(Function2 op) {
/*  39 */     return ParIterableLike.class.reduceLeftOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceRightOption(Function2 op) {
/*  39 */     return ParIterableLike.class.reduceRightOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/*  39 */     ParIterableLike.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/*  39 */     return ParIterableLike.class.count(this, p);
/*     */   }
/*     */   
/*     */   public <U> U sum(Numeric num) {
/*  39 */     return (U)ParIterableLike.class.sum(this, num);
/*     */   }
/*     */   
/*     */   public <U> U product(Numeric num) {
/*  39 */     return (U)ParIterableLike.class.product(this, num);
/*     */   }
/*     */   
/*     */   public <U> Tuple2<K, V> min(Ordering ord) {
/*  39 */     return (Tuple2<K, V>)ParIterableLike.class.min(this, ord);
/*     */   }
/*     */   
/*     */   public <U> Tuple2<K, V> max(Ordering ord) {
/*  39 */     return (Tuple2<K, V>)ParIterableLike.class.max(this, ord);
/*     */   }
/*     */   
/*     */   public <S> Tuple2<K, V> maxBy(Function1 f, Ordering cmp) {
/*  39 */     return (Tuple2<K, V>)ParIterableLike.class.maxBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <S> Tuple2<K, V> minBy(Function1 f, Ordering cmp) {
/*  39 */     return (Tuple2<K, V>)ParIterableLike.class.minBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <S, That> That map(Function1 f, CanBuildFrom bf) {
/*  39 */     return (That)ParIterableLike.class.map(this, f, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  39 */     return (That)ParIterableLike.class.collect(this, pf, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  39 */     return (That)ParIterableLike.class.flatMap(this, f, bf);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 pred) {
/*  39 */     return ParIterableLike.class.forall(this, pred);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 pred) {
/*  39 */     return ParIterableLike.class.exists(this, pred);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<K, V>> find(Function1 pred) {
/*  39 */     return ParIterableLike.class.find(this, pred);
/*     */   }
/*     */   
/*     */   public Object combinerFactory() {
/*  39 */     return ParIterableLike.class.combinerFactory(this);
/*     */   }
/*     */   
/*     */   public <S, That> Object combinerFactory(Function0 cbf) {
/*  39 */     return ParIterableLike.class.combinerFactory(this, cbf);
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> filter(Function1 pred) {
/*  39 */     return (ParTrieMap<K, V>)ParIterableLike.class.filter(this, pred);
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> filterNot(Function1 pred) {
/*  39 */     return (ParTrieMap<K, V>)ParIterableLike.class.filterNot(this, pred);
/*     */   }
/*     */   
/*     */   public <U, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/*  39 */     return (That)ParIterableLike.class.$plus$plus(this, that, bf);
/*     */   }
/*     */   
/*     */   public Tuple2<ParTrieMap<K, V>, ParTrieMap<K, V>> partition(Function1 pred) {
/*  39 */     return ParIterableLike.class.partition(this, pred);
/*     */   }
/*     */   
/*     */   public <K> ParMap<K, ParTrieMap<K, V>> groupBy(Function1 f) {
/*  39 */     return ParIterableLike.class.groupBy(this, f);
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> take(int n) {
/*  39 */     return (ParTrieMap<K, V>)ParIterableLike.class.take(this, n);
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> drop(int n) {
/*  39 */     return (ParTrieMap<K, V>)ParIterableLike.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> slice(int unc_from, int unc_until) {
/*  39 */     return (ParTrieMap<K, V>)ParIterableLike.class.slice(this, unc_from, unc_until);
/*     */   }
/*     */   
/*     */   public Tuple2<ParTrieMap<K, V>, ParTrieMap<K, V>> splitAt(int n) {
/*  39 */     return ParIterableLike.class.splitAt(this, n);
/*     */   }
/*     */   
/*     */   public <U, That> That scan(Object z, Function2 op, CanBuildFrom bf) {
/*  39 */     return (That)ParIterableLike.class.scan(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  39 */     return (That)ParIterableLike.class.scanLeft(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  39 */     return (That)ParIterableLike.class.scanRight(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> takeWhile(Function1 pred) {
/*  39 */     return (ParTrieMap<K, V>)ParIterableLike.class.takeWhile(this, pred);
/*     */   }
/*     */   
/*     */   public Tuple2<ParTrieMap<K, V>, ParTrieMap<K, V>> span(Function1 pred) {
/*  39 */     return ParIterableLike.class.span(this, pred);
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> dropWhile(Function1 pred) {
/*  39 */     return (ParTrieMap<K, V>)ParIterableLike.class.dropWhile(this, pred);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs) {
/*  39 */     ParIterableLike.class.copyToArray(this, xs);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs, int start) {
/*  39 */     ParIterableLike.class.copyToArray(this, xs, start);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs, int start, int len) {
/*  39 */     ParIterableLike.class.copyToArray(this, xs, start, len);
/*     */   }
/*     */   
/*     */   public <U> boolean sameElements(GenIterable that) {
/*  39 */     return ParIterableLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public <U, S, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  39 */     return (That)ParIterableLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That zipWithIndex(CanBuildFrom bf) {
/*  39 */     return (That)ParIterableLike.class.zipWithIndex(this, bf);
/*     */   }
/*     */   
/*     */   public <S, U, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  39 */     return (That)ParIterableLike.class.zipAll(this, that, thisElem, thatElem, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That toParCollection(Function0 cbf) {
/*  39 */     return (That)ParIterableLike.class.toParCollection(this, cbf);
/*     */   }
/*     */   
/*     */   public <K, V, That> That toParMap(Function0 cbf, Predef$.less.colon.less ev) {
/*  39 */     return (That)ParIterableLike.class.toParMap(this, cbf, ev);
/*     */   }
/*     */   
/*     */   public Object view() {
/*  39 */     return ParIterableLike.class.view(this);
/*     */   }
/*     */   
/*     */   public <U> Object toArray(ClassTag evidence$1) {
/*  39 */     return ParIterableLike.class.toArray(this, evidence$1);
/*     */   }
/*     */   
/*     */   public List<Tuple2<K, V>> toList() {
/*  39 */     return ParIterableLike.class.toList(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<Tuple2<K, V>> toIndexedSeq() {
/*  39 */     return ParIterableLike.class.toIndexedSeq(this);
/*     */   }
/*     */   
/*     */   public Stream<Tuple2<K, V>> toStream() {
/*  39 */     return ParIterableLike.class.toStream(this);
/*     */   }
/*     */   
/*     */   public Iterator<Tuple2<K, V>> toIterator() {
/*  39 */     return ParIterableLike.class.toIterator(this);
/*     */   }
/*     */   
/*     */   public <U> Buffer<U> toBuffer() {
/*  39 */     return ParIterableLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public GenTraversable<Tuple2<K, V>> toTraversable() {
/*  39 */     return ParIterableLike.class.toTraversable(this);
/*     */   }
/*     */   
/*     */   public <U> ParSet<U> toSet() {
/*  39 */     return ParIterableLike.class.toSet(this);
/*     */   }
/*     */   
/*     */   public <K, V> ParMap<K, V> toMap(Predef$.less.colon.less ev) {
/*  39 */     return ParIterableLike.class.toMap(this, ev);
/*     */   }
/*     */   
/*     */   public Vector<Tuple2<K, V>> toVector() {
/*  39 */     return ParIterableLike.class.toVector(this);
/*     */   }
/*     */   
/*     */   public <Col> Col to(CanBuildFrom cbf) {
/*  39 */     return (Col)ParIterableLike.class.to(this, cbf);
/*     */   }
/*     */   
/*     */   public int scanBlockSize() {
/*  39 */     return ParIterableLike.class.scanBlockSize(this);
/*     */   }
/*     */   
/*     */   public <S> S $div$colon(Object z, Function2 op) {
/*  39 */     return (S)ParIterableLike.class.$div$colon(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S $colon$bslash(Object z, Function2 op) {
/*  39 */     return (S)ParIterableLike.class.$colon$bslash(this, z, op);
/*     */   }
/*     */   
/*     */   public String debugInformation() {
/*  39 */     return ParIterableLike.class.debugInformation(this);
/*     */   }
/*     */   
/*     */   public Seq<String> brokenInvariants() {
/*  39 */     return ParIterableLike.class.brokenInvariants(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<String> debugBuffer() {
/*  39 */     return ParIterableLike.class.debugBuffer(this);
/*     */   }
/*     */   
/*     */   public void debugclear() {
/*  39 */     ParIterableLike.class.debugclear(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<String> debuglog(String s) {
/*  39 */     return ParIterableLike.class.debuglog(this, s);
/*     */   }
/*     */   
/*     */   public void printDebugBuffer() {
/*  39 */     ParIterableLike.class.printDebugBuffer(this);
/*     */   }
/*     */   
/*     */   public Combiner<Tuple2<K, V>, ParTrieMap<K, V>> parCombiner() {
/*  39 */     return CustomParallelizable.class.parCombiner((CustomParallelizable)this);
/*     */   }
/*     */   
/*     */   public <P, Q> Combiner<Tuple2<P, Q>, ParTrieMap<P, Q>> genericMapCombiner() {
/*  39 */     return GenericParMapTemplate.class.genericMapCombiner(this);
/*     */   }
/*     */   
/*     */   public Builder<Tuple2<K, V>, ParIterable<Tuple2<K, V>>> newBuilder() {
/*  39 */     return GenericParTemplate.class.newBuilder(this);
/*     */   }
/*     */   
/*     */   public <B> Combiner<B, ParIterable<B>> genericBuilder() {
/*  39 */     return GenericParTemplate.class.genericBuilder(this);
/*     */   }
/*     */   
/*     */   public <B> Combiner<B, ParIterable<B>> genericCombiner() {
/*  39 */     return GenericParTemplate.class.genericCombiner(this);
/*     */   }
/*     */   
/*     */   public <A1, A2> Tuple2<ParIterable<A1>, ParIterable<A2>> unzip(Function1 asPair) {
/*  39 */     return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*     */   }
/*     */   
/*     */   public <A1, A2, A3> Tuple3<ParIterable<A1>, ParIterable<A2>, ParIterable<A3>> unzip3(Function1 asTriple) {
/*  39 */     return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*     */   }
/*     */   
/*     */   public <B> ParIterable<B> flatten(Function1 asTraversable) {
/*  39 */     return (ParIterable<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public <B> ParIterable<ParIterable<B>> transpose(Function1 asTraversable) {
/*  39 */     return (ParIterable<ParIterable<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  39 */     return GenMapLike.class.hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*  39 */     return GenMapLike.class.equals(this, that);
/*     */   }
/*     */   
/*     */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  39 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> scala$collection$parallel$mutable$ParTrieMap$$ctrie() {
/*  39 */     return this.scala$collection$parallel$mutable$ParTrieMap$$ctrie;
/*     */   }
/*     */   
/*     */   public ParTrieMap(TrieMap<K, V> ctrie) {
/*  39 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  39 */     Parallelizable.class.$init$((Parallelizable)this);
/*  39 */     GenMapLike.class.$init$(this);
/*  39 */     GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/*  39 */     GenTraversable.class.$init$((GenTraversable)this);
/*  39 */     GenIterable.class.$init$(this);
/*  39 */     GenericParTemplate.class.$init$(this);
/*  39 */     GenericParMapTemplate.class.$init$(this);
/*  39 */     CustomParallelizable.class.$init$((CustomParallelizable)this);
/*  39 */     ParIterableLike.class.$init$(this);
/*  39 */     ParIterable.class.$init$(this);
/*  39 */     ParMapLike.class.$init$(this);
/*  39 */     ParMap.class.$init$(this);
/*  39 */     ParIterable$class.$init$(this);
/*  39 */     Growable.class.$init$(this);
/*  39 */     Shrinkable.class.$init$(this);
/*  39 */     Cloneable.class.$init$(this);
/*  39 */     ParMapLike$class.$init$(this);
/*  39 */     ParMap$class.$init$(this);
/*  39 */     Builder.class.$init$((Builder)this);
/*  39 */     Combiner.class.$init$(this);
/*  39 */     ParTrieMapCombiner$class.$init$(this);
/*     */   }
/*     */   
/*     */   public ParTrieMap() {
/*  46 */     this(new TrieMap());
/*     */   }
/*     */   
/*     */   public GenericParMapCompanion<ParTrieMap> mapCompanion() {
/*  48 */     return (GenericParMapCompanion<ParTrieMap>)ParTrieMap$.MODULE$;
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> empty() {
/*  50 */     return ParTrieMap$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public Combiner<Tuple2<K, V>, ParTrieMap<K, V>> newCombiner() {
/*  52 */     return ParTrieMap$.MODULE$.newCombiner();
/*     */   }
/*     */   
/*     */   public TrieMap<K, V> seq() {
/*  54 */     return scala$collection$parallel$mutable$ParTrieMap$$ctrie();
/*     */   }
/*     */   
/*     */   public ParTrieMapSplitter<K, V> splitter() {
/*  56 */     return new ParTrieMapSplitter<K, V>(0, (TrieMap<K, V>)scala$collection$parallel$mutable$ParTrieMap$$ctrie().readOnlySnapshot(), true);
/*     */   }
/*     */   
/*     */   public void clear() {
/*  58 */     scala$collection$parallel$mutable$ParTrieMap$$ctrie().clear();
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> result() {
/*  60 */     return this;
/*     */   }
/*     */   
/*     */   public Option<V> get(Object key) {
/*  62 */     return scala$collection$parallel$mutable$ParTrieMap$$ctrie().get(key);
/*     */   }
/*     */   
/*     */   public Option<V> put(Object key, Object value) {
/*  64 */     return scala$collection$parallel$mutable$ParTrieMap$$ctrie().put(key, value);
/*     */   }
/*     */   
/*     */   public void update(Object key, Object value) {
/*  66 */     scala$collection$parallel$mutable$ParTrieMap$$ctrie().update(key, value);
/*     */   }
/*     */   
/*     */   public Option<V> remove(Object key) {
/*  68 */     return scala$collection$parallel$mutable$ParTrieMap$$ctrie().remove(key);
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> $plus$eq(Tuple2 kv) {
/*  71 */     scala$collection$parallel$mutable$ParTrieMap$$ctrie().$plus$eq(kv);
/*  72 */     return this;
/*     */   }
/*     */   
/*     */   public ParTrieMap<K, V> $minus$eq(Object key) {
/*  76 */     scala$collection$parallel$mutable$ParTrieMap$$ctrie().$minus$eq(key);
/*  77 */     return this;
/*     */   }
/*     */   
/*     */   public int size() {
/*  81 */     INode in = scala$collection$parallel$mutable$ParTrieMap$$ctrie().readRoot(scala$collection$parallel$mutable$ParTrieMap$$ctrie().readRoot$default$1());
/*  82 */     MainNode r = in.gcasRead(scala$collection$parallel$mutable$ParTrieMap$$ctrie());
/*  83 */     if (r instanceof TNode) {
/*  83 */       TNode tNode = (TNode)r;
/*  83 */       int i = tNode.cachedSize(scala$collection$parallel$mutable$ParTrieMap$$ctrie());
/*  85 */     } else if (r instanceof LNode) {
/*  85 */       LNode lNode = (LNode)r;
/*  85 */       int i = lNode.cachedSize(scala$collection$parallel$mutable$ParTrieMap$$ctrie());
/*     */     } else {
/*  86 */       if (r instanceof CNode) {
/*  86 */         CNode cNode = (CNode)r;
/*  87 */         tasksupport().executeAndWaitResult(new Size(this, 0, (cNode.array()).length, cNode.array()));
/*  88 */         return cNode.cachedSize(scala$collection$parallel$mutable$ParTrieMap$$ctrie());
/*     */       } 
/*     */       throw new MatchError(r);
/*     */     } 
/*     */     return SYNTHETIC_LOCAL_VARIABLE_5;
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/*  92 */     return "ParTrieMap";
/*     */   }
/*     */   
/*     */   public static <K, V> CanCombineFrom<ParTrieMap<?, ?>, Tuple2<K, V>, ParTrieMap<K, V>> canBuildFrom() {
/*     */     return ParTrieMap$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public class Size implements Task<Object, Size> {
/*     */     private final int offset;
/*     */     
/*     */     private final int howmany;
/*     */     
/*     */     private final BasicNode[] array;
/*     */     
/*     */     private int result;
/*     */     
/*     */     private volatile Throwable throwable;
/*     */     
/*     */     public Throwable throwable() {
/*  97 */       return this.throwable;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void throwable_$eq(Throwable x$1) {
/*  97 */       this.throwable = x$1;
/*     */     }
/*     */     
/*     */     public Size repr() {
/*  97 */       return (Size)Task.class.repr(this);
/*     */     }
/*     */     
/*     */     public void forwardThrowable() {
/*  97 */       Task.class.forwardThrowable(this);
/*     */     }
/*     */     
/*     */     public void tryLeaf(Option lastres) {
/*  97 */       Task.class.tryLeaf(this, lastres);
/*     */     }
/*     */     
/*     */     public void tryMerge(Object t) {
/*  97 */       Task.class.tryMerge(this, t);
/*     */     }
/*     */     
/*     */     public void mergeThrowables(Task that) {
/*  97 */       Task.class.mergeThrowables(this, that);
/*     */     }
/*     */     
/*     */     public void signalAbort() {
/*  97 */       Task.class.signalAbort(this);
/*     */     }
/*     */     
/*     */     public Size(ParTrieMap $outer, int offset, int howmany, BasicNode[] array) {
/*  97 */       Task.class.$init$(this);
/*  98 */       this.result = -1;
/*     */     }
/*     */     
/*     */     public int result() {
/*  98 */       return this.result;
/*     */     }
/*     */     
/*     */     public void result_$eq(int x$1) {
/*  98 */       this.result = x$1;
/*     */     }
/*     */     
/*     */     public void leaf(Option prev) {
/* 100 */       int sz = 0;
/* 101 */       int i = this.offset;
/* 102 */       int until = this.offset + this.howmany;
/* 103 */       while (i < until) {
/* 104 */         BasicNode basicNode = this.array[i];
/* 105 */         if (basicNode instanceof scala.collection.concurrent.SNode) {
/* 105 */           sz++;
/* 106 */         } else if (basicNode instanceof INode) {
/* 106 */           INode iNode = (INode)basicNode;
/* 106 */           sz += iNode.cachedSize(scala$collection$parallel$mutable$ParTrieMap$Size$$$outer().scala$collection$parallel$mutable$ParTrieMap$$ctrie());
/*     */         } else {
/*     */           throw new MatchError(basicNode);
/*     */         } 
/* 108 */         i++;
/*     */       } 
/* 110 */       result_$eq(sz);
/*     */     }
/*     */     
/*     */     public Seq<Size> split() {
/* 113 */       int fp = this.howmany / 2;
/* 114 */       (new Size[2])[0] = new Size(scala$collection$parallel$mutable$ParTrieMap$Size$$$outer(), this.offset, fp, this.array);
/* 114 */       (new Size[2])[1] = new Size(scala$collection$parallel$mutable$ParTrieMap$Size$$$outer(), this.offset + fp, this.howmany - fp, this.array);
/* 114 */       return (Seq<Size>)Seq$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Size[2]));
/*     */     }
/*     */     
/*     */     public boolean shouldSplitFurther() {
/* 116 */       return (this.howmany > 1);
/*     */     }
/*     */     
/*     */     public void merge(Size that) {
/* 117 */       result_$eq(result() + that.result());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParTrieMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */