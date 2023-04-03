/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class RangeType implements Serializable {
/*     */   private static final long serialVersionUID = -9073319010650549239L;
/*     */   
/*  58 */   public static final RangeType FULL = new RangeType("RangeType.FULL");
/*     */   
/*  61 */   public static final RangeType POSITIVE = new RangeType("RangeType.POSITIVE");
/*     */   
/*  65 */   public static final RangeType NEGATIVE = new RangeType("RangeType.NEGATIVE");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private RangeType(String name) {
/*  77 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  86 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  99 */     if (this == obj)
/* 100 */       return true; 
/* 102 */     if (!(obj instanceof RangeType))
/* 103 */       return false; 
/* 105 */     RangeType that = (RangeType)obj;
/* 106 */     if (!this.name.equals(that.toString()))
/* 107 */       return false; 
/* 109 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 118 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 129 */     if (equals(FULL))
/* 130 */       return FULL; 
/* 132 */     if (equals(POSITIVE))
/* 133 */       return POSITIVE; 
/* 135 */     if (equals(NEGATIVE))
/* 136 */       return NEGATIVE; 
/* 138 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\RangeType.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */