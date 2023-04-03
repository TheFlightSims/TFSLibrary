/*     */ package org.apache.commons.math3.geometry.euclidean.twod;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.geometry.Vector;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Interval;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.OrientedPoint;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.SubOrientedPoint;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
/*     */ import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
/*     */ import org.apache.commons.math3.geometry.partitioning.BSPTree;
/*     */ import org.apache.commons.math3.geometry.partitioning.Hyperplane;
/*     */ import org.apache.commons.math3.geometry.partitioning.Region;
/*     */ import org.apache.commons.math3.geometry.partitioning.Side;
/*     */ import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
/*     */ import org.apache.commons.math3.util.FastMath;
/*     */ 
/*     */ public class SubLine extends AbstractSubHyperplane<Euclidean2D, Euclidean1D> {
/*     */   public SubLine(Hyperplane<Euclidean2D> hyperplane, Region<Euclidean1D> remainingRegion) {
/*  48 */     super(hyperplane, remainingRegion);
/*     */   }
/*     */   
/*     */   public SubLine(Vector2D start, Vector2D end) {
/*  56 */     super(new Line(start, end), (Region)buildIntervalSet(start, end));
/*     */   }
/*     */   
/*     */   public SubLine(Segment segment) {
/*  63 */     super(segment.getLine(), (Region)buildIntervalSet(segment.getStart(), segment.getEnd()));
/*     */   }
/*     */   
/*     */   public List<Segment> getSegments() {
/*  82 */     Line line = (Line)getHyperplane();
/*  83 */     List<Interval> list = ((IntervalsSet)getRemainingRegion()).asList();
/*  84 */     List<Segment> segments = new ArrayList<Segment>();
/*  86 */     for (Interval interval : list) {
/*  87 */       Vector2D start = line.toSpace((Vector<Euclidean1D>)new Vector1D(interval.getLower()));
/*  88 */       Vector2D end = line.toSpace((Vector<Euclidean1D>)new Vector1D(interval.getUpper()));
/*  89 */       segments.add(new Segment(start, end, line));
/*     */     } 
/*  92 */     return segments;
/*     */   }
/*     */   
/*     */   public Vector2D intersection(SubLine subLine, boolean includeEndPoints) {
/* 113 */     Line line1 = (Line)getHyperplane();
/* 114 */     Line line2 = (Line)subLine.getHyperplane();
/* 117 */     Vector2D v2D = line1.intersection(line2);
/* 120 */     Region.Location loc1 = getRemainingRegion().checkPoint((Vector)line1.toSubSpace(v2D));
/* 123 */     Region.Location loc2 = subLine.getRemainingRegion().checkPoint((Vector)line2.toSubSpace(v2D));
/* 125 */     if (includeEndPoints)
/* 126 */       return (loc1 != Region.Location.OUTSIDE && loc2 != Region.Location.OUTSIDE) ? v2D : null; 
/* 128 */     return (loc1 == Region.Location.INSIDE && loc2 == Region.Location.INSIDE) ? v2D : null;
/*     */   }
/*     */   
/*     */   private static IntervalsSet buildIntervalSet(Vector2D start, Vector2D end) {
/* 139 */     Line line = new Line(start, end);
/* 140 */     return new IntervalsSet(line.toSubSpace(start).getX(), line.toSubSpace(end).getX());
/*     */   }
/*     */   
/*     */   protected AbstractSubHyperplane<Euclidean2D, Euclidean1D> buildNew(Hyperplane<Euclidean2D> hyperplane, Region<Euclidean1D> remainingRegion) {
/* 148 */     return new SubLine(hyperplane, remainingRegion);
/*     */   }
/*     */   
/*     */   public Side side(Hyperplane<Euclidean2D> hyperplane) {
/* 155 */     Line thisLine = (Line)getHyperplane();
/* 156 */     Line otherLine = (Line)hyperplane;
/* 157 */     Vector2D crossing = thisLine.intersection(otherLine);
/* 159 */     if (crossing == null) {
/* 161 */       double global = otherLine.getOffset(thisLine);
/* 162 */       return (global < -1.0E-10D) ? Side.MINUS : ((global > 1.0E-10D) ? Side.PLUS : Side.HYPER);
/*     */     } 
/* 166 */     boolean direct = (FastMath.sin(thisLine.getAngle() - otherLine.getAngle()) < 0.0D);
/* 167 */     Vector1D x = thisLine.toSubSpace(crossing);
/* 168 */     return getRemainingRegion().side((Hyperplane)new OrientedPoint(x, direct));
/*     */   }
/*     */   
/*     */   public SubHyperplane.SplitSubHyperplane<Euclidean2D> split(Hyperplane<Euclidean2D> hyperplane) {
/* 176 */     Line thisLine = (Line)getHyperplane();
/* 177 */     Line otherLine = (Line)hyperplane;
/* 178 */     Vector2D crossing = thisLine.intersection(otherLine);
/* 180 */     if (crossing == null) {
/* 182 */       double global = otherLine.getOffset(thisLine);
/* 183 */       return (global < -1.0E-10D) ? new SubHyperplane.SplitSubHyperplane(null, (SubHyperplane)this) : new SubHyperplane.SplitSubHyperplane((SubHyperplane)this, null);
/*     */     } 
/* 189 */     boolean direct = (FastMath.sin(thisLine.getAngle() - otherLine.getAngle()) < 0.0D);
/* 190 */     Vector1D x = thisLine.toSubSpace(crossing);
/* 191 */     SubOrientedPoint subOrientedPoint1 = (new OrientedPoint(x, !direct)).wholeHyperplane();
/* 192 */     SubOrientedPoint subOrientedPoint2 = (new OrientedPoint(x, direct)).wholeHyperplane();
/* 194 */     BSPTree<Euclidean1D> splitTree = getRemainingRegion().getTree(false).split((SubHyperplane)subOrientedPoint2);
/* 195 */     BSPTree<Euclidean1D> plusTree = getRemainingRegion().isEmpty(splitTree.getPlus()) ? new BSPTree(Boolean.FALSE) : new BSPTree((SubHyperplane)subOrientedPoint1, new BSPTree(Boolean.FALSE), splitTree.getPlus(), null);
/* 199 */     BSPTree<Euclidean1D> minusTree = getRemainingRegion().isEmpty(splitTree.getMinus()) ? new BSPTree(Boolean.FALSE) : new BSPTree((SubHyperplane)subOrientedPoint2, new BSPTree(Boolean.FALSE), splitTree.getMinus(), null);
/* 204 */     return new SubHyperplane.SplitSubHyperplane((SubHyperplane)new SubLine(thisLine.copySelf(), (Region<Euclidean1D>)new IntervalsSet(plusTree)), (SubHyperplane)new SubLine(thisLine.copySelf(), (Region<Euclidean1D>)new IntervalsSet(minusTree)));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\twod\SubLine.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */