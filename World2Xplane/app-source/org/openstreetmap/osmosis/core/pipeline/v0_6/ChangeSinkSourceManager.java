/*    */ package org.openstreetmap.osmosis.core.pipeline.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PassiveTaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PipeTasks;
/*    */ import org.openstreetmap.osmosis.core.task.common.Task;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSinkSource;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSource;
/*    */ 
/*    */ public class ChangeSinkSourceManager extends PassiveTaskManager {
/*    */   private ChangeSinkSource task;
/*    */   
/*    */   public ChangeSinkSourceManager(String taskId, ChangeSinkSource task, Map<String, String> pipeArgs) {
/* 36 */     super(taskId, pipeArgs);
/* 38 */     this.task = task;
/*    */   }
/*    */   
/*    */   public void connect(PipeTasks pipeTasks) {
/* 50 */     ChangeSource source = (ChangeSource)getInputTask(pipeTasks, 0, ChangeSource.class);
/* 54 */     source.setChangeSink((ChangeSink)this.task);
/* 58 */     setOutputTask(pipeTasks, (Task)this.task, 0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\v0_6\ChangeSinkSourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */