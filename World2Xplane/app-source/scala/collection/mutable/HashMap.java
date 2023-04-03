/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.CustomParallelizable;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.MapLike;
/*     */ import scala.collection.Parallel;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.Shrinkable;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.mutable.ParHashMap;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tea\001B\001\003\001%\021q\001S1tQ6\013\007O\003\002\004\t\0059Q.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001Qc\001\006\0229M9\001a\003\020\"K-J\004\003\002\007\016\037mi\021AA\005\003\035\t\0211\"\0212tiJ\f7\r^'baB\021\001#\005\007\001\t\025\021\002A1\001\024\005\005\t\025C\001\013\031!\t)b#D\001\007\023\t9bAA\004O_RD\027N\\4\021\005UI\022B\001\016\007\005\r\te.\037\t\003!q!Q!\b\001C\002M\021\021A\021\t\005\031}y1$\003\002!\005\t\031Q*\0319\021\0131\021sb\007\023\n\005\r\022!aB'ba2K7.\032\t\005\031\001y1\004\005\003\rM=A\023BA\024\003\005%A\025m\0355UC\ndW\r\005\003\rS=Y\022B\001\026\003\0051!UMZ1vYR,e\016\036:z!\021aSf\f\032\016\003\021I!A\f\003\003)\r+8\017^8n!\006\024\030\r\0347fY&T\030M\0317f!\021)\002gD\016\n\005E2!A\002+va2,'\007\005\0034o=YR\"\001\033\013\005\r)$B\001\034\005\003!\001\030M]1mY\026d\027B\001\0355\005)\001\026M\035%bg\"l\025\r\035\t\003+iJ!a\017\004\003\031M+'/[1mSj\f'\r\\3\t\021u\002!\021!Q\001\ny\n\001bY8oi\026tGo\035\t\005\t{\001F\004\002\r\001&\021\021IA\001\n\021\006\034\b\016V1cY\026L!a\021#\003\021\r{g\016^3oiNT!!\021\002\t\r\031\003A\021\001\003H\003\031a\024N\\5u}Q\021A\005\023\005\006{\025\003\rAP\003\005\025\002\001\001FA\003F]R\024\030\020C\003M\001\021\005S*A\003f[B$\0300F\001%\021\025y\005\001\"\021Q\003\025\031G.Z1s)\005\t\006CA\013S\023\t\031fA\001\003V]&$\b\"B+\001\t\0032\026\001B:ju\026,\022a\026\t\003+aK!!\027\004\003\007%sG\017C\003G\001\021\0051\fF\001%\021\025i\006\001\"\021_\003\r\001\030M]\013\002e!)\001\r\001C!C\006A1m\0348uC&t7\017\006\002cKB\021QcY\005\003I\032\021qAQ8pY\026\fg\016C\003g?\002\007q\"A\002lKfDQ\001\033\001\005B%\fQ!\0319qYf$\"a\0076\t\013\031<\007\031A\b\t\0131\004A\021A7\002\007\035,G\017\006\002ocB\031Qc\\\016\n\005A4!AB(qi&|g\016C\003gW\002\007q\002C\003t\001\021\005C/A\002qkR$2A\\;w\021\0251'\0171\001\020\021\0259(\0171\001\034\003\0251\030\r\\;f\021\025I\b\001\"\021{\003\031)\b\017Z1uKR\031\021k\037?\t\013\031D\b\031A\b\t\013]D\b\031A\016\t\013y\004A\021I@\002\rI,Wn\034<f)\rq\027\021\001\005\006Mv\004\ra\004\005\b\003\013\001A\021AA\004\003!!\003\017\\;tI\025\fH\003BA\005\003\027i\021\001\001\005\b\003\033\t\031\0011\0010\003\tYg\017C\004\002\022\001!\t!a\005\002\023\021j\027N\\;tI\025\fH\003BA\005\003+AaAZA\b\001\004y\001bBA\r\001\021\005\0211D\001\tSR,'/\031;peV\021\021Q\004\t\005Y\005}q&C\002\002\"\021\021\001\"\023;fe\006$xN\035\005\b\003K\001A\021IA\024\003\0351wN]3bG\",B!!\013\0028Q\031\021+a\013\t\021\0055\0221\005a\001\003_\t\021A\032\t\007+\005Er&!\016\n\007\005MbAA\005Gk:\034G/[8ocA\031\001#a\016\005\017\005e\0221\005b\001'\t\t1\tC\004\002>\001!\t%a\020\002\r-,\027pU3u+\t\t\t\005\005\003-\003\007z\021bAA#\t\t\0311+\032;\t\017\005%\003\001\"\021\002L\0051a/\0317vKN,\"!!\024\021\t1\nyeG\005\004\003#\"!\001C%uKJ\f'\r\\3\t\017\005U\003\001\"\021\002X\005a1.Z=t\023R,'/\031;peV\021\021\021\f\t\005Y\005}q\002C\004\002^\001!\t%a\030\002\035Y\fG.^3t\023R,'/\031;peV\021\021\021\r\t\005Y\005}1\004C\004\002f\001!\t!a\032\002\025U\034XmU5{K6\013\007\017F\002R\003SBq!a\033\002d\001\007!-A\001u\021\035\ty\007\001C\t\003c\nab\031:fCR,g*Z<F]R\024\0300\006\003\002t\005uDCBA;\003o\nI\bE\002\002\n%CaAZA7\001\004y\001bB<\002n\001\007\0211\020\t\004!\005uDaBA@\003[\022\ra\005\002\003\005FBq!a!\001\t\023\t))A\006xe&$Xm\0242kK\016$HcA)\002\b\"A\021\021RAA\001\004\tY)A\002pkR\004B!!$\002\0306\021\021q\022\006\005\003#\013\031*\001\002j_*\021\021QS\001\005U\0064\030-\003\003\002\032\006=%AE(cU\026\034GoT;uaV$8\013\036:fC6Dq!!(\001\t\023\ty*\001\006sK\006$wJ\0316fGR$2!UAQ\021!\t\031+a'A\002\005\025\026AA5o!\021\ti)a*\n\t\005%\026q\022\002\022\037\nTWm\031;J]B,Ho\025;sK\006l\007&\002\001\002.\006M\006cA\013\0020&\031\021\021\027\004\003!M+'/[1m-\026\0248/[8o+&#e$A\001\b\017\005]&\001#\001\002:\0069\001*Y:i\033\006\004\bc\001\007\002<\0321\021A\001E\001\003{\033R!a/\002@f\002b!!1\002H\006-WBAAb\025\r\t)\rB\001\bO\026tWM]5d\023\021\tI-a1\003#5+H/\0312mK6\013\007OR1di>\024\030\020\005\002\r\001!9a)a/\005\002\005=GCAA]\021!\t\031.a/\005\004\005U\027\001D2b]\n+\030\016\0343Ge>lWCBAl\003_\f\0310\006\002\002ZBQ\021\021YAn\003?\fY/!>\n\t\005u\0271\031\002\r\007\006t')^5mI\032\023x.\034\t\005\003C\f\031/\004\002\002<&!\021Q]At\005\021\031u\016\0347\n\t\005%\0301\031\002\016\017\026tW*\0319GC\016$xN]=\021\rU\001\024Q^Ay!\r\001\022q\036\003\007%\005E'\031A\n\021\007A\t\031\020\002\004\036\003#\024\ra\005\t\007\031\001\ti/!=\t\0171\013Y\f\"\001\002zV1\0211 B\001\005\013)\"!!@\021\r1\001\021q B\002!\r\001\"\021\001\003\007%\005](\031A\n\021\007A\021)\001\002\004\036\003o\024\ra\005\005\013\005\023\tY,!A\005\n\t-\021a\003:fC\022\024Vm]8mm\026$\"A!\004\021\t\t=!QC\007\003\005#QAAa\005\002\024\006!A.\0318h\023\021\0219B!\005\003\r=\023'.Z2u\001")
/*     */ public class HashMap<A, B> extends AbstractMap<A, B> implements Map<A, B>, MapLike<A, B, HashMap<A, B>>, HashTable<A, DefaultEntry<A, B>>, CustomParallelizable<Tuple2<A, B>, ParHashMap<A, B>>, Serializable {
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
/*     */   public Combiner<Tuple2<A, B>, ParHashMap<A, B>> parCombiner() {
/*  39 */     return CustomParallelizable.class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public int _loadFactor() {
/*  39 */     return this._loadFactor;
/*     */   }
/*     */   
/*     */   public void _loadFactor_$eq(int x$1) {
/*  39 */     this._loadFactor = x$1;
/*     */   }
/*     */   
/*     */   public HashEntry<A, DefaultEntry<A, B>>[] table() {
/*  39 */     return (HashEntry[])this.table;
/*     */   }
/*     */   
/*     */   public void table_$eq(HashEntry[] x$1) {
/*  39 */     this.table = (HashEntry<Object, HashEntry>[])x$1;
/*     */   }
/*     */   
/*     */   public int tableSize() {
/*  39 */     return this.tableSize;
/*     */   }
/*     */   
/*     */   public void tableSize_$eq(int x$1) {
/*  39 */     this.tableSize = x$1;
/*     */   }
/*     */   
/*     */   public int threshold() {
/*  39 */     return this.threshold;
/*     */   }
/*     */   
/*     */   public void threshold_$eq(int x$1) {
/*  39 */     this.threshold = x$1;
/*     */   }
/*     */   
/*     */   public int[] sizemap() {
/*  39 */     return this.sizemap;
/*     */   }
/*     */   
/*     */   public void sizemap_$eq(int[] x$1) {
/*  39 */     this.sizemap = x$1;
/*     */   }
/*     */   
/*     */   public int seedvalue() {
/*  39 */     return this.seedvalue;
/*     */   }
/*     */   
/*     */   public void seedvalue_$eq(int x$1) {
/*  39 */     this.seedvalue = x$1;
/*     */   }
/*     */   
/*     */   public int tableSizeSeed() {
/*  39 */     return HashTable$class.tableSizeSeed(this);
/*     */   }
/*     */   
/*     */   public int initialSize() {
/*  39 */     return HashTable$class.initialSize(this);
/*     */   }
/*     */   
/*     */   public void init(ObjectInputStream in, Function0 readEntry) {
/*  39 */     HashTable$class.init(this, in, readEntry);
/*     */   }
/*     */   
/*     */   public void serializeTo(ObjectOutputStream out, Function1 writeEntry) {
/*  39 */     HashTable$class.serializeTo(this, out, writeEntry);
/*     */   }
/*     */   
/*     */   public DefaultEntry<A, B> findEntry(Object key) {
/*  39 */     return (DefaultEntry<A, B>)HashTable$class.findEntry(this, key);
/*     */   }
/*     */   
/*     */   public void addEntry(HashEntry e) {
/*  39 */     HashTable$class.addEntry(this, e);
/*     */   }
/*     */   
/*     */   public <B> DefaultEntry<A, B> findOrAddEntry(Object key, Object value) {
/*  39 */     return (DefaultEntry<A, B>)HashTable$class.findOrAddEntry(this, key, value);
/*     */   }
/*     */   
/*     */   public DefaultEntry<A, B> removeEntry(Object key) {
/*  39 */     return (DefaultEntry<A, B>)HashTable$class.removeEntry(this, key);
/*     */   }
/*     */   
/*     */   public Iterator<DefaultEntry<A, B>> entriesIterator() {
/*  39 */     return HashTable$class.entriesIterator(this);
/*     */   }
/*     */   
/*     */   public <U> void foreachEntry(Function1 f) {
/*  39 */     HashTable$class.foreachEntry(this, f);
/*     */   }
/*     */   
/*     */   public void clearTable() {
/*  39 */     HashTable$class.clearTable(this);
/*     */   }
/*     */   
/*     */   public void nnSizeMapAdd(int h) {
/*  39 */     HashTable$class.nnSizeMapAdd(this, h);
/*     */   }
/*     */   
/*     */   public void nnSizeMapRemove(int h) {
/*  39 */     HashTable$class.nnSizeMapRemove(this, h);
/*     */   }
/*     */   
/*     */   public void nnSizeMapReset(int tableLength) {
/*  39 */     HashTable$class.nnSizeMapReset(this, tableLength);
/*     */   }
/*     */   
/*     */   public final int totalSizeMapBuckets() {
/*  39 */     return HashTable$class.totalSizeMapBuckets(this);
/*     */   }
/*     */   
/*     */   public int calcSizeMapSize(int tableLength) {
/*  39 */     return HashTable$class.calcSizeMapSize(this, tableLength);
/*     */   }
/*     */   
/*     */   public void sizeMapInit(int tableLength) {
/*  39 */     HashTable$class.sizeMapInit(this, tableLength);
/*     */   }
/*     */   
/*     */   public void sizeMapInitAndRebuild() {
/*  39 */     HashTable$class.sizeMapInitAndRebuild(this);
/*     */   }
/*     */   
/*     */   public void printSizeMap() {
/*  39 */     HashTable$class.printSizeMap(this);
/*     */   }
/*     */   
/*     */   public void sizeMapDisable() {
/*  39 */     HashTable$class.sizeMapDisable(this);
/*     */   }
/*     */   
/*     */   public boolean isSizeMapDefined() {
/*  39 */     return HashTable$class.isSizeMapDefined(this);
/*     */   }
/*     */   
/*     */   public boolean alwaysInitSizeMap() {
/*  39 */     return HashTable$class.alwaysInitSizeMap(this);
/*     */   }
/*     */   
/*     */   public boolean elemEquals(Object key1, Object key2) {
/*  39 */     return HashTable$class.elemEquals(this, key1, key2);
/*     */   }
/*     */   
/*     */   public final int index(int hcode) {
/*  39 */     return HashTable$class.index(this, hcode);
/*     */   }
/*     */   
/*     */   public void initWithContents(HashTable.Contents c) {
/*  39 */     HashTable$class.initWithContents(this, c);
/*     */   }
/*     */   
/*     */   public HashTable.Contents<A, DefaultEntry<A, B>> hashTableContents() {
/*  39 */     return HashTable$class.hashTableContents(this);
/*     */   }
/*     */   
/*     */   public final int sizeMapBucketBitSize() {
/*  39 */     return HashTable.HashUtils$class.sizeMapBucketBitSize(this);
/*     */   }
/*     */   
/*     */   public final int sizeMapBucketSize() {
/*  39 */     return HashTable.HashUtils$class.sizeMapBucketSize(this);
/*     */   }
/*     */   
/*     */   public int elemHashCode(Object key) {
/*  39 */     return HashTable.HashUtils$class.elemHashCode(this, key);
/*     */   }
/*     */   
/*     */   public final int improve(int hcode, int seed) {
/*  39 */     return HashTable.HashUtils$class.improve(this, hcode, seed);
/*     */   }
/*     */   
/*     */   public HashMap(HashTable.Contents<A, DefaultEntry<A, B>> contents) {
/*  39 */     HashTable.HashUtils$class.$init$(this);
/*  39 */     HashTable$class.$init$(this);
/*  39 */     CustomParallelizable.class.$init$(this);
/*  47 */     initWithContents(contents);
/*     */   }
/*     */   
/*     */   public HashMap<A, B> empty() {
/*  51 */     return HashMap$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public void clear() {
/*  52 */     clearTable();
/*     */   }
/*     */   
/*     */   public int size() {
/*  53 */     return tableSize();
/*     */   }
/*     */   
/*     */   public HashMap() {
/*  55 */     this((HashTable.Contents<A, DefaultEntry<A, B>>)null);
/*     */   }
/*     */   
/*     */   public ParHashMap<A, B> par() {
/*  57 */     return new ParHashMap(hashTableContents());
/*     */   }
/*     */   
/*     */   public boolean contains(Object key) {
/*  60 */     return !(findEntry((A)key) == null);
/*     */   }
/*     */   
/*     */   public B apply(Object key) {
/*  63 */     DefaultEntry<A, B> result = findEntry((A)key);
/*  64 */     return (result == null) ? (B)default(key) : 
/*  65 */       result.value();
/*     */   }
/*     */   
/*     */   public Option<B> get(Object key) {
/*  69 */     DefaultEntry<A, B> e = findEntry((A)key);
/*  70 */     return (e == null) ? (Option<B>)None$.MODULE$ : 
/*  71 */       (Option<B>)new Some(e.value());
/*     */   }
/*     */   
/*     */   public Option<B> put(Object key, Object value) {
/*  75 */     DefaultEntry<Object, Object> e = findOrAddEntry(key, value);
/*  77 */     Object v = e.value();
/*  77 */     e.value_$eq(value);
/*  77 */     return (e == null) ? (Option<B>)None$.MODULE$ : (Option<B>)new Some(v);
/*     */   }
/*     */   
/*     */   public void update(Object key, Object value) {
/*  80 */     put((A)key, (B)value);
/*     */   }
/*     */   
/*     */   public Option<B> remove(Object key) {
/*  83 */     DefaultEntry<A, B> e = removeEntry((A)key);
/*  84 */     return (e != null) ? (Option<B>)new Some(e.value()) : 
/*  85 */       (Option<B>)None$.MODULE$;
/*     */   }
/*     */   
/*     */   public HashMap<A, B> $plus$eq(Tuple2 kv) {
/*  89 */     DefaultEntry<Object, Object> e = findOrAddEntry(kv._1(), kv._2());
/*  90 */     if (e != null)
/*  90 */       e.value_$eq(kv._2()); 
/*  91 */     return this;
/*     */   }
/*     */   
/*     */   public HashMap<A, B> $minus$eq(Object key) {
/*  94 */     removeEntry((A)key);
/*  94 */     return this;
/*     */   }
/*     */   
/*     */   public Iterator<Tuple2<A, B>> iterator() {
/*  96 */     return entriesIterator().map((Function1)new HashMap$$anonfun$iterator$1(this));
/*     */   }
/*     */   
/*     */   public class HashMap$$anonfun$iterator$1 extends AbstractFunction1<DefaultEntry<A, B>, Tuple2<A, B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<A, B> apply(DefaultEntry e) {
/*  96 */       return new Tuple2(e.key(), e.value());
/*     */     }
/*     */     
/*     */     public HashMap$$anonfun$iterator$1(HashMap $outer) {}
/*     */   }
/*     */   
/*     */   public <C> void foreach(Function1 f) {
/*  98 */     foreachEntry((Function1<DefaultEntry<?, ?>, ?>)new HashMap$$anonfun$foreach$1(this, (HashMap<A, B>)f));
/*     */   }
/*     */   
/*     */   public class HashMap$$anonfun$foreach$1 extends AbstractFunction1<DefaultEntry<A, B>, C> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$1;
/*     */     
/*     */     public final C apply(DefaultEntry e) {
/*  98 */       return (C)this.f$1.apply(new Tuple2(e.key(), e.value()));
/*     */     }
/*     */     
/*     */     public HashMap$$anonfun$foreach$1(HashMap $outer, Function1 f$1) {}
/*     */   }
/*     */   
/*     */   public Set<A> keySet() {
/* 101 */     return (Set<A>)new HashMap$$anon$1(this);
/*     */   }
/*     */   
/*     */   public class HashMap$$anon$1 extends MapLike<A, B, HashMap<A, B>>.DefaultKeySet {
/*     */     public HashMap$$anon$1(HashMap $outer) {
/* 101 */       super($outer);
/*     */     }
/*     */     
/*     */     public <C> void foreach(Function1 f) {
/* 102 */       this.$outer.foreachEntry((Function1)new HashMap$$anon$1$$anonfun$foreach$2(this, ($anon$1)f));
/*     */     }
/*     */     
/*     */     public class HashMap$$anon$1$$anonfun$foreach$2 extends AbstractFunction1<DefaultEntry<A, B>, C> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 f$2;
/*     */       
/*     */       public final C apply(DefaultEntry e) {
/* 102 */         return (C)this.f$2.apply(e.key());
/*     */       }
/*     */       
/*     */       public HashMap$$anon$1$$anonfun$foreach$2(HashMap$$anon$1 $outer, Function1 f$2) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public Iterable<B> values() {
/* 106 */     return (Iterable<B>)new HashMap$$anon$2(this);
/*     */   }
/*     */   
/*     */   public class HashMap$$anon$2 extends MapLike<A, B, HashMap<A, B>>.DefaultValuesIterable {
/*     */     public HashMap$$anon$2(HashMap $outer) {
/* 106 */       super($outer);
/*     */     }
/*     */     
/*     */     public <C> void foreach(Function1 f) {
/* 107 */       this.$outer.foreachEntry((Function1)new HashMap$$anon$2$$anonfun$foreach$3(this, ($anon$2)f));
/*     */     }
/*     */     
/*     */     public class HashMap$$anon$2$$anonfun$foreach$3 extends AbstractFunction1<DefaultEntry<A, B>, C> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 f$3;
/*     */       
/*     */       public final C apply(DefaultEntry e) {
/* 107 */         return (C)this.f$3.apply(e.value());
/*     */       }
/*     */       
/*     */       public HashMap$$anon$2$$anonfun$foreach$3(HashMap$$anon$2 $outer, Function1 f$3) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public Iterator<A> keysIterator() {
/* 111 */     return (Iterator<A>)new HashMap$$anon$3(this);
/*     */   }
/*     */   
/*     */   public class HashMap$$anon$3 extends AbstractIterator<A> {
/*     */     private final Iterator<DefaultEntry<A, B>> iter;
/*     */     
/*     */     public HashMap$$anon$3(HashMap<A, B> $outer) {
/* 112 */       this.iter = $outer.entriesIterator();
/*     */     }
/*     */     
/*     */     private Iterator<DefaultEntry<A, B>> iter() {
/* 112 */       return this.iter;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 113 */       return iter().hasNext();
/*     */     }
/*     */     
/*     */     public A next() {
/* 114 */       return (A)((DefaultEntry)iter().next()).key();
/*     */     }
/*     */   }
/*     */   
/*     */   public Iterator<B> valuesIterator() {
/* 118 */     return (Iterator<B>)new HashMap$$anon$4(this);
/*     */   }
/*     */   
/*     */   public class HashMap$$anon$4 extends AbstractIterator<B> {
/*     */     private final Iterator<DefaultEntry<A, B>> iter;
/*     */     
/*     */     public HashMap$$anon$4(HashMap<A, B> $outer) {
/* 119 */       this.iter = $outer.entriesIterator();
/*     */     }
/*     */     
/*     */     private Iterator<DefaultEntry<A, B>> iter() {
/* 119 */       return this.iter;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 120 */       return iter().hasNext();
/*     */     }
/*     */     
/*     */     public B next() {
/* 121 */       return (B)((DefaultEntry)iter().next()).value();
/*     */     }
/*     */   }
/*     */   
/*     */   public void useSizeMap(boolean t) {
/* 126 */     if (t) {
/* 127 */       if (!isSizeMapDefined())
/* 127 */         sizeMapInitAndRebuild(); 
/*     */     } else {
/* 128 */       sizeMapDisable();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <B1> DefaultEntry<A, B> createNewEntry(Object key, Object value) {
/* 131 */     return new DefaultEntry<A, B>((A)key, (B)value);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) {
/* 135 */     serializeTo(out, (Function1<DefaultEntry<A, B>, BoxedUnit>)new HashMap$$anonfun$writeObject$1(this, (HashMap<A, B>)out));
/*     */   }
/*     */   
/*     */   public class HashMap$$anonfun$writeObject$1 extends AbstractFunction1<DefaultEntry<A, B>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectOutputStream out$1;
/*     */     
/*     */     public HashMap$$anonfun$writeObject$1(HashMap $outer, ObjectOutputStream out$1) {}
/*     */     
/*     */     public final void apply(DefaultEntry entry) {
/* 136 */       this.out$1.writeObject(entry.key());
/* 137 */       this.out$1.writeObject(entry.value());
/*     */     }
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) {
/* 142 */     init(in, (Function0<DefaultEntry<A, B>>)new HashMap$$anonfun$readObject$1(this, (HashMap<A, B>)in));
/*     */   }
/*     */   
/*     */   public static <A, B> CanBuildFrom<HashMap<?, ?>, Tuple2<A, B>, HashMap<A, B>> canBuildFrom() {
/*     */     return HashMap$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public class HashMap$$anonfun$readObject$1 extends AbstractFunction0<DefaultEntry<A, B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectInputStream in$1;
/*     */     
/*     */     public final DefaultEntry<A, B> apply() {
/* 142 */       return this.$outer.createNewEntry((A)this.in$1.readObject(), this.in$1.readObject());
/*     */     }
/*     */     
/*     */     public HashMap$$anonfun$readObject$1(HashMap $outer, ObjectInputStream in$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\HashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */