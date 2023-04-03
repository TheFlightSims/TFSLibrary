/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.java.plugin.registry.Extension;
/*     */ import org.java.plugin.registry.ExtensionMultiplicity;
/*     */ import org.java.plugin.registry.ExtensionPoint;
/*     */ import org.java.plugin.registry.Identity;
/*     */ import org.java.plugin.registry.IntegrityCheckReport;
/*     */ import org.java.plugin.registry.ManifestProcessingException;
/*     */ import org.java.plugin.registry.ParameterMultiplicity;
/*     */ import org.java.plugin.registry.ParameterType;
/*     */ import org.java.plugin.registry.PluginDescriptor;
/*     */ import org.java.plugin.registry.PluginRegistry;
/*     */ 
/*     */ class ExtensionPointImpl extends PluginElementImpl<ExtensionPoint> implements ExtensionPoint {
/*     */   private final ModelExtensionPoint model;
/*     */   
/*     */   private Map<String, Extension> connectedExtensions;
/*     */   
/*     */   private Map<String, Extension> availableExtensions;
/*     */   
/*     */   private List<ExtensionPoint.ParameterDefinition> parameterDefinitions;
/*     */   
/*     */   private Boolean isValid;
/*     */   
/*     */   private boolean paramDefsMerged = false;
/*     */   
/*     */   private List<ExtensionPoint> descendants;
/*     */   
/*     */   ExtensionPointImpl(PluginDescriptorImpl descr, PluginFragmentImpl aFragment, ModelExtensionPoint aModel) throws ManifestProcessingException {
/*  59 */     super(descr, aFragment, aModel.getId(), aModel.getDocumentation());
/*  60 */     this.model = aModel;
/*  61 */     if (this.model.getParentPointId() != null && this.model.getParentPluginId() == null) {
/*  63 */       this.log.warn("parent plug-in ID not specified together with parent extension point ID, using declaring plug-in ID, extension point is " + getUniqueId());
/*  66 */       this.model.setParentPluginId(descr.getId());
/*     */     } 
/*  68 */     this.parameterDefinitions = new ArrayList<ExtensionPoint.ParameterDefinition>(this.model.getParamDefs().size());
/*  69 */     Set<String> names = new HashSet<String>();
/*  71 */     for (ModelParameterDef modelParameterDef : this.model.getParamDefs()) {
/*  72 */       ParameterDefinitionImpl def = new ParameterDefinitionImpl(null, modelParameterDef);
/*  73 */       if (names.contains(def.getId()))
/*  74 */         throw new ManifestProcessingException("org.java.plugin.registry.xml", "duplicateParameterDefinition", new Object[] { def.getId(), getId(), descr.getId() }); 
/*  79 */       names.add(def.getId());
/*  80 */       this.parameterDefinitions.add(def);
/*     */     } 
/*  82 */     this.parameterDefinitions = Collections.unmodifiableList(this.parameterDefinitions);
/*  84 */     if (this.log.isDebugEnabled())
/*  85 */       this.log.debug("object instantiated: " + this); 
/*     */   }
/*     */   
/*     */   public String getUniqueId() {
/*  93 */     return getDeclaringPluginDescriptor().getRegistry().makeUniqueId(getDeclaringPluginDescriptor().getId(), getId());
/*     */   }
/*     */   
/*     */   public ExtensionMultiplicity getMultiplicity() {
/* 101 */     return this.model.getExtensionMultiplicity();
/*     */   }
/*     */   
/*     */   private void updateExtensionsLists() {
/* 105 */     this.connectedExtensions = new HashMap<String, Extension>();
/* 106 */     this.availableExtensions = new HashMap<String, Extension>();
/* 107 */     for (PluginDescriptor descr : getDeclaringPluginDescriptor().getRegistry().getPluginDescriptors()) {
/* 109 */       for (Extension ext : descr.getExtensions()) {
/* 110 */         if (getDeclaringPluginDescriptor().getId().equals(ext.getExtendedPluginId()) && getId().equals(ext.getExtendedPointId())) {
/* 113 */           this.availableExtensions.put(ext.getUniqueId(), ext);
/* 114 */           if (ext.isValid()) {
/* 115 */             if (this.log.isDebugEnabled())
/* 116 */               this.log.debug("extension " + ext + " connected to point " + this); 
/* 119 */             this.connectedExtensions.put(ext.getUniqueId(), ext);
/*     */             continue;
/*     */           } 
/* 121 */           this.log.warn("extension " + ext.getUniqueId() + " is invalid and doesn't connected to" + " extension point " + getUniqueId());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Collection<Extension> getAvailableExtensions() {
/* 134 */     if (this.availableExtensions == null)
/* 135 */       updateExtensionsLists(); 
/* 137 */     return Collections.unmodifiableCollection(this.availableExtensions.values());
/*     */   }
/*     */   
/*     */   public Extension getAvailableExtension(String uniqueId) {
/* 145 */     if (this.availableExtensions == null)
/* 146 */       updateExtensionsLists(); 
/* 148 */     Extension result = this.availableExtensions.get(uniqueId);
/* 149 */     if (result == null)
/* 150 */       throw new IllegalArgumentException("extension " + uniqueId + " not available in point " + getUniqueId()); 
/* 153 */     return result;
/*     */   }
/*     */   
/*     */   public boolean isExtensionAvailable(String uniqueId) {
/* 161 */     if (this.availableExtensions == null)
/* 162 */       updateExtensionsLists(); 
/* 164 */     return this.availableExtensions.containsKey(uniqueId);
/*     */   }
/*     */   
/*     */   public Collection<Extension> getConnectedExtensions() {
/* 171 */     if (this.connectedExtensions == null)
/* 172 */       updateExtensionsLists(); 
/* 174 */     return Collections.unmodifiableCollection(this.connectedExtensions.values());
/*     */   }
/*     */   
/*     */   public Extension getConnectedExtension(String uniqueId) {
/* 182 */     if (this.connectedExtensions == null)
/* 183 */       updateExtensionsLists(); 
/* 185 */     Extension result = this.connectedExtensions.get(uniqueId);
/* 186 */     if (result == null)
/* 187 */       throw new IllegalArgumentException("extension " + uniqueId + " not connected to point " + getUniqueId()); 
/* 190 */     return result;
/*     */   }
/*     */   
/*     */   public boolean isExtensionConnected(String uniqueId) {
/* 198 */     if (this.connectedExtensions == null)
/* 199 */       updateExtensionsLists(); 
/* 201 */     return this.connectedExtensions.containsKey(uniqueId);
/*     */   }
/*     */   
/*     */   public boolean isValid() {
/* 208 */     if (this.isValid == null)
/* 209 */       validate(); 
/* 211 */     return this.isValid.booleanValue();
/*     */   }
/*     */   
/*     */   Collection<IntegrityCheckReport.ReportItem> validate() {
/*     */     Set<String> foundPlugins;
/* 215 */     if (this.model.getParentPluginId() != null && this.model.getParentPointId() != null)
/*     */       try {
/* 218 */         if (!isExtensionPointAvailable(this.model.getParentPluginId(), this.model.getParentPointId())) {
/* 220 */           this.isValid = Boolean.FALSE;
/* 221 */           return Collections.singletonList(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, this, IntegrityCheckReport.Error.INVALID_EXTENSION_POINT, "parentExtPointNotAvailable", new Object[] { getDeclaringPluginDescriptor().getRegistry().makeUniqueId(this.model.getParentPluginId(), this.model.getParentPointId()), getUniqueId() }));
/*     */         } 
/* 233 */       } catch (Throwable t) {
/* 234 */         this.isValid = Boolean.FALSE;
/* 235 */         if (this.log.isDebugEnabled())
/* 236 */           this.log.debug("failed checking availability of extension point " + getDeclaringPluginDescriptor().getRegistry().makeUniqueId(this.model.getParentPluginId(), this.model.getParentPointId()), t); 
/* 241 */         return Collections.singletonList(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, this, IntegrityCheckReport.Error.INVALID_EXTENSION_POINT, "parentExtPointAvailabilityCheckFailed", new Object[] { getDeclaringPluginDescriptor().getRegistry().makeUniqueId(this.model.getParentPluginId(), this.model.getParentPointId()), getUniqueId(), t }));
/*     */       }  
/* 254 */     switch (getMultiplicity()) {
/*     */       case ANY:
/* 256 */         this.isValid = Boolean.TRUE;
/* 257 */         return Collections.emptyList();
/*     */       case ONE:
/* 259 */         this.isValid = Boolean.valueOf((getAvailableExtensions().size() == 1));
/* 260 */         if (!this.isValid.booleanValue())
/* 261 */           return Collections.singletonList(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, this, IntegrityCheckReport.Error.INVALID_EXTENSION_POINT, "toManyOrFewExtsConnected", getUniqueId())); 
/*     */         break;
/*     */       case NONE:
/* 269 */         this.isValid = Boolean.valueOf((getAvailableExtensions().size() == 0));
/* 270 */         if (!this.isValid.booleanValue())
/* 271 */           return Collections.singletonList(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, this, IntegrityCheckReport.Error.INVALID_EXTENSION_POINT, "extsConnectedToAbstractExtPoint", getUniqueId())); 
/*     */         break;
/*     */       case ONE_PER_PLUGIN:
/* 279 */         this.isValid = Boolean.TRUE;
/* 280 */         foundPlugins = new HashSet<String>();
/* 282 */         for (Extension extension : getAvailableExtensions()) {
/* 283 */           String pluginId = extension.getDeclaringPluginDescriptor().getId();
/* 284 */           if (!foundPlugins.add(pluginId)) {
/* 285 */             this.isValid = Boolean.FALSE;
/* 286 */             return Collections.singletonList(new IntegrityChecker.ReportItemImpl(IntegrityCheckReport.Severity.ERROR, this, IntegrityCheckReport.Error.INVALID_EXTENSION_POINT, "toManyExtsConnected", getUniqueId()));
/*     */           } 
/*     */         } 
/*     */         break;
/*     */     } 
/* 295 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   private boolean isExtensionPointAvailable(String pluginId, String pointId) {
/* 300 */     PluginRegistry registry = getDeclaringPluginDescriptor().getRegistry();
/* 301 */     if (!registry.isPluginDescriptorAvailable(pluginId))
/* 302 */       return false; 
/* 304 */     for (ExtensionPoint extensionPoint : registry.getPluginDescriptor(pluginId).getExtensionPoints()) {
/* 306 */       if (extensionPoint.getId().equals(pointId))
/* 307 */         return true; 
/*     */     } 
/* 310 */     return false;
/*     */   }
/*     */   
/*     */   public Collection<ExtensionPoint.ParameterDefinition> getParameterDefinitions() {
/* 317 */     if (this.model.getParentPluginId() == null || this.model.getParentPointId() == null || this.paramDefsMerged)
/* 319 */       return this.parameterDefinitions; 
/* 321 */     Set<String> names = new HashSet<String>();
/* 322 */     Collection<ExtensionPoint.ParameterDefinition> parentParamDefs = getDeclaringPluginDescriptor().getRegistry().getExtensionPoint(this.model.getParentPluginId(), this.model.getParentPointId()).getParameterDefinitions();
/* 326 */     List<ExtensionPoint.ParameterDefinition> newParamDefs = new ArrayList<ExtensionPoint.ParameterDefinition>(this.parameterDefinitions.size() + parentParamDefs.size());
/* 328 */     for (ExtensionPoint.ParameterDefinition def : this.parameterDefinitions) {
/* 329 */       names.add(def.getId());
/* 330 */       newParamDefs.add(def);
/*     */     } 
/* 332 */     for (ExtensionPoint.ParameterDefinition def : parentParamDefs) {
/* 333 */       if (names.contains(def.getId()))
/*     */         continue; 
/* 335 */       newParamDefs.add(def);
/*     */     } 
/* 337 */     this.paramDefsMerged = true;
/* 338 */     this.parameterDefinitions = Collections.unmodifiableList(newParamDefs);
/* 339 */     return this.parameterDefinitions;
/*     */   }
/*     */   
/*     */   public ExtensionPoint.ParameterDefinition getParameterDefinition(String id) {
/* 347 */     for (ExtensionPoint.ParameterDefinition parameterDefinition : getParameterDefinitions()) {
/* 348 */       ParameterDefinitionImpl def = (ParameterDefinitionImpl)parameterDefinition;
/* 349 */       if (def.getId().equals(id))
/* 350 */         return def; 
/*     */     } 
/* 353 */     throw new IllegalArgumentException("parameter definition with ID " + id + " not found in extension point " + getUniqueId() + " and all it parents");
/*     */   }
/*     */   
/*     */   public String getParentPluginId() {
/* 362 */     return this.model.getParentPluginId();
/*     */   }
/*     */   
/*     */   public String getParentExtensionPointId() {
/* 369 */     return this.model.getParentPointId();
/*     */   }
/*     */   
/*     */   public boolean isSuccessorOf(ExtensionPoint extensionPoint) {
/* 377 */     if (this.model.getParentPluginId() == null || this.model.getParentPointId() == null)
/* 379 */       return false; 
/* 381 */     if (this.model.getParentPluginId().equals(extensionPoint.getDeclaringPluginDescriptor().getId()) && this.model.getParentPointId().equals(extensionPoint.getId()))
/* 384 */       return true; 
/*     */     try {
/* 387 */       return getDeclaringPluginDescriptor().getRegistry().getExtensionPoint(this.model.getParentPluginId(), this.model.getParentPointId()).isSuccessorOf(extensionPoint);
/* 390 */     } catch (IllegalArgumentException iae) {
/* 391 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void collectDescendants() {
/* 396 */     this.descendants = new LinkedList<ExtensionPoint>();
/* 397 */     for (PluginDescriptor descr : getDeclaringPluginDescriptor().getRegistry().getPluginDescriptors()) {
/* 399 */       for (ExtensionPoint extp : descr.getExtensionPoints()) {
/* 400 */         if (extp.isSuccessorOf(this)) {
/* 401 */           if (this.log.isDebugEnabled())
/* 402 */             this.log.debug("extension point " + extp + " is descendant of point " + this); 
/* 405 */           this.descendants.add(extp);
/*     */         } 
/*     */       } 
/*     */     } 
/* 409 */     this.descendants = Collections.unmodifiableList(this.descendants);
/*     */   }
/*     */   
/*     */   public Collection<ExtensionPoint> getDescendants() {
/* 416 */     if (this.descendants == null)
/* 417 */       collectDescendants(); 
/* 419 */     return this.descendants;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 427 */     return "{ExtensionPoint: uid=" + getUniqueId() + "}";
/*     */   }
/*     */   
/*     */   void registryChanged() {
/* 431 */     this.isValid = null;
/* 432 */     this.connectedExtensions = null;
/* 433 */     this.availableExtensions = null;
/* 434 */     this.descendants = null;
/*     */   }
/*     */   
/*     */   class ParameterDefinitionImpl extends PluginElementImpl<ExtensionPoint.ParameterDefinition> implements ExtensionPoint.ParameterDefinition {
/*     */     private List<ExtensionPoint.ParameterDefinition> subDefinitions;
/*     */     
/*     */     private final ParameterDefinitionImpl superDefinition;
/*     */     
/*     */     private final ModelParameterDef modelParamDef;
/*     */     
/*     */     private final ParameterValueParser valueParser;
/*     */     
/*     */     ParameterDefinitionImpl(ParameterDefinitionImpl aSuperDefinition, ModelParameterDef aModel) throws ManifestProcessingException {
/* 447 */       super(ExtensionPointImpl.this.getDeclaringPluginDescriptor(), ExtensionPointImpl.this.getDeclaringPluginFragment(), aModel.getId(), aModel.getDocumentation());
/* 450 */       this.superDefinition = aSuperDefinition;
/* 451 */       this.modelParamDef = aModel;
/* 452 */       this.valueParser = new ParameterValueParser(getDeclaringPluginDescriptor().getRegistry(), this, this.modelParamDef.getDefaultValue());
/* 455 */       if (!this.valueParser.isParsingSucceeds()) {
/* 456 */         this.log.warn("parsing default value for parameter definition " + this + " failed, message is: " + this.valueParser.getParsingMessage());
/* 459 */         throw new ManifestProcessingException("org.java.plugin.registry.xml", "invalidDefaultValueAttribute", new Object[] { this.modelParamDef.getDefaultValue(), this$0.getId(), this$0.getDeclaringPluginDescriptor().getId() });
/*     */       } 
/* 467 */       if (ParameterType.ANY == this.modelParamDef.getType()) {
/* 468 */         this.subDefinitions = Collections.emptyList();
/*     */       } else {
/* 470 */         this.subDefinitions = new ArrayList<ExtensionPoint.ParameterDefinition>(this.modelParamDef.getParamDefs().size());
/* 472 */         Set<String> names = new HashSet<String>();
/* 473 */         for (ModelParameterDef modelParameterDef : this.modelParamDef.getParamDefs()) {
/* 474 */           ParameterDefinitionImpl def = new ParameterDefinitionImpl(this, modelParameterDef);
/* 475 */           if (names.contains(def.getId()))
/* 476 */             throw new ManifestProcessingException("org.java.plugin.registry.xml", "duplicateParameterDefinition", new Object[] { def.getId(), this$0.getId(), this$0.getDeclaringPluginDescriptor().getId() }); 
/* 484 */           names.add(def.getId());
/* 485 */           this.subDefinitions.add(def);
/*     */         } 
/* 487 */         this.subDefinitions = Collections.unmodifiableList(this.subDefinitions);
/*     */       } 
/* 489 */       if (this.log.isDebugEnabled())
/* 490 */         this.log.debug("object instantiated: " + this); 
/*     */     }
/*     */     
/*     */     ParameterValueParser getValueParser() {
/* 495 */       return this.valueParser;
/*     */     }
/*     */     
/*     */     public ExtensionPoint getDeclaringExtensionPoint() {
/* 503 */       return ExtensionPointImpl.this;
/*     */     }
/*     */     
/*     */     public ParameterMultiplicity getMultiplicity() {
/* 511 */       return this.modelParamDef.getMultiplicity();
/*     */     }
/*     */     
/*     */     public Collection<ExtensionPoint.ParameterDefinition> getSubDefinitions() {
/* 519 */       return this.subDefinitions;
/*     */     }
/*     */     
/*     */     public ExtensionPoint.ParameterDefinition getSuperDefinition() {
/* 527 */       return this.superDefinition;
/*     */     }
/*     */     
/*     */     public ExtensionPoint.ParameterDefinition getSubDefinition(String id) {
/* 535 */       for (ExtensionPoint.ParameterDefinition parameterDefinition : this.subDefinitions) {
/* 536 */         ParameterDefinitionImpl def = (ParameterDefinitionImpl)parameterDefinition;
/* 537 */         if (def.getId().equals(id))
/* 538 */           return def; 
/*     */       } 
/* 541 */       throw new IllegalArgumentException("parameter definition with ID " + id + " not found in extension point " + ExtensionPointImpl.this.getUniqueId());
/*     */     }
/*     */     
/*     */     public ParameterType getType() {
/* 550 */       return this.modelParamDef.getType();
/*     */     }
/*     */     
/*     */     public String getCustomData() {
/* 558 */       return this.modelParamDef.getCustomData();
/*     */     }
/*     */     
/*     */     public String getDefaultValue() {
/* 566 */       return this.modelParamDef.getDefaultValue();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 574 */       return "{PluginExtensionPoint.ParameterDefinition: extPointUid=" + getDeclaringExtensionPoint().getUniqueId() + "; id=" + getId() + "}";
/*     */     }
/*     */     
/*     */     protected boolean isEqualTo(Identity idt) {
/* 585 */       if (!super.isEqualTo(idt))
/* 586 */         return false; 
/* 588 */       ParameterDefinitionImpl other = (ParameterDefinitionImpl)idt;
/* 589 */       if (getSuperDefinition() == null && other.getSuperDefinition() == null)
/* 591 */         return true; 
/* 593 */       if (getSuperDefinition() == null || other.getSuperDefinition() == null)
/* 595 */         return false; 
/* 597 */       return getSuperDefinition().equals(other.getSuperDefinition());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ExtensionPointImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */