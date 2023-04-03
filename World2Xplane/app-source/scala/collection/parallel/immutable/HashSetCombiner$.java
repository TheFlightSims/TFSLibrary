/*     */ package scala.collection.parallel.immutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.HashSet;
/*     */ import scala.collection.mutable.UnrolledBuffer;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class HashSetCombiner$ {
/*     */   public static final HashSetCombiner$ MODULE$;
/*     */   
/*     */   private final int rootbits;
/*     */   
/*     */   private final int rootsize;
/*     */   
/*     */   public class HashSetCombiner$$anonfun$1 extends AbstractFunction1<UnrolledBuffer<Object>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(UnrolledBuffer x$3) {
/* 154 */       return !(x$3 == null);
/*     */     }
/*     */     
/*     */     public HashSetCombiner$$anonfun$1(HashSetCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public class HashSetCombiner$$anonfun$2 extends AbstractFunction1<UnrolledBuffer<Object>, UnrolledBuffer.Unrolled<Object>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final UnrolledBuffer.Unrolled<Object> apply(UnrolledBuffer x$4) {
/* 154 */       return x$4.headPtr();
/*     */     }
/*     */     
/*     */     public HashSetCombiner$$anonfun$2(HashSetCombiner $outer) {}
/*     */   }
/*     */   
/*     */   public class HashSetCombiner$$anonfun$3 extends AbstractFunction2<Object, HashSet<T>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(int x$5, HashSet x$6) {
/* 165 */       return x$5 + x$6.size();
/*     */     }
/*     */     
/*     */     public HashSetCombiner$$anonfun$3(HashSetCombiner $outer) {}
/*     */   }
/*     */   
/*     */   private HashSetCombiner$() {
/* 217 */     MODULE$ = this;
/* 220 */     this.rootbits = 5;
/* 221 */     this.rootsize = 32;
/*     */   }
/*     */   
/*     */   public <T> HashSetCombiner<T> apply() {
/*     */     return new HashSetCombiner$$anon$1();
/*     */   }
/*     */   
/*     */   public static class HashSetCombiner$$anon$1 extends HashSetCombiner<T> {}
/*     */   
/*     */   public int rootbits() {
/*     */     return this.rootbits;
/*     */   }
/*     */   
/*     */   public int rootsize() {
/* 221 */     return this.rootsize;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\immutable\HashSetCombiner$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */