/*     */ package com.vividsolutions.jts.algorithm;
/*     */ 
/*     */ public interface BoundaryNodeRule {
/*  90 */   public static final BoundaryNodeRule MOD2_BOUNDARY_RULE = new Mod2BoundaryNodeRule();
/*     */   
/*  96 */   public static final BoundaryNodeRule ENDPOINT_BOUNDARY_RULE = new EndPointBoundaryNodeRule();
/*     */   
/* 102 */   public static final BoundaryNodeRule MULTIVALENT_ENDPOINT_BOUNDARY_RULE = new MultiValentEndPointBoundaryNodeRule();
/*     */   
/* 108 */   public static final BoundaryNodeRule MONOVALENT_ENDPOINT_BOUNDARY_RULE = new MonoValentEndPointBoundaryNodeRule();
/*     */   
/* 115 */   public static final BoundaryNodeRule OGC_SFS_BOUNDARY_RULE = MOD2_BOUNDARY_RULE;
/*     */   
/*     */   boolean isInBoundary(int paramInt);
/*     */   
/*     */   public static class Mod2BoundaryNodeRule implements BoundaryNodeRule {
/*     */     public boolean isInBoundary(int boundaryCount) {
/* 137 */       return (boundaryCount % 2 == 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class EndPointBoundaryNodeRule implements BoundaryNodeRule {
/*     */     public boolean isInBoundary(int boundaryCount) {
/* 168 */       return (boundaryCount > 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MultiValentEndPointBoundaryNodeRule implements BoundaryNodeRule {
/*     */     public boolean isInBoundary(int boundaryCount) {
/* 187 */       return (boundaryCount > 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MonoValentEndPointBoundaryNodeRule implements BoundaryNodeRule {
/*     */     public boolean isInBoundary(int boundaryCount) {
/* 205 */       return (boundaryCount == 1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\algorithm\BoundaryNodeRule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */