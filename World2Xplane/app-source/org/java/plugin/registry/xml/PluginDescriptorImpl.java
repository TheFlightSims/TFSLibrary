/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.java.plugin.registry.Documentation;
/*     */ import org.java.plugin.registry.Extension;
/*     */ import org.java.plugin.registry.ExtensionPoint;
/*     */ import org.java.plugin.registry.Identity;
/*     */ import org.java.plugin.registry.Library;
/*     */ import org.java.plugin.registry.ManifestProcessingException;
/*     */ import org.java.plugin.registry.PluginAttribute;
/*     */ import org.java.plugin.registry.PluginDescriptor;
/*     */ import org.java.plugin.registry.PluginFragment;
/*     */ import org.java.plugin.registry.PluginPrerequisite;
/*     */ import org.java.plugin.registry.PluginRegistry;
/*     */ import org.java.plugin.registry.Version;
/*     */ 
/*     */ class PluginDescriptorImpl extends IdentityImpl implements PluginDescriptor {
/*     */   private final PluginRegistry registry;
/*     */   
/*     */   private final ModelPluginDescriptor model;
/*     */   
/*     */   private Map<String, PluginPrerequisite> pluginPrerequisites;
/*     */   
/*     */   private Map<String, Library> libraries;
/*     */   
/*     */   private Map<String, ExtensionPoint> extensionPoints;
/*     */   
/*     */   private Map<String, Extension> extensions;
/*     */   
/*     */   private Documentation<PluginDescriptor> doc;
/*     */   
/*     */   private List<PluginFragment> fragments;
/*     */   
/*     */   private List<PluginAttribute> attributes;
/*     */   
/*     */   PluginDescriptorImpl(PluginRegistry aRegistry, ModelPluginDescriptor aModel) throws ManifestProcessingException {
/*  61 */     super(aModel.getId());
/*  62 */     this.registry = aRegistry;
/*  63 */     this.model = aModel;
/*  64 */     if (this.model.getVendor() == null)
/*  65 */       this.model.setVendor(""); 
/*  67 */     if (this.model.getClassName() != null && this.model.getClassName().trim().length() == 0)
/*  69 */       this.model.setClassName((String)null); 
/*  71 */     if (this.model.getDocsPath() == null || this.model.getDocsPath().trim().length() == 0)
/*  73 */       this.model.setDocsPath("docs"); 
/*  75 */     if (this.model.getDocumentation() != null)
/*  76 */       this.doc = new DocumentationImpl<PluginDescriptor>(this, this.model.getDocumentation()); 
/*  80 */     this.attributes = new LinkedList<PluginAttribute>();
/*  81 */     this.fragments = new LinkedList<PluginFragment>();
/*  82 */     this.pluginPrerequisites = new HashMap<String, PluginPrerequisite>();
/*  83 */     this.libraries = new HashMap<String, Library>();
/*  84 */     this.extensionPoints = new HashMap<String, ExtensionPoint>();
/*  85 */     this.extensions = new HashMap<String, Extension>();
/*  87 */     processAttributes((PluginFragmentImpl)null, this.model);
/*  88 */     processPrerequisites((PluginFragmentImpl)null, this.model);
/*  89 */     processLibraries((PluginFragmentImpl)null, this.model);
/*  90 */     processExtensionPoints((PluginFragmentImpl)null, this.model);
/*  91 */     processExtensions((PluginFragmentImpl)null, this.model);
/*  93 */     if (this.log.isDebugEnabled())
/*  94 */       this.log.debug("object instantiated: " + this); 
/*     */   }
/*     */   
/*     */   void registerFragment(PluginFragmentImpl fragment) throws ManifestProcessingException {
/* 100 */     this.fragments.add(fragment);
/* 101 */     processAttributes(fragment, fragment.getModel());
/* 102 */     processPrerequisites(fragment, fragment.getModel());
/* 103 */     processLibraries(fragment, fragment.getModel());
/* 104 */     processExtensionPoints(fragment, fragment.getModel());
/* 105 */     processExtensions(fragment, fragment.getModel());
/*     */   }
/*     */   
/*     */   void unregisterFragment(PluginFragmentImpl fragment) {
/* 110 */     for (Iterator<PluginAttribute> iterator3 = this.attributes.iterator(); iterator3.hasNext();) {
/* 111 */       if (fragment.equals(((PluginAttribute)iterator3.next()).getDeclaringPluginFragment()))
/* 113 */         iterator3.remove(); 
/*     */     } 
/* 117 */     Iterator<Map.Entry<String, PluginPrerequisite>> iterator2 = this.pluginPrerequisites.entrySet().iterator();
/* 118 */     while (iterator2.hasNext()) {
/* 119 */       Map.Entry<String, PluginPrerequisite> entry = iterator2.next();
/* 120 */       if (fragment.equals(((PluginPrerequisite)entry.getValue()).getDeclaringPluginFragment()))
/* 122 */         iterator2.remove(); 
/*     */     } 
/* 126 */     Iterator<Map.Entry<String, Library>> iterator1 = this.libraries.entrySet().iterator();
/* 127 */     while (iterator1.hasNext()) {
/* 128 */       Map.Entry<String, Library> entry = iterator1.next();
/* 129 */       if (fragment.equals(((Library)entry.getValue()).getDeclaringPluginFragment()))
/* 131 */         iterator1.remove(); 
/*     */     } 
/* 135 */     Iterator<Map.Entry<String, ExtensionPoint>> iterator = this.extensionPoints.entrySet().iterator();
/* 136 */     while (iterator.hasNext()) {
/* 137 */       Map.Entry<String, ExtensionPoint> entry = iterator.next();
/* 138 */       if (fragment.equals(((ExtensionPoint)entry.getValue()).getDeclaringPluginFragment()))
/* 140 */         iterator.remove(); 
/*     */     } 
/* 144 */     Iterator<Map.Entry<String, Extension>> it = this.extensions.entrySet().iterator();
/* 145 */     while (it.hasNext()) {
/* 146 */       Map.Entry<String, Extension> entry = it.next();
/* 147 */       if (fragment.equals(((Extension)entry.getValue()).getDeclaringPluginFragment()))
/* 149 */         it.remove(); 
/*     */     } 
/* 152 */     this.fragments.remove(fragment);
/*     */   }
/*     */   
/*     */   private void processAttributes(PluginFragmentImpl fragment, ModelPluginManifest modelManifest) throws ManifestProcessingException {
/* 158 */     for (ModelAttribute modelAttribute : modelManifest.getAttributes())
/* 159 */       this.attributes.add(new PluginAttributeImpl(this, fragment, modelAttribute, null)); 
/*     */   }
/*     */   
/*     */   private void processPrerequisites(PluginFragmentImpl fragment, ModelPluginManifest modelManifest) throws ManifestProcessingException {
/* 166 */     for (ModelPrerequisite modelPrerequisite : modelManifest.getPrerequisites()) {
/* 167 */       PluginPrerequisiteImpl pluginPrerequisite = new PluginPrerequisiteImpl(this, fragment, modelPrerequisite);
/* 169 */       if (this.pluginPrerequisites.containsKey(pluginPrerequisite.getPluginId()))
/* 171 */         throw new ManifestProcessingException("org.java.plugin.registry.xml", "duplicateImports", new Object[] { pluginPrerequisite.getPluginId(), getId() }); 
/* 176 */       this.pluginPrerequisites.put(pluginPrerequisite.getPluginId(), pluginPrerequisite);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void processLibraries(PluginFragmentImpl fragment, ModelPluginManifest modelManifest) throws ManifestProcessingException {
/* 184 */     for (ModelLibrary modelLibrary : modelManifest.getLibraries()) {
/* 185 */       LibraryImpl lib = new LibraryImpl(this, fragment, modelLibrary);
/* 186 */       if (this.libraries.containsKey(lib.getId()))
/* 187 */         throw new ManifestProcessingException("org.java.plugin.registry.xml", "duplicateLibraries", new Object[] { lib.getId(), getId() }); 
/* 192 */       this.libraries.put(lib.getId(), lib);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void processExtensionPoints(PluginFragmentImpl fragment, ModelPluginManifest modelManifest) throws ManifestProcessingException {
/* 199 */     for (ModelExtensionPoint modelExtensionPoint : modelManifest.getExtensionPoints()) {
/* 200 */       ExtensionPointImpl extensionPoint = new ExtensionPointImpl(this, fragment, modelExtensionPoint);
/* 202 */       if (this.extensionPoints.containsKey(extensionPoint.getId()))
/* 203 */         throw new ManifestProcessingException("org.java.plugin.registry.xml", "duplicateExtensionPoints", new Object[] { extensionPoint.getId(), getId() }); 
/* 208 */       this.extensionPoints.put(extensionPoint.getId(), extensionPoint);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void processExtensions(PluginFragmentImpl fragment, ModelPluginManifest modelManifest) throws ManifestProcessingException {
/* 215 */     for (ModelExtension modelExtension : modelManifest.getExtensions()) {
/* 216 */       ExtensionImpl extension = new ExtensionImpl(this, fragment, modelExtension);
/* 217 */       if (this.extensions.containsKey(extension.getId()))
/* 218 */         throw new ManifestProcessingException("org.java.plugin.registry.xml", "duplicateExtensions", new Object[] { extension.getId(), getId() }); 
/* 223 */       if (!getId().equals(extension.getExtendedPluginId()) && !this.pluginPrerequisites.containsKey(extension.getExtendedPluginId()))
/* 226 */         throw new ManifestProcessingException("org.java.plugin.registry.xml", "pluginNotDeclaredInPrerequisites", new Object[] { extension.getExtendedPluginId(), extension.getId(), getId() }); 
/* 232 */       this.extensions.put(extension.getId(), extension);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getUniqueId() {
/* 241 */     return this.registry.makeUniqueId(getId(), this.model.getVersion());
/*     */   }
/*     */   
/*     */   public String getVendor() {
/* 248 */     return this.model.getVendor();
/*     */   }
/*     */   
/*     */   public Version getVersion() {
/* 255 */     return this.model.getVersion();
/*     */   }
/*     */   
/*     */   public Collection<PluginPrerequisite> getPrerequisites() {
/* 262 */     return Collections.unmodifiableCollection(this.pluginPrerequisites.values());
/*     */   }
/*     */   
/*     */   public PluginPrerequisite getPrerequisite(String id) {
/* 269 */     return this.pluginPrerequisites.get(id);
/*     */   }
/*     */   
/*     */   public Collection<ExtensionPoint> getExtensionPoints() {
/* 276 */     return Collections.unmodifiableCollection(this.extensionPoints.values());
/*     */   }
/*     */   
/*     */   public ExtensionPoint getExtensionPoint(String id) {
/* 283 */     return this.extensionPoints.get(id);
/*     */   }
/*     */   
/*     */   public Collection<Extension> getExtensions() {
/* 290 */     return Collections.unmodifiableCollection(this.extensions.values());
/*     */   }
/*     */   
/*     */   public Extension getExtension(String id) {
/* 297 */     return this.extensions.get(id);
/*     */   }
/*     */   
/*     */   public Collection<Library> getLibraries() {
/* 304 */     return Collections.unmodifiableCollection(this.libraries.values());
/*     */   }
/*     */   
/*     */   public Library getLibrary(String id) {
/* 311 */     return this.libraries.get(id);
/*     */   }
/*     */   
/*     */   public PluginRegistry getRegistry() {
/* 318 */     return this.registry;
/*     */   }
/*     */   
/*     */   public String getPluginClassName() {
/* 325 */     return this.model.getClassName();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 333 */     return "{PluginDescriptor: uid=" + getUniqueId() + "}";
/*     */   }
/*     */   
/*     */   public Documentation<PluginDescriptor> getDocumentation() {
/* 340 */     return this.doc;
/*     */   }
/*     */   
/*     */   public Collection<PluginFragment> getFragments() {
/* 347 */     return Collections.unmodifiableCollection(this.fragments);
/*     */   }
/*     */   
/*     */   public PluginAttribute getAttribute(String id) {
/* 354 */     PluginAttributeImpl result = null;
/* 355 */     for (PluginAttribute attribute : this.attributes) {
/* 356 */       PluginAttributeImpl attr = (PluginAttributeImpl)attribute;
/* 357 */       if (attr.getId().equals(id)) {
/* 358 */         if (result == null) {
/* 359 */           result = attr;
/*     */           continue;
/*     */         } 
/* 361 */         throw new IllegalArgumentException("more than one attribute with ID " + id + " defined in plug-in " + getUniqueId());
/*     */       } 
/*     */     } 
/* 367 */     return result;
/*     */   }
/*     */   
/*     */   public Collection<PluginAttribute> getAttributes() {
/* 374 */     return Collections.unmodifiableCollection(this.attributes);
/*     */   }
/*     */   
/*     */   public Collection<PluginAttribute> getAttributes(String id) {
/* 381 */     List<PluginAttribute> result = new LinkedList<PluginAttribute>();
/* 382 */     for (PluginAttribute attribute : this.attributes) {
/* 383 */       PluginAttributeImpl param = (PluginAttributeImpl)attribute;
/* 384 */       if (param.getId().equals(id))
/* 385 */         result.add(param); 
/*     */     } 
/* 388 */     return Collections.unmodifiableList(result);
/*     */   }
/*     */   
/*     */   public String getDocsPath() {
/* 395 */     return this.model.getDocsPath();
/*     */   }
/*     */   
/*     */   public URL getLocation() {
/* 402 */     return this.model.getLocation();
/*     */   }
/*     */   
/*     */   protected boolean isEqualTo(Identity idt) {
/* 411 */     if (!(idt instanceof PluginDescriptorImpl))
/* 412 */       return false; 
/* 414 */     PluginDescriptorImpl other = (PluginDescriptorImpl)idt;
/* 415 */     return (getUniqueId().equals(other.getUniqueId()) && getLocation().toExternalForm().equals(other.getLocation().toExternalForm()));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\PluginDescriptorImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */