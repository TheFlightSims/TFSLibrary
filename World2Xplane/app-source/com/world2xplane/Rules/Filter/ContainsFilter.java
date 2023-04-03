/*    */ package com.world2xplane.Rules.Filter;
/*    */ 
/*    */ public class ContainsFilter extends Filter {
/*    */   private String filter;
/*    */   
/*    */   public ContainsFilter(String name) {
/* 31 */     super(name);
/*    */   }
/*    */   
/*    */   public boolean acceptsValue(String value) {
/* 35 */     if ("*".equals(this.filter))
/* 36 */       return true; 
/* 38 */     return value.toLowerCase().trim().contains(this.filter);
/*    */   }
/*    */   
/*    */   public String getFilter() {
/* 42 */     return this.filter;
/*    */   }
/*    */   
/*    */   public void setFilter(String filter) {
/* 46 */     this.filter = filter;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Filter\ContainsFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */