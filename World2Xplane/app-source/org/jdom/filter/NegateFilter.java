/*     */ package org.jdom.filter;
/*     */ 
/*     */ final class NegateFilter extends AbstractFilter {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: NegateFilter.java,v $ $Revision: 1.4 $ $Date: 2007/11/10 05:29:00 $";
/*     */   
/*     */   private Filter filter;
/*     */   
/*     */   public NegateFilter(Filter filter) {
/*  80 */     this.filter = filter;
/*     */   }
/*     */   
/*     */   public boolean matches(Object obj) {
/*  84 */     return !this.filter.matches(obj);
/*     */   }
/*     */   
/*     */   public Filter negate() {
/*  88 */     return this.filter;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  92 */     if (this == obj)
/*  93 */       return true; 
/*  96 */     if (obj instanceof NegateFilter)
/*  97 */       return this.filter.equals(((NegateFilter)obj).filter); 
/*  99 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 103 */     return this.filter.hashCode() ^ 0xFFFFFFFF;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 107 */     return (new StringBuffer(64)).append("[NegateFilter: ").append(this.filter.toString()).append("]").toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\filter\NegateFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */