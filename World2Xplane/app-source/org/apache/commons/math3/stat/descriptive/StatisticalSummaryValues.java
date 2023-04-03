/*     */ package org.apache.commons.math3.stat.descriptive;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ import org.apache.commons.math3.util.MathUtils;
/*     */ import org.apache.commons.math3.util.Precision;
/*     */ 
/*     */ public class StatisticalSummaryValues implements Serializable, StatisticalSummary {
/*     */   private static final long serialVersionUID = -5108854841843722536L;
/*     */   
/*     */   private final double mean;
/*     */   
/*     */   private final double variance;
/*     */   
/*     */   private final long n;
/*     */   
/*     */   private final double max;
/*     */   
/*     */   private final double min;
/*     */   
/*     */   private final double sum;
/*     */   
/*     */   public StatisticalSummaryValues(double mean, double variance, long n, double max, double min, double sum) {
/*  67 */     this.mean = mean;
/*  68 */     this.variance = variance;
/*  69 */     this.n = n;
/*  70 */     this.max = max;
/*  71 */     this.min = min;
/*  72 */     this.sum = sum;
/*     */   }
/*     */   
/*     */   public double getMax() {
/*  79 */     return this.max;
/*     */   }
/*     */   
/*     */   public double getMean() {
/*  86 */     return this.mean;
/*     */   }
/*     */   
/*     */   public double getMin() {
/*  93 */     return this.min;
/*     */   }
/*     */   
/*     */   public long getN() {
/* 100 */     return this.n;
/*     */   }
/*     */   
/*     */   public double getSum() {
/* 107 */     return this.sum;
/*     */   }
/*     */   
/*     */   public double getStandardDeviation() {
/* 114 */     return FastMath.sqrt(this.variance);
/*     */   }
/*     */   
/*     */   public double getVariance() {
/* 121 */     return this.variance;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 134 */     if (object == this)
/* 135 */       return true; 
/* 137 */     if (!(object instanceof StatisticalSummaryValues))
/* 138 */       return false; 
/* 140 */     StatisticalSummaryValues stat = (StatisticalSummaryValues)object;
/* 141 */     return (Precision.equalsIncludingNaN(stat.getMax(), getMax()) && Precision.equalsIncludingNaN(stat.getMean(), getMean()) && Precision.equalsIncludingNaN(stat.getMin(), getMin()) && Precision.equalsIncludingNaN((float)stat.getN(), (float)getN()) && Precision.equalsIncludingNaN(stat.getSum(), getSum()) && Precision.equalsIncludingNaN(stat.getVariance(), getVariance()));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 156 */     int result = 31 + MathUtils.hash(getMax());
/* 157 */     result = result * 31 + MathUtils.hash(getMean());
/* 158 */     result = result * 31 + MathUtils.hash(getMin());
/* 159 */     result = result * 31 + MathUtils.hash(getN());
/* 160 */     result = result * 31 + MathUtils.hash(getSum());
/* 161 */     result = result * 31 + MathUtils.hash(getVariance());
/* 162 */     return result;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 173 */     StringBuffer outBuffer = new StringBuffer();
/* 174 */     String endl = "\n";
/* 175 */     outBuffer.append("StatisticalSummaryValues:").append(endl);
/* 176 */     outBuffer.append("n: ").append(getN()).append(endl);
/* 177 */     outBuffer.append("min: ").append(getMin()).append(endl);
/* 178 */     outBuffer.append("max: ").append(getMax()).append(endl);
/* 179 */     outBuffer.append("mean: ").append(getMean()).append(endl);
/* 180 */     outBuffer.append("std dev: ").append(getStandardDeviation()).append(endl);
/* 182 */     outBuffer.append("variance: ").append(getVariance()).append(endl);
/* 183 */     outBuffer.append("sum: ").append(getSum()).append(endl);
/* 184 */     return outBuffer.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\StatisticalSummaryValues.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */