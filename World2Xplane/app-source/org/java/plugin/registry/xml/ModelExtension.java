/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ final class ModelExtension {
/*     */   private String id;
/*     */   
/*     */   private String pluginId;
/*     */   
/*     */   private String pointId;
/*     */   
/*     */   private ModelDocumentation documentation;
/*     */   
/* 534 */   private LinkedList<ModelParameter> params = new LinkedList<ModelParameter>();
/*     */   
/*     */   ModelDocumentation getDocumentation() {
/* 542 */     return this.documentation;
/*     */   }
/*     */   
/*     */   void setDocumentation(ModelDocumentation value) {
/* 546 */     this.documentation = value;
/*     */   }
/*     */   
/*     */   String getId() {
/* 550 */     return this.id;
/*     */   }
/*     */   
/*     */   void setId(String value) {
/* 554 */     this.id = value;
/*     */   }
/*     */   
/*     */   String getPluginId() {
/* 558 */     return this.pluginId;
/*     */   }
/*     */   
/*     */   void setPluginId(String value) {
/* 562 */     this.pluginId = value;
/*     */   }
/*     */   
/*     */   String getPointId() {
/* 566 */     return this.pointId;
/*     */   }
/*     */   
/*     */   void setPointId(String value) {
/* 570 */     this.pointId = value;
/*     */   }
/*     */   
/*     */   List<ModelParameter> getParams() {
/* 574 */     return this.params;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ModelExtension.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */