/*    */ package org.openstreetmap.osmosis.core.pipeline.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.ActiveTaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PipeTasks;
/*    */ import org.openstreetmap.osmosis.core.task.common.Task;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.RunnableSource;
/*    */ 
/*    */ public class RunnableSourceManager extends ActiveTaskManager {
/*    */   private RunnableSource task;
/*    */   
/*    */   public RunnableSourceManager(String taskId, RunnableSource task, Map<String, String> pipeArgs) {
/* 35 */     super(taskId, pipeArgs);
/* 37 */     this.task = task;
/*    */   }
/*    */   
/*    */   public void connect(PipeTasks pipeTasks) {
/* 48 */     setOutputTask(pipeTasks, (Task)this.task, 0);
/*    */   }
/*    */   
/*    */   protected Runnable getTask() {
/* 57 */     return (Runnable)this.task;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\v0_6\RunnableSourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */