/*     */ package org.apache.commons.math3.exception;
/*     */ 
/*     */ import org.apache.commons.math3.exception.util.Localizable;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class NoBracketingException extends MathIllegalArgumentException {
/*     */   private static final long serialVersionUID = -3629324471511904459L;
/*     */   
/*     */   private final double lo;
/*     */   
/*     */   private final double hi;
/*     */   
/*     */   private final double fLo;
/*     */   
/*     */   private final double fHi;
/*     */   
/*     */   public NoBracketingException(double lo, double hi, double fLo, double fHi) {
/*  51 */     this((Localizable)LocalizedFormats.SAME_SIGN_AT_ENDPOINTS, lo, hi, fLo, fHi, new Object[0]);
/*     */   }
/*     */   
/*     */   public NoBracketingException(Localizable specific, double lo, double hi, double fLo, double fHi, Object... args) {
/*  68 */     super(specific, new Object[] { Double.valueOf(lo), Double.valueOf(hi), Double.valueOf(fLo), Double.valueOf(fHi), args });
/*  69 */     this.lo = lo;
/*  70 */     this.hi = hi;
/*  71 */     this.fLo = fLo;
/*  72 */     this.fHi = fHi;
/*     */   }
/*     */   
/*     */   public double getLo() {
/*  81 */     return this.lo;
/*     */   }
/*     */   
/*     */   public double getHi() {
/*  89 */     return this.hi;
/*     */   }
/*     */   
/*     */   public double getFLo() {
/*  97 */     return this.fLo;
/*     */   }
/*     */   
/*     */   public double getFHi() {
/* 105 */     return this.fHi;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exception\NoBracketingException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */