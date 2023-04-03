/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ final class ModelAttribute {
/*     */   private String id;
/*     */   
/*     */   private String value;
/*     */   
/*     */   private ModelDocumentation documentation;
/*     */   
/* 231 */   private LinkedList<ModelAttribute> attributes = new LinkedList<ModelAttribute>();
/*     */   
/*     */   ModelDocumentation getDocumentation() {
/* 239 */     return this.documentation;
/*     */   }
/*     */   
/*     */   void setDocumentation(ModelDocumentation aValue) {
/* 243 */     this.documentation = aValue;
/*     */   }
/*     */   
/*     */   String getId() {
/* 247 */     return this.id;
/*     */   }
/*     */   
/*     */   void setId(String aValue) {
/* 251 */     this.id = aValue;
/*     */   }
/*     */   
/*     */   String getValue() {
/* 255 */     return this.value;
/*     */   }
/*     */   
/*     */   void setValue(String aValue) {
/* 259 */     this.value = aValue;
/*     */   }
/*     */   
/*     */   List<ModelAttribute> getAttributes() {
/* 263 */     return this.attributes;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ModelAttribute.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */