/*     */ package scala.collection.parallel.immutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.HashMap;
/*     */ import scala.collection.mutable.UnrolledBuffer;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class HashMapCombiner$ {
/*     */   public static final HashMapCombiner$ MODULE$;
/*     */   
/*     */   private final int rootbits;
/*     */   
/*     */   private final int rootsize;
/*     */   
/*     */   public class HashMapCombiner$$anonfun$1 extends AbstractFunction1<UnrolledBuffer<Tuple2<K, V>>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(UnrolledBuffer x$3) {
/* 181 */       return !(x$3 == null);
/*     */     }
/*     */     
/*     */     public HashMapCombiner$$anonfun$1(HashMapCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public class HashMapCombiner$$anonfun$2 extends AbstractFunction1<UnrolledBuffer<Tuple2<K, V>>, UnrolledBuffer.Unrolled<Tuple2<K, V>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final UnrolledBuffer.Unrolled<Tuple2<K, V>> apply(UnrolledBuffer x$4) {
/* 181 */       return x$4.headPtr();
/*     */     }
/*     */     
/*     */     public HashMapCombiner$$anonfun$2(HashMapCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public class HashMapCombiner$$anonfun$3 extends AbstractFunction2<Object, HashMap<K, V>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int x$5, HashMap x$6) {
/* 192 */       return x$5 + x$6.size();
/*     */     }
/*     */     
/*     */     public HashMapCombiner$$anonfun$3(HashMapCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public class HashMapCombiner$$anonfun$4 extends AbstractFunction1<UnrolledBuffer<Tuple2<K, V>>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(UnrolledBuffer x$7) {
/* 203 */       return !(x$7 == null);
/*     */     }
/*     */     
/*     */     public HashMapCombiner$$anonfun$4(HashMapCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public class HashMapCombiner$$anonfun$5 extends AbstractFunction1<UnrolledBuffer<Tuple2<K, V>>, UnrolledBuffer.Unrolled<Tuple2<K, V>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final UnrolledBuffer.Unrolled<Tuple2<K, V>> apply(UnrolledBuffer x$8) {
/* 203 */       return x$8.headPtr();
/*     */     }
/*     */     
/*     */     public HashMapCombiner$$anonfun$5(HashMapCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public class HashMapCombiner$$anonfun$6 extends AbstractFunction2<Object, HashMap<K, Object>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int x$9, HashMap x$10) {
/* 214 */       return x$9 + x$10.size();
/*     */     }
/*     */     
/*     */     public HashMapCombiner$$anonfun$6(HashMapCombiner $outer) {}
/*     */   }
/*     */   
/*     */   private HashMapCombiner$() {
/* 337 */     MODULE$ = this;
/* 340 */     this.rootbits = 5;
/* 341 */     this.rootsize = 32;
/*     */   }
/*     */   
/*     */   public <K, V> HashMapCombiner<K, V> apply() {
/*     */     return new HashMapCombiner$$anon$1();
/*     */   }
/*     */   
/*     */   public static class HashMapCombiner$$anon$1 extends HashMapCombiner<K, V> {}
/*     */   
/*     */   public int rootbits() {
/*     */     return this.rootbits;
/*     */   }
/*     */   
/*     */   public int rootsize() {
/* 341 */     return this.rootsize;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\HashMapCombiner$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */