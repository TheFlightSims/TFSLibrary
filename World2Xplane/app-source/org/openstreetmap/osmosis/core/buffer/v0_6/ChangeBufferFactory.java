/*    */ package org.openstreetmap.osmosis.core.buffer.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskConfiguration;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;
/*    */ import org.openstreetmap.osmosis.core.pipeline.v0_6.ChangeSinkRunnableChangeSourceManager;
/*    */ 
/*    */ public class ChangeBufferFactory extends TaskManagerFactory {
/*    */   private static final String ARG_BUFFER_CAPACITY = "bufferCapacity";
/*    */   
/*    */   private static final int DEFAULT_BUFFER_CAPACITY = 20;
/*    */   
/*    */   protected TaskManager createTaskManagerImpl(TaskConfiguration taskConfig) {
/* 28 */     int bufferCapacity = getIntegerArgument(taskConfig, "bufferCapacity", getDefaultIntegerArgument(taskConfig, 20));
/* 34 */     return (TaskManager)new ChangeSinkRunnableChangeSourceManager(taskConfig.getId(), new ChangeBuffer(bufferCapacity), taskConfig.getPipeArgs());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\buffer\v0_6\ChangeBufferFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */