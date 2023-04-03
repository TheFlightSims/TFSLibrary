/*    */ package org.openstreetmap.osmosis.core.pipeline.common;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TaskConfiguration {
/*    */   private String id;
/*    */   
/*    */   private String type;
/*    */   
/*    */   private Map<String, String> pipeArgs;
/*    */   
/*    */   private Map<String, String> configArgs;
/*    */   
/*    */   private String defaultArg;
/*    */   
/*    */   public TaskConfiguration(String id, String type, Map<String, String> pipeArgs, Map<String, String> configArgs, String defaultArg) {
/* 32 */     this.id = id;
/* 33 */     this.type = type;
/* 34 */     this.pipeArgs = Collections.unmodifiableMap(pipeArgs);
/* 35 */     this.configArgs = Collections.unmodifiableMap(configArgs);
/* 36 */     this.defaultArg = defaultArg;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 46 */     return this.id;
/*    */   }
/*    */   
/*    */   public String getType() {
/* 56 */     return this.type;
/*    */   }
/*    */   
/*    */   public Map<String, String> getPipeArgs() {
/* 66 */     return this.pipeArgs;
/*    */   }
/*    */   
/*    */   public Map<String, String> getConfigArgs() {
/* 76 */     return this.configArgs;
/*    */   }
/*    */   
/*    */   public String getDefaultArg() {
/* 86 */     return this.defaultArg;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\common\TaskConfiguration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */