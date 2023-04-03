/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ 
/*     */ public abstract class Regression {
/*     */   public static double[] getOLSRegression(double[][] data) {
/*  66 */     int n = data.length;
/*  67 */     if (n < 2)
/*  68 */       throw new IllegalArgumentException("Not enough data."); 
/*  71 */     double sumX = 0.0D;
/*  72 */     double sumY = 0.0D;
/*  73 */     double sumXX = 0.0D;
/*  74 */     double sumXY = 0.0D;
/*  75 */     for (int i = 0; i < n; i++) {
/*  76 */       double x = data[i][0];
/*  77 */       double y = data[i][1];
/*  78 */       sumX += x;
/*  79 */       sumY += y;
/*  80 */       double xx = x * x;
/*  81 */       sumXX += xx;
/*  82 */       double xy = x * y;
/*  83 */       sumXY += xy;
/*     */     } 
/*  85 */     double sxx = sumXX - sumX * sumX / n;
/*  86 */     double sxy = sumXY - sumX * sumY / n;
/*  87 */     double xbar = sumX / n;
/*  88 */     double ybar = sumY / n;
/*  90 */     double[] result = new double[2];
/*  91 */     result[1] = sxy / sxx;
/*  92 */     result[0] = ybar - result[1] * xbar;
/*  94 */     return result;
/*     */   }
/*     */   
/*     */   public static double[] getOLSRegression(XYDataset data, int series) {
/* 110 */     int n = data.getItemCount(series);
/* 111 */     if (n < 2)
/* 112 */       throw new IllegalArgumentException("Not enough data."); 
/* 115 */     double sumX = 0.0D;
/* 116 */     double sumY = 0.0D;
/* 117 */     double sumXX = 0.0D;
/* 118 */     double sumXY = 0.0D;
/* 119 */     for (int i = 0; i < n; i++) {
/* 120 */       double x = data.getXValue(series, i);
/* 121 */       double y = data.getYValue(series, i);
/* 122 */       sumX += x;
/* 123 */       sumY += y;
/* 124 */       double xx = x * x;
/* 125 */       sumXX += xx;
/* 126 */       double xy = x * y;
/* 127 */       sumXY += xy;
/*     */     } 
/* 129 */     double sxx = sumXX - sumX * sumX / n;
/* 130 */     double sxy = sumXY - sumX * sumY / n;
/* 131 */     double xbar = sumX / n;
/* 132 */     double ybar = sumY / n;
/* 134 */     double[] result = new double[2];
/* 135 */     result[1] = sxy / sxx;
/* 136 */     result[0] = ybar - result[1] * xbar;
/* 138 */     return result;
/*     */   }
/*     */   
/*     */   public static double[] getPowerRegression(double[][] data) {
/* 153 */     int n = data.length;
/* 154 */     if (n < 2)
/* 155 */       throw new IllegalArgumentException("Not enough data."); 
/* 158 */     double sumX = 0.0D;
/* 159 */     double sumY = 0.0D;
/* 160 */     double sumXX = 0.0D;
/* 161 */     double sumXY = 0.0D;
/* 162 */     for (int i = 0; i < n; i++) {
/* 163 */       double x = Math.log(data[i][0]);
/* 164 */       double y = Math.log(data[i][1]);
/* 165 */       sumX += x;
/* 166 */       sumY += y;
/* 167 */       double xx = x * x;
/* 168 */       sumXX += xx;
/* 169 */       double xy = x * y;
/* 170 */       sumXY += xy;
/*     */     } 
/* 172 */     double sxx = sumXX - sumX * sumX / n;
/* 173 */     double sxy = sumXY - sumX * sumY / n;
/* 174 */     double xbar = sumX / n;
/* 175 */     double ybar = sumY / n;
/* 177 */     double[] result = new double[2];
/* 178 */     result[1] = sxy / sxx;
/* 179 */     result[0] = Math.pow(Math.exp(1.0D), ybar - result[1] * xbar);
/* 181 */     return result;
/*     */   }
/*     */   
/*     */   public static double[] getPowerRegression(XYDataset data, int series) {
/* 197 */     int n = data.getItemCount(series);
/* 198 */     if (n < 2)
/* 199 */       throw new IllegalArgumentException("Not enough data."); 
/* 202 */     double sumX = 0.0D;
/* 203 */     double sumY = 0.0D;
/* 204 */     double sumXX = 0.0D;
/* 205 */     double sumXY = 0.0D;
/* 206 */     for (int i = 0; i < n; i++) {
/* 207 */       double x = Math.log(data.getXValue(series, i));
/* 208 */       double y = Math.log(data.getYValue(series, i));
/* 209 */       sumX += x;
/* 210 */       sumY += y;
/* 211 */       double xx = x * x;
/* 212 */       sumXX += xx;
/* 213 */       double xy = x * y;
/* 214 */       sumXY += xy;
/*     */     } 
/* 216 */     double sxx = sumXX - sumX * sumX / n;
/* 217 */     double sxy = sumXY - sumX * sumY / n;
/* 218 */     double xbar = sumX / n;
/* 219 */     double ybar = sumY / n;
/* 221 */     double[] result = new double[2];
/* 222 */     result[1] = sxy / sxx;
/* 223 */     result[0] = Math.pow(Math.exp(1.0D), ybar - result[1] * xbar);
/* 225 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\statistics\Regression.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */