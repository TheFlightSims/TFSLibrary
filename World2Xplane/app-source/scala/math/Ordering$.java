/*     */ package scala.math;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.Tuple4;
/*     */ import scala.Tuple5;
/*     */ import scala.Tuple6;
/*     */ import scala.Tuple7;
/*     */ import scala.Tuple8;
/*     */ import scala.Tuple9;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class Ordering$ implements LowPriorityOrderingImplicits, Serializable {
/*     */   public static final Ordering$ MODULE$;
/*     */   
/*     */   public class Ordering$$anon$4 implements Ordering<T> {
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 109 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 109 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 109 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 109 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 109 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 109 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public T max(Object x, Object y) {
/* 109 */       return (T)Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public T min(Object x, Object y) {
/* 109 */       return (T)Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 109 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<T>.Ops mkOrderingOps(Object lhs) {
/* 109 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Ordering$$anon$4(Ordering $outer) {
/* 109 */       PartialOrdering$class.$init$(this);
/* 109 */       Ordering$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Ordering<T> reverse() {
/* 110 */       return this.$outer;
/*     */     }
/*     */     
/*     */     public int compare(Object x, Object y) {
/* 111 */       return this.$outer.compare(y, x);
/*     */     }
/*     */   }
/*     */   
/*     */   public class Ordering$$anon$5 implements Ordering<U> {
/*     */     private final Function1 f$2;
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 121 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 121 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 121 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 121 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 121 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 121 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public U max(Object x, Object y) {
/* 121 */       return (U)Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public U min(Object x, Object y) {
/* 121 */       return (U)Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<U> reverse() {
/* 121 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 121 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<U>.Ops mkOrderingOps(Object lhs) {
/* 121 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Ordering$$anon$5(Ordering $outer, Function1 f$2) {
/* 121 */       PartialOrdering$class.$init$(this);
/* 121 */       Ordering$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Object x, Object y) {
/* 122 */       return this.$outer.compare(this.f$2.apply(x), this.f$2.apply(y));
/*     */     }
/*     */   }
/*     */   
/*     */   public <A> Ordering<A> ordered(Function1 evidence$1) {
/* 162 */     return LowPriorityOrderingImplicits$class.ordered(this, evidence$1);
/*     */   }
/*     */   
/*     */   public <A> Ordering<A> comparatorToOrdering(Comparator cmp) {
/* 162 */     return LowPriorityOrderingImplicits$class.comparatorToOrdering(this, cmp);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 162 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Ordering$() {
/* 162 */     MODULE$ = this;
/* 162 */     LowPriorityOrderingImplicits$class.$init$(this);
/*     */   }
/*     */   
/*     */   public <T> Ordering<T> apply(Ordering<T> ord) {
/* 163 */     return ord;
/*     */   }
/*     */   
/*     */   public <T> Ordering<T> fromLessThan(Function2 cmp) {
/* 199 */     return new Ordering$$anon$9(cmp);
/*     */   }
/*     */   
/*     */   public static class Ordering$$anon$9 implements Ordering<T> {
/*     */     private final Function2 cmp$1;
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 199 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 199 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public T max(Object x, Object y) {
/* 199 */       return (T)Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public T min(Object x, Object y) {
/* 199 */       return (T)Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<T> reverse() {
/* 199 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 199 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<T>.Ops mkOrderingOps(Object lhs) {
/* 199 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Ordering$$anon$9(Function2 cmp$1) {
/* 199 */       PartialOrdering$class.$init$(this);
/* 199 */       Ordering$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Object x, Object y) {
/* 200 */       return BoxesRunTime.unboxToBoolean(this.cmp$1.apply(x, y)) ? -1 : (BoxesRunTime.unboxToBoolean(this.cmp$1.apply(y, x)) ? 1 : 0);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 202 */       return BoxesRunTime.unboxToBoolean(this.cmp$1.apply(x, y));
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 203 */       return BoxesRunTime.unboxToBoolean(this.cmp$1.apply(y, x));
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 204 */       return !BoxesRunTime.unboxToBoolean(this.cmp$1.apply(x, y));
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 205 */       return !BoxesRunTime.unboxToBoolean(this.cmp$1.apply(y, x));
/*     */     }
/*     */   }
/*     */   
/*     */   public <T, S> Ordering<T> by(Function1 f, Ordering ord) {
/* 219 */     Ordering$$anonfun$by$1 ordering$$anonfun$by$1 = new Ordering$$anonfun$by$1(f, ord);
/* 219 */     return new Ordering$$anon$9((Function2)ordering$$anonfun$by$1);
/*     */   }
/*     */   
/*     */   public static class Ordering$$anonfun$by$1 extends AbstractFunction2<T, T, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$1;
/*     */     
/*     */     private final Ordering ord$3;
/*     */     
/*     */     public final boolean apply(Object x, Object y) {
/* 219 */       return this.ord$3.lt(this.f$1.apply(x), this.f$1.apply(y));
/*     */     }
/*     */     
/*     */     public Ordering$$anonfun$by$1(Function1 f$1, Ordering ord$3) {}
/*     */   }
/*     */   
/*     */   public <T> Ordering<Option<T>> Option(Ordering ord) {
/* 341 */     return new Ordering$$anon$3(ord);
/*     */   }
/*     */   
/*     */   public static class Ordering$$anon$3 implements Ordering.OptionOrdering<T> {
/*     */     private final Ordering<T> optionOrdering;
/*     */     
/*     */     public int compare(Option x, Option y) {
/* 341 */       return Ordering.OptionOrdering$class.compare(this, x, y);
/*     */     }
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 341 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 341 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 341 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 341 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 341 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 341 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Option<T> max(Object x, Object y) {
/* 341 */       return (Option<T>)Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Option<T> min(Object x, Object y) {
/* 341 */       return (Option<T>)Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Option<T>> reverse() {
/* 341 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 341 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Option<T>>.Ops mkOrderingOps(Object lhs) {
/* 341 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Ordering<T> optionOrdering() {
/* 341 */       return this.optionOrdering;
/*     */     }
/*     */     
/*     */     public Ordering$$anon$3(Ordering<T> ord$2) {
/* 341 */       PartialOrdering$class.$init$(this);
/* 341 */       Ordering$class.$init$(this);
/* 341 */       Ordering.OptionOrdering$class.$init$(this);
/* 341 */       this.optionOrdering = ord$2;
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> Ordering<Iterable<T>> Iterable(Ordering ord) {
/* 344 */     return new Ordering$$anon$10(ord);
/*     */   }
/*     */   
/*     */   public static class Ordering$$anon$10 implements Ordering<Iterable<T>> {
/*     */     private final Ordering ord$1;
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 344 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 344 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 344 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 344 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 344 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 344 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Iterable<T> max(Object x, Object y) {
/* 344 */       return (Iterable<T>)Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Iterable<T> min(Object x, Object y) {
/* 344 */       return (Iterable<T>)Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Iterable<T>> reverse() {
/* 344 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 344 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Iterable<T>>.Ops mkOrderingOps(Object lhs) {
/* 344 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Ordering$$anon$10(Ordering ord$1) {
/* 344 */       PartialOrdering$class.$init$(this);
/* 344 */       Ordering$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Iterable x, Iterable y) {
/* 346 */       Iterator xe = x.iterator();
/* 347 */       Iterator ye = y.iterator();
/* 349 */       while (xe.hasNext() && ye.hasNext()) {
/* 350 */         int res = this.ord$1.compare(xe.next(), ye.next());
/* 351 */         if (res != 0)
/* 351 */           return res; 
/*     */       } 
/* 354 */       return Ordering.Boolean$.MODULE$.compare(xe.hasNext(), ye.hasNext());
/*     */     }
/*     */   }
/*     */   
/*     */   public <T1, T2> Ordering<Tuple2<T1, T2>> Tuple2(Ordering ord1, Ordering ord2) {
/* 359 */     return new Ordering$$anon$11(ord1, ord2);
/*     */   }
/*     */   
/*     */   public static class Ordering$$anon$11 implements Ordering<Tuple2<T1, T2>> {
/*     */     private final Ordering ord1$8;
/*     */     
/*     */     private final Ordering ord2$8;
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 359 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 359 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 359 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 359 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 359 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 359 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple2<T1, T2> max(Object x, Object y) {
/* 359 */       return (Tuple2<T1, T2>)Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple2<T1, T2> min(Object x, Object y) {
/* 359 */       return (Tuple2<T1, T2>)Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple2<T1, T2>> reverse() {
/* 359 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 359 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple2<T1, T2>>.Ops mkOrderingOps(Object lhs) {
/* 359 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Ordering$$anon$11(Ordering ord1$8, Ordering ord2$8) {
/* 359 */       PartialOrdering$class.$init$(this);
/* 359 */       Ordering$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Tuple2 x, Tuple2 y) {
/* 361 */       int compare1 = this.ord1$8.compare(x._1(), y._1());
/* 362 */       if (compare1 != 0)
/* 362 */         return compare1; 
/* 363 */       int compare2 = this.ord2$8.compare(x._2(), y._2());
/* 364 */       if (compare2 != 0)
/* 364 */         return compare2; 
/* 365 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public <T1, T2, T3> Ordering<Tuple3<T1, T2, T3>> Tuple3(Ordering ord1, Ordering ord2, Ordering ord3) {
/* 370 */     return new Ordering$$anon$12(ord1, ord2, ord3);
/*     */   }
/*     */   
/*     */   public static class Ordering$$anon$12 implements Ordering<Tuple3<T1, T2, T3>> {
/*     */     private final Ordering ord1$7;
/*     */     
/*     */     private final Ordering ord2$7;
/*     */     
/*     */     private final Ordering ord3$7;
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 370 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 370 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 370 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 370 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 370 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 370 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple3<T1, T2, T3> max(Object x, Object y) {
/* 370 */       return (Tuple3<T1, T2, T3>)Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple3<T1, T2, T3> min(Object x, Object y) {
/* 370 */       return (Tuple3<T1, T2, T3>)Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple3<T1, T2, T3>> reverse() {
/* 370 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 370 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple3<T1, T2, T3>>.Ops mkOrderingOps(Object lhs) {
/* 370 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Ordering$$anon$12(Ordering ord1$7, Ordering ord2$7, Ordering ord3$7) {
/* 370 */       PartialOrdering$class.$init$(this);
/* 370 */       Ordering$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Tuple3 x, Tuple3 y) {
/* 372 */       int compare1 = this.ord1$7.compare(x._1(), y._1());
/* 373 */       if (compare1 != 0)
/* 373 */         return compare1; 
/* 374 */       int compare2 = this.ord2$7.compare(x._2(), y._2());
/* 375 */       if (compare2 != 0)
/* 375 */         return compare2; 
/* 376 */       int compare3 = this.ord3$7.compare(x._3(), y._3());
/* 377 */       if (compare3 != 0)
/* 377 */         return compare3; 
/* 378 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public <T1, T2, T3, T4> Ordering<Tuple4<T1, T2, T3, T4>> Tuple4(Ordering ord1, Ordering ord2, Ordering ord3, Ordering ord4) {
/* 383 */     return new Ordering$$anon$13(ord1, ord2, ord3, ord4);
/*     */   }
/*     */   
/*     */   public static class Ordering$$anon$13 implements Ordering<Tuple4<T1, T2, T3, T4>> {
/*     */     private final Ordering ord1$6;
/*     */     
/*     */     private final Ordering ord2$6;
/*     */     
/*     */     private final Ordering ord3$6;
/*     */     
/*     */     private final Ordering ord4$6;
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 383 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 383 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 383 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 383 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 383 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 383 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple4<T1, T2, T3, T4> max(Object x, Object y) {
/* 383 */       return (Tuple4<T1, T2, T3, T4>)Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple4<T1, T2, T3, T4> min(Object x, Object y) {
/* 383 */       return (Tuple4<T1, T2, T3, T4>)Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple4<T1, T2, T3, T4>> reverse() {
/* 383 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 383 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple4<T1, T2, T3, T4>>.Ops mkOrderingOps(Object lhs) {
/* 383 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Ordering$$anon$13(Ordering ord1$6, Ordering ord2$6, Ordering ord3$6, Ordering ord4$6) {
/* 383 */       PartialOrdering$class.$init$(this);
/* 383 */       Ordering$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Tuple4 x, Tuple4 y) {
/* 385 */       int compare1 = this.ord1$6.compare(x._1(), y._1());
/* 386 */       if (compare1 != 0)
/* 386 */         return compare1; 
/* 387 */       int compare2 = this.ord2$6.compare(x._2(), y._2());
/* 388 */       if (compare2 != 0)
/* 388 */         return compare2; 
/* 389 */       int compare3 = this.ord3$6.compare(x._3(), y._3());
/* 390 */       if (compare3 != 0)
/* 390 */         return compare3; 
/* 391 */       int compare4 = this.ord4$6.compare(x._4(), y._4());
/* 392 */       if (compare4 != 0)
/* 392 */         return compare4; 
/* 393 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public <T1, T2, T3, T4, T5> Ordering<Tuple5<T1, T2, T3, T4, T5>> Tuple5(Ordering ord1, Ordering ord2, Ordering ord3, Ordering ord4, Ordering ord5) {
/* 398 */     return new Ordering$$anon$14(ord1, ord2, ord3, ord4, ord5);
/*     */   }
/*     */   
/*     */   public static class Ordering$$anon$14 implements Ordering<Tuple5<T1, T2, T3, T4, T5>> {
/*     */     private final Ordering ord1$5;
/*     */     
/*     */     private final Ordering ord2$5;
/*     */     
/*     */     private final Ordering ord3$5;
/*     */     
/*     */     private final Ordering ord4$5;
/*     */     
/*     */     private final Ordering ord5$5;
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 398 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 398 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 398 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 398 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 398 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 398 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple5<T1, T2, T3, T4, T5> max(Object x, Object y) {
/* 398 */       return (Tuple5<T1, T2, T3, T4, T5>)Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple5<T1, T2, T3, T4, T5> min(Object x, Object y) {
/* 398 */       return (Tuple5<T1, T2, T3, T4, T5>)Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple5<T1, T2, T3, T4, T5>> reverse() {
/* 398 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 398 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple5<T1, T2, T3, T4, T5>>.Ops mkOrderingOps(Object lhs) {
/* 398 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Ordering$$anon$14(Ordering ord1$5, Ordering ord2$5, Ordering ord3$5, Ordering ord4$5, Ordering ord5$5) {
/* 398 */       PartialOrdering$class.$init$(this);
/* 398 */       Ordering$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Tuple5 x, Tuple5 y) {
/* 400 */       int compare1 = this.ord1$5.compare(x._1(), y._1());
/* 401 */       if (compare1 != 0)
/* 401 */         return compare1; 
/* 402 */       int compare2 = this.ord2$5.compare(x._2(), y._2());
/* 403 */       if (compare2 != 0)
/* 403 */         return compare2; 
/* 404 */       int compare3 = this.ord3$5.compare(x._3(), y._3());
/* 405 */       if (compare3 != 0)
/* 405 */         return compare3; 
/* 406 */       int compare4 = this.ord4$5.compare(x._4(), y._4());
/* 407 */       if (compare4 != 0)
/* 407 */         return compare4; 
/* 408 */       int compare5 = this.ord5$5.compare(x._5(), y._5());
/* 409 */       if (compare5 != 0)
/* 409 */         return compare5; 
/* 410 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public <T1, T2, T3, T4, T5, T6> Ordering<Tuple6<T1, T2, T3, T4, T5, T6>> Tuple6(Ordering ord1, Ordering ord2, Ordering ord3, Ordering ord4, Ordering ord5, Ordering ord6) {
/* 415 */     return new Ordering$$anon$15(ord1, ord2, ord3, ord4, ord5, ord6);
/*     */   }
/*     */   
/*     */   public static class Ordering$$anon$15 implements Ordering<Tuple6<T1, T2, T3, T4, T5, T6>> {
/*     */     private final Ordering ord1$4;
/*     */     
/*     */     private final Ordering ord2$4;
/*     */     
/*     */     private final Ordering ord3$4;
/*     */     
/*     */     private final Ordering ord4$4;
/*     */     
/*     */     private final Ordering ord5$4;
/*     */     
/*     */     private final Ordering ord6$4;
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 415 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 415 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 415 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 415 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 415 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 415 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple6<T1, T2, T3, T4, T5, T6> max(Object x, Object y) {
/* 415 */       return (Tuple6<T1, T2, T3, T4, T5, T6>)Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple6<T1, T2, T3, T4, T5, T6> min(Object x, Object y) {
/* 415 */       return (Tuple6<T1, T2, T3, T4, T5, T6>)Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple6<T1, T2, T3, T4, T5, T6>> reverse() {
/* 415 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 415 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple6<T1, T2, T3, T4, T5, T6>>.Ops mkOrderingOps(Object lhs) {
/* 415 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Ordering$$anon$15(Ordering ord1$4, Ordering ord2$4, Ordering ord3$4, Ordering ord4$4, Ordering ord5$4, Ordering ord6$4) {
/* 415 */       PartialOrdering$class.$init$(this);
/* 415 */       Ordering$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Tuple6 x, Tuple6 y) {
/* 417 */       int compare1 = this.ord1$4.compare(x._1(), y._1());
/* 418 */       if (compare1 != 0)
/* 418 */         return compare1; 
/* 419 */       int compare2 = this.ord2$4.compare(x._2(), y._2());
/* 420 */       if (compare2 != 0)
/* 420 */         return compare2; 
/* 421 */       int compare3 = this.ord3$4.compare(x._3(), y._3());
/* 422 */       if (compare3 != 0)
/* 422 */         return compare3; 
/* 423 */       int compare4 = this.ord4$4.compare(x._4(), y._4());
/* 424 */       if (compare4 != 0)
/* 424 */         return compare4; 
/* 425 */       int compare5 = this.ord5$4.compare(x._5(), y._5());
/* 426 */       if (compare5 != 0)
/* 426 */         return compare5; 
/* 427 */       int compare6 = this.ord6$4.compare(x._6(), y._6());
/* 428 */       if (compare6 != 0)
/* 428 */         return compare6; 
/* 429 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public <T1, T2, T3, T4, T5, T6, T7> Ordering<Tuple7<T1, T2, T3, T4, T5, T6, T7>> Tuple7(Ordering ord1, Ordering ord2, Ordering ord3, Ordering ord4, Ordering ord5, Ordering ord6, Ordering ord7) {
/* 434 */     return new Ordering$$anon$16(ord1, ord2, ord3, ord4, ord5, ord6, ord7);
/*     */   }
/*     */   
/*     */   public static class Ordering$$anon$16 implements Ordering<Tuple7<T1, T2, T3, T4, T5, T6, T7>> {
/*     */     private final Ordering ord1$3;
/*     */     
/*     */     private final Ordering ord2$3;
/*     */     
/*     */     private final Ordering ord3$3;
/*     */     
/*     */     private final Ordering ord4$3;
/*     */     
/*     */     private final Ordering ord5$3;
/*     */     
/*     */     private final Ordering ord6$3;
/*     */     
/*     */     private final Ordering ord7$3;
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 434 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 434 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 434 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 434 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 434 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 434 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple7<T1, T2, T3, T4, T5, T6, T7> max(Object x, Object y) {
/* 434 */       return (Tuple7<T1, T2, T3, T4, T5, T6, T7>)Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple7<T1, T2, T3, T4, T5, T6, T7> min(Object x, Object y) {
/* 434 */       return (Tuple7<T1, T2, T3, T4, T5, T6, T7>)Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple7<T1, T2, T3, T4, T5, T6, T7>> reverse() {
/* 434 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 434 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple7<T1, T2, T3, T4, T5, T6, T7>>.Ops mkOrderingOps(Object lhs) {
/* 434 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Ordering$$anon$16(Ordering ord1$3, Ordering ord2$3, Ordering ord3$3, Ordering ord4$3, Ordering ord5$3, Ordering ord6$3, Ordering ord7$3) {
/* 434 */       PartialOrdering$class.$init$(this);
/* 434 */       Ordering$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Tuple7 x, Tuple7 y) {
/* 436 */       int compare1 = this.ord1$3.compare(x._1(), y._1());
/* 437 */       if (compare1 != 0)
/* 437 */         return compare1; 
/* 438 */       int compare2 = this.ord2$3.compare(x._2(), y._2());
/* 439 */       if (compare2 != 0)
/* 439 */         return compare2; 
/* 440 */       int compare3 = this.ord3$3.compare(x._3(), y._3());
/* 441 */       if (compare3 != 0)
/* 441 */         return compare3; 
/* 442 */       int compare4 = this.ord4$3.compare(x._4(), y._4());
/* 443 */       if (compare4 != 0)
/* 443 */         return compare4; 
/* 444 */       int compare5 = this.ord5$3.compare(x._5(), y._5());
/* 445 */       if (compare5 != 0)
/* 445 */         return compare5; 
/* 446 */       int compare6 = this.ord6$3.compare(x._6(), y._6());
/* 447 */       if (compare6 != 0)
/* 447 */         return compare6; 
/* 448 */       int compare7 = this.ord7$3.compare(x._7(), y._7());
/* 449 */       if (compare7 != 0)
/* 449 */         return compare7; 
/* 450 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public <T1, T2, T3, T4, T5, T6, T7, T8> Ordering<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>> Tuple8(Ordering ord1, Ordering ord2, Ordering ord3, Ordering ord4, Ordering ord5, Ordering ord6, Ordering ord7, Ordering ord8) {
/* 455 */     return new Ordering$$anon$17(ord1, ord2, ord3, ord4, ord5, ord6, ord7, ord8);
/*     */   }
/*     */   
/*     */   public static class Ordering$$anon$17 implements Ordering<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>> {
/*     */     private final Ordering ord1$2;
/*     */     
/*     */     private final Ordering ord2$2;
/*     */     
/*     */     private final Ordering ord3$2;
/*     */     
/*     */     private final Ordering ord4$2;
/*     */     
/*     */     private final Ordering ord5$2;
/*     */     
/*     */     private final Ordering ord6$2;
/*     */     
/*     */     private final Ordering ord7$2;
/*     */     
/*     */     private final Ordering ord8$2;
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 455 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 455 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 455 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 455 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 455 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 455 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> max(Object x, Object y) {
/* 455 */       return (Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>)Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> min(Object x, Object y) {
/* 455 */       return (Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>)Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>> reverse() {
/* 455 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 455 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>>.Ops mkOrderingOps(Object lhs) {
/* 455 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Ordering$$anon$17(Ordering ord1$2, Ordering ord2$2, Ordering ord3$2, Ordering ord4$2, Ordering ord5$2, Ordering ord6$2, Ordering ord7$2, Ordering ord8$2) {
/* 455 */       PartialOrdering$class.$init$(this);
/* 455 */       Ordering$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Tuple8 x, Tuple8 y) {
/* 457 */       int compare1 = this.ord1$2.compare(x._1(), y._1());
/* 458 */       if (compare1 != 0)
/* 458 */         return compare1; 
/* 459 */       int compare2 = this.ord2$2.compare(x._2(), y._2());
/* 460 */       if (compare2 != 0)
/* 460 */         return compare2; 
/* 461 */       int compare3 = this.ord3$2.compare(x._3(), y._3());
/* 462 */       if (compare3 != 0)
/* 462 */         return compare3; 
/* 463 */       int compare4 = this.ord4$2.compare(x._4(), y._4());
/* 464 */       if (compare4 != 0)
/* 464 */         return compare4; 
/* 465 */       int compare5 = this.ord5$2.compare(x._5(), y._5());
/* 466 */       if (compare5 != 0)
/* 466 */         return compare5; 
/* 467 */       int compare6 = this.ord6$2.compare(x._6(), y._6());
/* 468 */       if (compare6 != 0)
/* 468 */         return compare6; 
/* 469 */       int compare7 = this.ord7$2.compare(x._7(), y._7());
/* 470 */       if (compare7 != 0)
/* 470 */         return compare7; 
/* 471 */       int compare8 = this.ord8$2.compare(x._8(), y._8());
/* 472 */       if (compare8 != 0)
/* 472 */         return compare8; 
/* 473 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public <T1, T2, T3, T4, T5, T6, T7, T8, T9> Ordering<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> Tuple9(Ordering ord1, Ordering ord2, Ordering ord3, Ordering ord4, Ordering ord5, Ordering ord6, Ordering ord7, Ordering ord8, Ordering ord9) {
/* 478 */     return new Ordering$$anon$18(ord1, ord2, ord3, ord4, ord5, ord6, ord7, ord8, ord9);
/*     */   }
/*     */   
/*     */   public static class Ordering$$anon$18 implements Ordering<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> {
/*     */     private final Ordering ord1$1;
/*     */     
/*     */     private final Ordering ord2$1;
/*     */     
/*     */     private final Ordering ord3$1;
/*     */     
/*     */     private final Ordering ord4$1;
/*     */     
/*     */     private final Ordering ord5$1;
/*     */     
/*     */     private final Ordering ord6$1;
/*     */     
/*     */     private final Ordering ord7$1;
/*     */     
/*     */     private final Ordering ord8$1;
/*     */     
/*     */     private final Ordering ord9$1;
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 478 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 478 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 478 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 478 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 478 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 478 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> max(Object x, Object y) {
/* 478 */       return (Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>)Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> min(Object x, Object y) {
/* 478 */       return (Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>)Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>> reverse() {
/* 478 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 478 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>>.Ops mkOrderingOps(Object lhs) {
/* 478 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Ordering$$anon$18(Ordering ord1$1, Ordering ord2$1, Ordering ord3$1, Ordering ord4$1, Ordering ord5$1, Ordering ord6$1, Ordering ord7$1, Ordering ord8$1, Ordering ord9$1) {
/* 478 */       PartialOrdering$class.$init$(this);
/* 478 */       Ordering$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Tuple9 x, Tuple9 y) {
/* 480 */       int compare1 = this.ord1$1.compare(x._1(), y._1());
/* 481 */       if (compare1 != 0)
/* 481 */         return compare1; 
/* 482 */       int compare2 = this.ord2$1.compare(x._2(), y._2());
/* 483 */       if (compare2 != 0)
/* 483 */         return compare2; 
/* 484 */       int compare3 = this.ord3$1.compare(x._3(), y._3());
/* 485 */       if (compare3 != 0)
/* 485 */         return compare3; 
/* 486 */       int compare4 = this.ord4$1.compare(x._4(), y._4());
/* 487 */       if (compare4 != 0)
/* 487 */         return compare4; 
/* 488 */       int compare5 = this.ord5$1.compare(x._5(), y._5());
/* 489 */       if (compare5 != 0)
/* 489 */         return compare5; 
/* 490 */       int compare6 = this.ord6$1.compare(x._6(), y._6());
/* 491 */       if (compare6 != 0)
/* 491 */         return compare6; 
/* 492 */       int compare7 = this.ord7$1.compare(x._7(), y._7());
/* 493 */       if (compare7 != 0)
/* 493 */         return compare7; 
/* 494 */       int compare8 = this.ord8$1.compare(x._8(), y._8());
/* 495 */       if (compare8 != 0)
/* 495 */         return compare8; 
/* 496 */       int compare9 = this.ord9$1.compare(x._9(), y._9());
/* 497 */       if (compare9 != 0)
/* 497 */         return compare9; 
/* 498 */       return 0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\Ordering$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */