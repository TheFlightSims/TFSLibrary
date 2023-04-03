/*     */ package org.openstreetmap.osmosis.core;
/*     */ 
/*     */ import java.util.logging.ConsoleHandler;
/*     */ import java.util.logging.Handler;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.openstreetmap.osmosis.core.cli.CommandLineParser;
/*     */ import org.openstreetmap.osmosis.core.pipeline.common.Pipeline;
/*     */ 
/*     */ public final class Osmosis {
/*  19 */   private static final Logger LOG = Logger.getLogger(Osmosis.class.getName());
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/*  37 */       run(args);
/*  39 */       System.exit(0);
/*  41 */     } catch (Throwable t) {
/*  42 */       LOG.log(Level.SEVERE, "Execution aborted.", t);
/*     */     } 
/*  45 */     System.exit(1);
/*     */   }
/*     */   
/*     */   public static void run(String[] args) {
/*  67 */     long startTime = System.currentTimeMillis();
/*  69 */     configureLoggingConsole();
/*  71 */     CommandLineParser commandLineParser = new CommandLineParser();
/*  74 */     commandLineParser.parse(args);
/*  77 */     configureLoggingLevel(commandLineParser.getLogLevelIndex());
/*  79 */     LOG.info("Osmosis Version 0.43.1");
/*  80 */     TaskRegistrar taskRegistrar = new TaskRegistrar();
/*  81 */     taskRegistrar.initialize(commandLineParser.getPlugins());
/*  83 */     Pipeline pipeline = new Pipeline(taskRegistrar.getFactoryRegister());
/*  85 */     LOG.info("Preparing pipeline.");
/*  86 */     pipeline.prepare(commandLineParser.getTaskInfoList());
/*  88 */     LOG.info("Launching pipeline execution.");
/*  89 */     pipeline.execute();
/*  91 */     LOG.info("Pipeline executing, waiting for completion.");
/*  92 */     pipeline.waitForCompletion();
/*  94 */     LOG.info("Pipeline complete.");
/*  96 */     long finishTime = System.currentTimeMillis();
/*  98 */     LOG.info("Total execution time: " + (finishTime - startTime) + " milliseconds.");
/*     */   }
/*     */   
/*     */   private static void configureLoggingConsole() {
/* 109 */     Logger rootLogger = Logger.getLogger("");
/* 112 */     for (Handler handler : rootLogger.getHandlers())
/* 113 */       rootLogger.removeHandler(handler); 
/* 117 */     Handler consoleHandler = new ConsoleHandler();
/* 118 */     consoleHandler.setLevel(Level.ALL);
/* 119 */     rootLogger.addHandler(consoleHandler);
/*     */   }
/*     */   
/*     */   private static void configureLoggingLevel(int logLevelIndex) {
/* 132 */     Logger rootLogger = Logger.getLogger("");
/* 135 */     rootLogger.setLevel(LogLevels.getLogLevel(logLevelIndex));
/* 138 */     Logger.getLogger("org.springframework").setLevel(LogLevels.getLogLevel(logLevelIndex - 1));
/* 141 */     Logger.getLogger("org.java.plugin").setLevel(Level.WARNING);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\Osmosis.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */