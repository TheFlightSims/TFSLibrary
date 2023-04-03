/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import org.java.plugin.registry.MatchingRule;
/*     */ import org.java.plugin.registry.Version;
/*     */ 
/*     */ final class ModelManifestInfo {
/*     */   private String id;
/*     */   
/*     */   private Version version;
/*     */   
/*     */   private String vendor;
/*     */   
/*     */   private String pluginId;
/*     */   
/*     */   private Version pluginVersion;
/*     */   
/* 624 */   private MatchingRule matchingRule = MatchingRule.COMPATIBLE;
/*     */   
/*     */   String getId() {
/* 631 */     return this.id;
/*     */   }
/*     */   
/*     */   void setId(String value) {
/* 635 */     this.id = value;
/*     */   }
/*     */   
/*     */   String getVendor() {
/* 639 */     return this.vendor;
/*     */   }
/*     */   
/*     */   void setVendor(String value) {
/* 643 */     this.vendor = value;
/*     */   }
/*     */   
/*     */   Version getVersion() {
/* 647 */     return this.version;
/*     */   }
/*     */   
/*     */   void setVersion(String value) {
/* 651 */     this.version = Version.parse(value);
/*     */   }
/*     */   
/*     */   MatchingRule getMatchRule() {
/* 655 */     return this.matchingRule;
/*     */   }
/*     */   
/*     */   void setMatchingRule(MatchingRule value) {
/* 659 */     this.matchingRule = value;
/*     */   }
/*     */   
/*     */   String getPluginId() {
/* 663 */     return this.pluginId;
/*     */   }
/*     */   
/*     */   void setPluginId(String value) {
/* 667 */     this.pluginId = value;
/*     */   }
/*     */   
/*     */   Version getPluginVersion() {
/* 671 */     return this.pluginVersion;
/*     */   }
/*     */   
/*     */   void setPluginVersion(String value) {
/* 675 */     this.pluginVersion = Version.parse(value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ModelManifestInfo.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */