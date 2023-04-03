/*    */ package org.java.plugin.standard;
/*    */ 
/*    */ import org.java.plugin.Plugin;
/*    */ import org.java.plugin.PluginClassLoader;
/*    */ import org.java.plugin.PluginLifecycleException;
/*    */ import org.java.plugin.PluginManager;
/*    */ import org.java.plugin.registry.PluginDescriptor;
/*    */ import org.java.plugin.util.ExtendedProperties;
/*    */ 
/*    */ public abstract class PluginLifecycleHandler {
/*    */   private PluginManager manager;
/*    */   
/*    */   protected void init(PluginManager aManager) {
/* 46 */     this.manager = aManager;
/*    */   }
/*    */   
/*    */   protected PluginManager getPluginManager() {
/* 53 */     return this.manager;
/*    */   }
/*    */   
/*    */   protected abstract void configure(ExtendedProperties paramExtendedProperties);
/*    */   
/*    */   protected abstract PluginClassLoader createPluginClassLoader(PluginDescriptor paramPluginDescriptor);
/*    */   
/*    */   protected abstract Plugin createPluginInstance(PluginDescriptor paramPluginDescriptor) throws PluginLifecycleException;
/*    */   
/*    */   protected abstract void beforePluginStart(Plugin paramPlugin) throws PluginLifecycleException;
/*    */   
/*    */   protected abstract void afterPluginStop(Plugin paramPlugin) throws PluginLifecycleException;
/*    */   
/*    */   protected abstract void dispose();
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\standard\PluginLifecycleHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */