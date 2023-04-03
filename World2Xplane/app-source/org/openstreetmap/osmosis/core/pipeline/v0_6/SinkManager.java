/*    */ package org.openstreetmap.osmosis.core.pipeline.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PassiveTaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PipeTasks;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Source;
/*    */ 
/*    */ public class SinkManager extends PassiveTaskManager {
/*    */   private Sink task;
/*    */   
/*    */   public SinkManager(String taskId, Sink task, Map<String, String> pipeArgs) {
/* 35 */     super(taskId, pipeArgs);
/* 37 */     this.task = task;
/*    */   }
/*    */   
/*    */   public void connect(PipeTasks pipeTasks) {
/* 50 */     Source source = (Source)getInputTask(pipeTasks, 0, Source.class);
/* 54 */     source.setSink(this.task);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\v0_6\SinkManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */