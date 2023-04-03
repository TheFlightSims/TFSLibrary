/*    */ package com.world2xplane.Rules;
/*    */ 
/*    */ import com.world2xplane.OSM.OSMPolygon;
/*    */ import com.world2xplane.Rules.ObjectRules.BestFitItem;
/*    */ import com.world2xplane.Rules.ObjectRules.ObjectList;
/*    */ 
/*    */ public class ObjectTagRule {
/*    */   private final String region;
/*    */   
/*    */   private final String area;
/*    */   
/*    */   private final String tag;
/*    */   
/*    */   private final float min;
/*    */   
/*    */   private final float max;
/*    */   
/*    */   private final int score;
/*    */   
/*    */   public ObjectTagRule(String region, String area, String tag, float min, float max, int score) {
/* 40 */     this.region = region;
/* 41 */     this.area = area;
/* 42 */     this.tag = tag;
/* 43 */     this.min = min;
/* 44 */     this.max = max;
/* 45 */     this.score = score;
/*    */   }
/*    */   
/*    */   public void applyScore(ObjectList.ObjectListEntry objectTagRule, BestFitItem bestFitItem, OSMPolygon shape, float randomSeed) {
/* 49 */     if (objectTagRule.tags != null && 
/* 50 */       objectTagRule.tags.contains(this.tag))
/* 51 */       bestFitItem.addPoint(); 
/*    */   }
/*    */   
/*    */   public String getRegion() {
/* 57 */     return this.region;
/*    */   }
/*    */   
/*    */   public String getArea() {
/* 61 */     return this.area;
/*    */   }
/*    */   
/*    */   public String getTag() {
/* 65 */     return this.tag;
/*    */   }
/*    */   
/*    */   public float getMin() {
/* 69 */     return this.min;
/*    */   }
/*    */   
/*    */   public float getMax() {
/* 73 */     return this.max;
/*    */   }
/*    */   
/*    */   public int getScore() {
/* 77 */     return this.score;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\ObjectTagRule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */