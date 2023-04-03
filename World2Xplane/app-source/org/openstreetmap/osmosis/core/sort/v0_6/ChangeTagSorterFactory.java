/*    */ package org.openstreetmap.osmosis.core.sort.v0_6;
/*    */ 
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskConfiguration;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;
/*    */ import org.openstreetmap.osmosis.core.pipeline.v0_6.ChangeSinkChangeSourceManager;
/*    */ 
/*    */ public class ChangeTagSorterFactory extends TaskManagerFactory {
/*    */   protected TaskManager createTaskManagerImpl(TaskConfiguration taskConfig) {
/* 23 */     return (TaskManager)new ChangeSinkChangeSourceManager(taskConfig.getId(), new ChangeTagSorter(), taskConfig.getPipeArgs());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\sort\v0_6\ChangeTagSorterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */