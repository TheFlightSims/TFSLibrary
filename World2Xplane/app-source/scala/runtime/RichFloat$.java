/*     */ package scala.runtime;
/*     */ 
/*     */ public final class RichFloat$ {
/*     */   public static final RichFloat$ MODULE$;
/*     */   
/*     */   public final int hashCode$extension(float $this) {
/*  12 */     return BoxesRunTime.boxToFloat($this).hashCode();
/*     */   }
/*     */   
/*     */   public final boolean equals$extension(float $this, Object x$1) {
/*     */     boolean bool;
/*  12 */     if (x$1 instanceof RichFloat) {
/* 236 */       bool = true;
/*     */     } else {
/* 236 */       bool = false;
/*     */     } 
/*     */     if (bool) {
/*     */       float f = ((RichFloat)x$1).self();
/*     */       if (($this == f));
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   private RichFloat$() {
/*     */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final scala.math.Numeric$FloatIsFractional$ num$extension(float $this) {
/*     */     return scala.math.Numeric$FloatIsFractional$.MODULE$;
/*     */   }
/*     */   
/*     */   public final scala.math.Ordering$Float$ ord$extension(float $this) {
/*     */     return scala.math.Ordering$Float$.MODULE$;
/*     */   }
/*     */   
/*     */   public final scala.math.Numeric$FloatAsIfIntegral$ integralNum$extension(float $this) {
/*     */     return scala.math.Numeric$FloatAsIfIntegral$.MODULE$;
/*     */   }
/*     */   
/*     */   public final int round$extension(float $this) {
/*     */     return scala.math.package$.MODULE$.round($this);
/*     */   }
/*     */   
/*     */   public final float ceil$extension(float $this) {
/*     */     return (float)scala.math.package$.MODULE$.ceil($this);
/*     */   }
/*     */   
/*     */   public final float floor$extension(float $this) {
/*     */     return (float)scala.math.package$.MODULE$.floor($this);
/*     */   }
/*     */   
/*     */   public final float toRadians$extension(float $this) {
/*     */     return (float)scala.math.package$.MODULE$.toRadians($this);
/*     */   }
/*     */   
/*     */   public final float toDegrees$extension(float $this) {
/*     */     return (float)scala.math.package$.MODULE$.toDegrees($this);
/*     */   }
/*     */   
/*     */   public final boolean isInfinity$extension(float $this) {
/*     */     return Float.isInfinite($this);
/*     */   }
/*     */   
/*     */   public final boolean isPosInfinity$extension(float $this) {
/*     */     return (isInfinity$extension($this) && $this > 0.0F);
/*     */   }
/*     */   
/*     */   public final boolean isNegInfinity$extension(float $this) {
/*     */     return (isInfinity$extension($this) && $this < 0.0F);
/*     */   }
/*     */   
/*     */   public final boolean isValidByte$extension(float $this) {
/*     */     return ((byte)(int)$this == $this);
/*     */   }
/*     */   
/*     */   public final boolean isValidShort$extension(float $this) {
/*     */     return ((short)(int)$this == $this);
/*     */   }
/*     */   
/*     */   public final boolean isValidChar$extension(float $this) {
/*     */     return ((char)(int)$this == $this);
/*     */   }
/*     */   
/*     */   public final boolean isValidInt$extension(float $this) {
/*     */     int i = (int)$this;
/*     */     return (i == $this && i != Integer.MAX_VALUE);
/*     */   }
/*     */   
/*     */   public final boolean isWhole$extension(float $this) {
/*     */     long l = (long)$this;
/*     */     return ((float)l == $this || (l == Long.MAX_VALUE && $this < Float.POSITIVE_INFINITY) || (l == Long.MIN_VALUE && $this > Float.NEGATIVE_INFINITY));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichFloat$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */