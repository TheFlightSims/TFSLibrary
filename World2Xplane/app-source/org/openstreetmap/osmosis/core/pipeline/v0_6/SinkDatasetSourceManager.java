/*    */ package org.openstreetmap.osmosis.core.pipeline.v0_6;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PassiveTaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.PipeTasks;
/*    */ import org.openstreetmap.osmosis.core.task.common.Task;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Sink;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.SinkDatasetSource;
/*    */ import org.openstreetmap.osmosis.core.task.v0_6.Source;
/*    */ 
/*    */ public class SinkDatasetSourceManager extends PassiveTaskManager {
/*    */   private SinkDatasetSource task;
/*    */   
/*    */   public SinkDatasetSourceManager(String taskId, SinkDatasetSource task, Map<String, String> pipeArgs) {
/* 36 */     super(taskId, pipeArgs);
/* 38 */     this.task = task;
/*    */   }
/*    */   
/*    */   public void connect(PipeTasks pipeTasks) {
/* 51 */     Source source = (Source)getInputTask(pipeTasks, 0, Source.class);
/* 55 */     source.setSink((Sink)this.task);
/* 59 */     setOutputTask(pipeTasks, (Task)this.task, 0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\v0_6\SinkDatasetSourceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */