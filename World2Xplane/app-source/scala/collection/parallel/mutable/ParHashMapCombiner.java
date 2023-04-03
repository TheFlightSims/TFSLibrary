/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.DefaultEntry;
/*     */ import scala.collection.mutable.HashEntry;
/*     */ import scala.collection.mutable.HashTable;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.mutable.UnrolledBuffer;
/*     */ import scala.collection.parallel.BucketCombiner;
/*     */ import scala.collection.parallel.Task;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ import scala.runtime.VolatileObjectRef;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\teaAB\001\003\003\003\021!B\001\nQCJD\025m\0355NCB\034u.\0342j]\026\024(BA\002\005\003\035iW\017^1cY\026T!!\002\004\002\021A\f'/\0317mK2T!a\002\005\002\025\r|G\016\\3di&|gNC\001\n\003\025\0318-\0317b+\rYa#I\n\004\0011i\003CB\007\017!\r:C&D\001\005\023\tyAA\001\bCk\016\\W\r^\"p[\nLg.\032:\021\tE\021B\003I\007\002\021%\0211\003\003\002\007)V\004H.\032\032\021\005U1B\002\001\003\006/\001\021\r!\007\002\002\027\016\001\021C\001\016\036!\t\t2$\003\002\035\021\t9aj\034;iS:<\007CA\t\037\023\ty\002BA\002B]f\004\"!F\021\005\013\t\002!\031A\r\003\003Y\003B\001J\023\025A5\t!!\003\002'\005\tQ\001+\031:ICNDW*\0319\021\t!RC\003I\007\002S)\0211AB\005\003W%\022A\002R3gCVdG/\0228uef\004B\001\n\001\025AA\031aF\017\013\017\005=BdB\001\0318\035\t\tdG\004\0023k5\t1G\003\00251\0051AH]8pizJ\021!C\005\003\017!I!a\001\004\n\005eJ\023!\003%bg\"$\026M\0317f\023\tYDHA\005ICNDW\013^5mg*\021\021(\013\005\t}\001\021)\031!C\005\005yA/\0312mK2{\027\r\032$bGR|'/F\001A!\t\t\022)\003\002C\021\t\031\021J\034;\t\021\021\003!\021!Q\001\n\001\013\001\003^1cY\026du.\0313GC\016$xN\035\021\t\013\031\003A\021A$\002\rqJg.\033;?)\ta\003\nC\003?\013\002\007\001\tC\004K\001\001\007I\021B \002\t5\f7o\033\005\b\031\002\001\r\021\"\003N\003!i\027m]6`I\025\fHC\001(R!\t\tr*\003\002Q\021\t!QK\\5u\021\035\0216*!AA\002\001\0131\001\037\0232\021\031!\006\001)Q\005\001\006)Q.Y:lA!9a\013\001a\001\n\023y\024A\0038p]6\f7o\0337f]\"9\001\f\001a\001\n\023I\026A\0048p]6\f7o\0337f]~#S-\035\013\003\035jCqAU,\002\002\003\007\001\t\003\004]\001\001\006K\001Q\001\f]>tW.Y:lY\026t\007\005C\004_\001\001\007I\021B \002\023M,W\r\032<bYV,\007b\0021\001\001\004%I!Y\001\016g\026,GM^1mk\026|F%Z9\025\0059\023\007b\002*`\003\003\005\r\001\021\005\007I\002\001\013\025\002!\002\025M,W\r\032<bYV,\007\005C\003g\001\021\005q-\001\005%a2,8\017J3r)\tA\027.D\001\001\021\025QW\r1\001\021\003\021)G.Z7\t\0131\004A\021A7\002\rI,7/\0367u)\005\031c\001B8\001\001A\024!BR5mY\ncwnY6t'\rq\027\017\036\t\003#IL!a\035\005\003\r\005s\027PU3g!\021iQ\017Q<\n\005Y$!\001\002+bg.\004\"\001\0338\t\021et'\021!Q\001\ni\fqAY;dW\026$8\017E\002\022wvL!\001 \005\003\013\005\023(/Y=\021\ty\f\031a\n\b\003Q}L1!!\001*\0039)fN]8mY\026$')\0364gKJLA!!\002\002\b\tAQK\034:pY2,GMC\002\002\002%B!\"a\003o\005\003\005\013\021BA\007\003\025!\030M\0317f!\rA\027q\002\004\b\003#\001\001\001AA\n\005=\tE\rZ5oO\"\0137\017\033+bE2,7#BA\bc\006U\001#\002\025\002\030Q9\023bAA\rS\tI\001*Y:i)\006\024G.\032\005\013\003;\tyA!A!\002\023\001\025\001\0038v[\026dW-\\:\t\025\005\005\022q\002B\001B\003%\001)\001\002mM\"Q\021QEA\b\005\003\005\013\021\002!\002\025}\033X-\0323wC2,X\rC\004G\003\037!\t!!\013\025\021\0055\0211FA\027\003_Aq!!\b\002(\001\007\001\tC\004\002\"\005\035\002\031\001!\t\017\005\025\022q\005a\001\001\"A\0211GA\b\t\003\t)$A\004tKR\034\026N_3\025\0079\0139\004C\004\002:\005E\002\031\001!\002\005MT\b\002CA\037\003\037!\t!a\020\002\027%t7/\032:u\013:$(/\037\013\005\003\003\n9\005E\002\022\003\007J1!!\022\t\005\035\021un\0347fC:Dq!!\023\002<\001\007q%A\001f\021!\ti%a\004\005\n\005=\023AE1tg\026\024HoQ8se\026\034GO\0217pG.$RATA)\003+Bq!a\025\002L\001\007\001)A\001i\021\035\t9&a\023A\002\001\013QA\0317pG.D\001\"a\027\002\020\021E\021QL\001\017GJ,\027\r^3OK^,e\016\036:z+\021\ty&a\033\025\013i\t\t'!\032\t\017\005\r\024\021\fa\001)\005\0311.Z=\t\021\005\035\024\021\fa\001\003S\n\021\001\037\t\004+\005-DaBA7\0033\022\r!\007\002\0021\"I\021\021\0178\003\002\003\006I\001Q\001\007_\03247/\032;\t\023\005UdN!A!\002\023\001\025a\0025po6\fg.\037\005\007\r:$\t!!\037\025\023]\fY(! \002\000\005\005\005BB=\002x\001\007!\020\003\005\002\f\005]\004\031AA\007\021\035\t\t(a\036A\002\001Cq!!\036\002x\001\007\001\tC\004m]\002\007I\021A \t\023\005\035e\0161A\005\002\005%\025A\003:fgVdGo\030\023fcR\031a*a#\t\021I\013))!AA\002\001Cq!a$oA\003&\001)A\004sKN,H\016\036\021\t\017\005Me\016\"\001\002\026\006!A.Z1g)\rq\025q\023\005\t\0033\013\t\n1\001\002\034\006!\001O]3w!\021\t\022Q\024!\n\007\005}\005B\001\004PaRLwN\034\005\b\003GsG\021BAS\003%1\027\016\0347CY>\0347\016F\003A\003O\013I\013C\004\002X\005\005\006\031\001!\t\017\005-\026\021\025a\001{\006)Q\r\\3ng\"9\021Q\n8\005\n\005=F#\002(\0022\006M\006bBA,\003[\003\r\001\021\005\b\003k\013i\0131\001\025\003\005Y\007bBA]]\022\005\0211X\001\006gBd\027\016^\013\003\003{\003R!a0\002F^l!!!1\013\007\005\rg!A\005j[6,H/\0312mK&!\021qYAa\005\021a\025n\035;\t\017\005-g\016\"\021\002N\006)Q.\032:hKR\031a*a4\t\017\005E\027\021\032a\001o\006!A\017[1u\021\035\t)N\034C\001\003/\f!c\0355pk2$7\013\0357ji\032+(\017\0365feV\021\021\021I\004\t\0037\024\001\022\001\003\002^\006\021\002+\031:ICNDW*\0319D_6\024\027N\\3s!\r!\023q\034\004\b\003\tA\t\001BAq'\r\ty.\035\005\b\r\006}G\021AAs)\t\ti\016\003\006\002j\006}'\031!C\001\005}\n\001\003Z5tGJLW.\0338b]R\024\027\016^:\t\021\0055\030q\034Q\001\n\001\013\021\003Z5tGJLW.\0338b]R\024\027\016^:!\021)\t\t0a8C\002\023\005!aP\001\n]Vl'\r\\8dWND\001\"!>\002`\002\006I\001Q\001\013]Vl'\r\\8dWN\004\003BCA}\003?\024\r\021\"\001\003\005\001B-[:de&l\027N\\1oi6\f7o\033\005\t\003{\fy\016)A\005\001\006\tB-[:de&l\027N\\1oi6\f7o\033\021\t\025\t\005\021q\034b\001\n\003\021q(A\007o_:l\027m]6mK:<G\017\033\005\t\005\013\ty\016)A\005\001\006qan\0348nCN\\G.\0328hi\"\004\003\002\003B\005\003?$\tAa\003\002\013\005\004\b\017\\=\026\r\t5!1\003B\f+\t\021y\001\005\004%\001\tE!Q\003\t\004+\tMAAB\f\003\b\t\007\021\004E\002\026\005/!aA\tB\004\005\004I\002")
/*     */ public abstract class ParHashMapCombiner<K, V> extends BucketCombiner<Tuple2<K, V>, ParHashMap<K, V>, DefaultEntry<K, V>, ParHashMapCombiner<K, V>> implements HashTable.HashUtils<K> {
/*     */   private final int tableLoadFactor;
/*     */   
/*     */   private int mask;
/*     */   
/*     */   private int scala$collection$parallel$mutable$ParHashMapCombiner$$nonmasklen;
/*     */   
/*     */   private int scala$collection$parallel$mutable$ParHashMapCombiner$$seedvalue;
/*     */   
/*     */   public final int sizeMapBucketBitSize() {
/* 165 */     return HashTable.HashUtils.class.sizeMapBucketBitSize(this);
/*     */   }
/*     */   
/*     */   public final int sizeMapBucketSize() {
/* 165 */     return HashTable.HashUtils.class.sizeMapBucketSize(this);
/*     */   }
/*     */   
/*     */   public int elemHashCode(Object key) {
/* 165 */     return HashTable.HashUtils.class.elemHashCode(this, key);
/*     */   }
/*     */   
/*     */   public final int improve(int hcode, int seed) {
/* 165 */     return HashTable.HashUtils.class.improve(this, hcode, seed);
/*     */   }
/*     */   
/*     */   private int tableLoadFactor() {
/* 165 */     return this.tableLoadFactor;
/*     */   }
/*     */   
/*     */   public ParHashMapCombiner(int tableLoadFactor) {
/* 165 */     super(
/* 166 */         ParHashMapCombiner$.MODULE$.numblocks());
/*     */     HashTable.HashUtils.class.$init$(this);
/* 169 */     this.mask = ParHashMapCombiner$.MODULE$.discriminantmask();
/* 170 */     this.scala$collection$parallel$mutable$ParHashMapCombiner$$nonmasklen = ParHashMapCombiner$.MODULE$.nonmasklength();
/* 171 */     this.scala$collection$parallel$mutable$ParHashMapCombiner$$seedvalue = 27;
/*     */   }
/*     */   
/*     */   private int mask() {
/*     */     return this.mask;
/*     */   }
/*     */   
/*     */   private void mask_$eq(int x$1) {
/*     */     this.mask = x$1;
/*     */   }
/*     */   
/*     */   public int scala$collection$parallel$mutable$ParHashMapCombiner$$nonmasklen() {
/*     */     return this.scala$collection$parallel$mutable$ParHashMapCombiner$$nonmasklen;
/*     */   }
/*     */   
/*     */   private void scala$collection$parallel$mutable$ParHashMapCombiner$$nonmasklen_$eq(int x$1) {
/*     */     this.scala$collection$parallel$mutable$ParHashMapCombiner$$nonmasklen = x$1;
/*     */   }
/*     */   
/*     */   public int scala$collection$parallel$mutable$ParHashMapCombiner$$seedvalue() {
/* 171 */     return this.scala$collection$parallel$mutable$ParHashMapCombiner$$seedvalue;
/*     */   }
/*     */   
/*     */   private void scala$collection$parallel$mutable$ParHashMapCombiner$$seedvalue_$eq(int x$1) {
/* 171 */     this.scala$collection$parallel$mutable$ParHashMapCombiner$$seedvalue = x$1;
/*     */   }
/*     */   
/*     */   public ParHashMapCombiner<K, V> $plus$eq(Tuple2 elem) {
/* 174 */     sz_$eq(sz() + 1);
/* 175 */     int hc = improve(elemHashCode((K)elem._1()), scala$collection$parallel$mutable$ParHashMapCombiner$$seedvalue());
/* 176 */     int pos = hc >>> scala$collection$parallel$mutable$ParHashMapCombiner$$nonmasklen();
/* 177 */     if (buckets()[pos] == null)
/* 179 */       buckets()[pos] = new UnrolledBuffer(scala.reflect.ClassTag$.MODULE$.apply(DefaultEntry.class)); 
/* 182 */     buckets()[pos].$plus$eq(new DefaultEntry(elem._1(), elem._2()));
/* 183 */     return this;
/*     */   }
/*     */   
/*     */   public ParHashMap<K, V> result() {
/* 188 */     AddingHashTable table = new AddingHashTable(this, size(), tableLoadFactor(), scala$collection$parallel$mutable$ParHashMapCombiner$$seedvalue());
/* 189 */     UnrolledBuffer.Unrolled[] bucks = (UnrolledBuffer.Unrolled[])scala.Predef$.MODULE$.refArrayOps((Object[])buckets()).map((Function1)new ParHashMapCombiner$$anonfun$4(this), scala.Array$.MODULE$.canBuildFrom(scala.reflect.ClassTag$.MODULE$.apply(UnrolledBuffer.Unrolled.class)));
/* 190 */     int insertcount = BoxesRunTime.unboxToInt(combinerTaskSupport().executeAndWaitResult(new FillBlocks(this, (UnrolledBuffer.Unrolled<DefaultEntry<K, V>>[])bucks, table, 0, bucks.length)));
/* 191 */     table.setSize(insertcount);
/* 193 */     HashTable.Contents<K, DefaultEntry<K, V>> c = table.hashTableContents();
/* 198 */     VolatileObjectRef table$module = new VolatileObjectRef(null);
/* 204 */     int i = 0;
/* 205 */     while (i < ParHashMapCombiner$.MODULE$.numblocks()) {
/* 206 */       if (buckets()[i] != null)
/* 207 */         buckets()[i].foreach((Function1)new ParHashMapCombiner$$anonfun$result$1(this, (ParHashMapCombiner<K, V>)table$module)); 
/* 209 */       i++;
/*     */     } 
/* 211 */     return (size() >= ParHashMapCombiner$.MODULE$.numblocks() * sizeMapBucketSize()) ? new ParHashMap<K, V>(c) : new ParHashMap<K, V>(scala$collection$parallel$mutable$ParHashMapCombiner$$table$1(table$module).hashTableContents());
/*     */   }
/*     */   
/*     */   public class ParHashMapCombiner$$anonfun$4 extends AbstractFunction1<UnrolledBuffer<DefaultEntry<K, V>>, UnrolledBuffer.Unrolled<DefaultEntry<K, V>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final UnrolledBuffer.Unrolled<DefaultEntry<K, V>> apply(UnrolledBuffer b) {
/*     */       return (b != null) ? b.headPtr() : null;
/*     */     }
/*     */     
/*     */     public ParHashMapCombiner$$anonfun$4(ParHashMapCombiner $outer) {}
/*     */   }
/*     */   
/*     */   private ParHashMapCombiner$table$2$ scala$collection$parallel$mutable$ParHashMapCombiner$$table$1$lzycompute(VolatileObjectRef x$1) {
/*     */     synchronized (this) {
/*     */       if (x$1.elem == null)
/*     */         x$1.elem = new ParHashMapCombiner$table$2$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/mutable/ParHashMapCombiner}} */
/*     */       return (ParHashMapCombiner$table$2$)x$1.elem;
/*     */     } 
/*     */   }
/*     */   
/*     */   public final ParHashMapCombiner$table$2$ scala$collection$parallel$mutable$ParHashMapCombiner$$table$1(VolatileObjectRef table$module$1) {
/*     */     return (table$module$1.elem == null) ? scala$collection$parallel$mutable$ParHashMapCombiner$$table$1$lzycompute(table$module$1) : (ParHashMapCombiner$table$2$)table$module$1.elem;
/*     */   }
/*     */   
/*     */   public static <K, V> ParHashMapCombiner<K, V> apply() {
/*     */     return ParHashMapCombiner$.MODULE$.apply();
/*     */   }
/*     */   
/*     */   public class ParHashMapCombiner$table$2$ implements HashTable<K, DefaultEntry<K, V>> {
/*     */     private transient int _loadFactor;
/*     */     
/*     */     private transient HashEntry<Object, HashEntry>[] table;
/*     */     
/*     */     private transient int tableSize;
/*     */     
/*     */     private transient int threshold;
/*     */     
/*     */     private transient int[] sizemap;
/*     */     
/*     */     private transient int seedvalue;
/*     */     
/*     */     public int _loadFactor() {
/*     */       return this._loadFactor;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void _loadFactor_$eq(int x$1) {
/*     */       this._loadFactor = x$1;
/*     */     }
/*     */     
/*     */     public HashEntry<K, DefaultEntry<K, V>>[] table() {
/*     */       return (HashEntry[])this.table;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void table_$eq(HashEntry[] x$1) {
/*     */       this.table = (HashEntry<Object, HashEntry>[])x$1;
/*     */     }
/*     */     
/*     */     public int tableSize() {
/*     */       return this.tableSize;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void tableSize_$eq(int x$1) {
/*     */       this.tableSize = x$1;
/*     */     }
/*     */     
/*     */     public int threshold() {
/*     */       return this.threshold;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void threshold_$eq(int x$1) {
/*     */       this.threshold = x$1;
/*     */     }
/*     */     
/*     */     public int[] sizemap() {
/*     */       return this.sizemap;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void sizemap_$eq(int[] x$1) {
/*     */       this.sizemap = x$1;
/*     */     }
/*     */     
/*     */     public int seedvalue() {
/*     */       return this.seedvalue;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void seedvalue_$eq(int x$1) {
/*     */       this.seedvalue = x$1;
/*     */     }
/*     */     
/*     */     public int tableSizeSeed() {
/*     */       return HashTable.class.tableSizeSeed(this);
/*     */     }
/*     */     
/*     */     public int initialSize() {
/*     */       return HashTable.class.initialSize(this);
/*     */     }
/*     */     
/*     */     public void init(ObjectInputStream in, Function0 readEntry) {
/*     */       HashTable.class.init(this, in, readEntry);
/*     */     }
/*     */     
/*     */     public void serializeTo(ObjectOutputStream out, Function1 writeEntry) {
/*     */       HashTable.class.serializeTo(this, out, writeEntry);
/*     */     }
/*     */     
/*     */     public DefaultEntry<K, V> findEntry(Object key) {
/*     */       return (DefaultEntry<K, V>)HashTable.class.findEntry(this, key);
/*     */     }
/*     */     
/*     */     public void addEntry(HashEntry e) {
/*     */       HashTable.class.addEntry(this, e);
/*     */     }
/*     */     
/*     */     public <B> DefaultEntry<K, V> findOrAddEntry(Object key, Object value) {
/*     */       return (DefaultEntry<K, V>)HashTable.class.findOrAddEntry(this, key, value);
/*     */     }
/*     */     
/*     */     public DefaultEntry<K, V> removeEntry(Object key) {
/*     */       return (DefaultEntry<K, V>)HashTable.class.removeEntry(this, key);
/*     */     }
/*     */     
/*     */     public Iterator<DefaultEntry<K, V>> entriesIterator() {
/*     */       return HashTable.class.entriesIterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreachEntry(Function1 f) {
/*     */       HashTable.class.foreachEntry(this, f);
/*     */     }
/*     */     
/*     */     public void clearTable() {
/*     */       HashTable.class.clearTable(this);
/*     */     }
/*     */     
/*     */     public void nnSizeMapAdd(int h) {
/*     */       HashTable.class.nnSizeMapAdd(this, h);
/*     */     }
/*     */     
/*     */     public void nnSizeMapRemove(int h) {
/*     */       HashTable.class.nnSizeMapRemove(this, h);
/*     */     }
/*     */     
/*     */     public void nnSizeMapReset(int tableLength) {
/*     */       HashTable.class.nnSizeMapReset(this, tableLength);
/*     */     }
/*     */     
/*     */     public final int totalSizeMapBuckets() {
/*     */       return HashTable.class.totalSizeMapBuckets(this);
/*     */     }
/*     */     
/*     */     public int calcSizeMapSize(int tableLength) {
/*     */       return HashTable.class.calcSizeMapSize(this, tableLength);
/*     */     }
/*     */     
/*     */     public void sizeMapInit(int tableLength) {
/*     */       HashTable.class.sizeMapInit(this, tableLength);
/*     */     }
/*     */     
/*     */     public void sizeMapInitAndRebuild() {
/*     */       HashTable.class.sizeMapInitAndRebuild(this);
/*     */     }
/*     */     
/*     */     public void printSizeMap() {
/*     */       HashTable.class.printSizeMap(this);
/*     */     }
/*     */     
/*     */     public void sizeMapDisable() {
/*     */       HashTable.class.sizeMapDisable(this);
/*     */     }
/*     */     
/*     */     public boolean isSizeMapDefined() {
/*     */       return HashTable.class.isSizeMapDefined(this);
/*     */     }
/*     */     
/*     */     public boolean alwaysInitSizeMap() {
/*     */       return HashTable.class.alwaysInitSizeMap(this);
/*     */     }
/*     */     
/*     */     public boolean elemEquals(Object key1, Object key2) {
/*     */       return HashTable.class.elemEquals(this, key1, key2);
/*     */     }
/*     */     
/*     */     public final int index(int hcode) {
/*     */       return HashTable.class.index(this, hcode);
/*     */     }
/*     */     
/*     */     public void initWithContents(HashTable.Contents c) {
/*     */       HashTable.class.initWithContents(this, c);
/*     */     }
/*     */     
/*     */     public HashTable.Contents<K, DefaultEntry<K, V>> hashTableContents() {
/*     */       return HashTable.class.hashTableContents(this);
/*     */     }
/*     */     
/*     */     public final int sizeMapBucketBitSize() {
/*     */       return HashTable.HashUtils.class.sizeMapBucketBitSize((HashTable.HashUtils)this);
/*     */     }
/*     */     
/*     */     public final int sizeMapBucketSize() {
/*     */       return HashTable.HashUtils.class.sizeMapBucketSize((HashTable.HashUtils)this);
/*     */     }
/*     */     
/*     */     public int elemHashCode(Object key) {
/*     */       return HashTable.HashUtils.class.elemHashCode((HashTable.HashUtils)this, key);
/*     */     }
/*     */     
/*     */     public final int improve(int hcode, int seed) {
/*     */       return HashTable.HashUtils.class.improve((HashTable.HashUtils)this, hcode, seed);
/*     */     }
/*     */     
/*     */     public ParHashMapCombiner$table$2$(ParHashMapCombiner $outer) {
/*     */       HashTable.HashUtils.class.$init$((HashTable.HashUtils)this);
/*     */       HashTable.class.$init$(this);
/*     */       sizeMapInit((table()).length);
/*     */     }
/*     */     
/*     */     public void insertEntry(DefaultEntry e) {
/*     */       HashTable.class.findOrAddEntry(this, e.key(), e);
/*     */     }
/*     */     
/*     */     public <E> DefaultEntry<K, V> createNewEntry(Object key, Object entry) {
/*     */       return (DefaultEntry<K, V>)entry;
/*     */     }
/*     */   }
/*     */   
/*     */   public class ParHashMapCombiner$$anonfun$result$1 extends AbstractFunction1<DefaultEntry<K, V>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final VolatileObjectRef table$module$1;
/*     */     
/*     */     public final void apply(DefaultEntry<K, V> elem) {
/*     */       this.$outer.scala$collection$parallel$mutable$ParHashMapCombiner$$table$1(this.table$module$1).insertEntry(elem);
/*     */     }
/*     */     
/*     */     public ParHashMapCombiner$$anonfun$result$1(ParHashMapCombiner $outer, VolatileObjectRef table$module$1) {}
/*     */   }
/*     */   
/*     */   public class AddingHashTable implements HashTable<K, DefaultEntry<K, V>> {
/*     */     private transient int _loadFactor;
/*     */     
/*     */     private transient HashEntry<Object, HashEntry>[] table;
/*     */     
/*     */     private transient int tableSize;
/*     */     
/*     */     private transient int threshold;
/*     */     
/*     */     private transient int[] sizemap;
/*     */     
/*     */     private transient int seedvalue;
/*     */     
/*     */     public int _loadFactor() {
/* 224 */       return this._loadFactor;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void _loadFactor_$eq(int x$1) {
/* 224 */       this._loadFactor = x$1;
/*     */     }
/*     */     
/*     */     public HashEntry<K, DefaultEntry<K, V>>[] table() {
/* 224 */       return (HashEntry[])this.table;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void table_$eq(HashEntry[] x$1) {
/* 224 */       this.table = (HashEntry<Object, HashEntry>[])x$1;
/*     */     }
/*     */     
/*     */     public int tableSize() {
/* 224 */       return this.tableSize;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void tableSize_$eq(int x$1) {
/* 224 */       this.tableSize = x$1;
/*     */     }
/*     */     
/*     */     public int threshold() {
/* 224 */       return this.threshold;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void threshold_$eq(int x$1) {
/* 224 */       this.threshold = x$1;
/*     */     }
/*     */     
/*     */     public int[] sizemap() {
/* 224 */       return this.sizemap;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void sizemap_$eq(int[] x$1) {
/* 224 */       this.sizemap = x$1;
/*     */     }
/*     */     
/*     */     public int seedvalue() {
/* 224 */       return this.seedvalue;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void seedvalue_$eq(int x$1) {
/* 224 */       this.seedvalue = x$1;
/*     */     }
/*     */     
/*     */     public int tableSizeSeed() {
/* 224 */       return HashTable.class.tableSizeSeed(this);
/*     */     }
/*     */     
/*     */     public int initialSize() {
/* 224 */       return HashTable.class.initialSize(this);
/*     */     }
/*     */     
/*     */     public void init(ObjectInputStream in, Function0 readEntry) {
/* 224 */       HashTable.class.init(this, in, readEntry);
/*     */     }
/*     */     
/*     */     public void serializeTo(ObjectOutputStream out, Function1 writeEntry) {
/* 224 */       HashTable.class.serializeTo(this, out, writeEntry);
/*     */     }
/*     */     
/*     */     public DefaultEntry<K, V> findEntry(Object key) {
/* 224 */       return (DefaultEntry<K, V>)HashTable.class.findEntry(this, key);
/*     */     }
/*     */     
/*     */     public void addEntry(HashEntry e) {
/* 224 */       HashTable.class.addEntry(this, e);
/*     */     }
/*     */     
/*     */     public <B> DefaultEntry<K, V> findOrAddEntry(Object key, Object value) {
/* 224 */       return (DefaultEntry<K, V>)HashTable.class.findOrAddEntry(this, key, value);
/*     */     }
/*     */     
/*     */     public DefaultEntry<K, V> removeEntry(Object key) {
/* 224 */       return (DefaultEntry<K, V>)HashTable.class.removeEntry(this, key);
/*     */     }
/*     */     
/*     */     public Iterator<DefaultEntry<K, V>> entriesIterator() {
/* 224 */       return HashTable.class.entriesIterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreachEntry(Function1 f) {
/* 224 */       HashTable.class.foreachEntry(this, f);
/*     */     }
/*     */     
/*     */     public void clearTable() {
/* 224 */       HashTable.class.clearTable(this);
/*     */     }
/*     */     
/*     */     public void nnSizeMapAdd(int h) {
/* 224 */       HashTable.class.nnSizeMapAdd(this, h);
/*     */     }
/*     */     
/*     */     public void nnSizeMapRemove(int h) {
/* 224 */       HashTable.class.nnSizeMapRemove(this, h);
/*     */     }
/*     */     
/*     */     public void nnSizeMapReset(int tableLength) {
/* 224 */       HashTable.class.nnSizeMapReset(this, tableLength);
/*     */     }
/*     */     
/*     */     public final int totalSizeMapBuckets() {
/* 224 */       return HashTable.class.totalSizeMapBuckets(this);
/*     */     }
/*     */     
/*     */     public int calcSizeMapSize(int tableLength) {
/* 224 */       return HashTable.class.calcSizeMapSize(this, tableLength);
/*     */     }
/*     */     
/*     */     public void sizeMapInit(int tableLength) {
/* 224 */       HashTable.class.sizeMapInit(this, tableLength);
/*     */     }
/*     */     
/*     */     public void sizeMapInitAndRebuild() {
/* 224 */       HashTable.class.sizeMapInitAndRebuild(this);
/*     */     }
/*     */     
/*     */     public void printSizeMap() {
/* 224 */       HashTable.class.printSizeMap(this);
/*     */     }
/*     */     
/*     */     public void sizeMapDisable() {
/* 224 */       HashTable.class.sizeMapDisable(this);
/*     */     }
/*     */     
/*     */     public boolean isSizeMapDefined() {
/* 224 */       return HashTable.class.isSizeMapDefined(this);
/*     */     }
/*     */     
/*     */     public boolean alwaysInitSizeMap() {
/* 224 */       return HashTable.class.alwaysInitSizeMap(this);
/*     */     }
/*     */     
/*     */     public boolean elemEquals(Object key1, Object key2) {
/* 224 */       return HashTable.class.elemEquals(this, key1, key2);
/*     */     }
/*     */     
/*     */     public final int index(int hcode) {
/* 224 */       return HashTable.class.index(this, hcode);
/*     */     }
/*     */     
/*     */     public void initWithContents(HashTable.Contents c) {
/* 224 */       HashTable.class.initWithContents(this, c);
/*     */     }
/*     */     
/*     */     public HashTable.Contents<K, DefaultEntry<K, V>> hashTableContents() {
/* 224 */       return HashTable.class.hashTableContents(this);
/*     */     }
/*     */     
/*     */     public final int sizeMapBucketBitSize() {
/* 224 */       return HashTable.HashUtils.class.sizeMapBucketBitSize((HashTable.HashUtils)this);
/*     */     }
/*     */     
/*     */     public final int sizeMapBucketSize() {
/* 224 */       return HashTable.HashUtils.class.sizeMapBucketSize((HashTable.HashUtils)this);
/*     */     }
/*     */     
/*     */     public int elemHashCode(Object key) {
/* 224 */       return HashTable.HashUtils.class.elemHashCode((HashTable.HashUtils)this, key);
/*     */     }
/*     */     
/*     */     public final int improve(int hcode, int seed) {
/* 224 */       return HashTable.HashUtils.class.improve((HashTable.HashUtils)this, hcode, seed);
/*     */     }
/*     */     
/*     */     public AddingHashTable(ParHashMapCombiner $outer, int numelems, int lf, int _seedvalue) {
/* 224 */       HashTable.HashUtils.class.$init$((HashTable.HashUtils)this);
/* 224 */       HashTable.class.$init$(this);
/* 226 */       _loadFactor_$eq(lf);
/* 227 */       table_$eq((HashEntry<K, DefaultEntry<K, V>>[])new HashEntry[scala.collection.mutable.HashTable$.MODULE$.capacity(scala.collection.mutable.HashTable$.MODULE$.sizeForThreshold(_loadFactor(), numelems))]);
/* 228 */       tableSize_$eq(0);
/* 229 */       seedvalue_$eq(_seedvalue);
/* 230 */       threshold_$eq(scala.collection.mutable.HashTable$.MODULE$.newThreshold(_loadFactor(), (table()).length));
/* 231 */       sizeMapInit((table()).length);
/*     */     }
/*     */     
/*     */     public void setSize(int sz) {
/* 232 */       tableSize_$eq(sz);
/*     */     }
/*     */     
/*     */     public boolean insertEntry(DefaultEntry e) {
/* 234 */       int h = index(elemHashCode((K)e.key()));
/* 236 */       DefaultEntry olde = (DefaultEntry)table()[h];
/* 239 */       DefaultEntry ce = olde;
/* 240 */       while (ce != null) {
/* 241 */         Object object2 = e.key();
/*     */         Object object1;
/* 241 */         if (((object1 = ce.key()) == object2) ? true : ((object1 == null) ? false : ((object1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object1, object2) : ((object1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object1, object2) : object1.equals(object2))))) {
/* 242 */           h = -1;
/* 243 */           ce = null;
/*     */           continue;
/*     */         } 
/* 244 */         ce = (DefaultEntry)ce.next();
/*     */       } 
/* 249 */       e.next_$eq(olde);
/* 250 */       table()[h] = (HashEntry<K, DefaultEntry<K, V>>)e;
/* 251 */       nnSizeMapAdd(h);
/*     */       return (h != -1);
/*     */     }
/*     */     
/*     */     private void assertCorrectBlock(int h, int block) {
/* 256 */       int blocksize = (table()).length / (1 << ParHashMapCombiner$.MODULE$.discriminantbits());
/* 257 */       if (h < block * blocksize || h >= (block + 1) * blocksize) {
/* 258 */         scala.Predef$.MODULE$.println((new StringBuilder()).append("trying to put ").append(BoxesRunTime.boxToInteger(h)).append(" into block no.: ").append(BoxesRunTime.boxToInteger(block)).append(", range: [").append(BoxesRunTime.boxToInteger(block * blocksize)).append(", ").append(BoxesRunTime.boxToInteger((block + 1) * blocksize)).append(">").toString());
/* 259 */         scala.Predef$.MODULE$.assert((h >= block * blocksize && h < (block + 1) * blocksize));
/*     */       } 
/*     */     }
/*     */     
/*     */     public <X> scala.runtime.Nothing$ createNewEntry(Object key, Object x) {
/* 262 */       return scala.Predef$.MODULE$.$qmark$qmark$qmark();
/*     */     }
/*     */   }
/*     */   
/*     */   public class FillBlocks implements Task<Object, FillBlocks> {
/*     */     private final UnrolledBuffer.Unrolled<DefaultEntry<K, V>>[] buckets;
/*     */     
/*     */     private final ParHashMapCombiner<K, V>.AddingHashTable table;
/*     */     
/*     */     private final int offset;
/*     */     
/*     */     private final int howmany;
/*     */     
/*     */     private int result;
/*     */     
/*     */     private volatile Throwable throwable;
/*     */     
/*     */     public Throwable throwable() {
/* 269 */       return this.throwable;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void throwable_$eq(Throwable x$1) {
/* 269 */       this.throwable = x$1;
/*     */     }
/*     */     
/*     */     public FillBlocks repr() {
/* 269 */       return (FillBlocks)Task.class.repr(this);
/*     */     }
/*     */     
/*     */     public void forwardThrowable() {
/* 269 */       Task.class.forwardThrowable(this);
/*     */     }
/*     */     
/*     */     public void tryLeaf(Option lastres) {
/* 269 */       Task.class.tryLeaf(this, lastres);
/*     */     }
/*     */     
/*     */     public void tryMerge(Object t) {
/* 269 */       Task.class.tryMerge(this, t);
/*     */     }
/*     */     
/*     */     public void mergeThrowables(Task that) {
/* 269 */       Task.class.mergeThrowables(this, that);
/*     */     }
/*     */     
/*     */     public void signalAbort() {
/* 269 */       Task.class.signalAbort(this);
/*     */     }
/*     */     
/*     */     public FillBlocks(ParHashMapCombiner $outer, UnrolledBuffer.Unrolled[] buckets, ParHashMapCombiner.AddingHashTable table, int offset, int howmany) {
/* 269 */       Task.class.$init$(this);
/* 271 */       this.result = Integer.MIN_VALUE;
/*     */     }
/*     */     
/*     */     public int result() {
/* 271 */       return this.result;
/*     */     }
/*     */     
/*     */     public void result_$eq(int x$1) {
/* 271 */       this.result = x$1;
/*     */     }
/*     */     
/*     */     public void leaf(Option prev) {
/* 273 */       int i = this.offset;
/* 274 */       int until = this.offset + this.howmany;
/* 275 */       result_$eq(0);
/* 276 */       while (i < until) {
/* 277 */         result_$eq(result() + fillBlock(i, this.buckets[i]));
/* 278 */         i++;
/*     */       } 
/*     */     }
/*     */     
/*     */     private int fillBlock(int block, UnrolledBuffer.Unrolled elems) {
/* 282 */       int insertcount = 0;
/* 283 */       UnrolledBuffer.Unrolled unrolled = elems;
/* 284 */       int i = 0;
/* 285 */       ParHashMapCombiner.AddingHashTable t = this.table;
/* 286 */       while (unrolled != null) {
/* 287 */         DefaultEntry[] chunkarr = (DefaultEntry[])unrolled.array();
/* 288 */         int chunksz = unrolled.size();
/* 289 */         while (i < chunksz) {
/* 290 */           DefaultEntry<K, V> elem = chunkarr[i];
/* 292 */           if (t.insertEntry(elem))
/* 292 */             insertcount++; 
/* 293 */           i++;
/*     */         } 
/* 295 */         i = 0;
/* 296 */         unrolled = unrolled.next();
/*     */       } 
/* 298 */       return insertcount;
/*     */     }
/*     */     
/*     */     private void assertCorrectBlock(int block, Object k) {
/* 301 */       int hc = scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer().improve(scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer().elemHashCode(k), scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer().scala$collection$parallel$mutable$ParHashMapCombiner$$seedvalue());
/* 302 */       if (hc >>> scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer().scala$collection$parallel$mutable$ParHashMapCombiner$$nonmasklen() != block) {
/* 303 */         scala.Predef$.MODULE$.println((new StringBuilder()).append(hc).append(" goes to ").append(BoxesRunTime.boxToInteger(hc >>> scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer().scala$collection$parallel$mutable$ParHashMapCombiner$$nonmasklen())).append(", while expected block is ").append(BoxesRunTime.boxToInteger(block)).toString());
/* 304 */         scala.Predef$.MODULE$.assert((hc >>> scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer().scala$collection$parallel$mutable$ParHashMapCombiner$$nonmasklen() == block));
/*     */       } 
/*     */     }
/*     */     
/*     */     public List<FillBlocks> split() {
/* 308 */       int fp = this.howmany / 2;
/* 309 */       (new FillBlocks[2])[0] = new FillBlocks(scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer(), this.buckets, this.table, this.offset, fp);
/* 309 */       (new FillBlocks[2])[1] = new FillBlocks(scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer(), this.buckets, this.table, this.offset + fp, this.howmany - fp);
/* 309 */       return scala.collection.immutable.List$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new FillBlocks[2]));
/*     */     }
/*     */     
/*     */     public void merge(FillBlocks that) {
/* 312 */       result_$eq(result() + that.result());
/*     */     }
/*     */     
/*     */     public boolean shouldSplitFurther() {
/* 314 */       return (this.howmany > scala.collection.parallel.package$.MODULE$.thresholdFromSize(ParHashMapCombiner$.MODULE$.numblocks(), scala$collection$parallel$mutable$ParHashMapCombiner$FillBlocks$$$outer().combinerTaskSupport().parallelismLevel()));
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ParHashMapCombiner$$anon$1 extends ParHashMapCombiner<K, V> {
/*     */     public ParHashMapCombiner$$anon$1() {
/* 326 */       super(scala.collection.mutable.HashTable$.MODULE$.defaultLoadFactor());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParHashMapCombiner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */