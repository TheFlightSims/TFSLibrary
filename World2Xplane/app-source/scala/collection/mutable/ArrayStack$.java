/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.SeqFactory;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class ArrayStack$ extends SeqFactory<ArrayStack> implements Serializable {
/*     */   public static final ArrayStack$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/*  22 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private ArrayStack$() {
/*  22 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<ArrayStack<?>, A, ArrayStack<A>> canBuildFrom() {
/*  23 */     return (CanBuildFrom<ArrayStack<?>, A, ArrayStack<A>>)ReusableCBF();
/*     */   }
/*     */   
/*     */   public <A> Builder<A, ArrayStack<A>> newBuilder() {
/*  24 */     return new ArrayStack<A>();
/*     */   }
/*     */   
/*     */   public ArrayStack<scala.runtime.Nothing$> empty() {
/*  25 */     return new ArrayStack<scala.runtime.Nothing$>();
/*     */   }
/*     */   
/*     */   public <A> ArrayStack<A> apply(Seq elems, ClassTag evidence$1) {
/*  27 */     Object[] els = (Object[])elems.reverseMap((Function1)new ArrayStack$$anonfun$1(), scala.collection.package$.MODULE$.breakOut(scala.Array$.MODULE$.canBuildFrom(scala.reflect.ClassTag$.MODULE$.AnyRef())));
/*  28 */     return (els.length == 0) ? new ArrayStack<A>() : 
/*  29 */       new ArrayStack<A>(els, els.length);
/*     */   }
/*     */   
/*     */   public static class ArrayStack$$anonfun$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Object apply(Object x$1) {
/*     */       return x$1;
/*     */     }
/*     */   }
/*     */   
/*     */   public Object[] growArray(Object[] x) {
/*  33 */     Object[] y = new Object[scala.math.package$.MODULE$.max(x.length * 2, 1)];
/*  34 */     scala.Array$.MODULE$.copy(x, 0, y, 0, x.length);
/*  35 */     return y;
/*     */   }
/*     */   
/*     */   public Object[] clone(Object[] x) {
/*  39 */     Object[] y = new Object[x.length];
/*  40 */     scala.Array$.MODULE$.copy(x, 0, y, 0, x.length);
/*  41 */     return y;
/*     */   }
/*     */   
/*     */   public class ArrayStack$$anonfun$$plus$plus$eq$1 extends AbstractFunction1<T, ArrayStack<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final ArrayStack<T> apply(Object x) {
/* 160 */       return this.$outer.$plus$eq((T)x);
/*     */     }
/*     */     
/*     */     public ArrayStack$$anonfun$$plus$plus$eq$1(ArrayStack $outer) {}
/*     */   }
/*     */   
/*     */   public class ArrayStack$$anon$1 extends AbstractIterator<T> {
/*     */     private int currentIndex;
/*     */     
/*     */     public ArrayStack$$anon$1(ArrayStack $outer) {
/* 228 */       this.currentIndex = $outer.scala$collection$mutable$ArrayStack$$index();
/*     */     }
/*     */     
/*     */     private int currentIndex() {
/* 228 */       return this.currentIndex;
/*     */     }
/*     */     
/*     */     private void currentIndex_$eq(int x$1) {
/* 228 */       this.currentIndex = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 229 */       return (currentIndex() > 0);
/*     */     }
/*     */     
/*     */     public T next() {
/* 231 */       currentIndex_$eq(currentIndex() - 1);
/* 232 */       return (T)this.$outer.scala$collection$mutable$ArrayStack$$table()[currentIndex()];
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ArrayStack$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */