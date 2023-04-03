/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.data.xy.XYSeries;
/*     */ import org.jfree.data.xy.XYSeriesCollection;
/*     */ 
/*     */ public class MovingAverage {
/*     */   public static TimeSeriesCollection createMovingAverage(TimeSeriesCollection source, String suffix, int periodCount, int skip) {
/*  80 */     if (source == null)
/*  81 */       throw new IllegalArgumentException("MovingAverage.createMovingAverage() : null source."); 
/*  86 */     if (periodCount < 1)
/*  87 */       throw new IllegalArgumentException("periodCount must be greater than or equal to 1."); 
/*  92 */     TimeSeriesCollection result = new TimeSeriesCollection();
/*  94 */     for (int i = 0; i < source.getSeriesCount(); i++) {
/*  95 */       TimeSeries sourceSeries = source.getSeries(i);
/*  96 */       TimeSeries maSeries = createMovingAverage(sourceSeries, sourceSeries.getKey() + suffix, periodCount, skip);
/*  99 */       result.addSeries(maSeries);
/*     */     } 
/* 102 */     return result;
/*     */   }
/*     */   
/*     */   public static TimeSeries createMovingAverage(TimeSeries source, String name, int periodCount, int skip) {
/* 125 */     if (source == null)
/* 126 */       throw new IllegalArgumentException("Null source."); 
/* 129 */     if (periodCount < 1)
/* 130 */       throw new IllegalArgumentException("periodCount must be greater than or equal to 1."); 
/* 136 */     TimeSeries result = new TimeSeries(name, source.getTimePeriodClass());
/* 138 */     if (source.getItemCount() > 0) {
/* 143 */       long firstSerial = source.getDataItem(0).getPeriod().getSerialIndex() + skip;
/* 146 */       for (int i = source.getItemCount() - 1; i >= 0; i--) {
/* 149 */         TimeSeriesDataItem current = source.getDataItem(i);
/* 150 */         RegularTimePeriod period = current.getPeriod();
/* 151 */         long serial = period.getSerialIndex();
/* 153 */         if (serial >= firstSerial) {
/* 155 */           int n = 0;
/* 156 */           double sum = 0.0D;
/* 157 */           long serialLimit = period.getSerialIndex() - periodCount;
/* 158 */           int offset = 0;
/* 159 */           boolean finished = false;
/* 161 */           while (offset < periodCount && !finished) {
/* 162 */             if (i - offset >= 0) {
/* 163 */               TimeSeriesDataItem item = source.getDataItem(i - offset);
/* 165 */               RegularTimePeriod p = item.getPeriod();
/* 166 */               Number v = item.getValue();
/* 167 */               long currentIndex = p.getSerialIndex();
/* 168 */               if (currentIndex > serialLimit) {
/* 169 */                 if (v != null) {
/* 170 */                   sum += v.doubleValue();
/* 171 */                   n++;
/*     */                 } 
/*     */               } else {
/* 175 */                 finished = true;
/*     */               } 
/*     */             } 
/* 178 */             offset++;
/*     */           } 
/* 180 */           if (n > 0) {
/* 181 */             result.add(period, sum / n);
/*     */           } else {
/* 184 */             result.add(period, (Number)null);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 191 */     return result;
/*     */   }
/*     */   
/*     */   public static TimeSeries createPointMovingAverage(TimeSeries source, String name, int pointCount) {
/* 215 */     if (source == null)
/* 216 */       throw new IllegalArgumentException("Null 'source'."); 
/* 219 */     if (pointCount < 2)
/* 220 */       throw new IllegalArgumentException("periodCount must be greater than or equal to 2."); 
/* 225 */     TimeSeries result = new TimeSeries(name, source.getTimePeriodClass());
/* 226 */     double rollingSumForPeriod = 0.0D;
/* 227 */     for (int i = 0; i < source.getItemCount(); i++) {
/* 229 */       TimeSeriesDataItem current = source.getDataItem(i);
/* 230 */       RegularTimePeriod period = current.getPeriod();
/* 231 */       rollingSumForPeriod += current.getValue().doubleValue();
/* 233 */       if (i > pointCount - 1) {
/* 235 */         TimeSeriesDataItem startOfMovingAvg = source.getDataItem(i - pointCount);
/* 237 */         rollingSumForPeriod -= startOfMovingAvg.getValue().doubleValue();
/* 239 */         result.add(period, rollingSumForPeriod / pointCount);
/* 241 */       } else if (i == pointCount - 1) {
/* 242 */         result.add(period, rollingSumForPeriod / pointCount);
/*     */       } 
/*     */     } 
/* 245 */     return result;
/*     */   }
/*     */   
/*     */   public static XYDataset createMovingAverage(XYDataset source, String suffix, long period, long skip) {
/* 263 */     return createMovingAverage(source, suffix, period, skip);
/*     */   }
/*     */   
/*     */   public static XYDataset createMovingAverage(XYDataset source, String suffix, double period, double skip) {
/* 286 */     if (source == null)
/* 287 */       throw new IllegalArgumentException("Null source (XYDataset)."); 
/* 290 */     XYSeriesCollection result = new XYSeriesCollection();
/* 292 */     for (int i = 0; i < source.getSeriesCount(); i++) {
/* 293 */       XYSeries s = createMovingAverage(source, i, source.getSeriesKey(i) + suffix, period, skip);
/* 296 */       result.addSeries(s);
/*     */     } 
/* 299 */     return (XYDataset)result;
/*     */   }
/*     */   
/*     */   public static XYSeries createMovingAverage(XYDataset source, int series, String name, double period, double skip) {
/* 321 */     if (source == null)
/* 322 */       throw new IllegalArgumentException("Null source (XYDataset)."); 
/* 325 */     if (period < Double.MIN_VALUE)
/* 326 */       throw new IllegalArgumentException("period must be positive."); 
/* 330 */     if (skip < 0.0D)
/* 331 */       throw new IllegalArgumentException("skip must be >= 0.0."); 
/* 335 */     XYSeries result = new XYSeries(name);
/* 337 */     if (source.getItemCount(series) > 0) {
/* 341 */       double first = source.getXValue(series, 0) + skip;
/* 343 */       for (int i = source.getItemCount(series) - 1; i >= 0; i--) {
/* 346 */         double x = source.getXValue(series, i);
/* 348 */         if (x >= first) {
/* 350 */           int n = 0;
/* 351 */           double sum = 0.0D;
/* 352 */           double limit = x - period;
/* 353 */           int offset = 0;
/* 354 */           boolean finished = false;
/* 356 */           while (!finished) {
/* 357 */             if (i - offset >= 0) {
/* 358 */               double xx = source.getXValue(series, i - offset);
/* 359 */               Number yy = source.getY(series, i - offset);
/* 360 */               if (xx > limit) {
/* 361 */                 if (yy != null) {
/* 362 */                   sum += yy.doubleValue();
/* 363 */                   n++;
/*     */                 } 
/*     */               } else {
/* 367 */                 finished = true;
/*     */               } 
/*     */             } else {
/* 371 */               finished = true;
/*     */             } 
/* 373 */             offset++;
/*     */           } 
/* 375 */           if (n > 0) {
/* 376 */             result.add(x, sum / n);
/*     */           } else {
/* 379 */             result.add(x, null);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 386 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\MovingAverage.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */