/*     */ package org.jfree.chart.event;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class ChartChangeEventType implements Serializable {
/*     */   private static final long serialVersionUID = 5481917022435735602L;
/*     */   
/*  57 */   public static final ChartChangeEventType GENERAL = new ChartChangeEventType("ChartChangeEventType.GENERAL");
/*     */   
/*  61 */   public static final ChartChangeEventType NEW_DATASET = new ChartChangeEventType("ChartChangeEventType.NEW_DATASET");
/*     */   
/*  65 */   public static final ChartChangeEventType DATASET_UPDATED = new ChartChangeEventType("ChartChangeEventType.DATASET_UPDATED");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private ChartChangeEventType(String name) {
/*  77 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  86 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  98 */     if (this == obj)
/*  99 */       return true; 
/* 101 */     if (!(obj instanceof ChartChangeEventType))
/* 102 */       return false; 
/* 104 */     ChartChangeEventType that = (ChartChangeEventType)obj;
/* 105 */     if (!this.name.equals(that.toString()))
/* 106 */       return false; 
/* 108 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 117 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 128 */     if (equals(GENERAL))
/* 129 */       return GENERAL; 
/* 131 */     if (equals(NEW_DATASET))
/* 132 */       return NEW_DATASET; 
/* 134 */     if (equals(DATASET_UPDATED))
/* 135 */       return DATASET_UPDATED; 
/* 137 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\event\ChartChangeEventType.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */