/*    */ package com.typesafe.config.impl;
/*    */ 
/*    */ import com.typesafe.config.ConfigOrigin;
/*    */ import com.typesafe.config.ConfigRenderOptions;
/*    */ import com.typesafe.config.ConfigValueType;
/*    */ import java.io.ObjectStreamException;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ final class ConfigNull extends AbstractConfigValue implements Serializable {
/*    */   private static final long serialVersionUID = 2L;
/*    */   
/*    */   ConfigNull(ConfigOrigin origin) {
/* 26 */     super(origin);
/*    */   }
/*    */   
/*    */   public ConfigValueType valueType() {
/* 31 */     return ConfigValueType.NULL;
/*    */   }
/*    */   
/*    */   public Object unwrapped() {
/* 36 */     return null;
/*    */   }
/*    */   
/*    */   String transformToString() {
/* 41 */     return "null";
/*    */   }
/*    */   
/*    */   protected void render(StringBuilder sb, int indent, boolean atRoot, ConfigRenderOptions options) {
/* 46 */     sb.append("null");
/*    */   }
/*    */   
/*    */   protected ConfigNull newCopy(ConfigOrigin origin) {
/* 51 */     return new ConfigNull(origin);
/*    */   }
/*    */   
/*    */   private Object writeReplace() throws ObjectStreamException {
/* 56 */     return new SerializedConfigValue(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ConfigNull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */