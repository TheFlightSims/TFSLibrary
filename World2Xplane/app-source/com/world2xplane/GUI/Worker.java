/*     */ package com.world2xplane.GUI;
/*     */ 
/*     */ import com.world2xplane.Rules.GeneratorStore;
/*     */ import com.world2xplane.World2XPlane;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class Worker extends Thread {
/*  38 */   private static Logger log = LoggerFactory.getLogger(Worker.class);
/*     */   
/*     */   private final String xplanePath;
/*     */   
/*     */   private final boolean validate;
/*     */   
/*     */   private final boolean resume;
/*     */   
/*     */   private final boolean useDatabase;
/*     */   
/*     */   private final String configPath;
/*     */   
/*     */   private final String polyFile;
/*     */   
/*     */   private final Integer cores;
/*     */   
/*     */   private String osmFilePath;
/*     */   
/*     */   private World2XPlane world2XPlane;
/*     */   
/*     */   private World2XPlane.StatusReporter workerAction;
/*     */   
/*     */   public Worker(Integer cores, String configPath, String osmfilePath, String xplanePath, String polyFile, boolean validate, boolean resume, boolean useDatabase, World2XPlane.StatusReporter workerAction) {
/*  63 */     this.osmFilePath = osmfilePath;
/*  64 */     this.xplanePath = xplanePath;
/*  65 */     this.validate = validate;
/*  66 */     this.polyFile = polyFile;
/*  67 */     this.workerAction = workerAction;
/*  68 */     this.resume = resume;
/*  69 */     this.useDatabase = useDatabase;
/*  70 */     this.configPath = configPath;
/*  71 */     this.cores = cores;
/*     */   }
/*     */   
/*     */   public void run() {
/*  76 */     this.world2XPlane = new World2XPlane();
/*  77 */     String outputFolder = GeneratorStore.createOutputFolder(this.osmFilePath);
/*     */     try {
/*  79 */       this.world2XPlane.run(this.cores, this.configPath, outputFolder, this.osmFilePath, this.xplanePath, this.polyFile, this.validate, this.resume, this.useDatabase, this.workerAction);
/*  85 */       this.workerAction.onCompleted();
/*  86 */     } catch (Exception e) {
/*  87 */       this.workerAction.onError();
/*  88 */       e.printStackTrace();
/*  89 */     } catch (OutOfMemoryError e) {
/*  90 */       if (this.useDatabase) {
/*  91 */         log.error("Your computer did not have enough memory allocated to generate this scenery.Please restart the application and select Use Local Storage.");
/*     */       } else {
/*  94 */         log.error("Your computer did not have enough memory allocated to generate this scenery.Please either increase the scenery, or report a bug at www.world2xplane.com.");
/*     */       } 
/*     */     } finally {}
/*     */   }
/*     */   
/*     */   public float getProgress() {
/* 104 */     if (this.world2XPlane != null)
/* 105 */       return this.world2XPlane.getProgress(); 
/* 107 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public String getStatusMessage() {
/* 112 */     if (this.world2XPlane != null)
/* 113 */       return this.world2XPlane.getStatusMessage(); 
/* 115 */     return "";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\GUI\Worker.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */