/*    */ package org.openstreetmap.osmosis.core.pipeline.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PassiveTaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PipeTasks;
/*    */ import org.openstreetmap.osmosis.core.task.common.Task;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.SinkMultiSource;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Source;
/*    */ 
/*    */ public class SinkMultiSourceManager extends PassiveTaskManager {
/*    */   private SinkMultiSource task;
/*    */   
/*    */   public SinkMultiSourceManager(String taskId, SinkMultiSource task, Map<String, String> pipeArgs) {
/* 36 */     super(taskId, pipeArgs);
/* 38 */     this.task = task;
/*    */   }
/*    */   
/*    */   public void connect(PipeTasks pipeTasks) {
/* 52 */     Source source = (Source)getInputTask(pipeTasks, 0, Source.class);
/* 56 */     source.setSink((Sink)this.task);
/* 59 */     int taskSourceCount = this.task.getSourceCount();
/* 60 */     for (int i = 0; i < taskSourceCount; i++)
/* 61 */       setOutputTask(pipeTasks, (Task)this.task.getSource(i), i); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\v0_6\SinkMultiSourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */