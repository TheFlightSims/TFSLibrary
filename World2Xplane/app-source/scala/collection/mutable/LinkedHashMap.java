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
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Iterator$;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.MapLike;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.Shrinkable;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t5t!B\001\003\021\003I\021!\004'j].,G\rS1tQ6\013\007O\003\002\004\t\0059Q.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001\001C\001\006\f\033\005\021a!\002\007\003\021\003i!!\004'j].,G\rS1tQ6\013\007oE\002\f\035]\0022a\004\n\025\033\005\001\"BA\t\005\003\0359WM\\3sS\016L!a\005\t\003#5+H/\0312mK6\013\007OR1di>\024\030\020\005\002\013+\031!AB\001\001\027+\r9R\004K\n\007+aQS&M\034\021\t)I2dJ\005\0035\t\0211\"\0212tiJ\f7\r^'baB\021A$\b\007\001\t\025qRC1\001 \005\005\t\025C\001\021%!\t\t#%D\001\007\023\t\031cAA\004O_RD\027N\\4\021\005\005*\023B\001\024\007\005\r\te.\037\t\0039!\"Q!K\013C\002}\021\021A\021\t\005\025-Zr%\003\002-\005\t\031Q*\0319\021\013)q3d\n\031\n\005=\022!aB'ba2K7.\032\t\005\025UYr\005\005\003\013em!\024BA\032\003\005%A\025m\0355UC\ndW\r\005\003\013km9\023B\001\034\003\005-a\025N\\6fI\026sGO]=\021\005\005B\024BA\035\007\0051\031VM]5bY&T\030M\0317f\021\025YT\003\"\001=\003\031a\024N\\5u}Q\t\001\007C\003?+\021\005s(A\003f[B$\0300F\0011\021\025\tU\003\"\021C\003\021\031\030N_3\026\003\r\003\"!\t#\n\005\0253!aA%oi\026!q)\006\0015\005\025)e\016\036:z\021\035IU\0031A\005\022)\013!BZ5sgR,e\016\036:z+\005Y\005C\001'G\033\005)\002b\002(\026\001\004%\tbT\001\017M&\0248\017^#oiJLx\fJ3r)\t\0016\013\005\002\"#&\021!K\002\002\005+:LG\017C\004U\033\006\005\t\031A&\002\007a$\023\007\003\004W+\001\006KaS\001\fM&\0248\017^#oiJL\b\005\013\002V1B\021\021%W\005\0035\032\021\021\002\036:b]NLWM\034;\t\017q+\002\031!C\t\025\006IA.Y:u\013:$(/\037\005\b=V\001\r\021\"\005`\0035a\027m\035;F]R\024\030p\030\023fcR\021\001\013\031\005\b)v\013\t\0211\001L\021\031\021W\003)Q\005\027\006QA.Y:u\013:$(/\037\021)\005\005D\006\"B3\026\t\0031\027aA4fiR\021qM\033\t\004C!<\023BA5\007\005\031y\005\017^5p]\")1\016\032a\0017\005\0311.Z=\t\0135,B\021\t8\002\007A,H\017F\002h_BDQa\0337A\002mAQ!\0357A\002\035\nQA^1mk\026DQa]\013\005BQ\faA]3n_Z,GCA4v\021\025Y'\0171\001\034\021\0259X\003\"\001y\003!!\003\017\\;tI\025\fHC\001'z\021\025Qh\0171\001|\003\tYg\017\005\003\"yn9\023BA?\007\005\031!V\017\0357fe!1q0\006C\001\003\003\t\021\002J7j]V\034H%Z9\025\0071\013\031\001C\003l}\002\0071\004C\004\002\bU!\t!!\003\002\021%$XM]1u_J,\"!a\003\021\013\0055\021qB>\016\003\021I1!!\005\005\005!IE/\032:bi>\024hABA\013+!\t9B\001\007GS2$XM]3e\027\026L8o\005\003\002\024\005e\001c\001'\002\034%!\021QCA\017\023\tyC\001C\006\002\"\005M!\021!Q\001\n\005\r\022!\0019\021\r\005\n)cGA\025\023\r\t9C\002\002\n\rVt7\r^5p]F\0022!IA\026\023\r\tiC\002\002\b\005>|G.Z1o\021\035Y\0241\003C\001\003c!B!a\r\0026A\031A*a\005\t\021\005\005\022q\006a\001\003GAqAPA\n\t\003\nI$\006\002\002<A!!\"F\016!\021\035\ty$\006C!\003\003\n!BZ5mi\026\0248*Z=t)\021\t\031%a\022\021\r\0055\021QI\016(\023\taC\001\003\005\002\"\005u\002\031AA\022\r\031\tY%\006\005\002N\taQ*\0319qK\0224\026\r\\;fgV!\021qJA,'\021\tI%!\025\021\0131\013\031&!\026\n\t\005-\023Q\004\t\0049\005]CaBA-\003\023\022\ra\b\002\002\007\"Y\021QLA%\005\003\005\013\021BA0\003\0051\007CB\021\002&\035\n)\006C\004<\003\023\"\t!a\031\025\t\005\025\024q\r\t\006\031\006%\023Q\013\005\t\003;\n\t\0071\001\002`!9a(!\023\005B\005e\002bBA7+\021\005\023qN\001\n[\006\004h+\0317vKN,B!!\035\002xQ!\0211OA=!\035\ti!!\022\034\003k\0022\001HA<\t\035\tI&a\033C\002}A\001\"!\030\002l\001\007\0211\020\t\007C\005\025r%!\036\007\r\005}T\003CAA\0055!UMZ1vYR\\U-_*fiN!\021QPAB!\ra\025QQ\005\005\003\ni\002C\004<\003{\"\t!!#\025\005\005-\005c\001'\002~!9a(! \005B\005=UCAAI!\021Q\0211S\016\n\007\005U%AA\007MS:\\W\r\032%bg\"\034V\r\036\005\b\0033+B\021IAN\003\031YW-_*fiV\021\021Q\024\t\006\003\033\tyjG\005\004\003C#!aA*fi\"9\021QU\013\005B\005\035\026\001D6fsNLE/\032:bi>\024XCAAU!\025\ti!a\004\034\021\035\ti+\006C!\003_\013aB^1mk\026\034\030\n^3sCR|'/\006\002\0022B)\021QBA\bO!9\021QW\013\005B\005]\026a\0024pe\026\f7\r[\013\005\003s\013\t\rF\002Q\003wC\001\"!\030\0024\002\007\021Q\030\t\007C\005\02520a0\021\007q\t\t\rB\004\002D\006M&\031A\020\003\003UCq!a2\026\t#\nI-\001\007g_J,\027m\0315F]R\024\0300\006\003\002L\006MGc\001)\002N\"A\021QLAc\001\004\ty\r\005\004\"\003KY\025\021\033\t\0049\005MGaBAb\003\013\024\ra\b\005\b\003/,B\021CAm\0039\031'/Z1uK:+w/\0228uef,B!a7\002dR)1*!8\002`\"11.!6A\002mAq!]Ak\001\004\t\t\017E\002\035\003G$q!!:\002V\n\007qD\001\002Cc!9\021\021^\013\005B\005-\030!B2mK\006\024H#\001)\t\017\005=X\003\"\003\002r\006YqO]5uK>\023'.Z2u)\r\001\0261\037\005\t\003k\fi\0171\001\002x\006\031q.\036;\021\t\005e(1A\007\003\003wTA!!@\002\000\006\021\021n\034\006\003\005\003\tAA[1wC&!!QAA~\005Iy%M[3di>+H\017];u'R\024X-Y7\t\017\t%Q\003\"\003\003\f\005Q!/Z1e\037\nTWm\031;\025\007A\023i\001\003\005\003\020\t\035\001\031\001B\t\003\tIg\016\005\003\002z\nM\021\002\002B\013\003w\024\021c\0242kK\016$\030J\0349viN#(/Z1nQ\025)\"\021\004B\020!\r\t#1D\005\004\005;1!\001E*fe&\fGNV3sg&|g.V%E=\005\t\001BB\036\f\t\003\021\031\003F\001\n\021\035\0219c\003C\002\005S\tAbY1o\005VLG\016\032$s_6,bAa\013\003D\t\035SC\001B\027!%y!q\006B\032\005\021I%C\002\0032A\021AbQ1o\005VLG\016\032$s_6\004BA!\016\00385\t1\"\003\003\003:\tm\"\001B\"pY2L1A!\020\021\00559UM\\'ba\032\0137\r^8ssB1\021\005 B!\005\013\0022\001\bB\"\t\031q\"Q\005b\001?A\031ADa\022\005\r%\022)C1\001 !\031QQC!\021\003F!1ah\003C\001\005\033*bAa\024\003V\teSC\001B)!\031QQCa\025\003XA\031AD!\026\005\ry\021YE1\001 !\ra\"\021\f\003\007S\t-#\031A\020\t\023\tu3\"!A\005\n\t}\023a\003:fC\022\024Vm]8mm\026$\"A!\031\021\t\t\r$\021N\007\003\005KRAAa\032\002\000\006!A.\0318h\023\021\021YG!\032\003\r=\023'.Z2u\001")
/*     */ public class LinkedHashMap<A, B> extends AbstractMap<A, B> implements Map<A, B>, MapLike<A, B, LinkedHashMap<A, B>>, HashTable<A, LinkedEntry<A, B>>, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private transient LinkedEntry<A, B> firstEntry;
/*     */   
/*     */   private transient LinkedEntry<A, B> lastEntry;
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
/*     */   public int _loadFactor() {
/*  48 */     return this._loadFactor;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void _loadFactor_$eq(int x$1) {
/*  48 */     this._loadFactor = x$1;
/*     */   }
/*     */   
/*     */   public HashEntry<A, LinkedEntry<A, B>>[] table() {
/*  48 */     return (HashEntry[])this.table;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void table_$eq(HashEntry[] x$1) {
/*  48 */     this.table = (HashEntry<Object, HashEntry>[])x$1;
/*     */   }
/*     */   
/*     */   public int tableSize() {
/*  48 */     return this.tableSize;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void tableSize_$eq(int x$1) {
/*  48 */     this.tableSize = x$1;
/*     */   }
/*     */   
/*     */   public int threshold() {
/*  48 */     return this.threshold;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void threshold_$eq(int x$1) {
/*  48 */     this.threshold = x$1;
/*     */   }
/*     */   
/*     */   public int[] sizemap() {
/*  48 */     return this.sizemap;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void sizemap_$eq(int[] x$1) {
/*  48 */     this.sizemap = x$1;
/*     */   }
/*     */   
/*     */   public int seedvalue() {
/*  48 */     return this.seedvalue;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void seedvalue_$eq(int x$1) {
/*  48 */     this.seedvalue = x$1;
/*     */   }
/*     */   
/*     */   public int tableSizeSeed() {
/*  48 */     return HashTable$class.tableSizeSeed(this);
/*     */   }
/*     */   
/*     */   public int initialSize() {
/*  48 */     return HashTable$class.initialSize(this);
/*     */   }
/*     */   
/*     */   public void init(ObjectInputStream in, Function0 readEntry) {
/*  48 */     HashTable$class.init(this, in, readEntry);
/*     */   }
/*     */   
/*     */   public void serializeTo(ObjectOutputStream out, Function1 writeEntry) {
/*  48 */     HashTable$class.serializeTo(this, out, writeEntry);
/*     */   }
/*     */   
/*     */   public LinkedEntry<A, B> findEntry(Object key) {
/*  48 */     return (LinkedEntry<A, B>)HashTable$class.findEntry(this, key);
/*     */   }
/*     */   
/*     */   public void addEntry(HashEntry e) {
/*  48 */     HashTable$class.addEntry(this, e);
/*     */   }
/*     */   
/*     */   public <B> LinkedEntry<A, B> findOrAddEntry(Object key, Object value) {
/*  48 */     return (LinkedEntry<A, B>)HashTable$class.findOrAddEntry(this, key, value);
/*     */   }
/*     */   
/*     */   public LinkedEntry<A, B> removeEntry(Object key) {
/*  48 */     return (LinkedEntry<A, B>)HashTable$class.removeEntry(this, key);
/*     */   }
/*     */   
/*     */   public Iterator<LinkedEntry<A, B>> entriesIterator() {
/*  48 */     return HashTable$class.entriesIterator(this);
/*     */   }
/*     */   
/*     */   public void clearTable() {
/*  48 */     HashTable$class.clearTable(this);
/*     */   }
/*     */   
/*     */   public void nnSizeMapAdd(int h) {
/*  48 */     HashTable$class.nnSizeMapAdd(this, h);
/*     */   }
/*     */   
/*     */   public void nnSizeMapRemove(int h) {
/*  48 */     HashTable$class.nnSizeMapRemove(this, h);
/*     */   }
/*     */   
/*     */   public void nnSizeMapReset(int tableLength) {
/*  48 */     HashTable$class.nnSizeMapReset(this, tableLength);
/*     */   }
/*     */   
/*     */   public final int totalSizeMapBuckets() {
/*  48 */     return HashTable$class.totalSizeMapBuckets(this);
/*     */   }
/*     */   
/*     */   public int calcSizeMapSize(int tableLength) {
/*  48 */     return HashTable$class.calcSizeMapSize(this, tableLength);
/*     */   }
/*     */   
/*     */   public void sizeMapInit(int tableLength) {
/*  48 */     HashTable$class.sizeMapInit(this, tableLength);
/*     */   }
/*     */   
/*     */   public void sizeMapInitAndRebuild() {
/*  48 */     HashTable$class.sizeMapInitAndRebuild(this);
/*     */   }
/*     */   
/*     */   public void printSizeMap() {
/*  48 */     HashTable$class.printSizeMap(this);
/*     */   }
/*     */   
/*     */   public void sizeMapDisable() {
/*  48 */     HashTable$class.sizeMapDisable(this);
/*     */   }
/*     */   
/*     */   public boolean isSizeMapDefined() {
/*  48 */     return HashTable$class.isSizeMapDefined(this);
/*     */   }
/*     */   
/*     */   public boolean alwaysInitSizeMap() {
/*  48 */     return HashTable$class.alwaysInitSizeMap(this);
/*     */   }
/*     */   
/*     */   public boolean elemEquals(Object key1, Object key2) {
/*  48 */     return HashTable$class.elemEquals(this, key1, key2);
/*     */   }
/*     */   
/*     */   public final int index(int hcode) {
/*  48 */     return HashTable$class.index(this, hcode);
/*     */   }
/*     */   
/*     */   public void initWithContents(HashTable.Contents c) {
/*  48 */     HashTable$class.initWithContents(this, c);
/*     */   }
/*     */   
/*     */   public HashTable.Contents<A, LinkedEntry<A, B>> hashTableContents() {
/*  48 */     return HashTable$class.hashTableContents(this);
/*     */   }
/*     */   
/*     */   public final int sizeMapBucketBitSize() {
/*  48 */     return HashTable.HashUtils$class.sizeMapBucketBitSize(this);
/*     */   }
/*     */   
/*     */   public final int sizeMapBucketSize() {
/*  48 */     return HashTable.HashUtils$class.sizeMapBucketSize(this);
/*     */   }
/*     */   
/*     */   public int elemHashCode(Object key) {
/*  48 */     return HashTable.HashUtils$class.elemHashCode(this, key);
/*     */   }
/*     */   
/*     */   public final int improve(int hcode, int seed) {
/*  48 */     return HashTable.HashUtils$class.improve(this, hcode, seed);
/*     */   }
/*     */   
/*     */   public LinkedHashMap() {
/*  48 */     HashTable.HashUtils$class.$init$(this);
/*  48 */     HashTable$class.$init$(this);
/*  60 */     this.firstEntry = null;
/*  61 */     this.lastEntry = null;
/*     */   }
/*     */   
/*     */   public LinkedHashMap<A, B> empty() {
/*     */     return LinkedHashMap$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public int size() {
/*     */     return tableSize();
/*     */   }
/*     */   
/*     */   public LinkedEntry<A, B> firstEntry() {
/*     */     return this.firstEntry;
/*     */   }
/*     */   
/*     */   public void firstEntry_$eq(LinkedEntry<A, B> x$1) {
/*     */     this.firstEntry = x$1;
/*     */   }
/*     */   
/*     */   public LinkedEntry<A, B> lastEntry() {
/*  61 */     return this.lastEntry;
/*     */   }
/*     */   
/*     */   public void lastEntry_$eq(LinkedEntry<A, B> x$1) {
/*  61 */     this.lastEntry = x$1;
/*     */   }
/*     */   
/*     */   public Option<B> get(Object key) {
/*  64 */     LinkedEntry<A, B> e = findEntry((A)key);
/*  65 */     return (e == null) ? (Option<B>)None$.MODULE$ : 
/*  66 */       (Option<B>)new Some(e.value());
/*     */   }
/*     */   
/*     */   public Option<B> put(Object key, Object value) {
/*  70 */     LinkedEntry<Object, Object> e = findOrAddEntry(key, value);
/*  72 */     Object v = e.value();
/*  72 */     e.value_$eq(value);
/*  72 */     return (e == null) ? (Option<B>)None$.MODULE$ : (Option<B>)new Some(v);
/*     */   }
/*     */   
/*     */   public Option<B> remove(Object key) {
/*  76 */     LinkedEntry<A, B> e = removeEntry((A)key);
/*  79 */     if (e.earlier() == null) {
/*  79 */       firstEntry_$eq(e.later());
/*     */     } else {
/*  80 */       e.earlier().later_$eq(e.later());
/*     */     } 
/*  81 */     if (e.later() == null) {
/*  81 */       lastEntry_$eq(e.earlier());
/*     */     } else {
/*  82 */       e.later().earlier_$eq(e.earlier());
/*     */     } 
/*  83 */     return (e == null) ? (Option<B>)None$.MODULE$ : (Option<B>)new Some(e.value());
/*     */   }
/*     */   
/*     */   public LinkedHashMap<A, B> $plus$eq(Tuple2 kv) {
/*  87 */     put((A)kv._1(), (B)kv._2());
/*  87 */     return this;
/*     */   }
/*     */   
/*     */   public LinkedHashMap<A, B> $minus$eq(Object key) {
/*  88 */     remove((A)key);
/*  88 */     return this;
/*     */   }
/*     */   
/*     */   public Iterator<Tuple2<A, B>> iterator() {
/*  90 */     return (Iterator<Tuple2<A, B>>)new LinkedHashMap$$anon$1(this);
/*     */   }
/*     */   
/*     */   public class LinkedHashMap$$anon$1 extends AbstractIterator<Tuple2<A, B>> {
/*     */     private LinkedEntry<A, B> cur;
/*     */     
/*     */     public LinkedHashMap$$anon$1(LinkedHashMap<A, B> $outer) {
/*  91 */       this.cur = $outer.firstEntry();
/*     */     }
/*     */     
/*     */     private LinkedEntry<A, B> cur() {
/*  91 */       return this.cur;
/*     */     }
/*     */     
/*     */     private void cur_$eq(LinkedEntry<A, B> x$1) {
/*  91 */       this.cur = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*  92 */       return (cur() != null);
/*     */     }
/*     */     
/*     */     public Tuple2<A, B> next() {
/*  94 */       Tuple2<A, B> res = new Tuple2(cur().key(), cur().value());
/*  94 */       cur_$eq(cur().later());
/*  94 */       return hasNext() ? res : 
/*  95 */         (Tuple2<A, B>)Iterator$.MODULE$.empty().next();
/*     */     }
/*     */   }
/*     */   
/*     */   public class FilteredKeys extends MapLike<A, B, LinkedHashMap<A, B>>.FilteredKeys {
/*     */     public FilteredKeys(LinkedHashMap $outer, Function1 p) {
/*  98 */       super($outer, p);
/*     */     }
/*     */     
/*     */     public LinkedHashMap<A, Nothing$> empty() {
/*  99 */       return LinkedHashMap$.MODULE$.empty();
/*     */     }
/*     */   }
/*     */   
/*     */   public Map<A, B> filterKeys(Function1<A, Object> p) {
/* 102 */     return (Map<A, B>)new FilteredKeys(this, p);
/*     */   }
/*     */   
/*     */   public class MappedValues<C> extends MapLike<A, B, LinkedHashMap<A, B>>.MappedValues<C> {
/*     */     public MappedValues(LinkedHashMap $outer, Function1 f) {
/* 104 */       super($outer, f);
/*     */     }
/*     */     
/*     */     public LinkedHashMap<A, Nothing$> empty() {
/* 105 */       return LinkedHashMap$.MODULE$.empty();
/*     */     }
/*     */   }
/*     */   
/*     */   public <C> Map<A, C> mapValues(Function1<B, ?> f) {
/* 108 */     return (Map<A, C>)new MappedValues(this, f);
/*     */   }
/*     */   
/*     */   public class DefaultKeySet extends MapLike<A, B, LinkedHashMap<A, B>>.DefaultKeySet {
/*     */     public DefaultKeySet(LinkedHashMap $outer) {
/* 110 */       super($outer);
/*     */     }
/*     */     
/*     */     public LinkedHashSet<A> empty() {
/* 111 */       return LinkedHashSet$.MODULE$.empty();
/*     */     }
/*     */   }
/*     */   
/*     */   public Set<A> keySet() {
/* 114 */     return (Set<A>)new DefaultKeySet(this);
/*     */   }
/*     */   
/*     */   public Iterator<A> keysIterator() {
/* 116 */     return (Iterator<A>)new LinkedHashMap$$anon$2(this);
/*     */   }
/*     */   
/*     */   public class LinkedHashMap$$anon$2 extends AbstractIterator<A> {
/*     */     private LinkedEntry<A, B> cur;
/*     */     
/*     */     public LinkedHashMap$$anon$2(LinkedHashMap<A, B> $outer) {
/* 117 */       this.cur = $outer.firstEntry();
/*     */     }
/*     */     
/*     */     private LinkedEntry<A, B> cur() {
/* 117 */       return this.cur;
/*     */     }
/*     */     
/*     */     private void cur_$eq(LinkedEntry<A, B> x$1) {
/* 117 */       this.cur = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 118 */       return (cur() != null);
/*     */     }
/*     */     
/*     */     public A next() {
/* 120 */       Object res = cur().key();
/* 120 */       cur_$eq(cur().later());
/* 120 */       return hasNext() ? (A)res : 
/* 121 */         (A)Iterator$.MODULE$.empty().next();
/*     */     }
/*     */   }
/*     */   
/*     */   public Iterator<B> valuesIterator() {
/* 124 */     return (Iterator<B>)new LinkedHashMap$$anon$3(this);
/*     */   }
/*     */   
/*     */   public class LinkedHashMap$$anon$3 extends AbstractIterator<B> {
/*     */     private LinkedEntry<A, B> cur;
/*     */     
/*     */     public LinkedHashMap$$anon$3(LinkedHashMap<A, B> $outer) {
/* 125 */       this.cur = $outer.firstEntry();
/*     */     }
/*     */     
/*     */     private LinkedEntry<A, B> cur() {
/* 125 */       return this.cur;
/*     */     }
/*     */     
/*     */     private void cur_$eq(LinkedEntry<A, B> x$1) {
/* 125 */       this.cur = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 126 */       return (cur() != null);
/*     */     }
/*     */     
/*     */     public B next() {
/* 128 */       Object res = cur().value();
/* 128 */       cur_$eq(cur().later());
/* 128 */       return hasNext() ? (B)res : 
/* 129 */         (B)Iterator$.MODULE$.empty().next();
/*     */     }
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/* 133 */     LinkedEntry<A, B> cur = firstEntry();
/* 134 */     while (cur != null) {
/* 135 */       f.apply(new Tuple2(cur.key(), cur.value()));
/* 136 */       cur = cur.later();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <U> void foreachEntry(Function1 f) {
/* 141 */     LinkedEntry<A, B> cur = firstEntry();
/* 142 */     while (cur != null) {
/* 143 */       f.apply(cur);
/* 144 */       cur = cur.later();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <B1> LinkedEntry<A, B> createNewEntry(Object key, Object value) {
/* 149 */     LinkedEntry<Object, Object> e = new LinkedEntry<Object, Object>(key, value);
/* 150 */     if (firstEntry() == null) {
/* 150 */       firstEntry_$eq((LinkedEntry)e);
/*     */     } else {
/* 151 */       lastEntry().later_$eq((LinkedEntry)e);
/* 151 */       e.earlier_$eq((LinkedEntry)lastEntry());
/*     */     } 
/* 152 */     lastEntry_$eq((LinkedEntry)e);
/* 153 */     return (LinkedEntry)e;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 157 */     clearTable();
/* 158 */     firstEntry_$eq((LinkedEntry<A, B>)null);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) {
/* 162 */     serializeTo(out, (Function1<LinkedEntry<A, B>, BoxedUnit>)new LinkedHashMap$$anonfun$writeObject$1(this, (LinkedHashMap<A, B>)out));
/*     */   }
/*     */   
/*     */   public class LinkedHashMap$$anonfun$writeObject$1 extends AbstractFunction1<LinkedEntry<A, B>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectOutputStream out$1;
/*     */     
/*     */     public LinkedHashMap$$anonfun$writeObject$1(LinkedHashMap $outer, ObjectOutputStream out$1) {}
/*     */     
/*     */     public final void apply(LinkedEntry entry) {
/* 163 */       this.out$1.writeObject(entry.key());
/* 164 */       this.out$1.writeObject(entry.value());
/*     */     }
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) {
/* 169 */     firstEntry_$eq((LinkedEntry<A, B>)null);
/* 170 */     lastEntry_$eq((LinkedEntry<A, B>)null);
/* 171 */     init(in, (Function0<LinkedEntry<A, B>>)new LinkedHashMap$$anonfun$readObject$1(this, (LinkedHashMap<A, B>)in));
/*     */   }
/*     */   
/*     */   public static <A, B> CanBuildFrom<LinkedHashMap<?, ?>, Tuple2<A, B>, LinkedHashMap<A, B>> canBuildFrom() {
/*     */     return LinkedHashMap$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public class LinkedHashMap$$anonfun$readObject$1 extends AbstractFunction0<LinkedEntry<A, B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectInputStream in$1;
/*     */     
/*     */     public final LinkedEntry<A, B> apply() {
/* 171 */       return this.$outer.createNewEntry((A)this.in$1.readObject(), this.in$1.readObject());
/*     */     }
/*     */     
/*     */     public LinkedHashMap$$anonfun$readObject$1(LinkedHashMap $outer, ObjectInputStream in$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\LinkedHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */