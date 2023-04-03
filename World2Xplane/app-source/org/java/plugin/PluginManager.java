/*     */ package org.java.plugin;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.Map;
/*     */ import org.java.plugin.registry.Identity;
/*     */ import org.java.plugin.registry.PluginDescriptor;
/*     */ import org.java.plugin.registry.PluginRegistry;
/*     */ 
/*     */ public abstract class PluginManager {
/*     */   public static PluginManager lookup(Object obj) {
/*     */     ClassLoader clsLoader;
/*  62 */     if (obj == null)
/*  63 */       return null; 
/*  66 */     if (obj instanceof Plugin)
/*  67 */       return ((Plugin)obj).getManager(); 
/*  68 */     if (obj instanceof Class) {
/*  69 */       clsLoader = ((Class)obj).getClassLoader();
/*  70 */     } else if (obj instanceof ClassLoader) {
/*  71 */       clsLoader = (ClassLoader)obj;
/*     */     } else {
/*  73 */       clsLoader = obj.getClass().getClassLoader();
/*     */     } 
/*  75 */     if (!(clsLoader instanceof PluginClassLoader))
/*  76 */       return lookup(clsLoader.getParent()); 
/*  78 */     return ((PluginClassLoader)clsLoader).getPluginManager();
/*     */   }
/*     */   
/*     */   public abstract PluginRegistry getRegistry();
/*     */   
/*     */   public abstract PathResolver getPathResolver();
/*     */   
/*     */   public abstract Map<String, Identity> publishPlugins(PluginLocation[] paramArrayOfPluginLocation) throws JpfException;
/*     */   
/*     */   public abstract Plugin getPlugin(String paramString) throws PluginLifecycleException;
/*     */   
/*     */   public abstract void activatePlugin(String paramString) throws PluginLifecycleException;
/*     */   
/*     */   public abstract Plugin getPluginFor(Object paramObject);
/*     */   
/*     */   public abstract boolean isPluginActivated(PluginDescriptor paramPluginDescriptor);
/*     */   
/*     */   public abstract boolean isBadPlugin(PluginDescriptor paramPluginDescriptor);
/*     */   
/*     */   public abstract boolean isPluginActivating(PluginDescriptor paramPluginDescriptor);
/*     */   
/*     */   public abstract PluginClassLoader getPluginClassLoader(PluginDescriptor paramPluginDescriptor);
/*     */   
/*     */   public abstract void shutdown();
/*     */   
/*     */   public abstract void deactivatePlugin(String paramString);
/*     */   
/*     */   public abstract PluginDescriptor[] disablePlugin(PluginDescriptor paramPluginDescriptor);
/*     */   
/*     */   public abstract PluginDescriptor[] enablePlugin(PluginDescriptor paramPluginDescriptor, boolean paramBoolean);
/*     */   
/*     */   public abstract boolean isPluginEnabled(PluginDescriptor paramPluginDescriptor);
/*     */   
/*     */   public abstract void registerListener(EventListener paramEventListener);
/*     */   
/*     */   public abstract void unregisterListener(EventListener paramEventListener);
/*     */   
/*     */   protected final void initPlugin(Plugin plugin, PluginDescriptor descr) {
/* 284 */     plugin.setManager(this);
/* 285 */     plugin.setDescriptor(descr);
/*     */   }
/*     */   
/*     */   protected final void startPlugin(Plugin plugin) throws Exception {
/* 298 */     plugin.start();
/*     */   }
/*     */   
/*     */   protected final void stopPlugin(Plugin plugin) throws Exception {
/* 311 */     plugin.stop();
/*     */   }
/*     */   
/*     */   protected final void disposeClassLoader(PluginClassLoader cl) {
/* 321 */     cl.dispose();
/*     */   }
/*     */   
/*     */   protected final void notifyClassLoader(PluginClassLoader cl) {
/* 331 */     cl.pluginsSetChanged();
/*     */   }
/*     */   
/*     */   public static interface PluginLocation {
/*     */     URL getManifestLocation();
/*     */     
/*     */     URL getContextLocation();
/*     */   }
/*     */   
/*     */   public static abstract class EventListenerAdapter implements EventListener {
/*     */     public void pluginActivated(Plugin plugin) {}
/*     */     
/*     */     public void pluginDeactivated(Plugin plugin) {}
/*     */     
/*     */     public void pluginDisabled(PluginDescriptor descriptor) {}
/*     */     
/*     */     public void pluginEnabled(PluginDescriptor descriptor) {}
/*     */   }
/*     */   
/*     */   public static interface EventListener {
/*     */     void pluginActivated(Plugin param1Plugin);
/*     */     
/*     */     void pluginDeactivated(Plugin param1Plugin);
/*     */     
/*     */     void pluginDisabled(PluginDescriptor param1PluginDescriptor);
/*     */     
/*     */     void pluginEnabled(PluginDescriptor param1PluginDescriptor);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\PluginManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */