/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.collection.CustomParallelizable;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Parallel;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Set;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.GenericSetTemplate;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.generic.Shrinkable;
/*    */ import scala.collection.generic.Subtractable;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.mutable.ParHashSet;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\025f\001B\001\003\001%\021q\001S1tQN+GO\003\002\004\t\0059Q.\036;bE2,'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001QC\001\006\022'!\0011b\007\020&S1:\004c\001\007\016\0375\t!!\003\002\017\005\tY\021IY:ue\006\034GoU3u!\t\001\022\003\004\001\005\013I\001!\031A\n\003\003\005\013\"\001\006\r\021\005U1R\"\001\004\n\005]1!a\002(pi\"Lgn\032\t\003+eI!A\007\004\003\007\005s\027\020E\002\r9=I!!\b\002\003\007M+G\017\005\003 E=!S\"\001\021\013\005\005\"\021aB4f]\026\024\030nY\005\003G\001\022!cR3oKJL7mU3u)\026l\007\017\\1uKB\021A\002\001\t\005\031\031z\001&\003\002(\005\t91+\032;MS.,\007c\001\007\001\037A\031ABK\b\n\005-\022!!\004$mCRD\025m\0355UC\ndW\r\005\003.]=\001T\"\001\003\n\005=\"!\001F\"vgR|W\016U1sC2dW\r\\5{C\ndW\rE\0022k=i\021A\r\006\003\007MR!\001\016\003\002\021A\f'/\0317mK2L!A\016\032\003\025A\013'\017S1tQN+G\017\005\002\026q%\021\021H\002\002\r'\026\024\030.\0317ju\006\024G.\032\005\tw\001\021\t\021)A\005y\005A1m\0348uK:$8\017E\002>\001>q!\001\004 \n\005}\022\021!\004$mCRD\025m\0355UC\ndW-\003\002B\005\nA1i\0348uK:$8O\003\002@\005!1A\t\001C\001\t\025\013a\001P5oSRtDC\001\025G\021\025Y4\t1\001=\021\025!\005\001\"\001I)\005A\003\"\002&\001\t\003Z\025!C2p[B\fg.[8o+\005a\005cA\020NI%\021a\n\t\002\021\017\026tWM]5d\007>l\007/\0318j_:DQ\001\025\001\005BE\013Aa]5{KV\t!\013\005\002\026'&\021AK\002\002\004\023:$\b\"\002,\001\t\0039\026\001C2p]R\f\027N\\:\025\005a[\006CA\013Z\023\tQfAA\004C_>dW-\0318\t\013q+\006\031A\b\002\t\025dW-\034\005\006=\002!\taX\001\tIAdWo\035\023fcR\021\001-Y\007\002\001!)A,\030a\001\037!)1\r\001C\001I\006IA%\\5okN$S-\035\013\003A\026DQ\001\0302A\002=AQa\032\001\005B!\f1\001]1s+\005\001\004\"\0026\001\t\003Z\027aA1eIR\021\001\f\034\005\0069&\004\ra\004\005\006]\002!\te\\\001\007e\026lwN^3\025\005a\003\b\"\002/n\001\004y\001\"\002:\001\t\003\032\030!B2mK\006\024H#\001;\021\005U)\030B\001<\007\005\021)f.\033;\t\013a\004A\021I=\002\021%$XM]1u_J,\022A\037\t\004[m|\021B\001?\005\005!IE/\032:bi>\024\b\"\002@\001\t\003z\030a\0024pe\026\f7\r[\013\005\003\003\ty\001F\002u\003\007Aq!!\002~\001\004\t9!A\001g!\031)\022\021B\b\002\016%\031\0211\002\004\003\023\031+hn\031;j_:\f\004c\001\t\002\020\0211\021\021C?C\002M\021\021!\026\005\007\003+\001A\021\t%\002\013\rdwN\\3\t\017\005e\001\001\"\003\002\034\005YqO]5uK>\023'.Z2u)\r!\030Q\004\005\t\003?\t9\0021\001\002\"\005\t1\017\005\003\002$\0055RBAA\023\025\021\t9#!\013\002\005%|'BAA\026\003\021Q\027M^1\n\t\005=\022Q\005\002\023\037\nTWm\031;PkR\004X\017^*ue\026\fW\016C\004\0024\001!I!!\016\002\025I,\027\rZ(cU\026\034G\017F\002u\003oA\001\"!\017\0022\001\007\0211H\001\003S:\004B!a\t\002>%!\021qHA\023\005Ey%M[3di&s\007/\036;TiJ,\027-\034\005\b\003\007\002A\021AA#\003))8/Z*ju\026l\025\r\035\013\004i\006\035\003bBA%\003\003\002\r\001W\001\002i\"*\001!!\024\002TA\031Q#a\024\n\007\005EcA\001\tTKJL\027\r\034,feNLwN\\+J\tz\t\021aB\004\002X\tA\t!!\027\002\017!\0137\017[*fiB\031A\"a\027\007\r\005\021\001\022AA/'\025\tY&a\0308!\021y\022\021\r\023\n\007\005\r\004EA\tNkR\f'\r\\3TKR4\025m\031;pefDq\001RA.\t\003\t9\007\006\002\002Z!A\0211NA.\t\007\ti'\001\007dC:\024U/\0337e\rJ|W.\006\003\002p\005\005UCAA9!%y\0221OA<\003\n\031)C\002\002v\001\022AbQ1o\005VLG\016\032$s_6\004B!!\037\002|5\021\0211L\005\004\003{j%\001B\"pY2\0042\001EAA\t\031\021\022\021\016b\001'A!A\002AA@\021!\t9)a\027\005B\005%\025!B3naRLX\003BAF\003#+\"!!$\021\t1\001\021q\022\t\004!\005EEA\002\n\002\006\n\0071\003\003\006\002\026\006m\023\021!C\005\003/\0131B]3bIJ+7o\0347wKR\021\021\021\024\t\005\0037\013\t+\004\002\002\036*!\021qTA\025\003\021a\027M\\4\n\t\005\r\026Q\024\002\007\037\nTWm\031;")
/*    */ public class HashSet<A> extends AbstractSet<A> implements Set<A>, GenericSetTemplate<A, HashSet>, SetLike<A, HashSet<A>>, FlatHashTable<A>, CustomParallelizable<A, ParHashSet<A>>, Serializable {
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   private transient int _loadFactor;
/*    */   
/*    */   private transient Object[] table;
/*    */   
/*    */   private transient int tableSize;
/*    */   
/*    */   private transient int threshold;
/*    */   
/*    */   private transient int[] sizemap;
/*    */   
/*    */   private transient int seedvalue;
/*    */   
/*    */   public Combiner<A, ParHashSet<A>> parCombiner() {
/* 41 */     return CustomParallelizable.class.parCombiner(this);
/*    */   }
/*    */   
/*    */   public int _loadFactor() {
/* 41 */     return this._loadFactor;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void _loadFactor_$eq(int x$1) {
/* 41 */     this._loadFactor = x$1;
/*    */   }
/*    */   
/*    */   public Object[] table() {
/* 41 */     return this.table;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void table_$eq(Object[] x$1) {
/* 41 */     this.table = x$1;
/*    */   }
/*    */   
/*    */   public int tableSize() {
/* 41 */     return this.tableSize;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void tableSize_$eq(int x$1) {
/* 41 */     this.tableSize = x$1;
/*    */   }
/*    */   
/*    */   public int threshold() {
/* 41 */     return this.threshold;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void threshold_$eq(int x$1) {
/* 41 */     this.threshold = x$1;
/*    */   }
/*    */   
/*    */   public int[] sizemap() {
/* 41 */     return this.sizemap;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void sizemap_$eq(int[] x$1) {
/* 41 */     this.sizemap = x$1;
/*    */   }
/*    */   
/*    */   public int seedvalue() {
/* 41 */     return this.seedvalue;
/*    */   }
/*    */   
/*    */   @TraitSetter
/*    */   public void seedvalue_$eq(int x$1) {
/* 41 */     this.seedvalue = x$1;
/*    */   }
/*    */   
/*    */   public int capacity(int expectedSize) {
/* 41 */     return FlatHashTable$class.capacity(this, expectedSize);
/*    */   }
/*    */   
/*    */   public int initialSize() {
/* 41 */     return FlatHashTable$class.initialSize(this);
/*    */   }
/*    */   
/*    */   public int randomSeed() {
/* 41 */     return FlatHashTable$class.randomSeed(this);
/*    */   }
/*    */   
/*    */   public int tableSizeSeed() {
/* 41 */     return FlatHashTable$class.tableSizeSeed(this);
/*    */   }
/*    */   
/*    */   public void init(ObjectInputStream in, Function1 f) {
/* 41 */     FlatHashTable$class.init(this, in, f);
/*    */   }
/*    */   
/*    */   public void serializeTo(ObjectOutputStream out) {
/* 41 */     FlatHashTable$class.serializeTo(this, out);
/*    */   }
/*    */   
/*    */   public Option<A> findEntry(Object elem) {
/* 41 */     return FlatHashTable$class.findEntry(this, elem);
/*    */   }
/*    */   
/*    */   public boolean containsEntry(Object elem) {
/* 41 */     return FlatHashTable$class.containsEntry(this, elem);
/*    */   }
/*    */   
/*    */   public boolean addEntry(Object elem) {
/* 41 */     return FlatHashTable$class.addEntry(this, elem);
/*    */   }
/*    */   
/*    */   public Option<A> removeEntry(Object elem) {
/* 41 */     return FlatHashTable$class.removeEntry(this, elem);
/*    */   }
/*    */   
/*    */   public void nnSizeMapAdd(int h) {
/* 41 */     FlatHashTable$class.nnSizeMapAdd(this, h);
/*    */   }
/*    */   
/*    */   public void nnSizeMapRemove(int h) {
/* 41 */     FlatHashTable$class.nnSizeMapRemove(this, h);
/*    */   }
/*    */   
/*    */   public void nnSizeMapReset(int tableLength) {
/* 41 */     FlatHashTable$class.nnSizeMapReset(this, tableLength);
/*    */   }
/*    */   
/*    */   public final int totalSizeMapBuckets() {
/* 41 */     return FlatHashTable$class.totalSizeMapBuckets(this);
/*    */   }
/*    */   
/*    */   public int calcSizeMapSize(int tableLength) {
/* 41 */     return FlatHashTable$class.calcSizeMapSize(this, tableLength);
/*    */   }
/*    */   
/*    */   public void sizeMapInit(int tableLength) {
/* 41 */     FlatHashTable$class.sizeMapInit(this, tableLength);
/*    */   }
/*    */   
/*    */   public void sizeMapInitAndRebuild() {
/* 41 */     FlatHashTable$class.sizeMapInitAndRebuild(this);
/*    */   }
/*    */   
/*    */   public void printSizeMap() {
/* 41 */     FlatHashTable$class.printSizeMap(this);
/*    */   }
/*    */   
/*    */   public void printContents() {
/* 41 */     FlatHashTable$class.printContents(this);
/*    */   }
/*    */   
/*    */   public void sizeMapDisable() {
/* 41 */     FlatHashTable$class.sizeMapDisable(this);
/*    */   }
/*    */   
/*    */   public boolean isSizeMapDefined() {
/* 41 */     return FlatHashTable$class.isSizeMapDefined(this);
/*    */   }
/*    */   
/*    */   public boolean alwaysInitSizeMap() {
/* 41 */     return FlatHashTable$class.alwaysInitSizeMap(this);
/*    */   }
/*    */   
/*    */   public final int index(int hcode) {
/* 41 */     return FlatHashTable$class.index(this, hcode);
/*    */   }
/*    */   
/*    */   public void clearTable() {
/* 41 */     FlatHashTable$class.clearTable(this);
/*    */   }
/*    */   
/*    */   public FlatHashTable.Contents<A> hashTableContents() {
/* 41 */     return FlatHashTable$class.hashTableContents(this);
/*    */   }
/*    */   
/*    */   public void initWithContents(FlatHashTable.Contents c) {
/* 41 */     FlatHashTable$class.initWithContents(this, c);
/*    */   }
/*    */   
/*    */   public final int sizeMapBucketBitSize() {
/* 41 */     return FlatHashTable.HashUtils$class.sizeMapBucketBitSize(this);
/*    */   }
/*    */   
/*    */   public final int sizeMapBucketSize() {
/* 41 */     return FlatHashTable.HashUtils$class.sizeMapBucketSize(this);
/*    */   }
/*    */   
/*    */   public int elemHashCode(Object elem) {
/* 41 */     return FlatHashTable.HashUtils$class.elemHashCode(this, elem);
/*    */   }
/*    */   
/*    */   public final int improve(int hcode, int seed) {
/* 41 */     return FlatHashTable.HashUtils$class.improve(this, hcode, seed);
/*    */   }
/*    */   
/*    */   public HashSet(FlatHashTable.Contents<A> contents) {
/* 41 */     FlatHashTable.HashUtils$class.$init$(this);
/* 41 */     FlatHashTable$class.$init$(this);
/* 41 */     CustomParallelizable.class.$init$(this);
/* 50 */     initWithContents(contents);
/*    */   }
/*    */   
/*    */   public HashSet() {
/* 52 */     this((FlatHashTable.Contents<A>)null);
/*    */   }
/*    */   
/*    */   public GenericCompanion<HashSet> companion() {
/* 54 */     return (GenericCompanion<HashSet>)HashSet$.MODULE$;
/*    */   }
/*    */   
/*    */   public int size() {
/* 56 */     return tableSize();
/*    */   }
/*    */   
/*    */   public boolean contains(Object elem) {
/* 58 */     return containsEntry((A)elem);
/*    */   }
/*    */   
/*    */   public HashSet<A> $plus$eq(Object elem) {
/* 60 */     addEntry((A)elem);
/* 60 */     return this;
/*    */   }
/*    */   
/*    */   public HashSet<A> $minus$eq(Object elem) {
/* 62 */     removeEntry((A)elem);
/* 62 */     return this;
/*    */   }
/*    */   
/*    */   public ParHashSet<A> par() {
/* 64 */     return new ParHashSet(hashTableContents());
/*    */   }
/*    */   
/*    */   public boolean add(Object elem) {
/* 66 */     return addEntry((A)elem);
/*    */   }
/*    */   
/*    */   public boolean remove(Object elem) {
/* 68 */     return removeEntry((A)elem).isDefined();
/*    */   }
/*    */   
/*    */   public void clear() {
/* 70 */     clearTable();
/*    */   }
/*    */   
/*    */   public Iterator<A> iterator() {
/* 72 */     return FlatHashTable$class.iterator(this);
/*    */   }
/*    */   
/*    */   public <U> void foreach(Function1 f) {
/* 75 */     int i = 0;
/* 76 */     int len = (table()).length;
/* 77 */     while (i < len) {
/* 78 */       Object elem = table()[i];
/* 79 */       (elem != null) ? f.apply(elem) : BoxedUnit.UNIT;
/* 80 */       i++;
/*    */     } 
/*    */   }
/*    */   
/*    */   public HashSet<A> clone() {
/* 84 */     return (HashSet<A>)(new HashSet()).$plus$plus$eq((TraversableOnce<?>)this);
/*    */   }
/*    */   
/*    */   private void writeObject(ObjectOutputStream s) {
/* 87 */     serializeTo(s);
/*    */   }
/*    */   
/*    */   private void readObject(ObjectInputStream in) {
/* 91 */     init(in, (Function1<A, BoxedUnit>)new HashSet$$anonfun$readObject$1(this));
/*    */   }
/*    */   
/*    */   public class HashSet$$anonfun$readObject$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(Object x) {}
/*    */     
/*    */     public HashSet$$anonfun$readObject$1(HashSet $outer) {}
/*    */   }
/*    */   
/*    */   public void useSizeMap(boolean t) {
/* 96 */     if (t) {
/* 97 */       if (!isSizeMapDefined())
/* 97 */         sizeMapInitAndRebuild(); 
/*    */     } else {
/* 98 */       sizeMapDisable();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static <A> CanBuildFrom<HashSet<?>, A, HashSet<A>> canBuildFrom() {
/*    */     return HashSet$.MODULE$.canBuildFrom();
/*    */   }
/*    */   
/*    */   public static <A> Object setCanBuildFrom() {
/*    */     return HashSet$.MODULE$.setCanBuildFrom();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\HashSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */