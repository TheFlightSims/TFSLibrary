/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Iterator$;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericSetTemplate;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.generic.Shrinkable;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t-a\001B\001\003\001%\021Q\002T5oW\026$\007*Y:i'\026$(BA\002\005\003\035iW\017^1cY\026T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)\"AC\t\024\017\001Y1DH\023*mA\031A\"D\b\016\003\tI!A\004\002\003\027\005\0237\017\036:bGR\034V\r\036\t\003!Ea\001\001B\003\023\001\t\0071CA\001B#\t!\002\004\005\002\026-5\ta!\003\002\030\r\t9aj\034;iS:<\007CA\013\032\023\tQbAA\002B]f\0042\001\004\017\020\023\ti\"AA\002TKR\004Ba\b\022\020I5\t\001E\003\002\"\t\0059q-\0328fe&\034\027BA\022!\005I9UM\\3sS\016\034V\r\036+f[Bd\027\r^3\021\0051\001\001\003\002\007'\037!J!a\n\002\003\017M+G\017T5lKB\031A\002A\b\021\t1Qs\002L\005\003W\t\021\021\002S1tQR\013'\r\\3\021\0075\032vB\004\002\r]\035)qF\001E\001a\005iA*\0338lK\022D\025m\0355TKR\004\"\001D\031\007\013\005\021\001\022\001\032\024\007E\032d\007E\002 i\021J!!\016\021\003#5+H/\0312mKN+GOR1di>\024\030\020\005\002\026o%\021\001H\002\002\r'\026\024\030.\0317ju\006\024G.\032\005\006uE\"\taO\001\007y%t\027\016\036 \025\003ABQ!P\031\005\004y\nAbY1o\005VLG\016\032$s_6,\"a\020&\026\003\001\003RaH!D\023.K!A\021\021\003\031\r\013gNQ;jY\0224%o\\7\021\005\021+U\"A\031\n\005\031;%\001B\"pY2L!\001\023\021\003!\035+g.\032:jG\016{W\016]1oS>t\007C\001\tK\t\025\021BH1\001\024!\ra\001!\023\005\006\033F\"\tET\001\006K6\004H/_\013\003\037J+\022\001\025\t\004\031\001\t\006C\001\tS\t\025\021BJ1\001\024\r\025!\026G\001\004V\005\025)e\016\036:z+\t1fl\005\003T/j3\004CA\013Y\023\tIfA\001\004B]f\024VM\032\t\005\031mkv,\003\002]\005\tI\001*Y:i\013:$(/\037\t\003!y#QAE*C\002M\0012\001R*^\021!\t7K!b\001\n\003\021\027aA6fsV\tQ\f\003\005e'\n\005\t\025!\003^\003\021YW-\037\021\t\013i\032F\021\0014\025\005};\007\"B1f\001\004i\006bB5T\001\004%\tA[\001\bK\006\024H.[3s+\005y\006b\0027T\001\004%\t!\\\001\fK\006\024H.[3s?\022*\027\017\006\002ocB\021Qc\\\005\003a\032\021A!\0268ji\"9!o[A\001\002\004y\026a\001=%c!1Ao\025Q!\n}\013\001\"Z1sY&,'\017\t\005\bmN\003\r\021\"\001k\003\025a\027\r^3s\021\035A8\0131A\005\002e\f\021\002\\1uKJ|F%Z9\025\0059T\bb\002:x\003\003\005\ra\030\005\007yN\003\013\025B0\002\r1\fG/\032:!\021\035q\030'!A\005\n}\f1B]3bIJ+7o\0347wKR\021\021\021\001\t\005\003\007\ti!\004\002\002\006)!\021qAA\005\003\021a\027M\\4\013\005\005-\021\001\0026bm\006LA!a\004\002\006\t1qJ\0316fGRDaA\017\001\005\002\005MA#\001\025\t\017\005]\001\001\"\021\002\032\005I1m\\7qC:LwN\\\013\003\0037\0012aH$%\013\021!\006\001\001\027\t\023\005\005\002\0011A\005\022\005\r\022A\0034jeN$XI\034;ssV\021\021Q\005\t\005\003O\ti\"D\001\001\021%\tY\003\001a\001\n#\ti#\001\bgSJ\034H/\0228uef|F%Z9\025\0079\fy\003C\005s\003S\t\t\0211\001\002&!A\0211\007\001!B\023\t)#A\006gSJ\034H/\0228uef\004\003\006BA\031\003o\0012!FA\035\023\r\tYD\002\002\niJ\fgn]5f]RD\021\"a\020\001\001\004%\t\"a\t\002\0231\f7\017^#oiJL\b\"CA\"\001\001\007I\021CA#\0035a\027m\035;F]R\024\030p\030\023fcR\031a.a\022\t\023I\f\t%!AA\002\005\025\002\002CA&\001\001\006K!!\n\002\0251\f7\017^#oiJL\b\005\013\003\002J\005]\002bBA)\001\021\005\0231K\001\005g&TX-\006\002\002VA\031Q#a\026\n\007\005ecAA\002J]RDq!!\030\001\t\003\ty&\001\005d_:$\030-\0338t)\021\t\t'a\032\021\007U\t\031'C\002\002f\031\021qAQ8pY\026\fg\016C\004\002j\005m\003\031A\b\002\t\025dW-\034\005\b\003[\002A\021AA8\003!!\003\017\\;tI\025\fH\003BA\024\003cBq!!\033\002l\001\007q\002C\004\002v\001!\t!a\036\002\023\021j\027N\\;tI\025\fH\003BA\024\003sBq!!\033\002t\001\007q\002C\004\002~\001!\t%a \002\007\005$G\r\006\003\002b\005\005\005bBA5\003w\002\ra\004\005\b\003\013\003A\021IAD\003\031\021X-\\8wKR!\021\021MAE\021\035\tI'a!A\002=Aq!!$\001\t\003\ty)\001\005ji\026\024\030\r^8s+\t\t\t\nE\003\002\024\006Uu\"D\001\005\023\r\t9\n\002\002\t\023R,'/\031;pe\"9\0211\024\001\005B\005u\025a\0024pe\026\f7\r[\013\005\003?\013i\013F\002o\003CC\001\"a)\002\032\002\007\021QU\001\002MB1Q#a*\020\003WK1!!+\007\005%1UO\\2uS>t\027\007E\002\021\003[#q!a,\002\032\n\0071CA\001V\021\035\t\031\f\001C)\003k\013ABZ8sK\006\034\007.\0228uef,B!a.\002@R\031a.!/\t\021\005\r\026\021\027a\001\003w\003r!FAT\003K\ti\fE\002\021\003#q!a,\0022\n\0071\003C\004\002D\002!\t\"!2\002\035\r\024X-\031;f\035\026<XI\034;ssV!\021qYAi)\031\t)#!3\002L\"1\021-!1A\002=A\001\"!4\002B\002\007\021qZ\001\006IVlW.\037\t\004!\005EGaBAj\003\003\024\ra\005\002\002\005\"9\021q\033\001\005B\005e\027!B2mK\006\024H#\0018\t\017\005u\007\001\"\003\002`\006YqO]5uK>\023'.Z2u)\rq\027\021\035\005\t\003G\fY\0161\001\002f\006\031q.\036;\021\t\005\035\030Q^\007\003\003STA!a;\002\n\005\021\021n\\\005\005\003_\fIO\001\nPE*,7\r^(viB,Ho\025;sK\006l\007bBAz\001\021%\021Q_\001\013e\026\fGm\0242kK\016$Hc\0018\002x\"A\021\021`Ay\001\004\tY0\001\002j]B!\021q]A\023\021\ty0!;\003#=\023'.Z2u\023:\004X\017^*ue\026\fW\016K\003\001\005\007\021I\001E\002\026\005\013I1Aa\002\007\005A\031VM]5bYZ+'o]5p]VKEIH\001\002\001")
/*     */ public class LinkedHashSet<A> extends AbstractSet<A> implements Set<A>, GenericSetTemplate<A, LinkedHashSet>, SetLike<A, LinkedHashSet<A>>, HashTable<A, LinkedHashSet.Entry<A>>, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private transient Entry<A> firstEntry;
/*     */   
/*     */   private transient Entry<A> lastEntry;
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
/*  41 */     return this._loadFactor;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void _loadFactor_$eq(int x$1) {
/*  41 */     this._loadFactor = x$1;
/*     */   }
/*     */   
/*     */   public HashEntry<A, Entry<A>>[] table() {
/*  41 */     return (HashEntry[])this.table;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void table_$eq(HashEntry[] x$1) {
/*  41 */     this.table = (HashEntry<Object, HashEntry>[])x$1;
/*     */   }
/*     */   
/*     */   public int tableSize() {
/*  41 */     return this.tableSize;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void tableSize_$eq(int x$1) {
/*  41 */     this.tableSize = x$1;
/*     */   }
/*     */   
/*     */   public int threshold() {
/*  41 */     return this.threshold;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void threshold_$eq(int x$1) {
/*  41 */     this.threshold = x$1;
/*     */   }
/*     */   
/*     */   public int[] sizemap() {
/*  41 */     return this.sizemap;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void sizemap_$eq(int[] x$1) {
/*  41 */     this.sizemap = x$1;
/*     */   }
/*     */   
/*     */   public int seedvalue() {
/*  41 */     return this.seedvalue;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void seedvalue_$eq(int x$1) {
/*  41 */     this.seedvalue = x$1;
/*     */   }
/*     */   
/*     */   public int tableSizeSeed() {
/*  41 */     return HashTable$class.tableSizeSeed(this);
/*     */   }
/*     */   
/*     */   public int initialSize() {
/*  41 */     return HashTable$class.initialSize(this);
/*     */   }
/*     */   
/*     */   public void init(ObjectInputStream in, Function0 readEntry) {
/*  41 */     HashTable$class.init(this, in, readEntry);
/*     */   }
/*     */   
/*     */   public void serializeTo(ObjectOutputStream out, Function1 writeEntry) {
/*  41 */     HashTable$class.serializeTo(this, out, writeEntry);
/*     */   }
/*     */   
/*     */   public Entry<A> findEntry(Object key) {
/*  41 */     return (Entry<A>)HashTable$class.findEntry(this, key);
/*     */   }
/*     */   
/*     */   public void addEntry(HashEntry e) {
/*  41 */     HashTable$class.addEntry(this, e);
/*     */   }
/*     */   
/*     */   public <B> Entry<A> findOrAddEntry(Object key, Object value) {
/*  41 */     return (Entry<A>)HashTable$class.findOrAddEntry(this, key, value);
/*     */   }
/*     */   
/*     */   public Entry<A> removeEntry(Object key) {
/*  41 */     return (Entry<A>)HashTable$class.removeEntry(this, key);
/*     */   }
/*     */   
/*     */   public Iterator<Entry<A>> entriesIterator() {
/*  41 */     return HashTable$class.entriesIterator(this);
/*     */   }
/*     */   
/*     */   public void clearTable() {
/*  41 */     HashTable$class.clearTable(this);
/*     */   }
/*     */   
/*     */   public void nnSizeMapAdd(int h) {
/*  41 */     HashTable$class.nnSizeMapAdd(this, h);
/*     */   }
/*     */   
/*     */   public void nnSizeMapRemove(int h) {
/*  41 */     HashTable$class.nnSizeMapRemove(this, h);
/*     */   }
/*     */   
/*     */   public void nnSizeMapReset(int tableLength) {
/*  41 */     HashTable$class.nnSizeMapReset(this, tableLength);
/*     */   }
/*     */   
/*     */   public final int totalSizeMapBuckets() {
/*  41 */     return HashTable$class.totalSizeMapBuckets(this);
/*     */   }
/*     */   
/*     */   public int calcSizeMapSize(int tableLength) {
/*  41 */     return HashTable$class.calcSizeMapSize(this, tableLength);
/*     */   }
/*     */   
/*     */   public void sizeMapInit(int tableLength) {
/*  41 */     HashTable$class.sizeMapInit(this, tableLength);
/*     */   }
/*     */   
/*     */   public void sizeMapInitAndRebuild() {
/*  41 */     HashTable$class.sizeMapInitAndRebuild(this);
/*     */   }
/*     */   
/*     */   public void printSizeMap() {
/*  41 */     HashTable$class.printSizeMap(this);
/*     */   }
/*     */   
/*     */   public void sizeMapDisable() {
/*  41 */     HashTable$class.sizeMapDisable(this);
/*     */   }
/*     */   
/*     */   public boolean isSizeMapDefined() {
/*  41 */     return HashTable$class.isSizeMapDefined(this);
/*     */   }
/*     */   
/*     */   public boolean alwaysInitSizeMap() {
/*  41 */     return HashTable$class.alwaysInitSizeMap(this);
/*     */   }
/*     */   
/*     */   public boolean elemEquals(Object key1, Object key2) {
/*  41 */     return HashTable$class.elemEquals(this, key1, key2);
/*     */   }
/*     */   
/*     */   public final int index(int hcode) {
/*  41 */     return HashTable$class.index(this, hcode);
/*     */   }
/*     */   
/*     */   public void initWithContents(HashTable.Contents c) {
/*  41 */     HashTable$class.initWithContents(this, c);
/*     */   }
/*     */   
/*     */   public HashTable.Contents<A, Entry<A>> hashTableContents() {
/*  41 */     return HashTable$class.hashTableContents(this);
/*     */   }
/*     */   
/*     */   public final int sizeMapBucketBitSize() {
/*  41 */     return HashTable.HashUtils$class.sizeMapBucketBitSize(this);
/*     */   }
/*     */   
/*     */   public final int sizeMapBucketSize() {
/*  41 */     return HashTable.HashUtils$class.sizeMapBucketSize(this);
/*     */   }
/*     */   
/*     */   public int elemHashCode(Object key) {
/*  41 */     return HashTable.HashUtils$class.elemHashCode(this, key);
/*     */   }
/*     */   
/*     */   public final int improve(int hcode, int seed) {
/*  41 */     return HashTable.HashUtils$class.improve(this, hcode, seed);
/*     */   }
/*     */   
/*     */   public LinkedHashSet() {
/*  41 */     HashTable.HashUtils$class.$init$(this);
/*  41 */     HashTable$class.$init$(this);
/*  52 */     this.firstEntry = null;
/*  53 */     this.lastEntry = null;
/*     */   }
/*     */   
/*     */   public GenericCompanion<LinkedHashSet> companion() {
/*     */     return (GenericCompanion<LinkedHashSet>)LinkedHashSet$.MODULE$;
/*     */   }
/*     */   
/*     */   public Entry<A> firstEntry() {
/*     */     return this.firstEntry;
/*     */   }
/*     */   
/*     */   public void firstEntry_$eq(Entry<A> x$1) {
/*     */     this.firstEntry = x$1;
/*     */   }
/*     */   
/*     */   public Entry<A> lastEntry() {
/*  53 */     return this.lastEntry;
/*     */   }
/*     */   
/*     */   public void lastEntry_$eq(Entry<A> x$1) {
/*  53 */     this.lastEntry = x$1;
/*     */   }
/*     */   
/*     */   public int size() {
/*  55 */     return tableSize();
/*     */   }
/*     */   
/*     */   public boolean contains(Object elem) {
/*  57 */     return (findEntry((A)elem) != null);
/*     */   }
/*     */   
/*     */   public LinkedHashSet<A> $plus$eq(Object elem) {
/*  59 */     add((A)elem);
/*  59 */     return this;
/*     */   }
/*     */   
/*     */   public LinkedHashSet<A> $minus$eq(Object elem) {
/*  60 */     remove((A)elem);
/*  60 */     return this;
/*     */   }
/*     */   
/*     */   public boolean add(Object elem) {
/*  62 */     return (findOrAddEntry(elem, (Object)null) == null);
/*     */   }
/*     */   
/*     */   public boolean remove(Object elem) {
/*  65 */     Entry<A> e = removeEntry((A)elem);
/*  68 */     if (e.earlier() == null) {
/*  68 */       firstEntry_$eq(e.later());
/*     */     } else {
/*  69 */       e.earlier().later_$eq(e.later());
/*     */     } 
/*  70 */     if (e.later() == null) {
/*  70 */       lastEntry_$eq(e.earlier());
/*     */     } else {
/*  71 */       e.later().earlier_$eq(e.earlier());
/*     */     } 
/*     */     return !(e == null);
/*     */   }
/*     */   
/*     */   public Iterator<A> iterator() {
/*  76 */     return (Iterator<A>)new LinkedHashSet$$anon$1(this);
/*     */   }
/*     */   
/*     */   public class LinkedHashSet$$anon$1 extends AbstractIterator<A> {
/*     */     private LinkedHashSet.Entry<A> cur;
/*     */     
/*     */     public LinkedHashSet$$anon$1(LinkedHashSet<A> $outer) {
/*  77 */       this.cur = $outer.firstEntry();
/*     */     }
/*     */     
/*     */     private LinkedHashSet.Entry<A> cur() {
/*  77 */       return this.cur;
/*     */     }
/*     */     
/*     */     private void cur_$eq(LinkedHashSet.Entry<A> x$1) {
/*  77 */       this.cur = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*  78 */       return (cur() != null);
/*     */     }
/*     */     
/*     */     public A next() {
/*  80 */       Object res = cur().key();
/*  80 */       cur_$eq(cur().later());
/*  80 */       return hasNext() ? (A)res : 
/*  81 */         (A)Iterator$.MODULE$.empty().next();
/*     */     }
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/*  85 */     Entry<A> cur = firstEntry();
/*  86 */     while (cur != null) {
/*  87 */       f.apply(cur.key());
/*  88 */       cur = cur.later();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <U> void foreachEntry(Function1 f) {
/*  93 */     Entry<A> cur = firstEntry();
/*  94 */     while (cur != null) {
/*  95 */       f.apply(cur);
/*  96 */       cur = cur.later();
/*     */     } 
/*     */   }
/*     */   
/*     */   public <B> Entry<A> createNewEntry(Object key, Object dummy) {
/* 101 */     Entry<A> e = new Entry(key);
/* 102 */     if (firstEntry() == null) {
/* 102 */       firstEntry_$eq(e);
/*     */     } else {
/* 103 */       lastEntry().later_$eq(e);
/* 103 */       e.earlier_$eq(lastEntry());
/*     */     } 
/* 104 */     lastEntry_$eq(e);
/* 105 */     return e;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 109 */     clearTable();
/* 110 */     firstEntry_$eq((Entry<A>)null);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) {
/* 114 */     serializeTo(out, (Function1<Entry<A>, BoxedUnit>)new LinkedHashSet$$anonfun$writeObject$1(this, (LinkedHashSet<A>)out));
/*     */   }
/*     */   
/*     */   public class LinkedHashSet$$anonfun$writeObject$1 extends AbstractFunction1<Entry<A>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectOutputStream out$1;
/*     */     
/*     */     public final void apply(LinkedHashSet.Entry e) {
/* 114 */       this.out$1.writeObject(e.key());
/*     */     }
/*     */     
/*     */     public LinkedHashSet$$anonfun$writeObject$1(LinkedHashSet $outer, ObjectOutputStream out$1) {}
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) {
/* 118 */     firstEntry_$eq((Entry<A>)null);
/* 119 */     lastEntry_$eq((Entry<A>)null);
/* 120 */     init(in, (Function0<Entry<A>>)new LinkedHashSet$$anonfun$readObject$1(this, (LinkedHashSet<A>)in));
/*     */   }
/*     */   
/*     */   public static <A> CanBuildFrom<LinkedHashSet<?>, A, LinkedHashSet<A>> canBuildFrom() {
/*     */     return LinkedHashSet$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static <A> Object setCanBuildFrom() {
/*     */     return LinkedHashSet$.MODULE$.setCanBuildFrom();
/*     */   }
/*     */   
/*     */   public class LinkedHashSet$$anonfun$readObject$1 extends AbstractFunction0<Entry<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectInputStream in$1;
/*     */     
/*     */     public final LinkedHashSet.Entry<A> apply() {
/* 120 */       return this.$outer.createNewEntry(this.in$1.readObject(), (Object)null);
/*     */     }
/*     */     
/*     */     public LinkedHashSet$$anonfun$readObject$1(LinkedHashSet $outer, ObjectInputStream in$1) {}
/*     */   }
/*     */   
/*     */   public static class Entry<A> implements HashEntry<A, Entry<A>>, Serializable {
/*     */     private final A key;
/*     */     
/*     */     private Entry<A> earlier;
/*     */     
/*     */     private Entry<A> later;
/*     */     
/*     */     private Object next;
/*     */     
/*     */     public Entry<A> next() {
/* 135 */       return (Entry<A>)this.next;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void next_$eq(Object x$1) {
/* 135 */       this.next = x$1;
/*     */     }
/*     */     
/*     */     public A key() {
/* 135 */       return this.key;
/*     */     }
/*     */     
/*     */     public Entry(Object key) {
/* 135 */       HashEntry$class.$init$(this);
/* 136 */       this.earlier = null;
/* 137 */       this.later = null;
/*     */     }
/*     */     
/*     */     public Entry<A> earlier() {
/*     */       return this.earlier;
/*     */     }
/*     */     
/*     */     public void earlier_$eq(Entry<A> x$1) {
/*     */       this.earlier = x$1;
/*     */     }
/*     */     
/*     */     public Entry<A> later() {
/* 137 */       return this.later;
/*     */     }
/*     */     
/*     */     public void later_$eq(Entry<A> x$1) {
/* 137 */       this.later = x$1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\LinkedHashSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */