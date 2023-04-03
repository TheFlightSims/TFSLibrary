/*     */ package org.openstreetmap.osmosis.core.pipeline.common;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ 
/*     */ public class Pipeline {
/*  20 */   private static final Logger LOG = Logger.getLogger(Pipeline.class.getName());
/*     */   
/*     */   private TaskManagerFactoryRegister factoryRegister;
/*     */   
/*     */   private List<TaskManager> taskManagers;
/*     */   
/*     */   public Pipeline(TaskManagerFactoryRegister factoryRegister) {
/*  33 */     this.factoryRegister = factoryRegister;
/*  35 */     this.taskManagers = new ArrayList<TaskManager>();
/*     */   }
/*     */   
/*     */   private void buildTasks(List<TaskConfiguration> taskInfoList) {
/*  48 */     for (TaskConfiguration taskConfig : taskInfoList) {
/*  50 */       this.taskManagers.add(this.factoryRegister.getInstance(taskConfig.getType()).createTaskManager(taskConfig));
/*  54 */       if (LOG.isLoggable(Level.FINE))
/*  55 */         LOG.fine("Created task \"" + taskConfig.getId() + "\""); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void connectTasks() {
/*  69 */     PipeTasks pipeTasks = new PipeTasks();
/*  73 */     for (TaskManager taskManager : this.taskManagers) {
/*  74 */       taskManager.connect(pipeTasks);
/*  76 */       if (LOG.isLoggable(Level.FINE))
/*  77 */         LOG.fine("Connected task \"" + taskManager.getTaskId() + "\""); 
/*     */     } 
/*  82 */     if (pipeTasks.size() > 0) {
/*  86 */       StringBuilder namedPipes = new StringBuilder();
/*  87 */       for (String pipeName : pipeTasks.getPipeNames()) {
/*  88 */         if (namedPipes.length() > 0)
/*  89 */           namedPipes.append(", "); 
/*  91 */         namedPipes.append(pipeName);
/*     */       } 
/*  94 */       throw new OsmosisRuntimeException("The following named pipes (" + namedPipes + ") and " + pipeTasks.defaultTaskSize() + " default pipes have not been terminated with appropriate output sinks.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void prepare(List<TaskConfiguration> taskInfoList) {
/* 111 */     LOG.fine("Building tasks.");
/* 112 */     buildTasks(taskInfoList);
/* 115 */     LOG.fine("Connecting tasks.");
/* 116 */     connectTasks();
/*     */   }
/*     */   
/*     */   public void execute() {
/* 125 */     for (TaskManager taskManager : this.taskManagers)
/* 126 */       taskManager.execute(); 
/*     */   }
/*     */   
/*     */   public void waitForCompletion() {
/* 138 */     boolean successful = true;
/* 139 */     for (TaskManager taskManager : this.taskManagers) {
/* 140 */       if (!taskManager.waitForCompletion())
/* 141 */         successful = false; 
/*     */     } 
/* 145 */     if (!successful)
/* 146 */       throw new OsmosisRuntimeException("One or more tasks failed."); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\common\Pipeline.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */