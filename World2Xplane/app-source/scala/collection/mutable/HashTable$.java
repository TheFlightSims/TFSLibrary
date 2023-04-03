/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.collection.AbstractIterator;
/*     */ 
/*     */ public final class HashTable$ {
/*     */   public static final HashTable$ MODULE$;
/*     */   
/*     */   public class HashTable$$anon$1 extends AbstractIterator<Entry> {
/*     */     private final HashEntry<A, Entry>[] iterTable;
/*     */     
/*     */     private int idx;
/*     */     
/*     */     private HashEntry<A, Entry> es;
/*     */     
/*     */     public HashTable$$anon$1(HashTable $outer) {
/* 203 */       this.iterTable = (HashEntry<A, Entry>[])$outer.table();
/* 204 */       this.idx = HashTable$class.scala$collection$mutable$HashTable$$lastPopulatedIndex($outer);
/* 205 */       this.es = iterTable()[idx()];
/*     */     }
/*     */     
/*     */     private HashEntry<A, Entry>[] iterTable() {
/*     */       return this.iterTable;
/*     */     }
/*     */     
/*     */     private int idx() {
/*     */       return this.idx;
/*     */     }
/*     */     
/*     */     private void idx_$eq(int x$1) {
/*     */       this.idx = x$1;
/*     */     }
/*     */     
/*     */     private HashEntry<A, Entry> es() {
/* 205 */       return this.es;
/*     */     }
/*     */     
/*     */     private void es_$eq(HashEntry<A, Entry> x$1) {
/* 205 */       this.es = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 207 */       return !(es() == null);
/*     */     }
/*     */     
/*     */     public Entry next() {
/* 209 */       HashEntry<A, Entry> res = es();
/* 210 */       es_$eq((HashEntry<A, Entry>)es().next());
/* 211 */       while (es() == null && idx() > 0) {
/* 212 */         idx_$eq(idx() - 1);
/* 213 */         es_$eq(iterTable()[idx()]);
/*     */       } 
/* 215 */       return (Entry)res;
/*     */     }
/*     */   }
/*     */   
/*     */   private HashTable$() {
/* 381 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final int defaultLoadFactor() {
/* 384 */     return 750;
/*     */   }
/*     */   
/*     */   public final int loadFactorDenum() {
/* 385 */     return 1000;
/*     */   }
/*     */   
/*     */   public final int newThreshold(int _loadFactor, int size) {
/* 387 */     return (int)(size * _loadFactor / loadFactorDenum());
/*     */   }
/*     */   
/*     */   public final int sizeForThreshold(int _loadFactor, int thr) {
/* 389 */     return (int)(thr * loadFactorDenum() / _loadFactor);
/*     */   }
/*     */   
/*     */   public final int capacity(int expectedSize) {
/* 391 */     return (expectedSize == 0) ? 1 : powerOfTwo(expectedSize);
/*     */   }
/*     */   
/*     */   public int powerOfTwo(int target) {
/* 460 */     int c = target - 1;
/* 461 */     c |= c >>> 1;
/* 462 */     c |= c >>> 2;
/* 463 */     c |= c >>> 4;
/* 464 */     c |= c >>> 8;
/* 466 */     return (c | c >>> 16) + 1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\HashTable$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */