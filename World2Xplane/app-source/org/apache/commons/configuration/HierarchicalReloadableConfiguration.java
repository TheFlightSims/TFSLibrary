/*    */ package org.apache.commons.configuration;
/*    */ 
/*    */ import org.apache.commons.configuration.reloading.Reloadable;
/*    */ 
/*    */ public class HierarchicalReloadableConfiguration extends HierarchicalConfiguration implements Reloadable {
/*    */   private static final String LOCK_NAME = "HierarchicalReloadableConfigurationLock";
/*    */   
/*    */   private final Object reloadLock;
/*    */   
/*    */   public HierarchicalReloadableConfiguration() {
/* 47 */     this.reloadLock = new Lock("HierarchicalReloadableConfigurationLock");
/*    */   }
/*    */   
/*    */   public HierarchicalReloadableConfiguration(Object lock) {
/* 60 */     this.reloadLock = (lock == null) ? new Lock("HierarchicalReloadableConfigurationLock") : lock;
/*    */   }
/*    */   
/*    */   public HierarchicalReloadableConfiguration(HierarchicalConfiguration c) {
/* 73 */     super(c);
/* 74 */     this.reloadLock = new Lock("HierarchicalReloadableConfigurationLock");
/*    */   }
/*    */   
/*    */   public Object getReloadLock() {
/* 79 */     return this.reloadLock;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\HierarchicalReloadableConfiguration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */