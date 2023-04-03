/*    */ package org.openstreetmap.osmosis.core.bound.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskConfiguration;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;
/*    */ import org.openstreetmap.osmosis.core.pipeline.v0_6.SinkSourceManager;
/*    */ 
/*    */ public class BoundComputerFactory extends TaskManagerFactory {
/*    */   private static final String ARG_ORIGIN = "origin";
/*    */   
/*    */   private static final String DEFAULT_ORIGIN = "Osmosis/0.43.1";
/*    */   
/*    */   protected TaskManager createTaskManagerImpl(TaskConfiguration taskConfig) {
/* 25 */     String origin = getStringArgument(taskConfig, "origin", "Osmosis/0.43.1");
/* 27 */     return (TaskManager)new SinkSourceManager(taskConfig.getId(), new BoundComputer(origin), taskConfig.getPipeArgs());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\bound\v0_6\BoundComputerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */