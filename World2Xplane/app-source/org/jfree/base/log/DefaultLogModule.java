/*     */ package org.jfree.base.log;
/*     */ 
/*     */ import org.jfree.base.modules.AbstractModule;
/*     */ import org.jfree.base.modules.ModuleInitializeException;
/*     */ import org.jfree.base.modules.SubSystem;
/*     */ import org.jfree.util.Log;
/*     */ import org.jfree.util.LogTarget;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PrintStreamLogTarget;
/*     */ 
/*     */ public class DefaultLogModule extends AbstractModule {
/*     */   public DefaultLogModule() throws ModuleInitializeException {
/*  70 */     loadModuleInfo();
/*     */   }
/*     */   
/*     */   public void initialize(SubSystem subSystem) throws ModuleInitializeException {
/*  83 */     if (LogConfiguration.isDisableLogging())
/*     */       return; 
/*  87 */     if (LogConfiguration.getLogTarget().equals(PrintStreamLogTarget.class.getName())) {
/*  90 */       DefaultLog.installDefaultLog();
/*  91 */       Log.getInstance().addTarget((LogTarget)new PrintStreamLogTarget());
/*  92 */       Log.info("Default log target started ... previous log messages could have been ignored.");
/*  94 */     } else if ("true".equals(subSystem.getGlobalConfig().getConfigProperty("org.jfree.base.LogAutoInit"))) {
/*  97 */       DefaultLog.installDefaultLog();
/*  98 */       LogTarget lt = (LogTarget)ObjectUtilities.loadAndInstantiate(LogConfiguration.getLogTarget(), getClass());
/* 100 */       Log.getInstance().addTarget(lt);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\log\DefaultLogModule.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */