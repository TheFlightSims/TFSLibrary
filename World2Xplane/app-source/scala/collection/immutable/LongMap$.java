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
/*     */ public final class LongMap$ {
/*     */   public static final LongMap$ MODULE$;
/*     */   
/*     */   private LongMap$() {
/*  46 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A, B> Object canBuildFrom() {
/*  48 */     return new LongMap$$anon$1();
/*     */   }
/*     */   
/*     */   public static class LongMap$$anon$1 implements CanBuildFrom<LongMap<A>, Tuple2<Object, B>, LongMap<B>> {
/*     */     public Builder<Tuple2<Object, B>, LongMap<B>> apply(LongMap from) {
/*  49 */       return apply();
/*     */     }
/*     */     
/*     */     public Builder<Tuple2<Object, B>, LongMap<B>> apply() {
/*  50 */       return (Builder<Tuple2<Object, B>, LongMap<B>>)new MapBuilder((GenMap)LongMap$.MODULE$.empty());
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> LongMap<T> empty() {
/*  53 */     return LongMap.Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public <T> LongMap<T> singleton(long key, Object value) {
/*  54 */     return new LongMap.Tip<T>(key, (T)value);
/*     */   }
/*     */   
/*     */   public <T> LongMap<T> apply(Seq elems) {
/*  56 */     return (LongMap<T>)elems.foldLeft(empty(), (Function2)new LongMap$$anonfun$apply$1());
/*     */   }
/*     */   
/*     */   public static class LongMap$$anonfun$apply$1 extends AbstractFunction2<LongMap<T>, Tuple2<Object, T>, LongMap<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final LongMap<T> apply(LongMap x, Tuple2 y) {
/*  56 */       return x.updated(y._1$mcJ$sp(), y._2());
/*     */     }
/*     */   }
/*     */   
/*     */   public class LongMap$$anonfun$toList$1 extends AbstractFunction1<Tuple2<Object, T>, ListBuffer<Tuple2<Object, T>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ListBuffer buffer$1;
/*     */     
/*     */     public final ListBuffer<Tuple2<Object, T>> apply(Tuple2 x$1) {
/* 163 */       return this.buffer$1.$plus$eq(x$1);
/*     */     }
/*     */     
/*     */     public LongMap$$anonfun$toList$1(LongMap $outer, ListBuffer buffer$1) {}
/*     */   }
/*     */   
/*     */   public class LongMap$$anonfun$unionWith$1 extends AbstractFunction2<S, S, S> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function3 f$1;
/*     */     
/*     */     private final LongMap.Tip x6$1;
/*     */     
/*     */     public final S apply(Object x, Object y) {
/* 368 */       return (S)this.f$1.apply(BoxesRunTime.boxToLong(this.x6$1.key()), y, x);
/*     */     }
/*     */     
/*     */     public LongMap$$anonfun$unionWith$1(LongMap $outer, Function3 f$1, LongMap.Tip x6$1) {}
/*     */   }
/*     */   
/*     */   public class LongMap$$anonfun$unionWith$2 extends AbstractFunction2<T, S, S> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function3 f$1;
/*     */     
/*     */     private final LongMap.Tip x8$1;
/*     */     
/*     */     public final S apply(Object x, Object y) {
/* 369 */       return (S)this.f$1.apply(BoxesRunTime.boxToLong(this.x8$1.key()), x, y);
/*     */     }
/*     */     
/*     */     public LongMap$$anonfun$unionWith$2(LongMap $outer, Function3 f$1, LongMap.Tip x8$1) {}
/*     */   }
/*     */   
/*     */   public class LongMap$$anonfun$intersection$1 extends AbstractFunction3<Object, T, R, T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final T apply(long key, Object value, Object value2) {
/* 417 */       return (T)value;
/*     */     }
/*     */     
/*     */     public LongMap$$anonfun$intersection$1(LongMap $outer) {}
/*     */   }
/*     */   
/*     */   public class LongMap$$anonfun$$plus$plus$1 extends AbstractFunction3<Object, S, S, S> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final S apply(long key, Object x, Object y) {
/* 420 */       return (S)y;
/*     */     }
/*     */     
/*     */     public LongMap$$anonfun$$plus$plus$1(LongMap $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\LongMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */