/*    */ package org.apache.commons.configuration.reloading;
/*    */ 
/*    */ import org.apache.commons.configuration.FileConfiguration;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class ManagedReloadingStrategy implements ReloadingStrategy, ManagedReloadingStrategyMBean {
/* 34 */   private Log log = LogFactory.getLog(ManagedReloadingStrategy.class);
/*    */   
/*    */   private FileConfiguration configuration;
/*    */   
/*    */   private boolean reloadingRequired;
/*    */   
/*    */   public void init() {}
/*    */   
/*    */   public void reloadingPerformed() {
/* 54 */     this.reloadingRequired = false;
/*    */   }
/*    */   
/*    */   public boolean reloadingRequired() {
/* 66 */     return this.reloadingRequired;
/*    */   }
/*    */   
/*    */   public void setConfiguration(FileConfiguration configuration) {
/* 76 */     this.configuration = configuration;
/*    */   }
/*    */   
/*    */   public void refresh() {
/* 88 */     this.log.info("Reloading configuration.");
/* 89 */     this.reloadingRequired = true;
/* 91 */     this.configuration.isEmpty();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\reloading\ManagedReloadingStrategy.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */