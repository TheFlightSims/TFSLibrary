/*     */ package scala;
/*     */ 
/*     */ public abstract class PartialFunction$class {
/*     */   public static void $init$(PartialFunction $this) {}
/*     */   
/*     */   public static PartialFunction orElse(PartialFunction<?, ?> $this, PartialFunction<?, ?> that) {
/*  71 */     return new PartialFunction.OrElse<Object, Object>($this, that);
/*     */   }
/*     */   
/*     */   public static PartialFunction andThen(PartialFunction<?, ?> $this, Function1<?, ?> k) {
/*  82 */     return new PartialFunction.AndThen<Object, Object, Object>($this, k);
/*     */   }
/*     */   
/*     */   public static Function1 lift(PartialFunction<?, ?> $this) {
/*  89 */     return (Function1)new PartialFunction.Lifted<Object, Object>($this);
/*     */   }
/*     */   
/*     */   public static Object applyOrElse(PartialFunction $this, Object x, Function1 default) {
/* 118 */     return $this.isDefinedAt(x) ? $this.apply(x) : default.apply(x);
/*     */   }
/*     */   
/*     */   public static Function1 runWith(PartialFunction $this, Function1 action) {
/* 135 */     return (Function1)new PartialFunction$$anonfun$runWith$1($this, (PartialFunction<A, B>)action);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\PartialFunction$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */