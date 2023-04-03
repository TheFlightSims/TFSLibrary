/*     */ package org.apache.commons.math3.random;
/*     */ 
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.OutOfRangeException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class StableRandomGenerator implements NormalizedRandomGenerator {
/*     */   private final RandomGenerator generator;
/*     */   
/*     */   private final double alpha;
/*     */   
/*     */   private final double beta;
/*     */   
/*     */   private final double zeta;
/*     */   
/*     */   public StableRandomGenerator(RandomGenerator generator, double alpha, double beta) {
/*  57 */     if (generator == null)
/*  58 */       throw new NullArgumentException(); 
/*  61 */     if (alpha <= 0.0D || alpha > 2.0D)
/*  62 */       throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_LEFT, Double.valueOf(alpha), Integer.valueOf(0), Integer.valueOf(2)); 
/*  66 */     if (beta < -1.0D || beta > 1.0D)
/*  67 */       throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_SIMPLE, Double.valueOf(beta), Integer.valueOf(-1), Integer.valueOf(1)); 
/*  71 */     this.generator = generator;
/*  72 */     this.alpha = alpha;
/*  73 */     this.beta = beta;
/*  74 */     if (alpha < 2.0D && beta != 0.0D) {
/*  75 */       this.zeta = beta * FastMath.tan(Math.PI * alpha / 2.0D);
/*     */     } else {
/*  77 */       this.zeta = 0.0D;
/*     */     } 
/*     */   }
/*     */   
/*     */   public double nextNormalizedDouble() {
/*  88 */     double x, omega = -FastMath.log(this.generator.nextDouble());
/*  89 */     double phi = Math.PI * (this.generator.nextDouble() - 0.5D);
/*  92 */     if (this.alpha == 2.0D)
/*  93 */       return FastMath.sqrt(2.0D * omega) * FastMath.sin(phi); 
/*  99 */     if (this.beta == 0.0D) {
/* 101 */       if (this.alpha == 1.0D) {
/* 102 */         x = FastMath.tan(phi);
/*     */       } else {
/* 104 */         x = FastMath.pow(omega * FastMath.cos((1.0D - this.alpha) * phi), 1.0D / this.alpha - 1.0D) * FastMath.sin(this.alpha * phi) / FastMath.pow(FastMath.cos(phi), 1.0D / this.alpha);
/*     */       } 
/*     */     } else {
/* 111 */       double cosPhi = FastMath.cos(phi);
/* 113 */       if (FastMath.abs(this.alpha - 1.0D) > 1.0E-8D) {
/* 114 */         double alphaPhi = this.alpha * phi;
/* 115 */         double invAlphaPhi = phi - alphaPhi;
/* 116 */         x = (FastMath.sin(alphaPhi) + this.zeta * FastMath.cos(alphaPhi)) / cosPhi * (FastMath.cos(invAlphaPhi) + this.zeta * FastMath.sin(invAlphaPhi)) / FastMath.pow(omega * cosPhi, (1.0D - this.alpha) / this.alpha);
/*     */       } else {
/* 120 */         double betaPhi = 1.5707963267948966D + this.beta * phi;
/* 121 */         x = 0.6366197723675814D * (betaPhi * FastMath.tan(phi) - this.beta * FastMath.log(1.5707963267948966D * omega * cosPhi / betaPhi));
/* 124 */         if (this.alpha != 1.0D)
/* 125 */           x += this.beta * FastMath.tan(Math.PI * this.alpha / 2.0D); 
/*     */       } 
/*     */     } 
/* 129 */     return x;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\random\StableRandomGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */