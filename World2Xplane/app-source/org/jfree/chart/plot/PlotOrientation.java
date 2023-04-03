/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class PlotOrientation implements Serializable {
/*     */   private static final long serialVersionUID = -2508771828190337782L;
/*     */   
/*  58 */   public static final PlotOrientation HORIZONTAL = new PlotOrientation("PlotOrientation.HORIZONTAL");
/*     */   
/*  62 */   public static final PlotOrientation VERTICAL = new PlotOrientation("PlotOrientation.VERTICAL");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private PlotOrientation(String name) {
/*  74 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  83 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  96 */     if (this == o)
/*  97 */       return true; 
/*  99 */     if (!(o instanceof PlotOrientation))
/* 100 */       return false; 
/* 103 */     PlotOrientation orientation = (PlotOrientation)o;
/* 104 */     if (!this.name.equals(orientation.toString()))
/* 105 */       return false; 
/* 108 */     return true;
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 120 */     Object result = null;
/* 121 */     if (equals(HORIZONTAL)) {
/* 122 */       result = HORIZONTAL;
/* 124 */     } else if (equals(VERTICAL)) {
/* 125 */       result = VERTICAL;
/*     */     } 
/* 127 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\PlotOrientation.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */