/*    */ package com.typesafe.config.impl;
/*    */ 
/*    */ import com.typesafe.config.ConfigOrigin;
/*    */ import com.typesafe.config.ConfigValueType;
/*    */ import java.io.ObjectStreamException;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ final class ConfigInt extends ConfigNumber implements Serializable {
/*    */   private static final long serialVersionUID = 2L;
/*    */   
/*    */   private final int value;
/*    */   
/*    */   ConfigInt(ConfigOrigin origin, int value, String originalText) {
/* 19 */     super(origin, originalText);
/* 20 */     this.value = value;
/*    */   }
/*    */   
/*    */   public ConfigValueType valueType() {
/* 25 */     return ConfigValueType.NUMBER;
/*    */   }
/*    */   
/*    */   public Integer unwrapped() {
/* 30 */     return Integer.valueOf(this.value);
/*    */   }
/*    */   
/*    */   String transformToString() {
/* 35 */     String s = super.transformToString();
/* 36 */     if (s == null)
/* 37 */       return Integer.toString(this.value); 
/* 39 */     return s;
/*    */   }
/*    */   
/*    */   protected long longValue() {
/* 44 */     return this.value;
/*    */   }
/*    */   
/*    */   protected double doubleValue() {
/* 49 */     return this.value;
/*    */   }
/*    */   
/*    */   protected ConfigInt newCopy(ConfigOrigin origin) {
/* 54 */     return new ConfigInt(origin, this.value, this.originalText);
/*    */   }
/*    */   
/*    */   private Object writeReplace() throws ObjectStreamException {
/* 59 */     return new SerializedConfigValue(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ConfigInt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */