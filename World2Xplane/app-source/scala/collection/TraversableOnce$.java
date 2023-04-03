/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BooleanRef;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.NonLocalReturnControl;
/*     */ import scala.runtime.ObjectRef;
/*     */ 
/*     */ public final class TraversableOnce$ {
/*     */   public static final TraversableOnce$ MODULE$;
/*     */   
/*     */   public class TraversableOnce$$anonfun$reversed$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectRef elems$1;
/*     */     
/*     */     public final void apply(Object x$1) {
/*  99 */       this.elems$1.elem = ((List)this.elems$1.elem).$colon$colon(x$1);
/*     */     }
/*     */     
/*     */     public TraversableOnce$$anonfun$reversed$1(TraversableOnce $outer, ObjectRef elems$1) {}
/*     */   }
/*     */   
/*     */   public class TraversableOnce$$anonfun$size$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final IntRef result$1;
/*     */     
/*     */     public final void apply(Object x) {
/* 105 */       this.result$1.elem++;
/*     */     }
/*     */     
/*     */     public TraversableOnce$$anonfun$size$1(TraversableOnce $outer, IntRef result$1) {}
/*     */   }
/*     */   
/*     */   public class TraversableOnce$$anonfun$count$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final IntRef cnt$1;
/*     */     
/*     */     private final Function1 p$1;
/*     */     
/*     */     public TraversableOnce$$anonfun$count$1(TraversableOnce $outer, IntRef cnt$1, Function1 p$1) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 114 */       if (BoxesRunTime.unboxToBoolean(this.p$1.apply(x)))
/* 114 */         this.cnt$1.elem++; 
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableOnce$$anonfun$collectFirst$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object nonLocalReturnKey1$1;
/*     */     
/*     */     private final PartialFunction pf$1;
/*     */     
/*     */     public TraversableOnce$$anonfun$collectFirst$1(TraversableOnce $outer, Object nonLocalReturnKey1$1, PartialFunction pf$1) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 132 */       if (this.pf$1.isDefinedAt(x))
/* 133 */         throw new NonLocalReturnControl(this.nonLocalReturnKey1$1, new Some(this.pf$1.apply(x))); 
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableOnce$$anonfun$foldLeft$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectRef result$2;
/*     */     
/*     */     private final Function2 op$1;
/*     */     
/*     */     public final void apply(Object x) {
/* 144 */       this.result$2.elem = this.op$1.apply(this.result$2.elem, x);
/*     */     }
/*     */     
/*     */     public TraversableOnce$$anonfun$foldLeft$1(TraversableOnce $outer, ObjectRef result$2, Function2 op$1) {}
/*     */   }
/*     */   
/*     */   public class TraversableOnce$$anonfun$foldRight$1 extends AbstractFunction2<B, A, B> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 op$2;
/*     */     
/*     */     public final B apply(Object x, Object y) {
/* 149 */       return (B)this.op$2.apply(y, x);
/*     */     }
/*     */     
/*     */     public TraversableOnce$$anonfun$foldRight$1(TraversableOnce $outer, Function2 op$2) {}
/*     */   }
/*     */   
/*     */   public class TraversableOnce$$anonfun$reduceLeft$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BooleanRef first$1;
/*     */     
/*     */     private final ObjectRef acc$1;
/*     */     
/*     */     private final Function2 op$3;
/*     */     
/*     */     public TraversableOnce$$anonfun$reduceLeft$1(TraversableOnce $outer, BooleanRef first$1, ObjectRef acc$1, Function2 op$3) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 173 */       if (this.first$1.elem) {
/* 174 */         this.acc$1.elem = x;
/* 175 */         this.first$1.elem = false;
/*     */       } else {
/* 177 */         this.acc$1.elem = this.op$3.apply(this.acc$1.elem, x);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableOnce$$anonfun$reduceRight$1 extends AbstractFunction2<B, A, B> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function2 op$4;
/*     */     
/*     */     public final B apply(Object x, Object y) {
/* 186 */       return (B)this.op$4.apply(y, x);
/*     */     }
/*     */     
/*     */     public TraversableOnce$$anonfun$reduceRight$1(TraversableOnce $outer, Function2 op$4) {}
/*     */   }
/*     */   
/*     */   public class TraversableOnce$$anonfun$sum$1 extends AbstractFunction2<B, B, B> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Numeric num$1;
/*     */     
/*     */     public final B apply(Object x, Object y) {
/* 203 */       return (B)this.num$1.plus(x, y);
/*     */     }
/*     */     
/*     */     public TraversableOnce$$anonfun$sum$1(TraversableOnce $outer, Numeric num$1) {}
/*     */   }
/*     */   
/*     */   public class TraversableOnce$$anonfun$product$1 extends AbstractFunction2<B, B, B> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Numeric num$2;
/*     */     
/*     */     public final B apply(Object x, Object y) {
/* 205 */       return (B)this.num$2.times(x, y);
/*     */     }
/*     */     
/*     */     public TraversableOnce$$anonfun$product$1(TraversableOnce $outer, Numeric num$2) {}
/*     */   }
/*     */   
/*     */   public class TraversableOnce$$anonfun$min$1 extends AbstractFunction2<A, A, A> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Ordering cmp$1;
/*     */     
/*     */     public final A apply(Object x, Object y) {
/* 211 */       return this.cmp$1.lteq(x, y) ? (A)x : (A)y;
/*     */     }
/*     */     
/*     */     public TraversableOnce$$anonfun$min$1(TraversableOnce $outer, Ordering cmp$1) {}
/*     */   }
/*     */   
/*     */   public class TraversableOnce$$anonfun$max$1 extends AbstractFunction2<A, A, A> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Ordering cmp$2;
/*     */     
/*     */     public final A apply(Object x, Object y) {
/* 218 */       return this.cmp$2.gteq(x, y) ? (A)x : (A)y;
/*     */     }
/*     */     
/*     */     public TraversableOnce$$anonfun$max$1(TraversableOnce $outer, Ordering cmp$2) {}
/*     */   }
/*     */   
/*     */   public class TraversableOnce$$anonfun$maxBy$1 extends AbstractFunction2<A, A, A> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$1;
/*     */     
/*     */     private final Ordering cmp$3;
/*     */     
/*     */     public final A apply(Object x, Object y) {
/* 225 */       return this.cmp$3.gteq(this.f$1.apply(x), this.f$1.apply(y)) ? (A)x : (A)y;
/*     */     }
/*     */     
/*     */     public TraversableOnce$$anonfun$maxBy$1(TraversableOnce $outer, Function1 f$1, Ordering cmp$3) {}
/*     */   }
/*     */   
/*     */   public class TraversableOnce$$anonfun$minBy$1 extends AbstractFunction2<A, A, A> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$2;
/*     */     
/*     */     private final Ordering cmp$4;
/*     */     
/*     */     public final A apply(Object x, Object y) {
/* 231 */       return this.cmp$4.lteq(this.f$2.apply(x), this.f$2.apply(y)) ? (A)x : (A)y;
/*     */     }
/*     */     
/*     */     public TraversableOnce$$anonfun$minBy$1(TraversableOnce $outer, Function1 f$2, Ordering cmp$4) {}
/*     */   }
/*     */   
/*     */   public class TraversableOnce$$anonfun$toMap$1 extends AbstractFunction1<A, Builder<Tuple2<T, U>, Map<T, U>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$1;
/*     */     
/*     */     private final scala.Predef$.less.colon.less ev$1;
/*     */     
/*     */     public TraversableOnce$$anonfun$toMap$1(TraversableOnce $outer, Builder b$1, scala.Predef$.less.colon.less ev$1) {}
/*     */     
/*     */     public final Builder<Tuple2<T, U>, Map<T, U>> apply(Object x) {
/* 280 */       return this.b$1.$plus$eq(this.ev$1.apply(x));
/*     */     }
/*     */   }
/*     */   
/*     */   public class TraversableOnce$$anonfun$addString$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BooleanRef first$2;
/*     */     
/*     */     private final StringBuilder b$2;
/*     */     
/*     */     private final String sep$1;
/*     */     
/*     */     public TraversableOnce$$anonfun$addString$1(TraversableOnce $outer, BooleanRef first$2, StringBuilder b$2, String sep$1) {}
/*     */     
/*     */     public final Object apply(Object x) {
/* 322 */       this.b$2.append(x);
/* 323 */       this.first$2.elem = false;
/* 326 */       this.b$2.append(this.sep$1);
/* 327 */       return this.first$2.elem ? BoxedUnit.UNIT : this.b$2.append(x);
/*     */     }
/*     */   }
/*     */   
/*     */   private TraversableOnce$() {
/* 382 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <T> TraversableOnce.OnceCanBuildFrom<T> traversableOnceCanBuildFrom() {
/* 384 */     return new TraversableOnce.OnceCanBuildFrom<T>();
/*     */   }
/*     */   
/*     */   public <A> TraversableOnce.MonadOps<A> wrapTraversableOnce(TraversableOnce<A> trav) {
/* 386 */     return new TraversableOnce.MonadOps<A>(trav);
/*     */   }
/*     */   
/*     */   public <A> TraversableOnce.ForceImplicitAmbiguity alternateImplicit(TraversableOnce trav) {
/* 388 */     return new TraversableOnce.ForceImplicitAmbiguity();
/*     */   }
/*     */   
/*     */   public <A, CC> TraversableOnce.FlattenOps<A> flattenTraversableOnce(TraversableOnce<?> travs, Function1 ev) {
/* 390 */     return new TraversableOnce.FlattenOps<A>(MonadOps(travs).map(ev));
/*     */   }
/*     */   
/*     */   public <A> TraversableOnce.OnceCanBuildFrom<A> OnceCanBuildFrom() {
/* 426 */     return new TraversableOnce.OnceCanBuildFrom<A>();
/*     */   }
/*     */   
/*     */   public <A> TraversableOnce.MonadOps<A> MonadOps(TraversableOnce<A> trav) {
/* 439 */     return new TraversableOnce.MonadOps<A>(trav);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\TraversableOnce$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */