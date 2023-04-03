/*    */ package org.openstreetmap.osmosis.core.pipeline.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.ActiveTaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PipeTasks;
/*    */ import org.openstreetmap.osmosis.core.task.common.Task;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSource;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.MultiSinkMultiChangeSinkRunnableSource;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Source;
/*    */ 
/*    */ public class MultiSinkMultiChangeSinkRunnableSourceManager extends ActiveTaskManager {
/*    */   private MultiSinkMultiChangeSinkRunnableSource task;
/*    */   
/*    */   public MultiSinkMultiChangeSinkRunnableSourceManager(String taskId, MultiSinkMultiChangeSinkRunnableSource task, Map<String, String> pipeArgs) {
/* 39 */     super(taskId, pipeArgs);
/* 41 */     this.task = task;
/*    */   }
/*    */   
/*    */   public void connect(PipeTasks pipeTasks) {
/*    */     int i;
/* 53 */     for (i = 0; i < this.task.getSinkCount(); i++) {
/* 58 */       Sink sink = this.task.getSink(i);
/* 61 */       Source source = (Source)getInputTask(pipeTasks, i, Source.class);
/* 64 */       source.setSink(sink);
/*    */     } 
/* 66 */     for (i = 0; i < this.task.getChangeSinkCount(); i++) {
/* 71 */       ChangeSink changeSink = this.task.getChangeSink(i);
/* 74 */       ChangeSource changeSource = (ChangeSource)getInputTask(pipeTasks, i + this.task.getSinkCount(), ChangeSource.class);
/* 81 */       changeSource.setChangeSink(changeSink);
/*    */     } 
/* 85 */     setOutputTask(pipeTasks, (Task)this.task, 0);
/*    */   }
/*    */   
/*    */   protected Runnable getTask() {
/* 94 */     return (Runnable)this.task;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\v0_6\MultiSinkMultiChangeSinkRunnableSourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */