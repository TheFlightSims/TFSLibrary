/*     */ package scala.runtime;
/*     */ 
/*     */ public final class RichLong$ {
/*     */   public static final RichLong$ MODULE$;
/*     */   
/*     */   public final int hashCode$extension(long $this) {
/*  11 */     return BoxesRunTime.boxToLong($this).hashCode();
/*     */   }
/*     */   
/*     */   public final boolean equals$extension(long $this, Object x$1) {
/*     */     boolean bool;
/*  11 */     if (x$1 instanceof RichLong) {
/* 236 */       bool = true;
/*     */     } else {
/* 236 */       bool = false;
/*     */     } 
/*     */     if (bool) {
/*     */       long l = ((RichLong)x$1).self();
/*     */       if (($this == l));
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   private RichLong$() {
/*     */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final scala.math.Numeric$LongIsIntegral$ num$extension(long $this) {
/*     */     return scala.math.Numeric$LongIsIntegral$.MODULE$;
/*     */   }
/*     */   
/*     */   public final scala.math.Ordering$Long$ ord$extension(long $this) {
/*     */     return scala.math.Ordering$Long$.MODULE$;
/*     */   }
/*     */   
/*     */   public final String toBinaryString$extension(long $this) {
/*     */     return Long.toBinaryString($this);
/*     */   }
/*     */   
/*     */   public final String toHexString$extension(long $this) {
/*     */     return Long.toHexString($this);
/*     */   }
/*     */   
/*     */   public final String toOctalString$extension(long $this) {
/*     */     return Long.toOctalString($this);
/*     */   }
/*     */   
/*     */   public final boolean isValidByte$extension(long $this) {
/*     */     return ((byte)(int)$this == $this);
/*     */   }
/*     */   
/*     */   public final boolean isValidShort$extension(long $this) {
/*     */     return ((short)(int)$this == $this);
/*     */   }
/*     */   
/*     */   public final boolean isValidChar$extension(long $this) {
/*     */     return ((char)(int)$this == $this);
/*     */   }
/*     */   
/*     */   public final boolean isValidInt$extension(long $this) {
/*     */     return ((int)$this == $this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichLong$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */