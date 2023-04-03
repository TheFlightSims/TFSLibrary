/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.java.plugin.registry.ExtensionMultiplicity;
/*     */ 
/*     */ final class ModelExtensionPoint {
/*     */   private String id;
/*     */   
/*     */   private String parentPluginId;
/*     */   
/*     */   private String parentPointId;
/*     */   
/* 407 */   private ExtensionMultiplicity extensionMultiplicity = ExtensionMultiplicity.ONE;
/*     */   
/*     */   private ModelDocumentation documentation;
/*     */   
/* 410 */   private LinkedList<ModelParameterDef> paramDefs = new LinkedList<ModelParameterDef>();
/*     */   
/*     */   ModelDocumentation getDocumentation() {
/* 418 */     return this.documentation;
/*     */   }
/*     */   
/*     */   void setDocumentation(ModelDocumentation value) {
/* 422 */     this.documentation = value;
/*     */   }
/*     */   
/*     */   ExtensionMultiplicity getExtensionMultiplicity() {
/* 426 */     return this.extensionMultiplicity;
/*     */   }
/*     */   
/*     */   void setExtensionMultiplicity(ExtensionMultiplicity value) {
/* 430 */     this.extensionMultiplicity = value;
/*     */   }
/*     */   
/*     */   String getId() {
/* 434 */     return this.id;
/*     */   }
/*     */   
/*     */   void setId(String value) {
/* 438 */     this.id = value;
/*     */   }
/*     */   
/*     */   String getParentPluginId() {
/* 442 */     return this.parentPluginId;
/*     */   }
/*     */   
/*     */   void setParentPluginId(String value) {
/* 446 */     this.parentPluginId = value;
/*     */   }
/*     */   
/*     */   String getParentPointId() {
/* 450 */     return this.parentPointId;
/*     */   }
/*     */   
/*     */   void setParentPointId(String value) {
/* 454 */     this.parentPointId = value;
/*     */   }
/*     */   
/*     */   List<ModelParameterDef> getParamDefs() {
/* 458 */     return this.paramDefs;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ModelExtensionPoint.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */