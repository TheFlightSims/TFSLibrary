/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function2;
/*     */ import scala.Function3;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.ListBuffer;
/*     */ import scala.collection.mutable.MapBuilder;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractFunction3;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class IntMap$ {
/*     */   public static final IntMap$ MODULE$;
/*     */   
/*     */   private IntMap$() {
/*  46 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A, B> Object canBuildFrom() {
/*  48 */     return new IntMap$$anon$1();
/*     */   }
/*     */   
/*     */   public static class IntMap$$anon$1 implements CanBuildFrom<IntMap<A>, Tuple2<Object, B>, IntMap<B>> {
/*     */     public Builder<Tuple2<Object, B>, IntMap<B>> apply(IntMap from) {
/*  49 */       return apply();
/*     */     }
/*     */     
/*     */     public Builder<Tuple2<Object, B>, IntMap<B>> apply() {
/*  50 */       return (Builder<Tuple2<Object, B>, IntMap<B>>)new MapBuilder((GenMap)IntMap$.MODULE$.empty());
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> IntMap<T> empty() {
/*  53 */     return IntMap.Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public <T> IntMap<T> singleton(int key, Object value) {
/*  54 */     return new IntMap.Tip<T>(key, (T)value);
/*     */   }
/*     */   
/*     */   public <T> IntMap<T> apply(Seq elems) {
/*  56 */     return (IntMap<T>)elems.foldLeft(empty(), (Function2)new IntMap$$anonfun$apply$1());
/*     */   }
/*     */   
/*     */   public static class IntMap$$anonfun$apply$1 extends AbstractFunction2<IntMap<T>, Tuple2<Object, T>, IntMap<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final IntMap<T> apply(IntMap x, Tuple2 y) {
/*  56 */       return x.updated(y._1$mcI$sp(), y._2());
/*     */     }
/*     */   }
/*     */   
/*     */   public class IntMap$$anonfun$toList$1 extends AbstractFunction1<Tuple2<Object, T>, ListBuffer<Tuple2<Object, T>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ListBuffer buffer$1;
/*     */     
/*     */     public final ListBuffer<Tuple2<Object, T>> apply(Tuple2 x$1) {
/* 167 */       return this.buffer$1.$plus$eq(x$1);
/*     */     }
/*     */     
/*     */     public IntMap$$anonfun$toList$1(IntMap $outer, ListBuffer buffer$1) {}
/*     */   }
/*     */   
/*     */   public class IntMap$$anonfun$unionWith$1 extends AbstractFunction2<S, S, S> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function3 f$1;
/*     */     
/*     */     private final IntMap.Tip x6$1;
/*     */     
/*     */     public final S apply(Object x, Object y) {
/* 373 */       return (S)this.f$1.apply(BoxesRunTime.boxToInteger(this.x6$1.key()), y, x);
/*     */     }
/*     */     
/*     */     public IntMap$$anonfun$unionWith$1(IntMap $outer, Function3 f$1, IntMap.Tip x6$1) {}
/*     */   }
/*     */   
/*     */   public class IntMap$$anonfun$unionWith$2 extends AbstractFunction2<T, S, S> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function3 f$1;
/*     */     
/*     */     private final IntMap.Tip x8$1;
/*     */     
/*     */     public final S apply(Object x, Object y) {
/* 374 */       return (S)this.f$1.apply(BoxesRunTime.boxToInteger(this.x8$1.key()), x, y);
/*     */     }
/*     */     
/*     */     public IntMap$$anonfun$unionWith$2(IntMap $outer, Function3 f$1, IntMap.Tip x8$1) {}
/*     */   }
/*     */   
/*     */   public class IntMap$$anonfun$intersection$1 extends AbstractFunction3<Object, T, R, T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final T apply(int key, Object value, Object value2) {
/* 422 */       return (T)value;
/*     */     }
/*     */     
/*     */     public IntMap$$anonfun$intersection$1(IntMap $outer) {}
/*     */   }
/*     */   
/*     */   public class IntMap$$anonfun$$plus$plus$1 extends AbstractFunction3<Object, S, S, S> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final S apply(int key, Object x, Object y) {
/* 425 */       return (S)y;
/*     */     }
/*     */     
/*     */     public IntMap$$anonfun$$plus$plus$1(IntMap $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\IntMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */