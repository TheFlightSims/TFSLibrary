/*    */ package org.apache.commons.configuration.reloading;
/*    */ 
/*    */ import org.apache.commons.configuration.FileConfiguration;
/*    */ 
/*    */ public class InvariantReloadingStrategy implements ReloadingStrategy {
/*    */   public void setConfiguration(FileConfiguration configuration) {}
/*    */   
/*    */   public void init() {}
/*    */   
/*    */   public boolean reloadingRequired() {
/* 41 */     return false;
/*    */   }
/*    */   
/*    */   public void reloadingPerformed() {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\reloading\InvariantReloadingStrategy.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */