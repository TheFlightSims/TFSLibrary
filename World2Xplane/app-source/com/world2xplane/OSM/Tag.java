/*    */ package com.world2xplane.OSM;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class Tag implements Serializable {
/*    */   private String key;
/*    */   
/*    */   private String value;
/*    */   
/*    */   public Tag(String key, String value) {
/* 32 */     this.key = key;
/* 33 */     this.value = value;
/*    */   }
/*    */   
/*    */   public Tag() {}
/*    */   
/*    */   public String getKey() {
/* 41 */     return this.key;
/*    */   }
/*    */   
/*    */   public void setKey(String key) {
/* 45 */     this.key = key;
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 49 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(String value) {
/* 53 */     this.value = value;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 57 */     return this.key + " = " + this.value;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\OSM\Tag.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */