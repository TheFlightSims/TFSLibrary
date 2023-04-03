/*      */ package org.java.plugin.registry.xml;
/*      */ 
/*      */ import java.net.URL;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.java.plugin.PathResolver;
/*      */ import org.java.plugin.registry.Extension;
/*      */ import org.java.plugin.registry.ExtensionPoint;
/*      */ import org.java.plugin.registry.Identity;
/*      */ import org.java.plugin.registry.IntegrityCheckReport;
/*      */ import org.java.plugin.registry.ManifestInfo;
/*      */ import org.java.plugin.registry.ManifestProcessingException;
/*      */ import org.java.plugin.registry.MatchingRule;
/*      */ import org.java.plugin.registry.PluginDescriptor;
/*      */ import org.java.plugin.registry.PluginFragment;
/*      */ import org.java.plugin.registry.PluginPrerequisite;
/*      */ import org.java.plugin.registry.PluginRegistry;
/*      */ import org.java.plugin.registry.Version;
/*      */ import org.java.plugin.util.ExtendedProperties;
/*      */ 
/*      */ public final class PluginRegistryImpl implements PluginRegistry {
/*      */   static final String PACKAGE_NAME = "org.java.plugin.registry.xml";
/*      */   
/*      */   private static final char UNIQUE_SEPARATOR = '@';
/*      */   
/*   81 */   private static final Log log = LogFactory.getLog(PluginRegistryImpl.class);
/*      */   
/*   83 */   private final List<IntegrityCheckReport.ReportItem> registrationReport = new LinkedList<IntegrityCheckReport.ReportItem>();
/*      */   
/*   84 */   private final Map<String, PluginDescriptor> registeredPlugins = new HashMap<String, PluginDescriptor>();
/*      */   
/*   85 */   private final Map<String, PluginFragment> registeredFragments = new HashMap<String, PluginFragment>();
/*      */   
/*   86 */   private final List<PluginRegistry.RegistryChangeListener> listeners = Collections.synchronizedList(new LinkedList<PluginRegistry.RegistryChangeListener>());
/*      */   
/*      */   private ManifestParser manifestParser;
/*      */   
/*      */   private boolean stopOnError = false;
/*      */   
/*      */   public PluginRegistryImpl() {
/*   94 */     this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "registryStart", null));
/*      */   }
/*      */   
/*      */   public void configure(ExtendedProperties config) {
/*  104 */     this.stopOnError = "true".equalsIgnoreCase(config.getProperty("stopOnError", "false"));
/*  106 */     boolean isValidating = !"false".equalsIgnoreCase(config.getProperty("isValidating", "true"));
/*  108 */     this.manifestParser = new ManifestParser(isValidating);
/*  109 */     log.info("configured, stopOnError=" + this.stopOnError + ", isValidating=" + isValidating);
/*      */   }
/*      */   
/*      */   public ManifestInfo readManifestInfo(URL url) throws ManifestProcessingException {
/*      */     try {
/*  120 */       return new ManifestInfoImpl(this.manifestParser.parseManifestInfo(url));
/*  121 */     } catch (Exception e) {
/*  122 */       throw new ManifestProcessingException("org.java.plugin.registry.xml", "manifestParsingError", url, e);
/*      */     } 
/*      */   }
/*      */   
/*      */   public Map<String, Identity> register(URL[] manifests) throws ManifestProcessingException {
/*  161 */     List<ExtensionPoint> registeredPoints = new LinkedList<ExtensionPoint>();
/*  163 */     Map<String, Extension> registeredExtensions = new HashMap<String, Extension>();
/*  165 */     for (PluginDescriptor descriptor : this.registeredPlugins.values()) {
/*  166 */       for (ExtensionPoint point : descriptor.getExtensionPoints()) {
/*  167 */         registeredPoints.add(point);
/*  168 */         for (Extension ext : point.getConnectedExtensions())
/*  169 */           registeredExtensions.put(ext.getUniqueId(), ext); 
/*      */       } 
/*      */     } 
/*  173 */     Map<String, Identity> result = new HashMap<String, Identity>(manifests.length);
/*  175 */     Map<String, ModelPluginManifest> plugins = new HashMap<String, ModelPluginManifest>();
/*  177 */     Map<String, ModelPluginManifest> fragments = new HashMap<String, ModelPluginManifest>();
/*  180 */     this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "manifestsParsingStart", null));
/*  184 */     for (URL url : manifests) {
/*      */       ModelPluginManifest model;
/*      */       try {
/*  187 */         model = this.manifestParser.parseManifest(url);
/*  188 */       } catch (Exception e) {
/*  189 */         log.error("can't parse manifest file " + url, e);
/*  190 */         if (this.stopOnError)
/*  191 */           throw new ManifestProcessingException("org.java.plugin.registry.xml", "manifestParsingError", url, e); 
/*  194 */         this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, null, IntegrityCheckReport.Error.MANIFEST_PROCESSING_FAILED, "manifestParsingError", new Object[] { url, e }));
/*      */       } 
/*  200 */       if (model instanceof ModelPluginFragment) {
/*  201 */         fragments.put(url.toExternalForm(), model);
/*  204 */       } else if (!(model instanceof ModelPluginDescriptor)) {
/*  205 */         log.warn("URL " + url + " points to XML document of unknown type");
/*      */       } else {
/*  209 */         plugins.put(url.toExternalForm(), model);
/*      */       } 
/*      */     } 
/*  211 */     if (log.isDebugEnabled())
/*  212 */       log.debug("manifest files parsed, plugins.size=" + plugins.size() + ", fragments.size=" + fragments.size()); 
/*  215 */     this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "manifestsParsingFinish", new Object[] { Integer.valueOf(plugins.size()), Integer.valueOf(fragments.size()) }));
/*  220 */     checkVersions(plugins);
/*  221 */     if (log.isDebugEnabled())
/*  222 */       log.debug("plug-ins versions checked, plugins.size=" + plugins.size()); 
/*  225 */     checkVersions(fragments);
/*  226 */     if (log.isDebugEnabled())
/*  227 */       log.debug("plug-in fragments versions checked, fragments.size=" + fragments.size()); 
/*  230 */     RegistryChangeDataImpl registryChangeData = new RegistryChangeDataImpl();
/*  233 */     this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "registeringPluginsStart", null));
/*  237 */     for (ModelPluginManifest model : plugins.values()) {
/*  238 */       PluginDescriptor descr = registerPlugin((ModelPluginDescriptor)model, registryChangeData);
/*  240 */       if (descr != null)
/*  241 */         result.put(descr.getLocation().toExternalForm(), descr); 
/*      */     } 
/*  244 */     plugins.clear();
/*  246 */     this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "registeringFragmentsStart", null));
/*  250 */     for (ModelPluginManifest entry : fragments.values()) {
/*  251 */       PluginFragment fragment = registerFragment((ModelPluginFragment)entry, registryChangeData);
/*  253 */       if (fragment != null)
/*  254 */         result.put(fragment.getLocation().toExternalForm(), fragment); 
/*      */     } 
/*  257 */     fragments.clear();
/*  258 */     this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "registeringPluginsFinish", Integer.valueOf(this.registeredPlugins.size())));
/*  263 */     this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "registeringFragmentsFinish", Integer.valueOf(this.registeredFragments.size())));
/*  268 */     log.info("plug-in and fragment descriptors registered - " + result.size());
/*  270 */     dump();
/*  271 */     if (result.isEmpty())
/*  272 */       return result; 
/*  275 */     for (ExtensionPoint extensionPoint : registeredPoints)
/*  276 */       ((ExtensionPointImpl)extensionPoint).registryChanged(); 
/*  278 */     for (Extension extension : registeredExtensions.values())
/*  279 */       ((ExtensionImpl)extension).registryChanged(); 
/*  281 */     if (!this.listeners.isEmpty() || log.isDebugEnabled()) {
/*  284 */       for (PluginDescriptor pluginDescriptor : this.registeredPlugins.values()) {
/*  286 */         for (ExtensionPoint extensionPoint : pluginDescriptor.getExtensionPoints()) {
/*  288 */           for (Extension ext : extensionPoint.getConnectedExtensions()) {
/*  289 */             if (!registeredExtensions.containsKey(ext.getUniqueId())) {
/*  291 */               registryChangeData.putAddedExtension(ext.getUniqueId(), makeUniqueId(ext.getExtendedPluginId(), ext.getExtendedPointId()));
/*      */               continue;
/*      */             } 
/*  296 */             registeredExtensions.remove(ext.getUniqueId());
/*  297 */             if (registryChangeData.modifiedPlugins().contains(ext.getDeclaringPluginDescriptor().getId()) || registryChangeData.modifiedPlugins().contains(ext.getExtendedPluginId()))
/*  301 */               registryChangeData.putModifiedExtension(ext.getUniqueId(), makeUniqueId(ext.getExtendedPluginId(), ext.getExtendedPointId())); 
/*      */           } 
/*      */         } 
/*      */       } 
/*  310 */       for (Extension ext : registeredExtensions.values())
/*  311 */         registryChangeData.putRemovedExtension(ext.getUniqueId(), makeUniqueId(ext.getExtendedPluginId(), ext.getExtendedPointId())); 
/*  316 */       fireEvent(registryChangeData);
/*      */     } 
/*  318 */     return result;
/*      */   }
/*      */   
/*      */   private void checkVersions(Map<String, ModelPluginManifest> plugins) throws ManifestProcessingException {
/*  323 */     Map<String, Object[]> versions = (Map)new HashMap<String, Object>();
/*  324 */     Set<String> toBeRemovedUrls = new HashSet<String>();
/*  325 */     Iterator<Map.Entry<String, ModelPluginManifest>> it = plugins.entrySet().iterator();
/*  326 */     while (it.hasNext()) {
/*  327 */       Map.Entry<String, ModelPluginManifest> entry = it.next();
/*  328 */       String url = entry.getKey();
/*  329 */       ModelPluginManifest model = entry.getValue();
/*  330 */       if (this.registeredPlugins.containsKey(model.getId())) {
/*  331 */         if (this.stopOnError)
/*  332 */           throw new ManifestProcessingException("org.java.plugin.registry.xml", "duplicatePlugin", model.getId()); 
/*  336 */         it.remove();
/*  337 */         this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, null, IntegrityCheckReport.Error.MANIFEST_PROCESSING_FAILED, "duplicatedPluginId", model.getId()));
/*      */         continue;
/*      */       } 
/*  343 */       if (this.registeredFragments.containsKey(model.getId())) {
/*  344 */         if (this.stopOnError)
/*  345 */           throw new ManifestProcessingException("org.java.plugin.registry.xml", "duplicatePluginFragment", model.getId()); 
/*  349 */         it.remove();
/*  350 */         this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, null, IntegrityCheckReport.Error.MANIFEST_PROCESSING_FAILED, "duplicatedFragmentId", model.getId()));
/*      */         continue;
/*      */       } 
/*  356 */       Object[] version = versions.get(model.getId());
/*  357 */       if (version == null) {
/*  358 */         versions.put(model.getId(), new Object[] { model.getVersion(), url });
/*      */         continue;
/*      */       } 
/*  362 */       if (((Version)version[0]).compareTo(model.getVersion()) < 0) {
/*  363 */         toBeRemovedUrls.add((String)version[1]);
/*  364 */         versions.put(model.getId(), new Object[] { model.getVersion(), url });
/*      */         continue;
/*      */       } 
/*  367 */       toBeRemovedUrls.add(url);
/*      */     } 
/*  370 */     versions.clear();
/*  371 */     for (String url : toBeRemovedUrls)
/*  372 */       plugins.remove(url); 
/*  374 */     toBeRemovedUrls.clear();
/*      */   }
/*      */   
/*      */   private PluginDescriptor registerPlugin(ModelPluginDescriptor model, RegistryChangeDataImpl registryChangeData) throws ManifestProcessingException {
/*  380 */     if (log.isDebugEnabled())
/*  381 */       log.debug("registering plug-in, URL - " + model.getLocation()); 
/*  383 */     PluginDescriptorImpl result = null;
/*      */     try {
/*  385 */       result = new PluginDescriptorImpl(this, model);
/*  386 */       registryChangeData.addedPlugins().add(result.getId());
/*  388 */       for (PluginFragment pluginFragment : this.registeredFragments.values()) {
/*  389 */         PluginFragmentImpl fragment = (PluginFragmentImpl)pluginFragment;
/*  390 */         if (fragment.matches(result))
/*  391 */           result.registerFragment(fragment); 
/*      */       } 
/*  394 */       this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "pluginRegistered", result.getUniqueId()));
/*  398 */     } catch (ManifestProcessingException mpe) {
/*  399 */       log.error("failed registering plug-in, URL - " + model.getLocation(), (Throwable)mpe);
/*  401 */       if (this.stopOnError)
/*  402 */         throw mpe; 
/*  404 */       this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, null, IntegrityCheckReport.Error.MANIFEST_PROCESSING_FAILED, "pluginRegistrationFailed", new Object[] { model.getLocation(), mpe }));
/*  409 */       return null;
/*      */     } 
/*  411 */     this.registeredPlugins.put(result.getId(), result);
/*  412 */     return result;
/*      */   }
/*      */   
/*      */   private PluginFragment registerFragment(ModelPluginFragment model, RegistryChangeDataImpl registryChangeData) throws ManifestProcessingException {
/*  418 */     if (log.isDebugEnabled())
/*  419 */       log.debug("registering plug-in fragment descriptor, URL - " + model.getLocation()); 
/*  422 */     PluginFragmentImpl result = null;
/*      */     try {
/*  424 */       result = new PluginFragmentImpl(this, model);
/*  426 */       boolean isRegistered = false;
/*  427 */       PluginDescriptorImpl descr = (PluginDescriptorImpl)getPluginDescriptor(result.getPluginId());
/*  430 */       if (result.matches(descr)) {
/*  431 */         descr.registerFragment(result);
/*  432 */         if (!registryChangeData.addedPlugins().contains(descr.getId()))
/*  434 */           registryChangeData.modifiedPlugins().add(descr.getId()); 
/*  436 */         isRegistered = true;
/*      */       } 
/*  438 */       if (!isRegistered) {
/*  439 */         log.warn("no matching plug-ins found for fragment " + result.getUniqueId());
/*  441 */         this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.WARNING, null, IntegrityCheckReport.Error.NO_ERROR, "noMatchingPluginFound", result.getUniqueId()));
/*      */       } 
/*  446 */       this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "fragmentRegistered", result.getUniqueId()));
/*  450 */     } catch (ManifestProcessingException mpe) {
/*  451 */       log.error("failed registering plug-in fragment descriptor, URL - " + model.getLocation(), (Throwable)mpe);
/*  453 */       if (this.stopOnError)
/*  454 */         throw mpe; 
/*  456 */       this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, null, IntegrityCheckReport.Error.MANIFEST_PROCESSING_FAILED, "fragmentRegistrationFailed", new Object[] { model.getLocation(), mpe }));
/*  461 */       return null;
/*      */     } 
/*  463 */     this.registeredFragments.put(result.getId(), result);
/*  464 */     return result;
/*      */   }
/*      */   
/*      */   public Collection<String> unregister(String[] ids) {
/*  472 */     List<ExtensionPoint> registeredPoints = new LinkedList<ExtensionPoint>();
/*  473 */     Map<String, Extension> registeredExtensions = new HashMap<String, Extension>();
/*  474 */     for (PluginDescriptor pluginDescriptor : this.registeredPlugins.values()) {
/*  475 */       for (ExtensionPoint point : pluginDescriptor.getExtensionPoints()) {
/*  476 */         registeredPoints.add(point);
/*  477 */         for (Extension ext : point.getConnectedExtensions())
/*  478 */           registeredExtensions.put(ext.getUniqueId(), ext); 
/*      */       } 
/*      */     } 
/*  482 */     Set<String> result = new HashSet<String>();
/*  483 */     RegistryChangeDataImpl registryChangeData = new RegistryChangeDataImpl();
/*  486 */     this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "unregisteringPrepare", null));
/*  490 */     Map<String, PluginDescriptor> removingPlugins = new HashMap<String, PluginDescriptor>();
/*  491 */     Map<String, PluginFragment> removingFragments = new HashMap<String, PluginFragment>();
/*  492 */     for (String element : ids) {
/*  494 */       PluginDescriptor descr = this.registeredPlugins.get(element);
/*  495 */       if (descr != null) {
/*  496 */         for (PluginDescriptor depDescr : getDependingPlugins(descr)) {
/*  497 */           removingPlugins.put(depDescr.getId(), depDescr);
/*  498 */           registryChangeData.removedPlugins().add(depDescr.getId());
/*      */         } 
/*  500 */         removingPlugins.put(descr.getId(), descr);
/*  501 */         registryChangeData.removedPlugins().add(descr.getId());
/*      */       } else {
/*  504 */         PluginFragment fragment = this.registeredFragments.get(element);
/*  505 */         if (fragment != null) {
/*  506 */           removingFragments.put(fragment.getId(), fragment);
/*      */         } else {
/*  509 */           this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.WARNING, null, IntegrityCheckReport.Error.NO_ERROR, "pluginToUngregisterNotFound", element));
/*      */         } 
/*      */       } 
/*      */     } 
/*  514 */     for (PluginDescriptor descr : removingPlugins.values()) {
/*  515 */       for (PluginFragment fragment : descr.getFragments()) {
/*  516 */         if (removingFragments.containsKey(fragment.getId()))
/*      */           continue; 
/*  519 */         removingFragments.put(fragment.getId(), fragment);
/*      */       } 
/*      */     } 
/*  523 */     fireEvent(registryChangeData);
/*  524 */     this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "unregisteringFragmentsStart", null));
/*  528 */     for (PluginFragment pluginFragment : removingFragments.values()) {
/*  529 */       PluginFragmentImpl fragment = (PluginFragmentImpl)pluginFragment;
/*  530 */       unregisterFragment(fragment);
/*  531 */       if (!removingPlugins.containsKey(fragment.getPluginId()))
/*  532 */         registryChangeData.modifiedPlugins().add(fragment.getPluginId()); 
/*  535 */       result.add(fragment.getUniqueId());
/*      */     } 
/*  537 */     removingFragments.clear();
/*  538 */     this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "unregisteringPluginsStart", null));
/*  542 */     for (PluginDescriptor pluginDescriptor : removingPlugins.values()) {
/*  543 */       PluginDescriptorImpl descr = (PluginDescriptorImpl)pluginDescriptor;
/*  544 */       unregisterPlugin(descr);
/*  545 */       result.add(descr.getUniqueId());
/*      */     } 
/*  547 */     removingPlugins.clear();
/*  548 */     this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "unregisteringPluginsFinish", Integer.valueOf(this.registeredPlugins.size())));
/*  553 */     this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "unregisteringFragmentsFinish", Integer.valueOf(this.registeredFragments.size())));
/*  558 */     log.info("plug-in and fragment descriptors unregistered - " + result.size());
/*  560 */     dump();
/*  561 */     if (result.isEmpty())
/*  562 */       return result; 
/*  565 */     for (ExtensionPoint extensionPoint : registeredPoints)
/*  566 */       ((ExtensionPointImpl)extensionPoint).registryChanged(); 
/*  568 */     for (Extension extension : registeredExtensions.values())
/*  569 */       ((ExtensionImpl)extension).registryChanged(); 
/*  571 */     if (!this.listeners.isEmpty() || log.isDebugEnabled()) {
/*  573 */       for (PluginDescriptor descriptor : this.registeredPlugins.values()) {
/*  574 */         for (ExtensionPoint point : descriptor.getExtensionPoints()) {
/*  575 */           for (Extension ext : point.getConnectedExtensions()) {
/*  576 */             if (!registeredExtensions.containsKey(ext.getUniqueId())) {
/*  578 */               registryChangeData.putAddedExtension(ext.getUniqueId(), makeUniqueId(ext.getExtendedPluginId(), ext.getExtendedPointId()));
/*      */               continue;
/*      */             } 
/*  583 */             registeredExtensions.remove(ext.getUniqueId());
/*  584 */             if (registryChangeData.modifiedPlugins().contains(ext.getDeclaringPluginDescriptor().getId()) || registryChangeData.modifiedPlugins().contains(ext.getExtendedPluginId()))
/*  588 */               registryChangeData.putModifiedExtension(ext.getUniqueId(), makeUniqueId(ext.getExtendedPluginId(), ext.getExtendedPointId())); 
/*      */           } 
/*      */         } 
/*      */       } 
/*  597 */       for (Extension ext : registeredExtensions.values())
/*  598 */         registryChangeData.putRemovedExtension(ext.getUniqueId(), makeUniqueId(ext.getExtendedPluginId(), ext.getExtendedPointId())); 
/*  603 */       fireEvent(registryChangeData);
/*      */     } 
/*  605 */     return result;
/*      */   }
/*      */   
/*      */   private void unregisterPlugin(PluginDescriptorImpl descr) {
/*  609 */     this.registeredPlugins.remove(descr.getId());
/*  610 */     this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "pluginUnregistered", descr.getUniqueId()));
/*      */   }
/*      */   
/*      */   private void unregisterFragment(PluginFragmentImpl fragment) {
/*  617 */     PluginDescriptorImpl descr = (PluginDescriptorImpl)this.registeredPlugins.get(fragment.getPluginId());
/*  620 */     if (descr != null)
/*  621 */       descr.unregisterFragment(fragment); 
/*  623 */     this.registeredFragments.remove(fragment.getId());
/*  624 */     this.registrationReport.add(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.INFO, null, IntegrityCheckReport.Error.NO_ERROR, "fragmentUnregistered", fragment.getUniqueId()));
/*      */   }
/*      */   
/*      */   private void dump() {
/*  631 */     if (!log.isDebugEnabled())
/*      */       return; 
/*  634 */     StringBuilder buf = new StringBuilder();
/*  635 */     buf.append("PLUG-IN REGISTRY DUMP:\r\n").append("-------------- DUMP BEGIN -----------------\r\n").append("\tPlug-ins: " + this.registeredPlugins.size() + "\r\n");
/*  639 */     for (PluginDescriptor descriptor : this.registeredPlugins.values())
/*  640 */       buf.append("\t\t").append(descriptor).append("\r\n"); 
/*  644 */     buf.append("\tFragments: " + this.registeredFragments.size() + "\r\n");
/*  646 */     for (PluginFragment fragment : this.registeredFragments.values())
/*  647 */       buf.append("\t\t").append(fragment).append("\r\n"); 
/*  651 */     buf.append("Memory TOTAL/FREE/MAX: ").append(Runtime.getRuntime().totalMemory()).append("/").append(Runtime.getRuntime().freeMemory()).append("/").append(Runtime.getRuntime().maxMemory()).append("\r\n");
/*  658 */     buf.append("-------------- DUMP END -----------------\r\n");
/*  659 */     log.debug(buf.toString());
/*      */   }
/*      */   
/*      */   public ExtensionPoint getExtensionPoint(String pluginId, String pointId) {
/*  668 */     PluginDescriptor descriptor = this.registeredPlugins.get(pluginId);
/*  669 */     if (descriptor == null)
/*  670 */       throw new IllegalArgumentException("unknown plug-in ID " + pluginId + " provided for extension point " + pointId); 
/*  673 */     for (ExtensionPoint point : descriptor.getExtensionPoints()) {
/*  674 */       if (point.getId().equals(pointId)) {
/*  675 */         if (point.isValid())
/*  676 */           return point; 
/*  678 */         log.warn("extension point " + point.getUniqueId() + " is invalid and ignored by registry");
/*      */         break;
/*      */       } 
/*      */     } 
/*  683 */     throw new IllegalArgumentException("unknown extension point ID - " + makeUniqueId(pluginId, pointId));
/*      */   }
/*      */   
/*      */   public ExtensionPoint getExtensionPoint(String uniqueId) {
/*  691 */     return getExtensionPoint(extractPluginId(uniqueId), extractId(uniqueId));
/*      */   }
/*      */   
/*      */   public boolean isExtensionPointAvailable(String pluginId, String pointId) {
/*  701 */     PluginDescriptor descriptor = this.registeredPlugins.get(pluginId);
/*  702 */     if (descriptor == null)
/*  703 */       return false; 
/*  705 */     for (ExtensionPoint point : descriptor.getExtensionPoints()) {
/*  706 */       if (point.getId().equals(pointId))
/*  707 */         return point.isValid(); 
/*      */     } 
/*  710 */     return false;
/*      */   }
/*      */   
/*      */   public boolean isExtensionPointAvailable(String uniqueId) {
/*  718 */     return isExtensionPointAvailable(extractPluginId(uniqueId), extractId(uniqueId));
/*      */   }
/*      */   
/*      */   public PluginDescriptor getPluginDescriptor(String pluginId) {
/*  726 */     PluginDescriptor result = this.registeredPlugins.get(pluginId);
/*  728 */     if (result == null)
/*  729 */       throw new IllegalArgumentException("unknown plug-in ID - " + pluginId); 
/*  732 */     return result;
/*      */   }
/*      */   
/*      */   public boolean isPluginDescriptorAvailable(String pluginId) {
/*  739 */     return this.registeredPlugins.containsKey(pluginId);
/*      */   }
/*      */   
/*      */   public Collection<PluginDescriptor> getPluginDescriptors() {
/*  746 */     Collection<PluginDescriptor> empty_collection = Collections.emptyList();
/*  747 */     return this.registeredPlugins.isEmpty() ? empty_collection : Collections.<PluginDescriptor>unmodifiableCollection(this.registeredPlugins.values());
/*      */   }
/*      */   
/*      */   public Collection<PluginFragment> getPluginFragments() {
/*  756 */     Collection<PluginFragment> empty_collection = Collections.emptyList();
/*  757 */     return this.registeredFragments.isEmpty() ? empty_collection : Collections.<PluginFragment>unmodifiableCollection(this.registeredFragments.values());
/*      */   }
/*      */   
/*      */   public Collection<PluginDescriptor> getDependingPlugins(PluginDescriptor descr) {
/*  766 */     Map<String, PluginDescriptor> result = new HashMap<String, PluginDescriptor>();
/*  767 */     for (PluginDescriptor dependedDescr : getPluginDescriptors()) {
/*  769 */       if (dependedDescr.getId().equals(descr.getId()))
/*      */         continue; 
/*  772 */       for (PluginPrerequisite pre : dependedDescr.getPrerequisites()) {
/*  774 */         if (!pre.getPluginId().equals(descr.getId()) || !pre.matches())
/*      */           continue; 
/*  778 */         if (!result.containsKey(dependedDescr.getId())) {
/*  779 */           result.put(dependedDescr.getId(), dependedDescr);
/*  780 */           for (PluginDescriptor descriptor : getDependingPlugins(dependedDescr)) {
/*  782 */             if (!result.containsKey(descriptor.getId()))
/*  783 */               result.put(descriptor.getId(), descriptor); 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  790 */     return result.values();
/*      */   }
/*      */   
/*      */   public IntegrityCheckReport checkIntegrity(PathResolver pathResolver) {
/*  799 */     return checkIntegrity(pathResolver, false);
/*      */   }
/*      */   
/*      */   public IntegrityCheckReport checkIntegrity(PathResolver pathResolver, boolean includeRegistrationReport) {
/*  808 */     Collection<IntegrityCheckReport.ReportItem> empty_collection = Collections.emptyList();
/*  809 */     IntegrityChecker intergityCheckReport = new IntegrityChecker(this, includeRegistrationReport ? this.registrationReport : empty_collection);
/*  812 */     intergityCheckReport.doCheck(pathResolver);
/*  813 */     return intergityCheckReport;
/*      */   }
/*      */   
/*      */   public IntegrityCheckReport getRegistrationReport() {
/*  820 */     return new IntegrityChecker(this, this.registrationReport);
/*      */   }
/*      */   
/*      */   public String makeUniqueId(String pluginId, String id) {
/*  828 */     return pluginId + '@' + id;
/*      */   }
/*      */   
/*      */   public String makeUniqueId(String pluginId, Version version) {
/*  836 */     return pluginId + '@' + version;
/*      */   }
/*      */   
/*      */   public String extractPluginId(String uniqueId) {
/*  843 */     int p = uniqueId.indexOf('@');
/*  844 */     if (p <= 0 || p >= uniqueId.length() - 1)
/*  845 */       throw new IllegalArgumentException("invalid unique ID - " + uniqueId); 
/*  848 */     return uniqueId.substring(0, p);
/*      */   }
/*      */   
/*      */   public String extractId(String uniqueId) {
/*  855 */     int p = uniqueId.indexOf('@');
/*  856 */     if (p <= 0 || p >= uniqueId.length() - 1)
/*  857 */       throw new IllegalArgumentException("invalid unique ID - " + uniqueId); 
/*  860 */     return uniqueId.substring(p + 1);
/*      */   }
/*      */   
/*      */   public Version extractVersion(String uniqueId) {
/*  867 */     int p = uniqueId.indexOf('@');
/*  868 */     if (p <= 0 || p >= uniqueId.length() - 1)
/*  869 */       throw new IllegalArgumentException("invalid unique ID - " + uniqueId); 
/*  872 */     return Version.parse(uniqueId.substring(p + 1));
/*      */   }
/*      */   
/*      */   public void registerListener(PluginRegistry.RegistryChangeListener listener) {
/*  880 */     if (this.listeners.contains(listener))
/*  881 */       throw new IllegalArgumentException("listener " + listener + " already registered"); 
/*  884 */     this.listeners.add(listener);
/*      */   }
/*      */   
/*      */   public void unregisterListener(PluginRegistry.RegistryChangeListener listener) {
/*  892 */     if (!this.listeners.remove(listener))
/*  893 */       log.warn("unknown listener " + listener); 
/*      */   }
/*      */   
/*      */   void fireEvent(RegistryChangeDataImpl data) {
/*  898 */     data.dump();
/*  899 */     if (this.listeners.isEmpty())
/*      */       return; 
/*  903 */     PluginRegistry.RegistryChangeListener[] arr = this.listeners.<PluginRegistry.RegistryChangeListener>toArray(new PluginRegistry.RegistryChangeListener[this.listeners.size()]);
/*  906 */     data.beforeEventFire();
/*  907 */     if (log.isDebugEnabled())
/*  908 */       log.debug("propagating registry change event"); 
/*  910 */     for (PluginRegistry.RegistryChangeListener element : arr)
/*  912 */       element.registryChanged(data); 
/*  914 */     if (log.isDebugEnabled())
/*  915 */       log.debug("registry change event propagated"); 
/*  917 */     data.afterEventFire();
/*      */   }
/*      */   
/*      */   private static final class RegistryChangeDataImpl implements PluginRegistry.RegistryChangeData {
/*      */     private Set<String> addedPlugins;
/*      */     
/*      */     private Set<String> removedPlugins;
/*      */     
/*      */     private Set<String> modifiedPlugins;
/*      */     
/*      */     private Map<String, String> addedExtensions;
/*      */     
/*      */     private Map<String, String> removedExtensions;
/*      */     
/*      */     private Map<String, String> modifiedExtensions;
/*      */     
/*      */     protected RegistryChangeDataImpl() {
/*  930 */       reset();
/*      */     }
/*      */     
/*      */     private void reset() {
/*  934 */       this.addedPlugins = new HashSet<String>();
/*  935 */       this.removedPlugins = new HashSet<String>();
/*  936 */       this.modifiedPlugins = new HashSet<String>();
/*  937 */       this.addedExtensions = new HashMap<String, String>();
/*  938 */       this.removedExtensions = new HashMap<String, String>();
/*  939 */       this.modifiedExtensions = new HashMap<String, String>();
/*      */     }
/*      */     
/*      */     protected void beforeEventFire() {
/*  943 */       this.addedPlugins = Collections.unmodifiableSet(this.addedPlugins);
/*  944 */       this.removedPlugins = Collections.unmodifiableSet(this.removedPlugins);
/*  945 */       this.modifiedPlugins = Collections.unmodifiableSet(this.modifiedPlugins);
/*  946 */       this.addedExtensions = Collections.unmodifiableMap(this.addedExtensions);
/*  947 */       this.removedExtensions = Collections.unmodifiableMap(this.removedExtensions);
/*  948 */       this.modifiedExtensions = Collections.unmodifiableMap(this.modifiedExtensions);
/*      */     }
/*      */     
/*      */     protected void afterEventFire() {
/*  953 */       reset();
/*      */     }
/*      */     
/*      */     protected void dump() {
/*  957 */       Log logger = LogFactory.getLog(getClass());
/*  958 */       if (!logger.isDebugEnabled())
/*      */         return; 
/*  961 */       StringBuilder buf = new StringBuilder();
/*  962 */       buf.append("PLUG-IN REGISTRY CHANGES DUMP:\r\n").append("-------------- DUMP BEGIN -----------------\r\n").append("\tAdded plug-ins: " + this.addedPlugins.size() + "\r\n");
/*  966 */       for (String element : this.addedPlugins)
/*  968 */         buf.append("\t\t").append(element).append("\r\n"); 
/*  972 */       buf.append("\tRemoved plug-ins: " + this.removedPlugins.size() + "\r\n");
/*  974 */       for (String element : this.removedPlugins)
/*  976 */         buf.append("\t\t").append(element).append("\r\n"); 
/*  980 */       buf.append("\tModified plug-ins: " + this.modifiedPlugins.size() + "\r\n");
/*  982 */       for (String element : this.modifiedPlugins)
/*  984 */         buf.append("\t\t").append(element).append("\r\n"); 
/*  988 */       buf.append("\tAdded extensions: " + this.addedExtensions.size() + "\r\n");
/*  990 */       for (Map.Entry<String, String> element : this.addedExtensions.entrySet())
/*  992 */         buf.append("\t\t").append(element).append("\r\n"); 
/*  996 */       buf.append("\tRemoved extensions: " + this.removedExtensions.size() + "\r\n");
/*  998 */       for (Map.Entry<String, String> element : this.removedExtensions.entrySet())
/* 1000 */         buf.append("\t\t").append(element).append("\r\n"); 
/* 1004 */       buf.append("\tModified extensions: " + this.modifiedExtensions.size() + "\r\n");
/* 1006 */       for (Map.Entry<String, String> element : this.modifiedExtensions.entrySet())
/* 1008 */         buf.append("\t\t").append(element).append("\r\n"); 
/* 1012 */       buf.append("Memory TOTAL/FREE/MAX: ").append(Runtime.getRuntime().totalMemory()).append("/").append(Runtime.getRuntime().freeMemory()).append("/").append(Runtime.getRuntime().maxMemory()).append("\r\n");
/* 1019 */       buf.append("-------------- DUMP END -----------------\r\n");
/* 1020 */       logger.debug(buf.toString());
/*      */     }
/*      */     
/*      */     public Set<String> addedPlugins() {
/* 1027 */       return this.addedPlugins;
/*      */     }
/*      */     
/*      */     public Set<String> removedPlugins() {
/* 1035 */       return this.removedPlugins;
/*      */     }
/*      */     
/*      */     public Set<String> modifiedPlugins() {
/* 1043 */       return this.modifiedPlugins;
/*      */     }
/*      */     
/*      */     void putAddedExtension(String extensionUid, String extensionPointUid) {
/* 1048 */       this.addedExtensions.put(extensionUid, extensionPointUid);
/*      */     }
/*      */     
/*      */     public Set<String> addedExtensions() {
/* 1056 */       return this.addedExtensions.keySet();
/*      */     }
/*      */     
/*      */     public Set<String> addedExtensions(String extensionPointUid) {
/* 1064 */       Set<String> result = new HashSet<String>();
/* 1066 */       Iterator<Map.Entry<String, String>> it = this.addedExtensions.entrySet().iterator();
/* 1067 */       while (it.hasNext()) {
/* 1068 */         Map.Entry<String, String> entry = it.next();
/* 1069 */         if (((String)entry.getValue()).equals(extensionPointUid))
/* 1070 */           result.add(entry.getKey()); 
/*      */       } 
/* 1073 */       return Collections.unmodifiableSet(result);
/*      */     }
/*      */     
/*      */     void putRemovedExtension(String extensionUid, String extensionPointUid) {
/* 1078 */       this.removedExtensions.put(extensionUid, extensionPointUid);
/*      */     }
/*      */     
/*      */     public Set<String> removedExtensions() {
/* 1086 */       return this.removedExtensions.keySet();
/*      */     }
/*      */     
/*      */     public Set<String> removedExtensions(String extensionPointUid) {
/* 1094 */       Set<String> result = new HashSet<String>();
/* 1096 */       Iterator<Map.Entry<String, String>> it = this.removedExtensions.entrySet().iterator();
/* 1097 */       while (it.hasNext()) {
/* 1098 */         Map.Entry<String, String> entry = it.next();
/* 1099 */         if (((String)entry.getValue()).equals(extensionPointUid))
/* 1100 */           result.add(entry.getKey()); 
/*      */       } 
/* 1103 */       return Collections.unmodifiableSet(result);
/*      */     }
/*      */     
/*      */     void putModifiedExtension(String extensionUid, String extensionPointUid) {
/* 1108 */       this.modifiedExtensions.put(extensionUid, extensionPointUid);
/*      */     }
/*      */     
/*      */     public Set<String> modifiedExtensions() {
/* 1116 */       return this.modifiedExtensions.keySet();
/*      */     }
/*      */     
/*      */     public Set<String> modifiedExtensions(String extensionPointUid) {
/* 1124 */       Set<String> result = new HashSet<String>();
/* 1126 */       Iterator<Map.Entry<String, String>> it = this.modifiedExtensions.entrySet().iterator();
/* 1127 */       while (it.hasNext()) {
/* 1128 */         Map.Entry<String, String> entry = it.next();
/* 1129 */         if (((String)entry.getValue()).equals(extensionPointUid))
/* 1130 */           result.add(entry.getKey()); 
/*      */       } 
/* 1133 */       return Collections.unmodifiableSet(result);
/*      */     }
/*      */   }
/*      */   
/*      */   private static final class ManifestInfoImpl implements ManifestInfo {
/*      */     private final ModelManifestInfo model;
/*      */     
/*      */     ManifestInfoImpl(ModelManifestInfo aModel) {
/* 1141 */       this.model = aModel;
/*      */     }
/*      */     
/*      */     public String getId() {
/* 1148 */       return this.model.getId();
/*      */     }
/*      */     
/*      */     public Version getVersion() {
/* 1155 */       return this.model.getVersion();
/*      */     }
/*      */     
/*      */     public String getVendor() {
/* 1162 */       return this.model.getVendor();
/*      */     }
/*      */     
/*      */     public String getPluginId() {
/* 1169 */       return this.model.getPluginId();
/*      */     }
/*      */     
/*      */     public Version getPluginVersion() {
/* 1176 */       return this.model.getPluginVersion();
/*      */     }
/*      */     
/*      */     public MatchingRule getMatchingRule() {
/* 1183 */       return this.model.getMatchRule();
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\PluginRegistryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */