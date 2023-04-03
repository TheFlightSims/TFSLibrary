/*     */ package scala.runtime;
/*     */ 
/*     */ public final class RichBoolean$ {
/*     */   public static final RichBoolean$ MODULE$;
/*     */   
/*     */   private RichBoolean$() {
/*  11 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final boolean equals$extension(boolean $this, Object x$1) {
/*     */     boolean bool;
/*  11 */     if (x$1 instanceof RichBoolean) {
/* 236 */       bool = true;
/*     */     } else {
/* 236 */       bool = false;
/*     */     } 
/*     */     if (bool) {
/*     */       boolean bool1 = ((RichBoolean)x$1).self();
/*     */       if (($this == bool1));
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public final int hashCode$extension(boolean $this) {
/*     */     return BoxesRunTime.boxToBoolean($this).hashCode();
/*     */   }
/*     */   
/*     */   public final scala.math.Ordering$Boolean$ ord$extension(boolean $this) {
/*     */     return scala.math.Ordering$Boolean$.MODULE$;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichBoolean$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */