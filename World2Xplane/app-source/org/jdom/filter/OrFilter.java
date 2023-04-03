/*     */ package org.jdom.filter;
/*     */ 
/*     */ final class OrFilter extends AbstractFilter {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: OrFilter.java,v $ $Revision: 1.5 $ $Date: 2007/11/10 05:29:00 $";
/*     */   
/*     */   private Filter left;
/*     */   
/*     */   private Filter right;
/*     */   
/*     */   public OrFilter(Filter left, Filter right) {
/*  85 */     if (left == null || right == null)
/*  86 */       throw new IllegalArgumentException("null filter not allowed"); 
/*  88 */     this.left = left;
/*  89 */     this.right = right;
/*     */   }
/*     */   
/*     */   public boolean matches(Object obj) {
/*  93 */     return (this.left.matches(obj) || this.right.matches(obj));
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  97 */     if (this == obj)
/*  98 */       return true; 
/* 101 */     if (obj instanceof OrFilter) {
/* 102 */       OrFilter filter = (OrFilter)obj;
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
/* 116 */     return (new StringBuffer(64)).append("[OrFilter: ").append(this.left.toString()).append(",\n").append("           ").append(this.right.toString()).append("]").toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\filter\OrFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */