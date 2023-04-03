/*     */ package com.vividsolutions.jts.operation.distance;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.index.strtree.ItemBoundable;
/*     */ import com.vividsolutions.jts.index.strtree.ItemDistance;
/*     */ import com.vividsolutions.jts.index.strtree.STRtree;
/*     */ 
/*     */ public class IndexedFacetDistance {
/*     */   private STRtree cachedTree;
/*     */   
/*     */   public static double distance(Geometry g1, Geometry g2) {
/*  88 */     IndexedFacetDistance dist = new IndexedFacetDistance(g1);
/*  89 */     return dist.getDistance(g2);
/*     */   }
/*     */   
/*     */   public IndexedFacetDistance(Geometry g1) {
/* 108 */     this.cachedTree = FacetSequenceTreeBuilder.build(g1);
/*     */   }
/*     */   
/*     */   public double getDistance(Geometry g) {
/* 121 */     STRtree tree2 = FacetSequenceTreeBuilder.build(g);
/* 122 */     Object[] obj = this.cachedTree.nearestNeighbour(tree2, new FacetSequenceDistance());
/* 124 */     return facetDistance(obj);
/*     */   }
/*     */   
/*     */   private static double facetDistance(Object[] obj) {
/* 129 */     Object o1 = obj[0];
/* 130 */     Object o2 = obj[1];
/* 131 */     return ((FacetSequence)o1).distance((FacetSequence)o2);
/*     */   }
/*     */   
/*     */   private static class FacetSequenceDistance implements ItemDistance {
/*     */     private FacetSequenceDistance() {}
/*     */     
/*     */     public double distance(ItemBoundable item1, ItemBoundable item2) {
/* 181 */       FacetSequence fs1 = (FacetSequence)item1.getItem();
/* 182 */       FacetSequence fs2 = (FacetSequence)item2.getItem();
/* 183 */       return fs1.distance(fs2);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\distance\IndexedFacetDistance.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */