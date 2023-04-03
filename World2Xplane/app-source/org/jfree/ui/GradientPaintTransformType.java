/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class GradientPaintTransformType implements Serializable {
/*     */   private static final long serialVersionUID = 8331561784933982450L;
/*     */   
/*  59 */   public static final GradientPaintTransformType VERTICAL = new GradientPaintTransformType("GradientPaintTransformType.VERTICAL");
/*     */   
/*  63 */   public static final GradientPaintTransformType HORIZONTAL = new GradientPaintTransformType("GradientPaintTransformType.HORIZONTAL");
/*     */   
/*  68 */   public static final GradientPaintTransformType CENTER_VERTICAL = new GradientPaintTransformType("GradientPaintTransformType.CENTER_VERTICAL");
/*     */   
/*  73 */   public static final GradientPaintTransformType CENTER_HORIZONTAL = new GradientPaintTransformType("GradientPaintTransformType.CENTER_HORIZONTAL");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private GradientPaintTransformType(String name) {
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
/* 111 */     if (!(o instanceof GradientPaintTransformType))
/* 112 */       return false; 
/* 115 */     GradientPaintTransformType t = (GradientPaintTransformType)o;
/* 116 */     if (!this.name.equals(t.name))
/* 117 */       return false; 
/* 120 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 129 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 140 */     GradientPaintTransformType result = null;
/* 141 */     if (equals(HORIZONTAL)) {
/* 142 */       result = HORIZONTAL;
/* 144 */     } else if (equals(VERTICAL)) {
/* 145 */       result = VERTICAL;
/* 147 */     } else if (equals(CENTER_HORIZONTAL)) {
/* 148 */       result = CENTER_HORIZONTAL;
/* 150 */     } else if (equals(CENTER_VERTICAL)) {
/* 151 */       result = CENTER_VERTICAL;
/*     */     } 
/* 153 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\GradientPaintTransformType.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */