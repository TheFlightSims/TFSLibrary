/*     */ package org.apache.commons.math3.geometry.euclidean.threed;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.math3.geometry.Vector;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Interval;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
/*     */ import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
/*     */ import org.apache.commons.math3.geometry.partitioning.Region;
/*     */ 
/*     */ public class SubLine {
/*     */   private final Line line;
/*     */   
/*     */   private final IntervalsSet remainingRegion;
/*     */   
/*     */   public SubLine(Line line, IntervalsSet remainingRegion) {
/*  44 */     this.line = line;
/*  45 */     this.remainingRegion = remainingRegion;
/*     */   }
/*     */   
/*     */   public SubLine(Vector3D start, Vector3D end) {
/*  53 */     this(new Line(start, end), buildIntervalSet(start, end));
/*     */   }
/*     */   
/*     */   public SubLine(Segment segment) {
/*  60 */     this(segment.getLine(), buildIntervalSet(segment.getStart(), segment.getEnd()));
/*     */   }
/*     */   
/*     */   public List<Segment> getSegments() {
/*  79 */     List<Interval> list = this.remainingRegion.asList();
/*  80 */     List<Segment> segments = new ArrayList<Segment>();
/*  82 */     for (Interval interval : list) {
/*  83 */       Vector3D start = this.line.toSpace((Vector<Euclidean1D>)new Vector1D(interval.getLower()));
/*  84 */       Vector3D end = this.line.toSpace((Vector<Euclidean1D>)new Vector1D(interval.getUpper()));
/*  85 */       segments.add(new Segment(start, end, this.line));
/*     */     } 
/*  88 */     return segments;
/*     */   }
/*     */   
/*     */   public Vector3D intersection(SubLine subLine, boolean includeEndPoints) {
/* 109 */     Vector3D v1D = this.line.intersection(subLine.line);
/* 112 */     Region.Location loc1 = this.remainingRegion.checkPoint((Vector)this.line.toSubSpace(v1D));
/* 115 */     Region.Location loc2 = subLine.remainingRegion.checkPoint((Vector)subLine.line.toSubSpace(v1D));
/* 117 */     if (includeEndPoints)
/* 118 */       return (loc1 != Region.Location.OUTSIDE && loc2 != Region.Location.OUTSIDE) ? v1D : null; 
/* 120 */     return (loc1 == Region.Location.INSIDE && loc2 == Region.Location.INSIDE) ? v1D : null;
/*     */   }
/*     */   
/*     */   private static IntervalsSet buildIntervalSet(Vector3D start, Vector3D end) {
/* 131 */     Line line = new Line(start, end);
/* 132 */     return new IntervalsSet(line.toSubSpace(start).getX(), line.toSubSpace(end).getX());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\geometry\euclidean\threed\SubLine.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */