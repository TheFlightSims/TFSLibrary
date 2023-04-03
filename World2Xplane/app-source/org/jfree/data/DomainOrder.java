/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public final class DomainOrder implements Serializable {
/*     */   private static final long serialVersionUID = 4902774943512072627L;
/*     */   
/*  57 */   public static final DomainOrder NONE = new DomainOrder("DomainOrder.NONE");
/*     */   
/*  60 */   public static final DomainOrder ASCENDING = new DomainOrder("DomainOrder.ASCENDING");
/*     */   
/*  64 */   public static final DomainOrder DESCENDING = new DomainOrder("DomainOrder.DESCENDING");
/*     */   
/*     */   private String name;
/*     */   
/*     */   private DomainOrder(String name) {
/*  76 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  85 */     return this.name;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  98 */     if (this == obj)
/*  99 */       return true; 
/* 101 */     if (!(obj instanceof DomainOrder))
/* 102 */       return false; 
/* 104 */     DomainOrder that = (DomainOrder)obj;
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
/* 128 */     if (equals(ASCENDING))
/* 129 */       return ASCENDING; 
/* 131 */     if (equals(DESCENDING))
/* 132 */       return DESCENDING; 
/* 134 */     if (equals(NONE))
/* 135 */       return NONE; 
/* 137 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\DomainOrder.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */