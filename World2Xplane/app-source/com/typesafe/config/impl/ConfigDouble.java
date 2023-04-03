/*    */ package com.typesafe.config.impl;
/*    */ 
/*    */ import com.typesafe.config.ConfigOrigin;
/*    */ import com.typesafe.config.ConfigValueType;
/*    */ import java.io.ObjectStreamException;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ final class ConfigDouble extends ConfigNumber implements Serializable {
/*    */   private static final long serialVersionUID = 2L;
/*    */   
/*    */   private final double value;
/*    */   
/*    */   ConfigDouble(ConfigOrigin origin, double value, String originalText) {
/* 19 */     super(origin, originalText);
/* 20 */     this.value = value;
/*    */   }
/*    */   
/*    */   public ConfigValueType valueType() {
/* 25 */     return ConfigValueType.NUMBER;
/*    */   }
/*    */   
/*    */   public Double unwrapped() {
/* 30 */     return Double.valueOf(this.value);
/*    */   }
/*    */   
/*    */   String transformToString() {
/* 35 */     String s = super.transformToString();
/* 36 */     if (s == null)
/* 37 */       return Double.toString(this.value); 
/* 39 */     return s;
/*    */   }
/*    */   
/*    */   protected long longValue() {
/* 44 */     return (long)this.value;
/*    */   }
/*    */   
/*    */   protected double doubleValue() {
/* 49 */     return this.value;
/*    */   }
/*    */   
/*    */   protected ConfigDouble newCopy(ConfigOrigin origin) {
/* 54 */     return new ConfigDouble(origin, this.value, this.originalText);
/*    */   }
/*    */   
/*    */   private Object writeReplace() throws ObjectStreamException {
/* 59 */     return new SerializedConfigValue(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ConfigDouble.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */