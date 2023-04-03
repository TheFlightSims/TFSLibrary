/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class SortOrder implements Serializable {
/*     */   private static final long serialVersionUID = -2124469847758108312L;
/*     */   
/*  62 */   public static final SortOrder ASCENDING = new SortOrder("SortOrder.ASCENDING");
/*     */   
/*  66 */   public static final SortOrder DESCENDING = new SortOrder("SortOrder.DESCENDING");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private SortOrder(String name) {
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
/* 103 */     if (!(obj instanceof SortOrder))
/* 104 */       return false; 
/* 107 */     SortOrder that = (SortOrder)obj;
/* 108 */     if (!this.name.equals(that.toString()))
/* 109 */       return false; 
/* 112 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 121 */     return this.name.hashCode();
/*     */   }
/*     */   
/*     */   private Object readResolve() throws ObjectStreamException {
/* 132 */     if (equals(ASCENDING))
/* 133 */       return ASCENDING; 
/* 135 */     if (equals(DESCENDING))
/* 136 */       return DESCENDING; 
/* 138 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\SortOrder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */