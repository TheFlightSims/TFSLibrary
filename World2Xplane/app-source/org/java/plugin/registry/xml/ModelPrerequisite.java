/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import org.java.plugin.registry.MatchingRule;
/*     */ import org.java.plugin.registry.Version;
/*     */ 
/*     */ final class ModelPrerequisite {
/*     */   private String id;
/*     */   
/*     */   private String pluginId;
/*     */   
/*     */   private Version pluginVersion;
/*     */   
/* 271 */   private MatchingRule matchingRule = MatchingRule.COMPATIBLE;
/*     */   
/*     */   private ModelDocumentation documentation;
/*     */   
/*     */   private boolean isExported;
/*     */   
/*     */   private boolean isOptional;
/*     */   
/*     */   private boolean isReverseLookup;
/*     */   
/*     */   ModelDocumentation getDocumentation() {
/* 282 */     return this.documentation;
/*     */   }
/*     */   
/*     */   void setDocumentation(ModelDocumentation value) {
/* 286 */     this.documentation = value;
/*     */   }
/*     */   
/*     */   String getId() {
/* 290 */     return this.id;
/*     */   }
/*     */   
/*     */   void setId(String value) {
/* 294 */     this.id = value;
/*     */   }
/*     */   
/*     */   boolean isExported() {
/* 298 */     return this.isExported;
/*     */   }
/*     */   
/*     */   void setExported(String value) {
/* 302 */     this.isExported = "true".equals(value);
/*     */   }
/*     */   
/*     */   boolean isOptional() {
/* 306 */     return this.isOptional;
/*     */   }
/*     */   
/*     */   void setOptional(String value) {
/* 310 */     this.isOptional = "true".equals(value);
/*     */   }
/*     */   
/*     */   boolean isReverseLookup() {
/* 314 */     return this.isReverseLookup;
/*     */   }
/*     */   
/*     */   void setReverseLookup(String value) {
/* 318 */     this.isReverseLookup = "true".equals(value);
/*     */   }
/*     */   
/*     */   MatchingRule getMatchingRule() {
/* 322 */     return this.matchingRule;
/*     */   }
/*     */   
/*     */   void setMatchingRule(MatchingRule value) {
/* 326 */     this.matchingRule = value;
/*     */   }
/*     */   
/*     */   String getPluginId() {
/* 330 */     return this.pluginId;
/*     */   }
/*     */   
/*     */   void setPluginId(String value) {
/* 334 */     this.pluginId = value;
/*     */   }
/*     */   
/*     */   Version getPluginVersion() {
/* 338 */     return this.pluginVersion;
/*     */   }
/*     */   
/*     */   void setPluginVersion(String value) {
/* 342 */     this.pluginVersion = Version.parse(value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ModelPrerequisite.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */