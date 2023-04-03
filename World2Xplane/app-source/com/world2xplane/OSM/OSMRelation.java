/*    */ package com.world2xplane.OSM;
/*    */ 
/*    */ import com.world2xplane.Rules.AcceptingRule;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class OSMRelation {
/*    */   public long identifier;
/*    */   
/*    */   public double height;
/*    */   
/*    */   public Integer customFacade;
/*    */   
/*    */   public List<AcceptingRule> relationRules;
/*    */   
/*    */   public boolean hasRules() {
/* 33 */     if (this.relationRules != null && this.relationRules.size() > 0)
/* 34 */       return true; 
/* 36 */     if (this.outer != null && this.outer.size() > 0)
/* 37 */       for (Member item : this.outer) {
/* 38 */         if (item.rules != null && item.rules.size() > 0)
/* 39 */           return true; 
/*    */       }  
/* 44 */     if (this.inner != null && this.inner.size() > 0)
/* 45 */       for (Member item : this.inner) {
/* 46 */         if (item.rules != null && item.rules.size() > 0)
/* 47 */           return true; 
/*    */       }  
/* 52 */     if (this.parts != null && this.parts.size() > 0)
/* 53 */       for (Member item : this.parts) {
/* 54 */         if (item.rules != null && item.rules.size() > 0)
/* 55 */           return true; 
/*    */       }  
/* 59 */     return false;
/*    */   }
/*    */   
/*    */   public static class Member {
/*    */     public OSMPolygon shape;
/*    */     
/*    */     public List<AcceptingRule> rules;
/*    */     
/*    */     public String toString() {
/* 66 */       return this.shape.toString();
/*    */     }
/*    */   }
/*    */   
/* 78 */   public ArrayList<Member> outer = new ArrayList<>();
/*    */   
/* 79 */   public ArrayList<Member> inner = new ArrayList<>();
/*    */   
/* 80 */   public ArrayList<Member> parts = new ArrayList<>();
/*    */   
/*    */   public String toString() {
/* 83 */     return "Relation: " + this.identifier;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\OSM\OSMRelation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */