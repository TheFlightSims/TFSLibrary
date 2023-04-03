/*     */ package com.world2xplane;
/*     */ 
/*     */ import ch.qos.logback.classic.LoggerContext;
/*     */ import ch.qos.logback.classic.PatternLayout;
/*     */ import ch.qos.logback.classic.spi.ILoggingEvent;
/*     */ import ch.qos.logback.core.Appender;
/*     */ import ch.qos.logback.core.ConsoleAppender;
/*     */ import ch.qos.logback.core.Context;
/*     */ import ch.qos.logback.core.Layout;
/*     */ import com.world2xplane.GUI.GUI;
/*     */ import com.world2xplane.Parser.OSMProcess;
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import com.world2xplane.XPlane.DSFTile;
/*     */ import java.io.File;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class World2XPlane {
/*     */   private OSMProcess osmProcess;
/*     */   
/*  69 */   private static Logger log = LoggerFactory.getLogger(World2XPlane.class);
/*     */   
/*     */   public void run(String configFilename, String outputFolder, String osmFile, String xplaneFolder, String polyFile, boolean validateFolder, boolean resume, boolean useDatabase, StatusReporter statusReporter) throws Exception {
/*  81 */     run(null, configFilename, outputFolder, osmFile, xplaneFolder, polyFile, validateFolder, resume, useDatabase, statusReporter);
/*     */   }
/*     */   
/*     */   public void run(Integer cores, String configFilename, String outputFolder, String osmFile, String xplaneFolder, String polyFile, boolean validateFolder, boolean resume, boolean useDatabase, StatusReporter statusReporter) throws Exception {
/*  97 */     File configFile = new File(configFilename);
/*  98 */     GeneratorStore generatorStore = new GeneratorStore();
/*  99 */     generatorStore.setOutputFolder(outputFolder);
/* 100 */     generatorStore.setXplaneSceneryFolder(xplaneFolder);
/*     */     try {
/* 102 */       generatorStore.readConfig(configFile);
/* 103 */     } catch (Exception e) {
/* 104 */       if (e instanceof org.xml.sax.SAXException)
/* 105 */         throw new Exception("Invalid config file passed in. Please use the config files provided inside the resources folder"); 
/* 107 */       throw new Exception("Invalid Config", e);
/*     */     } 
/* 110 */     generatorStore.setPolyFile(polyFile);
/* 111 */     if (cores != null)
/* 112 */       generatorStore.setProcessorThreads(cores.intValue()); 
/* 114 */     if (validateFolder)
/* 115 */       generatorStore.validateObjectsExists(); 
/* 118 */     this.osmProcess = new OSMProcess(generatorStore, statusReporter);
/* 119 */     this.osmProcess.process(osmFile, resume, useDatabase, null);
/*     */   }
/*     */   
/*     */   public static void main(String[] arg) throws Exception {
/* 126 */     LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
/* 129 */     lc.reset();
/* 130 */     PatternLayout patternLayout = new PatternLayout();
/* 131 */     patternLayout.setContext((Context)lc);
/* 132 */     patternLayout.setPattern("[%level] [%thread] - %msg%n");
/* 133 */     patternLayout.start();
/* 134 */     ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender();
/* 137 */     appender.setContext((Context)lc);
/* 138 */     appender.setLayout((Layout)patternLayout);
/* 140 */     appender.start();
/* 141 */     lc.getLogger("root").addAppender((Appender)appender);
/* 143 */     lc.start();
/* 145 */     int mb = 1048576;
/* 146 */     Runtime runtime = Runtime.getRuntime();
/* 147 */     long vmMemory = runtime.maxMemory() / mb;
/* 148 */     if (vmMemory < 2048L)
/* 149 */       log.info("You have a maximum of " + vmMemory + "MB assigned to this application. Consider assigning more memory, otherwise you may receive out of memory errors."); 
/* 155 */     if (arg.length < 1) {
/* 156 */       new GUI();
/*     */       return;
/*     */     } 
/* 161 */     if (arg.length < 3) {
/* 162 */       System.out.println("Usage: World2XPlane [config-file path] [input-file] [output-folder] {xplane-folder} {validate} {resume} {useDatabase}");
/* 163 */       System.exit(1);
/*     */     } 
/* 166 */     String configFile = arg[0];
/* 167 */     if (!(new File(configFile)).exists()) {
/* 168 */       log.error("Config File " + configFile + " does not exist.");
/* 169 */       System.exit(1);
/*     */     } 
/* 172 */     String inputFile = arg[1];
/* 173 */     if (!(new File(inputFile)).exists()) {
/* 174 */       log.error("Input File " + inputFile + " does not exist.");
/* 175 */       System.exit(1);
/*     */     } 
/* 178 */     String outputFolder = arg[2];
/* 181 */     String xplaneFolder = null;
/* 183 */     xplaneFolder = arg[3];
/* 184 */     if (arg.length > 3 && !(new File(xplaneFolder)).exists()) {
/* 185 */       log.error("XPlane Folder " + xplaneFolder + " does not exist.");
/* 186 */       System.exit(1);
/*     */     } 
/* 190 */     boolean validate = ArrayUtils.contains((Object[])arg, "validate");
/* 191 */     boolean resume = ArrayUtils.contains((Object[])arg, "resume");
/* 192 */     boolean useDatabase = ArrayUtils.contains((Object[])arg, "useDatabase");
/* 194 */     String polyFile = null;
/* 195 */     for (String item : arg) {
/* 196 */       if (item.startsWith("polyfile="))
/* 197 */         polyFile = item.replaceAll("polyfile=", ""); 
/*     */     } 
/* 201 */     log.info("World2XPlane v0.7.4");
/* 202 */     log.info("Generating " + inputFile + " to " + outputFolder + " using config " + configFile);
/* 204 */     if (!resume && (new File(outputFolder)).exists()) {
/* 205 */       log.error("Output Folder " + outputFolder + " already exists");
/* 206 */       System.exit(1);
/*     */     } 
/* 209 */     FileUtils.forceMkdir(new File(outputFolder));
/* 210 */     if (!(new File(outputFolder)).exists()) {
/* 211 */       log.error("Output Folder " + outputFolder + " could not be created.");
/* 212 */       System.exit(1);
/*     */     } 
/*     */     try {
/* 216 */       World2XPlane world2XPlane = new World2XPlane();
/* 217 */       world2XPlane.run(configFile, outputFolder, inputFile, xplaneFolder, polyFile, validate, resume, useDatabase, null);
/* 218 */       System.exit(0);
/* 219 */     } catch (Exception e) {
/* 220 */       log.error("An error has occurred", e);
/* 221 */       System.exit(1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getProgress() {
/* 227 */     if (this.osmProcess != null)
/* 228 */       return this.osmProcess.getProgress(); 
/* 230 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public String getStatusMessage() {
/* 234 */     if (this.osmProcess != null)
/* 235 */       return this.osmProcess.getStatusMessage(); 
/* 237 */     return "";
/*     */   }
/*     */   
/*     */   public static interface StatusReporter {
/*     */     void onCompleted();
/*     */     
/*     */     void onError();
/*     */     
/*     */     void tileList(Set<DSFTile> param1Set);
/*     */     
/*     */     void tileProcessing(DSFTile param1DSFTile);
/*     */     
/*     */     void tileStatus(DSFTile param1DSFTile, float param1Float, String param1String);
/*     */     
/*     */     void tileComplete(DSFTile param1DSFTile, File param1File);
/*     */     
/*     */     void showMessage(String param1String);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\World2XPlane.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */