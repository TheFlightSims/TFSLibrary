/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.java.plugin.registry.Version;
/*     */ 
/*     */ final class ModelLibrary {
/*     */   private String id;
/*     */   
/*     */   private String path;
/*     */   
/*     */   private boolean isCodeLibrary;
/*     */   
/*     */   private ModelDocumentation documentation;
/*     */   
/* 351 */   private LinkedList<String> exports = new LinkedList<String>();
/*     */   
/*     */   private Version version;
/*     */   
/*     */   ModelDocumentation getDocumentation() {
/* 359 */     return this.documentation;
/*     */   }
/*     */   
/*     */   void setDocumentation(ModelDocumentation value) {
/* 363 */     this.documentation = value;
/*     */   }
/*     */   
/*     */   String getId() {
/* 367 */     return this.id;
/*     */   }
/*     */   
/*     */   void setId(String value) {
/* 371 */     this.id = value;
/*     */   }
/*     */   
/*     */   boolean isCodeLibrary() {
/* 375 */     return this.isCodeLibrary;
/*     */   }
/*     */   
/*     */   void setCodeLibrary(String value) {
/* 379 */     this.isCodeLibrary = "code".equals(value);
/*     */   }
/*     */   
/*     */   String getPath() {
/* 383 */     return this.path;
/*     */   }
/*     */   
/*     */   void setPath(String value) {
/* 387 */     this.path = value;
/*     */   }
/*     */   
/*     */   List<String> getExports() {
/* 391 */     return this.exports;
/*     */   }
/*     */   
/*     */   Version getVersion() {
/* 395 */     return this.version;
/*     */   }
/*     */   
/*     */   void setVersion(String value) {
/* 399 */     this.version = Version.parse(value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ModelLibrary.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */