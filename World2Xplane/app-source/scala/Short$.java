/*     */ package scala;
/*     */ 
/*     */ public final class Short$ implements AnyValCompanion {
/*     */   public static final Short$ MODULE$;
/*     */   
/*     */   private final short MinValue;
/*     */   
/*     */   private final short MaxValue;
/*     */   
/*     */   private Short$() {
/* 598 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final short MinValue() {
/* 601 */     return java.lang.Short.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public final short MaxValue() {
/* 605 */     return java.lang.Short.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public java.lang.Short box(short x) {
/* 612 */     return java.lang.Short.valueOf(x);
/*     */   }
/*     */   
/*     */   public short unbox(Object x) {
/* 622 */     return ((java.lang.Short)x).shortValue();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 626 */     return "object scala.Short";
/*     */   }
/*     */   
/*     */   public int short2int(short x) {
/* 630 */     return x;
/*     */   }
/*     */   
/*     */   public long short2long(short x) {
/* 631 */     return x;
/*     */   }
/*     */   
/*     */   public float short2float(short x) {
/* 632 */     return x;
/*     */   }
/*     */   
/*     */   public double short2double(short x) {
/* 633 */     return x;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Short$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */