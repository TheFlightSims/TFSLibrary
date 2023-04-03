/*     */ package org.java.plugin.registry.xml;
/*     */ 
/*     */ import org.java.plugin.registry.MatchingRule;
/*     */ import org.java.plugin.registry.Version;
/*     */ 
/*     */ final class ModelPluginFragment extends ModelPluginManifest {
/*     */   private String pluginId;
/*     */   
/*     */   private Version pluginVersion;
/*     */   
/* 140 */   private MatchingRule matchingRule = MatchingRule.COMPATIBLE;
/*     */   
/*     */   MatchingRule getMatchingRule() {
/* 147 */     return this.matchingRule;
/*     */   }
/*     */   
/*     */   void setMatchingRule(MatchingRule value) {
/* 151 */     this.matchingRule = value;
/*     */   }
/*     */   
/*     */   String getPluginId() {
/* 155 */     return this.pluginId;
/*     */   }
/*     */   
/*     */   void setPluginId(String value) {
/* 159 */     this.pluginId = value;
/*     */   }
/*     */   
/*     */   Version getPluginVersion() {
/* 163 */     return this.pluginVersion;
/*     */   }
/*     */   
/*     */   void setPluginVersion(String value) {
/* 167 */     this.pluginVersion = Version.parse(value);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\xml\ModelPluginFragment.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */