/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ 
/*     */ public final class ArrayOps$ {
/*     */   public static final ArrayOps$ MODULE$;
/*     */   
/*     */   public class ArrayOps$$anonfun$flatten$1 extends AbstractFunction1<T, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(Object x0$1) {
/*     */       boolean bool;
/*  66 */       if (x0$1 instanceof scala.collection.IndexedSeq) {
/*  66 */         bool = ((SeqLike)x0$1).size();
/*     */       } else {
/*  66 */         bool = false;
/*     */       } 
/*  66 */       return bool;
/*     */     }
/*     */     
/*     */     public ArrayOps$$anonfun$flatten$1(ArrayOps $outer) {}
/*     */   }
/*     */   
/*     */   public class ArrayOps$$anonfun$flatten$2 extends AbstractFunction1<T, ArrayBuilder<U>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ArrayBuilder b$1;
/*     */     
/*     */     private final Function1 asTrav$1;
/*     */     
/*     */     public ArrayOps$$anonfun$flatten$2(ArrayOps $outer, ArrayBuilder b$1, Function1 asTrav$1) {}
/*     */     
/*     */     public final ArrayBuilder<U> apply(Object xs) {
/*  68 */       return (ArrayBuilder<U>)this.b$1.$plus$plus$eq((TraversableOnce)this.asTrav$1.apply(xs));
/*     */     }
/*     */   }
/*     */   
/*     */   public class ArrayOps$$anonfun$1 extends AbstractFunction1<U, ArrayBuilder<U>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ArrayBuilder<U> apply(Object x$1) {
/*  83 */       return ArrayOps$class.mkRowBuilder$1(this.$outer);
/*     */     }
/*     */     
/*     */     public ArrayOps$$anonfun$1(ArrayOps $outer) {}
/*     */   }
/*     */   
/*     */   public class ArrayOps$$anonfun$transpose$1 extends AbstractFunction1<T, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ArrayBuilder[] bs$1;
/*     */     
/*     */     private final Function1 asArray$1;
/*     */     
/*     */     public ArrayOps$$anonfun$transpose$1(ArrayOps $outer, ArrayBuilder[] bs$1, Function1 asArray$1) {}
/*     */     
/*     */     public final void apply(Object xs) {
/*  85 */       IntRef i = new IntRef(0);
/*  86 */       scala.Predef$.MODULE$.genericArrayOps(this.asArray$1.apply(xs)).foreach((Function1)new ArrayOps$$anonfun$transpose$1$$anonfun$apply$1(this, ($anonfun$transpose$1)i));
/*     */     }
/*     */     
/*     */     public class ArrayOps$$anonfun$transpose$1$$anonfun$apply$1 extends AbstractFunction1<U, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final IntRef i$1;
/*     */       
/*     */       public ArrayOps$$anonfun$transpose$1$$anonfun$apply$1(ArrayOps$$anonfun$transpose$1 $outer, IntRef i$1) {}
/*     */       
/*     */       public final void apply(Object x) {
/*  87 */         this.$outer.bs$1[this.i$1.elem].$plus$eq(x);
/*  88 */         this.i$1.elem++;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class ArrayOps$$anonfun$transpose$2 extends AbstractFunction1<ArrayBuilder<U>, Builder<Object, Object[]>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder bb$1;
/*     */     
/*     */     public final Builder<Object, Object[]> apply(ArrayBuilder b) {
/*  91 */       return (Builder)this.bb$1.$plus$eq(b.result());
/*     */     }
/*     */     
/*     */     public ArrayOps$$anonfun$transpose$2(ArrayOps $outer, Builder bb$1) {}
/*     */   }
/*     */   
/*     */   private ArrayOps$() {
/* 105 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ArrayOps$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */