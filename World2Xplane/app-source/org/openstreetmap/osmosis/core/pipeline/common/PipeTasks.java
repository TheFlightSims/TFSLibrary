/*     */ package org.openstreetmap.osmosis.core.pipeline.common;
/*     */ 
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Deque;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ import org.openstreetmap.osmosis.core.task.common.Task;
/*     */ 
/*     */ public class PipeTasks {
/*  23 */   private static final Logger LOG = Logger.getLogger(PipeTasks.class.getName());
/*     */   
/*  33 */   private Map<String, Task> namedTasks = new HashMap<String, Task>();
/*     */   
/*  34 */   private Deque<Task> defaultTasks = new ArrayDeque<Task>();
/*     */   
/*     */   public void putTask(String taskId, String pipeName, Task task) {
/*  50 */     if (this.namedTasks.containsKey(pipeName))
/*  51 */       throw new OsmosisRuntimeException("Task " + taskId + " cannot write to pipe " + pipeName + " because the pipe is already being written to."); 
/*  56 */     this.namedTasks.put(pipeName, task);
/*  58 */     if (LOG.isLoggable(Level.FINE))
/*  59 */       LOG.fine("Task \"" + taskId + "\" produced pipe \"" + pipeName + "\""); 
/*     */   }
/*     */   
/*     */   public void putTask(String taskId, Task task) {
/*  75 */     this.defaultTasks.push(task);
/*  77 */     if (LOG.isLoggable(Level.FINE))
/*  78 */       LOG.fine("Task \"" + taskId + "\" produced unnamed pipe stored at level " + this.defaultTasks.size() + " in the default pipe stack."); 
/*     */   }
/*     */   
/*     */   private boolean verifyPipeType(Class<? extends Task> requiredTaskType, Task task) {
/*  95 */     return requiredTaskType.isInstance(task);
/*     */   }
/*     */   
/*     */   public Task retrieveTask(String taskId, String pipeName, Class<? extends Task> requiredTaskType) {
/* 113 */     if (!this.namedTasks.containsKey(pipeName))
/* 114 */       throw new OsmosisRuntimeException("No pipe named " + pipeName + " is available as input for task " + taskId + "."); 
/* 118 */     Task task = this.namedTasks.remove(pipeName);
/* 121 */     if (!verifyPipeType(requiredTaskType, task))
/* 122 */       throw new OsmosisRuntimeException("Task " + taskId + " does not support data provided by input pipe " + pipeName + "."); 
/* 126 */     if (LOG.isLoggable(Level.FINE))
/* 127 */       LOG.fine("Task \"" + taskId + "\" consumed pipe \"" + pipeName + "\""); 
/* 130 */     return task;
/*     */   }
/*     */   
/*     */   public Task retrieveTask(String taskId, Class<? extends Task> requiredTaskType) {
/* 147 */     int defaultTaskCount = this.defaultTasks.size();
/* 149 */     if (defaultTaskCount == 0)
/* 150 */       throw new OsmosisRuntimeException("No default pipes are available as input for task " + taskId + "."); 
/* 153 */     Task task = this.defaultTasks.pop();
/* 156 */     if (!verifyPipeType(requiredTaskType, task))
/* 157 */       throw new OsmosisRuntimeException("Task " + taskId + " does not support data provided by default pipe stored at level " + (this.defaultTasks.size() + 1) + " in the default pipe stack."); 
/* 162 */     if (LOG.isLoggable(Level.FINE))
/* 163 */       LOG.fine("Task \"" + taskId + "\" consumed unnamed pipe stored at level " + defaultTaskCount + " in the default pipe stack."); 
/* 167 */     return task;
/*     */   }
/*     */   
/*     */   public int size() {
/* 177 */     return this.namedTasks.size() + this.defaultTasks.size();
/*     */   }
/*     */   
/*     */   public int defaultTaskSize() {
/* 188 */     return this.defaultTasks.size();
/*     */   }
/*     */   
/*     */   public Set<String> getPipeNames() {
/* 198 */     return this.namedTasks.keySet();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\common\PipeTasks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */