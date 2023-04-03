/*     */ package org.openstreetmap.osmosis.core.pipeline.common;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TimeZone;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ 
/*     */ public abstract class TaskManagerFactory {
/*     */   private static final String DATE_FORMAT = "yyyy-MM-dd_HH:mm:ss";
/*     */   
/*  27 */   private static final Locale DATE_LOCALE = Locale.US;
/*     */   
/*  42 */   private ThreadLocal<Set<String>> accessedTaskOptions = new ThreadLocal<Set<String>>();
/*     */   
/*     */   public TaskManager createTaskManager(TaskConfiguration taskConfig) {
/*  58 */     this.accessedTaskOptions.set(new HashSet<String>());
/*  60 */     TaskManager taskManager = createTaskManagerImpl(taskConfig);
/*  62 */     for (String argName : taskConfig.getConfigArgs().keySet()) {
/*  63 */       if (!((Set)this.accessedTaskOptions.get()).contains(argName))
/*  64 */         throw new OsmosisRuntimeException("Argument " + argName + " for task " + taskConfig.getId() + " was not recognised."); 
/*     */     } 
/*  70 */     this.accessedTaskOptions.set(null);
/*  72 */     return taskManager;
/*     */   }
/*     */   
/*     */   protected abstract TaskManager createTaskManagerImpl(TaskConfiguration paramTaskConfiguration);
/*     */   
/*     */   protected boolean doesArgumentExist(TaskConfiguration taskConfig, String argName) {
/*  98 */     return taskConfig.getConfigArgs().containsKey(argName);
/*     */   }
/*     */   
/*     */   protected String getDefaultStringArgument(TaskConfiguration taskConfig, String defaultValue) {
/* 113 */     if (taskConfig.getDefaultArg() != null)
/* 114 */       return taskConfig.getDefaultArg(); 
/* 116 */     return defaultValue;
/*     */   }
/*     */   
/*     */   protected String getStringArgument(TaskConfiguration taskConfig, String argName) {
/* 135 */     ((Set<String>)this.accessedTaskOptions.get()).add(argName);
/* 137 */     Map<String, String> configArgs = taskConfig.getConfigArgs();
/* 139 */     if (configArgs.containsKey(argName))
/* 140 */       return configArgs.get(argName); 
/* 142 */     throw new OsmosisRuntimeException("Argument " + argName + " for task " + taskConfig.getId() + " does not exist.");
/*     */   }
/*     */   
/*     */   protected String getStringArgument(TaskConfiguration taskConfig, String argName, String defaultValue) {
/* 165 */     ((Set<String>)this.accessedTaskOptions.get()).add(argName);
/* 167 */     Map<String, String> configArgs = taskConfig.getConfigArgs();
/* 169 */     if (configArgs.containsKey(argName))
/* 170 */       return configArgs.get(argName); 
/* 172 */     return defaultValue;
/*     */   }
/*     */   
/*     */   protected int getDefaultIntegerArgument(TaskConfiguration taskConfig, int defaultValue) {
/* 190 */     String defaultArg = taskConfig.getDefaultArg();
/* 192 */     if (defaultArg != null)
/*     */       try {
/* 194 */         return Integer.parseInt(defaultArg);
/* 195 */       } catch (NumberFormatException e) {
/* 196 */         throw new OsmosisRuntimeException("Default argument for task " + taskConfig.getId() + " must be an integer number.", e);
/*     */       }  
/* 201 */     return defaultValue;
/*     */   }
/*     */   
/*     */   protected int getIntegerArgument(TaskConfiguration taskConfig, String argName) {
/* 220 */     ((Set<String>)this.accessedTaskOptions.get()).add(argName);
/* 222 */     Map<String, String> configArgs = taskConfig.getConfigArgs();
/* 224 */     if (configArgs.containsKey(argName))
/*     */       try {
/* 226 */         return Integer.parseInt(configArgs.get(argName));
/* 227 */       } catch (NumberFormatException e) {
/* 228 */         throw new OsmosisRuntimeException("Argument " + argName + " for task " + taskConfig.getId() + " must be an integer number.", e);
/*     */       }  
/* 233 */     throw new OsmosisRuntimeException("Argument " + argName + " for task " + taskConfig.getId() + " does not exist.");
/*     */   }
/*     */   
/*     */   protected int getIntegerArgument(TaskConfiguration taskConfig, String argName, int defaultValue) {
/* 256 */     ((Set<String>)this.accessedTaskOptions.get()).add(argName);
/* 258 */     Map<String, String> configArgs = taskConfig.getConfigArgs();
/* 260 */     if (configArgs.containsKey(argName))
/*     */       try {
/* 262 */         return Integer.parseInt(configArgs.get(argName));
/* 263 */       } catch (NumberFormatException e) {
/* 264 */         throw new OsmosisRuntimeException("Argument " + argName + " for task " + taskConfig.getId() + " must be an integer number.", e);
/*     */       }  
/* 269 */     return defaultValue;
/*     */   }
/*     */   
/*     */   protected double getDoubleArgument(TaskConfiguration taskConfig, String argName, double defaultValue) {
/* 291 */     ((Set<String>)this.accessedTaskOptions.get()).add(argName);
/* 293 */     Map<String, String> configArgs = taskConfig.getConfigArgs();
/* 295 */     if (configArgs.containsKey(argName))
/*     */       try {
/* 297 */         return Double.parseDouble(configArgs.get(argName));
/* 298 */       } catch (NumberFormatException e) {
/* 299 */         throw new OsmosisRuntimeException("Argument " + argName + " for task " + taskConfig.getId() + " must be a decimal number.", e);
/*     */       }  
/* 304 */     return defaultValue;
/*     */   }
/*     */   
/*     */   protected Date getDateArgument(TaskConfiguration taskConfig, String argName, Date defaultValue) {
/* 326 */     ((Set<String>)this.accessedTaskOptions.get()).add(argName);
/* 328 */     Map<String, String> configArgs = taskConfig.getConfigArgs();
/* 330 */     if (configArgs.containsKey(argName))
/*     */       try {
/* 334 */         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", DATE_LOCALE);
/* 336 */         return dateFormat.parse(configArgs.get(argName));
/* 338 */       } catch (ParseException e) {
/* 339 */         throw new OsmosisRuntimeException("Argument " + argName + " for task " + taskConfig.getId() + " must be a date in format " + "yyyy-MM-dd_HH:mm:ss" + ".", e);
/*     */       }  
/* 344 */     return defaultValue;
/*     */   }
/*     */   
/*     */   protected Date getDateArgument(TaskConfiguration taskConfig, String argName, TimeZone timeZone) {
/* 366 */     ((Set<String>)this.accessedTaskOptions.get()).add(argName);
/* 368 */     Map<String, String> configArgs = taskConfig.getConfigArgs();
/* 370 */     if (configArgs.containsKey(argName))
/*     */       try {
/* 374 */         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", DATE_LOCALE);
/* 375 */         dateFormat.setTimeZone(timeZone);
/* 377 */         return dateFormat.parse(configArgs.get(argName));
/* 379 */       } catch (ParseException e) {
/* 380 */         throw new OsmosisRuntimeException("Argument " + argName + " for task " + taskConfig.getId() + " must be a date in format " + "yyyy-MM-dd_HH:mm:ss" + ".", e);
/*     */       }  
/* 385 */     throw new OsmosisRuntimeException("Argument " + argName + " for task " + taskConfig.getId() + " does not exist.");
/*     */   }
/*     */   
/*     */   protected boolean getBooleanArgument(TaskConfiguration taskConfig, String argName, boolean defaultValue) {
/* 408 */     ((Set<String>)this.accessedTaskOptions.get()).add(argName);
/* 410 */     Map<String, String> configArgs = taskConfig.getConfigArgs();
/* 412 */     if (configArgs.containsKey(argName)) {
/* 415 */       String rawValue = ((String)configArgs.get(argName)).toLowerCase();
/* 417 */       if ("true".equals(rawValue) || "yes".equals(rawValue))
/* 418 */         return true; 
/* 420 */       if ("false".equals(rawValue) || "no".equals(rawValue))
/* 421 */         return false; 
/* 424 */       throw new OsmosisRuntimeException("Argument " + argName + " for task " + taskConfig.getId() + " must be one of yes, no, true or false.");
/*     */     } 
/* 430 */     return defaultValue;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\pipeline\common\TaskManagerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */