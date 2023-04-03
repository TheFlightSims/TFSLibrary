/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.java.plugin.registry.ParameterMultiplicity;
/*     */ import org.java.plugin.registry.ParameterType;
/*     */ 
/*     */ final class ModelParameterDef {
/*     */   private String id;
/*     */   
/* 464 */   private ParameterMultiplicity multiplicity = ParameterMultiplicity.ONE;
/*     */   
/* 465 */   private ParameterType type = ParameterType.STRING;
/*     */   
/*     */   private String customData;
/*     */   
/*     */   private ModelDocumentation documentation;
/*     */   
/* 468 */   private LinkedList<ModelParameterDef> paramDefs = new LinkedList<ModelParameterDef>();
/*     */   
/*     */   private String defaultValue;
/*     */   
/*     */   String getCustomData() {
/* 477 */     return this.customData;
/*     */   }
/*     */   
/*     */   void setCustomData(String value) {
/* 481 */     this.customData = value;
/*     */   }
/*     */   
/*     */   ModelDocumentation getDocumentation() {
/* 485 */     return this.documentation;
/*     */   }
/*     */   
/*     */   void setDocumentation(ModelDocumentation value) {
/* 489 */     this.documentation = value;
/*     */   }
/*     */   
/*     */   String getId() {
/* 493 */     return this.id;
/*     */   }
/*     */   
/*     */   void setId(String value) {
/* 497 */     this.id = value;
/*     */   }
/*     */   
/*     */   ParameterMultiplicity getMultiplicity() {
/* 501 */     return this.multiplicity;
/*     */   }
/*     */   
/*     */   void setMultiplicity(ParameterMultiplicity value) {
/* 505 */     this.multiplicity = value;
/*     */   }
/*     */   
/*     */   ParameterType getType() {
/* 509 */     return this.type;
/*     */   }
/*     */   
/*     */   void setType(ParameterType value) {
/* 513 */     this.type = value;
/*     */   }
/*     */   
/*     */   List<ModelParameterDef> getParamDefs() {
/* 517 */     return this.paramDefs;
/*     */   }
/*     */   
/*     */   String getDefaultValue() {
/* 521 */     return this.defaultValue;
/*     */   }
/*     */   
/*     */   void setDefaultValue(String value) {
/* 525 */     this.defaultValue = value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ModelParameterDef.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */