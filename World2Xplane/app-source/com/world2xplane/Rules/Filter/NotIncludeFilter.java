/*    */ package com.world2xplane.Rules.Filter;
/*    */ 
/*    */ public class NotIncludeFilter extends Filter {
/*    */   private String filter;
/*    */   
/*    */   public NotIncludeFilter(String name) {
/* 30 */     super(name);
/*    */   }
/*    */   
/*    */   public boolean acceptsValue(String value) {
/* 34 */     if (this.filter.equals(value))
/* 35 */       return false; 
/* 37 */     return true;
/*    */   }
/*    */   
/*    */   public String getFilter() {
/* 41 */     return this.filter;
/*    */   }
/*    */   
/*    */   public void setFilter(String filter) {
/* 45 */     this.filter = filter;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Filter\NotIncludeFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */