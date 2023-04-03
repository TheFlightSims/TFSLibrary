/*    */ package com.world2xplane.Rules.ObjectRules;
/*    */ 
/*    */ public class BestFitItem {
/*    */   private final ObjectList.ObjectListEntry objectListEntry;
/*    */   
/*    */   public String name;
/*    */   
/*    */   public String region;
/*    */   
/* 31 */   public int points = 0;
/*    */   
/*    */   public BestFitItem(ObjectList.ObjectListEntry item) {
/* 34 */     this.objectListEntry = item;
/*    */   }
/*    */   
/*    */   public void setDimensionScore(double sizeScore) {
/* 42 */     int score = (int)(sizeScore / 0.25D);
/* 43 */     score += (sizeScore / 0.25D % 0.25D > 0.0D) ? 1 : 0;
/* 45 */     this.points -= score;
/*    */   }
/*    */   
/*    */   public void addPoint() {
/* 51 */     this.points++;
/*    */   }
/*    */   
/*    */   public int getPoints() {
/* 55 */     return this.points;
/*    */   }
/*    */   
/*    */   public String getRegion() {
/* 59 */     return this.region;
/*    */   }
/*    */   
/*    */   public void setRegion(String region) {
/* 63 */     this.region = region;
/*    */   }
/*    */   
/*    */   public ObjectList.ObjectListEntry getObjectListEntry() {
/* 67 */     return this.objectListEntry;
/*    */   }
/*    */   
/*    */   public void removePoint() {
/* 71 */     this.points--;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\ObjectRules\BestFitItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */