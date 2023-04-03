/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.java.plugin.registry.Version;
/*     */ 
/*     */ abstract class ModelPluginManifest {
/*     */   private URL location;
/*     */   
/*     */   private String id;
/*     */   
/*     */   private Version version;
/*     */   
/*     */   private String vendor;
/*     */   
/*     */   private String docsPath;
/*     */   
/*     */   private ModelDocumentation documentation;
/*     */   
/*  41 */   private LinkedList<ModelAttribute> attributes = new LinkedList<ModelAttribute>();
/*     */   
/*  43 */   private LinkedList<ModelPrerequisite> prerequisites = new LinkedList<ModelPrerequisite>();
/*     */   
/*  45 */   private LinkedList<ModelLibrary> libraries = new LinkedList<ModelLibrary>();
/*     */   
/*  47 */   private LinkedList<ModelExtensionPoint> extensionPoints = new LinkedList<ModelExtensionPoint>();
/*     */   
/*  49 */   private LinkedList<ModelExtension> extensions = new LinkedList<ModelExtension>();
/*     */   
/*     */   URL getLocation() {
/*  53 */     return this.location;
/*     */   }
/*     */   
/*     */   void setLocation(URL value) {
/*  57 */     this.location = value;
/*     */   }
/*     */   
/*     */   String getDocsPath() {
/*  61 */     return this.docsPath;
/*     */   }
/*     */   
/*     */   void setDocsPath(String value) {
/*  65 */     this.docsPath = value;
/*     */   }
/*     */   
/*     */   ModelDocumentation getDocumentation() {
/*  69 */     return this.documentation;
/*     */   }
/*     */   
/*     */   void setDocumentation(ModelDocumentation value) {
/*  73 */     this.documentation = value;
/*     */   }
/*     */   
/*     */   String getId() {
/*  77 */     return this.id;
/*     */   }
/*     */   
/*     */   void setId(String value) {
/*  81 */     this.id = value;
/*     */   }
/*     */   
/*     */   String getVendor() {
/*  85 */     return this.vendor;
/*     */   }
/*     */   
/*     */   void setVendor(String value) {
/*  89 */     this.vendor = value;
/*     */   }
/*     */   
/*     */   Version getVersion() {
/*  93 */     return this.version;
/*     */   }
/*     */   
/*     */   void setVersion(String value) {
/*  97 */     this.version = Version.parse(value);
/*     */   }
/*     */   
/*     */   List<ModelAttribute> getAttributes() {
/* 101 */     return this.attributes;
/*     */   }
/*     */   
/*     */   List<ModelExtensionPoint> getExtensionPoints() {
/* 105 */     return this.extensionPoints;
/*     */   }
/*     */   
/*     */   List<ModelExtension> getExtensions() {
/* 109 */     return this.extensions;
/*     */   }
/*     */   
/*     */   List<ModelLibrary> getLibraries() {
/* 113 */     return this.libraries;
/*     */   }
/*     */   
/*     */   List<ModelPrerequisite> getPrerequisites() {
/* 117 */     return this.prerequisites;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ModelPluginManifest.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */