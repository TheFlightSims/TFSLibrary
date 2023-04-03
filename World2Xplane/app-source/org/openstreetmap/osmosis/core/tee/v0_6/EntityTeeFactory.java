/*    */ package org.openstreetmap.osmosis.core.tee.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskConfiguration;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;
/*    */ import org.openstreetmap.osmosis.core.pipeline.v0_6.SinkMultiSourceManager;
/*    */ 
/*    */ public class EntityTeeFactory extends TaskManagerFactory {
/*    */   private static final String ARG_OUTPUT_COUNT = "outputCount";
/*    */   
/*    */   private static final int DEFAULT_OUTPUT_COUNT = 2;
/*    */   
/*    */   protected TaskManager createTaskManagerImpl(TaskConfiguration taskConfig) {
/* 28 */     int outputCount = getIntegerArgument(taskConfig, "outputCount", getDefaultIntegerArgument(taskConfig, 2));
/* 34 */     return (TaskManager)new SinkMultiSourceManager(taskConfig.getId(), new EntityTee(outputCount), taskConfig.getPipeArgs());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\tee\v0_6\EntityTeeFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */