/*    */ package com.world2xplane.Rules.Filter;
/*    */ 
/*    */ public class FalseFilter extends Filter {
/*    */   private String filter;
/*    */   
/*    */   public FalseFilter(String name) {
/* 33 */     super(name);
/*    */   }
/*    */   
/*    */   public boolean acceptsValue(String value) {
/* 37 */     if ("*".equals(this.filter))
/* 38 */       return false; 
/* 40 */     return !this.filter.equals(value.trim());
/*    */   }
/*    */   
/*    */   public String getFilter() {
/* 44 */     return this.filter;
/*    */   }
/*    */   
/*    */   public void setFilter(String filter) {
/* 48 */     this.filter = filter;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Filter\FalseFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */