/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class LengthAdjustmentType implements Serializable {
/*     */   private static final long serialVersionUID = -6097408511380545010L;
/*     */   
/*  60 */   public static final LengthAdjustmentType NO_CHANGE = new LengthAdjustmentType("NO_CHANGE");
/*     */   
/*  64 */   public static final LengthAdjustmentType EXPAND = new LengthAdjustmentType("EXPAND");
/*     */   
/*  68 */   public static final LengthAdjustmentType CONTRACT = new LengthAdjustmentType("CONTRACT");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private LengthAdjustmentType(String name) {
/*  80 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  89 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 101 */     if (obj == this)
/* 102 */       return true; 
/* 104 */     if (!(obj instanceof LengthAdjustmentType))
/* 105 */       return false; 
/* 107 */     LengthAdjustmentType that = (LengthAdjustmentType)obj;
/* 108 */     if (!this.name.equals(that.name))
/* 109 */       return false; 
/* 111 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 120 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 131 */     if (equals(NO_CHANGE))
/* 132 */       return NO_CHANGE; 
/* 134 */     if (equals(EXPAND))
/* 135 */       return EXPAND; 
/* 137 */     if (equals(CONTRACT))
/* 138 */       return CONTRACT; 
/* 140 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\LengthAdjustmentType.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */