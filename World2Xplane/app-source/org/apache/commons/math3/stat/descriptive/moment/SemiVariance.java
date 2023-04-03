/*     */ package org.apache.commons.math3.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.exception.NullArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ import org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic;
/*     */ import org.apache.commons.math3.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ 
/*     */ public class SemiVariance extends AbstractUnivariateStatistic implements Serializable {
/*  61 */   public static final Direction UPSIDE_VARIANCE = Direction.UPSIDE;
/*     */   
/*  67 */   public static final Direction DOWNSIDE_VARIANCE = Direction.DOWNSIDE;
/*     */   
/*     */   private static final long serialVersionUID = -2653430366886024994L;
/*     */   
/*     */   private boolean biasCorrected = true;
/*     */   
/*  81 */   private Direction varianceDirection = Direction.DOWNSIDE;
/*     */   
/*     */   public SemiVariance(boolean biasCorrected) {
/*  99 */     this.biasCorrected = biasCorrected;
/*     */   }
/*     */   
/*     */   public SemiVariance(Direction direction) {
/* 111 */     this.varianceDirection = direction;
/*     */   }
/*     */   
/*     */   public SemiVariance(boolean corrected, Direction direction) {
/* 127 */     this.biasCorrected = corrected;
/* 128 */     this.varianceDirection = direction;
/*     */   }
/*     */   
/*     */   public SemiVariance(SemiVariance original) {
/* 139 */     copy(original, this);
/*     */   }
/*     */   
/*     */   public SemiVariance copy() {
/* 148 */     SemiVariance result = new SemiVariance();
/* 149 */     copy(this, result);
/* 150 */     return result;
/*     */   }
/*     */   
/*     */   public static void copy(SemiVariance source, SemiVariance dest) throws NullArgumentException {
/* 164 */     MathUtils.checkNotNull(source);
/* 165 */     MathUtils.checkNotNull(dest);
/* 166 */     dest.setData(source.getDataRef());
/* 167 */     dest.biasCorrected = source.biasCorrected;
/* 168 */     dest.varianceDirection = source.varianceDirection;
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values) {
/* 183 */     if (values == null)
/* 184 */       throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]); 
/* 186 */     return evaluate(values, 0, values.length);
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, int start, int length) {
/* 206 */     double m = (new Mean()).evaluate(values, start, length);
/* 207 */     return evaluate(values, m, this.varianceDirection, this.biasCorrected, 0, values.length);
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, Direction direction) {
/* 222 */     double m = (new Mean()).evaluate(values);
/* 223 */     return evaluate(values, m, direction, this.biasCorrected, 0, values.length);
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double cutoff) {
/* 239 */     return evaluate(values, cutoff, this.varianceDirection, this.biasCorrected, 0, values.length);
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double cutoff, Direction direction) {
/* 256 */     return evaluate(values, cutoff, direction, this.biasCorrected, 0, values.length);
/*     */   }
/*     */   
/*     */   public double evaluate(double[] values, double cutoff, Direction direction, boolean corrected, int start, int length) {
/* 280 */     test(values, start, length);
/* 281 */     if (values.length == 0)
/* 282 */       return Double.NaN; 
/* 284 */     if (values.length == 1)
/* 285 */       return 0.0D; 
/* 287 */     boolean booleanDirection = direction.getDirection();
/* 289 */     double dev = 0.0D;
/* 290 */     double sumsq = 0.0D;
/* 291 */     for (int i = start; i < length; i++) {
/* 292 */       if (((values[i] > cutoff)) == booleanDirection) {
/* 293 */         dev = values[i] - cutoff;
/* 294 */         sumsq += dev * dev;
/*     */       } 
/*     */     } 
/* 298 */     if (corrected)
/* 299 */       return sumsq / (length - 1.0D); 
/* 301 */     return sumsq / length;
/*     */   }
/*     */   
/*     */   public boolean isBiasCorrected() {
/* 313 */     return this.biasCorrected;
/*     */   }
/*     */   
/*     */   public void setBiasCorrected(boolean biasCorrected) {
/* 322 */     this.biasCorrected = biasCorrected;
/*     */   }
/*     */   
/*     */   public Direction getVarianceDirection() {
/* 331 */     return this.varianceDirection;
/*     */   }
/*     */   
/*     */   public void setVarianceDirection(Direction varianceDirection) {
/* 340 */     this.varianceDirection = varianceDirection;
/*     */   }
/*     */   
/*     */   public SemiVariance() {}
/*     */   
/*     */   public enum Direction {
/* 352 */     UPSIDE(true),
/* 358 */     DOWNSIDE(false);
/*     */     
/*     */     private boolean direction;
/*     */     
/*     */     Direction(boolean b) {
/* 371 */       this.direction = b;
/*     */     }
/*     */     
/*     */     boolean getDirection() {
/* 380 */       return this.direction;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\moment\SemiVariance.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */