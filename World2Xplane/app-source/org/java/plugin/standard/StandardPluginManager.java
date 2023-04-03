/*     */ package org.java.plugin.standard;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.java.plugin.JpfException;
/*     */ import org.java.plugin.PathResolver;
/*     */ import org.java.plugin.Plugin;
/*     */ import org.java.plugin.PluginClassLoader;
/*     */ import org.java.plugin.PluginLifecycleException;
/*     */ import org.java.plugin.PluginManager;
/*     */ import org.java.plugin.registry.Identity;
/*     */ import org.java.plugin.registry.PluginDescriptor;
/*     */ import org.java.plugin.registry.PluginFragment;
/*     */ import org.java.plugin.registry.PluginPrerequisite;
/*     */ import org.java.plugin.registry.PluginRegistry;
/*     */ 
/*     */ public final class StandardPluginManager extends PluginManager {
/*  53 */   Log log = LogFactory.getLog(getClass());
/*     */   
/*     */   private final PathResolver pathResolver;
/*     */   
/*     */   private final PluginRegistry registry;
/*     */   
/*     */   private final PluginLifecycleHandler lifecycleHandler;
/*     */   
/*  57 */   private final Map<String, Plugin> activePlugins = new HashMap<String, Plugin>();
/*     */   
/*  59 */   private final Set<String> activatingPlugins = new HashSet<String>();
/*     */   
/*  60 */   private final Set<String> badPlugins = new HashSet<String>();
/*     */   
/*  61 */   private final List<String> activationLog = new LinkedList<String>();
/*     */   
/*  62 */   private final Map<String, PluginClassLoader> classLoaders = new HashMap<String, PluginClassLoader>();
/*     */   
/*  64 */   private final Set<String> disabledPlugins = new HashSet<String>();
/*     */   
/*  65 */   private final List<PluginManager.EventListener> listeners = Collections.synchronizedList(new LinkedList<PluginManager.EventListener>());
/*     */   
/*     */   private PluginRegistry.RegistryChangeListener registryChangeListener;
/*     */   
/*  68 */   private Map<String, URL> notRegisteredPluginLocations = new HashMap<String, URL>();
/*     */   
/*     */   protected StandardPluginManager(PluginRegistry aRegistry, PathResolver aPathResolver, PluginLifecycleHandler aLifecycleHandler) {
/*  87 */     this.registry = aRegistry;
/*  88 */     this.pathResolver = aPathResolver;
/*  89 */     this.lifecycleHandler = aLifecycleHandler;
/*  90 */     this.lifecycleHandler.init(this);
/*  91 */     this.registryChangeListener = new PluginRegistry.RegistryChangeListener() {
/*     */         public void registryChanged(PluginRegistry.RegistryChangeData data) {
/*  93 */           StandardPluginManager.this.registryChangeHandler(data);
/*     */         }
/*     */       };
/*  96 */     this.registry.registerListener(this.registryChangeListener);
/*     */   }
/*     */   
/*     */   public PluginRegistry getRegistry() {
/* 104 */     return this.registry;
/*     */   }
/*     */   
/*     */   public PathResolver getPathResolver() {
/* 112 */     return this.pathResolver;
/*     */   }
/*     */   
/*     */   synchronized void registryChangeHandler(PluginRegistry.RegistryChangeData data) {
/* 122 */     this.badPlugins.clear();
/* 123 */     for (String id : data.removedPlugins()) {
/* 124 */       deactivatePlugin(id);
/* 125 */       this.pathResolver.unregisterContext(id);
/*     */     } 
/* 128 */     for (PluginDescriptor idt : this.registry.getPluginDescriptors()) {
/* 129 */       URL location = this.notRegisteredPluginLocations.remove(idt.getLocation().toExternalForm());
/* 131 */       if (location != null)
/* 132 */         this.pathResolver.registerContext((Identity)idt, location); 
/*     */     } 
/* 135 */     for (PluginFragment idt : this.registry.getPluginFragments()) {
/* 136 */       URL location = this.notRegisteredPluginLocations.remove(idt.getLocation().toExternalForm());
/* 138 */       if (location != null)
/* 139 */         this.pathResolver.registerContext((Identity)idt, location); 
/*     */     } 
/* 142 */     for (String id : data.modifiedPlugins()) {
/* 143 */       if (this.activePlugins.containsKey(id)) {
/* 144 */         deactivatePlugin(id);
/*     */         try {
/* 146 */           activatePlugin(id);
/* 147 */         } catch (Exception e) {
/* 148 */           this.log.error("failed activating modified plug-in " + id, e);
/*     */         } 
/*     */         continue;
/*     */       } 
/* 151 */       PluginClassLoader clsLoader = this.classLoaders.get(id);
/* 152 */       if (clsLoader != null)
/* 153 */         notifyClassLoader(clsLoader); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Map<String, Identity> publishPlugins(PluginManager.PluginLocation[] locations) throws JpfException {
/* 179 */     LinkedList<URL> manifests = new LinkedList<URL>();
/* 180 */     for (PluginManager.PluginLocation location : locations) {
/* 181 */       URL manifest = location.getManifestLocation();
/* 182 */       manifests.add(manifest);
/* 183 */       this.notRegisteredPluginLocations.put(manifest.toExternalForm(), location.getContextLocation());
/*     */     } 
/* 186 */     return this.registry.register(manifests.<URL>toArray(new URL[manifests.size()]));
/*     */   }
/*     */   
/*     */   public Plugin getPlugin(String id) throws PluginLifecycleException {
/* 201 */     Plugin result = this.activePlugins.get(id);
/* 202 */     if (result != null)
/* 203 */       return result; 
/* 205 */     if (this.badPlugins.contains(id))
/* 206 */       throw new IllegalArgumentException("plug-in " + id + " disabled internally as it wasn't properly initialized"); 
/* 209 */     if (this.disabledPlugins.contains(id))
/* 210 */       throw new IllegalArgumentException("plug-in " + id + " disabled externally"); 
/* 213 */     PluginDescriptor descr = this.registry.getPluginDescriptor(id);
/* 214 */     if (descr == null)
/* 215 */       throw new IllegalArgumentException("unknown plug-in ID - " + id); 
/* 217 */     return activatePlugin(descr);
/*     */   }
/*     */   
/*     */   public void activatePlugin(String id) throws PluginLifecycleException {
/* 230 */     if (this.activePlugins.containsKey(id))
/*     */       return; 
/* 233 */     if (this.badPlugins.contains(id))
/* 234 */       throw new IllegalArgumentException("plug-in " + id + " disabled internally as it wasn't properly initialized"); 
/* 237 */     if (this.disabledPlugins.contains(id))
/* 238 */       throw new IllegalArgumentException("plug-in " + id + " disabled externally"); 
/* 241 */     PluginDescriptor descr = this.registry.getPluginDescriptor(id);
/* 242 */     if (descr == null)
/* 243 */       throw new IllegalArgumentException("unknown plug-in ID - " + id); 
/* 245 */     activatePlugin(descr);
/*     */   }
/*     */   
/*     */   public Plugin getPluginFor(Object obj) {
/*     */     ClassLoader clsLoader;
/* 259 */     if (obj == null)
/* 260 */       return null; 
/* 263 */     if (obj instanceof Class) {
/* 264 */       clsLoader = ((Class)obj).getClassLoader();
/* 265 */     } else if (obj instanceof ClassLoader) {
/* 266 */       clsLoader = (ClassLoader)obj;
/*     */     } else {
/* 268 */       clsLoader = obj.getClass().getClassLoader();
/*     */     } 
/* 270 */     if (!(clsLoader instanceof PluginClassLoader))
/* 271 */       return null; 
/* 273 */     PluginDescriptor descr = ((PluginClassLoader)clsLoader).getPluginDescriptor();
/* 275 */     Plugin result = this.activePlugins.get(descr.getId());
/* 276 */     if (result != null)
/* 277 */       return result; 
/* 279 */     throw new IllegalStateException("can't get plug-in " + descr);
/*     */   }
/*     */   
/*     */   public boolean isPluginActivated(PluginDescriptor descr) {
/* 289 */     return this.activePlugins.containsKey(descr.getId());
/*     */   }
/*     */   
/*     */   public boolean isBadPlugin(PluginDescriptor descr) {
/* 299 */     return this.badPlugins.contains(descr.getId());
/*     */   }
/*     */   
/*     */   public boolean isPluginActivating(PluginDescriptor descr) {
/* 309 */     return this.activatingPlugins.contains(descr.getId());
/*     */   }
/*     */   
/*     */   public PluginClassLoader getPluginClassLoader(PluginDescriptor descr) {
/* 323 */     if (this.badPlugins.contains(descr.getId()))
/* 324 */       throw new IllegalArgumentException("plug-in " + descr.getId() + " disabled internally as it wasn't properly initialized"); 
/* 327 */     if (this.disabledPlugins.contains(descr.getId()))
/* 328 */       throw new IllegalArgumentException("plug-in " + descr.getId() + " disabled externally"); 
/* 331 */     PluginClassLoader result = this.classLoaders.get(descr.getId());
/* 332 */     if (result != null)
/* 333 */       return result; 
/* 335 */     synchronized (this) {
/* 336 */       result = this.classLoaders.get(descr.getId());
/* 337 */       if (result != null)
/* 338 */         return result; 
/* 340 */       result = this.lifecycleHandler.createPluginClassLoader(descr);
/* 341 */       this.classLoaders.put(descr.getId(), result);
/*     */     } 
/* 343 */     return result;
/*     */   }
/*     */   
/*     */   public synchronized void shutdown() {
/* 356 */     this.log.debug("shutting down...");
/* 357 */     dump();
/* 358 */     this.registry.unregisterListener(this.registryChangeListener);
/* 359 */     List<String> reversedLog = new ArrayList<String>(this.activationLog);
/* 360 */     Collections.reverse(reversedLog);
/* 361 */     for (String id : reversedLog) {
/* 362 */       PluginDescriptor descr = this.registry.getPluginDescriptor(id);
/* 363 */       if (descr == null) {
/* 364 */         this.log.warn("can't find descriptor for plug-in " + id + " to deactivate plug-in", new Exception("fake exception to view stack trace"));
/*     */         continue;
/*     */       } 
/* 369 */       deactivatePlugin(descr);
/*     */     } 
/* 371 */     dump();
/* 372 */     this.classLoaders.clear();
/* 373 */     this.disabledPlugins.clear();
/* 374 */     this.listeners.clear();
/* 375 */     this.lifecycleHandler.dispose();
/* 376 */     this.log.info("shutdown done");
/*     */   }
/*     */   
/*     */   private synchronized Plugin activatePlugin(PluginDescriptor descr) throws PluginLifecycleException {
/* 381 */     Plugin result = this.activePlugins.get(descr.getId());
/* 382 */     if (result != null)
/* 383 */       return result; 
/* 385 */     if (this.badPlugins.contains(descr.getId()))
/* 386 */       throw new IllegalArgumentException("plug-in " + descr.getId() + " disabled as it wasn't properly initialized"); 
/* 389 */     if (this.activatingPlugins.contains(descr.getId()))
/* 390 */       throw new PluginLifecycleException("org.java.plugin.standard", "pluginActivating", descr.getId()); 
/* 394 */     this.activatingPlugins.add(descr.getId());
/*     */     try {
/*     */       try {
/* 397 */         checkPrerequisites(descr);
/* 398 */         String pluginClassName = descr.getPluginClassName();
/* 399 */         if (pluginClassName == null || pluginClassName.trim().length() == 0) {
/* 401 */           result = new EmptyPlugin();
/*     */         } else {
/* 403 */           result = this.lifecycleHandler.createPluginInstance(descr);
/*     */         } 
/* 405 */         initPlugin(result, descr);
/* 406 */         this.lifecycleHandler.beforePluginStart(result);
/* 407 */         startPlugin(result);
/* 408 */       } catch (PluginLifecycleException ple) {
/* 409 */         this.badPlugins.add(descr.getId());
/* 410 */         this.classLoaders.remove(descr.getId());
/* 411 */         throw ple;
/* 412 */       } catch (Exception e) {
/* 413 */         this.badPlugins.add(descr.getId());
/* 414 */         this.classLoaders.remove(descr.getId());
/* 415 */         throw new PluginLifecycleException("org.java.plugin.standard", "pluginStartFailed", descr.getUniqueId(), e);
/*     */       } 
/* 419 */       this.activePlugins.put(descr.getId(), result);
/* 420 */       this.activationLog.add(descr.getId());
/* 421 */       this.log.info("plug-in started - " + descr.getUniqueId());
/* 422 */       fireEvent(result, true);
/* 423 */       return result;
/*     */     } finally {
/* 425 */       this.activatingPlugins.remove(descr.getId());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkPrerequisites(PluginDescriptor descr) throws PluginLifecycleException {
/* 431 */     for (PluginPrerequisite pre : descr.getPrerequisites()) {
/* 432 */       if (this.activatingPlugins.contains(pre.getPluginId())) {
/* 433 */         this.log.warn("dependencies loop detected during activation of plug-in " + descr, new Exception("fake exception to view stack trace"));
/*     */         continue;
/*     */       } 
/* 438 */       if (this.badPlugins.contains(pre.getPluginId())) {
/* 439 */         if (pre.isOptional())
/*     */           continue; 
/* 442 */         throw new PluginLifecycleException("org.java.plugin.standard", "pluginPrerequisiteBad", new Object[] { descr.getId(), pre.getPluginId() });
/*     */       } 
/* 447 */       if (this.disabledPlugins.contains(pre.getPluginId())) {
/* 448 */         if (pre.isOptional())
/*     */           continue; 
/* 451 */         throw new PluginLifecycleException("org.java.plugin.standard", "pluginPrerequisiteDisabled", new Object[] { descr.getId(), pre.getPluginId() });
/*     */       } 
/* 456 */       if (!pre.matches()) {
/* 457 */         if (pre.isOptional())
/*     */           continue; 
/* 460 */         throw new PluginLifecycleException("org.java.plugin.standard", "pluginPrerequisiteNotMatches", new Object[] { descr.getId(), pre.getPluginId() });
/*     */       } 
/*     */       try {
/* 466 */         activatePlugin(this.registry.getPluginDescriptor(pre.getPluginId()));
/* 467 */       } catch (PluginLifecycleException ple) {
/* 468 */         if (pre.isOptional()) {
/* 469 */           this.log.warn("failed activating optional plug-in from prerequisite " + pre, (Throwable)ple);
/*     */           continue;
/*     */         } 
/* 473 */         throw ple;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void deactivatePlugin(String id) {
/* 488 */     if (!this.activePlugins.containsKey(id))
/*     */       return; 
/* 491 */     PluginDescriptor descr = this.registry.getPluginDescriptor(id);
/* 492 */     if (descr == null)
/* 493 */       throw new IllegalArgumentException("unknown plug-in ID - " + id); 
/* 496 */     Map<String, PluginDescriptor> dependingPluginsMap = new HashMap<String, PluginDescriptor>();
/* 497 */     for (PluginDescriptor dependingPlugin : this.registry.getDependingPlugins(descr))
/* 499 */       dependingPluginsMap.put(dependingPlugin.getId(), dependingPlugin); 
/* 502 */     List<PluginDescriptor> tobeDeactivated = new LinkedList<PluginDescriptor>();
/* 503 */     List<String> reversedLog = new ArrayList<String>(this.activationLog);
/* 504 */     Collections.reverse(reversedLog);
/* 505 */     for (String pluginId : reversedLog) {
/* 506 */       if (pluginId.equals(descr.getId())) {
/* 507 */         tobeDeactivated.add(descr);
/*     */         continue;
/*     */       } 
/* 508 */       if (dependingPluginsMap.containsKey(pluginId))
/* 509 */         tobeDeactivated.add(dependingPluginsMap.get(pluginId)); 
/*     */     } 
/* 513 */     for (PluginDescriptor descriptor : tobeDeactivated)
/* 514 */       deactivatePlugin(descriptor); 
/* 516 */     dump();
/*     */   }
/*     */   
/*     */   private synchronized void deactivatePlugin(PluginDescriptor descr) {
/* 520 */     Plugin plugin = this.activePlugins.remove(descr.getId());
/* 521 */     if (plugin != null)
/*     */       try {
/* 523 */         if (plugin.isActive()) {
/* 524 */           fireEvent(plugin, false);
/* 525 */           stopPlugin(plugin);
/* 526 */           this.lifecycleHandler.afterPluginStop(plugin);
/* 527 */           this.log.info("plug-in stopped - " + descr.getUniqueId());
/*     */         } else {
/* 529 */           this.log.warn("plug-in " + descr.getUniqueId() + " is not active although present in active " + "plug-ins list", new Exception("fake exception to view stack trace"));
/*     */         } 
/* 534 */       } catch (Exception e) {
/* 535 */         this.log.error("error while stopping plug-in " + descr.getUniqueId(), e);
/*     */       }  
/* 539 */     PluginClassLoader clsLoader = this.classLoaders.remove(descr.getId());
/* 540 */     if (clsLoader != null)
/* 541 */       disposeClassLoader(clsLoader); 
/* 543 */     this.badPlugins.remove(descr.getId());
/* 544 */     this.activationLog.remove(descr.getId());
/*     */   }
/*     */   
/*     */   private void dump() {
/* 548 */     if (!this.log.isDebugEnabled())
/*     */       return; 
/* 551 */     StringBuilder buf = new StringBuilder("PLUGIN MANAGER DUMP:\r\n");
/* 552 */     buf.append("-------------- DUMP BEGIN -----------------\r\n");
/* 553 */     buf.append("\tActive plug-ins: " + this.activePlugins.size()).append("\r\n");
/* 555 */     for (Plugin plugin : this.activePlugins.values())
/* 556 */       buf.append("\t\t").append(plugin).append("\r\n"); 
/* 559 */     buf.append("\tActivating plug-ins: " + this.activatingPlugins.size()).append("\r\n");
/* 561 */     for (String s : this.activatingPlugins)
/* 562 */       buf.append("\t\t").append(s).append("\r\n"); 
/* 565 */     buf.append("\tPlug-ins with instantiated class loaders: " + this.classLoaders.size()).append("\r\n");
/* 567 */     for (String s : this.classLoaders.keySet())
/* 568 */       buf.append("\t\t").append(s).append("\r\n"); 
/* 571 */     buf.append("\tDisabled plug-ins: " + this.disabledPlugins.size()).append("\r\n");
/* 573 */     for (String s : this.disabledPlugins)
/* 574 */       buf.append("\t\t").append(s).append("\r\n"); 
/* 577 */     buf.append("\tBad plug-ins: " + this.badPlugins.size()).append("\r\n");
/* 579 */     for (String s : this.badPlugins)
/* 580 */       buf.append("\t\t").append(s).append("\r\n"); 
/* 583 */     buf.append("\tActivation log: " + this.activationLog.size()).append("\r\n");
/* 585 */     for (String s : this.activationLog)
/* 586 */       buf.append("\t\t").append(s).append("\r\n"); 
/* 589 */     buf.append("Memory TOTAL/FREE/MAX: ").append(Runtime.getRuntime().totalMemory()).append("/").append(Runtime.getRuntime().freeMemory()).append("/").append(Runtime.getRuntime().maxMemory()).append("\r\n");
/* 593 */     buf.append("-------------- DUMP END -----------------");
/* 594 */     this.log.debug(buf.toString());
/*     */   }
/*     */   
/*     */   public PluginDescriptor[] disablePlugin(PluginDescriptor descr) {
/* 612 */     List<PluginDescriptor> result = new LinkedList<PluginDescriptor>();
/* 613 */     if (!this.disabledPlugins.contains(descr.getId())) {
/* 614 */       deactivatePlugin(descr);
/* 615 */       fireEvent(descr, false);
/* 616 */       this.disabledPlugins.add(descr.getId());
/* 617 */       result.add(descr);
/*     */     } 
/* 620 */     for (PluginDescriptor dependedPlugin : this.registry.getDependingPlugins(descr)) {
/* 622 */       if (!this.disabledPlugins.contains(dependedPlugin.getId())) {
/* 623 */         deactivatePlugin(dependedPlugin);
/* 624 */         fireEvent(dependedPlugin, false);
/* 625 */         this.disabledPlugins.add(dependedPlugin.getId());
/* 626 */         result.add(dependedPlugin);
/*     */       } 
/*     */     } 
/* 629 */     return result.<PluginDescriptor>toArray(new PluginDescriptor[result.size()]);
/*     */   }
/*     */   
/*     */   public PluginDescriptor[] enablePlugin(PluginDescriptor descr, boolean includeDependings) {
/* 646 */     List<PluginDescriptor> result = new LinkedList<PluginDescriptor>();
/* 647 */     if (this.disabledPlugins.contains(descr.getId())) {
/* 648 */       this.disabledPlugins.remove(descr.getId());
/* 649 */       fireEvent(descr, true);
/* 650 */       result.add(descr);
/*     */     } 
/* 652 */     if (includeDependings)
/* 653 */       for (PluginDescriptor dependedPlugin : this.registry.getDependingPlugins(descr)) {
/* 655 */         if (this.disabledPlugins.contains(dependedPlugin.getId())) {
/* 656 */           this.disabledPlugins.remove(dependedPlugin.getId());
/* 657 */           fireEvent(dependedPlugin, true);
/* 658 */           result.add(dependedPlugin);
/*     */         } 
/*     */       }  
/* 662 */     return result.<PluginDescriptor>toArray(new PluginDescriptor[result.size()]);
/*     */   }
/*     */   
/*     */   public boolean isPluginEnabled(PluginDescriptor descr) {
/* 672 */     return !this.disabledPlugins.contains(descr.getId());
/*     */   }
/*     */   
/*     */   public void registerListener(PluginManager.EventListener listener) {
/* 685 */     if (this.listeners.contains(listener))
/* 686 */       throw new IllegalArgumentException("listener " + listener + " already registered"); 
/* 689 */     this.listeners.add(listener);
/*     */   }
/*     */   
/*     */   public void unregisterListener(PluginManager.EventListener listener) {
/* 702 */     if (!this.listeners.remove(listener))
/* 703 */       this.log.warn("unknown listener " + listener); 
/*     */   }
/*     */   
/*     */   private void fireEvent(Object data, boolean on) {
/* 708 */     if (this.listeners.isEmpty())
/*     */       return; 
/* 712 */     PluginManager.EventListener[] arr = this.listeners.<PluginManager.EventListener>toArray(new PluginManager.EventListener[this.listeners.size()]);
/* 716 */     if (data instanceof PluginDescriptor) {
/* 717 */       PluginDescriptor descr = (PluginDescriptor)data;
/* 718 */       if (on) {
/* 719 */         if (this.log.isDebugEnabled())
/* 720 */           this.log.debug("propagating \"pluginEnabled\" event for " + descr); 
/* 723 */         for (PluginManager.EventListener element : arr)
/* 724 */           element.pluginEnabled(descr); 
/*     */       } else {
/* 727 */         if (this.log.isDebugEnabled())
/* 728 */           this.log.debug("propagating \"pluginDisabled\" event for " + descr); 
/* 731 */         for (PluginManager.EventListener element : arr)
/* 732 */           element.pluginDisabled(descr); 
/*     */       } 
/*     */     } else {
/* 736 */       Plugin plugin = (Plugin)data;
/* 737 */       if (on) {
/* 738 */         if (this.log.isDebugEnabled())
/* 739 */           this.log.debug("propagating \"pluginActivated\" event for " + plugin); 
/* 742 */         for (PluginManager.EventListener element : arr)
/* 743 */           element.pluginActivated(plugin); 
/*     */       } else {
/* 746 */         if (this.log.isDebugEnabled())
/* 747 */           this.log.debug("propagating \"pluginDeactivated\" event for " + plugin); 
/* 750 */         for (PluginManager.EventListener element : arr)
/* 751 */           element.pluginDeactivated(plugin); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static final class EmptyPlugin extends Plugin {
/*     */     protected void doStart() throws Exception {}
/*     */     
/*     */     protected void doStop() throws Exception {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\standard\StandardPluginManager.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */