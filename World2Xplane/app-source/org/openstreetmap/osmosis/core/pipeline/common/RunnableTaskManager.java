/*    */ package org.openstreetmap.osmosis.core.pipeline.common;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.task.common.RunnableTask;
/*    */ 
/*    */ public class RunnableTaskManager extends ActiveTaskManager {
/*    */   private RunnableTask task;
/*    */   
/*    */   public RunnableTaskManager(String taskId, RunnableTask task, Map<String, String> pipeArgs) {
/* 33 */     super(taskId, pipeArgs);
/* 35 */     this.task = task;
/*    */   }
/*    */   
/*    */   public void connect(PipeTasks pipeTasks) {}
/*    */   
/*    */   protected Runnable getTask() {
/* 53 */     return (Runnable)this.task;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\common\RunnableTaskManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */