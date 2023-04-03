/*     */ package scala.runtime;
/*     */ 
/*     */ public final class RichShort$ {
/*     */   public static final RichShort$ MODULE$;
/*     */   
/*     */   public final int hashCode$extension(short $this) {
/*  11 */     return BoxesRunTime.boxToShort($this).hashCode();
/*     */   }
/*     */   
/*     */   public final boolean equals$extension(short $this, Object x$1) {
/*     */     boolean bool;
/*  11 */     if (x$1 instanceof RichShort) {
/* 236 */       bool = true;
/*     */     } else {
/* 236 */       bool = false;
/*     */     } 
/*     */     if (bool) {
/*     */       short s = ((RichShort)x$1).self();
/*     */       if (($this == s));
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   private RichShort$() {
/*     */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final scala.math.Numeric$ShortIsIntegral$ num$extension(short $this) {
/*     */     return scala.math.Numeric$ShortIsIntegral$.MODULE$;
/*     */   }
/*     */   
/*     */   public final scala.math.Ordering$Short$ ord$extension(short $this) {
/*     */     return scala.math.Ordering$Short$.MODULE$;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichShort$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */