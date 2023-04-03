/*    */ package com.typesafe.config;
/*    */ 
/*    */ import com.typesafe.config.impl.ConfigImplUtil;
/*    */ import java.util.List;
/*    */ 
/*    */ public final class ConfigUtil {
/*    */   public static String quoteString(String s) {
/* 24 */     return ConfigImplUtil.renderJsonString(s);
/*    */   }
/*    */   
/*    */   public static String joinPath(String... elements) {
/* 43 */     return ConfigImplUtil.joinPath(elements);
/*    */   }
/*    */   
/*    */   public static String joinPath(List<String> elements) {
/* 62 */     return ConfigImplUtil.joinPath(elements);
/*    */   }
/*    */   
/*    */   public static List<String> splitPath(String path) {
/* 81 */     return ConfigImplUtil.splitPath(path);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\ConfigUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */