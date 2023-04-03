/*     */ package org.jfree.chart;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class LegendRenderingOrder implements Serializable {
/*     */   private static final long serialVersionUID = -3832486612685808616L;
/*     */   
/*  57 */   public static final LegendRenderingOrder STANDARD = new LegendRenderingOrder("LegendRenderingOrder.STANDARD");
/*     */   
/*  61 */   public static final LegendRenderingOrder REVERSE = new LegendRenderingOrder("LegendRenderingOrder.REVERSE");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private LegendRenderingOrder(String name) {
/*  73 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  82 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  94 */     if (this == obj)
/*  95 */       return true; 
/*  97 */     if (!(obj instanceof LegendRenderingOrder))
/*  98 */       return false; 
/* 100 */     LegendRenderingOrder order = (LegendRenderingOrder)obj;
/* 101 */     if (!this.name.equals(order.toString()))
/* 102 */       return false; 
/* 104 */     return true;
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 115 */     if (equals(STANDARD))
/* 116 */       return STANDARD; 
/* 118 */     if (equals(REVERSE))
/* 119 */       return REVERSE; 
/* 121 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\LegendRenderingOrder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */