/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.Random;
/*     */ 
/*     */ public final class FlatHashTable$ {
/*     */   public static final FlatHashTable$ MODULE$;
/*     */   
/*     */   public class FlatHashTable$$anonfun$serializeTo$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectOutputStream out$1;
/*     */     
/*     */     public final void apply(Object x$1) {
/* 108 */       this.out$1.writeObject(x$1);
/*     */     }
/*     */     
/*     */     public FlatHashTable$$anonfun$serializeTo$1(FlatHashTable $outer, ObjectOutputStream out$1) {}
/*     */   }
/*     */   
/*     */   public class FlatHashTable$$anon$1 extends AbstractIterator<A> {
/* 188 */     private int i = 0;
/*     */     
/*     */     private int i() {
/* 188 */       return this.i;
/*     */     }
/*     */     
/*     */     private void i_$eq(int x$1) {
/* 188 */       this.i = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 190 */       for (; i() < (this.$outer.table()).length && this.$outer.table()[i()] == null; i_$eq(i() + 1));
/* 191 */       return (i() < (this.$outer.table()).length);
/*     */     }
/*     */     
/*     */     public A next() {
/* 194 */       i_$eq(i() + 1);
/* 194 */       return hasNext() ? (A)this.$outer.table()[i() - 1] : 
/* 195 */         (A)scala.collection.Iterator$.MODULE$.empty().next();
/*     */     }
/*     */     
/*     */     public FlatHashTable$$anon$1(FlatHashTable $outer) {}
/*     */   }
/*     */   
/*     */   public class FlatHashTable$$anonfun$checkConsistent$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(int i) {
/* 215 */       apply$mcVI$sp(i);
/*     */     }
/*     */     
/*     */     public FlatHashTable$$anonfun$checkConsistent$1(FlatHashTable $outer) {}
/*     */     
/*     */     public void apply$mcVI$sp(int i) {
/* 216 */       if (this.$outer.table()[i] != null && !this.$outer.containsEntry(this.$outer.table()[i])) {
/* 217 */         FlatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1 flatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1 = new FlatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1(this, i);
/* 217 */         scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 217 */         if (!false)
/* 217 */           throw new AssertionError((new StringBuilder()).append("assertion failed: ").append(flatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1.apply()).toString()); 
/*     */       } 
/*     */     }
/*     */     
/*     */     public class FlatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int i$1;
/*     */       
/*     */       public final String apply() {
/* 217 */         return (new StringBuilder()).append(this.i$1).append(" ").append(this.$outer.$outer.table()[this.i$1]).append(" ").append(scala.Predef$.MODULE$.refArrayOps(this.$outer.$outer.table()).mkString()).toString();
/*     */       }
/*     */       
/*     */       public FlatHashTable$$anonfun$checkConsistent$1$$anonfun$apply$mcVI$sp$1(FlatHashTable$$anonfun$checkConsistent$1 $outer, int i$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   private FlatHashTable$() {
/* 350 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final ThreadLocal<Random> seedGenerator() {
/* 358 */     return new FlatHashTable$$anon$2();
/*     */   }
/*     */   
/*     */   public static class FlatHashTable$$anon$2 extends ThreadLocal<Random> {
/*     */     public Random initialValue() {
/* 359 */       return new Random();
/*     */     }
/*     */   }
/*     */   
/*     */   public int defaultLoadFactor() {
/* 364 */     return 450;
/*     */   }
/*     */   
/*     */   public final int loadFactorDenum() {
/* 365 */     return 1000;
/*     */   }
/*     */   
/*     */   public int sizeForThreshold(int size, int _loadFactor) {
/* 367 */     return scala.math.package$.MODULE$.max(32, (int)(size * loadFactorDenum() / _loadFactor));
/*     */   }
/*     */   
/*     */   public int newThreshold(int _loadFactor, int size) {
/* 370 */     boolean bool = (_loadFactor < 1000 / 2) ? true : false;
/* 370 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 370 */     if (bool)
/* 372 */       return (int)(size * _loadFactor / 1000L); 
/*     */     throw new AssertionError((new StringBuilder()).append("assertion failed: ").append("loadFactor too large; must be < 0.5").toString());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\FlatHashTable$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */