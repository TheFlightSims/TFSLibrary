/*    */ package com.world2xplane.OSM;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class RelationWay implements Serializable {
/*    */   private char role;
/*    */   
/* 35 */   private ArrayList<Tag> tags = new ArrayList<>();
/*    */   
/*    */   private Long wayId;
/*    */   
/*    */   private Long relationId;
/*    */   
/*    */   public ArrayList<Tag> getTags() {
/* 39 */     return this.tags;
/*    */   }
/*    */   
/*    */   public void setTags(ArrayList<Tag> tags) {
/* 43 */     this.tags = tags;
/*    */   }
/*    */   
/*    */   public Long getWayId() {
/* 47 */     return this.wayId;
/*    */   }
/*    */   
/*    */   public void setWayId(Long wayId) {
/* 51 */     this.wayId = wayId;
/*    */   }
/*    */   
/*    */   public Long getRelationId() {
/* 55 */     return this.relationId;
/*    */   }
/*    */   
/*    */   public void setRelationId(Long relationId) {
/* 59 */     this.relationId = relationId;
/*    */   }
/*    */   
/*    */   public void setRelationId(long relationId) {
/* 63 */     this.relationId = Long.valueOf(relationId);
/*    */   }
/*    */   
/*    */   public char getRole() {
/* 67 */     return this.role;
/*    */   }
/*    */   
/*    */   public void setRole(char role) {
/* 71 */     this.role = role;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 75 */     return String.format("RelationWay: relId=%l wayId=%l role=%s", new Object[] { this.relationId, this.wayId, Character.valueOf(this.role) });
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\OSM\RelationWay.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */