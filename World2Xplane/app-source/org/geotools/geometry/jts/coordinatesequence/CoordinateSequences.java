/*     */ package org.geotools.geometry.jts.coordinatesequence;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.RobustDeterminant;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequences;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ 
/*     */ public class CoordinateSequences extends CoordinateSequences {
/*     */   public static boolean isCCW(CoordinateSequence ring) {
/*  57 */     int nPts = ring.size() - 1;
/*  60 */     double hiy = ring.getOrdinate(0, 1);
/*  61 */     int hiIndex = 0;
/*  62 */     for (int i = 1; i <= nPts; i++) {
/*  63 */       if (ring.getOrdinate(i, 1) > hiy) {
/*  64 */         hiy = ring.getOrdinate(i, 1);
/*  65 */         hiIndex = i;
/*     */       } 
/*     */     } 
/*  70 */     int iPrev = hiIndex;
/*     */     do {
/*  72 */       iPrev--;
/*  73 */       if (iPrev >= 0)
/*     */         continue; 
/*  73 */       iPrev = nPts;
/*  74 */     } while (equals2D(ring, iPrev, hiIndex) && iPrev != hiIndex);
/*  77 */     int iNext = hiIndex;
/*     */     do {
/*  79 */       iNext = (iNext + 1) % nPts;
/*  80 */     } while (equals2D(ring, iNext, hiIndex) && iNext != hiIndex);
/*  88 */     if (equals2D(ring, iPrev, hiIndex) || equals2D(ring, iNext, hiIndex) || equals2D(ring, iPrev, iNext))
/*  89 */       return false; 
/*  91 */     int disc = computeOrientation(ring, iPrev, hiIndex, iNext);
/* 102 */     boolean isCCW = false;
/* 103 */     if (disc == 0) {
/* 105 */       isCCW = (ring.getOrdinate(iPrev, 0) > ring.getOrdinate(iNext, 0));
/*     */     } else {
/* 108 */       isCCW = (disc > 0);
/*     */     } 
/* 110 */     return isCCW;
/*     */   }
/*     */   
/*     */   private static boolean equals2D(CoordinateSequence cs, int i, int j) {
/* 114 */     return (cs.getOrdinate(i, 0) == cs.getOrdinate(j, 0) && cs.getOrdinate(i, 1) == cs.getOrdinate(j, 1));
/*     */   }
/*     */   
/*     */   public static int computeOrientation(CoordinateSequence cs, int p1, int p2, int q) {
/* 122 */     double p1x = cs.getOrdinate(p1, 0);
/* 123 */     double p1y = cs.getOrdinate(p1, 1);
/* 124 */     double p2x = cs.getOrdinate(p2, 0);
/* 125 */     double p2y = cs.getOrdinate(p2, 1);
/* 126 */     double qx = cs.getOrdinate(q, 0);
/* 127 */     double qy = cs.getOrdinate(q, 1);
/* 128 */     double dx1 = p2x - p1x;
/* 129 */     double dy1 = p2y - p1y;
/* 130 */     double dx2 = qx - p2x;
/* 131 */     double dy2 = qy - p2y;
/* 132 */     return RobustDeterminant.signOfDet2x2(dx1, dy1, dx2, dy2);
/*     */   }
/*     */   
/*     */   public static int coordinateDimension(Geometry g) {
/* 147 */     if (g instanceof Point)
/* 148 */       return coordinateDimension(((Point)g).getCoordinateSequence()); 
/* 149 */     if (g instanceof LineString)
/* 150 */       return coordinateDimension(((LineString)g).getCoordinateSequence()); 
/* 151 */     if (g instanceof Polygon)
/* 152 */       return coordinateDimension(((Polygon)g).getExteriorRing().getCoordinateSequence()); 
/* 156 */     CoordinateSequence cs = CoordinateSequenceFinder.find(g);
/* 157 */     return coordinateDimension(cs);
/*     */   }
/*     */   
/*     */   public static int coordinateDimension(CoordinateSequence seq) {
/* 176 */     if (seq == null)
/* 176 */       return 3; 
/* 178 */     int dim = seq.getDimension();
/* 179 */     if (dim != 3)
/* 180 */       return dim; 
/* 185 */     if (seq instanceof com.vividsolutions.jts.geom.impl.CoordinateArraySequence && 
/* 186 */       seq.size() > 0) {
/* 187 */       if (Double.isNaN(seq.getOrdinate(0, 1)))
/* 188 */         return 1; 
/* 189 */       if (Double.isNaN(seq.getOrdinate(0, 2)))
/* 190 */         return 2; 
/*     */     } 
/* 193 */     return 3;
/*     */   }
/*     */   
/*     */   private static class CoordinateSequenceFinder implements CoordinateSequenceFilter {
/*     */     public static CoordinateSequence find(Geometry g) {
/* 199 */       CoordinateSequenceFinder finder = new CoordinateSequenceFinder();
/* 200 */       g.apply(finder);
/* 201 */       return finder.getSeq();
/*     */     }
/*     */     
/* 204 */     private CoordinateSequence firstSeqFound = null;
/*     */     
/*     */     public CoordinateSequence getSeq() {
/* 213 */       return this.firstSeqFound;
/*     */     }
/*     */     
/*     */     public void filter(CoordinateSequence seq, int i) {
/* 217 */       if (this.firstSeqFound == null)
/* 218 */         this.firstSeqFound = seq; 
/*     */     }
/*     */     
/*     */     public boolean isDone() {
/* 223 */       return (this.firstSeqFound != null);
/*     */     }
/*     */     
/*     */     public boolean isGeometryChanged() {
/* 227 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\coordinatesequence\CoordinateSequences.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */