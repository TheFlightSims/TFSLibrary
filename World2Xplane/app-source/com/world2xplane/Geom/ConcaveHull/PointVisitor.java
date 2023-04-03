/*     */ package com.world2xplane.Geom.ConcaveHull;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.index.ItemVisitor;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ class PointVisitor implements ItemVisitor {
/* 202 */   public List<Point> plist = new ArrayList<>();
/*     */   
/*     */   private double maxDist;
/*     */   
/*     */   private Point refP;
/*     */   
/*     */   PointVisitor(Point refP, double maxDist) {
/* 207 */     this.refP = refP;
/* 208 */     this.maxDist = maxDist;
/*     */   }
/*     */   
/*     */   public void visitItem(Object o) {
/* 212 */     if (this.refP.isWithinDistance((Geometry)o, this.maxDist))
/* 213 */       this.plist.add((Point)o); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\ConcaveHull\PointVisitor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */