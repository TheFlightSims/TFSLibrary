/*     */ package scala.util;
/*     */ 
/*     */ import java.util.Random;
/*     */ import scala.Serializable;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class Random$ extends Random {
/*     */   public static final Random$ MODULE$;
/*     */   
/*     */   public class Random$$anonfun$nextString$1 extends AbstractFunction0.mcC.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final char apply() {
/*  88 */       return this.$outer.scala$util$Random$$safeChar$1();
/*     */     }
/*     */     
/*     */     public char apply$mcC$sp() {
/*  88 */       return this.$outer.scala$util$Random$$safeChar$1();
/*     */     }
/*     */     
/*     */     public Random$$anonfun$nextString$1(Random $outer) {}
/*     */   }
/*     */   
/*     */   public class Random$$anonfun$shuffle$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ArrayBuffer buf$1;
/*     */     
/*     */     public final void apply(int n) {
/* 115 */       apply$mcVI$sp(n);
/*     */     }
/*     */     
/*     */     public Random$$anonfun$shuffle$1(Random $outer, ArrayBuffer buf$1) {}
/*     */     
/*     */     public void apply$mcVI$sp(int n) {
/* 116 */       int k = this.$outer.nextInt(n);
/* 117 */       this.$outer.scala$util$Random$$swap$1(n - 1, k, this.buf$1);
/*     */     }
/*     */   }
/*     */   
/*     */   public class Random$$anonfun$alphanumeric$1 extends AbstractFunction0.mcC.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final char apply() {
/* 131 */       return this.$outer.nextPrintableChar();
/*     */     }
/*     */     
/*     */     public char apply$mcC$sp() {
/* 131 */       return this.$outer.nextPrintableChar();
/*     */     }
/*     */     
/*     */     public Random$$anonfun$alphanumeric$1(Random $outer) {}
/*     */   }
/*     */   
/*     */   public class Random$$anonfun$alphanumeric$2 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(char c) {
/* 131 */       return this.$outer.scala$util$Random$$isAlphaNum$1(c);
/*     */     }
/*     */     
/*     */     public Random$$anonfun$alphanumeric$2(Random $outer) {}
/*     */   }
/*     */   
/*     */   private Random$() {
/* 141 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public Random javaRandomToRandom(Random r) {
/* 143 */     return new Random(r);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\Random$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */