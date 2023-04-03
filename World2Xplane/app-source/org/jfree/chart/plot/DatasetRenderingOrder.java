/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class DatasetRenderingOrder implements Serializable {
/*     */   private static final long serialVersionUID = -600593412366385072L;
/*     */   
/*  63 */   public static final DatasetRenderingOrder FORWARD = new DatasetRenderingOrder("DatasetRenderingOrder.FORWARD");
/*     */   
/*  70 */   public static final DatasetRenderingOrder REVERSE = new DatasetRenderingOrder("DatasetRenderingOrder.REVERSE");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private DatasetRenderingOrder(String name) {
/*  82 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  91 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 104 */     if (this == o)
/* 105 */       return true; 
/* 107 */     if (!(o instanceof DatasetRenderingOrder))
/* 108 */       return false; 
/* 111 */     DatasetRenderingOrder order = (DatasetRenderingOrder)o;
/* 112 */     if (!this.name.equals(order.toString()))
/* 113 */       return false; 
/* 116 */     return true;
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 128 */     if (equals(FORWARD))
/* 129 */       return FORWARD; 
/* 131 */     if (equals(REVERSE))
/* 132 */       return REVERSE; 
/* 134 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\DatasetRenderingOrder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */