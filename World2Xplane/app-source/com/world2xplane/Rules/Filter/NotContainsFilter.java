/*    */ package com.world2xplane.Rules.Filter;
/*    */ 
/*    */ public class NotContainsFilter extends Filter {
/*    */   private String filter;
/*    */   
/*    */   public NotContainsFilter(String name) {
/* 31 */     super(name);
/*    */   }
/*    */   
/*    */   public boolean acceptsValue(String value) {
/* 35 */     return !value.toLowerCase().trim().contains(this.filter);
/*    */   }
/*    */   
/*    */   public String getFilter() {
/* 39 */     return this.filter;
/*    */   }
/*    */   
/*    */   public void setFilter(String filter) {
/* 43 */     this.filter = filter;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Filter\NotContainsFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */