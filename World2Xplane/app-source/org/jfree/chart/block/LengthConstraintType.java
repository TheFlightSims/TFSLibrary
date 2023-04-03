/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class LengthConstraintType implements Serializable {
/*     */   private static final long serialVersionUID = -1156658804028142978L;
/*     */   
/*  57 */   public static final LengthConstraintType NONE = new LengthConstraintType("LengthConstraintType.NONE");
/*     */   
/*  61 */   public static final LengthConstraintType RANGE = new LengthConstraintType("RectangleConstraintType.RANGE");
/*     */   
/*  65 */   public static final LengthConstraintType FIXED = new LengthConstraintType("LengthConstraintType.FIXED");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private LengthConstraintType(String name) {
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
/* 101 */     if (!(obj instanceof LengthConstraintType))
/* 102 */       return false; 
/* 104 */     LengthConstraintType that = (LengthConstraintType)obj;
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
/* 128 */     if (equals(NONE))
/* 129 */       return NONE; 
/* 131 */     if (equals(RANGE))
/* 132 */       return RANGE; 
/* 134 */     if (equals(FIXED))
/* 135 */       return FIXED; 
/* 137 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\block\LengthConstraintType.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */