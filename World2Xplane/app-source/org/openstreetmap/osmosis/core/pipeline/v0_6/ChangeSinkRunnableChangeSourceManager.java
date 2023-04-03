/*    */ package org.openstreetmap.osmosis.core.pipeline.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.ActiveTaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PipeTasks;
/*    */ import org.openstreetmap.osmosis.core.task.common.Task;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSinkRunnableChangeSource;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSource;
/*    */ 
/*    */ public class ChangeSinkRunnableChangeSourceManager extends ActiveTaskManager {
/*    */   private ChangeSinkRunnableChangeSource task;
/*    */   
/*    */   public ChangeSinkRunnableChangeSourceManager(String taskId, ChangeSinkRunnableChangeSource task, Map<String, String> pipeArgs) {
/* 36 */     super(taskId, pipeArgs);
/* 38 */     this.task = task;
/*    */   }
/*    */   
/*    */   public void connect(PipeTasks pipeTasks) {
/* 51 */     ChangeSource source = (ChangeSource)getInputTask(pipeTasks, 0, ChangeSource.class);
/* 54 */     source.setChangeSink((ChangeSink)this.task);
/* 58 */     setOutputTask(pipeTasks, (Task)this.task, 0);
/*    */   }
/*    */   
/*    */   protected Runnable getTask() {
/* 67 */     return (Runnable)this.task;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\v0_6\ChangeSinkRunnableChangeSourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */