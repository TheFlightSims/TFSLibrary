/*    */ package org.openstreetmap.osmosis.core.report.v0_6;
/*    */ 
/*    */ import java.io.File;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskConfiguration;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManager;
/*    */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;
/*    */ import org.openstreetmap.osmosis.core.pipeline.v0_6.SinkManager;
/*    */ 
/*    */ public class IntegrityReporterFactory extends TaskManagerFactory {
/*    */   private static final String ARG_FILE_NAME = "file";
/*    */   
/*    */   private static final String DEFAULT_FILE_NAME = "integrity-report.txt";
/*    */   
/*    */   protected TaskManager createTaskManagerImpl(TaskConfiguration taskConfig) {
/* 32 */     String fileName = getStringArgument(taskConfig, "file", getDefaultStringArgument(taskConfig, "integrity-report.txt"));
/* 39 */     File file = new File(fileName);
/* 42 */     IntegrityReporter task = new IntegrityReporter(file);
/* 44 */     return (TaskManager)new SinkManager(taskConfig.getId(), task, taskConfig.getPipeArgs());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\report\v0_6\IntegrityReporterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */