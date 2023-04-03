/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class DialShape implements Serializable {
/*     */   private static final long serialVersionUID = -3471933055190251131L;
/*     */   
/*  58 */   public static final DialShape CIRCLE = new DialShape("DialShape.CIRCLE");
/*     */   
/*  61 */   public static final DialShape CHORD = new DialShape("DialShape.CHORD");
/*     */   
/*  64 */   public static final DialShape PIE = new DialShape("DialShape.PIE");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private DialShape(String name) {
/*  75 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  84 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  96 */     if (this == obj)
/*  97 */       return true; 
/*  99 */     if (!(obj instanceof DialShape))
/* 100 */       return false; 
/* 102 */     DialShape shape = (DialShape)obj;
/* 103 */     if (!this.name.equals(shape.toString()))
/* 104 */       return false; 
/* 106 */     return true;
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 117 */     if (equals(CIRCLE))
/* 118 */       return CIRCLE; 
/* 120 */     if (equals(CHORD))
/* 121 */       return CHORD; 
/* 123 */     if (equals(PIE))
/* 124 */       return PIE; 
/* 126 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\DialShape.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */