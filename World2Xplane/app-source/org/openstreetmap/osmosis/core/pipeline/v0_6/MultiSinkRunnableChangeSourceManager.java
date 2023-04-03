/*    */ package org.openstreetmap.osmosis.core.pipeline.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.ActiveTaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PipeTasks;
/*    */ import org.openstreetmap.osmosis.core.task.common.Task;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.MultiSinkRunnableChangeSource;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Source;
/*    */ 
/*    */ public class MultiSinkRunnableChangeSourceManager extends ActiveTaskManager {
/*    */   private MultiSinkRunnableChangeSource task;
/*    */   
/*    */   public MultiSinkRunnableChangeSourceManager(String taskId, MultiSinkRunnableChangeSource task, Map<String, String> pipeArgs) {
/* 37 */     super(taskId, pipeArgs);
/* 39 */     this.task = task;
/*    */   }
/*    */   
/*    */   public void connect(PipeTasks pipeTasks) {
/* 50 */     for (int i = 0; i < this.task.getSinkCount(); i++) {
/* 55 */       Sink sink = this.task.getSink(i);
/* 58 */       Source source = (Source)getInputTask(pipeTasks, i, Source.class);
/* 61 */       source.setSink(sink);
/*    */     } 
/* 65 */     setOutputTask(pipeTasks, (Task)this.task, 0);
/*    */   }
/*    */   
/*    */   protected Runnable getTask() {
/* 74 */     return (Runnable)this.task;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\v0_6\MultiSinkRunnableChangeSourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */