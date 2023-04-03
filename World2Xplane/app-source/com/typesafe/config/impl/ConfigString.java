/*    */ package com.typesafe.config.impl;
/*    */ 
/*    */ import com.typesafe.config.ConfigOrigin;
/*    */ import com.typesafe.config.ConfigRenderOptions;
/*    */ import com.typesafe.config.ConfigValueType;
/*    */ import java.io.ObjectStreamException;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ final class ConfigString extends AbstractConfigValue implements Serializable {
/*    */   private static final long serialVersionUID = 2L;
/*    */   
/*    */   private final String value;
/*    */   
/*    */   ConfigString(ConfigOrigin origin, String value) {
/* 20 */     super(origin);
/* 21 */     this.value = value;
/*    */   }
/*    */   
/*    */   public ConfigValueType valueType() {
/* 26 */     return ConfigValueType.STRING;
/*    */   }
/*    */   
/*    */   public String unwrapped() {
/* 31 */     return this.value;
/*    */   }
/*    */   
/*    */   String transformToString() {
/* 36 */     return this.value;
/*    */   }
/*    */   
/*    */   protected void render(StringBuilder sb, int indent, boolean atRoot, ConfigRenderOptions options) {
/*    */     String rendered;
/* 42 */     if (options.getJson()) {
/* 43 */       rendered = ConfigImplUtil.renderJsonString(this.value);
/*    */     } else {
/* 45 */       rendered = ConfigImplUtil.renderStringUnquotedIfPossible(this.value);
/*    */     } 
/* 46 */     sb.append(rendered);
/*    */   }
/*    */   
/*    */   protected ConfigString newCopy(ConfigOrigin origin) {
/* 51 */     return new ConfigString(origin, this.value);
/*    */   }
/*    */   
/*    */   private Object writeReplace() throws ObjectStreamException {
/* 56 */     return new SerializedConfigValue(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ConfigString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */