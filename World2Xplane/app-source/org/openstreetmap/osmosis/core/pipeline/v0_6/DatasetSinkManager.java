/*    */ package org.openstreetmap.osmosis.core.pipeline.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PassiveTaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PipeTasks;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.DatasetSink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.DatasetSource;
/*    */ 
/*    */ public class DatasetSinkManager extends PassiveTaskManager {
/*    */   private DatasetSink task;
/*    */   
/*    */   public DatasetSinkManager(String taskId, DatasetSink task, Map<String, String> pipeArgs) {
/* 35 */     super(taskId, pipeArgs);
/* 37 */     this.task = task;
/*    */   }
/*    */   
/*    */   public void connect(PipeTasks pipeTasks) {
/* 50 */     DatasetSource source = (DatasetSource)getInputTask(pipeTasks, 0, DatasetSource.class);
/* 53 */     source.setDatasetSink(this.task);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\v0_6\DatasetSinkManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */