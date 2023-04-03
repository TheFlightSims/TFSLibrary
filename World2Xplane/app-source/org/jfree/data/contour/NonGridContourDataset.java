/*     */ package org.jfree.data.contour;
/*     */ 
/*     */ import org.jfree.data.Range;
/*     */ 
/*     */ public class NonGridContourDataset extends DefaultContourDataset {
/*     */   static final int DEFAULT_NUM_X = 50;
/*     */   
/*     */   static final int DEFAULT_NUM_Y = 50;
/*     */   
/*     */   static final int DEFAULT_POWER = 4;
/*     */   
/*     */   public NonGridContourDataset() {}
/*     */   
/*     */   public NonGridContourDataset(String seriesName, Object[] xData, Object[] yData, Object[] zData) {
/*  81 */     super(seriesName, xData, yData, zData);
/*  82 */     buildGrid(50, 50, 4);
/*     */   }
/*     */   
/*     */   public NonGridContourDataset(String seriesName, Object[] xData, Object[] yData, Object[] zData, int numX, int numY, int power) {
/* 100 */     super(seriesName, xData, yData, zData);
/* 101 */     buildGrid(numX, numY, power);
/*     */   }
/*     */   
/*     */   protected void buildGrid(int numX, int numY, int power) {
/* 117 */     int numValues = numX * numY;
/* 118 */     double[] xGrid = new double[numValues];
/* 119 */     double[] yGrid = new double[numValues];
/* 120 */     double[] zGrid = new double[numValues];
/* 123 */     double xMin = 1.0E20D;
/* 124 */     for (int k = 0; k < this.xValues.length; k++)
/* 125 */       xMin = Math.min(xMin, this.xValues[k].doubleValue()); 
/* 128 */     double xMax = -1.0E20D;
/* 129 */     for (int j = 0; j < this.xValues.length; j++)
/* 130 */       xMax = Math.max(xMax, this.xValues[j].doubleValue()); 
/* 133 */     double yMin = 1.0E20D;
/* 134 */     for (int m = 0; m < this.yValues.length; m++)
/* 135 */       yMin = Math.min(yMin, this.yValues[m].doubleValue()); 
/* 138 */     double yMax = -1.0E20D;
/* 139 */     for (int n = 0; n < this.yValues.length; n++)
/* 140 */       yMax = Math.max(yMax, this.yValues[n].doubleValue()); 
/* 143 */     Range xRange = new Range(xMin, xMax);
/* 144 */     Range yRange = new Range(yMin, yMax);
/* 146 */     xRange.getLength();
/* 147 */     yRange.getLength();
/* 150 */     double dxGrid = xRange.getLength() / (numX - 1);
/* 151 */     double dyGrid = yRange.getLength() / (numY - 1);
/* 154 */     double x = 0.0D;
/* 155 */     for (int i = 0; i < numX; i++) {
/* 156 */       if (i == 0) {
/* 157 */         x = xMin;
/*     */       } else {
/* 160 */         x += dxGrid;
/*     */       } 
/* 162 */       double y = 0.0D;
/* 163 */       for (int i1 = 0; i1 < numY; i1++) {
/* 164 */         int i2 = numY * i + i1;
/* 165 */         xGrid[i2] = x;
/* 166 */         if (i1 == 0) {
/* 167 */           y = yMin;
/*     */         } else {
/* 170 */           y += dyGrid;
/*     */         } 
/* 172 */         yGrid[i2] = y;
/*     */       } 
/*     */     } 
/* 177 */     for (int kGrid = 0; kGrid < xGrid.length; kGrid++) {
/* 178 */       double dTotal = 0.0D;
/* 179 */       zGrid[kGrid] = 0.0D;
/* 180 */       for (int i1 = 0; i1 < this.xValues.length; i1++) {
/* 181 */         double xPt = this.xValues[i1].doubleValue();
/* 182 */         double yPt = this.yValues[i1].doubleValue();
/* 183 */         double d = distance(xPt, yPt, xGrid[kGrid], yGrid[kGrid]);
/* 184 */         if (power != 1)
/* 185 */           d = Math.pow(d, power); 
/* 187 */         d = Math.sqrt(d);
/* 188 */         if (d > 0.0D) {
/* 189 */           d = 1.0D / d;
/*     */         } else {
/* 193 */           d = 1.0E20D;
/*     */         } 
/* 195 */         if (this.zValues[i1] != null)
/* 197 */           zGrid[kGrid] = zGrid[kGrid] + this.zValues[i1].doubleValue() * d; 
/* 199 */         dTotal += d;
/*     */       } 
/* 201 */       zGrid[kGrid] = zGrid[kGrid] / dTotal;
/*     */     } 
/* 205 */     initialize(formObjectArray(xGrid), formObjectArray(yGrid), formObjectArray(zGrid));
/*     */   }
/*     */   
/*     */   protected double distance(double xDataPt, double yDataPt, double xGrdPt, double yGrdPt) {
/* 226 */     double dx = xDataPt - xGrdPt;
/* 227 */     double dy = yDataPt - yGrdPt;
/* 228 */     return Math.sqrt(dx * dx + dy * dy);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\contour\NonGridContourDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */