/*     */ package scala.runtime;
/*     */ 
/*     */ public final class RichDouble$ {
/*     */   public static final RichDouble$ MODULE$;
/*     */   
/*     */   public final int hashCode$extension(double $this) {
/*  12 */     return BoxesRunTime.boxToDouble($this).hashCode();
/*     */   }
/*     */   
/*     */   public final boolean equals$extension(double $this, Object x$1) {
/*     */     boolean bool;
/*  12 */     if (x$1 instanceof RichDouble) {
/* 236 */       bool = true;
/*     */     } else {
/* 236 */       bool = false;
/*     */     } 
/*     */     if (bool) {
/*     */       double d = ((RichDouble)x$1).self();
/*     */       if (($this == d));
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   private RichDouble$() {
/*     */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final scala.math.Numeric$DoubleIsFractional$ num$extension(double $this) {
/*     */     return scala.math.Numeric$DoubleIsFractional$.MODULE$;
/*     */   }
/*     */   
/*     */   public final scala.math.Ordering$Double$ ord$extension(double $this) {
/*     */     return scala.math.Ordering$Double$.MODULE$;
/*     */   }
/*     */   
/*     */   public final scala.math.Numeric$DoubleAsIfIntegral$ integralNum$extension(double $this) {
/*     */     return scala.math.Numeric$DoubleAsIfIntegral$.MODULE$;
/*     */   }
/*     */   
/*     */   public final long round$extension(double $this) {
/*     */     return scala.math.package$.MODULE$.round($this);
/*     */   }
/*     */   
/*     */   public final double ceil$extension(double $this) {
/*     */     return scala.math.package$.MODULE$.ceil($this);
/*     */   }
/*     */   
/*     */   public final double floor$extension(double $this) {
/*     */     return scala.math.package$.MODULE$.floor($this);
/*     */   }
/*     */   
/*     */   public final double toRadians$extension(double $this) {
/*     */     return scala.math.package$.MODULE$.toRadians($this);
/*     */   }
/*     */   
/*     */   public final double toDegrees$extension(double $this) {
/*     */     return scala.math.package$.MODULE$.toDegrees($this);
/*     */   }
/*     */   
/*     */   public final boolean isInfinity$extension(double $this) {
/*     */     return Double.isInfinite($this);
/*     */   }
/*     */   
/*     */   public final boolean isPosInfinity$extension(double $this) {
/*     */     return (isInfinity$extension($this) && $this > 0.0D);
/*     */   }
/*     */   
/*     */   public final boolean isNegInfinity$extension(double $this) {
/*     */     return (isInfinity$extension($this) && $this < 0.0D);
/*     */   }
/*     */   
/*     */   public final boolean isValidByte$extension(double $this) {
/*     */     return ((byte)(int)$this == $this);
/*     */   }
/*     */   
/*     */   public final boolean isValidShort$extension(double $this) {
/*     */     return ((short)(int)$this == $this);
/*     */   }
/*     */   
/*     */   public final boolean isValidChar$extension(double $this) {
/*     */     return ((char)(int)$this == $this);
/*     */   }
/*     */   
/*     */   public final boolean isValidInt$extension(double $this) {
/*     */     return ((int)$this == $this);
/*     */   }
/*     */   
/*     */   public final boolean isWhole$extension(double $this) {
/*     */     long l = (long)$this;
/*     */     return (l == $this || (l == Long.MAX_VALUE && $this < Double.POSITIVE_INFINITY) || (l == Long.MIN_VALUE && $this > Double.NEGATIVE_INFINITY));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichDouble$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */