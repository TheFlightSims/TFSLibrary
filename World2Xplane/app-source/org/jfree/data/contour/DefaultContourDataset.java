/*     */ package org.jfree.data.contour;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import java.util.Vector;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.xy.AbstractXYZDataset;
/*     */ 
/*     */ public class DefaultContourDataset extends AbstractXYZDataset implements ContourDataset {
/*  67 */   protected Comparable seriesKey = null;
/*     */   
/*  70 */   protected Number[] xValues = null;
/*     */   
/*  73 */   protected Number[] yValues = null;
/*     */   
/*  76 */   protected Number[] zValues = null;
/*     */   
/*  79 */   protected int[] xIndex = null;
/*     */   
/*  82 */   boolean[] dateAxis = new boolean[3];
/*     */   
/*     */   public DefaultContourDataset() {}
/*     */   
/*     */   public DefaultContourDataset(Comparable seriesKey, Object[] xData, Object[] yData, Object[] zData) {
/* 104 */     this.seriesKey = seriesKey;
/* 105 */     initialize(xData, yData, zData);
/*     */   }
/*     */   
/*     */   public void initialize(Object[] xData, Object[] yData, Object[] zData) {
/* 119 */     this.xValues = (Number[])new Double[xData.length];
/* 120 */     this.yValues = (Number[])new Double[yData.length];
/* 121 */     this.zValues = (Number[])new Double[zData.length];
/* 131 */     Vector tmpVector = new Vector();
/* 132 */     double x = 1.123452E31D;
/* 133 */     for (int k = 0; k < this.xValues.length; k++) {
/* 134 */       if (xData[k] != null) {
/*     */         Number xNumber;
/* 136 */         if (xData[k] instanceof Number) {
/* 137 */           xNumber = (Number)xData[k];
/* 139 */         } else if (xData[k] instanceof Date) {
/* 140 */           this.dateAxis[0] = true;
/* 141 */           Date xDate = (Date)xData[k];
/* 142 */           xNumber = new Long(xDate.getTime());
/*     */         } else {
/* 145 */           xNumber = new Integer(0);
/*     */         } 
/* 147 */         this.xValues[k] = new Double(xNumber.doubleValue());
/* 151 */         if (x != this.xValues[k].doubleValue()) {
/* 152 */           tmpVector.add(new Integer(k));
/* 154 */           x = this.xValues[k].doubleValue();
/*     */         } 
/*     */       } 
/*     */     } 
/* 160 */     Object[] inttmp = tmpVector.toArray();
/* 161 */     this.xIndex = new int[inttmp.length];
/* 164 */     for (int i = 0; i < inttmp.length; i++)
/* 165 */       this.xIndex[i] = ((Integer)inttmp[i]).intValue(); 
/* 167 */     for (int j = 0; j < this.yValues.length; j++) {
/* 169 */       this.yValues[j] = (Double)yData[j];
/* 170 */       if (zData[j] != null)
/* 171 */         this.zValues[j] = (Double)zData[j]; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object[][] formObjectArray(double[][] data) {
/* 184 */     Double[][] arrayOfDouble = new Double[data.length][(data[0]).length];
/* 186 */     for (int i = 0; i < arrayOfDouble.length; i++) {
/* 187 */       for (int j = 0; j < (arrayOfDouble[i]).length; j++)
/* 188 */         arrayOfDouble[i][j] = new Double(data[i][j]); 
/*     */     } 
/* 191 */     return (Object[][])arrayOfDouble;
/*     */   }
/*     */   
/*     */   public static Object[] formObjectArray(double[] data) {
/* 202 */     Double[] arrayOfDouble = new Double[data.length];
/* 203 */     for (int i = 0; i < arrayOfDouble.length; i++)
/* 204 */       arrayOfDouble[i] = new Double(data[i]); 
/* 206 */     return (Object[])arrayOfDouble;
/*     */   }
/*     */   
/*     */   public int getItemCount(int series) {
/* 218 */     if (series > 0)
/* 219 */       throw new IllegalArgumentException("Only one series for contour"); 
/* 221 */     return this.zValues.length;
/*     */   }
/*     */   
/*     */   public double getMaxZValue() {
/* 230 */     double zMax = -1.0E20D;
/* 231 */     for (int k = 0; k < this.zValues.length; k++) {
/* 232 */       if (this.zValues[k] != null)
/* 233 */         zMax = Math.max(zMax, this.zValues[k].doubleValue()); 
/*     */     } 
/* 236 */     return zMax;
/*     */   }
/*     */   
/*     */   public double getMinZValue() {
/* 245 */     double zMin = 1.0E20D;
/* 246 */     for (int k = 0; k < this.zValues.length; k++) {
/* 247 */       if (this.zValues[k] != null)
/* 248 */         zMin = Math.min(zMin, this.zValues[k].doubleValue()); 
/*     */     } 
/* 251 */     return zMin;
/*     */   }
/*     */   
/*     */   public Range getZValueRange(Range x, Range y) {
/* 264 */     double minX = x.getLowerBound();
/* 265 */     double minY = y.getLowerBound();
/* 266 */     double maxX = x.getUpperBound();
/* 267 */     double maxY = y.getUpperBound();
/* 269 */     double zMin = 1.0E20D;
/* 270 */     double zMax = -1.0E20D;
/* 271 */     for (int k = 0; k < this.zValues.length; k++) {
/* 272 */       if (this.xValues[k].doubleValue() >= minX && this.xValues[k].doubleValue() <= maxX && this.yValues[k].doubleValue() >= minY && this.yValues[k].doubleValue() <= maxY)
/* 276 */         if (this.zValues[k] != null) {
/* 277 */           zMin = Math.min(zMin, this.zValues[k].doubleValue());
/* 278 */           zMax = Math.max(zMax, this.zValues[k].doubleValue());
/*     */         }  
/*     */     } 
/* 283 */     return new Range(zMin, zMax);
/*     */   }
/*     */   
/*     */   public double getMinZValue(double minX, double minY, double maxX, double maxY) {
/* 301 */     double zMin = 1.0E20D;
/* 302 */     for (int k = 0; k < this.zValues.length; k++) {
/* 303 */       if (this.zValues[k] != null)
/* 304 */         zMin = Math.min(zMin, this.zValues[k].doubleValue()); 
/*     */     } 
/* 307 */     return zMin;
/*     */   }
/*     */   
/*     */   public int getSeriesCount() {
/* 319 */     return 1;
/*     */   }
/*     */   
/*     */   public Comparable getSeriesKey(int series) {
/* 332 */     if (series > 0)
/* 333 */       throw new IllegalArgumentException("Only one series for contour"); 
/* 335 */     return this.seriesKey;
/*     */   }
/*     */   
/*     */   public int[] getXIndices() {
/* 344 */     return this.xIndex;
/*     */   }
/*     */   
/*     */   public Number[] getXValues() {
/* 353 */     return this.xValues;
/*     */   }
/*     */   
/*     */   public Number getX(int series, int item) {
/* 366 */     if (series > 0)
/* 367 */       throw new IllegalArgumentException("Only one series for contour"); 
/* 369 */     return this.xValues[item];
/*     */   }
/*     */   
/*     */   public Number getXValue(int item) {
/* 380 */     return this.xValues[item];
/*     */   }
/*     */   
/*     */   public Number[] getYValues() {
/* 389 */     return this.yValues;
/*     */   }
/*     */   
/*     */   public Number getY(int series, int item) {
/* 402 */     if (series > 0)
/* 403 */       throw new IllegalArgumentException("Only one series for contour"); 
/* 405 */     return this.yValues[item];
/*     */   }
/*     */   
/*     */   public Number[] getZValues() {
/* 414 */     return this.zValues;
/*     */   }
/*     */   
/*     */   public Number getZ(int series, int item) {
/* 427 */     if (series > 0)
/* 428 */       throw new IllegalArgumentException("Only one series for contour"); 
/* 430 */     return this.zValues[item];
/*     */   }
/*     */   
/*     */   public int[] indexX() {
/* 439 */     int[] index = new int[this.xValues.length];
/* 440 */     for (int k = 0; k < index.length; k++)
/* 441 */       index[k] = indexX(k); 
/* 443 */     return index;
/*     */   }
/*     */   
/*     */   public int indexX(int k) {
/* 454 */     int i = Arrays.binarySearch(this.xIndex, k);
/* 455 */     if (i >= 0)
/* 456 */       return i; 
/* 459 */     return -1 * i - 2;
/*     */   }
/*     */   
/*     */   public int indexY(int k) {
/* 472 */     return k / this.xValues.length;
/*     */   }
/*     */   
/*     */   public int indexZ(int i, int j) {
/* 484 */     return this.xValues.length * j + i;
/*     */   }
/*     */   
/*     */   public boolean isDateAxis(int axisNumber) {
/* 495 */     if (axisNumber < 0 || axisNumber > 2)
/* 496 */       return false; 
/* 498 */     return this.dateAxis[axisNumber];
/*     */   }
/*     */   
/*     */   public void setSeriesKeys(Comparable[] seriesKeys) {
/* 507 */     if (seriesKeys.length > 1)
/* 508 */       throw new IllegalArgumentException("Contours only support one series"); 
/* 512 */     this.seriesKey = seriesKeys[0];
/* 513 */     fireDatasetChanged();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\contour\DefaultContourDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */