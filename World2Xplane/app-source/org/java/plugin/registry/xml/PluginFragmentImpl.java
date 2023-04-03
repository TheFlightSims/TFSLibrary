/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.net.URL;
/*     */ import org.java.plugin.registry.Documentation;
/*     */ import org.java.plugin.registry.Identity;
/*     */ import org.java.plugin.registry.ManifestProcessingException;
/*     */ import org.java.plugin.registry.MatchingRule;
/*     */ import org.java.plugin.registry.PluginDescriptor;
/*     */ import org.java.plugin.registry.PluginFragment;
/*     */ import org.java.plugin.registry.PluginRegistry;
/*     */ import org.java.plugin.registry.Version;
/*     */ 
/*     */ class PluginFragmentImpl extends IdentityImpl implements PluginFragment {
/*     */   private final PluginRegistry registry;
/*     */   
/*     */   private final ModelPluginFragment model;
/*     */   
/*     */   private Documentation<PluginFragment> doc;
/*     */   
/*     */   PluginFragmentImpl(PluginRegistry aRegistry, ModelPluginFragment aModel) throws ManifestProcessingException {
/*  43 */     super(aModel.getId());
/*  44 */     this.registry = aRegistry;
/*  45 */     this.model = aModel;
/*  46 */     if (this.model.getVendor() == null)
/*  47 */       this.model.setVendor(""); 
/*  49 */     if (this.model.getPluginId() == null || this.model.getPluginId().trim().length() == 0)
/*  51 */       throw new ManifestProcessingException("org.java.plugin.registry.xml", "fragmentPliginIdIsBlank", getId()); 
/*  55 */     if (getId().equals(this.model.getPluginId()))
/*  56 */       throw new ManifestProcessingException("org.java.plugin.registry.xml", "invalidFragmentPluginId", getId()); 
/*  60 */     if (this.model.getDocsPath() == null || this.model.getDocsPath().trim().length() == 0)
/*  62 */       this.model.setDocsPath("docs"); 
/*  64 */     if (this.model.getDocumentation() != null)
/*  65 */       this.doc = new DocumentationImpl<PluginFragment>(this, this.model.getDocumentation()); 
/*  68 */     if (this.log.isDebugEnabled())
/*  69 */       this.log.debug("object instantiated: " + this); 
/*     */   }
/*     */   
/*     */   ModelPluginFragment getModel() {
/*  74 */     return this.model;
/*     */   }
/*     */   
/*     */   public String getUniqueId() {
/*  81 */     return this.registry.makeUniqueId(getId(), this.model.getVersion());
/*     */   }
/*     */   
/*     */   public String getVendor() {
/*  88 */     return this.model.getVendor();
/*     */   }
/*     */   
/*     */   public Version getVersion() {
/*  95 */     return this.model.getVersion();
/*     */   }
/*     */   
/*     */   public String getPluginId() {
/* 102 */     return this.model.getPluginId();
/*     */   }
/*     */   
/*     */   public Version getPluginVersion() {
/* 109 */     return this.model.getPluginVersion();
/*     */   }
/*     */   
/*     */   public PluginRegistry getRegistry() {
/* 116 */     return this.registry;
/*     */   }
/*     */   
/*     */   public boolean matches(PluginDescriptor descr) {
/* 124 */     return PluginPrerequisiteImpl.matches(this.model.getPluginVersion(), descr.getVersion(), this.model.getMatchingRule());
/*     */   }
/*     */   
/*     */   public MatchingRule getMatchingRule() {
/* 132 */     return this.model.getMatchingRule();
/*     */   }
/*     */   
/*     */   public Documentation<PluginFragment> getDocumentation() {
/* 139 */     return this.doc;
/*     */   }
/*     */   
/*     */   public String getDocsPath() {
/* 146 */     return this.model.getDocsPath();
/*     */   }
/*     */   
/*     */   public URL getLocation() {
/* 153 */     return this.model.getLocation();
/*     */   }
/*     */   
/*     */   protected boolean isEqualTo(Identity idt) {
/* 162 */     if (!(idt instanceof PluginFragmentImpl))
/* 163 */       return false; 
/* 165 */     PluginFragmentImpl other = (PluginFragmentImpl)idt;
/* 166 */     return (getUniqueId().equals(other.getUniqueId()) && getLocation().toExternalForm().equals(other.getLocation().toExternalForm()));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 176 */     return "{PluginFragment: uid=" + getUniqueId() + "}";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\PluginFragmentImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */