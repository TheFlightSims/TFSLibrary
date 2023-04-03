/*     */ package scala;
/*     */ 
/*     */ public final class Char$ implements AnyValCompanion {
/*     */   public static final Char$ MODULE$;
/*     */   
/*     */   private final char MinValue;
/*     */   
/*     */   private final char MaxValue;
/*     */   
/*     */   private Char$() {
/* 598 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final char MinValue() {
/* 601 */     return Character.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public final char MaxValue() {
/* 605 */     return Character.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public Character box(char x) {
/* 612 */     return Character.valueOf(x);
/*     */   }
/*     */   
/*     */   public char unbox(Object x) {
/* 622 */     return ((Character)x).charValue();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 626 */     return "object scala.Char";
/*     */   }
/*     */   
/*     */   public int char2int(char x) {
/* 630 */     return x;
/*     */   }
/*     */   
/*     */   public long char2long(char x) {
/* 631 */     return x;
/*     */   }
/*     */   
/*     */   public float char2float(char x) {
/* 632 */     return x;
/*     */   }
/*     */   
/*     */   public double char2double(char x) {
/* 633 */     return x;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Char$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */