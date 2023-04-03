/*     */ package com.vividsolutions.jts.geomgraph;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class EdgeIntersectionList {
/*  49 */   private Map nodeMap = new TreeMap<>();
/*     */   
/*     */   Edge edge;
/*     */   
/*     */   public EdgeIntersectionList(Edge edge) {
/*  54 */     this.edge = edge;
/*     */   }
/*     */   
/*     */   public EdgeIntersection add(Coordinate intPt, int segmentIndex, double dist) {
/*  64 */     EdgeIntersection eiNew = new EdgeIntersection(intPt, segmentIndex, dist);
/*  65 */     EdgeIntersection ei = (EdgeIntersection)this.nodeMap.get(eiNew);
/*  66 */     if (ei != null)
/*  67 */       return ei; 
/*  69 */     this.nodeMap.put(eiNew, eiNew);
/*  70 */     return eiNew;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/*  78 */     return this.nodeMap.values().iterator();
/*     */   }
/*     */   
/*     */   public boolean isIntersection(Coordinate pt) {
/*  88 */     for (Iterator<EdgeIntersection> it = iterator(); it.hasNext(); ) {
/*  89 */       EdgeIntersection ei = it.next();
/*  90 */       if (ei.coord.equals(pt))
/*  91 */         return true; 
/*     */     } 
/*  93 */     return false;
/*     */   }
/*     */   
/*     */   public void addEndpoints() {
/* 101 */     int maxSegIndex = this.edge.pts.length - 1;
/* 102 */     add(this.edge.pts[0], 0, 0.0D);
/* 103 */     add(this.edge.pts[maxSegIndex], maxSegIndex, 0.0D);
/*     */   }
/*     */   
/*     */   public void addSplitEdges(List<Edge> edgeList) {
/* 117 */     addEndpoints();
/* 119 */     Iterator<EdgeIntersection> it = iterator();
/* 121 */     EdgeIntersection eiPrev = it.next();
/* 122 */     while (it.hasNext()) {
/* 123 */       EdgeIntersection ei = it.next();
/* 124 */       Edge newEdge = createSplitEdge(eiPrev, ei);
/* 125 */       edgeList.add(newEdge);
/* 127 */       eiPrev = ei;
/*     */     } 
/*     */   }
/*     */   
/*     */   Edge createSplitEdge(EdgeIntersection ei0, EdgeIntersection ei1) {
/* 138 */     int npts = ei1.segmentIndex - ei0.segmentIndex + 2;
/* 140 */     Coordinate lastSegStartPt = this.edge.pts[ei1.segmentIndex];
/* 145 */     boolean useIntPt1 = (ei1.dist > 0.0D || !ei1.coord.equals2D(lastSegStartPt));
/* 146 */     if (!useIntPt1)
/* 147 */       npts--; 
/* 150 */     Coordinate[] pts = new Coordinate[npts];
/* 151 */     int ipt = 0;
/* 152 */     pts[ipt++] = new Coordinate(ei0.coord);
/* 153 */     for (int i = ei0.segmentIndex + 1; i <= ei1.segmentIndex; i++)
/* 154 */       pts[ipt++] = this.edge.pts[i]; 
/* 156 */     if (useIntPt1)
/* 156 */       pts[ipt] = ei1.coord; 
/* 157 */     return new Edge(pts, new Label(this.edge.label));
/*     */   }
/*     */   
/*     */   public void print(PrintStream out) {
/* 162 */     out.println("Intersections:");
/* 163 */     for (Iterator<EdgeIntersection> it = iterator(); it.hasNext(); ) {
/* 164 */       EdgeIntersection ei = it.next();
/* 165 */       ei.print(out);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geomgraph\EdgeIntersectionList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */