/*     */ package org.openstreetmap.osmosis.core;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FilenameFilter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Logger;
/*     */ import org.java.plugin.JpfException;
/*     */ import org.java.plugin.ObjectFactory;
/*     */ import org.java.plugin.PluginClassLoader;
/*     */ import org.java.plugin.PluginLifecycleException;
/*     */ import org.java.plugin.PluginManager;
/*     */ import org.java.plugin.registry.Extension;
/*     */ import org.java.plugin.registry.ExtensionPoint;
/*     */ import org.java.plugin.registry.ManifestProcessingException;
/*     */ import org.java.plugin.registry.PluginDescriptor;
/*     */ import org.java.plugin.standard.StandardPluginLocation;
/*     */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactory;
/*     */ import org.openstreetmap.osmosis.core.pipeline.common.TaskManagerFactoryRegister;
/*     */ import org.openstreetmap.osmosis.core.plugin.PluginLoader;
/*     */ 
/*     */ public class TaskRegistrar {
/*  45 */   private static final Logger LOG = Logger.getLogger(TaskRegistrar.class.getName());
/*     */   
/*  57 */   private TaskManagerFactoryRegister factoryRegister = new TaskManagerFactoryRegister();
/*     */   
/*     */   public TaskManagerFactoryRegister getFactoryRegister() {
/*  67 */     return this.factoryRegister;
/*     */   }
/*     */   
/*     */   public void initialize(List<String> plugins) {
/*  80 */     loadBuiltInPlugins();
/*  83 */     for (String plugin : plugins)
/*  84 */       loadPlugin(plugin); 
/*  88 */     loadJPFPlugins();
/*     */   }
/*     */   
/*     */   private void loadBuiltInPlugins() {
/*  93 */     String pluginResourceName = "osmosis-plugins.conf";
/*     */     try {
/*  96 */       for (URL pluginConfigurationUrl : Collections.<URL>list(Thread.currentThread().getContextClassLoader().getResources("osmosis-plugins.conf"))) {
/* 101 */         LOG.finer("Loading plugin configuration file from url " + pluginConfigurationUrl + ".");
/* 103 */         InputStream pluginInputStream = pluginConfigurationUrl.openStream();
/* 104 */         if (pluginInputStream == null)
/* 105 */           throw new OsmosisRuntimeException("Cannot open URL " + pluginConfigurationUrl + "."); 
/*     */         try {
/* 109 */           BufferedReader pluginReader = new BufferedReader(new InputStreamReader(pluginInputStream));
/*     */           while (true) {
/* 114 */             String plugin = pluginReader.readLine();
/* 115 */             if (plugin == null)
/*     */               break; 
/* 119 */             plugin = plugin.trim();
/* 120 */             if (!plugin.isEmpty()) {
/* 121 */               LOG.finer("Loading plugin via loader " + plugin + ".");
/* 123 */               loadPlugin(plugin);
/*     */             } 
/*     */           } 
/*     */         } finally {
/*     */           try {
/* 128 */             pluginInputStream.close();
/* 129 */           } catch (IOException e) {
/* 130 */             LOG.warning("Unable to close plugin resource osmosis-plugins.conf.");
/*     */           } 
/*     */         } 
/*     */       } 
/* 135 */     } catch (IOException e) {
/* 136 */       throw new OsmosisRuntimeException("Unable to load the plugins based on osmosis-plugins.conf resources.");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void loadJPFPlugins() {
/* 151 */     PluginManager pluginManager = ObjectFactory.newInstance().createManager();
/* 154 */     LOG.fine("Searching for JPF plugins.");
/* 155 */     List<PluginManager.PluginLocation> locations = gatherJpfPlugins();
/* 158 */     LOG.fine("Registering the core plugin.");
/* 159 */     registerCorePlugin(pluginManager);
/* 162 */     LOG.fine("Registering the extension plugins.");
/* 163 */     if (locations.size() == 0)
/*     */       return; 
/* 167 */     registerJpfPlugins(pluginManager, locations);
/* 170 */     LOG.fine("Activating the plugins.");
/* 172 */     PluginDescriptor core = pluginManager.getRegistry().getPluginDescriptor("org.openstreetmap.osmosis.core.plugin.Core");
/* 175 */     ExtensionPoint point = pluginManager.getRegistry().getExtensionPoint(core.getId(), "Task");
/* 176 */     for (Iterator<Extension> it = point.getConnectedExtensions().iterator(); it.hasNext(); ) {
/* 178 */       Extension ext = it.next();
/* 179 */       PluginDescriptor descr = ext.getDeclaringPluginDescriptor();
/*     */       try {
/* 181 */         pluginManager.enablePlugin(descr, true);
/* 182 */         pluginManager.activatePlugin(descr.getId());
/* 183 */         PluginClassLoader pluginClassLoader = pluginManager.getPluginClassLoader(descr);
/* 184 */         loadPluginClass(ext.getParameter("class").valueAsString(), (ClassLoader)pluginClassLoader);
/* 185 */       } catch (PluginLifecycleException e) {
/* 186 */         throw new OsmosisRuntimeException("Cannot load JPF-plugin '" + ext.getId() + "' for extensionpoint '" + ext.getExtendedPointId() + "'", e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void registerCorePlugin(PluginManager pluginManager) {
/*     */     try {
/* 205 */       URL core = getClass().getResource("/org/openstreetmap/osmosis/core/plugin/plugin.xml");
/* 206 */       LOG.finest("Plugin URL: " + core);
/* 209 */       pluginManager.getRegistry().register(new URL[] { core });
/* 212 */       PluginDescriptor coreDescriptor = pluginManager.getRegistry().getPluginDescriptor("org.openstreetmap.osmosis.core.plugin.Core");
/* 216 */       pluginManager.enablePlugin(coreDescriptor, true);
/* 217 */       pluginManager.activatePlugin("org.openstreetmap.osmosis.core.plugin.Core");
/* 219 */     } catch (ManifestProcessingException e) {
/* 220 */       throw new OsmosisRuntimeException("Unable to register core plugin.", e);
/* 221 */     } catch (PluginLifecycleException e) {
/* 222 */       throw new OsmosisRuntimeException("Unable to enable core plugin.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void registerJpfPlugins(PluginManager pluginManager, List<PluginManager.PluginLocation> locations) {
/* 234 */     if (locations == null)
/* 235 */       throw new IllegalArgumentException("null plugin-list given"); 
/*     */     try {
/* 239 */       pluginManager.publishPlugins(locations.<PluginManager.PluginLocation>toArray(new PluginManager.PluginLocation[locations.size()]));
/* 240 */     } catch (JpfException e) {
/* 241 */       throw new OsmosisRuntimeException("Unable to publish plugins.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private List<PluginManager.PluginLocation> gatherJpfPlugins() {
/* 250 */     File[] pluginsDirs = { new File("plugins"), new File(System.getProperty("user.home") + "/.openstreetmap" + File.separator + "osmosis" + File.separator + "plugins"), new File(System.getenv("APPDATA") + File.separator + "openstreetmap" + File.separator + "osmosis" + File.separator + "plugins") };
/* 259 */     FilenameFilter pluginFileNameFilter = new FilenameFilter() {
/*     */         public boolean accept(File dir, String name) {
/* 269 */           return (name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar"));
/*     */         }
/*     */       };
/* 272 */     List<PluginManager.PluginLocation> locations = new LinkedList<PluginManager.PluginLocation>();
/* 273 */     for (File pluginDir : pluginsDirs) {
/* 274 */       LOG.finer("Loading plugins in " + pluginDir.getAbsolutePath());
/* 275 */       if (pluginDir.exists()) {
/* 278 */         File[] plugins = pluginDir.listFiles(pluginFileNameFilter);
/*     */         try {
/* 280 */           for (int i = 0; i < plugins.length; i++) {
/* 281 */             LOG.finest("Found plugin " + plugins[i].getAbsolutePath());
/* 282 */             PluginManager.PluginLocation location = StandardPluginLocation.create(plugins[i]);
/* 284 */             if (location != null) {
/* 285 */               locations.add(location);
/*     */             } else {
/* 287 */               LOG.warning("JPF Plugin " + plugins[i].getAbsolutePath() + " is malformed and cannot be loaded.");
/*     */             } 
/*     */           } 
/* 291 */         } catch (MalformedURLException e) {
/* 292 */           throw new OsmosisRuntimeException("Cannot create plugin location " + pluginDir.getAbsolutePath(), e);
/*     */         } 
/*     */       } 
/*     */     } 
/* 295 */     return locations;
/*     */   }
/*     */   
/*     */   private void loadPlugin(String plugin) {
/* 311 */     ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
/* 313 */     loadPluginClass(plugin, classLoader);
/*     */   }
/*     */   
/*     */   private void loadPluginClass(String pluginClassName, ClassLoader classLoader) {
/*     */     Class<?> untypedPluginClass;
/*     */     PluginLoader pluginLoader;
/*     */     try {
/* 332 */       untypedPluginClass = classLoader.loadClass(pluginClassName);
/* 333 */     } catch (ClassNotFoundException e) {
/* 334 */       throw new OsmosisRuntimeException("Unable to load plugin class (" + pluginClassName + ").", e);
/*     */     } 
/* 337 */     if (!PluginLoader.class.isAssignableFrom(untypedPluginClass))
/* 338 */       throw new OsmosisRuntimeException("The class (" + pluginClassName + ") does not implement interface (" + PluginLoader.class.getName() + "). Maybe it's not a plugin?"); 
/* 341 */     Class<PluginLoader> pluginClass = (Class)untypedPluginClass;
/*     */     try {
/* 345 */       pluginLoader = pluginClass.newInstance();
/* 346 */     } catch (InstantiationException e) {
/* 347 */       throw new IllegalArgumentException("Unable to instantiate plugin class (" + pluginClassName + ").", e);
/* 348 */     } catch (IllegalAccessException e) {
/* 349 */       throw new IllegalArgumentException("Unable to instantiate plugin class (" + pluginClassName + ").", e);
/*     */     } 
/* 353 */     Map<String, TaskManagerFactory> pluginTasks = pluginLoader.loadTaskFactories();
/* 356 */     for (Map.Entry<String, TaskManagerFactory> taskEntry : pluginTasks.entrySet())
/* 357 */       this.factoryRegister.register(taskEntry.getKey(), taskEntry.getValue()); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\TaskRegistrar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */