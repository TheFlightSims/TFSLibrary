/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.general.Series;
/*     */ 
/*     */ public class MatrixSeries extends Series implements Serializable {
/*     */   private static final long serialVersionUID = 7934188527308315704L;
/*     */   
/*     */   protected double[][] data;
/*     */   
/*     */   public MatrixSeries(String name, int rows, int columns) {
/*  78 */     super(name);
/*  79 */     this.data = new double[rows][columns];
/*  80 */     zeroAll();
/*     */   }
/*     */   
/*     */   public int getColumnsCount() {
/*  89 */     return (this.data[0]).length;
/*     */   }
/*     */   
/*     */   public Number getItem(int itemIndex) {
/* 101 */     int i = getItemRow(itemIndex);
/* 102 */     int j = getItemColumn(itemIndex);
/* 104 */     Number n = new Double(get(i, j));
/* 106 */     return n;
/*     */   }
/*     */   
/*     */   public int getItemColumn(int itemIndex) {
/* 119 */     return itemIndex % getColumnsCount();
/*     */   }
/*     */   
/*     */   public int getItemCount() {
/* 129 */     return getRowCount() * getColumnsCount();
/*     */   }
/*     */   
/*     */   public int getItemRow(int itemIndex) {
/* 142 */     return itemIndex / getColumnsCount();
/*     */   }
/*     */   
/*     */   public int getRowCount() {
/* 152 */     return this.data.length;
/*     */   }
/*     */   
/*     */   public double get(int i, int j) {
/* 165 */     return this.data[i][j];
/*     */   }
/*     */   
/*     */   public void update(int i, int j, double mij) {
/* 177 */     this.data[i][j] = mij;
/* 178 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public void zeroAll() {
/* 188 */     int rows = getRowCount();
/* 189 */     int columns = getColumnsCount();
/* 191 */     for (int row = 0; row < rows; row++) {
/* 192 */       for (int column = 0; column < columns; column++)
/* 193 */         this.data[row][column] = 0.0D; 
/*     */     } 
/* 196 */     fireSeriesChanged();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 207 */     if (obj == this)
/* 208 */       return true; 
/* 210 */     if (obj instanceof MatrixSeries && super.equals(obj)) {
/* 211 */       MatrixSeries m = (MatrixSeries)obj;
/* 212 */       if (getRowCount() != m.getRowCount())
/* 213 */         return false; 
/* 215 */       if (getColumnsCount() != m.getColumnsCount())
/* 216 */         return false; 
/* 218 */       return true;
/*     */     } 
/* 220 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\MatrixSeries.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */