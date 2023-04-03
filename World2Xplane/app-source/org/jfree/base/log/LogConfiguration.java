/*     */ package org.jfree.base.log;
/*     */ 
/*     */ import org.jfree.base.BaseBoot;
/*     */ import org.jfree.util.PrintStreamLogTarget;
/*     */ 
/*     */ public class LogConfiguration {
/*     */   public static final String DISABLE_LOGGING_DEFAULT = "false";
/*     */   
/*     */   public static final String LOGLEVEL = "org.jfree.base.LogLevel";
/*     */   
/*     */   public static final String LOGLEVEL_DEFAULT = "Info";
/*     */   
/*     */   public static final String LOGTARGET = "org.jfree.base.LogTarget";
/*     */   
/*  69 */   public static final String LOGTARGET_DEFAULT = PrintStreamLogTarget.class.getName();
/*     */   
/*     */   public static final String DISABLE_LOGGING = "org.jfree.base.NoDefaultDebug";
/*     */   
/*     */   public static String getLogTarget() {
/*  90 */     return BaseBoot.getInstance().getGlobalConfig().getConfigProperty("org.jfree.base.LogTarget", LOGTARGET_DEFAULT);
/*     */   }
/*     */   
/*     */   public static void setLogTarget(String logTarget) {
/* 101 */     BaseBoot.getConfiguration().setConfigProperty("org.jfree.base.LogTarget", logTarget);
/*     */   }
/*     */   
/*     */   public static String getLogLevel() {
/* 111 */     return BaseBoot.getInstance().getGlobalConfig().getConfigProperty("org.jfree.base.LogLevel", "Info");
/*     */   }
/*     */   
/*     */   public static void setLogLevel(String level) {
/* 140 */     BaseBoot.getConfiguration().setConfigProperty("org.jfree.base.LogLevel", level);
/*     */   }
/*     */   
/*     */   public static boolean isDisableLogging() {
/* 150 */     return BaseBoot.getInstance().getGlobalConfig().getConfigProperty("org.jfree.base.NoDefaultDebug", "false").equalsIgnoreCase("true");
/*     */   }
/*     */   
/*     */   public static void setDisableLogging(boolean disableLogging) {
/* 165 */     BaseBoot.getConfiguration().setConfigProperty("org.jfree.base.NoDefaultDebug", String.valueOf(disableLogging));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\base\log\LogConfiguration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */