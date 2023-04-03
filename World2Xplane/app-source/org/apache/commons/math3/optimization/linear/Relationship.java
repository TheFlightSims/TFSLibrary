/*    */ package org.apache.commons.math3.optimization.linear;
/*    */ 
/*    */ public enum Relationship {
/* 28 */   EQ("="),
/* 31 */   LEQ("<="),
/* 34 */   GEQ(">=");
/*    */   
/*    */   private final String stringValue;
/*    */   
/*    */   Relationship(String stringValue) {
/* 43 */     this.stringValue = stringValue;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 49 */     return this.stringValue;
/*    */   }
/*    */   
/*    */   public Relationship oppositeRelationship() {
/* 57 */     switch (this) {
/*    */       case LEQ:
/* 59 */         return GEQ;
/*    */       case GEQ:
/* 61 */         return LEQ;
/*    */     } 
/* 63 */     return EQ;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\linear\Relationship.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */