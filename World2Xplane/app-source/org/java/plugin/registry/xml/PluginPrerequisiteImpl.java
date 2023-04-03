/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.java.plugin.registry.Documentation;
/*     */ import org.java.plugin.registry.ManifestProcessingException;
/*     */ import org.java.plugin.registry.MatchingRule;
/*     */ import org.java.plugin.registry.PluginDescriptor;
/*     */ import org.java.plugin.registry.PluginFragment;
/*     */ import org.java.plugin.registry.PluginPrerequisite;
/*     */ import org.java.plugin.registry.Version;
/*     */ 
/*     */ class PluginPrerequisiteImpl implements PluginPrerequisite {
/*  35 */   private static Log log = LogFactory.getLog(PluginPrerequisiteImpl.class);
/*     */   
/*     */   private final PluginDescriptorImpl descriptor;
/*     */   
/*     */   private final PluginFragmentImpl fragment;
/*     */   
/*     */   private final ModelPrerequisite model;
/*     */   
/*     */   private DocumentationImpl<PluginPrerequisite> doc;
/*     */   
/*     */   static boolean matches(Version source, Version target, MatchingRule match) {
/*  39 */     if (source == null)
/*  40 */       return true; 
/*  42 */     switch (match) {
/*     */       case EQUAL:
/*  43 */         return target.equals(source);
/*     */       case EQUIVALENT:
/*  44 */         return target.isEquivalentTo(source);
/*     */       case COMPATIBLE:
/*  45 */         return target.isCompatibleWith(source);
/*     */       case GREATER_OR_EQUAL:
/*  46 */         return target.isGreaterOrEqualTo(source);
/*     */     } 
/*  48 */     return target.isCompatibleWith(source);
/*     */   }
/*     */   
/*     */   PluginPrerequisiteImpl(PluginDescriptorImpl descr, PluginFragmentImpl aFragment, ModelPrerequisite aModel) throws ManifestProcessingException {
/*  60 */     this.descriptor = descr;
/*  61 */     this.fragment = aFragment;
/*  62 */     this.model = aModel;
/*  63 */     if (this.model.getPluginId() == null || this.model.getPluginId().trim().length() == 0)
/*  65 */       throw new ManifestProcessingException("org.java.plugin.registry.xml", "prerequisitePliginIdIsBlank", descr.getId()); 
/*  69 */     if (descr.getId().equals(this.model.getPluginId()))
/*  70 */       throw new ManifestProcessingException("org.java.plugin.registry.xml", "invalidPrerequisitePluginId", descr.getId()); 
/*  74 */     if (this.model.getId() == null || this.model.getId().trim().length() == 0)
/*  75 */       this.model.setId("prerequisite:" + this.model.getPluginId()); 
/*  77 */     if (this.model.getDocumentation() != null)
/*  78 */       this.doc = new DocumentationImpl<PluginPrerequisite>(this, this.model.getDocumentation()); 
/*  81 */     if (log.isDebugEnabled())
/*  82 */       log.debug("object instantiated: " + this); 
/*     */   }
/*     */   
/*     */   public String getPluginId() {
/*  90 */     return this.model.getPluginId();
/*     */   }
/*     */   
/*     */   public Version getPluginVersion() {
/*  97 */     return this.model.getPluginVersion();
/*     */   }
/*     */   
/*     */   public PluginDescriptor getDeclaringPluginDescriptor() {
/* 104 */     return this.descriptor;
/*     */   }
/*     */   
/*     */   public PluginFragment getDeclaringPluginFragment() {
/* 111 */     return this.fragment;
/*     */   }
/*     */   
/*     */   public boolean isOptional() {
/* 118 */     return this.model.isOptional();
/*     */   }
/*     */   
/*     */   public boolean isReverseLookup() {
/* 125 */     return this.model.isReverseLookup();
/*     */   }
/*     */   
/*     */   public boolean matches() {
/* 132 */     PluginDescriptor descr = null;
/*     */     try {
/* 134 */       descr = this.descriptor.getRegistry().getPluginDescriptor(this.model.getPluginId());
/* 136 */     } catch (IllegalArgumentException iae) {
/* 137 */       return false;
/*     */     } 
/* 139 */     return matches(this.model.getPluginVersion(), descr.getVersion(), this.model.getMatchingRule());
/*     */   }
/*     */   
/*     */   public MatchingRule getMatchingRule() {
/* 147 */     return this.model.getMatchingRule();
/*     */   }
/*     */   
/*     */   public boolean isExported() {
/* 154 */     return this.model.isExported();
/*     */   }
/*     */   
/*     */   public String getId() {
/* 161 */     return this.model.getId();
/*     */   }
/*     */   
/*     */   public String getDocsPath() {
/* 168 */     return (this.fragment != null) ? this.fragment.getDocsPath() : this.descriptor.getDocsPath();
/*     */   }
/*     */   
/*     */   public Documentation<PluginPrerequisite> getDocumentation() {
/* 176 */     return this.doc;
/*     */   }
/*     */   
/*     */   public String getUniqueId() {
/* 183 */     return this.descriptor.getRegistry().makeUniqueId(this.descriptor.getId(), getId());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 192 */     return "{Prerequisite: uid=" + getUniqueId() + "}";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\PluginPrerequisiteImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */