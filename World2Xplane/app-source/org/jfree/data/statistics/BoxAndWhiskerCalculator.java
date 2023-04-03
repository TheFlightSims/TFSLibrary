/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class BoxAndWhiskerCalculator {
/*     */   public static BoxAndWhiskerItem calculateBoxAndWhiskerStatistics(List values) {
/*  74 */     Collections.sort(values);
/*  76 */     double mean = Statistics.calculateMean(values);
/*  77 */     double median = Statistics.calculateMedian(values, false);
/*  78 */     double q1 = calculateQ1(values);
/*  79 */     double q3 = calculateQ3(values);
/*  81 */     double interQuartileRange = q3 - q1;
/*  83 */     double upperOutlierThreshold = q3 + interQuartileRange * 1.5D;
/*  84 */     double lowerOutlierThreshold = q1 - interQuartileRange * 1.5D;
/*  86 */     double upperFaroutThreshold = q3 + interQuartileRange * 2.0D;
/*  87 */     double lowerFaroutThreshold = q1 - interQuartileRange * 2.0D;
/*  89 */     double minRegularValue = Double.POSITIVE_INFINITY;
/*  90 */     double maxRegularValue = Double.NEGATIVE_INFINITY;
/*  91 */     double minOutlier = Double.POSITIVE_INFINITY;
/*  92 */     double maxOutlier = Double.NEGATIVE_INFINITY;
/*  93 */     List outliers = new ArrayList();
/*  95 */     Iterator iterator = values.iterator();
/*  96 */     while (iterator.hasNext()) {
/*  97 */       Object object = iterator.next();
/*  98 */       if (object != null && object instanceof Number) {
/*  99 */         Number number = (Number)object;
/* 100 */         double value = number.doubleValue();
/* 101 */         if (value > upperOutlierThreshold) {
/* 102 */           outliers.add(number);
/* 103 */           if (value > maxOutlier && value <= upperFaroutThreshold)
/* 104 */             maxOutlier = value; 
/*     */           continue;
/*     */         } 
/* 107 */         if (value < lowerOutlierThreshold) {
/* 108 */           outliers.add(number);
/* 109 */           if (value < minOutlier && value >= lowerFaroutThreshold)
/* 110 */             minOutlier = value; 
/*     */           continue;
/*     */         } 
/* 114 */         if (minRegularValue == Double.NaN) {
/* 115 */           minRegularValue = value;
/*     */         } else {
/* 118 */           minRegularValue = Math.min(minRegularValue, value);
/*     */         } 
/* 120 */         if (maxRegularValue == Double.NaN) {
/* 121 */           maxRegularValue = value;
/*     */           continue;
/*     */         } 
/* 124 */         maxRegularValue = Math.max(maxRegularValue, value);
/*     */       } 
/*     */     } 
/* 130 */     minOutlier = Math.min(minOutlier, minRegularValue);
/* 131 */     maxOutlier = Math.max(maxOutlier, maxRegularValue);
/* 133 */     return new BoxAndWhiskerItem(new Double(mean), new Double(median), new Double(q1), new Double(q3), new Double(minRegularValue), new Double(maxRegularValue), new Double(minOutlier), new Double(maxOutlier), outliers);
/*     */   }
/*     */   
/*     */   public static double calculateQ1(List values) {
/* 155 */     double result = Double.NaN;
/* 156 */     int count = values.size();
/* 157 */     if (count > 0)
/* 158 */       if (count % 2 == 1) {
/* 159 */         if (count > 1) {
/* 160 */           result = Statistics.calculateMedian(values, 0, count / 2);
/*     */         } else {
/* 163 */           result = Statistics.calculateMedian(values, 0, 0);
/*     */         } 
/*     */       } else {
/* 167 */         result = Statistics.calculateMedian(values, 0, count / 2 - 1);
/*     */       }  
/* 171 */     return result;
/*     */   }
/*     */   
/*     */   public static double calculateQ3(List values) {
/* 182 */     double result = Double.NaN;
/* 183 */     int count = values.size();
/* 184 */     if (count > 0)
/* 185 */       if (count % 2 == 1) {
/* 186 */         if (count > 1) {
/* 187 */           result = Statistics.calculateMedian(values, count / 2, count - 1);
/*     */         } else {
/* 192 */           result = Statistics.calculateMedian(values, 0, 0);
/*     */         } 
/*     */       } else {
/* 196 */         result = Statistics.calculateMedian(values, count / 2, count - 1);
/*     */       }  
/* 202 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\statistics\BoxAndWhiskerCalculator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */