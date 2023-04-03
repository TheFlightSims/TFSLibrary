/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class HistogramBin implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 7614685080015589931L;
/*     */   
/*     */   private int count;
/*     */   
/*     */   private double startBoundary;
/*     */   
/*     */   private double endBoundary;
/*     */   
/*     */   public HistogramBin(double startBoundary, double endBoundary) {
/*  75 */     if (startBoundary > endBoundary)
/*  76 */       throw new IllegalArgumentException("HistogramBin():  startBoundary > endBoundary."); 
/*  80 */     this.count = 0;
/*  81 */     this.startBoundary = startBoundary;
/*  82 */     this.endBoundary = endBoundary;
/*     */   }
/*     */   
/*     */   public int getCount() {
/*  91 */     return this.count;
/*     */   }
/*     */   
/*     */   public void incrementCount() {
/*  98 */     this.count++;
/*     */   }
/*     */   
/*     */   public double getStartBoundary() {
/* 107 */     return this.startBoundary;
/*     */   }
/*     */   
/*     */   public double getEndBoundary() {
/* 116 */     return this.endBoundary;
/*     */   }
/*     */   
/*     */   public double getBinWidth() {
/* 125 */     return this.endBoundary - this.startBoundary;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 136 */     if (obj == null)
/* 137 */       return false; 
/* 139 */     if (obj == this)
/* 140 */       return true; 
/* 142 */     if (obj instanceof HistogramBin) {
/* 143 */       HistogramBin bin = (HistogramBin)obj;
/* 144 */       boolean b0 = (bin.startBoundary == this.startBoundary);
/* 145 */       boolean b1 = (bin.endBoundary == this.endBoundary);
/* 146 */       boolean b2 = (bin.count == this.count);
/* 147 */       return (b0 && b1 && b2);
/*     */     } 
/* 149 */     return false;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 160 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\statistics\HistogramBin.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */