/*    */ package org.openstreetmap.osmosis.core.pipeline.common;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*    */ 
/*    */ public abstract class ActiveTaskManager extends TaskManager {
/* 18 */   private static final Logger LOG = Logger.getLogger(ActiveTaskManager.class.getName());
/*    */   
/*    */   private TaskRunner thread;
/*    */   
/*    */   protected ActiveTaskManager(String taskId, Map<String, String> pipeArgs) {
/* 35 */     super(taskId, pipeArgs);
/*    */   }
/*    */   
/*    */   protected abstract Runnable getTask();
/*    */   
/*    */   public void execute() {
/* 52 */     LOG.fine("Launching task " + getTaskId() + " in a new thread.");
/* 54 */     if (this.thread != null)
/* 55 */       throw new OsmosisRuntimeException("Task " + getTaskId() + " is already running."); 
/* 59 */     this.thread = new TaskRunner(getTask(), "Thread-" + getTaskId());
/* 61 */     this.thread.start();
/*    */   }
/*    */   
/*    */   public boolean waitForCompletion() {
/* 70 */     LOG.fine("Waiting for task " + getTaskId() + " to complete.");
/* 71 */     if (this.thread != null) {
/*    */       try {
/* 75 */         this.thread.join();
/* 76 */       } catch (InterruptedException e) {
/* 78 */         LOG.log(Level.WARNING, "The wait for task completion was interrupted.", e);
/*    */       } 
/* 81 */       boolean successful = this.thread.isSuccessful();
/* 83 */       if (!successful)
/* 84 */         LOG.log(Level.SEVERE, "Thread for task " + getTaskId() + " failed", this.thread.getException()); 
/* 87 */       this.thread = null;
/* 89 */       return successful;
/*    */     } 
/* 92 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\common\ActiveTaskManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */