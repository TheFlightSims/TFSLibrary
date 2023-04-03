/*    */ package org.openstreetmap.osmosis.core.pipeline.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PassiveTaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PipeTasks;
/*    */ import org.openstreetmap.osmosis.core.task.common.Task;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.DatasetSink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.DatasetSinkSource;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.DatasetSource;
/*    */ 
/*    */ public class DatasetSinkSourceManager extends PassiveTaskManager {
/*    */   private DatasetSinkSource task;
/*    */   
/*    */   public DatasetSinkSourceManager(String taskId, DatasetSinkSource task, Map<String, String> pipeArgs) {
/* 36 */     super(taskId, pipeArgs);
/* 38 */     this.task = task;
/*    */   }
/*    */   
/*    */   public void connect(PipeTasks pipeTasks) {
/* 51 */     DatasetSource source = (DatasetSource)getInputTask(pipeTasks, 0, DatasetSource.class);
/* 55 */     source.setDatasetSink((DatasetSink)this.task);
/* 59 */     setOutputTask(pipeTasks, (Task)this.task, 0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\v0_6\DatasetSinkSourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */