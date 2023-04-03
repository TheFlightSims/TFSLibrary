/*     */ package org.apache.commons.math3.geometry.euclidean.threed;
/*     */ 
/*     */ import org.apache.commons.math3.geometry.Vector;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.Line;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.PolygonsSet;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.SubLine;
/*     */ import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
/*     */ import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
/*     */ import org.apache.commons.math3.geometry.partitioning.BSPTree;
/*     */ import org.apache.commons.math3.geometry.partitioning.Hyperplane;
/*     */ import org.apache.commons.math3.geometry.partitioning.Region;
/*     */ import org.apache.commons.math3.geometry.partitioning.Side;
/*     */ import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
/*     */ 
/*     */ public class SubPlane extends AbstractSubHyperplane<Euclidean3D, Euclidean2D> {
/*     */   public SubPlane(Hyperplane<Euclidean3D> hyperplane, Region<Euclidean2D> remainingRegion) {
/*  42 */     super(hyperplane, remainingRegion);
/*     */   }
/*     */   
/*     */   protected AbstractSubHyperplane<Euclidean3D, Euclidean2D> buildNew(Hyperplane<Euclidean3D> hyperplane, Region<Euclidean2D> remainingRegion) {
/*  49 */     return new SubPlane(hyperplane, remainingRegion);
/*     */   }
/*     */   
/*     */   public Side side(Hyperplane<Euclidean3D> hyperplane) {
/*  56 */     Plane otherPlane = (Plane)hyperplane;
/*  57 */     Plane thisPlane = (Plane)getHyperplane();
/*  58 */     Line inter = otherPlane.intersection(thisPlane);
/*  60 */     if (inter == null) {
/*  63 */       double global = otherPlane.getOffset(thisPlane);
/*  64 */       return (global < -1.0E-10D) ? Side.MINUS : ((global > 1.0E-10D) ? Side.PLUS : Side.HYPER);
/*     */     } 
/*  74 */     Vector2D p = thisPlane.toSubSpace(inter.toSpace((Vector<Euclidean1D>)Vector1D.ZERO));
/*  75 */     Vector2D q = thisPlane.toSubSpace(inter.toSpace((Vector<Euclidean1D>)Vector1D.ONE));
/*  76 */     Vector3D crossP = Vector3D.crossProduct(inter.getDirection(), thisPlane.getNormal());
/*  77 */     if (crossP.dotProduct(otherPlane.getNormal()) < 0.0D) {
/*  78 */       Vector2D tmp = p;
/*  79 */       p = q;
/*  80 */       q = tmp;
/*     */     } 
/*  82 */     Line line2D = new Line(p, q);
/*  86 */     return getRemainingRegion().side((Hyperplane)line2D);
/*     */   }
/*     */   
/*     */   public SubHyperplane.SplitSubHyperplane<Euclidean3D> split(Hyperplane<Euclidean3D> hyperplane) {
/*  99 */     Plane otherPlane = (Plane)hyperplane;
/* 100 */     Plane thisPlane = (Plane)getHyperplane();
/* 101 */     Line inter = otherPlane.intersection(thisPlane);
/* 103 */     if (inter == null) {
/* 105 */       double global = otherPlane.getOffset(thisPlane);
/* 106 */       return (global < -1.0E-10D) ? new SubHyperplane.SplitSubHyperplane(null, (SubHyperplane)this) : new SubHyperplane.SplitSubHyperplane((SubHyperplane)this, null);
/*     */     } 
/* 112 */     Vector2D p = thisPlane.toSubSpace(inter.toSpace((Vector<Euclidean1D>)Vector1D.ZERO));
/* 113 */     Vector2D q = thisPlane.toSubSpace(inter.toSpace((Vector<Euclidean1D>)Vector1D.ONE));
/* 114 */     Vector3D crossP = Vector3D.crossProduct(inter.getDirection(), thisPlane.getNormal());
/* 115 */     if (crossP.dotProduct(otherPlane.getNormal()) < 0.0D) {
/* 116 */       Vector2D tmp = p;
/* 117 */       p = q;
/* 118 */       q = tmp;
/*     */     } 
/* 120 */     SubLine subLine1 = (new Line(p, q)).wholeHyperplane();
/* 122 */     SubLine subLine2 = (new Line(q, p)).wholeHyperplane();
/* 125 */     BSPTree<Euclidean2D> splitTree = getRemainingRegion().getTree(false).split((SubHyperplane)subLine1);
/* 126 */     BSPTree<Euclidean2D> plusTree = getRemainingRegion().isEmpty(splitTree.getPlus()) ? new BSPTree(Boolean.FALSE) : new BSPTree((SubHyperplane)subLine2, new BSPTree(Boolean.FALSE), splitTree.getPlus(), null);
/* 131 */     BSPTree<Euclidean2D> minusTree = getRemainingRegion().isEmpty(splitTree.getMinus()) ? new BSPTree(Boolean.FALSE) : new BSPTree((SubHyperplane)subLine1, new BSPTree(Boolean.FALSE), splitTree.getMinus(), null);
/* 136 */     return new SubHyperplane.SplitSubHyperplane((SubHyperplane)new SubPlane(thisPlane.copySelf(), (Region<Euclidean2D>)new PolygonsSet(plusTree)), (SubHyperplane)new SubPlane(thisPlane.copySelf(), (Region<Euclidean2D>)new PolygonsSet(minusTree)));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\threed\SubPlane.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */