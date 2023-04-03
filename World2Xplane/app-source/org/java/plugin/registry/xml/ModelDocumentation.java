/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ 
/*     */ final class ModelDocumentation {
/* 172 */   private LinkedList<ModelDocumentationReference> references = new LinkedList<ModelDocumentationReference>();
/*     */   
/*     */   private String caption;
/*     */   
/*     */   private String text;
/*     */   
/*     */   String getCaption() {
/* 182 */     return this.caption;
/*     */   }
/*     */   
/*     */   void setCaption(String value) {
/* 186 */     this.caption = value;
/*     */   }
/*     */   
/*     */   String getText() {
/* 190 */     return this.text;
/*     */   }
/*     */   
/*     */   void setText(String value) {
/* 194 */     this.text = value;
/*     */   }
/*     */   
/*     */   List<ModelDocumentationReference> getReferences() {
/* 198 */     return this.references;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ModelDocumentation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */