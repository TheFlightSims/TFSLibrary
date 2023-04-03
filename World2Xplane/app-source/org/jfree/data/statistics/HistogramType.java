/*     */ package org.jfree.data.statistics;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class HistogramType implements Serializable {
/*     */   private static final long serialVersionUID = 2618927186251997727L;
/*     */   
/*  58 */   public static final HistogramType FREQUENCY = new HistogramType("FREQUENCY");
/*     */   
/*  62 */   public static final HistogramType RELATIVE_FREQUENCY = new HistogramType("RELATIVE_FREQUENCY");
/*     */   
/*  66 */   public static final HistogramType SCALE_AREA_TO_1 = new HistogramType("SCALE_AREA_TO_1");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private HistogramType(String name) {
/*  78 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  87 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  99 */     if (obj == null)
/* 100 */       return false; 
/* 103 */     if (obj == this)
/* 104 */       return true; 
/* 107 */     if (!(obj instanceof HistogramType))
/* 108 */       return false; 
/* 111 */     HistogramType t = (HistogramType)obj;
/* 112 */     if (!this.name.equals(t.name))
/* 113 */       return false; 
/* 116 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 126 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 137 */     if (equals(FREQUENCY))
/* 138 */       return FREQUENCY; 
/* 140 */     if (equals(RELATIVE_FREQUENCY))
/* 141 */       return RELATIVE_FREQUENCY; 
/* 143 */     if (equals(SCALE_AREA_TO_1))
/* 144 */       return SCALE_AREA_TO_1; 
/* 146 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\statistics\HistogramType.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */