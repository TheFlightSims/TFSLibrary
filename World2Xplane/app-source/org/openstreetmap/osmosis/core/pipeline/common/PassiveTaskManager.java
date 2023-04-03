/*    */ package org.openstreetmap.osmosis.core.pipeline.common;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ public abstract class PassiveTaskManager extends TaskManager {
/* 15 */   private static final Logger LOG = Logger.getLogger(PassiveTaskManager.class.getName());
/*    */   
/*    */   protected PassiveTaskManager(String taskId, Map<String, String> pipeArgs) {
/* 30 */     super(taskId, pipeArgs);
/*    */   }
/*    */   
/*    */   public void execute() {
/* 40 */     LOG.fine("Task " + getTaskId() + " is passive, no execution required.");
/*    */   }
/*    */   
/*    */   public boolean waitForCompletion() {
/* 50 */     LOG.fine("Task " + getTaskId() + " is passive, no completion wait required.");
/* 52 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\common\PassiveTaskManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */