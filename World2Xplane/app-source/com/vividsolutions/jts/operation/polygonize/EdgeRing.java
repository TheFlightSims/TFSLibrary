/*     */ package com.vividsolutions.jts.operation.polygonize;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.planargraph.DirectedEdge;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ class EdgeRing {
/*     */   private GeometryFactory factory;
/*     */   
/*     */   public static EdgeRing findEdgeRingContaining(EdgeRing testEr, List shellList) {
/*  67 */     LinearRing testRing = testEr.getRing();
/*  68 */     Envelope testEnv = testRing.getEnvelopeInternal();
/*  69 */     Coordinate testPt = testRing.getCoordinateN(0);
/*  71 */     EdgeRing minShell = null;
/*  72 */     Envelope minShellEnv = null;
/*  73 */     for (Iterator<EdgeRing> it = shellList.iterator(); it.hasNext(); ) {
/*  74 */       EdgeRing tryShell = it.next();
/*  75 */       LinearRing tryShellRing = tryShell.getRing();
/*  76 */       Envelope tryShellEnv = tryShellRing.getEnvelopeInternal();
/*  79 */       if (tryShellEnv.equals(testEnv))
/*     */         continue; 
/*  81 */       if (!tryShellEnv.contains(testEnv))
/*     */         continue; 
/*  83 */       testPt = CoordinateArrays.ptNotInList(testRing.getCoordinates(), tryShellRing.getCoordinates());
/*  84 */       boolean isContained = false;
/*  85 */       if (CGAlgorithms.isPointInRing(testPt, tryShellRing.getCoordinates()))
/*  86 */         isContained = true; 
/*  89 */       if (isContained && (
/*  90 */         minShell == null || minShellEnv.contains(tryShellEnv))) {
/*  92 */         minShell = tryShell;
/*  93 */         minShellEnv = minShell.getRing().getEnvelopeInternal();
/*     */       } 
/*     */     } 
/*  97 */     return minShell;
/*     */   }
/*     */   
/*     */   public static Coordinate ptNotInList(Coordinate[] testPts, Coordinate[] pts) {
/* 111 */     for (int i = 0; i < testPts.length; i++) {
/* 112 */       Coordinate testPt = testPts[i];
/* 113 */       if (!isInList(testPt, pts))
/* 114 */         return testPt; 
/*     */     } 
/* 116 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean isInList(Coordinate pt, Coordinate[] pts) {
/* 131 */     for (int i = 0; i < pts.length; i++) {
/* 132 */       if (pt.equals(pts[i]))
/* 133 */         return true; 
/*     */     } 
/* 135 */     return false;
/*     */   }
/*     */   
/* 140 */   private List deList = new ArrayList();
/*     */   
/* 143 */   private LinearRing ring = null;
/*     */   
/* 145 */   private Coordinate[] ringPts = null;
/*     */   
/*     */   private List holes;
/*     */   
/*     */   public EdgeRing(GeometryFactory factory) {
/* 150 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public void add(DirectedEdge de) {
/* 159 */     this.deList.add(de);
/*     */   }
/*     */   
/*     */   public boolean isHole() {
/* 170 */     LinearRing ring = getRing();
/* 171 */     return CGAlgorithms.isCCW(ring.getCoordinates());
/*     */   }
/*     */   
/*     */   public void addHole(LinearRing hole) {
/* 179 */     if (this.holes == null)
/* 180 */       this.holes = new ArrayList(); 
/* 181 */     this.holes.add(hole);
/*     */   }
/*     */   
/*     */   public Polygon getPolygon() {
/* 191 */     LinearRing[] holeLR = null;
/* 192 */     if (this.holes != null) {
/* 193 */       holeLR = new LinearRing[this.holes.size()];
/* 194 */       for (int i = 0; i < this.holes.size(); i++)
/* 195 */         holeLR[i] = this.holes.get(i); 
/*     */     } 
/* 198 */     Polygon poly = this.factory.createPolygon(this.ring, holeLR);
/* 199 */     return poly;
/*     */   }
/*     */   
/*     */   public boolean isValid() {
/* 209 */     getCoordinates();
/* 210 */     if (this.ringPts.length <= 3)
/* 210 */       return false; 
/* 211 */     getRing();
/* 212 */     return this.ring.isValid();
/*     */   }
/*     */   
/*     */   private Coordinate[] getCoordinates() {
/* 223 */     if (this.ringPts == null) {
/* 224 */       CoordinateList coordList = new CoordinateList();
/* 225 */       for (Iterator<DirectedEdge> i = this.deList.iterator(); i.hasNext(); ) {
/* 226 */         DirectedEdge de = i.next();
/* 227 */         PolygonizeEdge edge = (PolygonizeEdge)de.getEdge();
/* 228 */         addEdge(edge.getLine().getCoordinates(), de.getEdgeDirection(), coordList);
/*     */       } 
/* 230 */       this.ringPts = coordList.toCoordinateArray();
/*     */     } 
/* 232 */     return this.ringPts;
/*     */   }
/*     */   
/*     */   public LineString getLineString() {
/* 244 */     getCoordinates();
/* 245 */     return this.factory.createLineString(this.ringPts);
/*     */   }
/*     */   
/*     */   public LinearRing getRing() {
/* 255 */     if (this.ring != null)
/* 255 */       return this.ring; 
/* 256 */     getCoordinates();
/* 257 */     if (this.ringPts.length < 3)
/* 257 */       System.out.println(this.ringPts); 
/*     */     try {
/* 259 */       this.ring = this.factory.createLinearRing(this.ringPts);
/* 261 */     } catch (Exception ex) {
/* 262 */       System.out.println(this.ringPts);
/*     */     } 
/* 264 */     return this.ring;
/*     */   }
/*     */   
/*     */   private static void addEdge(Coordinate[] coords, boolean isForward, CoordinateList coordList) {
/* 269 */     if (isForward) {
/* 270 */       for (int i = 0; i < coords.length; i++)
/* 271 */         coordList.add(coords[i], false); 
/*     */     } else {
/* 275 */       for (int i = coords.length - 1; i >= 0; i--)
/* 276 */         coordList.add(coords[i], false); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\polygonize\EdgeRing.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */