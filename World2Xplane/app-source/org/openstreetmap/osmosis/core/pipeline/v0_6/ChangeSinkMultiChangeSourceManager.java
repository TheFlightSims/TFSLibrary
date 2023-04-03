/*    */ package org.openstreetmap.osmosis.core.pipeline.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PassiveTaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PipeTasks;
/*    */ import org.openstreetmap.osmosis.core.task.common.Task;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSinkMultiChangeSource;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSource;
/*    */ 
/*    */ public class ChangeSinkMultiChangeSourceManager extends PassiveTaskManager {
/*    */   private ChangeSinkMultiChangeSource task;
/*    */   
/*    */   public ChangeSinkMultiChangeSourceManager(String taskId, ChangeSinkMultiChangeSource task, Map<String, String> pipeArgs) {
/* 37 */     super(taskId, pipeArgs);
/* 39 */     this.task = task;
/*    */   }
/*    */   
/*    */   public void connect(PipeTasks pipeTasks) {
/* 53 */     ChangeSource source = (ChangeSource)getInputTask(pipeTasks, 0, ChangeSource.class);
/* 57 */     source.setChangeSink((ChangeSink)this.task);
/* 60 */     int taskSourceCount = this.task.getChangeSourceCount();
/* 61 */     for (int i = 0; i < taskSourceCount; i++)
/* 62 */       setOutputTask(pipeTasks, (Task)this.task.getChangeSource(i), i); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\v0_6\ChangeSinkMultiChangeSourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */