/*     */ package org.openstreetmap.osmosis.core.cli;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ import org.openstreetmap.osmosis.core.pipeline.common.TaskConfiguration;
/*     */ 
/*     */ public class CommandLineParser {
/*     */   private static final String GLOBAL_ARGUMENT_PREFIX = "-";
/*     */   
/*     */   private static final String TASK_ARGUMENT_PREFIX = "--";
/*     */   
/*     */   private static final String OPTION_QUIET_SHORT = "q";
/*     */   
/*     */   private static final String OPTION_QUIET_LONG = "quiet";
/*     */   
/*     */   private static final String OPTION_VERBOSE_SHORT = "v";
/*     */   
/*     */   private static final String OPTION_VERBOSE_LONG = "verbose";
/*     */   
/*     */   private static final String OPTION_PLUGIN_SHORT = "p";
/*     */   
/*     */   private static final String OPTION_PLUGIN_LONG = "plugin";
/*     */   
/*     */   private static final int DEFAULT_LOG_LEVEL_INDEX = 3;
/*     */   
/*  48 */   private List<TaskConfiguration> taskConfigList = new ArrayList<TaskConfiguration>();
/*     */   
/*  50 */   private int quietValue = 0;
/*     */   
/*  51 */   private int verboseValue = 0;
/*     */   
/*  52 */   private List<String> plugins = new ArrayList<String>();
/*     */   
/*     */   public void parse(String[] programArgs) {
/*  66 */     List<GlobalOptionConfiguration> globalOptions = new ArrayList<GlobalOptionConfiguration>();
/*  69 */     for (int i = 0; i < programArgs.length; ) {
/*  72 */       String arg = programArgs[i];
/*  74 */       if (arg.indexOf("--") == 0) {
/*  75 */         i = parseTask(programArgs, i);
/*     */         continue;
/*     */       } 
/*  76 */       if (arg.indexOf("-") == 0) {
/*  77 */         i = parseGlobalOption(globalOptions, programArgs, i);
/*     */         continue;
/*     */       } 
/*  79 */       throw new OsmosisRuntimeException("Expected argument " + (i + 1) + " to be an option or task name.");
/*     */     } 
/*  84 */     for (GlobalOptionConfiguration globalOption : globalOptions) {
/*  85 */       if (isArgumentForOption("q", "quiet", globalOption.name)) {
/*  86 */         this.quietValue = parseOptionIntegerWithDefault(globalOption, 0) + 1;
/*     */         continue;
/*     */       } 
/*  87 */       if (isArgumentForOption("v", "verbose", globalOption.name)) {
/*  88 */         this.verboseValue = parseOptionIntegerWithDefault(globalOption, 0) + 1;
/*     */         continue;
/*     */       } 
/*  89 */       if (isArgumentForOption("p", "plugin", globalOption.name)) {
/*  90 */         this.plugins.add(parseOptionString(globalOption));
/*     */         continue;
/*     */       } 
/*  92 */       throw new OsmosisRuntimeException("Argument " + (globalOption.offset + 1) + " specifies an unrecognised option \"" + "-" + globalOption.name + "\".");
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isArgumentForOption(String shortOptionName, String longOptionName, String argument) {
/* 112 */     return (shortOptionName.equals(argument) || longOptionName.equals(argument));
/*     */   }
/*     */   
/*     */   private boolean isOptionParameter(String argument) {
/* 125 */     if (argument.length() >= "-".length() && 
/* 126 */       argument.substring(0, "-".length()).equals("-"))
/* 127 */       return false; 
/* 131 */     if (argument.length() >= "--".length() && 
/* 132 */       argument.substring(0, "--".length()).equals("--"))
/* 133 */       return false; 
/* 137 */     return true;
/*     */   }
/*     */   
/*     */   private int parseOptionIntegerWithDefault(GlobalOptionConfiguration globalOption, int defaultValue) {
/*     */     int result;
/* 153 */     if (globalOption.parameters.size() <= 0)
/* 154 */       return defaultValue; 
/* 158 */     if (globalOption.parameters.size() > 1)
/* 159 */       throw new OsmosisRuntimeException("Expected argument " + (globalOption.offset + 1) + " to have no more than one parameter."); 
/*     */     try {
/* 165 */       result = Integer.parseInt(globalOption.parameters.get(0));
/* 167 */     } catch (NumberFormatException e) {
/* 168 */       throw new OsmosisRuntimeException("Expected argument " + (globalOption.offset + 2) + " to contain an integer value.");
/*     */     } 
/* 172 */     return result;
/*     */   }
/*     */   
/*     */   private String parseOptionString(GlobalOptionConfiguration globalOption) {
/* 184 */     if (globalOption.parameters.size() != 1)
/* 185 */       throw new OsmosisRuntimeException("Expected argument " + (globalOption.offset + 1) + " to have one parameter."); 
/* 189 */     return globalOption.parameters.get(0);
/*     */   }
/*     */   
/*     */   private int parseGlobalOption(List<GlobalOptionConfiguration> globalOptions, String[] programArgs, int offset) {
/* 210 */     int i = offset;
/* 211 */     String argument = programArgs[i++].substring(1);
/* 213 */     GlobalOptionConfiguration globalOption = new GlobalOptionConfiguration();
/* 214 */     globalOption.name = argument;
/* 215 */     globalOption.offset = offset;
/* 218 */     while (i < programArgs.length && isOptionParameter(programArgs[i]))
/* 219 */       globalOption.parameters.add(programArgs[i++]); 
/* 223 */     globalOptions.add(globalOption);
/* 225 */     return i;
/*     */   }
/*     */   
/*     */   private int parseTask(String[] programArgs, int offset) {
/* 247 */     int i = offset;
/* 250 */     String taskType = programArgs[i++].substring("--".length());
/* 253 */     Map<String, String> taskArgs = new HashMap<String, String>();
/* 254 */     Map<String, String> pipeArgs = new HashMap<String, String>();
/* 255 */     String defaultArg = null;
/* 256 */     int defaultArgIndex = -1;
/* 257 */     while (i < programArgs.length) {
/* 263 */       String arg = programArgs[i];
/* 265 */       if (arg.indexOf("--") == 0)
/*     */         break; 
/* 269 */       int equalsIndex = arg.indexOf("=");
/* 272 */       if (equalsIndex >= 0) {
/* 274 */         if (equalsIndex == 0)
/* 275 */           throw new OsmosisRuntimeException("Argument " + (i + 1) + " doesn't contain a name before the '=' (ie. name=value)."); 
/* 280 */         if (equalsIndex >= arg.length() - 1)
/* 281 */           throw new OsmosisRuntimeException("Argument " + (i + 1) + " doesn't contain a value after the '=' (ie. name=value)."); 
/* 286 */         String argName = arg.substring(0, equalsIndex);
/* 287 */         String argValue = arg.substring(equalsIndex + 1);
/* 291 */         if ("inPipe".equals(argName) || argName.indexOf("inPipe.") == 0 || "outPipe".equals(argName) || argName.indexOf("outPipe.") == 0) {
/* 296 */           pipeArgs.put(argName, argValue);
/*     */         } else {
/* 298 */           taskArgs.put(argName, argValue);
/*     */         } 
/*     */       } else {
/* 302 */         if (defaultArgIndex >= 0)
/* 303 */           throw new OsmosisRuntimeException("Only one default (un-named) argument can exist per task.  Arguments " + (i + 1) + " and " + (defaultArgIndex + 1) + " have no name."); 
/* 308 */         defaultArg = arg;
/* 309 */         defaultArgIndex = i;
/*     */       } 
/* 312 */       i++;
/*     */     } 
/* 316 */     String taskId = (this.taskConfigList.size() + 1) + "-" + taskType;
/* 319 */     this.taskConfigList.add(new TaskConfiguration(taskId, taskType, pipeArgs, taskArgs, defaultArg));
/* 323 */     return i;
/*     */   }
/*     */   
/*     */   public List<TaskConfiguration> getTaskInfoList() {
/* 333 */     return this.taskConfigList;
/*     */   }
/*     */   
/*     */   public int getLogLevelIndex() {
/* 344 */     return 3 + this.verboseValue - this.quietValue;
/*     */   }
/*     */   
/*     */   public List<String> getPlugins() {
/* 354 */     return this.plugins;
/*     */   }
/*     */   
/*     */   private class GlobalOptionConfiguration {
/*     */     public String name;
/*     */     
/* 381 */     public List<String> parameters = new ArrayList<String>();
/*     */     
/*     */     public int offset;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\cli\CommandLineParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */