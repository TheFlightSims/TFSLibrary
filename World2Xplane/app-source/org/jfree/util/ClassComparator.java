/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ 
/*     */ public class ClassComparator implements Comparator, Serializable {
/*     */   private static final long serialVersionUID = -5225335361837391120L;
/*     */   
/*     */   public int compare(Object o1, Object o2) {
/* 102 */     Class c1 = (Class)o1;
/* 103 */     Class c2 = (Class)o2;
/* 104 */     if (c1.equals(o2))
/* 105 */       return 0; 
/* 107 */     if (c1.isAssignableFrom(c2))
/* 108 */       return -1; 
/* 111 */     if (!c2.isAssignableFrom(c2))
/* 112 */       throw new IllegalArgumentException("The classes share no relation"); 
/* 116 */     return 1;
/*     */   }
/*     */   
/*     */   public boolean isComparable(Class c1, Class c2) {
/* 129 */     return (c1.isAssignableFrom(c2) || c2.isAssignableFrom(c1));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\ClassComparator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */