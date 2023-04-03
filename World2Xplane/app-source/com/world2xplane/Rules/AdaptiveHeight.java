/*    */ package com.world2xplane.Rules;
/*    */ 
/*    */ import com.world2xplane.XPlane.NodeTracker;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class AdaptiveHeight {
/*    */   private String identifier;
/*    */   
/*    */   Map<String, AdaptiveHeightPolicy> adaptiveHeightPolicyMap;
/*    */   
/*    */   public AdaptiveHeightPolicy getRangeForTrackPoint(NodeTracker.TrackPoint trackPoint) {
/* 40 */     if (this.adaptiveHeightPolicyMap == null) {
/* 41 */       this.adaptiveHeightPolicyMap = new HashMap<>();
/* 42 */       for (AdaptiveHeightPolicy item : this.adaptiveHeightPolicies)
/* 43 */         this.adaptiveHeightPolicyMap.put(item.nodeTracker, item); 
/*    */     } 
/* 46 */     return this.adaptiveHeightPolicyMap.get(trackPoint.nodeTrackerRule.getIdentifier());
/*    */   }
/*    */   
/*    */   public static class AdaptiveHeightPolicy {
/*    */     private String nodeTracker;
/*    */     
/*    */     private int min_levels;
/*    */     
/*    */     private int max_levels;
/*    */     
/*    */     private float range;
/*    */     
/*    */     public AdaptiveHeightPolicy(String nodeTracker, int min_levels, int max_levels, float range) {
/* 56 */       this.nodeTracker = nodeTracker;
/* 57 */       this.min_levels = min_levels;
/* 58 */       this.max_levels = max_levels;
/* 59 */       this.range = range;
/*    */     }
/*    */     
/*    */     public String getNodeTracker() {
/* 63 */       return this.nodeTracker;
/*    */     }
/*    */     
/*    */     public int getMin_levels() {
/* 67 */       return this.min_levels;
/*    */     }
/*    */     
/*    */     public int getMax_levels() {
/* 71 */       return this.max_levels;
/*    */     }
/*    */     
/*    */     public float getRange() {
/* 75 */       return this.range;
/*    */     }
/*    */     
/*    */     public float getRandomHeight() {
/* 79 */       return (Rule.getRandomNumber(this.min_levels, this.max_levels + 1) * 3);
/*    */     }
/*    */   }
/*    */   
/* 83 */   List<AdaptiveHeightPolicy> adaptiveHeightPolicies = new ArrayList<>();
/*    */   
/*    */   public String getIdentifier() {
/* 86 */     return this.identifier;
/*    */   }
/*    */   
/*    */   public void setIdentifier(String identifier) {
/* 90 */     this.identifier = identifier;
/*    */   }
/*    */   
/*    */   public List<AdaptiveHeightPolicy> getAdaptiveHeightPolicies() {
/* 94 */     return this.adaptiveHeightPolicies;
/*    */   }
/*    */   
/*    */   public void setAdaptiveHeightPolicies(List<AdaptiveHeightPolicy> adaptiveHeightPolicies) {
/* 98 */     this.adaptiveHeightPolicies = adaptiveHeightPolicies;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\AdaptiveHeight.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */