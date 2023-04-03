/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.collection.mutable.DefaultEntry;
/*     */ import scala.collection.mutable.UnrolledBuffer;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.VolatileObjectRef;
/*     */ 
/*     */ public final class ParHashMapCombiner$ {
/*     */   public static final ParHashMapCombiner$ MODULE$;
/*     */   
/*     */   private final int discriminantbits;
/*     */   
/*     */   private final int numblocks;
/*     */   
/*     */   private final int discriminantmask;
/*     */   
/*     */   private final int nonmasklength;
/*     */   
/*     */   public class ParHashMapCombiner$$anonfun$4 extends AbstractFunction1<UnrolledBuffer<DefaultEntry<K, V>>, UnrolledBuffer.Unrolled<DefaultEntry<K, V>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final UnrolledBuffer.Unrolled<DefaultEntry<K, V>> apply(UnrolledBuffer b) {
/* 189 */       return (b != null) ? b.headPtr() : null;
/*     */     }
/*     */     
/*     */     public ParHashMapCombiner$$anonfun$4(ParHashMapCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public class ParHashMapCombiner$$anonfun$result$1 extends AbstractFunction1<DefaultEntry<K, V>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final VolatileObjectRef table$module$1;
/*     */     
/*     */     public final void apply(DefaultEntry<K, V> elem) {
/* 207 */       this.$outer.scala$collection$parallel$mutable$ParHashMapCombiner$$table$1(this.table$module$1).insertEntry(elem);
/*     */     }
/*     */     
/*     */     public ParHashMapCombiner$$anonfun$result$1(ParHashMapCombiner $outer, VolatileObjectRef table$module$1) {}
/*     */   }
/*     */   
/*     */   private ParHashMapCombiner$() {
/* 320 */     MODULE$ = this;
/* 321 */     this.discriminantbits = 5;
/* 322 */     this.numblocks = 1 << discriminantbits();
/* 323 */     this.discriminantmask = (1 << discriminantbits()) - 1;
/* 324 */     this.nonmasklength = 32 - discriminantbits();
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
/* 324 */     return this.nonmasklength;
/*     */   }
/*     */   
/*     */   public <K, V> ParHashMapCombiner<K, V> apply() {
/* 326 */     return new ParHashMapCombiner$$anon$1();
/*     */   }
/*     */   
/*     */   public static class ParHashMapCombiner$$anon$1 extends ParHashMapCombiner<K, V> {
/*     */     public ParHashMapCombiner$$anon$1() {
/* 326 */       super(scala.collection.mutable.HashTable$.MODULE$.defaultLoadFactor());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParHashMapCombiner$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */