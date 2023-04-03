/*    */ package org.jdom.filter;
/*    */ 
/*    */ public abstract class AbstractFilter implements Filter {
/*    */   private static final String CVS_ID = "@(#) $RCSfile: AbstractFilter.java,v $ $Revision: 1.6 $ $Date: 2007/11/10 05:29:00 $";
/*    */   
/*    */   public Filter negate() {
/* 71 */     return new NegateFilter(this);
/*    */   }
/*    */   
/*    */   public Filter or(Filter filter) {
/* 75 */     return new OrFilter(this, filter);
/*    */   }
/*    */   
/*    */   public Filter and(Filter filter) {
/* 79 */     return new AndFilter(this, filter);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\filter\AbstractFilter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */