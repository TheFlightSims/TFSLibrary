/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class CategoryLabelWidthType implements Serializable {
/*     */   private static final long serialVersionUID = -6976024792582949656L;
/*     */   
/*  58 */   public static final CategoryLabelWidthType CATEGORY = new CategoryLabelWidthType("CategoryLabelWidthType.CATEGORY");
/*     */   
/*  64 */   public static final CategoryLabelWidthType RANGE = new CategoryLabelWidthType("CategoryLabelWidthType.RANGE");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private CategoryLabelWidthType(String name) {
/*  75 */     if (name == null)
/*  76 */       throw new IllegalArgumentException("Null 'name' argument."); 
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
/* 103 */     if (!(obj instanceof CategoryLabelWidthType))
/* 104 */       return false; 
/* 106 */     CategoryLabelWidthType t = (CategoryLabelWidthType)obj;
/* 107 */     if (!this.name.equals(t.toString()))
/* 108 */       return false; 
/* 110 */     return true;
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 122 */     if (equals(CATEGORY))
/* 123 */       return CATEGORY; 
/* 125 */     if (equals(RANGE))
/* 126 */       return RANGE; 
/* 128 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\axis\CategoryLabelWidthType.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */