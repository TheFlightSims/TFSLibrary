/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ final class ModelParameter {
/*     */   private String id;
/*     */   
/*     */   private String value;
/*     */   
/*     */   private ModelDocumentation documentation;
/*     */   
/* 582 */   private LinkedList<ModelParameter> params = new LinkedList<ModelParameter>();
/*     */   
/*     */   ModelDocumentation getDocumentation() {
/* 590 */     return this.documentation;
/*     */   }
/*     */   
/*     */   void setDocumentation(ModelDocumentation aValue) {
/* 594 */     this.documentation = aValue;
/*     */   }
/*     */   
/*     */   String getId() {
/* 598 */     return this.id;
/*     */   }
/*     */   
/*     */   void setId(String aValue) {
/* 602 */     this.id = aValue;
/*     */   }
/*     */   
/*     */   String getValue() {
/* 606 */     return this.value;
/*     */   }
/*     */   
/*     */   void setValue(String aValue) {
/* 610 */     this.value = aValue;
/*     */   }
/*     */   
/*     */   List<ModelParameter> getParams() {
/* 614 */     return this.params;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ModelParameter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */