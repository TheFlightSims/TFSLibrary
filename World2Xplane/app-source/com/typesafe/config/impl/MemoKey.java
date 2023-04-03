/*    */ package com.typesafe.config.impl;
/*    */ 
/*    */ final class MemoKey {
/*    */   private final AbstractConfigValue value;
/*    */   
/*    */   private final Path restrictToChildOrNull;
/*    */   
/*    */   MemoKey(AbstractConfigValue value, Path restrictToChildOrNull) {
/*  6 */     this.value = value;
/*  7 */     this.restrictToChildOrNull = restrictToChildOrNull;
/*    */   }
/*    */   
/*    */   public final int hashCode() {
/* 15 */     int h = System.identityHashCode(this.value);
/* 16 */     if (this.restrictToChildOrNull != null)
/* 17 */       return h + 41 * (41 + this.restrictToChildOrNull.hashCode()); 
/* 19 */     return h;
/*    */   }
/*    */   
/*    */   public final boolean equals(Object other) {
/* 25 */     if (other instanceof MemoKey) {
/* 26 */       MemoKey o = (MemoKey)other;
/* 27 */       if (o.value != this.value)
/* 28 */         return false; 
/* 29 */       if (o.restrictToChildOrNull == this.restrictToChildOrNull)
/* 30 */         return true; 
/* 31 */       if (o.restrictToChildOrNull == null || this.restrictToChildOrNull == null)
/* 32 */         return false; 
/* 34 */       return o.restrictToChildOrNull.equals(this.restrictToChildOrNull);
/*    */     } 
/* 36 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\MemoKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */