/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class Statistics {
/*     */   public static double calculateMean(Number[] values) {
/*  73 */     double result = Double.NaN;
/*  74 */     if (values != null && values.length > 0) {
/*  75 */       double sum = 0.0D;
/*  76 */       int counter = 0;
/*  77 */       for (; counter < values.length; counter++)
/*  78 */         sum += values[counter].doubleValue(); 
/*  80 */       result = sum / counter;
/*     */     } 
/*  82 */     return result;
/*     */   }
/*     */   
/*     */   public static double calculateMean(Collection values) {
/*  95 */     double result = Double.NaN;
/*  96 */     int count = 0;
/*  97 */     double total = 0.0D;
/*  98 */     Iterator iterator = values.iterator();
/*  99 */     while (iterator.hasNext()) {
/* 100 */       Object object = iterator.next();
/* 101 */       if (object != null && object instanceof Number) {
/* 102 */         Number number = (Number)object;
/* 103 */         total += number.doubleValue();
/* 104 */         count++;
/*     */       } 
/*     */     } 
/* 107 */     if (count > 0)
/* 108 */       result = total / count; 
/* 110 */     return result;
/*     */   }
/*     */   
/*     */   public static double calculateMedian(List values) {
/* 123 */     return calculateMedian(values, true);
/*     */   }
/*     */   
/*     */   public static double calculateMedian(List values, boolean copyAndSort) {
/* 138 */     double result = Double.NaN;
/* 139 */     if (values != null) {
/* 140 */       if (copyAndSort) {
/* 141 */         int itemCount = values.size();
/* 142 */         List copy = new ArrayList(itemCount);
/* 143 */         for (int i = 0; i < itemCount; i++)
/* 144 */           copy.add(i, values.get(i)); 
/* 146 */         Collections.sort(copy);
/* 147 */         values = copy;
/*     */       } 
/* 149 */       int count = values.size();
/* 150 */       if (count > 0)
/* 151 */         if (count % 2 == 1) {
/* 152 */           if (count > 1) {
/* 153 */             Number value = (Number)values.get((count - 1) / 2);
/* 154 */             result = value.doubleValue();
/*     */           } else {
/* 157 */             Number value = (Number)values.get(0);
/* 158 */             result = value.doubleValue();
/*     */           } 
/*     */         } else {
/* 162 */           Number value1 = (Number)values.get(count / 2 - 1);
/* 163 */           Number value2 = (Number)values.get(count / 2);
/* 164 */           result = (value1.doubleValue() + value2.doubleValue()) / 2.0D;
/*     */         }  
/*     */     } 
/* 169 */     return result;
/*     */   }
/*     */   
/*     */   public static double calculateMedian(List values, int start, int end) {
/* 183 */     return calculateMedian(values, start, end, true);
/*     */   }
/*     */   
/*     */   public static double calculateMedian(List values, int start, int end, boolean copyAndSort) {
/* 202 */     double result = Double.NaN;
/* 203 */     if (copyAndSort) {
/* 204 */       List working = new ArrayList(end - start + 1);
/* 205 */       for (int i = start; i <= end; i++)
/* 206 */         working.add(values.get(i)); 
/* 208 */       Collections.sort(working);
/* 209 */       result = calculateMedian(working, false);
/*     */     } else {
/* 212 */       int count = end - start + 1;
/* 213 */       if (count > 0)
/* 214 */         if (count % 2 == 1) {
/* 215 */           if (count > 1) {
/* 216 */             Number value = values.get(start + (count - 1) / 2);
/* 218 */             result = value.doubleValue();
/*     */           } else {
/* 221 */             Number value = values.get(start);
/* 222 */             result = value.doubleValue();
/*     */           } 
/*     */         } else {
/* 226 */           Number value1 = values.get(start + count / 2 - 1);
/* 227 */           Number value2 = values.get(start + count / 2);
/* 228 */           result = (value1.doubleValue() + value2.doubleValue()) / 2.0D;
/*     */         }  
/*     */     } 
/* 233 */     return result;
/*     */   }
/*     */   
/*     */   public static double getStdDev(Number[] data) {
/* 245 */     double avg = calculateMean(data);
/* 246 */     double sum = 0.0D;
/* 248 */     for (int counter = 0; counter < data.length; counter++) {
/* 249 */       double diff = data[counter].doubleValue() - avg;
/* 250 */       sum += diff * diff;
/*     */     } 
/* 252 */     return Math.sqrt(sum / (data.length - 1));
/*     */   }
/*     */   
/*     */   public static double[] getLinearFit(Number[] xData, Number[] yData) {
/* 267 */     if (xData.length != yData.length)
/* 268 */       throw new IllegalArgumentException("Statistics.getLinearFit(): array lengths must be equal."); 
/* 272 */     double[] result = new double[2];
/* 274 */     result[1] = getSlope(xData, yData);
/* 276 */     result[0] = calculateMean(yData) - result[1] * calculateMean(xData);
/* 278 */     return result;
/*     */   }
/*     */   
/*     */   public static double getSlope(Number[] xData, Number[] yData) {
/* 293 */     if (xData.length != yData.length)
/* 294 */       throw new IllegalArgumentException("Array lengths must be equal."); 
/* 306 */     double sx = 0.0D, sxx = 0.0D, sxy = 0.0D, sy = 0.0D;
/*     */     int counter;
/* 308 */     for (counter = 0; counter < xData.length; counter++) {
/* 309 */       sx += xData[counter].doubleValue();
/* 310 */       sxx += Math.pow(xData[counter].doubleValue(), 2.0D);
/* 311 */       sxy += yData[counter].doubleValue() * xData[counter].doubleValue();
/* 313 */       sy += yData[counter].doubleValue();
/*     */     } 
/* 315 */     return (sxy - sx * sy / counter) / (sxx - sx * sx / counter);
/*     */   }
/*     */   
/*     */   public static double getCorrelation(Number[] data1, Number[] data2) {
/* 333 */     if (data1 == null)
/* 334 */       throw new IllegalArgumentException("Null 'data1' argument."); 
/* 336 */     if (data2 == null)
/* 337 */       throw new IllegalArgumentException("Null 'data2' argument."); 
/* 339 */     if (data1.length != data2.length)
/* 340 */       throw new IllegalArgumentException("'data1' and 'data2' arrays must have same length."); 
/* 344 */     int n = data1.length;
/* 345 */     double sumX = 0.0D;
/* 346 */     double sumY = 0.0D;
/* 347 */     double sumX2 = 0.0D;
/* 348 */     double sumY2 = 0.0D;
/* 349 */     double sumXY = 0.0D;
/* 350 */     for (int i = 0; i < n; i++) {
/* 351 */       double x = 0.0D;
/* 352 */       if (data1[i] != null)
/* 353 */         x = data1[i].doubleValue(); 
/* 355 */       double y = 0.0D;
/* 356 */       if (data2[i] != null)
/* 357 */         y = data2[i].doubleValue(); 
/* 359 */       sumX += x;
/* 360 */       sumY += y;
/* 361 */       sumXY += x * y;
/* 362 */       sumX2 += x * x;
/* 363 */       sumY2 += y * y;
/*     */     } 
/* 365 */     return (n * sumXY - sumX * sumY) / Math.pow((n * sumX2 - sumX * sumX) * (n * sumY2 - sumY * sumY), 0.5D);
/*     */   }
/*     */   
/*     */   public static double[][] getMovingAverage(Number[] xData, Number[] yData, int period) {
/* 384 */     if (xData.length != yData.length)
/* 385 */       throw new IllegalArgumentException("Array lengths must be equal."); 
/* 388 */     if (period > xData.length)
/* 389 */       throw new IllegalArgumentException("Period can't be longer than dataset."); 
/* 394 */     double[][] result = new double[xData.length - period][2];
/* 395 */     for (int i = 0; i < result.length; i++) {
/* 396 */       result[i][0] = xData[i + period].doubleValue();
/* 398 */       double sum = 0.0D;
/* 399 */       for (int j = 0; j < period; j++)
/* 400 */         sum += yData[i + j].doubleValue(); 
/* 402 */       sum /= period;
/* 403 */       result[i][1] = sum;
/*     */     } 
/* 405 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\statistics\Statistics.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */