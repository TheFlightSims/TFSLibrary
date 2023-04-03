/*     */ package org.java.plugin.standard;
/*     */ 
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.java.plugin.Plugin;
/*     */ import org.java.plugin.PluginClassLoader;
/*     */ import org.java.plugin.PluginLifecycleException;
/*     */ import org.java.plugin.registry.PluginDescriptor;
/*     */ import org.java.plugin.util.ExtendedProperties;
/*     */ 
/*     */ public class StandardPluginLifecycleHandler extends PluginLifecycleHandler {
/*  52 */   private final Log log = LogFactory.getLog(getClass());
/*     */   
/*     */   private boolean probeParentLoaderLast;
/*     */   
/*     */   protected PluginClassLoader createPluginClassLoader(final PluginDescriptor descr) {
/*  65 */     StandardPluginClassLoader result = AccessController.<StandardPluginClassLoader>doPrivileged(new PrivilegedAction<StandardPluginClassLoader>() {
/*     */           public StandardPluginClassLoader run() {
/*  68 */             return new StandardPluginClassLoader(StandardPluginLifecycleHandler.this.getPluginManager(), descr, StandardPluginLifecycleHandler.this.getClass().getClassLoader());
/*     */           }
/*     */         });
/*  73 */     result.setProbeParentLoaderLast(this.probeParentLoaderLast);
/*  74 */     return result;
/*     */   }
/*     */   
/*     */   protected Plugin createPluginInstance(PluginDescriptor descr) throws PluginLifecycleException {
/*     */     Class<?> pluginClass;
/*  87 */     String className = descr.getPluginClassName();
/*     */     try {
/*  90 */       pluginClass = getPluginManager().getPluginClassLoader(descr).loadClass(className);
/*  93 */     } catch (ClassNotFoundException cnfe) {
/*  94 */       throw new PluginLifecycleException("org.java.plugin.standard", "pluginClassNotFound", className, cnfe);
/*     */     } 
/*     */     try {
/*  99 */       return (Plugin)pluginClass.newInstance();
/* 100 */     } catch (InstantiationException ie) {
/* 101 */       throw new PluginLifecycleException("org.java.plugin.standard", "pluginClassInstantiationFailed", descr.getId(), ie);
/* 104 */     } catch (IllegalAccessException iae) {
/* 105 */       throw new PluginLifecycleException("org.java.plugin.standard", "pluginClassInstantiationFailed", descr.getId(), iae);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void beforePluginStart(Plugin plugin) {}
/*     */   
/*     */   protected void afterPluginStop(Plugin plugin) {}
/*     */   
/*     */   protected void dispose() {}
/*     */   
/*     */   public void configure(ExtendedProperties config) {
/* 146 */     this.probeParentLoaderLast = "true".equalsIgnoreCase(config.getProperty("probeParentLoaderLast", "false"));
/* 148 */     this.log.debug("probeParentLoaderLast parameter value is " + this.probeParentLoaderLast);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\standard\StandardPluginLifecycleHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */