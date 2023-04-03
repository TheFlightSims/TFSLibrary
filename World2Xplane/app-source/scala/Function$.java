/*     */ package scala;
/*     */ 
/*     */ import scala.collection.Seq;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractFunction3;
/*     */ import scala.runtime.AbstractFunction4;
/*     */ import scala.runtime.AbstractFunction5;
/*     */ 
/*     */ public final class Function$ {
/*     */   public static final Function$ MODULE$;
/*     */   
/*     */   private Function$() {
/*  18 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <a> Function1<a, a> chain(Seq fs) {
/*  24 */     return (Function1<a, a>)new Function$$anonfun$chain$1(fs);
/*     */   }
/*     */   
/*     */   public static class Function$$anonfun$chain$1 extends AbstractFunction1<a, a> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Seq fs$1;
/*     */     
/*     */     public final a apply(Object x) {
/*  24 */       return (a)this.fs$1.$div$colon(x, (Function2)new Function$$anonfun$chain$1$$anonfun$apply$1(this));
/*     */     }
/*     */     
/*     */     public Function$$anonfun$chain$1(Seq fs$1) {}
/*     */     
/*     */     public class Function$$anonfun$chain$1$$anonfun$apply$1 extends AbstractFunction2<a, Function1<a, a>, a> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final a apply(Object x, Function1<Object, a> f) {
/*  24 */         return f.apply(x);
/*     */       }
/*     */       
/*     */       public Function$$anonfun$chain$1$$anonfun$apply$1(Function$$anonfun$chain$1 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public <T, U> T const(Object x, Object y) {
/*  27 */     return (T)x;
/*     */   }
/*     */   
/*     */   public <T, R> PartialFunction<T, R> unlift(Function1<T, Option<R>> f) {
/*  42 */     return PartialFunction$.MODULE$.unlifted(f);
/*     */   }
/*     */   
/*     */   public <a1, a2, b> Function2<a1, a2, b> uncurried(Function1 f) {
/*  48 */     return (Function2<a1, a2, b>)new Function$$anonfun$uncurried$1(f);
/*     */   }
/*     */   
/*     */   public static class Function$$anonfun$uncurried$1 extends AbstractFunction2<a1, a2, b> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$1;
/*     */     
/*     */     public final b apply(Object x1, Object x2) {
/*  48 */       return ((Function1<Object, b>)this.f$1.apply(x1)).apply(x2);
/*     */     }
/*     */     
/*     */     public Function$$anonfun$uncurried$1(Function1 f$1) {}
/*     */   }
/*     */   
/*     */   public <a1, a2, a3, b> Function3<a1, a2, a3, b> uncurried(Function1 f) {
/*  54 */     return (Function3<a1, a2, a3, b>)new Function$$anonfun$uncurried$2(f);
/*     */   }
/*     */   
/*     */   public static class Function$$anonfun$uncurried$2 extends AbstractFunction3<a1, a2, a3, b> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$2;
/*     */     
/*     */     public final b apply(Object x1, Object x2, Object x3) {
/*  54 */       return ((Function1<Object, b>)((Function1<Object, Function1<Object, b>>)this.f$2.apply(x1)).apply(x2)).apply(x3);
/*     */     }
/*     */     
/*     */     public Function$$anonfun$uncurried$2(Function1 f$2) {}
/*     */   }
/*     */   
/*     */   public <a1, a2, a3, a4, b> Function4<a1, a2, a3, a4, b> uncurried(Function1 f) {
/*  60 */     return (Function4<a1, a2, a3, a4, b>)new Function$$anonfun$uncurried$3(f);
/*     */   }
/*     */   
/*     */   public static class Function$$anonfun$uncurried$3 extends AbstractFunction4<a1, a2, a3, a4, b> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$3;
/*     */     
/*     */     public final b apply(Object x1, Object x2, Object x3, Object x4) {
/*  60 */       return ((Function1<Object, b>)((Function1<Object, Function1<Object, b>>)((Function1<Object, Function1<Object, Function1<Object, b>>>)this.f$3.apply(x1)).apply(x2)).apply(x3)).apply(x4);
/*     */     }
/*     */     
/*     */     public Function$$anonfun$uncurried$3(Function1 f$3) {}
/*     */   }
/*     */   
/*     */   public <a1, a2, a3, a4, a5, b> Function5<a1, a2, a3, a4, a5, b> uncurried(Function1 f) {
/*  66 */     return (Function5<a1, a2, a3, a4, a5, b>)new Function$$anonfun$uncurried$4(f);
/*     */   }
/*     */   
/*     */   public static class Function$$anonfun$uncurried$4 extends AbstractFunction5<a1, a2, a3, a4, a5, b> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$4;
/*     */     
/*     */     public final b apply(Object x1, Object x2, Object x3, Object x4, Object x5) {
/*  66 */       return ((Function1<Object, b>)((Function1<Object, Function1<Object, b>>)((Function1<Object, Function1<Object, Function1<Object, b>>>)((Function1<Object, Function1<Object, Function1<Object, Function1<Object, b>>>>)this.f$4.apply(x1)).apply(x2)).apply(x3)).apply(x4)).apply(x5);
/*     */     }
/*     */     
/*     */     public Function$$anonfun$uncurried$4(Function1 f$4) {}
/*     */   }
/*     */   
/*     */   public <a1, a2, b> Function1<Tuple2<a1, a2>, b> tupled(Function2 f) {
/*  76 */     return (Function1<Tuple2<a1, a2>, b>)new Function$$anonfun$tupled$1(f);
/*     */   }
/*     */   
/*     */   public static class Function$$anonfun$tupled$1 extends AbstractFunction1<Tuple2<a1, a2>, b> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 f$5;
/*     */     
/*     */     public final b apply(Tuple2 x0$1) {
/*  76 */       if (x0$1 != null)
/*  76 */         return 
/*  77 */           (b)this.f$5.apply(x0$1._1(), x0$1._2()); 
/*     */       throw new MatchError(x0$1);
/*     */     }
/*     */     
/*     */     public Function$$anonfun$tupled$1(Function2 f$5) {}
/*     */   }
/*     */   
/*     */   public <a1, a2, a3, b> Function1<Tuple3<a1, a2, a3>, b> tupled(Function3 f) {
/*  84 */     return (Function1<Tuple3<a1, a2, a3>, b>)new Function$$anonfun$tupled$2(f);
/*     */   }
/*     */   
/*     */   public static class Function$$anonfun$tupled$2 extends AbstractFunction1<Tuple3<a1, a2, a3>, b> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function3 f$6;
/*     */     
/*     */     public final b apply(Tuple3 x0$2) {
/*  84 */       if (x0$2 != null)
/*  84 */         return 
/*  85 */           (b)this.f$6.apply(x0$2._1(), x0$2._2(), x0$2._3()); 
/*     */       throw new MatchError(x0$2);
/*     */     }
/*     */     
/*     */     public Function$$anonfun$tupled$2(Function3 f$6) {}
/*     */   }
/*     */   
/*     */   public <a1, a2, a3, a4, b> Function1<Tuple4<a1, a2, a3, a4>, b> tupled(Function4 f) {
/*  92 */     return (Function1<Tuple4<a1, a2, a3, a4>, b>)new Function$$anonfun$tupled$3(f);
/*     */   }
/*     */   
/*     */   public static class Function$$anonfun$tupled$3 extends AbstractFunction1<Tuple4<a1, a2, a3, a4>, b> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function4 f$7;
/*     */     
/*     */     public final b apply(Tuple4 x0$3) {
/*  92 */       if (x0$3 != null)
/*  92 */         return 
/*  93 */           (b)this.f$7.apply(x0$3._1(), x0$3._2(), x0$3._3(), x0$3._4()); 
/*     */       throw new MatchError(x0$3);
/*     */     }
/*     */     
/*     */     public Function$$anonfun$tupled$3(Function4 f$7) {}
/*     */   }
/*     */   
/*     */   public <a1, a2, a3, a4, a5, b> Function1<Tuple5<a1, a2, a3, a4, a5>, b> tupled(Function5 f) {
/* 100 */     return (Function1<Tuple5<a1, a2, a3, a4, a5>, b>)new Function$$anonfun$tupled$4(f);
/*     */   }
/*     */   
/*     */   public static class Function$$anonfun$tupled$4 extends AbstractFunction1<Tuple5<a1, a2, a3, a4, a5>, b> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function5 f$8;
/*     */     
/*     */     public final b apply(Tuple5 x0$4) {
/* 100 */       if (x0$4 != null)
/* 100 */         return 
/* 101 */           (b)this.f$8.apply(x0$4._1(), x0$4._2(), x0$4._3(), x0$4._4(), x0$4._5()); 
/*     */       throw new MatchError(x0$4);
/*     */     }
/*     */     
/*     */     public Function$$anonfun$tupled$4(Function5 f$8) {}
/*     */   }
/*     */   
/*     */   public <a1, a2, b> Function2<a1, a2, b> untupled(Function1 f) {
/* 108 */     return (Function2<a1, a2, b>)new Function$$anonfun$untupled$1(f);
/*     */   }
/*     */   
/*     */   public static class Function$$anonfun$untupled$1 extends AbstractFunction2<a1, a2, b> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$9;
/*     */     
/*     */     public final b apply(Object x1, Object x2) {
/* 108 */       return (b)this.f$9.apply(new Tuple2<Object, Object>(x1, x2));
/*     */     }
/*     */     
/*     */     public Function$$anonfun$untupled$1(Function1 f$9) {}
/*     */   }
/*     */   
/*     */   public <a1, a2, a3, b> Function3<a1, a2, a3, b> untupled(Function1 f) {
/* 115 */     return (Function3<a1, a2, a3, b>)new Function$$anonfun$untupled$2(f);
/*     */   }
/*     */   
/*     */   public static class Function$$anonfun$untupled$2 extends AbstractFunction3<a1, a2, a3, b> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$10;
/*     */     
/*     */     public final b apply(Object x1, Object x2, Object x3) {
/* 115 */       return (b)this.f$10.apply(new Tuple3<Object, Object, Object>(x1, x2, x3));
/*     */     }
/*     */     
/*     */     public Function$$anonfun$untupled$2(Function1 f$10) {}
/*     */   }
/*     */   
/*     */   public <a1, a2, a3, a4, b> Function4<a1, a2, a3, a4, b> untupled(Function1 f) {
/* 122 */     return (Function4<a1, a2, a3, a4, b>)new Function$$anonfun$untupled$3(f);
/*     */   }
/*     */   
/*     */   public static class Function$$anonfun$untupled$3 extends AbstractFunction4<a1, a2, a3, a4, b> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$11;
/*     */     
/*     */     public final b apply(Object x1, Object x2, Object x3, Object x4) {
/* 122 */       return (b)this.f$11.apply(new Tuple4<Object, Object, Object, Object>(x1, x2, x3, x4));
/*     */     }
/*     */     
/*     */     public Function$$anonfun$untupled$3(Function1 f$11) {}
/*     */   }
/*     */   
/*     */   public <a1, a2, a3, a4, a5, b> Function5<a1, a2, a3, a4, a5, b> untupled(Function1 f) {
/* 129 */     return (Function5<a1, a2, a3, a4, a5, b>)new Function$$anonfun$untupled$4(f);
/*     */   }
/*     */   
/*     */   public static class Function$$anonfun$untupled$4 extends AbstractFunction5<a1, a2, a3, a4, a5, b> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$12;
/*     */     
/*     */     public final b apply(Object x1, Object x2, Object x3, Object x4, Object x5) {
/* 129 */       return (b)this.f$12.apply(new Tuple5<Object, Object, Object, Object, Object>(x1, x2, x3, x4, x5));
/*     */     }
/*     */     
/*     */     public Function$$anonfun$untupled$4(Function1 f$12) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Function$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */