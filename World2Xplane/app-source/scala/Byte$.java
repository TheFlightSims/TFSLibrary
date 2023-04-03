/*     */ package scala;
/*     */ 
/*     */ public final class Byte$ implements AnyValCompanion {
/*     */   public static final Byte$ MODULE$;
/*     */   
/*     */   private final byte MinValue;
/*     */   
/*     */   private final byte MaxValue;
/*     */   
/*     */   private Byte$() {
/* 598 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final byte MinValue() {
/* 601 */     return java.lang.Byte.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public final byte MaxValue() {
/* 605 */     return java.lang.Byte.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public java.lang.Byte box(byte x) {
/* 612 */     return java.lang.Byte.valueOf(x);
/*     */   }
/*     */   
/*     */   public byte unbox(Object x) {
/* 622 */     return ((java.lang.Byte)x).byteValue();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 626 */     return "object scala.Byte";
/*     */   }
/*     */   
/*     */   public short byte2short(byte x) {
/* 630 */     return x;
/*     */   }
/*     */   
/*     */   public int byte2int(byte x) {
/* 631 */     return x;
/*     */   }
/*     */   
/*     */   public long byte2long(byte x) {
/* 632 */     return x;
/*     */   }
/*     */   
/*     */   public float byte2float(byte x) {
/* 633 */     return x;
/*     */   }
/*     */   
/*     */   public double byte2double(byte x) {
/* 634 */     return x;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Byte$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */