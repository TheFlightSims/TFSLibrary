/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ public class NormalizedMatrixSeries extends MatrixSeries {
/*     */   public static final double DEFAULT_SCALE_FACTOR = 1.0D;
/*     */   
/*  63 */   private double m_scaleFactor = 1.0D;
/*     */   
/*     */   private double m_totalSum;
/*     */   
/*     */   public NormalizedMatrixSeries(String name, int rows, int columns) {
/*  76 */     super(name, rows, columns);
/*  84 */     this.m_totalSum = Double.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public Number getItem(int itemIndex) {
/*  97 */     int i = getItemRow(itemIndex);
/*  98 */     int j = getItemColumn(itemIndex);
/* 100 */     double mij = get(i, j) * this.m_scaleFactor;
/* 101 */     Number n = new Double(mij / this.m_totalSum);
/* 103 */     return n;
/*     */   }
/*     */   
/*     */   public void setScaleFactor(double factor) {
/* 115 */     this.m_scaleFactor = factor;
/*     */   }
/*     */   
/*     */   public double getScaleFactor() {
/* 126 */     return this.m_scaleFactor;
/*     */   }
/*     */   
/*     */   public void update(int i, int j, double mij) {
/* 134 */     this.m_totalSum -= get(i, j);
/* 135 */     this.m_totalSum += mij;
/* 137 */     super.update(i, j, mij);
/*     */   }
/*     */   
/*     */   public void zeroAll() {
/* 144 */     this.m_totalSum = 0.0D;
/* 145 */     super.zeroAll();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\NormalizedMatrixSeries.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */