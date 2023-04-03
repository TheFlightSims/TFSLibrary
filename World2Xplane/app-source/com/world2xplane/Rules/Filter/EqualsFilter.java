/*    */ package com.world2xplane.Rules.Filter;
/*    */ 
/*    */ public class EqualsFilter extends Filter {
/*    */   private String filter;
/*    */   
/*    */   public EqualsFilter(String name) {
/* 31 */     super(name);
/*    */   }
/*    */   
/*    */   public boolean acceptsValue(String value) {
/* 35 */     if ("*".equals(this.filter))
/* 36 */       return true; 
/* 38 */     return this.filter.equals(value.trim());
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Filter\EqualsFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */