/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.FlatHashTable;
/*     */ import scala.collection.mutable.UnrolledBuffer;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ public final class ParHashSetCombiner$ {
/*     */   public static final ParHashSetCombiner$ MODULE$;
/*     */   
/*     */   private final int discriminantbits;
/*     */   
/*     */   private final int numblocks;
/*     */   
/*     */   private final int discriminantmask;
/*     */   
/*     */   private final int nonmasklength;
/*     */   
/*     */   public class ParHashSetCombiner$$anonfun$parPopulate$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ParHashSetCombiner.AddingFlatHashTable table$1;
/*     */     
/*     */     private final IntRef leftinserts$1;
/*     */     
/*     */     public final void apply(Object elem) {
/* 150 */       this.leftinserts$1.elem += this.table$1.insertEntry(0, this.table$1.tableLength(), (T)elem);
/*     */     }
/*     */     
/*     */     public ParHashSetCombiner$$anonfun$parPopulate$1(ParHashSetCombiner $outer, ParHashSetCombiner.AddingFlatHashTable table$1, IntRef leftinserts$1) {}
/*     */   }
/*     */   
/*     */   public class ParHashSetCombiner$$anon$2 implements FlatHashTable<T> {
/*     */     private transient int _loadFactor;
/*     */     
/*     */     private transient Object[] table;
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
/* 158 */       return this._loadFactor;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void _loadFactor_$eq(int x$1) {
/* 158 */       this._loadFactor = x$1;
/*     */     }
/*     */     
/*     */     public Object[] table() {
/* 158 */       return this.table;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void table_$eq(Object[] x$1) {
/* 158 */       this.table = x$1;
/*     */     }
/*     */     
/*     */     public int tableSize() {
/* 158 */       return this.tableSize;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void tableSize_$eq(int x$1) {
/* 158 */       this.tableSize = x$1;
/*     */     }
/*     */     
/*     */     public int threshold() {
/* 158 */       return this.threshold;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void threshold_$eq(int x$1) {
/* 158 */       this.threshold = x$1;
/*     */     }
/*     */     
/*     */     public int[] sizemap() {
/* 158 */       return this.sizemap;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void sizemap_$eq(int[] x$1) {
/* 158 */       this.sizemap = x$1;
/*     */     }
/*     */     
/*     */     public int seedvalue() {
/* 158 */       return this.seedvalue;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void seedvalue_$eq(int x$1) {
/* 158 */       this.seedvalue = x$1;
/*     */     }
/*     */     
/*     */     public int capacity(int expectedSize) {
/* 158 */       return FlatHashTable.class.capacity(this, expectedSize);
/*     */     }
/*     */     
/*     */     public int initialSize() {
/* 158 */       return FlatHashTable.class.initialSize(this);
/*     */     }
/*     */     
/*     */     public int randomSeed() {
/* 158 */       return FlatHashTable.class.randomSeed(this);
/*     */     }
/*     */     
/*     */     public int tableSizeSeed() {
/* 158 */       return FlatHashTable.class.tableSizeSeed(this);
/*     */     }
/*     */     
/*     */     public void init(ObjectInputStream in, Function1 f) {
/* 158 */       FlatHashTable.class.init(this, in, f);
/*     */     }
/*     */     
/*     */     public void serializeTo(ObjectOutputStream out) {
/* 158 */       FlatHashTable.class.serializeTo(this, out);
/*     */     }
/*     */     
/*     */     public Option<T> findEntry(Object elem) {
/* 158 */       return FlatHashTable.class.findEntry(this, elem);
/*     */     }
/*     */     
/*     */     public boolean containsEntry(Object elem) {
/* 158 */       return FlatHashTable.class.containsEntry(this, elem);
/*     */     }
/*     */     
/*     */     public boolean addEntry(Object elem) {
/* 158 */       return FlatHashTable.class.addEntry(this, elem);
/*     */     }
/*     */     
/*     */     public Option<T> removeEntry(Object elem) {
/* 158 */       return FlatHashTable.class.removeEntry(this, elem);
/*     */     }
/*     */     
/*     */     public Iterator<T> iterator() {
/* 158 */       return FlatHashTable.class.iterator(this);
/*     */     }
/*     */     
/*     */     public void nnSizeMapAdd(int h) {
/* 158 */       FlatHashTable.class.nnSizeMapAdd(this, h);
/*     */     }
/*     */     
/*     */     public void nnSizeMapRemove(int h) {
/* 158 */       FlatHashTable.class.nnSizeMapRemove(this, h);
/*     */     }
/*     */     
/*     */     public void nnSizeMapReset(int tableLength) {
/* 158 */       FlatHashTable.class.nnSizeMapReset(this, tableLength);
/*     */     }
/*     */     
/*     */     public final int totalSizeMapBuckets() {
/* 158 */       return FlatHashTable.class.totalSizeMapBuckets(this);
/*     */     }
/*     */     
/*     */     public int calcSizeMapSize(int tableLength) {
/* 158 */       return FlatHashTable.class.calcSizeMapSize(this, tableLength);
/*     */     }
/*     */     
/*     */     public void sizeMapInit(int tableLength) {
/* 158 */       FlatHashTable.class.sizeMapInit(this, tableLength);
/*     */     }
/*     */     
/*     */     public void sizeMapInitAndRebuild() {
/* 158 */       FlatHashTable.class.sizeMapInitAndRebuild(this);
/*     */     }
/*     */     
/*     */     public void printSizeMap() {
/* 158 */       FlatHashTable.class.printSizeMap(this);
/*     */     }
/*     */     
/*     */     public void printContents() {
/* 158 */       FlatHashTable.class.printContents(this);
/*     */     }
/*     */     
/*     */     public void sizeMapDisable() {
/* 158 */       FlatHashTable.class.sizeMapDisable(this);
/*     */     }
/*     */     
/*     */     public boolean isSizeMapDefined() {
/* 158 */       return FlatHashTable.class.isSizeMapDefined(this);
/*     */     }
/*     */     
/*     */     public boolean alwaysInitSizeMap() {
/* 158 */       return FlatHashTable.class.alwaysInitSizeMap(this);
/*     */     }
/*     */     
/*     */     public final int index(int hcode) {
/* 158 */       return FlatHashTable.class.index(this, hcode);
/*     */     }
/*     */     
/*     */     public void clearTable() {
/* 158 */       FlatHashTable.class.clearTable(this);
/*     */     }
/*     */     
/*     */     public FlatHashTable.Contents<T> hashTableContents() {
/* 158 */       return FlatHashTable.class.hashTableContents(this);
/*     */     }
/*     */     
/*     */     public void initWithContents(FlatHashTable.Contents c) {
/* 158 */       FlatHashTable.class.initWithContents(this, c);
/*     */     }
/*     */     
/*     */     public final int sizeMapBucketBitSize() {
/* 158 */       return FlatHashTable.HashUtils.class.sizeMapBucketBitSize((FlatHashTable.HashUtils)this);
/*     */     }
/*     */     
/*     */     public final int sizeMapBucketSize() {
/* 158 */       return FlatHashTable.HashUtils.class.sizeMapBucketSize((FlatHashTable.HashUtils)this);
/*     */     }
/*     */     
/*     */     public int elemHashCode(Object elem) {
/* 158 */       return FlatHashTable.HashUtils.class.elemHashCode((FlatHashTable.HashUtils)this, elem);
/*     */     }
/*     */     
/*     */     public final int improve(int hcode, int seed) {
/* 158 */       return FlatHashTable.HashUtils.class.improve((FlatHashTable.HashUtils)this, hcode, seed);
/*     */     }
/*     */     
/*     */     public ParHashSetCombiner$$anon$2(ParHashSetCombiner $outer) {
/* 158 */       FlatHashTable.HashUtils.class.$init$((FlatHashTable.HashUtils)this);
/* 158 */       FlatHashTable.class.$init$(this);
/* 159 */       sizeMapInit((table()).length);
/* 160 */       seedvalue_$eq($outer.scala$collection$parallel$mutable$ParHashSetCombiner$$seedvalue());
/* 162 */       scala.Predef$.MODULE$.refArrayOps((Object[])$outer.buckets()).withFilter((Function1)new ParHashSetCombiner$$anon$2$$anonfun$1(this)).foreach((Function1)new ParHashSetCombiner$$anon$2$$anonfun$2(this));
/*     */     }
/*     */     
/*     */     public class ParHashSetCombiner$$anon$2$$anonfun$1 extends AbstractFunction1<UnrolledBuffer<Object>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public ParHashSetCombiner$$anon$2$$anonfun$1(ParHashSetCombiner$$anon$2 $outer) {}
/*     */       
/*     */       public final boolean apply(UnrolledBuffer buffer) {
/* 163 */         return (buffer != null);
/*     */       }
/*     */     }
/*     */     
/*     */     public class ParHashSetCombiner$$anon$2$$anonfun$2 extends AbstractFunction1<UnrolledBuffer<Object>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public ParHashSetCombiner$$anon$2$$anonfun$2(ParHashSetCombiner$$anon$2 $outer) {}
/*     */       
/*     */       public final void apply(UnrolledBuffer buffer) {
/* 164 */         buffer.foreach((Function1)new ParHashSetCombiner$$anon$2$$anonfun$2$$anonfun$apply$1(this));
/*     */       }
/*     */       
/*     */       public class ParHashSetCombiner$$anon$2$$anonfun$2$$anonfun$apply$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public ParHashSetCombiner$$anon$2$$anonfun$2$$anonfun$apply$1(ParHashSetCombiner$$anon$2$$anonfun$2 $outer) {}
/*     */         
/*     */         public final boolean apply(Object elem) {
/* 165 */           return this.$outer.$outer.addEntry((T)elem);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private ParHashSetCombiner$() {
/* 319 */     MODULE$ = this;
/* 320 */     this.discriminantbits = 5;
/* 321 */     this.numblocks = 1 << discriminantbits();
/* 322 */     this.discriminantmask = (1 << discriminantbits()) - 1;
/* 323 */     this.nonmasklength = 32 - discriminantbits();
/*     */   }
/*     */   
/*     */   public int discriminantbits() {
/*     */     return this.discriminantbits;
/*     */   }
/*     */   
/*     */   public int numblocks() {
/*     */     return this.numblocks;
/*     */   }
/*     */   
/*     */   public int discriminantmask() {
/*     */     return this.discriminantmask;
/*     */   }
/*     */   
/*     */   public int nonmasklength() {
/* 323 */     return this.nonmasklength;
/*     */   }
/*     */   
/*     */   public <T> ParHashSetCombiner<T> apply() {
/* 325 */     return new ParHashSetCombiner$$anon$1();
/*     */   }
/*     */   
/*     */   public static class ParHashSetCombiner$$anon$1 extends ParHashSetCombiner<T> {
/*     */     public ParHashSetCombiner$$anon$1() {
/* 325 */       super(scala.collection.mutable.FlatHashTable$.MODULE$.defaultLoadFactor());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParHashSetCombiner$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */