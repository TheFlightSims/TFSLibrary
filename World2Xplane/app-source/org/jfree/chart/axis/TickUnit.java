/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class TickUnit implements Comparable, Serializable {
/*     */   private static final long serialVersionUID = 510179855057013974L;
/*     */   
/*     */   private double size;
/*     */   
/*     */   public TickUnit(double size) {
/*  78 */     this.size = size;
/*     */   }
/*     */   
/*     */   public double getSize() {
/*  87 */     return this.size;
/*     */   }
/*     */   
/*     */   public String valueToString(double value) {
/* 100 */     return String.valueOf(value);
/*     */   }
/*     */   
/*     */   public int compareTo(Object object) {
/* 114 */     if (object instanceof TickUnit) {
/* 115 */       TickUnit other = (TickUnit)object;
/* 116 */       if (this.size > other.getSize())
/* 117 */         return 1; 
/* 119 */       if (this.size < other.getSize())
/* 120 */         return -1; 
/* 123 */       return 0;
/*     */     } 
/* 127 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 141 */     if (obj == null)
/* 142 */       return false; 
/* 144 */     if (obj == this)
/* 145 */       return true; 
/* 147 */     if (obj instanceof TickUnit) {
/* 148 */       TickUnit tu = (TickUnit)obj;
/* 149 */       return (this.size == tu.size);
/*     */     } 
/* 151 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 161 */     long temp = (this.size != 0.0D) ? Double.doubleToLongBits(this.size) : 0L;
/* 162 */     return (int)(temp ^ temp >>> 32L);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\TickUnit.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */