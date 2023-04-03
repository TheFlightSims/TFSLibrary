/*     */ package scala.runtime;
/*     */ 
/*     */ public final class RichByte$ {
/*     */   public static final RichByte$ MODULE$;
/*     */   
/*     */   public final int hashCode$extension(byte $this) {
/*  11 */     return BoxesRunTime.boxToByte($this).hashCode();
/*     */   }
/*     */   
/*     */   public final boolean equals$extension(byte $this, Object x$1) {
/*     */     boolean bool;
/*  11 */     if (x$1 instanceof RichByte) {
/* 236 */       bool = true;
/*     */     } else {
/* 236 */       bool = false;
/*     */     } 
/*     */     if (bool) {
/*     */       byte b = ((RichByte)x$1).self();
/*     */       if (($this == b));
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   private RichByte$() {
/*     */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final scala.math.Numeric$ByteIsIntegral$ num$extension(byte $this) {
/*     */     return scala.math.Numeric$ByteIsIntegral$.MODULE$;
/*     */   }
/*     */   
/*     */   public final scala.math.Ordering$Byte$ ord$extension(byte $this) {
/*     */     return scala.math.Ordering$Byte$.MODULE$;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichByte$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */