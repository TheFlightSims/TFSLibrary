/*     */ package org.jfree.base;
/*     */ 
/*     */ import org.jfree.JCommon;
/*     */ import org.jfree.base.config.ModifiableConfiguration;
/*     */ import org.jfree.base.log.DefaultLogModule;
/*     */ import org.jfree.util.Configuration;
/*     */ import org.jfree.util.Log;
/*     */ 
/*     */ public class BaseBoot extends AbstractBoot {
/*     */   private static BaseBoot singleton;
/*     */   
/*  73 */   private BootableProjectInfo bootableProjectInfo = (BootableProjectInfo)JCommon.INFO;
/*     */   
/*     */   public static ModifiableConfiguration getConfiguration() {
/*  82 */     return (ModifiableConfiguration)getInstance().getGlobalConfig();
/*     */   }
/*     */   
/*     */   protected synchronized Configuration loadConfiguration() {
/*  98 */     return createDefaultHierarchicalConfiguration("/org/jfree/base/jcommon.properties", "/jcommon.properties", true);
/*     */   }
/*     */   
/*     */   public static synchronized AbstractBoot getInstance() {
/* 109 */     if (singleton == null)
/* 110 */       singleton = new BaseBoot(); 
/* 112 */     return singleton;
/*     */   }
/*     */   
/*     */   protected void performBoot() {
/* 119 */     getPackageManager().addModule(DefaultLogModule.class.getName());
/* 120 */     getPackageManager().load("org.jfree.base.");
/* 121 */     getPackageManager().initializeModules();
/*     */   }
/*     */   
/*     */   protected BootableProjectInfo getProjectInfo() {
/* 130 */     return this.bootableProjectInfo;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 140 */     getInstance().start();
/* 141 */     Log.debug("Hello world");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\BaseBoot.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */