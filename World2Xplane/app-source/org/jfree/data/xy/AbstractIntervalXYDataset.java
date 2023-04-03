/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ public abstract class AbstractIntervalXYDataset extends AbstractXYDataset implements IntervalXYDataset {
/*     */   public double getStartXValue(int series, int item) {
/*  66 */     double result = Double.NaN;
/*  67 */     Number x = getStartX(series, item);
/*  68 */     if (x != null)
/*  69 */       result = x.doubleValue(); 
/*  71 */     return result;
/*     */   }
/*     */   
/*     */   public double getEndXValue(int series, int item) {
/*  84 */     double result = Double.NaN;
/*  85 */     Number x = getEndX(series, item);
/*  86 */     if (x != null)
/*  87 */       result = x.doubleValue(); 
/*  89 */     return result;
/*     */   }
/*     */   
/*     */   public double getStartYValue(int series, int item) {
/* 102 */     double result = Double.NaN;
/* 103 */     Number y = getStartY(series, item);
/* 104 */     if (y != null)
/* 105 */       result = y.doubleValue(); 
/* 107 */     return result;
/*     */   }
/*     */   
/*     */   public double getEndYValue(int series, int item) {
/* 120 */     double result = Double.NaN;
/* 121 */     Number y = getEndY(series, item);
/* 122 */     if (y != null)
/* 123 */       result = y.doubleValue(); 
/* 125 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\AbstractIntervalXYDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */