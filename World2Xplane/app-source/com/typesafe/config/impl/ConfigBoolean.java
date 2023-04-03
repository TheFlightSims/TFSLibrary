/*    */ package com.typesafe.config.impl;
/*    */ 
/*    */ import com.typesafe.config.ConfigOrigin;
/*    */ import com.typesafe.config.ConfigValueType;
/*    */ import java.io.ObjectStreamException;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ final class ConfigBoolean extends AbstractConfigValue implements Serializable {
/*    */   private static final long serialVersionUID = 2L;
/*    */   
/*    */   private final boolean value;
/*    */   
/*    */   ConfigBoolean(ConfigOrigin origin, boolean value) {
/* 19 */     super(origin);
/* 20 */     this.value = value;
/*    */   }
/*    */   
/*    */   public ConfigValueType valueType() {
/* 25 */     return ConfigValueType.BOOLEAN;
/*    */   }
/*    */   
/*    */   public Boolean unwrapped() {
/* 30 */     return Boolean.valueOf(this.value);
/*    */   }
/*    */   
/*    */   String transformToString() {
/* 35 */     return this.value ? "true" : "false";
/*    */   }
/*    */   
/*    */   protected ConfigBoolean newCopy(ConfigOrigin origin) {
/* 40 */     return new ConfigBoolean(origin, this.value);
/*    */   }
/*    */   
/*    */   private Object writeReplace() throws ObjectStreamException {
/* 45 */     return new SerializedConfigValue(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ConfigBoolean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */