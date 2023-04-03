/*     */ package org.jdom.filter;
/*     */ 
/*     */ final class AndFilter extends AbstractFilter {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: AndFilter.java,v $ $Revision: 1.4 $ $Date: 2007/11/10 05:29:00 $";
/*     */   
/*     */   private Filter left;
/*     */   
/*     */   private Filter right;
/*     */   
/*     */   public AndFilter(Filter left, Filter right) {
/*  85 */     if (left == null || right == null)
/*  86 */       throw new IllegalArgumentException("null filter not allowed"); 
/*  88 */     this.left = left;
/*  89 */     this.right = right;
/*     */   }
/*     */   
/*     */   public boolean matches(Object obj) {
/*  93 */     return (this.left.matches(obj) && this.right.matches(obj));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  97 */     if (this == obj)
/*  98 */       return true; 
/* 101 */     if (obj instanceof AndFilter) {
/* 102 */       AndFilter filter = (AndFilter)obj;
/* 103 */       if ((this.left.equals(filter.left) && this.right.equals(filter.right)) || (this.left.equals(filter.right) && this.right.equals(filter.left)))
/* 105 */         return true; 
/*     */     } 
/* 108 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 112 */     return 31 * this.left.hashCode() + this.right.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 116 */     return (new StringBuffer(64)).append("[AndFilter: ").append(this.left.toString()).append(",\n").append("            ").append(this.right.toString()).append("]").toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\filter\AndFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */