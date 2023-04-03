/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class UnitType implements Serializable {
/*     */   private static final long serialVersionUID = 6531925392288519884L;
/*     */   
/*  59 */   public static final UnitType ABSOLUTE = new UnitType("UnitType.ABSOLUTE");
/*     */   
/*  62 */   public static final UnitType RELATIVE = new UnitType("UnitType.RELATIVE");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private UnitType(String name) {
/*  73 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  82 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  94 */     if (obj == this)
/*  95 */       return true; 
/*  97 */     if (!(obj instanceof UnitType))
/*  98 */       return false; 
/* 100 */     UnitType that = (UnitType)obj;
/* 101 */     if (!this.name.equals(that.name))
/* 102 */       return false; 
/* 104 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 113 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 124 */     if (equals(ABSOLUTE))
/* 125 */       return ABSOLUTE; 
/* 127 */     if (equals(RELATIVE))
/* 128 */       return RELATIVE; 
/* 130 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\UnitType.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */