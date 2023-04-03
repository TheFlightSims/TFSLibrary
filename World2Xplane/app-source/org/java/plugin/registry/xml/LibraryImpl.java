/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.java.plugin.registry.Library;
/*     */ import org.java.plugin.registry.ManifestProcessingException;
/*     */ import org.java.plugin.registry.Version;
/*     */ 
/*     */ class LibraryImpl extends PluginElementImpl<Library> implements Library {
/*     */   private final ModelLibrary model;
/*     */   
/*     */   private List<String> exports;
/*     */   
/*     */   LibraryImpl(PluginDescriptorImpl descr, PluginFragmentImpl aFragment, ModelLibrary aModel) throws ManifestProcessingException {
/*  40 */     super(descr, aFragment, aModel.getId(), aModel.getDocumentation());
/*  41 */     this.model = aModel;
/*  42 */     if (this.model.getPath() == null || this.model.getPath().trim().length() == 0)
/*  44 */       throw new ManifestProcessingException("org.java.plugin.registry.xml", "libraryPathIsBlank"); 
/*  48 */     this.exports = new ArrayList<String>(this.model.getExports().size());
/*  49 */     for (String exportPrefix : this.model.getExports()) {
/*  50 */       if (exportPrefix == null || exportPrefix.trim().length() == 0)
/*  51 */         throw new ManifestProcessingException("org.java.plugin.registry.xml", "exportPrefixIBlank"); 
/*  55 */       exportPrefix = exportPrefix.replace('\\', '.').replace('/', '.');
/*  56 */       if (exportPrefix.startsWith("."))
/*  57 */         exportPrefix = exportPrefix.substring(1); 
/*  59 */       this.exports.add(exportPrefix);
/*     */     } 
/*  61 */     this.exports = Collections.unmodifiableList(this.exports);
/*  62 */     if (this.log.isDebugEnabled())
/*  63 */       this.log.debug("object instantiated: " + this); 
/*     */   }
/*     */   
/*     */   public String getPath() {
/*  71 */     return this.model.getPath();
/*     */   }
/*     */   
/*     */   public Collection<String> getExports() {
/*  78 */     return this.exports;
/*     */   }
/*     */   
/*     */   public boolean isCodeLibrary() {
/*  85 */     return this.model.isCodeLibrary();
/*     */   }
/*     */   
/*     */   public String getUniqueId() {
/*  92 */     return getDeclaringPluginDescriptor().getRegistry().makeUniqueId(getDeclaringPluginDescriptor().getId(), getId());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 101 */     return "{Library: uid=" + getUniqueId() + "}";
/*     */   }
/*     */   
/*     */   public Version getVersion() {
/* 108 */     return this.model.getVersion();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\LibraryImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */