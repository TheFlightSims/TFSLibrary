/*    */ package org.openstreetmap.osmosis.core.progress.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskConfiguration;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;
/*    */ import org.openstreetmap.osmosis.core.pipeline.v0_6.ChangeSinkChangeSourceManager;
/*    */ 
/*    */ public class ChangeProgressLoggerFactory extends TaskManagerFactory {
/*    */   private static final String ARG_LOG_INTERVAL = "interval";
/*    */   
/*    */   private static final int DEFAULT_LOG_INTERVAL = 5;
/*    */   
/*    */   private static final String ARG_LABEL = "label";
/*    */   
/*    */   private static final String DEFAULT_LABEL = "";
/*    */   
/*    */   protected TaskManager createTaskManagerImpl(TaskConfiguration taskConfig) {
/* 32 */     int interval = getIntegerArgument(taskConfig, "interval", 5);
/* 33 */     String label = getStringArgument(taskConfig, "label", "");
/* 36 */     ChangeProgressLogger task = new ChangeProgressLogger(interval * 1000, label);
/* 38 */     return (TaskManager)new ChangeSinkChangeSourceManager(taskConfig.getId(), task, taskConfig.getPipeArgs());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\progress\v0_6\ChangeProgressLoggerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */