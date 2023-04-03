/*     */ package org.jfree.chart.renderer;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class AreaRendererEndType implements Serializable {
/*     */   private static final long serialVersionUID = -1774146392916359839L;
/*     */   
/*  59 */   public static final AreaRendererEndType TAPER = new AreaRendererEndType("AreaRendererEndType.TAPER");
/*     */   
/*  66 */   public static final AreaRendererEndType TRUNCATE = new AreaRendererEndType("AreaRendererEndType.TRUNCATE");
/*     */   
/*  73 */   public static final AreaRendererEndType LEVEL = new AreaRendererEndType("AreaRendererEndType.LEVEL");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private AreaRendererEndType(String name) {
/*  86 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  95 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 108 */     if (this == o)
/* 109 */       return true; 
/* 111 */     if (!(o instanceof AreaRendererEndType))
/* 112 */       return false; 
/* 115 */     AreaRendererEndType t = (AreaRendererEndType)o;
/* 116 */     if (!this.name.equals(t.toString()))
/* 117 */       return false; 
/* 120 */     return true;
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 132 */     Object result = null;
/* 133 */     if (equals(LEVEL)) {
/* 134 */       result = LEVEL;
/* 136 */     } else if (equals(TAPER)) {
/* 137 */       result = TAPER;
/* 139 */     } else if (equals(TRUNCATE)) {
/* 140 */       result = TRUNCATE;
/*     */     } 
/* 142 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\renderer\AreaRendererEndType.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */