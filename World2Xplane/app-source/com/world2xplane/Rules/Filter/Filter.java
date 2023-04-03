/*    */ package com.world2xplane.Rules.Filter;
/*    */ 
/*    */ public abstract class Filter {
/*    */   public String name;
/*    */   
/*    */   public Filter(String name) {
/* 30 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 34 */     return this.name;
/*    */   }
/*    */   
/*    */   public abstract boolean acceptsValue(String paramString);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\Filter\Filter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */