/*    */ package org.openstreetmap.osmosis.core.pipeline.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.ActiveTaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PipeTasks;
/*    */ import org.openstreetmap.osmosis.core.task.common.Task;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.MultiSinkRunnableSource;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Source;
/*    */ 
/*    */ public class MultiSinkRunnableSourceManager extends ActiveTaskManager {
/*    */   private MultiSinkRunnableSource task;
/*    */   
/*    */   public MultiSinkRunnableSourceManager(String taskId, MultiSinkRunnableSource task, Map<String, String> pipeArgs) {
/* 36 */     super(taskId, pipeArgs);
/* 38 */     this.task = task;
/*    */   }
/*    */   
/*    */   public void connect(PipeTasks pipeTasks) {
/* 49 */     for (int i = 0; i < this.task.getSinkCount(); i++) {
/* 54 */       Sink sink = this.task.getSink(i);
/* 57 */       Source source = (Source)getInputTask(pipeTasks, i, Source.class);
/* 60 */       source.setSink(sink);
/*    */     } 
/* 64 */     setOutputTask(pipeTasks, (Task)this.task, 0);
/*    */   }
/*    */   
/*    */   protected Runnable getTask() {
/* 73 */     return (Runnable)this.task;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\v0_6\MultiSinkRunnableSourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */