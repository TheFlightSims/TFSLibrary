/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SeqFactory;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class ArraySeq$ extends SeqFactory<ArraySeq> implements Serializable {
/*     */   public static final ArraySeq$ MODULE$;
/*     */   
/*     */   public class ArraySeq$$anon$1 extends ArraySeq<A> {
/*     */     private final Object[] array;
/*     */     
/*     */     public ArraySeq$$anon$1(ArraySeq $outer, Object[] cloned$1) {
/*  94 */       super($outer.length());
/*  95 */       this.array = cloned$1;
/*     */     }
/*     */     
/*     */     public Object[] array() {
/*  95 */       return this.array;
/*     */     }
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 105 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ArraySeq$() {
/* 105 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<ArraySeq<?>, A, ArraySeq<A>> canBuildFrom() {
/* 107 */     return (CanBuildFrom<ArraySeq<?>, A, ArraySeq<A>>)ReusableCBF();
/*     */   }
/*     */   
/*     */   public <A> Builder<A, ArraySeq<A>> newBuilder() {
/* 109 */     return (new ArrayBuffer<A>()).mapResult((Function1<ArrayBuffer<A>, ArraySeq<A>>)new ArraySeq$$anonfun$newBuilder$1());
/*     */   }
/*     */   
/*     */   public static class ArraySeq$$anonfun$newBuilder$1 extends AbstractFunction1<ArrayBuffer<A>, ArraySeq<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ArraySeq<A> apply(ArrayBuffer buf) {
/* 110 */       ArraySeq<A> result = new ArraySeq(buf.length());
/* 111 */       buf.copyToArray(result.array(), 0);
/* 112 */       return result;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ArraySeq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */