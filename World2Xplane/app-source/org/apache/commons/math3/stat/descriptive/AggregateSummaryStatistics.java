/*     */ package org.apache.commons.math3.stat.descriptive;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class AggregateSummaryStatistics implements StatisticalSummary, Serializable {
/*     */   private static final long serialVersionUID = -8207112444016386906L;
/*     */   
/*     */   private final SummaryStatistics statisticsPrototype;
/*     */   
/*     */   private final SummaryStatistics statistics;
/*     */   
/*     */   public AggregateSummaryStatistics() {
/*  76 */     this(new SummaryStatistics());
/*     */   }
/*     */   
/*     */   public AggregateSummaryStatistics(SummaryStatistics prototypeStatistics) {
/*  96 */     this(prototypeStatistics, (prototypeStatistics == null) ? null : new SummaryStatistics(prototypeStatistics));
/*     */   }
/*     */   
/*     */   public AggregateSummaryStatistics(SummaryStatistics prototypeStatistics, SummaryStatistics initialStatistics) {
/* 122 */     this.statisticsPrototype = (prototypeStatistics == null) ? new SummaryStatistics() : prototypeStatistics;
/* 124 */     this.statistics = (initialStatistics == null) ? new SummaryStatistics() : initialStatistics;
/*     */   }
/*     */   
/*     */   public double getMax() {
/* 135 */     synchronized (this.statistics) {
/* 136 */       return this.statistics.getMax();
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getMean() {
/* 146 */     synchronized (this.statistics) {
/* 147 */       return this.statistics.getMean();
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getMin() {
/* 158 */     synchronized (this.statistics) {
/* 159 */       return this.statistics.getMin();
/*     */     } 
/*     */   }
/*     */   
/*     */   public long getN() {
/* 169 */     synchronized (this.statistics) {
/* 170 */       return this.statistics.getN();
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getStandardDeviation() {
/* 181 */     synchronized (this.statistics) {
/* 182 */       return this.statistics.getStandardDeviation();
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getSum() {
/* 192 */     synchronized (this.statistics) {
/* 193 */       return this.statistics.getSum();
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getVariance() {
/* 204 */     synchronized (this.statistics) {
/* 205 */       return this.statistics.getVariance();
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getSumOfLogs() {
/* 216 */     synchronized (this.statistics) {
/* 217 */       return this.statistics.getSumOfLogs();
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getGeometricMean() {
/* 228 */     synchronized (this.statistics) {
/* 229 */       return this.statistics.getGeometricMean();
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getSumsq() {
/* 240 */     synchronized (this.statistics) {
/* 241 */       return this.statistics.getSumsq();
/*     */     } 
/*     */   }
/*     */   
/*     */   public double getSecondMoment() {
/* 254 */     synchronized (this.statistics) {
/* 255 */       return this.statistics.getSecondMoment();
/*     */     } 
/*     */   }
/*     */   
/*     */   public StatisticalSummary getSummary() {
/* 266 */     synchronized (this.statistics) {
/* 267 */       return new StatisticalSummaryValues(getMean(), getVariance(), getN(), getMax(), getMin(), getSum());
/*     */     } 
/*     */   }
/*     */   
/*     */   public SummaryStatistics createContributingStatistics() {
/* 281 */     SummaryStatistics contributingStatistics = new AggregatingSummaryStatistics(this.statistics);
/* 284 */     SummaryStatistics.copy(this.statisticsPrototype, contributingStatistics);
/* 286 */     return contributingStatistics;
/*     */   }
/*     */   
/*     */   public static StatisticalSummaryValues aggregate(Collection<SummaryStatistics> statistics) {
/*     */     double variance;
/* 302 */     if (statistics == null)
/* 303 */       return null; 
/* 305 */     Iterator<SummaryStatistics> iterator = statistics.iterator();
/* 306 */     if (!iterator.hasNext())
/* 307 */       return null; 
/* 309 */     SummaryStatistics current = iterator.next();
/* 310 */     long n = current.getN();
/* 311 */     double min = current.getMin();
/* 312 */     double sum = current.getSum();
/* 313 */     double max = current.getMax();
/* 314 */     double m2 = current.getSecondMoment();
/* 315 */     double mean = current.getMean();
/* 316 */     while (iterator.hasNext()) {
/* 317 */       current = iterator.next();
/* 318 */       if (current.getMin() < min || Double.isNaN(min))
/* 319 */         min = current.getMin(); 
/* 321 */       if (current.getMax() > max || Double.isNaN(max))
/* 322 */         max = current.getMax(); 
/* 324 */       sum += current.getSum();
/* 325 */       double oldN = n;
/* 326 */       double curN = current.getN();
/* 327 */       n = (long)(n + curN);
/* 328 */       double meanDiff = current.getMean() - mean;
/* 329 */       mean = sum / n;
/* 330 */       m2 = m2 + current.getSecondMoment() + meanDiff * meanDiff * oldN * curN / n;
/*     */     } 
/* 333 */     if (n == 0L) {
/* 334 */       variance = Double.NaN;
/* 335 */     } else if (n == 1L) {
/* 336 */       variance = 0.0D;
/*     */     } else {
/* 338 */       variance = m2 / (n - 1L);
/*     */     } 
/* 340 */     return new StatisticalSummaryValues(mean, variance, n, max, min, sum);
/*     */   }
/*     */   
/*     */   private static class AggregatingSummaryStatistics extends SummaryStatistics {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private final SummaryStatistics aggregateStatistics;
/*     */     
/*     */     public AggregatingSummaryStatistics(SummaryStatistics aggregateStatistics) {
/* 370 */       this.aggregateStatistics = aggregateStatistics;
/*     */     }
/*     */     
/*     */     public void addValue(double value) {
/* 381 */       super.addValue(value);
/* 382 */       synchronized (this.aggregateStatistics) {
/* 383 */         this.aggregateStatistics.addValue(value);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean equals(Object object) {
/* 396 */       if (object == this)
/* 397 */         return true; 
/* 399 */       if (!(object instanceof AggregatingSummaryStatistics))
/* 400 */         return false; 
/* 402 */       AggregatingSummaryStatistics stat = (AggregatingSummaryStatistics)object;
/* 403 */       return (super.equals(stat) && this.aggregateStatistics.equals(stat.aggregateStatistics));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 413 */       return 123 + super.hashCode() + this.aggregateStatistics.hashCode();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\descriptive\AggregateSummaryStatistics.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */