/*     */ package com.typesafe.config;
/*     */ 
/*     */ import com.typesafe.config.impl.ConfigImpl;
/*     */ import java.util.Map;
/*     */ 
/*     */ public final class ConfigValueFactory {
/*     */   public static ConfigValue fromAnyRef(Object object, String originDescription) {
/*  66 */     return ConfigImpl.fromAnyRef(object, originDescription);
/*     */   }
/*     */   
/*     */   public static ConfigObject fromMap(Map<String, ? extends Object> values, String originDescription) {
/*  94 */     return (ConfigObject)fromAnyRef(values, originDescription);
/*     */   }
/*     */   
/*     */   public static ConfigList fromIterable(Iterable<? extends Object> values, String originDescription) {
/* 108 */     return (ConfigList)fromAnyRef(values, originDescription);
/*     */   }
/*     */   
/*     */   public static ConfigValue fromAnyRef(Object object) {
/* 119 */     return fromAnyRef(object, null);
/*     */   }
/*     */   
/*     */   public static ConfigObject fromMap(Map<String, ? extends Object> values) {
/* 134 */     return fromMap(values, null);
/*     */   }
/*     */   
/*     */   public static ConfigList fromIterable(Iterable<? extends Object> values) {
/* 145 */     return fromIterable(values, null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\ConfigValueFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */