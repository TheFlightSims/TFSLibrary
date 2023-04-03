/*    */ package com.world2xplane.Rules;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class KeyCache {
/*    */   public String key;
/*    */   
/* 34 */   public Set<String> values = new HashSet<>();
/*    */   
/*    */   public boolean acceptsAll;
/*    */   
/*    */   public KeyCache(boolean b) {
/* 39 */     this.acceptsAll = b;
/*    */   }
/*    */   
/*    */   public KeyCache(String value) {
/* 43 */     this.values.add(value);
/* 44 */     this.acceptsAll = false;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\KeyCache.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */