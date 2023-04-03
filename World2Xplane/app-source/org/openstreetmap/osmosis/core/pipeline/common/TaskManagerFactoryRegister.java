/*    */ package org.openstreetmap.osmosis.core.pipeline.common;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public class TaskManagerFactoryRegister {
/* 30 */   private Map<String, TaskManagerFactory> factoryMap = new HashMap<String, TaskManagerFactory>();
/*    */   
/*    */   public void register(String taskType, TaskManagerFactory factory) {
/* 43 */     if (this.factoryMap.containsKey(taskType))
/* 44 */       throw new OsmosisRuntimeException("Task type \"" + taskType + "\" already exists."); 
/* 47 */     this.factoryMap.put(taskType, factory);
/*    */   }
/*    */   
/*    */   public TaskManagerFactory getInstance(String taskType) {
/* 59 */     if (!this.factoryMap.containsKey(taskType))
/* 60 */       throw new OsmosisRuntimeException("Task type " + taskType + " doesn't exist."); 
/* 64 */     return this.factoryMap.get(taskType);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\common\TaskManagerFactoryRegister.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */