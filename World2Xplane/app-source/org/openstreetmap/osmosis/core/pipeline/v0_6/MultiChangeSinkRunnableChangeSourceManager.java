/*    */ package org.openstreetmap.osmosis.core.pipeline.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.ActiveTaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PipeTasks;
/*    */ import org.openstreetmap.osmosis.core.task.common.Task;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSource;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.MultiChangeSinkRunnableChangeSource;
/*    */ 
/*    */ public class MultiChangeSinkRunnableChangeSourceManager extends ActiveTaskManager {
/*    */   private MultiChangeSinkRunnableChangeSource task;
/*    */   
/*    */   public MultiChangeSinkRunnableChangeSourceManager(String taskId, MultiChangeSinkRunnableChangeSource task, Map<String, String> pipeArgs) {
/* 37 */     super(taskId, pipeArgs);
/* 39 */     this.task = task;
/*    */   }
/*    */   
/*    */   public void connect(PipeTasks pipeTasks) {
/* 50 */     for (int i = 0; i < this.task.getChangeSinkCount(); i++) {
/* 55 */       ChangeSink sink = this.task.getChangeSink(i);
/* 58 */       ChangeSource source = (ChangeSource)getInputTask(pipeTasks, i, ChangeSource.class);
/* 61 */       source.setChangeSink(sink);
/*    */     } 
/* 65 */     setOutputTask(pipeTasks, (Task)this.task, 0);
/*    */   }
/*    */   
/*    */   protected Runnable getTask() {
/* 74 */     return (Runnable)this.task;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\v0_6\MultiChangeSinkRunnableChangeSourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */