/*    */ package org.openstreetmap.osmosis.core.pipeline.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PassiveTaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PipeTasks;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.ChangeSource;
/*    */ 
/*    */ public class ChangeSinkManager extends PassiveTaskManager {
/*    */   private ChangeSink task;
/*    */   
/*    */   public ChangeSinkManager(String taskId, ChangeSink task, Map<String, String> pipeArgs) {
/* 35 */     super(taskId, pipeArgs);
/* 37 */     this.task = task;
/*    */   }
/*    */   
/*    */   public void connect(PipeTasks pipeTasks) {
/* 50 */     ChangeSource source = (ChangeSource)getInputTask(pipeTasks, 0, ChangeSource.class);
/* 54 */     source.setChangeSink(this.task);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\v0_6\ChangeSinkManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */