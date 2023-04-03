/*    */ package org.openstreetmap.osmosis.core.misc.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskConfiguration;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;
/*    */ import org.openstreetmap.osmosis.core.pipeline.v0_6.RunnableSourceManager;
/*    */ 
/*    */ public class EmptyReaderFactory extends TaskManagerFactory {
/*    */   protected TaskManager createTaskManagerImpl(TaskConfiguration taskConfig) {
/* 21 */     return (TaskManager)new RunnableSourceManager(taskConfig.getId(), new EmptyReader(), taskConfig.getPipeArgs());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\misc\v0_6\EmptyReaderFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */