/*     */ package scala.runtime;
/*     */ 
/*     */ import scala.collection.immutable.Range;
/*     */ 
/*     */ public final class RichInt$ {
/*     */   public static final RichInt$ MODULE$;
/*     */   
/*     */   public final int hashCode$extension(int $this) {
/*  15 */     return BoxesRunTime.boxToInteger($this).hashCode();
/*     */   }
/*     */   
/*     */   public final boolean equals$extension(int $this, Object x$1) {
/*     */     boolean bool;
/*  15 */     if (x$1 instanceof RichInt) {
/* 236 */       bool = true;
/*     */     } else {
/* 236 */       bool = false;
/*     */     } 
/*     */     if (bool) {
/*     */       int i = ((RichInt)x$1).self();
/*     */       if (($this == i));
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   private RichInt$() {
/*     */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final scala.math.Numeric$IntIsIntegral$ num$extension(int $this) {
/*     */     return scala.math.Numeric$IntIsIntegral$.MODULE$;
/*     */   }
/*     */   
/*     */   public final scala.math.Ordering$Int$ ord$extension(int $this) {
/*     */     return scala.math.Ordering$Int$.MODULE$;
/*     */   }
/*     */   
/*     */   public final boolean isWhole$extension(int $this) {
/*     */     return true;
/*     */   }
/*     */   
/*     */   public final Range until$extension0(int $this, int end) {
/*     */     return scala.collection.immutable.Range$.MODULE$.apply($this, end);
/*     */   }
/*     */   
/*     */   public final Range until$extension1(int $this, int end, int step) {
/*     */     return scala.collection.immutable.Range$.MODULE$.apply($this, end, step);
/*     */   }
/*     */   
/*     */   public final Range.Inclusive to$extension0(int $this, int end) {
/*     */     return scala.collection.immutable.Range$.MODULE$.inclusive($this, end);
/*     */   }
/*     */   
/*     */   public final Range.Inclusive to$extension1(int $this, int end, int step) {
/*     */     return scala.collection.immutable.Range$.MODULE$.inclusive($this, end, step);
/*     */   }
/*     */   
/*     */   public final int min$extension(int $this, int that) {
/*     */     return ($this < that) ? $this : that;
/*     */   }
/*     */   
/*     */   public final int max$extension(int $this, int that) {
/*     */     return ($this > that) ? $this : that;
/*     */   }
/*     */   
/*     */   public final int abs$extension(int $this) {
/*     */     return ($this < 0) ? -$this : $this;
/*     */   }
/*     */   
/*     */   public final String toBinaryString$extension(int $this) {
/*     */     return Integer.toBinaryString($this);
/*     */   }
/*     */   
/*     */   public final String toHexString$extension(int $this) {
/*     */     return Integer.toHexString($this);
/*     */   }
/*     */   
/*     */   public final String toOctalString$extension(int $this) {
/*     */     return Integer.toOctalString($this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichInt$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */