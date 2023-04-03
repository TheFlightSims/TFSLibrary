/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class SegmentNode implements Comparable {
/*     */   private final NodedSegmentString segString;
/*     */   
/*     */   public final Coordinate coord;
/*     */   
/*     */   public final int segmentIndex;
/*     */   
/*     */   private final int segmentOctant;
/*     */   
/*     */   private final boolean isInterior;
/*     */   
/*     */   public SegmentNode(NodedSegmentString segString, Coordinate coord, int segmentIndex, int segmentOctant) {
/*  53 */     this.segString = segString;
/*  54 */     this.coord = new Coordinate(coord);
/*  55 */     this.segmentIndex = segmentIndex;
/*  56 */     this.segmentOctant = segmentOctant;
/*  57 */     this.isInterior = !coord.equals2D(segString.getCoordinate(segmentIndex));
/*     */   }
/*     */   
/*     */   public Coordinate getCoordinate() {
/*  67 */     return this.coord;
/*     */   }
/*     */   
/*     */   public boolean isInterior() {
/*  70 */     return this.isInterior;
/*     */   }
/*     */   
/*     */   public boolean isEndPoint(int maxSegmentIndex) {
/*  74 */     if (this.segmentIndex == 0 && !this.isInterior)
/*  74 */       return true; 
/*  75 */     if (this.segmentIndex == maxSegmentIndex)
/*  75 */       return true; 
/*  76 */     return false;
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/*  86 */     SegmentNode other = (SegmentNode)obj;
/*  88 */     if (this.segmentIndex < other.segmentIndex)
/*  88 */       return -1; 
/*  89 */     if (this.segmentIndex > other.segmentIndex)
/*  89 */       return 1; 
/*  91 */     if (this.coord.equals2D(other.coord))
/*  91 */       return 0; 
/*  93 */     return SegmentPointComparator.compare(this.segmentOctant, this.coord, other.coord);
/*     */   }
/*     */   
/*     */   public void print(PrintStream out) {
/*  99 */     out.print(this.coord);
/* 100 */     out.print(" seg # = " + this.segmentIndex);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\SegmentNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */