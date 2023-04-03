/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class SeriesRenderingOrder implements Serializable {
/*     */   private static final long serialVersionUID = 209336477448807735L;
/*     */   
/*  59 */   public static final SeriesRenderingOrder FORWARD = new SeriesRenderingOrder("SeriesRenderingOrder.FORWARD");
/*     */   
/*  66 */   public static final SeriesRenderingOrder REVERSE = new SeriesRenderingOrder("SeriesRenderingOrder.REVERSE");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private SeriesRenderingOrder(String name) {
/*  78 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  87 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 100 */     if (this == obj)
/* 101 */       return true; 
/* 103 */     if (!(obj instanceof SeriesRenderingOrder))
/* 104 */       return false; 
/* 107 */     SeriesRenderingOrder order = (SeriesRenderingOrder)obj;
/* 108 */     if (!this.name.equals(order.toString()))
/* 109 */       return false; 
/* 112 */     return true;
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 124 */     if (equals(FORWARD))
/* 125 */       return FORWARD; 
/* 127 */     if (equals(REVERSE))
/* 128 */       return REVERSE; 
/* 130 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\SeriesRenderingOrder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */