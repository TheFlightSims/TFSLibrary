/*    */ package com.world2xplane.Rules.Filter;
/*    */ 
/*    */ public class NodeFilter extends Filter {
/*    */   private String filter;
/*    */   
/*    */   public NodeFilter(String name) {
/* 53 */     super(name);
/*    */   }
/*    */   
/*    */   public boolean acceptsValue(String value) {
/* 57 */     return true;
/*    */   }
/*    */   
/*    */   public String getFilter() {
/* 61 */     return this.filter;
/*    */   }
/*    */   
/*    */   public void setFilter(String filter) {
/* 65 */     this.filter = filter;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Filter\NodeFilter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */