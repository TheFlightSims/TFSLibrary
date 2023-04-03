/*    */ package com.world2xplane.XPlane;
/*    */ 
/*    */ public class DSFObjectDefinition {
/* 27 */   private String path = "";
/*    */   
/* 28 */   private int requiredLevel = 6;
/*    */   
/*    */   public DSFObjectDefinition(String path, int requiredLevel) {
/* 31 */     this.path = path;
/* 32 */     this.requiredLevel = requiredLevel;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 36 */     return this.requiredLevel + ":" + this.path;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 40 */     return toString().hashCode();
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 44 */     if (o == null || !(o instanceof DSFObjectDefinition))
/* 45 */       return false; 
/* 47 */     return o.toString().equals(toString());
/*    */   }
/*    */   
/*    */   public String getPath() {
/* 51 */     return this.path;
/*    */   }
/*    */   
/*    */   public void setPath(String path) {
/* 55 */     this.path = path;
/*    */   }
/*    */   
/*    */   public int getRequiredLevel() {
/* 59 */     return this.requiredLevel;
/*    */   }
/*    */   
/*    */   public void setRequiredLevel(int requiredLevel) {
/* 63 */     this.requiredLevel = requiredLevel;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\DSFObjectDefinition.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */