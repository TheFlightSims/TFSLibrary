/*     */ package org.openstreetmap.osmosis.core.pipeline.common;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ import org.openstreetmap.osmosis.core.task.common.Task;
/*     */ 
/*     */ public abstract class TaskManager {
/*     */   private String taskId;
/*     */   
/*     */   private Map<Integer, String> inputPipeNames;
/*     */   
/*     */   private Map<Integer, String> outputPipeNames;
/*     */   
/*     */   protected TaskManager(String taskId, Map<String, String> pipeArgs) {
/*  37 */     this.taskId = taskId;
/*  39 */     this.inputPipeNames = buildPipes(pipeArgs, true);
/*  40 */     this.outputPipeNames = buildPipes(pipeArgs, false);
/*     */   }
/*     */   
/*     */   private int getPipeIndex(String pipeArgNameSuffix) {
/*     */     int pipeIndex;
/*  56 */     if (pipeArgNameSuffix.length() <= 0) {
/*  57 */       pipeIndex = 0;
/*     */     } else {
/*  62 */       if (pipeArgNameSuffix.indexOf('.') != 0)
/*  63 */         throw new OsmosisRuntimeException("Task " + this.taskId + " contains a pipe definition without '.' between prefix and suffix."); 
/*  70 */       String indexString = pipeArgNameSuffix.substring(1);
/*  73 */       if (indexString.length() <= 0)
/*  74 */         throw new OsmosisRuntimeException("Task " + this.taskId + " contains a pipe definition without an index after the '.'."); 
/*     */       try {
/*  82 */         pipeIndex = Integer.parseInt(indexString);
/*  84 */       } catch (NumberFormatException e) {
/*  85 */         throw new OsmosisRuntimeException("Task " + this.taskId + " has a pipe with an incorrect index suffix.");
/*     */       } 
/*     */     } 
/*  89 */     return pipeIndex;
/*     */   }
/*     */   
/*     */   private Map<Integer, String> buildPipes(Map<String, String> pipeArgs, boolean buildInputPipes) {
/*     */     String pipeArgumentPrefix, pipeType;
/* 107 */     Map<Integer, String> pipes = new HashMap<Integer, String>();
/* 111 */     if (buildInputPipes) {
/* 112 */       pipeArgumentPrefix = "inPipe";
/* 113 */       pipeType = "input";
/*     */     } else {
/* 115 */       pipeArgumentPrefix = "outPipe";
/* 116 */       pipeType = "output";
/*     */     } 
/* 120 */     for (String pipeArgName : pipeArgs.keySet()) {
/* 122 */       if (pipeArgName.indexOf(pipeArgumentPrefix) == 0) {
/* 125 */         Integer pipeIndex = new Integer(getPipeIndex(pipeArgName.substring(pipeArgumentPrefix.length())));
/* 130 */         if (pipes.containsKey(pipeIndex))
/* 131 */           throw new OsmosisRuntimeException("Task " + this.taskId + " has a duplicate " + pipeType + " pipe with index " + pipeIndex + "."); 
/* 136 */         pipes.put(pipeIndex, pipeArgs.get(pipeArgName));
/*     */       } 
/*     */     } 
/* 140 */     return pipes;
/*     */   }
/*     */   
/*     */   protected Task getInputTask(PipeTasks pipeTasks, int pipeIndex, Class<? extends Task> requiredTaskType) {
/*     */     Task inputTask;
/* 158 */     Integer pipeIndexO = new Integer(pipeIndex);
/* 162 */     if (this.inputPipeNames.containsKey(pipeIndexO)) {
/* 163 */       inputTask = pipeTasks.retrieveTask(this.taskId, this.inputPipeNames.get(pipeIndexO), requiredTaskType);
/*     */     } else {
/* 165 */       inputTask = pipeTasks.retrieveTask(this.taskId, requiredTaskType);
/*     */     } 
/* 168 */     return inputTask;
/*     */   }
/*     */   
/*     */   protected void setOutputTask(PipeTasks pipeTasks, Task outputTask, int pipeIndex) {
/* 183 */     Integer pipeIndexO = new Integer(pipeIndex);
/* 187 */     if (this.outputPipeNames.containsKey(pipeIndexO)) {
/* 188 */       pipeTasks.putTask(this.taskId, this.outputPipeNames.get(pipeIndexO), outputTask);
/*     */     } else {
/* 190 */       pipeTasks.putTask(this.taskId, outputTask);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String getTaskId() {
/* 199 */     return this.taskId;
/*     */   }
/*     */   
/*     */   public abstract void connect(PipeTasks paramPipeTasks);
/*     */   
/*     */   public abstract void execute();
/*     */   
/*     */   public abstract boolean waitForCompletion();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\common\TaskManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */