/*    */ package com.typesafe.config.impl;
/*    */ 
/*    */ import java.util.Collection;
/*    */ 
/*    */ enum ResolveStatus {
/* 12 */   UNRESOLVED, RESOLVED;
/*    */   
/*    */   static final ResolveStatus fromValues(Collection<? extends AbstractConfigValue> values) {
/* 16 */     for (AbstractConfigValue v : values) {
/* 17 */       if (v.resolveStatus() == UNRESOLVED)
/* 18 */         return UNRESOLVED; 
/*    */     } 
/* 20 */     return RESOLVED;
/*    */   }
/*    */   
/*    */   static final ResolveStatus fromBoolean(boolean resolved) {
/* 24 */     return resolved ? RESOLVED : UNRESOLVED;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\ResolveStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */