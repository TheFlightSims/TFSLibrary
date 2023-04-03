/*    */ package com.typesafe.config.impl;
/*    */ 
/*    */ final class SubstitutionExpression {
/*    */   private final Path path;
/*    */   
/*    */   private final boolean optional;
/*    */   
/*    */   SubstitutionExpression(Path path, boolean optional) {
/*  9 */     this.path = path;
/* 10 */     this.optional = optional;
/*    */   }
/*    */   
/*    */   Path path() {
/* 14 */     return this.path;
/*    */   }
/*    */   
/*    */   boolean optional() {
/* 18 */     return this.optional;
/*    */   }
/*    */   
/*    */   SubstitutionExpression changePath(Path newPath) {
/* 22 */     if (newPath == this.path)
/* 23 */       return this; 
/* 25 */     return new SubstitutionExpression(newPath, this.optional);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 30 */     return "${" + (this.optional ? "?" : "") + this.path.render() + "}";
/*    */   }
/*    */   
/*    */   public boolean equals(Object other) {
/* 35 */     if (other instanceof SubstitutionExpression) {
/* 36 */       SubstitutionExpression otherExp = (SubstitutionExpression)other;
/* 37 */       return (otherExp.path.equals(this.path) && otherExp.optional == this.optional);
/*    */     } 
/* 39 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 45 */     int h = 41 * (41 + this.path.hashCode());
/* 46 */     h = 41 * (h + (this.optional ? 1 : 0));
/* 47 */     return h;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\SubstitutionExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */