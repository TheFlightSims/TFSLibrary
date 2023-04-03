/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
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
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
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
/*     */ import scala.collection.immutable.IndexedSeq$;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.List$;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.Cloneable;
/*     */ import scala.collection.mutable.DefaultEntry;
/*     */ import scala.collection.mutable.HashEntry;
/*     */ import scala.collection.mutable.HashMap;
/*     */ import scala.collection.mutable.HashTable;
/*     */ import scala.collection.mutable.Iterable;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.StringBuilder;
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
/*     */ import scala.collection.parallel.TaskSupport;
/*     */ import scala.collection.parallel.immutable.ParMap;
/*     */ import scala.collection.parallel.immutable.ParSet;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichInt$;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tee\001B\001\003\001-\021!\002U1s\021\006\034\b.T1q\025\t\031A!A\004nkR\f'\r\\3\013\005\0251\021\001\0039be\006dG.\0327\013\005\035A\021AC2pY2,7\r^5p]*\t\021\"A\003tG\006d\027m\001\001\026\00719\022eE\004\001\033E\031#fM\035\021\0059yQ\"\001\005\n\005AA!AB!osJ+g\r\005\003\023'U\001S\"\001\002\n\005Q\021!A\002)be6\013\007\017\005\002\027/1\001A!\002\r\001\005\004I\"!A&\022\005ii\002C\001\b\034\023\ta\002BA\004O_RD\027N\\4\021\0059q\022BA\020\t\005\r\te.\037\t\003-\005\"QA\t\001C\002e\021\021A\026\t\006I\035*\002%K\007\002K)\021aEB\001\bO\026tWM]5d\023\tASEA\013HK:,'/[2QCJl\025\r\035+f[Bd\027\r^3\021\005I\001\001C\002\n,+\001jc&\003\002-\005\tQ\001+\031:NCBd\025n[3\021\tI\001Q\003\t\t\005_E*\002%D\0011\025\t\031a!\003\0023a\t9\001*Y:i\033\006\004\b\003\002\n5+YJ!!\016\002\003\031A\013'\017S1tQR\013'\r\\3\021\t=:T\003I\005\003qA\022A\002R3gCVdG/\0228uef\004\"A\004\036\n\005mB!\001D*fe&\fG.\033>bE2,\007\002C\037\001\005\003\005\013\021\002 \002\021\r|g\016^3oiN\004Ba\020\"\026m9\021q\006Q\005\003\003B\n\021\002S1tQR\013'\r\\3\n\005\r#%\001C\"p]R,g\016^:\013\005\005\003\004B\002$\001\t\0031q)\001\004=S:LGO\020\013\003[!CQ!P#A\002y*AA\023\001\001m\t)QI\034;ss\")a\t\001C\001\031R\tQ\006C\003O\001\021\005s*\001\007nCB\034u.\0349b]&|g.F\001Q!\r!\023+K\005\003%\026\022acR3oKJL7\rU1s\033\006\0048i\\7qC:LwN\034\005\006)\002!\t%V\001\006K6\004H/_\013\002[!1q\013\001Q\005Ra\0131B\\3x\007>l'-\0338feV\t\021\f\005\003\0235V\001\023BA.\003\005I\001\026M\035%bg\"l\025\r]\"p[\nLg.\032:\t\013u\003A\021\t0\002\007M,\027/F\001/\021\025\001\007\001\"\001b\003!\031\b\017\\5ui\026\024X#\0012\021\005\r$W\"\001\001\007\t\025\004\001A\032\002\023!\006\024\b*Y:i\033\006\004\030\n^3sCR|'o\005\002eOB!1\r\0336c\023\tIGGA\007F]R\024\0300\023;fe\006$xN\035\t\005\035-,\002%\003\002m\021\t1A+\0369mKJB\001B\0343\003\002\003\006Ia\\\001\006gR\f'\017\036\t\003\035AL!!\035\005\003\007%sG\017\003\005tI\n\005\t\025!\003p\003!)h\016^5m\023\022D\b\002C;e\005\003\005\013\021B8\002\023Q|G/\0317TSj,\007\002C<e\005\003\005\013\021\002\034\002\003\025DQA\0223\005\002e$RA\031>|yvDQA\034=A\002=DQa\035=A\002=DQ!\036=A\002=DQa\036=A\002YBaa 3\005\002\005\005\021AC3oiJL('\033;f[R\031!.a\001\t\r\005\025a\0201\0017\003\025)g\016\036:z\021\035\tI\001\032C\001\003\027\t1B\\3x\023R,'/\031;peRI!-!\004\002\022\005U\021\021\004\005\b\003\037\t9\0011\001p\003\035IG\r\037$s_6Dq!a\005\002\b\001\007q.\001\005jIb,f\016^5m\021\035\t9\"a\002A\002=\fq\001^8uC2\034&\020C\004\002\034\005\035\001\031\001\034\002\005\025\034\bbBA\020\001\021\005\023\021E\001\005g&TX-F\001p\021\035\t)\003\001C!\003O\tQa\0317fCJ$\"!!\013\021\0079\tY#C\002\002.!\021A!\0268ji\"9\021\021\007\001\005\002\005M\022aA4fiR!\021QGA\036!\021q\021q\007\021\n\007\005e\002B\001\004PaRLwN\034\005\b\003{\ty\0031\001\026\003\rYW-\037\005\b\003\003\002A\021AA\"\003\r\001X\017\036\013\007\003k\t)%a\022\t\017\005u\022q\ba\001+!9\021\021JA \001\004\001\023!\002<bYV,\007bBA'\001\021\005\021qJ\001\007kB$\027\r^3\025\r\005%\022\021KA*\021\035\ti$a\023A\002UAq!!\023\002L\001\007\001\005C\004\002X\001!\t!!\027\002\rI,Wn\034<f)\021\t)$a\027\t\017\005u\022Q\013a\001+!9\021q\f\001\005\002\005\005\024\001\003\023qYV\034H%Z9\025\007\r\f\031\007C\004\002f\005u\003\031\0016\002\005-4\bbBA5\001\021\005\0211N\001\nI5Lg.^:%KF$2aYA7\021\035\ti$a\032A\002UAq!!\035\001\t\003\n\031(\001\007tiJLgn\032)sK\032L\0070\006\002\002vA!\021qOAA\033\t\tIH\003\003\002|\005u\024\001\0027b]\036T!!a \002\t)\fg/Y\005\005\003\007\013IH\001\004TiJLgn\032\005\b\003\017\003A\021CAE\0039\031'/Z1uK:+w/\0228uef,B!a#\002\026R1\021QRAH\003#\003\"aY%\t\017\005u\022Q\021a\001+!A\021\021JAC\001\004\t\031\nE\002\027\003+#q!a&\002\006\n\007\021D\001\002Wc!9\0211\024\001\005\n\005u\025aC<sSR,wJ\0316fGR$B!!\013\002 \"A\021\021UAM\001\004\t\031+A\002pkR\004B!!*\002,6\021\021q\025\006\005\003S\013i(\001\002j_&!\021QVAT\005Iy%M[3di>+H\017];u'R\024X-Y7\t\017\005E\006\001\"\003\0024\006Q!/Z1e\037\nTWm\031;\025\t\005%\022Q\027\005\t\003o\013y\0131\001\002:\006\021\021N\034\t\005\003K\013Y,\003\003\002>\006\035&!E(cU\026\034G/\0238qkR\034FO]3b[\"A\021\021\031\001\005B\021\t\031-\001\tce>\\WM\\%om\006\024\030.\0318ugV\021\021Q\031\t\007\003\017\fI-!4\016\003\031I1!a3\007\005\r\031V-\035\t\005\003\037\f)ND\002\017\003#L1!a5\t\003\031\001&/\0323fM&!\0211QAl\025\r\t\031\016\003\005\b\0037\004A\021BAo\003-\031\007.Z2l\005V\0347.\032;\025\t\005}\0271\036\t\007\003C\f9/!\036\016\005\005\r(bAAs\r\005I\021.\\7vi\006\024G.Z\005\005\003S\f\031O\001\003MSN$\bbBAw\0033\004\ra\\\001\002S\"9\021\021\037\001\005\n\005M\030AC2iK\016\\WI\034;ssR!\021Q\037B\006!\031\t9Pa\002\002N:!\021\021 B\002\035\021\tYP!\001\016\005\005u(bAA\000\025\0051AH]8pizJ\021!C\005\004\005\013A\021a\0029bG.\fw-Z\005\005\003S\024IAC\002\003\006!Aq!!<\002p\002\007q\016K\003\001\005\037\021)\002E\002\017\005#I1Aa\005\t\005A\031VM]5bYZ+'o]5p]VKEIH\001\002\017\035\021IB\001E\001\0057\t!\002U1s\021\006\034\b.T1q!\r\021\"Q\004\004\007\003\tA\tAa\b\024\013\tu!\021E\035\021\t\021\022\031#K\005\004\005K)#!\004)be6\013\007OR1di>\024\030\020C\004G\005;!\tA!\013\025\005\tm\001B\003B\027\005;\001\r\021\"\001\002\"\005)\021\016^3sg\"Q!\021\007B\017\001\004%\tAa\r\002\023%$XM]:`I\025\fH\003BA\025\005kA\021Ba\016\0030\005\005\t\031A8\002\007a$\023\007\003\005\003<\tu\001\025)\003p\003\031IG/\032:tA!9AK!\b\005\002\t}RC\002B!\005\017\022Y%\006\002\003DA1!\003\001B#\005\023\0022A\006B$\t\031A\"Q\bb\0013A\031aCa\023\005\r\t\022iD1\001\032\021\0359&Q\004C\001\005\037*bA!\025\003`\t\rTC\001B*!!\021)Fa\026\003\\\t\025T\"\001\003\n\007\teCA\001\005D_6\024\027N\\3s!\031q1N!\030\003bA\031aCa\030\005\ra\021iE1\001\032!\r1\"1\r\003\007E\t5#\031A\r\021\rI\001!Q\fB1\021!\021IG!\b\005\004\t-\024\001D2b]\n+\030\016\0343Ge>lWC\002B7\005\013\023I)\006\002\003pAIAE!\035\003v\t\005%1R\005\004\005g*#AD\"b]\016{WNY5oK\032\023x.\034\t\005\005o\022I(\004\002\003\036%!!1\020B?\005\021\031u\016\0347\n\007\t}TEA\007HK:l\025\r\035$bGR|'/\037\t\007\035-\024\031Ia\"\021\007Y\021)\t\002\004\031\005O\022\r!\007\t\004-\t%EA\002\022\003h\t\007\021\004\005\004\023\001\t\r%q\021\005\013\005\037\023i\"!A\005\n\tE\025a\003:fC\022\024Vm]8mm\026$\"Aa%\021\t\005]$QS\005\005\005/\013IH\001\004PE*,7\r\036")
/*     */ public class ParHashMap<K, V> implements ParMap<K, V>, GenericParMapTemplate<K, V, ParHashMap>, ParMapLike<K, V, ParHashMap<K, V>, HashMap<K, V>>, ParHashTable<K, DefaultEntry<K, V>>, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private transient int _loadFactor;
/*     */   
/*     */   private transient HashEntry<Object, HashEntry>[] table;
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
/*  40 */     return ParHashTable$class.alwaysInitSizeMap(this);
/*     */   }
/*     */   
/*     */   public int _loadFactor() {
/*  40 */     return this._loadFactor;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void _loadFactor_$eq(int x$1) {
/*  40 */     this._loadFactor = x$1;
/*     */   }
/*     */   
/*     */   public HashEntry<K, DefaultEntry<K, V>>[] table() {
/*  40 */     return (HashEntry[])this.table;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void table_$eq(HashEntry[] x$1) {
/*  40 */     this.table = (HashEntry<Object, HashEntry>[])x$1;
/*     */   }
/*     */   
/*     */   public int tableSize() {
/*  40 */     return this.tableSize;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void tableSize_$eq(int x$1) {
/*  40 */     this.tableSize = x$1;
/*     */   }
/*     */   
/*     */   public int threshold() {
/*  40 */     return this.threshold;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void threshold_$eq(int x$1) {
/*  40 */     this.threshold = x$1;
/*     */   }
/*     */   
/*     */   public int[] sizemap() {
/*  40 */     return this.sizemap;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void sizemap_$eq(int[] x$1) {
/*  40 */     this.sizemap = x$1;
/*     */   }
/*     */   
/*     */   public int seedvalue() {
/*  40 */     return this.seedvalue;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void seedvalue_$eq(int x$1) {
/*  40 */     this.seedvalue = x$1;
/*     */   }
/*     */   
/*     */   public int tableSizeSeed() {
/*  40 */     return HashTable.class.tableSizeSeed(this);
/*     */   }
/*     */   
/*     */   public int initialSize() {
/*  40 */     return HashTable.class.initialSize(this);
/*     */   }
/*     */   
/*     */   public void init(ObjectInputStream in, Function0 readEntry) {
/*  40 */     HashTable.class.init(this, in, readEntry);
/*     */   }
/*     */   
/*     */   public void serializeTo(ObjectOutputStream out, Function1 writeEntry) {
/*  40 */     HashTable.class.serializeTo(this, out, writeEntry);
/*     */   }
/*     */   
/*     */   public DefaultEntry<K, V> findEntry(Object key) {
/*  40 */     return (DefaultEntry<K, V>)HashTable.class.findEntry(this, key);
/*     */   }
/*     */   
/*     */   public void addEntry(HashEntry e) {
/*  40 */     HashTable.class.addEntry(this, e);
/*     */   }
/*     */   
/*     */   public <B> DefaultEntry<K, V> findOrAddEntry(Object key, Object value) {
/*  40 */     return (DefaultEntry<K, V>)HashTable.class.findOrAddEntry(this, key, value);
/*     */   }
/*     */   
/*     */   public DefaultEntry<K, V> removeEntry(Object key) {
/*  40 */     return (DefaultEntry<K, V>)HashTable.class.removeEntry(this, key);
/*     */   }
/*     */   
/*     */   public Iterator<DefaultEntry<K, V>> entriesIterator() {
/*  40 */     return HashTable.class.entriesIterator(this);
/*     */   }
/*     */   
/*     */   public <U> void foreachEntry(Function1 f) {
/*  40 */     HashTable.class.foreachEntry(this, f);
/*     */   }
/*     */   
/*     */   public void clearTable() {
/*  40 */     HashTable.class.clearTable(this);
/*     */   }
/*     */   
/*     */   public void nnSizeMapAdd(int h) {
/*  40 */     HashTable.class.nnSizeMapAdd(this, h);
/*     */   }
/*     */   
/*     */   public void nnSizeMapRemove(int h) {
/*  40 */     HashTable.class.nnSizeMapRemove(this, h);
/*     */   }
/*     */   
/*     */   public void nnSizeMapReset(int tableLength) {
/*  40 */     HashTable.class.nnSizeMapReset(this, tableLength);
/*     */   }
/*     */   
/*     */   public final int totalSizeMapBuckets() {
/*  40 */     return HashTable.class.totalSizeMapBuckets(this);
/*     */   }
/*     */   
/*     */   public int calcSizeMapSize(int tableLength) {
/*  40 */     return HashTable.class.calcSizeMapSize(this, tableLength);
/*     */   }
/*     */   
/*     */   public void sizeMapInit(int tableLength) {
/*  40 */     HashTable.class.sizeMapInit(this, tableLength);
/*     */   }
/*     */   
/*     */   public void sizeMapInitAndRebuild() {
/*  40 */     HashTable.class.sizeMapInitAndRebuild(this);
/*     */   }
/*     */   
/*     */   public void printSizeMap() {
/*  40 */     HashTable.class.printSizeMap(this);
/*     */   }
/*     */   
/*     */   public void sizeMapDisable() {
/*  40 */     HashTable.class.sizeMapDisable(this);
/*     */   }
/*     */   
/*     */   public boolean isSizeMapDefined() {
/*  40 */     return HashTable.class.isSizeMapDefined(this);
/*     */   }
/*     */   
/*     */   public boolean elemEquals(Object key1, Object key2) {
/*  40 */     return HashTable.class.elemEquals(this, key1, key2);
/*     */   }
/*     */   
/*     */   public final int index(int hcode) {
/*  40 */     return HashTable.class.index(this, hcode);
/*     */   }
/*     */   
/*     */   public void initWithContents(HashTable.Contents c) {
/*  40 */     HashTable.class.initWithContents(this, c);
/*     */   }
/*     */   
/*     */   public HashTable.Contents<K, DefaultEntry<K, V>> hashTableContents() {
/*  40 */     return HashTable.class.hashTableContents(this);
/*     */   }
/*     */   
/*     */   public final int sizeMapBucketBitSize() {
/*  40 */     return HashTable.HashUtils.class.sizeMapBucketBitSize((HashTable.HashUtils)this);
/*     */   }
/*     */   
/*     */   public final int sizeMapBucketSize() {
/*  40 */     return HashTable.HashUtils.class.sizeMapBucketSize((HashTable.HashUtils)this);
/*     */   }
/*     */   
/*     */   public int elemHashCode(Object key) {
/*  40 */     return HashTable.HashUtils.class.elemHashCode((HashTable.HashUtils)this, key);
/*     */   }
/*     */   
/*     */   public final int improve(int hcode, int seed) {
/*  40 */     return HashTable.HashUtils.class.improve((HashTable.HashUtils)this, hcode, seed);
/*     */   }
/*     */   
/*     */   public <U> ParMap<K, U> updated(Object key, Object value) {
/*  40 */     return ParMap$class.updated(this, key, value);
/*     */   }
/*     */   
/*     */   public ParMap<K, V> withDefault(Function1 d) {
/*  40 */     return ParMap$class.withDefault(this, d);
/*     */   }
/*     */   
/*     */   public ParMap<K, V> withDefaultValue(Object d) {
/*  40 */     return ParMap$class.withDefaultValue(this, d);
/*     */   }
/*     */   
/*     */   public <U> ParMap<K, U> $plus(Tuple2 kv) {
/*  40 */     return ParMapLike$class.$plus(this, kv);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> $minus(Object key) {
/*  40 */     return (ParHashMap<K, V>)ParMapLike$class.$minus(this, key);
/*     */   }
/*     */   
/*     */   public Object scala$collection$mutable$Cloneable$$super$clone() {
/*  40 */     return super.clone();
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> clone() {
/*  40 */     return (ParHashMap<K, V>)Cloneable.class.clone(this);
/*     */   }
/*     */   
/*     */   public Shrinkable<K> $minus$eq(Object elem1, Object elem2, Seq elems) {
/*  40 */     return Shrinkable.class.$minus$eq(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Shrinkable<K> $minus$minus$eq(TraversableOnce xs) {
/*  40 */     return Shrinkable.class.$minus$minus$eq(this, xs);
/*     */   }
/*     */   
/*     */   public Growable<Tuple2<K, V>> $plus$eq(Object elem1, Object elem2, Seq elems) {
/*  40 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Growable<Tuple2<K, V>> $plus$plus$eq(TraversableOnce xs) {
/*  40 */     return Growable.class.$plus$plus$eq(this, xs);
/*     */   }
/*     */   
/*     */   public GenericCompanion<ParIterable> companion() {
/*  40 */     return ParIterable$class.companion(this);
/*     */   }
/*     */   
/*     */   public ParIterable<Tuple2<K, V>> toIterable() {
/*  40 */     return ParIterable$class.toIterable(this);
/*     */   }
/*     */   
/*     */   public ParSeq<Tuple2<K, V>> toSeq() {
/*  40 */     return ParIterable$class.toSeq(this);
/*     */   }
/*     */   
/*     */   public V default(Object key) {
/*  40 */     return (V)ParMapLike.class.default(this, key);
/*     */   }
/*     */   
/*     */   public V apply(Object key) {
/*  40 */     return (V)ParMapLike.class.apply(this, key);
/*     */   }
/*     */   
/*     */   public <U> U getOrElse(Object key, Function0 default) {
/*  40 */     return (U)ParMapLike.class.getOrElse(this, key, default);
/*     */   }
/*     */   
/*     */   public boolean contains(Object key) {
/*  40 */     return ParMapLike.class.contains(this, key);
/*     */   }
/*     */   
/*     */   public boolean isDefinedAt(Object key) {
/*  40 */     return ParMapLike.class.isDefinedAt(this, key);
/*     */   }
/*     */   
/*     */   public IterableSplitter<K> keysIterator() {
/*  40 */     return ParMapLike.class.keysIterator(this);
/*     */   }
/*     */   
/*     */   public IterableSplitter<V> valuesIterator() {
/*  40 */     return ParMapLike.class.valuesIterator(this);
/*     */   }
/*     */   
/*     */   public ParSet<K> keySet() {
/*  40 */     return ParMapLike.class.keySet(this);
/*     */   }
/*     */   
/*     */   public ParIterable<K> keys() {
/*  40 */     return ParMapLike.class.keys(this);
/*     */   }
/*     */   
/*     */   public ParIterable<V> values() {
/*  40 */     return ParMapLike.class.values(this);
/*     */   }
/*     */   
/*     */   public ParMap<K, V> filterKeys(Function1 p) {
/*  40 */     return ParMapLike.class.filterKeys(this, p);
/*     */   }
/*     */   
/*     */   public <S> ParMap<K, S> mapValues(Function1 f) {
/*  40 */     return ParMapLike.class.mapValues(this, f);
/*     */   }
/*     */   
/*     */   public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
/*  40 */     return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
/*  40 */     this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
/*     */   }
/*     */   
/*     */   private ParIterableLike$ScanNode$ ScanNode$lzycompute() {
/*  40 */     synchronized (this) {
/*  40 */       if (this.ScanNode$module == null)
/*  40 */         this.ScanNode$module = new ParIterableLike$ScanNode$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/mutable/ParHashMap}} */
/*  40 */       return this.ScanNode$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParIterableLike$ScanNode$ ScanNode() {
/*  40 */     return (this.ScanNode$module == null) ? ScanNode$lzycompute() : this.ScanNode$module;
/*     */   }
/*     */   
/*     */   private ParIterableLike$ScanLeaf$ ScanLeaf$lzycompute() {
/*  40 */     synchronized (this) {
/*  40 */       if (this.ScanLeaf$module == null)
/*  40 */         this.ScanLeaf$module = new ParIterableLike$ScanLeaf$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/mutable/ParHashMap}} */
/*  40 */       return this.ScanLeaf$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParIterableLike$ScanLeaf$ ScanLeaf() {
/*  40 */     return (this.ScanLeaf$module == null) ? ScanLeaf$lzycompute() : this.ScanLeaf$module;
/*     */   }
/*     */   
/*     */   public void initTaskSupport() {
/*  40 */     ParIterableLike.class.initTaskSupport(this);
/*     */   }
/*     */   
/*     */   public TaskSupport tasksupport() {
/*  40 */     return ParIterableLike.class.tasksupport(this);
/*     */   }
/*     */   
/*     */   public void tasksupport_$eq(TaskSupport ts) {
/*  40 */     ParIterableLike.class.tasksupport_$eq(this, ts);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> repr() {
/*  40 */     return (ParHashMap<K, V>)ParIterableLike.class.repr(this);
/*     */   }
/*     */   
/*     */   public final boolean isTraversableAgain() {
/*  40 */     return ParIterableLike.class.isTraversableAgain(this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/*  40 */     return ParIterableLike.class.hasDefiniteSize(this);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  40 */     return ParIterableLike.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/*  40 */     return ParIterableLike.class.nonEmpty(this);
/*     */   }
/*     */   
/*     */   public Tuple2<K, V> head() {
/*  40 */     return (Tuple2<K, V>)ParIterableLike.class.head(this);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<K, V>> headOption() {
/*  40 */     return ParIterableLike.class.headOption(this);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> tail() {
/*  40 */     return (ParHashMap<K, V>)ParIterableLike.class.tail(this);
/*     */   }
/*     */   
/*     */   public Tuple2<K, V> last() {
/*  40 */     return (Tuple2<K, V>)ParIterableLike.class.last(this);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<K, V>> lastOption() {
/*  40 */     return ParIterableLike.class.lastOption(this);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> init() {
/*  40 */     return (ParHashMap<K, V>)ParIterableLike.class.init(this);
/*     */   }
/*     */   
/*     */   public Splitter<Tuple2<K, V>> iterator() {
/*  40 */     return ParIterableLike.class.iterator(this);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> par() {
/*  40 */     return (ParHashMap<K, V>)ParIterableLike.class.par(this);
/*     */   }
/*     */   
/*     */   public boolean isStrictSplitterCollection() {
/*  40 */     return ParIterableLike.class.isStrictSplitterCollection(this);
/*     */   }
/*     */   
/*     */   public <S, That> Combiner<S, That> reuse(Option oldc, Combiner newc) {
/*  40 */     return ParIterableLike.class.reuse(this, oldc, newc);
/*     */   }
/*     */   
/*     */   public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask tsk) {
/*  40 */     return ParIterableLike.class.task2ops(this, tsk);
/*     */   }
/*     */   
/*     */   public <R> Object wrap(Function0 body) {
/*  40 */     return ParIterableLike.class.wrap(this, body);
/*     */   }
/*     */   
/*     */   public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(DelegatedSignalling it) {
/*  40 */     return ParIterableLike.class.delegatedSignalling2ops(this, it);
/*     */   }
/*     */   
/*     */   public <Elem, To> Object builder2ops(Builder cb) {
/*  40 */     return ParIterableLike.class.builder2ops(this, cb);
/*     */   }
/*     */   
/*     */   public <S, That> Object bf2seq(CanBuildFrom bf) {
/*  40 */     return ParIterableLike.class.bf2seq(this, bf);
/*     */   }
/*     */   
/*     */   public <S, That extends Parallel> ParHashMap<K, V> sequentially(Function1 b) {
/*  40 */     return (ParHashMap<K, V>)ParIterableLike.class.sequentially(this, b);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/*  40 */     return ParIterableLike.class.mkString(this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/*  40 */     return ParIterableLike.class.mkString(this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/*  40 */     return ParIterableLike.class.mkString(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  40 */     return ParIterableLike.class.toString(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object other) {
/*  40 */     return ParIterableLike.class.canEqual(this, other);
/*     */   }
/*     */   
/*     */   public <U> U reduce(Function2 op) {
/*  40 */     return (U)ParIterableLike.class.reduce(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceOption(Function2 op) {
/*  40 */     return ParIterableLike.class.reduceOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> U fold(Object z, Function2 op) {
/*  40 */     return (U)ParIterableLike.class.fold(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S aggregate(Object z, Function2 seqop, Function2 combop) {
/*  40 */     return (S)ParIterableLike.class.aggregate(this, z, seqop, combop);
/*     */   }
/*     */   
/*     */   public <S> S foldLeft(Object z, Function2 op) {
/*  40 */     return (S)ParIterableLike.class.foldLeft(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S foldRight(Object z, Function2 op) {
/*  40 */     return (S)ParIterableLike.class.foldRight(this, z, op);
/*     */   }
/*     */   
/*     */   public <U> U reduceLeft(Function2 op) {
/*  40 */     return (U)ParIterableLike.class.reduceLeft(this, op);
/*     */   }
/*     */   
/*     */   public <U> U reduceRight(Function2 op) {
/*  40 */     return (U)ParIterableLike.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceLeftOption(Function2 op) {
/*  40 */     return ParIterableLike.class.reduceLeftOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceRightOption(Function2 op) {
/*  40 */     return ParIterableLike.class.reduceRightOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/*  40 */     ParIterableLike.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/*  40 */     return ParIterableLike.class.count(this, p);
/*     */   }
/*     */   
/*     */   public <U> U sum(Numeric num) {
/*  40 */     return (U)ParIterableLike.class.sum(this, num);
/*     */   }
/*     */   
/*     */   public <U> U product(Numeric num) {
/*  40 */     return (U)ParIterableLike.class.product(this, num);
/*     */   }
/*     */   
/*     */   public <U> Tuple2<K, V> min(Ordering ord) {
/*  40 */     return (Tuple2<K, V>)ParIterableLike.class.min(this, ord);
/*     */   }
/*     */   
/*     */   public <U> Tuple2<K, V> max(Ordering ord) {
/*  40 */     return (Tuple2<K, V>)ParIterableLike.class.max(this, ord);
/*     */   }
/*     */   
/*     */   public <S> Tuple2<K, V> maxBy(Function1 f, Ordering cmp) {
/*  40 */     return (Tuple2<K, V>)ParIterableLike.class.maxBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <S> Tuple2<K, V> minBy(Function1 f, Ordering cmp) {
/*  40 */     return (Tuple2<K, V>)ParIterableLike.class.minBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <S, That> That map(Function1 f, CanBuildFrom bf) {
/*  40 */     return (That)ParIterableLike.class.map(this, f, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  40 */     return (That)ParIterableLike.class.collect(this, pf, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  40 */     return (That)ParIterableLike.class.flatMap(this, f, bf);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 pred) {
/*  40 */     return ParIterableLike.class.forall(this, pred);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 pred) {
/*  40 */     return ParIterableLike.class.exists(this, pred);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<K, V>> find(Function1 pred) {
/*  40 */     return ParIterableLike.class.find(this, pred);
/*     */   }
/*     */   
/*     */   public Object combinerFactory() {
/*  40 */     return ParIterableLike.class.combinerFactory(this);
/*     */   }
/*     */   
/*     */   public <S, That> Object combinerFactory(Function0 cbf) {
/*  40 */     return ParIterableLike.class.combinerFactory(this, cbf);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> filter(Function1 pred) {
/*  40 */     return (ParHashMap<K, V>)ParIterableLike.class.filter(this, pred);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> filterNot(Function1 pred) {
/*  40 */     return (ParHashMap<K, V>)ParIterableLike.class.filterNot(this, pred);
/*     */   }
/*     */   
/*     */   public <U, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/*  40 */     return (That)ParIterableLike.class.$plus$plus(this, that, bf);
/*     */   }
/*     */   
/*     */   public Tuple2<ParHashMap<K, V>, ParHashMap<K, V>> partition(Function1 pred) {
/*  40 */     return ParIterableLike.class.partition(this, pred);
/*     */   }
/*     */   
/*     */   public <K> ParMap<K, ParHashMap<K, V>> groupBy(Function1 f) {
/*  40 */     return ParIterableLike.class.groupBy(this, f);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> take(int n) {
/*  40 */     return (ParHashMap<K, V>)ParIterableLike.class.take(this, n);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> drop(int n) {
/*  40 */     return (ParHashMap<K, V>)ParIterableLike.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> slice(int unc_from, int unc_until) {
/*  40 */     return (ParHashMap<K, V>)ParIterableLike.class.slice(this, unc_from, unc_until);
/*     */   }
/*     */   
/*     */   public Tuple2<ParHashMap<K, V>, ParHashMap<K, V>> splitAt(int n) {
/*  40 */     return ParIterableLike.class.splitAt(this, n);
/*     */   }
/*     */   
/*     */   public <U, That> That scan(Object z, Function2 op, CanBuildFrom bf) {
/*  40 */     return (That)ParIterableLike.class.scan(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  40 */     return (That)ParIterableLike.class.scanLeft(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  40 */     return (That)ParIterableLike.class.scanRight(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> takeWhile(Function1 pred) {
/*  40 */     return (ParHashMap<K, V>)ParIterableLike.class.takeWhile(this, pred);
/*     */   }
/*     */   
/*     */   public Tuple2<ParHashMap<K, V>, ParHashMap<K, V>> span(Function1 pred) {
/*  40 */     return ParIterableLike.class.span(this, pred);
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> dropWhile(Function1 pred) {
/*  40 */     return (ParHashMap<K, V>)ParIterableLike.class.dropWhile(this, pred);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs) {
/*  40 */     ParIterableLike.class.copyToArray(this, xs);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs, int start) {
/*  40 */     ParIterableLike.class.copyToArray(this, xs, start);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs, int start, int len) {
/*  40 */     ParIterableLike.class.copyToArray(this, xs, start, len);
/*     */   }
/*     */   
/*     */   public <U> boolean sameElements(GenIterable that) {
/*  40 */     return ParIterableLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public <U, S, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  40 */     return (That)ParIterableLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That zipWithIndex(CanBuildFrom bf) {
/*  40 */     return (That)ParIterableLike.class.zipWithIndex(this, bf);
/*     */   }
/*     */   
/*     */   public <S, U, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  40 */     return (That)ParIterableLike.class.zipAll(this, that, thisElem, thatElem, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That toParCollection(Function0 cbf) {
/*  40 */     return (That)ParIterableLike.class.toParCollection(this, cbf);
/*     */   }
/*     */   
/*     */   public <K, V, That> That toParMap(Function0 cbf, Predef$.less.colon.less ev) {
/*  40 */     return (That)ParIterableLike.class.toParMap(this, cbf, ev);
/*     */   }
/*     */   
/*     */   public Object view() {
/*  40 */     return ParIterableLike.class.view(this);
/*     */   }
/*     */   
/*     */   public <U> Object toArray(ClassTag evidence$1) {
/*  40 */     return ParIterableLike.class.toArray(this, evidence$1);
/*     */   }
/*     */   
/*     */   public List<Tuple2<K, V>> toList() {
/*  40 */     return ParIterableLike.class.toList(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<Tuple2<K, V>> toIndexedSeq() {
/*  40 */     return ParIterableLike.class.toIndexedSeq(this);
/*     */   }
/*     */   
/*     */   public Stream<Tuple2<K, V>> toStream() {
/*  40 */     return ParIterableLike.class.toStream(this);
/*     */   }
/*     */   
/*     */   public Iterator<Tuple2<K, V>> toIterator() {
/*  40 */     return ParIterableLike.class.toIterator(this);
/*     */   }
/*     */   
/*     */   public <U> Buffer<U> toBuffer() {
/*  40 */     return ParIterableLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public GenTraversable<Tuple2<K, V>> toTraversable() {
/*  40 */     return ParIterableLike.class.toTraversable(this);
/*     */   }
/*     */   
/*     */   public <U> ParSet<U> toSet() {
/*  40 */     return ParIterableLike.class.toSet(this);
/*     */   }
/*     */   
/*     */   public <K, V> ParMap<K, V> toMap(Predef$.less.colon.less ev) {
/*  40 */     return ParIterableLike.class.toMap(this, ev);
/*     */   }
/*     */   
/*     */   public Vector<Tuple2<K, V>> toVector() {
/*  40 */     return ParIterableLike.class.toVector(this);
/*     */   }
/*     */   
/*     */   public <Col> Col to(CanBuildFrom cbf) {
/*  40 */     return (Col)ParIterableLike.class.to(this, cbf);
/*     */   }
/*     */   
/*     */   public int scanBlockSize() {
/*  40 */     return ParIterableLike.class.scanBlockSize(this);
/*     */   }
/*     */   
/*     */   public <S> S $div$colon(Object z, Function2 op) {
/*  40 */     return (S)ParIterableLike.class.$div$colon(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S $colon$bslash(Object z, Function2 op) {
/*  40 */     return (S)ParIterableLike.class.$colon$bslash(this, z, op);
/*     */   }
/*     */   
/*     */   public String debugInformation() {
/*  40 */     return ParIterableLike.class.debugInformation(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<String> debugBuffer() {
/*  40 */     return ParIterableLike.class.debugBuffer(this);
/*     */   }
/*     */   
/*     */   public void debugclear() {
/*  40 */     ParIterableLike.class.debugclear(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<String> debuglog(String s) {
/*  40 */     return ParIterableLike.class.debuglog(this, s);
/*     */   }
/*     */   
/*     */   public void printDebugBuffer() {
/*  40 */     ParIterableLike.class.printDebugBuffer(this);
/*     */   }
/*     */   
/*     */   public Combiner<Tuple2<K, V>, ParHashMap<K, V>> parCombiner() {
/*  40 */     return CustomParallelizable.class.parCombiner((CustomParallelizable)this);
/*     */   }
/*     */   
/*     */   public <P, Q> Combiner<Tuple2<P, Q>, ParHashMap<P, Q>> genericMapCombiner() {
/*  40 */     return GenericParMapTemplate.class.genericMapCombiner(this);
/*     */   }
/*     */   
/*     */   public Builder<Tuple2<K, V>, ParIterable<Tuple2<K, V>>> newBuilder() {
/*  40 */     return GenericParTemplate.class.newBuilder(this);
/*     */   }
/*     */   
/*     */   public <B> Combiner<B, ParIterable<B>> genericBuilder() {
/*  40 */     return GenericParTemplate.class.genericBuilder(this);
/*     */   }
/*     */   
/*     */   public <B> Combiner<B, ParIterable<B>> genericCombiner() {
/*  40 */     return GenericParTemplate.class.genericCombiner(this);
/*     */   }
/*     */   
/*     */   public <A1, A2> Tuple2<ParIterable<A1>, ParIterable<A2>> unzip(Function1 asPair) {
/*  40 */     return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*     */   }
/*     */   
/*     */   public <A1, A2, A3> Tuple3<ParIterable<A1>, ParIterable<A2>, ParIterable<A3>> unzip3(Function1 asTriple) {
/*  40 */     return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*     */   }
/*     */   
/*     */   public <B> ParIterable<B> flatten(Function1 asTraversable) {
/*  40 */     return (ParIterable<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public <B> ParIterable<ParIterable<B>> transpose(Function1 asTraversable) {
/*  40 */     return (ParIterable<ParIterable<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  40 */     return GenMapLike.class.hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*  40 */     return GenMapLike.class.equals(this, that);
/*     */   }
/*     */   
/*     */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  40 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public ParHashMap(HashTable.Contents<K, DefaultEntry<K, V>> contents) {
/*  40 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  40 */     Parallelizable.class.$init$((Parallelizable)this);
/*  40 */     GenMapLike.class.$init$(this);
/*  40 */     GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/*  40 */     GenTraversable.class.$init$((GenTraversable)this);
/*  40 */     GenIterable.class.$init$(this);
/*  40 */     GenericParTemplate.class.$init$(this);
/*  40 */     GenericParMapTemplate.class.$init$(this);
/*  40 */     CustomParallelizable.class.$init$((CustomParallelizable)this);
/*  40 */     ParIterableLike.class.$init$(this);
/*  40 */     ParIterable.class.$init$(this);
/*  40 */     ParMapLike.class.$init$(this);
/*  40 */     ParMap.class.$init$(this);
/*  40 */     ParIterable$class.$init$(this);
/*  40 */     Growable.class.$init$(this);
/*  40 */     Shrinkable.class.$init$(this);
/*  40 */     Cloneable.class.$init$(this);
/*  40 */     ParMapLike$class.$init$(this);
/*  40 */     ParMap$class.$init$(this);
/*  40 */     HashTable.HashUtils.class.$init$((HashTable.HashUtils)this);
/*  40 */     HashTable.class.$init$(this);
/*  40 */     ParHashTable$class.$init$(this);
/*  48 */     initWithContents(contents);
/*     */   }
/*     */   
/*     */   public ParHashMap() {
/*  52 */     this(null);
/*     */   }
/*     */   
/*     */   public GenericParMapCompanion<ParHashMap> mapCompanion() {
/*  54 */     return (GenericParMapCompanion<ParHashMap>)ParHashMap$.MODULE$;
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> empty() {
/*  56 */     return new ParHashMap();
/*     */   }
/*     */   
/*     */   public ParHashMapCombiner<K, V> newCombiner() {
/*  58 */     return ParHashMapCombiner$.MODULE$.apply();
/*     */   }
/*     */   
/*     */   public HashMap<K, V> seq() {
/*  60 */     return new HashMap(hashTableContents());
/*     */   }
/*     */   
/*     */   public ParHashMapIterator splitter() {
/*  62 */     return new ParHashMapIterator(this, 1, (table()).length, size(), (DefaultEntry)table()[0]);
/*     */   }
/*     */   
/*     */   public int size() {
/*  64 */     return tableSize();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  66 */     clearTable();
/*     */   }
/*     */   
/*     */   public Option<V> get(Object key) {
/*  69 */     DefaultEntry<K, V> e = findEntry((K)key);
/*  70 */     return (e == null) ? (Option<V>)None$.MODULE$ : 
/*  71 */       (Option<V>)new Some(e.value());
/*     */   }
/*     */   
/*     */   public Option<V> put(Object key, Object value) {
/*  75 */     DefaultEntry<Object, ?> e = findOrAddEntry(key, value);
/*  77 */     Object v = e.value();
/*  77 */     e.value_$eq(value);
/*  77 */     return (e == null) ? (Option<V>)None$.MODULE$ : (Option<V>)new Some(v);
/*     */   }
/*     */   
/*     */   public void update(Object key, Object value) {
/*  80 */     put((K)key, (V)value);
/*     */   }
/*     */   
/*     */   public Option<V> remove(Object key) {
/*  83 */     DefaultEntry<K, V> e = removeEntry((K)key);
/*  84 */     return (e != null) ? (Option<V>)new Some(e.value()) : 
/*  85 */       (Option<V>)None$.MODULE$;
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> $plus$eq(Tuple2 kv) {
/*  89 */     DefaultEntry<Object, ?> e = findOrAddEntry(kv._1(), kv._2());
/*  90 */     if (e != null)
/*  90 */       e.value_$eq(kv._2()); 
/*  91 */     return this;
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> $minus$eq(Object key) {
/*  94 */     removeEntry((K)key);
/*  94 */     return this;
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/*  96 */     return "ParHashMap";
/*     */   }
/*     */   
/*     */   public class ParHashMapIterator extends ParHashTable<K, DefaultEntry<K, V>>.EntryIterator<Tuple2<K, V>, ParHashMapIterator> {
/*     */     public ParHashMapIterator(ParHashMap $outer, int start, int untilIdx, int totalSize, DefaultEntry e) {
/*  98 */       super($outer, 
/*  99 */           start, untilIdx, totalSize, e);
/*     */     }
/*     */     
/*     */     public Tuple2<K, V> entry2item(DefaultEntry entry) {
/* 100 */       return new Tuple2(entry.key(), entry.value());
/*     */     }
/*     */     
/*     */     public ParHashMapIterator newIterator(int idxFrom, int idxUntil, int totalSz, DefaultEntry<K, V> es) {
/* 102 */       return new ParHashMapIterator(scala$collection$parallel$mutable$ParHashMap$ParHashMapIterator$$$outer(), idxFrom, idxUntil, totalSz, es);
/*     */     }
/*     */   }
/*     */   
/*     */   public <V1> DefaultEntry<K, V> createNewEntry(Object key, Object value) {
/* 106 */     return new DefaultEntry(key, value);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) {
/* 110 */     serializeTo(out, (Function1<DefaultEntry<K, V>, BoxedUnit>)new ParHashMap$$anonfun$writeObject$1(this, (ParHashMap<K, V>)out));
/*     */   }
/*     */   
/*     */   public class ParHashMap$$anonfun$writeObject$1 extends AbstractFunction1<DefaultEntry<K, V>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectOutputStream out$1;
/*     */     
/*     */     public ParHashMap$$anonfun$writeObject$1(ParHashMap $outer, ObjectOutputStream out$1) {}
/*     */     
/*     */     public final void apply(DefaultEntry entry) {
/* 111 */       this.out$1.writeObject(entry.key());
/* 112 */       this.out$1.writeObject(entry.value());
/*     */     }
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) {
/* 117 */     init(in, (Function0<DefaultEntry<K, V>>)new ParHashMap$$anonfun$readObject$1(this, (ParHashMap<K, V>)in));
/*     */   }
/*     */   
/*     */   public class ParHashMap$$anonfun$readObject$1 extends AbstractFunction0<DefaultEntry<K, V>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectInputStream in$1;
/*     */     
/*     */     public final DefaultEntry<K, V> apply() {
/* 117 */       return this.$outer.createNewEntry((K)this.in$1.readObject(), this.in$1.readObject());
/*     */     }
/*     */     
/*     */     public ParHashMap$$anonfun$readObject$1(ParHashMap $outer, ObjectInputStream in$1) {}
/*     */   }
/*     */   
/*     */   public Seq<String> brokenInvariants() {
/* 122 */     Predef$ predef$1 = Predef$.MODULE$;
/* 122 */     IndexedSeq buckets = (IndexedSeq)RichInt$.MODULE$.until$extension0(0, (table()).length / sizeMapBucketSize()).map((Function1)new ParHashMap$$anonfun$2(this), IndexedSeq$.MODULE$.canBuildFrom());
/* 125 */     Predef$ predef$2 = Predef$.MODULE$;
/* 125 */     IndexedSeq elems = (IndexedSeq)RichInt$.MODULE$.until$extension0(0, (table()).length).map((Function1)new ParHashMap$$anonfun$3(this), IndexedSeq$.MODULE$.canBuildFrom());
/* 127 */     return (Seq<String>)((TraversableLike)buckets.flatMap((Function1)new ParHashMap$$anonfun$brokenInvariants$1(this), IndexedSeq$.MODULE$.canBuildFrom())).$plus$plus((GenTraversableOnce)elems.flatMap((Function1)new ParHashMap$$anonfun$brokenInvariants$2(this), IndexedSeq$.MODULE$.canBuildFrom()), IndexedSeq$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public class ParHashMap$$anonfun$2 extends AbstractFunction1<Object, List<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<String> apply(int i) {
/*     */       return this.$outer.scala$collection$parallel$mutable$ParHashMap$$checkBucket(i);
/*     */     }
/*     */     
/*     */     public ParHashMap$$anonfun$2(ParHashMap $outer) {}
/*     */   }
/*     */   
/*     */   public class ParHashMap$$anonfun$3 extends AbstractFunction1<Object, List<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<String> apply(int i) {
/*     */       return this.$outer.scala$collection$parallel$mutable$ParHashMap$$checkEntry(i);
/*     */     }
/*     */     
/*     */     public ParHashMap$$anonfun$3(ParHashMap $outer) {}
/*     */   }
/*     */   
/*     */   public class ParHashMap$$anonfun$brokenInvariants$1 extends AbstractFunction1<List<String>, List<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<String> apply(List<String> x) {
/* 127 */       return x;
/*     */     }
/*     */     
/*     */     public ParHashMap$$anonfun$brokenInvariants$1(ParHashMap $outer) {}
/*     */   }
/*     */   
/*     */   public class ParHashMap$$anonfun$brokenInvariants$2 extends AbstractFunction1<List<String>, List<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<String> apply(List<String> x) {
/* 127 */       return x;
/*     */     }
/*     */     
/*     */     public ParHashMap$$anonfun$brokenInvariants$2(ParHashMap $outer) {}
/*     */   }
/*     */   
/*     */   public final int scala$collection$parallel$mutable$ParHashMap$$count$1(HashEntry e) {
/* 131 */     return (e == null) ? 0 : (1 + scala$collection$parallel$mutable$ParHashMap$$count$1((HashEntry)e.next()));
/*     */   }
/*     */   
/*     */   public List<String> scala$collection$parallel$mutable$ParHashMap$$checkBucket(int i) {
/* 132 */     int expected = sizemap()[i];
/* 133 */     int j = i * sizeMapBucketSize();
/* 133 */     Predef$ predef$ = Predef$.MODULE$;
/* 133 */     int found = BoxesRunTime.unboxToInt(RichInt$.MODULE$.until$extension0(j, (i + 1) * sizeMapBucketSize()).foldLeft(BoxesRunTime.boxToInteger(0), 
/* 134 */           (Function2)new ParHashMap$$anonfun$1(this)));
/* 136 */     (new String[1])[0] = (new StringBuilder()).append("Found ").append(BoxesRunTime.boxToInteger(found)).append(" elements, while sizemap showed ").append(BoxesRunTime.boxToInteger(expected)).toString();
/* 136 */     return (found != expected) ? List$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[1])) : 
/* 137 */       (List<String>)Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public class ParHashMap$$anonfun$1 extends AbstractFunction2.mcIII.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int acc, int c) {
/*     */       return apply$mcIII$sp(acc, c);
/*     */     }
/*     */     
/*     */     public int apply$mcIII$sp(int acc, int c) {
/*     */       return acc + this.$outer.scala$collection$parallel$mutable$ParHashMap$$count$1(this.$outer.table()[c]);
/*     */     }
/*     */     
/*     */     public ParHashMap$$anonfun$1(ParHashMap $outer) {}
/*     */   }
/*     */   
/*     */   private final List check$1(HashEntry e, int i$1) {
/*     */     while (true) {
/* 142 */       if (index(elemHashCode((K)e.key())) == i$1) {
/* 142 */         e = (HashEntry)e.next();
/*     */         continue;
/*     */       } 
/* 143 */       String str = (new StringBuilder()).append("Element ").append(e.key()).append(" at ").append(BoxesRunTime.boxToInteger(i$1)).append(" with ").append(BoxesRunTime.boxToInteger(elemHashCode((K)e.key()))).append(" maps to ").append(BoxesRunTime.boxToInteger(index(elemHashCode((K)e.key())))).toString();
/* 143 */       return (e == null) ? (List)Nil$.MODULE$ : check$1((HashEntry)e.next(), i$1).$colon$colon(str);
/*     */     } 
/*     */   }
/*     */   
/*     */   public List<String> scala$collection$parallel$mutable$ParHashMap$$checkEntry(int i) {
/* 144 */     return check$1(table()[i], i);
/*     */   }
/*     */   
/*     */   public static <K, V> CanCombineFrom<ParHashMap<?, ?>, Tuple2<K, V>, ParHashMap<K, V>> canBuildFrom() {
/*     */     return ParHashMap$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static void iters_$eq(int paramInt) {
/*     */     ParHashMap$.MODULE$.iters_$eq(paramInt);
/*     */   }
/*     */   
/*     */   public static int iters() {
/*     */     return ParHashMap$.MODULE$.iters();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */